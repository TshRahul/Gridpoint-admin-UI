@AdminUI_FirmwarePage 
Feature: Firmware Page test 
	Here we will test the firmware Page functionality.
	For this, we will login with admin credentials and will test
	the different scenarios including behavior and function test of the peripheral firmware page and endpoint firmware page.
 
@AdminUI_PeripheralFirmware 
Scenario:
Scenario_01: Verify visibility of Firmware tab and then verify visibility of Peripheral Firmware Page 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_01" 
	Given we are on "Peripheral Firmware" page and search field is enabled and visible 
	Then verify the visibility "peripheral firmware" page by confirming the availability 
	
@AdminUI_PeripheralFirmwareSorting 
Scenario:
Scenario_02: Verify the column names and sorting functionality of peripheral firmware detail grid on Peripheral firmware page 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_02" 
	Given we are on "Peripheral Firmware" page and search field is enabled and visible 
	Then verify the following columns in "Peripheral Firmware" details grid 
		| Release Date | Active | Version | Peripheral Type | Peripheral Type ID | Controller Min Version |
	Then verify the sorting functionality of columns in "Peripheral Firmware" details grid 
		| Peripheral Firmware:Release Date | Peripheral Firmware:Active | Peripheral Firmware:Version | Peripheral Firmware:Peripheral Type | Peripheral Firmware:Peripheral Type ID | Peripheral Firmware:Controller Min Version |
		
@AdminUI_PeripheralFirmwarePagination 
Scenario:
Scenario_03: Verify the pagination functionality on peripheral firmware detail grid. 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_03" 
	Given we are on "Peripheral Firmware" page and search field is enabled and visible 
	When click on "next" link in "Peripheral Firmware" grid 
	Then "Next" page should displayed in "Peripheral Firmware" grid 
	When click on "prev" link in "Peripheral Firmware" grid 
	When click on "last" link in "Peripheral Firmware" grid 
	Then "Last" page should displayed in "Peripheral Firmware" grid 
	When click on "prev" link in "Peripheral Firmware" grid 
	Then "Previous" page should displayed in "Peripheral Firmware" grid 
	When click on "next" link in "Peripheral Firmware" grid 
	When click on "first" link in "Peripheral Firmware" grid 
	Then "First" page should displayed in "Peripheral Firmware" grid 
	When enter random page number in page link text in firmware detail grid 
	Then user should be navigated on the given random page in "Peripheral Firmware" detail grid 
	
