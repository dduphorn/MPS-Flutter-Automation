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
| **`AutomationCode/SentryHealth.java`** | Health-kiosk helper class; only referenced by removed `S1001`–`S1007` tests (owner confirmed never run). |
| **`AutomationCode/ImgurClient_Old.java`** | After health-kiosk removal, only used for `getLastUrl()` in `@AfterMethod` blocks; migrated to `ImgurClient.getLastUrl()` (same singleton state as screenshot uploads). |
| **`TestCases.java` — seven `@Test` methods** | `S1001_RU_VT_SA_NS_MOFF_NC_VHS` through `S1007_UU_VT_SA_NS_MOFF_NC_VHS` (Sentry Health kiosk flows). |
| **`GlobalClass.java` — device profiles** | `strDeviceId` cases `Health Kiosk Blue`, `SH72`, `SH79` (only used by health-kiosk tests). |
| **`CommonWeb.java` — locators/navigation** | `"Health Sessions"` page navigation and table XPath map (only used by `SentryHealth` validation). |

**Imgur migration (owner standard: use `ImgurClient` only):** The team uses **`ImgurClient.java`** (OkHttp + RapidAPI) for all screenshot uploads. **`ImgurClient_Old.java` was deleted** on this branch. Every former `ImgurClient_Old` caller was migrated:

| Former usage | Migration |
|--------------|-----------|
| `@AfterMethod` / teardown `objDictionary.put("link", …)` in `TestCases`, `*_TestCases*`, `Crawler_SG`, `PaymentSite` | `ImgurClient.getLastUrl()` — same static field updated by `UploadImage()` in `CommonWeb`, `CommonANDROID*`, `CommonIOS*`, `Meter` |
| Health-kiosk `IMGUR_CopyImageFromImgurToHealthKiosk` | Removed with health-kiosk tests (not reimplemented on Old client) |
| Comment in `Meter.java` referencing Old upload | Left as comment only; live path uses `ImgurClient.getInstance().UploadImage` |

**Behavior:** Teardown links now read the same `lastUrl` that upload paths set on `ImgurClient` (previously Old and New were separate singletons, so links could be stale). **Dependency:** `unirest-java` removed; `httpclient` added explicitly for `HttpConnections` (formerly transitive via Unirest).


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

**Suite XML:** No uncommented `<include>` entries referenced `S100*` or health-kiosk tests in any checked-in suite file; commented catalog lines were left unchanged per repo convention.

### Maven dependencies (`pom.xml`)

| Dependency | Reason |
|------------|--------|
| `selenium-htmlunit-driver` | No HtmlUnit usage in `AutomationSolution` (only a commented import in `SOAP.java`). |
| `opentelemetry-exporter-jaeger`, `grpc-netty` | No OpenTelemetry usage in automation sources; were unused direct deps. |
| `guice` | No `com.google.inject` usage in automation sources. |
| `junit-jupiter-api` | No JUnit tests in `AutomationSolution` (TestNG only). |
| `com.mashape.unirest:unirest-java` | Only used by removed `ImgurClient_Old.java`. |
| **Added:** `io.netty:netty-handler` | Explicit compile dependency for `io.netty.handler.timeout.TimeoutException` in Flutter Android code (previously transitive via `grpc-netty`). |
| **Added:** `org.apache.httpcomponents:httpclient` | Explicit dependency for `HttpConnections` / lot tests (previously transitive via Unirest). |

---

## Probably dead but kept (owner confirmation)

| Item | Why uncertain |
|------|----------------|
| `CompareMeterSettings.java` | Only called from `UserRole_TestCases`; duplicate of commented `Meter.METER_CompareMeterSettingsDual`. |
| `UserRole_TestCases.java` | 43 `@Test` methods (`UserTest` group) but **not referenced** in any checked-in `TestSuites/` or `Flutter_*.xml` — may run from external Jenkins/IDE config. **Not removed** (live `@Test` methods). |
| `PaymentSite.java` | Used only from payment-site URL smoke tests in `TestCases.java`. |
| `Lib/google-api-services-gmail-v1-rev62-1.22.0.jar.properties` | Orphan metadata; Gmail tests use live API via `Gmail.java`. |
| Duplicate mail stacks (`javax.mail` + `jakarta.mail`) | Both may be required by different classes; not trimmed in this pass. |
| SentryLink UI strings mentioning `HealthKiosk` / `HEALTHKIOSK` in `UserRole_TestCases` expected listbox values | Product UI still exposes health kiosk device types; not health-kiosk test code. |
| Large commented blocks inside `CommonWeb`, `CommonANDROID`, `Meter`, etc. | May document retired flows; method-level deletion needs product owner review. |
| Legacy `TestSuites/*.xml` with wrong class names | Broken suites kept as-is per scope (suite XML not edited). |

---

## Build verification

```text
/tmp/apache-maven-3.9.9/bin/mvn compile test-compile
```

**Result:** SUCCESS (Java 21) after all removals, Imgur migration, and dependency adjustments.

---

## Scale (approximate)

| Metric | Value |
|--------|------:|
| Files deleted (first pass) | 164 |
| Lines removed (first pass, all files) | ~16,544 |
| **Additional pass (health kiosk + Imgur)** | ~1,730 lines across 17 files; +2 Java classes deleted (`SentryHealth`, `ImgurClient_Old`); 7 `@Test` methods removed |
| Java classes removed (total) | **12** |
| Maven dependencies removed (total) | **6** (+ `netty-handler`, `httpclient` added) |
