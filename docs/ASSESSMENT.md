# Flutter Automated Test Architecture — Assessment

**Date:** 2026-11-02  
**Scope:** Assessment only (no framework or test code changes).  
**Repository:** MPS / SentryLink mobile E2E automation (Java, TestNG, Appium, Selenium).

---

## Executive summary

This repo is a **mature, monolithic Java E2E framework** that automates MPS parking products end-to-end: **native and Flutter mobile apps** (Sentry Mobile / Consumer App, PEO / Mobile Officer), **physical and virtual meters**, and **SentryLink / Launchpad web** (admin, violations, payment site, session history). Tests are **keyword-driven**: large `Common*` libraries expose `PopulateAction`, `ClickButton`, and `VerificationPoint*` APIs backed by implicit page/locator maps, not a classic Page Object Model.

There are **~1,450+ `@Test` methods** across platform classes, plus **Global Pay** and **SentryLink role** suites in `TestCases.java`. **Flutter coverage exists** (`*_Flutter.java`, `CommonANDROID_Flutter.java`, `IOS_Common_Flutter.java`) but **Flutter TestNG suites are almost entirely commented out**; only one Flutter Android case is active in `Flutter_Android_CA_Regression.xml`. Legacy suites under `TestSuites/` still reference **removed class names** (`PackageTestCases.ClassTestCases`) and **obsolete test method names** (`A20_*` vs current `A2019_*` / `A2xxxF_*`), so **CI/smoke configuration and code have diverged**.

**Build:** `mvn compile` **succeeds** on Java 21 with current `pom.xml` (verified in this environment).

**Highest-impact next work:** (1) repair suite wiring and a minimal Flutter smoke pipeline, (2) externalize secrets and environment config, (3) enable and run the new Flutter negative/resilience tests (A5204F–A5219F), (4) reduce `Thread.sleep` / brittle XPath on Flutter semantics, (5) close iOS Flutter and PEO Flutter execution gaps vs Android.

---

## 1. Architecture overview

### 1.1 Repository layout

| Area | Purpose |
|------|---------|
| `AutomationSolution/AutomationCode/` | All framework and test Java (~58 production classes, ~250k LOC) |
| `TestSuites/` | Legacy TestNG XML (native Android/iOS/PEO/meter, Global Pay, examples) |
| `Flutter_*.xml` | Flutter-focused TestNG suites (CA, PEO, iOS, Lot) |
| `pom.xml` | Maven build: Java 21, source root `AutomationSolution`, TestNG 6.14, Appium 10, Selenium 4.41 |
| `Lib/` | Vendored Selenium JARs, Python meter scripts, shell launchers |
| `AndroidHardware/` | Genymotion-style device skins (legacy emulator tooling) |
| `Batch/launcher.sh` | Selenium Grid 2.x hub/node launcher (very old `selenium-server-standalone-2.52.0.jar`) |
| `testrail-api-*` | Vendored TestRail client (mostly unused; `TestRails.java` is commented out) |

There is **no root README** and **no in-repo GitHub Actions**; execution is assumed to run on **Jenkins / lab machines** (hard-coded users `jenkins`, `mpsadmin`, internal IPs).

### 1.2 Architectural pattern (not classic POM)

```
TestNG @Test method (Android_TestCases_Flutter, etc.)
    → BeforeMethod: GlobalClass.AddUserVariablesToDictionaryObject (objDictionary)
    → CommonANDROID_Flutter / CommonIOS / CommonWeb "actions"
    → *ParkingSessions / Meter / HttpConnections / Database (orchestration & back-end checks)
    → AfterMethod: screenshots, meter logs, optional DB write, cleanup
```