@AdminUI_PeripheralFirmwareResoures 
Scenario: Scenario_04: Create Resoures for Periperal Firmware Page 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_04" 
	Given we are on "Peripheral Firmware" page and search field is enabled and visible 
	
	#Test data for @PeripheralFirmware Firmware Tab,	
	Then delete the "Peripheral Firmware" data used as test data for firmware page 
		| TS200 |
	Then click on "Firmware" tab/button from Firmware page 
	Then click on "Peripheral Firmware" tab/button from Firmware page 
	When click on "Add Peripheral Firmware" tab/button from Firmware page 
	Then add Firmware popup should be displayed with following fields: 
		| Peripheral Firmware:File | Peripheral Firmware:Peripheral Type | Peripheral Firmware:Release Date | Peripheral Firmware:Version Major | Peripheral Firmware:Version Minor | Peripheral Firmware:Controller Version Major | Peripheral Firmware:Controller Version Minor | Peripheral Firmware:Controller Version Revision | Peripheral Firmware:Apply Button | Peripheral Firmware:Close Button |
	When click on "Save Peripheral Firmware" tab/button from Firmware page 
	And verify the validation messages for following fields 
		| Field Name | Error Message |
		| File | No file chosen |
		| Peripheral Firmware:Release Date | Release date is required. |
		| Version Major | Version major number required |
		| Version Minor | Version minor number required |
		| Controller Version Major | Controller major version number required |
		| Controller Version Minor | Controller minor version number required |
		| Controller Version Revision | Controller revision version number required |
	Then click on the "Choose File" button on add "Peripheral Firmware" popup and select "Invalid" file 
	When click on "Save Peripheral Firmware" tab/button from Firmware page 
	And verify the message "Invalid file format. Please upload a .bin file" will display when peripheral firmware file with invalid format is uploaded 
	When click on "Close Add Peripheral Firmware" tab/button from Firmware page 
	Then user should be navigated on peripheral firmware detail grid 
	When click on "Add Peripheral Firmware" tab/button from Firmware page 
	Then add Firmware popup should be displayed with following fields: 
		| Peripheral Firmware:File | Peripheral Firmware:Peripheral Type | Peripheral Firmware:Release Date | Peripheral Firmware:Version Major | Peripheral Firmware:Version Minor | Peripheral Firmware:Controller Version Major | Peripheral Firmware:Controller Version Minor | Peripheral Firmware:Controller Version Revision | Peripheral Firmware:Apply Button | Peripheral Firmware:Close Button |
	When click on "Add Peripheral Firmware:X" tab/button from Firmware page 
	Then user should be navigated on peripheral firmware detail grid 
	When click on "Add Peripheral Firmware" tab/button from Firmware page 
	Then add Firmware popup should be displayed with following fields: 
		| Peripheral Firmware:File | Peripheral Firmware:Peripheral Type | Peripheral Firmware:Release Date | Peripheral Firmware:Version Major | Peripheral Firmware:Version Minor | Peripheral Firmware:Controller Version Major | Peripheral Firmware:Controller Version Minor | Peripheral Firmware:Controller Version Revision | Peripheral Firmware:Apply Button | Peripheral Firmware:Close Button |
	Then click on the "Choose File" button on add "Peripheral Firmware" popup and select "Valid" file 
	Then enter the test data for the following fields to create new firmware 
		| Peripheral Firmware:Peripheral Type | Peripheral Firmware:Release Date | Peripheral Firmware:Version Major | Peripheral Firmware:Version Minor | Peripheral Firmware:Controller Version Major | Peripheral Firmware:Controller Version Minor | Peripheral Firmware:Controller Version Revision | Peripheral Firmware:Apply Button | Peripheral Firmware:Close Button |
	When click on "Save Peripheral Firmware" tab/button from Firmware page 
	Then user is navigated on "Peripheral Firmware" with a label saying "Peripheral Firmware TS200 saved." 
	When click on "Add Peripheral Firmware" tab/button from Firmware page 
	Then click on the "Choose File" button on add "Peripheral Firmware" popup and select "Valid" file 
	Then enter the test data for the following fields to create new firmware 
		| Peripheral Firmware:Peripheral Type | Peripheral Firmware:Release Date | Peripheral Firmware:Version Major | Peripheral Firmware:Version Minor | Peripheral Firmware:Controller Version Major | Peripheral Firmware:Controller Version Minor | Peripheral Firmware:Controller Version Revision | Peripheral Firmware:Apply Button | Peripheral Firmware:Close Button |
	When click on "Save Peripheral Firmware" tab/button from Firmware page 
	Then verify that error message will be displayed a "Peripheral Firmware could not be uploaded: errorMessage: could not execute batch; nested exception is org.hibernate.exception.GenericJDBCException: could not execute batch" 
	When click on "Close Add Peripheral Firmware" tab/button from Firmware page 
	Then user should be navigated on peripheral firmware detail grid 
	
@AdminUI_PeripheralFirmwareSearch 
Scenario:
Scenario_05: Verify the search functionality of Peripheral firmware page. 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_05" 
	Given we are on "Peripheral Firmware" page and search field is enabled and visible 
	
	Then verify the search output by entering "-123.-123.-123:Init3Char" in peripheral firmware search field 
	Then verify the search output by entering ":RANDOM" in peripheral firmware search field 
	Then verify the search output by entering "-123.-123.-123:BACKSPACE" in peripheral firmware search field 
	Then verify the search output by entering "-123.-123.-123:DELETE" in peripheral firmware search field 
	Then verify the pagination by entering value "-123.-123.-123:Pagination" in peripheral firmware search field 
	Then verify the search output by entering ":LONGTEXT" in peripheral firmware search field 
	
