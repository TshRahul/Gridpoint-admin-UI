package com.gridpoint.adminui.automation.user;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import com.gridpoint.adminui.automation.testdata.ManageTestData;
import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.automation.tests.base.TestBase;
import com.gridpoint.util.BaseUtil;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserPageTest extends CommonInit {
	private Logger logger = Logger.getLogger(UserPageTest.class);
	private UserPage userPage;

	String totalItems;
	String searchUser;

	@Given("^User is already logged in to Admin URL and is already present at the UserPage Tab for \"(.*?)\"$")
	public void user_is_already_logged_in_to_Admin_URL_and_is_already_present_at_the_user_Tab_for_Scenario_(
			String arg1) throws Throwable {

		userPage = new UserPage();

		BaseUtil.waitForSpinner();
		BaseUtil.overrideTimeout(getProps().getProperty("userPageTimeout"));
		BaseUtil.overridePollingfrequency(getProps().getProperty(
				"userPagePollingFrequency"));

		String message = "Unsuccessful login with valid credentials";
		BaseUtil.assertElementDisplayed(getProps()
				.getProperty("optionDropDown"), message);

	}

	@Given("^we are on User page and search user field is enabled visible$")
	public void we_are_on_User_page_and_search_audit_field_is_enabled()
			throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.clickAndWait(getProps().getProperty("userTab"));
		BaseUtil.explicitWait(3000);
		BaseUtil.waitForSpinner();
		BaseUtil.instantElementCheck("Display",
				getProps().getProperty("userDetailGrid"));
	}

	@Then("^verify the visibility of following columns in user details grid$")
	public void verify_the_visibility_of_following_columns_in_user_details_grid(
			DataTable arg1) throws Throwable {
		List<String> lstData = arg1.asList(String.class);
		arg1.asList(String.class);
		for (String elementName : lstData) {
			userPage.verifyElementsUserPage(elementName);
		}
	}

	@Then("^click on \"(.*?)\" button on user page$")
	public void click_on_button_on_User_page(String sButtonName)
			throws Throwable {
		String message = "Failed to click on button :" + sButtonName + " "
				+ " on User Page";
		Assert.assertTrue(message,
				userPage.clickOperationOnUserPage(sButtonName));
		BaseUtil.waitForSpinner();
		totalItems = BaseUtil.getElementText(getProps().getProperty(
				"userTotalItemsCount"));
		searchUser = BaseUtil.getElementText(getProps().getProperty(
				"userSearchField"));
	}
	 
	@Then("^confirmation box should displayed on edit user page with message \"(.*?)\"$")
	public void confirmation_box_should_displayed_with_message(String popupMessage)
	{
		boolean alert=BaseUtil.isAlertPresent();
		if(alert==true)
		{
			Alert alertPopup=BaseUtil. moveToAlert();
			String alertPopupMessage=alertPopup.getText();
			if(alertPopupMessage.equalsIgnoreCase(popupMessage))
			{
				logger.info("the Reset Password popup message on edit user page is displayed");
				
			}
			else
			{
				logger.info("the Reset Password popup message on edit user page is not 3displayed");
			}
			
			}
		else
		{
			logger.info("Failed: the alert popup on reset password page is not present");
		}
		
	}
	
	@Then("^click on cancel button on edit user page$")
	public void click_on_cancel_button_on_edit_user_page()
	{
		boolean alert=BaseUtil.isAlertPresent();
		if(alert==true)
		{
			Alert alertPopup=BaseUtil. moveToAlert();
			alertPopup.dismiss();
		}
		else
		{
			logger.info("Failed: to click on the cancel button on the reset password popup on alert page");
		}
		
	}

	@Then("^add user popup should be displayed with following fields:$")
	public void add_user_popup_should_be_displayed_with_following_fields(
			DataTable arg1) throws Throwable {
		List<String> lstData = arg1.asList(String.class);
		arg1.asList(String.class);
		boolean isVisible = true;

		for (String header : lstData) {
			boolean visible = false;
			if (header.trim().equals("username")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("userNameField"));
			}
			if (header.trim().equals("first name")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("firstNameField"));
			}
			if (header.trim().equals("last name")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("lastNameField"));
			}
			if (header.trim().equals("email")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("emailField"));
			}
			if (header.trim().equals("Locale")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("localeField"));
			}
			if (header.trim().equals("Measurement System")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("measurementField"));
			}
			if (header.trim().equals("Role")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("roleField"));
			}
			if (header.trim().equals("Site tree")) {
				visible = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("siteTree"));
			}
			// break if any column is not visible
			if (visible == false) {
				isVisible = false;
				Assert.assertTrue("Header [" + header
						+ "] is not visible in User Grid", isVisible);
				break;
			}
			BaseUtil.explicitWait(2000);
		}
	}

	@Then("^verify following button should be enabled on add user popup$")
	public void verify_following_button_should_be_enabled_on_add_user_popup(
			DataTable arg1) throws Throwable {
		List<String> lstData = arg1.asList(String.class);
		arg1.asList(String.class);
		for (String button : lstData) {
			String message = button + "should  be enabled on edit user page ";
			Assert.assertTrue(message,
					userPage.isElementEnabledOnUserPage(button));
		}
	}

	@Then("^enter the test data in the following fields for \"(.*?)\" user$")
	public void enter_the_test_data_in_the_following_fields(String userNo,
			List<String> listFieldsName) throws Throwable {
		for (String fieldName : listFieldsName) {
			userPage.enterTestData(userNo, fieldName);
		}
		/*
		 * BaseUtil.checkCheckBox(getProps().getProperty(
		 * "addUserCapabalitiesByPassThrottlingField"));
		 * BaseUtil.checkCheckBox(getProps().getProperty(
		 * "addUserCapabalitiesDisableCIDashboardField"));
		 * BaseUtil.checkCheckBox(getProps().getProperty(
		 * "addUserCapabalitiesMSTRCapableField"));
		 */
	}

	@When("^click on \"(.*?)\" button on add user popup$")
	public void click_on_button_on_add_user_popup(String sButtonName)
			throws Throwable {
		String message = "Failed to click on button :" + sButtonName + " "
				+ " on User Page";
		Assert.assertTrue(message,
				userPage.clickOperationOnUserPage(sButtonName));
		BaseUtil.explicitWait(1000);
	}

	@When("^verify \"(.*?)\" button is disabled$")
	public void verify_button_is_disabled(String buttonName) throws Throwable {
		Assert.assertFalse(buttonName
				+ "button is enable on User page but it should be disable",
				userPage.isElementEnabledOnUserPage(buttonName));
	}

	@When("^click on \"(.*?)\" accordion on user page$")
	public void click_on_accordionon_user_page(String accordionName)
			throws Throwable {
		String message = "Failed to click on accordion :" + accordionName + " "
				+ " on User Page";
		Assert.assertTrue(message,
				userPage.clickOperationOnUserPage(accordionName));
	}

	@When("^click on \"(.*?)\" in Selected Tenant frame$")
	public void click_on_in_Selected_Tenant_frame(String clickableElement)
			throws Throwable {
		String message = "Failed to click on accordion :" + clickableElement
				+ " " + " on User Page";
		Assert.assertTrue(message,
				userPage.clickOperationOnUserPage(clickableElement));
	}

	@When("^click on \"(.*?)\" checkbox in Available Sites area$")
	public void click_on_checkbox_in_Available_Sites_area(String dropdown)
			throws Throwable {
		String message = "Failed to click on checkbox :" + dropdown + " "
				+ " on User Page";
		Assert.assertTrue(message, userPage.clickOperationOnUserPage(dropdown));
	}

	@When("^verify that \"(.*?)\" Site get selected and display with Tenant name on right side of Permission Accordion$")
	public void verify_that_Sites_get_selected_and_display_with_Tenant_name_on_right_side_of_Permission_Accordion(
			String siteName) throws Throwable {
		Assert.assertTrue(siteName + "site is not selected or ",
				userPage.verifyElementsUserPage(siteName));
	}

	@When("^verify that all Sites get selected and display as \"(.*?)\" with Tenant name on right side of Permission Accordion$")
	public void verify_that_all_Sites_get_selected_and_display_as_with_Tenant_name_on_right_side_of_Permission_Accordion(
			String markingType) throws Throwable {
		Assert.assertTrue(markingType + "Either all sites are not selected or "
				+ markingType + " is not displayed",
				userPage.verifyElementsUserPage(markingType));
	}

	@When("^verify \"(.*?)\" button is enabled$")
	public void verify_button_is_enabled(String buttonName) throws Throwable {
		Assert.assertTrue(buttonName
				+ "button is disable on User page but it should be enable",
				userPage.isElementEnabledOnUserPage(buttonName));
	}

	@When("^enter the user name \"(.*?)\" in search field$")
	public void enter_the_user_name_in_search_field(String userName)
			throws Throwable {
		userPage.enterText_UserPage("USER_SEARCH", userName);
	}

	@Then("^select the user \"(.*?)\" from the user details grid$")
	public void select_the_user_from_the_user_details_grid(String userName)
			throws Throwable {
		userPage.selectUserFromGrid_UserPage(userName);
		BaseUtil.explicitWait(3000);

	}

	@When("^click on \"(.*?)\" link in user grid$")
	public void click_on_link_in_user_grid(String linkName) throws Throwable {
		BaseUtil.explicitWait(5000);
		userPage.clickPaginationUserPage(linkName);
	}

	@Then("^\"(.*?)\" page should displayed in user grid$")
	public void page_should_displayed_in_user_grid(String task)
			throws Throwable {
		userPage.verifySiteDetailGridPagination(task);
	}

	@When("^enter random page number in page link text in user grid$")
	public void enter_random_page_number_in_page_link_text_in_user_grid()
			throws Throwable {
		String totalPages = BaseUtil.getElementText(getProps().getProperty(
				"userPaginationTotalPages"));
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
		boolean bSet = userPage.setRandomPage(Integer.parseInt(totalPages),
				randomPage);
		BaseUtil.waitForSpinner();
		Assert.assertTrue("Page" + randomPage + " is not selected.", bSet);
	}

	@Then("^user should be navigated on the given random page in user grid$")
	public void user_should_be_navigated_on_the_given_random_page_in_user_grid()
			throws Throwable {
		BaseUtil.waitForSpinner();
		List<String> lstSelectedPageuserNames = BaseUtil
				.getMultipleElementTextAfterLoaded(getProps().getProperty(
						"gridUserNameColumn"));
		BaseUtil.setElementAttribute(
				getProps().getProperty("userPaginationCurrentPage"), "1");
		BaseUtil.waitForSpinner();
		List<String> listFirstPagesiteNames = BaseUtil
				.getMultipleElementTextAfterLoaded(getProps().getProperty(
						"gridUserNameColumn"));
		if (lstSelectedPageuserNames.equals(listFirstPagesiteNames)) {
			Assert.fail("Error while navigating to given random page");
		}
	}

	@Then("^verify the search output by entering \"(.*?)\" in user search field$")
	public void verify_the_search_output_by_entering_in_user_search_field(
			String arg) throws Throwable {
		userPage.verifySearchFunctionality(arg);
	}

	@Then("^verify the search output by entering \"(.*?)\" more than three character and click on backspace button in user search field$")
	public void verify_the_search_output_by_entering_more_than_three_character_and_click_on_backspace_button_in_user_search_field(
			String arg) throws Throwable {
		userPage.verifySearchFunctionality(arg);
	}

	@Then("^verify the pagination by entering value \"(.*?)\" in user search field$")
	public void verify_the_pagination_by_entering_value_in_user_search_field(
			String arg) throws Throwable {
		userPage.verifySearchFunctionality(arg);
	}

	@Then("^verify the search output by entering string \"(.*?)\" and enter delete button in user search field$")
	public void verify_the_search_output_by_entering_string_and_enter_delete_button_in_user_search_field(
			String arg) throws Throwable {
		userPage.verifySearchFunctionality(arg);
	}

	@Then("^valid error message should be displayed for \"(.*?)\" scenario for add user page$")
	public void valid_error_message_should_be_displayed_for_scenario_for_add_user_page(
			String scenario) throws Throwable {
		if (scenario.contains("empty")) {
			String[] fieldsName = { "Username", "First Name", "Last Name",
					"Email" };

			for (String field : fieldsName) {
				String message = " [" + field + "] cannot be blank.";
				Assert.assertTrue(message, userPage.isBlank(field));
			}

		} else if (scenario.contains("invalid")) {
			String[] fieldsName = { "EMAIL" };
			for (String field : fieldsName) {
				String message = "Invalid value message is not displayed for ["
						+ field + "]";
				switch (field.toUpperCase()) {
				case "EMAIL":
					Assert.assertTrue(
							message,
							BaseUtil.getElementText(
									getProps().getProperty(
											"addUserUserNameErrorMessage"))
									.equals(getProps().getProperty(
											"addUserUserNameErrorMessageTextForSpecialCase")));
					break;
				}
			}
			
		} else if (scenario.contains(" $  & + .")) {
			String[] fieldsName = { "Username" };
			for (String field : fieldsName) {
				String message = "Invalid value message is not displayed for ["
						+ field + "]";
				switch (field.toUpperCase()) {
				case "USERNAME":
					Assert.assertTrue(
							message,
							BaseUtil.getElementText(
									getProps().getProperty(
											"addUserEmailErrorMessage"))
									.equals(getProps().getProperty(
											"addUserEmailErrorMessageText")));
					break;
				}
			}
			
		} else if (scenario.contains("existing")) {
			Assert.assertTrue(
					"Duplicate error message is not displayed as expected on add site popup",
					getProps().getProperty("duplicateUserErrorMsgText").equals(
							BaseUtil.getElementText(getProps().getProperty(
									"duplicateUserErrorMsg"))));
		}
	}

	@Then("^confirmation box should display with message \"(.*?)\" for user page$")
	public void confirmation_box_should_display_with_message_for_user_page(
			String confirmationboxMessage) throws Throwable {
		if (confirmationboxMessage.toLowerCase().startsWith("are you sure")) {
			boolean cancelButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("addUserPopupCancelButton"));
			boolean okButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("addUserPopupOkButton"));
			Assert.assertTrue(
					"Behavior confirmation popup did not appear when clicked on add user save button without filling any information",
					(okButton && cancelButton));
		} else if (confirmationboxMessage.toLowerCase().endsWith(
				"are you sure?")) {
			boolean cancelButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("addUserPopupCancelButton"));
			boolean okButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("addUserPopupOkButton"));
			Assert.assertTrue(
					"Confirmation popup did not appear when clicked on cancel button",
					(okButton && cancelButton));
		}
	}

