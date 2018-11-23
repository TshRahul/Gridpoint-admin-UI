package com.gridpoint.adminui.automation.endpoint;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;

import com.gargoylesoftware.htmlunit.javascript.host.Element;
import com.gridpoint.adminui.automation.runner.InitializeResources;
import com.gridpoint.adminui.automation.site.SitePage;
import com.gridpoint.adminui.automation.tenant.TenantPage;
import com.gridpoint.adminui.automation.testdata.ManageTestData;
import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.database.GPConnection;
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

public class EndpointPageTest extends CommonInit {
	private EndpointPage endpointPage = new EndpointPage();
	private TenantPage tenantPage = new TenantPage();
	private SitePage sitePage = new SitePage();

	String total;
	String endpointSearchName;

	private Logger logger = Logger.getLogger(EndpointPageTest.class);

	@Given("^Initialize resources in the beginning of each scenario for endpoint Page$")
	public void Initialize_resources_in_the_beginning_of_each_scenario_for_endpoint_Page() throws Throwable {
		BaseUtil.overrideTimeout(getProps().getProperty("endpointTimeout"));
		BaseUtil.overridePollingfrequency(getProps().getProperty("endpointPollingFrequency"));
		BaseUtil.waitForSpinner();
		
	}
	
	@Then("^delete the \"(.*?)\" test data$")
	public void delete_the_site_used_as_test_data_for_endpoint(String testData, List<String> listSiteTestData)
			throws Throwable {
		ManageTestData objDelTestdata = new ManageTestData();

		if (testData.equalsIgnoreCase("Endpoints")) {
			for (String endpointName : listSiteTestData) {
				Assert.assertTrue("Data should be deleted ",
						objDelTestdata.manageTestData("Endpoint_Delete", endpointName));
			}
		} else if (testData.equalsIgnoreCase("Tenants")) {
			for (String tenantsName : listSiteTestData) {
				Assert.assertTrue("Data should be deleted ", objDelTestdata.manageTestData("Tenant", tenantsName));
			}
		} else if (testData.equalsIgnoreCase("Sites")) {
			for (String sitesName : listSiteTestData) {
				Assert.assertTrue("Data should be deleted ", objDelTestdata.manageTestData("Site", sitesName));
			}
		}

	}

	@Then("^User is already logged in to application$")
	public void User_is_already_logged_in_to_application()
	{
		if (logger.isInfoEnabled()) {
			logger.debug("Initialize resources in the beginning of each scenario");
		}

		InitializeResources initilizeresources =new InitializeResources();
		initilizeresources.loadConfigFiles();
		
		BaseUtil.overridePollingfrequency(""
				+ PathConstants.defaultPollingFrequency);
		BaseUtil.overrideTimeout("" + PathConstants.defaultTimeout);
			TestBase.getInstance();
			new CommonInit();
			
		} 
		
	@Then("^initialize dummydata for \"(.*?)\"$")
	public void initialize_dummy_data_in_database(String testName) throws Throwable {
		ManageTestData objDelTestdata = new ManageTestData();
		Assert.assertTrue("Error occured while deleting datafix page test data",
				objDelTestdata.manageTestData("Datafix_Delete", testName));
		Assert.assertTrue("Error occured while inserting datafix page test data",
				objDelTestdata.manageTestData("Datafix_Insert", testName));
	}

	@Then("^verify the following columns in endpoint details grid$")
	public void verify_the_following_columns_in_endpoint_details_grid(DataTable arg1) throws Throwable {
		List<String> lstData = arg1.asList(String.class);
		arg1.asList(String.class);
		boolean isVisible = true;
		for (String header : lstData) {
			boolean visible = false;
			if (header.trim().equals("Endpoint ID")) {
				visible = BaseUtil.verifyElementDisplayed(getProps().getProperty("endpointPageEndpointIdHeader"));
			}
			if (header.trim().equals("Connection Status")) {
				visible = BaseUtil.verifyElementDisplayed(getProps().getProperty("endpointPageConnectionStatusHeader"));
			}
			if (header.trim().equals("Type")) {
				visible = BaseUtil.verifyElementDisplayed(getProps().getProperty("endpointPageTypeHeader"));
			}
			if (header.trim().equals("Serial")) {
				visible = BaseUtil.verifyElementDisplayed(getProps().getProperty("endpointPageSerialHeader"));
			}
			if (header.trim().equals("Reference ID")) {
				visible = BaseUtil.verifyElementDisplayed(getProps().getProperty("endpointPageReferenceIDHeader"));
			}
			if (header.trim().equals("Live Firmware")) {
				visible = BaseUtil.verifyElementDisplayed(getProps().getProperty("endpointPageLiveFirmwareHeader"));
			}
			if (header.trim().equals("Last Message Received (UTC)")) {
				visible = BaseUtil
						.verifyElementDisplayed(getProps().getProperty("endpointPageLastMessageRecievedUTCHeader"));
			}

			// break if any column is not visible
			if (visible == false) {
				isVisible = false;
				Assert.assertTrue("Header [" + header + "] is not visible in User Grid", isVisible);
				break;
			}
		}
	}

	@Then("^verify the sorting functionality of columns in endpoint details grid$")
	public void verify_the_sorting_functionality_of_columns_in_endpoint_details_grid(DataTable args) throws Throwable {
		List<String> lstData = args.asList(String.class);
		for (String header : lstData) {
			endpointPage.verifySorting_EndpointPage("Descending", header);
			endpointPage.verifySorting_EndpointPage("Ascending", header);
		}
	}