@AdminUI_PeripheralFirmwareDetailPopup 
Scenario:
Scenario_06: Verify detail accordion functionality on Peripheral firmware page 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_06" 
	Given we are on "Peripheral Firmware" page and search field is enabled and visible 
	
	When enter test data "-123.-123.-123" in "Peripheral Firmware" search field 
	When click on "Peripheral Firmware:Search" tab/button from Firmware page 
	Then select the firmware "-123.-123.-123" from the "Peripheral Firmware" details grid 
	And verify the "Detail Peripheral Firmware" page is being displayed 
	Then verify the details of the selected firmware in following elements on firmware detail popup 
		| Edit Peripheral Firmware:Peripheral Type | Edit Peripheral Firmware:Peripheral Type ID | Edit Peripheral Firmware:Release Date | Edit Peripheral Firmware:Version | Edit Peripheral Firmware:Min Controller Version | Edit Peripheral Firmware:Close | Edit Peripheral Firmware:Delete |
	When click on "Close Edit Peripheral Firmware" tab/button from Firmware page 
	Then user should be navigated on peripheral firmware detail grid 
	
@AdminUI_PeripheralFirmwareDelete 
Scenario:
Scenario_07: Verify delete firmware functionlaity on Peripheral firmware page 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_07" 
	Given we are on "Peripheral Firmware" page and search field is enabled and visible 
	
	When enter test data "-123.-123.-123" in "Peripheral Firmware" search field 
	When click on "Peripheral Firmware:Search" tab/button from Firmware page 
	Then select the firmware "-123.-123.-123" from the "Peripheral Firmware" details grid 
	Then verify the details of the selected firmware in following elements on firmware detail popup 
		| Edit Peripheral Firmware:Close | Edit Peripheral Firmware:Delete |
	When click on "Close Edit Peripheral Firmware" tab/button from Firmware page 
	Then select the firmware "-123.-123.-123" from the "Peripheral Firmware" details grid 
	Then verify the details of the selected firmware in following elements on firmware detail popup 
		| Edit Peripheral Firmware:Close | Edit Peripheral Firmware:Delete |
	When click on "Delete Peripheral Firmware" tab/button from Firmware page 
	Then confirmation box on detail peripheral firmware popup should displayed with message "Are you sure you want to delete the peripheral?" 
	When click on "Cancel Delete Peripheral Firmware" tab/button from Firmware page 
	Then user should remain on "Detail Peripheral Firmware" detail popup 
	When click on "Delete Peripheral Firmware" tab/button from Firmware page 
	Then confirmation box on detail peripheral firmware popup should displayed with message "Are you sure you want to delete the peripheral?" 
	When click on "Peripheral Firmware:Delete_Ok" tab/button from Firmware page 
	Then user is navigated on "Peripheral Firmware" with a label saying "Peripheral Firmware TS200deleted ." 
	
@AdminUI_ExportIconPeripheralFirmware 
Scenario: Scenario_08 Verify the functionality of Export icon. 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_08" 
	Given we are on "Peripheral Firmware" page and search field is enabled and visible 
	Then verify Export icon is display on Firmware tab 
	#	Then click on Export icon on PeripheralFirmware tab
	#	Then open the downloaded report for peripheral firmware
	Then select the firmware "-123.-123.-123" from the "Peripheral Firmware" details grid 
	When click on "Peripheral Firmware:Search" tab/button from Firmware page 
	#   Then click on Export icon on EndpointFirmware tab
	#	Then open the downloaded report for peripheral firmware
	
@AdminUI_EndpointFirmware 
Scenario:
Scenario_09: Verify visibility of Firmware tab and then verify visibility of Peripheral Firmware Page 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_09" 
	Given we are on "Endpoint Firmware" page and search field is enabled and visible 
	Then verify the visibility "Endpoint firmware" page by confirming the availability 
	
@AdminUI_EndpointFirmwareSorting 
Scenario:
Scenario_10: Verify the column names and sorting functionality of peripheral firmware detail grid on Peripheral firmware page 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_10" 
	Given we are on "Endpoint Firmware" page and search field is enabled and visible 
	Then verify the following columns in "Endpoint Firmware" details grid 
		| Name | Version | Endpoint type | Release date | Active |
	Then verify the sorting functionality of columns in "Peripheral Firmware" details grid 
		| Endpoint Firmware:Name | Endpoint Firmware:Version | Endpoint Firmware:Endpoint type | Endpoint Firmware:Release date | Endpoint Firmware:Active |
		
