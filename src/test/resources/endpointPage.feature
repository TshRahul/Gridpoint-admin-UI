@abc
@EndpointPage  
Feature: Endpoint Page test 
	Here we will test the Endpoint Page functionality.
	For this, we will login with admin credentials and will test
	the different scenarios including behavior and function test of the endpoint page.


Background: Initialize resources for test script 
	Given Initialize resources in the beginning of each scenario for endpoint Page 
	
@UserLogin
Scenario: Scenario_01
	Scenario_initialize: Initialize the browser before execution of the test cases of Add Test Data 
	Given Initialize resources in the beginning of each scenario	
#	
#@EndpointSorting 
#Scenario: Scenario_02: Verify the column names and sorting functionality of endpoint detail grid on endpoint page
#    Given User is already logged in to application  
#	Given we are on endpoint page and search endpoint field is enabled 
#	 Then verify the following columns in endpoint details grid 
#		| Endpoint ID | Connection Status | Type | Serial | Reference ID | Live Firmware | Last Message Received (UTC) |
#	Then verify the sorting functionality of columns in endpoint details grid 
#		| Endpoint ID | Connection Status | Type | Serial | Reference ID | Live Firmware | Last Message Received (UTC) |
#
#@EndpointPagePagination 
#Scenario: Scenario_03: Verify the pagination functionality on endpoint detail grid. 
#	Given User is already logged in to application 
#	Given we are on endpoint page and search endpoint field is enabled
#	When click on "next" link in endpoint grid 
#	Then "Next" page should displayed in endpoint grid 
#	When click on "last" link in endpoint grid 
#	Then "Last" page should displayed in endpoint grid 
#	When click on "prev" link in endpoint grid 
#	Then "Previous" page should displayed in endpoint grid 
#	When click on "first" link in endpoint grid 
#	Then "First" page should displayed in endpoint grid 
#	When enter random page number in page link text in "Endpoint" grid
#	
#	# TODO : below step removed : 1/8/2018 8:32:19 PM
#	Then user should be navigated on the given random page in endpoint grid 
#		
#@AddEnpoint 
#Scenario: Scenario_03: Verify Add various Endpoints by various Users
#    Given User is already logged in to application 
#	Given we are on endpoint page and search endpoint field is enabled 
#	Then delete the "Endpoints" test data
#		| endpointPage_endpointBot1 | 	
#	Then click on the "Add Endpoint" button on endpoint page 
#	Then add endpoint popup should be displayed with following fields and button: 
#		| Add Endpoint | Serial | Type | Save | Cancel |
#	Then verify the values for Type field in Add Endpoint popup
#		| Blank | EC100 | EC1000 | Tridium Adapter | 	
#	Then enter the values for new endpoint "1" 
#	When click on "Save" button on add endpoint popup 
#	Then user should be navigated to endpoint page with a label saying "Endpoint endpointPage_endpointBot1 saved."  
#	When enter endpoint id "endpointPage_endpointBot1" in search text box on the Endpoint list page 
#	And  click on the "Search" button on endpoint page 
#	Then select the entered endpoint id "endpointPage_endpointBot1" from the endpoint details grid 
#	Then verify the endpoint name on edit endpoint popup for endpoint "1" 
#	And  close endpoint popup 
#	Then click on the "Add Endpoint" button on endpoint page 
#	When click on "Cancel" button on add endpoints popup 
#	Then confirmation box on add endpoint popup should display with message "All Changes will be lost. Are you sure?" 
#	When click on "Cancel_Popup" button on add endpoints popup  
#	Then user should remain on add endpoint popup 
#	When click on "Cancel" button on add endpoint popup 
#	Then confirmation box on add endpoint popup should display with message "All Changes will be lost. Are you sure?" 
#	When click on "Ok_Popup" button on add endpoints popup 
#	Then user should be navigated to endpoints list page 
#	Then click on the "Add Endpoint" button on endpoint page 
#	Then enter invalid values in add endpoint fields 
#		|    Serial     | 
#		| !@$%^^SDFSDFS |
#	Then enter the type of endpoint 
#	When click on "Save" button on add endpoint popup 
#	Then valid error message "Required String parameter 'serial' is not present" should be displayed 
#	When click on "Cancel" button on add endpoint popup 
#	When click on "Ok_Popup" button on add endpoints popup 
#	Then user should be navigated to endpoints list page
#
#@EndpointSearch 
#Scenario: Scenario_04: Verify the search functionality of endpoint page. 
#    Given Given User is already logged in to application 
#	Given we are on endpoint page and search endpoint field is enabled 
#	Then verify the search output by entering "endpointBot1:Init3Char" in endpoint search field 
#	Then verify the search output by entering ":RANDOM" which is a non existing endpoint in endpoint search field 
#	#Then verify the search output by entering "endpointBot1:BACKSPACE" more than three character and click on backspace button in endpoint search field 
#	Then verify the search output by entering string "endpointBot1:DELETE" and enter delete button in endpoint search field 
#	Then verify the pagination by entering value "endpointPage_endpointBot1:Pagination" in endpoint search field 
#	Then verify the search output by entering ":LONGTEXT" which is a non existing endpoint in endpoint search field 
##					
#
#
#@abc
@EditEndpoint
Scenario: Scenario_05: Verify the edit functionality on the endpoints details section
	Given User is already logged in to application    
	Given we are on endpoint page and search endpoint field is enabled 
