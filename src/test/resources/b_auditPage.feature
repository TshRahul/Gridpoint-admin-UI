@AdminUI_AuditPage 
Feature: Audit Page test 
	Here we will test the audit Page functionality.
	For this, we will login with admin credentials and will test
	the different scenarios including behavior and function test of the audit page.
 
@AdminUI_AuditCredentials 
Scenario: Scenario_01: verify visibility of audit tab as login with different user roles 
	Given User is already logged in to Admin URL and is already present at the AuditPage Tab for "Scenario_01" 
	Given we are on Audit page and search audit field is enabled and date picker is visible
	When click on logout button and redirect to login page 
	And login with following roles with valid username and password and verify these user should not be able to view Audit Page 
		| autoSupportTest |
	When click on logout button and redirect to login page 
	Then Initialize resources in the beginning of Audit Page 
	
@AdminUI_AuditSorting 
Scenario: Scenario_02: Verify the column names and sorting functionality of audit detail grid on Audit page 
	Given User is already logged in to Admin URL and is already present at the AuditPage Tab for "Scenario_02" 
	Given we are on Audit page and search audit field is enabled and date picker is visible 
	Then verify the following columns in audit details grid 
		| Entity ID | Entity Name | Domain Type | Property | Action | Original Value | Updated Value | User | Date Stamp |
	Then verify the sorting functionality of columns in audit details grid 
		| Domain Type | Property | Action | Original Value | Updated Value | User | Date Stamp |
		
@AdminUI_AuditPagination 
Scenario: Scenario_03: Verify the pagination functionality on audit detail grid. 
	Given User is already logged in to Admin URL and is already present at the AuditPage Tab for "Scenario_03" 
	Given we are on Audit page and search audit field is enabled and date picker is visible 
	When click on "next" link in audit grid 
	Then "Next" page should displayed in audit grid 
	When click on "last" link in audit grid 
	Then "Last" page should displayed in audit grid 
	When click on "prev" link in audit grid 
	Then "Previous" page should displayed in audit grid 
	When click on "first" link in audit grid 
	Then "First" page should displayed in audit grid 
	When enter random page number in page link text in audit grid 
	Then user should be navigated on the given random page in audit grid 
	
