package com.gridpoint.adminui.automation.firmware;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.gridpoint.adminui.automation.loginAndInitilizeResources.LoginPageTest;
import com.gridpoint.adminui.automation.testdata.ManageTestData;
import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.util.BaseUtil;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FirmwarePageTest extends CommonInit {

	private Logger logger = Logger.getLogger(LoginPageTest.class);
	private FirmwarePage firmwarePage;

	private static boolean isEndpointFirmwarePresent;

	@Given("^User is already logged in to Admin URL and is already present at the Firmware Tab for \"(.*?)\"$")
	public void user_is_already_logged_in_to_Admin_URL_and_is_already_present_at_the_Firmware_Tab_for_Scenario_(
			String arg1) throws Throwable {
		BaseUtil.explicitWait(8000);
		firmwarePage = new FirmwarePage();
		BaseUtil.explicitWait(3000);
		BaseUtil.explicitWait(3000);
		BaseUtil.waitForSpinner();
		BaseUtil.overrideTimeout(getProps().getProperty("firmwarePageTimeout"));
		BaseUtil.overridePollingfrequency(getProps().getProperty(
				"firmwarePagePollingFrequency"));

		String message = "Unsuccessful login with valid credentials";
		BaseUtil.assertElementDisplayed(getProps()
				.getProperty("optionDropDown"), message);

	}

	@Given("^we are on \"(.*?)\" page and search field is enabled and visible$")
	public void we_are_on_Firmware_page_and_search_firmware_field_is_enabled_and_visible(
			String subTabName) throws Throwable {
		BaseUtil.explicitWait(8000);
		BaseUtil.waitForSpinner();
		BaseUtil.explicitWait(2000);
		BaseUtil.clickAndWait(getProps().getProperty("firmwareTab"));
		BaseUtil.waitForSpinner();
		if (subTabName.equals("Peripheral Firmware")) {
			BaseUtil.clickAndWait(getProps().getProperty(
					"peripheralFirmwareTab"));
			BaseUtil.instantElementCheck("Display",
					getProps().getProperty("peripheralFirmwareSearchField"));

		} else if (subTabName.equals("Endpoint Firmware")) {
			BaseUtil.clickAndWait(getProps().getProperty("endpointFirmwareTab"));
			BaseUtil.instantElementCheck("Display",
					getProps().getProperty("endpointFirmwareSearchField"));

		} else if (subTabName.equals("Update Endpoint Firmware")) {
			BaseUtil.clickAndWait(getProps().getProperty(
					"updateEndpointFirmwareTab"));
			BaseUtil.instantElementCheck("Display",
					getProps().getProperty("endpointFirmwareSearchField"));
		}

	}

	@Then("^verify the visibility \"(.*?)\" page by confirming the availability$")
	public void verify_the_peripheral_firmware_page_by_confirming_the_availability_of_peripheral_firmware_grid(
			String subTabName) throws Throwable {
		BaseUtil.explicitWait(8000);
		if (subTabName.equals("Peripheral Firmware")) {
			String message = "Failed to verify Peripheral firmware grid availablity.  ";
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("peripheralfirmwareGrid"), message);

		} else if (subTabName.equals("Endpoint Firmware")) {
			String message = "Failed to verify Endpoint firmware grid availablity.  ";
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("endpointfirmwareGrid"), message);

		} else if (subTabName.equals("Update Endpoint Firmware")) {
			String message = "Failed to verify Endpoint firmware grid availablity.  ";
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("updateEndpointFirmwareGrid"),
					message);

		}

	}

	@Then("^verify the following columns in \"(.*?)\" details grid$")
	public void verify_the_following_columns_in_peripheral_firmware_details_grid(
			String subTabName, DataTable firmwaredetailgrid) throws Throwable {
		BaseUtil.explicitWait(8000);
		List<String> lstData = firmwaredetailgrid.asList(String.class);
		firmwaredetailgrid.asList(String.class);
		boolean isVisible = true;
		if (subTabName.equals("Peripheral Firmware")) {
			for (String header : lstData) {
				boolean visible = false;
				if (header.trim().equals("Release Date")) {
					visible = BaseUtil.verifyElementDisplayed(getProps()
							.getProperty(
									"peripheralfirmwarePageReleaseDateHeader"));
				}
				if (header.trim().equals("Active")) {
					visible = BaseUtil.verifyElementDisplayed(getProps()
							.getProperty("peripheralfirmwarePageActiveHeader"));
				}
				if (header.trim().equals("Version")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps().getProperty(
									"peripheralfirmwarePageVersionHeader"));
				}
				if (header.trim().equals("Peripheral Type")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps()
									.getProperty(
											"peripheralfirmwarePagePeripheralTypeHeader"));
				}
				if (header.trim().equals("Peripheral Type ID")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps()
									.getProperty(
											"peripheralfirmwarePagePeripheralTypeIDHeader"));
				}
				if (header.trim().equals("Controller Min Version")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps()
									.getProperty(
											"peripheralfirmwarePageControllerMinVersionHeader"));
				}
				if (visible == false) {
					if (visible == false) {
						isVisible = false;
						Assert.assertTrue(
								"Header ["
										+ header
										+ "] is not visible in peripheral firmware Grid",
								isVisible);
						break;
					}
				}
			}

		} else if (subTabName.equals("Endpoint Firmware")) {
			for (String header : lstData) {
				boolean visible = false;
				if (header.trim().equals("Name")) {
					visible = BaseUtil.verifyElementDisplayed(getProps()
							.getProperty("endpointfirmwarePageNameHeader"));
				}
				if (header.trim().equals("Version")) {
					visible = BaseUtil.verifyElementDisplayed(getProps()
							.getProperty("endpointFirmwarePageVersionHeader"));
				}
				if (header.trim().equals("Endpoint type")) {
					visible = BaseUtil.verifyElementDisplayed(getProps()
							.getProperty(
									"endpointFirmwarePageEndpointTypeHeader"));
				}
				if (header.trim().equals("Release date")) {
					visible = BaseUtil.verifyElementDisplayed(getProps()
							.getProperty(
									"endpointFirmwarePageReleaseDateHeader"));
				}
				if (header.trim().equals("Active")) {
					visible = BaseUtil.verifyElementDisplayed(getProps()
							.getProperty("endpointFirmwarePageActiveHeader"));
				}
				if (visible == false) {
					if (visible == false) {
						isVisible = false;
						Assert.assertTrue(
								"Header ["
										+ header
										+ "] is not visible in peripheral firmware Grid",
								isVisible);
						break;
					}
				}
			}

		} else if (subTabName.equals("Endpoint Firmware:Audit")) {
			for (String header : lstData) {
				boolean visible = false;
				if (header.trim().equals("Property")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps()
									.getProperty(
											"editEndpointFirmwareAuditGridPropertyHeader"));
				}
				if (header.trim().equals("Action")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps()
									.getProperty(
											"editEndpointFirmwareAuditGridActionHeader"));
				}
				if (header.trim().equals("Original Value")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps()
									.getProperty(
											"editEndpointFirmwareAuditGridOriginalValueHeader"));
				}
				if (header.trim().equals("Updated value")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps()
									.getProperty(
											"editEndpointFirmwareAuditGridUpdatedValueHeader"));
				}
				if (header.trim().equals("User")) {
					visible = BaseUtil.verifyElementDisplayed(getProps()
							.getProperty(
									"editEndpointFirmwareAuditGridUserHeader"));
				}
				if (header.trim().equals("Date Stamp")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps()
									.getProperty(
											"editEndpointFirmwareAuditGridDateStampHeader"));
				}
				if (visible == false) {
					if (visible == false) {
						isVisible = false;
						Assert.assertTrue(
								"Header ["
										+ header
										+ "] is not visible in peripheral firmware Grid",
								isVisible);
						break;
					}
				}
			}

		} else if (subTabName.equals("Update Endpoint Firmware")) {
			for (String header : lstData) {
				boolean visible = false;
				if (header.trim().equals("Select")) {
					visible = BaseUtil.verifyElementDisplayed(getProps()
							.getProperty(
									"updateEndpointFirmwarePageColumnSelect"));
				}
				if (header.trim().equals("Endpoint ID")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps()
									.getProperty(
											"updateEndpointFirmwarePageColumnEndpointID"));
				}
				if (header.trim().equals("Tenant")) {
					visible = BaseUtil.verifyElementDisplayed(getProps()
							.getProperty(
									"updateEndpointFirmwarePageColumnTenant"));
				}
				if (header.trim().equals("Premises")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps().getProperty(
									"updateEndpointFirmwarePageColumnPremises"));
				}
				if (header.trim().equals("Connection Status")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps()
									.getProperty(
											"updateEndpointFirmwarePageColumnConnectionStatus"));
				}
				if (header.trim().equals("Type")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps().getProperty(
									"updateEndpointFirmwarePageColumnType"));
				}
				if (header.trim().equals("Serial")) {
					visible = BaseUtil.verifyElementDisplayed(getProps()
							.getProperty(
									"updateEndpointFirmwarePageColumnSerial"));
				}
				if (header.trim().equals("Live Firmware")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps()
									.getProperty(
											"updateEndpointFirmwarePageColumnLiveFirmware"));
				}
				if (header.trim().equals("Last Message Received (UTC)")) {
					visible = BaseUtil
							.verifyElementDisplayed(getProps()
									.getProperty(
											"updateEndpointFirmwarePageColumnLastMessageReceived"));
				}
				if (visible == false) {
					if (visible == false) {
						isVisible = false;
						Assert.assertTrue(
								"Header ["
										+ header
										+ "] is not visible in peripheral firmware Grid",
								isVisible);
						break;
					}
				}
			}

		}

	}

	@Then("^verify the sorting functionality of columns in \"(.*?)\" details grid$")
	public void verify_the_sorting_functionality_of_columns_in_firmware_details_grid(
			String subTabName, DataTable peripheralFirmwareDetailGrid)
			throws Throwable {
		BaseUtil.explicitWait(8000);
		List<String> lstData = peripheralFirmwareDetailGrid
				.asList(String.class);
		if (subTabName.equals("Peripheral Firmware")) {
			for (String header : lstData) {
				firmwarePage.verifySorting_FirmwarePage("Descending", header);
				firmwarePage.verifySorting_FirmwarePage("Ascending", header);
			}

		} else if (subTabName.equals("Endpoint Firmware")) {
			for (String header : lstData) {
				firmwarePage.verifySorting_FirmwarePage("Descending", header);
				firmwarePage.verifySorting_FirmwarePage("Ascending", header);
			}
		} else if (subTabName.equals("Update Endpoint Firmware")) {
			for (String header : lstData) {
				firmwarePage.verifySorting_FirmwarePage("Descending", header);
				firmwarePage.verifySorting_FirmwarePage("Ascending", header);
			}
		}
	}

	@When("^click on \"(.*?)\" link in \"(.*?)\" grid$")
	public void click_on_link_in_peripheral_firmware_grid(
			String firmwareLinkName, String subTabName) throws Throwable {
		BaseUtil.explicitWait(2000);
		BaseUtil.waitForSpinner(2000);
		BaseUtil.explicitWait(8000);
		if (subTabName.equals("Peripheral Firmware")) {
			BaseUtil.getElementAfterLoaded(
					getProps().getProperty("peripheralfirmwareTab")).sendKeys(
					Keys.PAGE_DOWN);
			firmwarePage.clickPaginationFirmwarePage(firmwareLinkName);

		} else if (subTabName.equals("Endpoint Firmware")) {
			BaseUtil.getElementAfterLoaded(
					getProps().getProperty("endpointFirmwareTab")).sendKeys(
					Keys.PAGE_DOWN);
			BaseUtil.waitForSpinner(1000);
			firmwarePage.clickPaginationFirmwarePage(firmwareLinkName);

		}

	}

	@Then("^\"(.*?)\" page should displayed in \"(.*?)\" grid$")
	public void page_should_displayed_in_peripheral_firmware_grid(
			String firmwarepagedisplay, String subTabName) throws Throwable {
		BaseUtil.explicitWait(8000);
		if (subTabName.equals("Peripheral Firmware")) {
			firmwarePage
					.verifyFirmwareDetailGridPagination(firmwarepagedisplay);

		} else if (subTabName.equals("Endpoint Firmware")) {
			firmwarePage
					.verifyFirmwareDetailGridPagination(firmwarepagedisplay);
		}

	}

	@When("^enter random page number in page link text in firmware detail grid$")
	public void enter_random_page_number_in_page_link_text_in_firmware_detail_grid()
			throws Throwable {
		BaseUtil.explicitWait(8000);
		String totalPages = BaseUtil.getElementText(getProps().getProperty(
				"firmwarePaginationTotalPages"));
		totalPages = totalPages.substring(totalPages.indexOf("/") + 1).trim();
		int pageCount = Integer.parseInt(totalPages);
		if (pageCount <= 1) {
		}
		Random random = new Random();
		int randomPage = random.nextInt(pageCount + 1);
		if (randomPage == 0 || randomPage == 1)
			randomPage = 2;
		firmwarePage.setRandomPage(Integer.parseInt(totalPages), randomPage);
		BaseUtil.waitForSpinner();
	}

	@Then("^user should be navigated on the given random page in \"(.*?)\" detail grid$")
	public void user_should_be_navigated_on_the_given_random_page_in_firmware_detail_grid(
			String subTabName) throws Throwable {
		BaseUtil.explicitWait(8000);
		if (subTabName.equals("Peripheral Firmware")) {
			BaseUtil.waitForSpinner();
			BaseUtil.getMultipleElementTextAfterLoaded(getProps().getProperty(
					"periperalFirmwarePagePeripheralTypeColumn"));
			BaseUtil.setElementAttribute(
					getProps().getProperty("firmwarePaginationCurrentPage"),
					"1");
			BaseUtil.waitForSpinner();
			BaseUtil.getMultipleElementTextAfterLoaded(getProps().getProperty(
					"periperalFirmwarePagePeripheralTypeColumn"));

		} else if (subTabName.equals("Endpoint Firmware")) {
			BaseUtil.waitForSpinner();
			BaseUtil.getMultipleElementTextAfterLoaded(getProps().getProperty(
					"endpointFirmwarePageNameColumn"));
			BaseUtil.setElementAttribute(
					getProps().getProperty("firmwarePaginationCurrentPage"),
					"1");
			BaseUtil.waitForSpinner();
			BaseUtil.getMultipleElementTextAfterLoaded(getProps().getProperty(
					"endpointFirmwarePageNameColumn"));

		}
	}

	@Then("^delete the \"(.*?)\" data used as test data for firmware page$")
	public void add_delete_data_used_as_test_data_for_audit_page(
			String dataType, List<String> listFirmwareTestData)
			throws Throwable {
		BaseUtil.explicitWait(8000);
		ManageTestData objTestdata = new ManageTestData();
		boolean flag = false;
		if (dataType.equals("Endpoint Firmware")) {
			for (String endpointFirmwareTestData : listFirmwareTestData) {
				isEndpointFirmwarePresent = objTestdata.manageTestData(
						"Endpoint_Firmware_Present", endpointFirmwareTestData);
				if (!isEndpointFirmwarePresent) {
					return;
				}
				BaseUtil.waitForSpinner(1000);
				String tabName = "Firmware", subTabName = "Endpoint Firmware";

				Assert.assertTrue("Clicking on " + tabName + " is failed",
						firmwarePage.clickOperationOnFirmwarepage(tabName));
				BaseUtil.waitForSpinner(1000);
				Assert.assertTrue("Clicking on " + subTabName + " is failed",
						firmwarePage.clickOperationOnFirmwarepage(subTabName));
				BaseUtil.waitForSpinner(1000);

				enter_the_test_Data_in_the_search_field_and_click_on_the_search_button(
						subTabName, endpointFirmwareTestData);
				flag = select_the_given_from_the_list_and_verify_the_confirmation_popup(
						subTabName, endpointFirmwareTestData);

				if (flag == true) {
					BaseUtil.explicitWait(PathConstants.WAIT_HIGH);
					click_on_the_button(subTabName, "Delete Endpoint Firmware");
					BaseUtil.explicitWait(PathConstants.WAIT_LOW);
					confirmation_box_should_displayed_with_message_saying(
							subTabName,
							"Are you sure you want to delete this firmware version?",
							endpointFirmwareTestData);
					click_on_the_button(subTabName,
							"Endpoint Firmware:Delete_OK");
					BaseUtil.waitForSpinner(1000);
					user_should_be_navigated_to_page_with_a_label(
							"Endpoint Firmware ##### deleted.", subTabName,
							endpointFirmwareTestData);
					isEndpointFirmwarePresent = false;

					logger.info("*****("
							+ endpointFirmwareTestData
							+ ")Endpoint Firmware has been deleted successfully*****");
				} else {
					logger.info("*****("
							+ endpointFirmwareTestData
							+ ")Endpoint Firmware is already deleted or not in the database*****");
				}
			}

		} else if (dataType.equals("Peripheral Firmware")) {

			for (String peripheralFirmwareTestData : listFirmwareTestData) {
				BaseUtil.waitForSpinner(1000);
				String tabName = "Firmware", subTabName = "Peripheral Firmware";

				Assert.assertTrue("Clicking on " + tabName + " is failed",
						firmwarePage.clickOperationOnFirmwarepage(tabName));
				BaseUtil.waitForSpinner(1000);
				Assert.assertTrue("Clicking on " + subTabName + " is failed",
						firmwarePage.clickOperationOnFirmwarepage(subTabName));
				BaseUtil.waitForSpinner(2000);
				BaseUtil.explicitWait(2000);
				enter_the_test_Data_in_the_search_field_and_click_on_the_search_button(
						tabName, peripheralFirmwareTestData);
				flag = select_the_given_from_the_list_and_verify_the_confirmation_popup(
						tabName, peripheralFirmwareTestData);

				if (flag == true) {
					BaseUtil.explicitWait(PathConstants.WAIT_HIGH);
					click_on_the_button(subTabName,
							"Delete Peripheral Firmware");
					BaseUtil.explicitWait(PathConstants.WAIT_LOW);
					confirmation_box_should_displayed_with_message_saying(
							subTabName,
							"Are you sure you want to delete the peripheral?",
							peripheralFirmwareTestData);
					click_on_the_button(subTabName,
							"Peripheral Firmware:Delete_OK");
					BaseUtil.waitForSpinner(1000);
					user_should_be_navigated_to_page_with_a_label(
							"Peripheral Firmware ####deleted .", subTabName,
							peripheralFirmwareTestData);
					isEndpointFirmwarePresent = false;

					logger.info("*****("
							+ peripheralFirmwareTestData
							+ ")Peripheral Firmware has been deleted successfully*****");
				} else {
					logger.info("*****("
							+ peripheralFirmwareTestData
							+ ")Peripheral Firmware is already deleted or not in the database*****");
				}
			}
		}
	}

	@Then("^enter_the_test_Data_in_the_search_field_and_click_on_the_search_button$")
	public void enter_the_test_Data_in_the_search_field_and_click_on_the_search_button(
			String type, String testData) {
		String fieldLocator = null;
		BaseUtil.explicitWait(8000);
		if (type.equalsIgnoreCase("Endpoint Firmware")) {
			fieldLocator = getProps()
					.getProperty("endpointFirmwareSearchField");

		} else if (type.equalsIgnoreCase("Peripheral Firmware")) {
			fieldLocator = getProps().getProperty(
					"peripheralFirmwareSearchField");

		}
		BaseUtil.waitForSpinner(1000);
		BaseUtil.enterText(fieldLocator, testData);
		BaseUtil.waitForSpinner(1000);
	}

	@Then("^select given \"(.*?)\" from the list and verify the Confirmation popup$")
	public boolean select_the_given_from_the_list_and_verify_the_confirmation_popup(
			String sourceNameFeature, String TestData) {
		BaseUtil.explicitWait(8000);
		boolean bResult = false;
		String gridColumnLocator = null;

		if (sourceNameFeature.equalsIgnoreCase("Endpoint Firmware")) {
			BaseUtil.click(getProps().getProperty(
					"endpointFirmwareSearchButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty(
					"endpointFirmwarePageNameColumn");

		} else if (sourceNameFeature.equalsIgnoreCase("Peripheral Firmware")) {
			BaseUtil.click(getProps().getProperty(
					"periperalFirmwareSearchButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty(
					"periperalFirmwarePagePeripheralTypeColumn");

		}
		try {
			List<WebElement> lstSourcesNames = BaseUtil
					.getMultipleElementsAfterLoaded(gridColumnLocator);
			for (WebElement webElement : lstSourcesNames) {
				if (gridColumnLocator.equals("No records found.")) {
					bResult = false;
					break;
				} else {
					if (webElement.getText().trim().equalsIgnoreCase(TestData)) {
						webElement.click();
						BaseUtil.waitForSpinner(1000);
						bResult = true;
						break;
					}
				}
			}
			if (!bResult) {
				Assert.fail("Given test " + sourceNameFeature
						+ " is not available");
			} else {
				logger.debug("Searched source :" + TestData + " is selected.");
			}

		} catch (Exception e) {
			logger.error("Failed to select source: " + TestData
					+ " from grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the: " + TestData
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.explicitWait(3000);
		}
		return bResult;
	}

	@Then("^click on the \"(.*?)\" button/tab$")
	public void click_on_the_button(String tabName, String buttonName)
			throws Throwable {
		BaseUtil.explicitWait(8000);
		if (tabName.equals("Endpoint Firmware")) {
			Assert.assertTrue("Clicking on " + buttonName + " is failed",
					firmwarePage.clickOperationOnFirmwarepage(buttonName));
		} else if (tabName.equals("Site")) {
			Assert.assertTrue("Clicking on " + buttonName + " is failed",
					firmwarePage.clickOperationOnFirmwarepage(buttonName));
		}

	}

	@Then("^confirmation box should displayed with message \"(.*?)\"$")
	public void confirmation_box_should_displayed_with_message_saying(
			String type, String expectedMessage, String testData)
			throws Throwable {
		BaseUtil.explicitWait(8000);
		String messageOnUI = null;
		if (type.equals("Endpoint Firmware")) {
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
			expectedMessage = expectedMessage.replace("#####", testData);
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

	@Then("^verify user should be navigated to page with a label \"(.*?)\"$")
	public void user_should_be_navigated_to_page_with_a_label(String message,
			String type, String testData) throws Throwable {
		BaseUtil.explicitWait(8000);
		BaseUtil.explicitWait(2000);
		String messageOnUI = null;
		if (type.equalsIgnoreCase("Endpoint Firmware")) {
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
					"Message is not visible on the Site page");
			messageOnUI = BaseUtil.getElementText(getProps().getProperty(
					"addPeripheralFirmwareSaveMsg"));
		}
		Assert.assertTrue(" Message on UI(" + messageOnUI
				+ ") is not expected(" + message + ") message",
				message.equals(messageOnUI));
	}

	@Then("^click on \"(.*?)\" tab/button from Firmware page$")
	public void click_on_tab_from_Audit_page(String sElement) throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Failed to click tab/button " + sElement;
		Assert.assertTrue(message,
				firmwarePage.clickOperationOnFirmwarepage(sElement));
	}

	@Then("^add Firmware popup should be displayed with following fields:$")
	public void add_peripheral_firmware_popup_should_be_displayed_with_following_fields(
			List<String> listHeaderNames) throws Throwable {
		BaseUtil.explicitWait(2000);
		firmwarePage.verifyElementsDisplayed(listHeaderNames);
	}

	@Then("^verify the validation messages for following fields$")
	public void verify_the_validation_messages_for_following_fields(
			DataTable table) throws Throwable {
		BaseUtil.explicitWait(8000);
		Map<String, String> lstData = table.asMap(String.class, String.class);
		Set<String> fieldsName = lstData.keySet();
		for (String field : fieldsName) {
			String message = "Invalid value entered in [" + field + "]";
			String msg;
			switch (field.toUpperCase()) {
			case "FILE":
				msg = BaseUtil.getElementText(getProps().getProperty(
						"fileFieldErrorMsg"));
				Assert.assertEquals(message, lstData.get(field), msg);
				break;
			case "RELEASE DATE":
				msg = BaseUtil.getElementText(getProps().getProperty(
						"releasedateFieldErrorMsg"));
				Assert.assertEquals(message, lstData.get(field), msg);
				break;
			case "VERSION MAJOR":
				msg = BaseUtil.getElementText(getProps().getProperty(
						"versionmajorFieldErrorMsg"));
				Assert.assertEquals(message, lstData.get(field), msg);
				break;
			case "VERSION MINOR":
				msg = BaseUtil.getElementText(getProps().getProperty(
						"versionminorFieldErrorMsg"));
				Assert.assertEquals(message, lstData.get(field), msg);
				break;
			case "CONTROLLER VERSION MAJOR":
				msg = BaseUtil.getElementText(getProps().getProperty(
						"controllerversionmajorFieldErrorMsg"));
				Assert.assertEquals(message, lstData.get(field), msg);
				break;
			case "CONTRLLER VERSION MINOR":
				msg = BaseUtil.getElementText(getProps().getProperty(
						"controllerversionminorFieldErrorMsg"));
				Assert.assertEquals(message, lstData.get(field), msg);
				break;
			case "CONTROLLER VERSION REVISION":
				msg = BaseUtil.getElementText(getProps().getProperty(
						"controllerversionrevisionFieldErrorMsg"));
				Assert.assertEquals(message, lstData.get(field), msg);
				break;
			}
		}
	}

	@Then("^click on the \"(.*?)\" button on add \"(.*?)\" popup and select \"(.*?)\" file$")
	public void click_on_the_button_on_add_peripheral_firmware_popup_and_select_an_valid_file(
			String buttonName, String tabName, String fileType)
			throws Throwable {
		BaseUtil.explicitWait(8000);
		String firmwareFile = null, filePath = null, absolutePath = null;
		File f = null;
		if (tabName.equals("Endpoint Firmware")) {
			switch (fileType.toUpperCase()) {
			case "VALID":
				firmwareFile = "live_firmware.tar.gz";
				f = new File(firmwareFile);
				absolutePath = f.getAbsolutePath();
				filePath = absolutePath.substring(0,
						absolutePath.lastIndexOf(File.separator))
						+ "\\downloadDir\\" + firmwareFile;
				break;
			case "INVALID":
				firmwareFile = "InvalidPeripheralFirmwareFile.xlsx";
				f = new File(firmwareFile);
				absolutePath = f.getAbsolutePath();
				filePath = absolutePath.substring(0,
						absolutePath.lastIndexOf(File.separator))
						+ "\\downloadDir\\" + firmwareFile;
				break;
			}

		} else if (tabName.equals("Peripheral Firmware")) {
			switch (fileType.toUpperCase()) {
			case "VALID":
				firmwareFile = "TS102_400.bin";
				f = new File(firmwareFile);
				absolutePath = f.getAbsolutePath();
				filePath = absolutePath.substring(0,
						absolutePath.lastIndexOf(File.separator))
						+ "\\downloadDir\\" + firmwareFile;
				break;
			case "INVALID":
				firmwareFile = "InvalidPeripheralFirmwareFile.xlsx";
				f = new File(firmwareFile);
				absolutePath = f.getAbsolutePath();
				filePath = absolutePath.substring(0,
						absolutePath.lastIndexOf(File.separator))
						+ "\\downloadDir\\" + firmwareFile;
				break;
			}
		}
		String message = "Failed to click on button :" + buttonName + " "
				+ " on add endpoint firmware popup.";
		Assert.assertTrue(message, firmwarePage.clickChooseButtonAndSelectFile(
				buttonName, tabName, fileType, filePath));
	}

	@Then("^verify the message \"(.*?)\" will display when peripheral firmware file with invalid format is uploaded$")
	public void verify_the_message_will_display_when_peripheral_firmware_file_with_invalid_format_is_uploaded(
			String errorMessage) throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Failed to display error message";
		Assert.assertTrue(message, firmwarePage
				.verifyErrorMessage_InvalidFileChoosen(errorMessage));
	}

	@Then("^user should be navigated on peripheral firmware detail grid$")
	public void user_should_be_navigated_on_peripheral_firmware_detail_grid()
			throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Failed to verify peripheral firmware grid availablity.  ";
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("peripheralfirmwareGrid"), message);
	}

	@Then("^enter the test data for the following fields to create new firmware$")
	public void enter_the_test_data_for_the_following_fields_to_create_new_Test_Data_for_auditing(
			DataTable arg1) throws Throwable {
		BaseUtil.explicitWait(8000);
		List<String> fieldList = arg1.asList(String.class);
		for (String list : fieldList) {
			firmwarePage.enterTestData(list);
		}
	}

	@Then("^enter the invalid test data for the following fields to create new firmware$")
	public void enter_the_invalid_test_data_for_the_following_fields_to_create_new_Test_Data_for_auditing(
			DataTable arg1) throws Throwable {
		BaseUtil.explicitWait(8000);
		List<String> fieldList = arg1.asList(String.class);
		for (String list : fieldList) {
			firmwarePage.enterInvalidTestData(list);
		}
	}

	@Then("^user is navigated on \"(.*?)\" with a label saying \"(.*?)\"$")
	public void user_should_be_navigated_to_firmware_page_with_a_label_saying_for(
			String subTabName, String peripheralFirmwareMsg) throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Expected message not matched with actual message.";
		switch (subTabName.toUpperCase()) {
		case "PERIPHERAL FIRMWARE":
			BaseUtil.waitForSpinner();
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("addPeripheralFirmwareSaveMsg"),
					"Message is not visible on the page");
			Assert.assertTrue(message, peripheralFirmwareMsg.equals(BaseUtil
					.getElementText(getProps().getProperty(
							"addPeripheralFirmwareSaveMsg"))));
			BaseUtil.waitForSpinner();
			BaseUtil.explicitWait(5000);
			break;
		case "ENDPOINT FIRMWARE":
			BaseUtil.waitForSpinner();
			BaseUtil.assertElementDisplayed(
					getProps().getProperty("addPeripheralFirmwareSaveMsg"),
					"Message is not visible on the page");
			Assert.assertTrue(message, peripheralFirmwareMsg.equals(BaseUtil
					.getElementText(getProps().getProperty(
							"addEndpointFirmwareSaveMsg"))));
			BaseUtil.waitForSpinner();
			BaseUtil.explicitWait(5000);
			break;
		}
	}

	@Then("^verify that error message will be displayed a \"(.*?)\"$")
	public void verify_that_error_message_will_be_displayed(String errorMessage)
			throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Failed to display error message";
		Assert.assertTrue(message,
				firmwarePage.verifyErrorMessage_DuplicateFirmware(errorMessage));
	}

	@Then("^verify the search output by entering \"(.*?)\" in peripheral firmware search field$")
	public void verify_the_search_output_by_entering_in_peripheral_firmware_search_field(
			String peripheralFirmwarePageSearchOutput) throws Throwable {
		BaseUtil.explicitWait(8000);
		firmwarePage
				.verifyPeripheralFirmwareSearchFunctionality(peripheralFirmwarePageSearchOutput);
	}

	@Then("^verify the pagination by entering value \"(.*?)\" in peripheral firmware search field$")
	public void verify_the_pagination_by_entering_value_in_peripheral_firmware_search_field(
			String peripheralfirmwaresearchfield) throws Throwable {
		BaseUtil.explicitWait(8000);
		firmwarePage
				.verifyPeripheralFirmwareSearchFunctionality(peripheralfirmwaresearchfield);
	}

	@When("^enter test data \"(.*?)\" in \"(.*?)\" search field$")
	public void enter_controller_min_version_in_peripheral_firmware_search_field(
			String firmwarePageTestData, String subTabName) throws Throwable {
		BaseUtil.explicitWait(8000);
		if (subTabName.equals("Peripheral Firmware")) {
			firmwarePage.enterText_FirmwarePage(subTabName,
					firmwarePageTestData);

		} else if (subTabName.equals("Endpoint Firmware")) {
			firmwarePage.enterText_FirmwarePage(subTabName,
					firmwarePageTestData);

		}

	}

	@Then("^select the firmware \"(.*?)\" from the \"(.*?)\" details grid$")
	public void select_the_firmware_from_the_firmware_details_grid(
			String testData, String sourceNameFeature) throws Throwable {
		BaseUtil.explicitWait(8000);
		boolean bResult = false;
		String gridColumnLocator = null;
		BaseUtil.explicitWait(2000);
		if (sourceNameFeature.equalsIgnoreCase("Endpoint Firmware")) {
			BaseUtil.click(getProps().getProperty(
					"endpointFirmwareSearchButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty(
					"endpointFirmwarePageNameColumn");

		} else if (sourceNameFeature.equalsIgnoreCase("Peripheral Firmware")) {
			BaseUtil.click(getProps().getProperty(
					"periperalFirmwareSearchButton"));
			BaseUtil.waitForSpinner();
			gridColumnLocator = getProps().getProperty(
					"periperalFirmwarePagePeripheralTypeColumn");

		}
		try {
			BaseUtil.explicitWait(3000);
			List<WebElement> lstSourcesNames = BaseUtil
					.getMultipleElementsAfterLoaded(gridColumnLocator);
			for (WebElement webElement : lstSourcesNames) {
				if (gridColumnLocator.equals("No records found.")) {
					bResult = false;
					break;
				} else {
					if (webElement.getText().trim().equalsIgnoreCase(testData)) {
						webElement.click();
						BaseUtil.waitForSpinner(1000);
						bResult = true;
						break;
					}
				}
			}
			if (!bResult) {
				Assert.fail("Given test " + sourceNameFeature
						+ " is not available");
			} else {
				logger.debug("Searched source :" + testData + " is selected.");
			}

		} catch (Exception e) {
			logger.error("Failed to select source: " + testData
					+ " from grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the: " + testData
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.explicitWait(3000);
		}

	}

	@Then("^verify the details of the selected firmware in following elements on firmware detail popup$")
	public void verify_the_details_of_the_selected_firmware_in_following_elements_on_firmware_detail_popup(
			List<String> listHeaderNames) throws Throwable {
		BaseUtil.explicitWait(8000);
		firmwarePage.verifyElementsDisplayed(listHeaderNames);
	}

	@When("^verify the \"(.*?)\" page is being displayed$")
	public void verify_page_should_be_displayed(String firmwarePopupName)
			throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Failed to check existence of edit Firmware popup.";
		String expectedMessage = null;
		Assert.assertTrue(message, firmwarePage.checkPopupExistence(
				firmwarePopupName, expectedMessage));
	}

	@Then("^confirmation box on detail peripheral firmware popup should displayed with message \"(.*?)\"$")
	public void confirmation_box_on_detail_peripheral_firmware_popup_should_displayed_with_message(
			String confirmationboxMessage) throws Throwable {
		if (confirmationboxMessage.toLowerCase().startsWith("are you sure")) {
			boolean okButton = BaseUtil
					.verifyElementDisplayed(getProps().getProperty(
							"DeletePeripheralFirmwareOkConfirmationButton"));
			boolean cancelButton = BaseUtil
					.verifyElementDisplayed(getProps().getProperty(
							"DeletePeripheralFirmwareCancelConfirmationButton"));
			Assert.assertTrue(
					"Behavior confirmation popup did not appear when clicked on delete button of edit Peripheral Firmware",
					(okButton && cancelButton));
		}
	}

	@Then("^user should remain on \"(.*?)\" detail popup$")
	public void user_should_remain_on_peripheral_firmware_detail_popup(
			String firmwarePopupName) throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Failed to check existence of edit Firmware popup.";
		String expectedMessage = null;
		Assert.assertTrue(message, firmwarePage.checkPopupExistence(
				firmwarePopupName, expectedMessage));
	}

	@Then("verify Export icon is display on Firmware tab$")
	public void verify_Export_icon_is_display_on_Audit_tab() throws Throwable {
		String message = "Export Icon is not available on Audit Tab";
		BaseUtil.assertElementDisplayed(getProps().getProperty("exportIcon"),
				message);
	}

	@And("^verify the validation messages \"(.*?)\" for \"(.*?)\" field on add Endpoint Firmware$")
	public void verify_the_validation_messages_for_following_fields(
			String errorMessage, String fieldName) throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Failed to verify error message[" + errorMessage
				+ "] for field [" + fieldName
				+ "] on add Endpoint Firmware popup.";
		Assert.assertTrue(message, firmwarePage
				.verifyErrorMessageOnAddEndpointFirmwarePopup(errorMessage,
						fieldName));
	}

	@Then("^verify the message \"(.*?)\" will display when wrong endpoint firmware file is uploaded$")
	public void verify_the_message_will_display_when_endpoint_firmware_file_with_wrong_name_is_uploaded(
			String errorMessage) throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Failed to display error message";
		Assert.assertTrue(message, firmwarePage
				.verifyErrorMessage_InvalidFileChoosen(errorMessage));
	}

	@Then("^confirmation box on \"(.*?)\" popup should displayed with message \"(.*?)\"$")
	public void confirmation_box_on_firmware_popup_should_displayed_with_message(
			String popupName, String endpointFirmwareMessage) throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Failed to display the confirmation dialog with message: "
				+ endpointFirmwareMessage;
		Assert.assertTrue(message, firmwarePage.checkPopupExistence(popupName,
				endpointFirmwareMessage));
	}

	@Then("^user should be navigated on endpoint firmware detail grid$")
	public void user_should_be_navigated_on_endpoint_firmware_detail_grid()
			throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Failed to verify endpoint firmware grid availablity.  ";
		BaseUtil.assertElementDisplayed(
				getProps().getProperty("endpointfirmwareGrid"), message);
	}

	@Then("^verify the search output by entering \"(.*?)\" in endpoint firmware search field on endpoint firmware page$")
	public void verify_the_search_output_by_entering_in_endpoint_firmware_search_field(
			String args) throws Throwable {
		BaseUtil.explicitWait(8000);
		firmwarePage.verifyEndpointFirmwareSearchFunctionality(args);
	}

	@Then("^verify the pagination by entering value \"(.*?)\" in endpoint firmware search field on endpoint firmware page$")
	public void verify_the_pagination_by_entering_value_in_endpoint_firmware_search_field(
			String args) throws Throwable {
		BaseUtil.explicitWait(8000);
		firmwarePage.verifyEndpointFirmwareSearchFunctionality(args);
	}

	@When("^modify release date field and version field for the selected endpoint firmware on edit endpoint fimrware$")
	public void modify_relase_date_field_and_version_field_for_the_selected_endpoint_firmware(
			DataTable arg1) throws Throwable {
		BaseUtil.explicitWait(8000);
		List<String> fieldList = arg1.asList(String.class);
		for (String list : fieldList) {
			firmwarePage.modifyTextEndpointFirmware(list);
		}
	}

	@Then("^verify the audit details grid is available inside \"(.*?)\" accordion in edit endpoint firmware$")
	public void verify_the_details_grid_is_available_inside_accordion_in_edit_endpoint_firmware_popup(
			String accordion) throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Failed to verify endpoint firmware grid availablity.  ";
		BaseUtil.click(getProps().getProperty(
				"editEndpointFirmwareAuditAccordion"));
		BaseUtil.assertElementDisplayed(
				getProps().getProperty(
						"editEndpointFirmwareAuditAccordionDetailGrid"),
				message);
	}

	@Then("^verify that newly created \"(.*?)\" logs are present in \"(.*?)\" details grid$")
	public void verify_that_newly_created_logs_are_present_in_details_grid(
			String endpointFirmware, String accordion) throws Throwable {
		BaseUtil.explicitWait(8000);
		String message = "Failed to verify logs of  newly created Endpoint Firmware ["
				+ endpointFirmware + "]";
		Assert.assertTrue(message, firmwarePage
				.verifyLogsOfNewlyCreatedFirmware(endpointFirmware, accordion));
	}

	@Then("^verify that pagination buttons are available in \"(.*?)\" details grid$")
	public void verify_that_pagination_buttons_are_available_in_details_grid(
			String arg1) throws Throwable {
		BaseUtil.explicitWait(8000);
		boolean nextFlg = BaseUtil
				.verifyElementDisplayed(getProps().getProperty(
						"editEndpointFirmwareAuditAccordionPaginationNext"));
		boolean lastFlg = BaseUtil
				.verifyElementDisplayed(getProps().getProperty(
						"editEndpointFirmwareAuditAccordionPaginationLast"));
		boolean prevFlg = BaseUtil
				.verifyElementDisplayed(getProps().getProperty(
						"editEndpointFirmwareAuditAccordionPaginationPrev"));
		boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps()
				.getProperty(
						"editEndpointFirmwareAuditAccordionPaginationFirst"));
		if (!(nextFlg && lastFlg && prevFlg && firstFlg)) {
			Assert.fail("Pagination buttons are not visible");
		}
	}

	@Then("^verify the search output by entering \"(.*?)\" in update endpoint firmware search field$")
	public void verify_the_search_output_by_entering_in_uendpoint_firmware_search_field(
			String args) throws Throwable {
		BaseUtil.explicitWait(8000);
		firmwarePage.verifyUpdateEndpointFirmwareSearchFunctionality(args);
	}

	@Then("^confirmation box on edit endpoint firmware popup should displayed with message \"(.*?)\"$")
	public void confirmation_box_on_edit_endpoint_firmware_popup_should_displayed_with_message(
			String confirmationboxMessage) throws Throwable {
		BaseUtil.explicitWait(8000);
		if (!isEndpointFirmwarePresent) {
			return;
		}
		BaseUtil.explicitWait(2000);
		if (confirmationboxMessage.toLowerCase().startsWith("are you sure")) {
			boolean okButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("DeleteEndpointFirmwareOkConfirmationButton"));
			boolean cancelButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty(
							"DeleteEndpointFirmwareCancelConfirmationButton"));
			Assert.assertTrue(
					"Behavior confirmation popup did not appear when clicked on delete button of edit Endpoint Firmware",
					(okButton && cancelButton));
		} else if (confirmationboxMessage.toLowerCase().endsWith(
				"are you sure?")) {
			boolean okButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("saveEndpointFirmwareButton"));
			boolean cancelButton = BaseUtil.verifyElementDisplayed(getProps()
					.getProperty("cancelEndpointFirmwareButton"));
			Assert.assertTrue(
					"Confirmation popup did not appear when clicked on cancel button",
					(okButton && cancelButton));
		}
	}
}