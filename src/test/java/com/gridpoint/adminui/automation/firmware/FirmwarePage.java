package com.gridpoint.adminui.automation.firmware;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.util.BaseUtil;

public class FirmwarePage extends CommonInit {

	private Logger logger = Logger.getLogger(FirmwarePage.class);

	public void verifySorting_FirmwarePage(String sSortingType, String sColName) {
		List<String> listColumnValues = null;
		try {
			switch (sColName.toUpperCase()) {
			case "PERIPHERAL FIRMWARE:RELEASE DATE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"peripheralfirmwarePageReleaseDateHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"peripheralfirmwarePageReleaseDateHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"peripheralfirmwarePageReleaseDateHeader"));
				break;
			case "PERIPHERAL FIRMWARE:ACTIVE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"peripheralfirmwarePageActiveHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"peripheralfirmwarePageActiveHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"peripheralfirmwarePageActiveHeader"));
				break;
			case "PERIPHERAL FIRMWARE:VERSION":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"peripheralfirmwarePageVersionHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"peripheralfirmwarePageVersionHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"peripheralfirmwarePageVersionHeader"));
				break;
			case "PERIPHERAL FIRMWARE:PERIPHERAL TYPE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"peripheralfirmwarePagePeripheralTypeHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"peripheralfirmwarePagePeripheralTypeHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"peripheralfirmwarePagePeripheralTypeHeader"));
				break;
			case "PERIPHERAL FIRMWARE:PERIPHERAL TYPE ID":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"peripheralfirmwarePagePeripheralTypeIDHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"peripheralfirmwarePagePeripheralTypeIDHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"peripheralfirmwarePagePeripheralTypeIDHeader"));
				break;
			case "PERIPHERAL FIRMWARE:CONTROLLER MIN VERSION":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"peripheralfirmwarePageControllerMinVersionHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"peripheralfirmwarePageControllerMinVersionHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"peripheralfirmwarePageControllerMinVersionHeader"));
				break;
			case "ENDPOINT FIRMWARE:NAME":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"endpointfirmwarePageNameHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"endpointfirmwarePageNameHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("endpointfirmwarePageNameHeader"));
				break;
			case "ENDPOINT FIRMWARE:VERSION":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"endpointFirmwarePageVersionHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"endpointFirmwarePageVersionHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"endpointFirmwarePageVersionHeader"));
				break;
			case "ENDPOINT FIRMWARE:ENDPOINT TYPE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"endpointFirmwarePageEndpointTypeHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"endpointFirmwarePageEndpointTypeHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"endpointFirmwarePageEndpointTypeHeader"));
				break;
			case "ENDPOINT FIRMWARE:RELEASE DATE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"endpointFirmwarePageReleaseDateHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"endpointFirmwarePageReleaseDateHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"endpointFirmwarePageReleaseDateHeader"));
				break;
			case "ENDPOINT FIRMWARE:ACTIVE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"endpointFirmwarePageActiveHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"endpointFirmwarePageActiveHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("endpointFirmwarePageActiveHeader"));
				break;
			case "AUDITGRID:PROPERTY":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editEndpointFirmwareAuditGridPropertyHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editEndpointFirmwareAuditGridPropertyHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editEndpointFirmwareAuditGridPropertyHeader"));
				break;
			case "AUDITGRID:ACTION":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editEndpointFirmwareAuditGridActionHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editEndpointFirmwareAuditGridActionHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editEndpointFirmwareAuditGridActionHeader"));
				break;
			case "AUDITGRID:ORIGINAL VALUE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editEndpointFirmwareAuditGridOriginalValueHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editEndpointFirmwareAuditGridOriginalValueHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editEndpointFirmwareAuditGridOriginalValueHeader"));
				break;
			case "AUDITGRID:UPDATED VALUE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editEndpointFirmwareAuditGridUpdatedValueHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editEndpointFirmwareAuditGridUpdatedValueHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editEndpointFirmwareAuditGridUpdatedValueHeader"));
				break;
			case "AUDITGRID:USER":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editEndpointFirmwareAuditGridUserHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editEndpointFirmwareAuditGridUserHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editEndpointFirmwareAuditGridUserHeader"));
				break;
			case "AUDITGRID:DATE STAMP":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"editEndpointFirmwareAuditGridDateStampHeader"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"editEndpointFirmwareAuditGridDateStampHeader"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"editEndpointFirmwareAuditGridDateStampHeader"));
				break;
			case "UEF:ENDPOINT ID":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"updateEndpointFirmwarePageColumnEndpointID"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"updateEndpointFirmwarePageColumnEndpointID"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"updateEndpointFirmwarePageColumnEndpointID"));
				break;
			case "UEF:TENANT":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"updateEndpointFirmwarePageColumnTenant"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"updateEndpointFirmwarePageColumnTenant"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"updateEndpointFirmwarePageColumnTenant"));
				break;
			case "UEF:PREMISES":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"updateEndpointFirmwarePageColumnPremises"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"updateEndpointFirmwarePageColumnPremises"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"updateEndpointFirmwarePageColumnPremises"));
				break;
			case "UEF:CONNECTION STATUS":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"updateEndpointFirmwarePageColumnConnectionStatus"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"updateEndpointFirmwarePageColumnConnectionStatus"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"updateEndpointFirmwarePageColumnConnectionStatus"));
				break;
			case "UEF:TYPE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"updateEndpointFirmwarePageColumnType"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"updateEndpointFirmwarePageColumnType"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"updateEndpointFirmwarePageColumnType"));
				break;
			case "UEF:SERIAL":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"updateEndpointFirmwarePageColumnSerial"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"updateEndpointFirmwarePageColumnSerial"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"updateEndpointFirmwarePageColumnSerial"));
				break;
			case "UEF:LIVE FIRMWARE":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty(
							"updateEndpointFirmwarePageColumnLiveFirmware"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty(
							"updateEndpointFirmwarePageColumnLiveFirmware"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"updateEndpointFirmwarePageColumnLiveFirmware"));
				break;
			case "UEF:LAST MESSAGE RECEIVED (UTC)":
				if (sSortingType.equals("Descending")) {
					BaseUtil.click(getProps()
							.getProperty(
									"updateEndpointFirmwarePageColumnLastMessageReceived"));
					BaseUtil.waitForSpinner();
				} else if (sSortingType.equals("Ascending")) {
					BaseUtil.click(getProps()
							.getProperty(
									"updateEndpointFirmwarePageColumnLastMessageReceived"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty(
										"updateEndpointFirmwarePageColumnLastMessageReceived"));
				break;
			default:
				logger.error("Switch Case["
						+ sColName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid column name: "
						+ sColName
						+ ", Please pass valid column name e.g.[Name,Hostname, parent] from feature file or test class.");
			}

			Assert.assertTrue(sColName + " is not in " + sSortingType
					+ " order",
					BaseUtil.isSorted(sSortingType, listColumnValues));

		} catch (Exception e) {
			logger.error("Failed to sort the column :" + sColName
					+ " Sorting order:" + sSortingType + " , detail message: "
					+ e.getStackTrace().toString());
			Assert.fail(e.getStackTrace().toString());
		}
	}

	public void clickPaginationFirmwarePage(String peripheralFirmwareLinkName) {
		try {
			switch (peripheralFirmwareLinkName.toUpperCase()) {
			case "NEXT":
				Assert.assertTrue(
						"Required data is not present. Next button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"firmwarePaginationNext")));
				BaseUtil.click(getProps().getProperty("firmwarePaginationNext"));
				break;
			case "PREV":
				Assert.assertTrue(
						"Required data is not present. Prev button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"firmwarePaginationPrev")));
				BaseUtil.click(getProps().getProperty("firmwarePaginationPrev"));
				break;
			case "LAST":
				Assert.assertTrue(
						"Required data is not present. Last button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"firmwarePaginationLast")));
				BaseUtil.click(getProps().getProperty("firmwarePaginationLast"));
				break;
			case "FIRST":
				Assert.assertTrue(
						"Required data is not present. First button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"firmwarePaginationFirst")));
				BaseUtil.click(getProps()
						.getProperty("firmwarePaginationFirst"));
				break;
			default:
				logger.error("Switch Case["
						+ peripheralFirmwareLinkName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid button clicked");
			}
		} catch (Exception e) {
			logger.error("Failed to click on link :"
					+ peripheralFirmwareLinkName + " , detail message: "
					+ e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	public void verifyFirmwareDetailGridPagination(String task) {
		String totalPages = null;
		try {
			switch (task.toUpperCase()) {
			case "NEXT":
				BaseUtil.getElementAttribute(
						getProps().getProperty("firmwarePaginationCurrentPage"),
						"value");
				break;
			case "PREVIOUS":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"firmwarePaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				BaseUtil.getElementAttribute(
						getProps().getProperty("firmwarePaginationCurrentPage"),
						"value");
				break;
			case "LAST":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"firmwarePaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				BaseUtil.getElementAttribute(
						getProps().getProperty("firmwarePaginationCurrentPage"),
						"value");
				break;
			case "FIRST":
				BaseUtil.getElementAttribute(
						getProps().getProperty("firmwarePaginationCurrentPage"),
						"value");
				break;
			default:
				logger.error("Switch Case["
						+ task
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid task clicked");
			}
		} catch (Exception e) {
			logger.error("Failed to click on link :" + task
					+ " , detail message: " + e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	public boolean setRandomPage(int iTotalPage, int iPageNum) {
		String sArrow = "";
		int iDiff = 0;
		try {
			int iCurrentPage = Integer.parseInt(BaseUtil.getElementAttribute(
					getProps().getProperty("firmwarePaginationCurrentPage"),
					"value"));
			if (iCurrentPage >= iPageNum) {
				iDiff = iCurrentPage - iPageNum;
				sArrow = "DOWN";
			} else {
				iDiff = iPageNum - iCurrentPage;
				sArrow = "UP";
			}
			WebElement element = BaseUtil.getElementAfterLoaded(getProps()
					.getProperty("firmwarePaginationCurrentPage"));
			element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			for (int iCount = 1; iCount < iDiff + 1; iCount++) {
				if (sArrow.equals("UP")) {
					element.sendKeys(Keys.chord(Keys.ARROW_UP));
				} else {
					element.sendKeys(Keys.chord(Keys.ARROW_DOWN));
				}
			}
			return true;
		} catch (Exception e) {
			logger.error("Some error occured while setting the number["
					+ iPageNum + "] in page number input box : "
					+ e.getMessage());
			return false;
		}
	}

	public boolean clickOperationOnFirmwarepage(String buttonName) {
		try {
			String locator = null;
			switch (buttonName.toUpperCase()) {
			case "FIRMWARE":
				locator = getProps().getProperty("firmwareTab");
				break;
			case "ENDPOINT FIRMWARE":
				locator = getProps().getProperty("endpointFirmwareTab");
				break;
			case "PERIPHERAL FIRMWARE":
				locator = getProps().getProperty("peripheralFirmwareTab");
				break;
			case "DELETE ENDPOINT FIRMWARE":
				locator = getProps()
						.getProperty("deleteEndpointFirmwareButton");
				break;
			case "ENDPOINT FIRMWARE:DELETE_OK":
				locator = getProps().getProperty(
						"DeleteEndpointFirmwareOkConfirmationButton");
				break;
			case "DELETE PERIPHERAL FIRMWARE":
				locator = getProps().getProperty(
						"deletePeripheralFirmwareButton");
				break;
			case "PERIPHERAL FIRMWARE:DELETE_OK":
				locator = getProps().getProperty(
						"DeletePeripheralFirmwareOkConfirmationButton");
				break;
			case "ADD PERIPHERAL FIRMWARE":
				locator = getProps().getProperty("addPeripheralFirmwareButton");
				break;
			case "SAVE PERIPHERAL FIRMWARE":
				locator = getProps().getProperty(
						"addPeripheralFirmwareApplyButton");
				break;
			case "CLOSE ADD PERIPHERAL FIRMWARE":
				locator = getProps().getProperty(
						"addPeripheralFirmwareCloseButton");
				break;
			case "ADD PERIPHERAL FIRMWARE:X":
				locator = getProps()
						.getProperty("addPeripheralFirmwareXButton");
				break;
			case "PERIPHERAL FIRMWARE:SEARCH":
				locator = getProps().getProperty(
						"periperalFirmwareSearchButton");
				break;
			case "CLOSE EDIT PERIPHERAL FIRMWARE":
				locator = getProps().getProperty(
						"editPeripheralFirmwareCloseButton");
				break;
			case "CANCEL DELETE PERIPHERAL FIRMWARE":
				locator = getProps().getProperty(
						"DeletePeripheralFirmwareCancelConfirmationButton");
				break;
			case "ADD ENDPOINT FIRMWARE":
				locator = getProps().getProperty("addEndpointFirmwareButton");
				break;
			case "SAVE ENDPOINT FIRMWARE":
				locator = getProps().getProperty(
						"addEndpointFirmwareSaveButton");
				break;
			case "CANCEL ENDPOINT FIRMWARE":
				locator = getProps().getProperty(
						"addEndpointFirmwareCancelButton");
				break;
			case "ADD ENDPOINT FIRMWARE:OK_CANCEL":
				locator = getProps().getProperty(
						"addEndpointFirmwareOkCancelButton");
				break;
			case "ADD ENDPOINT FIRMWARE:CANCEL_CANCEL":
				locator = getProps().getProperty(
						"addEndpointFirmwareCancelCancelButton");
				break;
			case "ENDPOINT FIRMWARE:SEARCH":
				locator = getProps()
						.getProperty("endpointFirmwareSearchButton");
				break;
			case "EDIT ENDPOINT FIRMWARE:SAVE":
				locator = getProps().getProperty(
						"editEndpointFirmwareSaveButton");
				break;
			case "EDIT ENDPOINT FIRMWARE:CANCEL":
				locator = getProps().getProperty(
						"editEndpointFirmwareCancelButton");
				break;
			case "EDIT ENDPOINT FIRMWARE:OK_CANCEL":
				locator = getProps().getProperty(
						"editEndpointFirmwareOkCancelButton");
				break;
			case "DELETE ENDPOINT FIRMWARE:CANCEL":
				locator = getProps().getProperty(
						"DeleteEndpointFirmwareCancelConfirmationButton");
				break;
			case "DELETE ENDPOINT FIRMWARE:OK":
				locator = getProps().getProperty(
						"DeleteEndpointFirmwareOkConfirmationButton");
				break;
			default:
				logger.error("Switch Case["
						+ buttonName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
			}

			BaseUtil.clickAndWait(locator);
			return true;

		} catch (Exception e) {
			logger.error("Failed to click on button : " + buttonName
					+ " , detail message: " + e.getMessage());
			return false;
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public void verifyElementsDisplayed(List<String> listElementNames,
			String... tabNo) {
		String labelLocator = null, flag = null, labelCheckErrorMessage = null, clickableErrorMsg = null;
		String message = null;
		for (String elementName : listElementNames) {
			String realElementName;
			if (elementName.split(":").length == 2) {
				realElementName = elementName.split(":")[1].trim();
			} else {
				realElementName = elementName;
			}
			message = "[" + elementName + "] is not available";
			labelCheckErrorMessage = "[" + elementName
					+ "] is not having text as expected";
			clickableErrorMsg = "[" + elementName + "] is not clickable";

			try {
				switch (elementName.toUpperCase()) {
				case "PERIPHERAL FIRMWARE:FILE":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addPeripheralFirmwareFileField");
					break;
				case "PERIPHERAL FIRMWARE:PERIPHERAL TYPE":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addPeripheralFirmwarePeripheralTypeField");
					break;
				case "PERIPHERAL FIRMWARE:RELEASE DATE":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addPeripheralFirmwareReleaseDateField");
					break;
				case "PERIPHERAL FIRMWARE:VERSION MAJOR":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addPeripheralFirmwareVersionMajorField");
					break;
				case "PERIPHERAL FIRMWARE:VERSION MINOR":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addPeripheralFirmwareVersionMinorField");
					break;
				case "PERIPHERAL FIRMWARE:CONTROLLER VERSION MAJOR":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addPeripheralFirmwareControllerVersionMajorField");
					break;
				case "PERIPHERAL FIRMWARE:CONTROLLER VERSION MINOR":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addPeripheralFirmwareControllerVersionMinorField");
					break;
				case "PERIPHERAL FIRMWARE:CONTROLLER VERSION REVISION":
					flag = "Label";
					labelLocator = getProps()
							.getProperty(
									"addPeripheralFirmwareControllerVersionRevisionField");
					break;
				case "PERIPHERAL FIRMWARE:APPLY BUTTON":
					flag = "Button";
					labelLocator = getProps().getProperty(
							"addPeripheralFirmwareApplyButton");
					break;
				case "PERIPHERAL FIRMWARE:CLOSE BUTTON":
					flag = "Button";
					labelLocator = getProps().getProperty(
							"addPeripheralFirmwarCloseButton");
					break;
				case "EDIT PERIPHERAL FIRMWARE:PERIPHERAL TYPE":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"editPeriPheralFirmwarePeripheralTypeField");
					break;
				case "EDIT PERIPHERAL FIRMWARE:PERIPHERAL TYPE ID":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"editPeriPheralFirmwarePeripheralTypeIDField");
					break;
				case "EDIT PERIPHERAL FIRMWARE:RELEASE DATE":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"editPeriPheralFirmwareReleaseDateField");
					break;
				case "EDIT PERIPHERAL FIRMWARE:VERSION":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"editPeriPheralFirmwareVersionField");
					break;
				case "EDIT PERIPHERAL FIRMWARE:MIN CONTROLLER VERSION":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"editPeriPheralFirmwareMinControllerVersionField");
					break;
				case "EDIT PERIPHERAL FIRMWARE:CLOSE":
					flag = "Button";
					labelLocator = getProps().getProperty(
							"editPeripheralFirmwareCloseButton");
					break;
				case "EDIT PERIPHERAL FIRMWARE:DELETE":
					flag = "Button";
					labelLocator = getProps().getProperty(
							"deletePeripheralFirmwareButton");
					break;
				case "ENDPOINT FIRMWARE:FILE":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addEndpointFirmwareFileLabel");
					break;
				case "ENDPOINT FIRMWARE:NAME":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addEndpointFirmwareNameLabel");
					break;
				case "ENDPOINT FIRMWARE:VERSION":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addEndpointFirmwareVersionLabel");
					break;
				case "ENDPOINT FIRMWARE:ENDPOINT TYPE":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addEndpointFirmwareEndpointTypeLabel");
					break;
				case "ENDPOINT FIRMWARE:RELEASE DATE":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addEndpointFirmwareReleaseDateLabel");
					break;
				case "ENDPOINT FIRMWARE:SAVE BUTTON":
					flag = "Button";
					labelLocator = getProps().getProperty(
							"addEndpointFirmwareSaveButton");
					break;
				case "ENDPOINT FIRMWARE:CANCEL BUTTON":
					flag = "Button";
					labelLocator = getProps().getProperty(
							"addEndpointFirmwareCancelButton");
					break;
				case "EDIT ENDPOINT FIRMWARE:NAME":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addEndpointFirmwareNameLabel");
					break;
				case "EDIT ENDPOINT FIRMWARE:VERSION":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addEndpointFirmwareVersionLabel");
					break;
				case "EDIT ENDPOINT FIRMWARE:ENDPOINT TYPE":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addEndpointFirmwareEndpointTypeLabel");
					break;
				case "EDIT ENDPOINT FIRMWARE:RELEASE DATE":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"addEndpointFirmwareReleaseDateLabel");
					break;
				case "EDIT ENDPOINT FIRMWARE:ACTIVE":
					flag = "Label";
					labelLocator = getProps().getProperty(
							"editEndpointFirmwareActiveLabel");
					break;
				case "EDIT ENDPOINT FIRMWARE:DELETE BUTTON":
					flag = "Button";
					labelLocator = getProps().getProperty(
							"editEndpointFirmwareDeleteButton");
					break;
				case "EDIT ENDPOINT FIRMWARE:SAVE BUTTON":
					flag = "Button";
					labelLocator = getProps().getProperty(
							"editEndpointFirmwareSaveButton");
					break;
				case "EDIT ENDPOINT FIRMWARE:CANCEL BUTTON":
					flag = "Button";
					labelLocator = getProps().getProperty(
							"editEndpointFirmwareCancelButton");
					break;
				default:
					logger.error("Switch Case["
							+ elementName
							+ "] is not matched in class["
							+ getClass().getName()
							+ "] , Method["
							+ Thread.currentThread().getStackTrace()[1]
									.getMethodName() + "]");
					Assert.fail("Invalid element name : " + elementName
							+ " selected");
				}

				if (flag.equalsIgnoreCase("Label")) {
					BaseUtil.assertElementDisplayed(labelLocator, message);
				} else if (flag.equalsIgnoreCase("Button")) {
					BaseUtil.assertElementDisplayed(labelLocator, message);
					Assert.assertTrue(labelCheckErrorMessage, realElementName
							.equals(BaseUtil.getElementText(labelLocator)));
					Assert.assertTrue(clickableErrorMsg,
							BaseUtil.isElementClickable(labelLocator));
				}
			} catch (Exception e) {
				logger.error("Failed to verify the display of element: "
						+ elementName + " See detail message: "
						+ e.getMessage());
				Assert.fail(e.getMessage());
			}
		}
	}

	public boolean clickChooseButtonAndSelectFile(String buttonName,
			String tabName, String fileType, String filePath) {
		try {
			switch (tabName.toUpperCase()) {
			case "ENDPOINT FIRMWARE":
				BaseUtil.uploadFile(
						getProps().getProperty(
								"addEndpointFirmwareBrowseButton"), filePath);
				BaseUtil.explicitWait(2000);
				return true;
			case "PERIPHERAL FIRMWARE":
				BaseUtil.uploadFile(
						getProps().getProperty(
								"addperipheralFirmwareBrowseButton"), filePath);
				BaseUtil.explicitWait(2000);
				return true;
			default:
				logger.error("Switch Case["
						+ tabName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
			}

		} catch (Exception e) {
			logger.error("Failed to click on button :" + buttonName
					+ " , detail message: " + e.getMessage());
			return false;
		}
		return false;
	}

	public boolean verifyErrorMessage_InvalidFileChoosen(String errorMessage) {
		return BaseUtil.getElementText(
				getProps().getProperty("addPeripheralFirmwareBlankFileErr"))
				.equalsIgnoreCase(errorMessage);
	}

	public void enterTestData(String list) {
		try {
			Calendar calendar;
			Date releaseDate;
			DateFormat outputFormatter;
			String enteredDate = null, flag = null, locator = null, testData = null;
			calendar = Calendar.getInstance();
			outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
			releaseDate = calendar.getTime();
			enteredDate = outputFormatter.format(releaseDate);
			switch (list.toUpperCase()) {

			// Peripheral Firmware Test Data
			case "PERIPHERAL FIRMWARE:PERIPHERAL TYPE":
				flag = "Dropdown";
				locator = getProps().getProperty(
						"addPeripheralFirmwarePeripheralType");
				testData = getProps().getProperty(
						"addPeripheralFirmwarePeripheralTypeTestData");
				break;
			case "PERIPHERAL FIRMWARE:RELEASE DATE":
				flag = "Dropdown";
				locator = getProps().getProperty(
						"addPeripheralFirmwareReleaseDate");
				testData = getProps().getProperty(
						"addPeripheralFirmwareReleaseDateTestData");
				break;
			case "PERIPHERAL FIRMWARE:VERSION MAJOR":
				flag = "Field";
				locator = getProps().getProperty(
						"addPeripheralFirmwareVersionMajor");
				testData = getProps().getProperty(
						"addPeripheralFirmwareVersionMajorTestData");
				break;
			case "PERIPHERAL FIRMWARE:VERSION MINOR":
				flag = "Field";
				locator = getProps().getProperty(
						"addPeripheralFirmwareVersionMinor");
				testData = getProps().getProperty(
						"addPeripheralFirmwareVersionMinorTestData");
				break;
			case "PERIPHERAL FIRMWARE:CONTROLLER VERSION MAJOR":
				flag = "Dropdown";
				locator = getProps().getProperty(
						"addPeripheralFirmwareControllerVersionMajor");
				testData = getProps().getProperty(
						"addPeripheralFirmwareControllerVersionMajorTestData");
				break;
			case "PERIPHERAL FIRMWARE:CONTROLLER VERSION MINOR":
				flag = "Field";
				locator = getProps().getProperty(
						"addPeripheralFirmwareControllerVersionMinor");
				testData = getProps().getProperty(
						"addPeripheralFirmwareControllerVersionMinorTestData");
				break;
			case "PERIPHERAL FIRMWARE:CONTROLLER VERSION REVISION":
				flag = "Field";
				locator = getProps().getProperty(
						"addPeripheralFirmwareControllerVersionRevision");
				testData = getProps()
						.getProperty(
								"addPeripheralFirmwareControllerVersionRevisionTestData");
				break;
			case "ENDPOINT FIRMWARE:NAME":
				flag = "Field";
				locator = getProps()
						.getProperty("addEndpointFimrwareNameField");
				testData = getProps().getProperty(
						"addEndpointFimrwareNameFieldTestData");
				break;
			case "ENDPOINT FIRMWARE:VERSION":
				flag = "Field";
				locator = getProps().getProperty(
						"addEndpointFimrwareVersionField");
				testData = getProps().getProperty(
						"addEndpointFimrwareVersionFieldTestData");
				break;
			case "ENDPOINT FIRMWARE:ENDPOINT TYPE":
				flag = "Dropdown";
				locator = getProps().getProperty(
						"addEndpointFimrwareEndpointTypeField");
				testData = getProps().getProperty(
						"addEndpointFimrwareEndpointTypeFieldTestData");
				break;
			case "ENDPOINT FIRMWARE:RELEASE DATE":
				flag = "Field";
				locator = getProps().getProperty(
						"addEndpointFimrwareReleaseDateField");
				testData = getProps().getProperty(
						"addEndpointFimrwareReleaseDateFieldTestData");
				break;
			default:
				logger.error("Switch Case["
						+ list
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
			}

			testData = testData.replace("#index#", enteredDate);

			if (flag.equalsIgnoreCase("Field")) {
				BaseUtil.enterText(locator, testData);
			} else if (flag.equalsIgnoreCase("Dropdown")) {
				BaseUtil.selectDropDownByValue(locator, testData);
			}
		} catch (Exception e) {
			logger.error("Failed to enter text in field :" + list
					+ " see detail message : " + e.getMessage());
		}
	}

	public boolean verifyErrorMessage_DuplicateFirmware(String errorMessage) {
		return BaseUtil.getElementText(
				getProps().getProperty("DuplicatePeripheralFirmwareErrorMsg"))
				.equalsIgnoreCase(errorMessage);
	}

	public void verifyPeripheralFirmwareSearchFunctionality(String arg) {
		String[] arr = arg.split(":");
		String keyword = arr[1];
		String totalItemsText = null;
		int initialTotalItemsCount = 0;
		int laterTotalItemsCount = 0;
		WebElement searchBox = null;
		try {
			switch (keyword.toUpperCase()) {
			case "INIT3CHAR":
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				BaseUtil.enterText(
						getProps().getProperty("peripheralFirmwareSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"peripheralFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				try {
					if (BaseUtil
							.getElementText(
									getProps().getProperty(
											"peripheralFirmwareGridZeroRecord"))
							.equalsIgnoreCase(
									BaseUtil.getElementText(getProps()
											.getProperty(
													"firmwareGridZeroRecordText")))) {
						Assert.fail("No record available. Test cannot be continue.");
					}
				} catch (Exception e) {
					logger.info("Sufficient records are available");
				}

				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount > initialTotalItemsCount) {
					Assert.fail("Endpoint search results after entering 3 characters "
							+ arr[0] + " is not as expected");
				}
				break;
			case "RANDOM":
				String randomSearchString = RandomStringUtils.randomAscii(10);
				BaseUtil.enterText(
						getProps().getProperty("peripheralFirmwareSearchField"),
						randomSearchString);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"peripheralFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				String elementText = BaseUtil.getElementText(getProps()
						.getProperty("peripheralFirmwareGridZeroRecord"));
				Assert.assertTrue(
						"Multiple results found for the string : "
								+ randomSearchString,
						elementText.equalsIgnoreCase(getProps().getProperty(
								"firmwareGridZeroRecordText")));
				break;
			case "LONGTEXT":
				String longTextSearchString = RandomStringUtils
						.randomAlphanumeric(50);
				BaseUtil.enterText(
						getProps().getProperty("peripheralFirmwareSearchField"),
						longTextSearchString);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"peripheralFirmwareSearchField"));
				BaseUtil.waitForSpinner();
				String elementLongText = BaseUtil.getElementText(getProps()
						.getProperty("peripheralFirmwareGridZeroRecord"));
				Assert.assertTrue("No Record for String : "
						+ longTextSearchString, elementLongText
						.equalsIgnoreCase(getProps().getProperty(
								"peripheralFirmwareGridZeroRecordText")));
				break;
			case "BACKSPACE":
				BaseUtil.enterText(
						getProps().getProperty("peripheralFirmwareSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"periperalFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				searchBox = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("peripheralFirmwareSearchField"));
				searchBox.sendKeys(Keys.BACK_SPACE);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"periperalFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount < initialTotalItemsCount) {
					Assert.fail("Peripheral Firmware search results are not as expected when backspace is clicked");
				}
				break;
			case "DELETE":
				BaseUtil.enterText(
						getProps().getProperty("peripheralFirmwareSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"periperalFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());

				searchBox = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("peripheralFirmwareSearchField"));
				searchBox.sendKeys(Keys.CONTROL + "a");
				searchBox.sendKeys(Keys.DELETE);
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount < initialTotalItemsCount) {
					Assert.fail("Peripheral Firmware search results after deleting the keyword "
							+ arr[0] + " is not as expected");
				}
				break;
			case "PAGINATION":
				BaseUtil.enterText(
						getProps().getProperty("peripheralFirmwareSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"periperalFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				boolean nextFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("firmwarePaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("firmwarePaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("firmwarePaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("firmwarePaginationFirst"));
				Assert.assertTrue(
						"Expected : All the pagination buttons should be disable, Actual : one or more buttons are enabled",
						(nextFlg || lastFlg || prevFlg || firstFlg));
				break;
			}
		} catch (Exception e) {
			logger.error("Some error occured while verifying the search results : "
					+ e.getMessage());
			Assert.fail(e.getMessage());
		} finally {
			BaseUtil.explicitWait(2000);
		}
	}

	public void enterText_FirmwarePage(String firmwareElementName,
			String firmwarePageTestData) {
		try {
			switch (firmwareElementName.toUpperCase()) {
			case "PERIPHERAL FIRMWARE":
				BaseUtil.enterText(
						getProps().getProperty("peripheralFirmwareSearchField"),
						firmwarePageTestData);
				BaseUtil.waitForSpinner();
				break;
			case "ENDPOINT FIRMWARE":
				BaseUtil.enterText(
						getProps().getProperty("endpointFirmwareSearchField"),
						firmwarePageTestData);
				BaseUtil.waitForSpinner();
				break;
			default:
				logger.error("Switch Case["
						+ firmwareElementName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Failed to enter the text in search field of  ["
						+ firmwareElementName + "] tab");
			}
		} catch (Exception e) {
			logger.error("Failed to enter the value in the ["
					+ firmwareElementName
					+ "] field in peripheral firmware page. see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to enter the value in the ["
					+ firmwareElementName
					+ "] field in peripheral page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
	}

	public boolean checkPopupExistence(String sPopName, String sExpMsg) {
		try {
			switch (sPopName.toUpperCase()) {
			case "DETAIL PERIPHERAL FIRMWARE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"DetailPeripheralFirmwarePopup"));
			case "DETAIL ENDPOINT FIRMWARE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"DetailPeripheralFirmwarePopup"));
			case "ADD ENDPOINT FIRMWARE":
				if (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addEndpointFirmwarePopupAlert"))) {
					return BaseUtil.getElementText(
							getProps().getProperty(
									"addEndpointFirmwarePopupAlertMessage"))
							.equalsIgnoreCase(sExpMsg);
				} else {
					return false;
				}
			case "EDIT ENDPOINT FIRMWARE":
				if (BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"addEndpointFirmwarePopupAlert"))) {
					return BaseUtil.getElementText(
							getProps().getProperty(
									"addEndpointFirmwarePopupAlertMessage"))
							.equalsIgnoreCase(sExpMsg);
				} else {
					return false;
				}
			default:
				logger.error("Switch Case["
						+ sPopName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to check existence of popup:" + sPopName
					+ " , detail message: " + e.getMessage());
			return false;
		}
	}

	public boolean verifyErrorMessageOnAddEndpointFirmwarePopup(
			String errorMassage, String fieldName) {
		try {
			switch (fieldName.toUpperCase()) {
			case "FILE":
				return BaseUtil.getElementText(
						getProps().getProperty(
								"addEndpointFirmwareBlankFileErr"))
						.equalsIgnoreCase(errorMassage);
			case "NAME":
				return BaseUtil.getElementText(
						getProps().getProperty(
								"addEndpointFirmwareBlankNameErr"))
						.equalsIgnoreCase(errorMassage);
			case "VERSION":
				return BaseUtil.getElementText(
						getProps().getProperty(
								"addEndpointFirmwareBlankVersionErr"))
						.equalsIgnoreCase(errorMassage);
			default:
				logger.error("Switch Case["
						+ fieldName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + fieldName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public void verifyEndpointFirmwareSearchFunctionality(String args) {
		String[] arr = args.split(":");
		String keyword = arr[1];
		String totalItemsText = null, elementText = null;
		int initialTotalItemsCount = 0;
		int laterTotalItemsCount = 0;
		WebElement searchBox = null;
		try {
			switch (keyword.toUpperCase()) {
			case "INIT3CHAR":
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				BaseUtil.enterText(
						getProps().getProperty("endpointFirmwareSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"endpointFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				try {
					if (BaseUtil
							.getElementText(
									getProps().getProperty(
											"endpointFirmwareGridZeroRecord"))
							.equalsIgnoreCase(
									BaseUtil.getElementText(getProps()
											.getProperty(
													"firmwareGridZeroRecordText")))) {
						Assert.fail("No record available. Test cannot be continue.");
					}
				} catch (Exception e) {
					logger.info("Sufficient records are available");
				}

				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount > initialTotalItemsCount) {
					Assert.fail("Endpoint search results after entering 3 characters "
							+ arr[0] + " is not as expected");
				}
				break;
			case "RANDOM":
				String randomSearchString = RandomStringUtils.randomAscii(10);
				BaseUtil.enterText(
						getProps().getProperty("endpointFirmwareSearchField"),
						randomSearchString);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"endpointFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				elementText = BaseUtil.getElementText(getProps().getProperty(
						"endpointFirmwareGridZeroRecord"));
				Assert.assertFalse(
						"Multiple results found for the string : "
								+ randomSearchString,
						elementText.equalsIgnoreCase(getProps().getProperty(
								"firmwareGridZeroRecordText")));
				break;
			case "LONGTEST":
				String longTextSearchString = RandomStringUtils.randomAscii(25);
				BaseUtil.enterText(
						getProps().getProperty("endpointFirmwareSearchField"),
						longTextSearchString);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"endpointFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				elementText = BaseUtil.getElementText(getProps().getProperty(
						"endpointFirmwareGridZeroRecord"));
				Assert.assertFalse(
						"Multiple results found for the string : "
								+ longTextSearchString,
						elementText.equalsIgnoreCase(getProps().getProperty(
								"firmwareGridZeroRecordText")));
				break;
			case "BACKSPACE":
				BaseUtil.enterText(
						getProps().getProperty("endpointFirmwareSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"endpointFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				searchBox = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("endpointFirmwareSearchField"));
				searchBox.sendKeys(Keys.BACK_SPACE);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"endpointFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount < initialTotalItemsCount) {
					Assert.fail("Endpoint Firmware search results are not as expected when backspace is clicked");
				}
				break;
			case "DELETE":
				BaseUtil.enterText(
						getProps().getProperty("endpointFirmwareSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"endpointFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());

				searchBox = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("endpointFirmwareSearchField"));
				searchBox.sendKeys(Keys.CONTROL + "a");
				searchBox.sendKeys(Keys.DELETE);
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount < initialTotalItemsCount) {
					Assert.fail("Endpoint Firmware search results after deleting the keyword "
							+ arr[0] + " is not as expected");
				}
				break;
			case "PAGINATION":
				BaseUtil.enterText(
						getProps().getProperty("endpointFirmwareSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"endpointFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				boolean nextFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("firmwarePaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("firmwarePaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("firmwarePaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("firmwarePaginationFirst"));
				Assert.assertTrue(
						"Expected : All the pagination buttons should be disable, Actual : one or more buttons are enabled",
						(nextFlg || lastFlg || prevFlg || firstFlg));
				break;
			}
		} catch (Exception e) {
			logger.error("Some error occured while verifying the search results : "
					+ e.getMessage());
			Assert.fail(e.getMessage());
		} finally {
			BaseUtil.explicitWait(2000);
		}
	}

	public void enterInvalidTestData(String list) {
		try {
			String flag = null, locator = null, testData = null;
			switch (list.toUpperCase()) {
			case "ENDPOINT FIRMWARE:NAME":
				flag = "Field";
				locator = getProps()
						.getProperty("addEndpointFimrwareNameField");
				testData = getProps().getProperty(
						"editEndpointFimrwareNameFieldInvalidTestData");
				break;

			default:
				logger.error("Switch Case["
						+ list
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
			}

			if (flag.equalsIgnoreCase("Field")) {
				BaseUtil.enterText(locator, testData);
			}
		} catch (Exception e) {
			logger.error("Failed to enter text in field :" + list
					+ " see detail message : " + e.getMessage());
		}
	}

	public void modifyTextEndpointFirmware(String list) {
		try {
			Calendar calendar;
			Date releaseDate;
			DateFormat outputFormatter;
			String enteredDate = null, flag = null, locator = null, testData = null;
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, 7);
			outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
			releaseDate = calendar.getTime();
			enteredDate = outputFormatter.format(releaseDate);
			switch (list.toUpperCase()) {
			case "VERSION":
				flag = "Field";
				locator = getProps().getProperty(
						"editEndpointFirmwareVersionField");
				testData = getProps().getProperty(
						"endpointFirmwareVersionModifiedTestData");
				break;
			case "RELEASE DATE":
				flag = "Field";
				locator = getProps().getProperty(
						"addEndpointFimrwareReleaseDateField");
				testData = getProps().getProperty(
						"endpointFirmwareReleaseDateModifiedTestData");
				break;
			default:
				logger.error("Switch Case["
						+ list
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
			}
			testData = testData.replace("#index#", enteredDate);

			if (flag.equalsIgnoreCase("Field")) {
				BaseUtil.enterText(locator, testData);
			}
		} catch (Exception e) {
			logger.error("Failed to enter text in field :" + list
					+ " see detail message : " + e.getMessage());
		}
	}

	public boolean verifyLogsOfNewlyCreatedFirmware(
			String endpointFirmwareName, String accordion) {
		boolean bResult = false;
		logger.debug("Selecting the endpoint firmware :" + endpointFirmwareName);
		try {
			List<WebElement> lstAuditAccordionUpdatedvalue = BaseUtil
					.getMultipleElementsAfterLoaded(getProps()
							.getProperty(
									"editEndpointFirmwareAuditAccordionUpdatedValueColumn"));
			for (WebElement webElement : lstAuditAccordionUpdatedvalue) {
				if (webElement.getText().trim()
						.equalsIgnoreCase(endpointFirmwareName)) {
					return true;
				}
			}
			if (!bResult) {
				Assert.fail("Given test endpoint firmware's log is not available in ");
			}
			logger.debug("endpoint :" + endpointFirmwareName + " is selected.");

		} catch (Exception e) {
			logger.error("Failed to select endpoint firmware: "
					+ endpointFirmwareName
					+ " from endpoint grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the endpoint firmware : "
					+ endpointFirmwareName + ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.waitForSpinner();
		}
		return false;
	}

	public void verifyUpdateEndpointFirmwareSearchFunctionality(String args) {
		String[] arr = args.split(":");
		String keyword = arr[1];
		String totalItemsText = null;
		int initialTotalItemsCount = 0;
		int laterTotalItemsCount = 0;
		WebElement searchBox = null;
		try {
			switch (keyword.toUpperCase()) {
			case "INIT3CHAR":
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				BaseUtil.enterText(
						getProps().getProperty("updateEndpointSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"updateEndpointFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				try {
					if (BaseUtil
							.getElementText(
									getProps()
											.getProperty(
													"updateEndpointFirmwareGridZeroRecord"))
							.equalsIgnoreCase(
									BaseUtil.getElementText(getProps()
											.getProperty(
													"firmwareGridZeroRecordText")))) {
						Assert.fail("No record available. Test cannot be continue.");
					}
				} catch (Exception e) {
					logger.info("Sufficient records are available");
				}

				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount > initialTotalItemsCount) {
					Assert.fail("Endpoint search results after entering 3 characters "
							+ arr[0] + " is not as expected");
				}
				break;
			case "RANDOM":
				String randomSearchString = RandomStringUtils.randomAscii(10);
				BaseUtil.enterText(
						getProps().getProperty("updateEndpointSearchField"),
						randomSearchString);
				// BaseUtil.waitForSpinner(1000);
				BaseUtil.click(getProps().getProperty(
						"updateEndpointFirmwareSearchButton"));
				// BaseUtil.waitForSpinner(1000);
				String elementText = BaseUtil.getElementText(getProps()
						.getProperty("updateEndpointFirmwareGridZeroRecord"));
				Assert.assertFalse(
						"Multiple results found for the string : "
								+ randomSearchString,
						elementText.equalsIgnoreCase(getProps().getProperty(
								"firmwareGridZeroRecordText")));
				break;
			case "BACKSPACE":
				BaseUtil.enterText(
						getProps().getProperty("updateEndpointSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"updateEndpointFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				searchBox = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("updateEndpointSearchField"));
				searchBox.sendKeys(Keys.BACK_SPACE);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"updateEndpointFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount < initialTotalItemsCount) {
					Assert.fail("Endpoint Firmware search results are not as expected when backspace is clicked");
				}
				break;
			case "DELETE":
				BaseUtil.enterText(
						getProps().getProperty("updateEndpointSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"updateEndpointFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());

				searchBox = BaseUtil.getElementAfterLoaded(getProps()
						.getProperty("updateEndpointFirmwareSearchButton"));
				searchBox.sendKeys(Keys.CONTROL + "a");
				searchBox.sendKeys(Keys.DELETE);
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("firmwareTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				if (laterTotalItemsCount < initialTotalItemsCount) {
					Assert.fail("Endpoint Firmware search results after deleting the keyword "
							+ arr[0] + " is not as expected");
				}
				break;
			case "PAGINATION":
				BaseUtil.enterText(
						getProps().getProperty("updateEndpointSearchField"),
						arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"updateEndpointFirmwareSearchButton"));
				BaseUtil.waitForSpinner();
				boolean nextFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("firmwarePaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("firmwarePaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("firmwarePaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("firmwarePaginationFirst"));
				Assert.assertTrue(
						"Expected : All the pagination buttons should be disable, Actual : one or more buttons are enabled",
						(nextFlg || lastFlg || prevFlg || firstFlg));
				break;
			}
		} catch (Exception e) {
			logger.error("Some error occured while verifying the search results : "
					+ e.getMessage());
			Assert.fail(e.getMessage());
		} finally {
			BaseUtil.explicitWait(2000);
		}
	}

}