@AdminUI_EndpointFirmwarePagination 
Scenario:
Scenario_11: Verify the pagination functionality on peripheral firmware detail grid. 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_11" 
	Given we are on "Endpoint Firmware" page and search field is enabled and visible 
	When click on "next" link in "Endpoint Firmware" grid 
	Then "Next" page should displayed in "Endpoint Firmware" grid 
	When click on "prev" link in "Endpoint Firmware" grid 
	When click on "last" link in "Endpoint Firmware" grid 
	Then "Last" page should displayed in "Endpoint Firmware" grid 
	When click on "prev" link in "Endpoint Firmware" grid 
	Then "Previous" page should displayed in "Endpoint Firmware" grid 
	When click on "next" link in "Endpoint Firmware" grid 
	When click on "first" link in "Endpoint Firmware" grid 
	Then "First" page should displayed in "Endpoint Firmware" grid 
	When enter random page number in page link text in firmware detail grid 
	Then user should be navigated on the given random page in "Endpoint Firmware" detail grid 
	
	
@AdminUI_EndpointFirmwareResoures 
Scenario: Scenario_12: Create Resoures for Periperal Firmware Page 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_12" 
	Given we are on "Endpoint Firmware" page and search field is enabled and visible 
	
	#Test data for @PeripheralFirmware Firmware Tab,	
	Then delete the "Endpoint Firmware" data used as test data for firmware page 
		| endpointFirmwareEFBot |
	Then click on "Firmware" tab/button from Firmware page 
	Then click on "Endpoint Firmware" tab/button from Firmware page 
	When click on "Add Endpoint Firmware" tab/button from Firmware page 
	Then add Firmware popup should be displayed with following fields: 
		| Endpoint Firmware:File | Endpoint Firmware:Name | Endpoint Firmware:Version | Endpoint Firmware:Endpoint Type | Endpoint Firmware:Release Date | Endpoint Firmware:Save Button | Endpoint Firmware:Cancel Button |
	When click on "Save Endpoint Firmware" tab/button from Firmware page 
	And verify the validation messages "No file chosen" for "File" field on add Endpoint Firmware 
	Then click on the "Choose File" button on add "Endpoint Firmware" popup and select "Valid" file 
	When click on "Save Endpoint Firmware" tab/button from Firmware page 
	And verify the validation messages "This field is required and must be letters, numbers, and periods only." for "Name" field on add Endpoint Firmware 
	Then enter the test data for the following fields to create new firmware 
		| Endpoint Firmware:Name |
	When click on "Save Endpoint Firmware" tab/button from Firmware page 
	And verify the validation messages "This field must not be empty." for "Version" field on add Endpoint Firmware 
	Then click on the "Choose File" button on add "Endpoint Firmware" popup and select "InValid" file 
	When click on "Save Endpoint Firmware" tab/button from Firmware page 
	And verify the message "The firmware filename must match the pattern/^(live_firmware.*.tar.gz)$/" will display when wrong endpoint firmware file is uploaded 
	When click on "Cancel Endpoint Firmware" tab/button from Firmware page 
	Then confirmation box on "Add Endpoint Firmware" popup should displayed with message "All Changes will be lost. Are you sure?" 
	When click on "Add Endpoint Firmware:Cancel_Cancel" tab/button from Firmware page 
	Then user should remain on "Add Endpoint Firmware" detail popup 
	When click on "Cancel Endpoint Firmware" tab/button from Firmware page 
	Then confirmation box on "Add Endpoint Firmware" popup should displayed with message "All Changes will be lost. Are you sure?" 
	When click on "Add Endpoint Firmware:OK_Cancel" tab/button from Firmware page 
	Then user should be navigated on endpoint firmware detail grid 
	When click on "Add Endpoint Firmware" tab/button from Firmware page 
	Then click on the "Choose File" button on add "Endpoint Firmware" popup and select "Valid" file 
	Then enter the test data for the following fields to create new firmware 
		| Endpoint Firmware:Name | Endpoint Firmware:Version | Endpoint Firmware:Endpoint Type | Endpoint Firmware:Release Date |
	When click on "Save Endpoint Firmware" tab/button from Firmware page 
	Then user is navigated on "Endpoint Firmware" with a label saying "Endpoint Firmware endpointFirmwarePageFirmwareBot1 saved." 
	