- **Session / state:** `Map<String, String> objDictionary` passed everywhere (URLs, credentials, meter hosts, APK names, bug IDs, timings).
- **Locators:** Encoded in giant switch/map logic inside `CommonWeb`, `CommonANDROID`, `CommonANDROID_Flutter` (page name + control label → XPath, accessibility id, or table coordinates). Flutter UI is driven primarily via **`content-desc` / semantics** and XPath on `android.view.View`, not Appium Flutter Driver or `flutter:` finders.
- **Flows:** `Android_ParkingSessions`, `IOS_ParkingSessions`, `Meter_ParkingSessions`, `PEO_ParkingSessions` implement multi-step scenarios; test method names encode steps (e.g. `FTFP0`, `PS1`, `VPSH`, `VICAE`, `VIAC`).
- **Drivers:** Appium `AndroidDriver` / `IOSDriver`, local or remote (`strRemotePath`), optional embedded Appium via `AppiumDriverLocalService` in Flutter common code. Web uses Chrome + `WebDriverManager`.
- **Hardware / meters:** SSH + Python scripts on meter hosts (`Meter.java`, `Lib/PythonScripts/`), ADB helpers (`ADB_Commands.java`) for airplane mode, Wi‑Fi, battery, cache corruption, etc.

### 1.3 Test entry classes

| Class | Role | ~`@Test` count |
|-------|------|----------------|
| `TestCases` | Umbrella: Global Pay (`GP*`), prod crawlers, SentryLink admin snippets, meter setup | 230 |
| `Android_TestCases` | Native Android Sentry Mobile | 193 |
| `Android_TestCases_Flutter` | Flutter Consumer App (Android) | 210 |
| `IOS_TestCases` | Native iOS | 169 |
| `IOS_TestCases_Flutter` | Flutter CA (iOS) | 162 |
| `PEO_TestCases` / `PEO_TestCases_Flutter` | PEO native / Flutter | 58 / 65 |
| `Meter_TestCases` | Meter-only scenarios | 229 |
| `Android_Lot_TestCases` | Lot / off-street variant | 47 |
| `Android_TuffPark_TestCases` | TuffPark product | 9 |
| `UserRole_TestCases` | SentryLink RBAC (`UserTest` group) | 43 |
| `Crawler_SG` | Staging crawl checks | 31 |
| `NegativeInputCasesTest` | Unit tests for negative-input helpers | 12 |

Each platform class **duplicates** `@BeforeMethod` / `@AfterMethod` / `@Listeners` boilerplate (~same as `TestCases`).

### 1.4 Configuration and environments

- **Environments:** `QA`, `SG` (staging), `PROD` — set via `strDeviceId` → `GlobalClass.GetMeterProperties()` (large switch with **internal IPs**, municipality names, rate block groups).
- **APKs / builds:** Defaults in `GlobalClass` (e.g. `SENTRYMOBILE-Staging-8.2.384.apk`); Flutter suites override with newer names (`FusionAIMobile-Staging-1.0.1.apk`, `MPSPEO-Flutter-Staging-2.0.18.apk`).
- **Credentials:** Derived from municipality + role (`strUserName`, `strPassword`); DB password from env `UNIQUE` or default `"firesale"` (`GlobalClass.java`). **Not suitable for shared CI without secrets management.**
- **User gate:** `AddUserVariablesToDictionaryObject` **fails the run** unless `System.getProperty("user.name")` is one of `darinduphorn`, `RickyWalz`, `jenkins`, `mpsadmin`, `mpsdev` — cloud agents and new engineers cannot run without code change.
- **Parameters:** TestNG XML passes `strDeviceId`, `strAndroidUdid`, `strRemotePath`, `strMobileAPK`, `strAppiumPort`, etc.

### 1.5 Runners and reporting

- **Runner:** TestNG via Maven Surefire (`suiteXmlFiles` in `pom.xml` is **commented out** — suites are selected externally, e.g. `-DsuiteXmlFile=...` or IDE/Jenkins).
- **Parallelism:** Suites use `parallel="tests"` with fixed `thread-count` (e.g. 9–10) — requires **one device/Appium port per `<test>`**.
- **Listeners:** `EmailableReporter2`, `EmailableReporter3`, `ListenersClass` — HTML email reports, meter log links on failure.
- **TestRail:** Integration stubbed/commented (`TestRails.java`).
- **Results DB:** `Database.java` → PostgreSQL `10.10.101.63` (optional / partially disabled in teardown).