	@When("^click on \"(.*?)\" button on the site page$")
	public void click_button(String buttonName) throws Throwable {
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, sitePage.clickOperationOnSitePage(buttonName));
	}

	@Then("^enter test data for the following fields to create site \"(.*?)\"$")
	public void enter_the_test_data_for_the_following_fields_to_create_site(String siteNumber, DataTable table)
			throws Throwable {
		BaseUtil.explicitWait(4000);
		List<String> listElements = table.asList(String.class);
		for (String field : listElements) {
			endpointPage.enterTestData(siteNumber, field);
		}
	}

	@Then("^the \"(.*?)\" name \"(.*?)\" from parent selection box on Add Site box$")
	public void select_the_text_in_field_on_add_tenant_popup(String task, String fieldName) {
		String message = "Failed to enter the value for Fields:" + fieldName;
		Assert.assertTrue(message, sitePage.enterText_EndpointPage(task, fieldName));
	}

	@Then("^enter valid values for the site$")
	public void enter_existing_name_as(DataTable table) throws Throwable {
		// sitePage.explicitWait(4000);
		List<String> listElements = table.asList(String.class);
		for (String field : listElements) {
			endpointPage.enterSiteTestData(field);
		}
	}

	@When("^click on \"(.*?)\" button of add site popup$")
	public void click_on_button_on_add_site_popup(String buttonElement) throws Throwable {
		if (buttonElement.equalsIgnoreCase("Save")) {
			BaseUtil.explicitWait(2000);
			BaseUtil.click(getProps().getProperty("addSiteSaveButton"));
		} else if (buttonElement.equalsIgnoreCase("Cancel")) {
			BaseUtil.click(getProps().getProperty("addSiteCancelButton"));
		} else if (buttonElement.equalsIgnoreCase("Accept Verified Data")) {
			BaseUtil.click(getProps().getProperty("addSiteAcceptVerifiedDataButton"));
		}
		BaseUtil.waitForSpinner();
	}

	@Then("^user should be navigated to the sites page with a label saying \"(.*?)\"$")
	public void user_should_be_navigated_to_sites_page_with_a_label_saying(String siteSaveMessage) throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.assertElementDisplayed(getProps().getProperty("addSiteSaveMsg"), "Message is not visible on the page");
		String message = "Expected message not matched with actual message.";
		Assert.assertTrue(message,
				siteSaveMessage.equals(BaseUtil.getElementText(getProps().getProperty("addSiteSaveMsg"))));
	}

	@Given("^delete the following tenant test data before creating new test data$")
	public void manageTestData(List<String> lstTestData) throws SQLException, IOException {
		ManageTestData objDelTestdata = new ManageTestData();
		for (String tenantName : lstTestData) {
			if (tenantName.equalsIgnoreCase("tenantPage_tenantBot1")
					|| tenantName.equalsIgnoreCase("SubTenant_tenantBot1")) {
				objDelTestdata.manageTestData("Tenant", tenantName);
			} else if (tenantName.equalsIgnoreCase("sitePage_TenantSiteBot1")) {
				objDelTestdata.manageTestData("Site", tenantName);
			}
		}
	}

	@Given("^we are on tenant tab and search tenant field is enabled$")
	public void we_are_on_tenant_page_and_search_tenant_field_is_enabled() throws Throwable {
		BaseUtil.clickAndWait(getProps().getProperty("tenantTab"));
		BaseUtil.waitForSpinner();
		String message = "Failed to verify tenant grid availablity.  ";
		BaseUtil.assertElementDisplayed(getProps().getProperty("optionDropDown"), message);
		BaseUtil.assertElementDisplayed(getProps().getProperty("tenantDetailGrid"), message);
	}

	@When("^click on the \"(.*?)\" button$")
	public void click_on_add_button(String sButtonName) throws Throwable {
		String message = "Failed to click on button :" + sButtonName + " " + " on tenant detail grid.";
		Assert.assertTrue(message, tenantPage.clickOperationOnTenantPage(sButtonName));
		// tenantPage.explicitWait(2000);
	}

	@Then("^click on the \"(.*?)\" Tab$")
	public void click_on_endpoint_tab(String sButtonName) throws Throwable {

		if (sButtonName.equalsIgnoreCase("Tenants")) {
			BaseUtil.click(getProps().getProperty("tenantTab"));
		} else if (sButtonName.equalsIgnoreCase("Endpoints")) {
			BaseUtil.click(getProps().getProperty("endpointTab"));
		}
	}

	// @Then("^enter the \"(.*?)\" in the \"(.*?)\" field on add tenant popup$")
	// public void inter_the_text_in_field_on_add_tenant_popup(String task,
	// String fieldName) {
	// String message = "Failed to enter the value for Fields:" + fieldName;
	// Assert.assertTrue(message, tenantPage.enterText_TenantPage(task,
	// fieldName, tenantPageObjectRepo));
	// }

	// @Then("^enter the \"(.*?)\" like \"(.*?)\" on add tenant popup$")
	// public void inter_the_text_field_on_add_tenant_popup(String task, String
	// fieldName) {
	// String message = "Failed to enter the value for Fields:" + fieldName;
	// Assert.assertTrue(message, tenantPage.enterText_TenantPage(task,
	// fieldName, tenantPageObjectRepo));
	// }

	@Given("^we launch the browser with admin-UI URL and login with valid admin credentials for endpoint page$")
	public void we_launch_the_browser_with_admin_UI_URL_and_login_with_valid_admin_credentials_endpoint_page()
			throws Throwable {
		String message = "Unsuccessful login with valid credentials";
		BaseUtil.waitForSpinner();
		BaseUtil.assertElementDisplayed(getProps().getProperty("optionDropDown"), message);
	}

	@When("^we will land on Site Page, click on endpoint tab$")
	public void we_will_land_on_site_Page_we_will_click_on_endpoint_tab() {
		BaseUtil.waitForSpinner();
		BaseUtil.click(getProps().getProperty("endpointTab"));
	}

	@Then("^\"(.*?)\" various endpoints as test data for endpoint page$")
	public void various_User_as_test_data_for_endpoint_page(String task, DataTable table) throws Throwable {
		List<String> lstData = table.asList(String.class);
		switch (task.toUpperCase()) {
		case "CREATE":
			for (String userName : lstData) {
				ManageTestData objDelTestdata = new ManageTestData();
				Assert.assertTrue("Error occured while Inserting test data for enduser data table",
						objDelTestdata.manageTestData("Endpoint_Insert", userName));
			}
			break;
		case "DELETE":
			for (String userName : lstData) {
				ManageTestData objDelTestdata = new ManageTestData();
				Assert.assertTrue("Error occured while deleting test data for enduser data table",
						objDelTestdata.manageTestData("Endpoint_User_Delete", userName));
			}

			break;
		}
	}

	@When("^verify the Endpoint page by confirming the availability of endpoint grid$")
	public void verify_the_Endpoint_page_by_confirming_the_availability_of_endpoint_grid() throws Throwable {
		String message = "Failed to verify the availability of endpoint grid";
		BaseUtil.assertElementDisplayed(getProps().getProperty("endpointGrid"), message);
	}

	@Given("^we are on endpoint page and search endpoint field is enabled$")
	public void we_are_on_endpoint_page_and_search_endpoint_field_is_enabled() throws Throwable {
		BaseUtil.clickAndWait(getProps().getProperty("endpointTab"));
		BaseUtil.explicitWait(9000);
		// BaseUtil.waitForSpinner();
		// String message = "Failed to verify endpoint grid availability.";
		// BaseUtil.isElementDisplayed(getProps().getProperty("optionDropDown"),
		// message);
		BaseUtil.verifyElementDisplayed(getProps().getProperty("endpointGrid"));
	}

	@Then("^click on endpoint page and verify we are on endpoint page$")
	public void click_on_endpoint_page() {
		BaseUtil.clickAndWait(getProps().getProperty("endpointTab"));
		BaseUtil.explicitWait(9000);
		// BaseUtil.waitForSpinner();
		// String message = "Failed to verify endpoint grid availability.";
		// BaseUtil.isElementDisplayed(getProps().getProperty("optionDropDown"),
		// message);
		BaseUtil.verifyElementDisplayed(getProps().getProperty("endpointGrid"));
	}

	@When("^enter endpoint id \"(.*?)\" in search text box on the Endpoint list page$")
	public void enter_endpoint_id_in_search_text_box_on_the_Endpoint_list_page(String endpointName) throws Throwable {
		endpointPage.enterText_endpointPage("ENDPOINT_SEARCH", endpointName);
		BaseUtil.explicitWait(3000);
	}

	@And("^click on Logout button on endpoint page$")
	public void click_on_button() throws Throwable {
		BaseUtil.waitForSpinner();
		if (!BaseUtil.checkIfClickable(getProps().getProperty("optionDropDown"))) {
			Assert.fail("Not able to login in the portal.");
		}
		BaseUtil.explicitWait(2000);
		BaseUtil.clickElement(getProps().getProperty("optionDropDown"));
		BaseUtil.clickElement(getProps().getProperty("logout"));

		String message = "Error while logging out";
		BaseUtil.assertElementDisplayed(getProps().getProperty("adminUserName_Locator"), message);
	}

	@When("^we will login with \"(.*?)\" credential and varify endpoint tab$")
	public void we_will_login_with_credential_and_verify_dashboard_tab(String role, DataTable table) throws Throwable {
		List<String> lstData = table.asList(String.class);
		String userName = lstData.get(0);
		endpointPage.isEndpointTabAvailable(userName);
		if (!BaseUtil.checkIfClickable(getProps().getProperty("optionDropDown"))) {
			Assert.fail("Not able to login in the portal for the user : " + role);
		}
	}

	@Then("^user should be navigated to endpoint page with a label saying \"(.*?)\"")
	public void user_should_be_navigated_to_endpoint_page_with_a_label_saying(String endpointSaveMsg) throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.assertElementDisplayed(getProps().getProperty("addEndpointSaveMsg"),
				"Message is not visible on the page");
		String message = "Expected message not matched with actual message.";
		Assert.assertTrue(message, endpointSaveMsg
				.equalsIgnoreCase(BaseUtil.getElementText(getProps().getProperty("addEndpointSaveMsg"))));
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(2000);
	}

	@Then("^click on \"(.*?)\" for endpoint page$")
	public void click_on_for_endpoint_page(String sElement) throws Throwable {
		String message = "Failed to click tab" + sElement;
		Assert.assertTrue(message, endpointPage.clickOperationOnEndpointpage(sElement));
	}

	@Then("^User should be navigated to sites page with a label saying \"(.*?)\"$")
	public void User_should_be_navigated_to_sites_page_with_a_label_saying(String siteSaveMessage) throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.assertElementDisplayed(getProps().getProperty("addSiteSaveMsg"), "Message is not visible on the page");
		String message = "Expected message not matched with actual message.";
		Assert.assertTrue(message,
				siteSaveMessage.equals(BaseUtil.getElementText(getProps().getProperty("addSiteSaveMsg"))));
	}

	@Then("^add tenant popup should be displayed with following fields for endpoint page:$")
	public void add_tenant_popup_should_be_displayed_with_name_parent_and_hostname_fields(
			List<String> lstAddTenantFields) throws Throwable {
		for (String sField : lstAddTenantFields) {
			String message = "Failed to display the field[" + sField + "] on add tenant popup.";
			Assert.assertTrue(message, endpointPage.isElementDisplayedOnTenantPageEndpointPage(sField));
		}
	}

	@When("^enter the value for the following$")
	public void enter_the_value_for_the_following(DataTable table) throws Throwable {
		List<Map<String, String>> lstData = table.asMaps(String.class, String.class);
		String parentName = lstData.get(0).get("Parent").trim();
		String hostName = lstData.get(0).get("Hostname").trim();
		String tenantName = lstData.get(0).get("Name").trim();
		String message = "Failed to Enter the value in field [Name] on add tenant popup.";
		Assert.assertTrue(message, endpointPage.enterText_EndpointTenant("Name", tenantName));
		message = "Failed to Enter the value in field [Hostname] on add tenant popup.";
		Assert.assertTrue(message, endpointPage.enterText_EndpointTenant("Hostname", hostName));
		message = "Failed to Enter the value in field [Parent] on add tenant popup.";
		Assert.assertTrue(message, endpointPage.enterText_EndpointTenant("Parent", parentName));
	}

	@When("^check the 'Inherit from parent tenant' Checkbox against Hostname$")
	public void check_the_Inherit_from_parent_tenant_Checkbox_against_Hostname() throws Throwable {
		BaseUtil.checkCheckBox(getProps().getProperty("addTenantPopupInheritCheckBox"));
	}

	@Then("^a valid hostname should be displayed as per the parent Selected$")
	public void a_valid_hostname_should_be_displayed_as_per_the_parent_Selected(DataTable table) throws Throwable {
		List<Map<String, String>> lstData = table.asMaps(String.class, String.class);
		String message = "Failed to display valid hostname[" + lstData.get(0).get("Hostname").trim()
				+ "] as per selected parent[" + lstData.get(0).get("Parent").trim() + "].";
		Assert.assertTrue(message,
				endpointPage.verifyText_EndpointTenantPage("Hostname", lstData.get(0).get("Hostname").trim()));
	}

	@And("^Verify \"(.*?)\" field is disabled$")
	public void Verify_field_is_disabled(String sFieldName) throws Throwable {
		String message = "Failed to verify that [" + sFieldName + "] is disabled.";
		Assert.assertFalse(message, endpointPage.isElementEnabledOnTenantPage(sFieldName));
	}

	@Then("^User should be navigated to Tenants page with a label saying \"(.*?)\"$")
	public void User_should_be_navigated_to_Tenants_page_with_a_label_saying_Tenant_tenant_name_saved(
			String sTenantSaveMsg) throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(5000);
		String message = "Expected message not matched with actual message.";
		Assert.assertEquals(message, sTenantSaveMsg,
				BaseUtil.getElementText(getProps().getProperty("addTenantSaveMsg")));
	}

	@Then("^select the entered endpoint id \"(.*?)\" from the endpoint details grid$")
	public void select_the_entered_endpoint_id_from_the_endpoint_details_grid(String sEndpointName) {
		endpointPage.selectEndpointFromGrid_EndpointPage(sEndpointName);
		BaseUtil.waitForSpinner(8000);
		endpointPage.checkAlert();
	}

	// GPUP-29949: Verify Save and Close button on edit endpoint model.
	@Then("^verify following button should be enabled on edit endpoint popup$")
	public void verify_following_button_should_be_enabled_on_edit_endpoint_popup(DataTable buttons) throws Throwable {
		List<String> lstData = buttons.asList(String.class);
		buttons.asList(String.class);
		for (String button : lstData) {
			String message = button + " button should be displayed on edit endpoint page ";
			Assert.assertTrue(message, endpointPage.isElementDisplayedOnEndpointPage(button));
		}
	}

	// GPUP-29949: Verify Save and Close button on edit site model.
	@Then("^click \"(.*?)\" button on edit endpoint model and verify message \"(.*?)\" on the same$")
	public void click_button_on_edit_endpoint_model_and_verify_message_on_the_same(String saveButton, String message)
			throws Throwable {
		endpointPage.clickOperationOnEndpointPage(saveButton);
		Assert.assertTrue(message + ": is not showing on click save button of site detail page",
				(BaseUtil.getElementText(getProps().getProperty("editendpointSuccessMsg")).equalsIgnoreCase(message)));
	}

	@Then("^verify the \"(.*?)\" checkbox value on the basis of last saved$")
	public void verify_the_Monitoring_checkbox_value_on_the_basis_of_last_saved(String checkBox) {
		if (checkBox.trim().equals("Monitoring")) {
			BaseUtil.verifyCheckBoxIsSelected(getProps().getProperty("endpointDetailMonitoringCheckbox"));
		}
	}

	@Then("^verify Peripheral accordion should not display for legacy sites$")
	public void verify_Peripheral_ccordion_should_not_display_for_legacy_sites() {

		Assert.assertFalse("Peripheral Accordion is appear for legaacy sites.",
				BaseUtil.instantElementCheck("Display", getProps().getProperty("peripheralsAccordion")));
	}

	@Then("^user click on the \"(.*?)\" tab$")
	public void user_will_be_navigated_to_page(String tabName) throws Throwable {
		endpointPage.clickOperationOnEndpointPage(tabName);
		BaseUtil.waitForSpinner();
	}

	@And("^verify the selected endpoint details with following labels in endpoint details page$")
	public void compare_following_with_endpoint_detail_page(List<String> elementList) throws Throwable {
		GPConnection gpcon = new GPConnection();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = gpcon.getConnection(TestBase.getDbUrl(), TestBase.getDbUsername(), TestBase.getDbPassword());
		String query = "SELECT endpoint.reference_id, premises.name, endpointType.name, endpoint.password "
				+ "FROM endpoint endpoint, premises premises, endpoint_type endpointType WHERE "
				+ "endpoint.premises_id=premises.premises_id AND endpoint.endpoint_type_id=endpointType.endpoint_type_id "
				+ "AND endpoint.serial='endpointPage_endpointBot1'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		rs.next();
		for (String element : elementList) {
			String message = "Failed to compare element [" + element + "] on endpoint details page";
			Assert.assertTrue(message, endpointPage.isEndpointsDetailsDisplayed(element, rs));
		}

		conn.close();
		conn = null;
		stmt = null;
		rs = null;
	}

	@And("^verify that new endpoint is associated to \"(.*?)\" tenant and \"(.*?)\" site$")
	public void verify_that_new_endpoint_is_associated_to_default_values(String defaultTenant, String defaultSite) {
		Assert.assertTrue("Endpoint is not associated to default tenant", defaultTenant
				.equals(BaseUtil.getElementText(getProps().getProperty("endpointDetailsSiteInformationTenantValue"))));
		Assert.assertTrue("Endpoint is not associated to default Site", defaultSite.equals(
				BaseUtil.getElementText(getProps().getProperty("endpointDetailsSiteInformationSiteNameValue"))));
	}

	@And("^verify the endpoint type for \"(.*?)\" is \"(.*?)\"$")
	public void verify_the_endpoint_type_is_EC100(String endpoint, String endpointType) {
		String endpointTypeCheck = BaseUtil
				.getElementText(getProps().getProperty("endpointDetailsSiteInformationTenantValue"));
		boolean flag = endpointType.equals(endpointTypeCheck);
		if (flag) {
			Assert.assertTrue("The endpoint type for " + endpoint + " is = " + endpointTypeCheck, flag);
		}
	}

	@Then("^verify the visibility of the attributes in site information section on edit endpoints popup$")
	public void verify_the_visibility_of_the_attributes_in_site_information_section_on_edit_endpoints_popup(
			List<String> attributeList) throws Throwable {
		for (String attribute : attributeList) {
			String message = "Failed to verify element [" + attribute
					+ "]in site information section on edit endpoints popup";
			Assert.assertTrue(message, endpointPage.isElementEnabledSiteSectionEditEndpointPopup(attribute));
		}
	}

	@Then("^Verify user should be able to change the tenant type for the newly created \"(.*?)\" type endpoint$")
	public void Verify_user_should_be_able_to_change_the_tenant_type_for_the_newly_created_Tridium_type_endpoint(
			String tridiumEndpoint) throws Throwable {
		endpointPage.enterText_endpointPage("ENDPOINT_SEARCH", tridiumEndpoint);
		BaseUtil.explicitWait(3000);
		endpointPage.clickOperationOnEndpointPage("Search");
		endpointPage.selectEndpointFromGrid_EndpointPage(tridiumEndpoint);
		BaseUtil.waitForSpinner(8000);
		endpointPage.checkAlert();
		BaseUtil.selectDropdownElement(getProps().getProperty("modifyEndpointTypeDropDown"), "EC100");
		// BaseUtil.selectDropDownByValue(getProps().getProperty("modifyEndpointTypeDropDown"),"8");
		endpointPage.clickOperationOnEndpointPage("Save and Close");
		BaseUtil.explicitWait(3000);
		/*
		 * String ec100=BaseUtil.getElementText(getProps().getProperty(
		 * "endpointGridEndpointTypeColumn"));
		 * if(ec100.equalsIgnoreCase("EC100")) {
		 * logger.info("the verification of tenant modified is successfull"); }
		 * else { logger.info("the verification of tenant modified is failed");
		 * }
		 */
	}

	@Then("^change the endpoint type$")
	public void change_the_endpoint_type() {
		BaseUtil.selectDropdownElement(getProps().getProperty("modifyEndpointTypeDropDown"), "Tridium Adapter");
	}

	@Then("^click on \"(.*?)\" button$")
	public void click_on_Edit_Save_button(String saveButton) {
		endpointPage.clickOperationOnEndpointPage(saveButton);
	}

	@Then("^verify that the save button is still enable$")
	public void verify_save_button_is_still_enable() {
		boolean saveButton = BaseUtil.verifyElementDisplayed(getProps().getProperty("editEndpointSaveButton"));
		if (saveButton)
			logger.info("the save button is still displayed");
		else
			logger.info("the save button is not displayed");
		BaseUtil.selectDropdownElement(getProps().getProperty("modifyEndpointTypeDropDown"), "EC100");
		endpointPage.clickOperationOnEndpointPage("Save and Close");

	}

	@Then("^verify \"(.*?)\" is tied to sites$")
	public void verify_Endpoints_are_tied_to_sites(String endpoint) {
		endpointPage.selectEndpointFromGrid_EndpointPage(endpoint);
		BaseUtil.waitForSpinner(8000);
		endpointPage.checkAlert();
		String site = BaseUtil.getElementText(getProps().getProperty("siteAssociatedWithEndpoint"));

		if (site.equalsIgnoreCase("rs_site")) {
			logger.info("endpoint is associated with site");
		} else {
			logger.info("endpoint is not associated with site");
		}

	}

	@Then("^Verify The Graph Icon on editEndpoint$")
	public void Verify_The_Graph_Icon_on() {
		BaseUtil.explicitWait(2000);
		endpointPage.clickOperationOnEndpointPage("Graph");
		BaseUtil.explicitWait(30000);
		BaseUtil.switchToNewBrowserTab(0);
		BaseUtil.clickAndWait(getProps().getProperty("portalHomePage"));
		endpointPage.clickOperationOnEndpointPage("Graph");
		BaseUtil.explicitWait(20000);
		String portalGraph = BaseUtil.getElementText(getProps().getProperty("graphPageOnPortal"));
		String expected = "Graphs Channels";
		Assert.assertEquals("the graph is not verified on the portal page", expected, portalGraph);
		BaseUtil.mouseOver(getProps().getProperty("portalOption"));
		BaseUtil.clickAndWait(getProps().getProperty("portalOptionAdmin"));
		BaseUtil.explicitWait(8000);
		BaseUtil.verifyElementDisplayed(getProps().getProperty("siteSearchField"));

	}

	@Then("^move to endpoint and verify the following columns of table$")
	public void Verify_the_device_table(DataTable arg1) {
		List<String> list = arg1.asList(String.class);
		arg1.asList(String.class);
		boolean isVisible1 = true;
		for (String colName : list) {
			boolean visible1 = false;
			if (colName.trim().equals("Port")) {
				visible1 = BaseUtil.verifyElementDisplayed(getProps().getProperty("tablePort"));
			}
			if (colName.trim().equals("Interface")) {
				visible1 = BaseUtil.verifyElementDisplayed(getProps().getProperty("tableInterface"));
			}
			if (colName.trim().equals("MAC Address")) {
				visible1 = BaseUtil.verifyElementDisplayed(getProps().getProperty("tableMacAddress"));
			}
			if (colName.trim().equals("IP Address")) {
				visible1 = BaseUtil.verifyElementDisplayed(getProps().getProperty("tableIPAddress"));
			}

			if (visible1 == false) {
				isVisible1 = false;
				Assert.assertTrue("Header [" + colName + "] is not visible in User Grid", isVisible1);
				break;
			}
		}
	}

	@Then("^Verify the number if endipoint is connected with more than one device$")
	public void Verify_the_number_if_endpoint_is_connected_with_one_Netport() {
		int deviceCount = endpointPage.getTableRowCount(getProps().getProperty("tableEndpoint"));
		if (deviceCount > 1) {
			logger.info("more than one devices with endpoint is verified");
		} else {
			logger.info("more than two devices with endpoint is not verified");
		}

	}

	@Then("^Verify the device table$")
	public void Verify_the_device_table() {
		String firstrow = BaseUtil.getElementText(getProps().getProperty("tableFirstColumn"));
		String secondcol = BaseUtil.getElementText(getProps().getProperty("tableSecondColumn"));
		if (firstrow.equalsIgnoreCase("1") && secondcol.equalsIgnoreCase("2")) {
			logger.info("the device table has first device port 1 and second device port is 2 verified");
		} else {
			logger.info("the device table has first device port 1 and second device port is 2 not verified");
		}
	}

	@Then("^Verify that On edit endpoint this should be shown net1 for key keyNum:1 or show net 0 for keyNum:2$")

	public void Verify_that_On_edit_endpoint_this_should_be_shown_net1_for_key_keyNum_1_or_show_net0_for_keyNum_2() {
		String port1 = BaseUtil.getElementText(getProps().getProperty("editEndpointPortNumber1"));
		String interface1 = BaseUtil.getElementText(getProps().getProperty("editEndpointInterface1"));
		String port2 = BaseUtil.getElementText(getProps().getProperty("editEndpointPortNumber2"));
		String interface2 = BaseUtil.getElementText(getProps().getProperty("editEndpointInterface2"));

	    Assert.assertTrue("On edit endpoint this should be shown net0 for key keyNum:1 is not verified ",(port1.equals("1") && interface1.equals("net0")));
		Assert.assertTrue("On edit endpoint this should be shown net 1 for keyNum:2 is not verified",(port2.equals("2") && interface2.equals("net1")));
	}

	@Then("^Verify Select Configuration dropdown list on Reset Configuration$")
	public void Verify_Select_Configuration_dropdown_list_on_Reset_Configuration() {
		endpointPage.clickOnAccordionOfEditEndpointPopup("Configuration");
		endpointPage.clickOperationOnConfigurationAccordion("Config History");
		boolean configDropdown = BaseUtil.verifyElementDisplayed(getProps().getProperty("configHistoryDropdown"));
		if (configDropdown) {
			logger.info("the configHistory dropdown is displayed");
		} else {
			logger.info("the configHistory dropdown is not displayed");
		}

		endpointPage.clickOnEditEndpointConfigurationConfigHistory("closeButton");
		endpointPage.clickOperationOnEndpointPage("x");
	}

	@Then("^click on \"(.*?)\" tab on gridpoint page$")
	public void click_on_site_tab(String tab) {
		BaseUtil.clickAndWait(getProps().getProperty("siteTab"));
		BaseUtil.explicitWait(9000);
		BaseUtil.verifyElementDisplayed(getProps().getProperty("addSiteButton"));

	}

	@Then("^Verify that endpoints for the selected site are listed in the endpoints section, and navigate on endpoint after selection$")
	public void Verify_that_endpoints_for_the_selected_site_are_listed_in_the_endpoints_section_and_navigate_on_endpoint_after_selection() {
		BaseUtil.clickAndWait(getProps().getProperty("editSiteEndpointAccordion"));
		BaseUtil.explicitWait(3000);
		BaseUtil.clickAndWait(getProps().getProperty("editSiteEndpointAccordionEndpoint"));
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(3000);
		endpointPage.checkAlert();
		boolean endpointpage = BaseUtil.verifyElementDisplayed(getProps().getProperty("graphIcon"));
		if (endpointpage)
			logger.info("the endpoint is associated to the given site");
		else
			logger.info("the endpoint is not associated with the given site");
	}

	@Then("^Verify the enable editing button is enable on endpoint Configuration accordion$")
	public void Verify_the_enable_editing_button() {
		BaseUtil.explicitWait(3000);
		endpointPage.clickOnAccordionOfEditEndpointPopup("Configuration");
		boolean xmlEdit = BaseUtil
				.verifyElementEnabled(getProps().getProperty("enableEditingCombinedXmlButtongraphIcon"));
		Assert.assertTrue("the xml editing button is not enable at edit endpoint Configuration accordion", xmlEdit);
		endpointPage.clickOperationOnEndpointPage("Save and Close");
	}

	@Then("^Verify the \"(.*?)\" drop down field on the endpoints details page$")
	public void Verify_the_Association_TenantSite_drop_down_field_on_the_endpoints_details_page(
			String associatedTenantSite) {
		boolean associatedTenant = BaseUtil
				.verifyElementDisplayed(getProps().getProperty("editEndpointAssociatedTenantSite"));
		if (associatedTenant) {
			logger.info("the associaated tenant site dropdown is verified at edit endpoint page");
		} else {
			logger.info("the associaated tenant site dropdown is not verified at edit endpoint page");
		}
		endpointPage.clickOperationOnEndpointPage("Save and Close");

	}

	@Then("^Verify the firmware dropdown should contain the real value$")
	public void Verify_the_firmware_dropdown_should_contain_the_real_value() {
		endpointPage.enterText_endpointPage("ENDPOINT_SEARCH", "EC1K12370026");
		BaseUtil.explicitWait(3000);
		endpointPage.clickOperationOnEndpointPage("Search");
		endpointPage.selectEndpointFromGrid_EndpointPage("EC1K12370026");
		BaseUtil.waitForSpinner(8000);
		endpointPage.checkAlert();

		boolean firmwareDropdown = BaseUtil
				.verifyElementDisplayed(getProps().getProperty("editEndpointFirmwareDropdown"));
		if (firmwareDropdown) {
			logger.info("the firmware dropdown site dropdown is verified at edit endpoint page");
		} else {
			logger.info("the firmware dropdown site dropdown is not verified at edit endpoint page");
		}

		List<String> list = BaseUtil.getDropDownText(getProps().getProperty("editEndpointFirmwareDropdown"));
		for (String lis : list) {
			if (lis == "2.2.2 - EC1CRelease2.2.2") {
				logger.info("firmware dropdown 2.2.2 - EC1CRelease2.2.2 firmware is verified");
			} else if (lis == "2.2.0 - EC1CRelease2.2.0") {
				logger.info("firmware dropdown 2.2.0 - EC1CRelease2.2.0 firmware is verified");
			} else if (lis == "EC1c2.3.2rc8 - EC1c2.3.2rc8") {
			} else {
				continue;
			}
		}

	}

	@Then("^Verify search by leaving search textbox blank on the Endpoints page$")
	public void Verify_search_by_leaving_search_textbox_blank_on_the_Endpoints_page() {
		BaseUtil.clickAndWait(getProps().getProperty("siteTab"));
		BaseUtil.explicitWait(9000);
		BaseUtil.clickAndWait(getProps().getProperty("endpointTab"));
		BaseUtil.explicitWait(9000);
		String endpointCount = BaseUtil.getElementText(getProps().getProperty("totalEndpointsOnGrid"));
		int initialTotalItemsCount = Integer.parseInt(
				endpointCount.substring(endpointCount.indexOf("of") + 2, endpointCount.indexOf("items")).trim());
		endpointPage.enterText_endpointPage("ENDPOINT_SEARCH", " ");
		BaseUtil.explicitWait(3000);
		endpointPage.clickOperationOnEndpointPage("Search");
		BaseUtil.explicitWait(3000);
		String endpointCount1 = BaseUtil.getElementText(getProps().getProperty("totalEndpointsOnGrid"));
		int initialTotalItemsAfterCount = Integer.parseInt(
				endpointCount1.substring(endpointCount1.indexOf("of") + 2, endpointCount1.indexOf("items")).trim());
		if (initialTotalItemsCount == initialTotalItemsAfterCount) {
			logger.info("the endpoint page remains same by leaving searchbox empty verified");
		} else {
			logger.info("the endpoint page remains same by leaving searchbox empty failed");
		}

	}

	@Then("^enter site \"(.*?)\" in the site search text box$")
	public void enter_site_in_the_site_search_text_box(String site) {
		BaseUtil.enterText(getProps().getProperty("siteSearchField"), site);
		BaseUtil.explicitWait(3000);
	}

	@Then("^select the entered site \"(.*?)\" from site tab grid$")
	public void select_site_from_the_grid(String siteName) {
		endpointPage.selectSiteFromGrid_SitePage(siteName);
		BaseUtil.waitForSpinner(8000);
		endpointPage.checkAlert();
	}

	@When("^click on \"(.*?)\" button on endpoints details page$")
	public void click_on_button_on_endpoints_details_page(String buttonName) {
		BaseUtil.explicitWait(2000);
		BaseUtil.click(getProps().getProperty("endpointPageDetailsCloseButton"));
		BaseUtil.explicitWait(2000);
	}

	@Then("^confirmation box on endpoint details page should display with message \"(.*?)\"$")
	public void confirmation_box_on_endpoint_details_page_should_display_with_message(String confirmationboxMessage)
			throws Throwable {
		if (confirmationboxMessage.toLowerCase().startsWith("are you sure")) {
			boolean okButton = BaseUtil
					.verifyElementDisplayed(getProps().getProperty("addEndpointOkConfirmationButton"));
			boolean cancelButton = BaseUtil
					.verifyElementDisplayed(getProps().getProperty("addEndpointCancelConfirmationButton"));
			Assert.assertTrue(
					"Behavior confirmation popup did not appear when clicked on delete button of edit Peripheral Firmware",
					(okButton && cancelButton));
		}
	}

	@Then("^message box on endpoint details page should display with message \"(.*?)\"$")
	public void message_box_on_endpoint_details_page_should_display_with_message(String confirmationboxMessage)
			throws Throwable {
		if (confirmationboxMessage.toLowerCase().startsWith("reset is pending")) {
			boolean closeButton = BaseUtil
					.verifyElementDisplayed(getProps().getProperty("addEndpointCancelConfirmationButton"));
			Assert.assertTrue(
					"Behavior confirmation popup did not appear when clicked on delete button of edit Peripheral Firmware",
					(closeButton));
		}
	}

	@Then("^user should be navigated to endpoints list page$")
	public void user_should_be_navigated_to_endpoints_list_page() {
		BaseUtil.explicitWait(5000);
		String message = "Failed to verify the availability of endpoint grid";
		BaseUtil.assertElementDisplayed(getProps().getProperty("endpointGrid"), message);
	}

	@Then("^click on the \"(.*?)\" button on endpoint page$")
	public void click_on_the_button_on_endpoint_page(String buttonName) throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.assertElementDisplayed(getProps().getProperty("addEndpointButton"),
				"Failed to verify endpoint grid availability.");
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, endpointPage.clickOperationOnEndpointPage(buttonName));
		total = BaseUtil.getElementText(getProps().getProperty("endpointTotalItemsCount"));
		endpointSearchName = BaseUtil.getElementText(getProps().getProperty("endpointSearchField"));

	}

	@Then("^click the \"(.*?)\" button on edit endpoint page and verify the total record count on endpoint list page$")
	public void click_save_and_close_button_of_endpoint_page(String buttonName) throws Throwable {

		String message1 = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message1, endpointPage.clickOperationOnEndpointPage(buttonName));

		String message2 = "Search entries is clear when user saves edits to a endpoint record";
		Assert.assertTrue(message2,
				BaseUtil.getElementText(getProps().getProperty("endpointTotalItemsCount")).equalsIgnoreCase(total));
	}

	@Then("^click the \"(.*?)\" button on edit endpoint page$")
	public void click_save_and_close_button_of_endpoint(String buttonName) throws Throwable {

		String message1 = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message1, endpointPage.clickOperationOnEndpointPage(buttonName));

		String message2 = "Search entries is clear when user saves edits to a endpoint record";
		Assert.assertTrue(message2,
				BaseUtil.getElementText(getProps().getProperty("endpointTotalItemsCount")).equalsIgnoreCase(total));
	}

	@Then("^add endpoint popup should be displayed with following fields and button:$")
	public void add_endpoint_popu_should_displayed_with_following_fields_and_button(
			List<String> listAddEndpointElements) throws Throwable {
		for (String elements : listAddEndpointElements) {
			String message = "Failed to display the field[" + elements + "] on add endpoint popup.";
			Assert.assertTrue(message, endpointPage.isElementDisplayedOnEndpointPage(elements));
		}
	}

	@Then("^verify the values for Type field in Add Endpoint popup$")
	public void verify_the_values_for_type_field_in_add_endpoint_popup(List<String> lstTenantTypeFields)
			throws Throwable {
		for (String sField : lstTenantTypeFields) {
			String message = "Failed to display the field[" + sField + "] on add endpoint popup.";
			Assert.assertTrue(message, endpointPage.isElementDisplayedOnEndpointType(sField));
		}
	}

	@When("^click on \"(.*?)\" button on add endpoint popup$")
	public void click_on_button_on_add_endpoint_popup(String buttonName) throws Throwable {
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, endpointPage.clickOperationOnEndpointPage(buttonName));
		BaseUtil.explicitWait(2000);
	}

	@Then("^error message will displayed for serial fields as \"(.*?)\"$")
	public void error_message_will_displayed_for_serial_fields_as(String errorMessage) {
		String message = "Failed to displayed error message for serial fields";
		Assert.assertTrue(message, endpointPage.verifyErrorMessage_SerialRequired(errorMessage));
	}

	@When("^click on \"(.*?)\" button on add endpoints popup$")
	public void click_on_button_on_add_endpoints_popup(String buttonName) throws Throwable {
		BaseUtil.assertElementDisplayed(getProps().getProperty("addEndpointCancelButton"),
				"Failed to verify endpoint grid availability.");
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, endpointPage.clickOperationOnEndpointPage(buttonName));
		BaseUtil.explicitWait(2000);
	}

	@When("^click on \"(.*?)\" button on edit endpoints popup$")
	public void click_on_button_on_edit_endpoints_popup(String buttonName) throws Throwable {
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, endpointPage.clickOperationOnEndpointPage(buttonName));
		BaseUtil.explicitWait(2000);
	}

	@Then("^confirmation box on add endpoint popup should display with message \"(.*?)\"$")
	public void confirmation_box_on_endpoint_popup_should_display_with_message(String confirmationboxMessage)
			throws Throwable {
		if (confirmationboxMessage.toLowerCase().startsWith("are you sure")) {
			boolean okButton = BaseUtil
					.verifyElementDisplayed(getProps().getProperty("addEndpointAbnormalBehaviorOkConfirmationButton"));
			boolean cancelButton = BaseUtil.verifyElementDisplayed(
					getProps().getProperty("addEndpointAbnormalBehaviorCancelConfirmationButton"));
			Assert.assertTrue(
					"Behavior confirmation popup did not appear when clicked on add endpoint save button without filling any information",
					(okButton && cancelButton));
		} else if (confirmationboxMessage.toLowerCase().endsWith("are you sure?")) {
			boolean okButton = BaseUtil
					.verifyElementDisplayed(getProps().getProperty("addEndpointOkConfirmationButton"));
			boolean cancelButton = BaseUtil
					.verifyElementDisplayed(getProps().getProperty("addEndpointCancelConfirmationButton"));
			Assert.assertTrue("Confirmation popup did not appear when clicked on cancel button",
					(okButton && cancelButton));
		}
	}

	@When("^click on \"(.*?)\" button on confirmation box on Endpoint Page$")
	public void click_on_Cancel_button_on_confirmation_box_on_Endpoint_Page(String buttonName) throws Throwable {
		String message = "Failed to click on button :" + buttonName + " " + " on confirmation box.";
		Assert.assertTrue(message, endpointPage.clickOperationOnConfirmationBox(buttonName));
	}

	@Then("^user should remain on add endpoint popup$")
	public void user_should_remain_on_add_endpoint_popup() throws Throwable {
		String message = "Failed to check existence of add endpoint popup.";
		Assert.assertTrue(message, endpointPage.checkPopupExistence("ADDENDPOINT", ""));
	}

	@Then("^enter invalid values in add endpoint fields$")
	public void enter_invalid_values_in_add_endpoint_fields(DataTable table) throws Throwable {
		List<Map<String, String>> lstData = table.asMaps(String.class, String.class);
		String message = "Failed to Enter the value in field [Serial] on add endpoint popup.";
		Assert.assertTrue(message, endpointPage.enterText_SerialColumn("Serial", lstData.get(0).get("Serial").trim()));
	}

	@Then("^enter the type of endpoint$")
	public void enter_the_type_of_endpoint() throws Throwable {
		BaseUtil.selectDropDownByValue(getProps().getProperty("addEndpointTypeDropDown"),
				getProps().getProperty("addEndpointTypeDropDownTestData1"));
	}

	@Then("^valid error message \"(.*?)\" should be displayed$")
	public void valid_error_message_should_be_displayed(String errorMessage) throws Throwable {
		String message = "Failed to display error message";
		Assert.assertTrue(message, endpointPage.verifyErrorMessage_SerialColumnRequiredString(errorMessage));
	}

	@When("^user modify following fields$")
	public void user_modify_following_fields(List<String> sFields) throws Throwable {
		for (String fields : sFields) {
			String message = "Failed to modify fields of endpoint";
			Assert.assertTrue(message, endpointPage.modify_FirstEndpoint(fields));
		}
	}

	@When("^user modify the following fields$")
	public void user_modify_the_following_fields(List<String> sFields) throws Throwable {
		for (String fields : sFields) {
			String message = "Failed to modify endpoint fields";
			Assert.assertTrue(message, endpointPage.modify_SecondEndpoint(fields));
		}
	}

	@Then("^verify the updated following fields$")
	public void verify_the_updated_following_fields(List<String> sFieldList) throws Throwable {
		for (String fields : sFieldList) {
			String message = "Failed to verify values of updated fields";
			Assert.assertTrue(message, endpointPage.verifyUpdatedFields(fields));
		}
	}

	@Then("^verify error message \"(.*?)\" should be displayed$")
	public void verify_error_message_should_be_displayed(String errorMessage) {
		String message = "Failed to display error message";
		Assert.assertTrue(message, endpointPage.verifyErrorMessage_editEndpointReadOnly(errorMessage));
	}

	@Then("^verify the search output by entering \"(.*?)\" in endpoint search field$")
	public void verify_the_search_output_by_entering_in_endpoint_search_field(String args) throws Throwable {
		endpointPage.verifySearchFunctionality(args);
	}

	@Then("^verify the search output by entering \"(.*?)\" which is a non existing endpoint in endpoint search field$")
	public void verify_the_search_output_by_entering_which_is_a_non_existing_endpoint_in_endpoint_search_field(
			String args) throws Throwable {
		endpointPage.verifySearchFunctionality(args);
	}

	@Then("^verify the search output by entering \"(.*?)\" more than three character and click on backspace button in endpoint search field$")
	public void verify_the_search_output_by_entering_more_than_three_character_and_click_on_backspace_button_in_endpoint_search_field(
			String args) throws Throwable {
		endpointPage.verifySearchFunctionality(args);
	}

	@Then("^verify the search output by entering string \"(.*?)\" and enter delete button in endpoint search field$")
	public void verify_the_search_output_by_entering_string_and_enter_delete_button_in_endpoint_search_field(
			String args) throws Throwable {
		endpointPage.verifySearchFunctionality(args);
	}

	@Then("^verify the pagination by entering value \"(.*?)\" in endpoint search field$")
	public void verify_the_pagination_by_entering_value_in_endpoint_search_field(String args) throws Throwable {
		endpointPage.verifySearchFunctionality(args);
	}

	@When("^click on \"(.*?)\" link in endpoint grid$")
	public void click_on_link_in_endpoint_grid(String sLinkName) throws Throwable {
		BaseUtil.explicitWait(20000);
		endpointPage.clickPaginationEndpointPage(sLinkName);
	}

	@Then("^\"(.*?)\" page should displayed in endpoint grid$")
	public void page_should_displayed_in_endpoint_grid(String task) throws Throwable {
		endpointPage.verifyEndpointsDetailGridPagination(task);
	}

	@When("^enter random page number in page link text in \"(.*?)\" grid$")
	public void enter_random_page_number_in_page_link_text_in_endpoint_grid(String accordion) throws Throwable {
		String totalPages = BaseUtil.getElementText(getProps().getProperty("endpointPaginationTotalPages"));
		totalPages = totalPages.substring(totalPages.indexOf("/") + 1).trim();
		int pageCount = Integer.parseInt(totalPages);
		if (pageCount <= 1) {
			Assert.fail(
					"Insufficient data for this test step. All the available endpoints are on the first page only.");
		}
		Random random = new Random();
		// TODO : Verify impact of beow single line of code
		// int randomPage = random.nextInt(pageCount + 1);

		// Hard coded due to slow performance
		int randomPage = random.nextInt(100);
		if (randomPage == 0 || randomPage == 1)
			randomPage = 2;
		boolean bSet = endpointPage.setRandomPage(Integer.parseInt(totalPages), randomPage, accordion);
		BaseUtil.waitForSpinner();
		Assert.assertTrue("Page" + randomPage + " is not selected.", bSet);
	}

	@Then("^user should be navigated on the given random page in endpoint grid$")
	public void user_should_be_navigated_on_the_given_random_page_in_endpoint_grid() throws Throwable {
		BaseUtil.waitForSpinner();
		List<String> lstSelectedPageEndpointNames = BaseUtil
				.getMultipleElementTextAfterLoaded(getProps().getProperty("endpointGridEndpointIdColumn"));
		BaseUtil.setElementAttribute(getProps().getProperty("endpointPaginationCurrentPage"), "1");
		BaseUtil.waitForSpinner();
		List<String> lstFirstPageEndpointNames = BaseUtil
				.getMultipleElementTextAfterLoaded(getProps().getProperty("endpointGridEndpointIdColumn"));
		if (lstFirstPageEndpointNames.equals(lstSelectedPageEndpointNames)) {
			Assert.fail("Error while navigating to given random page");
		}
	}

	@Then("^open the \"(.*?)\" accordion on edit Site popup$")
	public void open_the_accordion_on_edit_Site_popup(String accordionName) {
		endpointPage.clickOnEndpointAccordian(accordionName);
	}

	@Then("^verify that endpoint \"(.*?)\" is associated with the site \"(.*?)\" is displaying in the endpoint grid or not$")
	public void verify_that_endpoint_is_associated_with_the_site_is_displaying_in_the_endpoint_grid_or_not(
			String sEndpointName, String sSiteName) {
		String message = "Failed to verify endpoint grid availability.";
		BaseUtil.assertElementDisplayed(getProps().getProperty("editSiteEndpointGrid"), message);
		endpointPage.verifyAssociatedEndpoint(sEndpointName, sSiteName);
	}

	@When("^click on endpoint \"(.*?)\" on endpoint grid$")
	public void click_on_endpoint_on_endpoint_grid(String sEndpointName) {
		endpointPage.selectEndpointFromGrid_EndpointPage(sEndpointName);
	}

	@Then("^verify the update in endpoint with message \"(.*?)\"$")
	public void verify_the_update_in_endpoint_with_message(String endpointSaveMsg) throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(5000);
		String message = "Expected message not matched with actual message.";
		Assert.assertEquals(message, endpointSaveMsg,
				BaseUtil.getElementText(getProps().getProperty("endpointSaveMsg")));
	}

	@Then("^click on \"(.*?)\" tab\\.$")
	public void click_on_tab(String tabName) {
		BaseUtil.clickAndWait(getProps().getProperty("endpointTab"));
		BaseUtil.waitForSpinner();
	}

	@Then("^enter the endpoint name \"(.*?)\" in search field$")
	public void enter_the_endpoint_name_in_search_field(String endpointName) throws Throwable {
		endpointPage.enterText_endpointPage("ENDPOINT_SEARCH", endpointName);
	}

	@Then("^delete test data for endpoint page test scenario for endpoint serial \"(.*?)\"$")
	public void delete_test_data_for_endpoint_page_test_scenario_for_endpoint_serial(String endpointSerial)
			throws Throwable {
		ManageTestData objDelTestdata = new ManageTestData();
		Assert.assertTrue("Error occured while deleting endpoint page test data",
				objDelTestdata.manageTestData("Endpoint_Delete", endpointSerial));
	}

	@Then("^enter the values for new endpoint \"(.*?)\"$")
	public void enter_the_value_for_new_endpoint(String endpointNumber) throws Throwable {
		if (endpointNumber.equals("1")) {
			BaseUtil.enterText(getProps().getProperty("addEndpointPopupSerialField"),
					getProps().getProperty("addEndpointSerialTestData1"));
			BaseUtil.selectDropDownByValue(getProps().getProperty("addEndpointTypeDropDown"),
					getProps().getProperty("addEndpointTypeDropDownTestData1"));
		} else if (endpointNumber.equals("2")) {
			BaseUtil.enterText(getProps().getProperty("addEndpointPopupSerialField"),
					getProps().getProperty("addEndpointSerialTestData2"));
			BaseUtil.selectDropDownByValue(getProps().getProperty("addEndpointTypeDropDown"),
					getProps().getProperty("addEndpointTypeDropDownTestData2"));
		} else if (endpointNumber.equals("3")) {
			BaseUtil.enterText(getProps().getProperty("addEndpointPopupSerialField"),
					getProps().getProperty("addEndpointSerialTestData3"));
			BaseUtil.selectDropDownByValue(getProps().getProperty("addEndpointTypeDropDown"),
					getProps().getProperty("addEndpointTypeDropDownTestData3"));
		} else if (endpointNumber.equals("4")) {
			BaseUtil.enterText(getProps().getProperty("addEndpointPopupSerialField"),
					getProps().getProperty("addEndpointSerialTestData4"));
			BaseUtil.selectDropDownByValue(getProps().getProperty("addEndpointTypeDropDown"),
					getProps().getProperty("addEndpointTypeDropDownTestData4"));
		} else if (endpointNumber.equals("11")) {
			BaseUtil.enterText(getProps().getProperty("addEndpointPopupSerialField"),
					getProps().getProperty("addEndpointSerialTestData11"));
			BaseUtil.selectDropDownByValue(getProps().getProperty("addEndpointTypeDropDown"),
					getProps().getProperty("addEndpointTypeDropDownTestData1"));
		} else if (endpointNumber.equals("21")) {
			BaseUtil.enterText(getProps().getProperty("addEndpointPopupSerialField"),
					getProps().getProperty("addEndpointSerialTestData21"));
			BaseUtil.selectDropDownByValue(getProps().getProperty("addEndpointTypeDropDown"),
					getProps().getProperty("addEndpointTypeDropDownTestData2"));
		}

	}

	@Then("^open the \"(.*?)\" accordion on edit endpoint popup$")
	public void open_the_accordion_on_edit_endpoint_popup(String accordionName) throws Throwable {
		endpointPage.clickOnEditEndpointPageAccordion(accordionName);
	}

	@Then("^click on Endpoint accordion of edit site popup$")
	public void click_on_Endpoint_accordion_of_edit_site_popup() throws Throwable {
		BaseUtil.clickAndWait(getProps().getProperty("editSitePagePopupEndpointsAccordion"));
		BaseUtil.explicitWait(10000);
	}

	@Then("^Devices accordion should be opened$")
	public void Devices_accordion_should_be_opened() throws Throwable {
		BaseUtil.assertElementDisplayed(getProps().getProperty("deviceAccordionGrid"),
				"Failed to open device accordion grid");
	}

	@Then("^click on \"(.*?)\" accordion on edit endpoint page$")
	public void click_accordion_on_edit_endpoint_page(String accordionName) throws Throwable {
		String message = "Failed to click on [" + accordionName + "] on edit endpoint page";
		Assert.assertTrue(message, endpointPage.clickOnEditEndpointAccordion(accordionName));
	}

	@Then("^verify the \"(.*?)\" xml location in text box in configuration accordion$")
	public void verify_the_xml_location_in_text_box_in_configuration_accordion(String xmlName) throws Throwable {
		endpointPage.xmlVerification(xmlName);
	}

	@And("^are visible when clicked on \"(.*?)\" Button of \"(.*?)\" xml location with blank values$")
	public void are_visible_when_clicked_on_Button_of_xml_location_with_blank_values(String buttonName, String xmlName)
			throws Throwable {
		endpointPage.clickOnXmlShowButton(buttonName, xmlName);
	}

	@And("^are on locked mode and can be edited only when \"(.*?)\" button is clicked for \"(.*?)\"$")
	public void are_on_locked_mode_and_can_be_edited_only_when_button_is_clicked_for(String buttonName, String type)
			throws Throwable {
		endpointPage.clickEnableEditingButton(buttonName, type);
	}

	@Then("^verify that some random values can be added in the text field for \"(.*?)\"$")
	public void verify_that_some_random_values_can_be_added_in_the_text_field_for(String xmlName) throws Throwable {
		endpointPage.verifyValueAddableInTextArea(xmlName);
	}

	@And("^following button are disabled$")
	public void following_button_are_disabled(List<String> buttonNameList) throws Throwable {
		for (String buttonName : buttonNameList) {
			endpointPage.verifyButtonsIsDisable(buttonName);
		}
	}

	@Then("^confirmation box on endpoint edit details popup should display with message \"(.*?)\"$")
	public void confirmation_box_on_endpoint_edit_details_popup_should_display_with_message(
			String confirmationboxMessage) throws Throwable {
		String message = "Failed to verify text on Java Script popup on edit endpoint page.";
		Assert.assertTrue(message, endpointPage.verifyTextOnJavaScriptPopup(confirmationboxMessage));
	}

	@Then("^Verify that following buttons are available on the configuration accordion$")
	public void Verify_that_following_buttons_are_available_on_the_configuration_accordion(List<String> buttonNameList)
			throws Throwable {
		for (String buttonName : buttonNameList) {
			String message = "Failed to verify buttons on the configuration accordion";
			Assert.assertTrue(message, endpointPage.verifyButtonsOnConfigurationAccor(buttonName));
		}
	}

	@Given("^we are on Site page and search site field is enabled$")
	public void we_are_on_Site_page_and_search_site_field_is_enabled() throws Throwable {
		BaseUtil.explicitWait(2000);
		BaseUtil.clickAndWait(getProps().getProperty("siteTab"));
		BaseUtil.waitForSpinner();
		String message = "Failed to verify site grid availability.";
		BaseUtil.assertElementDisplayed(getProps().getProperty("optionDropDown"), message);
		BaseUtil.assertElementDisplayed(getProps().getProperty("siteDetailGrid"), message);
	}

	@When("^Click on \"(.*?)\" button on Site page$")
	public void Click_on_button(String buttonName) throws Throwable {
		BaseUtil.assertElementDisplayed(getProps().getProperty("addSiteButton"),
				"Failed to verify site grid availability.");
		BaseUtil.explicitWait(2000);
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, endpointPage.clickOperationsOnSitePage(buttonName));
		BaseUtil.explicitWait(2000);
	}

	@Then("^select the Site \"(.*?)\" from the site details grid$")
	public void select_the_root_tenant_from_the_tenant_details_grid(String sSiteName) throws Throwable {
		BaseUtil.waitForSpinner();
		endpointPage.selectSiteFromSiteGrid_SitePage(sSiteName);
	}

	@When("^Verify \"(.*?)\" page should be displayed$")
	public void Verify_page_should_be_displayed(String sPopupName) throws Throwable {
		endpointPage.popupVerification(sPopupName, "");
	}

	@When("^enter the Site name \"(.*?)\" in search field$")
	public void enter_the_root_tenant_in_search_field(String sRootSiteName) throws Throwable {
		endpointPage.enterText_SitePage("SITE_SEARCH", sRootSiteName);
	}

	@Then("^modify the following field$")
	public void modify_the_following_field(String fieldName) throws Throwable {
		String message = "Failed to modified the field [" + fieldName + "]";
		Assert.assertTrue(message, endpointPage.modifyAssociateSiteName(fieldName));
	}

	@Then("^user should be navigated on site page$")
	public void user_should_be_navigated_on_site_page() throws Throwable {
		String message = "Failed to navigate on site detail grid.";
		Assert.assertTrue(message, BaseUtil.verifyElementDisplayed(getProps().getProperty("editSitePage")));
	}

	@When("^click on the \"(.*?)\" on the site details section$")
	public void click_on_the_on_the_site_details_section(String sLinkName) throws Throwable {
		BaseUtil.click(getProps().getProperty("siteNameLink"));
		BaseUtil.explicitWait(5000);
	}

	@And("^site details accordion should be open$")
	public void site_details_accordion_should_be_open() throws Throwable {
		String message = "Failed to verify that site details accordion is opened";
		Assert.assertTrue(message, BaseUtil.verifyElementEnabled(getProps().getProperty("editSiteMap")));
	}

	@Then("^verify \"(.*?)\" button are disabled$")
	public void verify_button_are_disabled(String buttonName) throws Throwable {
		endpointPage.verifyButtonsIsDisable(buttonName);
	}

	@Then("^status popup will display with message \"(.*?)\" with status message as \"(.*?)\"$")
	public void status_popup_will_display_with_message_with_status_as(String popupMessage, String statusMessage)
			throws Throwable {
		// String message = "Failed to verify status popup message.";
		// Assert.assertTrue(message,
		// endpointPage.verifyStatusPopupMessage(popupMessage,
		// statusMessage,));
	}

	@Then("^click on the \"(.*?)\" accordion on edit endpoint popup$")
	public void click_on_the_accordion_on_edit_endpoint_popup(String accordionName) throws Throwable {
		String message = "Failed to click on accordion [" + accordionName + "]";
		Assert.assertTrue(message, endpointPage.clickOnAccordionOfEditEndpointPopup(accordionName));
	}

	@And("^verify that \"(.*?)\" accordion is opened$")
	public void verify_that_accordion_is_opened(String accordionName) throws Throwable {
		String message = "Failed to verify that accordion [" + accordionName + "] is opened or not";
		Assert.assertTrue(message, endpointPage.verifyAccordionOpened(accordionName));
	}

	@Then("^verify that following columns are present in the \"(.*?)\" accordion datagrid$")
	public void verify_that_following_columns_are_present_in_the_accordion_datagrid(String accordionName,
			List<String> listColumnHeader) throws Throwable {
		if (accordionName.equalsIgnoreCase("history")) {
			for (String historyAccordionHeader : listColumnHeader) {
				Assert.assertTrue(
						"Header [" + historyAccordionHeader + "] does not exists in the [" + accordionName + "] grid",
						endpointPage.isHistoryGridHeaderDisplayed(historyAccordionHeader));
			}
		} else if (accordionName.equalsIgnoreCase("peripherals")) {
			for (String peripheralsAccordionHeader : listColumnHeader) {
				Assert.assertTrue("Header [" + peripheralsAccordionHeader + "] does not exists in the [" + accordionName
						+ "] grid", endpointPage.isPeripheralsGridHeaderDisplayed(peripheralsAccordionHeader));
			}
		} else if (accordionName.equalsIgnoreCase("audit")) {
			for (String auditAccordionHeader : listColumnHeader) {
				Assert.assertTrue(
						"Header [" + auditAccordionHeader + "] does not exists in the [" + accordionName + "] grid",
						endpointPage.isAuditGridHeaderDisplayed(auditAccordionHeader));
			}
		} else if (accordionName.equalsIgnoreCase("Devices")) {
			for (String devicesColumnHeader : listColumnHeader) {
				Assert.assertTrue(
						"Header [" + devicesColumnHeader + "] does not exists in the [" + accordionName + "] grid",
						endpointPage.isDevicesGridHeaderDisplayed(devicesColumnHeader));
			}
		}
	}

	@Then("^verify the sorting functionality when click on the following attributes on \"(.*?)\" accordion datagrid$")
	public void verify_the_sorting_functionality_when_click_on_the_following_attributes_on_accordion_datagrid(
			String accordionName, DataTable colNames) throws Throwable {
		List<String> lstData = colNames.asList(String.class);
		for (String header : lstData) {
			endpointPage.verifySorting_AllAccordion("Descending", header);
			endpointPage.verifySorting_AllAccordion("Ascending", header);
		}
	}

	@Then("^verify the paging controls display in \"(.*?)\" accordion datagrid$")
	public void verify_the_paging_controls_display_in_accordion_datagrid(String accordionName, DataTable table)
			throws Throwable {
		List<String> linkList = table.asList(String.class);
		for (String link : linkList) {
			String message = "Failed to pagination link  [" + link + "] in [" + accordionName
					+ "] the accordion  datagrid";
			Assert.assertTrue(message, endpointPage.verifyPagination(link, accordionName));
		}
	}

	@When("^click on \"(.*?)\" in the History accordion$")
	public void click_on_in_the_History_accordion(String buttonName) throws Throwable {
		BaseUtil.click(getProps().getProperty("auditDatePicker"));
	}

	@Then("^verify the visibility of the following$")
	public void verify_the_visibility_of_the_following(List<String> datePickerList) throws Throwable {
		for (String list : datePickerList) {
			String message = "Failed to verify the start date and start time";
			Assert.assertTrue(message, endpointPage.verifyDatePicker(list));
		}
	}

	@Then("^enter data for following attributes in the date picker$")
	public void enter_data_for_following_attributes_in_the_date_picker(DataTable table) throws Throwable {
		List<Map<String, String>> startAndEndDates = table.asMaps(String.class, String.class);
		Assert.assertTrue("datepicker should be update date ", endpointPage.validateDatePicker(startAndEndDates));
		BaseUtil.click(getProps().getProperty("auditDatePickerApplyButton"));
		BaseUtil.explicitWait(5000);
	}

	@Then("^verify \"(.*?)\" button on endpoint page$")
	public void verify_button_on_endpoint_page(String buttonName) throws Throwable {
		String message = "Failed to verify button [" + buttonName + "]";
		Assert.assertTrue(message, endpointPage.verifyButtons(buttonName));
	}

	@Then("^verify that following buttons are enable$")
	public void verify_that_following_buttons_are_enable(List<String> listEnableButtons) throws Throwable {
		for (String buttonList : listEnableButtons) {
			String message = "Failed to verify enabled button [" + buttonList + "]";
			Assert.assertTrue(message, endpointPage.verifyEnabledButtons(buttonList));
		}
	}

	@Then("^Click on \"(.*?)\" button of xml location \"(.*?)\"$")
	public void Click_on_button_of_xml_location(String buttonName, String xmlName) throws Throwable {
		endpointPage.clickOnXmlShowButton(buttonName, xmlName);
	}

	@Then("^Click on \"(.*?)\" button for xml location \"(.*?)\"$")
	public void click_on_button_for_xml_location(String buttonName, String xmlLocation) throws Throwable {
		String message = "Failed to click on show button of xml location [" + xmlLocation + "[";
		Assert.assertTrue(message, endpointPage.clickOnEnableEditingButton(buttonName, xmlLocation));
	}

	@Then("^value of Select Firmware for update label should always be \"(.*?)\"$")
	public void value_of_select_Firmware_for_update_label_should_always_be(String value) throws Throwable {
		String message = "Failed to show the value  - Select One";
		Assert.assertEquals(message, value,
				BaseUtil.getSelectedDropDownValues(getProps().getProperty("selectedFirmwareValue")));
	}

	@Then("^check the \"(.*?)\" checkbox on endpoint detail grid.$")
	public void check_the_checkbox_on_endpoint_detail_grid(String value) throws Throwable {
		if (value.trim().equals("Monitoring")) {
			BaseUtil.checkCheckBox(getProps().getProperty("endpointDetailMonitoringCheckbox"));
		}
	}

	@Then("^delete test data for \"(.*?)\" accordion of endpoint page for \"(.*?)\"$")
	public void delete_test_data_for_of_endpoint_page(String accordion, String endpointName) throws Throwable {
		ManageTestData objDelTestdata = new ManageTestData();
		Assert.assertTrue("Error occured while deleting endpoint page " + accordion + " accordion test data",
				objDelTestdata.manageTestData("Endpoint_Delete_VariousAccordion", endpointName, accordion));
	}

	@Then("^we enter the test \"(.*?)\" data in the database table to check the functionality for \"(.*?)\"$")
	public void we_enter_the_test_data_in_the_database_table_to_check_the_funnctionality_for(String accordion,
			String endpointName) throws Throwable {
		ManageTestData objDelTestdata = new ManageTestData();
		Assert.assertTrue("Error occured while inserting endpoint page " + accordion + " accordion test data",
				objDelTestdata.manageTestData("Endpoint_Insert_VariousAccordion", endpointName, accordion));
	}

	@When("^click on \"(.*?)\" link in History accordion datagrid$")
	public void click_on_link_in_History_accordion_datagrid(String endpointLinkName) throws Throwable {
		endpointPage.clickPaginationEndpointPageHistoryAccordion(endpointLinkName);
	}

	@Then("^\"(.*?)\" page should displayed in History accordion datagrid$")
	public void page_should_displayed_in_History_accordion_datagrid(String task) throws Throwable {
		endpointPage.verifyEndpointsHistoryAccordionPagination(task);
	}

	@When("^click on \"(.*?)\" link in Devices accordion datagrid$")
	public void click_on_link_in_accordion_datagrid(String endpointLinkName) throws Throwable {
		endpointPage.clickPaginationEndpointPageDevicesAccordion(endpointLinkName);
	}

	@Then("^\"(.*?)\" page should displayed in Devices accordion datagrid$")
	public void page_should_displayed_in_accordion_datagrid(String task) throws Throwable {
		endpointPage.verifyEndpointsDevicesAccordionPagination(task);
	}

	@When("^enter random page number in page link text in \"(.*?)\" accordion datagrid$")
	public void enter_random_page_number_in_page_link_text_in_Devices_accordion_datagrid(String accordion)
			throws Throwable {
		String totalPages = BaseUtil
				.getElementText(getProps().getProperty("endpointDevicesAccordionPaginationTotalPages"));
		totalPages = totalPages.substring(totalPages.indexOf("/") + 1).trim();
		int pageCount = Integer.parseInt(totalPages);
		if (pageCount <= 1) {
			Assert.fail(
					"Indufficient data for this test step. All the available Endpoint devices are on the first page only.");
		}
		Random random = new Random();
		int randomPage = random.nextInt(pageCount + 1);
		if (randomPage == 0 || randomPage == 1)
			randomPage = 2;
		boolean bSet = endpointPage.setRandomPage(Integer.parseInt(totalPages), randomPage, accordion);
		BaseUtil.waitForSpinner(2000);
		Assert.assertTrue("Page" + randomPage + " is not selected.", bSet);
	}

	@Then("^user should be navigated on the given random page in Devices accordion datagrid$")
	public void user_should_be_navigated_on_the_given_random_page_in_Devices_accordion_datagrid() throws Throwable {
		BaseUtil.waitForSpinner();
		List<String> lstSelectedPageEndpointDevicesNames = BaseUtil.getMultipleElementTextAfterLoaded(
				getProps().getProperty("editEndpointDevicesAccordionDeviceTypeColumn"));
		BaseUtil.setElementAttribute(getProps().getProperty("endpointDevicesAccordionPaginationCurrentPage"), "1");
		BaseUtil.waitForSpinner();
		List<String> lstFirstPageEndpointDevicesNames = BaseUtil.getMultipleElementTextAfterLoaded(
				getProps().getProperty("editEndpointDevicesAccordionDeviceTypeColumn"));
		if (lstFirstPageEndpointDevicesNames.equals(lstSelectedPageEndpointDevicesNames)) {
			Assert.fail("Error while navigating to given random page");
		}
	}

	@Then("^verify total number of records is displaying at the bottom of the Devices accordion datagrid$")
	public void verify_total_number_of_records_is_displaying_at_the_bottom_of_the_Devices_accordion_datagrid()
			throws Throwable {
		String message = "Total no of records are not displaying ";
		Assert.assertTrue(message, endpointPage.verifyTotalNumberOfRecords());
	}

	@Then("^verify the audit details of the \"(.*?)\" created endpoint \"(.*?)\"$")
	public void verify_the_audit_details_of_the_created_endpoint(String typeOfLog, String endpointName)
			throws Throwable {
		String message = "Failed to verify logs of new created Endpoint [" + endpointName + "]";
		Assert.assertTrue(message, endpointPage.verifyLogsOfEndpoint(typeOfLog, endpointName));
	}

	@And("^update value of endpoint type from \"(.*?)\" to \"(.*?)\"$")
	public void update_value_of_endpoint_type_from_to(String endpointNameOldType, String endpointNameNewType)
			throws Throwable {
		BaseUtil.selectDropDownByValue(getProps().getProperty("endpointDetailsPageTypeValue"), endpointNameNewType);
	}

	@Then("^verify the audit details of the \"(.*?)\" endpoint type \"(.*?)\" in previous steps")
	public void verify_the_audit_details_of_the_endpoint_type(String typeOfLog, String updatedItem) throws Throwable {
		String message = "Failed to verify logs of updated Endpoint type [" + updatedItem + "]";
		Assert.assertTrue(message, endpointPage.verifyLogsOfEndpoint(typeOfLog, updatedItem));
	}

	@Then("^select the \"(.*?)\" firmware in the \"(.*?)\" accordion")
	public void select_the_firmware_in_the_accordion(String firmwareName, String accordion) throws Throwable {
		BaseUtil.selectDropDownByValue(getProps().getProperty("selectedFirmwareValue"), firmwareName);
	}

	@When("^click on \"(.*?)\" link in Audit accordion datagrid$")
	public void click_on_link_in_Audit_accordion_datagrid(String endpointLinkName) throws Throwable {
		endpointPage.clickPaginationEndpointPageAuditAccordion(endpointLinkName);
	}

	@Then("^\"(.*?)\" page should displayed in Audit accordion datagrid$")
	public void page_should_displayed_in_Audit_accordion_datagrid(String task) throws Throwable {
		endpointPage.verifyEndpointsAuditAccordionPagination(task);
	}

	@Then("^enter new test data endpoint firmware \"(.*?)\" for endpoint page$")
	public void enter_new_test_data_enpoint_firmware_for_endpoint_page(String firmwareName) throws Throwable {
		endpointPage.createTestEndpointFirmware(firmwareName);
		;
	}

	@Then("^delete all the dependencies associated with \"(.*?)\" used as test data for endpoint page$")
	public void delete_used_as_test_data_for_endpoint_page(String endpointFirmwareName) throws Throwable {
		ManageTestData objDelTestdata = new ManageTestData();
		Assert.assertTrue("Dependencies are not deleted of endpoint Firmware: " + endpointFirmwareName,
				objDelTestdata.manageTestData("EndpointFirmware", endpointFirmwareName));
	}

	@Then("^click on \"(.*?)\" button on endpoint page$")
	public void click_the_on_button_on_endpoint_page(String buttonName) throws Throwable {
		BaseUtil.assertElementDisplayed(getProps().getProperty("addEndpointButton"),
				"Failed to verify endpoint grid availability.");
		String message = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message, endpointPage.clickOperationOnEndpointPage(buttonName));

	}

	@Then("^user should be navigated to endpoint page with a label \"(.*?)\" for endpoint \"(.*?)\"")
	public void user_should_be_navigated_to_endpoint_page_with_a_label_for_endpoint(String endpointSaveMsg,
			String endpointName) throws Throwable {
		BaseUtil.explicitWait(2000);
		BaseUtil.assertElementDisplayed(getProps().getProperty("addEndpointSaveMsg"),
				"Message is not visible on the page");
		String query = "SELECT endpoint_id FROM endpoint WHERE serial='" + endpointName + "'";
		Assert.assertTrue("Expected message not matched with actual message.",
				endpointSaveMsg.replace("########", endpointPage.getEndpointId(query))
						.equalsIgnoreCase(BaseUtil.getElementText(getProps().getProperty("addEndpointSaveMsg"))));
		BaseUtil.waitForSpinner();
	}

	@Then("^verify new browser tab is not showing message \"(.*?)\"$")
	public void verify_new_browser_tab_is_not_showing_message(String message) throws Throwable {
		Assert.assertFalse("Real Time data can't be fetched because it is not a Live endpoint",
				endpointPage.checkRealTimeDataTabMessage());
	}

	@And("^new browser tab will open with real time data of the \"(.*?)\" endpoint$")
	public void new_browser_tab_will_open_with_real_time_data_of_the_endpoint(String endpointName) throws Throwable {
		Assert.assertTrue("Failed to check Real Time Data", endpointPage.checkPresentRealTimeData(endpointName));
	}

	@Then("^verify the endpoint name on edit endpoint popup for endpoint \"(.*?)\"$")
	public void verify_the_endpoint_name_on_edit_endpoint_popup(String endpointName) throws Throwable {

		String endpointData = null;
		switch (endpointName) {
		case "1":
			endpointData = getProps().getProperty("addEndpointSerialTestData1");
			break;
		case "2":
			endpointData = getProps().getProperty("addEndpointSerialTestData2");
			break;
		case "3":
			endpointData = getProps().getProperty("addEndpointSerialTestData3");
			break;
		}
		Assert.assertTrue("Title are Not matched",
				BaseUtil.getElementText(getProps().getProperty("editEndpointSerial")).equals(endpointData));
	}

	@Then("^select the entered ec1k endpoint id \"(.*?)\" from the endpoint details grid$")
	public void select_the_entered_ec1k_endpoint_id_from_the_endpoint_details_grid_ec1k(String sEndpointName) {
		endpointPage.selectEndpointFromGrid_EndpointPage_ec1k(sEndpointName);
	}

	@Then("^Suspend Connections button will change to \"(.*?)\" button$")
	public void suspend_Connections_button_will_change_to_button(List<String> enableButton) throws Throwable {
		for (String attribute : enableButton) {
			String message = "Failed to verify [ " + attribute + " ] button";
			Assert.assertTrue(message, endpointPage.verifyEnableButton(attribute));
		}
	}

	@Then("^user will be navigate to endpoint page and test site \"(.*?)\" will be \"(.*?)\"$")
	public void user_will_be_navigate_to_endpoint_page_and_test_site_will_be_in_state(String status,
			String connectionState) throws Throwable {
		String message_gridfail = "Failed to verify endpoint grid availability.";
		BaseUtil.assertElementDisplayed(getProps().getProperty("optionDropDown"), message_gridfail);
		BaseUtil.assertElementDisplayed(getProps().getProperty("endpointGrid"), message_gridfail);
		String message_connectStatus = "Failed to verify connection status as [" + connectionState + "]";
		Assert.assertTrue(message_connectStatus, endpointPage.verifyControllerConnection(status, connectionState));
	}

	@When("^controller connection is \"(.*?)\" status for endpoint \"(.*?)\"$")
	public void controller_is_disconnected_for_endpoint(String controllerStatus, String endpointSerial)
			throws Throwable {
		ManageTestData objDelTestdata = new ManageTestData();
		Assert.assertTrue("Error occured while executing queries",
				objDelTestdata.ec1kReplacementControllerStatus("Replace_controller", controllerStatus, endpointSerial));
	}

	@Then("^\"(.*?)\" button will be displayed$")
	public void button_will_be_displayed(String replace) throws Throwable {
		String message = "Failed to verify [ " + replace + " ] button";
		Assert.assertTrue(message, endpointPage.verifyEnableButton(replace));
	}

	@Then("^user will be navigated to \"(.*?)\" page for endpoint replacement$")
	public void user_will_be_navigated_to_page_for_endpoint_replacement(String arg1) {
		String message = "Failed to verify [ " + arg1 + " ] text";
		Assert.assertTrue(message, BaseUtil.verifyElementDisplayed(getProps().getProperty("SelectReplacement")));
	}

	@Then("verify Export icon is display on Endpoint tab$")
	public void verify_Export_icon_is_display_on_Endpoint_tab() throws Throwable {
		String message = "Export Icon is not available on Endpoint Firmware Tab";
		BaseUtil.assertElementDisplayed(getProps().getProperty("exportIcon"), message);
	}

	@Then("click on Export icon on Endpoint tab$")
	public void click_on_Export_icon_on_EndpointFirmware_tab() throws Throwable {
		BaseUtil.waitForSpinner();
		if (!BaseUtil.checkIfClickable(getProps().getProperty("exportIcon"))) {
			Assert.fail("Not able to click on export icon.");
		}
		BaseUtil.clickAndWait(getProps().getProperty("exportIcon"));

		String message = "Error while downloading a report";
		BaseUtil.assertElementDisplayed(getProps().getProperty("endpointTab"), message);
	}

	@Then("^close endpoint popup$")
	public void close_endpoint_popup() throws Throwable {
		Assert.assertTrue("Edit Endpoint popup is not closed", endpointPage.closeModel());
	}

	@Then("^logout from the application $")
	public void logout_from_application() throws Throwable {
	}

	@After("@EndpointPage")
	public void afterClass(Scenario scenario) {
		// BaseUtil.closeResources(scenario);
	}
}