@AdminUI_AuditResources 
Scenario: Scenario_04: Create Resources for audit page. 
	Given User is already logged in to Admin URL and is already present at the AuditPage Tab for "Scenario_04" 
	Given we are on Audit page and search audit field is enabled and date picker is visible 
	
	#Test data for @EndpointFirmwareAudit,	
	Then delete the "Endpoint Firmware" data used as test data for audit page 
		| auditPageEndpointFirmwareBot |
	Then click on "Firmware" tab/button from Audit page 
	Then click on "Endpoint Firmware" tab/button from Audit page 
	When click on "Add Endpoint Firmware" tab/button from Audit page 
	Then click on the "Choose File" button on add "endpoint firmware" popup and select Valid file for auditing 
	Then enter the test data for the following fields to create new testData for auditing 
		| Name | Version | Endpoint Type | Endpoint Firmware:Release Date |
	When click on "Save Endpoint Firmware" tab/button from Audit page 
	Then user should be navigated to endpoint firware page with a label saying "Endpoint Firmware auditPageEndpointFirmwareBot saved." for "saving" for auditing 
	
	#Test data for @Site and Tenant Audit,
	Then delete the "Site" data used as test data for audit page 
		| auditPage_siteBot |
	Then delete the "Tenant" data used as test data for audit page 
		| auditPage_tenantBot |
	Then click on "Tenant" tab/button from Audit page 
	Then click on "Add Tenant" tab/button from Audit page 
	Then enter the test data for the following fields to create new testData for auditing 
		| Tenant Name | Tenant Parent | Tenant Type |
	Then click on "Save Tenant" tab/button from Audit page 
	Then user should navigate to Tenant page with message "Tenant auditPage_tenantBot saved." 
	Then click on "Site" tab/button from Audit page 
	Then click on "Add Site" tab/button from Audit page 
	Then enter the test data for the following fields to create new testData for auditing 
		| Tenant Dropdown | Site ID  | Site Description | Address1 | City | State | Postal Code | Country | Phone 1 | Commission Date | Area | Path | 
	Then select the following check boxes 
		| Has Control | Active |
	Then click on "Save" button of add site popup for auditing 
	When click on "Accept Verified Data" button of add site popup for auditing 
	Then click on "Save" button of add site popup for auditing 
	Then user should navigate to Site page with message "Site auditPage_siteBot saved." 
	
	#Test data for @Endpoint Audit,
	Then delete the "Endpoint" data used as test data for audit page 
		| auditPage_EndpointBot |
	Then click on "Endpoint" tab/button from Audit page 
	When click on "Add Endpoint" tab/button from Audit page 
	Then enter the test data for the following fields to create new testData for auditing 
		| Serial | Type |
	When click on "Save Endpoint" tab/button from Audit page 
	Then user should navigate to endpoint page with message "Endpoint auditPage_EndpointBot saved." 
	
	#Test data for @User Audit,
	Then delete the "Users" data used as test data for audit page 
		| auditPage_EndpointBot |
	Then click on "Users" tab/button from Audit page 
	When click on "Add Users" tab/button from Audit page 
	Then enter the test data for the following fields to create new testData for auditing 
		| Username | First Name | Last Name | Email | Locale | Unit of Temperature | Role |
	When click on "Save User" tab/button from Audit page 
	Then user is navigated on Edit User Page with the created user 
	Then click on "Users:X" tab/button from Audit page 
	

# TODO : this scenario is later commented. validate (Scenario is valid need to run)
@AdminUI_AuditSearch 
Scenario: Scenario_05: Verify the search functionality of Audit page. 
	Given User is already logged in to Admin URL and is already present at the AuditPage Tab for "Scenario_05" 
	Given we are on Audit page and search audit field is enabled and date picker is visible 
	
	Then verify the search output by entering "auditPage_siteBot:Init3Char" in audit search field 
	Then verify the search output by entering ":RANDOM" in audit search field 
	Then verify the search output by entering "auditPage_siteBot1:BACKSPACE" in audit search field 
	Then verify the search output by entering "auditPage_siteBot1:DELETE" in audit search field 
	Then verify the pagination by entering value "auditPage_siteBot1:Pagination" in audit search field 
	Then verify the search output by entering ":LONGTEXT" in audit search field 
	Then click on "Date Picker" tab/button from Audit page 
	Then enter start date and end date and click apply button 
		| startDate  |  endDate |
		| 04/01/2018 |04/10/2018| 
	Then verify the search output by entering "sit:DateStamp" in audit search field 
	
 
# TODO : this scenario is later commented. validate (Scenario is valid need to run)
@AdminUI_AuditSiteIDUpdatation 
Scenario: Scenario_06: Verify audit trail for site creation on Audit page 
	Given User is already logged in to Admin URL and is already present at the AuditPage Tab for "Scenario_06" 
	Given we are on Audit page and search audit field is enabled and date picker is visible 
	When enter site name "auditPage_siteBot" in audit search field 
	Then click on "Audit:Search" tab/button from Audit page 
	Then select the audit record "auditPage_siteBot" from the audit details grid 
	Then user should navigate to "Edit Site" page 
	Then verify that the "New" test data details should be displayed in the following columns 
		| Site ID | Site Description | Address | City | Postal Code |
	And modify "auditPage_siteBot" name to "auditPage_siteBot1" 
	Then click on "Save And Close" button of add site popup for auditing 
	Then user should navigate to Site page with message "Site auditPage_siteBot1 saved." 
	Then click on "Audit" tab/button from Audit page 
	
	Then change the time of date picker on audit details grid 
	When enter site name "auditPage_siteBot1" in audit search field 
	Then click on "Audit:Search" tab/button from Audit page 
	Then verify that the "Updated" test data details should be displayed in the following columns 
		| Site ID | Site Description | Address | City | Postal Code |
	Then select the audit record "auditPage_siteBot1" from the audit details grid 
	Then user should navigate to "Edit Site" page 
	And modify "auditPage_siteBot1" name to "auditPage_siteBot" 
	Then click on "Save And Close" button of add site popup for auditing 
	Then user should navigate to Site page with message "Site auditPage_siteBot saved." 
	

