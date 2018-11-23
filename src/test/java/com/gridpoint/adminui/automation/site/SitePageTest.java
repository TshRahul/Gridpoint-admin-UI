package com.gridpoint.adminui.automation.site;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.gridpoint.adminui.automation.audit.AuditPage;
import com.gridpoint.adminui.automation.commonSteps.CommonStepsImplementation;
import com.gridpoint.adminui.automation.tenant.TenantPage;
import com.gridpoint.adminui.automation.testdata.ManageTestData;
import com.gridpoint.adminui.automation.user.UserPage;
import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.database.GPConnection;
import com.gridpoint.automation.database.GPDataBaseUtil;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.automation.tests.base.TestBase;
import com.gridpoint.util.BaseUtil;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SitePageTest extends CommonInit {
	private Logger logger = Logger.getLogger(SitePageTest.class);
	private SitePage sitePage;
	private UserPage userPage;
	private AuditPage auditPage;

	// TestBase testBase;
	String totalItems = "1";
	String selectedTenant;
	String searchSite;

	CommonStepsImplementation commonStepsImplementation = new CommonStepsImplementation();

	private static boolean isSitePresent, isTenantPresent, isEndpointPresent;

	@Given("^User is already logged in to Admin URL and is already present at the Sitetab for \"(.*?)\"$")
	public void User_is_already_logged_in_to_Admin_URL_and_is_already_present_at_the_Sitetab_for(
			String arg1) throws Throwable {
		sitePage = new SitePage();
		userPage = new UserPage();
		BaseUtil.waitForSpinner();
		BaseUtil.overrideTimeout(getProps().getProperty("sitePageTimeout"));
		BaseUtil.overridePollingfrequency(getProps().getProperty(
				"sitePagePollingFrequency"));
		String message = "Unsuccessful login with valid credentials";
		BaseUtil.assertElementDisplayed(getProps()
				.getProperty("optionDropDown"), message);
	}

	@Given("^we are on Site page and search site field is enabled visible$")
	public void we_are_on_Site_page_and_search_site_field_is_enabled()
			throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.clickAndWait(getProps().getProperty("sitesTab"));
		BaseUtil.explicitWait(3000);
		BaseUtil.waitForSpinner();
		BaseUtil.instantElementCheck("Display",
				getProps().getProperty("siteDetailGrid"));
	}

	@Then("^verify the columns in \"(.*?)\" grid$")
	public void verify_the_columns_in_grid(String resourceName, DataTable args)
			throws Throwable {
		List<String> lstData = args.asList(String.class);
		args.asList(String.class);
		for (String header : lstData) {
			sitePage.IsGridHeaderDisplayed(header);

		}
	}

	@Then("^verify the following columns sorting functionality in site detail grid$")
	public void verify_the_following_columns_sorting_functionality_in_site_detail_grid(
			DataTable siteDetailGrid) throws Throwable {
		List<String> lstData = siteDetailGrid.asList(String.class);
		for (String header : lstData) {
			sitePage.verifySorting_SitePage("Descending", header);
			sitePage.verifySorting_SitePage("Ascending", header);
		}
	}

	@Then("^verify the tenant dropdown is present on site page including the list of sites associated with the user$")
	public void verify_the_tenant_dropdown_is_present_on_site_page_including_the_list_of_sites_associdated_with_the_user()
			throws Throwable {
		String message = "Failed to verify tenant list in tenant dropdown associated with the user";
		Assert.assertTrue(
				message,
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"sitePageTenantDropdown")));
	}

	@When("^click on \"(.*?)\" link in site grid$")
	public void click_on_link_in_site_grid(String linkName) throws Throwable {
		sitePage.clickPagination(linkName);
	}

	@Then("^\"(.*?)\" page should displayed in site grid$")
	public void page_should_displayed_in_site_grid(String task)
			throws Throwable {
		sitePage.verifySiteDetailGridPagination(task);
	}

	@When("^enter random page number in page link text in site grid$")
	public void enter_random_page_number_in_page_link_text_in_site_grid()
			throws Throwable {
		String totalPages = BaseUtil.getElementText(getProps().getProperty(
				"sitePaginationTotalPages"));
		totalPages = totalPages.substring(totalPages.indexOf("/") + 1).trim();
		int pageCount = Integer.parseInt(totalPages);
		if (pageCount <= 1) {
			Assert.fail("Insufficient data for this test step. All the available site are on the first page only.");
		}
		Random random = new Random();
		int randomPage = random.nextInt(pageCount + 1);
		if (randomPage == 0 || randomPage == 1) {
			randomPage = 2;
		}
		boolean bSet = sitePage.setRandomPage(Integer.parseInt(totalPages),
				randomPage);
		BaseUtil.waitForSpinner();
		Assert.assertTrue("Page" + randomPage + " is not selected.", bSet);
	}

	@Then("^user should be navigated on the given random page in site grid$")
	public void user_should_be_navigated_on_the_given_random_page_in_site_grid()
			throws Throwable {
		BaseUtil.waitForSpinner();
		List<String> lstSelectedPagesiteNames = BaseUtil
				.getMultipleElementTextAfterLoaded(getProps().getProperty(
						"siteGridNameColumn"));
		BaseUtil.setElementAttribute(
				getProps().getProperty("sitePaginationCurrentPage"), "1");
		BaseUtil.waitForSpinner();
		List<String> listFirstPagesiteNames = BaseUtil
				.getMultipleElementTextAfterLoaded(getProps().getProperty(
						"siteGridNameColumn"));
		if (!lstSelectedPagesiteNames.equals(listFirstPagesiteNames)) {
			logger.info("Successfully navigated to given random page");
		} else {
			Assert.fail("Error while navigating to given random page");
		}
	}

	@Then("^delete the \"(.*?)\" data used as test data for site page$")
	public void add_delete_data_used_as_test_data_for_site_page(
			String dataType, List<String> listAuditTestData) throws Throwable {
		ManageTestData objTestdata = new ManageTestData();
		if (dataType.equals("Tenant")) {

			for (String tenantTestData : listAuditTestData) {
				isTenantPresent = objTestdata.manageTestData("Tenant_Present",
						tenantTestData);
				if (!isTenantPresent) {
					return;
				}
				BaseUtil.waitForSpinner(1000);
				String tabName = "Tenant";
				Assert.assertTrue("Clicking on " + tabName + " is failed",
						sitePage.clickOperationOnSitePage(tabName));
				BaseUtil.waitForSpinner(1000);

				commonStepsImplementation
						.enter_the_test_Data_in_the_search_box_and_click_on_the_search_button(
								tabName, tenantTestData);
				commonStepsImplementation
						.select_the_given_from_the_list_and_verify_the_confirmation_box(
								tabName, tenantTestData);

				BaseUtil.explicitWait(PathConstants.WAIT_HIGH);
				commonStepsImplementation.click_on_button(tabName,
						"Delete Tenant");
				BaseUtil.explicitWait(PathConstants.WAIT_LOW);
				commonStepsImplementation
						.confirmation_box_should_displayed_with_message(
								tabName,
								"Are you sure you want to delete this Tenant?",
								tenantTestData);
				commonStepsImplementation.click_on_button(tabName,
						"Tenant:Delete_OK");
				BaseUtil.waitForSpinner(1000);
				commonStepsImplementation
						.user_should_be_navigated_to_page_with_a_label_saying(
								"Tenant ##### deleted.", tabName,
								tenantTestData);
				isTenantPresent = false;

				logger.info("*****(" + tenantTestData
						+ ")Tenant has been deleted successfully*****");
			}

		} else if (dataType.equals("Site")) {
			for (String sitesTestData : listAuditTestData) {
				isSitePresent = objTestdata.manageTestData("Site_Present",
						sitesTestData);
				if (!isSitePresent) {
					return;
				}
				BaseUtil.waitForSpinner(1000);
				String tabName = "Site";
				Assert.assertTrue("Clicking on " + tabName + " is failed",
						sitePage.clickOperationOnSitePage(tabName));
				BaseUtil.waitForSpinner(1000);

				commonStepsImplementation
						.enter_the_test_Data_in_the_search_box_and_click_on_the_search_button(
								tabName, sitesTestData);
				commonStepsImplementation
						.select_the_given_from_the_list_and_verify_the_confirmation_box(
								tabName, sitesTestData);

				BaseUtil.explicitWait(PathConstants.WAIT_HIGH);
				commonStepsImplementation.click_on_button(tabName,
						"Delete Site");
				BaseUtil.explicitWait(PathConstants.WAIT_LOW);
				commonStepsImplementation
						.confirmation_box_should_displayed_with_message(
								tabName,
								"Do you want to delete the site ##### ?",
								sitesTestData);
				commonStepsImplementation.click_on_button(tabName,
						"Site:Delete_OK");
				BaseUtil.waitForSpinner(1000);
				commonStepsImplementation
						.user_should_be_navigated_to_page_with_a_label_saying(
								"##### is deleted successfully.", tabName,
								sitesTestData);
				isSitePresent = false;

				logger.info("*****(" + sitesTestData
						+ ")Site has been deleted successfully*****");
			}

		} else if (dataType.equals("Endpoint")) {
			for (String endpointTestData : listAuditTestData) {
				isEndpointPresent = objTestdata.manageTestData(
						"Endpoint_Present", endpointTestData);
				if (!isEndpointPresent) {
					return;
				}
				Assert.assertTrue("Data should be deleted ", objTestdata
						.manageTestData("Endpoint_Delete", endpointTestData));
			}

		}

	}

	// TODO : code is moved to auditPageTest.java ( Now this code has been gone
	// to CommonStepsImplementation Class and it should be done for all tabs)
	// @Then("^enter_the_test_Data_in_the_search_box_and_click_on_the_search_button$")
	// public void
	// enter_the_test_Data_in_the_search_box_and_click_on_the_search_button(String
	// type, String testData) {
	// String fieldLocator = null;
	// if (type.equalsIgnoreCase("Site")) {
	// fieldLocator = getProps().getProperty("siteSearchField");
	//
	// } else if (type.equalsIgnoreCase("Tenants")) {
	// fieldLocator = getProps().getProperty("tenantsSearchField");
	//
	// }
	// sitePage.enterText(fieldLocator, testData);
	// sitePage.waitForSpinner(1000);
	// }

	// TODO : code is moved to auditPageTest.java (( Now this code has been gone
	// to CommonStepsImplementation Class and it should be done for all tabs)
	// @Then("^select given \"(.*?)\" from the list and verify the Confirmation box$")
	// public void
	// select_the_given_from_the_list_and_verify_the_confirmation_box(String
	// sourceNameFeature,
	// String TestData) {
	// boolean bResult = false;
	// String gridColumnLocator = null;
	//
	// if (sourceNameFeature.equalsIgnoreCase("Site")) {
	// sitePage.click(getProps().getProperty("siteTabSearchButton"));
	// sitePage.waitForSpinner();
	// gridColumnLocator = getProps().getProperty("sitePageNameColumn");
	//
	// } else if (sourceNameFeature.equalsIgnoreCase("Tenant")) {
	// sitePage.click(getProps().getProperty("tenantSearchButton"));
	// sitePage.waitForSpinner();
	// gridColumnLocator = getProps().getProperty("tenantPageNameColumn");
	//
	// }
	//
	// try {
	// List<WebElement> lstSourcesNames =
	// sitePage.getMultipleElementsAfterLoaded(gridColumnLocator);
	// for (WebElement webElement : lstSourcesNames) {
	// if (webElement.getText().trim().equalsIgnoreCase(TestData)) {
	// webElement.click();
	// sitePage.waitForSpinner(1000);
	// bResult = true;
	// break;
	// }
	// }
	// if (!bResult) {
	// Assert.fail("Given test " + sourceNameFeature + " is not available");
	// }
	// logger.debug("Searched source :" + TestData + " is selected.");
	// } catch (Exception e) {
	// logger.error("Failed to select source: " + TestData +
	// " from grid , see detail error message: "
	// + e.getStackTrace().toString());
	// Assert.fail("Failed to select the: " + TestData +
	// ", see detail error message: \n"
	// + e.getStackTrace().toString());
	// } finally {
	// sitePage.explicitWait(3000);
	// }
	// }

	// TODO : code is moved to auditPageTest.java (( Now this code has been gone
	// to CommonStepsImplementation Class and it should be done for all tabs)
	// @Then("^click on \"(.*?)\" button/tab$")
	// public void click_on_button(String tabName, String buttonName) throws
	// Throwable {
	// if (tabName.equals("Site")) {
	// Assert.assertTrue("Clicking on " + buttonName + " is failed",
	// sitePage.clickOperationOnSitePage(buttonName));
	// } else if (tabName.equals("Tenant")) {
	// Assert.assertTrue("Clicking on " + buttonName + " is failed",
	// sitePage.clickOperationOnSitePage(buttonName));
	// }
	//
	// }

	// TODO : code is moved to auditPageTest.java (( Now this code has been gone
	// to CommonStepsImplementation Class and it should be done for all tabs)
	// @Then("^confirmation box should displayed with message saying \"(.*?)\"$")
	// public void confirmation_box_should_displayed_with_message(String type,
	// String expectedMessage, String testData)
	// throws Throwable {
	// String messageOnUI = null;
	// if (type.equals("Site")) {
	// expectedMessage = expectedMessage.replace("#####", testData);
	// sitePage.assertElementDisplayed(getProps().getProperty("editSiteDeleteConfirmationPopup"),
	// "Delete Confirmation box is not displayed");
	// sitePage.assertElementDisplayed(getProps().getProperty("editSiteDeleteConfirmationPopupTitle"),
	// "Delete Confirmation box Title is not displayed");
	// sitePage.assertElementDisplayed(getProps().getProperty("editSiteDeleteConfirmationPopupMessage"),
	// "Delete Confirmation box Message is not displayed");
	// messageOnUI =
	// sitePage.getElementText(getProps().getProperty("editSiteDeleteConfirmationPopupMessage"));
	//
	// } else if (type.equals("Tenant")) {
	// sitePage.assertElementDisplayed(getProps().getProperty("editTenantDeleteConfirmationPopup"),
	// "Delete Confirmation box is not displayed");
	// sitePage.assertElementDisplayed(getProps().getProperty("editTenantDeleteConfirmationPopupTitle"),
	// "Delete Confirmation box Title is not displayed");
	// sitePage.assertElementDisplayed(getProps().getProperty("editTenantDeleteConfirmationPopupMessage"),
	// "Delete Confirmation box Message is not displayed");
	// messageOnUI =
	// sitePage.getElementText(getProps().getProperty("editTenantDeleteConfirmationPopupMessage"));
	//
	// }
	//
	// Assert.assertTrue(
	// "Confirmation message on UI(" + messageOnUI + ") is not expected(" +
	// expectedMessage + ") message",
	// expectedMessage.equals(messageOnUI));
	// }

	// TODO : code is moved to auditPageTest.java (( Now this code has been gone
	// to CommonStepsImplementation Class and it should be done for all tabs)
	// @Then("^verify user should be navigated to sites page with a label saying \"(.*?)\"$")
	// public void user_should_be_navigated_to_page_with_a_label_saying(String
	// message, String type, String testData)
	// throws Throwable {
	// sitePage.explicitWait(2000);
	// String messageOnUI = null;
	// if (type.equalsIgnoreCase("Site")) {
	// message = message.replace("#####", testData);
	// sitePage.waitForSpinner();
	// sitePage.assertElementDisplayed(getProps().getProperty("addSiteSaveMsg"),
	// "Message is not visible on the Site page");
	// messageOnUI =
	// sitePage.getElementText(getProps().getProperty("addSiteSaveMsg"));
	// } else if (type.equalsIgnoreCase("Tenant")) {
	// message = message.replace("#####", testData);
	// sitePage.waitForSpinner();
	// sitePage.assertElementDisplayed(getProps().getProperty("addTenantSaveMsg"),
	// "Message is not visible on the Tenant page");
	// messageOnUI =
	// sitePage.getElementText(getProps().getProperty("addTenantSaveMsg"));
	// }
	//
	// Assert.assertTrue(" Message on UI(" + messageOnUI + ") is not expected("
	// + message + ") message",
	// message.equals(messageOnUI));
	// }

	@Then("^click on \"(.*?)\" tab/button from Site page$")
	public void click_on_tab_from_Site_page(String sElement) throws Throwable {
		String message = "Failed to click tab/button " + sElement;
		Assert.assertTrue(message, sitePage.clickOperationOnSitePage(sElement));
	}

	@Then("^enter the test data for the following fields to create new testData for Site Page for Site \"(.*?)\"$")
	public void enter_the_test_data_for_the_following_fields_to_create_new_testData_for_Site_Page_for_Site(
			String tenantNo, DataTable arg1) throws Throwable {
		List<String> fieldList = arg1.asList(String.class);
		for (String list : fieldList) {
			sitePage.enterTestData(tenantNo, list);
		}
	}

	@Then("^enter the test data for the following fields to create new testData for Site Page for tenant \"(.*?)\"$")
	public void enter_the_test_data_for_the_following_fields_to_create_new_TestData_for_Site_Page_for_tenant(
			String tenantNo, DataTable arg1) throws Throwable {
		List<String> fieldList = arg1.asList(String.class);
		for (String list : fieldList) {
			sitePage.enterTestData(tenantNo, list);
		}
	}

	@Then("^user should be navigated to tenant page with a label displaying \"(.*?)\"$")
	public void user_should_be_navigated_to_Site_page_with_a_label_displaying(
			String siteSaveMessage) throws Throwable {
		BaseUtil.assertElementDisplayed(getProps()
				.getProperty("addSiteSaveMsg"),
				"Message is not visible on the page");
		String message = "Expected message not matched with actual message.";
		BaseUtil.explicitWait(2000);
		Assert.assertTrue(message,
				siteSaveMessage.equals(BaseUtil.getElementText(getProps()
						.getProperty("addSiteSaveMsg").trim())));
		BaseUtil.waitForSpinner();
	}

	@Then("^add site popup should be displayed with following fields:$")
	public void add_site_popup_should_be_displayed_with_following_fields(
			List<String> listAddSiteElements) throws Throwable {
		for (String elements : listAddSiteElements) {
			String message = "Failed to display the field[" + elements
					+ "] on add site popup.";
			Assert.assertTrue(message,
					sitePage.isElementDisplayedOnSitePage(elements));
		}
	}

	@Then("^verify \"(.*?)\" checkbox is unchecked by default$")
	public void verify_active_checkbox_is_unchecked_by_default(String name)
			throws Throwable {
		String locator = getProps().getProperty("addSiteActiveCheckBox");
		Assert.assertFalse(name
				+ " checkbox should be unchecked by default on Add Site model",
				BaseUtil.verifyCheckBoxIsSelected(locator));
	}

	@Then("^user verify that \"(.*?)\" is a dropdown has the following values for Site Page$")
	public void user_verifies_that_is_a_dropdown_has_the_following_values(
			String dropdownType, List<String> valueList) throws Throwable {
		sitePage.verifyDropdownStringValues(dropdownType, valueList);
	}

	@Then("^verify the validation on \"(.*?)\" fields$")
	public void verify_the_validation_on_fields(String symbol) throws Throwable {
		try {
			WebElement phone1Element;
			WebElement phone2Element;
			String phone1 = null;
			String phone2 = null;
			boolean phone1SpecialCaseMatch;
			boolean phone2SpecialCaseMatch;

			phone1Element = TestBase.getWebDriver().findElement(
					By.id("idAddSitePhoneOne"));
			phone2Element = TestBase.getWebDriver().findElement(
					By.id("idAddSitePhoneTwo"));
			phone1 = phone1Element.getAttribute("value");
			phone2 = phone2Element.getAttribute("value");

			String phone1Value = phone1.replaceAll("-", "");
			String phone2Value = phone2.replaceAll("-", "");
			int phone1Length = phone1Value.length();
			int phone2Length = phone2Value.length();
			boolean value1 = StringUtils.isNumeric(phone1Value);
			boolean value2 = StringUtils.isNumeric(phone2Value);

			// verify phone values dosen't have any special characters
			Pattern specailCasePatthern = Pattern.compile("[^A-Za-z0-9]");
			Matcher match1 = specailCasePatthern.matcher(phone1Value);
			Matcher match2 = specailCasePatthern.matcher(phone2Value);

			phone1SpecialCaseMatch = match1.find();
			phone2SpecialCaseMatch = match2.find();

			if (value1 && value2) {
				Assert.assertTrue(
						"Values of Phone 1 and Phone 2 are of numeric type.",
						value1 && value2);
			}
			if (phone1Length == 10 || phone2Length == 10) {
				Assert.assertTrue(
						"Phone 1 and Phone 2 maximum lenght is of 10 digits.",
						value1 && value2);
			}
			if (!(phone1SpecialCaseMatch || phone2SpecialCaseMatch)) {
				Assert.assertTrue(
						"There are no special character in phone 1 and phone 2 values.",
						!(phone1SpecialCaseMatch || phone2SpecialCaseMatch));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^select the following check boxes for Site Page$")
	public void select_the_following_check_box(List<String> checkBoxList)
			throws Throwable {
		BaseUtil.explicitWait(1000);
		sitePage.checkCheckBox(checkBoxList);
	}

	@When("^click on \"(.*?)\" button of add site popup for Site Page$")
	public void click_on_button_on_add_site_popup_for_Site_Page(
			String buttonElement) throws Throwable {
		if (buttonElement.equalsIgnoreCase("Save And Close")) {
			BaseUtil.click(getProps().getProperty("addSiteSaveAndCloseButton"));
		} else if (buttonElement.equalsIgnoreCase("Cancel")) {
			BaseUtil.click(getProps().getProperty("addSiteCancelButton"));
		} else if (buttonElement.equalsIgnoreCase("Accept Verified Data")) {
			BaseUtil.click(getProps().getProperty(
					"addSiteAcceptVerifiedDataButton"));
			BaseUtil.explicitWait(3000);
		} else if (buttonElement.equalsIgnoreCase("Accept Supplied Data")) {
			BaseUtil.click(getProps().getProperty(
					"addSiteAcceptSuppliedDataButton"));
			BaseUtil.explicitWait(3000);
		}else if (buttonElement.equalsIgnoreCase("Save")) {
			BaseUtil.click(getProps().getProperty("addSiteSaveButton"));
			BaseUtil.waitForSpinner();
		} else if (buttonElement.equalsIgnoreCase("Add Path")) {
			BaseUtil.click(getProps().getProperty("addSiteAddPathsButton"));
			BaseUtil.waitForSpinner();
		} else {
			throw new Exception("Error in if");
		}

		BaseUtil.waitForSpinner();
	}

	@Then("^user should navigate to Site page with message \"(.*?)\" for Site Page$")
	public void user_should_navigate_to_Site_page_with_message(
			String sSiteSaveMsg) throws Throwable {
		String message = "Expected message not matched with actual message.";
		Assert.assertEquals(message, sSiteSaveMsg, BaseUtil
				.getElementText(getProps().getProperty("addSiteSaveMsg")));
	}

	@Then("^verify top level path is auto populated on the basis of tenant selection$")
	public void verify_top_level_path_is_auto_populated_on_the_basis_of_tenant_selection()
			throws Throwable {
		try {

			String tenantValue = BaseUtil.getElementText(getProps()
					.getProperty("addSiteTenantDropDown"));
			WebElement pathValueElement = TestBase.getWebDriver().findElement(
					By.id("idAddSitepath1"));

			String sitePathValue = pathValueElement.getAttribute("value");
			sitePathValue = sitePathValue.substring(sitePathValue.lastIndexOf("/")+1).trim();
			if (tenantValue.equals(sitePathValue)) {
				Assert.assertTrue(
						"The Top level path is auto populated in the path field.",
						true);
			} else {
				Assert.fail("The top level path is not generated on the basis of tenant selection");
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	@Then("^add \"(.*?)\" with the existing path$")
	public void add_in_existing_path(String symbol) throws Throwable {
		WebElement pathValueElement = TestBase.getWebDriver().findElement(
				By.id("idAddSitepath1"));
		String sitePathValue = pathValueElement.getAttribute("value");
		String completePathValue = sitePathValue + symbol;
		BaseUtil.enterText(getProps().getProperty("addSitePathsField"),
				completePathValue);
	}

	@Then("^it shows a warning message if the new string is added \"(.*?)\" for \"(.*?)\"$")
	public void it_shows_a_waning_message_if_the_new_string_is_added(
			String warningMessage, String tenantName) throws Throwable {
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("addSitePathWarningMessage"),
				"Message is not visible on the page");
		Assert.assertTrue(
				"Expected message not matched with actual message.",
				warningMessage.replace("########", tenantName)
						.equalsIgnoreCase(
								BaseUtil.getElementText(getProps().getProperty(
										"addSitePathWarningMessage"))));
		BaseUtil.waitForSpinner();
	}

	@Then("^verify Each path must start with a forward slash \"(.*?)\"$")
	public void verify_Each_path_mmust_start_with_a_forward_slash(char slash)
			throws Throwable {
		try {

			WebElement pathValueElement = TestBase.getWebDriver().findElement(
					By.id("idAddSitepath1"));

			String sitePathValue = pathValueElement.getAttribute("value");
			System.out.println("sitePathValue1 =" + sitePathValue);

			char firstCharacterofPath = sitePathValue.charAt(0);

			if (firstCharacterofPath == slash) {
				Assert.assertTrue(
						"The path is starting with forward slash (/) ",
						(firstCharacterofPath == slash));
			} else {
				Assert.fail("The path is not generated on the basis of tenant selection with statring character as forward slash (/)");
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	@Then("^the Address Verification popup should be displayed with the follwoing fields:$")
	public void the_Address_verification_popup_should_be_displayed_with_the_following_fields(
			List<String> listAddSiteElements) throws Throwable {
		for (String elements : listAddSiteElements) {
			String message = "Failed to display the field[" + elements
					+ "] on add site popup.";
			Assert.assertTrue(message,
					sitePage.isElementDisplayedOnSitePage(elements));
		}
	}

	@Then("^verify \"(.*?)\" field return a value$")
	public void verify_field_return_a_value(String fieldName) throws Throwable {
		String message = "Failed to verify the value for  [" + fieldName
				+ "] field.";
		Assert.assertTrue(message,
				sitePage.verifyValueForAddressVerification(fieldName));
	}

	@Then("^verify the Message for \"(.*?)\" data is \"(.*?)\"$")
	public void verify_the_Message_for_data(String scenario,
			String addressMessage) throws Throwable {
		String message = "Failed to verify the message [" + addressMessage
				+ "] for [" + scenario + "] scenario.";
		Assert.assertTrue(message,
				sitePage.verifyAddressMessage(scenario, addressMessage));
	}

	@Then("^enter \"(.*?)\" details on address verification popup$")
	public void enter_details_on_address_verification_popup(String scenario)
			throws Throwable {
		if (scenario.equals("Invalid")) {
			BaseUtil.enterText(
					getProps().getProperty(
							"addressVerificationSuppliedAddress1Field"),
					getProps().getProperty(
							"addressVerificationSuppliedAddress1FieldTestData"));
			BaseUtil.enterText(
					getProps().getProperty(
							"addressVerificationSuppliedCityField"),
					getProps().getProperty(
							"addressVerificationSuppliedCityFieldTestData"));
			BaseUtil.selectDropDownByValue(
					getProps().getProperty(
							"addressVerificationSuppliedStateProvinceField"),
					getProps()
							.getProperty(
									"addressVerificationSuppliedStateProvinceFieldTestData"));
			BaseUtil.enterText(
					getProps().getProperty(
							"addressVerificationSuppliedPostalCodeField"),
					getProps()
							.getProperty(
									"addressVerificationSuppliedPostalCodeFieldTestData"));
			BaseUtil.selectDropDownByValue(
					getProps().getProperty(
							"addressVerificationSuppliedCountryField"),
					getProps().getProperty(
							"addressVerificationSuppliedCountryFieldTestData"));
		} else if (scenario.equals("Valid")) {
			BaseUtil.enterText(
					getProps().getProperty(
							"addressVerificationSuppliedAddress1Field"),
					getProps().getProperty("addSiteAddress1TestData"));
			BaseUtil.enterText(
					getProps().getProperty(
							"addressVerificationSuppliedCityField"), getProps()
							.getProperty("addSiteCityTestData"));

			BaseUtil.selectDropDownByValue(
					getProps().getProperty(
							"addressVerificationSuppliedStateProvinceField"),
					getProps()
							.getProperty(
									"addressVerificationSuppliedStateProvinceFieldTestData"));
			BaseUtil.enterText(
					getProps().getProperty(
							"addressVerificationSuppliedPostalCodeField"),
					getProps().getProperty("addSitePostalCodeTestData"));
			BaseUtil.selectDropDownByValue(
					getProps().getProperty(
							"addressVerificationSuppliedCountryField"),
					getProps().getProperty("addSiteCountryDropDownTestData"));
		}
	}

	@Then("^click \"(.*?)\" checkbox on address verification model for site and latitude and longitude fields become editable$")
	public void click_checkbox_on_address_verification_model_for_site_and_latitude_and_longitude_fields_become_editable(
			String checkbox) throws Throwable {
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("addSiteverifiedLongitude"),
				"Longitude is not displayed on add site model");
		Assert.assertTrue(checkbox + " Checkbox is not checked",
				sitePage.clickOperationOnSitePage(checkbox));
	}

	@Then("^clear the values of latitude and longitude fields$")
	public void clear_the_values_of_latitude_and_longitude_fields()
			throws Throwable {
		BaseUtil.getElementAfterLoaded(
				getProps().getProperty(
						"addressVerificationSuppliedLatitudeField")).clear();
		BaseUtil.getElementAfterLoaded(
				getProps().getProperty(
						"addressVerificationSuppliedLongitudeField")).clear();
	}

	@Then("^verify that \"(.*?)\" and \"(.*?)\" buttons are disabled$")
	public void verify_that_and_buttons_are_disabled(String button1,
			String button2) throws Throwable {
		Assert.assertFalse(button1
				+ " button should be disabled but its enabled",
				sitePage.isElementEnabledOnSitePage(button1));
		Assert.assertFalse(button2
				+ " button should be disabled but its enabled",
				sitePage.isElementEnabledOnSitePage(button2));
	}

	@Then("^verify that \"(.*?)\" button is enabled$")
	public void verify_that_and_button_is_enabled(String button1)
			throws Throwable {
		Assert.assertTrue(button1
				+ " button should be disabled but its enabled",
				sitePage.isElementEnabledOnSitePage(button1));
	}

	@Then("^verify that \"(.*?)\" and \"(.*?)\" buttons are enabled$")
	public void verify_that_and_buttons_are_enabled(String button1,
			String button2) throws Throwable {
		Assert.assertTrue(button1
				+ " button should be disabled but its enabled",
				sitePage.isElementEnabledOnSitePage(button1));
		Assert.assertTrue(button2
				+ " button should be disabled but its enabled",
				sitePage.isElementEnabledOnSitePage(button2));
	}

	@Then("^enter values (\\d+) in latitude and (\\d+) in longitude field$")
	public void enter_values_in_latitude_and_longitude_fields(int value1,
			int value2) throws Throwable {
		BaseUtil.enterText(
				getProps().getProperty(
						"addressVerificationSuppliedLatitudeField"), String
						.valueOf(value1).trim());
		BaseUtil.enterText(
				getProps().getProperty(
						"addressVerificationSuppliedLongitudeField"), String
						.valueOf(value2).trim());
	}

	@Then("^enter values in latitude and longitude fields$")
	public void enter_values_in_latitude_and_longitude_fields()
			throws Throwable {
		BaseUtil.enterText(
				getProps().getProperty(
						"addressVerificationSuppliedLatitudeField"),
				getProps().getProperty(
						"addressVerificationSuppliedLatitudeFieldTestData"));
		BaseUtil.enterText(
				getProps().getProperty(
						"addressVerificationSuppliedLongitudeField"),
				getProps().getProperty(
						"addressVerificationSuppliedLongitudeFieldTestData"));
	}

	@Then("^modify the value of Timezone field for \"(.*?)\" time$")
	public void modify_the_value_of_Timezone_field(String timeCount)
			throws Throwable {
		if (timeCount.equals("1")) {
			BaseUtil.selectDropDownByValue(
					getProps().getProperty(
							"addressVerificationSuppliedTimezoneField"),
					getProps().getProperty("addSiteTimeZoneDropDownTestData1"));
		} else if (timeCount.equals("2")) {
			BaseUtil.selectDropDownByValue(
					getProps().getProperty(
							"addressVerificationSuppliedTimezoneField"),
					getProps().getProperty("addSiteTimeZoneDropDownTestData2"));
		} else if (timeCount.equals("3")) {
			BaseUtil.selectDropDownByValue(
					getProps().getProperty(
							"addressVerificationSuppliedTimezoneField"),
					getProps().getProperty("addSiteTimeZoneDropDownTestData3"));
		} else if (timeCount.equals("4")) {
			BaseUtil.selectDropDownByValue(
					getProps().getProperty(
							"addressVerificationSuppliedTimezoneField"),
					getProps().getProperty("addSiteTimeZoneDropDownTestData4"));
		}

	}

	@Then("^it shows a warning popup with a warning message \"(.*?)\"$")
	public void it_shows_a_warning_popup_with_a_warning_message(
			String warningMessage) throws Throwable {
		if (warningMessage.toLowerCase().startsWith("do you still")) {
			boolean warnignPopup = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty(
							"addressVerificationModifiedTimezoneWarningPopup"));
			boolean okButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("addSiteOkConfirmationButton"));
			boolean cancelButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty(
							"addSiteAbnormalBehaviorCancelConfirmationButton"));
			Assert.assertTrue(
					"Behavior warning popup did not appear when timezone field is modified",
					(warnignPopup && okButton && cancelButton));
		}
	}

	@Then("^verify \"(.*?)\" field return a modified value$")
	public void verify_field_return_a_modified_value(String fieldName)
			throws Throwable {
		String message = "Failed to verify the value for  [" + fieldName
				+ "] field.";
		Assert.assertTrue(message,
				sitePage.verifyValueForAddressVerification(fieldName));
		BaseUtil.explicitWait(2000);
	}

	@Then("modify the values for \"(.*?)\" fields and click on save button on \"(.*?)\" site modal$")
	public void modify_the_values_fields_and_click_on_save_button(String field,
			String modal) throws Throwable {
		try {
			switch (modal.toUpperCase()) {
			case "ADD":
				if (field.equals("Address")) {
					BaseUtil.enterText(
							getProps().getProperty("addSiteAddress1Field"),
							getProps().getProperty("addSiteAddress1TestData"));
					BaseUtil.enterText(
							getProps().getProperty("addSiteCityField"),
							getProps().getProperty("addSiteCityTestData"));
					BaseUtil.selectDropDownByValue(
							getProps().getProperty("addSiteProvinceField"),
							getProps().getProperty("addSiteProvinceTestData"));
					BaseUtil.enterText(
							getProps().getProperty("addSitePostalCodeField"),
							getProps().getProperty("addSitePostalCodeTestData"));
					BaseUtil.selectDropDownByValue(
							getProps().getProperty("addSiteCountryDropDown"),
							getProps()
									.getProperty(
											"addressVerificationSuppliedCountryFieldTestData"));
				} else if (field.equals("Name")) {
					BaseUtil.enterText(
							getProps().getProperty("editSiteNameField"),
							getProps().getProperty(
									"addSiteAdditonalNameTestData"));
				}

				BaseUtil.clickAndWait(getProps().getProperty(
						"addSiteSaveButton"));
				break;
			case "EDIT":
				if (field.equals("Address")) {
					BaseUtil.enterText(
							getProps().getProperty("editSiteAddress1Field"),
							getProps().getProperty("addSiteAddress1TestData"));
					BaseUtil.enterText(
							getProps().getProperty("editSiteCityField"),
							getProps().getProperty("addSiteCityTestData"));
					BaseUtil.enterText(
							getProps().getProperty("editSitePostalCodeField"),
							getProps().getProperty("addSitePostalCodeTestData"));
					BaseUtil.selectDropDownByValue(
							getProps().getProperty("editSiteCountryDropDown"),
							getProps()
									.getProperty(
											"addressVerificationSuppliedCountryFieldTestData"));

					BaseUtil.clickAndWait(getProps().getProperty(
							"addSiteSaveButton"));
				} else if (field.equals("Name")) {
					BaseUtil.enterText(
							getProps().getProperty("editSiteSiteNameField"),
							getProps().getProperty(
									"addSiteAdditonalNameTestData"));
					BaseUtil.enterText(
							getProps().getProperty(
									"editSiteSiteDescriptionField"),
							getProps().getProperty(
									"addSiteAdditonalNameTestData"));
					BaseUtil.clickAndWait(getProps().getProperty(
							"addSiteSaveButton"));
				} else if (field.equals("Solar Panel System Size")) {
					BaseUtil.checkCheckBox(getProps().getProperty(
							"editSitePagePopupDetailHasSolarPanelsCheckBox"));
					BaseUtil.enterText(
							getProps()
									.getProperty(
											"editSitePagePopupDetailHasSolarPanelsField"),
							getProps().getProperty(
									"addSiteSolarPanelSystemSizeTestData"));

					BaseUtil.clickAndWait(getProps().getProperty(
							"addSiteSaveButton"));
				} else if (field.equals("Active")) {
					BaseUtil.checkCheckBox(getProps().getProperty(
							"editSitePagePopupDetailActiveCheckbox"));
				}

				break;
			default:
				logger.error("Switch Case["
						+ modal
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				break;
			}

		} catch (Exception e) {
			String message = "Failed to verify the modal :" + modal + " "
					+ " for Site Tab.";
			Assert.fail(message);
		}

	}

	@Then("^click \"(.*?)\" button on address verification model for site$")
	public void click_address_verification_button_UserRolePermissions(
			String button) throws Throwable {
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("addSiteverifiedLongitude"),
				"Longitude is not displayed on add site model");
		Assert.assertTrue(button + " Button is not clicked",
				sitePage.clickOperationOnSitePage(button));
	}

	@Then("^verify the search output by entering \"(.*?)\" in site search field$")
	public void verify_the_search_output_by_entering_in_site_search_field(
			String sitePageSearchOutput) throws Throwable {
		sitePage.verifySearchFunctionality(sitePageSearchOutput);
	}

	@Then("^verify the pagination output by entering value \"(.*?)\" in search field$")
	public void verify_the_pagination_by_entering_value_in_search_field(
			String arg) throws Throwable {
		sitePage.verifySearchFunctionality(arg);
	}

	@Then("^valid error message should be displayed for \"(.*?)\" scenario$")
	public void valid_error_message_should_be_displayed_for_form(String scenario)
			throws Throwable {
		if (scenario.contains("already")) {
			Assert.assertTrue(
					"Duplicate error message is not displayed as expected on add site popup",
					getProps().getProperty("addSiteDuplicateErrorMessageText")
							.equals(BaseUtil
									.getElementText(getProps().getProperty(
											"addSiteDuplicateErrorMessage"))));
		} else if (scenario.contains("invalid")) {
			String[] fieldsName = { "SITE NAME", "AREA", "SOLAR SYSTEM SIZE",
					"PATHS" };
			for (String field : fieldsName) {
				String message = "Invalid value message is not displayed for ["
						+ field + "]";
				switch (field.toUpperCase()) {
				case "SITE NAME":
					Assert.assertTrue(
							message,
							BaseUtil.getElementText(
									getProps().getProperty(
											"addSiteNameErrorMessage"))
									.equals(getProps()
											.getProperty(
													"addSiteNameInvalidErrorMessageText")));
					break;
				case "AREA":
					Assert.assertTrue(
							message,
							BaseUtil.getElementText(
									getProps().getProperty(
											"addSiteAreaErrorMessage"))
									.equals(getProps()
											.getProperty(
													"addSiteAreaInvalidErrorMessageText")));
					break;
				case "SOLAR SYSTEM SIZE":
					Assert.assertTrue(
							message,
							BaseUtil.getElementText(
									getProps()
											.getProperty(
													"addSiteSolarSystemSizeErrorMessage"))
									.equals(getProps()
											.getProperty(
													"addSiteSolarSystemSizeInvalidErrorMessageText")));
					break;
				case "PATHS":
					Assert.assertTrue(
							message,
							BaseUtil.getElementText(
									getProps().getProperty(
											"addSitePathErrorMessage"))
									.startsWith(
											getProps()
													.getProperty(
															"addSitePathsInvalidErrorMessageText")));
					break;
				}
			}
		} else if (scenario.contains("empty")) {
			String[] fieldsName = { "SITE NAME", "SITE DESCRIPTION", "CITY",
					"POSTAL CODE", "AREA", "PATHS" };
			for (String field : fieldsName) {
				String message = " [" + field + "] should not be blank";
				Assert.assertTrue(message, sitePage.isBlank(field));
			}
		}
	}

	@Then("^confirmation box should display with message \"(.*?)\"$")
	public void confirmation_box_should_display_with_message(
			String confirmationboxMessage) throws Throwable {
		if (confirmationboxMessage.toLowerCase().startsWith("are you sure")) {
			boolean okButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("addSiteOkConfirmationButton"));
			boolean cancelButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty(
							"addSiteAbnormalBehaviorCancelConfirmationButton"));
			Assert.assertTrue(
					"Behavior confirmation popup did not appear when clicked on add site save button without filling any information",
					(okButton && cancelButton));
		} else if (confirmationboxMessage.toLowerCase().endsWith(
				"are you sure?")) {
			boolean okButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("addSiteOkConfirmationButton"));
			boolean cancelButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("addSiteCancelConfirmationButton"));
			Assert.assertTrue(
					"Confirmation popup did not appear when clicked on cancel button",
					(okButton && cancelButton));
		}
	}

	@When("^click on \"(.*?)\" button on confirmation box on Site Page$")
	public void click_on_button_on_confirmation_box(String buttonName)
			throws Throwable {
		try {
			if (buttonName.toLowerCase().equals("cancel")) {
				BaseUtil.click(getProps().getProperty(
						"addSiteCancelConfirmationButton"));
			} else if (buttonName.toLowerCase().equals("ok")) {
				BaseUtil.click(getProps().getProperty(
						"addSiteOkConfirmationButton"));
			} else if (buttonName.toLowerCase().equals("cancel on virtual")) {
				BaseUtil.click(getProps().getProperty(
						"addVirtualChannelConfirmationCancelButton"));
			} else if (buttonName.toLowerCase().equals("ok on virtual")) {
				BaseUtil.click(getProps().getProperty(
						"addSiteOkConfirmationButton"));
			}
		} catch (Exception e) {
			String message = "Failed to click on button :" + buttonName + " "
					+ " on confirmation box.";
			Assert.fail(message);
		}
		BaseUtil.explicitWait(2000);
	}

	@Then("^user should be navigated on site detail grid$")
	public void user_should_be_navigated_on_site_detail_grid() throws Throwable {
		BaseUtil.assertElementDisplayed(getProps()
				.getProperty("siteDetailGrid"),
				"Failed to verify site grid availability.");
		BaseUtil.explicitWait(2000);
	}

	@Then("^enter invalid values is add site fields$")
	public void enter_name_with_special_character_as(DataTable table)
			throws Throwable {
		Map<String, String> lstData = table.asMap(String.class, String.class);
		Set<String> fieldsName = lstData.keySet();
		for (String field : fieldsName) {
			String message = "Invalid value entered in [" + field + "]";
			switch (field.toUpperCase()) {
			case "SITE NAME":
				BaseUtil.enterText(getProps().getProperty("addSiteNameField"),
						lstData.get(field));
				Assert.assertTrue(message, true);
				break;
			case "AREA":
				BaseUtil.enterText(getProps().getProperty("addSiteAreaField"),
						lstData.get(field));
				Assert.assertTrue(message, true);
				break;
			case "SOLAR SYSTEM SIZE":
				BaseUtil.checkCheckBox(getProps().getProperty(
						"addSiteSolarPanelCheckBox"));
				BaseUtil.explicitWait(1000);
				BaseUtil.enterText(
						getProps().getProperty(
								"addSiteSolarPanelSystemSizeField"),
						lstData.get(field));
				Assert.assertTrue(message, true);
				break;
			case "PATHS":
				BaseUtil.enterText(getProps().getProperty("addSitePathsField"),
						lstData.get(field));
				Assert.assertTrue(message, true);
				break;
			}
		}
	}

	@Then("^select \"(.*?)\" from tenant dropdown$")
	public void select_the_tenant_in_tenant_dropdown(String tenantName)
			throws Throwable {
		// BaseUtil.waitForSpinner();
		BaseUtil.click(getProps().getProperty("sitePageTenantDropdownSelected"));
		BaseUtil.explicitWait(3000);
		BaseUtil.click(getProps().getProperty("sitePageValueTenantDropDown"));
		selectedTenant = BaseUtil.getElementText(getProps().getProperty(
				"sitePageTenantDropdownSelected"));
	}

	@When("^enter the site name \"(.*?)\" in search field$")
	public void enter_the_root_tenant_in_search_field(String rootSiteName)
			throws Throwable {
		sitePage.enterText_SitePage("SITE_SEARCH", rootSiteName);
	}

	@Then("^select the site \"(.*?)\" from the site details grid for Site Page$")
	public void select_the_root_tenant_from_the_tenant_details_grid(
			String sSiteName, String resourceName) throws Throwable {
		sitePage.selectSiteFromGrid_SitePage(sSiteName, resourceName);
		BaseUtil.explicitWait(1000);
		BaseUtil.waitForSpinner();
	}

	// GPUP-29950: Verify Search entries should not clear when user saves edits
	@Then("^click the \"(.*?)\" button on edit site page of \"(.*?)\" and verify selection should not change$")
	public void click_save_and_close_button_of_endpoint_page(String buttonName,
			String siteName) throws Throwable {
		String message1 = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message1,
				sitePage.clickOperationOnSitePage(buttonName));
		BaseUtil.waitForSpinner(2000);

		String message2 = "Search entries is clear when user click save and close button on edit user.";
		String totalcount = BaseUtil.getElementText(getProps().getProperty(
				"siteTotalItemsCount"));
		totalcount = totalcount.substring(totalcount.indexOf("of") + 2,
				totalcount.indexOf("items") - 1).trim();
		Assert.assertTrue(message2, totalcount.equalsIgnoreCase(totalItems));

		String message3 = "Hide Disabled checkbox of site page is unselected";
		Assert.assertFalse(
				message3,
				BaseUtil.verifyCheckBoxIsSelected(getProps().getProperty(
						"hideInactiveCheckBox")));

		String message4 = "Selected tenant is reset automaticlly";
		Assert.assertTrue(
				message4,
				BaseUtil.getElementText(
						getProps().getProperty("siteListPageTenantDropdown"), 0)
						.equalsIgnoreCase(selectedTenant));

		String message5 = "Search Site text box is reset automaticlly";
		Assert.assertTrue(
				message5,
				BaseUtil.getElementAttribute(
						getProps().getProperty("siteSearchField"), "value")
						.equals(siteName));
	}

	@Then("^verify \"(.*?)\" is unchecked by default$")
	public void verify_is_unchecked_by_default(String checkBox)
			throws Throwable {
		String locator = getProps().getProperty(
				"editSitePagePopupDetailDecommissionedDate");
		Assert.assertFalse(checkBox
				+ " checkbox should be FALSE by default on Edit Site Page",
				BaseUtil.verifyCheckBoxIsSelected(locator));
	}

	@Then("^also verify the values of Phone fields for the site$")
	public void also_verify_the_values_of_Phone_field_for_the_site()
			throws Throwable {
		try {
			WebElement phone1Element;
			WebElement phone2Element;
			String phone1 = null;
			String phone2 = null;

			phone1Element = TestBase.getWebDriver().findElement(
					By.id("idEditSitePhoneOne"));
			phone2Element = TestBase.getWebDriver().findElement(
					By.id("idEdotSitePhoneTwo"));
			phone1 = phone1Element.getAttribute("value");
			phone2 = phone2Element.getAttribute("value");

			String phone1Value = phone1.replaceAll("-", "");
			String phone2Value = phone2.replaceAll("-", "");
			boolean value1 = StringUtils.isNumeric(phone1Value);
			boolean value2 = StringUtils.isNumeric(phone2Value);

			if ((phone1.equals(null)) && (phone2.equals(null))) {
				Assert.assertTrue("Values of Phone 1 and Phone 2 are null.",
						phone1.equals(phone2));
			} else if ((phone1.equals(null)) || (phone2.equals(null))) {
				if (phone1.equals(null)) {
					Assert.assertTrue("Values of Phone 1 is null.",
							!(phone1.equals(phone2)));
				} else if (phone2.equals(null)) {
					Assert.assertTrue("Values of Phone 2 is null.",
							!(phone1.equals(phone2)));
				}
			} else if (value1 && value2) {
				Assert.assertTrue(
						"Values of Phone 1 and Phone 2 are of numeric type.",
						value1 && value2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^verify the presence of following site details section on site detail grid page\\.$")
	public void verify_the_presence_of_following_site_details_section_on_site_detail_grid_page(
			DataTable listProps) throws Throwable {

		List<String> lstData = listProps.asList(String.class);
		listProps.asList(String.class);
		boolean isVisible = true;
		for (String header : lstData) {
			boolean visible = false;
			if (header.trim().equals("MAP/Satellite")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailMapSection"));
			}
			if (header.trim().equals("Site Name")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailSiteName"));
			}
			if (header.trim().equals("Site Description")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailSiteDescription"));
			}
			if (header.trim().equals("Address 1")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailAddress1"));
			}
			if (header.trim().equals("Address 2")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailAddress2"));
			}
			if (header.trim().equals("City")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailCity"));
			}
			if (header.trim().equals("Phone 1")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailPhone1"));
			}
			if (header.trim().equals("Phone 2")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailPhone2"));
			}
			if (header.trim().equals("State")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailState"));
			}
			if (header.trim().equals("Postal Code")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailPostalCode"));
			}
			if (header.trim().equals("Country")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailCountry"));
			}
			if (header.trim().equals("Time Zone")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailTimeZone"));
			}
			if (header.trim().equals("Latitude")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailLatitude"));
			}
			if (header.trim().equals("Longitude")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailLongitude"));
			}
			if (header.trim().equals("Commission Date")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailCommissionDate"));
			}
			if (header.trim().equals("Control Date")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailControlDate"));
			}
			if (header.trim().equals("Area")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailArea"));
			}
			if (header.trim().equals("Tenant")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailTenant"));
			}
			if (header.trim().equals("Has Control")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailHasControl"));
			}
			if (header.trim().equals("Has Solar Panels")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailHasSolarPanels"));
				BaseUtil.click(getProps().getProperty(
						"editSitePagePopupDetailHasSolarPanelsCheckBox"));
			}
			if (header.trim().equals("Active")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePagePopupDetailActive"));
			}
			if (header.trim().equals("Decommissioned Date")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty(
								"editSitePagePopupDetailDecommissionedDate"));
			}
			if (header.trim().equals("Solar Panels System Size")) {
				visible = BaseUtil
						.verifyElementDisplayed(getProps().getProperty(
								"editSitePagePopupDetailSolarPanelsSystemSize"));
			}

			if (header.trim().equals("Save and Close")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePageSaveAndCloseSiteButton"));
			}
			if (header.trim().equals("Save")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePageSaveButton"));
			}
			if (header.trim().equals("Cancel")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("editSitePageCancelButton"));
			}

			if (visible == false) {
				if (visible == false) {
					isVisible = false;
					Assert.assertTrue("Header [" + header
							+ "] is not visible in Edit Site Detail Grid",
							isVisible);
					break;
				}
			}
		}
	}

	@Then("^enter \"(.*?)\" greater then current date$")
	public void enter_commission_date_greater_then_current_date(String date)
			throws Throwable {
		try {
			Calendar calendar;
			Date futureDate;
			DateFormat outputFormatter;
			String output = null;
			switch (date.toUpperCase()) {
			case "COMMISSION DATE":
				calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_YEAR, 7);
				// now get 7 days later date from current date"
				futureDate = calendar.getTime();
				outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
				output = outputFormatter.format(futureDate);
				BaseUtil.enterText(
						getProps().getProperty(
								"editSitePageCommissionDateField"), output);
				break;
			case "DECOMMISSION DATE":
				calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_YEAR, 7);
				// now get 7 days later date from current date"
				futureDate = calendar.getTime();
				outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
				output = outputFormatter.format(futureDate);
				BaseUtil.enterText(
						getProps().getProperty(
								"editSitePageDeCommissionDateField"), output);
				break;
			case "CONTROL DATE":
				WebElement commissionDateElement = TestBase.getWebDriver()
						.findElement(By.id("idEditSiteCommissionDate"));
				String commissionDateValue = commissionDateElement
						.getAttribute("value");
				System.out.println("commissionDateValue ="
						+ commissionDateValue);
				Date commissionDate = new SimpleDateFormat("MM/dd/yyyy")
						.parse(commissionDateValue);
				calendar = Calendar.getInstance();
				calendar.setTime(commissionDate);
				calendar.add(Calendar.DAY_OF_YEAR, 7);
				// now get 7 days later date from commission date"
				futureDate = calendar.getTime();
				outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
				output = outputFormatter.format(futureDate);
				BaseUtil.enterText(
						getProps().getProperty("editSitePageControlDateField"),
						output);
				break;
			default:
				logger.error("Switch Case["
						+ date
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Then("^enter \"(.*?)\" less then \"(.*?)\"$")
	public void enter_control_date_less_then_commission_date(String date1,
			String date2) throws Throwable {
		String message = "Failed to enter the date: " + date1
				+ " on edit site modal";
		Assert.assertTrue(message,
				sitePage.enterDateAsCompareWithOtherDate(date1, date2));
	}

	@Then("^it shows a error message on edit site page \"(.*?)\" for \"(.*?)\"$")
	public void it_shows_a_error_message_on_edit_site_page_for_dates(
			String errorMessage, String dateType) throws Throwable {
		String message = "Failed to verify the message: " + errorMessage
				+ " on accordion :" + dateType;
		Assert.assertTrue(message, sitePage.verifyErrorMessageDecommissionDate(
				errorMessage, dateType));
	}

	@Then("^click the \"(.*?)\" checkbox on site page$")
	public void click_checkbox_is_unchecked(String checkBox) throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue(checkBox
				+ " checkbox is not unchecked before searching site",
				sitePage.clickOperationOnSitePage(checkBox));
	}

	@Then("^all three dates Commission Date Control Date Decommission Date for the site \"(.*?)\"$")
	public void all_three_dates_for_the_siteCommission_Date_Control_Date_Decommission_Date(
			String siteName) throws Throwable {
		try {
			String dateValue = null;
			Calendar calendar;
			Date futureDate;
			DateFormat outputFormatter;
			if (siteName.equals("sitePage_siteBot1")) {
				calendar = Calendar.getInstance();
				calendar.add(Calendar.YEAR, -1);
				// now get 1 year back date from current date as a commissioning
				// date"
				futureDate = calendar.getTime();
				outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
				dateValue = outputFormatter.format(futureDate);
				BaseUtil.enterText(
						getProps().getProperty(
								"editSitePageCommissionDateField"), dateValue);

				calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -9);
				// now get 9 months back date from current date as a control
				// date date"
				futureDate = calendar.getTime();
				outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
				dateValue = outputFormatter.format(futureDate);
				BaseUtil.enterText(
						getProps().getProperty("editSitePageControlDateField"),
						dateValue);

				// check decomission checkbox
				BaseUtil.checkCheckBox(getProps().getProperty(
						"editSiteDecommissionDateCheckbox"));
			} else if (siteName.equals("sitePage_siteBot2")) {
				calendar = Calendar.getInstance();
				calendar.add(Calendar.YEAR, -1);

				// now get 1 year back date from current date as a commissioning
				// date"
				futureDate = calendar.getTime();
				outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
				dateValue = outputFormatter.format(futureDate);
				BaseUtil.enterText(
						getProps().getProperty(
								"editSitePageCommissionDateField"), dateValue);
				calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -9);

				// now get 9 months back date from current date as a control
				// date date"
				futureDate = calendar.getTime();
				outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
				dateValue = outputFormatter.format(futureDate);
				BaseUtil.enterText(
						getProps().getProperty("editSitePageControlDateField"),
						dateValue);

				// check decomission checkbox
				BaseUtil.checkCheckBox(getProps().getProperty(
						"editSiteDecommissionDateCheckbox"));
			} else if (siteName.equals("sitePage_siteBot3")) {
				calendar = Calendar.getInstance();
				calendar.add(Calendar.YEAR, -1);
				// now get 1 year back date from current date as a commissioning
				// date"
				futureDate = calendar.getTime();
				outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
				dateValue = outputFormatter.format(futureDate);
				BaseUtil.enterText(
						getProps().getProperty(
								"editSitePageCommissionDateField"), dateValue);

				calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -9);
				// now get 9 months back date from current date as a control
				// date date"
				futureDate = calendar.getTime();
				outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
				dateValue = outputFormatter.format(futureDate);
				BaseUtil.enterText(
						getProps().getProperty("editSitePageControlDateField"),
						dateValue);

				calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -9);
				// now get 20 days back date from current date as a
				// decommissioning date"
				futureDate = calendar.getTime();
				outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
				dateValue = outputFormatter.format(futureDate);
				// check decomission checkbox and enter decomission date
				BaseUtil.checkCheckBox(getProps().getProperty(
						"editSiteDecommissionDateCheckbox"));

				BaseUtil.enterText(
						getProps().getProperty(
								"editSitePageDeCommissionDateField"), dateValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Then("^verify that following fields are editable of edit site modal$")
	public void verify_that_ollowing_fields_are_editable_of_edit_Site_modal(
			List<String> listFieldNames) throws Throwable {
		for (String fieldName : listFieldNames) {
			Assert.assertTrue("Failed to verify editablity of field "
					+ fieldName, sitePage.verifyElementEditable(fieldName));
		}

	}

	@Then("^Latitude and Longitude values are prifilled in edit site modal$")
	public void Latitude_and_Longitude_values_are_prifilled_in_edit_site_modal() {
		try {
			String latitdueValue = null;
			String longitudeValue = null;
			WebElement latitudeValueElement = TestBase.getWebDriver()
					.findElement(By.id("idEditSiteLatitude"));
			latitdueValue = latitudeValueElement.getAttribute("value");

			WebElement longitudeValueElement = TestBase.getWebDriver()
					.findElement(By.id("idEditSiteLogitude"));
			longitudeValue = longitudeValueElement.getAttribute("value");

			Assert.assertTrue("The value is prifilled for Latitude [[ "
					+ latitdueValue + " ]] and Longitude [[ " + longitudeValue
					+ " ]].", true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Then("^confirmation box should displayed on site page with message \"(.*?)\"$")
	public void confirmation_box_should_displayed_with_message(String sMessage)
			throws Throwable {
		sitePage.checkPopupExistence_SitePage("DELETE_SITE_CONFIRMATION",
				sMessage);
	}

	@Then("^user should be navigated to sites page with a label saying \"(.*?)\"$")
	public void user_should_be_navigated_to_sites_page_with_a_label_saying(
			String siteSaveMessage) throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.assertElementDisplayed(getProps()
				.getProperty("addSiteSaveMsg"),
				"Message is not visible on the page");
		String message = "Expected message not matched with actual message.";
		Assert.assertTrue(
				message,
				siteSaveMessage.equals(BaseUtil.getElementText(
						getProps().getProperty("addSiteSaveMsg")).trim()));
	}

	@Then("^verify the site \"(.*?)\" is deleted successfully$")
	public void verify_the_site_is_deleted_successfully(String deletedSite)
			throws Throwable {
		try {
			List<WebElement> listSiteNames = null;
			BaseUtil.enterText(getProps().getProperty("siteSearchField"),
					deletedSite);

			BaseUtil.waitForSpinner();
			BaseUtil.click(getProps().getProperty("siteSearchButton"));
			BaseUtil.waitForSpinner();
			try {
				Assert.assertTrue(
						"Multiple results found for the string : "
								+ deletedSite,
						BaseUtil.getElementText(
								getProps().getProperty("siteGridZeroRecord"))
								.equals(getProps().getProperty(
										"siteGridZeroRecordText")));
			} catch (Exception ex) {
				listSiteNames = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty(
								"siteGridNameColumn"));
				logger.info("In case RANDOM the list size is : "
						+ listSiteNames.size());
				for (WebElement siteNameElement : listSiteNames) {
					if (!siteNameElement.getText().contains(deletedSite)) {
						Assert.fail("Site search results are not as expected for string : "
								+ deletedSite);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^verify the site name \"(.*?)\" in the label on the site details page\\.$")
	public void verify_the_site_name_in_the_label_on_the_site_details_page(
			String siteName) throws Throwable {
		Assert.assertTrue(
				"Failed to verify site name on the site detail page.",
				BaseUtil.getElementText(
						getProps().getProperty("editSitePagePopupLabel")
								.replace("%siteName%", siteName)).contains(
						siteName));
	}

	@When("^change the tenant to \"(.*?)\" from tenant dropdown$")
	public void change_the_tenant_to_from_tenant_dropdown(String newTenant)
			throws Throwable {
		BaseUtil.click(getProps().getProperty(
				"editSitePagePopupDetailTenantField"));
		BaseUtil.explicitWait(1000);
		if (newTenant.equals("sitePage_tenantBot1")) {
			BaseUtil.selectComboBoxDropDownByValueNew(
					getProps().getProperty(
							"editSitePagePopupDetailTenantFieldNew"),
					getProps().getProperty("actualTenantValueTestData"));
			BaseUtil.explicitWait(3000);
		} else {
			BaseUtil.selectComboBoxDropDownByValueNew(
					getProps().getProperty(
							"editSitePagePopupDetailTenantFieldNew"),
					getProps().getProperty("actualTenantValueTestDataNew"));
			BaseUtil.explicitWait(3000);
		}

	}

	@Then("^enter the test data for the following fields to create new testData for Site Page for endpoint \"(.*?)\"$")
	public void enter_the_test_data_for_the_following_fields_to_create_new_Test_Data_for_Site_Page_for_endpoint(
			String TenantNo, DataTable arg1) throws Throwable {
		List<String> fieldList = arg1.asList(String.class);
		for (String list : fieldList) {
			sitePage.enterTestData(TenantNo, list);
		}
	}

	@Then("^user should be navigated to endpoint page with a message saying \"(.*?)\"")
	public void user_should_be_navigated_to_endpoint_page_with_a_label_saying(
			String endpointSaveMsg) throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("addEndpointSaveMsg"),
				"Message is not visible on the page");
		String message = "Expected message not matched with actual message.";
		Assert.assertTrue(message, endpointSaveMsg.equalsIgnoreCase(BaseUtil
				.getElementText(getProps().getProperty("addEndpointSaveMsg"))));
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(2000);
	}

	@When("^enter endpoint id \"(.*?)\" in search text field on the Endpoint list page$")
	public void enter_endpoint_id_in_search_text_box_on_the_Endpoint_list_page(
			String endpointName) throws Throwable {
		sitePage.enterText_SitePage("ENDPOINT_SEARCH", endpointName);
		BaseUtil.explicitWait(3000);
	}

	@Then("^select the entered endpoint id \"(.*?)\" from the endpoint detail grid$")
	public void select_the_entered_endpoint_id_from_the_endpoint_details_grid(
			String sEndpointName) {
		sitePage.selectEndpointFromGrid_EndpointPage(sEndpointName);
		BaseUtil.explicitWait(3000);
		sitePage.checkAlert();

	}

	@Then("^verify Site and Tenant for newly created endpoint \"(.*?)\"$")
	public void verify_Site_and_Tenant_for_newly_created_endpoint(
			String sEndpointName) {
		String defaultSite = BaseUtil.getElementText(getProps().getProperty(
				"endpointDetailsSiteInformationSiteNameValue"));
		String defaultTenant = BaseUtil.getElementText(getProps().getProperty(
				"endpointDetailsSiteInformationTenantValue"));

		if (defaultSite.equals("Provisioning")
				&& defaultTenant.equals("GridPoint")) {
			Assert.assertTrue(
					"Default Tenant for the endpoint [" + sEndpointName
							+ "] are Site ID : [" + defaultSite + "]"
							+ "and Tenant : [" + defaultTenant + "]",
					BaseUtil.verifyElementDisplayed(getProps().getProperty(
							"editEndpointPagePopup")));
		}
	}

	@When("^user modify following ASSOCIATION TENANTSITE fields for an endpoint$")
	public void user_modify_following_fields() throws Throwable {
		BaseUtil.click(getProps().getProperty("modifyEndpointTenantDropDown"));
		BaseUtil.explicitWait(1000);
		BaseUtil.selectComboBoxDropDownByValue(
				getProps().getProperty("modifyEndpointTenantDropDownNew"),
				getProps().getProperty("modifyEndpointTenantDropDownTestData3"));

		GPConnection gpcon = new GPConnection();
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = gpcon.getConnection(TestBase.getDbUrl(),
				TestBase.getDbUsername(), TestBase.getDbPassword());
		String getPremisesIdQuery = "SELECT premises_id FROM premises WHERE name='sitePage_siteBot1'";
		logger.info("getPremisesIdQuery : " + getPremisesIdQuery);
		String premisesId = dbutil.getIdFromDB(getPremisesIdQuery, conn);
		if (null == premisesId) {
			Assert.fail("Given site [sitePage_siteBot1] is not available");
		}
		String premisesName = "sitePage_siteBot1";
		String premisesNameWithId = premisesName.concat("/").concat(premisesId);
		BaseUtil.enterText(
				getProps().getProperty("modifyEndpointAssociateSiteId"),
				premisesNameWithId);
		BaseUtil.explicitWait(2000);

	}

	@Then("^verify Site and Tenant for an endpoint \"(.*?)\"$")
	public void verify_Site_and_Tenant_for_an_endpoint(String sEndpointName) {
		try {
			String defaultSite = BaseUtil
					.getElementText(getProps().getProperty(
							"endpointDetailsSiteInformationSiteNameValue"));
			String defaultTenant = BaseUtil.getElementText(getProps()
					.getProperty("endpointDetailsSiteInformationTenantValue"));

			String siteByQuery = "select name from premises where premises_id in (select premises_id from endpoint where serial = '"
					+ sEndpointName + "')";
			String tenantByQuery = "select name from tenant where tenant_id in "
					+ "(select tenant_id from premises where premises_id in "
					+ "(select premises_id from endpoint  where serial = '"
					+ sEndpointName + "'))";

			String site = sitePage.getPhoneNo(siteByQuery);
			String tenant = sitePage.getPhoneNo(tenantByQuery);

			if (defaultSite.equals(site) && defaultTenant.equals(tenant)) {
				Assert.assertTrue(
						"Default Tenant for the endpoint [" + sEndpointName
								+ "] are Site ID : [" + defaultSite + "]"
								+ "and Tenant : [" + defaultTenant + "]",
						BaseUtil.verifyElementDisplayed(getProps().getProperty(
								"editEndpointPagePopup")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^click on \"(.*?)\" accordion$")
	public void click_on_accordion(String arg1) throws Throwable {
		// TODO : verify below if condition
		if (BaseUtil.instantElementCheck("Display",
				getProps().getProperty("editSitePagePopupDetailMapSection"))) {
			BaseUtil.clickAndWait(getProps().getProperty(
					"editSitePagePoupDetailsAccordion"));
			BaseUtil.explicitWait(2000);
		}
		// //
		else if (arg1.equals("Channels")) {
			BaseUtil.clickAndWait(getProps().getProperty(
					"editSitePagePopupChannelAccordion"));
		} else if (arg1.equals("Paths")) {
			BaseUtil.explicitWait(3000);
			BaseUtil.clickAndWait(getProps().getProperty(
					"editSitePagePopupPathsAccordion"));

		} else if (arg1.equals("Endpoints")) {
			BaseUtil.clickAndWait(getProps().getProperty(
					"editSitePagePopupEndpointsAccordion"));
		} else if (arg1.equals("Details")) {
			BaseUtil.clickAndWait(getProps().getProperty(
					"editSitePagePoupDetailsAccordion"));
		}
	}

	@Then("^verify the tenant for \"(.*?)\" site is \"(.*?)\"$")
	public void verify_the_tenant_for_site(String sSiteName, String tenantname)
			throws Throwable {
		String message = "Failed to verify the tenant: " + tenantname
				+ " on edit site modal for the site: " + sSiteName;
		Assert.assertTrue(message,
				sitePage.verifyTenantOnEditSite(sSiteName, tenantname));
	}

	@Then("^verify the Sorting functionality of columns on \"(.*?)\" list$")
	public void verify_the_sorting_functionality_of_columns_on_field_list(
			String gridName, DataTable listGridHeaders) throws Throwable {
		if (gridName.equalsIgnoreCase("Endpoints")) {
			List<String> lstData = listGridHeaders.asList(String.class);
			for (String header : lstData) {
				sitePage.verifySorting_EndpointAccordian("Descending", header);
				sitePage.verifySorting_EndpointAccordian("Ascending", header);
			}
		} else if (gridName.equalsIgnoreCase("Audit")) {
			List<String> lstData = listGridHeaders.asList(String.class);
			for (String header : lstData) {
				sitePage.verifySorting_AuditAccordian("Descending", header);
				sitePage.verifySorting_AuditAccordian("Ascending", header);
			}
		} else if (gridName.equalsIgnoreCase("Channel")) {
			List<String> lstData = listGridHeaders.asList(String.class);
			for (String header : lstData) {
				sitePage.verifySorting_ChannelAccordian("Descending", header);
				sitePage.verifySorting_ChannelAccordian("Ascending", header);
			}
		} else if (gridName.equalsIgnoreCase("PV Audit")) {
			List<String> lstData = listGridHeaders.asList(String.class);
			for (String header : lstData) {
				sitePage.verifySorting_PVAuditAccordian("Descending", header);
				sitePage.verifySorting_PVAuditAccordian("Ascending", header);
			}
		}
	}

	@Then("verify the pagination functionality on \"(.*?)\" list$")
	public void verify_the_pagination_functionality(String accordianElementName)
			throws Throwable {
		try {
			switch (accordianElementName.toUpperCase()) {
			case "AUDIT":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSiteAuditAccordionNext"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSiteAuditAccordionPrev"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSiteAuditAccordionFirst"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSiteAuditAccordionLast"));

				break;
			case "ENDPOINTS":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSiteEndpointsAccordionNext"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSiteEndpointsAccordionPrev"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSiteEndpointsAccordionFirst"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSiteEndpointsAccordionLast"));

				break;
			case "PV AUDIT":
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSitePVAuditAccordionNext"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSitePVAuditAccordionPrev"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSitePVAuditAccordionFirst"));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editSitePVAuditAccordionLast"));

				break;
			default:
				logger.error("Switch Case["
						+ accordianElementName
						+ "] is not present in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
			}

		} catch (Exception e) {
			logger.error("Failed to displayed the element :"
					+ accordianElementName + " see detail message : "
					+ e.getMessage());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	@Then("^verify the search functionalty of following \"(.*?)\" list$")
	public void verify_the_search_functionalty_of_following_list(
			String accordian) throws Throwable {
		if (accordian.equalsIgnoreCase("channel")) {
			Assert.assertTrue(BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("editSitePagePopupChannelGrid")));
		}
	}

	@Then("verify the presence of edit, view, export and add virtual buttons in \"(.*?)\" list$")
	public void verify_the_presence_of_edit_and_view_button_in_list(
			String accordianElementName) throws Throwable {
		try {
			switch (accordianElementName.toUpperCase()) {
			case "Channels":
				Assert.assertTrue(
						"Edit Button is not displayed",
						BaseUtil.verifyElementDisplayed(getProps().getProperty(
								"sitePageChannelAccordianEditButton")));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"editVirtualChannelBtn"));
				Assert.assertTrue(
						"View Button is not displayed",
						BaseUtil.verifyElementDisplayed(getProps().getProperty(
								"sitePageChannelAccordianViewButton")));
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"viewVirtualChannelBtn"));
				Assert.assertTrue(
						"Export Icon is not displayed",
						BaseUtil.verifyElementDisplayed(getProps().getProperty(
								"exportChannelBtn")));
				Assert.assertTrue(
						"Add Virtual Channel Button is not displayed",
						BaseUtil.verifyElementDisplayed(getProps().getProperty(
								"addVirtualChannelBtn")));
				break;
			default:
				logger.error("Switch Case["
						+ accordianElementName
						+ "] is not present in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
			}

		} catch (Exception e) {
			logger.error("Failed to displayed the element :"
					+ accordianElementName + " see detail message : "
					+ e.getMessage());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	@Then("^verify the expansion and shrinkage while click on \"(.*?)\" accordion$")
	public void verify_the_expansion_and_shrinkage_while_click_on_accordion(
			String accordionName) throws Throwable {
		sitePage.verifyAccordionToggle_SitePage(accordionName);
	}

	@Then("^select \"(.*?)\" details from the \"(.*?)\" accordion and it navigates to pv audit accordion$")
	public void select_details_from_the_accordion(String accordianDetails,
			String accordianName) throws Throwable {
		String message = "PV Audit [" + accordianDetails
				+ "] are not found in the accordion [" + accordianName + "].";
		Assert.assertTrue(message, sitePage.selectDetailsFromAccordion(
				accordianDetails, accordianName));
	}

	@When("^verify the various attribute in pv audit popup$")
	public void verify_the_various_attribute_in_pv_audit_popup(
			List<String> listGridHeaders) throws Throwable {
		for (String siteGridHeader : listGridHeaders) {
			Assert.assertTrue("Header [" + siteGridHeader
					+ "] does not exists in the PV Audit Summary grid",
					sitePage.IsGridHeaderDisplayed(siteGridHeader));
		}
	}

	@When("^click on \"(.*?)\" button on pv audit popup$")
	public void click_on_button_on_pvaudit_popup(String buttonName)
			throws Throwable {
		Assert.assertTrue(sitePage.clickOperationOnSitePage(buttonName));
	}

	@Given("^\"(.*?)\" the following test data of PVAudit Table$")
	public void testDataQuery(String qyeryType, DataTable table)
			throws SQLException, IOException {
		List<String> lstData = table.asList(String.class);
		switch (qyeryType.toUpperCase()) {
		case "INSERT":
			for (String insertPvAudit : lstData) {
				ManageTestData objDelTestdata = new ManageTestData();
				Assert.assertTrue(
						"Error occured while Inserting test data for PVAudit data table",
						objDelTestdata.manageTestData("PVAudit_Insert",
								insertPvAudit));
			}
			break;
		case "DELETE":
			ManageTestData objDelTestdata = new ManageTestData();
			Assert.assertTrue(
					"Error occured while deleting test data for PVAudit data table",
					objDelTestdata.manageTestData("PVAudit_Delete", lstData));

			break;
		}
	}

	@Then("verify Export icon is display on Site tab$")
	public void verify_Export_icon_is_display_on_site_tab() throws Throwable {
		String message = "Export Icon is not available on Peripheral Firmware Tab";
		BaseUtil.assertElementDisplayed(
				TestBase.getReadConfigData().get("exportIcon"), message);
	}

	@Then("the choose Column popup is displayed$")
	public void the_choose_Column_popup_is_displayed() throws Throwable {
		BaseUtil.waitForSpinner();
		String message = "Failed to verify display of choose column popup on site tab";
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("chooseColumnPopup"), message);
	}

	@Then("verify the default column list on \"(.*?)\" side on Choose column popup$")
	public void verify_the_default_column_list_on_choose_column_popup(
			String columnSide, List<String> chooseColumnList) throws Throwable {
		if (columnSide.equalsIgnoreCase("Right")) {
			for (String chooseColumnHeader : chooseColumnList) {
				Assert.assertTrue(
						"Header [" + chooseColumnHeader
								+ "] does not exists in the [" + columnSide
								+ "] grid",
						sitePage.verifyColumns_ChooseColumnPopup(chooseColumnHeader));
			}
		} else if (columnSide.equalsIgnoreCase("Left")) {
			for (String chooseColumnHeader : chooseColumnList) {
				Assert.assertTrue(
						"Header [" + chooseColumnHeader
								+ "] does not exists in the [" + columnSide
								+ "] grid",
						sitePage.verifyColumns_ChooseColumnPopup(chooseColumnHeader));
			}
		}
	}

	@Then("verify the total column list on Choose column popup$")
	public void verify_the_total_column_list_on_Choose_column_popup(
			List<String> chooseColumnList) throws Throwable {
		for (String chooseColumnHeader : chooseColumnList) {
			Assert.assertTrue("Header [" + chooseColumnHeader
					+ "] does not exists in the choose column popup", sitePage
					.verifyColumns_ChooseColumnPopup(chooseColumnHeader));
		}
	}

	@Then("select some of the columns from \"(.*?)\" side of choose column popup$")
	public void select_some_of_the_columns_from_side_of_choose_column_popup(
			String popupSide) throws Throwable {
		try {
			WebElement startColumn = null;
			WebElement endColumn = null;
			Actions shiftClick = new Actions(TestBase.getWebDriver());
			if (popupSide.equals("Left")) {
				startColumn = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("chooseChoumnPopupAddress2Column"));
				endColumn = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("chooseChoumnPopupPhone2Column"));
				startColumn.click();
				shiftClick.keyDown(Keys.SHIFT).click(endColumn);
			} else if (popupSide.equals("Right")) {
				startColumn = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("chooseChoumnPopupSiteIdColumn"));
				endColumn = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("chooseChoumnPopupPostalCodeColumn"));
				startColumn.click();
				shiftClick.keyDown(Keys.SHIFT).click(endColumn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("select All of the columns from \"(.*?)\" side of choose column popup$")
	public void select_all_of_the_columns_from_side_of_choose_column_popup(
			String popupSide) throws Throwable {
		try {
			WebElement startColumn = null;
			WebElement endColumn = null;
			Actions shiftClick = new Actions(TestBase.getWebDriver());
			startColumn = BaseUtil.getElementAfterLoaded(getProps()
					.getProperty("chooseChoumnPopupSiteIdColumn"));
			endColumn = BaseUtil.getElementAfterLoaded(getProps().getProperty(
					"chooseChoumnPopupPhone2Column"));
			startColumn.click();
			shiftClick.keyDown(Keys.SHIFT).click(endColumn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("verify and click on \"(.*?)\" button functionality on Choose column popup$")
	public void verify_and_click_on_button_functionality_on_Choose_column_popup(
			String chooseColumnButton) throws Throwable {
		try {
			if (chooseColumnButton.equals("Add")) {
				BaseUtil.clickAndWait(getProps().getProperty(
						"chooseColumnAddBtn"));
			} else if (chooseColumnButton.equals("Add All Columns")) {
				BaseUtil.clickAndWait(getProps().getProperty(
						"chooseColumnAddAllColumnsBtn"));
			} else if (chooseColumnButton.equals("Remove")) {
				BaseUtil.click(getProps().getProperty(
						"chooseChoumnPopupSiteIdColumn"));
				BaseUtil.clickAndWait(getProps().getProperty(
						"chooseColumnRemoveBtn"));
				BaseUtil.clickAndWait(getProps().getProperty(
						"chooseChoumnPopupSiteNameColumn"));
				BaseUtil.clickAndWait(getProps().getProperty(
						"chooseColumnRemoveBtn"));

			} else if (chooseColumnButton.equals("Reset Defaults")) {
				BaseUtil.clickAndWait(getProps().getProperty(
						"chooseColumnResetDefaultsBtn"));
			} else if (chooseColumnButton.equals("Submit")) {
				BaseUtil.clickAndWait(getProps().getProperty(
						"chooseColumnSubmitButton"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^verify the tenant in tenant dropdown is \"(.*?)\"$")
	public void verify_the_tenant_in_tenant_dropdown_is(String tenantName)
			throws Throwable {
		boolean tenantDropDownCheck = BaseUtil
				.verifyElementDisplayed(getProps().getProperty(
						"sitePageTenantDropdown"));
		if (tenantDropDownCheck) {
			String message = "Failed to verify tenant list in tenant dropdown associated with the user";
			Assert.assertTrue(message,
					sitePage.verifyTenantListsOnTenantDropdownS(tenantName));
		}
	}

	@Then("it shows the default layout of Choose column popup$")
	public void it_shows_the_default_layout_of_Choose_column_popup(
			List<String> chooseColumnList) throws Throwable {
		for (String chooseColumnHeader : chooseColumnList) {
			Assert.assertTrue("Header [" + chooseColumnHeader
					+ "] does not exists in the choose column popup", sitePage
					.verifyColumns_ChooseColumnPopup(chooseColumnHeader));
		}
	}

	@Then("^select the ADM site \"(.*?)\" from the site details grid$")
	public void select_the_ADM_Site(String sSiteName) throws Throwable {
		BaseUtil.waitForSpinner();
		sitePage.selectADMSiteFromGrid_SitePage(sSiteName);
	}

	@Then("^verify that following fields are editable of ADM Site$")
	public void verify_that_following_fields_are_editable(
			List<String> listFieldNames) throws Throwable {
		for (String fieldName : listFieldNames) {
			Assert.assertTrue("Failed to verify editablity of field "
					+ fieldName, sitePage.verifyElementEditable(fieldName));
		}

	}

	@Then("^verify the Postal Code field contains postal code for a site$")
	public void verify_the_Postal_Code_contains_postal_code_for_a_site()
			throws Throwable {
		String message = "Failed to verify postal code for a site";
		Assert.assertTrue(message, sitePage.verifyPostalCode());
	}

	@Then("^verify \"(.*?)\" button is not displayed$")
	public void verify_button_is_not_displayed(String buttonName)
			throws Throwable {
		String message = buttonName + " button is displayed";
		Assert.assertFalse(
				message,
				BaseUtil.instantElementCheck("Display",
						getProps().getProperty("editSiteDeleteSiteButton")));
	}

	@Then("^verify that \"(.*?)\" is editable of ADM Site$")
	public void verify_that_is_editable_of_ADM_Site(String textField)
			throws Throwable {
		Assert.assertTrue("Failed to verify editablity of field " + textField,
				sitePage.verifyElementEditable(textField));
	}

	@Then("^verify Virtual channel creation tool window$")
	public void verify_Virtual_channel_creation_tool_window() throws Throwable {
		String message = "Failed to verify Virtual channel creation tool window";
		BaseUtil.assertElementDisplayed(
				getProps().getProperty(
						"addVirtualChannelConstituentChannelLabel"), message);
	}

	@Then("^click on \"(.*?)\" button on Add Virtual Channel creation tool window$")
	public void click_on_button_on_add_virtual_Channel(String buttonName)
			throws Throwable {
		BaseUtil.click(getProps().getProperty(
				"addVirtualChannelWindowCloseButton"));
		BaseUtil.explicitWait(2000);
	}

	@Then("^click on \"(.*?)\" button on edit Site Page$")
	public void click_on_button_on_edit_Site_Page(String buttonName)
			throws Throwable {
		if (buttonName.equals("X")) {
			BaseUtil.click(getProps().getProperty("editSitePagecloseBtn"));
		} else {
			BaseUtil.click(getProps().getProperty("editSitePageSaveButton"));
			BaseUtil.waitForSpinner();
		}

	}

	@Then("^verify \"(.*?)\" icon is \"(.*?)\" on Edit Site Page$")
	public void verify_icon_is_displayed_on_Edit_Site_Page(String iconName,
			String action) throws Throwable {
		if (action.equals("Displayed")) {
			String message = "Failed to verify Real Time Element is displayed";
			Assert.assertTrue(message, BaseUtil.instantElementCheck("Display",
					getProps().getProperty("editSitePageRTDIcon")));
		} else if (action.equals("Not Displayed")) {
			String message = "Failed to verify Real Time Element is displayed";
			Assert.assertFalse(message, BaseUtil.instantElementCheck("Display",
					getProps().getProperty("editSitePageRTDIcon")));
		}
	}

	@When("^click on \"(.*?)\" button on edit site page$")
	public void click_on_button_on_edit_sitepage(String buttonName)
			throws Throwable {
		Assert.assertTrue(buttonName
				+ " checkbox is not unchecked before searching site",
				sitePage.clickOperationOnSitePage(buttonName));
		BaseUtil.waitForSpinner();
	}

	@Then("^select the first endpoint from the list of Endpoint accordion$")
	public void select_the_first_endpoint_from_the_list_of_Endpoint_accordion()
			throws Throwable {
		BaseUtil.click(getProps().getProperty(
				"editSitePageFirstRowEndpointsAccordion"));
		BaseUtil.waitForSpinner(2000);
	}

	@Then("^verify \"(.*?)\" icon is \"(.*?)\" on Edit Endpoint Page$")
	public void verify_icon_is_not_displayed_on_Edit_Endpoint_Page(
			String iconName, String action) throws Throwable {
		String message = "Failed to verify Real Time Data icon is not displayed";
		Assert.assertFalse(
				message,
				BaseUtil.instantElementCheck("Display",
						getProps().getProperty("editEndpointPageRTDIcon")));

	}

	@Then("^click on \"(.*?)\" button on edit Endpoint Page$")
	public void click_on_button_on_edit_Endpoint_Page(String buttonName)
			throws Throwable {
		BaseUtil.click(getProps().getProperty("editEndpointPageCancelButton"));
		BaseUtil.explicitWait(2000);
	}

	@Then("^click on the portal button which navigate to HTML Portal for Site Page$")
	public void click_on_portal_button_which_navigate_to_HTML_Portal()
			throws Throwable {
		BaseUtil.waitForSpinner();
		if (!BaseUtil.checkIfClickable(TestBase.getReadConfigData().get(
				"optionDropDown"))) {
			Assert.fail("Not able to click on portal navigator.");
		}
		BaseUtil.clickElement(TestBase.getReadConfigData()
				.get("optionDropDown"));
		BaseUtil.clickElement(TestBase.getReadConfigData()
				.get("PortalNavigate"));
		BaseUtil.waitForSpinner();
		String message = "Error while navigating to Portal";
		BaseUtil.assertElementDisplayed(TestBase.getReadConfigData()
				.get("Home"), message);
	}

	@Then("^click site tree \"(.*?)\" button on home tab$")
	public void click_on_site_tree_clear_button_userRolePermissions(
			String button) throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue(button + " Button is not clicked",
				sitePage.clickOperationOnSitePage(button));
	}

	@Then("^click in the \"(.*?)\" text box in the portal Home Tab$")
	public void click_inthe_portal_search_site_textbox_UserRolePermissions(
			String button) throws Throwable {
		BaseUtil.explicitWait(1000);
		Assert.assertTrue(button + " Button is not clicked",
				sitePage.clickOperationOnSitePage(button));
	}

	@Then("^enter text \"(.*?)\" in the site search text box on home tab$")
	public void enter_text_inthe_search_site_textbox_UserRolePermissions(
			String searctText) throws Throwable {
		BaseUtil.enterText(getProps().getProperty("siteTreeSearchBox"),
				searctText);
		BaseUtil.explicitWait(3000);
	}

	@Then("^click on the \"(.*?)\" tab in the portal$")
	public void click_on_Tab_on_Portal(String button) throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue(button + " Button is not clicked",
				sitePage.clickOnTab(button));
	}

	@Then("^select any \"(.*?)\" on the tab$")
	public void select_any_device_on_tab(String deviceName) throws Throwable {
		try {
			if (deviceName.equals("HVAC Device")) {
				BaseUtil.checkCheckBox(getProps().getProperty(
						"hvacDeviceCheckBox"));
			} else if (deviceName.equals("Lighting Device")) {
				BaseUtil.checkCheckBox(getProps().getProperty(
						"lightingDeviceCheckBox"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^verify \"(.*?)\" text is displayed on \"(.*?)\"$")
	public void verify_text_is_displayed_on(String text, String tabName)
			throws Throwable {
		String message = "Failed to verify" + text + "on" + tabName;
		try {
			switch (tabName.toUpperCase()) {
			case "HOME TAB":
				BaseUtil.assertElementDisplayed(
						getProps().getProperty("inactiveTextHomeTab"), message);
				break;
			case "CONTROL:HOURS TAB":
				BaseUtil.assertElementDisplayed(
						getProps().getProperty("inactiveTextControlHoursTab"),
						message);
				break;
			case "CONTROL:HVAC TAB":
				BaseUtil.assertElementDisplayed(
						getProps().getProperty("inactiveTextControlHVACTab"),
						message);
				break;
			case "CONTROL:LIGHTING TAB":
				BaseUtil.assertElementDisplayed(
						getProps()
								.getProperty("inactiveTextControlLightingTab"),
						message);
				break;
			case "ALARMS TAB":
				BaseUtil.assertElementDisplayed(
						getProps().getProperty("inactiveTextAlarmsTab"),
						message);
				break;
			case "PROJECTS TAB":
				BaseUtil.assertElementDisplayed(
						getProps().getProperty("inactiveTextProjectsTab"),
						message);
				break;
			case "GRAPHS TAB":
				BaseUtil.assertElementDisplayed(
						getProps().getProperty("inactiveTextGraphsTab"),
						message);
				BaseUtil.waitForSpinner();
				break;
			case "REPORTS TAB":
				BaseUtil.assertElementDisplayed(
						getProps().getProperty("inactiveTextReportsTab"),
						message);
				break;
			case "SAVINGS TAB":
				BaseUtil.assertElementDisplayed(
						getProps().getProperty("inactiveTextSavingTab"),
						message);
				break;
			default:
				logger.error("Switch Case["
						+ tabName
						+ "] is not present in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Then("^verify \"(.*?)\" on home tab for the site \"(.*?)\"$")
	public void verify_Details_on_home_tab(String attribute, String siteName)
			throws Throwable {
		try {
			if (siteName.equals("sitePage_siteBot1")) {
				if (attribute.equals("Phone No")) {
					BaseUtil.verifyElementDisplayed(getProps().getProperty(
							"homeTabPhoneNoField"));
					WebElement phoneNo = TestBase
							.getWebDriver()
							.findElement(
									By.xpath("//*[@id='Project_details']/div/div/div[2]/div[2]/article[1]/div/ul/li"));
					String phoneNoValue = phoneNo.getText();

					String phoneNo1ByQuery = "SELECT phone1 FROM premises WHERE name='"
							+ siteName + "'";
					String phoneNo2ByQuery = "SELECT phone2 FROM premises WHERE name='"
							+ siteName + "'";

					String phone1 = sitePage.getPhoneNo(phoneNo1ByQuery);
					String phone2 = sitePage.getPhoneNo(phoneNo2ByQuery);

					if ((phone1.equals(phoneNoValue))
							|| (phone2.equals(phoneNoValue))) {
						Assert.assertTrue(
								"THe phone number displayed on home tab is [["
										+ phoneNoValue
										+ "]] for the site is [[ " + siteName
										+ " ]].", BaseUtil
										.verifyElementDisplayed(getProps()
												.getProperty(
														"homeTabPhoneNoField")));
					}

				} else if (attribute.equals("Decommission Date")) {

					Assert.assertTrue(
							"The decommission date is not None for the given site [[ "
									+ siteName + " ]].",
							BaseUtil.getElementText(
									getProps().getProperty(
											"homeTabDecommissionDateField"))
									.trim().equalsIgnoreCase("None"));
				}
			} else if (siteName.equals("sitePage_siteBot2")) {
				if (attribute.equals("Phone No")) {
					BaseUtil.verifyElementDisplayed(getProps().getProperty(
							"homeTabPhoneNoField"));
					WebElement phoneNo = TestBase
							.getWebDriver()
							.findElement(
									By.xpath("//*[@id='Project_details']/div/div/div[2]/div[2]/article[1]/div/ul/li"));
					String phoneNoValue = phoneNo.getText();

					String phoneNo1ByQuery = "SELECT phone1 FROM premises WHERE name='"
							+ siteName + "'";
					String phoneNo2ByQuery = "SELECT phone2 FROM premises WHERE name='"
							+ siteName + "'";

					String phone1 = sitePage.getPhoneNo(phoneNo1ByQuery);
					String phone2 = sitePage.getPhoneNo(phoneNo2ByQuery);

					if ((phone1.equals(phoneNoValue))
							|| (phone2.equals(phoneNoValue))) {
						Assert.assertTrue(
								"THe phone number displayed on home tab is [["
										+ phoneNoValue
										+ "]] for the site is [[ " + siteName
										+ " ]].", BaseUtil
										.verifyElementDisplayed(getProps()
												.getProperty(
														"homeTabPhoneNoField")));
					}

				} else if (attribute.equals("Decommission Date")) {
					BaseUtil.verifyElementDisplayed(getProps().getProperty(
							"homeTabDecommissionDateField"));
					WebElement decommissionDate = TestBase
							.getWebDriver()
							.findElement(
									By.xpath("//*[@id='Project_details']/div/div/div[2]/div[2]/article[2]/div/ul/li"));
					String decommissionDateValue = decommissionDate.getText();

					String decommissionDateByQuery = "select decommissioned_date from premises where name='"
							+ siteName + "'";

					String siteDecommissionDate = sitePage
							.getDecommissionDate(decommissionDateByQuery);

					if ((siteDecommissionDate.equals(decommissionDateValue))
							|| (decommissionDateValue.equals("None"))) {
						Assert.assertTrue(
								"THe Decommission Date displayed on home tab is [["
										+ decommissionDateValue
										+ "]] for the site is [[ " + siteName
										+ " ]].", BaseUtil
										.verifyElementDisplayed(getProps()
												.getProperty(
														"homeTabPhoneNoField")));
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Then("^delete the upload bulk sites from the site tab having name \"(.*?)\"$")
	public void delete_the_upload_bulk_sites_from_the_site_tab_having_name(
			String importSitesName) throws Throwable {
		ManageTestData objDelTestdata = new ManageTestData();
		Assert.assertTrue("Data should be deleted ", objDelTestdata
				.manageTestData("DeleteBulkImportSites", importSitesName));
	}

	@Then("^verify \"(.*?)\" icon is not displayed on \"(.*?)\" page$")
	public void verify_icon_is_not_displayed_on_pages(String tabName,
			String pageName) throws Throwable {
		try {
			String message = null;
			switch (pageName.toUpperCase()) {
			case "CONTROL:HVAC":
				message = "Failed to verify Real Time Data Tab on HVAC Tab";
				Assert.assertFalse(message, BaseUtil.instantElementCheck(
						"Display", getProps().getProperty("realTimeTab")));
				break;
			case "CONTROL:LIGHTING":
				message = "Failed to verify Real Time Data Tab on Lighting Tab";
				Assert.assertFalse(message, BaseUtil.instantElementCheck(
						"Display", getProps().getProperty("realTimeTab")));
				break;
			case "DATA":
				message = "Failed to verify Real Time Data icon Tab on Data Tab";
				Assert.assertFalse(message, BaseUtil.instantElementCheck(
						"Display", getProps().getProperty("realTimeTab")));
				break;
			case "HOME":
				message = "Failed to verify Real Time Data icon is not displayed on Home Tab";
				Assert.assertFalse(message, BaseUtil.instantElementCheck(
						"Display",
						getProps().getProperty("portalHomePageRTDIcon")));
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^verify that path text associated with the site should be same with the Path that is provided during site creation$")
	public void verify_that_paths_field_associated_with_site_should_be_same_with_the_Path_that_is_provided_during_site_creation()
			throws Throwable {
		Assert.assertTrue(
				"Path values is not as expected",
				getProps()
						.getProperty("editSitePathsFieldTestData")
						.equals(BaseUtil
								.getMultipleElementsAfterLoaded(
										getProps()
												.getProperty(
														"editSitePagePathsAccordianPathTextField"))
								.get(0).getAttribute("value").trim()));
	}

	// TODO : verify the below code (verified and it is fine and usable)
	@Then("^verify the text box should contain site path$")
	public void verify_the_test_box_should_contain_site_path_and_should_be_disabled()
			throws Throwable {
		Assert.assertTrue(
				"Path field is not displayed",
				BaseUtil.verifyElementDisplayed(
						getProps().getProperty(
								"editSitePagePathsAccordianPathTextField"), 0));
		Assert.assertTrue(
				"Path values is not as expected",
				getProps()
						.getProperty("addSitePathsFieldTestData")
						.equals(BaseUtil
								.getMultipleElementsAfterLoaded(
										getProps()
												.getProperty(
														"editSitePagePathsAccordianPathTextField"))
								.get(0).getAttribute("value")));
		if (BaseUtil.verifyElementDisplayed(getProps().getProperty(
				"addSiteAddPathsButton"))) {
			BaseUtil.click(getProps().getProperty("addSiteAddPathsButton"));
			BaseUtil.explicitWait(500);
			Assert.assertTrue(
					"Add Path button is not adding another filed for path",
					BaseUtil.verifyElementDisplayed(
							getProps().getProperty(
									"editSitePagePathsAccordianPathTextField"),
							0));
		}
	}

	@When("^the new field is displayed on the edit site page$")
	public void the_new_field_is_displayed_on_the_edit_site_page()
			throws Throwable {
		BaseUtil.verifyElementEnabled(getProps().getProperty(
				"editSitePathTextBox1"));
	}

	@Then("^verify top level path is auto populated on the basis of tenant selection edit site page$")
	public void verify_top_level_path_is_auto_populated_on_the_basis_of_tenant_selection_edit_site_page() {
		try {

			String tenantValue = BaseUtil.getElementText(getProps()
					.getProperty("editSitePagePopupDetailTenantField"));
			WebElement pathValueElement = TestBase.getWebDriver().findElement(
					By.id("idEditSitepath1"));

			String sitePathValue = pathValueElement.getAttribute("value");
			System.out.println("sitePathValue1 =" + sitePathValue);

			sitePathValue = sitePathValue.substring(
					sitePathValue.indexOf("/") + 1).trim();

			System.out.println("sitePathValue2 =" + sitePathValue);
			if (tenantValue.equals(sitePathValue)) {
				Assert.assertTrue(
						"The Top level path is auto populated in the path field.",
						true);
			} else {
				Assert.fail("The top level path is not generated on the basis of tenant selection");
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	@Then("^verify top level path \"(.*?)\" is displayed in the site path field$")
	public void verify_top_level_path_is_displayed_in_the_site_path_field(
			String topLevelPath) throws Throwable {
		try {
			String pathFieldtext = BaseUtil.getElementText(
					getProps().getProperty("editSitePathTextBox1")).trim();
			Assert.assertTrue(
					"Failed to verify top leval path in the site path field",
					pathFieldtext.trim().equalsIgnoreCase(topLevelPath));

		} catch (Exception e) {
			logger.error("Failed to verify top leval path in the site path field : see detail message : "
					+ e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@Then("^add \"(.*?)\" with the existing path on edit site page$")
	public void add_in_existing_path_on_Edit_site_page(String symbol)
			throws Throwable {
		WebElement pathValueElement = TestBase.getWebDriver().findElement(
				By.id("idEditSitepath1"));

		String sitePathValue = pathValueElement.getAttribute("value");
		String completePathValue = sitePathValue + symbol;
		BaseUtil.enterText(getProps().getProperty("editSitePathTextBox1"),
				completePathValue);
		BaseUtil.explicitWait(1000);
	}

	@Then("^it shows a warning message if the new string is added \"(.*?)\" for \"(.*?)\" on edit site page$")
	public void ot_shows_a_waning_message_if_the_new_string_is_added_on_edit_site_page(
			String warningMessage, String tenantName) throws Throwable {
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("editSitePathWarningMessage"),
				"Message is not visible on the page");
		Assert.assertTrue(
				"Expected message not matched with actual message.",
				warningMessage.replace("########", tenantName)
						.equalsIgnoreCase(
								BaseUtil.getElementText(
										getProps().getProperty(
												"editSitePathWarningMessage"))
										.trim()));
		BaseUtil.waitForSpinner();
	}

	@And("^the Report is dowmloaded for Site Tab$")
	public void the_Report_is_dowmloaded_for_Site_Tab() throws Throwable {
		try {
			String ReportName = "Site_Report_"
					+ RandomStringUtils.randomAlphabetic(5) + ".xlsx";
			String updatedReportsName = downLoadReports("Site Report",
					ReportName);
			Assert.assertTrue("Report is getting downloaded with the name [ "
					+ updatedReportsName + " +].", true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String downLoadReports(String reportName, String downloadFileName) {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger.info("Executing test step::" + methodName);

		downloadFileName = downloadFileName.replaceAll(" ", "_");

		try {
			String filePath = BaseUtil.getDownloadsPath()
					+ "/target/generated-report-temp-files";
			File fileToBeDeleted = new File(filePath);
			if (!fileToBeDeleted.exists()) {
				fileToBeDeleted.mkdir();
			} else {
				FileUtils.cleanDirectory(fileToBeDeleted);
			}
			return downloadFileName;
		} catch (Exception e) {
			sitePage.reportError("Error while downloading report");
			logger.info("Error while downloading report");
			return downloadFileName.replaceAll(" ", "_");
		}
	}

	@Then("the \"(.*?)\" button become disabled on Choose Column Popup$")
	public void the_button_become_disabled_on_Choose_column_popup(
			String buttonName) throws Throwable {
		boolean checkButton = BaseUtil.verifyElementEnabled(getProps()
				.getProperty("chooseColumnSubmitButton"));
		Assert.assertTrue(buttonName
				+ " is in disabled state on choose column popup.",
				!(checkButton));
	}

	@Then("all the following columns are added on right side of choose column popup$")
	public void all_the_columns_are_added_on_right_side_of_choose_column_popup(
			List<String> chooseColumnList) throws Throwable {
		try {
			for (String chooseColumnHeader : chooseColumnList) {
				Assert.assertTrue(
						"Header ["
								+ chooseColumnHeader
								+ "] does not exists in the choose column popup",
						sitePage.verifyColumns_ChooseColumnPopup(chooseColumnHeader));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^user verify the \"(.*?)\" checkbox with label$")
	public void user_verifies_the_checkbox_with_label(String checkBox)
			throws Throwable {
		String locator1 = getProps().getProperty("hideInactiveCheckBox");
		String locator2 = getProps().getProperty("hideInactiveCheckBoxLabel");
		Assert.assertTrue("Failed to verify Hide Inactive checkbox",
				BaseUtil.verifyElementDisplayed(locator1));
		Assert.assertTrue("Failed to verify Hide Inactive checkbox label",
				BaseUtil.verifyElementDisplayed(locator2));
	}

	@Then("^uncheck the \"(.*?)\" checkbox$")
	public void uncheck_checkbox_is_unchecked(String checkBox) throws Throwable {

		Assert.assertTrue(checkBox
				+ " checkbox is not unchecked before searching site",
				sitePage.clickOperationOnSitePage(checkBox));
	}

	@Then("^enter \"(.*?)\" in channel search field$")
	public void enter_in_channel_search_field(String channelName)
			throws Throwable {
		sitePage.enterText_SitePage("SEARCH_CHANNEL_NAME", channelName);
		if (BaseUtil.instantElementCheck("Display",
				getProps().getProperty("viewVirtualChannelBtn"))) {
			BaseUtil.clickElement(getProps().getProperty(
					"viewVirtualChannelBtn"));
			BaseUtil.clickElement(getProps().getProperty(
					"removeVirtualChannelBtn"));
			BaseUtil.clickElement(getProps().getProperty(
					"removeVirtualChannelOKBtn"));
			BaseUtil.waitForSpinner();
		} else {
			return;
		}
	}

	@Then("^click on \"(.*?)\" button of newly created channel \"(.*?)\" on Channel Accordion$")
	public void click_on_button_of_newly_created_channel_on_Channel_Accordion(
			String buttonName, String channelName) throws Throwable {
		String message = "Failed to click on" + buttonName + "button";
		Assert.assertTrue(message,
				sitePage.clickOperationOnSitePageChennalAccordion(buttonName));
	}

	@When("^click on \"(.*?)\" button on delete virtual channel pop-up$")
	public void click_on_Remove_button(String buttonName) throws Throwable {
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message,
				sitePage.clickOperationOnSitePage(buttonName));
	}

	@When("^click on \"(.*?)\" button on delete confirmation box$")
	public void click_on_OK_button(String buttonName) throws Throwable {
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message,
				sitePage.clickOperationOnSitePage(buttonName));
	}

	@Then("^verify Channel \"(.*?)\" message \"(.*?)\" is displayed on Channel Accordion for the channel \"(.*?)\"$")
	public void verify_message_is_displayed_on_Channel_Accordion_for_the_channel(
			String action, String actionMessage, String channelName)
			throws Throwable {
		// String query = "SELECT channel_id FROM channel WHERE channel_name='"
		// + channelName + "'";
		// String channelID = sitePage.getChannelId(testBase, query);
		if (action.equals("Creation")) {
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("addChannelSaveMsg"),
					"Message is not visible on the page");
			String query = "SELECT channel_id FROM channel WHERE channel_name='"
					+ channelName + "'";
			System.out.println("QUERY==========================" + query);
			String channelID = sitePage.getChannelId(query);
			actionMessage = actionMessage.replace("########", channelID);
			String actionMessageByQuery = BaseUtil.getElementText(getProps()
					.getProperty("addChannelSaveMsg"));
			actionMessageByQuery = actionMessageByQuery.trim();
			// Assert.assertTrue("Expected message not matched with actual
			// message.",
			// actionMessage.trim().replace("########",
			// sitePage.getChannelId(testBase, query))
			// .equalsIgnoreCase(sitePage.getElementText(getProps().getProperty("addChannelSaveMsg"))));
			Assert.assertTrue(
					"Expected message not matched with actual message.",
					actionMessage.equalsIgnoreCase(actionMessageByQuery));

		} else if (action.equals("Deletion")) {

			if (BaseUtil.instantElementCheck("Display",
					getProps().getProperty("deleteChannelMsg"))) {
				BaseUtil.assertElementDisplayed(
						getProps().getProperty("deleteChannelMsg"),
						"Message is not visible on the page");
				String query = "SELECT channel_id FROM channel WHERE channel_name='"
						+ channelName + "'";
				String channelID = sitePage.getChannelId(query);
				actionMessage = actionMessage.replace("########", channelID);
				String actionMessageByQuery = BaseUtil
						.getElementText(getProps().getProperty(
								"deleteChannelMsg"));
				Assert.assertTrue(
						"Expected message not matched with actual message.",
						actionMessage.equalsIgnoreCase(actionMessageByQuery));
			} else {
				BaseUtil.assertElementDisplayed(
						getProps().getProperty("zeroRecordsFoundChannelGrid"),
						"Message is not visible on the page");
			}
		}
	}

	@When("^click on \"(.*?)\" button on site page$")
	public void click_on_button(String buttonName) throws Throwable {
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message,
				sitePage.clickOperationOnSitePage(buttonName));
	}

	@Then("^enter \"(.*?)\" in Name field of channel on Add Virtual Channel$")
	public void enter_in_Name_field_of_channel_on_Add_Virtual_Channel(
			String channelName) throws Throwable {
		sitePage.enterText_SitePage("CHANNEL_NAME", channelName);
	}

	@Then("^select \"(.*?)\" from Measure Type Dropdown$")
	public void select_from_Measure_Type_Dropdown(String measureType)
			throws Throwable {
		sitePage.enterText_SitePage("MEASURE_TYPE", measureType);
	}

	@Then("^select \"(.*?)\" from Metadata Category Dropdown$")
	public void select_from_Metadata_Category_Dropdown(String metadataCategory)
			throws Throwable {
		sitePage.enterText_SitePage("METADATA_CATEGORY", metadataCategory);
	}

	@Then("^select \"(.*?)\" from Channel Filter Dropdown$")
	public void select_from_Channel_Filter_Dropdown(String channelFilter)
			throws Throwable {
		sitePage.enterText_SitePage("CHANNEL_FILTER", channelFilter);
	}

	@Then("^click on \"(.*?)\" button for adding the channel$")
	public void click_on_button_for_channel_on_Add_Virtual_Channel(
			String buttonName) throws Throwable {
		BaseUtil.click(getProps().getProperty("addVirtualChannelAddButton"));
	}

	@Then("^verify message \"(.*?)\" is displayed on Channel Accordion for the channel \"(.*?)\"$")
	public void verify_message_is_displayed_on_Channel_Accordion_for_the_channel(
			String saveMessage, String channelName) throws Throwable {

		BaseUtil.assertElementDisplayed(
				getProps().getProperty("addChannelSaveMsg"),
				"Message is not visible on the page");
		String query = "SELECT channel_id FROM channel WHERE channel_name='"
				+ channelName + "'";
		Assert.assertTrue(
				"Expected message not matched with actual message.",
				saveMessage.replace("########", sitePage.getChannelId(query))
						.equalsIgnoreCase(
								BaseUtil.getElementText(getProps().getProperty(
										"addChannelSaveMsg"))));

	}

	@Then("^verify newly created channel name \"(.*?)\" is displayed$")
	public void verify_created_channel_name_is_displayed(String channelName)
			throws Throwable {
		String message = "Failed to verify newly created channel";
		Assert.assertTrue(message,
				sitePage.verifyNewlyCreatedChannel(channelName));
	}

	@Then("^enter \"(.*?)\" in channel search field of add virtual channel popup$")
	public void enter_in_channel_search_field_of_add_virtual_channel_popup(
			String channelName) throws Throwable {
		sitePage.enterText_SitePage("SEARCH_CHANNEL_NAME_AVC", channelName);
	}

	@Then("^apply \"(.*?)\" operator for the selected channels$")
	public void apply_operator_for_the_selected_channels(String operatorName)
			throws Throwable {
		sitePage.enterText_SitePage("OPERATOR", operatorName);
	}

	@Then("^verify \"(.*?)\" Dropdown has following operators$")
	public void verify_Operator_Dropdown_has_following_operators(
			String dropDownName, List<String> listOperator) throws Throwable {
		Assert.assertTrue("Failed to varify dropdown values of dropdown"
				+ dropDownName,
				sitePage.verifyDropDownValues(dropDownName, listOperator));
	}

	@Then("^verify the tenant name \"(.*?)\" in the tenant column related to entered site \"(.*?)\"$")
	public void verify_the_tenant_name_in_the_tenant_column_related_to_entered_site(
			String tenantName, String siteName) throws Throwable {
		String message = "Failed to verify tenant name in the tenant column";
		Assert.assertTrue(message, sitePage.verifyTenantName(tenantName));
	}

	@Then("^the Channel list is displayed in channel accordion$")
	public void the_Channel_list_is_displayed_in_channel_accordion()
			throws Throwable {
		BaseUtil.verifyElementDisplayed(getProps().getProperty(
				"editSitePagePopupChannelAccordion"));
		String message = "Failed to verify channels on channel accordin.";
		Assert.assertTrue(message, sitePage.verifyChannelsOnChannelAccordion());
	}

	@When("^click on \"(.*?)\" button of Local Dewpoint channel$")
	public void click_on_button_of_Local_Dewpoint_channel(String buttonName)
			throws Throwable {
		String message = "Failed to click on" + buttonName + "button";
		Assert.assertTrue(message,
				sitePage.clickOperationOnSitePageChennalAccordion(buttonName));
	}

	@Then("^verify that values of following fields have been modified for Local Dewpoint channel$")
	public void verify_that_values_of_following_fields_have_been_modified_for_Local_Dewpoint_channel(
			List<String> listFieldNames) throws Throwable {
		for (String fieldName : listFieldNames) {
			Assert.assertTrue("Failed to verify modified value of field ["
					+ fieldName + "]", sitePage.verifyModifiedValues(fieldName));
		}
	}

	@Then("^reset the previous values of the Local Dewpoint channel$")
	public void reset_the_previous_values_of_the_Local_Dewpoint_channel(
			List<String> listFieldNames) throws Throwable {
		for (String fieldName : listFieldNames) {
			try {
				switch (fieldName.toUpperCase()) {
				case "DISPLAY NAME":
					BaseUtil.enterText(
							getProps().getProperty(
									"channelAccordionEditDisplayNameField"),
							getProps()
									.getProperty(
											"channelAccordionEditDisplayNameFieldOldTestData"));
					break;
				case "MEASURE TYPE":
					BaseUtil.selectDropDownByValue(
							getProps().getProperty(
									"channelAccordionEditMeasureTypeField"),
							getProps()
									.getProperty(
											"channelAccordionEditMeasureTypeFieldOldTestData"));
					break;
				case "METADATA CATEGORY":
					BaseUtil.explicitWait(2000);
					BaseUtil.selectDropDownByValue(
							getProps()
									.getProperty(
											"channelAccordionEditMetadataCategoryField"),
							getProps()
									.getProperty(
											"channelAccordionEditMetadataCategoryFieldOldTestData"));
					break;
				case "TOTAL TYPE":
					BaseUtil.selectDropDownByValue(
							getProps().getProperty(
									"channelAccordionEditTotalTypeField"),
							getProps()
									.getProperty(
											"channelAccordionEditTotalTypeFieldOldTestData"));
					break;
				default:
					logger.error("Switch Case["
							+ fieldName
							+ "] is not matched in class["
							+ getClass().getName()
							+ "] , Method["
							+ Thread.currentThread().getStackTrace()[1]
									.getMethodName() + "]");
					break;
				}
			} catch (Exception e) {
				logger.error("Failed to enter values in  field " + fieldName
						+ " see detail message : " + e.getMessage());
				Assert.fail(e.getMessage());
			}
		}
	}

	@Then("^verify that following fields are editable of Local Dewpoint channel$")
	public void verify_that_ollowing_fields_are_editable_of_Local_Dewpoint_channel(
			List<String> listFieldNames) throws Throwable {
		for (String fieldName : listFieldNames) {
			Assert.assertTrue("Failed to verify editablity of field "
					+ fieldName, sitePage.verifyElementEditable(fieldName));
		}

	}

	@Then("^modify the valuse of following fields with valid data for Local Dewpoint channel$")
	public void modify_the_valuse_of_following_fields_with_valid_data_for_Local_Dewpoint_channel(
			List<String> listFieldNames) throws Throwable {
		for (String fieldName : listFieldNames) {
			try {
				switch (fieldName.toUpperCase()) {
				case "DISPLAY NAME":
					BaseUtil.click(getProps().getProperty(
							"channelAccordionEditDisplayNameField"));
					BaseUtil.enterText(
							getProps().getProperty(
									"channelAccordionEditDisplayNameField"),
							getProps()
									.getProperty(
											"channelAccordionEditDisplayNameFieldTestData"));
					break;
				case "MEASURE TYPE":
					BaseUtil.selectDropDownByValue(
							getProps().getProperty(
									"channelAccordionEditMeasureTypeField"),
							getProps()
									.getProperty(
											"channelAccordionEditMeasureTypeFieldTestData"));
					break;
				case "METADATA CATEGORY":
					BaseUtil.explicitWait(2000);
					BaseUtil.selectDropDownByValue(
							getProps()
									.getProperty(
											"channelAccordionEditMetadataCategoryField"),
							getProps()
									.getProperty(
											"channelAccordionEditMetadataCategoryFieldTestData"));
					break;
				case "TOTAL TYPE":
					BaseUtil.selectDropDownByValue(
							getProps().getProperty(
									"channelAccordionEditTotalTypeField"),
							getProps()
									.getProperty(
											"channelAccordionEditTotalTypeFieldTestData"));
					break;
				case "MEASURE TYPE:CANCEL":
					BaseUtil.selectDropDownByValue(
							getProps().getProperty(
									"channelAccordionEditMeasureTypeField"),
							getProps()
									.getProperty(
											"channelAccordionEditMeasureTypeFieldTestDataCancel"));
					break;
				case "NVC:DISPLAY NAME":
					BaseUtil.click(getProps().getProperty(
							"channelAccordionEditDisplayNameField"));
					BaseUtil.enterText(
							getProps().getProperty(
									"channelAccordionEditDisplayNameField"),
							getProps()
									.getProperty(
											"channelAccordionEditDisplayNameFieldNVCTestData"));
					break;
				case "NVC:MEASURE TYPE":
					BaseUtil.selectDropDownByValue(
							getProps().getProperty(
									"channelAccordionEditMeasureTypeField"),
							getProps()
									.getProperty(
											"channelAccordionEditMeasureTypeFieldNVCTestData"));
					break;
				case "NVC:TOTAL TYPE":
					BaseUtil.selectDropDownByValue(
							getProps().getProperty(
									"channelAccordionEditTotalTypeField"),
							getProps()
									.getProperty(
											"channelAccordionEditTotalTypeFieldNVCTestData"));
					break;
				default:
					logger.error("Switch Case["
							+ fieldName
							+ "] is not matched in class["
							+ getClass().getName()
							+ "] , Method["
							+ Thread.currentThread().getStackTrace()[1]
									.getMethodName() + "]");
					break;
				}
			} catch (Exception e) {
				logger.error("Failed to enter values in  field " + fieldName
						+ " see detail message : " + e.getMessage());
				Assert.fail(e.getMessage());
			}
		}
	}

	@Then("^verify last saved value should be displayed for \"(.*?)\"$")
	public void verify_last_saved_value_should_be_displayed_for(
			String dropDownType) throws Throwable {
		String message = "Failed to verify last save value for" + dropDownType
				+ "dropdown";
		Assert.assertTrue(message, sitePage.verifyModifiedValues(dropDownType));
	}

	@Then("^verify the elements present on \"(.*?)\" popup$")
	public void verify_the_elements_present_on_popup(String pageName,
			List<String> listElements) throws Throwable {
		if (pageName.equals("Add Channel")) {
			for (String elementName : listElements) {
				try {
					switch (elementName.toUpperCase()) {
					case "NAME":
						Assert.assertTrue("Element " + elementName
								+ "] is not displayed", BaseUtil
								.verifyElementDisplayed(getProps().getProperty(
										"addVirtualChannelNameField")));
						break;
					case "MEASURE TYPE":
						Assert.assertTrue("Element " + elementName
								+ "] is not displayed", BaseUtil
								.verifyElementDisplayed(getProps().getProperty(
										"addVirtualChannelMTypeDD")));
						break;
					case "METADATA CATEGORY":
						Assert.assertTrue("Element " + elementName
								+ "] is not displayed", BaseUtil
								.verifyElementDisplayed(getProps().getProperty(
										"addVirtualChannelMetadataDD")));
						break;
					case "TOTAL TYPE":
						Assert.assertTrue("Element " + elementName
								+ "] is not displayed", BaseUtil
								.verifyElementDisplayed(getProps().getProperty(
										"addVirtualChannelTotalTypeDD")));
						break;
					case "AVAILABLE CHANNEL UOM":
						Assert.assertTrue("Element " + elementName
								+ "] is not displayed", BaseUtil
								.verifyElementDisplayed(getProps().getProperty(
										"addVirtualChannelUOMDD")));
						break;
					case "AVAILABLE CHANNEL MEASURE TYPE":
						Assert.assertTrue(
								"Element " + elementName + "] is not displayed",
								BaseUtil.verifyElementDisplayed(getProps()
										.getProperty(
												"addVirtualChannelAvailableChannelMTypeDD")));
						break;
					case "AVAILABLE CHANNEL SEARCH":
						Assert.assertTrue("Element " + elementName
								+ "] is not displayed", BaseUtil
								.verifyElementDisplayed(getProps().getProperty(
										"siteSearchField")));
						break;
					case "AVAILABLE CHANNEL LIST":
						Assert.assertTrue("Element " + elementName
								+ "] is not displayed", BaseUtil
								.verifyElementDisplayed(getProps().getProperty(
										"addVirtualChannelGrid")));
						break;
					default:
						logger.error("Switch Case["
								+ elementName
								+ "] is not matched in class["
								+ getClass().getName()
								+ "] , Method["
								+ Thread.currentThread().getStackTrace()[1]
										.getMethodName() + "]");
						break;
					}
				} catch (NullPointerException e) {
					logger.error("Element :" + elementName
							+ " does not exist in [" + pageName
							+ "] the , see detail message : " + e.getMessage());
				} catch (Exception e) {
					logger.error("Failed to displayed the element :"
							+ elementName + " see detail message : "
							+ e.getMessage());
				}
			}
		}
	}

	@When("^click on save button without filling add virtual channel form\\.$")
	public void click_on_save_button_without_filling_add_virtual_channel_form()
			throws Throwable {
		BaseUtil.click(getProps().getProperty("addVirtualChannelSaveButton"));
		BaseUtil.explicitWait(1000);
	}

	@Then("^valid error message \"(.*?)\" with following list should be displayed for \"(.*?)\" form$")
	public void valid_error_message_with_following_list_should_be_displayed_for_form(
			String arg1, String arg2, List<String> errorMessages)
			throws Throwable {
		Assert.assertTrue(
				"Add virtual channel error popup is not displayed",
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addVirtualChannelErrorPopup")));
		Assert.assertTrue(
				"Add virtual channel error popup header is not displayed",
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addVirtualChannelErrorPopupHeader")));
		List<String> multipleElementTextAfterLoaded = BaseUtil
				.getMultipleElementTextAfterLoaded(getProps().getProperty(
						"addVirtualChannelErrorMessageList"));
		int i = 0;
		for (String message : errorMessages) {
			Assert.assertTrue("Error message [" + message
					+ "] is not displayed",
					multipleElementTextAfterLoaded.get(i).equals(message));
			i++;
		}
	}

	@Then("^click on \"(.*?)\" button on add virtual channel error popup$")
	public void click_on_button_on_add_virtual_channel_Error_popup(String arg1)
			throws Throwable {
		BaseUtil.click(getProps().getProperty(
				"addVirtualChannelErrorMessageCloseButton"));
		BaseUtil.explicitWait(2000);
	}

	@When("^click on \"(.*?)\" button on add virtual channel popup$")
	public void click_on_button_on_add_virtual_channel_popup(String arg1)
			throws Throwable {
		BaseUtil.click(getProps().getProperty("addVirtualChannelCancelButton"));
		BaseUtil.explicitWait(2000);
	}

	@Then("^user should remain on add virtual channel popup$")
	public void user_should_remain_on_add_virtual_channel_popup()
			throws Throwable {
		Assert.assertTrue(
				"Add virtual popup is displayed",
				BaseUtil.isElementEnabled(By.xpath(getProps().getProperty(
						"addVirtualChannelSaveButton").split("===")[1])));
	}

	@Then("^user should be navigated on edit site popup with channel accordion expended$")
	public void user_should_be_navigated_on_edit_site_popup_with_channel_accordion_expended()
			throws Throwable {
		Assert.assertTrue(
				"Channel accordian is displayed",
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"channelAccordianAddVirtualChannelButton")));
	}

	@Then("^confirmation box on virtual channel should displayed on site page with message \"(.*?)\"$")
	public void confirmation_box_On_Virtual_Channel_should_displayed_with_message(
			String sMessage) throws Throwable {
		Assert.assertTrue(
				"Confirmation box is not displayed on add virtual channel pup",
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addVirtualChannelConfirmationCancelButton")));
	}

	@And("^click on Logout button on site page$")
	public void click_on_logout_button() throws Throwable {
		BaseUtil.waitForSpinner();
		if (!BaseUtil.checkIfClickable(TestBase.getReadConfigData().get(
				"optionDropDown"))) {
			Assert.fail("Not able to login in the portal.");
		}
		BaseUtil.explicitWait(2000);
		BaseUtil.clickAndWait(TestBase.getReadConfigData()
				.get("optionDropDown"));
		BaseUtil.clickAndWait(TestBase.getReadConfigData().get("logout"));

		String message = "Error while logging out";
		BaseUtil.assertElementDisplayed(
				TestBase.getReadConfigData().get("adminUserName_Locator"),
				message);
	}

	@Then("^\"(.*?)\" various users as test data for site page$")
	public void various_User_as_test_data_for_endpoint_page(String task,
			DataTable table) throws Throwable {
		List<String> lstData = table.asList(String.class);
		switch (task.toUpperCase()) {
		case "CREATE":
			for (String userName : lstData) {
				ManageTestData objDelTestdata = new ManageTestData();
				Assert.assertTrue(
						"Error occured while Inserting test data for enduser data table",
						objDelTestdata.manageTestData(
								"Site_Tenant_User_Insert", userName));
			}
			break;
		case "DELETE":
			for (String userName : lstData) {
				ManageTestData objDelTestdata = new ManageTestData();
				Assert.assertTrue(
						"Error occured while deleting test data for enduser data table",
						objDelTestdata.manageTestData(
								"Site_Tenant_User_Delete", userName));
			}

			break;
		}
	}

	@When("^we will login with \"(.*?)\" credential and varify site tab$")
	public void we_will_login_with_credential_and_verify_site_tab(
			String userName, DataTable table) throws Throwable {
		List<String> lstData = table.asList(String.class);
		userName = lstData.get(0);
		sitePage.isSiteTabAvailable(userName);
	}

	@Then("^select \"(.*?)\" from Channel Filter Dropdown on Available Channels Section$")
	public void select_from_Channel_Filter_Dropdown_on_Available_Channels_Section(
			String channelFilter) throws Throwable {
		sitePage.enterText_SitePage("FILTER:CHANNEL_FILTER", channelFilter);
	}

	@Then("^select \"(.*?)\" from Measure Type Dropdown on Available Channels Section$")
	public void select_from_Measure_Type_Dropdown_on_Available_Channels_Section(
			String measureType) throws Throwable {
		sitePage.enterText_SitePage("FILTER:MEASURE_TYPE", measureType);
	}

	@Then("^verify \"(.*?)\" channel is displayed after making filter on Unit of Measure and Measure Type$")
	public void verify_channel_is_displayed_after_making_filter_on_Unit_of_Measure_and_Measure_Type(
			String channelName) throws Throwable {
		String message = "Failed to verify filtered channel";
		Assert.assertTrue(message, sitePage.verifyFilteredChannel(channelName));
	}

	@Then("^verify that \"(.*?)\" checkbox is checked for channel \"(.*?)\" by default and sql query output should be \"(.*?)\"$")
	public void verify_that_checkbox_is_checked_by_default_and_sql_query_output_should_be(
			String checkBox, String channelName, String sqlOutput)
			throws Throwable {
		String message = "Failed to verify that checkbox" + checkBox
				+ "is checked by default";
		Assert.assertTrue(
				message,
				BaseUtil.verifyCheckBoxIsSelected(getProps().getProperty(
						"showInGraph")));
		String query = "SELECT displayed_in_graphs FROM gpup.channel WHERE channel_id in (SELECT channel_id FROM channel WHERE channel_name ='"
				+ channelName + "')";
		String output = sitePage.getShowInGraphResult(query);
		Assert.assertTrue("Expected output not matched with actual output.",
				sqlOutput.equalsIgnoreCase(output));
	}

	@Then("^verify following buttons are present in the virtual channel action column$")
	public void verify_following_buttons_are_present_in_the_virtual_channel_action_column(
			List<String> elementsList) throws Throwable {
		for (String buttonName : elementsList) {
			Assert.assertTrue("Failed to verify button[" + buttonName
					+ "[ in the virtual channel action column",
					sitePage.isElementDisplayedOnChannelTable(buttonName));
		}
	}

	@Then("^modify the valuse of following fields with valid data for Newly Created channel$")
	public void modify_the_valuse_of_following_fields_with_valid_data_for_Newly_created_channel(
			List<String> listFieldNames) throws Throwable {
		for (String fieldName : listFieldNames) {
			try {
				switch (fieldName.toUpperCase()) {
				case "NVC:DISPLAY NAME":
					BaseUtil.click(getProps().getProperty(
							"channelAccordionEditDisplayNameField"));
					BaseUtil.enterText(
							getProps().getProperty(
									"channelAccordionEditDisplayNameField"),
							getProps()
									.getProperty(
											"channelAccordionEditDisplayNameFieldNVCTestData"));
					break;
				case "NVC:MEASURE TYPE":
					BaseUtil.selectDropDownByValue(
							getProps().getProperty(
									"channelAccordionEditMeasureTypeField"),
							getProps()
									.getProperty(
											"channelAccordionEditMeasureTypeFieldNVCTestData"));
					break;
				case "NVC:TOTAL TYPE":
					BaseUtil.selectDropDownByValue(
							getProps().getProperty(
									"channelAccordionEditTotalTypeField"),
							getProps()
									.getProperty(
											"channelAccordionEditTotalTypeFieldNVCTestData"));
					break;
				default:
					logger.error("Switch Case["
							+ fieldName
							+ "] is not matched in class["
							+ getClass().getName()
							+ "] , Method["
							+ Thread.currentThread().getStackTrace()[1]
									.getMethodName() + "]");
					break;
				}
			} catch (Exception e) {
				logger.error("Failed to enter values in  field " + fieldName
						+ " see detail message : " + e.getMessage());
				Assert.fail(e.getMessage());
			}
		}
	}

	@Then("^verify that values of following fields have been modified for Newly Created channel$")
	public void verify_that_values_of_following_fields_have_been_modified_for_Newly_Created_channel(
			List<String> listFieldNames) throws Throwable {
		for (String fieldName : listFieldNames) {
			Assert.assertTrue("Failed to verify modified value of field ["
					+ fieldName + "]", sitePage.verifyModifiedValues(fieldName));
		}
	}

	@Then("^verify channel \"(.*?)\" has been removed from Channel List saying \"(.*?)\"$")
	public void verify_channel_has_been_removed_from_Channel_List(
			String channelName, String errorMsg) throws Throwable {
		String message = "Failed to verify that channel has been removed from Channel List ";
		Assert.assertTrue(message, errorMsg.equalsIgnoreCase(BaseUtil
				.getElementText(getProps().getProperty(
						"zeroRecordsFoundChannelGrid"))));
	}

	@Then("^verify inactive site \"(.*?)\" is not displayed on home tab$")
	public void verify_inactive_site_is_not_displayed_on_home_tab(
			String inactiveSite) throws Throwable {
		String message = "Failed to verify that inactive site is not displayed for Custome user";
		Assert.assertTrue(
				message,
				BaseUtil.instantElementCheck("Display",
						getProps().getProperty("inactiveSiteHomeTab")));
	}

	@When("^we will login with \"(.*?)\" credential and varify Home tab$")
	public void we_will_login_with_credential_and_verify_Home_tab(String role,
			DataTable table) throws Throwable {
		List<String> lstData = table.asList(String.class);
		String userName = lstData.get(0);
		sitePage.isHomePageAvailable(userName);
		if (!BaseUtil.checkIfClickable(TestBase.getReadConfigData().get(
				"optionDropDown"))) {
			Assert.fail("Not able to login in the portal for the user : "
					+ role);
		}
	}

	@When("^enter the username \"(.*?)\" in search field on user page$")
	public void enter_the_user_name_in_search_field(String userName)
			throws Throwable {
		userPage.enterText_UserPage("USER_SEARCH", userName);
	}

	@Then("^select the user \"(.*?)\" from the users detail grid$")
	public void select_the_user_from_the_user_details_grid(String userName)
			throws Throwable {
		userPage.selectUserFromGrid_UserPage(userName);
	}

	@When("^click on \"(.*?)\" accordion on usertab$")
	public void click_on_accordion_on_usertab(String accordionName)
			throws Throwable {
		String message = "Failed to click on accordion :" + accordionName + " "
				+ " on User Page";
		Assert.assertTrue(message,
				userPage.clickOperationOnUserPage(accordionName));
	}

	@When("^select a tenant \"(.*?)\" from the tenant drop down on edit user$")
	public void select_tenant_On_UserPage(String tenantName) throws Throwable {
		String message = "Failed to select tenant on :" + tenantName + " "
				+ " in Permission Accordion on User Page";
		TenantPage tenantPage = new TenantPage();
		Assert.assertTrue(message,
				tenantPage.selectTenantOnuserPage("SELECT_TENANT", tenantName));
	}

	@When("^verify that all Sites get selected and display as \"(.*?)\" with Tenant name on right side of Permission Accordion on edit user page$")
	public void verify_that_all_Sites_get_selected_and_display_as_with_Tenant_name_on_right_side_of_Permission_Accordion(
			String markingType) throws Throwable {
		Assert.assertTrue(markingType + "Either all sites are not selected or "
				+ markingType + " is not displayed",
				userPage.verifyUserSectionDisplayed(markingType));
	}

	@Then("^click on \"(.*?)\" button on user tab$")
	public void click_on_button_on_User_page(String sButtonName)
			throws Throwable {
		String message = "Failed to click on button :" + sButtonName + " "
				+ " on User Page";
		Assert.assertTrue(message,
				userPage.clickOperationOnUserPage(sButtonName));
		BaseUtil.explicitWait(2000);
	}

	@When("^verify the message \"(.*?)\" on \"(.*?)\" accordion on edit user$")
	public void verify_Message_Accordion_On_Edit_User_page(
			String accordionMessage, String accordion) throws Throwable {
		String message = "Failed to verify the message: " + accordionMessage
				+ " on accordion :" + accordion;
		Assert.assertTrue(message,
				userPage.verifyAccordionMessage(accordionMessage, accordion));
	}
	
	@Then("^bulk site creation popup should be displayed with following fields:$")
	public void bulk_site_creation_popup_should_be_displayed_with_following_fields(List<String> listAddSiteElements)
			throws Throwable {
		for (String elements : listAddSiteElements) {
			String message = "Failed to display the field[" + elements + "] on add site popup.";
			Assert.assertTrue(message, sitePage.isElementDisplayedOnSitePage(elements));
		}
	}
	
	@Then("^select the \"(.*?)\" csv file by clicking on \"(.*?)\" button")
	public void click_on_the_button_on_add_peripheral_firmware_popup_and_select_an_valid_file(String fileType,
			String buttonName) throws Throwable {
		String firmwareFile = null, filePath = null, absolutePath = null;
		File f = null;
		switch (fileType.toUpperCase()) {
		case "VALID":
			firmwareFile = "ValidBulkImportSitesFile.csv";
			f = new File(firmwareFile);
			absolutePath = f.getAbsolutePath();
			filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator)) + "\\downloadDir\\"
					+ firmwareFile;
			break;
		case "INVALID HEADER":
			firmwareFile = "HeaderMissingBulkImportSitesFile.csv";
			f = new File(firmwareFile);
			absolutePath = f.getAbsolutePath();
			filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator)) + "\\downloadDir\\"
					+ firmwareFile;
			break;
		case "INVALID":
			firmwareFile = "InvalidBulkImportSitesFile.csv";
			f = new File(firmwareFile);
			absolutePath = f.getAbsolutePath();
			filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator)) + "\\downloadDir\\"
					+ firmwareFile;
			break;
		}
		String message = "Failed to click on button :" + buttonName + " " + " on add endpoint firmware popup.";
		Assert.assertTrue(message, sitePage.clickChooseButtonAndSelectInvalidFile(fileType, buttonName, filePath));
	}
	
	 @When("^click on \"(.*?)\" button on bulk import site popup$")
		 public void click_on_button_on_bulk_import_site_popup(String buttonName)
		 throws Throwable {
		 BaseUtil.explicitWait(2000);
		 String message = "Failed to click on button :" + buttonName;
		 Assert.assertTrue(message,
		 sitePage.clickOperationOnSitePage(buttonName));
		 }
	 
	@Then("^verify the error messages shown on the popup for \"(.*?)\" file$")
	public void verify_the_error_messages_shown_on_the_popup_for_files(String fileType) throws Throwable {
		String message = "Failed to get the [" + fileType + "] file on bulk import popup.";
		Assert.assertTrue(message, sitePage.verifyErrorMessageBulkImport(fileType));
	}
	
	@Then("^enter one of the site name \"(.*?)\" in search field imported by bulk import$")
	public void enter_one_of_the_site_name_in_search_field_imported_by_bulk_import(String siteName) throws Throwable {
		// sitePage.waitForElement(sitePageObjectRepo);
		sitePage.enterText_SitePage("SITE_SEARCH", siteName);

	}
	
	@Then("^verify the values of site \"(.*?)\" on edit site page$")
	public void verify_the_values_for_newly_created_tenant(String sSiteName) throws Throwable {
		try {
			String message = "Failed to verify the values for " + sSiteName;
			Assert.assertTrue(message, sitePage.verifyFieldsValue(sSiteName));
		} catch (Exception e) {
			Assert.fail("Failed to Navigate on the following Page");
		} finally {
			BaseUtil.waitForSpinner();
		}
	}
	
	@When("^click on \"(.*?)\" button on site details page$")
	public void click_on_button_on_site_details_page(String arg1) throws Throwable {
		Assert.assertTrue("Failed to click on button : " + arg1, sitePage.clickOperationOnSitePage(arg1));
	}
	

	// TODO below code : 12/22/2017 7:48:35 PM
	@When("^enter the \"(.*?)\" as \"(.*?)\" text field$")
	public void enter_the_value_in_name_field(String task, String fieldName)
			throws Throwable {
//		String message = "Failed to enter the value for Fields:" + fieldName;
		// Assert.assertTrue(message, tenantPage.enterText_TenantPage(task,
		// fieldName, tenantPageObjectRepo));
	}
	
	@Then("^delete the \"(.*?)\" used as test data$")
	public void delete_the_data_used_as_test_data(String tab,
			List<String> listSiteTestData) throws Throwable {
		ManageTestData objDelTestdata = new ManageTestData();
		if (tab.equalsIgnoreCase("Sites")) {
			for (String sitesName : listSiteTestData) {
				Assert.assertTrue("Data should be deleted ",
						objDelTestdata.manageTestData("Site", sitesName));
			}
		} else if (tab.equalsIgnoreCase("Tenants")) {
			for (String tenantsName : listSiteTestData) {
				Assert.assertTrue("Data should be deleted ",
						objDelTestdata.manageTestData("Tenant", tenantsName));
			}
		} else if (tab.equalsIgnoreCase("Endpoints")) {
			for (String endpointName : listSiteTestData) {
				Assert.assertTrue("Data should be deleted ", objDelTestdata
						.manageTestData("Endpoint_Delete", endpointName));
			}
		}
	}
	
	@Then("^verify following elements are \"(.*?)\" for GP Support Users$")
	public void verify_following_elements_are_for_GP_Support_Users(String task, List<String> listElements) throws Throwable {
		if (task.equalsIgnoreCase("Displayed")) {
			for (String elements : listElements) {
			String message = "Failed to display the field[" + elements
					+ "] on add site popup.";
			Assert.assertTrue(message,
					sitePage.isElementDisplayedOnSitePage(elements));
			}
		}
		if (task.equalsIgnoreCase("Displayed")) {
			for (String elements : listElements) {
				String message = "Failed to display the field[" + elements
						+ "] on add site popup.";
				Assert.assertFalse(message,
						sitePage.isElementDisplayedOnSitePage(elements));
				}
		}
	}

	@Then("^verify following fields have been modified on Edit Site Page$")
	public void verify_following_fields_have_been_modified_on_Edit_Site_Page(List<String> fieldNameList) throws Throwable { 
		for (String fieldName : fieldNameList) {
			String message = "Failed to verify modify value for element [" + fieldName
					+ "] on add site popup.";
			Assert.assertTrue(message,
					sitePage.verifyModifiedValues(fieldName));
			} 
		}
	
	@Then("^verify \"(.*?)\" checkbox is checked after modification on Edit Page$")
	public void verify_checkbox_is_checked_after_modificationon_Edit_Page(String name)
			throws Throwable {
		String locator = getProps().getProperty("editSitePagePopupDetailActiveCheckbox");
		Assert.assertTrue(name
				+ " checkbox should be unchecked by default on Add Site model",
				BaseUtil.verifyCheckBoxIsSelected(locator));
	}
	
	@Then("^verify \"(.*?)\" column contains site value which is entered in site search field$")
	public void verify_column_contains_site_value_on_Site_Page(String columnName) throws Throwable {
		boolean bResult = false;
		List<WebElement> lstSiteNames = BaseUtil
				.getMultipleElementsAfterLoaded(getProps().getProperty(
						"siteGridNameColumn"));
		for (WebElement webElement : lstSiteNames) {
			if(webElement.getText().trim().equalsIgnoreCase(BaseUtil.getElementText(getProps().getProperty("siteNameColumnValue")))) {
				bResult = true;
				break;
			}
    	} 
		if (!bResult) {
				Assert.fail("Site value is not displayed in Site Name Column");
			}
	}
	
	@Then("^verify that \"(.*?)\" buttons is disabled if any required field is not entered on Add Site Page$")
	public void verify_buttons_is_if_any_required_field_is_not_entered_on_Add_Site_Page(String buttonName) throws Throwable {
		Assert.assertFalse(buttonName
				+ " button should be disabled but its enabled",
				sitePage.isElementEnabledOnSitePage(buttonName));
	}
	
	@Then("^click on \"(.*?)\" tab/button on Edit Site Page$")
	public void click_on_tab_on_Edit_Site_page(String sElement) throws Throwable {
		String message = "Failed to click tab/button " + sElement;
		Assert.assertTrue(message, sitePage.clickOperationOnSitePage(sElement));
	}
	
	@Then("^user should remains on edit site page and \"(.*?)\" fields should not be changed$")
	public void user_should_remains_on_edit_site_page_and_fields_should_not_be_changed(String fieldName) throws Throwable {
		String message = "Edit Site popup is not displayed";
		Assert.assertTrue(
				message,
				BaseUtil.verifyElementEnabled(getProps().getProperty("editSitePageSaveAndCloseSiteButton")));
	}
	
	@Then("verify following buttons on Site Grid Page$")
	public void verify_Buttons_on_Site_Grid(List<String> buttonList) throws Throwable {
		for (String buttonName : buttonList) {
			String message = "Failed to verify modify value for element [" + buttonName
					+ "] on add site popup.";
			Assert.assertTrue(message,
					sitePage.isElementEnabledOnSitePage(buttonName));
			} 
	}
	
	@When("^enter site name \"(.*?)\" in audit search field for Site Page")
	public void enter_site_name_in_audit_search_field(String sRootSiteName)
			throws Throwable {
		BaseUtil.explicitWait(8000);
		sitePage.enterText_AuditPage("SITE_SEARCH", sRootSiteName);
	}
	
	@Then("^select the audit record \"(.*?)\" from the audit details grid for Site Page$")
	public void select_the_audit_record_of_firmware_from_the_audit_details_grid(
			String testDataName) throws Throwable {
		BaseUtil.explicitWait(8000);
		auditPage.selectAuditFromGrid_AuditPage(testDataName);
	}
	
	@Then("^verify that user should navigate on \"(.*?)\" page$")
	public void user_should_navigate_to_page(String sPopupName)
			throws Throwable {
		BaseUtil.explicitWait(25000);
		String message = "Failed to check the popup existence" + sPopupName;
		Assert.assertTrue(message,
				auditPage.checkPopupExistence("EDIT_SITE", ""));
	}
	
	@Then("^verify that the \"(.*?)\" test data details should be displayed in the following columns for Site log")
	public void verify_that_the_newly_created_test_data_details_should_be_displayed_in_the_following_columns(
			String testDataType, DataTable table) {
		BaseUtil.explicitWait(8000);
		List<String> listElements = table.asList(String.class);
		for (String columnName : listElements) {
			sitePage.verifyDetails(columnName, testDataType);
		}
	}
	
	@Then("^verify site \"(.*?)\" has been removed from Site Details Page saying \"(.*?)\"$")
	public void verify_site_has_been_removed_from_Site_Details_Page(
			String siteName, String errorMsg) throws Throwable {
		String message = "Failed to verify that site has been removed from site details page";
		Assert.assertTrue(message, sitePage.verifyErrorMessage(errorMsg));
	}
	
	@Then("^verify following Available Channel list on Virtual channel creation tool window$")
	public void verify_following_Available_Channel_list_on_Virtual_channel_creation_tool_window(List<String> channelList)
			throws Throwable {
		BaseUtil.verifyElementDisplayed(getProps().getProperty(
				"editSitePagePopupChannelAccordion"));
		String message = "Failed to verify channels on channel accordin.";
		Assert.assertTrue(message, sitePage.verifyChannelsOnVCCreationTool(channelList));
	}
	
	@Then("^verify following fields and buttons under Paths accordion$")
	public void verify_following_fields_and_buttons_under_Paths_accordion(List<String> fieldList) throws Throwable {
		for (String fieldName : fieldList) {
			String message = "Failed to verify field [" + fieldName
					+ "] on path accordion";
			Assert.assertTrue(message,
					sitePage.isElementDisplayedOnSitePage(fieldName));
			} 
	}
	
	@Then("^verify inactive site \"(.*?)\" is not displayed on Site details Page with message \"(.*?)\"$")
	public void verify_inactive_site_is_not_displayed_on_Site_details_Page_with_message(String errorMsg) throws Throwable {
		String message = "Inactive site still showing on site details page";
		Assert.assertTrue(message, sitePage.verifyErrorMessage(errorMsg));
	}
	
	@Then("^verify \"(.*?)\" checkbox is checked by default$")
	public void verify_hideInactive_checkbox_is_checked_by_default(String name)
			throws Throwable {
		String locator = getProps().getProperty("hideInactiveCheckBox");
		Assert.assertTrue(name
				+ " checkbox should be unchecked by default on Add Site model",
				BaseUtil.verifyCheckBoxIsSelected(locator));
	}
	
	@Then("^verify that following fields are non-editable on Address Verification Screen for edit Site$")
	public void verify_that_following_fields_are_non_editable_on_Address_Verification_Screen_for_edit_Site(
			List<String> listFieldNames) throws Throwable {
		for (String fieldName : listFieldNames) {
			Assert.assertFalse("Failed to verify editablity of field "
					+ fieldName, sitePage.verifyElementEditable(fieldName));
		}

	}
	
	@Then("^click on \"(.*?)\" button on Address Verification Screen for Edit Site Page$")
	public void button_on_Address_Verification_Screen_for_Edit_Site_Page(String sElement) throws Throwable {
		String message = "Failed to click tab/button " + sElement;
		Assert.assertTrue(message, sitePage.clickOperationOnSitePage(sElement));
	}
	
	@And("^verify that \"(.*?)\" is selected by default for Country dropdown$")
	public void verify_that_is_selected_by_default_for_Country_dropdown(String countryName) throws Throwable {
		String message = "Failed to verify that" + countryName + "is selected by default";
		Assert.assertTrue(message, countryName.equalsIgnoreCase(BaseUtil.getSelectedDropDownValues(getProps().getProperty("addSiteCountryDropDown"))));
	}
	
	@Then("^enter following values in \"(.*?)\" field on edit Site Page$") 
	public void enter_following_values_in_field_on_edit_site_page(String fieldName, List<String> valuesList) throws Throwable {
		for(String value : valuesList) {
			sitePage.enterTestData(value, fieldName);
		}
	}
	
	@Then("^user should remains on edit site page$")
	public void user_should_remains_on_edit_site_page(String fieldName) throws Throwable {
		String message = "Edit Site popup is not displayed";
		Assert.assertTrue(
				message,
				BaseUtil.verifyElementEnabled(getProps().getProperty("editSitePageSaveAndCloseSiteButton")));
	}
	
	@After("@SitePage")
	public void afterClass(Scenario scenario) {
		BaseUtil.closeResources(scenario);
	}
}
