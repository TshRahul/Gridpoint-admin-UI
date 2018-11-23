package com.gridpoint.adminui.automation.runner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;

import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.automation.tests.base.TestBase;
import com.gridpoint.util.BaseUtil;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class InitializeResources extends CommonInit {
	private static final Logger LOGGER = LogManager
			.getLogger(InitializeResources.class);

	Map<String, String> initializeResourceObjectRepo;

	/**
	 * Method:- initializeResources Parameter:- No Description:- Initialize
	 * resources in the beginning of each scenario
	 *
	 */
	@Given("^Initialize resources in the beginning of each scenario$")
	public void initializeResourcesInTheBeginningOfEachScenarioForResourceMenuPage()
			throws Throwable {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.debug("Initialize resources in the beginning of each scenario");
		}

		loadConfigFiles();

		BaseUtil.overridePollingfrequency(""
				+ PathConstants.defaultPollingFrequency);
		BaseUtil.overrideTimeout("" + PathConstants.defaultTimeout);

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("::::: Before Login :::::");
		}
		String url = TestBase.getEnvUrl();
		try {
			getDriver().get(url);
		} catch (TimeoutException e) {
			TestBase.setWebDriver(null);
			TestBase.getInstance();
			new CommonInit();
			getDriver().get(url);
		} catch (Exception e) {
			TestBase.getInstance();
			new CommonInit();
			getDriver().get(url);
		}
		BaseUtil.waitForSpinner();
		BaseUtil.enterText(getProps().getProperty("adminUserName_Locator"),
				TestBase.getUserName());
		BaseUtil.enterText(getProps().getProperty("adminPassword_Locator"),
				TestBase.getPassword());
		BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));
		BaseUtil.waitForSpinner();
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("::::: After Login :::::");
		}
		BaseUtil.checkPageIsReady(getProps().getProperty("loginspinner"), 600);
		BaseUtil.checkPageIsReady(getProps().getProperty("pagespinner"), 600);
	}

	/**
	 * Method:- Reads all the property files
	 *
	 */
	public void loadConfigFiles() {
		InitializeResources.loadMenuOptionsConfigFile(CommonInit.class,
				"Global.properties");
		InitializeResources.loadMenuOptionsConfigFile(CommonInit.class,
				"AuditPage.properties");
		InitializeResources.loadMenuOptionsConfigFile(CommonInit.class,
				"DatafixPage.properties");
		InitializeResources.loadMenuOptionsConfigFile(CommonInit.class,
				"EndpointPage.properties");
		InitializeResources.loadMenuOptionsConfigFile(CommonInit.class,
				"FirmwarePage.properties");
		InitializeResources.loadMenuOptionsConfigFile(CommonInit.class,
				"SitePage.properties");
		InitializeResources.loadMenuOptionsConfigFile(CommonInit.class,
				"TenantPage.properties");
		InitializeResources.loadMenuOptionsConfigFile(CommonInit.class,
				"UserPage.properties");
		InitializeResources.loadMenuOptionsConfigFile(CommonInit.class,
				"UserRolePermissions.properties");
	}

	private static void loadMenuOptionsConfigFile(
			Class<? extends Object> className, String resource) {
		InputStream input = className.getClassLoader().getResourceAsStream(
				resource);
		try {
			if (input == null) {
				return;
			}
			getProps().load(input);
		} catch (Exception e) {

		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Then("^Initialize resources in the beginning of each scenario for Admin UI$")
	public void Initialize_resources_in_the_beginning_of_each_scenario_for_Admin_UI()
	{
		if (LOGGER.isInfoEnabled()) {
			LOGGER.debug("Initialize resources in the beginning of each scenario");
		}

		loadConfigFiles();

		BaseUtil.overridePollingfrequency(""
				+ PathConstants.defaultPollingFrequency);
		BaseUtil.overrideTimeout("" + PathConstants.defaultTimeout);

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("::::: Before Login :::::");
		}
		String url = TestBase.getEnvUrl();
		try {
			getDriver().get(url);
		} catch (TimeoutException e) {
			TestBase.setWebDriver(null);
			TestBase.getInstance();
			new CommonInit();
			getDriver().get(url);
		} catch (Exception e) {
			TestBase.getInstance();
			new CommonInit();
			getDriver().get(url);
		}
		BaseUtil.waitForSpinner();
		BaseUtil.enterText(getProps().getProperty("adminUserName_Locator"),
				TestBase.getUserName());
		BaseUtil.enterText(getProps().getProperty("adminPassword_Locator"),
				TestBase.getPassword());
		BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));
		BaseUtil.waitForSpinner();
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("::::: After Login :::::");
		}
		BaseUtil.checkPageIsReady(getProps().getProperty("loginspinner"), 600);
		BaseUtil.checkPageIsReady(getProps().getProperty("pagespinner"), 600);
	}
}
