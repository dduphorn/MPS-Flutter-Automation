# Dead legacy code removal

**Branch:** `cursor/dead-code-removal-ede8`  
**Date:** 2026-11-02  
**Method:** Static reachability from all `@Test` entry classes (14 classes, including `UserRole_TestCases` and unit tests), plus `@Listeners` types (`EmailableReporter2`, `EmailableReporter3`, `ListenersClass`) and their transitive references. Commented suite XML was not used as a signal for liveness. Reflection / keyword dispatch in `Common*` libraries was spot-checked; nothing removed appeared in those paths.

---

## Removed

### Java (`AutomationSolution/`)

| Item | Reason |
|------|--------|
| `AutomationCode/CVS_reader.java` | No references anywhere in the repo; KPI CSV helper never wired to tests or common libraries. |
| `AutomationCode/CommonUserRole.java` | Never instantiated; SentryLink role tests use `CommonWeb.UpdateErrorMessageWithPivotalData` instead. |
| `AutomationCode/Utilities.java` | `TimeConversion` helper never referenced. |
| `AutomationCode/TestRails.java` | Entire file commented out; TestRail integration disabled. |
| `AutomationCode/TestNGCustomReportListener.java` | Entire file commented out; not registered on any `@Listeners`. |
| `AutomationCode/invocationListener.java` | Legacy listener stub (package commented, unused by TestNG). |
| `snippet/Snippet.java` | Empty IDE snippet file. |
| `AutomationCode/ForwardPortOwnerTest.java` | Standalone `main()` harness; not part of TestNG graph (logic covered by `CommonANDROID_Flutter` production code). |
| `AutomationCode/FreshInstallCommandTest.java` | Standalone `main()` harness for uninstall command strings. |
| `AutomationCode/MeterConnectRetryTest.java` | Standalone `main()` harness for `Meter.shouldRetryMeterConnect`. |

### Resources and vendored trees

| Item | Reason |
|------|--------|
| `AndroidHardware/` (Galaxy S6/S7 Genymotion skins) | No references from Java, XML, or scripts in this repo. |
| `Batch/launcher.sh` | Selenium Grid 2.x launcher; not referenced by Maven or automation code. |
| `Lib/launch-selenuim.sh`, `Lib/selenium-launcher.sh` | Legacy local Grid launchers; not referenced by code. |
| `Lib/selenium-java-3.141.5/`, `Lib/selenium-java-4.9.0/` | Vendored CHANGELOG/LICENSE only; build uses Maven Central Selenium 4.41. |
| `Merge Documents/` | Internal PNG screenshots; no code references. |
| `PythonCommands/PythonCommands.txt` | Documentation only; meter scripts live under `Lib/PythonScripts/` and are still referenced from `Meter.java`. |
| `testrail-api-java-client-master/` | Vendored client; not on Maven compile classpath and `TestRails.java` removed/disabled. |
| `testrail-api-master/` | Vendored multi-language TestRail samples; unused by this project. |

### Maven dependencies (`pom.xml`)

| Dependency | Reason |
|------------|--------|
| `selenium-htmlunit-driver` | No HtmlUnit usage in `AutomationSolution` (only a commented import in `SOAP.java`). |
| `opentelemetry-exporter-jaeger`, `grpc-netty` | No OpenTelemetry usage in automation sources; were unused direct deps. |
| `guice` | No `com.google.inject` usage in automation sources. |
| `junit-jupiter-api` | No JUnit tests in `AutomationSolution` (TestNG only). |
| **Added:** `io.netty:netty-handler` | Explicit compile dependency for `io.netty.handler.timeout.TimeoutException` used in Flutter Android code (previously pulled transitively via `grpc-netty`). |

---

## Probably dead but kept (owner confirmation)

| Item | Why uncertain |
|------|----------------|
| `ImgurClient_Old.java` | Superseded by `ImgurClient.java` (OkHttp) but still used in many `@AfterMethod` blocks and Sentry Health kiosk tests. |
| `CompareMeterSettings.java` | Only called from `UserRole_TestCases`; duplicate of commented `Meter.METER_CompareMeterSettingsDual`. |
| `UserRole_TestCases.java` | 43 `@Test` methods (`UserTest` group) but **not referenced** in any checked-in `TestSuites/` or `Flutter_*.xml` — may run from external Jenkins/IDE config. **Not removed** (live `@Test` methods). |
| `SentryHealth.java` + related `@Test` methods in `TestCases.java` | Health-kiosk meter simulation; narrow lab use. |
| `PaymentSite.java` | Used only from payment-site URL smoke tests in `TestCases.java`. |
| `Lib/google-api-services-gmail-v1-rev62-1.22.0.jar.properties` | Orphan metadata; Gmail tests use live API via `Gmail.java`. |
| Duplicate mail stacks (`javax.mail` + `jakarta.mail`) | Both may be required by different classes; not trimmed in this pass. |
| Large commented blocks inside `CommonWeb`, `CommonANDROID`, `Meter`, etc. | May document retired flows; method-level deletion needs product owner review. |
| Legacy `TestSuites/*.xml` with wrong class names | Broken suites kept as-is per scope (suite XML not edited). |

---

## Build verification

```text
/tmp/apache-maven-3.9.9/bin/mvn compile test-compile
```

**Result:** SUCCESS (Java 21) after removals and `netty-handler` addition.

---

## Scale (approximate)

| Metric | Value |
|--------|------:|
| Files deleted | 164 |
| Lines removed (all files) | ~16,544 |
| Java/XML/POM lines removed | ~7,485 |
| Java classes removed | 10 |
| Maven dependencies removed | 5 (+ 1 explicit netty addition) |