//	@Then("^click on \"(.*?)\" button on confirmation box on edit user popup$")
//	public void click_on_button_on_confirmation_box_on_edit_user_popup(String ok)
//	{
//		boolean editOkButton = BaseUtil.verifyElementDisplayed(getProps()
//				.getProperty("addUserPopupOkButton"));
//		if(editOkButton)
//		{
//			BaseUtil.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
//			
//		}
//		else
//		{
//			logger.info("Failed: to find ok button");
//		}
//		
//	}
//	
	@Then("^user navigate back to the user detail page$")
	public void user_navigate_back_to_the_user_detail_page()
			throws Throwable {
		boolean addUserPageButton= BaseUtil.verifyElementDisplayed(getProps().getProperty("UsersAddUserButton"));
		Assert.assertTrue("Failed: To navigate back to the user detail page", addUserPageButton);
		
	}
	@When("^click on \"(.*?)\" button on confirmation box on add user popup$")
	public void click_on_button_on_confirmation_box_on_add_user_popup(
			String sButtonName) throws Throwable {
		String message = "Failed to click on button :" + sButtonName + " "
				+ " on User Page";
		Assert.assertTrue(message, userPage.clickOnUserPopup(sButtonName));
	}

	@Then("^user should remain on add user popup$")
	public void user_should_remain_on_add_user_popup() throws Throwable {
		boolean onUserPopup = BaseUtil.verifyElementDisplayed(getProps()
				.getProperty("userNameField"));
		Assert.assertTrue("Add user Pop Up is not displayed", onUserPopup);
	}

	@Then("^user should be navigated on user detail grid$")
	public void user_should_be_navigated_on_user_detail_grid() throws Throwable {
		String message = "Failed to verify user grid availablity.  ";
		BaseUtil.assertElementDisplayed(getProps()
				.getProperty("userDetailGrid"), message);
	}

	@Then("^verify default value of \"(.*?)\" dropdown on add user page for \"(.*?)\" user$")
	public void verify_default_value_of_dropdown_on_add_user_page(
			String fieldName, String userNo) throws Throwable {
		userPage.verifyFieldValues(fieldName, userNo);
	}

	@Then("^user verify that \"(.*?)\" is a dropdown has the following values$")
	public void user_verifies_that_is_a_dropdown_has_the_following_values(
			String dropdownType, List<String> valueList) throws Throwable {
		userPage.verifyDropdownStringValues(dropdownType, valueList);
	}

	@Then("^verify that \"(.*?)\" is dropdown has the following roles for GP Admin$")
	public void verify_that_is_dropdown_has_the_following_roles_for_GP_Admin(
			String dropdownType, List<String> valueList) throws Throwable {
		userPage.verifyDropdownStringValues(dropdownType, valueList);
	}

	@Then("^uncheck the \"(.*?)\" checkbox on user$")
	public void uncheck_checkbox_is_unchecked(String checkBox) throws Throwable {
		// BaseUtil.clickAndWaitNew(getProps().getProperty("userinActiveUsersChkBox"));
		BaseUtil.unCheckCheckBox(getProps().getProperty(
				"userinActiveUsersChkBox"));
		BaseUtil.explicitWait(1000);
	}

	// GPUP-29950: Verify Search entries should not clear when user saves edits
	// to a
	// record.
	@Then("^click the \"(.*?)\" button on edit user page and verify entries should not clear when user saves edits to a record$")
	public void click_save_and_close_button_of_endpoint_page(String buttonName)
			throws Throwable {
		String message1 = "Failed to click on button :" + buttonName;
		Assert.assertTrue(message1,
				userPage.clickOperationOnUserPage(buttonName));
		BaseUtil.waitForSpinner();

		String message2 = "Search entries is clear when user click save and close button on edit user.";
		// TODO : assert true is converted to assert false
		Assert.assertFalse(
				message2,
				BaseUtil.getElementText(
						getProps().getProperty("userTotalItemsCount"))
						.equalsIgnoreCase(totalItems));

		String message3 = "Hide Disabled checkbox of user page is selected";
		Assert.assertFalse(
				message3,
				BaseUtil.verifyCheckBoxIsSelected(getProps().getProperty(
						"userinActiveUsersChkBox")));

		String message4 = "Search user taxt box is rest";
		// TODO : assert true is converted to assert false
		Assert.assertFalse(
				message4,
				BaseUtil.getElementText(
						getProps().getProperty("userSearchField"))
						.equalsIgnoreCase(searchUser));

	}

	@Then("^verify that \"(.*?)\" page have following section$")
	public void verify_that_page_have_following_section(String pageName,
			List<String> sSection) throws Throwable {
		for (String elementName : sSection) {
			String message = elementName + " should be displayed ";
			Assert.assertTrue(message,
					userPage.verifyElementsUserPage(elementName));
		}
	}

	@Then("^verify that \"(.*?)\" Section have following fields$")
	public void verify_that_Section_have_following_fields(String pageName,
			DataTable arg1) throws Throwable {
		List<String> lstData = arg1.asList(String.class);
		arg1.asList(String.class);
		for (String field : lstData) {
			String message = field + "should be displayed ";
			Assert.assertTrue(message,
					userPage.verifyUserSectionDisplayed(field));
		}
	}

	@Then("^verify the expansion and shrinkage while click on \"(.*?)\" accordion on edit user popup$")
	public void verify_the_expansion_and_shrinkage_while_click_on_accordion_on_edit_user_popup(
			String sAccordionName) throws Throwable {
		BaseUtil.waitForSpinner();
		userPage.verifyAccordionToggle_UserPage(sAccordionName);
	}

	@Then("^verify that \"(.*?)\" detail grid have following columns$")
	public void verify_that_detail_grid_have_following_columns(String gridName,
			List<String> listGridHeaders) throws Throwable {
		if (gridName.equalsIgnoreCase("audit")) {
			for (String auditGridHeader : listGridHeaders) {
				Assert.assertTrue("Header [" + auditGridHeader
						+ "] does not exists in the [" + gridName + "] grid",
						userPage.verifyUserSectionDisplayed(auditGridHeader));
			}
		} else if (gridName.equalsIgnoreCase("Permission")) {
			for (String auditGridHeader : listGridHeaders) {
				Assert.assertTrue("Header [" + auditGridHeader
						+ "] does not exists in the [" + gridName + "] grid",
						userPage.verifyUserSectionDisplayed(auditGridHeader));
			}
		}
	}

	@Then("^valid error message should be displayed for \"(.*?)\" scenario for user page$")
	public void valid_error_message_should_be_displayed_for_scenario(
			String scenario) throws Throwable {
		if (scenario.equals("Add User with invalid values")) {
			boolean errorDisplayed = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("emailErrorMsg"));
			Assert.assertTrue(
					"Error message for invalid Email is not displayed on Add User",
					errorDisplayed);
		} else if (scenario.equals("Reset Password")) {
			Assert.assertTrue(
					"Error message for invalid reset password is not displayed on Reset password popup",
					BaseUtil.getElementText(
							getProps().getProperty("resetPasswordErrorMessage"))
							.replace("&", "")
							.equals(getProps().getProperty(
									"resetPasswordErrorMessageText")));
		} else if (scenario.equals("not match Reset Password")) {
			Assert.assertTrue(
					"Error message for invalid reset password is not displayed on Reset password popup",
					BaseUtil.getElementText(
							getProps().getProperty("resetPasswordErrorMessage"))
							.equals(getProps().getProperty(
									"resetPasswordNotMatchErrorMessageText")));
		} else if (scenario.equals("Add User with existing values")) {
			boolean duplicateErrorMsg = BaseUtil
					.verifyElementDisplayed(getProps().getProperty(
							"duplicateUserErrorMsg"));
			Assert.assertTrue(
					"Error message for duplicate user is not displayed",
					duplicateErrorMsg);
			Assert.assertTrue(
					"Error message is not matched with the expected message",
					BaseUtil.getElementText(
							getProps().getProperty("duplicateUserErrorMsg"))
							.equals(getProps().getProperty(
									"duplicateUserErrorMsgText")));
		}
	}

	@Then("^enter invalid values in edit user fields$")
	public void enter_invalid_values_in_edit_user_fields(DataTable table)
			throws Throwable {
		Map<String, String> lstData = table.asMap(String.class, String.class);
		Set<String> fieldsName = lstData.keySet();
		for (String field : fieldsName) {
			// String message = "Invalid value entered in [" + field + "]";
			switch (field.toUpperCase()) {
			case "FIRST NAME":
				BaseUtil.enterText(getProps().getProperty("firstNameField"),
						lstData.get(field));
				break;
			case "LAST NAME":
				BaseUtil.enterText(getProps().getProperty("lastNameField"),
						lstData.get(field));
				break;
			case "EMAIL":
				BaseUtil.enterText(getProps().getProperty("emailField"),
						lstData.get(field));
				break;
			}
		}
	}

	@Given("^change the user role to \"(.*?)\" for user \"(.*?)\"$")
	public void change_the_user_role_to_for_user(String userRole,
			String userName) throws Throwable {
		BaseUtil.clickAndWait(getProps().getProperty(
				"userCapabilitiesAccordion"));
		BaseUtil.selectDropDownByValue(
				getProps().getProperty("addUserRoleField"), userRole);
		userPage.clickOperationOnUserPage("CAPABILITIES:SAVE AND CLOSE");
		BaseUtil.waitForSpinner();
	}

	@Then("^\"(.*?)\" popup should open$")
	public void edit_user_popup_should_open(String popupName)
			throws Throwable {
		
		String expectedPopupName="Edit User";
		String actualPopupName=BaseUtil.getElementText(getProps().getProperty("editUserpopupName"));
		 if(actualPopupName.equalsIgnoreCase(expectedPopupName))
				 {
			 logger.info("Passed: The edit user popup is displayed");
			 
				 }
		 else
		 {
			 logger.info("Failed: The edit user popup is not displayed");
		 }
	}