#	# Create test data
#	When user click on the "Tenants" tab
#	Then delete the "Tenants" test data
#		|endpointPage_tenantBot1|
#	When click on the "Add Tenant" button 
#	#Then enter the "Name" like "endpointPage_tenantBot1" on add tenant popup
#	When click on the "Save" button
#	Then user click on the "Site" tab
#	Then delete the "Sites" test data
#		| endpointPage_siteBot1 | 
#	When click on "Add Site" button on the site page
#	Then enter test data for the following fields to create site "1"
#		| Site ID  | Site Description | Address1 | City | State | Postal Code | Country | Phone 1 | Phone 2 | Paths | 
#	When click on "Save" button of add site popup
#	When click on "Accept Verified Data" button of add site popup	
#	When click on "Save" button of add site popup
#	Then user click on the "Endpoints" tab
#	Then delete the "Endpoints" test data
#		| endpointPage_endpointBot1 | 
#	Then click on the "Add Endpoint" button on endpoint page  	
#	Then enter the values for new endpoint "1" 
#	When click on "Save" button on add endpoint popup
##****************************These are in the pending excel file***************
#And verify the selected endpoint details with following labels in endpoint details page 
#	| Serial | Reference ID | Password | Type | Last Message Received (UTC) | Association Tenant/Site | MAC Address | Commission State  | Uboot Version | Linux Version | Root File system Version | Monitoring | Connection Status (UTC) |
#	Then verify the visibility of the attributes in site information section on edit endpoints popup 
#		| Tenant | Site ID(Name) | Site Name(Description) | Address | Postal Code | Latitude | Longitude | Time Zone | 
##	********************************************************************************
#	#Test Start
#	When enter endpoint id "endpointPage_endpointBot1" in search text box on the Endpoint list page 
#	And click on the "Search" button on endpoint page 
#	Then select the entered endpoint id "endpointPage_endpointBot1" from the endpoint details grid 	
#	And verify that new endpoint is associated to "GridPoint" tenant and "Provisioning" site 
#	Then value of Select Firmware for update label should always be " - Select One" 
#	Then check the "Monitoring" checkbox on endpoint detail grid.
#	Then verify the "Monitoring" checkbox value on the basis of last saved
#	
#	When user modify following fields (Need to work on this)
#	| Type | ASSOCIATION TENANT/SITE |
	
	# GPUP-29949: Verify Save and Close button on edit user model.		