# TODO : this scenario is later commented. validate (Scenario is valid need to run)
@AdminUI_EndpointFirmwareAudit 
Scenario: Scenario_08 Verify audit details for Endpoint Firmware. 
	Given User is already logged in to Admin URL and is already present at the AuditPage Tab for "Scenario_08" 
	Given we are on Audit page and search audit field is enabled and date picker is visible 
	When enter firmware name "auditPageEndpointFirmwareBot" in audit search field 
	Then click on "Audit:Search" tab/button from Audit page 
	Then select the audit record "auditPageEndpointFirmwareBot" from the audit details grid 
	Then verify that the "New" test data details should be displayed in the following columns 
		| Endpoint Firmware:Name | Endpoint Firmware:Veverify user should be navigated to sites page with a label sayingrsion | Endpoint Firmware:Release Date |
		
	Then modify release date field and name field for the selected endpoint firmware for Auditing 
		| Name | Version | Release Date |
	Then click on "Save Edit Endpoint Firmware" tab/button from Audit page 
	Then user should be navigated to endpoint firware page with a label saying "Endpoint Firmware auditPageEndpointFirmwareBot1 saved." for "saving" for auditing 
	Then click on "Audit" tab/button from Audit page 
	Then change the time of date picker on audit details grid 
	When enter firmware name "auditPageEndpointFirmwareBot1" in audit search field 
	Then click on "Audit:Search" tab/button from Audit page 
	Then select the audit record "auditPageEndpointFirmwareBot1" from the audit details grid 
	Then user should navigate to "Edit Endpoint Firmware" page for auditing 
	Then verify that the "Updated" test data details should be displayed in the following columns 
		| Endpoint Firmware:Name | Endpoint Firmware:Version | Endpoint Firmware:Release Date |
	Then click on "Delete Firmware" tab/button from Audit page 
	Then confirmation box on edit endpoint firmware popup should displayed with message "Are you sure you want to delete this firmware version?" for auditing 
	Then click on "Endpoint Firmware:Delete_Ok" tab/button from Audit page 
	Then click on "Audit" tab/button from Audit page 
	Then change the time of date picker on audit details grid 
	When enter firmware name "auditPageEndpointFirmwareBot1" in audit search field 
	Then click on "Audit:Search" tab/button from Audit page 
	And it shows a confirmation popup with a message "The Firmware true has already been deleted."
	

