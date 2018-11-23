package com.gridpoint.automation.tests.base;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.util.ReadResources;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class TestBase {

	private static Logger logger = Logger.getLogger(TestBase.class);

	public static long WAIT = 60;

	private static ReadResources readResources;
	private static Map<String, String> readConfigData;
	private static Map<String, String> readEnvironmentUrls;
	private static Map<String, String> readUserLoginCredentials;
	private static Map<String, String> readDataBaseUrls;
	private static Map<String, String> readDataBaseCredentials;

	private static String env;
	private static String userName;
	private static String password;
	private static String envUrl;
	private static String dbUrl;
	private static String dbUserName;
	private static String dbPassword;
	private static String browser;

	private static TestBase instance;
	private static long timeout = PathConstants.defaultTimeout;
	private static long pollingFrequency = PathConstants.defaultPollingFrequency;

	private static WebDriverWait webDriverWait;
	private static WebDriver webDriver;

	private TestBase() {
		readResources = new ReadResources();
		readConfigData = readResources.getValuesFromXml("Configuration.xml",
				"gridpoint");
		readEnvironmentUrls = readResources.getValuesFromXml(
				"Configuration.xml", "environment_url");
		readUserLoginCredentials = readResources.getValuesFromXml(
				"Configuration.xml", "userlogincredentials");
		readDataBaseUrls = readResources.getValuesFromXml("Configuration.xml",
				"databaseconfig");
		readDataBaseCredentials = readResources.getValuesFromXml(
				"Configuration.xml", "databaseuserconfig");
		try {
			// if (null == webDriver)
			// if (System.getProperty("browser") == null ||
			// System.getProperty("browser").trim().isEmpty()) {
			// webDriver = initializeWebDriver(readConfigData.get("browser"));
			// } else {
			// webDriver =
			// initializeWebDriver(System.getProperty("browser").trim());
			// }
			if (null == webDriver) {
				initializeGlobalVariables();
				webDriver = initializeWebDriver(browser);
				webDriver.manage().deleteAllCookies();
				webDriverWait = new WebDriverWait(webDriver,
						TimeUnit.MILLISECONDS.toSeconds(timeout));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		webDriverWait = new WebDriverWait(webDriver,
				TimeUnit.SECONDS.toSeconds(timeout));
	}

	public static TestBase getInstance() {
		if (null == instance || getWebDriver() == null) {
			return new TestBase();
		} else {
			return instance;
		}
	}

	public static void initializeGlobalVariables() {
		env = System.getProperty("env");
		if (env == null || env.trim().isEmpty()) {
			env = readConfigData.get("env");
		}

		envUrl = System.getProperty("url");
		if (envUrl == null || envUrl.trim().isEmpty()) {
			envUrl = readEnvironmentUrls.get(env);
		}

		userName = System.getProperty("username");
		if (userName == null || userName.trim().isEmpty()) {
			userName = readUserLoginCredentials.get(env + "_username");
		}

		password = System.getProperty("password");
		if (password == null || password.trim().isEmpty()) {
			password = readUserLoginCredentials.get(env + "_password");
		}

		dbUrl = System.getProperty("dburl");
		if (dbUrl == null || dbUrl.trim().isEmpty()) {
			dbUrl = readDataBaseUrls.get(env);
		}

		dbUserName = System.getProperty("dbusername");
		if (dbUserName == null || dbUserName.trim().isEmpty()) {
			dbUserName = readDataBaseCredentials.get(env + "_username");
		}

		dbPassword = System.getProperty("dbpassword");
		if (dbPassword == null || dbPassword.trim().isEmpty()) {
			dbPassword = readDataBaseCredentials.get(env + "_password");
		}

		browser = System.getProperty("browser");
		if (browser == null || browser.trim().isEmpty()) {
			browser = readConfigData.get("browser");
		}
		System.out.println(browser);

		String eol = System.getProperty("line.separator");
		logger.info("System will initialized with following values :" + eol
				+ "Environment : " + env + eol + "Environment URL : " + envUrl
				+ eol + "Username : " + userName + eol + "Password : "
				+ password + eol + "Database URL : " + dbUrl + eol
				+ "DataBase Username : " + dbUserName + eol
				+ "Database Password : " + dbPassword);
	}

	private static FirefoxProfile getFirefoxProfile(String basePath) {
		FirefoxProfile fxProfile = new FirefoxProfile();
		fxProfile.setPreference("browser.download.manager.showWhenStarting",
				false);
		fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"text/plain");
		fxProfile.setPreference("security.mixed_content.block_active_content",
				false);
		fxProfile.setPreference("security.mixed_content.block_display_content",
				true);
		fxProfile.setPreference("browser.download.folderList", 2);
		fxProfile.setPreference("browser.download.dir", basePath + ""
				+ File.separator + "downloadDir");
		fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"image/jpg,text/csv,text/xml,text/plain,application/xml,"
						+ "application/json,application/vnd.ms-excel,"
						+ "application/octet-stream, application/x-excel,"
						+ "application/x-msexcel,application/excel,"
						+ "application/pdf");
		return fxProfile;
	}

	private static FirefoxOptions getFirefoxOptions() {
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.addArguments("--start-maximized");
		return firefoxOptions;
	}

	public static WebDriver initializeWebDriver(String browser)
			throws InterruptedException {
		String filePath = new File("").getAbsolutePath();
		String browserValue = browser.trim();
		char cforwardslash = (char) 47;
		char cbackslash = (char) 92;
		String basePath = filePath.replace(cbackslash, cforwardslash);
		logger.info(basePath);
		if (browserValue.equalsIgnoreCase("firefox")) {
			FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX)
					.version(readConfigData.get("firefoxDriverVersion"))
					.setup();
			FirefoxOptions firefoxOptions = getFirefoxOptions();
			firefoxOptions.setProfile(getFirefoxProfile(basePath));
			webDriver = new FirefoxDriver(firefoxOptions);
			logger.info("Initializing firefox webDriver");
		} else if (browserValue.equalsIgnoreCase("firefox_headless")) {
			FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX)
					.version(readConfigData.get("firefoxDriverVersion"))
					.setup();
			FirefoxOptions firefoxOptions = getFirefoxOptions();
			firefoxOptions.addArguments("--headless");
			// firefoxOptions.setHeadless(true);
			firefoxOptions.setProfile(getFirefoxProfile(basePath));
			webDriver = new FirefoxDriver(firefoxOptions);
			logger.info("Initializing firefox webDriver");
		} else if (browserValue.equalsIgnoreCase("chrome")) {
			ChromeDriverManager.getInstance(DriverManagerType.CHROME)
					.version(readConfigData.get("chromeDriverVersion")).setup();
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", basePath + ""
					+ File.separator + "downloadDir");
			chromePrefs.put("safebrowsing.enabled", "true");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			options.addArguments("--start-maximized");
			options.merge(cap);
			webDriver = new ChromeDriver(options);
			logger.info("Initializing chrome webDriver");

		} else if (browserValue.equalsIgnoreCase("chrome_headless")) {
			ChromeDriverManager.getInstance(DriverManagerType.CHROME)
					.version(readConfigData.get("chromeDriverVersion")).setup();

			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", basePath + ""
					+ File.separator + "downloadDir");
			chromePrefs.put("safebrowsing.enabled", "true");

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--headless");
			options.addArguments("--start-maximized");
			// options.addArguments("--window-size=1920x1080x16");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.merge(cap);
			webDriver = new ChromeDriver(options);
			logger.info("Initializing chrome webDriver");
		} else if (browserValue.equalsIgnoreCase("iexplorer")) {
			System.setProperty("webdriver.ie.driver", basePath + ""
					+ File.separator + "ExecutableDrivers" + File.separator
					+ "IEDriverServer_Win32_2.45.0" + File.separator
					+ "IEDriverServer.exe");
			webDriver = new InternetExplorerDriver();
			logger.info("Initializing iexplorer webDriver");
		}

		// else if (browserValue.equalsIgnoreCase("chrome")) {
		// HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		// chromePrefs.put("profile.default_content_settings.popups", 0);
		// chromePrefs.put("download.default_directory", basePath + "" +
		// File.separator
		// + "downloadDir");
		// ChromeOptions options = new ChromeOptions();
		// options.setExperimentalOptions("prefs", chromePrefs);
		// DesiredCapabilities cap = DesiredCapabilities.chrome();
		// cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		// cap.setCapability(ChromeOptions.CAPABILITY, options);
		// if (SystemUtils.IS_OS_WINDOWS) {
		// System.setProperty("webdriver.chrome.driver", basePath + "" +
		// File.separator
		// + "ExecutableDrivers"
		// + File.separator + "chromedriver_win32_2.35" + File.separator +
		// "chromedriver.exe");
		// } else {
		// System.setProperty("webdriver.chrome.driver", basePath + "" +
		// File.separator
		// + "ExecutableDrivers"
		// + File.separator + "chromedriver_linux32" + File.separator +
		// "chromedriver");
		// }
		// webDriver = new ChromeDriver(cap);
		// logger.info("Initializing chrome webDriver");
		// }
		// try {
		// webDriver.manage().window().maximize();
		// } catch (WebDriverException e) {
		// logger.error("Failed to maximize browser window");
		// initializeWebDriver(browser);
		// }
		return webDriver;
	}

	public DesiredCapabilities getFirefoxCapabilities() {
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		return dc;
	}

	public DesiredCapabilities getChromeCapabilities() {
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		return dc;
	}

	public DesiredCapabilities getInternetExplorerCapabilities() {
		DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
		return dc;
	}

	public static WebDriver getWebDriver() {
		return webDriver;
	}

	public static void setWebDriver(WebDriver webDriver) {
		TestBase.webDriver = webDriver;
	}

	public static ReadResources getReadResources() {
		return readResources;
	}

	public void setReadResources(ReadResources readResources) {
		TestBase.readResources = readResources;
	}

	public static Map<String, String> getReadConfigData() {
		return readConfigData;
	}

	public static WebDriverWait getWebDriverWait() {
		return webDriverWait;
	}

	public static void setWebDriverWait(WebDriverWait webDriverWait) {
		TestBase.webDriverWait = webDriverWait;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		TestBase.env = env;
	}

	public static String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		TestBase.userName = userName;
	}

	public static String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		TestBase.password = password;
	}

	public static String getEnvUrl() {
		return envUrl;
	}

	public static void setEnvUrl(String envUrl) {
		TestBase.envUrl = envUrl;
	}

	public static String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		TestBase.dbUrl = dbUrl;
	}

	public static String getDbUsername() {
		return dbUserName;
	}

	public void setDbUsername(String dbUserName) {
		TestBase.dbUserName = dbUserName;
	}

	public static String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		TestBase.dbPassword = dbPassword;
	}

	public static Map<String, String> getReadEnvironmentUrls() {
		return readEnvironmentUrls;
	}

	public static void getLoginPage() {
		webDriver.get(envUrl);
	}

	public static long getTimeout() {
		return timeout;
	}

	public static void setTimeout(long timeout) {
		TestBase.timeout = timeout;
	}

	public static long getPollingFrequency() {
		return pollingFrequency;
	}

	public static void setPollingFrequency(long pollingFrequency) {
		TestBase.pollingFrequency = pollingFrequency;
	}

	public static Logger getLogger() {
		return logger;
	}

}