### 1.6 Targets (apps and platforms)

| Product | Android | iOS | Notes |
|---------|---------|-----|--------|
| Sentry Mobile / Consumer App (CA) | Native + Flutter | Native + Flutter | Primary regression surface |
| PEO / Mobile Officer | Native + Flutter | — (PEO Android-focused) | Flutter APK in `Flutter_Android_PEO.xml` |
| Lot / TuffPark | `Android_Lot_*`, `Android_TuffPark_*` | — | Smaller suites |
| Fusion AI Mobile | Referenced in Flutter Android XML | — | `FusionAIMobile-Staging-1.0.1.apk` |
| Meters | `Meter_TestCases`, Python scripts | — | Physical + virtual |
| SentryLink / Launchpad / Payment site | Chrome (`CommonWeb`) | — | Cross-check session history, violations, admin |
| Global Pay | `TestCases` GP* + dedicated parking session helpers | — | Multi-meter sync timing |

---

## 2. Test inventory

### 2.1 Scale

- **~1,468 `@Test` annotations** in `AutomationSolution/AutomationCode` (excluding vendored TestRail tests).
- **~1,692 `public void` test-shaped methods** in `*TestCases*.java` (includes helpers named like tests).
- **~2,700+ `Thread.sleep` usages** across automation code (strong flakiness signal).

### 2.2 Feature areas (inferred from names and libraries)

| Area | Approx. coverage | Test types | Primary classes |
|------|------------------|------------|-----------------|
| **Registration / login / remember-me / lockout** | High (native); Medium (Flutter) | Negative + E2E | `A2001*`, `I*`, `A2001F*`, forgot-password series |
| **Parking session E2E** (pay, extend, free time, rate transitions) | Very high | Regression E2E | `*ParkingSessions`, `A2021*`, `A2xxxF*`, `M*`, meter `M*` |
| **Vehicle / plate / LP rules** | High | Regression | `GPV1`, `AV_`, `RV_`, `TUON`/`TUOFF` encoded flows |
| **GPS / location** | Medium | E2E + settings | `GPSOFF_*`, `A5209F_*`, permission tests |
| **Violations / images / SentryLink** | High | E2E + web verify | `VICAE`, `VIAC`, `VPSH`, PEO `P*` flows |
| **PEO violations** (claim, photos, NTAAV, dispute) | High native; Medium Flutter | E2E | `PEO_*`, `PEO_TestCases_Flutter` |
| **Reservations (`RSVN_*`)** | Medium–high in code | Regression | Flutter Android tests A2199F–A2203F (suites commented) |
| **Payments** (card, Google Pay, PayPal refs) | Medium | E2E | `A400*`, `PaymentTest_*` (many commented in Flutter XML) |
| **Global Pay multi-meter** | Medium | Integration E2E | `GP1001_*` in `TestCases` |
| **User roles (SentryLink admin)** | Medium | RBAC E2E | `UserRole_TestCases` |
| **Prod smoke crawlers** | Low | Smoke | `PROD_*` in `TestCases` |
| **Negative / resilience (TC-NEG-*)** | **Implemented, barely scheduled** | Edge / non-functional | `A5204F`–`A5219F`, `NegativeInputCases`, `NegativeInputCasesTest` |
| **Lot / TuffPark** | Low | Smoke/regression | 47 + 9 tests |

### 2.3 Test typing (how the repo thinks about tests)

- **Explicit TestNG groups:** `Smoke` appears on a **small subset** (e.g. some Flutter/Lot/TuffPark tests); **`Regression` group is not used** in Java — “regression” is implied by suite XML names only.
- **Smoke vs regression in practice:** Defined by **which methods are `<include>`d** in XML, not annotations — and most Flutter includes are **commented out**.
- **E2E:** Default — almost every test drives mobile + meter and/or SentryLink.
- **Unit-style:** Only `NegativeInputCasesTest`, `AdbSlowTextInputTest`, plus TestRail client tests in vendored tree.

