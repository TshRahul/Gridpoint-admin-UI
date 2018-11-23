package com.gridpoint.adminui.automation.tenant;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.gridpoint.adminui.automation.commonSteps.CommonStepsImplementation;
import com.gridpoint.adminui.automation.loginAndInitilizeResources.LoginPageTest;
import com.gridpoint.adminui.automation.testdata.ManageTestData;
import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.util.BaseUtil;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TenantPageTest extends CommonInit {

	private Logger logger = Logger.getLogger(LoginPageTest.class);
	CommonStepsImplementation commonStepsImplementation = new CommonStepsImplementation();

	private TenantPage tenantPage;
	private static boolean isTenantPresent;

	@Given("^User is already logged in to Admin URL and is already present at the Tenant Tab for \"(.*?)\"$")
	public void user_is_already_logged_in_to_Admin_URL_and_is_already_present_at_the_Tenant_Tab_for_Scenario_(
			String arg1) throws Throwable {
		tenantPage = new TenantPage();

		BaseUtil.waitForSpinner();
		BaseUtil.overrideTimeout(getProps().getProperty("tenantPageTimeout"));
		BaseUtil.overridePollingfrequency(getProps().getProperty("tenantPagePollingFrequency"));

		String message = "Unsuccessful login with valid credentials";
		BaseUtil.assertElementDisplayed(getProps().getProperty("optionDropDown"), message);
	}

	@Given("^we are on Tenant page and search Tenant field is enabled visible$")
	public void we_are_on_Audit_page_and_search_audit_field_is_enabled() throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.clickAndWait(getProps().getProperty("tenantTab"));
		BaseUtil.explicitWait(3000);
		BaseUtil.waitForSpinner();
		BaseUtil.instantElementCheck("Display", getProps().getProperty("tenantDetailGrid"));
	}

	@Then("^verify the tenant page by confirming the availability of tenant grid$")
	public void verify_the_tenant_page_by_confirming_the_availability_of_tenant_grid() throws Throwable {
		String message = "Failed to verify tenant grid availablity. ";
		BaseUtil.assertElementDisplayed(getProps().getProperty("tenantDetailGrid"), message);
	}

	@Then("^verify the columns in tenant details grid$")
	public void verify_the_columns_in_tenant_details_grid(DataTable args) throws Throwable {
		List<String> lstData = args.asList(String.class);
		args.asList(String.class);
		boolean isVisible = true;
		for (String header : lstData) {
			boolean visible = false;
			if (header.trim().equals("Name")) {
				visible = BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantGridNameHeader"));
			}
			if (header.trim().equals("Tenant Type")) {
				visible = BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantGridTenantTypeHeader"));
			}
			if (header.trim().equals("Parent")) {
				visible = BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantGridParentHeader"));
			}
			if (header.trim().equals("Parent Tenant Type")) {
				visible = BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantGridParentTenantTypeHeader"));
			}
			if (header.trim().equals("Active")) {
				visible = BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantGridActiveHeader"));
			}

			// break if any column is not visible
			if (visible == false) {
				isVisible = false;
				Assert.assertTrue("Header [" + header + "] is not visible in User Grid", isVisible);
				break;
			}
		}
	}

	@Then("^verify the sorting functionality of columns in tenant details grid$")
	public void verify_the_sorting_functionality_of_columns_in_tenant_details_grid(DataTable args) throws Throwable {
		List<String> lstData = args.asList(String.class);
		for (String header : lstData) {
			tenantPage.verifySorting_TenantPage("Descending", header);
			tenantPage.verifySorting_TenantPage("Ascending", header);
		}
	}

	@Then("^verify the default size of page navigation$")
	public void verify_default_size_for_page_navigation() throws Throwable {
		String message = "Verify Default Size for Page navigation";
		Assert.assertTrue(message, tenantPage.clickOperationOnTenantDetailPopup("DEFAULT VALUE NAVIGATION"));
	}

	@When("^click on \"(.*?)\" link$")
	public void click_on_link(String sLinkName) throws Throwable {
		BaseUtil.explicitWait(2000);
		tenantPage.clickPaginationTenantPage(sLinkName);
	}

	@Then("^\"(.*?)\" page should displayed$")
	public void page_should_displayed(String task) throws Throwable {
		tenantPage.verifyTenantDetailGridPagination(task);
	}

	@When("^enter random page number in page link text$")
	public void enter_page_number_in_page_link_text() throws Throwable {
		String totalPages = BaseUtil.getElementText(getProps().getProperty("tenantPaginationTotalPages"));
		totalPages = totalPages.substring(totalPages.indexOf("/") + 1).trim();
		int pageCount = Integer.parseInt(totalPages);
		if (pageCount <= 1) {
			Assert.fail("Indufficient data for this test step. All the available tenants are on the first page only.");
		}
		Random random = new Random();
		int randomPage = random.nextInt(pageCount + 1);
		if (randomPage == 0 || randomPage == 1)
			randomPage = 2;
		boolean bSet = tenantPage.setRandomPage(Integer.parseInt(totalPages), randomPage);
		BaseUtil.waitForSpinner();
		Assert.assertTrue("Page" + randomPage + " is not selected.", bSet);
	}

	@Then("^user should be navigated on the given random page$")
	public void user_should_be_navigated_on_the_page() throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.getMultipleElementTextAfterLoaded(getProps().getProperty("tenantGridNameColumn"));
		BaseUtil.setElementAttribute(getProps().getProperty("tenantPaginationCurrentPage"), "1");
		BaseUtil.waitForSpinner();
		BaseUtil.getMultipleElementTextAfterLoaded(getProps().getProperty("tenantGridNameColumn"));
	}

	@Then("^delete the \"(.*?)\" data used as test data for tenant page$")
	public void add_delete_data_used_as_test_data_for_tenant_page(String dataType, List<String> listTenantTestData)
			throws Throwable {
		ManageTestData objTestdata = new ManageTestData();
		if (dataType.equals("Tenant")) {

			for (String tenantTestData : listTenantTestData) {
				isTenantPresent = objTestdata.manageTestData("Tenant_Present", tenantTestData);
				if (!isTenantPresent) {
					return;
				}
				// BaseUtil.waitForSpinner(1000);
				String tabName = "Tenant";
				// Assert.assertTrue("Clicking on " + tabName + " is failed",
				// tenantPage.clickOperationOnTenantPage(tabName,
				// getProps().getProperty));
				// tenantPage.waitForSpinner(1000);

				commonStepsImplementation.enter_the_test_Data_in_the_search_box_and_click_on_the_search_button(tabName,
						tenantTestData);
				commonStepsImplementation.select_the_given_from_the_list_and_verify_the_confirmation_box(tabName,
						tenantTestData);

				BaseUtil.explicitWait(PathConstants.WAIT_HIGH);
				commonStepsImplementation.click_on_button(tabName, "Delete Tenant");
				BaseUtil.explicitWait(PathConstants.WAIT_LOW);
				commonStepsImplementation.confirmation_box_should_displayed_with_message(tabName,
						"Are you sure you want to delete this Tenant?", tenantTestData);
				commonStepsImplementation.click_on_button(tabName, "Tenant:Delete_OK");
				BaseUtil.waitForSpinner(1000);
				commonStepsImplementation.user_should_be_navigated_to_page_with_a_label_saying("Tenant ##### deleted.",
						tabName, tenantTestData);
				isTenantPresent = false;

				logger.info("*****(" + tenantTestData + ")Tenant has been deleted successfully*****");
			}

		}

	}

	// TODO : method shifted to Common file
	// @Then("^select given \"(.*?)\" from the list and verify the Confirmation
	// box$")
	// private void
	// select_the_given_tenant_from_the_list_and_verify_the_confirmation_box(
	// String sourceNameFeature, String testData) {
	// boolean bResult = false;
	// String gridColumnLocator = null;
	// if (sourceNameFeature.equalsIgnoreCase("Tenant")) {
	// BaseUtil.click(getProps().getProperty("tenantSearchButton"));
	// BaseUtil.waitForSpinner();
	// gridColumnLocator = getProps().getProperty("tenantGridNameColumn");
	//
	// }
	//
	// try {
	// List<WebElement> lstSourcesNames = BaseUtil
	// .getMultipleElementsAfterLoaded(gridColumnLocator);
	// for (WebElement webElement : lstSourcesNames) {
	// if (webElement.getText().trim().equalsIgnoreCase(testData)) {
	// webElement.click();
	// BaseUtil.waitForSpinner(1000);
	// bResult = true;
	// break;
	// }
	// }
	// if (!bResult) {
	// Assert.fail("Given test " + sourceNameFeature
	// + " is not available");
	// }
	// logger.debug("Searched source :" + testData + " is selected.");
	// } catch (Exception e) {
	// logger.error("Failed to select source: " + testData
	// + " from grid , see detail error message: "
	// + e.getStackTrace().toString());
	// Assert.fail("Failed to select the: " + testData
	// + ", see detail error message: \n"
	// + e.getStackTrace().toString());
	// } finally {
	// BaseUtil.explicitWait(3000);
	// }
	// }

	// TODO : method shifted to Common file
	// @Then("^enter_the_test_Data_in_the_search_box_and_click_on_the_search_button$")
	// private void
	// enter_tenant_test_Data_in_the_search_box_and_click_on_the_search_button(
	// String type, String testData) {
	// String fieldLocator = null;
	// if (type.equalsIgnoreCase("Tenant")) {
	// fieldLocator = getProps().getProperty("tenantsSearchField");
	//
	// }
	// BaseUtil.enterText(fieldLocator, testData);
	// BaseUtil.waitForSpinner(1000);
	//
	// }

	// TODO : method shifted to sitepagetest.java
	// @Then("^click on \"(.*?)\" button/tab$")
	// public void click_on_button_Tenant(String tabName, String buttonName)
	// throws Throwable {
	// if (tabName.equals("Tenant")) {
	// Assert.assertTrue("Clicking on " + buttonName + " is failed",
	// tenantPage.clickOperationOnTenantPage(buttonName));
	// }
	//
	// }

	// TODO : method shifted to Common file
	// @Then("^confirmation box will be displayed with message saying \"(.*?)\"$")
	// public void confirmation_box_will_be_displayed_with_message_saying(
	// String type, String expectedMessage, String testData)
	// throws Throwable {
	// String messageOnUI = null;
	// if (type.equals("Tenant")) {
	// BaseUtil.assertElementDisplayed(
	// getProps().getProperty("editTenantDeleteConfirmationPopup"),
	// "Delete Confirmation box is not displayed");
	// BaseUtil.assertElementDisplayed(
	// getProps().getProperty(
	// "editTenantDeleteConfirmationPopupTitle"),
	// "Delete Confirmation box Title is not displayed");
	// BaseUtil.assertElementDisplayed(
	// getProps().getProperty(
	// "editTenantDeleteConfirmationPopupMessage"),
	// "Delete Confirmation box Message is not displayed");
	// messageOnUI = BaseUtil.getElementText(getProps().getProperty(
	// "editTenantDeleteConfirmationPopupMessage"));
	//
	// }
	// Assert.assertTrue("Confirmation message on UI(" + messageOnUI
	// + ") is not expected(" + expectedMessage + ") message",
	// expectedMessage.equals(messageOnUI));
	// }

	// TODO : method shifted to Common file
	// @Then("^verify user should be navigated to tenant page with a label saying
	// \"(.*?)\"$")
	// public void user_should_be_navigated_to_page_with_a_label_saying(
	// String message, String type, String testData) throws Throwable {
	// BaseUtil.explicitWait(2000);
	// String messageOnUI = null;
	// if (type.equalsIgnoreCase("Tenant")) {
	// message = message.replace("#####", testData);
	// BaseUtil.waitForSpinner();
	// BaseUtil.assertElementDisplayed(
	// getProps().getProperty("addTenantSaveMsg"),
	// "Message is not visible on the Tenant page");
	// messageOnUI = BaseUtil.getElementText(getProps().getProperty(
	// "addTenantSaveMsg"));
	// }
	//
	// Assert.assertTrue(" Message on UI(" + messageOnUI
	// + ") is not expected(" + message + ") message",
	// message.equals(messageOnUI));
	// }

	@When("^click on \"(.*?)\" button on Tenant Page$")
	public void click_on_button_on_Tenant_Page(String sButtonName) throws Throwable {
		String message = "Failed to click on button :" + sButtonName + " " + " on tenant detail grid.";
		Assert.assertTrue(message, tenantPage.clickOperationOnTenantPage(sButtonName));
		// tenantPage.waitForSpinner();
	}

	@Then("^add tenant popup should be displayed with following fields:$")
	public void add_tenant_popup_should_be_displayed_with_name_parent_and_fields(List<String> lstAddTenantFields)
			throws Throwable {
		for (String sField : lstAddTenantFields) {
			String message = "Failed to display the field[" + sField + "] on add tenant popup.";
			Assert.assertTrue(message, tenantPage.isElementDisplayedOnTenantPage(sField));
		}
	}

	@Then("^verify the dropdown values for \"(.*?)\" field in Add Tenant popup$")
	public void verify_the_dropdown_values_for_field_in_add_tenant_popup(String fieldName,
			List<String> listDropdownValues) throws Throwable {
		tenantPage.verifyDropdownStringValues(fieldName, listDropdownValues);
	}

	@Then("^verify \"(.*?)\" is not an option in Tenant Type field on add tenant popup$")
	public void verify_root_is_not_an_option_in_tenant_type_field_on_add_tenant_popup(String sField) throws Throwable {
		String message = "Failed to display the field[" + sField + "] on add tenant popup.";
		Assert.assertTrue(message, tenantPage.isElementDisplayedOnTenantPage(sField));
	}

	@Then("^verify following button status on add tenant popup$")
	public void verify_and_button_should_be_enabled(DataTable table) throws Throwable {
		Map<String, String> data = table.asMaps(String.class, String.class).get(0);
		Set<String> buttonStatus = data.keySet();
		for (String sButton : buttonStatus) {
			if (sButton.equalsIgnoreCase("Enable")) {
				String message = "Failed to verify that button[" + data.get(sButton).trim()
						+ "] is enabled on add tenant popup.";
				Assert.assertTrue(message, tenantPage.isElementEnabledOnTenantPage(data.get(sButton).trim()));
			} else if (sButton.equalsIgnoreCase("Disable")) {
				String message = "Failed to verify that button[" + data.get(sButton).trim()
						+ "] is disabled on add tenant popup.";
				Assert.assertTrue(message, !tenantPage.isElementEnabledOnTenantPage(data.get(sButton).trim()));
			}
		}
	}

	@Then("^enter the \"(.*?)\" in \"(.*?)\" field on add tenant popup$")
	public void enter_the_text_in_field_on_add_tenant_popup(String text, String fieldName) {
		tenantPage.enterText_TenantPage(text, fieldName);
	}

	@Then("^select \"(.*?)\" parent tenant from \"(.*?)\" parent selection box$")
	public void select_parent_tenant_from_parent_selection_box(String text, String fieldName) {
		BaseUtil.explicitWait(1000);
		tenantPage.enterText_TenantPage(text, fieldName);
	}

	@Then("^verify Active flag is default to true$")
	public void verify_active_flag_is_dafault_to_true() {
		String message = "Failed to verify the default statue of Active checkbox";
		Assert.assertTrue(message, tenantPage.verifyDefaultStatus("ACTIVE"));
	}

	@Then("^select \"(.*?)\" type from \"(.*?)\" dropdown field on Add Tenant popup$")
	public void select_type_from_dropdown_field_on_Add_Tenant_popup(String task, String fieldName) {
		BaseUtil.explicitWait(1000);
		tenantPage.enterText_TenantPage(task, fieldName);
	}

	@Then("^user should be navigated to tenant page with a label saying \"(.*?)\"$")
	public void user_should_be_navigated_to_Site_page_with_a_label_saying(String siteSaveMessage) throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.assertElementDisplayed(getProps().getProperty("addTenantSaveMsg"),
				"Message is not visible on the page");
		String message = "Expected message not matched with actual message.";
		Assert.assertTrue(message,
				siteSaveMessage.equals(BaseUtil.getElementText(getProps().getProperty("addTenantSaveMsg"))));
	}

	@When("^enter the tenant name \"(.*?)\" in search field$")
	public void enter_the_root_tenant_in_search_field(String sRootTenantName) throws Throwable {
		tenantPage.enterText_TenantPage(sRootTenantName, "TENANT_SEARCH");
	}

	@Then("^verify the values for newly created tenant \"(.*?)\"$")
	public void verify_the_values_for_newly_created_tenant(String sTenantName) throws Throwable {
		try {
			String message = "Failed to verify the values for " + sTenantName;
			Assert.assertTrue(message, tenantPage.verifyFieldsValue(sTenantName));
		} catch (Exception e) {
			Assert.fail("Failed to Navigate on the following Page");
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	@Then("^select the tenant \"(.*?)\" from the tenant details grid$")
	public void select_the_root_tenant_from_the_tenant_details_grid(String sTenantName) throws Throwable {
		String message = "Tenant [" + sTenantName + "] is not found in tenant detail grid.";
		Assert.assertTrue(message, tenantPage.selectTenantFromGrid_TenantPage(sTenantName));
	}

	@Then("^verify the search output by entering value \"(.*?)\" in search field$")
	public void verify_the_search_output_by_entering_value_in_search_field(String arg) throws Throwable {
		tenantPage.verifyTenantSearchFunctionality(arg);
	}

	@Then("^verify the pagination by entering value \"(.*?)\" in search field$")
	public void verify_the_pagination_by_entering_value_in_search_field(String arg) throws Throwable {
		tenantPage.verifyTenantSearchFunctionality(arg);
	}

	@Then("^root tenant details page should be displayed\\.$")
	public void root_tenant_details_page_should_be_displayed() throws Throwable {
		String message = "Root tenant detail page is not displayed.";
		BaseUtil.assertElementDisplayed(getProps().getProperty("tenantEditTenantDetailGrid"), message);
	}

	@Then("^verify there should not be any \"(.*?)\" button$")
	public void verify_there_should_not_be_any_button(String sButtonName) throws Throwable {
		String message = sButtonName + " is available.";
		Assert.assertFalse(message, tenantPage.isElementDisplayedOnTenantPage(sButtonName));
	}

	@Then("^verify following fields should be in Read-only mode$")
	public void verify_all_the_fields_should_be_in_Read_only_mode(List<String> lstEditTenantFields) throws Throwable {
		for (String sField : lstEditTenantFields) {
			String message = "Failed to display the field[" + sField + "] on add tenant popup.";
			
			if (sField.equalsIgnoreCase("Parent")) {
				sField = "EDITTENANT_PARENT";
				Assert.assertFalse(message, tenantPage.isElementEnabledOnTenantPage(sField));
			} else {
				Assert.assertFalse(message, tenantPage.verifyElementReadOnly(sField));
			}
			
		}
	}

	@Then("^verify the selection of \"(.*?)\" tenant after clicking on it in tenant detail grid$")
	public void verify_the_selection_of_tenant_after_clicking_on_it_in_tenant_detail_grid(String arg1)
			throws Throwable {
		BaseUtil.explicitWait(5000);
		List<WebElement> lstTenantNames = BaseUtil
				.getMultipleElementsAfterLoaded(getProps().getProperty("tenantGridNameColumn"));
		BaseUtil.getMultipleElementsAfterLoaded(getProps().getProperty("tenantGridParentColumn"));
		Random random = new Random();
		int randomTenantIndex = random.nextInt(lstTenantNames.size());
		lstTenantNames.get(randomTenantIndex).click();
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(5000);
		boolean nameFlag = BaseUtil.verifyElementDisplayed(getProps().getProperty("addTenantPopupNameField"));
		boolean parentFlag = BaseUtil.verifyElementDisplayed(getProps().getProperty("addTenantPopupParentField"));
		Assert.assertTrue("Tenant selection is failed", (nameFlag && parentFlag));
	}

	@Then("^Verify the presence of \"(.*?)\" button on Tenants Detail page$")
	public void verify_the_presence_of_button_on_Tenants_Detail_page(String arg1) throws Throwable {
		Assert.assertTrue(arg1 + " button is available on edit tenant popup",
				tenantPage.isElementDisplayedOnTenantPage(arg1));
	}

	@Then("click on the \"(.*?)\" button on Tenant Detail page")
	public void click_on_the_button_on_Tenant_Detail_page(String arg1) throws Throwable {
		String message = "Failed to click on button :" + arg1 + " " + " on Tenant Detail Page .";
		Assert.assertTrue(message, tenantPage.clickOperationOnTenantDetailPopup(arg1));
	}

	@Then("^user should be navigated on tenant detail grid$")
	public void user_should_be_navigated_on_tenant_detail_grid() throws Throwable {
		String message = "Failed to navigate on tenant detail grid.";
		Assert.assertTrue(message, tenantPage.isElementEnabledOnTenantPage("TENANT_DETAIL_GRID"));
	}

	@Then("^valid error message \"(.*?)\" should be displayed for \"(.*?)\" field on add tenant popup$")
	public void valid_error_message_should_be_displayed_for_field(String sErrorMsg, String sFieldName)
			throws Throwable {
		String message = "Failed to verify error message[" + sErrorMsg + "] for field [" + sFieldName
				+ "] on add tenant popup.";
		Assert.assertTrue(message, tenantPage.verifyErrorMessage_TenantPage(sErrorMsg, sFieldName));
	}

	@Then("^confirmation box should displayed with message \"(.*?)\" for tenant tab$")
	public void confirmation_box_should_displayed_with_message_for_tenant_tab(String sMessage) throws Throwable {
		String message = "Failed to display the confirmation dialog with message: " + sMessage;
		Assert.assertTrue(message, tenantPage.checkPopupExistence("ADDTENANT_CONFIRMATION", sMessage));
	}

	@When("^click on \"(.*?)\" button on confirmation box$")
	public void click_on_button_on_confirmation_box(String sButtonName) throws Throwable {
		String message = "Failed to click on button :" + sButtonName + " " + " on confirmation box.";
		Assert.assertTrue(message, tenantPage.clickOperationOnConfirmationBox(sButtonName));
	}

	@Then("^user should remain on add tenant popup$")
	public void user_should_remain_on_add_tenant_popup() throws Throwable {
		String message = "Failed to check existence of  add tenant popup.";
		Assert.assertTrue(message, tenantPage.checkPopupExistence("ADDTENANT", ""));
	}

	@And("^verify that \"(.*?)\" column should be displayed$")
	public void verify_that_column_should_be_displayed(String columnName) throws Throwable {
		String message = columnName + " coulumn is not available.";
		Assert.assertTrue(message, tenantPage.isElementDisplayedOnTenantPage(columnName));
	}

	@Then("^select \"(.*?)\" from \"(.*?)\" dropdown$")
	public void select_from_dropdown(String value, String tenantType) throws Throwable {
		tenantPage.enterText_TenantPage(tenantType, value);
	}

	@Then("^verify that dropdown value of \"(.*?)\" has been updated to \"(.*?)\"$")
	public void verify_that_dropdown_value_of_has_been_updated(String dropdown, String value) throws Throwable {
		String message = "failed to verify the updated value of ]" + dropdown + "[ dropdown";
		Assert.assertTrue(message, tenantPage.verifyUpdatedValue(dropdown, value));
	}

	@Then("^verify the \"(.*?)\" on tenant details popup$")
	public void verify_site_label_on_tenant_details_grid(String elementName) throws Throwable {
		String message = "[" + elementName + "] count is not found on tenant detail popup.";
		Assert.assertTrue(message, tenantPage.isElementDisplayedOnTenantPage(elementName));
	}

	@When("^select the \"(.*?)\" tenant in the Subtenants section$")
	public void click_on_tenant_in_the_Subtenants_section(String subtenant) throws Throwable {
		String message = "Tenant [" + subtenant + "] is not found in Tenant section of tenant detail grid.";
		Assert.assertTrue(message, tenantPage.selectSubtenantFromTenantGrid_TenantPage(subtenant));
	}

	@When("^verify delete button visibility for \"(.*?)\" users$")
	public void verify_delete_button_visibility(String userRole) throws Throwable {
		try {
			String message;
			switch (userRole.toUpperCase()) {
			case "GP Admin":
				message = "Delete button is not found for the Role [" + userRole + "] on edit Tenant Modal.";
				Assert.assertTrue(message, tenantPage.isElementDisplayedOnTenantPage("DELETE"));
				break;
			case "GP Support":
				message = "Delete button is not found for the Role [" + userRole + "] on edit Tenant Modal.";
				Assert.assertFalse(message, !(tenantPage.isElementDisplayedOnTenantPage("DELETE")));
				break;
			}
		} catch (Exception e) {
			Assert.fail("Failed to Navigate on the following Page");
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	@Then("^confirmation box on detail tenant popup should displayed with message \"(.*?)\"$")
	public void confirmation_box_on_detail_tenant_firmware_popup_should_displayed_with_message(
			String confirmationboxMessage) throws Throwable {
		if (confirmationboxMessage.toLowerCase().startsWith("are you sure")) {
			boolean okButton = BaseUtil
					.verifyElementDisplayed(getProps().getProperty("DeleteTenantOkConfirmationButton"));
			boolean cancelButton = BaseUtil
					.verifyElementDisplayed(getProps().getProperty("DeleteTenantCancelConfirmationButton"));
			Assert.assertTrue(
					"Behavior confirmation popup did not appear when clicked on delete button of edit Peripheral Firmware",
					(okButton && cancelButton));
		}
	}

	@When("^click on \"(.*?)\" button on delete tenant popup$")
	public void click_on_button_delete_tenant_popup(String sButtonName) throws Throwable {
		String message = "Failed to click on button :" + sButtonName + " " + " on tenant detail grid.";
		Assert.assertTrue(message, tenantPage.clickOperationOnTenantPage(sButtonName));
	}

	@Then("^verify pagination should be disabled if \"(.*?)\" section have no data and verify error message \"(.*?)\"$")
	public void verify_pagination_should_be_disabled_if_section_have_no_data_and_verify_error_message(String sSection,
			String sMessage) throws Throwable {
		tenantPage.verifyPaginationAndNoRecordFounds(sSection, sMessage);
	}

	@Given("^click on \"(.*?)\" Tab$")
	public void click_on_following_Tab(String tab) throws Throwable {
		String message = "Failed to click on tab :" + tab + " " + " on tenant detail grid.";
		Assert.assertTrue(message, tenantPage.clickOperationOnTenantPage(tab));

	}

	@Then("^user should navigated on \"(.*?)\" page$")
	public void user_should_be_navigated_on_following_page(String tab) throws Throwable {
		try {
			String message;
			switch (tab.toUpperCase()) {
			case "SITES":
				message = "Failed to navigate on Sites detail grid.";
				Assert.assertTrue(message, BaseUtil.verifyElementEnabled(getProps().getProperty("siteDetailGrid")));
				break;
			case "TENTANTS":
				message = "Failed to navigate on Tenant detail grid.";
				Assert.assertTrue(message, BaseUtil.verifyElementEnabled(getProps().getProperty("tenantDetailGrid")));
				break;
			}
		} catch (Exception e) {
			Assert.fail("Failed to Navigate on the following Page");
		}
	}

	@Then("^click on \"(.*?)\" checkbox on edit tenant page$")
	public void active_checkbox_is_checked(String name) throws Throwable {
		BaseUtil.clickAndWait(getProps().getProperty("editSiteActiveCheckbox"));
	}

	@Then("^click the \"(.*?)\" checkbox on tenant page$")
	public void click_checkbox_is_unchecked(String checkBox) throws Throwable {
		Assert.assertTrue(checkBox + " checkbox is not unchecked before searching site",
				tenantPage.clickOperationOnTenantPage(checkBox));
	}

	@Then("verify Export icon is display on Tenant tab$")
	public void verify_Export_icon_is_display_on_tenant_tab() throws Throwable {
		String message = "Export Icon is not available on Peripheral Firmware Tab";
		BaseUtil.assertElementDisplayed(getProps().getProperty("exportIcon"), message);
	}

	@When("^enter the username \"(.*?)\" in search field$")
	public void enter_the_user_name_in_search_field(String userName) throws Throwable {
		tenantPage.enterText_TenantPage("USER_SEARCH", userName);
	}

	@Then("^select the user \"(.*?)\" from the users details grid$")
	public void select_the_user_from_the_users_details_grid(String userName) throws Throwable {
		tenantPage.selectUserFromGrid_UserPage(userName);
		BaseUtil.explicitWait(3000);
	}

	@When("^click on \"(.*?)\" accordion on userpage$")
	public void click_on_accordion(String accordionName) throws Throwable {
		String message = "Failed to click on accordion :" + accordionName + " " + " on User Page";
		Assert.assertTrue(message, tenantPage.clickOperationOnTenantPage(accordionName));
	}

	@When("^select a tenant \"(.*?)\" from the tenant drop down on edit user page$")
	public void select_tenant_On_UserPage(String tenantName) throws Throwable {
		String message = "Failed to select tenant on :" + tenantName + " " + " in Permission Accordion on User Page";
		Assert.assertTrue(message, tenantPage.selectTenantOnuserPage("SELECT_TENANT", tenantName));
	}

	@When("^click on \"(.*?)\" button on edit user$")
	public void click_on_button_On_Edit_User(String sButtonName) throws Throwable {
		String message = "Failed to click on button :" + sButtonName + " " + " on User Page";
		Assert.assertTrue(message, tenantPage.clickOperationOnTenantPage(sButtonName));
	}

	@Then("^verify \"(.*?)\" is not an option in Tenant Type field on edit tenant popup$")
	public void verify_root_is_not_an_option_in_tenant_type_field_on_edit_tenant_popup(String sField) throws Throwable {
		String message = "Failed to display the field[" + sField + "] on add tenant popup.";
		Assert.assertTrue(message, tenantPage.isElementDisplayedOnTenantPage(sField));
	}

	@Then("^verify delete button is not available for the tenant who is associated with any site or subtenant$")
	public void verify_delete_button_is_not_available_for_the_tenant_who_is_associate_with_any_site_or_subtenant()
			throws Throwable {
		String message = "Delete button is available for the tenant who is associated with any site or subtenant.";
		Assert.assertTrue(message, tenantPage.isElementDisplayedOnTenantPage("DELETE"));
	}

	@Then("^click on the portal button to logout from the application$")
	public void click_on_portal_button_to_logout() throws Throwable {
		BaseUtil.waitForSpinner();
		if (!BaseUtil.checkIfClickable(getProps().getProperty("optionDropDown"))) {
			Assert.fail("Not able to click on portal navigator.");
		}
		BaseUtil.clickAndWait(getProps().getProperty("optionDropDown"));
		BaseUtil.clickAndWait(getProps().getProperty("logout"));
		BaseUtil.waitForSpinner();
	}

	@When("^login with user \"(.*?)\" to verify tenant$")
	public void we_will_login_with_credential_and_verify_dashboard_tab(String userName) throws Throwable {
		BaseUtil.enterText(getProps().getProperty("adminUserName_Locator"), userName);
		BaseUtil.enterText(getProps().getProperty("adminPassword_Locator"), PathConstants.DEFAULT_USER_PASSWORD);
		BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));
		BaseUtil.waitForSpinner();
	}

	@Then("^verify the Edit modal is open and edit Property is not available for GP_Support users$")
	public void verify_the_edit_properties_for_GP_Support_users() throws Throwable {
		boolean nameFlag = BaseUtil.verifyElementEnabled(getProps().getProperty("addTenantPopupNameField"));
		boolean cancelFlag = BaseUtil.verifyElementEnabled(getProps().getProperty("addTenantPopupCancelButton"));
		if (!(nameFlag && cancelFlag)) {
			Assert.assertTrue("The edit properties is not available for Role_GP_Support User.",
					!nameFlag && cancelFlag);
		}
	}

	@When("^click on \"(.*?)\" button on add tenant popup$")
	public void click_on_button_on_add_tenant_popup(String sButtonName) throws Throwable {
		String message = "Failed to click on button :" + sButtonName + " " + " on endpoint detail grid.";
		Assert.assertTrue(message, tenantPage.clickOperationOnTenantPage(sButtonName));
	}

	@After("@TenantPage")
	public void afterClass(Scenario scenario) {
		BaseUtil.closeResources(scenario);
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// @Given("^Initialize resources in the beginning of each scenario for Tenant
	// Page$")
	// public void Initialize_Resources() throws IOException,
	// InterruptedException {
	// TestBase.getInstance();
	//
	// endpointPageObjectRepo =
	// TestBase.getReadResources().getValuesFromXml("EndpointPage.xml",
	// "endpointObjectRepository");
	// userPageObjectRepo =
	// TestBase.getReadResources().getValuesFromXml("UserPage.xml",
	// "userObjectRepository");
	// getProps().getProperty =
	// TestBase.getReadResources().getValuesFromXml("TenantPage.xml",
	// "tenantObjectRepository");
	// tenantPageObjectText =
	// TestBase.getReadResources().getValuesFromXml("TenantPage.xml",
	// "tenantObjectText");
	// tenantPageObjectTestData =
	// TestBase.getReadResources().getValuesFromXml("TenantPage.xml",
	// "tenantPageObjectTestData");
	// endpointPageObjectTestData =
	// TestBase.getReadResources().getValuesFromXml("EndpointPage.xml",
	// "endpointObjectTestData");
	//
	//
	// // BaseUtil.overrideTimeout(getProps().getProperty("timeout"));
	// //
	// BaseUtil.overridePollingfrequency(getProps().getProperty("pollingFrequency"));
	// TestBase.userLogin();
	// TestBase.setWebDriverWait(TestBase.getWebDriverWait());
	// BaseUtil.waitForSpinner();
	// }
	//
	//
	// @Given("^we launch the browser with adminUI URL and login with valid admin
	// credentials$")
	// public void
	// we_launch_the_browser_with_adminUI_URL_and_login_with_valid_admin_credentials()
	// throws Throwable {
	// String message = "Unsuccessful login with valid credentials";
	// BaseUtil.waitForSpinner();
	// BaseUtil.assertElementDisplayed(getProps().getProperty("optionDropDown"),
	// message);
	// }
	//
	// @When("^we will land on site Page, we will click on tenant tab$")
	// public void we_will_land_on_site_Page_we_will_click_on_tenant_tab()
	// throws Throwable {
	// BaseUtil.click(getProps().getProperty("tenantTab"));
	// }
	//
	// @Then("^\"(.*?)\" various users as test data for tenant page$")
	// public void various_User_as_test_data_for_endpoint_page(String task,
	// DataTable table) throws Throwable {
	// List<String> lstData = table.asList(String.class);
	// switch (task.toUpperCase()) {
	// case "CREATE":
	// for (String userName : lstData) {
	// ManageTestData objDelTestdata = new ManageTestData();
	// Assert.assertTrue("Error occured while Inserting test data for enduser data
	// table",
	// objDelTestdata.manageTestData("Site_Tenant_User_Insert", userName));
	// }
	// break;
	// case "DELETE":
	// for (String userName : lstData) {
	// ManageTestData objDelTestdata = new ManageTestData();
	// Assert.assertTrue("Error occured while deleting test data for enduser data
	// table",
	// objDelTestdata.manageTestData("Site_Tenant_User_Delete", userName));
	// }
	//
	// break;
	// }
	// }
	//
	// // @Then("^enter the \"(.*?)\" in \"(.*?)\" field on add tenant popup$")
	// // public void inter_the_text_in_field_on_add_tenant_popup(String task,
	// String fieldName) {
	// // String message = "Failed to enter the value for Fields:" + fieldName;
	// // Assert.assertTrue(message, tenantPage.enterNameAndParentType(task,
	// fieldName));
	// // }
	//
	//
	// @Given("^we are on tenant page and search tenant field is enabled$")
	// public void we_are_on_tenant_page_and_search_tenant_field_is_enabled()
	// throws Throwable {
	// BaseUtil.waitForSpinner();
	// BaseUtil.clickAndWait(getProps().getProperty("tenantTab"));
	// BaseUtil.waitForSpinner();
	// String message = "Failed to verify tenant grid availablity. ";
	// BaseUtil.assertElementDisplayed(getProps().getProperty("optionDropDown"),
	// message);
	// BaseUtil.assertElementDisplayed(getProps().getProperty("tenantDetailGrid"),
	// message);
	// }
	//
	//
	// @When("^the \"(.*?)\" popup is show$")
	// public void the_popup_is_show(String sPopup) throws Throwable {
	// try {
	// if (sPopup.equals("Template")) {
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("templateWindow"));
	// } else if (sPopup.equals("Upload Template")) {
	// BaseUtil.verifyElementDisplayed(getProps().getProperty("uploadTemplateWindow"));
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// @When("^verify that all Sites get selected and display as \"(.*?)\" with
	// Tenant name on right side of Permission Accordion on edit user$")
	// public void
	// verify_that_all_Sites_get_selected_and_display_as_with_Tenant_name_on_right_side_of_Permission_Accordion(
	// String markingType) throws Throwable {
	// Assert.assertTrue(markingType + "Either all sites are not selected or " +
	// markingType + " is not displayed",
	// userPage.verifyUserSectionDisplayed(markingType, userPageObjectRepo));
	// }
	//
	// @Then("^enter the values for new endpoint for tenant verification$")
	// public void enter_the_values_for_new_endpoint() throws Throwable {
	// String message = "Failed to create new endpoint";
	// Assert.assertTrue(message,
	// tenantPage.enterValuesEndpointCreate(getProps().getProperty,
	// tenantPageObjectTestData));
	// }
	//
	// @Then("^user should be navigated to endpoint page with a text saying
	// \"(.*?)\"")
	// public void
	// user_should_be_navigated_to_endpoint_page_with_a_label_saying(String
	// endpointSaveMsg) throws Throwable {
	// BaseUtil.waitForSpinner();
	// BaseUtil.assertElementDisplayed(getProps().getProperty("addSiteSaveMsg"),
	// "Message is not visible on the page");
	// String message = "Expected message not matched with actual message.";
	// Assert.assertTrue(message, endpointSaveMsg
	// .equalsIgnoreCase(BaseUtil.getElementText(getProps().getProperty("addSiteSaveMsg"))));
	// BaseUtil.waitForSpinner();
	// BaseUtil.explicitWait(2000);
	// }
	//
	// @When("^enter endpoint id \"(.*?)\" in search text box on the Endpoint
	// page$")
	// public void
	// enter_endpoint_id_in_search_text_box_on_the_Endpoint_list_page(String
	// endpointName) throws Throwable {
	// endpointPage.enterText_endpointPage("ENDPOINT_SEARCH", endpointName,
	// endpointPageObjectRepo);
	// endpointPage.waitForSpinner();
	// }
	//
	// @Given("^delete the following test data before creating new test data$")
	// public void manageTestData(List<String> lstTestData) throws SQLException,
	// IOException {
	// ManageTestData objDelTestdata = new ManageTestData();
	// for (String tenantName : lstTestData) {
	// if (tenantName.equalsIgnoreCase("tenantPage_tenantBot1")
	// ||tenantName.equalsIgnoreCase("sitePage_TenantSiteBot1")
	// || tenantName.equalsIgnoreCase("pTenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("cPTenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("p_TenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("cP_TenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("c_TenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("p_pTenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("c_pTenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("cP_pTenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("p_cPTenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("c_cPTenantPage_tenantBot1")
	// || tenantName.equalsIgnoreCase("cP_cPTenantPag_tenantBot1")) {
	// objDelTestdata.manageTestData("Tenant", tenantName);
	// }
	// }
	// }
	//
	// @Then("^click on \"(.*?)\" button on endpoint detail page$")
	// public void click_on_the_button_on_endpoint_page(String buttonName)
	// throws Throwable {
	// endpointPage.assertElementDisplayed(endpointPageObjectRepo("addEndpointButton"),
	// "Failed to verify endpoint grid availability.");
	// String message = "Failed to click on button :" + buttonName;
	// Assert.assertTrue(message,
	// endpointPage.clickOperationOnEndpointPage(buttonName,
	// endpointPageObjectRepo));
	// endpointPage.explicitWait(4000);
	// }
	//
	// @Then("^select the entered endpoint id \"(.*?)\" from the endpoint grid$")
	// public void
	// select_the_entered_endpoint_id_from_the_endpoint_details_grid(String
	// sEndpointName) {
	// endpointPage.selectEndpointFromGrid_EndpointPage(sEndpointName,
	// endpointPageObjectRepo);
	// endpointPage.explicitWait(3000);
	// endpointPage.checkAlert();
	// }
	// @When("^user should modify the following fields$")
	// public void user_modify_the_following_fields(List<String> sFields) throws
	// Throwable {
	// for (String fields : sFields) {
	// String message = "Failed to modify endpoint fields";
	// Assert.assertTrue(message,
	// endpointPage.modify_SecondEndpoint(fields, endpointPageObjectRepo,
	// endpointPageObjectTestData));
	// }
	// }
	//
	// public boolean modify_SecondEndpoint(String fields, Map<String, String>
	// endpointPageObjectRepo,
	// Map<String, String> endpointPageObjectTestData) {
	// try {
	// switch (fields.toUpperCase()) {
	// case "TYPE":
	// endpointPage.selectDropDownByValue(endpointPageObjectRepo("modifyEndpointTypeDropDown"),
	// endpointPageObjectTestData("modifyEndpointTypeDropDownTestData2"));
	// return true;
	// case "ASSOCIATION TENANT/SITE":
	// endpointPage.instantClick(endpointPageObjectRepo("modifyEndpointTenantDropDown"));
	// endpointPage.explicitWait(1000);
	// endpointPage.selectDropDownByValue(endpointPageObjectRepo("updateEndpointTenantDropDown"),
	// endpointPageObjectTestData("modifyTenantDropDownTestData"));
	// GPConnection gpcon = new GPConnection();
	// GPDataBaseUtil dbutil = new GPDataBaseUtil();
	// Connection conn = gpcon.getConnection(TestBase.getDbUrl(),
	// TestBase.getDbUsername(),
	// TestBase.getDbPassword());
	// String getPremisesIdQuery =
	// "SELECT premises_id FROM premises WHERE name='endpointPage_siteBot2'";
	// logger.info("getPremisesIdQuery : " + getPremisesIdQuery);
	// String premisesId = dbutil.getIdFromDB(getPremisesIdQuery, conn);
	// String premisesName = "endpointPage_siteBot2";
	// String premisesNameWithId = premisesName.concat("/").concat(premisesId);
	// endpointPage.enterText(endpointPageObjectRepo("modifyEndpointAssociateSiteId"),
	// premisesNameWithId);
	// endpointPage.explicitWait(2000);
	// return true;
	// }
	// } catch (Exception e) {
	// logger.error("Failed to modify field " + fields +
	// "in endpoint page. , see detail error message \n"
	// + e.getStackTrace().toString());
	// Assert.fail("Failed to modify field [" + fields +
	// "] in endpoint page. see detail error message \n"
	// + e.getStackTrace().toString());
	// }
	// return false;
	// }
	//
	// @Then("^click the \"(.*?)\" button on the edit endpoint page$")
	// public void click_save_and_close_button_of_endpoint(String buttonName)
	// throws Throwable {
	// String message1 = "Failed to click on button :" + buttonName;
	// Assert.assertTrue(message1,
	// endpointPage.clickOperationOnEndpointPage(buttonName,
	// endpointPageObjectRepo));
	// }
	//
	// @Then("^confirmation box on edit endpoint popup should display with message
	// \"(.*?)\" for endpoint \"(.*?)\"$")
	// public void
	// confirmation_box_on_endpoint_edit_details_popup_should_display_with_message(
	// String confirmationboxMessage, String endpointName) throws Throwable {
	// String message =
	// "Failed to verify text on Java Script popup on edit endpoint page.";
	// Assert.assertTrue(message,
	// endpointPage.verifyTextOnJavaScriptPopupForUncommissionedEndpoint(
	// confirmationboxMessage, endpointName, endpointPageObjectRepo));
	// }
	//
	// @When("^click on \"(.*?)\" button on confirmation box on Edit Endpoint
	// popup$")
	// public void
	// click_on_Cancel_button_on_confirmation_box_on_Edit_Endpoint_Page(String
	// buttonName) throws Throwable {
	// String message = "Failed to click on button :" + buttonName + " " +
	// " on confirmation box.";
	// Assert.assertTrue(message,
	// endpointPage.clickOperationOnConfirmationBox(buttonName,
	// endpointPageObjectRepo));
	// }
	//
	// @When("^verify the message \"(.*?)\" on \"(.*?)\" accordion on edit user
	// page$")
	// public void verify_Message_Accordion_On_Edit_User_page(String
	// accordionMessage, String accordion) throws Throwable {
	// String message = "Failed to verify the message: " + accordionMessage +
	// " on accordion :" + accordion;
	// Assert.assertTrue(message,
	// userPage.verifyAccordionMessage(accordionMessage, accordion,
	// userPageObjectRepo));
	// }
	//
	// @When("^we will login with \"(.*?)\" credential and varify tenant tab$")
	// public void we_will_login_with_credential_and_verify_site_tab(String
	// role, DataTable table) throws Throwable {
	// List<String> lstData = table.asList(String.class);
	// String userName = lstData.get(0);
	// BaseUtil.isTenantTabAvailable(userName);
	// if
	// (!BaseUtil.checkIfClickable(getProps().getProperty("optionDropDown")))
	// {
	// Assert.fail("Not able to login in the portal for the user : " + role);
	// }
	// }
	//
	//
	// @Then("^verify Edit tenant has two columns in SubTenant Table:$")
	// public void
	// verify_edit_tenant_has_two_columns_in_subTenant_tab(List<String>
	// lstTenantTypeFields) throws Throwable {
	// for (String sField : lstTenantTypeFields) {
	// String message = "Failed to display the field[" + sField +
	// "] on edit tenant popup.";
	// Assert.assertTrue(message,
	// tenantPage.isElementDisplayedOnTenantPage(sField));
	// }
	// }
	//
	//
	// // @Then("^select \"(.*?)\" type from tenant type field$")
	// // public void select_type_from_tenant_type_field(String type) {
	// // String message = "Failed to select the value for Field:" + type;
	// // Assert.assertTrue(message,
	// // tenantPage.selectTenantTypeOnAddTenantPopup(type,
	// getProps().getProperty);
	// // }
	//
	// @And("^verify \"(.*?)\" field is disabled$")
	// public void verify_field_is_disabled(String sFieldName) throws Throwable
	// {
	// String message = "Failed to verify that [" + sFieldName +
	// "] is disabled.";
	// Assert.assertFalse(message,
	// tenantPage.isElementEnabledOnTenantPage(sFieldName,
	// getProps().getProperty));
	// }
	//
	// @Then("^user should be navigated to Tenants page with a label saying
	// \"(.*?)\"$")
	// public void
	// user_should_be_navigated_to_Tenants_page_with_a_label_saying_Tenant_tenant_name_saved(
	// String sTenantSaveMsg) throws Throwable {
	// BaseUtil.waitForSpinner();
	// BaseUtil.explicitWait(5000);
	// String message = "Expected message not matched with actual message.";
	// Assert.assertEquals(message, sTenantSaveMsg,
	// BaseUtil.getElementText(getProps().getProperty("addTenantSaveMsg")));
	// }
	//
	//
	//
	// @Then("^delete the \"(.*?)\" test data for tenant page$")
	// public void delete_the_site_used_as_test_data_for_endpoint(String
	// testData, List<String> listSiteTestData)
	// throws Throwable {
	// ManageTestData objDelTestdata = new ManageTestData();
	//
	// if (testData.equalsIgnoreCase("Endpoints")) {
	// for (String endpointName : listSiteTestData) {
	// Assert.assertTrue("Data should be deleted ",
	// objDelTestdata.manageTestData("Endpoint_Delete", endpointName));
	// }
	// } else if (testData.equalsIgnoreCase("Tenants")) {
	// for (String tenantsName : listSiteTestData) {
	// Assert.assertTrue("Data should be deleted ",
	// objDelTestdata.manageTestData("Tenant", tenantsName));
	// }
	// } else if (testData.equalsIgnoreCase("Sites")) {
	// for (String sitesName : listSiteTestData) {
	// Assert.assertTrue("Data should be deleted ",
	// objDelTestdata.manageTestData("Site", sitesName));
	// }
	// }
	//
	// }
	//
	// @Then("^delete the \"(.*?)\" for tenant test data$")
	// public void delete_the_data_used_as_test_data(String tab, List<String>
	// listSiteTestData) throws Throwable {
	// ManageTestData objDelTestdata = new ManageTestData();
	// if (tab.equalsIgnoreCase("Sites")) {
	// for (String sitesName : listSiteTestData) {
	// Assert.assertTrue("Data should be deleted ",
	// objDelTestdata.manageTestData("Site", sitesName));
	// }
	// } else if (tab.equalsIgnoreCase("Tenants")) {
	// for (String tenantsName : listSiteTestData) {
	// Assert.assertTrue("Data should be deleted ",
	// objDelTestdata.manageTestData("Tenant", tenantsName));
	// }
	// }
	// }
	//
	// @When("^verify the tenant \"(.*?)\" on \"(.*?)\" tab$")
	// public void verify_Tenant_on_Various_Tab(String tenantName, String
	// tabName) throws Throwable {
	// String message = "Failed to verify tenant on tab:" + tabName;
	// Assert.assertTrue(message, tenantPage.verifyTenantOnTab(tenantName,
	// tabName));
	// }
	//
	// @When("^click on Logout button on tenant page$")
	// public void click_on_button_tenant_page() throws Throwable {
	// BaseUtil.explicitWait(3000);
	// if
	// (!BaseUtil.checkIfClickable(getProps().getProperty("optionDropDown")))
	// {
	// Assert.fail("Not able to login in the portal.");
	// }
	// BaseUtil.explicitWait(2000);
	// BaseUtil.clickElement(getProps().getProperty("optionDropDown"));
	// BaseUtil.clickElement(getProps().getProperty("logout"));
	//
	// String message = "Error while logging out";
	// BaseUtil.assertElementDisplayed(getProps().getProperty("adminUserName_Locator"),
	// message);
	// }
	//
	// @When("^click on Template button on endpoint Page$")
	// public void click_on_Template_button_on_endpoint_Page() throws Throwable
	// {
	// endpointPage.waitForSpinner();
	// if
	// (!endpointPage.checkIfClickable(getProps().getProperty("optionDropDown")))
	// {
	// Assert.fail("Not able to login in the portal.");
	// }
	// endpointPage.explicitWait(2000);
	// endpointPage.clickElement(getProps().getProperty("optionDropDown"));
	// endpointPage.clickElement(getProps().getProperty("template"));
	//
	// String message = "Error while logging out";
	// endpointPage.assertElementDisplayed(getProps().getProperty("templateWindow"),
	// message);
	// }
	//
	// @Then("^click on \"(.*?)\" button on userpage$")
	// public void click_on_button_on_User_page(String sButtonName) throws
	// Throwable {
	// String message = "Failed to click on button :" + sButtonName + " " +
	// " on User Page";
	// Assert.assertTrue(message, userPage.clickOperationOnUserPage(sButtonName,
	// userPageObjectRepo));
	// userPage.explicitWait(2000);
	// }
	//
	// @When("^click on Hide Inactive checkbox$")
	// public void click_on_Hide_Inactive_checkbox() {
	// BaseUtil.unCheckCheckBox(getProps().getProperty("hideInactiveCheckBox"));
	//
	// }
	//
	// @Then("^add site popup is displayed with following fields:$")
	// public void
	// add_site_popup_is_displayed_with_following_fields(List<String>
	// listAddSiteElements) throws Throwable {
	// for (String elements : listAddSiteElements) {
	// String message = "Failed to display the field[" + elements +
	// "] on add site popup.";
	// Assert.assertTrue(message,
	// tenantPage.isElementDisplayedOnSitePage(elements));
	// }
	// }
	//
	// @Then("^enter the test data for the following fields to create new site$")
	// public void
	// enter_the_test_data_for_the_following_fields_to_create_new_site(DataTable
	// arg1) throws Throwable {
	// List<String> fieldList = arg1.asList(String.class);
	// for (String list : fieldList) {
	// tenantPage.enterTextTenantSitePage(list,
	// tenantPageObjectTestData);
	// }
	// }
	//
	// @When("^click on the \"(.*?)\" button on add site popup$")
	// public void click_on_button_on_add_site_popup(String buttonElement)
	// throws Throwable {
	// if (buttonElement.equalsIgnoreCase("Save")) {
	// BaseUtil.click(getProps().getProperty("addSiteSaveButton"));
	// BaseUtil.waitForSpinner();
	// } else if (buttonElement.equalsIgnoreCase("Cancel")) {
	// BaseUtil.click(getProps().getProperty("addSiteCancelButton"));
	// } else if (buttonElement.equalsIgnoreCase("Accept Verified Data")) {
	// BaseUtil.explicitWait(2000);
	// BaseUtil.click(getProps().getProperty("addSiteAcceptVerifiedDataButton"));
	// } else if (buttonElement.equalsIgnoreCase("Save And Close")) {
	// BaseUtil.click(getProps().getProperty("addSiteSaveAndCloseButton"));
	// }
	// BaseUtil.waitForSpinner();
	// }
	//
	// //
	// @Then("^user should be navigated to tenant page with a label saying
	// \"(.*?)\"$")
	// // public void
	// user_should_be_navigated_to_Site_page_with_a_label_saying(String
	// siteSaveMessage) throws Throwable {
	// // BaseUtil.waitForSpinner();
	// //
	// BaseUtil.assertElementDisplayed(getProps().getProperty("addSiteSaveMsg"),
	// // "Message is not visible on the page");
	// // String message = "Expected message not matched with actual message.";
	// // Assert.assertTrue(message,
	// //
	// siteSaveMessage.equals(BaseUtil.getElementText(getProps().getProperty("addSiteSaveMsg"))));
	// // }
	//
	// //
	// @Then("^Verify the presence of \"(.*?)\" button on Tenants Detail page$")
	// // public void
	// verify_the_presence_of_button_on_Tenants_Detail_page(String arg1) throws
	// Throwable {
	// // Assert.assertTrue(arg1 + " button is available on edit tenant popup",
	// // tenantPage.isElementDisplayedOnTenantPage(arg1,
	// getProps().getProperty));
	// // }
	//
	//
	// // @When("^enter the tenant name \"(.*?)\" in search field$")
	// // public void enter_the_root_tenant_in_search_field(String
	// sRootTenantName) throws Throwable {
	// // String message =
	// "Failed to enter the value in the [Search] field in tenant detail grid.";
	// // Assert.assertTrue(message,
	// // tenantPage.enterText_TenantPage("TENANT_SEARCH", sRootTenantName,
	// getProps().getProperty));
	// // }
	//
	//
	@Then("click on Export icon on Tenant tab$")
	public void click_on_Export_icon_on_tenant_tab() throws Throwable {
		BaseUtil.waitForSpinner();
		if (!BaseUtil.checkIfClickable(getProps().getProperty("exportIcon"))) {
			Assert.fail("Not able to click on export icon.");
		}
		BaseUtil.clickAndWait(getProps().getProperty("exportIcon"));

		String message = "Error while downloading a report";
		BaseUtil.assertElementDisplayed(getProps().getProperty("tenantTab"), message);
	}

	@Then("^verify the \"(.*?)\" on tenant detail popup for selected tenant \"(.*?)\"$")
	public void verify_site_count_on_tenant_details_grid(String elementName, String tenantName) throws Throwable {
		ManageTestData objDelTestdata = new ManageTestData();
		int siteCount[] = objDelTestdata.selectSite(tenantName);
		String TSites = BaseUtil.getElementText(getProps().getProperty("editTenantTotalSitesDisplay"));
		String sitenumber = TSites.substring(0, TSites.indexOf("View") - 1);
		int uiSiteCount = Integer.parseInt(sitenumber);
		int totalSiteCount = siteCount[0];
		Assert.assertFalse("Total Site Count is not equal", totalSiteCount == uiSiteCount);
		int totalActiveSiteCount = siteCount[1];
		String totalActiveSites = BaseUtil.getElementText(getProps().getProperty("editTenantActiveSitesDisplay"));
		String activeSiteNumber = totalActiveSites.substring(0, totalActiveSites.indexOf("View") - 1);
		int uiActiveSiteCount = Integer.parseInt(activeSiteNumber);
		Assert.assertFalse("Total Site Count is not equal", totalActiveSiteCount == uiActiveSiteCount);
	}

//	 @Then("^verify the values for newly created tenant \"(.*?)\"$")
//	 public void verify_the_values_for_newly_created_tenant(String
//	sTenantName)throws Throwable

//	{
	 // try {
	 // String message = "Failed to verify the values for " + sTenantName;
	 // Assert.assertTrue(message, tenantPage.verifyFieldsValue(sTenantName,
//	 getProps().getProperty));
	 // } catch (Exception e) {
	 // Assert.fail("Failed to Navigate on the following Page");
	 // } finally {
	 // BaseUtil.waitForSpinner();
	 // }
	 // }
	
	 @Then("^verify pagination should be displayed if \"(.*?)\" section have data$")
	 public void verify_pagination_should_be_displayed_if_section_have_data(String tenantSection) throws Throwable {
	 try {
	 boolean nextFlg, lastFlg, prevFlg, firstFlg;
	 switch (tenantSection.toUpperCase()) {
	 case "SITES":
	 nextFlg =
	 BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantPaginationNext"));
	 lastFlg =
	 BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantPaginationPrev"));
	 prevFlg =
	 BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantPaginationLast"));
	 firstFlg =
	 BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantPaginationFirst"));
	 if (!(nextFlg && lastFlg && prevFlg && firstFlg)) {
	 Assert.fail("Pagination buttons are not visible");
	 }
	 break;
	 case "SUBTENANTS":
	 nextFlg =
	 BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantPaginationNext"));
	 lastFlg =
	 BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantPaginationPrev"));
	 prevFlg =
	 BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantPaginationLast"));
	 firstFlg =
	 BaseUtil.verifyElementDisplayed(getProps().getProperty("tenantPaginationFirst"));
	 if (!(nextFlg && lastFlg && prevFlg && firstFlg)) {
	 Assert.fail("Pagination buttons are not visible");
	 }
	 }
	 } catch (Exception e) {
	 Assert.fail("Failed to Navigate on the following Page");
	 } finally {
	 BaseUtil.waitForSpinner();
	 }
	 }

	@When("^select the site \"(.*?)\" sites in the sites section$")
	public void select_the_site_field_sites_in_the_sites_section(String siteName) throws Throwable {
		String message = "Site [" + siteName + "] is not found in site section of tenant detail grid.";
		Assert.assertTrue(message, tenantPage.selectSiteTenantFromGrid_TenantPage(siteName));
	}

	@Then("^user should be navigated to Site detail popup$")
	public void user_should_be_navigated_to_Site_detail_popup() throws Throwable {
		String message = "Failed to navigate on site detail popup.";
		Assert.assertTrue(message, BaseUtil.verifyElementDisplayed(getProps().getProperty("siteDetailPopup")));
	}

	

	@Then("^valid error message \"(.*?)\" should be displayed for tenant \"(.*?)\" field$")
	public void valid_error_message_should_be_displayed_for_existing_tenantname(String sErrorMsg, String sFieldName)
			throws Throwable {
		String message = "Failed to verify expected error message[" + sErrorMsg + "] for field [" + sFieldName
				+ "] on add tenant popup";
		Assert.assertTrue(message,
				tenantPage.verifyErrorMessage_TenantPage(sErrorMsg, sFieldName));
	}

	@Then("^verify that \"(.*?)\" column has been removed$")
	public void verify_that_column_has_been_removed(String columnName) throws Throwable {
		String message = columnName + " coulumn is available.";
		Assert.assertFalse(message, tenantPage.isElementDisplayedOnTenantPage(columnName));
	}

	
	public void click_on_string(String sLinkName) throws Throwable {
		BaseUtil.explicitWait(3000);
		tenantPage.clickTenantGridColumnName(sLinkName);
	}

}
