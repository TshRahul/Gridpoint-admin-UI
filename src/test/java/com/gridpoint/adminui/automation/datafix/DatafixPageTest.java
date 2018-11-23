package com.gridpoint.adminui.automation.datafix;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import com.gridpoint.adminui.automation.testdata.ManageTestData;
import com.gridpoint.automation.IntervalData.App;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.util.BaseUtil;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DatafixPageTest extends CommonInit {
	private DatafixPage datafixPage;

	@Given("^we launch the browser with admin-UI URL and login with valid admin credential$")
	public void we_launch_the_browser_with_admin_UI_URL_and_login_with_valid_admin_credential()
			throws Throwable {
		String msg = "Unsuccessful login with valid credentials on Datafix Page";
		BaseUtil.waitForSpinner();
		BaseUtil.assertElementDisplayed(getProps()
				.getProperty("optionDropDown"), msg);
	}

	@When("^we will land on Site page and we will click on Datafix tab$")
	public void we_will_land_on_Dashboard_page_and_we_will_click_on_Datafix_tab()
			throws Throwable {
		BaseUtil.explicitWait(3000);
		BaseUtil.clickAndWait(getProps().getProperty("datafixTab"));
	}

	@Then("^initialize dummydata for \"(.*?)\" in database$")
	public void initialize_dummy_data_in_database(String testName)
			throws Throwable {
		ManageTestData objDelTestdata = new ManageTestData();
		Assert.assertTrue(
				"Error occured while deleting datafix page test data",
				objDelTestdata.manageTestData("Datafix_Delete", testName));
		Assert.assertTrue(
				"Error occured while inserting datafix page test data",
				objDelTestdata.manageTestData("Datafix_Insert", testName));
		new App().createIntervalData();
	}

	@Then("^validate \"(.*?)\" page on datafix grid$")
	public void validate_page_on_datafix_grid(String arg1) throws Throwable {
		BaseUtil.waitForSpinner();
		String message = "Failed to verify datafix grid availablity.";
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("datafixDetailGrid"), message);
	}

	@Then("^click on logout on datafix page$")
	public void click_on_logout_on_datafix_page() throws Throwable {
		if (!BaseUtil
				.checkIfClickable(getProps().getProperty("optionDropDown"))) {
			Assert.fail("Not able to login in the portal.");
		}
		BaseUtil.click(getProps().getProperty("optionDropDown"));
		BaseUtil.click(getProps().getProperty("logout"));
		BaseUtil.waitForSpinner(1000);
		String message = "Error while logging out";
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("adminUserName_Locator"), message);
	}

	@Then("^\"(.*?)\" the user used as test data for datafix page$")
	public void add_delete_user_used_as_test_data_for_audit_page(String action,
			DataTable args) throws Throwable {
		List<String> lstData = args.asList(String.class);
		ManageTestData testData = new ManageTestData();
		if (action.equalsIgnoreCase("add")) {
			for (String userName : lstData) {
				Assert.assertTrue("Error occured while creating user : "
						+ userName,
						testData.manageTestData("Datafix_Insert", userName));
			}
		} else if (action.equalsIgnoreCase("delete")) {
			for (String userName : lstData) {
				Assert.assertTrue("Error occured while deleting user : "
						+ userName,
						testData.manageTestData("Datafix_Delete", userName));
			}
		}
	}

	@And("^login with following roles with valid username and password and verify these user should not be able to view Datafix Page$")
	public void login_with_credenatial(DataTable table) throws Throwable {
		List<String> lstData = table.asList(String.class);
		for (String userName : lstData) {
			datafixPage.isDatafixTabAvailable(userName);
			if (BaseUtil.checkIfClickable(getProps().getProperty(
					"optionDropDown"))) {
				Assert.fail("Not able to login in the portal for the user : "
						+ userName);
			}
			BaseUtil.explicitWait(2000);
			BaseUtil.click(getProps().getProperty("logout"));
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("adminUserName_Locator"),
					"Error while logging out for user : " + userName);
		}
	}

	@Then("^verify the visibility of following columns in datafix details grid$")
	public void verify_the_visibility_of_following_columns_in_datafix_details_grid(
			List<String> datafixElement) throws Throwable {
		for (String element : datafixElement) {
			String message = "Failed to display the field[" + element
					+ "] on datafix.";
			Assert.assertTrue(message, datafixPage.isElementDisplayed(element));
		}
	}

	@Then("^verify \"(.*?)\" model is open and task is showing for selected schema$")
	public void verify_the_task_for_selected_datafix_schema(String modelName)
			throws Throwable {
		Assert.assertTrue("message", modelName.equals(BaseUtil.getElementText(
				getProps().getProperty("dataFixTasksModelTitle")).trim()));
		Assert.assertTrue("For selected datafix schema have no task record.",
				BaseUtil.isElementLoaded(BaseUtil.getLocator(getProps()
						.getProperty("dataFixTasksTotalItem"))));
	}

	@Then("^verify the visibility of following columns on datafix tasks detail$")
	public void verify_the_visibility_of_following_in_datafix_task_detail(
			List<String> datafixElement) throws Throwable {
		for (String element : datafixElement) {
			String message = "Failed to display the field[" + element
					+ "] on datafix tasks model.";
			Assert.assertTrue(message, datafixPage.isElementDisplayed(element));
		}
	}

	@Then("^verify the sorting functionality of columns in datafix details grid$")
	public void verify_the_sorting_functionality_of_columns_in_datafix_details_grid(
			DataTable arg1) throws Throwable {
		List<String> lstData = arg1.asList(String.class);
		arg1.asList(String.class);
		for (String header : lstData) {
			datafixPage.verifySorting_DatafixPage("Ascending", header);
			datafixPage.verifySorting_DatafixPage("Descending", header);
		}
	}

	@Given("^we are on Datafix page and search Datafix field is enabled$")
	public void we_are_on_Datafix_page_and_search_Datafix_field_is_enabled()
			throws Throwable {
		// BaseUtil.waitForSpinner();
		// BaseUtil.clickAndWait(getProps().getProperty("datafixTab"));
		BaseUtil.waitForSpinner();
		String message = "Failed to verify datafix grid availability.";
		BaseUtil.assertElementDisplayed(getProps()
				.getProperty("optionDropDown"), message);
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("datafixSearchButton"), message);
	}

	@Then("^verify the following button should be availabe on datepicker popup for \"(.*?)\" on datafix page$")
	public void verify_the_following_button_should_be_availabe_on_datepick_popup_for_on_datafix_page(
			String taskName, List<String> datepickerElement) throws Throwable {
		if (taskName.equalsIgnoreCase("datafix"))
			BaseUtil.click(getProps().getProperty("datafixdatepicker"));
		else if (taskName.equalsIgnoreCase("backup/restore"))
			BaseUtil.click(getProps().getProperty("datafixBackUpDatepicker"));
		for (String elements : datepickerElement) {
			String message = "Failed to display the field[" + elements
					+ "] on datafix backup.";
			Assert.assertTrue(message, datafixPage.isElementDisplayed(elements));
		}
	}

	@Then("^verify the following button should be availabe on datepicker popup on datafix backup page$")
	public void verify_the_following_button_should_be_availabe_on_datepick_popup_on_datafix_backup_page(
			List<String> datepickerElement) throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixDatepicker"));
		BaseUtil.explicitWait(2000);
		for (String elements : datepickerElement) {
			String message = "Failed to display the field[" + elements
					+ "] on add site popup.";
			Assert.assertTrue(message, datafixPage.isElementDisplayed(elements));
		}
	}

	@Then("^click on datepicker for \"(.*?)\" on datafix page$")
	public void click_on_datepicker_for_on_datafix_page(String taskName)
			throws Throwable {
		if (taskName.equalsIgnoreCase("datafix")) {
			BaseUtil.click(getProps().getProperty("datafixDatePicker"));
			BaseUtil.explicitWait(2000);
		} else if (taskName.equalsIgnoreCase("backup/restore")) {
			BaseUtil.click(getProps().getProperty("datafixBackUpDatepicker"));
		}
		BaseUtil.explicitWait(2000);
	}

	@Then("^verify the datepicker functionality of \"(.*?)\" tab$")
	public void verify_the_datepicker_functionality_of_for_a_fixed_time_interval_on_datafix_page(
			String taskName, DataTable table) throws Throwable {
		List<Map<String, String>> startAndEndDates = table.asMaps(String.class,
				String.class);
		BaseUtil.click(getProps().getProperty("datafixDatePicker"));
		Assert.assertTrue("datepicker should be update date ",
				datafixPage.validateDatePicker(taskName, startAndEndDates));

		if (taskName.equalsIgnoreCase("Datafix")) {
			BaseUtil.click(getProps().getProperty("datafixApplyDateButton"));
			BaseUtil.explicitWait(2000);
			BaseUtil.click(getProps().getProperty("datafixSearchButton"));
			BaseUtil.waitForSpinner();
		} else if (taskName.equalsIgnoreCase("backup/restore")) {
			BaseUtil.click(getProps().getProperty(
					"datafixBackUpApplyDateButton"));
			BaseUtil.explicitWait(2000);
			BaseUtil.click(getProps().getProperty("datafixBackupSearchButoon"));
			BaseUtil.waitForSpinner();
			BaseUtil.explicitWait(2000);
		}
		BaseUtil.explicitWait(2000);
	}

	@Then("^verify records displaying in \"(.*?)\" on datafix page$")
	public void verify_displaying_in_datafix_page(String taskName)
			throws Throwable {
		BaseUtil.explicitWait(3000);
		Assert.assertTrue("No records found.", datafixPage
				.validateDisplayingRecords(taskName));
	}

	@When("^click on \"(.*?)\" link on datafix page$")
	public void click_on_link_on_datafix_page(String arg1) throws Throwable {
		if (arg1.equalsIgnoreCase("save"))
			BaseUtil.click(getProps().getProperty("datafixSaveButton"));
		else if (arg1.equalsIgnoreCase("backup/restore")) {
			BaseUtil.explicitWait(2000);
			BaseUtil.clickAndWait(getProps().getProperty(
					"datafixBackupAccordion"));
		} else if (arg1.equalsIgnoreCase("datafix")) {
			BaseUtil.explicitWait(2000);
			BaseUtil.clickAndWait(getProps().getProperty("datafixFixAccordion"));
		} else {
			BaseUtil.click(getProps().getProperty("datafixButton"));
			BaseUtil.explicitWait(2000);
			BaseUtil.click(getProps().getProperty(
					"datafixBackupOrRestoreButton"));

		}
		BaseUtil.explicitWait(2000);
	}

	@Then("^valid error message should be displayed for \"(.*?)\" scenario on create datafix page$")
	public void valid_error_message_should_be_displayed_for_form(
			String scenario, List<String> fieldsName) throws Throwable {
		if (scenario.contains("empty")) {
			for (String field : fieldsName) {
				String message = " [" + field + "] should not be blank";
				Assert.assertTrue(message, datafixPage.isBlank(field));
			}

		}
	}

	@When("^click on \"(.*?)\" button on datafix page$")
	public void click_on_datafix_button_on_datafix_page(String button)
			throws Throwable {
		datafixPage.clickOperationOnDatafix(button);
		// datafixPage.waitForSpinner();
	}

	@When("^click on \"(.*?)\" button on restore datafix$")
	public void click_save_on_restore_datafix(String button) throws Throwable {
		datafixPage.clickOperationOnDatafix(button);
		BaseUtil.waitForSpinner();
	}

	@Then("^verify \"(.*?)\" should be expended on datafix page$")
	public void verify_backup_restore_should_be_expended(String sAccordionName)
			throws Throwable {
		datafixPage.verifyAccordionToggle_DatafixPage(sAccordionName);
	}

	@Then("^verify \"(.*?)\" should be shrink on datafix page$")
	public void verify_datafix_should_be_shrink(String sAccordionName)
			throws Throwable {
		datafixPage.verifyAccordionToggle_DatafixPage(sAccordionName);
	}

	@When("^validate data is available for test search functionality$")
	public void validate_data_is_available_for_test_search_functionality()
			throws Throwable {
		Assert.assertTrue("Data is not available ",
				datafixPage.validateDisplayingRecords("Available Records"));
	}

	@Then("^verify for the selected record of \"(.*?)\" task is showing$")
	public void verify_for_the_selected_record_of_task_is_showing(
			String taskName) throws Throwable {
		BaseUtil.waitForSpinner();
		boolean isData = datafixPage
				.validateDisplayingRecords("Available Records");
		if (taskName.equalsIgnoreCase("datafix") && isData == true) {
			BaseUtil.click(getProps().getProperty("datafixList"));
		}

	}

	@When("^Validate data is available for pagination fubctionality functionality$")
	public void validate_data_is_available_for_pagination_fubctionality_functionality()
			throws Throwable {
		Assert.assertTrue(
				"Insufficiant data to test pagination functionality ",
				datafixPage.validateDisplayingRecords("pagination"));
	}

	/**
	 * 
	 * @param Search
	 *            implements for improvement GPUP-25981
	 * @param searchString
	 * @throws Throwable
	 */

	@Then("^verify the search output for \"(.*?)\" accordion by entering \"(.*?)\" in datafix search field$")
	public void verify_the_search_output_by_entering_in_datafix_search_field(
			String taskName, String searchString) throws Throwable {
		BaseUtil.waitForSpinner();
		if (taskName.equalsIgnoreCase("datafix"))
			datafixPage.verifySearchFunctionalityForDataFix(searchString);
		else
			datafixPage
					.verifySearchFunctionalityForBackupOrRestore(searchString);
	}

	@Then("^verify the search output for \"(.*?)\" by entering \"(.*?)\" which is a non existing datafix in Datafix search field$")
	public void verify_the_search_output_by_entering_which_is_a_non_existing_datafix_in_Datafix_search_field(
			String taskName, String arg1) throws Throwable {
		if (taskName.equalsIgnoreCase("datafix"))
			datafixPage.verifySearchFunctionalityForDataFix(arg1);
		else
			datafixPage.verifySearchFunctionalityForBackupOrRestore(arg1);
	}

	@Then("^verify the search output for \"(.*?)\" by entering \"(.*?)\" more than three character and click on backspace button in datafix field$")
	public void verify_the_search_output_by_entering_more_than_three_character_and_click_on_backspace_button_in_datafix_field(
			String taskName, String arg1) throws Throwable {
		if (taskName.equalsIgnoreCase("datafix"))
			datafixPage.verifySearchFunctionalityForDataFix(arg1);
		else
			datafixPage.verifySearchFunctionalityForBackupOrRestore(arg1);
	}

	@Then("^verify the search output for \"(.*?)\" by entering string \"(.*?)\" and enter delete button in datafix search field$")
	public void verify_the_search_output_by_entering_string_and_enter_delete_button_in_datafix_search_field(
			String taskName, String arg1) throws Throwable {
		if (taskName.equalsIgnoreCase("datafix"))
			datafixPage.verifySearchFunctionalityForDataFix(arg1);
		else
			datafixPage.verifySearchFunctionalityForBackupOrRestore(arg1);
	}

	@Then("^verify the pagination for \"(.*?)\" by entering value \"(.*?)\" in datafix search field$")
	public void verify_the_pagination_by_entering_value_in_datafix_search_field(
			String taskName, String arg1) throws Throwable {
		if (taskName.equalsIgnoreCase("datafix"))
			datafixPage.verifySearchFunctionalityForDataFix(arg1);
		else
			datafixPage.verifySearchFunctionalityForBackupOrRestore(arg1);
	}

	@When("^click on \"(.*?)\" button on Datafix page$")
	public void click_on_button_on_Datafix_page(String actionName)
			throws Throwable {
		if (actionName.equalsIgnoreCase("create datafix")) {
			BaseUtil.click(getProps().getProperty("datafixCreateDatafixButton"));
			BaseUtil.isElementLoaded(BaseUtil.getLocator(getProps()
					.getProperty("datafixSiteDetailsPopupTotalItems")));

		} else if (actionName.equalsIgnoreCase("create backup"))
			BaseUtil.click(getProps().getProperty("CreateBackupButton"));
		else if (actionName.equalsIgnoreCase("Restore"))
			Assert.assertTrue(
					"restore must be clicked",
					datafixPage.clickWithIndex(
							getProps().getProperty("datafixRestoreButton"), 0));
	}

	@Then("^create datafix should be displayed$")
	public void create_datafix_should_be_displayed() throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue("Create datafix page should be displayed ",
				datafixPage.validateCreateDatafix());
	}

	@Then("^preview datafix should be displayed$")
	public void preview_datafix_should_be_displayed() throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue("Preview DataFix page should be displayed ",
				datafixPage.validateCreateDatafix());
	}

	@Then("^click on \"(.*?)\" on preview BackUp DataFix model$")
	public void click_on_save_button_on_preview_datafix_page(String button)
			throws Throwable {
		BaseUtil.click(getProps().getProperty("previewDatafixSaveButton"));
		BaseUtil.explicitWait(2000);
	}

	@When("^select \"(.*?)\" from task dropdown on create datafix page$")
	public void select_from_task_dropdown_on_create_datafix_page(
			String datafixTask) throws Throwable {
		String message = "DataFix task  [" + datafixTask
				+ "] Should be selected";
		Assert.assertTrue(message,
				datafixPage.selectValueFromDropDown(datafixTask));
		BaseUtil.explicitWait(2000);
		Assert.assertTrue(
				"element should be displayed ",
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixCreateDatafixNotes")));
		BaseUtil.explicitWait(2000);
	}

	@Then("^Verify the ChannelId input fields are ready only on create datafix page$")
	public void verify_the_ChannelId_input_fields_are_ready_only_on_create_datafix_page(
			List<String> tasks) throws Throwable {
		for (String task : tasks)
			if (task.equalsIgnoreCase("channelid"))
				Assert.assertTrue("element should be readonly ", BaseUtil
						.verifyElementIsReadOnly(getProps().getProperty(
								"datafixSourceChannelId")));
			else if (task.equalsIgnoreCase("destination channelid"))
				Assert.assertTrue("element should be readonly ", BaseUtil
						.verifyElementIsReadOnly(getProps().getProperty(
								"datafixDestinationChannelId")));
			else if (task.equalsIgnoreCase("channelId"))
				Assert.assertTrue("element should be readonly ", BaseUtil
						.verifyElementIsReadOnly(getProps().getProperty(
								"datafixChannelIdText")));
			else if (task.equalsIgnoreCase("channelId1"))
				Assert.assertTrue("element should be readonly ", BaseUtil
						.verifyElementIsReadOnly(getProps().getProperty(
								"datafixTasksModelChannelID1")));
			else if (task.equalsIgnoreCase("channelId2"))
				Assert.assertTrue("element should be readonly ", BaseUtil
						.verifyElementIsReadOnly(getProps().getProperty(
								"datafixTasksModelChannelID2")));
			else if (task.equalsIgnoreCase("ChannelID1"))
				Assert.assertTrue("element should be readonly ", BaseUtil
						.verifyElementIsReadOnly(getProps().getProperty(
								"datafixTasksModelChannelID1")));

			else if (task.equalsIgnoreCase("ChannelID2"))
				Assert.assertTrue("element should be readonly ", BaseUtil
						.verifyElementIsReadOnly(getProps().getProperty(
								"datafixTasksModelChannelID2")));

			else if (task.equalsIgnoreCase("sourceChannelID"))
				Assert.assertTrue("element should be readonly ", BaseUtil
						.verifyElementIsReadOnly(getProps().getProperty(
								"datafixCopyChannel1RadioButton")));

			else if (task.equalsIgnoreCase("datafixCopyChannel2RadioButton"))
				Assert.assertTrue("element should be readonly ", BaseUtil
						.verifyElementIsReadOnly(getProps().getProperty(
								"datafixCopyChannel2RadioButton")));

	}

	@Then("^Verify the ChannelId input fields are ready only on backup datafix page$")
	public void verify_the_ChannelId_input_fields_are_ready_only_on_backup_datafix_page(
			List<String> tasks) throws Throwable {
		for (String task : tasks)
			if (task.equalsIgnoreCase("channelid"))
				Assert.assertTrue("element should be readonly ", BaseUtil
						.verifyElementIsReadOnly(getProps().getProperty(
								"datafixBackupChannelId")));
	}

	@Then("^insert dates in datepicker for \"(.*?)\" on create datafix page$")
	public void insert_dates_in_datepicker_on_create_datafix_page(
			String taskName, DataTable table) throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixDatePicker"));
		BaseUtil.explicitWait(2000);
		List<Map<String, String>> startAndEndDates = table.asMaps(String.class,
				String.class);
		BaseUtil.explicitWait(2000);
		Assert.assertTrue("datepicker should be update date ", datafixPage
				.validateDatePickerForCreateTask(taskName, startAndEndDates));
		BaseUtil.click(getProps().getProperty("datafixTaskApplyDateButton"));
		BaseUtil.explicitWait(2000);
	}

	@Then("^insert dates in datepicker for \"(.*?)\" on datafix page$")
	public void insert_dates_in_datepicker_on_datafix_page(String taskName,
			DataTable table) throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixDatePicker"));
		List<Map<String, String>> startAndEndDates = table.asMaps(String.class,
				String.class);
		Assert.assertTrue("datepicker should be update date ", datafixPage
				.validateDatePickerForCreateTask(taskName, startAndEndDates));
		BaseUtil.click(getProps().getProperty("datafixApplyDateButton"));
	}

	@Then("^select date from datepicker and click search button on \"(.*?)\" page$")
	public void set_the_datepicker_on_datafix_page(String taskName,
			DataTable table) throws Throwable {
		List<Map<String, String>> startAndEndDates = table.asMaps(String.class,
				String.class);
		Assert.assertTrue("datepicker should be update date ",
				datafixPage.validateTimepicker(taskName, startAndEndDates));
		BaseUtil.click(getProps().getProperty("datafixApplyDateButton"));
		BaseUtil.explicitWait(2000);
		BaseUtil.click(getProps().getProperty("datafixSearchButton"));

		// } else if (taskName.equalsIgnoreCase("backup/restore")) {
		// BaseUtil.click(getProps().getProperty("datafixBackUpApplyDateButton"));
		// BaseUtil.explicitWait(2000);
		// BaseUtil.click(getProps().getProperty("datafixBackupSearchButoon"));
		// BaseUtil.waitForSpinner();
		// BaseUtil.explicitWait(2000);
		// }
		// BaseUtil.explicitWait(2000);
	}

	@Then("^select \"(.*?)\" from datafix list on \"(.*?)\" page$")
	public void select_record_on_datafix_page(String record, String taskName)
			throws Throwable {

		Assert.assertTrue("Record is not selected form dataFix list",
				datafixPage.clickOperationOnDatafix(record));
	}

	// @Then("^click search button$")
	// public void click_search_button(String arg)throws Throwable{
	// BaseUtil.explicitWait(2000);
	// datafixPage.click(getProps().getProperty("datafixSearchButton"));
	// }

	@Then("^insert \"(.*?)\" in ticket number field on create datafix page$")
	public void insert_in_ticket_number_field_on_create_datafix_page(String arg1)
			throws Throwable {
		String randomString = RandomStringUtils.randomNumeric(4);
		BaseUtil.enterText(getProps().getProperty("datafixCreateTicketNumber"),
				arg1 + randomString);
		Assert.assertTrue("Text must be entered in field", BaseUtil
				.verifyPresenceOfText(
						getProps().getProperty("datafixCreateTicketNumber"),
						arg1));
	}

	@Then("^insert \"(.*?)\" in datafix search box on datafix page$")
	public void insert_in_datafix_search_box_page(String searchString)
			throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue(BaseUtil.verifyElementEnabled(getProps().getProperty(
				"datafixSearchField")));
		BaseUtil.enterText(getProps().getProperty("datafixSearchField"),
				searchString);
		BaseUtil.explicitWait(2000);
	}

	@Then("^insert \"(.*?)\" in notes field on create datafix page$")
	public void insert_in_notes_field_on_create_datafix_page(String arg1)
			throws Throwable {
		getDriver()
				.findElement(
						BaseUtil.getLocator(getProps().getProperty(
								"datafixCreateNotes"))).clear();
		BaseUtil.enterText(getProps().getProperty("datafixCreateNotes"), arg1);
		Assert.assertTrue(
				"Text must be entered in field",
				BaseUtil.verifyPresenceOfText(
						getProps().getProperty("datafixCreateNotes"), arg1));
	}

	@Then("^insert \"(.*?)\" in notes field on restore datafix page$")
	public void insert_in_notes_field_on_restore_datafix_page(String arg1)
			throws Throwable {
		BaseUtil.enterText(getProps().getProperty("datafixRestoreNotes"),
				"resotre_backup");
		Assert.assertTrue(
				"Text must be entered in field",
				BaseUtil.verifyPresenceOfText(
						getProps().getProperty("datafixRestoreNotes"), arg1));
	}

	@Then("^insert \"(.*?)\" in notes field on create backup datafix$")
	public void insert_in_notes_field_on_backup_datafix_page(String arg1)
			throws Throwable {
		BaseUtil.enterText(getProps().getProperty("datafixCreateNotes"), arg1);
		Assert.assertTrue(
				"Text must be entered in field",
				BaseUtil.verifyPresenceOfText(
						getProps().getProperty("datafixBackupNotes"), arg1));
	}

	@Then("^click on \"(.*?)\" radio on create datafix page$")
	public void insert_on_radio_on_create_datafix_page(String arg1)
			throws Throwable {
		if (arg1.equalsIgnoreCase("source ChannelID")) {
			BaseUtil.click(getProps().getProperty(
					"datafixCopyChannel1RadioButton"));
			BaseUtil.explicitWait(3000);
		} else if (arg1.equalsIgnoreCase("destination ChannelID")) {
			BaseUtil.click(getProps().getProperty(
					"datafixCopyChannel2RadioButton"));
			BaseUtil.explicitWait(3000);
		}
	}

	@Then("^click on the \"(.*?)\" radio on create datafix page$")
	public void insert_on_the_radio_on_create_datafix_page(String arg1)
			throws Throwable {
		Assert.assertTrue(arg1 + " Button/tab is not clicked",
				datafixPage.clickOperationOnDatafix(arg1));
	}

	@Then("^insert in \"(.*?)\" field on create datafix page$")
	public void insert_in_field_on_create_datafix_page(String Task)
			throws Throwable {
		BaseUtil.enterText(getProps().getProperty("datafixDivisorText"),
				getProps().getProperty("channelValue"));
		Assert.assertTrue("Text must be entered in field", BaseUtil
				.verifyPresenceOfText(
						getProps().getProperty("datafixDivisorText"),
						getProps().getProperty("channelValue")));
	}

	@Then("^insert in to \"(.*?)\" fields on create datafix page$")
	public void insert_in_to_Divisor_field_on_create_datafix_page(String Task)
			throws Throwable {
		BaseUtil.enterText(getProps().getProperty("dataFixTaskDivisor"),
				getProps().getProperty("channelValue"));
		Assert.assertTrue("Text must be entered in first Divisor field",
				BaseUtil.verifyPresenceOfText(
						getProps().getProperty("dataFixTaskDivisor"),
						getProps().getProperty("channelValue")));

		BaseUtil.enterText(getProps().getProperty("datafixDivisorText2"),
				getProps().getProperty("channelValue"));
		Assert.assertTrue("Text must be entered in second Divisor field",
				BaseUtil.verifyPresenceOfText(
						getProps().getProperty("datafixDivisorText2"),
						getProps().getProperty("channelValue")));
	}

	@Then("^insert in to \"(.*?)\" field on create datafix page$")
	public void insert_in_multiplicand_field_on_create_datafix_page(String task)
			throws Throwable {
		BaseUtil.enterText(getProps().getProperty("datafixMultiplicandText1"),
				getProps().getProperty("channelValue"));
		Assert.assertTrue("Text must be entered in field", BaseUtil
				.verifyPresenceOfText(
						getProps().getProperty("datafixMultiplicandText1"),
						getProps().getProperty("channelValue")));

	}

	@Then("^insert in to multi \"(.*?)\" field on create datafix page$")
	public void insert_in_multi_multiplicand_field_on_create_datafix_page(
			String task) throws Throwable {
		BaseUtil.enterText(getProps().getProperty("datafixMultiplicandText1"),
				getProps().getProperty("channelValue"));
		Assert.assertTrue("Text must be entered in field", BaseUtil
				.verifyPresenceOfText(
						getProps().getProperty("datafixMultiplicandText1"),
						getProps().getProperty("channelValue")));

		BaseUtil.enterText(getProps().getProperty("datafixMultiplicandText2"),
				getProps().getProperty("channelValue2"));
		Assert.assertTrue("Text must be entered in field", BaseUtil
				.verifyPresenceOfText(
						getProps().getProperty("datafixMultiplicandText2"),
						getProps().getProperty("channelValue2")));

	}

	@Then("^insert in multiplicand fields on create datafix page$")
	public void insert_in_multiplicand_field1_on_create_datafix_page()
			throws Throwable {
		BaseUtil.enterText(getProps().getProperty("datafixMultiplicandText1"),
				"4");
		BaseUtil.enterText(getProps().getProperty("datafixMultiplicandText2"),
				"4");
		Assert.assertTrue(
				"Text must be entered in field",
				BaseUtil.verifyPresenceOfText(
						getProps().getProperty("datafixMultiplicandText1"), "4"));
		Assert.assertTrue(
				"Text must be entered in field",
				BaseUtil.verifyPresenceOfText(
						getProps().getProperty("datafixMultiplicandText2"), "4"));
	}

	@Then("^click \"(.*?)\" button on create datafix page$")
	public void click_add_button_on_create_datafix_page(String button)
			throws Throwable {
		Assert.assertTrue(
				"Add Multi Channel button is showing",
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixCreateDatafixAddButton")));
		{
			BaseUtil.click(getProps().getProperty(
					"datafixCreateDatafixAddButton"));
			BaseUtil.explicitWait(2000);
		}
	}

	@Then("^click on \"(.*?)\" button in channel accordion on create datafix page$")
	public void click_on_button_in_channele_accordion_on_create_datafix_page(
			String arg1) throws Throwable {
		BaseUtil.isElementClickable(getProps().getProperty(
				"datafixSelectChannel"));
		BaseUtil.click(getProps().getProperty("datafixSelectChannel"));
		BaseUtil.waitForSpinner();
	}

	@Then("^click on \"(.*?)\" button for destination channel id in channel accordion on create datafix page$")
	public void click_on_button_for_destination_channel_id_in_channel_accordion_on_create_datafix_page(
			String arg1) throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixSelectChannel"));
		BaseUtil.explicitWait(2000);
	}

	@Then("^click on \"(.*?)\" button in channels accordion on create backup datafix$")
	public void click_on_button_in_channele_accordion_on_create_backup_datafix_page(
			String arg1) throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixSelectChannel"));
		BaseUtil.explicitWait(2000);
	}

	@When("^select on destination channel redio on create datafix page$")
	public void select_on_destination_channel_redio_on_create_datafix_page()
			throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixDestinationRedio"));
		BaseUtil.explicitWait(3000);
	}

	@Then("^select on source channel redio on create datafix page$")
	public void select_on_source_channel_redio_on_create_datafix_page()
			throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixSourceChannel1Radio"));
		BaseUtil.explicitWait(3000);
	}

	@When("^select channel1 radio button on create datafix page$")
	public void select_multiple_channes_redio_on_create_datafix_page()
			throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixSourceChannel1Radio"));
		// BaseUtil.click(getProps().getProperty("datafixSourceChannel2Radio"));
		BaseUtil.explicitWait(3000);
	}

	@When("^select channel2 radio button on create datafix page$")
	public void select_multiple_channel2_redio_on_create_datafix_page()
			throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixSourceChannel2Radio"));
		BaseUtil.explicitWait(3000);
	}

	@Then("^verify channelid should not be empty on create datafix page$")
	public void verify_channel_should_not_be_empty_on_create_datafix_page()
			throws Throwable {
		Assert.assertTrue(
				"Text must be entered in field",
				null != BaseUtil.getElementText(getProps().getProperty(
						"datafixDestinationChannelIdText")));
	}

	@Then("^verify channelid text box should not be empty on create datafix page$")
	public void verify_channelid_should_not_be_empty_on_create_datafix_page()
			throws Throwable {
		Assert.assertTrue(
				"Source ChannelID Text box is empty ",
				null != BaseUtil.getElementText(getProps().getProperty(
						"datafixChannel1TextBox")));
		Assert.assertTrue(
				"Destination ChannelID Text box is empty ",
				null != BaseUtil.getElementText(getProps().getProperty(
						"datafixChannel2TextBox")));
	}

	@Then("^verify channel should not be empty for \"(.*?)\" on create datafix page$")
	public void verify_channel_should_not_be_empty_for_on_create_datafix_page(
			String arg1) throws Throwable {
		Assert.assertTrue(
				"Text must be entered in field",
				null != BaseUtil.getElementText(getProps().getProperty(
						"datafixSourceChannelId")));
	}

	@Then("^verify the search output for by entering \"(.*?)\" in datafix search field$")
	public void verify_the_search_output_for_by_entering_in_datafix_search_field(
			String arg1) throws Throwable {
		BaseUtil.waitForSpinner();
		datafixPage.verifySearchFunctionalityForDataFix(arg1);
	}

	@Then("^following field should be displayed on create datafix page$")
	public void following_field_should_be_displayed_on_create_datafix_page(
			List<String> datafixElements) throws Throwable {
		for (String element : datafixElements)
			Assert.assertTrue("DataFix element  [" + element
					+ "] Should be visible",
					datafixPage.verifyDatafixElementsDisplaying(element));
	}

	@Then("^following field should be displayed on backup datafix  page$")
	public void following_field_should_be_displayed_on_backup_datafix_page(
			List<String> datafixElements) throws Throwable {
		for (String element : datafixElements)
			Assert.assertTrue("DataFix element  [" + element
					+ "] Should be visible",
					datafixPage.verifyDatafixElementsDisplaying(element));
	}

	@Then("^following field of \"(.*?)\" should be displayed on create datafix page$")
	public void following_field_of_should_be_displayed_on_create_datafix_page(
			String taskName, List<String> datafixElements) throws Throwable {
		for (String element : datafixElements)
			Assert.assertTrue("DataFix element  [" + element
					+ "] Should be visible", datafixPage
					.isElementDisplayedOnSiteDetailsAccordion(element));
	}

	@Then("^following field of channel accordion should be displayed on create datafix page$")
	public void following_field_of_channel_should_be_displayed_on_create_datafix_page(
			List<String> datafixElements) throws Throwable {
		for (String element : datafixElements)
			Assert.assertTrue("DataFix element  [" + element
					+ "] Should be visible",
					datafixPage.isElementDisplayedOnChannelAccordion(element));
	}

	@Then("^following field of priview model should be displayed on create datafix page$")
	public void following_field_of_priview_should_be_displayed_on_create_datafix_page(
			List<String> datafixElements) throws Throwable {
		for (String element : datafixElements)
			Assert.assertTrue("DataFix element  [" + element
					+ "] Should be visible",
					datafixPage.isElementDisplayedOnChannelAccordion(element));
		BaseUtil.explicitWait(3000);
	}

	@Then("^verify following button should be enabled on create datafix popup$")
	public void verify_following_button_should_be_enabled_on_create_datafix_popup(
			DataTable arg1) throws Throwable {
		List<String> lstData = arg1.asList(String.class);
		arg1.asList(String.class);
		for (String button : lstData) {
			String message = button
					+ "should  be enabled on create datafix  page ";
			Assert.assertTrue(message,
					datafixPage.verifyDatafixElementsDisplaying(button));
		}
	}

	@When("^click on \"(.*?)\" accordion on create datafix$")
	public void click_on_accordion(String accordionName) throws Throwable {
		BaseUtil.waitForSpinner();
		if (accordionName.equalsIgnoreCase("Site Details"))
			BaseUtil.click(getProps().getProperty("datafixSiteDetailsPopup"));
		else if (accordionName.equalsIgnoreCase("Channels"))
			BaseUtil.click(getProps().getProperty("datafixChannelPopup"));
		else if (accordionName.equalsIgnoreCase("Create Backup Channel"))
			BaseUtil.click(getProps().getProperty(
					"datafixCreateBackupChannelPopup"));

		// BaseUtil.waitForSpinner();
		// BaseUtil.explicitWait(2000);
		// if (accordionName.equalsIgnoreCase("Site Details"))
		// Assert.assertTrue("element should be displayed",
		// BaseUtil.verifyElementDisplayed(getProps().getProperty("datafixSiteLable")));
		// else
		// Assert.assertTrue("element should be displayed",
		// BaseUtil.verifyElementDisplayed(getProps().getProperty("datafixChanneld")));
		//
		// BaseUtil.explicitWait(2000);
	}

	@When("^click on channel button in site details accordion on create datafix page$")
	public void click_on_channel_button_on_create_datafix() throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixSiteChannelButton"));
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(2000);
	}

	@When("^click on \"(.*?)\" accordion on datafix tab$")
	public void click_on_backup_accordian_on_create_datafix(String srt1)
			throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixFixAccordion"));
		BaseUtil.explicitWait(2000);
		BaseUtil.click(getProps().getProperty("datafixBackupAccordion"));
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(2000);
	}

	@When("^click on channel button in site details of copy channel accordion on create datafix page$")
	public void click_on_channel_button_copy_channel_on_create_datafix()
			throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixSiteChannelButton"), 0);
	}

	@When("^Add Datafix for \"(.*?)\" on Datafix page$")
	public void add_Datafix_for_on_Datafix_page(String arg1) throws Throwable {
		datafixPage.selectValueFromDropDown(arg1);
		BaseUtil.enterText(getProps().getProperty("datafixBackupNotes"),
				"this is my first datafix");
		BaseUtil.clickAndWait(getProps().getProperty("datafixSiteDetailsPopup"));
		BaseUtil.clickAndWait(getProps().getProperty("datafixSiteChannel1"));
		BaseUtil.clickAndWait(getProps().getProperty("datafixChannel"));
		Assert.assertTrue("channel id text should not be empty ",
				datafixPage.verifyChannelIdText());
	}

	@When("^click on \"(.*?)\" link of \"(.*?)\" accordion in datafix grid$")
	public void click_on_link_of_accordion_in_datafix_grid(
			String paginationLink, String accordionName) throws Throwable {
		BaseUtil.waitForSpinner();
		datafixPage.clickPagination(paginationLink, accordionName);
	}

	@Then("^\"(.*?)\" page should displayed of \"(.*?)\" accordion in datafix grid$")
	public void page_should_displayed_of_accordion_in_datafix_grid(
			String accordionName, String paginationLink) throws Throwable {
		datafixPage.verifyDatafixDetailGridPagination(accordionName,
				paginationLink);
	}

	@When("^enter random page number in page link text of \"(.*?)\" accordion in datafix grid$")
	public void enter_random_page_number_in_page_link_text_of_accordion_in_datafix_grid(
			String arg1) throws Throwable {
		BaseUtil.waitForSpinner();
		String totalPages = BaseUtil.getElementText(getProps().getProperty(
				"datafixPaginationTotalPages"));
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
		boolean bSet = datafixPage.setRandomPage(Integer.parseInt(totalPages),
				randomPage);
		BaseUtil.waitForSpinner();
		Assert.assertTrue("Page" + randomPage + " is not selected.", bSet);
	}

	@When("^click on datafix row on datafix page$")
	public void click_on_datafix_row_on_datafix_page() throws Throwable {
		Assert.assertTrue(
				"Datafix row must be clicked",
				datafixPage.clickWithIndex(
						getProps().getProperty("datafixGridUuidColumn"), 0));
	}

	@Then("^user should be on datafix task page$")
	public void user_should_be_on_datafix_task_page() throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("datafixTaskField"),
				"Datafix Task page should be displayed ");
	}

	@Then("^following field should be displayed on datafix task page$")
	public void following_field_should_be_displayed_on_datafix_task_page(
			List<String> datafixTaskElements) throws Throwable {
		for (String element : datafixTaskElements)
			Assert.assertTrue("DataFix element  [" + element
					+ "] Should be visible",
					datafixPage.isElementDisplayedOnDataFixTask(element));
	}

	@Then("^user should be navigated on the given random page of \"(.*?)\" accordion in datafix grid$")
	public void user_should_be_navigated_on_the_given_random_page_of_accordion_in_datafix_grid(
			String arg1) throws Throwable {
		BaseUtil.waitForSpinner();
		List<String> lstSelectedPagesiteNames = BaseUtil
				.getMultipleElementTextAfterLoaded(getProps().getProperty(
						"datafixGridUuidColumn"));
		BaseUtil.setElementAttribute(
				getProps().getProperty("datafixPaginationCurrentPage"), "1");
		BaseUtil.waitForSpinner();
		List<String> listFirstPagesiteNames = BaseUtil
				.getMultipleElementTextAfterLoaded(getProps().getProperty(
						"datafixGridUuidColumn"));
		if (!lstSelectedPagesiteNames.equals(listFirstPagesiteNames)) {
			Assert.assertTrue("Successfully navigated to given random page",
					true);
		} else {
			Assert.fail("Error while navigating to given random page");
		}
	}

	@Then("^create backup popup should be displayed$")
	public void create_backup_popup_should_be_displayed() throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue(
				"element should be displayed",
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackupCreateBackupLabel")));
		Assert.assertTrue(
				"element should be displayed",
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixSiteDetailsPopupTotalItems")));
	}

	@Then("^following field should be displayed on create backup  page$")
	public void following_field_should_be_displayed_on_create_backup_page(
			DataTable arg1) throws Throwable {
		for (String element : arg1.asList(String.class))
			Assert.assertTrue("DataFix element  [" + element
					+ "] Should be visible",
					datafixPage.verifyDatafixElementsDisplaying(element));
	}

	@Then("^following field of \"(.*?)\" should be displayed on create backup page$")
	public void following_field_of_should_be_displayed_on_create_backup_page(
			String taskName, List<String> datafixElements) throws Throwable {
		for (String element : datafixElements)
			Assert.assertTrue("DataFix element  [" + element
					+ "] Should be visible", datafixPage
					.isElementDisplayedOnBackupSiteDetailsAccordion(element));
	}

	@When("^click on channel button in site details accordion on create backup page$")
	public void click_on_channel_button_on_create_backup() throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixSiteChannelButton"));
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(2000);
	}

	@Then("^following field of channel accordion should be displayed on create backup page$")
	public void following_field_of_channel_should_be_displayed_on_create_backup_page(
			String taskName, List<String> datafixElements) throws Throwable {
		for (String element : datafixElements)
			Assert.assertTrue("DataFix element  [" + element
					+ "] Should be visible", datafixPage
					.isElementDisplayedOnChannelAccordion(element));
	}

	@When("^add a new backup on create backup page$")
	public void add_a_new_backup_on_create_backup_page() throws Throwable {
		BaseUtil.enterText(getProps().getProperty("datafixBackupNotes"),
				"this is my first backup");
		// BaseUtil.clickAndWait(getProps().getProperty("datafixSiteDetailsPopup"));
		Assert.assertTrue(
				"element should be displayed",
				BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixChanneld")));
		BaseUtil.click(getProps().getProperty("datafixSelectChannel"), 1);
		BaseUtil.explicitWait(2000);
		Assert.assertTrue("channel id text should not be empty ",
				datafixPage.verifyChannelIdText());
	}

	@Then("^Click on save button on create backup page$")
	public void click_on_save_button_on_create_backup_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		BaseUtil.click(getProps().getProperty("datafixSaveButton"));
		BaseUtil.explicitWait(2000);
	}

	@Then("^click on save button on restore datafix$")
	public void click_on_save_button_on_restore_backup_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(
				"save must be click",
				datafixPage.clickWithoutIndex(getProps().getProperty(
						"datafixSaveButton")));
		BaseUtil.waitForSpinner();
	}

	@Then("^Verify restore should be created on datafix page$")
	public void Verify_should_be_create_on_datafix_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("datafixBackupSearchButoon"),
				"Failed to verify datafix search button availability.");
		BaseUtil.explicitWait(2000);
	}

	@When("^click on \"(.*?)\" accordion on create backup datafix$")
	public void click_on_accordion_on_create_backup_datafix(String accordionName)
			throws Throwable {
		if (accordionName.equalsIgnoreCase("Site Details"))
			BaseUtil.click(getProps().getProperty("datafixSiteDetailsPopup"));
		else if (accordionName.equalsIgnoreCase("Channels"))
			BaseUtil.click(getProps().getProperty("datafixBackupChannelPopup"));

		BaseUtil.explicitWait(2000);

		if (accordionName.equalsIgnoreCase("Site Details"))
			Assert.assertTrue(
					"element should be displayed",
					BaseUtil.verifyElementDisplayed(getProps().getProperty(
							"datafixBackUpSiteId")));
		else
			Assert.assertTrue(
					"element should be displayed",
					BaseUtil.verifyElementDisplayed(getProps().getProperty(
							"datafixBackupChannelPopup")));

		BaseUtil.explicitWait(2000);
	}

	@Then("^Restore button should be visible in create backup accordion on  datafix page$")
	public void restore_button_should_be_visible_in_create_backup_accordion_on_datafix_page()
			throws Throwable {
	}

	@When("^click on \"(.*?)\" button in backup accordion on datafix page$")
	public void click_on_button_in_backup_accordion_on_datafix_page(String arg1)
			throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue(
				"button click should be work",
				datafixPage.clickWithoutIndex(getProps().getProperty(
						"datafixBackupRestoreButton")));
		BaseUtil.explicitWait(2000);
	}

	@Then("^verify restore should be created for backup data on datafix page$")
	public void verify_restore_should_be_created_for_backup_data_on_datafix_page()
			throws Throwable {

	}

	@Then("^insert \"(.*?)\" in search text box and select site from create datafix$")
	public void insert_in_search_text_box_and_select_site_on_create_datafix(
			String searchSite) throws Throwable {
		BaseUtil.enterText(
				getProps().getProperty("datafixCreateBackupSiteSearchField"),
				searchSite);
		BaseUtil.waitForSpinner(1000);
		datafixPage.selectSiteFromGrid_DadaFixPage(searchSite);
		BaseUtil.waitForSpinner();
	}

	@Then("^insert \"(.*?)\" in search text box of channels accordion and click on \"(.*?)\" button on create datafix$")
	public void insert_in_search_text_box_of_channels_acclordion_and_click_on_button_on_create_datafix(
			String searchText, String taskName) throws Throwable {
		datafixPage
				.verifySearchFunctionalityOfSiteDetails(searchText, taskName);
	}

	@When("^click on \"(.*?)\" button on restore page$")
	public void click_on_button_on_add_site_popup(String buttonElement)
			throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixCancelButton"));
		BaseUtil.waitForSpinner();
	}

	@Then("^confirmation box should display with message \"(.*?)\" on restore page$")
	public void confirmation_box_should_display_with_message(
			String confirmationboxMessage) throws Throwable {
		BaseUtil.explicitWait(2000);
		if (confirmationboxMessage.toLowerCase().startsWith("are you sure")) {
			boolean okButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty(
							"addDatafixAbnormalBehaviorOkConfirmationButton"));
			boolean cancelButton = BaseUtil
					.verifyElementDisplayed(getProps()
							.getProperty(
									"addDatafixAbnormalBehaviorCancelConfirmationButton"));
			Assert.assertTrue(
					"Behavior confirmation popup did not appear when clicked on restore save button without filling any information",
					(okButton && cancelButton));
		} else if (confirmationboxMessage.toLowerCase().endsWith(
				"are you sure?")) {
			boolean okButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("addDatafixOkConfirmationButton"));
			boolean cancelButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("addDatafixCancelConfirmationButton"));
			Assert.assertTrue(
					"Confirmation popup did not appear when clicked on cancel button",
					(okButton && cancelButton));
		} else if (confirmationboxMessage.toLowerCase().startsWith(
				"The start and end times")) {
			boolean okButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("timeAdjustmentConfirmationOkButton"));
			boolean cancelButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("timeAdjustmentConfirmationCancelButton"));
			Assert.assertTrue(
					"Confirmation popup did not appear when clicked on save button when creating datafix as copy_channel_data",
					(okButton && cancelButton));
		}
	}

	@When("^click on \"(.*?)\" button on confirmation box on restore Page$")
	public void click_on_button_on_confirmation_box(String buttonName)
			throws Throwable {
		try {
			if (buttonName.toLowerCase().equalsIgnoreCase("cancel")) {
				BaseUtil.click(getProps().getProperty(
						"addDatafixCancelConfirmationButton"));
			} else if (buttonName.toLowerCase().equalsIgnoreCase("ok")) {
				BaseUtil.click(getProps().getProperty(
						"addDatafixOkConfirmationButton"));
				BaseUtil.explicitWait(2000);
			}
		} catch (Exception e) {
			String message = "Failed to click on button :" + buttonName + " "
					+ " on confirmation box.";
			Assert.fail(message);
		}
		BaseUtil.explicitWait(2000);
	}

	@Then("^user should be navigated on datafix page$")
	public void user_should_be_navigated_on_datafix_page() throws Throwable {
		BaseUtil.assertElementDisplayed(getProps().getProperty("datafixPage"),
				"Failed to verify datafix Page availability.");
		BaseUtil.explicitWait(2000);
	}

	@Then("^enter start and end date from datepicker for \"(.*?)\" on datafix page as following$")
	public void enter_start_and_end_date_from_datepicker_for_on_datafix_page_as_following(
			String taskName, DataTable table) throws Throwable {
		List<Map<String, String>> startAndEndDates = table.asMaps(String.class,
				String.class);
		Assert.assertTrue("datepicker should be update date ",
				datafixPage.validateDatePicker(taskName, startAndEndDates));
		BaseUtil.click(getProps().getProperty("datafixApplyDateButton"));
		BaseUtil.explicitWait(2000);
		if (taskName.equalsIgnoreCase("Datafix")) {
			BaseUtil.click(getProps().getProperty("datafixSearchButton"));
			BaseUtil.waitForSpinner();
		} else if (taskName.equalsIgnoreCase("backup/restore"))
			BaseUtil.click(getProps().getProperty("datafixBackupSearch"));
		BaseUtil.waitForSpinner();
	}

	@Then("^verify the paging controls display for \"(.*?)\" accordion on datafix grid$")
	public void verify_the_paging_controls_display_for_accordion_on_datafix_grid(
			String taskName, DataTable table) throws Throwable {
		List<String> linkList = table.asList(String.class);
		for (String link : linkList) {
			String message = "Failed to pagination link  [" + link + "] in ["
					+ taskName + "] the accordion  datagrid";
			Assert.assertTrue(message,
					datafixPage.verifyPagination(link, taskName));
		}
	}

	@Then("^click on \"(.*?)\" accordion on datafix page$")
	public void click_on_accordion_on_datafix_page(String accordionName)
			throws Throwable {
		BaseUtil.click(getProps().getProperty("datafixButton"));
		BaseUtil.explicitWait(2000);
		BaseUtil.click(getProps().getProperty("datafixBackupOrRestoreButton"));
		BaseUtil.explicitWait(2000);
	}

	@Then("^enter \"(.*?)\" key for \"(.*?)\"$")
	public void enter_key_for(String pageName, String taskName)
			throws Throwable {
		datafixPage.enterKey(pageName, taskName);
		BaseUtil.explicitWait(2000);
	}

	@Then("^enter premises id \"(.*?)\" in premises search field on create datafix backup popup$")
	public void enter_premises_id_in_premises_search_field_on_create_datafix_backup_popup(
			String premisesId) throws Throwable {
		datafixPage.enterText_DataFixPage("SITE_SEARCH", premisesId);
		BaseUtil.explicitWait(2000);
		BaseUtil.click((getProps()
				.getProperty("datafixCreateBackupSiteSearchButton")));
		BaseUtil.waitForSpinner();
	}

	@Then("^enter channel name \"(.*?)\" in channel search field on create datafix backup popup$")
	public void enter_channel_name_in_channel_search_field_on_create_datafix_backup_popup(
			String channelName) throws Throwable {
		datafixPage.enterText_DataFixPage("CHANNEL_SEARCH", channelName);
	}

	@Then("^following field should be displayed on create backup popup of datafix page$")
	public void following_field_should_be_displayed_on_create_backup_popup_of_datafix_page(
			List<String> datafixElements) throws Throwable {
		for (String element : datafixElements) {
			Assert.assertTrue("DataFix element  [" + element
					+ "] Should be visible",
					datafixPage.isElementDisplayedOnCreateBackupPopup(element));
		}
	}

	@Then("^insert test data for interval data table$")
	public void insert_test_data_for_interval_data_table() throws Throwable {
		ManageTestData objDelTestdata = new ManageTestData();
		Assert.assertTrue(
				"Error occured while inserting test data for interval data table",
				objDelTestdata.manageTestData("Datafix_Insert", null));
	}

	@Then("^delete test data for interval data table$")
	public void delete_test_data_for_interval_data_table() throws Throwable {
		ManageTestData objDelTestdata = new ManageTestData();
		Assert.assertTrue(
				"Error occured while deleting test data for interval data table",
				objDelTestdata.manageTestData("Datafix_Delete", null));
	}

	@Then("^click \"(.*?)\" button on priview model$")
	public void click_save_button_on_priview(String buttonName)
			throws Throwable {

		Assert.assertTrue(datafixPage.clickOperationOnDatafix(buttonName));

	}

	@Then("^click \"(.*?)\" button on priview model for \"(.*?)\" task with channel id \"(.*?)\"$")
	public void verify_that_creation_of_datafix_for_has_done(String button,
			String taskName, String channelId) throws Throwable {
		String message = "Failed to perform datafix for :[" + taskName + "]";
		Assert.assertTrue(message,
				datafixPage.verifyCreationOfDataFix(taskName, channelId));
	}

	@Then("^click \"(.*?)\" button on priview window for \"(.*?)\" task with channelid \"(.*?)\" and \"(.*?)\"$")
	public void verify_that_creation_of_datafix_for_multi_channels(
			String button, String taskName, String channel1Id, String channel2Id)
			throws Throwable {
		String message = "Failed to perform datafix for :[" + taskName + "]";
		Assert.assertTrue(message, datafixPage.verifyCreationOfDataFix(
				taskName, channel1Id, channel2Id));
	}

	@Then("^success message should be displayed on \"(.*?)\" model$")
	public void success_message_should_be_displayed(String taskName)
			throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue("Create datafix page should be displayed ",
				datafixPage.validateSuccessMessageOfCreateDatafix());
	}

	@Then("^click save button on preview and verify that creation of datafix for swap channel has done for source id \"(.*?)\" and destination id \"(.*?)\" succesfully$")
	public void verify_that_creation_of_datafix_for_swap_channel_has_done_for_source_id_and_destination_id(
			String sourceId, String destId) throws Throwable {
		String message = "Failed to create swap channel for source id ["
				+ sourceId + "] and destination id [" + destId + "]";
		Assert.assertTrue(message,
				datafixPage.verifyCreationOfSwapChannel(sourceId, destId));
	}

	@Then("^error message should display with message \"(.*?)\"$")
	public void error_message_should_display_with_message(String message)
			throws Throwable {
		Assert.assertTrue(
				"Error message is not displayed",
				BaseUtil.getElementText(
						getProps().getProperty("differentCalcTypeMessage"))
						.equalsIgnoreCase(message));
	}

	@And("^confirm the Adjusted start date stamp value should be \"(.*?)\"$")
	public void confirm_the_Adjusted_start_date_stamp_value_should_be(
			String dateStamp) throws Throwable {
		Assert.assertTrue(
				"Adjusted start date stamp value is not matched",
				BaseUtil.getElementAttribute(
						getProps().getProperty("adjustedStartDateStampValue"),
						"value").equalsIgnoreCase(dateStamp));
	}

	@And("^confirm the Adjusted end date stamp value should be \"(.*?)\"$")
	public void confirm_the_Adjusted_end_date_stamp_value_should_be(
			String dateStamp) throws Throwable {
		Assert.assertTrue(
				"Adjusted end date stamp value is not matched",
				BaseUtil.getElementAttribute(
						getProps().getProperty("adjustedEndDateStampValue"),
						"value").equalsIgnoreCase(dateStamp));
	}

	@Then("^click on \"(.*?)\" button on confirmation box of roll up condition$")
	public void click_on_cancel_button_on_confirmation_box_of_roll_up_condition(
			String buttonName) throws Throwable {
		try {
			if (buttonName.toLowerCase().equalsIgnoreCase("cancel")) {
				BaseUtil.click(getProps().getProperty(
						"timeAdjustmentConfirmationCancelButton"));
			} else if (buttonName.toLowerCase().equalsIgnoreCase("ok")) {
				BaseUtil.click(getProps().getProperty(
						"timeAdjustmentConfirmationOkButton"));
				BaseUtil.explicitWait(2000);
			}
		} catch (Exception e) {
			String message = "Failed to click on button :" + buttonName + " "
					+ " on confirmation box.";
			Assert.fail(message);
		}
	}

	@And("^verify the rolled up rows for the destination channel id \"(.*?)\" for granularity \"(.*?)\"$")
	public void verify_the_rolled_up_rows_for_the_destination_channel_id(
			String channelId, String granularity) throws Throwable {
		int rowsCount = 0;
		switch (granularity) {
		case "1":
			rowsCount = 180;
			break;
		case "5":
			rowsCount = 36;
			break;
		case "15":
			rowsCount = 1;
			break;
		case "60":
			rowsCount = 3;
			break;
		}
		String getRowsCount = "SELECT COUNT(*) FROM interval_data WHERE channel_id="
				+ channelId;
		Assert.assertTrue("Rooled up rows are not matched with assumption",
				String.valueOf(datafixPage.getRowsCountOfDatafix(getRowsCount))
						.equalsIgnoreCase(String.valueOf(rowsCount)));
	}

	@Then("^delete the test data of rolled up rows for all channels$")
	public void delete_the_test_data_of_rolled_up_rows_for_all_channels()
			throws Throwable {
	}

	@Then("^select the record \"(.*?)\" from the datafix grid$")
	public void select_the_user_from_the_user_details_grid(String searchString)
			throws Throwable {
		datafixPage.selectRecordFromDataGrid(searchString);

	}

	@Then("^user should be navigated to create Datafix page with a label saying \"(.*?)\"$")
	public void user_should_be_navigated_to_create_Datafix_page_with_a_label_saying(
			String datafixSaveMsg) throws Throwable {
		BaseUtil.waitForSpinner();

		if (!BaseUtil.instantElementCheck("Display",
				getProps().getProperty("datafixPopupSuccessLabel"))) {
			Assert.fail("Success/Error message is not displayed on the page");
			return;
		}

		// datafixPage.assertElementDisplayed(getProps().getProperty("datafixPopupSuccessLabelLatest"),
		// "Message is not visible on the page");

		String message = "Expected message not matched with actual message";
		Assert.assertTrue(message, datafixSaveMsg.equalsIgnoreCase(BaseUtil
				.getElementText(getProps().getProperty(
						"datafixPopupSuccessLabel"))));
		BaseUtil.explicitWait(4000);
	}

	@Then("^verify \"(.*?)\" task is performed for channel id \"(.*?)\"$")
	public void verify_task_is_performed_for_channel_id(String taskName,
			String channelId) throws Throwable {
		String message = "Failed to perform datafix for :[" + taskName + "]";
		Assert.assertTrue(message,
				datafixPage.verifyCreationOfDataFix(taskName, channelId));
	}

	@When("^user click on \"(.*?)\" button on datafix page for channel id \"(.*?)\"$")
	public void user_click_on_datafix_button_on_datafix_page(String button,
			String channelId) throws Throwable {

		datafixPage.setBeforeSaveValues(channelId);
		datafixPage.clickOperationOnDatafix(button);
	}

	@When("^user click on \"(.*?)\" button on datafix page for channel \"(.*?)\" and \"(.*?)\"$")
	public void user_click_on_datafix_button_on_datafix_page_for_channel(
			String button, String channelId1, String channelId2)
			throws Throwable {
		datafixPage.setBeforeSaveValues(channelId1, channelId2);
		datafixPage.clickOperationOnDatafix(button);
	}

	@Then("^verify \"(.*?)\" task is performed for channel \"(.*?)\" and \"(.*?)\"$")
	public void verify_task_is_performed_for_channel(String taskName,
			String channel1Id, String channel2Id) throws Throwable {
		String message = "Failed to perform datafix for :[" + taskName + "]";
		Assert.assertTrue(message, datafixPage.verifyCreationOfDataFix(
				taskName, channel1Id, channel2Id));
	}

	@When("^click on \"(.*?)\" accordion on datafix$")
	public void click_on_accordion_on_datafix(String accordionName)
			throws Throwable {
		datafixPage.clickOperationOnDatafix(accordionName);
	}

	@When("^enter \"(.*?)\" in the \"(.*?)\" box and click on \"(.*?)\" button$")
	public void enter_in_the_search_box_and_click_on_button(String sText,
			String elementName, String buttonName) throws Throwable {
		datafixPage.enterText_DataFixPage(elementName, sText);
		datafixPage.clickOperationOnDatafix(buttonName);
	}

	@Then("^select row which is having notes as \"(.*?)\"$")
	public void select_row_which_is_having_notes_as(String sText)
			throws Throwable {
		datafixPage.selectDatafixFromGrid_SitePage(sText);
	}

	@Then("^verify user is on \"(.*?)\" popup and following button should be availabe on this popup$")
	public void verify_user_is_on_popup_and_following_button_should_be_availabe_on_this_popup(
			String taskName, List<String> datafixElement) throws Throwable {
		datafixElement.add(taskName);
		for (String element : datafixElement) {
			datafixPage.verifyDatafixElementsDisplaying(element);
		}
	}

	@Then("^success message should be displayed on the \"(.*?)\" model$")
	public void restore_success_message_display(String taskName)
			throws Throwable {
		BaseUtil.waitForSpinner();
		Assert.assertTrue("Backup not restore successfully",
				datafixPage.validateSuccessMessageOfRestoreDatafix());
	}

	@After("@DatafixPage")
	public void afterClass(Scenario scenario) {
		BaseUtil.closeResources(scenario);
	}
}