#	Then verify following button should be enabled on edit endpoint popup 
#		| Edit:Cancel | Edit:Save | Save and Close |
#	When click on "edit endpoint cancel" button on endpoint page 
#	Then confirmation box on endpoint details page should display with message "All Changes will be lost. Are you sure?" 
#	When click on "Cancel_Popup" button on edit endpoints popup 
#	Then click on "edit endpoint cancel" button on endpoint page 
#	Then confirmation box on endpoint details page should display with message "All Changes will be lost. Are you sure?"
#	When click on "Ok_Popup" button on edit endpoints popup 
#	Then user should be navigated to endpoints list page
	
	# GPUP-29950: Verify Search entries should not clear when user saves edits to a record.
	When enter endpoint id "tshEnd" in search text box on the Endpoint list page 
	And click on the "Search" button on endpoint page 
	Then select the entered endpoint id "tshEnd" from the endpoint details grid 	
	Then click "Edit:Save" button on edit endpoint model and verify message "Details have been successfully updated." on the same	
	Then click the "Save and Close" button on edit endpoint page and verify the total record count on endpoint list page
	#Then user should be navigated to endpoint page with a label "Endpoint ######## saved." for endpoint "tshEnd"
	Then Verify user should be able to change the tenant type for the newly created "tshEnd" type endpoint
	When enter endpoint id "tshEnd" in search text box on the Endpoint list page 
	And click on the "Search" button on endpoint page 
	Then select the entered endpoint id "tshEnd" from the endpoint details grid 
	Then change the endpoint type
	Then click on "Edit:Save" button
	Then verify that the save button is still enable
	Then verify "tshEnd" is tied to sites
    Then Verify The Graph Icon on editEndpoint
	Then click on endpoint page and verify we are on endpoint page
    When enter endpoint id "tshEnd" in search text box on the Endpoint list page 
	And click on the "Search" button on endpoint page 
	Then select the entered endpoint id "tshEnd" from the endpoint details grid 
	Then Verify the "Association Tenant/Site" drop down field on the endpoints details page
	Then Verify the firmware dropdown should contain the real value
	Then move to endpoint and verify the following columns of table
	         | Port | Interface | MAC Address| IP Address |
    And Verify the number if endipoint is connected with more than one device
    And Verify the device table
    Then Verify that On edit endpoint this should be shown net1 for key keyNum:1 or show net 0 for keyNum:2
	Then Verify Select Configuration dropdown list on Reset Configuration
	Then Verify search by leaving search textbox blank on the Endpoints page
	Then click on "Site" tab on gridpoint page
	Then enter site "rs_site" in the site search text box
	Then select the entered site "rs_site" from site tab grid
	Then Verify that endpoints for the selected site are listed in the endpoints section, and navigate on endpoint after selection
	Then Verify the enable editing button is enable on endpoint Configuration accordion
	

@EndpointPageConfigAccordion 
Scenario: Scenario_06: Verify the functionality of Endpoint Config section for the XML for the superadmin role. 
	Given User is already logged in to application 
	Given we are on endpoint page and search endpoint field is enabled  
	Then enter endpoint id "endpointPage_endpointBot1" in search text box on the Endpoint list page 
	And click on the "Search" button on endpoint page  
	Then select the entered endpoint id "endpointPage_endpointBot1" from the endpoint details grid 
	Then click on the "configuration" accordion on edit endpoint popup 
	Then verify the "driver.xml, schedule.xml and peripheral.xml" xml location in text box in configuration accordion 
	And are visible when clicked on "Show" Button of "driver.xml, schedule.xml and peripheral.xml" xml location with blank values 
	And following button are disabled 
		| Validate Config | Save Config | 
	Then verify the "endpoint.xml" xml location in text box in configuration accordion 
	And are visible when clicked on "Show" Button of "endpoint.xml" xml location with blank values 
	And are on locked mode and can be edited only when "Enable Editing" button is clicked for "endpoint xml" 
	Then confirmation box on endpoint edit details popup should display with message "Errant changes to endpoint.xml can cause the server to lose communication to the endpoint. Are you sure you want to edit it?" 
	When click on "Cancel" button on confirmation box on Endpoint Page 
	Then Click on "Enable Editing" button for xml location "endpoint.xml" 
	Then confirmation box on endpoint edit details popup should display with message "Errant changes to endpoint.xml can cause the server to lose communication to the endpoint. Are you sure you want to edit it?" 
	When click on "Ok" button on confirmation box on Endpoint Page 
	And following button are disabled 
		| Validate Config | Save Config |	
	Then Verify that following buttons are available on the configuration accordion 
		| Download XML Schema | Config History | Validate Config | Save Config | Upload Config File | Download Config File | Enable Editing(CombinedXml) | Enable Editing(EndpointXml) |
	Then click on "X" button on endpoint page