## TODO : compare below and above commented scenarios  (Should be deleted after successful run of above scenario because of duplicate)
@EndpointFirmwareAudit
Scenario: Scenario_08 Verify audit details for Endpoint Firmware.
	Given we are on Audit page and search audit field is enabled and date picker is visible
	Then "delete" the data used as test data for audit page
		| auditPageEndpointFirmwareBot11 | auditPageEndpointFirmwareBot1 |
	Then click on "Firmware" tab from Audit page
   	Then click on "Endpoint Firmware" tab from Audit page
   	When click on "Add Endpoint Firmware" button on Endpoint firmware page for Auditing 
    Then click on the "Choose File" button on add endpoint firmware popup and select "Valid" file for auditing
    Then add endpoint firmware popup should be displayed with following fields for auditing: 
        | File | Name | Version | Endpoint Type | Release Date |
    Then enter the test data for the following fields to create new endpoint firware for auditing 
        | Name | Version | Endpoint Type | Release Date |
    When click on "Save Firmware" button on Endpoint firmware page for Auditing 
    Then user should be navigated to endpoint firware page with a label saying "Endpoint Firmware auditPageEndpointFirmwareBot1 saved." for "saving" for auditing 
    Then click on the "Audit" tab  
 	When enter firmware name "auditPageEndpointFirmwareBot1" in audit search field     
	Then click on "Search" button on Audit page
	Then select the audit record "auditPageEndpointFirmwareBot1" from the audit details grid 
 	Then user should navigate to "Edit Endpoint Firmware" page for auditing  
 	Then verify that the newly created firmware details should be displayed in the following columns
		| Name | Version  | Release Date |
	Then modify release date field and name field for the selected endpoint firmware for Auditing
    	| Release Date | Name |
  	When click on "Save Edit Firmware" button on Endpoint firmware page for Auditing 
    Then user should be navigated to endpoint firware page with a label saying "Endpoint Firmware auditPageEndpointFirmwareBot11 saved." for "saving" for auditing 
   	Then click on the "Audit" tab  
 	When enter firmware name "auditPageEndpointFirmwareBot11" in audit search field     
	Then click on "Search" button on Audit page
	Then select the audit record "auditPageEndpointFirmwareBot11" from the audit details grid 
 	Then user should navigate to "Edit Endpoint Firmware" page for auditing  
 	Then click on "Delete Edit Firmware" button on Endpoint firmware page for Auditing
 	Then confirmation box on edit endpoint firmware popup should displayed with message "Are you sure you want to delete this firmware version?" for auditing
	When click on "Ok" button on delet confirmation box on Endpoint firmware Page 
	Then user should be navigated to endpoint firware page with a label saying "Endpoint Firmware auditPageEndpointFirmwareBot11 deleted." for "deletion" for auditing 
   	Then click on the "Audit" tab  
 	When enter firmware name "auditPageEndpointFirmwareBot11" in audit search field     
	Then click on "Search" button on Audit page
	Then select the audit record "auditPageEndpointFirmwareBot11" from the audit details grid
 #	Then confirmation box is displayed for deleted firmware
 	
 
## TODO : this scenario is later commented. validate (Scenario is valid need to run)
@AdminUI_ExportIconAudit 
Scenario: Scenario_07 Verify the functionality of Export icon. 
	Given User is already logged in to Admin URL and is already present at the AuditPage Tab for "Scenario_08" 
	Given we are on Audit page and search audit field is enabled and date picker is visible 
	Then verify Export icon is display on Audit tab 
	Then click on Export icon on Audit tab 
	Then open the downloaded report for audit tab
	When enter site name "auditPage_siteBot11" in audit search field 
	Then click on Export icon on Audit tab 
	Then open the downloaded report for audit tab 
	Then click on "Date Picker" tab/button from Audit page 
	Then enter start date and end date and click apply button 
		| startDate  |  endDate |
		| 04/01/2018 |04/10/2018| 
	Then click on Export icon on Audit tab 
	Then open the downloaded report for audit tab
		


## TODO : this scenario is later commented. validate (Scenario is valid need to run)
@AdminUI_DeleteScenario 
Scenario: Scenario_09: Delete the test date used in audit tab 
	Given User is already logged in to Admin URL and is already present at the AuditPage Tab for "Scenario_09" 
	Given we are on Audit page and search audit field is enabled and date picker is visible 
	Then delete the "Endpoint Firmware" data used as test data for audit page 
		| auditPageEndpointFirmwareBot |
	Then delete the "Site" data used as test data for audit page 
		| auditPage_siteBot |
	Then delete the "Tenant" data used as test data for audit page 
		| auditPage_tenantBot |
	Then delete the "Endpoint" data used as test data for audit page 
		| auditPage_EndpointBot |
	Then delete the "Users" data used as test data for audit page 
		| auditPage_EndpointBot |
	