@AdminUI_EndpointFirmwareSearch 
Scenario:
Scenario_13: Verify the search functionality of Peripheral firmware page. 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_13" 
	Given we are on "Endpoint Firmware" page and search field is enabled and visible 
	
	Then verify the search output by entering "endpointFirmwareEFBot:Init3Char" in endpoint firmware search field on endpoint firmware page 
	Then verify the search output by entering ":RANDOM" in endpoint firmware search field on endpoint firmware page 
	Then verify the search output by entering "endpointFirmwareEFBot:BACKSPACE" in endpoint firmware search field on endpoint firmware page 
	Then verify the search output by entering "endpointFirmwareEFBot:DELETE" in endpoint firmware search field on endpoint firmware page 
	Then verify the pagination by entering value "endpointFirmwareEFBot:Pagination" in endpoint firmware search field on endpoint firmware page 
	Then verify the search output by entering ":LONGTEXT" in endpoint firmware search field on endpoint firmware page 
	
@AdminUI_@EndpointFirmwareDetailAccordion @T1 
Scenario:
Scenario_14: Verify detail accordion functionality on Endpoint firmware page 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_14" 
	Given we are on "Endpoint Firmware" page and search field is enabled and visible 
	
	When enter test data "endpointFirmwareEFBot" in "Endpoint Firmware" search field 
	When click on "Endpoint Firmware:Search" tab/button from Firmware page 
	Then select the firmware "endpointFirmwareEFBot" from the "Endpoint Firmware" details grid 
	Then verify the details of the selected firmware in following elements on firmware detail popup 
		| Edit Endpoint Firmware:Name | Edit Endpoint Firmware:Version | Edit Endpoint Firmware:Endpoint Type | Edit Endpoint Firmware:Release Date | Edit Endpoint Firmware:Active | Edit Endpoint Firmware:Delete Button | Edit Endpoint Firmware:Save Button | Edit Endpoint Firmware:Cancel Button |
	Then enter the invalid test data for the following fields to create new firmware 
		| Endpoint Firmware:Name |
	When click on "Edit Endpoint Firmware:Save" tab/button from Firmware page 
	And verify the validation messages "This field is required and must be letters, numbers, and periods only." for "Name" field on add Endpoint Firmware 
	When click on "Edit Endpoint Firmware:Cancel" tab/button from Firmware page 
	Then confirmation box on "Edit Endpoint Firmware" popup should displayed with message "All changes will be lost. Are you sure?" 
	When click on "Add Endpoint Firmware:Cancel_Cancel" tab/button from Firmware page 
	Then user should remain on "Edit Endpoint Firmware" detail popup 
	When click on "Edit Endpoint Firmware:Cancel" tab/button from Firmware page 
	Then confirmation box on "Edit Endpoint Firmware" popup should displayed with message "All changes will be lost. Are you sure?" 
	When click on "Add Endpoint Firmware:Ok_Cancel" tab/button from Firmware page 
	Then user should be navigated on endpoint firmware detail grid 
	When enter test data "endpointFirmwareEFBot" in "Endpoint Firmware" search field 
	When click on "Endpoint Firmware:Search" tab/button from Firmware page 
	Then modify release date field and version field for the selected endpoint firmware on edit endpoint fimrware
		| Version | Release Date |
	When click on "Save Endpoint Firmware" tab/button from Firmware page 
	Then user is navigated on "Endpoint Firmware" with a label saying "Endpoint Firmware endpointFirmwarePageFirmwareBot1 saved." 
	
