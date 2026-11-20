package AutomationCode;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * Keeps overnight evidence that the next run would overwrite (test-output reports, appium_full.log, Reporter output).
 *
 * Layout (one folder per JVM run, git-ignored):
 *   test-output-archive/yyyyMMdd_HHmmss/
 *       I3078F_..._FAILURE.txt            Reporter output + stack trace of every test (small, always written)
 *       I3078F_..._appium_full.log        appium_full.log of a failed/skipped test (see ARCHIVE_APPIUM_LOG_FOR_PASSED_TESTS)
 *       test-output/...                   report files written during this run (copied by a JVM shutdown hook, i.e. AFTER
 *                                         TestNG's reporters/listeners have written emailable/custom/testng-results)
 *       appium_full.log                   last appium_full.log at the end of the run
 *
 * Every method swallows its own exceptions so archiving can never fail or slow a test meaningfully.
 */
public final class RunArtifactArchiver
{
	/** Set to true to also keep appium_full.log of passed tests (about 1 MB per test). */
	public static boolean ARCHIVE_APPIUM_LOG_FOR_PASSED_TESTS = false;
	/** Set to false to switch all archiving off. */
	public static boolean ENABLED = true;

	public static final String ARCHIVE_DIR_NAME = "test-output-archive";
	private static final DateTimeFormatter STAMP = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
	private static final long RUN_START_MILLIS = System.currentTimeMillis();
	private static final String RUN_STAMP = LocalDateTime.now().format(STAMP);
	private static volatile boolean blnShutdownHookRegistered = false;

	private RunArtifactArchiver() {}

	private static Path projectDir() {return Paths.get(System.getProperty("user.dir"));}

	public static Path runArchiveDir() {return projectDir().resolve(ARCHIVE_DIR_NAME).resolve(RUN_STAMP);}

	/** Call from @AfterMethod. Writes the test's Reporter output (and appium_full.log when it did not pass). */
	public static void archiveTestResult(ITestResult result, String strTestName)
	{
		if (!ENABLED) {return;}
		try
		{
			registerShutdownHook();
			Path pDir = runArchiveDir();
			Files.createDirectories(pDir);
			String strStatus = statusName(result);
			String strBase = safe(strTestName) + "_" + LocalDateTime.now().format(STAMP) + "_" + strStatus;
			StringBuilder sb = new StringBuilder();
			sb.append("Test: ").append(strTestName).append(System.lineSeparator());
			sb.append("Status: ").append(strStatus).append(System.lineSeparator());
			if (result != null)
			{
				sb.append("Start: ").append(new java.util.Date(result.getStartMillis())).append("  End: ").append(new java.util.Date(result.getEndMillis())).append(System.lineSeparator());
				if (result.getThrowable() != null)
				{
					StringWriter sw = new StringWriter();
					result.getThrowable().printStackTrace(new PrintWriter(sw));
					sb.append("Throwable:").append(System.lineSeparator()).append(sw).append(System.lineSeparator());
				}
				List<String> lstOutput = Reporter.getOutput(result);
				sb.append("---------------- Reporter output ----------------").append(System.lineSeparator());
				for (String strLine : lstOutput) {sb.append(strLine).append(System.lineSeparator());}
			}
			Files.write(pDir.resolve(strBase + ".txt"), sb.toString().getBytes(StandardCharsets.UTF_8));
			boolean blnPassed = result != null && result.getStatus() == ITestResult.SUCCESS;
			if (!blnPassed || ARCHIVE_APPIUM_LOG_FOR_PASSED_TESTS)
			{
				Path pAppium = projectDir().resolve("appium_full.log");
				if (Files.isRegularFile(pAppium)) {Files.copy(pAppium, pDir.resolve(strBase + "_appium_full.log"), StandardCopyOption.REPLACE_EXISTING);}
			}
		}
		catch (Throwable t) {System.out.println("RunArtifactArchiver.archiveTestResult failed: " + t);}
	}

	/** Registers (once per JVM) the hook that copies this run's report files after TestNG has finished writing them. */
	public static synchronized void registerShutdownHook()
	{
		if (blnShutdownHookRegistered || !ENABLED) {return;}
		blnShutdownHookRegistered = true;
		try {Runtime.getRuntime().addShutdownHook(new Thread(RunArtifactArchiver::archiveReportsNow, "RunArtifactArchiver"));}
		catch (Throwable t) {System.out.println("RunArtifactArchiver could not register shutdown hook: " + t);}
	}

	/** Copies test-output files modified during this run (skips test-output/old) and the current appium_full.log. */
	public static void archiveReportsNow()
	{
		try
		{
			Path pDir = runArchiveDir();
			Path pReports = projectDir().resolve("test-output");
			if (Files.isDirectory(pReports))
			{
				Path pTarget = pDir.resolve("test-output");
				Files.walkFileTree(pReports, new SimpleFileVisitor<Path>()
				{
					@Override public FileVisitResult preVisitDirectory(Path d, BasicFileAttributes a)
					{
						String strName = d.getFileName() == null ? "" : d.getFileName().toString();
						return (!d.equals(pReports) && strName.equals("old")) ? FileVisitResult.SKIP_SUBTREE : FileVisitResult.CONTINUE;
					}
					@Override public FileVisitResult visitFile(Path f, BasicFileAttributes a) throws IOException
					{
						if (a.lastModifiedTime().toMillis() >= RUN_START_MILLIS - 1000)
						{
							Path pTo = pTarget.resolve(pReports.relativize(f).toString());
							Files.createDirectories(pTo.getParent());
							Files.copy(f, pTo, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
						}
						return FileVisitResult.CONTINUE;
					}
				});
			}
			Path pAppium = projectDir().resolve("appium_full.log");
			if (Files.isRegularFile(pAppium) && Files.getLastModifiedTime(pAppium).toMillis() >= RUN_START_MILLIS - 1000)
			{
				Files.createDirectories(pDir);
				Files.copy(pAppium, pDir.resolve("appium_full.log"), StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (Throwable t) {System.out.println("RunArtifactArchiver.archiveReportsNow failed: " + t);}
	}

	private static String statusName(ITestResult result)
	{
		if (result == null) {return "UNKNOWN";}
		switch (result.getStatus())
		{
			case ITestResult.SUCCESS: return "SUCCESS";
			case ITestResult.FAILURE: return "FAILURE";
			case ITestResult.SKIP: return "SKIP";
			default: return "STATUS" + result.getStatus();
		}
	}

	private static String safe(String s) {return s == null ? "unknown" : s.replaceAll("[^A-Za-z0-9_.-]", "_");}

	/** For a quick manual check: java AutomationCode.RunArtifactArchiver prints the archive folder. */
	public static void main(String[] args) {System.out.println(new File(runArchiveDir().toString()).getAbsolutePath());}
}