### 2.4 Active suite snapshot (XML vs code)

| Suite file | Active `<include>` count | Notes |
|------------|-------------------------|--------|
| `Flutter_Android_CA_Regression.xml` | **1** | Only `A5209F_LocationServicesOff_DegradeThenEnable` |
| `Flutter_Android_PEO.xml` | **0** | All commented |
| `Flutter_IOS_CA_Regression.xml` | **0** | All commented |
| `Flutter_RegressionLotAndroid.xml` | **0** | All commented |
| `TestSuites/Smoke.xml` | Broken | Class `PackageTestCases.ClassTestCases` **missing** |
| `TestSuites/ProdSmokeTest.xml` | 66 includes | References **`AutomationCode.TestCases`** methods like `A20_*` — **no longer exist** (renamed to `A2019_*` etc. in `Android_TestCases`) |
| `TestSuites/Regression_Android.xml` | 1 active | Points at `TestCases` + `M1_*` (meter methods live in `Meter_TestCases`) |

**Conclusion:** Inventory in **source is large**; inventory ** routinely executed in Flutter pipelines is tiny** unless Jenkins uses unpublished job configs.

---

## 3. Coverage gaps

### 3.1 Flutter vs native

- **Android Flutter:** Broad `A2xxxF` parity with legacy Android (`A2xxx`) in source (~210 tests), but suites disable almost all.
- **iOS Flutter:** ~162 tests in `IOS_TestCases_Flutter.java`, **zero active** in `Flutter_IOS_CA_Regression.xml`.
- **PEO Flutter:** ~65 tests; **PEO Flutter XML has no active includes**.
- **No Flutter Driver / integration tests** — reliance on semantics tree increases risk when accessibility labels change.

### 3.2 New products

- **FusionAIMobile** — APK referenced; no dedicated test class name prefix (likely reuses CA flows) — **unclear dedicated coverage**.
- **Lot / TuffPark** — 56 tests total vs hundreds for on-street CA; **off-street flows thin**.
- **Consumer App “CS_*” / handicap / mobile-payment-blocked** — present in commented Flutter XML (`A2301F`–`A2308F`), **not scheduled**.

### 3.3 Negative and edge cases

- **TC-NEG-01–19** scenarios implemented under `A5204F`–`A5219F` with shared helpers in `NegativeInputCases.java` — **17 cases commented out in suite**, only location-degrade active.
- **Biometric, low storage, proxy/DNS failure, injection, excessive input** — coded, not in default run.
- **PEO null/empty login (`P4061F`–`P4062F`)** — present, not in active PEO suite.

### 3.4 Platform and environment

- **PROD mobile E2E** — limited (`ProdSmokeTest` miswired); most tests assume **SG/QA lab meters**.
- **Parallel multi-device** — configured in XML but depends on **fixed UDIDs and internal Appium hosts** (`10.10.x.x`).
- **Cloud agents / CI containers** — blocked by **OS username check** in `GlobalClass`.

### 3.5 Back-end and admin

- **SentryLink** role tests exist but are separate from Flutter suites; **cross-product journeys** (Flutter app + fresh SentryLink features) may lag new UI.
- **TestRail / DB result publishing** — partially disabled; **traceability gap** for management reporting.

---

## 4. Architectural issues (maintainability and testability)

### 4.1 Monoliths and duplication

| Issue | Evidence |
|-------|----------|
| **God classes** | `CommonWeb.java` (~23k LOC), `CommonANDROID.java` (~21k), `CommonANDROID_Flutter.java` (~16.6k), `CommonIOS.java` (~17.7k), `Meter.java` (~16.7k) |
| **Copy-paste test harness** | Identical `@BeforeMethod` / listeners in every `*_TestCases` class |
| **Parallel native + Flutter stacks** | `CommonANDROID` vs `CommonANDROID_Flutter` — drift risk (fixes must be duplicated) |
| **Parking session libraries** | Four huge `*_ParkingSessions.java` files with near-overlapping step codes |