//	// TODO : compare below method with above method : 1/3/2018 9:21:45 PM (Compared and found above method is valid, will delete once all run)
//	@Given("^change the user role to \"(.*?)\" for user \"(.*?)\"$")
//	public void change_the_user_role_to_for_user(String userRole,
//			String userName) throws Throwable {
//		userPage.clickOperationOnUserPage(getProps().getProperty(
//				"usersSearchField"));
//		BaseUtil.explicitWait(2000);
//		userPage.enterText_UserPage("USER_SEARCH", userName);
//		userPage.clickOperationOnUserPage("SEARCH");
//		userPage.clickOperationOnUserPage(getProps().getProperty(
//				"userSearchField"));
//		userPage.selectUserFromGrid_UserPage(userName);
//		BaseUtil.clickAndWait(getProps().getProperty(
//				"userCapabilitiesAccordion"));
//		BaseUtil.selectDropDownByValue(
//				getProps().getProperty("addUserRoleField"), userRole);
//		// BaseUtil.click(getProps().getProperty("editUserSiteTreeTenant"));
//		userPage.clickOperationOnUserPage("SAVE CAPABILITIES");
//		// BaseUtil.explicitWait(1000);
//		// userPage.clickOnUserPage("Cancel");
//		// BaseUtil.explicitWait(2000);
//		// userPage.clickOnUserPage("OK");
//	}

	@Then("^logout from the current user \"(.*?)\" role$")
	public void logout_from_the_current_user(String role) throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.clickAndWait(getProps().getProperty("optionDropDown"));
		BaseUtil.clickAndWait(getProps().getProperty("logout"));
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("adminUserName_Locator"),
				"Error while logging out for user : " + role);
	}

	@When("^login with user \"(.*?)\" in admin application$")
	public void we_will_login_with_credential_and_verify_dashboard_tab(
			String userName) throws Throwable {
		userPage.isHomePageAvailable(userName);
		if (!BaseUtil
				.checkIfClickable(getProps().getProperty("optionDropDown"))) {
			Assert.fail("Not able to login in the application for the user : "
					+ userName);
		}
	}

	@When("^verify \"(.*?)\" element is disabled$")
	public void verify_element_is_disabled(String elementName) throws Throwable {
		Assert.assertFalse(elementName
				+ "element is disable on User page but it should be enable",
				userPage.isElementEnabledOnUserPage(elementName));
	}

	@Then("^\"(.*?)\" the users data used as test data for user page$")
	public void delete_the_user_used_as_test_data(String action,
			List<String> listUserTestData) throws Throwable {
		int i = 0;
		ManageTestData objDelTestdata = new ManageTestData();
		if (action.equals("delete")) {
			for (String userName : listUserTestData) {
				Assert.assertTrue(
						"Error occured while deleting test Data for user page",
						objDelTestdata.manageTestData("Delete_Resources",
								userName.trim(), getTestBase()));
				logger.info("*****(" + userName
						+ ")User has been deleted successfully*****");
			}

		} else if (action.equalsIgnoreCase("add")) {
			for (String userName : listUserTestData) {
				i++;

				if (objDelTestdata.manageTestData("User_Present", userName,
						getTestBase())) {
					logger.info("*****Test User(" + userName
							+ ") is already present*****");
					continue;
				}

				BaseUtil.waitForSpinner(2000);
				String addUser = "Add User";
				BaseUtil.waitForSpinner(1000);
				Assert.assertTrue("Clicking on " + addUser + " is failed",
						userPage.clickOperationOnUserPage(addUser));
				BaseUtil.waitForSpinner(1000);

				BaseUtil.enterText(getProps().getProperty("userNameField"),
						getProps().getProperty("addUserName" + i + "TestData"));
				BaseUtil.enterText(getProps().getProperty("firstNameField"),
						getProps().getProperty("addUserFirstNameTestData"));
				BaseUtil.enterText(getProps().getProperty("lastNameField"),
						getProps().getProperty("addUserLastNameTestData"));
				BaseUtil.enterText(getProps().getProperty("emailField"),
						getProps().getProperty("addUserEmailTestData"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("roleField"), getProps()
								.getProperty("addUserRole" + i + "TestData"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("unitOfTemperatureField"),
						getProps().getProperty("unitOfTemperatureTestData"));
				BaseUtil.click(getProps().getProperty("saveButton"));
				BaseUtil.waitForSpinner(1000);

				BaseUtil.click(getProps().getProperty(
						"editUserModelPermissionAccordion"));
				BaseUtil.explicitWait(PathConstants.WAIT_VERY_LOW);
				BaseUtil.click(getProps().getProperty("AddAllButton"));
				BaseUtil.explicitWait(PathConstants.WAIT_HIGH);
				BaseUtil.click(getProps().getProperty("savePermissionUserBtn"));
				BaseUtil.waitForSpinner(1000);

				BaseUtil.click(getProps().getProperty("UserPopupCloseButton"));
				BaseUtil.waitForSpinner(1000);

				ManageTestData manageData = new ManageTestData();
				Assert.assertTrue(
						"Failed to update the password for the User ("
								+ userName + ")", manageData.manageTestData(
								"User_Password_Reset", userName, getTestBase()));

				logger.info("*****(" + userName
						+ ")User has been created successfully*****");
			}
		}
	}

	@When("^verify \"(.*?)\" element is enabled$")
	public void verify_element_is_enabled(String buttonName) throws Throwable {
		Assert.assertTrue(buttonName
				+ "button is disable on User page but it should be enable",
				userPage.isElementEnabledOnUserPage(buttonName));
	}

	@When("^click on \"(.*?)\" button on edit user popup$")
	public void click_on_button_on_edit_user_popup(String sButtonName)
			throws Throwable {
		String message = "Failed to click on button :" + sButtonName + " "
				+ " on User Page";
		Assert.assertTrue(message,
				userPage.clickOperationOnUserPage(sButtonName));
		BaseUtil.explicitWait(2000);
	}

	@When("^uncheck \"(.*?)\" checkbox on edit user popup$")
	public void uncheck_checkbox_on_edit_user_popup(String arg1)
			throws Throwable {
		BaseUtil.waitForSpinner();
		BaseUtil.unCheckCheckBox(getProps().getProperty("userEnabledCheckBox"));
		BaseUtil.explicitWait(1000);
	}

	// GPUP-29949: Verify Save and Close button on edit user model.
	@Then("^verify following button should be enabled on edit user popup$")
	public void verify_following_button_should_be_enabled_on_edit_user_popup(
			DataTable buttons) throws Throwable {
		BaseUtil.explicitWait(10000);
		List<String> lstData = buttons.asList(String.class);
		buttons.asList(String.class);
		for (String button : lstData) {
			String message = button
					+ " button should be displayed on edit user page ";
			Assert.assertTrue(message,
					userPage.isElementEnabledOnUserPage(button));
		}
	}

	@Then("^click \"(.*?)\" button on edit user model and verify message \"(.*?)\" on the same$")
	public void click_button_on_edit_site_model_and_verify_message_on_the_same(
			String saveButton, String message) throws Throwable {
		userPage.clickOperationOnUserPage(saveButton);
		Assert.assertTrue(
				message
						+ ": is not showing on click save button of site detail page",
				(BaseUtil.getElementText(getProps().getProperty(
						"editUserSuccessMsg")).equalsIgnoreCase(message)));
	}

	@After("@UserPage")
	public void afterClass(Scenario scenario) {
//		BaseUtil.closeResources(scenario);
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// @Given("^Initialize resources in the beginning of each scenario for User Page\\.$")
	// public void
	// initialize_resources_in_the_beginning_of_each_scenario_for_User_Page()
	// throws Throwable {
	// TestBase.getInstance();
	// getProps().getProperty =
	// TestBase.getReadResources().getValuesFromXml("UserPage.xml",
	// "userObjectRepository");
	// userPageObjectText =
	// TestBase.getReadResources().getValuesFromXml("UserPage.xml",
	// "userObjectText");
	// userPageObjectTestData =
	// TestBase.getReadResources().getValuesFromXml("UserPage.xml",
	// "userObjectTestData");
	// configObjectData =
	// TestBase.getReadResources().getValuesFromXml("Configuration.xml",
	// "userlogincredentials");
	// configGridpoint =
	// TestBase.getReadResources().getValuesFromXml("Configuration.xml",
	// "gridpoint");
	// configEnvironment =
	// TestBase.getReadResources().getValuesFromXml("Configuration.xml",
	// "environment_url");
	//
	//
	// TestBase.userLogin();
	// TestBase.setWebDriverWait(TestBase.getWebDriverWait());
	// userPage.waitForSpinner();
	// // String msg = "Unsuccessful login with valid credentials on User Page";
	// //
	// userPage.assertElementDisplayed(getProps().getProperty("optionDropDown"),
	// msg);
	//
	// }
	//
	// @Given("^we launch the browser with admin-UI URL and login with set of valid admin credentials$")
	// public void
	// we_launch_the_browser_with_admin_UI_URL_and_login_with_set_of_valid_admin_credentials()
	// throws Throwable {
	// String msg = "Unsuccessful login with valid credentials on User Page";
	// userPage.assertElementDisplayed(getProps().getProperty("optionDropDown"),
	// msg);
	// userPage.waitForSpinner();
	// }
	//
	// @Then("^Verify Role Dropdown has ROLE_CUSTOMER_SITE_MANAGER selected$")
	// public void
	// verify_Role_Dropdown_has_ROLE_CUSTOMER_SITE_MANAGER_selected() throws
	// Throwable {
	// // Added by FW. Write code here that turns the phrase above into concrete
	// // actions
	// // throw new PendingException();
	// boolean visible = false;
	// visible =
	// userPage.verifyElementDisplayed(getProps().getProperty("roleField"));
	// String defaultText = "";
	// if (visible) {
	// defaultText =
	// userPage.getSelectedDropDownValues(getProps().getProperty("roleField"));
	// if (defaultText.equals("ROLE_CUSTOMER_SITE_MANAGER"))
	// System.out.println("Test Case 24 Passed");
	// else
	// System.out.println("Test Case 24 Failed");
	// }
	// }
	//
	// @When("^we will land on Site page, we will click on \"(.*?)\" tab$")
	// public void we_will_land_on_Dashboard_page_we_will_click_on_tab(String
	// arg1) throws Throwable {
	// userPage.click(getProps().getProperty("userTab"));
	// userPage.waitForSpinner();
	// }
	//
	// @Then("^verify the \"(.*?)\" page by confirming the availability of user grid\\.$")
	// public void
	// verify_the_page_by_confirming_the_availability_of_user_grid(String arg1)
	// throws Throwable {
	// String message = "Failed to verify user grid availablity.  ";
	// userPage.assertElementDisplayed(getProps().getProperty("userGrid"),
	// message);
	// }
	//
	// @Given("^we are on User page and search user field is enabled$")
	// public void we_are_on_User_page_and_search_user_field_is_enabled() throws
	// Throwable {
	// userPage.waitForSpinner();
	// userPage.clickAndWait(getProps().getProperty("userTab"));
	// userPage.waitForSpinner();
	// String message = "Failed to verify user grid availablity.  ";
	// userPage.assertElementDisplayed(getProps().getProperty("optionDropDown"),
	// message);
	// userPage.assertElementDisplayed(getProps().getProperty("userGrid"),
	// message);
	// }
	//
	// //
	// @Then("^verify the visibility of following columns in user details grid$")
	// // public void
	// verify_the_visibility_of_following_columns_in_user_details_grid(DataTable
	// arg1) throws Throwable {
	// // List<String> lstData = arg1.asList(String.class);
	// // arg1.asList(String.class);
	// // boolean isVisible = true;
	// // for (String header : lstData) {
	// // boolean visible = false;
	// // if (header.trim().equals("Username")) {
	// // visible =
	// userPage.verifyElementDisplayed(getProps().getProperty("gridHeaderUsername"));
	// // }
	// // if (header.trim().equals("Email")) {
	// // visible =
	// userPage.verifyElementDisplayed(getProps().getProperty("gridHeaderUserEmail"));
	// // }
	// // if (header.trim().equals("First Name")) {
	// // visible =
	// userPage.verifyElementDisplayed(getProps().getProperty("gridHeaderFirstName"));
	// // }
	// // if (header.trim().equals("Last Name")) {
	// // visible =
	// userPage.verifyElementDisplayed(getProps().getProperty("gridHeaderLastName"));
	// // }
	// // if (header.trim().equals("Enabled")) {
	// // visible =
	// userPage.verifyElementDisplayed(getProps().getProperty("gridHeaderEnabled"));
	// // }
	// // if (header.trim().equals("Locked")) {
	// // visible =
	// userPage.verifyElementDisplayed(getProps().getProperty("gridHeaderLocked"));
	// // }
	// // if (header.trim().equals("Role")) {
	// // visible =
	// userPage.verifyElementDisplayed(getProps().getProperty("gridHeaderRole"));
	// // }
	// //
	// // // break if any column is not visible
	// // if (visible == false) {
	// // isVisible = false;
	// // Assert.assertTrue("Header [" + header +
	// "] is not visible in User Grid", isVisible);
	// // break;
	// // }
	// // }
	// // }
	//
	// @Then("^verify the sorting functionality of columns in user details grid$")
	// public void
	// verify_the_sorting_functionality_of_columns_in_user_details_grid(DataTable
	// arg1) throws Throwable {
	// List<String> lstData = arg1.asList(String.class);
	// arg1.asList(String.class);
	// for (String header : lstData) {
	// userPage.verifySorting_UserPage("Ascending", header);
	// userPage.verifySorting_UserPage("Descending", header,
	// );
	// }
	// }
	//
	// @When("^verify that \"(.*?)\" element is not present in Selected Tenant frame$")
	// public void
	// verify_that_element_is_not_present_in_Selected_Tenant_frame(String
	// buttonName) throws Throwable {
	// Assert.assertFalse("Sites are not removed after clicking on Remove button",
	// userPage.instantElementCheckOnUserPage(buttonName));
	// }
	//
	// // @When("^click on \"(.*?)\" drodown in Permission accordion$")
	// // public void click_on_drodown_in_Permission_accordion(String dropdown)
	// // throws Throwable {
	// // String message = "Failed to click on Drodpown :" + dropdown + " " + "
	// on
	// // User Page";
	// // Assert.assertTrue(message, userPage.clickOnUserPage(dropdown,
	// // ));
	// // }
	//
	// //
	// // @When("^select \"(.*?)\" from \"(.*?)\" dropown$")
	// // public void select_from_dropown(String value, String dropdown) throws
	// // Throwable {
	// // String message = "Failed to select " + value + " from Drodpown :" +
	// // dropdown + " " + " on User Page";
	// // Assert.assertTrue(message, userPage.clickOnUserPage(dropdown,
	// // ));
	// // }
	//
	//
	//
	//
	// @Then("^click on \"(.*?)\" on user page$")
	// public void click_on_button_on_user_page(String sButtonName) throws
	// Throwable {
	// String message = "Failed to click on button :" + sButtonName + " " +
	// " on User Page";
	// Assert.assertTrue(message, userPage.clickOperationOnUserPage(sButtonName,
	// ));
	// userPage.explicitWait(2000);
	// }
	//
	// @Then("^user should be navigated to user page with a label saying \"(.*?)\" for user page$")
	// public void
	// user_should_be_navigated_to_user_page_with_a_label_saying_for_user_page(String
	// userSaveMessage)
	// throws Throwable {
	// userPage.waitForSpinner();
	// String message = "Expected message not matched with actual message.";
	// Assert.assertTrue(message,
	// userSaveMessage.equals(userPage.getElementText(getProps().getProperty("addUserSaveMsg"))));
	// }
	//
	// @Then("^click on the logout button from user page and verify the login page$")
	// public void Verify_Logout_After_Successful_Login() throws
	// InterruptedException {
	// userPage.waitForSpinner();
	// if
	// (!userPage.checkIfClickable(getProps().getProperty("optionDropDown")))
	// {
	// Assert.fail("Not able to login in the portal.");
	// }
	// userPage.clickElement(getProps().getProperty("optionDropDown"));
	// userPage.clickElement(getProps().getProperty("logout"));
	//
	// String message = "Error while logging out";
	// userPage.assertElementDisplayed(getProps().getProperty("adminUserName_Locator"),
	// message);
	// }
	//
	//
	// // @Then("^adding tenant for User Page$")
	// // public void add_tenant_for_user_page() {
	// // final Map<String, String> tenantPageObjectRepo =
	// TestBase.getReadResources().getValuesFromXml("TenantPage.xml",
	// // "tenantObjectRepository");
	// // userPage.clickAndWait(tenantPageObjectRepo("tenantTab"));
	// // userPage.waitForSpinner();
	// // tenantPage.clickOperationOnTenantPage("Add Tenant",
	// tenantPageObjectRepo);
	// // userPage.explicitWait(2000);
	// // tenantPage.enterText_TenantPage("Name",
	// userPageObjectTestData("addUserTenantDropDownTestData"),
	// // tenantPageObjectRepo);
	// //
	// tenantPage.checkCheckBox(tenantPageObjectRepo("addTenantPopupInheritCheckBox"));
	// // tenantPage.enterText_TenantPage("Parent",
	// userPageObjectTestData("addUserTenantParentTestData"),
	// // tenantPageObjectRepo);
	// // tenantPage.clickOperationOnTenantPage("Save", tenantPageObjectRepo);
	// // userPage.waitForSpinner();
	// // String message = "Failed to verify tenant grid availablity.  ";
	// //
	// tenantPage.assertElementDisplayed(tenantPageObjectRepo("tenantDetailGrid"),
	// message);
	// // }
	//
	// @Then("^enter invalid values is add user fields$")
	// public void enter_invalid_values_is_add_user_fields(DataTable table)
	// throws Throwable {
	// Map<String, String> lstData = table.asMap(String.class, String.class);
	// Set<String> fieldsName = lstData.keySet();
	// for (String field : fieldsName) {
	// String message = "Invalid value entered in [" + field + "]";
	// switch (field.toUpperCase()) {
	// case "Email":
	// userPage.enterText(getProps().getProperty("emailField"),
	// lstData.get(field));
	// Assert.assertTrue(message, true);
	// break;
	// }
	// }
	// }
	//
	//
	// @Then("^select \"(.*?)\" from site tree on \"(.*?)\" user popup$")
	// public void select_value_from_site_tree_on_add_user_popup(String
	// tenantName, String action) throws Throwable {
	// if (action.equalsIgnoreCase("add")) {
	// userPage.click(getProps().getProperty("addUserSiteTreeTenant"));
	// } else if (action.equalsIgnoreCase("edit")) {
	// userPage.click(getProps().getProperty("editUserSiteTreeTenant"));
	// }
	// }
	//
	// @Then("^add user popup should be displayed with following default fields$")
	// public void
	// add_user_popup_should_be_displayed_with_following_default_fields(List<String>
	// lstData)
	// throws Throwable {
	// boolean isVisible = true;
	// for (String fielddefaultValue : lstData) {
	// boolean visible = false;
	// if (fielddefaultValue.trim().equals("English (United States)")) {
	// visible =
	// userPage.verifyElementDisplayed(getProps().getProperty("addUserDefaultLocaleMessage"));
	// } else if (fielddefaultValue.trim().equals("US")) {
	// visible = userPage
	// .verifyElementDisplayed(getProps().getProperty("addUserDefaultMeasurementSystemMessage"));
	// } else if (fielddefaultValue.trim().equals("C&I Internal Analyst")) {
	// visible =
	// userPage.verifyElementDisplayed(getProps().getProperty("addUserDefaultRoleMessage"));
	// }
	// // break if any column is not visible
	// if (visible == false) {
	// isVisible = false;
	// Assert.assertTrue("Default value [" + fielddefaultValue +
	// "] is not visible in User Grid", isVisible);
	// break;
	// }
	// }
	// }
	//
	 @Then("^Verify FAHRENHEIGHT is selected as Unit of Temperature$")
	 public void verify_FAHRENHEIGHT_is_selected_as_Unit_of_Temperature()
	 throws Throwable {
	 // Added by FW. Write code here that turns the phrase above into concrete
	 // actions
	 // throw new PendingException();
	 boolean visible = false;
	 visible =
	 BaseUtil.verifyElementDisplayed(getProps().getProperty("addUserUnitOfTemparature"));
	 String defaultText = "";
	 if (visible) {
	 defaultText =
	 BaseUtil.getSelectedDropDownValues(getProps().getProperty("addUserUnitOfTemparature"));
	 System.out.println(defaultText);
	 if (defaultText.equals("FAHRENHEIT"))
	 System.out.println("Test Case 25 Passed");
	 else
	 System.out.println("Test Case 25 Failed");
	 }
	 }
	
	 @Then("^Verify FAHRENHEIGHT and CELSIUS are available in Unit of Temperature dropdown$")
	 public void
	 verify_FAHRENHEIGHT_and_CELSIUS_are_available_in_Unit_of_Temperature_dropdown()
	 throws Throwable {
	 // Added by FW. Write code here that turns the phrase above into concrete
	 // actions
	 // throw new PendingException();
	 boolean visible = false;
	 visible =
	 BaseUtil.verifyElementDisplayed(getProps().getProperty("addUserUnitOfTemparature"));
	 if (visible) {
	 List<WebElement> units =
	 BaseUtil.getDropDownValues(getProps().getProperty("addUserUnitOfTemparature"));
	 if (units.get(0).getText().equals("FAHRENHEIT")) {
	 if (units.get(1).getText().equals("CELSIUS"))
	 System.out.println("Test Case 26 Passed");
	 } else if (units.get(0).getText().equals("CELSIUS")) {
	 if (units.get(1).getText().equals("FAHRENHEIT"))
	 System.out.println("Test Case 26 Passed");
	 } else
	 System.out.println("Test Case 26 Failed");
	 }
	
	 }
	
	 @Then("^Verify GPAdmin has following roles$")
	 public void verify_GPAdmin_has_following_roles(DataTable roles) throws
	 Throwable {
	 // Write code here that turns the phrase above into concrete actions
	 // For automatic transformation, change DataTable to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a scalar (String, Integer, Date, enum etc)
	 // throw new PendingException();
	 List<String> roleList = roles.asList(String.class);
	 boolean visible = false;
	 visible =
	 BaseUtil.verifyElementDisplayed(getProps().getProperty("roleField"));
	 if (visible) {
	 List<WebElement> roleItems =
	 BaseUtil.getDropDownValues(getProps().getProperty("roleField"));
	 boolean isExist = false;
	 for (String role : roleList) {
	 isExist = false;
	 for (WebElement item : roleItems) {
	 if (item.getText().trim().equals(role.trim())) {
	 isExist = true;
	 }
	 }
	 if (!isExist) {
	 System.out.println("Test Case 28 Failed");
	 break;
	 }
	 }
	 if (isExist)
	 System.out.println("Test Case 28 Passed");
	 }
	 }
	
//	 @Then("^Verify GPSupport has following roles$")
//	 public void verify_GPSupprt_has_following_roles(DataTable roles) throws
//	 Throwable {
//	 // Write code here that turns the phrase above into concrete actions
//	 // For automatic transformation, change DataTable to one of
//	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
//	 // E,K,V must be a scalar (String, Integer, Date, enum etc)
//	 // throw new PendingException();
//	 // Login as GP Support
//	 TestBase.getLoginPage();
//	 userPage.explicitWait(1000);
//	 userPage.enterText(getProps().getProperty("adminUserName_Locator"),
//	 userPageObjectTestData("addUserGPSupportUser"));
//	 userPage.enterText(getProps().getProperty("adminUserPassword_Locator"),
//	 DEFAULT_USER_PASSWORD);
//	 userPage.click(getProps().getProperty("adminLoginbutton_Locator"));
//	 userPage.clickAndWait(getProps().getProperty("userTab"));
//	 userPage.waitForSpinner();
//	 WebDriverWait wait = new WebDriverWait(TestBase.getWebDriver(), 1000);
//	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@ng-click='addUser()']")));
//	 // userPage.explicitWait(10000);
//	 BaseUtil.clickAndWait(getProps().getProperty("addUserButton"));
//	 userPage.explicitWait(1000);
//	 List<String> roleList = roles.asList(String.class);
//	 boolean visible = false;
//	 visible =
//	 BaseUtil.verifyElementDisplayed(getProps().getProperty("roleField"));
//	 if (visible) {
//	 List<WebElement> roleItems =
//	 BaseUtil.getDropDownValues(getProps().getProperty("roleField"));
//	 boolean isExist = false;
//	 for (String role : roleList) {
//	 isExist = false;
//	 for (WebElement item : roleItems) {
//	 if (item.getText().trim().equals(role.trim())) {
//	 isExist = true;
//	 }
//	 }
//	 if (!isExist) {
//	 System.out.println("Test Case 29 Failed");
//	 break;
//	 }
//	 }
//	 if (isExist)
//	 System.out.println("Test Case 29 Passed");
//	 }
//	 TestBase.getLoginPage();
//	 userPage.explicitWait(1000);
//	
//	 if (configGridpoint("env").equals("qa"))
//	 userPage.enterText(getProps().getProperty("adminUserName_Locator"),
//	 configObjectData("qa_username"));
//	 else
//	 userPage.enterText(getProps().getProperty("adminUserName_Locator"),
//	 configObjectData("dev_username"));
//	 userPage.enterText(getProps().getProperty("adminUserPassword_Locator"),
//	 configObjectData("dev_password"));
//	 userPage.click(getProps().getProperty("adminLoginbutton_Locator"));
//	 userPage.explicitWait(5000);
//	 userPage.clickAndWait(getProps().getProperty("userTab"));
//	 BaseUtil.waitForSpinner();
//	 BaseUtil.clickAndWait(getProps().getProperty("addUserButton"));
//	 }
//	
	
	// @When("^Special charachters \"(.*?)\" are used user gets created successfully$")
	// public void
	// special_charachters_are_used_user_gets_created_successfully(String arg1)
	// throws Throwable {
	// // Added by FW. Write code here that turns the phrase above into concrete
	// // actions
	// // throw new PendingException();
	// try {
	// ManageTestData objDelTestdata = new ManageTestData();
	// String userName =
	// userPageObjectTestData("addUserNameTestDataWithSpecialCharacter");
	// objDelTestdata.manageTestData("User", userName);
	// userPage.enterText(getProps().getProperty("userNameField"),
	// userPageObjectTestData("addUserNameTestDataWithSpecialCharacter"));
	// userPage.enterText(getProps().getProperty("firstNameField"),
	// userPageObjectTestData("addUserFirstNameTestData"));
	// userPage.enterText(getProps().getProperty("lastNameField"),
	// userPageObjectTestData("addUserLastNameTestData"));
	// userPage.enterText(getProps().getProperty("emailField"),
	// userPageObjectTestData("addUserEmailTestData"));
	// userPage.selectDropDownByValue(getProps().getProperty("roleField"),
	// userPageObjectTestData("addUserRoleTestData"));
	// userPage.selectDropDownByValue(getProps().getProperty("addUserUnitOfTemparature"),
	// userPageObjectTestData("addUserUnitOfTemparatureTestData"));
	// userPage.explicitWait(1000);
	// userPage.click(getProps().getProperty("saveButton"));
	// userPage.explicitWait(2000);
	// userPage.enterText(getProps().getProperty("userSearchField"),
	// userPageObjectTestData("addUserNameTestDataWithSpecialCharacter"));
	// userPage.click(getProps().getProperty("userSearchButton"));
	// userPage.explicitWait(4000);
	// WebElement table =
	// userPage.getElementAfterLoaded(getProps().getProperty("userGrid"));//
	// .getWebDriver().findElement(By.id(getProps().getProperty("userTable")));
	// System.out.println("Element Name : " + table.getTagName());
	// WebElement parent = table.findElement(By.className("ui-grid-canvas"));
	// System.out.println("Element Name : " + parent.getTagName());
	// List<WebElement> rows = parent.findElements(By.className("ui-grid-row"));
	// System.out.println("Row count : " + rows.size());
	// if (rows.size() == 1)
	// System.out.println("Test Case 19 Passed");
	// else
	// System.out.println("Test Case 19 Failed");
	// } catch (Exception ex) {
	// System.out.println("Test Case 19 Failed");
	// System.out.println(ex.getStackTrace().toString());
	// System.out.println(ex.getMessage().toString());
	// System.out.println(ex.toString());
	// }
	// }
	//
	// @Then("^Assigning User capabilitirs and site permission are not allowed$")
	// public void
	// assigning_User_capabilitirs_and_site_permission_are_not_allowed() throws
	// Throwable {
	// // Added by FW. Write code here that turns the phrase above into concrete
	// // actions
	// // throw new PendingException();
	// System.out.println("Not coded");
	// }
	//
	// @Then("^User is not able to login for user with special characters$")
	// public void user_is_not_able_to_login_for_user_with_special_characters()
	// throws Throwable {
	// // Added by FW. Write code here that turns the phrase above into concrete
	// // actions
	// // throw new PendingException();
	// // System.out.println("Not coded");
	// ManageTestData objDelTestdata = new ManageTestData();
	// objDelTestdata.updateUserPassword(userPageObjectTestData("addUserNameTestDataWithSpecialCharacter"));
	// userPage.explicitWait(1000);
	// TestBase.getLoginPage();
	// userPage.explicitWait(1000);
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// userPageObjectTestData("addUserNameTestDataWithSpecialCharacter"));
	// userPage.enterText(getProps().getProperty("adminUserPassword_Locator"),
	// DEFAULT_USER_PASSWORD);
	// userPage.click(getProps().getProperty("adminLoginbutton_Locator"));
	// WebElement errMessage =
	// userPage.getElementAfterLoaded(getProps().getProperty("userBadCredential"));
	// if (errMessage.getText().length() > 0) {
	// System.out.println("Test case passed.");
	// objDelTestdata
	// .deleteTestUser("%" +
	// userPageObjectTestData("addUserNameTestDataWithSpecialCharacter") +
	// "%");
	// userPage.getElementAfterLoaded(getProps().getProperty("adminUserName_Locator")).clear();
	// if (configGridpoint("env").equals("qa"))
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// configObjectData("qa_username"));
	// else
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// configObjectData("dev_username"));
	// userPage.enterText(getProps().getProperty("adminUserPassword_Locator"),
	// configObjectData("dev_password"));
	// userPage.click(getProps().getProperty("adminLoginbutton_Locator"));
	// userPage.waitForSpinner();
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userTab"));
	// userPage.waitForSpinner();
	// } else
	// System.out.println("Test case failed.");
	//
	// userPage.explicitWait(1000);
	// }
	//
	// @Then("^Add User and set CELSIUS as Unit of Temperature and verify the same unit displayed in edit mode$")
	// public void
	// add_User_and_set_CELSIUS_as_Unit_of_Temperature_and_verify_the_same_unit_displayed_in_edit_mode()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("addUserButton"));
	// userPage.enterText(getProps().getProperty("userNameField"),
	// userPageObjectTestData("addUserNameTempData"));
	// userPage.enterText(getProps().getProperty("firstNameField"),
	// userPageObjectTestData("addUserFirstNameTestData"));
	// userPage.enterText(getProps().getProperty("lastNameField"),
	// userPageObjectTestData("addUserLastNameTestData"));
	// userPage.enterText(getProps().getProperty("emailField"),
	// userPageObjectTestData("addUserEmailTestData"));
	// userPage.selectDropDownByValue(getProps().getProperty("roleField"),
	// userPageObjectTestData("addUserRoleTestData"));
	// userPage.selectDropDownByValue(getProps().getProperty("addUserUnitOfTemparature"),
	// userPageObjectTestData("addUserUOTCelsius"));
	// userPage.clickAndWait(getProps().getProperty("saveButton"));
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	// userPage.explicitWait(1000);
	// // userPage.clickOnUserPage(getProps().getProperty("usersSearchField"),
	// // );
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameTempData"),
	// );
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameTempData"),
	// );
	// if
	// (userPage.getSelectedDropDownValues(getProps().getProperty("addUserUnitOfTemparature"))
	// .equals(userPageObjectTestData("addUserUOTCelsius")))
	// System.out.println("Test Case 58 Passed");
	// else
	// System.out.println("Test Case 58 Failed");
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	// ManageTestData objDelTestdata = new ManageTestData();
	// objDelTestdata.deleteTestUser("%" +
	// userPageObjectTestData("addUserNameTempData") + "%");
	// }
	//
	// @Then("^Add User and set FAHRENHEIT as Unit of Temperature and verify the same unit displayed in edit mode$")
	// public void
	// add_User_and_set_FAHRENHEIT_as_Unit_of_Temperature_and_verify_the_same_unit_displayed_in_edit_mode()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("addUserButton"));
	// userPage.enterText(getProps().getProperty("userNameField"),
	// userPageObjectTestData("addUserNameTempData"));
	// userPage.enterText(getProps().getProperty("firstNameField"),
	// userPageObjectTestData("addUserFirstNameTestData"));
	// userPage.enterText(getProps().getProperty("lastNameField"),
	// userPageObjectTestData("addUserLastNameTestData"));
	// userPage.enterText(getProps().getProperty("emailField"),
	// userPageObjectTestData("addUserEmailTestData"));
	// userPage.selectDropDownByValue(getProps().getProperty("roleField"),
	// userPageObjectTestData("addUserRoleTestData"));
	// userPage.selectDropDownByValue(getProps().getProperty("addUserUnitOfTemparature"),
	// userPageObjectTestData("addUserUOTFahrenheit"));
	// userPage.clickAndWait(getProps().getProperty("saveButton"));
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	// userPage.explicitWait(1000);
	// // userPage.clickOnUserPage(getProps().getProperty("usersSearchField"),
	// // );
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameTempData"),
	// );
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameTempData"),
	// );
	// if
	// (userPage.getSelectedDropDownValues(getProps().getProperty("addUserUnitOfTemparature"))
	// .equals(userPageObjectTestData("addUserUOTFahrenheit")))
	// System.out.println("Test Case 59 Passed");
	// else
	// System.out.println("Test Case 59 Failed");
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	//
	// // Not deleting user as we need to verify user's change by login
	//
	// // objDelTestdata.deleteTestUser("%" +
	// // userPageObjectTestData("addUserNameTempData") + "%", testBase);
	// }
	//
	// @Then("^Verify if user changes the unit of temperaturethen Admin User sees the change$")
	// public void
	// verify_if_user_changes_the_unit_of_temperaturethen_Admin_User_sees_the_change()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // FW
	// userPage.explicitWait(1000);
	// ManageTestData objDelTestdata = new ManageTestData();
	// objDelTestdata.updateTempUserPassword(userPageObjectTestData("addUserNameTempData"));
	// userPage.explicitWait(1000);
	// TestBase.getLoginPage();
	// userPage.explicitWait(1000);
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// userPageObjectTestData("addUserNameTempData"));
	// userPage.enterText(getProps().getProperty("adminUserPassword_Locator"),
	// DEFAULT_USER_PASSWORD);
	// userPage.click(getProps().getProperty("adminLoginbutton_Locator"));
	// userPage.waitForSpinner();
	// userPage.enterText(userPageObjectTestData("resetPasswordOld"),
	// DEFAULT_USER_PASSWORD);
	// userPage.enterText(userPageObjectTestData("resetPasswordNew"),
	// TEMP_USER_PASSWORD);
	// userPage.enterText(userPageObjectTestData("resetPasswordConfirm"),
	// TEMP_USER_PASSWORD);
	// userPage.clickAndWait(userPageObjectTestData("resetPasswordSubmit"));
	// userPage.waitForSpinner();
	// userPage.explicitWait(1000);
	// userPage.getElementAfterLoaded(getProps().getProperty("adminUserPassword_Locator")).clear();
	// userPage.enterText(getProps().getProperty("adminUserPassword_Locator"),
	// TEMP_USER_PASSWORD);
	// userPage.click(getProps().getProperty("adminLoginbutton_Locator"));
	// userPage.waitForSpinner();
	// userPage.clickAndWait(getProps().getProperty("userTab"));
	// userPage.clickAndWait(userPageObjectTestData("extendedMenu"));
	// userPage.clickAndWait(userPageObjectTestData("portalMenu"));
	// userPage.waitForSpinner();
	// userPage.clickAndWait(userPageObjectTestData("extendedMenu"));
	// userPage.clickAndWait(userPageObjectTestData("portalSetting"));
	// userPage.explicitWait(1000);
	// userPage.selectDropDownByValue(userPageObjectTestData("settingUnitDropdown"),
	// userPageObjectTestData("addUserUOTCelsius"));
	// userPage.clickAndWait(userPageObjectTestData("settingSaveButton"));
	// userPage.waitForSpinner();
	//
	// TestBase.getLoginPage();
	// userPage.explicitWait(1000);
	// if (configGridpoint("env").equals("qa"))
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// configObjectData("qa_username"));
	// else
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// configObjectData("dev_username"));
	// userPage.enterText(getProps().getProperty("adminUserPassword_Locator"),
	// configObjectData("dev_password"));
	// userPage.click(getProps().getProperty("adminLoginbutton_Locator"));
	// userPage.waitForSpinner();
	//
	// userPage.clickAndWait(getProps().getProperty("userTab"));
	// userPage.waitForSpinner();
	// userPage.explicitWait(1000);
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameTempData"),
	// );
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameTempData"),
	// );
	// userPage.waitForSpinner();
	// userPage.explicitWait(1000);
	// if
	// (userPage.getSelectedDropDownValues(getProps().getProperty("addUserUnitOfTemparature"))
	// .equals(userPageObjectTestData("addUserUOTCelsius")))
	// System.out.println("Test Case 60 Passed");
	// else
	// System.out.println("Test Case 60 Failed");
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	// userPage.explicitWait(1000);
	// objDelTestdata.deleteTestUser("%" +
	// userPageObjectTestData("addUserNameTempData") + "%");
	// }
	//
	// @Then("^Verify  on setting popup Temperature Unit does not reflect changes automatically as admin set in the admin UI if setting popup is open before change$")
	// public void
	// verify_On_setting_popup_Temperature_Unit_does_not_reflect_changes_automatically_as_admin_set_in_the_admin_UI_if_setting_popup_is_open_before_change()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // added by FW
	// // Creating a new web driver which opens a new browser and login as cust.
	// // analyst and opens the setting dropdown to know the selected
	// temperature
	// WebDriver second = TestBase.initializeWebDriver("chrome");
	// @SuppressWarnings("unused")
	// WebDriverWait secondDriverWait = new WebDriverWait(second,
	// TimeUnit.MILLISECONDS.toSeconds(PathConstants.defaultTimeout));
	// if (configGridpoint("env").equals("dev"))
	// second.get(configEnvironment("dev"));
	// else if (configGridpoint("env").equals("qa"))
	// second.get(configEnvironment("qa"));
	// else if (configGridpoint("env").equals("staging"))
	// second.get(configEnvironment("staging"));
	// newPage.explicitWait(1000);
	// newPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// userPageObjectTestData("addUserNameCustAnalyst"));
	// newPage.enterText(getProps().getProperty("adminUserPassword_Locator"),
	// DEFAULT_USER_PASSWORD);
	// newPage.click(getProps().getProperty("adminLoginbutton_Locator"));
	// newPage.waitForSpinner();
	// newPage.explicitWait(1000);
	// newPage.clickAndWait(userPageObjectTestData("extendedMenu"));
	// newPage.explicitWait(1000);
	// System.out.println("Extended Menu Clicked");
	// // newPage.clickAndWait(userPageObjectTestData("portalMenu"));
	// // newPage.waitForSpinner();
	// // System.out.println("Portal Menu Clicked");
	// // newPage.clickAndWait(userPageObjectTestData("extendedMenu"));
	// // newPage.explicitWait(1000);
	// System.out.println("Extended Menu Clicked");
	// newPage.clickAndWait(userPageObjectTestData("portalSetting"));
	// newPage.explicitWait(1000);
	// System.out.println("Portal Setting Clicked");
	// String currentWindowHandle = TestBase.getWebDriver().getWindowHandle();
	// TestBase.getWebDriver().switchTo().window(currentWindowHandle);
	// // Using the initial web driver where admin is loggedin and verifying the
	// unit
	// // of temperature for the same cust. analyst user.
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameCustAnalyst"),
	// );
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// System.out.println("Search Button Clicked");
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameCustAnalyst"),
	// );
	// userPage.waitForSpinner();
	// userPage.selectDropDownByValue(getProps().getProperty("addUserUnitOfTemparature"),
	// userPageObjectTestData("addUserUOTCelsius"));
	// System.out.println("Temparature Unit Changed");
	// userPage.click(getProps().getProperty("saveButton"));
	// userPage.explicitWait(2000);
	// System.out.println("Save Button Clicked");
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	// userPage.explicitWait(1000);
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameCustAnalyst"),
	// );
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// System.out.println("Search Button Clicked again");
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameCustAnalyst"),
	// );
	// userPage.waitForSpinner();
	// System.out.println("Selected User from grid");
	// // Retrieving the data from other browser...
	// String temperature =
	// newPage.getSelectedDropDownValues(userPageObjectTestData("settingUnitDropdown"));
	// System.out.println("Selected Unit is " + temperature);
	//
	// if
	// (userPage.getSelectedDropDownValues(getProps().getProperty("addUserUnitOfTemparature")).equals(temperature))
	// System.out.println("Test Case 63_B Failed");
	// else
	// System.out.println("Test Case 63_B Passed");
	//
	// // Adding the code of next test case 63_A here so that repetition of code
	// is
	// // reduced.
	// newPage.clickAndWait(userPageObjectTestData("settingCancelButton"));
	// newPage.clickAndWait(userPageObjectTestData("extendedMenu"));
	// newPage.explicitWait(1000);
	// newPage.clickAndWait(userPageObjectTestData("portalSetting"));
	// newPage.explicitWait(1000);
	// if
	// (newPage.getSelectedDropDownValues(userPageObjectTestData("settingUnitDropdown")).equals("CELSIUS"))
	// System.out.println("Test Case 63_A Passed");
	// else
	// System.out.println("Test Case 63_A Failed");
	// // Resetting the unit with the previous value
	// userPage.selectDropDownByValue(getProps().getProperty("addUserUnitOfTemparature"),
	// userPageObjectTestData("addUserUOTFahrenheit"));
	// System.out.println("Temparature Unit Changed");
	// userPage.click(getProps().getProperty("saveButton"));
	// userPage.explicitWait(2000);
	// System.out.println("Save Button Clicked");
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	// userPage.explicitWait(1000);
	// second.quit();
	// }
	//
	// @Then("^Verify  on setting popup Temperature Unit reflects changes automatically$")
	// public void
	// verify_On_setting_popup_Temperature_Unit_reflects_changes_automatically_as_admin_set_in_the_admin_UI_if_setting_popup_is_open_after_changes_made()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // added by FW
	// // DO NOT DELETE this signature. Code has been shifted to 63_B test case
	// to
	// // reduce repetition
	// }
	//
	// @Then("^adding user for User Page$")
	// public void adding_user_for_User_Page() throws Throwable {
	// }
	//
	// @Then("^verify if the user has been successfully created and an email is sent to user containing username, password and URL and try to login with temp credentials$")
	// public void
	// verify_if_the_user_has_been_successfully_created_and_an_email_is_sent_to_user_containing_username_password_and_URL()
	// throws Throwable {
	// userPage.explicitWait(TestBase.getTimeout());
	// EmailReader emailReader = new EmailReader();
	// HashMap<String, String> usernamePassword = emailReader.execute(new
	// Date().toString());
	// userPage.enterText(getProps().getProperty("userPage_UserName_Locator"),
	// userPageObjectTestData("addUserNameTestData"));
	// userPage.enterText(getProps().getProperty("userPage_Password_Locator"),
	// usernamePassword.get(userPageObjectTestData("addUserNameTestData")));
	// userPage.click(getProps().getProperty("userPage_Loginbutton_Locator"));
	// userPage.waitForSpinner();
	// }
	//
	// @Then("^verify that reset password popup is displayed$")
	// public void verify_that_reset_password_popup_is_displayed() {
	// userPage.assertElementDisplayed(getProps().getProperty("userPageResetPasswordCancelButton"),
	// "Reset Password Window not displayed");
	// userPage.assertElementDisplayed(getProps().getProperty("userPageResetPasswordSubmitButton"),
	// "Reset Password Window not displayed");
	// }
	//
	// @Then("^enter the \"(.*?)\" password in reset password popup$")
	// public void enter_the_updated_password_in_reset_password_popup(String
	// passwordType) {
	// if (passwordType.equalsIgnoreCase("valid")) {
	// userPage.enterText(getProps().getProperty("userPageResetPasswordNewPasswordField"),
	// userPageObjectTestData("addUserPasswordTestData"));
	// userPage.enterText(getProps().getProperty("userPageResetPasswordConfirmPasswordField"),
	// userPageObjectTestData("addUserPasswordTestData"));
	// } else if (passwordType.equalsIgnoreCase("invalid")) {
	// userPage.enterText(getProps().getProperty("userPageResetPasswordNewPasswordField"),
	// userPageObjectTestData("addUserInvalidPasswordTestData"));
	// userPage.enterText(getProps().getProperty("userPageResetPasswordConfirmPasswordField"),
	// userPageObjectTestData("addUserInvalidPasswordTestData"));
	// } else if (passwordType.equalsIgnoreCase("not match")) {
	// userPage.enterText(getProps().getProperty("userPageResetPasswordNewPasswordField"),
	// "12345");
	// userPage.enterText(getProps().getProperty("userPageResetPasswordConfirmPasswordField"),
	// "qwerty");
	// }
	// }
	//
	 @When("^click on \"(.*?)\" button on reset password popup$")
	 public void click_on_button_on_reset_password_popup(String buttonName)
	 throws Throwable {
	 String message = "Failed to click on button :" + buttonName + " " +
	 " on reset password popup";
	 Assert.assertTrue(message, userPage.clickOperationOnUserPage(buttonName +
	 "_Reset"));
	 userPage.explicitWait(2000);
	 }
	
	// @Then("^verify that user is back to login page$")
	// public void verify_that_user_is_back_to_login_page() {
	// userPage.assertElementDisplayed(getProps().getProperty("userPage_Loginbutton_Locator"),
	// "Login page is not displayed after user click on cancel button on reset password popup.");
	// }
	//
	// @Then("^click on \"(.*?)\" button on login page$")
	// public void click_on_button_on_login_page(String buttonName) {
	// userPage.clickAndWait(getProps().getProperty("userPage_Loginbutton_Locator"));
	// }
	//
	// @Then("^verify that message \"(.*?)\" is displayed after successfull password reset$")
	// public void
	// verify_that_message_is_displayed_after_successfull_password_reset(String
	// message) {
	// Assert.assertTrue("Success message after reset password is not displayed",
	// userPage.getElementText(getProps().getProperty("userPageSuccessfulResetPassword")).contains(message));
	// }
	//
	// @When("^click on \"(.*?)\" selector on edit user popup$")
	// public void click_on_selector_on_edit_user_popup(String roleSelector)
	// throws Throwable {
	// String message = "Failed to click on selector :" + roleSelector + " " +
	// " on Edit User Page";
	// Assert.assertTrue(message, userPage.selectValueFromDropDown(roleSelector,
	// ));
	// }
	//
	// @When("^select \"(.*?)\" from drop-down options$")
	// public void select_from_drop_down_options(String roleType) throws
	// Throwable {
	// String message = "Failed to click on role type :" + roleType + " " +
	// " on User Page";
	// Assert.assertTrue(message, userPage.selectValueFromDropDown(roleType,
	// ));
	// }
	//
	// @Then("^role of \"(.*?)\" is changed to \"(.*?)\"$")
	// public void role_of_is_changed_to(String arg1, String arg2) throws
	// Throwable {
	// boolean roleTypeCheck =
	// userPage.verifyElementDisplayed(getProps().getProperty("editUserRoleType"));
	// Assert.assertTrue("Edit user role type is not displayed", roleTypeCheck);
	// }
	//
	// @When("^on the first record \"(.*?)\" and \"(.*?)\" link should be disabled$")
	// public void on_the_first_record_and_link_should_be_disabled(String arg1,
	// String arg2) throws Throwable {
	// // Added by FW. Write code here that turns the phrase above into concrete
	// // actions
	// // throw new PendingException();
	// System.out.println("Not coded");
	// }
	//
	// @When("^on the last record \"(.*?)\" and \"(.*?)\" link should be disabled$")
	// public void on_the_last_record_and_link_should_be_disabled(String arg1,
	// String arg2) throws Throwable {
	// // Added by FW. Write code here that turns the phrase above into concrete
	// // actions
	// // throw new PendingException();
	// System.out.println("Not coded");
	// }
	//
	//
	// @Then("^verify that the \"(.*?)\" Section have the following default values$")
	// public void
	// verify_that_the_Section_have_the_following_default_values(String arg1,
	// List<String> defaultValues)
	// throws Throwable {
	// for (String elementName : defaultValues) {
	// String message = elementName + "should be displayed ";
	// Assert.assertTrue(message,
	// userPage.verifyDefaultValuesOfUserProperties(elementName,
	// ));
	// }
	// }
	//
	 @When("^click on \"(.*?)\" button on confirmation box on edit user popup$")
	 public void click_on_button_on_confirmation_box_on_edit_user_popup(String sButtonName) throws Throwable {
	 String message = "Failed to click on button :" + sButtonName + " " +
	 " on User Page";
	 Assert.assertTrue(message, userPage.clickOnUserPopup(sButtonName
	 ));
	 }
	
	 @Then("^user should remain on edit user popup$")
	 public void user_should_remain_on_edit_user_popup() throws Throwable {
	 Assert.assertTrue("Add virtual popup is displayed",
	 BaseUtil.isElementEnabled(By.xpath(getProps().getProperty("cancelButton").split("===")[1])));
	 }
	//
	//
	// @When("^logged in Admin UI with following user$")
	// public void logged_in_Admin_UI_with_newly_created_user(DataTable table)
	// throws Throwable {
	// userPage.explicitWait(2000);
	// List<Map<String, String>> lstData = table.asMaps(String.class,
	// String.class);
	// userPage.enterText(getProps().getProperty("userPage_UserName_Locator"),
	// lstData.get(0)("username").trim());
	// userPage.enterText(getProps().getProperty("userPage_Password_Locator"),
	// lstData.get(0)("password").trim());
	// userPage.click(getProps().getProperty("userPage_Loginbutton_Locator"));
	// userPage.waitForSpinner();
	// if
	// (!lstData.get(0)("username").trim().equalsIgnoreCase("UserPage_UserBot1"))
	// {
	// userPage.assertElementDisplayed(getProps().getProperty("optionDropDown"),
	// "Not able to login with given credentials");
	// }
	// }
	//
	// @Then("^A validation message \"(.*?)\" should be displayed$")
	// public void a_validation_message_should_be_displayed(String
	// validationMessage) throws Throwable {
	// Assert.assertTrue("User is disabled",
	// userPage.checkValidationMessage(getProps().getProperty,
	// validationMessage));
	// }
	//
	// @When("^check \"(.*?)\" checkbox on edit user popup$")
	// public void check_checkbox_on_edit_user_popup(String arg1) throws
	// Throwable {
	// userPage.checkCheckBox(getProps().getProperty("userEnabledCheckBox"));
	// userPage.explicitWait(1000);
	// }
	//
	// @When("^change the \"(.*?)\" with \"(.*?)\" on edit user popup$")
	// public void change_the_with_on_edit_user_popup(String arg1, String
	// localType) throws Throwable {
	// String message = "Failed to select on local type :" + localType + " " +
	// " on User Page";
	// Assert.assertTrue(message, userPage.selectValueFromDropDown(localType,
	// ));
	// }
	//
	// // @Then("^create another user \"(.*?)\" as test data for user page$")
	// // public void create_another_user_as_test_data_for_user_page(String
	// username) throws IOException {
	// // ManageTestData testData = new ManageTestData();
	// // try {
	// // testData.manageTestData("User_Insert", username);
	// // } catch (SQLException e) {
	// // e.printStackTrace();
	// // Assert.fail("Error while creating test user " + username +
	// " for user page test : " + e.getMessage());
	// // } catch (IOException e) {
	// // e.printStackTrace();
	// // Assert.fail("Error while creating test user " + username +
	// " for user page test : " + e.getMessage());
	// // }
	// // }
	//
	//
	// @Then("^click on portal button which navigate to HTML Portal$")
	// public void click_on_portal_button_which_navigate_to_HTML_Portal() throws
	// Throwable {
	// userPage.waitForSpinner();
	// if
	// (!userPage.checkIfClickable(getProps().getProperty("optionDropDown")))
	// {
	// Assert.fail("Not able to click on portal navigator.");
	// }
	// userPage.clickElement(getProps().getProperty("optionDropDown"));
	// userPage.clickElement(getProps().getProperty("PortalNavigate"));
	//
	// String message = "Error while navigating to Portal";
	// userPage.assertElementDisplayed(getProps().getProperty("Home"),
	// message);
	// }
	//
	// @Given("^click on Admin button which navigate to Admin UI$")
	// public void click_on_Admin_button_which_navigate_to_Admin_UI() throws
	// Throwable {
	// userPage.waitForSpinner(20000);
	// if
	// (!userPage.checkIfClickable(getProps().getProperty("optionDropDown")))
	// {
	// Assert.fail("Not able to click on Admin navigator.");
	// }
	// userPage.clickElement(getProps().getProperty("optionDropDown"));
	// userPage.clickElement(getProps().getProperty("adminNavigate"));
	// userPage.waitForSpinner(10000);
	// String message = "Error while navigating to Admin UI";
	// userPage.assertElementDisplayed(getProps().getProperty("userTab"),
	// message);
	//
	// }
	//
	//
	// @Then("^Click on Permission accordion and verify Add button is disabled when Add All button is clicked$")
	// public void
	// click_on_Permission_accordion_and_verify_Add_button_is_disabled_when_Add_All_button_is_clicked()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // Created by FW
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.clickAndWait(getProps().getProperty("editUserPopupOKButton"));
	// userPage.clickAndWait(getProps().getProperty("userTab"));
	// userPage.waitForSpinner();
	// userPage.clickOperationOnUserPage(getProps().getProperty("usersSearchField"),
	// );
	// userPage.enterText_UserPage("USER_SEARCH",
	// getProps().getProperty("searchUserName"));
	// userPage.explicitWait(2000);
	// userPage.clickOperationOnUserPage("SEARCH");
	// userPage.selectUserFromGrid_UserPage(getProps().getProperty("searchUserName"),
	// );
	// userPage.clickAndWait(getProps().getProperty("editUserModelPermissionAccordion"));
	// Actions actions = new Actions(TestBase.getWebDriver());
	// WebElement targetElement =
	// userPage.getElementAfterLoaded(getProps().getProperty("editUserPermissionAddAll"));
	// actions.moveToElement(targetElement).perform();
	// userPage.click(getProps().getProperty("editUserPermissionAddAll"));
	// userPage.waitForSpinner(1000);
	// if
	// (!userPage.verifyElementEnabled(getProps().getProperty("editUserPermissionAdd")))
	// System.out.println("Test Case 38 Passed");
	// else
	// System.out.println("Test Case 38 Failed");
	// userPage.explicitWait(1000);
	// }
	//
	// @Then("^Verify on Permission accordion multiple selection by SHIFT plus CLICK and then REMOVE button click deletes selected items$")
	// public void
	// verify_on_Permission_accordion_multiple_selection_by_SHIFT_plus_CLICK_and_then_REMOVE_button_click_deletes_selected_items()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // Created by FW
	// WebElement listbox =
	// userPage.getElementAfterLoaded(getProps().getProperty("PermissionSelectedTenantValue"));//
	// .getWebDriver().findElement(By.id(getProps().getProperty("userTable")));
	// System.out.println("Element Name : " + listbox.getTagName());
	// List<WebElement> tenants = listbox.findElements(By.xpath("*"));
	// System.out.println("Row count : " + tenants.size());
	// WebElement firstTenant = tenants.get(0);
	// System.out.println("Element Name : " + firstTenant.getTagName());
	// WebElement siteRoot = firstTenant.findElement(By.className("sitePath"));
	// List<WebElement> items = siteRoot.findElements(By.xpath("*"));
	// System.out.println("Row count : " + items.size());
	// if (items.size() > 2) {
	// WebElement secondLastitem = items.get(items.size() - 2);
	// secondLastitem.click();
	// WebElement lastitem = items.get(items.size() - 1);
	// Actions shiftClick = new Actions(TestBase.getWebDriver());
	// shiftClick.keyDown(Keys.SHIFT).click(lastitem).keyUp(Keys.SHIFT).perform();
	// userPage.explicitWait(2000);
	// userPage.clickAndWait(getProps().getProperty("RemoveButton"));
	// List<WebElement> itemsAfterDelete = siteRoot.findElements(By.xpath("*"));
	// System.out.println("Row count : " + itemsAfterDelete.size());
	// if (itemsAfterDelete.size() == items.size() - 2)
	// System.out.println("Test Case 51 Passed");
	// else
	// System.out.println("Test Case 51 Failed");
	// }
	// userPage.explicitWait(5000);
	// }
	//
	// @Then("^Verify that GP Admin can Unlock any user$")
	// public void verify_that_GP_Admin_can_Unlock_any_user() throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// TestBase.getLoginPage();
	// userPage.explicitWait(1000);
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// userPageObjectTestData("addUserNameCustAnalyst"));
	// for (int i = 0; i <= 6; i++) {
	// userPage.enterText(getProps().getProperty("adminUserPassword_Locator"),
	// TEMP_USER_PASSWORD);
	// userPage.click(getProps().getProperty("adminLoginbutton_Locator"));
	// userPage.waitForSpinner();
	// }
	// if (configGridpoint("env").equals("qa"))
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// configObjectData("qa_username"));
	// else
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// configObjectData("dev_username"));
	// userPage.enterText(getProps().getProperty("adminUserPassword_Locator"),
	// configObjectData("dev_password"));
	// userPage.click(getProps().getProperty("adminLoginbutton_Locator"));
	// userPage.waitForSpinner();
	//
	// userPage.clickAndWait(getProps().getProperty("userTab"));
	// userPage.waitForSpinner();
	// userPage.explicitWait(1000);
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameCustAnalyst"),
	// );
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameCustAnalyst"),
	// );
	// userPage.waitForSpinner();
	// userPage.explicitWait(1000);
	// System.out.println(
	// userPage.getElementAfterLoaded(getProps().getProperty("editLockedField")).getAttribute("value"));
	// if
	// (userPage.getElementAfterLoaded(getProps().getProperty("editLockedField")).getAttribute("value")
	// .equals("Yes")) {
	// userPage.clickAndWait(getProps().getProperty("editUnlockButton"));
	// userPage.clickAndWait(getProps().getProperty("saveButton"));
	// TestBase.getLoginPage();
	// userPage.explicitWait(1000);
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// userPageObjectTestData("addUserNameCustAnalyst"));
	// userPage.enterText(getProps().getProperty("adminUserPassword_Locator"),
	// DEFAULT_USER_PASSWORD);
	// userPage.click(getProps().getProperty("adminLoginbutton_Locator"));
	// userPage.waitForSpinner();
	// System.out.println("Test Case 66 Passed");
	// } else
	// System.out.println("Test Case 66 Failed");
	// // Relogin as admin
	// TestBase.getLoginPage();
	// userPage.explicitWait(1000);
	// if (configGridpoint("env").equals("qa"))
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// configObjectData("qa_username"));
	// else
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// configObjectData("dev_username"));
	// userPage.enterText(getProps().getProperty("adminUserPassword_Locator"),
	// configObjectData("dev_password"));
	// userPage.click(getProps().getProperty("adminLoginbutton_Locator"));
	// userPage.waitForSpinner();
	//
	// userPage.clickAndWait(getProps().getProperty("userTab"));
	// userPage.waitForSpinner();
	// }
	//
	// @Then("^Audit Page should show the updates for the user$")
	// public void audit_Page_should_show_the_updates_for_the_user() throws
	// Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// System.out.println("Initiating 76");
	// userPage.explicitWait(1000);
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameCustAnalyst"),
	// );
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameCustAnalyst"),
	// );
	// userPage.waitForSpinner();
	// System.out.println("In Edit dialog");
	// // Changing Temperature Unit
	// String tmpUnit = "";
	// if
	// (userPage.getSelectedDropDownValues(getProps().getProperty("addUserUnitOfTemparature")).equals("CELSIUS"))
	// {
	// userPage.selectDropDownByValue(getProps().getProperty("addUserUnitOfTemparature"),
	// userPageObjectTestData("addUserUOTFahrenheit"));
	// tmpUnit = "FAHRENHEIT";
	// } else {
	// userPage.selectDropDownByValue(getProps().getProperty("addUserUnitOfTemparature"),
	// userPageObjectTestData("addUserUOTCelsius"));
	// tmpUnit = "CELSIUS";
	// }
	// System.out.println("Changed selection");
	// userPage.click(getProps().getProperty("saveButton"));
	// userPage.explicitWait(2000);
	// System.out.println("Saved selection");
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.explicitWait(1000);
	// System.out.println("Closed Edit Dialog");
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	// userPage.explicitWait(1000);
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameCustAnalyst"),
	// );
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameCustAnalyst"),
	// );
	// userPage.waitForSpinner();
	// System.out.println("Open Edit Dialog Again");
	// // Expanding Audit Accordian
	// WebElement targetElement =
	// userPage.getElementAfterLoaded(getProps().getProperty("editAuditAccordian"));
	// Actions actions = new Actions(TestBase.getWebDriver());
	// actions.moveToElement(targetElement).perform();
	// userPage.explicitWait(1000);
	// System.out.println("Moved to Audit accordian");
	// userPage.clickAndWait(getProps().getProperty("editAuditAccordian"));
	// userPage.explicitWait(1000);
	// System.out.println("accordian Expanded");
	// WebElement table =
	// userPage.getElementAfterLoaded(getProps().getProperty("auditGrid"));//
	// .getWebDriver().findElement(By.id(getProps().getProperty("userTable")));
	// System.out.println("Element Name : " + table.getTagName());
	// WebElement parent = table.findElement(By.className("ui-grid-canvas"));
	// System.out.println("Element Name : " + parent.getTagName());
	// List<WebElement> rows = parent.findElements(By.className("ui-grid-row"));
	// System.out.println("Number of rows : " + rows.size());
	// List<WebElement> cols =
	// rows.get(0).findElements(By.className("ui-grid-cell"));
	// WebElement latestdate =
	// cols.get(5).findElement(By.className("ui-grid-cell-contents"));
	//
	// DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	// dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	// System.out.println("Date: " + latestdate.getText().substring(0, 10));
	// String target = latestdate.getText().substring(0, 10);
	// System.out.println("Today : " + dateFormatter.format(new Date()));
	// String todayDate = dateFormatter.format(new Date());
	// if (target.equals(todayDate)) {
	// System.out.println("Pass 1");
	// System.out.println(cols.get(3).findElement(By.className("ui-grid-cell-contents")).getText());
	// System.out.println(tmpUnit);
	// if
	// (cols.get(3).findElement(By.className("ui-grid-cell-contents")).getText().equals(tmpUnit))
	// System.out.println("Test Case 76 Passed");
	// else
	// System.out.println("Test Case 76 Failed");
	// } else
	// System.out.println("Test Case 76 Failed");
	// }
	//
	// @Then("^Verify role drop down is showing empty while trying to edit user with level$")
	// public void
	// verify_role_drop_down_is_showing_empty_while_trying_to_edit_user_with_level()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// // Login with GP_SUPPORT ROLE
	// TestBase.getLoginPage();
	// userPage.explicitWait(1000);
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// userPageObjectTestData("addUserNameSupport"));
	// userPage.enterText(getProps().getProperty("adminUserPassword_Locator"),
	// DEFAULT_USER_PASSWORD);
	// userPage.click(getProps().getProperty("adminLoginbutton_Locator"));
	// userPage.waitForSpinner();
	// userPage.clickAndWait(getProps().getProperty("userTab"));
	// userPage.waitForSpinner();
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameAdmin"));
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameAdmin"),
	// );
	// userPage.waitForSpinner();
	// Actions actions = new Actions(TestBase.getWebDriver());
	// WebElement targetElement =
	// userPage.getElementAfterLoaded(getProps().getProperty("CapabilitiesAccordion"));
	// actions.moveToElement(targetElement).perform();
	// userPage.clickAndWait(getProps().getProperty("CapabilitiesAccordion"));
	// if
	// (userPage.getSelectedDropDownValues(getProps().getProperty("addUserRoleField")).equals(""))
	// System.out.println("Test Case 78 Passed");
	// else
	// System.out.println("Test Case 78 Failedsssss");
	// // Resetting
	// TestBase.getLoginPage();
	// userPage.explicitWait(1000);
	// if (configGridpoint("env").equals("qa"))
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// configObjectData("qa_username"));
	// else
	// userPage.enterText(getProps().getProperty("adminUserName_Locator"),
	// configObjectData("dev_username"));
	// userPage.enterText(getProps().getProperty("adminUserPassword_Locator"),
	// configObjectData("dev_password"));
	// userPage.click(getProps().getProperty("adminLoginbutton_Locator"));
	// userPage.waitForSpinner();
	//
	// userPage.clickAndWait(getProps().getProperty("userTab"));
	// userPage.waitForSpinner();
	// }
	//
	// @Then("^Verify Validations for First Name Last Name and Email in edit mode$")
	// public void
	// verify_Validations_for_First_Name_Last_Name_and_Email_in_edit_mode()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// boolean status = false;
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameAdmin"));
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameAdmin"),
	// );
	// userPage.waitForSpinner();
	// String fName =
	// userPage.getElementAfterLoaded(getProps().getProperty("propertiesSectionFirstNameField"))
	// .getAttribute("value");
	// userPage.getElementAfterLoaded(getProps().getProperty("propertiesSectionFirstNameField")).clear();
	// userPage.clickAndWait(getProps().getProperty("saveButton"));
	// System.out.println("Save Button Clicked for First Name");
	// //
	// userPage.clickAndWait(getProps().getProperty("propertiesSectionLastNameField"));
	// if
	// (userPage.getElementAfterLoaded(getProps().getProperty("addUserFirstNameErrorMessage")).getText()
	// .equals("First name cannot be blank.")) {
	// status = true;
	// userPage.enterText(getProps().getProperty("propertiesSectionFirstNameField"),
	// fName);
	// String lName =
	// userPage.getElementAfterLoaded(getProps().getProperty("propertiesSectionLastNameField"))
	// .getAttribute("value");
	// userPage.getElementAfterLoaded(getProps().getProperty("propertiesSectionLastNameField")).clear();
	// userPage.clickAndWait(getProps().getProperty("saveButton"));
	// System.out.println("Save Button Clicked for Last Name");
	// //
	// userPage.clickAndWait(getProps().getProperty("propertiesSectionEmailField"));
	// if
	// (userPage.getElementAfterLoaded(getProps().getProperty("addUserLastNameErrorMessage")).getText()
	// .equals("Last name cannot be blank.")) {
	// status = true;
	// userPage.enterText(getProps().getProperty("propertiesSectionLastNameField"),
	// lName);
	// @SuppressWarnings("unused")
	// String email =
	// userPage.getElementAfterLoaded(getProps().getProperty("propertiesSectionEmailField"))
	// .getAttribute("value");
	// userPage.getElementAfterLoaded(getProps().getProperty("propertiesSectionEmailField")).clear();
	// userPage.click(getProps().getProperty("saveButton"));
	// System.out.println("Save Button Clicked for Email");
	// //
	// userPage.clickAndWait(getProps().getProperty("propertiesSectionLastNameField"));
	// if
	// (userPage.getElementAfterLoaded(getProps().getProperty("addUserEmailErrorMessage")).getText()
	// .equals("Email cannot be blank."))
	// status = true;
	// else
	// status = false;
	// } else
	// status = false;
	// } else
	// status = false;
	//
	// if (status)
	// System.out.println("Test Case 81 Passed");
	// else
	// System.out.println("Test Case 81 Failed");
	// }
	//
	// @Then("^Verify Role Field in Detail Tab is not editable$")
	// public void verify_Role_Field_in_Detail_Tab_is_not_editable() throws
	// Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// WebElement role =
	// userPage.getElementAfterLoaded(getProps().getProperty("roleField"));
	// if (role.getTagName().equals("label"))
	// System.out.println("Test Case 87 Passed");
	// else
	// System.out.println("Test Case 87 Failed");
	//
	// }
	//
	// @Then("^Verify Unit Of Temperature has only two values CELSIUS and FAHRENHEIT$")
	// public void
	// verify_Unit_Of_Temperature_has_only_two_values_CELSIUS_and_FAHRENHEIT()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// List<WebElement> units =
	// userPage.getDropDownValues(getProps().getProperty("addUserUnitOfTemparature"));
	// if (units.size() == 2) {
	// if (units.get(0).getText().equals("FAHRENHEIT")) {
	// if (units.get(1).getText().equals("CELSIUS"))
	// System.out.println("Test Case 88 Passed");
	// } else if (units.get(0).getText().equals("CELSIUS")) {
	// if (units.get(1).getText().equals("FAHRENHEIT"))
	// System.out.println("Test Case 88 Passed");
	// } else
	// System.out.println("Test Case 88 Failed");
	// }
	// }
	//
	// @Then("^Verify Capabilities Accordion has Role Dropdown and given Capabilities checkboxes for GP Admin and GP Support$")
	// public void
	// verify_Capabilities_Accordion_has_Role_Dropdown_and_given_Capabilities_checkboxes_for_GP_Admin_and_GP_Support(
	// DataTable capabilities) throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // For automatic transformation, change DataTable to one of
	// // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	// // E,K,V must be a scalar (String, Integer, Date, enum etc)
	// // throw new PendingException();
	// // By FW
	// // List<String> capabilityLst = capabilities.asList(String.class);
	// @SuppressWarnings("unused")
	// boolean valid = false;
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	// userPage.explicitWait(1000);
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameAdmin"));
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameAdmin"),
	// );
	// userPage.waitForSpinner();
	// Actions actions = new Actions(TestBase.getWebDriver());
	// WebElement targetElement =
	// userPage.getElementAfterLoaded(getProps().getProperty("CapabilitiesAccordion"));
	// actions.moveToElement(targetElement).perform();
	// userPage.clickAndWait(getProps().getProperty("CapabilitiesAccordion"));
	// WebElement capabilityRoot =
	// userPage.getElementAfterLoaded(getProps().getProperty("editUserCapabilityList"));
	// List<WebElement> capabilityLst =
	// capabilityRoot.findElements(By.xpath("*"));
	// System.out.println("List Size = " + capabilityLst.size());
	// if (isCapabilitiesInRange(capabilityLst, 1))
	// System.out.println("Test Case 90 Passed");
	// else
	// System.out.println("Test Case 90 Failed");
	// // For GP_SUPPORT
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	// userPage.explicitWait(1000);
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameSupport"),
	// );
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameSupport"),
	// );
	// userPage.waitForSpinner();
	// targetElement =
	// userPage.getElementAfterLoaded(getProps().getProperty("CapabilitiesAccordion"));
	// actions.moveToElement(targetElement).perform();
	// userPage.clickAndWait(getProps().getProperty("CapabilitiesAccordion"));
	// capabilityRoot =
	// userPage.getElementAfterLoaded(getProps().getProperty("editUserCapabilityList"));
	// capabilityLst.clear();
	// capabilityLst = capabilityRoot.findElements(By.xpath("*"));
	// System.out.println("List Size = " + capabilityLst.size());
	// if (isCapabilitiesInRange(capabilityLst, 2))
	// System.out.println("Test Case 91 Passed");
	// else
	// System.out.println("Test Case 91 Failed");
	//
	// }
	//
	// // @Then("^delete the \"(.*?)\" data used as test data for user page$")
	// // public void add_delete_data_used_as_test_data_for_user_page(String
	// dataType, List<String> listAuditTestData)
	// // throws Throwable {
	// // ManageTestData objTestdata = new ManageTestData();
	// // if (dataType.equals("Users")) {
	// // for (String enduserTestData : listAuditTestData) {
	// // isUserPresent = objTestdata.manageTestData("User_Present",
	// enduserTestData);
	// // if (!isUserPresent) {
	// // return;
	// // }
	// // Assert.assertTrue("Data should be deleted ",
	// // objTestdata.manageTestData("Delete_Resources", enduserTestData));
	// // }
	// //
	// // }
	// //
	// // }
	//
	// @Then("^Verify Capabilities Accordion has Role Dropdown and given Capabilities checkboxes for GP Analyst$")
	// public void
	// verify_Capabilities_Accordion_has_Role_Dropdown_and_given_Capabilities_checkboxes_for_GP_Analyst(
	// DataTable arg1) throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // For automatic transformation, change DataTable to one of
	// // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	// // E,K,V must be a scalar (String, Integer, Date, enum etc)
	// // throw new PendingException();
	// // By FW
	// // For GP_SUPPORT
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	// userPage.explicitWait(1000);
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserGPAnalyst"));
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserGPAnalyst"),
	// );
	// userPage.waitForSpinner();
	// Actions actions = new Actions(TestBase.getWebDriver());
	// WebElement targetElement =
	// userPage.getElementAfterLoaded(getProps().getProperty("CapabilitiesAccordion"));
	// actions.moveToElement(targetElement).perform();
	// userPage.clickAndWait(getProps().getProperty("CapabilitiesAccordion"));
	// WebElement capabilityRoot =
	// userPage.getElementAfterLoaded(getProps().getProperty("editUserCapabilityList"));
	// List<WebElement> capabilityLst =
	// capabilityRoot.findElements(By.xpath("*"));
	// System.out.println("List Size = " + capabilityLst.size());
	// if (isCapabilitiesInRange(capabilityLst, 3))
	// System.out.println("Test Case 92 Passed");
	// else
	// System.out.println("Test Case 92 Failed");
	// }
	//
	// @Then("^Verify Capabilities Accordion has Role Dropdown and given Capabilities checkboxes for Customer Site Manager$")
	// public void
	// verify_Capabilities_Accordion_has_Role_Dropdown_and_given_Capabilities_checkboxes_for_Customer_Site_Manager(
	// DataTable arg1) throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // For automatic transformation, change DataTable to one of
	// // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	// // E,K,V must be a scalar (String, Integer, Date, enum etc)
	// // throw new PendingException();
	// // By FW
	// // For CUSTOM_MANAGER
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	// userPage.explicitWait(1000);
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserCustManager"),
	// );
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserCustManager"),
	// );
	// userPage.waitForSpinner();
	// Actions actions = new Actions(TestBase.getWebDriver());
	// WebElement targetElement =
	// userPage.getElementAfterLoaded(getProps().getProperty("CapabilitiesAccordion"));
	// actions.moveToElement(targetElement).perform();
	// userPage.clickAndWait(getProps().getProperty("CapabilitiesAccordion"));
	// WebElement capabilityRoot =
	// userPage.getElementAfterLoaded(getProps().getProperty("editUserCapabilityList"));
	// List<WebElement> capabilityLst =
	// capabilityRoot.findElements(By.xpath("*"));
	// System.out.println("List Size = " + capabilityLst.size());
	// if (isCapabilitiesInRange(capabilityLst, 4))
	// System.out.println("Test Case 93 Passed");
	// else
	// System.out.println("Test Case 93 Failed");
	// }
	//
	// @Then("^Verify Capabilities Accordion has Role Dropdown and given Capabilities checkboxes for Customer Analyst$")
	// public void
	// verify_Capabilities_Accordion_has_Role_Dropdown_and_given_Capabilities_checkboxes_for_Customer_Analyst(
	// DataTable arg1) throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // For automatic transformation, change DataTable to one of
	// // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	// // E,K,V must be a scalar (String, Integer, Date, enum etc)
	// // throw new PendingException();
	// // For CUSTOM_ANALYST
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	// userPage.explicitWait(1000);
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameCustAnalyst"),
	// );
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameCustAnalyst"),
	// );
	// userPage.waitForSpinner();
	// Actions actions = new Actions(TestBase.getWebDriver());
	// WebElement targetElement =
	// userPage.getElementAfterLoaded(getProps().getProperty("CapabilitiesAccordion"));
	// actions.moveToElement(targetElement).perform();
	// userPage.clickAndWait(getProps().getProperty("CapabilitiesAccordion"));
	// WebElement capabilityRoot =
	// userPage.getElementAfterLoaded(getProps().getProperty("editUserCapabilityList"));
	// List<WebElement> capabilityLst =
	// capabilityRoot.findElements(By.xpath("*"));
	// System.out.println("List Size = " + capabilityLst.size());
	// if (isCapabilitiesInRange(capabilityLst, 5))
	// System.out.println("Test Case 94 Passed");
	// else
	// System.out.println("Test Case 94 Failed");
	// }
	//
	// @Then("^Verify that selected tenant is added and count of sites displayed in destination listbox$")
	// public void
	// verify_that_selected_tenant_is_added_and_count_of_sites_displayed_in_destination_listbox()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	// userPage.explicitWait(1000);
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameAdmin"));
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameAdmin"),
	// );
	// userPage.waitForSpinner();
	// Actions actions = new Actions(TestBase.getWebDriver());
	// WebElement targetElement = userPage
	// .getElementAfterLoaded(getProps().getProperty("editUserModelPermissionAccordion"));
	// actions.moveToElement(targetElement).perform();
	// userPage.clickAndWait(getProps().getProperty("editUserModelPermissionAccordion"));
	// System.out.println("Permission Accordion expanded");
	// // WebElement source =
	// //
	// userPage.getElementAfterLoaded(getProps().getProperty("CapabilitiesAccordion"))
	// userPage.clickAndWait(userPageObjectTestData("editUserSourceSiteTree"));
	// System.out.println("Tenant Selected");
	// userPage.clickAndWait(getProps().getProperty("editUserPermissionAdd"));
	// System.out.println("Tenant Added");
	// if
	// (userPage.getElementAfterLoaded(userPageObjectTestData("editUserTargetSiteTree"))
	// != null) {
	// System.out.println("Test Case 105 Passed");
	// System.out.println(
	// userPage.getElementAfterLoaded(userPageObjectTestData("editUserTargetSiteTree")).getTagName());
	// WebElement count =
	// userPage.getElementAfterLoaded(userPageObjectTestData("editUserTargetSiteTree"))
	// .findElement(By.xpath("following-sibling::*[1]")); //
	// userPage.getElementAfterLoaded(getProps().getProperty("editUserTargetSiteTreeCount"));
	// String countText = count.getText();
	// System.out.println("Count: " + countText);
	// countText = countText.replace("(", "");
	// String[] output = countText.split("\\*");
	// int numOfSites = Integer.parseInt(output[0]);
	// if (numOfSites > 0)
	// System.out.println("Test Case 104 Passed");
	// else
	// System.out.println("Test Case 104 Failed");
	// } else
	// System.out.println("Test Case 105 Failed");
	//
	// }
	//
	// @Then("^Verify that selected tenant from the destination listbox is removed by clicking remove button$")
	// public void
	// verify_that_selected_tenant_from_the_destination_listbox_is_removed_by_clicking_remove_button()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// //
	// userPage.clickAndWait(userPageObjectTestData("editUserTargetSiteTree"));
	// userPage.getElementAfterLoaded(userPageObjectTestData("editUserTargetSiteTree")).click();
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("RemoveButton"));
	//
	// if (TestBase.getWebDriver().findElements(By.xpath(
	// "//*[@id='userSelectedSitePermissionSection']//li/span[contains(text(),'GridPoint Test Automation')]"))
	// .size() == 0)
	// System.out.println("Test Case 106 Passed");
	// else
	// System.out.println("Test Case 106 Failed");
	// }
	//
	// @Then("^Verify save functionality of edit page$")
	// public void verify_save_functionality_of_edit_page() throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// String fName =
	// userPage.getElementAfterLoaded(getProps().getProperty("propertiesSectionFirstNameField"))
	// .getAttribute("value");
	// userPage.enterText(getProps().getProperty("propertiesSectionFirstNameField"),
	// "TestFirstName");
	// String lName =
	// userPage.getElementAfterLoaded(getProps().getProperty("propertiesSectionLastNameField"))
	// .getAttribute("value");
	// userPage.enterText(getProps().getProperty("propertiesSectionLastNameField"),
	// "TestLastName");
	// String email =
	// userPage.getElementAfterLoaded(getProps().getProperty("propertiesSectionEmailField"))
	// .getAttribute("value");
	// userPage.enterText(getProps().getProperty("propertiesSectionEmailField"),
	// "test@gridpoint.com");
	// userPage.clickAndWait(getProps().getProperty("saveButton"));
	// // Saving Capability
	// Actions actions = new Actions(TestBase.getWebDriver());
	// WebElement targetElement =
	// userPage.getElementAfterLoaded(getProps().getProperty("CapabilitiesAccordion"));
	// actions.moveToElement(targetElement).perform();
	// userPage.clickAndWait(getProps().getProperty("CapabilitiesAccordion"));
	// WebElement capabilityRoot =
	// userPage.getElementAfterLoaded(getProps().getProperty("editUserCapabilityList"));
	// WebElement target =
	// capabilityRoot.findElement(By.xpath("//input[@value='EDIT_XML']"));
	// System.out.println("Name: " + target.getText());
	// boolean isChecked = target.isSelected();
	// target.click();
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("saveCapabilitiesUserBtn"));
	// // Permission saving
	// userPage.clickAndWait(userPageObjectTestData("editUserSourceSiteTree"));
	// System.out.println("Tenant Selected");
	// userPage.clickAndWait(getProps().getProperty("editUserPermissionAdd"));
	// System.out.println("Tenant Added");
	// userPage.clickAndWait(getProps().getProperty("PermissionSaveButton"));
	// // closing the dialog
	// userPage.clickAndWait(getProps().getProperty("editUserPopupCancelButton"));
	// userPage.waitForSpinner();
	// userPage.clickAndWait(getProps().getProperty("addUserPopupOkButton"));
	// userPage.explicitWait(1000);
	// // Opening the user in edit mode again to verify changes
	// userPage.enterText_UserPage("USER_SEARCH",
	// userPageObjectTestData("addUserNameAdmin"));
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("userSearchButton")); //
	// clickOnUserPage("SEARCH",
	// // );
	// userPage.explicitWait(1000);
	// userPage.selectUserFromGrid_UserPage(userPageObjectTestData("addUserNameAdmin"),
	// );
	// userPage.waitForSpinner();
	// // verifying detail
	// boolean detail = false;
	// if
	// (userPage.getElementAfterLoaded(getProps().getProperty("propertiesSectionFirstNameField"))
	// .getAttribute("value").equals("TestFirstName")) {
	// detail = true;
	// if
	// (userPage.getElementAfterLoaded(getProps().getProperty("propertiesSectionLastNameField"))
	// .getAttribute("value").equals("TestLastName")) {
	// detail = true;
	// if
	// (userPage.getElementAfterLoaded(getProps().getProperty("propertiesSectionEmailField"))
	// .getAttribute("value").equals("test@gridpoint.com")) {
	// detail = true;
	// userPage.enterText(getProps().getProperty("propertiesSectionFirstNameField"),
	// fName);
	// userPage.enterText(getProps().getProperty("propertiesSectionLastNameField"),
	// lName);
	// userPage.enterText(getProps().getProperty("propertiesSectionEmailField"),
	// email);
	// userPage.clickAndWait(getProps().getProperty("saveButton"));
	// } else
	// detail = false;
	// } else
	// detail = false;
	// } else
	// detail = false;
	// // verifying Capability
	// boolean capability = false;
	// targetElement =
	// userPage.getElementAfterLoaded(getProps().getProperty("CapabilitiesAccordion"));
	// actions.moveToElement(targetElement).perform();
	// userPage.clickAndWait(getProps().getProperty("CapabilitiesAccordion"));
	// capabilityRoot =
	// userPage.getElementAfterLoaded(getProps().getProperty("editUserCapabilityList"));
	// target =
	// capabilityRoot.findElement(By.xpath("//input[@value='EDIT_XML']"));
	// System.out.println("Name: " + target.getText());
	// if (!(target.isSelected() == isChecked)) {
	// capability = true;
	// target.click();
	// }
	// // verifying permission
	// boolean permission = false;
	// targetElement =
	// userPage.getElementAfterLoaded(getProps().getProperty("editUserModelPermissionAccordion"));
	// actions.moveToElement(targetElement).perform();
	// userPage.clickAndWait(getProps().getProperty("editUserModelPermissionAccordion"));
	// if (TestBase.getWebDriver().findElements(By.xpath(
	// "//*[@id='userSelectedSitePermissionSection']//li/span[contains(text(),'GridPoint Test Automation')]"))
	// .size() > 0) {
	// permission = true;
	// // Code for Test case 121 is written here to reduce re-write of duplicate
	// code.
	// if
	// (userPage.getElementAfterLoaded(userPageObjectTestData("editUserLabelSelectedSiteCount")).getText()
	// .length() > 0)
	// System.out.println("Test Case 121 Passed");
	// else
	// System.out.println("Test Case 121 Failed");
	// // Code for Test Case 117
	// userPage.getElementAfterLoaded(userPageObjectTestData("editUserTargetSiteTree")).click();
	// userPage.explicitWait(1000);
	// userPage.clickAndWait(getProps().getProperty("RemoveButton"));
	// }
	// userPage.explicitWait(1000);
	// if (detail && capability && permission)
	// System.out.println("Test Case 117 Passed");
	// else
	// System.out.println("Test Case 117 Failed");
	// }
	//
	// @Then("^Verify selected Tenant shows Type of Tenant$")
	// public void verify_selected_Tenant_shows_Type_of_Tenant() throws
	// Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// userPage.explicitWait(1000);
	// if
	// (userPage.getElementAfterLoaded(getProps().getProperty("PermissionSelectedTenantTypeValue")).getText()
	// .length() > 0)
	// System.out.println("Test Case 120 Passed");
	// else
	// System.out.println("Test Case 120 Failed");
	// }
	//
	// @Then("^Verify selected Tenant_Site Permission label shows the number of sites selected$")
	// public void
	// verify_selected_Tenant_Site_Permission_label_shows_the_number_of_sites_selected()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// userPage.explicitWait(1000);
	// // DO NOT REMOVE. Code for this test case has been written with Test Case
	// 117 to
	// // avoid duplicate code.
	// }
	//
	// @Then("^Verify Site Permission change appears in Audit$")
	// public void verify_Site_Permission_change_appears_in_Audit() throws
	// Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// WebElement targetElement =
	// userPage.getElementAfterLoaded(getProps().getProperty("editAuditAccordian"));
	// Actions actions = new Actions(TestBase.getWebDriver());
	// actions.moveToElement(targetElement).perform();
	// userPage.explicitWait(1000);
	// System.out.println("Moved to Audit accordian");
	// userPage.clickAndWait(getProps().getProperty("editAuditAccordian"));
	// userPage.explicitWait(1000);
	// System.out.println("accordian Expanded");
	// WebElement table =
	// userPage.getElementAfterLoaded(getProps().getProperty("auditGrid"));//
	// .getWebDriver().findElement(By.id(getProps().getProperty("userTable")));
	// System.out.println("Element Name : " + table.getTagName());
	// WebElement parent = table.findElement(By.className("ui-grid-canvas"));
	// System.out.println("Element Name : " + parent.getTagName());
	// List<WebElement> rows = parent.findElements(By.className("ui-grid-row"));
	// System.out.println("Number of rows : " + rows.size());
	// DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	// dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	//
	// System.out.println("Today : " + dateFormatter.format(new Date()));
	// String todayDate = dateFormatter.format(new Date());
	// boolean auditStatus = false;
	// for (WebElement row : rows) {
	// List<WebElement> cols = row.findElements(By.className("ui-grid-cell"));
	// WebElement latestdate =
	// cols.get(5).findElement(By.className("ui-grid-cell-contents"));
	// System.out.println("Date: " + latestdate.getText().substring(0, 10));
	// String targetDate = latestdate.getText().substring(0, 10);
	// if (targetDate.equals(todayDate)) {
	// System.out.println("Pass 1");
	// System.out.println(cols.get(3).findElement(By.className("ui-grid-cell-contents")).getText());
	// System.out.println(userPageObjectTestData("editUserPermissionAuditTest"));
	// if
	// (cols.get(3).findElement(By.className("ui-grid-cell-contents")).getText()
	// .equals(userPageObjectTestData("editUserPermissionAuditTest"))) {
	// auditStatus = true;
	// break;
	// }
	// }
	// }
	// if (auditStatus)
	// System.out.println("Test Case 122 Passed");
	// else
	// System.out.println("Test Case 122 Failed");
	// }
	//
	// @Then("^Verify Site Tree contains all sites associated with the Tenant$")
	// public void
	// verify_Site_Tree_contains_all_sites_associated_with_the_Tenant() throws
	// Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// WebElement treeRoot =
	// userPage.getElementAfterLoaded(getProps().getProperty("editUserSiteTree"));
	// WebElement treeContainer =
	// treeRoot.findElement(By.className("jstree-container-ul"));
	// List<WebElement> nodes =
	// treeContainer.findElements(By.className("jstree-node"));
	// if (nodes.size() > 0)
	// System.out.println("Test Case 123 Passed");
	// else
	// System.out.println("Test Case 123 Failed");
	//
	// // Test Case 124
	//
	// userPage.clickAndWait(userPageObjectTestData("editUserTenantDropdown"));
	// userPage.clickAndWait(userPageObjectTestData("editUserTenantDropdownItem"));
	// userPage.explicitWait(4000);
	// WebElement treeRoot2 =
	// userPage.getElementAfterLoaded(getProps().getProperty("editUserSiteTree"));
	// WebElement treeContainer2 =
	// treeRoot2.findElement(By.className("jstree-container-ul"));
	// List<WebElement> nodes2 =
	// treeContainer2.findElements(By.className("jstree-node"));
	// if (nodes.size() != nodes2.size())
	// System.out.println("Test Case 124 Passed");
	// else
	// System.out.println("Test Case 124 Failed");
	// }
	//
	// @Then("^Verify Site tree changes on the basis of Tenant selected$")
	// public void verify_Site_tree_changes_on_the_basis_of_Tenant_selected()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// // DO not delete this. The test case has been written along with Test
	// Case 123
	// // to reduce duplication of code
	// }
	//
	// @Then("^Verify that Root Tenant Gridpoint has multiple tenants available$")
	// public void
	// verify_that_Root_Tenant_Gridpoint_has_multiple_tenants_available() throws
	// Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// userPage.clickAndWait(userPageObjectTestData("editUserTenantDropdown"));
	// System.out.println("Dropdown Clicked");
	// userPage.clickAndWait(userPageObjectTestData("editUserRootTenant"));
	// userPage.explicitWait(1000);
	// System.out.println("Tenant Selected");
	// WebElement treeRoot =
	// userPage.getElementAfterLoaded(userPageObjectTestData("editUserTenantDropdown"));
	// System.out.println("Root Selected");
	// System.out.println("Tag Name: " + treeRoot.getTagName());
	// WebElement gridpoint =
	// treeRoot.findElement(By.xpath("//*[@id='tenantSelectedSection']/div/div/ul/li/ul"));
	// System.out.println("Tag Name: " + gridpoint.getTagName());
	// System.out.println("Tag Name: " + gridpoint.getText());
	// List<WebElement> nodes = treeRoot.findElements(By.className("ng-scope"));
	// System.out.println("Nodes found");
	// if (nodes.size() > 0)
	// System.out.println("Test Case 125 Passed");
	// else
	// System.out.println("Test Case 125 Failed");
	// }
	//
	// @Then("^Verify that Tenant Dropdown shows Gridpoint by default$")
	// public void verify_that_Tenant_Dropdown_shows_Gridpoint_by_default()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// WebElement tenantRoot =
	// userPage.getElementAfterLoaded(userPageObjectTestData("editUserTenantDropdown"));
	// WebElement rootText = tenantRoot.findElement(By.xpath("//p"));
	// if (rootText.getText().equals("GridPoint"))
	// System.out.println("Test Case 126 Passed");
	// else
	// System.out.println("Test Case 126 Failed");
	//
	// }
	//
	// @Then("^Verify all sites get selected after selecting a Tenant$")
	// public void verify_all_sites_get_selected_after_selecting_a_Tenant()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// // throw new PendingException();
	// // By FW
	// }
	//
	//
	// @When("^we will login with \"(.*?)\" credential and varify optionDropDown is there$")
	// public void we_will_login_with_credential_and_verify_dashboard_tab(String
	// role, DataTable table) throws Throwable {
	//
	// List<String> lstData = table.asList(String.class);
	// String userName = lstData.get(0);
	// userPage.enterText(getProps().getProperty("userPage_UserName_Locator"),
	// userName);
	//
	// // userPage.isDashboardTabAvailable(userName);
	// if
	// (!userPage.checkIfClickable(getProps().getProperty("optionDropDown")))
	// {
	// Assert.fail("Not able to login for the user : " + role);
	// }
	// Assert.assertTrue(" Expected : User Tab should be Available. Actual : User tab is not available for user ["
	// + userName + "] ",
	// userPage.isElementClickable(userPage.getLocator(getProps().getProperty("userTab"))));
	// }
	//
	 @When("^initialize dummy users in database for different roles and remove dependencies associated with \"(.*?)\"$")
	 public void
	 initialize_dummy_data_in_database_for_different_roles_and_remove(String
	 tenantName, DataTable table)
	 throws Throwable {
	 List<String> lstData = table.asList(String.class);
	 ManageTestData objDelTestdata = new ManageTestData();
	 Assert.assertTrue("Error occured while deleting userRolePermission page test data",
	 objDelTestdata.manageTestData("userRolePermission_Delete", lstData,
	 tenantName));
	 // Insert Different roles users only.
	 for (String userName : lstData) {
	 Assert.assertTrue("Error occured while inserting userRolePermission page test data",
	 objDelTestdata.manageTestData("userRolePermission_Insert", userName));
	 }
	 }
	
	// // @When("^Verify that \"(.*?)\" field value is \"(.*?)\"$")
	// // public void Verify_that_field_value_is(String column, String value)
	// throws Throwable {
	// // Assert.assertTrue("Failed to verify values of element " + column,
	// // userPage.verifyFieldValues(column, value));
	// // }
	//
	//
	// boolean isCapabilitiesInRange(List<WebElement> caps, int role) {
	// // //Assuming each capability has a number as following:
	// // BILLING_COMPARISON_REPORT = 1
	// // MSTR_CAPABLE = 2
	// // BYPASS_THROTTLING = 3
	// // REPORT_SAVINGS = 4
	// // EDIT_XML = 5
	// // CREATE_OVERRIDE = 6
	// // RUN_HVAC_TESTS = 7
	// // VIEW_SITE_SAVINGS = 8
	// // Assuming Role GP_ADMIN=1, GP_SUPPORT=2, GP_ANALYST=3,
	// CUSTOMER_SITE_MANAGER=4
	// // and CUSTOMER_ANALYST=5
	// int total = 0;
	// boolean cap = false;
	// for (WebElement capability : caps) {
	// String target =
	// capability.findElement(By.xpath("./input")).getAttribute("value");
	// System.out.println("Cap: " + target);
	// switch (target) {
	// case "BILLING_COMPARISON_REPORT":
	// total += 1;
	// break;
	// case "MSTR_CAPABLE":
	// total += 2;
	// break;
	// case "BYPASS_THROTTLING":
	// total += 3;
	// break;
	// case "REPORT_SAVINGS":
	// total += 4;
	// break;
	// case "EDIT_XML":
	// total += 5;
	// break;
	// case "CREATE_OVERRIDE":
	// total += 6;
	// break;
	// case "RUN_HVAC_TESTS":
	// total += 7;
	// break;
	// case "VIEW_SITE_SAVINGS":
	// total += 8;
	// break;
	// }
	// } // end of for
	// if ((role == 1 || role == 2) && total == 36)
	// cap = true;
	// else if (role == 3 && total == 31)
	// cap = true;
	// else if (role == 4 && total == 30)
	// cap = true;
	// else if (role == 5 && total == 17)
	// cap = true;
	// else
	// cap = false;
	//
	// return cap;
	// }
	 
	 @Then("^confirmation box should display with message \"(.*?)\" for reset Password alert pop up$")
		public void confirmation_box_should_display_with_message_for_reset_password_alert_popup(
				String confirmationboxMessage) throws Throwable {
		 
		 
			Alert alert1=TestBase.getWebDriver().switchTo().alert();
			
			String actualText=alert1.getText();
			logger.info("Text getting from popup" + actualText);
			
			boolean flag=confirmationboxMessage.equals(actualText);
			Assert.assertTrue(
						"On Confirmation popup text did not appeared as expected",
						flag);
		} 
	 
	 @When("^click on \"(.*?)\" button on reset password alert popup$")
	 public void click_on_button_on_reset_password_alert_popup(String buttonName)
	 throws Throwable {
	 String message = "Failed to click on button :" + buttonName + " " +
	 " on reset password alert popup";
		Alert alert1=TestBase.getWebDriver().switchTo().alert();	
		alert1.dismiss();
		
	 userPage.explicitWait(2000);
	 }
}