@AdminUI_@EndpointFirmwareAuditAccordion @T1
Scenario: Scenario_15: Verify audit accordion functionality on Endpoint firmware page 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_15" 
	Given we are on "Endpoint Firmware" page and search field is enabled and visible

	When enter test data "endpointFirmwareEFBot" in "Endpoint Firmware" search field 
	When click on "Endpoint Firmware:Search" tab/button from Firmware page 
	Then select the firmware "endpointFirmwareEFBot" from the "Endpoint Firmware" details grid 
	Then verify the audit details grid is available inside "Audit" accordion in edit endpoint firmware
    Then verify the following columns in "Endpoint Firmware:Audit" details grid
        | Property | Action | Original Value | Updated value | User | Date Stamp |
    Then verify the sorting functionality of columns in "Endpoint Firmware" details grid 
		| AuditGrid:Property | AuditGrid:Action | AuditGrid:Original Value | AuditGrid:Updated value | AuditGrid:User | AuditGrid:Date Stamp |
    Then verify that newly created "endpointFirmwareEFBot" logs are present in "Audit" details grid 
    And verify that pagination buttons are available in "Audit" details grid 	
    
#GPUP-31870:Bulk for Firmware Upgrade
@AdminUI_@UpdateEndpointFirmwareUIVerification 
Scenario: Scenario_16: Verify GPAdmin and GPSupport users can see the update endpoint firmware sub tab.
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_16" 
	Given we are on "Update Endpoint Firmware" page and search field is enabled and visible
	Then verify the visibility "Update Endpoint Firmware" page by confirming the availability  
	Then verify the following columns in "Update Endpoint Firmware" details grid 
		| Select | Endpoint ID | Tenant | Premises | Connection Status | Type | Serial | Live Firmware | Last Message Received (UTC) |
	Then verify the sorting functionality of columns in "Update Endpoint Firmware" details grid
	  | UEF:Endpoint ID | UEF:Tenant | UEF:Premises | UEF:Connection Status | UEF:Type | UEF:Serial | UEF:Live Firmware | UEF:Last Message Received (UTC) |      
    
@UpdateEndpointFirmwareSearch @T1
Scenario: Scenario_17: Verify the search functionality of Uudate Endpoint firmware page.
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_17" 
	Given we are on "Update Endpoint Firmware" page and search field is enabled and visible 
	
	Then verify the search output by entering "GridPoint Test Automation:Init3Char" in update endpoint firmware search field
	Then verify the search output by entering ":RANDOM" in update endpoint firmware search field
	Then verify the search output by entering "GridPoint Test Automation:BACKSPACE" in update endpoint firmware search field
	Then verify the search output by entering "GridPoint Test Automation:DELETE" in update endpoint firmware search field
	Then verify the search output by entering "GridPoint Test Automation:Pagination" in update endpoint firmware search field
	Then verify the search output by entering ":LONGTEXT" in update endpoint firmware search field
	
@DeleteEndpointFirmware 
Scenario: Scenario_10: Verify the delete EndpointFirmware functionality. 
	Given User is already logged in to Admin URL and is already present at the Firmware Tab for "Scenario_17" 
	Given we are on "Update Endpoint Firmware" page and search field is enabled and visible 
	 
	When enter test data "endpointFirmwareEFBot" in "Endpoint Firmware" search field 
	When click on "Endpoint Firmware:Search" tab/button from Firmware page 
	Then select the firmware "endpointFirmwareEFBot" from the "Endpoint Firmware" details grid 
    When click on "Delete Endpoint Firmware" tab/button from Firmware page 
    Then confirmation box on edit endpoint firmware popup should displayed with message "Are you sure you want to delete this firmware version?" 
    When click on "Delete Endpoint Firmware:Cancel" tab/button from Firmware page
    Then user should remain on "Edit Endpoint Firmware" detail popup
    Then click on "Delete" button on edit endpoint firmware popup 
    Then confirmation box on edit endpoint firmware popup should displayed with message "Are you sure you want to delete this firmware version?" 
    When click on "Delete Endpoint Firmware:Ok" tab/button from Firmware page
    Then user is navigated on "Endpoint Firmware" with a label saying "Endpoint Firmware endpointFirmwareEFBot deleted. saved." 
    
    
  # TODO : below scenario is missing : 1/23/2018 5:12:34 PM
  
  @EndpointFirmwareCreation 