### 4.2 Flaky and brittle patterns

- **`Thread.sleep`:** 2,700+ occurrences; e.g. `TestCases.Meter_SetBegunEqualFalse` uses 15s sleeps; `CommonWeb` 400+.
- **Brittle locators:** Deep XPath with embedded newlines in `content-desc` (`PEO_TestCases_Flutter.java` violation photo locators).
- **String-based actions:** `PopulateAction(..., "{T} Email|{T} Password", ...)` — fragile if label text changes.
- **Hard waits vs conditions:** Some newer Flutter helpers use `WebDriverWait` (`SENTRYMOBILE_ClickIfPresent`), but inconsistently applied.

### 4.3 Configuration and coupling

- **`GlobalClass.GetMeterProperties`:** Hundreds of `case "deviceId":` entries tying **device IDs → IPs, municipalities, APK defaults** — high cost to add environments.
- **Username allowlist** (`GlobalClass.java` ~lines 82–110): fails on unknown users.
- **Default password fallback** `"firesale"` when `UNIQUE` unset.
- **Absolute paths:** e.g. `TestCases.BASE_PATH = "/Users/mpsadmin/TestAutomation/MeterScreenShots/"`.

### 4.4 Security (critical)

- **`EmailableReporter2.java`:** Hard-coded Gmail username and **app password in source** (~lines 97–98). Must be rotated and moved to secrets.
- **Internal IPs, UDIDs, phone numbers** throughout `GlobalClass`, XML suites, and `Database` connection strings.

### 4.5 Dead, stale, or broken artifacts

- **`PackageTestCases.ClassTestCases`:** Referenced in `Smoke.xml`, `Regression_IOS.xml`, `Regression_PEO.xml`, `Regression_Meter.xml`, `RunSameTestMultipleTimes.xml` — **class not in repo**.
- **Suite method names** (`A20_*`, `I1_*` on wrong class) **do not match** current `Android_TestCases` / `IOS_TestCases` / `Meter_TestCases` naming.
- **`TestRails.java`:** Commented out; vendored TestRail modules add noise.
- **`ImgurClient_Old.java`**, duplicate Selenium versions in `Lib/`, Grid launcher on **Selenium 2.52**.
- **`pom.xml`:** Surefire `suiteXmlFiles` empty — easy to run **zero tests** by mistake.
- **Switch fall-through bug risk:** `GlobalClass` environment `switch` for QA/SG/PROD **missing `break`** between cases (lines 177–192) — can merge URL maps unintentionally.

### 4.6 Dependencies and tooling

- **TestNG 6.14.3** (2018) on **Java 21** with `--enable-preview` — unusual; upgrade path needed.
- **PostgreSQL JDBC 9.4.1210.jre6** — ancient.
- **Mixed JUnit 5 on classpath** but tests are TestNG-only.
- **Positive:** Appium 10 + Selenium 4.41 are relatively current; `mvn compile` succeeds.

### 4.7 Assertions and failure diagnosis

- Failures often routed through **`UpdateErrorMessageWithPivotalData`** (legacy Pivotal bug IDs) — still useful for tribal knowledge but obscures clean assertion messages.
- **`ListenersClass`:** Expects `strTestCaseName` on `ITestResult` attributes that are **never set** — dead diagnostic paths (“MIH” log).

---

## 5. Prioritized recommendations

Effort: **S** ≈ days, **M** ≈ 1–2 weeks, **L** ≈ multi-week / epic (team-dependent).