#	
@EndpointPagePeripheralsAccordion 
Scenario: Scenario_07: Verify the functionality of Peripherals section on the endpoints details page 
	Given User is already logged in to application 
	Given we are on endpoint page and search endpoint field is enabled 
	#Create test data
	Then delete the "Endpoints" test data
		| endpointPage_endpointBot11 | endpointPage_endpointBot21 | 
	Then click on the "Add Endpoint" button on endpoint page
	Then enter the values for new endpoint "11" 
	When click on "Save" button on add endpoint popup 
	Then user should be navigated to endpoint page with a label saying "Endpoint endpointPage_endpointBot11 saved."
	Then click on the "Add Endpoint" button on endpoint page
	Then enter the values for new endpoint "21" 
	When click on "Save" button on add endpoint popup 
	Then user should be navigated to endpoint page with a label saying "Endpoint endpointPage_endpointBot21 saved."	
	
	#Perform test
	When enter endpoint id "endpointPage_endpointBot11" in search text box on the Endpoint list page 
	And click on the "Search" button on endpoint page  
	Then select the entered endpoint id "endpointPage_endpointBot11" from the endpoint details grid 
	Then click on the "Peripherals" accordion on edit endpoint popup 
	And verify that "Peripherals" accordion is opened 
	Then verify that following columns are present in the "Peripherals" accordion datagrid 
		| Id | Gpec Peripheral Id | Name | Address | Type | Firmware Version | Validated | Responsive | Comm Id |
	Then verify the sorting functionality when click on the following attributes on "Peripherals" accordion datagrid 
		| Id | Gpec Peripheral Id | Peripheral Name | Peripheral Address | Type | Firmware Version |
	Then click on "edit endpoint cancel" button on endpoint page 
	Then confirmation box on endpoint details page should display with message "All Changes will be lost. Are you sure?" 
	When click on "Ok_Popup" button on edit endpoints popup
	Then user should be navigated to endpoints list page 
	
	#Check PeripheralAccordion for Legacy Sites
	When enter endpoint id "endpointPage_endpointBot21" in search text box on the Endpoint list page 
	And click on the "Search" button on endpoint page  
	Then select the entered endpoint id "endpointPage_endpointBot21" from the endpoint details grid 
	Then verify Peripheral accordion should not display for legacy sites
	
	#Remove test data
	Then delete the "Endpoints" test data
		| endpointPage_endpointBot11 | endpointPage_endpointBot21 | 
		
@EndpointPageDevicesAccordion 
Scenario: Scenario_08: Verify the functionality of Devices section on the endpoints details page 
	Given User is already logged in to application  
	Given we are on endpoint page and search endpoint field is enabled 
	Then delete the "Endpoints" test data
		| endpointPage_endpointBot1 | 
	Then click on the "Add Endpoint" button on endpoint page  	
	Then enter the values for new endpoint "1" 
	When click on "Save" button on add endpoint popup
	Then delete test data for "Devices" accordion of endpoint page for "endpointPage_endpointBot1" 
	Then we enter the test "Devices" data in the database table to check the functionality for "endpointPage_endpointBot1" 
	When enter endpoint id "endpointPage_endpointBot1" in search text box on the Endpoint list page 
	And click on the "Search" button on endpoint page  
	Then select the entered endpoint id "endpointPage_endpointBot1" from the endpoint details grid 
	Then click on the "Devices" accordion on edit endpoint popup 
	And verify that "Devices" accordion is opened 
	Then verify that following columns are present in the "Devices" accordion datagrid 
		| Device Id | Device Type | Name | Address | Channel Count | 
	Then verify the sorting functionality when click on the following attributes on "Devices" accordion datagrid 
		| Device Id | Device type | Name | Address | channel count | 
	Then verify the paging controls display in "Devices" accordion datagrid 
		| first | last | Previous | next | 
	When click on "next" link in Devices accordion datagrid 
	Then "Next" page should displayed in Devices accordion datagrid 
	When click on "first" link in Devices accordion datagrid 
	Then "First" page should displayed in Devices accordion datagrid 
	When click on "last" link in Devices accordion datagrid 
	Then "Last" page should displayed in Devices accordion datagrid 
	When click on "prev" link in Devices accordion datagrid 
	Then "Previous" page should displayed in Devices accordion datagrid 
	When enter random page number in page link text in "Devices" accordion datagrid 
	Then user should be navigated on the given random page in Devices accordion datagrid 
	Then verify total number of records is displaying at the bottom of the Devices accordion datagrid 
	Then delete test data for "Devices" accordion of endpoint page for "endpointPage_endpointBot1" 
	