Scenario: Scenario_03: Verify endpoint firmware creation, deleation, duplicate on Endpoint firmware page 
	Given we will land on sites tab, we will click on firmware tab
	When we will click on firmware tab, we will click on endpoint firmware tab
    When we are on Endpoint firmware page and search endpoint firmware field is enabled
    When verify that "endpointFirmwarePageFirmwareBot1" exist or not
    Then click on "Add Endpoint Firmware" button on Endpoint firmware page 
    Then add endpoint firmware popup should be displayed with following fields:
        | File | Name | Version | Endpoint Type | Release Date |
    Then verify following button are enabled on add endpoint firmware popup 
        | Save | Cancel |
    Then click on the "Save" button on add endpoint firmware popup 
    And verify the validation messages "No file chosen" for "File" field on add Endpoint Firmware popup 
    Then click on the "Choose File" button on add endpoint firmware popup and select "Valid" file 
    Then click on the "Save" button on add endpoint firmware popup 
    And verify the validation messages "This field is required and must be letters, numbers, and periods only." for "Name" field on add Endpoint Firmware popup 
    Then enter valid value in "Name" field 
    Then click on the "Save" button on add endpoint firmware popup 
    And verify the validation messages "This field must not be empty." for "Version" field on add Endpoint Firmware popup 
    Then click on the "Choose File" button on add endpoint firmware popup and select "Invalid" file 
    Then click on the "Save" button on add endpoint firmware popup 
    And verify the message "The firmware filename must match the pattern/^(live_firmware.*.tar.gz)$/" will display when endpoint firmware file with wrong name is uploaded 
    When click on "Cancel" button on add endpoint firmware popup 
    Then confirmation box on endpoint firmware popup should displayed with message "All Changes will be lost. Are you sure?" 
    When click on "Cancel" button on confirmation box on endpoint firmware 
    Then user should remain on add endpoint firmware popup 
    When click on "Cancel" button on add endpoint firmware popup 
    Then confirmation box on endpoint firmware should displayed with message "All Changes will be lost. Are you sure?" 
    When click on "Ok" button on confirmation box on endpoint firmware 
    Then user should be navigated on endpoint firmware detail grid 
    When enter friamware name "endpointFirmwarePageFirmwareBot1" in endpoint firmware search field
    Then click on "Search" button on Endpoint firmware page   
    Then select the endpoint firmware "endpointFirmwarePageFirmwareBot1" from the endpoint firmware details grid 
    Then click on "Delete" button on edit endpoint firmware popup 
    Then confirmation box on edit endpoint firmware popup should displayed with message "Are you sure you want to delete this firmware version?" 
    When click on "Cancel" button on delete confirmation box on edit endpoint firmware popup 
    Then user should remain on edit endpoint firmware popup 
    When click on "Delete" button on edit endpoint firmware popup 
    Then confirmation box on edit endpoint firmware popup should displayed with message "Are you sure you want to delete this firmware version?" 
    When click on "Ok" button on delete confirmation box on edit endpoint firmware popup 
    Then user should be navigated to endpoint firware page with a label saying "Endpoint Firmware endpointFirmwarePageFirmwareBot1 deleted." for "deletion" 
    When click on "Add Endpoint Firmware" button on Endpoint firmware page 
    Then click on the "Choose File" button on add endpoint firmware popup and select "Valid" file 
    Then add endpoint firmware popup should be displayed with following fields: 
        | File | Name | Version | Endpoint Type | Release Date |
    Then enter the test data for the following fields to create new endpoint firware 
        | Name | Version | Endpoint Type | Release Date |
    When click on "Save" button on add endpoint firware popup 
   Then user should be navigated to endpoint firware page with a label saying "Endpoint Firmware endpointFirmwarePageFirmwareBot1 saved." for "saving" 
    Then click on "Add Endpoint Firmware" button on Endpoint firmware page 
    Then click on the "Choose File" button on add endpoint firmware popup and select "Valid" file 
    Then enter the test data for the following fields to create new endpoint firware 
        | Name | Version | Endpoint Type | Release Date |
    When click on "Save" button on add endpoint firware popup 
    Then verify that error message will be displayed "The name and version for this firmware matches an existing record" 
    When click on "Cancel" button on add endpoint firmware popup 
    Then confirmation box on endpoint firmware popup should displayed with message "All Changes will be lost. Are you sure?" 
    When click on "Ok" button on confirmation box on endpoint firmware 
    Then user should be navigated on endpoint firmware detail grid 
            
	    