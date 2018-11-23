package com.gridpoint.adminui.automation.commonSteps;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;

import com.gridpoint.adminui.automation.audit.AuditPage;
import com.gridpoint.adminui.automation.site.SitePage;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.automation.tests.base.TestBase;
import com.gridpoint.util.BaseUtil;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class CommonStepsImplementation extends CommonInit {

	final static Logger LOGGER = LogManager
			.getLogger(CommonStepsImplementation.class);

	private static boolean isRefreshNeededFlg = false;
	private static boolean firstTimeFlg = true;

	/**
	 * These steps are used to refresh the page in the beginning of the scenario
	 * in case of failure of the previous scenario to bring the page in the
	 * normal state
	 * 
	 * Start
	 */
	@Then("^validate that all the test cases of scenario for portlet executed successfully$")
	public void validateAllTestCasesExecution() {
		isRefreshNeededFlg = false;
	}

	@And("^check if the refresh required for the portlet test scenario$")
	public void checkIfRefreshRequired() {
		try {
			if (firstTimeFlg) {
				firstTimeFlg = false;
				return;
			}
			if (null != getDriver()) {
				if (isRefreshNeededFlg) {
					getDriver().navigate().refresh();
				}
				isRefreshNeededFlg = true;
			}
		} catch (UnhandledAlertException e) {
			Alert alert = getDriver().switchTo().alert();
			alert.dismiss();
			getDriver().navigate().refresh();
		} catch (TimeoutException e) {
			LOGGER.error("******* Time out exception occured while accessing webdriver : "
					+ e.getMessage() + " *******");
			TestBase.setWebDriver(null);
			TestBase.getInstance();
			new CommonInit();
		} catch (Exception e) {
			LOGGER.error("Error occured while checking for the page refresh : "
					+ e.getMessage());
			Assert.fail("Error occured while checking for the page refresh : "
					+ e.getMessage());
		}

	}

	/* Stop */

	@Then("^close the browser$")
	public void closeTheBrowser() {
		firstTimeFlg = true;
		try {
			getDriver().quit();
			TestBase.setWebDriver(null);
		} catch (TimeoutException e) {
			LOGGER.error("******* Time out exception occured while accessing webdriver : "
					+ e.getMessage() + " *******");
			TestBase.setWebDriver(null);
			TestBase.getInstance();
			new CommonInit();
		} catch (UnhandledAlertException e) {
			Alert alert = getDriver().switchTo().alert();
			alert.dismiss();
			getDriver().quit();
			TestBase.setWebDriver(null);
		} catch (Exception e) {
			TestBase.setWebDriver(null);
			LOGGER.error("Error occured while closing the browser after test module execution : "
					+ e.getMessage());
			Assert.fail("Error occured while closing the browser after test module execution : "
					+ e.getMessage());
		}
	}

	@Then("refresh the browser")
	public void refreshBrowser() {
		// DoradoSeleniumUtil.explicitWait(DoradoConstants.WAIT_MINIMUM);
		try {
			getDriver().navigate().refresh();
		} catch (TimeoutException e) {
			LOGGER.error("******* Time out exception occured while accessing webdriver : "
					+ e.getMessage() + " *******");
			TestBase.setWebDriver(null);
			TestBase.getInstance();
			new CommonInit();
		} catch (UnhandledAlertException e) {
			Alert alert = getDriver().switchTo().alert();
			alert.dismiss();
			getDriver().navigate().refresh();
		} catch (Exception e) {
			LOGGER.error("Error occured while refreshing the browser page : "
					+ e.getMessage());
			Assert.fail("Error occured while refreshing the browser page : "
					+ e.getMessage());
		}
	}

	@Then("^enter_the_test_Data_in_the_search_box_and_click_on_the_search_button$")
	public void enter_the_test_Data_in_the_search_box_and_click_on_the_search_button(
			String type, String testData) {
		String fieldLocator = null;
		if (type.equalsIgnoreCase("Site")) {
			fieldLocator = getProps().getProperty("siteSearchField");

		} else if (type.equalsIgnoreCase("Tenant")) {
			fieldLocator = getProps().getProperty("tenantsSearchField");

		} else if (type.equalsIgnoreCase("Endpoint Firmware")) {
			fieldLocator = getProps().getProperty(
					"endpointFirmwareTabSearchField");

		} else if (type.equalsIgnoreCase("Peripheral Firmware")) {
			fieldLocator = getProps().getProperty(
					"peripheralFirmwareSearchField");

		}
		BaseUtil.enterText(fieldLocator, testData);
		BaseUtil.waitForSpinner(1000);
	}

	@Then("^select given \"(.*?)\" from the list and verify the Confirmation box$")
	public void select_the_given_from_the_list_and_verify_the_confirmation_box(
			String sourceNameFeature, String TestData) {
		boolean bResult = false;
		String gridColumnLocator = null;

		if (sourceNameFeature.equalsIgnoreCase("Site")) {
			BaseUtil.click(getProps().getProperty("siteTabSearchButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty("sitePageNameColumn");

		} else if (sourceNameFeature.equalsIgnoreCase("Tenant")) {
			BaseUtil.click(getProps().getProperty("tenantSearchButton")); 
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty("tenantPageNameColumn");

		} else if (sourceNameFeature.equalsIgnoreCase("Endpoint Firmware")) {
			BaseUtil.click(getProps().getProperty(
					"endpointFirmwareSearchButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty(
					"endpointFirmwarePageNameColumn");

		} else if (sourceNameFeature.equalsIgnoreCase("Peripheral Firmware")) {
			BaseUtil.click(getProps().getProperty(
					"peripheralFirmwarePageSearchButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty(
					"peripheralFirmwarePageNameColumn");

		}

		try {
			List<WebElement> lstSourcesNames = BaseUtil
					.getMultipleElementsAfterLoaded(gridColumnLocator);
			for (WebElement webElement : lstSourcesNames) {
				if (webElement.getText().trim().equalsIgnoreCase(TestData)) {
					webElement.click();
					BaseUtil.waitForSpinner(1000);
					bResult = true;
					break;
				}
			}
			if (!bResult) {
				Assert.fail("Given test " + sourceNameFeature
						+ " is not available");
			}
			LOGGER.debug("Searched source :" + TestData + " is selected.");
		} catch (Exception e) {
			LOGGER.error("Failed to select source: " + TestData
					+ " from grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the: " + TestData
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.explicitWait(3000);
		}
	}

	@Then("^click on \"(.*?)\" button/tab$")
	public void click_on_button(String tabName, String buttonName)
			throws Throwable {
		SitePage sitePage = new SitePage();
		AuditPage auditPage = new AuditPage();
		if (tabName.equals("Site")) {
			Assert.assertTrue("Clicking on " + buttonName + " is failed",
					sitePage.clickOperationOnSitePage(buttonName));
		} else if (tabName.equals("Tenant")) {
			Assert.assertTrue("Clicking on " + buttonName + " is failed",
					sitePage.clickOperationOnSitePage(buttonName));
		} else if (tabName.equals("Endpoint Firmware")) {
			Assert.assertTrue("Clicking on " + buttonName + " is failed",
					auditPage.clickOperationOnAuditpage(buttonName));
		} else if (tabName.equals("Peripheral Firmware")) {
			Assert.assertTrue("Clicking on " + buttonName + " is failed",
					auditPage.clickOperationOnAuditpage(buttonName));
		}

	}

	@Then("^confirmation box should displayed with message saying \"(.*?)\"$")
	public void confirmation_box_should_displayed_with_message(String type,
			String expectedMessage, String testData) throws Throwable {
		String messageOnUI = null;
		if (type.equals("Site")) {
			expectedMessage = expectedMessage.replace("#####", testData);
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("editSiteDeleteConfirmationPopup"),
					"Delete Confirmation box is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps().getProperty(
							"editSiteDeleteConfirmationPopupTitle"),
					"Delete Confirmation box Title is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps().getProperty(
							"editSiteDeleteConfirmationPopupMessage"),
					"Delete Confirmation box Message is not displayed");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"editSiteDeleteConfirmationPopupMessage"));

		} else if (type.equals("Tenant")) {
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("editTenantDeleteConfirmationPopup"),
					"Delete Confirmation box is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps().getProperty(
							"editTenantDeleteConfirmationPopupTitle"),
					"Delete Confirmation box Title is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps().getProperty(
							"editTenantDeleteConfirmationPopupMessage"),
					"Delete Confirmation box Message is not displayed");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"editTenantDeleteConfirmationPopupMessage"));

		} else if (type.equals("Endpoint Firmware")) {
			BaseUtil.assertElementDisplayed(
					getProps().getProperty(
							"editEndpointFirmwareDeleteConfirmationPopup"),
					"Delete Confirmation box is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps().getProperty(
							"editEndpointFirmwareDeleteConfirmationPopupTitle"),
					"Delete Confirmation box Title is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps()
							.getProperty(
									"editEndpointFirmwareDeleteConfirmationPopupMessage"),
					"Delete Confirmation box Message is not displayed");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"editEndpointFirmwareDeleteConfirmationPopupMessage"));

		} else if (type.equals("Peripheral Firmware")) {
			BaseUtil.assertElementDisplayed(
					getProps().getProperty(
							"editPeripheralFirmwareDeleteConfirmationPopup"),
					"Delete Confirmation box is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps()
							.getProperty(
									"editPeripheralFirmwareDeleteConfirmationPopupTitle"),
					"Delete Confirmation box Title is not displayed");
			BaseUtil.assertElementDisplayed(
					getProps()
							.getProperty(
									"editPeripheralFirmwareDeleteConfirmationPopupMessage"),
					"Delete Confirmation box Message is not displayed");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"editPeripheralFirmwareDeleteConfirmationPopupMessage"));

		}

		Assert.assertTrue("Confirmation message on UI(" + messageOnUI
				+ ") is not expected(" + expectedMessage + ") message",
				expectedMessage.equals(messageOnUI));
	}

	@Then("^verify user should be navigated to sites page with a label saying \"(.*?)\"$")
	public void user_should_be_navigated_to_page_with_a_label_saying(
			String message, String type, String testData) throws Throwable {
		BaseUtil.explicitWait(2000);
		String messageOnUI = null;
		if (type.equalsIgnoreCase("Site")) {
			message = message.replace("#####", testData);
			BaseUtil.waitForSpinner();
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("addSiteSaveMsg"),
					"Message is not visible on the Site page");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"addSiteSaveMsg"));
		} else if (type.equalsIgnoreCase("Tenant")) {
			message = message.replace("#####", testData);
			BaseUtil.waitForSpinner();
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("addTenantSaveMsg"),
					"Message is not visible on the Tenant page");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"addTenantSaveMsg"));
		} else if (type.equalsIgnoreCase("Endpoint Firmware")) {
			message = message.replace("#####", testData);
			BaseUtil.waitForSpinner();
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("addEndpointFirmwareSaveMsg"),
					"Message is not visible on the Endpoint Firmware page");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"addEndpointFirmwareSaveMsg"));
		} else if (type.equalsIgnoreCase("Peripheral Firmware")) {
			message = message.replace("#####", testData);
			BaseUtil.waitForSpinner();
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("addPeripheralFirmwareSaveMsg"),
					"Message is not visible on the Tenant page");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"addPeripheralFirmwareSaveMsg"));
		}

		Assert.assertTrue(" Message on UI(" + messageOnUI
				+ ") is not expected(" + message + ") message",
				message.equals(messageOnUI));
	}

	public void reportError(String sValue) {
		int i = 1;
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		BaseUtil.logger.info("<font color='red'>Step-" + i + ": " + sValue
				+ "</font></br>");
		i++;
	}

}