@EndpointPageHistoryAccordion 
Scenario: Scenario_09: Verify the functionality of History section on the endpoints details page 
	Given User is already logged in to application  
	Given we are on endpoint page and search endpoint field is enabled  
	Then delete test data for "History" accordion of endpoint page for "endpointPage_endpointBot1" 
	Then we enter the test "History" data in the database table to check the functionality for "endpointPage_endpointBot1" 
	When enter endpoint id "endpointPage_endpointBot1" in search text box on the Endpoint list page 
	And click on the "Search" button on endpoint page  
	Then select the entered endpoint id "endpointPage_endpointBot1" from the endpoint details grid 
	Then click on the "History" accordion on edit endpoint popup 
	And verify that "History" accordion is opened 
	Then verify that following columns are present in the "History" accordion datagrid 
		| Timestamp | Level | Module | Message |
	Then verify the sorting functionality when click on the following attributes on "History" accordion datagrid 	
	    | Timestamp | Level | Module | Message |
	Then verify the paging controls display in "History" accordion datagrid 
		| first | last | Previous | next | 
	When click on "date picker" in the History accordion 
	Then verify the visibility of the following 
		| Start date | Start time | End Date | End time |
	Then enter data for following attributes in the date picker 
		| startDate  | endDate     | 
		| 04/09/2018 | 04/11/2018  |  
	When click on "next" link in History accordion datagrid 
	Then "Next" page should displayed in History accordion datagrid 
	When click on "first" link in History accordion datagrid 
	Then "First" page should displayed in History accordion datagrid 
	When click on "last" link in History accordion datagrid 
	Then "Last" page should displayed in History accordion datagrid 
	When click on "prev" link in History accordion datagrid 
	Then "Previous" page should displayed in History accordion datagrid 	
	And verify that "Details" accordion is opened 
	Then delete test data for "History" accordion of endpoint page for "endpointPage_endpointBot1" 
	
@EndpointPageAuditAccordion 
Scenario: Scenario_10: Verify the functionality of Audit section on the endpoints details page 
	Given User is already logged in to application 
	Given we are on endpoint page and search endpoint field is enabled 
	
	# Create test data
	Then delete all the dependencies associated with "endpointPageFirmwareBot3" used as test data for endpoint page 
	Then delete test data for "Audit" accordion of endpoint page for "endpointPageFirmwareBot3"  
	Then click on the "Add Endpoint" button on endpoint page 
	Then delete the "Endpoints" test data
		| endpointPage_endpointBot3 | 
	Then enter the values for new endpoint "3" 
	When click on "Save" button on add endpoint popup 
	Then user should be navigated to endpoint page with a label saying "Endpoint endpointPage_endpointBot3 saved."
	
	# Test Start
	When enter endpoint id "endpointPage_endpointBot3" in search text box on the Endpoint list page 
	Then click on the "Search" button on endpoint page  
	Then select the entered endpoint id "endpointPage_endpointBot3" from the endpoint details grid 
	Then verify that "Details" accordion is opened 
	Then verify the selected endpoint details with following labels in endpoint details page
		| Reference ID | Password | Last Message Received (UTC) | Association Tenant/Site | MAC Address | Commission State |
	Then verify the endpoint type for "endpointPage_endpointBot3" is "EC100"
	Then update value of endpoint type from "Tridium Adapter" to "EC1000" 
	Then click on "Edit:Save" button on endpoint page 	
	Then click on the "Audit" accordion on edit endpoint popup 
	Then verify that "Audit" accordion is opened 
	Then verify the audit details of the "updated" endpoint type "EC1000" in previous steps
	Then verify that following columns are present in the "Audit" accordion datagrid 
		| Property | Action | Original Value | Updated Value | User | Date Stamp |
	Then verify the sorting functionality when click on the following attributes on "Audit" accordion datagrid 
		| Property | Action | Original Value | Updated Value | User | Date Stamp |
	Then verify the paging controls display in "Audit" accordion datagrid 
		| first | last | Previous | next | 
	Then verify the audit details of the "new" created endpoint "endpointPage_endpointBot3" 
	
	Then click on "edit endpoint cancel" button on endpoint page 
	Then confirmation box on endpoint details page should display with message "All Changes will be lost. Are you sure?" 
	When click on "Ok_Popup" button on edit endpoints popup
	Then user should be navigated to endpoints list page 
		
