package com.gridpoint.adminui.automation.datafix;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.gridpoint.automation.constants.PathConstants;
import com.gridpoint.automation.database.GPConnection;
import com.gridpoint.automation.database.GPDataBaseUtil;
import com.gridpoint.automation.tests.base.CommonInit;
import com.gridpoint.automation.tests.base.TestBase;
import com.gridpoint.util.BaseUtil;

public class DatafixPage extends CommonInit {
	String globalBeforeSave1, globalBeforeSave2;

	private Logger logger = Logger.getLogger(DatafixPage.class);

	public boolean isElementDisplayed(String elementName) {
		try {
			switch (elementName.toUpperCase()) {
			case "DATAFIX UUID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixUuid"));
			case "DATAFIX:NOTES":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixNotes"));
			case "BACKUP:NOTES":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"backupNotes"));
			case "USERNAME":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixUsername"));
			case "ENDUSERID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixEnduserId"));
			case "BACKUP/RESTORE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackupOrRestoreButton"));
			case "CREATE DATAFIX":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixCreateDatafixButton"));
			case "SEARCH":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixSearchButton"));
			case "DATAFIX:APPLY":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixApplyDateButton"));
			case "BACKUP:APPLY":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackUpApplyDateButton"));
			case "APPLYBACKUP":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackUpApplyDateButton"));
			case "DATAFIX:CANCEL":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixCancelDateButton"));
			case "BACKUP:CANCEL":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackUpCancelDateButton"));
			case "CANCELBACKUP":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackUpCancelDateButton"));
			case "BACKUPDATETIME":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackupDateTime"));
			case "BACKUP NOTES":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackupNotes"));
			case "STARTUTC":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackupStartUtc"));
			case "ENDUTC":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackupEndUtc"));
			case "BACKUPCHANNELDISPLAYNAME":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackupChannelDisplayName"));
			case "ACTION":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackupAction"));
			case "CREATE BACKUP":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixCreateBackupButton"));
			case "DATAFIX TASKS:TASK":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTasksModelTask"));
			case "DATAFIX TASKS:STARTUTC":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTasksModelStartUtc"));
			case "DATAFIX TASKS:ENDUTC":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTasksModelEndUtc"));
			case "DATAFIX TASKS:CHANNELID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTasksModelChannelID"));
			case "DATAFIX TASKS:SOURCE CHANNELID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTasksModelSourceChannel"));
			case "DATAFIX TASKS:DESTINATION CHANNELID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTasksModelDestinationChannel"));
			case "DATAFIX TASKS:CHANNELID1":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTasksChannelID1"));
			case "DATAFIX TASKS:CHANNELID2":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTasksChannelID2"));
			case "DATAFIX TASKS:DIVISOR":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTasksModelDivisor"));
			case "DATAFIX TASKS:MULTIPLICAND":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTasksModelMultiplicand"));
			case "DATAFIX:TASK":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTask"));
			case "DATEFIX:STARTUTC":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"dataFixTaskStartUtc"));
			case "DATEFIX:ENDUTC":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"dataFixTaskEndUtc"));
			case "DATEFIX:CHANNEL ID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"dataFixTaskChannelId"));

			default:
				logger.error("Switch Case["
						+ elementName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + elementName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public void verifyAccordionToggle_DatafixPage(String sAccordionName) {

		try {
			switch (sAccordionName.toUpperCase()) {
			case "BACKUP/RESTORE":
				if (BaseUtil.getElementAfterLoaded(
						getProps().getProperty("datafixUuid")).isDisplayed()) {
					Assert.fail("Failed to toggle the accordion: "
							+ sAccordionName);
				}
				break;

			case "DATAFIX":
				try {
					if (BaseUtil.getElementAfterLoaded(
							getProps().getProperty("datafixUuid"))
							.isDisplayed()) {
						Assert.fail("Failed to toggle the accordion: "
								+ sAccordionName);
					}
				} catch (Exception e) {
					logger.info("Accordion[" + sAccordionName + "] is toggled.");
				}
				break;

			default:
				logger.error("Switch Case["
						+ sAccordionName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Failed to check existence of popup ["
						+ sAccordionName
						+ "] because you passed wrong parameter["
						+ sAccordionName
						+ "] either in feature file or test class. \n please choose valid parameter from the following list: \n ["
						+ sAccordionName + "]");
			}
		} catch (Exception e) {
			logger.error("Failed to check existence of popup:" + sAccordionName
					+ "in datafix page. , see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to check existence of popup: ["
					+ sAccordionName
					+ "] in datafix page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
	}

	public void verifySearchFunctionalityForDataFix(String arg) {
		String[] arr = arg.split(":");
		String keyword = arr[1];
		List<WebElement> listDataFix = null;
		String totalItemsText = null;
		int initialTotalItemsCount = 0;
		int laterTotalItemsCount = 0;
		boolean bResult = false;
		try {
			switch (keyword.toUpperCase()) {
			case "BOT MULTIPLY DATAFIX CHANNEL":
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("dataFixTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				enterTextAndWait(arr[0]);
				try {
					if (BaseUtil
							.getElementText(
									getProps().getProperty(
											"datafixGridZeroRecord"))
							.equalsIgnoreCase(
									BaseUtil.getElementText(getProps()
											.getProperty(
													"datafixGridZeroRecordText")))) {
						Assert.fail("No record available. Test cannot be continue.");
					}
				} catch (Exception e) {
					logger.info("Sufficient records are available");
				}
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("datafixBackupTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				Assert.assertTrue("Search results after entering 3 characters "
						+ arr[0] + " is not as expected",
						laterTotalItemsCount <= initialTotalItemsCount);
				break;

			case "INIT3CHAR":
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("dataFixTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				enterTextAndWait(arr[0]);
				try {
					if (BaseUtil
							.getElementText(
									getProps().getProperty(
											"datafixGridZeroRecord"))
							.equalsIgnoreCase(
									BaseUtil.getElementText(getProps()
											.getProperty(
													"datafixGridZeroRecordText")))) {
						Assert.fail("No record available. Test cannot be continue.");
					}
				} catch (Exception e) {
					logger.info("Sufficient records are available");
				}
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("datafixBackupTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				Assert.assertTrue("Search results after entering 3 characters "
						+ arr[0] + " is not as expected",
						laterTotalItemsCount <= initialTotalItemsCount);
				break;
			case "RANDOM":
				String randomSearchString = RandomStringUtils.randomAscii(10);
				enterTextAndWait(randomSearchString);
				String elementText = BaseUtil.getElementText(getProps()
						.getProperty("datafixGridZeroRecord"));
				Assert.assertTrue(
						"Multiple results found for the string : "
								+ randomSearchString,
						elementText.equals(getProps().getProperty(
								"datafixGridZeroRecordText")));
				break;
			case "BACKSPACE":
				enterTextAndWait(arr[0]);
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("dataFixTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());

				WebElement searchBox = BaseUtil
						.getElementAfterLoaded(getProps().getProperty(
								"datafixSearchField"));
				searchBox.sendKeys(Keys.BACK_SPACE);
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("dataFixTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				Assert.assertTrue(
						"Search results are not as expected when backspace is clicked",
						laterTotalItemsCount > initialTotalItemsCount);
				break;
			case "DELETE":
				BaseUtil.enterText(
						getProps().getProperty("datafixSearchField"), arr[0]);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty("datafixSearchButton"));
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("dataFixTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				BaseUtil.enterText(
						getProps().getProperty("datafixSearchField"), "");
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("dataFixTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				Assert.assertTrue("Search results after deleting the keyword "
						+ arr[0] + " is not as expected",
						laterTotalItemsCount > initialTotalItemsCount);
				break;
			case "PAGINATION":
				enterTextAndWait(arr[0]);
				boolean nextFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("datafixPaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("datafixPaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("datafixPaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("datafixPaginationFirst"));
				Assert.assertTrue(
						"Expected : All the pagination buttons should be disable, Actual : one or more buttons are enabled",
						(nextFlg || lastFlg || prevFlg || firstFlg));
				break;

			case "SEARCH":
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("dataFixTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				BaseUtil.enterText(
						getProps().getProperty("datafixSearchField"), arr[0]);
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("dataFixTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				Assert.assertTrue("Search results after entering 3 characters "
						+ arr[0] + " is not as expected",
						laterTotalItemsCount < initialTotalItemsCount);
				break;

			case "BACKUPSEARCH":
				try {
					listDataFix = BaseUtil
							.getMultipleElementsAfterLoaded(getProps()
									.getProperty("datafixBackupNotesColumn"));
					for (WebElement webElement : listDataFix) {
						if (webElement.getText().trim()
								.equalsIgnoreCase(arr[0])) {
							bResult = true;
						}
					}
					if (!bResult) {
						Assert.fail("Given test data fix is not available in ");
					}
				} catch (Exception e) {
					logger.info("Sufficient records are not available");
				}
				break;
			}
		} catch (Exception e) {
			logger.error("Some error occured while verifying the search results : "
					+ e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	private void enterTextAndWait(String text) {
		BaseUtil.enterText(getProps().getProperty("datafixSearchField"), text);
		BaseUtil.explicitWait(2000);
		BaseUtil.waitForSpinner();
		BaseUtil.click(getProps().getProperty("datafixSearchButton"));
		BaseUtil.explicitWait(2000);
		BaseUtil.waitForSpinner();
	}

	public void clickPagination(String sLinkName, String accordionName) {
		try {
			switch (sLinkName.toUpperCase()) {
			case "NEXT":
				Assert.assertTrue(
						"Required data is not present. Next button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"datafixPaginationNext")));
				BaseUtil.click(getProps().getProperty("datafixPaginationNext"));
				break;
			case "PREV":
				Assert.assertTrue(
						"Required data is not present. Prev button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"datafixPaginationPrev")));
				BaseUtil.click(getProps().getProperty("datafixPaginationPrev"));
				break;
			case "LAST":
				Assert.assertTrue(
						"Required data is not present. Last button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"datafixPaginationLast")));
				BaseUtil.click(getProps().getProperty("datafixPaginationLast"));
				break;
			case "FIRST":
				Assert.assertTrue(
						"Required data is not present. First button is not enabled",
						BaseUtil.verifyElementEnabled(getProps().getProperty(
								"datafixPaginationFirst")));
				BaseUtil.click(getProps().getProperty("datafixPaginationFirst"));
				break;
			default:
				logger.error("Switch Case["
						+ sLinkName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid button clicked");
			}
		} catch (Exception e) {
			logger.error("Failed to click on link :" + sLinkName
					+ " , detail message: " + e.getMessage());
			Assert.fail(e.getMessage());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public void verifyDatafixDetailGridPagination(String task,
			String accordionName) {
		String totalPages = null;
		String currentPage = null;
		try {
			switch (task.toUpperCase()) {
			case "NEXT":
				String pageNumber = BaseUtil.getElementAttribute(getProps()
						.getProperty("datafixPaginationCurrentPage"), "value");
				Assert.assertTrue(
						"Next page is not displayed",
						(null != pageNumber && Integer.parseInt(pageNumber) > 1));
				break;
			case "PREVIOUS":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"datafixPaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("datafixPaginationCurrentPage"), "value");
				Assert.assertTrue("Previous page is not displayed",
						(null != totalPages && null != currentPage && Integer
								.parseInt(currentPage) < Integer
								.parseInt(totalPages)));
				break;
			case "LAST":
				totalPages = BaseUtil.getElementText(getProps().getProperty(
						"datafixPaginationTotalPages"));
				totalPages = totalPages.substring(totalPages.indexOf("/") + 1)
						.trim();
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("datafixPaginationCurrentPage"), "value");
				Assert.assertTrue(
						"Last page is not displayed",
						(Integer.parseInt(totalPages) == Integer
								.parseInt(currentPage)
								&& BaseUtil.verifyElementEnabled(getProps()
										.getProperty("datafixPaginationFirst")) && BaseUtil
								.verifyElementEnabled(getProps().getProperty(
										"datafixPaginationPrev"))));
				break;
			case "FIRST":
				currentPage = BaseUtil.getElementAttribute(getProps()
						.getProperty("datafixPaginationCurrentPage"), "value");
				Assert.assertTrue(
						"First page is not displayed",
						(Integer.parseInt(currentPage) == 1)
								&& !BaseUtil.verifyElementEnabled(getProps()
										.getProperty("datafixPaginationFirst"))
								&& !BaseUtil.verifyElementEnabled(getProps()
										.getProperty("datafixPaginationPrev")));
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
					getProps().getProperty("datafixPaginationCurrentPage"),
					"value"));
			if (iCurrentPage >= iPageNum) {
				iDiff = iCurrentPage - iPageNum;
				sArrow = "DOWN";
			} else {
				iDiff = iPageNum - iCurrentPage;
				sArrow = "UP";
			}
			WebElement element = BaseUtil.getElementAfterLoaded(getProps()
					.getProperty("datafixPaginationCurrentPage"));
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

	public boolean validateDatePicker(String taskName,
			List<Map<String, String>> dates) {
		try {
			if (taskName.equalsIgnoreCase("datafix")) {
				BaseUtil.enterText(
						getProps().getProperty("datafixStartDateText"), dates
								.get(0).get("startDate"));
				BaseUtil.explicitWait(3000);
				// BaseUtil.enterText(getProps().getProperty.get("datafixEndDateText"),
				// dates.get(0).get("endDate"));
				// BaseUtil.explicitWait(3000);
				return true;
			} else {
				BaseUtil.enterText(
						getProps().getProperty("datafixBackupStartDateText"),
						dates.get(0).get("startDate"));
				BaseUtil.explicitWait(3000);
				// BaseUtil.enterText(getProps().getProperty("datafixBackupEndDateText"),
				// dates.get(0).get("endDate"));
				// BaseUtil.explicitWait(3000);
				return true;
			}
		} catch (Exception e) {
			logger.error("Some error occured while setting the date["
					+ dates.get(0) + "] in datepicker : " + e.getMessage());
			return false;
		}
	}

	public boolean validateTimepicker(String taskName,
			List<Map<String, String>> dates) {
		try {
			if (taskName.equalsIgnoreCase("datafix")) {
				BaseUtil.click(getProps().getProperty("datafixDatePicker"));
				BaseUtil.selectDropDownByIndex(
						getProps().getProperty("datafixEndTimeHoursSelectBox"),
						23);
				return true;
			} else {
				BaseUtil.enterText(
						getProps().getProperty("datafixBackupStartDateText"),
						dates.get(0).get("startDate"));
				BaseUtil.explicitWait(3000);
				BaseUtil.enterText(
						getProps().getProperty("datafixBackupEndDateText"),
						dates.get(0).get("endDate"));
				BaseUtil.explicitWait(3000);
				return true;
			}
		} catch (Exception e) {
			logger.error("Some error occured while setting the date["
					+ dates.get(0) + "] in datepicker : " + e.getMessage());
			return false;
		}
	}

	public boolean validateDisplayingRecords(String taskName) {
		Integer totalPages = null;
		String totalItemsText = null;
		if (taskName.equalsIgnoreCase("datafix")) {
			try {
				if (BaseUtil.getElementText(getProps().getProperty(
						"dataFixTotalItemsCount")) == null) {

					return true;
				} else {
					totalItemsText = BaseUtil.getElementText(getProps()
							.getProperty("dataFixTotalItemsCount"));
					totalPages = Integer.parseInt(totalItemsText.substring(
							totalItemsText.indexOf("of") + 2,
							totalItemsText.indexOf("items")).trim());
					return totalPages > 0 ? true : false;
				}
			} catch (Exception e) {
				logger.info("Sufficient records are available");
				return false;
			}

		} else if (taskName.equalsIgnoreCase("backup/restore"))

		{
			try {
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("datafixBackupTotalItemsCount"));
				totalPages = Integer.parseInt(totalItemsText.substring(
						totalItemsText.indexOf("of") + 2,
						totalItemsText.indexOf("items")).trim());

				return totalPages > 0 ? true : false;

			} catch (Exception e) {
				logger.info("Sufficient records are available");
				return false;
			}

		} else if (taskName.equalsIgnoreCase("Available Records")) {
			try {
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("dataFixTotalItemsCount"));
				totalPages = Integer.parseInt(totalItemsText.substring(
						totalItemsText.indexOf("of") + 2,
						totalItemsText.indexOf("items")).trim());
				return totalPages > 0 ? true : false;

			} catch (Exception e) {
				logger.info("Sufficient records are available");
				return false;
			}
		} else if (taskName.equalsIgnoreCase("pagination")) {
			try {
				if (BaseUtil
						.getElementText(
								getProps().getProperty("datafixGridZeroRecord"))
						.equalsIgnoreCase(
								(getProps()
										.getProperty("datafixGridZeroRecordText")))) {
					return true;
				} else {
					totalItemsText = BaseUtil.getElementText(getProps()
							.getProperty("dataFixTotalItemsCount"));
					totalPages = Integer.parseInt(totalItemsText.substring(
							totalItemsText.indexOf("of") + 2,
							totalItemsText.indexOf("items")).trim());
					return totalPages > 0 ? true : false;
				}
			} catch (Exception e) {
				logger.info("Sufficient records are available");
				return false;
			}
		}
		return false;
	}

	public boolean validateCreateDatafix() {
		return BaseUtil.verifyElementDisplayed(getProps().getProperty(
				"datafixPopupLabel"));
	}

	public boolean validateSuccessMessageOfCreateDatafix() {
		return BaseUtil.verifyElementDisplayed(getProps().getProperty(
				"datafixPopupSuccessLabel"));
	}

	public boolean validatePreviewDataFix() {
		return BaseUtil.verifyElementDisplayed(getProps().getProperty(
				"datafixPopupLabel"));
	}

	public boolean selectValueFromDropDown(String taskType) {
		try {
			BaseUtil.clickAndWait(getProps().getProperty("datafixTaskType"));
			switch (taskType.toUpperCase()) {
			case "COPY_CHANNEL_DATA":
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixTaskType"), taskType);
				return true;
			case "DELETE_CHANNEL_DATA":
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixTaskType"), taskType);
				return true;
			case "DELETE_NEGATIVE_CHANNEL_DATA":
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixTaskType"), taskType);
				return true;
			case "DIVIDE_CHANNEL_DATA":
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixTaskType"), taskType);
				return true;
			case "SWAP_CHANNEL_DATA":
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixTaskType"), taskType);
				return true;
			case "MULTIPLY_CHANNEL_DATA":
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixTaskType"), taskType);
				return true;
			default:
				logger.error("Switch Case["
						+ taskType
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to select on tasktype :" + taskType
					+ " , detail message: " + e.getMessage());
			return false;
		}
	}

	public boolean verifyDatafixElementsDisplaying(String datafixElementName) {
		try {
			switch (datafixElementName.toUpperCase()) {
			case "DATETIME STAMP":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixDateTimeStamp"));
			case "NOTES":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixCreateDatafixNotes"));
			case "SOURCE CHANNELID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixSourceChannelId"));
			case "DESTINATION CHANNELID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixDestinationChannelId"));
			case "CHANNEL ID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackupChannelIdField"));
			case "SELECT CHANNEL":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackupSelectChannel"));
			case "SAVE":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"datafixSaveButton"));
			case "CANCEL":
				return BaseUtil.verifyElementEnabled(getProps().getProperty(
						"datafixCancelButton"));
			case "TASK:SEARCH":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTaskSearchButton"));
			case "TASK:RESTORE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTaskRestoreButton"));
			case "TASK:CLOSE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTaskCloseButton"));
			case "TASK:X":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTaskCutButton"));
			default:
				logger.error("Switch Case["
						+ datafixElementName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to select on tasktype :" + datafixElementName
					+ " , detail message: " + e.getMessage());
			return false;
		}
	}

	public void selectDatafixFromGrid_SitePage(String sSiteName) {
		int iTotalNumOfPage = 0;
		boolean bResult = false;
		logger.debug("Selecting the dataFixTask :" + sSiteName);
		try {
			iTotalNumOfPage = Integer.parseInt(BaseUtil
					.getElementText(
							getProps().getProperty(
									"datafixPaginationTotalPages")).trim()
					.replace("/", "").trim());
			if (iTotalNumOfPage == 1)
				iTotalNumOfPage++;
			for (int iNumPage = 1; iNumPage <= iTotalNumOfPage - 1; iNumPage++) {
				List<WebElement> lstSiteNames = BaseUtil
						.getMultipleElementsAfterLoaded(getProps().getProperty(
								"datafixGridNameColumn"));
				for (WebElement webElement : lstSiteNames) {
					if (webElement.getText().trim().equalsIgnoreCase(sSiteName)) {
						webElement.click();
						BaseUtil.waitForSpinner();
						bResult = true;
						break;
					}
				}
				if (!bResult) {
					Assert.fail("Given test dataFix is not available");
				}
			}
			logger.debug("DataFix :" + sSiteName + " is selected.");
		} catch (Exception e) {
			logger.error("Failed to select DataFix: " + sSiteName
					+ " from DataFix grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the DataFix : " + sSiteName
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public boolean isElementDisplayedOnSiteDetailsAccordion(String elementName) {
		try {
			switch (elementName.toUpperCase()) {
			case "SITE ID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixSiteId"));
			case "SITE NAME":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixSiteName"));
			case "ADDRESS":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixSiteAddress"));
			case "POSTAL CODE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixSitePostalCode"));
			case "TIME ZONE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixSiteTimeZone"));
			case "TANENT":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixSiteTenent"));
			case "ACTION":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixSiteAction"));
			default:
				logger.error("Switch Case["
						+ elementName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + elementName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean isElementDisplayedOnChannelAccordion(String elementName) {
		try {
			switch (elementName.toUpperCase()) {
			case "CHANNEL ID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixChanneld"));
			case "CHANNEL NAME":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixChanneName"));
			case "DISPLAY NAME":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixChanneDisplayName"));
			case "DEVICE NAME":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixChanneDeviceName"));
			case "MEASURE TYPE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixChanneMeasureType"));
			case "UNIT":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixChanneUnit"));
			case "METADATA CATEGORY":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixChanneMetadataCategory"));
			case "DISABLED FROM GRAPHS":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixChanneDisabledFromGraph"));
			case "ACTION":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixChanneAction"));
			case "TOTAL TYPE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixChanneTotalType"));
			case "PREVIEW MODEL LABLE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"PreviewDataFixLabel"));
			case "PREVIEW NOTES":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixPriviewPopupNote"));
			case "PREVIEW TICKET NUMBER":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixPriviewPopupticketNumber"));
			case "PREVIEW TASK":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixPriviewPopupTaskColumn"));
			case "PREVIEW STARTUTC":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixPriviewPopupStartUTCColumn"));
			case "PREVIEW ENDUTC":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixPriviewPopupEndUTCColumn"));
			case "PREVIEW CHANNELID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixPriviewPopupChannelIDColumn"));
			case "PREVIEW SAVE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"previewDatafixSaveButton"));
			case "PREVIEW CANCEL":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"previewDatafixCancelButton"));
			default:
				logger.error("Switch Case["
						+ elementName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + elementName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public void verifySearchFunctionalityForBackupOrRestore(String arg) {
		String[] arr = arg.split(":");
		String keyword = arr[1];
		String totalItemsText = null;
		int initialTotalItemsCount = 0;
		int laterTotalItemsCount = 0;
		try {
			switch (keyword.toUpperCase()) {
			case "INIT3CHAR":
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("datafixBackupTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				BaseUtil.enterText(
						getProps().getProperty("datafixBackupSearchField"),
						arr[0]);
				BaseUtil.explicitWait(2000);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"datafixBackupSearchButoon"));
				BaseUtil.explicitWait(2000);
				BaseUtil.waitForSpinner();
				try {
					if (BaseUtil
							.getElementText(
									getProps().getProperty(
											"datafixBackupGridZeroRecord"))
							.equalsIgnoreCase(
									BaseUtil.getElementText(getProps()
											.getProperty(
													"datafixGridZeroRecordText")))) {
						Assert.fail("No record available. Test cannot be continue.");
					}
				} catch (Exception e) {
					logger.info("Sufficient records are available");
				}
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("datafixBackupTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				Assert.assertTrue("Search results after entering 3 characters "
						+ arr[0] + " is not as expected",
						laterTotalItemsCount <= initialTotalItemsCount);
				break;
			case "RANDOM":
				String randomSearchString = RandomStringUtils.randomAscii(10);
				BaseUtil.enterText(
						getProps().getProperty("datafixBackupSearchField"),
						arr[0]);
				BaseUtil.explicitWait(2000);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"datafixBackupSearchButoon"));
				BaseUtil.explicitWait(2000);
				BaseUtil.waitForSpinner();
				String elementText = BaseUtil.getElementText(getProps()
						.getProperty("datafixBackupGridZeroRecord"));
				Assert.assertTrue(
						"Multiple results found for the string : "
								+ randomSearchString,
						elementText.equals(getProps().getProperty(
								"datafixGridZeroRecordText")));
				break;
			case "BACKSPACE":
				BaseUtil.enterText(
						getProps().getProperty("datafixBackupSearchField"),
						arr[0]);
				BaseUtil.explicitWait(2000);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"datafixBackupSearchButoon"));
				BaseUtil.explicitWait(2000);
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("datafixBackupTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());

				WebElement searchBox = BaseUtil
						.getElementAfterLoaded(getProps().getProperty(
								"datafixBackupSearchField"));
				searchBox.sendKeys(Keys.BACK_SPACE);
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("datafixBackupTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				Assert.assertTrue(
						"Search results are not as expected when backspace is clicked",
						laterTotalItemsCount > initialTotalItemsCount);
				break;
			case "DELETE":
				BaseUtil.enterText(
						getProps().getProperty("datafixBackupSearchField"),
						arr[0]);
				BaseUtil.explicitWait(2000);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"datafixBackupSearchButoon"));
				BaseUtil.explicitWait(2000);
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("datafixBackupTotalItemsCount"));
				initialTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				BaseUtil.enterText(
						getProps().getProperty("datafixBackupSearchField"), "");
				BaseUtil.waitForSpinner();
				totalItemsText = BaseUtil.getElementText(getProps()
						.getProperty("datafixBackupTotalItemsCount"));
				laterTotalItemsCount = Integer.parseInt(totalItemsText
						.substring(totalItemsText.indexOf("of") + 2,
								totalItemsText.indexOf("items")).trim());
				Assert.assertTrue("Search results after deleting the keyword "
						+ arr[0] + " is not as expected",
						laterTotalItemsCount >= initialTotalItemsCount);
				break;
			case "PAGINATION":
				BaseUtil.enterText(
						getProps().getProperty("datafixBackupSearchField"),
						arr[0]);
				BaseUtil.explicitWait(2000);
				BaseUtil.waitForSpinner();
				BaseUtil.click(getProps().getProperty(
						"datafixBackupSearchButoon"));
				BaseUtil.explicitWait(2000);
				BaseUtil.waitForSpinner();
				boolean nextFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("dataFixBackupPaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("dataFixBackupPaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("dataFixBackupPaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("dataFixBackupPaginationFirst"));
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

	public void createDatafix() {

		BaseUtil.selectDropDownByValue(getProps()
				.getProperty("datafixTaskType"), "COPY_CHANNEL_DATA");
		BaseUtil.enterText(getProps().getProperty("datafixTaskType"),
				"This is my first Datafix ");
		BaseUtil.clickAndWait(getProps().getProperty("datafixSiteDetailsPopup"));
		BaseUtil.clickAndWait(getProps().getProperty("datafixSiteChannel1"));
		BaseUtil.clickAndWait(getProps().getProperty("datafixCreateChannel"));
		BaseUtil.clickAndWait(getProps().getProperty("datafixChannel1"));
	}

	public boolean verifyChannelIdText() {
		if (null != BaseUtil.getElementText(getProps().getProperty(
				"datafixChannelIdText")))
			return true;

		return false;

	}

	public boolean isElementDisplayedOnBackupSiteDetailsAccordion(
			String elementName) {
		try {
			switch (elementName.toUpperCase()) {
			case "SITE ID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackUpSiteId"));
			case "SITE NAME":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackUpSiteName"));
			case "ADDRESS":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackUpSiteAddress"));
			case "POSTAL CODE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackUpSitePostalCode"));
			case "TIMEZONE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackUpSiteTimeZone"));
			case "TENENT":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackUpSiteTenent"));
			case "ACTION":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixBackUpSiteAction"));
			default:
				logger.error("Switch Case["
						+ elementName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + elementName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public void verifySorting_DatafixPage(String sortingType, String colName) {
		List<String> listColumnValues = null;
		try {
			switch (colName.toUpperCase()) {
			case "DATAFIX UUID":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty("datafixUuid"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty("datafixUuid"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("datafixUuid"));
				break;
			case "DATAFIX:NOTES":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty("datafixNotes"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty("datafixNotes"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("datafixNotes"));
				break;
			case "BACKUP:NOTES":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty("backupNotes"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty("backupNotes"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("backupNotes"));
				break;
			case "USERNAME":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty("datafixUsername"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty("datafixUsername"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("datafixUsername"));
				break;
			case "ENDUSERID":
				if (sortingType.equals("Descending")) {
					BaseUtil.click(getProps().getProperty("datafixEnduserId"));
					BaseUtil.waitForSpinner();
				} else if (sortingType.equals("Ascending")) {
					BaseUtil.click(getProps().getProperty("datafixEnduserId"));
					BaseUtil.waitForSpinner();
				}
				listColumnValues = BaseUtil
						.getMultipleElementTextAfterLoaded(getProps()
								.getProperty("datafixEnduserId"));
				break;

			default:
				logger.error("Switch Case["
						+ colName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Invalid column name: "
						+ colName
						+ ", Please pass valid column name e.g.[Property,Action] from feature file or test class.");
			}

			Assert.assertTrue(colName + " is not in " + sortingType + " order",
					BaseUtil.isSorted(sortingType, listColumnValues));

		} catch (Exception e) {
			logger.error("Failed to sort the column :" + colName
					+ " Sorting order:" + sortingType + " , detail message: "
					+ e.getStackTrace().toString());
			Assert.fail(e.getStackTrace().toString());
		}
	}

	public boolean verifyDatafixTab() {
		try {
			BaseUtil.verifyElementDisplayed(getProps().getProperty(
					"datafixDetailGrid"));
			return false;
		} catch (Exception ese) {
			return true;
		}
	}

	public void isDatafixTabAvailable(String userName) {
		BaseUtil.enterText(getProps().getProperty("adminUserName_Locator"),
				userName);
		BaseUtil.enterText(getProps().getProperty("adminUserPassword_Locator"),
				PathConstants.DEFAULT_USER_PASSWORD);
		BaseUtil.click(getProps().getProperty("adminLoginbutton_Locator"));
		BaseUtil.waitForSpinner();

		if (BaseUtil.isElementClickable(getProps().getProperty("datafixTab"))) {
			Assert.fail("Expected : Datafix Tab should not be Available. Actual : Datafix tab is available for user ["
					+ userName + "]");
		}
	}

	public boolean isBlank(String fieldName) {
		boolean isBlank = false;
		switch (fieldName.toUpperCase()) {
		case "NOTES":
			isBlank = getProps().getProperty(
					"datafixNotesBlankErrorMessageText").equals(
					BaseUtil.getElementText(getProps().getProperty(
							"datafixNotesError")));
			break;
		case "SOURCE CHANNELID":
			isBlank = getProps().getProperty(
					"datafixSourceChannelBlankErrorMessageText").equals(
					BaseUtil.getElementText(getProps().getProperty(
							"datafixSourceChanneError")));
			break;
		case "DESTINATION CHANNELID":
			isBlank = getProps().getProperty(
					"datafixDestinationChannelBlankErrorMessageText").equals(
					BaseUtil.getElementText(getProps().getProperty(
							"datefixDestinationChannelError")));
			break;

		case "CHANNELID":
			isBlank = getProps().getProperty(
					"datafixChannelIdBlankErrorMessageText").equals(
					BaseUtil.getElementText(getProps().getProperty(
							"datafixCreateChannelIDError")));
			break;
		case "DIVISOR":
			isBlank = getProps().getProperty(
					"datafixDivisorBlankErrorMessageText").equals(
					BaseUtil.getElementText(getProps().getProperty(
							"datafixDivisorError")));
			break;

		case "MULTIPLICAND":
			isBlank = getProps().getProperty(
					"datafixMultiplicandBlankErrorMessageText").equals(
					BaseUtil.getElementText(getProps().getProperty(
							"datafixMultiplicandError")));
			break;

		case "CHANNELID1":
			isBlank = getProps().getProperty(
					"datafixChanneld1BlankErrorMessageText").equals(
					BaseUtil.getElementText(getProps().getProperty(
							"datafixChannelId1Error")));
			break;

		case "CHANNELID2":
			isBlank = getProps().getProperty(
					"datafixChanneld2BlankErrorMessageText").equals(
					BaseUtil.getElementText(getProps().getProperty(
							"datafixChannelId2Error")));
			break;
		}

		return isBlank;
	}

	public void verifyCopyChannelData(String arg) {

		switch (arg.toUpperCase()) {
		case "COPY_CHANNEL_DATA":
			BaseUtil.enterText(getProps().getProperty("datafixSearchField"),
					arg);
			BaseUtil.waitForSpinner();
			BaseUtil.click(getProps().getProperty("datafixSearchButton"));
			BaseUtil.waitForSpinner();
			String elementText = BaseUtil.getElementText(getProps()
					.getProperty("datafixTotalItemsCount"));
			Assert.assertTrue(
					"Multiple results found for the string : " + arg,
					elementText.equals(getProps().getProperty(
							"datafixGridZeroRecordText")));
			break;

		}
	}

	public boolean clickWithoutIndex(String objectLocator) {
		try {
			BaseUtil.click(objectLocator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickWithIndex(String objectLocator, int index) {
		try {
			BaseUtil.click(objectLocator, index);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void selectSiteFromGrid_DadaFixPage(String sSiteName) {
		int count = 0;
		boolean bResult = false;
		logger.debug("Selecting the site :" + sSiteName);
		try {
			List<WebElement> lstSiteNames = BaseUtil
					.getMultipleElementsAfterLoaded(getProps().getProperty(
							"siteGridNameColumnNew"));
			for (WebElement webElement : lstSiteNames) {
				count++;
				if (webElement.getText().trim().equalsIgnoreCase(sSiteName)) {
					BaseUtil.click(getProps().getProperty(
							"DatafixChannelButton").replace("##index##",
							String.valueOf(count)));
					bResult = true;
					break;
				}
			}
			if (!bResult) {
				Assert.fail("Given test site is not available");
			}
			logger.debug("site :" + sSiteName + " is selected.");
		} catch (Exception e) {
			logger.error("Failed to select site: " + sSiteName
					+ " from site grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the site : " + sSiteName
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public void verifySearchFunctionalityOfSiteDetails(String searchText,
			String taskName) {

		List<WebElement> listDataFix = new ArrayList<>();

		if (taskName.equals("searchSite")) {
			BaseUtil.enterText(
					getProps().getProperty("datafixSiteSearchField"),
					searchText);
			listDataFix = BaseUtil.getMultipleElementsAfterLoaded(getProps()
					.getProperty("siteGridNameColumn"));
			BaseUtil.waitForSpinner();
			BaseUtil.click(getProps().getProperty(
					"datafixCreateBackupSiteSearchButton"));
			BaseUtil.waitForSpinner();
			BaseUtil.explicitWait(2000);
		} else if (taskName.equals("searchChannel")) {
			BaseUtil.enterText(
					getProps().getProperty("datafixChannelSearchField"),
					searchText);
			BaseUtil.waitForSpinner();
			Assert.assertTrue(
					"test should be inserted",
					BaseUtil.verifyPresenceOfText(
							getProps().getProperty("datafixChannelSearchField"),
							searchText));
			listDataFix = BaseUtil.getMultipleElementsAfterLoaded(getProps()
					.getProperty("datafixChanneld"));

		} else if (taskName.equalsIgnoreCase("DataFix")) {
			BaseUtil.enterText(getProps().getProperty("datafixSearchField"),
					searchText);
			BaseUtil.waitForSpinner();
			Assert.assertTrue("test should be inserted", BaseUtil
					.verifyPresenceOfText(
							getProps().getProperty("datafixSearchField"),
							searchText));
			listDataFix = BaseUtil.getMultipleElementsAfterLoaded(getProps()
					.getProperty("datafixSearchField"));
		}

		Assert.assertTrue("list site name must be 1", listDataFix.size() == 1);
	}

	public boolean isElementDisplayedOnDataFixTask(String elementName) {
		try {
			switch (elementName.toUpperCase()) {
			case "TASK":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"datafixTask"));
			case "STARTUTC":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"dataFixTaskStartUtc"));
			case "ENDUTC":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"dataFixTaskEndUtc"));
			case "CHANNELID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"dataFixTaskChannelId"));
			case "SOURCECHANNELID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"dataFixTaskSourceChannelId"));
			case "DESTCHANNELID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"dataFixTaskDestChannelId"));
			case "CHANNELID1":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"dataFixTaskChannelId1"));
			case "CHANNELID2":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"dataFixTaskChannelId2"));
			case "DIVISOR":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"dataFixTaskDivisor"));
			case "MULTIPLICAND":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"dataFixTaskMultiplicand"));

			default:
				logger.error("Switch Case["
						+ elementName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + elementName
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean verifyPagination(String link, String taskName) {
		try {
			if (taskName.equalsIgnoreCase("backup/restore")) {
				boolean nextFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("dataFixBackupPaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("dataFixBackupPaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("dataFixBackupPaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("dataFixBackupPaginationFirst"));
				Assert.assertTrue(
						"Expected : All the pagination buttons should be disable, Actual : one or more buttons are enabled",
						(nextFlg || lastFlg || prevFlg || firstFlg));
				return true;
			} else {
				boolean nextFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("datafixPaginationNext"));
				boolean lastFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("datafixPaginationLast"));
				boolean prevFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("datafixPaginationPrev"));
				boolean firstFlg = BaseUtil.verifyElementDisplayed(getProps()
						.getProperty("datafixPaginationFirst"));
				Assert.assertTrue(
						"Expected : All the pagination buttons should be disable, Actual : one or more buttons are enabled",
						(nextFlg || lastFlg || prevFlg || firstFlg));
				return true;
			}
		} catch (Exception e) {
			logger.error("Failed to verify pegination  for the link  " + link
					+ " see detail error message \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to verify pegination for the link  [" + link
					+ "] in data fix page. see detail error message \n"
					+ e.getStackTrace().toString());
		}
		return false;
	}

	public void enterKey(String pageName, String taskName) {
		try {
			if (taskName.equalsIgnoreCase("pagination")) {
				WebElement searchBox = BaseUtil
						.getElementAfterLoaded(getProps().getProperty(
								"datafixSearchField"));
				searchBox.sendKeys(Keys.PAGE_DOWN);
			} else if (taskName.equalsIgnoreCase("Create Backup")) {
				WebElement searchBox = BaseUtil
						.getElementAfterLoaded(getProps().getProperty(
								"datafixCreateBackupSiteSearchField"));
				searchBox.sendKeys(Keys.PAGE_DOWN);
			} else if (taskName.equalsIgnoreCase("Create DataFix")) {
				WebElement searchBox = BaseUtil
						.getElementAfterLoaded(getProps().getProperty(
								"datafixCreateBackupSiteSearchField"));
				searchBox.sendKeys(Keys.PAGE_UP);
			}
		} catch (Exception e) {
			logger.error("Failed to verify pegination  for the link  "
					+ pageName + " see detail error message \n"
					+ e.getStackTrace());
		}
	}

	public void enterText_DataFixPage(String sElementName, String sText) {
		// String sCaseOptions = "SITE_SEARCH, ";
		try {
			switch (sElementName.toUpperCase()) {
			case "SITE_SEARCH":
				BaseUtil.enterText(
						getProps().getProperty(
								"datafixCreateBackupSiteSearchField"), sText);
				BaseUtil.waitForSpinner();
				break;
			case "CHANNEL_SEARCH":
				BaseUtil.enterText(
						getProps().getProperty(
								"datafixBackupChannelSearchField"), sText);
				BaseUtil.waitForSpinner();
				break;
			case "SEARCH":
				BaseUtil.enterText(
						getProps().getProperty("datafixSearchField"), sText);
				BaseUtil.waitForSpinner();
				break;
			default:
				logger.error("Switch Case["
						+ sElementName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				Assert.fail("Failed to enter the text in field ["
						+ sElementName
						+ "] because you passed wrong parameter["
						+ sElementName
						+ "] either in feature file or test class. \n please choose valid parameter from the following list: \n ["
						+ sElementName + "]");
			}
		} catch (Exception e) {
			logger.error("Failed to enter the value in the ["
					+ sElementName
					+ "] field in datafix back page. see detail error message : \n"
					+ e.getStackTrace().toString());
			Assert.fail("Failed to enter the value in the [" + sElementName
					+ "] field in datafix page. see detail error message \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public boolean isElementDisplayedOnCreateBackupPopup(String element) {
		try {
			switch (element.toUpperCase()) {
			case "DATETIME STAMP":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"createBackupPopupdateTimeStamp"));
			case "NOTES":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"createBackupPopupNotes"));
			case "CHANNEL ID":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"createBackupPopupChannelId"));
			case "SAVE":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"createBackupPopupSave"));
			case "CANCEL":
				return BaseUtil.verifyElementDisplayed(getProps().getProperty(
						"createBackupPopupCancel"));
			default:
				logger.error("Switch Case["
						+ element
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to displayed the element :" + element
					+ " see detail message : " + e.getMessage());
			return false;
		}
	}

	public boolean verifyCreationOfDataFix(String taskName, String channelId,
			String... obj) {
		boolean success = false;
		String value1AfterSave = null, value2AfterSave = null, valueAfterSave = null;
		String query2 = null, message;
		try {
			GPConnection gpcon = new GPConnection();
			GPDataBaseUtil dbutil = new GPDataBaseUtil();
			Connection conn = gpcon.getConnection(TestBase.getDbUrl(),
					TestBase.getDbUsername(), TestBase.getDbPassword());
			// String query1 = "SELECT value FROM interval_data WHERE
			// channel_id=" + channelId;

			String query1 = "SELECT value FROM interval_data WHERE channel_id="
					+ channelId + " order by recorded_ts desc limit 1";
			valueAfterSave = dbutil.getIdFromDB(query1, conn);

			if (obj.length != 0) {
				// query2 = "SELECT value FROM interval_data WHERE channel_id="
				// + obj[0];
				query2 = "SELECT value FROM interval_data WHERE channel_id="
						+ obj[0] + " order by recorded_ts desc limit 1";
				value1AfterSave = dbutil.getIdFromDB(query1, conn);
				value2AfterSave = dbutil.getIdFromDB(query2, conn);

				message = "Failed to perform datafix for :[" + taskName
						+ "], Before Save values are: " + globalBeforeSave1
						+ "," + globalBeforeSave2
						+ " and After Save values are: " + value1AfterSave
						+ "," + value2AfterSave;
			} else {
				message = "Failed to perform datafix for :[" + taskName
						+ "], Before Save values are: " + globalBeforeSave1
						+ " and After Save values are: " + valueAfterSave;
			}

			String value1, value2;
			Long result1, result2;
			switch (taskName.toUpperCase()) {
			case "DELETE CHANNEL":
				if (globalBeforeSave1 != null && valueAfterSave == null) {
					success = true;
				}
				break;
			case "MULTIPLE DELETE CHANNEL":
				/**
				 * @author Gajendra Rawat
				 */
				if ((globalBeforeSave1 != null && value1AfterSave == null)
						&& (globalBeforeSave2 != null && value2AfterSave == null)) {
					success = true;
				}
				break;
			case "DELETE NEGATIVE CHANNEL":
				if (globalBeforeSave1 != null && valueAfterSave == null) {
					success = true;
				}
				break;
			case "DIVIDE CHANNEL":
				value1 = getProps().getProperty("channelValue");
				int divisor = Integer.parseInt(value1);
				Long result = Long.parseLong(globalBeforeSave1) / divisor;
				if (result == Long.parseLong(valueAfterSave)) {
					success = true;
				}
				break;
			case "MULTIPLE DIVIDE CHANNEL":
				/**
				 * @author Gajendra Rawat
				 */
				value1 = getProps().getProperty("channelValue");
				int divisor1 = Integer.parseInt(value1);
				result1 = Long.parseLong(globalBeforeSave1) / divisor1;
				value2 = getProps().getProperty("channelValue");
				int divisor2 = Integer.parseInt(value2);
				result2 = Long.parseLong(globalBeforeSave2) / divisor2;
				if ((result1 == Long.parseLong(value1AfterSave))
						&& (result2 == Long.parseLong(value2AfterSave))) {
					success = true;
				}
				break;
			case "MULTIPLY CHANNEL":
				value1 = getProps().getProperty("channelValue");
				int multiplicant = Integer.parseInt(value1);
				result1 = Long.parseLong(globalBeforeSave1) * multiplicant;
				if (result1 == Long.parseLong(valueAfterSave)) {
					success = true;
				}
				break;
			case "MULTIPLE MULTIPLY CHANNEL":
				/**
				 * @author Gajendra Rawat
				 */
				value1 = getProps().getProperty("channelValue");
				int multiplicant1 = Integer.parseInt(value1);
				result1 = Long.parseLong(globalBeforeSave1) * multiplicant1;
				value2 = getProps().getProperty("channelValue2");
				int multiplicant2 = Integer.parseInt(value2);
				result2 = Long.parseLong(globalBeforeSave2) * multiplicant2;
				if ((result1 == Long.parseLong(value1AfterSave))
						&& (result2 == Long.parseLong(value2AfterSave))) {
					success = true;
				}
				break;
			case "SWAP CHANNEL":
				/**
				 * @author Gajendra Rawat
				 */
				if ((Long.parseLong(globalBeforeSave1) == Long
						.parseLong(value2AfterSave))
						&& (Long.parseLong(globalBeforeSave2) == Long
								.parseLong(value1AfterSave))) {
					success = true;
				}
				break;
			case "COPY CHANNEL":
				if ((Long.parseLong(globalBeforeSave1) == Long
						.parseLong(value2AfterSave))
						&& (Long.parseLong(globalBeforeSave2) == Long
								.parseLong(value1AfterSave))) {
					success = true;
				}
				break;
			default:
				logger.error("Switch Case["
						+ taskName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
			}

			if (success == false) {
				Assert.fail(message);
			}
		} catch (Exception e) {
			logger.error("Failed to verify the creation of task :" + taskName
					+ " see detail message : " + e.getMessage());
			success = false;
		}
		return success;
	}

	public boolean validateDatePickerForDataFixTaskSearch(String taskName,
			List<Map<String, String>> dates) {
		try {

			if (taskName.equalsIgnoreCase("datafix")) {
				BaseUtil.explicitWait(3000);
				BaseUtil.click(getProps().getProperty("datafixDatePicker"));
				BaseUtil.enterText(
						getProps().getProperty("datafixStartDateText"), dates
								.get(0).get("startDate"));
				BaseUtil.explicitWait(3000);
				// BaseUtil.enterText(getProps().getProperty("datafixEndDateText"),
				// dates.get(0).get("endDate"));
				// BaseUtil.explicitWait(3000);
				// BaseUtil.selectDropDownByValue(getProps().getProperty("datafixTaskStartHour"),
				// getProps().getProperty("datafixTaskStartHourTestData"));
				// BaseUtil.selectDropDownByValue(getProps().getProperty("datafixTaskStartMin"),
				// getProps().getProperty("datafixTaskStartMinTestData"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixEndHours"), getProps()
								.getProperty("datafixEndHourTestData"));
				// BaseUtil.selectDropDownByValue(getProps().getProperty("datafixTaskEndMin"),
				// getProps().getProperty("datafixTaskEndMinTestData"));
				return true;
			} else {
				BaseUtil.click(getProps().getProperty("datafixDatePicker"));
				BaseUtil.enterText(
						getProps().getProperty("datafixStartDateText"), dates
								.get(0).get("startDate"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixEndHours"), getProps()
								.getProperty("datafixEndHourTestData"));

				return true;
			}
		} catch (Exception e) {
			logger.error("Some error occured while setting the date["
					+ dates.get(0) + "] in datepicker : " + e.getMessage());
			return false;
		}
	}

	public boolean validateDatePickerForCreateTask(String taskName,
			List<Map<String, String>> dates) {
		try {

			if (taskName.equalsIgnoreCase("datafix")) {
				BaseUtil.click(getProps()
						.getProperty("datafixCreateDatePicker"));
				BaseUtil.enterText(
						getProps().getProperty("datafixtaskStartDateText"),
						dates.get(0).get("startDate"));
				BaseUtil.explicitWait(3000);
				BaseUtil.enterText(
						getProps().getProperty("datafixtaskEndDateText"), dates
								.get(0).get("endDate"));
				BaseUtil.explicitWait(3000);
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixTaskStartHour"),
						getProps().getProperty("datafixTaskStartHourTestData"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixTaskStartMin"),
						getProps().getProperty("datafixTaskStartMinTestData"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixTaskEndHour"),
						getProps().getProperty("datafixTaskEndHourTestData"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixTaskEndMin"), getProps()
								.getProperty("datafixTaskEndMinTestData"));
				return true;
			} else {
				BaseUtil.click(getProps().getProperty("datafixdatepiker"));
				BaseUtil.enterText(
						getProps().getProperty("datafixStartDateText"), dates
								.get(0).get("startDate"));
				BaseUtil.enterText(
						getProps().getProperty("datafixEndDateText"), dates
								.get(0).get("endDate"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixDatePickerStartHour"),
						getProps().getProperty(
								"datafixTaskStartHourTestDataBkp"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixDatePickerStartMin"),
						getProps()
								.getProperty("datafixTaskStartMinTestDataBkp"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixDatePickerEndHour"),
						getProps().getProperty("datafixTaskEndHourTestDataBkp"));
				BaseUtil.selectDropDownByValue(
						getProps().getProperty("datafixDatePickerEndMin"),
						getProps().getProperty("datafixTaskEndMinTestDataBkp"));
				return true;
			}
		} catch (Exception e) {
			logger.error("Some error occured while setting the date["
					+ dates.get(0) + "] in datepicker : " + e.getMessage());
			return false;
		}
	}

	public boolean verifyCreationOfSwapChannel(String sourceId, String destId) {
		String query = "SELECT value FROM interval_data WHERE channel_id=";
		try {
			GPConnection gpcon = new GPConnection();
			GPDataBaseUtil dbutil = new GPDataBaseUtil();
			Connection conn = gpcon.getConnection(TestBase.getDbUrl(),
					TestBase.getDbUsername(), TestBase.getDbPassword());

			/* values before saving */
			String getValueQuery1 = query + sourceId;
			String sourceIdValueBeforeSave = dbutil.getIdFromDB(getValueQuery1,
					conn);

			String getValueQuery2 = query + destId;
			String destIdValueBeforeSave = dbutil.getIdFromDB(getValueQuery2,
					conn);

			BaseUtil.click(getProps().getProperty("previewDatafixSaveButton"));
			BaseUtil.waitForSpinner();
			BaseUtil.explicitWait(2000);

			/* values after saving */
			String getValueQuery3 = query + sourceId;
			String sourceIdValueAfterSave = dbutil.getIdFromDB(getValueQuery3,
					conn);

			String getValueQuery4 = query + destId;
			String destIdvValueAfterSave = dbutil.getIdFromDB(getValueQuery4,
					conn);
			if (sourceIdValueBeforeSave.equalsIgnoreCase(destIdvValueAfterSave)
					&& destIdValueBeforeSave
							.equalsIgnoreCase(sourceIdValueAfterSave)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error("Some error occured while creating data fix task swap channel: "
					+ e.getMessage());
			return false;
		}
	}

	public int getRowsCountOfDatafix(String query) throws SQLException,
			IOException {
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		GPConnection gpcon = new GPConnection();
		Connection conn = gpcon.getConnection(TestBase.getDbUrl(),
				TestBase.getDbUsername(), TestBase.getDbPassword());
		int getRowsCount = 0;
		try {
			getRowsCount = dbutil.getRowsCountFromDB(query, conn);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return getRowsCount;
	}

	public boolean clickOperationOnDatafix(String buttonName) {
		try {
			switch (buttonName.toUpperCase()) {
			case "OK":
				BaseUtil.clickAndWait(getProps().getProperty(
						"datafixConfirmationButton"));
				return true;
			case "DATAFIX:SAVE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"datafixSaveButton"));
				return true;
			case "PREVIEWDATAFIX:SAVE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"previewDatafixSaveButton"));
				return true;
			case "RECORD":
				BaseUtil.clickAndWait(getProps().getProperty("dataFixList"));
				return true;
			case "SAVE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"datafixSaveButton"));
				return true;
			case "PREVIEW:SAVE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"previewDatafixSaveButton"));
				return true;
			case "CHANNELID1":
				BaseUtil.clickAndWait(getProps().getProperty(
						"datafixChannel1RadioButton"));
				return true;
			case "CHANNELID2":
				BaseUtil.clickAndWait(getProps().getProperty(
						"datafixChannel2RadioButton"));
				return true;
			case "SOURCE CHANNEL":
				BaseUtil.clickAndWait(getProps().getProperty(
						"datafixSourceRadioButton"));
				return true;
			case "DESTINATION CHANNEL":
				BaseUtil.clickAndWait(getProps().getProperty(
						"datafixDestinatonRadioButton"));
				return true;
			case "RESTORE":
				BaseUtil.clickAndWait(getProps()
						.getProperty("RestoreAccordion"));
				return true;
			case "RESTORE:BUTTON1":
				BaseUtil.clickAndWait(getProps().getProperty(
						"datafixRestoreButton1"));
				return true;
			case "RESTORE:BUTTON2":
				BaseUtil.clickAndWait(getProps().getProperty(
						"datafixRestoreButton2"));
				return true;
			case "RESTORE:CLOSE":
				BaseUtil.clickAndWait(getProps().getProperty(
						"restoreDatafixClosebtn"));
				return true;
			case "DATAFIX:CLOSE":
				BaseUtil.clickAndWait(getProps()
						.getProperty("dataFixCloseIcon"));
				return true;
			case "DATAFIX":
				BaseUtil.clickAndWait(getProps().getProperty(
						"datafixFixAccordion"));
				return true;
			case "SEARCH":
				BaseUtil.clickAndWait(getProps().getProperty(
						"datafixSearchButton"));
				return true;

			default:
				logger.error("Switch Case["
						+ buttonName
						+ "] is not matched in class["
						+ getClass().getName()
						+ "] , Method["
						+ Thread.currentThread().getStackTrace()[1]
								.getMethodName() + "]");
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to click on button :" + buttonName
					+ " , detail message: " + e.getMessage());
			return false;
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public void selectRecordFromDataGrid(String searchString) {
		boolean bResult = false;
		logger.debug("Selecting the record :" + searchString);
		try {
			List<WebElement> lstRecord = BaseUtil
					.getMultipleElementsAfterLoaded(getProps().getProperty(
							"gridDatafixColumn"));
			for (WebElement webElement : lstRecord) {
				if (webElement.getText().trim().equalsIgnoreCase(searchString)) {
					webElement.click();
					BaseUtil.waitForSpinner();
					bResult = true;
					break;
				}
			}
			if (!bResult) {
				Assert.fail("Given test record is not available");
			}
			logger.debug("record :" + searchString + " is selected.");
		} catch (Exception e) {
			logger.error("Failed to select record: " + searchString
					+ " from datafix grid , see detail error message: "
					+ e.getStackTrace().toString());
			Assert.fail("Failed to select the datafixrecord : " + searchString
					+ ", see detail error message: \n"
					+ e.getStackTrace().toString());
		} finally {
			BaseUtil.waitForSpinner();
		}
	}

	public boolean setDatePickerForCreateTask() {

		BaseUtil.explicitWait(3000);
		// BaseUtil.selectDropDownByValue(getProps().getProperty("datafixTaskStartHour"),getProps().getProperty("datafixTaskStartHourTestData"));
		// BaseUtil.selectDropDownByValue(getProps().getProperty("datafixTaskStartMin"),
		// getProps().getProperty("datafixTaskStartMinTestData"));

		BaseUtil.selectComboBoxDropDownByIndex(
				getProps().getProperty("datafixEndHour"), 3);

		// BaseUtil.selectDropDownByValue(getProps().getProperty("datafixEndHour"),getProps().getProperty("datafixEndHourTestData"));
		// BaseUtil.selectDropDownByValue(getProps().getProperty("datafixTaskEndMin"),getProps().getProperty("datafixTaskEndMinTestData"));
		return true;
	}

	public void setBeforeSaveValues(String... channelId) throws SQLException {
		GPConnection gpcon = new GPConnection();
		GPDataBaseUtil dbutil = new GPDataBaseUtil();
		Connection conn = gpcon.getConnection(TestBase.getDbUrl(),
				TestBase.getDbUsername(), TestBase.getDbPassword());
		String query1 = "SELECT value FROM interval_data WHERE channel_id="
				+ channelId[0] + " order by recorded_ts desc limit 1";
		String valueBeforeSave1 = dbutil.getIdFromDB(query1, conn);
		globalBeforeSave1 = valueBeforeSave1;
		if (channelId.length == 2) {
			String query2 = "SELECT value FROM interval_data WHERE channel_id="
					+ channelId[1] + " order by recorded_ts desc limit 1";
			String valueBeforeSave2 = dbutil.getIdFromDB(query2, conn);
			globalBeforeSave2 = valueBeforeSave2;
		}
	}

	public boolean validateSuccessMessageOfRestoreDatafix() {
		return BaseUtil.verifyElementDisplayed(getProps().getProperty(
				"datafixPopupRestoreSuccessLabel"));

	}
}