| Priority | Item | Effort | Why it matters |
|----------|------|--------|----------------|
| **1** | **Repair TestNG suite wiring:** Replace `PackageTestCases.ClassTestCases`; point suites at correct classes (`Android_TestCases`, `Android_TestCases_Flutter`, `Meter_TestCases`, etc.); regenerate includes from actual method names; add one **verified smoke suite** checked into CI. | **M** | Without this, “regression” XML is misleading and Flutter work cannot run reliably. |
| **2** | **Secrets and config externalization:** Remove Gmail creds from `EmailableReporter2`; load from env/vault; replace `GlobalClass` username gate with env flag; move device/meter maps to YAML/JSON + profiles (`qa`, `sg`). | **M** | Security incident risk; blocks new developers and cloud agents. |
| **3** | **Enable Flutter smoke + negative pipeline:** Uncomment tier-1 CA tests (login, single park, extend) + full **A5204F–A5219F** suite on one lab device; mirror for PEO (`P4001F` smoke + `P4061F`). | **L** | Aligns execution with Flutter product direction; TC-NEG coverage is already written. |
| **4** | **Shared sync/wait layer:** Replace sleeps with meter-event polling, `WebDriverWait` wrappers, and session-state helpers; ban raw `Thread.sleep` in new code via review. | **L** | Biggest flake reducer; `CommonWeb` alone has 400+ sleeps. |
| **5** | **Incremental modularization for Flutter:** Extract screen-specific locator maps (Login, Park, Session, Account) from `CommonANDROID_Flutter`; keep `PopulateAction` as facade; add parity tests ensuring native vs Flutter step codes stay aligned. | **L** | Lowers cost of adding tests; reduces duplicate native/Flutter maintenance. |
| **6** | **iOS Flutter CI parity** | **M** | 162 tests dormant while Android Flutter is focus — release risk on iOS. |
| **7** | **Upgrade TestNG + reporting** | **S** | Modern listeners, stable Java 21 support, fix `--enable-preview` need. |
| **8** | **Document runbook** (`docs/RUNNING.md`): Maven command, required env vars, Appium/device setup, suite matrix. | **S** | Onboarding; complements this assessment. |

---

## 6. Build verification

| Step | Result |
|------|--------|
| Java | OpenJDK 21 available |
| `mvn compile` | **SUCCESS** (Apache Maven 3.9.9, dependencies resolve) |
| `mvn test` | **Not run** (requires Appium, devices, lab meters, credentials) |

---

## 7. Suggested “definition of done” for follow-up work

1. A green **Flutter Android smoke** job (≥5 tests: login, park, logout, one negative) on staging.  
2. All suite XML references **valid class and method names** (automated check in CI).  
3. No **plaintext credentials** in repo.  
4. `GlobalClass` runs under **`AUTOMATION_USER` env** without hardcoded OS username list.  
5. Documented **suite matrix** mapping products → XML → device requirements.

---

## Appendix A — Key files reference

| Concern | File(s) |
|---------|---------|
| Environment / device map | `AutomationSolution/AutomationCode/GlobalClass.java` |
| Flutter Android automation | `CommonANDROID_Flutter.java`, `Android_TestCases_Flutter.java` |
| Flutter iOS automation | `IOS_Common_Flutter.java`, `IOS_TestCases_Flutter.java` |
| Web / SentryLink | `CommonWeb.java` |
| Meter control | `Meter.java`, `Lib/PythonScripts/*` |
| Negative/resilience helpers | `NegativeInputCases.java`, `NegativeInputCasesTest.java` |
| Email reporting | `EmailableReporter2.java`, `EmailableReporter3.java` |
| Flutter suite (active) | `Flutter_Android_CA_Regression.xml` |
| Legacy suites | `TestSuites/*.xml` |

---

## Appendix B — Test method naming (decoder sketch)

Tests use a compressed DSL, e.g. `A2021F_FTFP0_PS1_MP1_ES1_VPSH_VICAE_VIAC`:

- **Prefix:** `A` Android, `I` iOS, `P` PEO, `M` meter; **`F`** suffix ≈ Flutter variant  
- **FTFP0/10:** free-time-first-payment flag  
- **PS1:** parking session step  
- **MP1/BMT/PMT:** mobile pay / balance / payment types  
- **ES1:** exit session  
- **VPSH / VICAE / VIAC:** validate parking session history / images / actions on SentryLink  

Understanding this DSL is **required** to add or debug tests.