@EndpointPageToSiteTabNavigation 
Scenario: Scenario_11:Verify the user should be able to navigate to Sites tab by Clicking on the Site name(Link) on the Site details Section of the Endpoints Tab 
	Given User is already logged in to application 
	Given we are on endpoint page and search endpoint field is enabled 
	Then delete the "Endpoints" test data
		| endpointPage_endpointBot1 | 
	Then click on the "Add Endpoint" button on endpoint page  	
	Then enter the values for new endpoint "1" 
	When click on "Save" button on add endpoint popup
	When enter endpoint id "endpointPage_endpointBot1" in search text box on the Endpoint list page 
	And click on the "Search" button on endpoint page  
	And select the entered endpoint id "endpointPage_endpointBot1" from the endpoint details grid 
	Then user should be navigated to endpoints list page 
	When click on the "Site ID (name) link" on the site details section 
	Then user should be navigated on site page 
	And site details accordion should be open 
	
@EndpointPageNonGpecEndpointDownloadXSD 
Scenario: Scenario_12: Download XSD and verify 
	Given User is already logged in to application 
	Given we are on endpoint page and search endpoint field is enabled 
	Then enter endpoint id "endpointPage_endpointBot1" in search text box on the Endpoint list page 
	Then click on the "Search" button on endpoint page  
	Then select the entered endpoint id "endpointPage_endpointBot1" from the endpoint details grid 
	Then click on the "configuration" accordion on edit endpoint popup 
	Then verify "Download XML Schema" button are disabled 
	
#@EndpointPageEditPopupResetEndpoint 
#Scenario: Scenario_13: Verify the functionality of the Reset Endpoint button on the edit endpoint popup 
#	Given User is already logged in to application  
#	Given we are on endpoint page and search endpoint field is enabled 
#	Then delete the "Endpoints" test data
#		| endpointPage_endpointBot1 | 
#	Then click on the "Add Endpoint" button on endpoint page  	
#	Then enter the values for new endpoint "1" 
#	When click on "Save" button on add endpoint popup
#	When enter endpoint id "endpointPage_endpointBot1" in search text box on the Endpoint list page 
##	And click on the "Search" button on endpoint page  
#	Then select the entered endpoint id "endpointPage_endpointBot1" from the endpoint details grid 
#	Then verify "Reset Endpoint" button on endpoint page 
#	When click on "Reset Endpoint" button on endpoint page 
#	Then confirmation box on endpoint edit details popup should display with message "Are you sure you want to reset endpoint?" 
#	When click on "Cancel" button on confirmation box on Endpoint Page 
#	When click on "Reset Endpoint" button on endpoint page 
#	Then confirmation box on endpoint edit details popup should display with message "Are you sure you want to reset endpoint?" 
#	When click on "Ok" button on confirmation box on Endpoint Page 
#	Then message box on endpoint details page should display with message "Endpoint reset is pending."
#	When click on "Cancel_Popup" button on add endpoint popup
#	When click on "Edit Endpoint Cancel" button on add endpoint popup 
#	Then confirmation box on endpoint details page should display with message "All Changes will be lost. Are you sure?" 
#	When click on "Cancel_Popup" button on edit endpoints popup 
#	Then click on "edit endpoint cancel" button on endpoint page 
#	Then confirmation box on endpoint details page should display with message "All Changes will be lost. Are you sure?" 
#	When click on "Ok_Popup" button on edit endpoints popup
#	Then user should be navigated to endpoints list page 

#These Scenarios are under implementation.	
#GPUP-30541, the real time button is removed from the UI	
#@EndpointPageEditPopupRealTimeData 
#Scenario: Scenario_14: Verify the functionality of the Real Time Data button on the edit endpoint popup 
#	Given we are on endpoint page and search endpoint field is enabled 
#	When enter endpoint id "RKEC1K4" in search text box on the Endpoint list page 
#	And click on the "Search" button on endpoint page  
#	Then select the entered endpoint id "RKEC1K4" from the endpoint details grid 
##	Then verify the Endpoint Device Network Details for the selected endpoint:
##		| Provisioned MAC Address: | Device | Address | MAC Address | IP Address |
#	Then verify "Real Time Data" button on endpoint page 
#	When click on the "Real Time Data" button on endpoint page 
#	Then verify new browser tab is not showing message "Error connecting to on-site controller. Please try again later." 
#	And new browser tab will open with real time data of the "RKEC1K4" endpoint 
	
#@EC1kReplacementParameters 
#Scenario: Scenario_16: Verify the EC1k Replacement functionality 
#	Given we are on endpoint page and search endpoint field is enabled	 
#	Then initialize dummydata for "EC1kReplacement"

#	When controller connection is "CONNECTED" status for endpoint "geepTestEC1k" 
#	And controller connection is "DISCONNECTED (Suspended)" status for endpoint "geepTestEC1k_new" 
#	When enter endpoint id "geepTestEC1k" in search text box on the Endpoint list page 
#	And click on the "Search" button on endpoint page  
#	Then user will be navigate to endpoint page and test site "Status" will be "CONNECTED" 
#	Then select the entered ec1k endpoint id "geepTestEC1k" from the endpoint details grid 
#	And click on "Suspend Connections" button on endpoint page 
#	When enter endpoint id "geepTestEC1k" in search text box on the Endpoint list page 
#	And click on the "Search" button on endpoint page  
#	And user will be navigate to endpoint page and test site "Status" will be "CONNECTED (Suspended)" 
#	When controller connection is "DISCONNECTED (Suspended)" status for endpoint "geepTestEC1k" 
#	Then select the entered ec1k endpoint id "geepTestEC1k" from the endpoint details grid 
#	Then Suspend Connections button will change to "Enable Connections" button 
#	And "Replace Controller" button will be displayed 
#	When click on "Replace Controller" button on endpoint page 
#	Then user will be navigated to "Select Replacement" page for endpoint replacement 
#	And select the entered ec1k endpoint id "geepTestEC1k_new" from the endpoint details grid 
#	And click on "Replace" button on endpoint page 
#	And user will be navigate to endpoint page and test site "Status" will be "CONNECTED (Suspended)" 

#@ExportIconEndpoint
#Scenario: Scenario_17 Verify the functionality of Export icon.
#	Given we are on endpoint page and search endpoint field is enabled
#   	Then verify Export icon is display on Endpoint tab
##	Then click on Export icon on Endpoint tab
##	Then open the downloaded report for endpoint firmware.
#	When enter endpoint id "endpointPage_endpointBot2" in search text box on the Endpoint list page
#    And click on the "Search" button on endpoint page  
##   Then click on Export icon on EndpointFirmware tab
##	Then open the downloaded report for endpoint firmware.	
	
	
	#TODO : previously below steps were not commented. verify : 1/25/2018 7:24:03 PM
	#Delete test data
#	Then delete the "Endpoints" test data
#		| endpointPage_endpointBot1 | endpointPage_endpointBot2 | endpointPage_endpointBot3 |
#	Then delete the "Sites" test data
#		| endpointPage_siteBot1 |
#	Then delete the "Tenants" test data
#		| endpointPage_tenantBot1 |
			