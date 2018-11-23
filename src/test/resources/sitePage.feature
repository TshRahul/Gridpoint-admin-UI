@SitePage 
Feature: Site Page Test 
	Here we will test the site Page functionality.
	For this, we will login with admin credentials and will test
	the different scenarios including behavior and function test of the site page.
	
Background: Check for page refresh requirement for each test scenario 
	And check if the refresh required for the portlet test scenario
	
@InitializeBrowserForAddTestData 
Scenario: 
	Scenario_initialize: Initialize the browser before execution of the test cases of Add Test Data 
	Given Initialize resources in the beginning of each scenario  
	
@AdminUI_SiteCredentials  
Scenario: Scenario_00: Login with admin credentials and verify visibility of Site tab 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_01"   
	Given we are on Site page and search site field is enabled visible
	Then validate that all the test cases of scenario for portlet executed successfully   
	
@AdminUI_SiteSorting
Scenario: Scenario_01: Login with admin credentials and navigate to Site Page 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_02"   
	Given we are on Site page and search site field is enabled visible  
	When verify the columns in "site" grid          
		| Site Name | Site Description | Address | Postal code | TimeZone | Tenant |
	Then verify following buttons on Site Grid Page
	    | Bulk Site Creation | Add Site | Export Icon |
	Then verify the following columns sorting functionality in site detail grid  
		| Site Name | Site Description | Address | Postal code | TimeZone | Tenant |
	Then verify the tenant dropdown is present on site page including the list of sites associated with the user
	Then validate that all the test cases of scenario for portlet executed successfully
		
@AdminUI_SitePagination 
Scenario: Scenario_04: Verify the pagination functionality on site detail grid. 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_03"   
	Given we are on Site page and search site field is enabled visible 
	When click on "next" link in site grid 
	Then "Next" page should displayed in site grid 
	When click on "last" link in site grid 
	Then "Last" page should displayed in site grid 
	When click on "prev" link in site grid 
	Then "Previous" page should displayed in site grid 
	When click on "first" link in site grid 
	Then "First" page should displayed in site grid 
	When enter random page number in page link text in site grid 
	Then user should be navigated on the given random page in site grid 
	Then validate that all the test cases of scenario for portlet executed successfully	
		
@AdminUI_SiteResources 
Scenario: Scenario_05: Verify the add site functionality by GPAdmin. 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_04"   
	Given we are on Site page and search site field is enabled visible
	Then verify "Hide Inactive" checkbox is checked by default  	
	
	# Create test data from UI
	Then delete the "Sites" used as test data  
		| sitePage_siteBot1 | 
	Then delete the "Tenants" used as test data
		| sitePage_tenantBot1 |
	Then click on "Tenant" tab/button from Site page 
	When click on "Add Tenant" tab/button from Site page 
	Then enter the test data for the following fields to create new testData for Site Page for tenant "First"    
	    | Tenant Name | Tenant Parent | Tenant Type | 
	Then click on "Save Tenant" tab/button from Site page 
	Then user should be navigated to tenant page with a label displaying "Tenant sitePage_tenantBot1 saved."
	
	#Verification Start 
	Then click on "Sites" tab/button from Site page 
	When click on "Add Site" tab/button from Site page 
	Then add site popup should be displayed with following fields: 
		| Tenant dropdown | Site Name | Site Description | Address1 | Address2 | Phone1 | Phone2 | City | State/Province | Postal Code | Country | commission Date | Control Date | Area | Has Control | Has Solar Panel | Active | Add Path button | Save | Cancel |
	Then verify "Active" checkbox is unchecked by default
	And verify the validation on "Phone" fields 
	And verify that "Save" buttons is disabled if any required field is not entered on Add Site Page   
	
	# Automation code for GPUP-29213
	Then user verify that "Country" is a dropdown has the following values for Site Page 
	   | UNITED_STATES | CANADA | NORTHERN_IRELAND | SCOTLAND | ENGLAND | WALES | MEXICO |  |   
	And verify that "UNITED_STATES" is selected by default for Country dropdown
    Then enter the test data for the following fields to create new testData for Site Page for Site "First"     
		| Tenant Dropdown | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country | Phone 1 | Phone 2 | Area | System Size | 
    Then select the following check boxes for Site Page  
		| Has Control | Active | Solar Panel |
	
	#Path Verification
	Then verify top level path is auto populated on the basis of tenant selection
	Then add "/botAutomationSite2" with the existing path 
	Then it shows a warning message if the new string is added "This would create a new path under ######## tenant, are you sure?" for "sitePage_tenantBot1" 
	And verify Each path must start with a forward slash "/"
	Then click on "Save" button of add site popup for Site Page 
	
	#Address Verification
	Then the Address Verification popup should be displayed with the follwoing fields: 
		| Supplied Address1 | Supplied City | Supplied State/Province | Supplied Postal Code | Supplied Country | Latitude | Longitude | Timezone | Override Checkbox | Get Cordinates | Accept Supplied Data | Accept Verified Data | Message Box |
	And verify "Latitude" field return a value 
	And verify "Longitude" field return a value 
	And verify "Time zone" field return a value 
	And verify the Message for "Valid" data is "Address is verified." 
	Then enter "Invalid" details on address verification popup 
	Then click "Get Coordinates" button on address verification model for site 
	And verify "Latitude" field return a value 
	And verify "Longitude" field return a value 
	And verify "Time zone" field return a value 
	And verify the Message for "Invalid" data is "Invalid Address." 
	Then click "Override" checkbox on address verification model for site and latitude and longitude fields become editable
	
	##GPUP-32888(AdminUI>Site: No validation for Null values for Latitude/Longitude from Edit Site page)
	Then clear the values of latitude and longitude fields
	And verify that "Accept Supplied Data" and "Accept Verified Data"  buttons are disabled
	Then enter values 0 in latitude and 0 in longitude field
	And verify that "Accept Supplied Data" button is enabled
	Then clear the values of latitude and longitude fields
	 
	Then enter values in latitude and longitude fields 
	And modify the value of Timezone field for "1" time 
	Then it shows a warning popup with a warning message "Do you still want to modify the timezone?" 
	When click on "Timezone Warning:Cancel" tab/button from Site page 
	And modify the value of Timezone field for "2" time 
	When click on "Timezone Warning:Ok" tab/button from Site page 
	And verify "Time zone" field return a modified value 
	When click on "Accept Verified Data" button of add site popup for Site Page 
	Then modify the values for "Address" fields and click on save button on "Add" site modal 
	Then enter "Valid" details on address verification popup 
	Then click "Get Coordinates" button on address verification model for site 
	And verify "Latitude" field return a value 
	And verify "Longitude" field return a value 
	And verify "Time zone" field return a value 
	And verify the Message for "Valid" data is "Address is verified." 
	When click on "Accept Verified Data" button of add site popup for Site Page 
	Then click on "Save" button of add site popup for Site Page 
	Then user should navigate to Site page with message "Site sitePage_siteBot1 saved." for Site Page
	Then validate that all the test cases of scenario for portlet executed successfully		
				
@AdminUI_SiteSearch 
Scenario: Scenario_03: Verify the search functionality of sites page. 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_05"   
	Given we are on Site page and search site field is enabled visible
	Then verify the search output by entering "sit:Init3Char" in site search field 
	Then verify the search output by entering ":RANDOM" in site search field 
	Then verify the search output by entering "sit:BACKSPACE" in site search field 
	Then verify the search output by entering "sit:DELETE" in site search field 
	Then verify the pagination output by entering value "sit:Pagination" in search field 
	Then verify the search output by entering ":LONGTEXT" in site search field 
	Then validate that all the test cases of scenario for portlet executed successfully 
		
@AdminUI_AddSiteNegativeVerification 
Scenario: Scenario_06: Verify the add site Address Verification popup functionality. 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_06"   
	Given we are on Site page and search site field is enabled visible 
	Then delete the "Site" data used as test data for site page 
		| sitePage_siteBot1 | sitePage_siteBot2 |
	#Add duplicat site verification
	When click on "Add Site" tab/button from Site page 
    Then enter the test data for the following fields to create new testData for Site Page for Site "First"  
		| Tenant Dropdown | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country | Phone 1 | Phone 2 | Area | Path | System Size | 
	When click on "Save" button of add site popup for Site Page 
	Then click on "Accept Verified Data" button of add site popup for Site Page 
	When click on "Save" button of add site popup for Site Page 
	Then valid error message should be displayed for "Another site already has that Site ID (Name)." scenario 
	When click on "Cancel" tab/button from Site page 
	Then confirmation box should display with message "All Changes will be lost. Are you sure?" 
	When click on "Ok" button on confirmation box on Site Page 
	Then user should be navigated on site detail grid 	
	
	#Add Site validation test
	When click on "Add Site" tab/button from Site page 
	Then enter invalid values is add site fields 
		| Site Name | Name$@%@@& |		
		| Solar System Size | NonNumeric |
		| Paths | without_forward_slash |
		| Area | NonNumeric |
		| Paths | /test/ |	
	Then valid error message should be displayed for "Add Site with invalid values" scenario 
    Then enter the test data for the following fields to create new testData for Site Page for Site "First"           
		| Tenant Dropdown | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country | Phone 1 | Phone 2 | Area | Path | System Size | 
	Then valid error message should be displayed for "empty fields" scenario 
	When click on "Cancel" tab/button from Site page 
	When click on "Ok" button on confirmation box on Site Page 
	Then user should be navigated on site detail grid 
	
	#GPUP-29950: Verify Search entries should not clear when user saves edits to a record.
	Then click on "Hide Inactive" tab/button from Site page 
	Then select "sitePage_tenantBot1" from tenant dropdown
	Then enter the site name "sitePage_siteBot1" in search field 
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page  
	Then click the "Save and Close" button on edit site page of "sitePage_siteBot1" and verify selection should not change
	Then validate that all the test cases of scenario for portlet executed successfully
	
@AdminUI_EditSiteVarification 
Scenario: Scenario_07: verify the edit site functionality by GPAdmin 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_07"   
	
	# Create test data
	Then delete the "Site" data used as test data for site page 
		| sitePage_siteBot1 | sitePage_siteBot2 |
	When click on "Add Site" tab/button from Site page
	Then verify "Decommissioned Checkbox" is unchecked by default 
    Then enter the test data for the following fields to create new testData for Site Page for Site "First"  
		| Tenant Dropdown | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country | Phone 1 | Phone 2 | Area | Path | System Size | 	
	When click on "Save" button of add site popup for Site Page 
	Then click on "Accept Verified Data" button of add site popup for Site Page 
	When click on "Save" button of add site popup for Site Page
	
	Given we are on Site page and search site field is enabled visible 
	
	# AddressVerificationScreen
	When enter the site name "sitePage_siteBot1" in search field 
	Then click on "Search" tab/button from Site page 
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page
	Then click on "Cancel" tab/button on Edit Site Page
	Then confirmation box should display with message "All Changes will be lost. Are you sure?" 
	When click on "Cancel" button on confirmation box on Site Page
	Then user should remains on edit site page
	Then click on "Cancel" tab/button on Edit Site Page
	When click on "Ok" button on confirmation box on Site Page
	Then user should be navigated on site detail grid 
	
	When enter the site name "sitePage_siteBot1" in search field 
	Then click on "Search" tab/button from Site page 
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page
	Then enter "Invalid" details on address verification popup
	Then click on "Save" button of add site popup for Site Page
	And verify the Message for "Invalid" data is "Invalid Address."
	Then click on "Address Verification:Cancel" button on Address Verification Screen for Edit Site Page 
	
	Then modify the values for "Address" fields and click on save button on "Edit" site modal
	Then verify that following fields are non-editable on Address Verification Screen for edit Site 
		| Latitude | Longitude | 
	Then click "Override" checkbox on address verification model for site and latitude and longitude fields become editable	
	And verify "Latitude" field return a value 
	And verify "Longitude" field return a value 
	Then clear the values of latitude and longitude fields
	And verify that "Accept Supplied Data" and "Accept Verified Data"  buttons are disabled
	Then enter values in latitude and longitude fields
	And verify that "Accept Supplied Data" button is enabled
	When click on "Accept Supplied Data" button of add site popup for Site Page 
	Then click on "Save" button of add site popup for Site Page 
	Then user should navigate to Site page with message "Site sitePage_siteBot1 saved." for Site Page
	
	# Verification Start
	When enter the site name "sitePage_siteBot1" in search field 
	Then click on "Search" tab/button from Site page 
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page
	Then modify the values for "Name" fields and click on save button on "Edit" site modal 
	Then click on "Cancel" tab/button on Edit Site Page
	When click on "Cancel" button on confirmation box on Site Page
	Then user should remains on edit site page and "Name" fields should not be changed
	
	##GPUP-32888(AdminUI>Site: No validation for Null values for Latitude/Longitude from Edit Site page)
	Then enter following values in "latitude" field on edit Site Page 
       | abcdefg | @#$%^& | 
    Then it shows a error message on edit site page "Invalid Latitude" for "Latitude"
    Then enter following values in "longitude" field on edit Site Page
       | abcdefg | @#$%^& | 
    Then it shows a error message on edit site page "Invalid Longitude" for "Longitude" 
    Then clear the values of latitude and longitude fields
    Then it shows a error message on edit site page "Invalid Latitude" for "Latitude"
    Then it shows a error message on edit site page "Invalid Longitude" for "Longitude"
    And verify that "Save" and "Save and Close"  buttons are disabled
    Then enter values 0 in latitude and 0 in longitude field
    And verify that "Save" and "Save and Close"  buttons are enabled
    When click on "X" tab/button from Site page
    
    Then select the site "sitePage_siteBot1" from the site details grid for Site Page
    Then verify the site name "sitePage_siteBot1" in the label on the site details page. 
	Then verify "Decommissioned Checkbox" is unchecked by default 
	And also verify the values of Phone fields for the site 
	Then verify the presence of following site details section on site detail grid page. 
		| MAP/Satellite	|
		| Site Name |
		| Site Description |
		| Address 1 |
		| Address 2 |
		| City |
		| Phone 1 |
		| Phone 2 |
		| State |
		| Postal Code |
		| Country |
		| Time Zone |
		| Latitude |
		| Longitude |
		| Commission Date |
		| Control Date |
		| Area |
		| Tenant |
		| Has Control |
		| Has Solar Panels |
		| Active |
		| Decommissioned Date |
		
		# GPUP-29949: Verify Save and Close button on edit site model.		
		| Save and Close |
		| Save 			 |
		| Cancel         |
 	Then verify that following fields are editable of edit site modal 
		| Latitude | Longitude | 
	And Latitude and Longitude values are prifilled in edit site modal 

	#Verify Commission and Decommissioned DateDate
	Then enter "Commission Date" greater then current date 
 	When click on "Save" button of add site popup for Site Page 
	Then enter "Control Date" less then "Commission Date" 
	When click on "Save" button of add site popup for Site Page 
	Then it shows a error message on edit site page "Control date should be more than commission date" for "Control Date" 
	Then enter "Control Date" greater then current date
	When click on "Save" button of add site popup for Site Page  
	Then click the "Decommission Date:checked" checkbox on site page 
	Then enter "Decommission Date" greater then current date 
	When click on "Save" button of add site popup for Site Page 
	Then it shows a error message on edit site page "Decommissioned date can not be a future date" for "Decommission Date" 
	Then enter "Decommission Date" less then "Control Date" 
	When click on "Save" button of add site popup for Site Page 
	Then it shows a error message on edit site page "Decommissioned date can not be less than control date" for "Decommission Date" 
	Then enter "Decommission Date" less then "Commission Date" 
	When click on "Save" button of add site popup for Site Page 
	Then it shows a error message on edit site page "Decommissioned date can not be less than commissioned date" for "Decommission Date" 
	Then all three dates Commission Date Control Date Decommission Date for the site "sitePage_siteBot1"
	When click on "Save and Close" button of add site popup for Site Page
	Then user should navigate to Site page with message "Site sitePage_siteBot1 saved." for Site Page

#	#Update site detail
	Then delete the "Site" data used as test data for site page  
		| sitePage_siteBot2 |  
	When enter the site name "sitePage_siteBot1" in search field 
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page
	Then modify the values for "Name" fields and click on save button on "Edit" site modal  
	Then modify the values for "Active" fields and click on save button on "Edit" site modal 
	Then all three dates Commission Date Control Date Decommission Date for the site "sitePage_siteBot1" 
	Then modify the values for "Address" fields and click on save button on "Edit" site modal 
	And the Address Verification popup should be displayed with the follwoing fields: 
		| Supplied Address1 | Supplied City | Supplied State/Province | Supplied Postal Code | Supplied Country | Latitude | Longitude | Timezone | Override Checkbox | Get Cordinates | Accept Supplied Data | Accept Verified Data | Message Box |
	And verify the Message for "Valid" data is "Address is verified." 
	Then click on "Accept Verified Data" button of add site popup for Site Page 
	When click on "Save and Close" button of add site popup for Site Page 
	Then user should navigate to Site page with message "Site sitePage_siteBot2 saved." for Site Page
	
	#AuditVarificationforUpdateSiteDetails 
	Then click on "Audit" tab/button from Site page
	When enter site name "sitePage_siteBot2" in audit search field for Site Page 
	Then click on "Audit:Search" tab/button from Site page 
	Then select the audit record "sitePage_siteBot2" from the audit details grid for Site Page  
	Then verify that user should navigate on "Edit Site" page 
	Then verify that the "New" test data details should be displayed in the following columns for Site log 
		| Site ID | Site Description | Address | City | Postal Code | Active | 
	 
			
	#Delete site
	Then click on "Sites" tab/button from Site page 
	Then enter the site name "sitePage_siteBot2" in search field 
	Then select the site "sitePage_siteBot2" from the site details grid for Site Page
    And verify that "Delete" button is enabled 
	When click on "Delete" tab/button from Site page 
	Then confirmation box should displayed on site page with message "Do you want to delete the site sitePage_siteBot2 ?" 
	When click on "Ok" button on confirmation box on Site Page 
	Then user should be navigated to sites page with a label saying "sitePage_siteBot2 is deleted successfully." 
	Then verify the site "sitePage_siteBot2" is deleted successfully
	
	#AuditVarificationforDeletedSite
	Then click on "Audit" tab/button from Site page
	When enter site name "sitePage_siteBot2" in audit search field for Site Page 
	Then click on "Audit:Search" tab/button from Site page 
	Then select the audit record "sitePage_siteBot2" from the audit details grid for Site Page
	Then verify site "VC/sitePage_siteBot1_Edited" has been removed from Site Details Page saying "No records found." 
	Then validate that all the test cases of scenario for portlet executed successfully 	
		
		
@AdminUI_PathAccordion 
Scenario: Scenario_09: To verify the functionality of Paths accordion 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_08"   
	Given we are on Site page and search site field is enabled visible  	
	
	## TODO : below steps are removed : 1/17/2018 8:49:58
	#Create test data
	Then delete the "Sites" used as test data    
		| sitePage_siteBot1 |  
	Then click on "Sites" tab/button from Site page
	When click on "Add Site" tab/button from Site page
	Then enter the test data for the following fields to create new testData for Site Page for Site "First"  
		| Tenant Dropdown | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country | Phone 1 | Phone 2 | Area | Path | System Size | 	
	When click on "Save" button of add site popup for Site Page 
	Then click on "Accept Verified Data" button of add site popup for Site Page 
	When click on "Save" button of add site popup for Site Page 
	
	
	#Add test data
	Then delete the "Tenant" data used as test data for site page  
		| sitePage_tenantBot2 |
    Then delete the "Tenant" used as test data
        | sitePage_tenantBot2 |
	Then delete the "Site" data used as test data for site page 
		| sitePage_siteBot2 |
    Then delete the "Sites" used as test data  
		| sitePage_siteBot2 | 
	Then click on "TENANT" tab/button from Site page
	When click on "Add Tenant" tab/button from Site page 
	Then enter the test data for the following fields to create new testData for Site Page for tenant "Second"    
	    | Tenant Name | Tenant Parent | Tenant Type |  
	When click on "Save Tenant" tab/button from Site page 
	Then user should be navigated to tenant page with a label displaying "Tenant sitePage_tenantBot2 saved." 
	Then click on "Sites" tab/button from Site page
	When click on "Add Site" tab/button from Site page 
	Then enter the test data for the following fields to create new testData for Site Page for Site "Second"  
		| Tenant Dropdown | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country | Phone 1 | Phone 2 | Area | Path | System Size | 
	When click on "Save" button of add site popup for Site Page 
	Then click on "Accept Verified Data" button of add site popup for Site Page 
	When click on "Save" button of add site popup for Site Page 
	Then user should be navigated to sites page with a label saying "Site sitePage_siteBot2 saved." 
	
	#Test start
	Then click on "Sites" tab/button from Site page 
	Then click the "Hide Inactive" checkbox on site page 
	When enter the site name "sitePage_siteBot1" in search field 
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page 	
	Then click on "Paths" accordion
	Then verify that path text associated with the site should be same with the Path that is provided during site creation  
	Then verify the text box should contain site path 
	
	#Update tenant 
	Then click on "Paths" accordion
	Then click on "Details" accordion
	When change the tenant to "sitePage_tenantBot2" from tenant dropdown 
	When click on "Save" button of add site popup for Site Page
	Then click on "Paths" accordion 
	And click on "Add Path" button of add site popup for Site Page 
	And the new field is displayed on the edit site page 
	Then verify top level path is auto populated on the basis of tenant selection edit site page 
	Then verify top level path "/sitePage_tenantBot2" is displayed in the site path field
	
	#Update Path
	Then add "/sitePage_tenantBot2" with the existing path on edit site page 
	Then it shows a warning message if the new string is added "This would create a new path under ######## tenant, are you sure?" for "sitePage_tenantBot2" on edit site page 
	When click on "Save and Close" button of add site popup for Site Page 
	Then validate that all the test cases of scenario for portlet executed successfully
	

# TODO: compare the below scenario with above scenario 
#Compared both scenario and found the above scenario is duplicate to this scenario.I have commented out below one but need to delete once the above scenario has implemented completely 
#@PathAccordion 
#Scenario: Scenario_09: To verify the functionality of Paths accordion 
#	Given we are on site page and optionDropDown field is enabled 
#	
#	#Create test data
#	Then delete the "Sites" used as test data 
#		| sitePage_siteBot1 |  
#	When click on "Add Site" button on site page 
#	Then enter valid values for site "1" 
#	When click on "Save" button on add site popup 
#	Then click "Accept Verified Data" button on address verification model for site 
#	When click on "Save" button on add site popup 
#	Then user should be navigated to sites page with a label saying "Site sitePage_siteBot1 saved." 
#	
#	Then delete the "Tenants" used as test data 
#		| sitePage_tenantBot2 |
#	Then click on "Tenants" tab in the application 
#	When click on "Add Tenant" button on tenant tab 
#	Then enter the "Name" as "sitePage_tenantBot2" text field
#	Then the "Select_Tenant_Parent" name "GridPoint Test Automation" from parent selection box on add tenant page 
#	When click "Save" button on the add tenant popup 
#	Then user should be navigated to tenant page with a label displaying "Tenant sitePage_tenantBot2 saved." 
#	
#	#Test start
#	Then click on "Sites" tab in the application 
#	Then click the "Hide Inactive" checkbox on site page 
#	When enter the site name "sitePage_siteBot1" in search field 
#	Then select the site "sitePage_siteBot1" from the site details grid 	
#	Then click on "Paths" accordion
#	Then verify that path text associated with the site should be same with the Path that is provided during site creation 
#	Then verify the text box should contain site path  
#	
#	#Update tenant 
#	Then click on "Paths" accordion
#	Then click on "Details" accordion
#	When change the tenant to "sitePage_tenantBot2" from tenant dropdown 
#	When click on "Save" button on site details page
#	Then click on "Paths" accordion 
#	And click on "Add Path" button on site details page 
#	And the new field is displayed on the edit site page 
#	Then verify top level path is auto populated on the basis of tenant selection edit site page 
#	Then verify top level path "/sitePage_tenantBot2" is displayed in the site path field
#	
#	#Update Path
#	Then add "/sitePage_tenantBot2" with the existing path on edit site page 
#	Then it shows a warning message if the new string is added "This would create a new path under ######## tenant, are you sure?" for "sitePage_tenantBot2" on edit site page 
#	When click on "Save and Close" button on add site popup 
#	Then user should be navigated to sites page with a label saying "Site sitePage_tenantBot2 saved." 
		
		
						
@AdminUI_VerifySiteForNewlyCreatedEndpoint 
Scenario: Scenario_11: To verify site for newly created endpoint 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_09"   
	Given we are on Site page and search site field is enabled visible 
	#Create test data
	Then delete the "Tenant" data used as test data for site page 
		| sitePage_endpointBot1 |   
	Then click on "Endpoints" tab/button from Site page 
	And click on "Add Endpoint" tab/button from Site page
    Then enter the test data for the following fields to create new testData for Site Page for endpoint "First" 
		| Serial | Type | 
	When click on "Endpoint Save" tab/button from Site page 
	Then user should be navigated to endpoint page with a message saying "Endpoint sitePage_endpointBot1 saved."
	
	#Test Start
	When enter endpoint id "sitePage_endpointBot1" in search text field on the Endpoint list page 
	And click on "Endpoint Search" tab/button from Site page 
	Then select the entered endpoint id "sitePage_endpointBot1" from the endpoint detail grid 
	Then verify Site and Tenant for newly created endpoint "sitePage_endpointBot1" 
	
	When user modify following ASSOCIATION TENANTSITE fields for an endpoint 
	And click on "Endpoint:Save and Close" tab/button from Site page 
	When enter endpoint id "sitePage_endpointBot1" in search text field on the Endpoint list page 
	And click on "Endpoint Search" tab/button from Site page
	Then select the entered endpoint id "sitePage_endpointBot1" from the endpoint detail grid 
	Then verify Site and Tenant for an endpoint "sitePage_endpointBot1" 
	And click on "Endpoint:Save and Close" tab/button from Site page  
	Then click on "Sites" tab/button from Site page 
	When enter the site name "sitePage_siteBot1" in search field 
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page
	Then verify "Delete" button is not displayed
	Then click on "Endpoints" accordion 
	Then click on "X" tab/button from Site page 
	Then validate that all the test cases of scenario for portlet executed successfully
	
@AdminUI_VerifyTenantForProvisingSite @P2
Scenario: Scenario_12: To verify tenant for Provising site 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_10"   
	Given we are on Site page and search site field is enabled visible
	Then enter the site name "Provisioning" in search field 
	# added by k on 12 sep in his branch.....verify
	Then click the "Hide Inactive" checkbox on site page 
	###############################################
	Then select the site "Provisioning" from the site details grid for Site Page 
	Then verify the tenant for "Provisioning" site is "Gridpoint" 
	Then validate that all the test cases of scenario for portlet executed successfully
	
@VerifyDiffAccordion @P2
Scenario: Scenario_13: To verify the functionality of audit accordian 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_11"   
	Given we are on Site page and search site field is enabled visible  
	Then click the "Hide Inactive" checkbox on site page 
	When enter the site name "sitePage_siteBot1" in search field 
	Then click on "Search" tab/button from Site page
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page
	
	#PathAccordion
	Then verify the expansion and shrinkage while click on "Paths" accordion
	Then verify following fields and buttons under Paths accordion
	   | Edit Site Path0 | Add Path | Remove Path0 |
	#EndpointAccordion
	Then verify the expansion and shrinkage while click on "Endpoints" accordion 
	Then verify the columns in "endpoints" grid 
		| Endpoint ID | Connection Status | Type | Serial | Reference ID | Live Firmware | Last Message Received (UTC) |
	Then verify the Sorting functionality of columns on "Endpoints" list 
		| Endpoint ID | Connection Status | Type | Serial | Reference ID | Live Firmware | Last Message Received (UTC) |
	Then verify the pagination functionality on "Endpoints" list 
	
	#AuditAccordion
	Then verify the expansion and shrinkage while click on "Audit" accordion  
	Then verify the columns in "Audit" grid 
		| Property | Action | Original Value | Updated Value | User | Date Stamp |
	Then verify the Sorting functionality of columns on "Audit" list 
		| Property | Action | Original Value | Updated Value | User | Date Stamp |
	Then verify the pagination functionality on "Audit" list 
	
	#ChannelAccordion
	Then verify the expansion and shrinkage while click on "Channel" accordion 
	Then verify the columns in "Channel" grid 
		| Channel ID | Channel Name | Display Name | Device Name | Measure Type | Unit | Metadata Category | Total Type | Show in Graphs |
	Then verify the Sorting functionality of columns on "Channel" list 
		| Channel ID | Channel Name | Display Name | Device Name | Measure Type | Unit | Metadata Category | Total Type |
	Then verify the search functionalty of following "Channel" list 
	Then verify the presence of edit, view, export and add virtual buttons in "Channels" list 
	Then click on "Export:Channel" tab/button from Site page
	Then validate that all the test cases of scenario for portlet executed successfully
		
@AdminUI_PVAuditAccordion @P2
Scenario: Scenario_15: To verify the functionality of PV audit accordian 
    Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_12"   
	Given we are on Site page and search site field is enabled visible
	Given "Insert" the following test data of PVAudit Table  
		| sitePage_siteBot1 | 
	When enter the site name "MRIAUCTIONMA1004" in search field 
	Then click on "Search" tab/button from Site page 
	Then select the site "Manheim NE Auction 1004" from the site details grid for Site Page 
	Then verify the expansion and shrinkage while click on "PV Audit" accordion 
	Then verify the columns in "PV Audit" grid 
		| ID | SiteName | StartDate | EndDate | SubmittedDate | Status | Request | Response |
	Then verify the Sorting functionality of columns on "PV Audit" list 
		| ID |  SiteName |  StartDate | EndDate | SubmittedDate | Status | Request | Response |
	Then verify the pagination functionality on "PV Audit" list 
	Then select "MRIAUCTIONMA1004" details from the "PV Audit" accordion and it navigates to pv audit accordion 
	Then verify the various attribute in pv audit popup 
		| PVAuditSummary | Requestxml | Responsexml |
	Then click on "PV_Audit:Cancel" button on pv audit popup 
	Then "Delete" the following test data of PVAudit Table 
		| sitePage_siteBot1 | 	
	Then validate that all the test cases of scenario for portlet executed successfully
		
@AdminUI_ExportIconSite @P2
Scenario: Scenario_16: Verify the functionality of Export icon. 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_13"   
	Given we are on Site page and search site field is enabled visible
	Then verify Export icon is display on Site tab 
	Then click on "Export:icon" tab/button from Site page 
	And the choose Column popup is displayed 
	Then verify the default column list on "Right" side on Choose column popup 
		| Site ID | Site Name | Address | Postal Code | Active | Time Zone | Tenant | Tenant ID |
	Then verify the default column list on "Left" side on Choose column popup 
		| State/Province | Country | City | Address 2 | Phone 1 | Phone 2 | Has Solar Panels | Commission Date | Control Date | Decommissioned Date |Area | Has Control | Latitude | Longitude |
	
	Then verify the total column list on Choose column popup 
		| Site ID | Site Name | Address | Postal Code | Active | Time Zone | Tenant | Tenant ID | State/Province | Country | City | Address 2 | Phone 1 | Phone 2 | Has Solar Panels | Commission Date | Control Date | Decommissioned Date | Area | Has Control | Latitude | Longitude |
	Then select some of the columns from "Left" side of choose column popup 
	Then verify and click on "Add" button functionality on Choose column popup 
	Then verify and click on "Add All Columns" button functionality on Choose column popup 
	And all the following columns are added on right side of choose column popup
	    | R:Site ID | R:Site Name | R:Address | R:Postal Code | R:Active | R:Time Zone | R:Tenant | R:Tenant ID | R:State/Province | R:Country | R:City | R:Address 2 | R:Phone 1 | R:Phone 2 | R:Has Solar Panels | R:Commission Date | R:Control Date | R:Decommissioned Date | R:Area | R:Has Control | R:Latitude | R:Longitude |
	Then verify and click on "Reset Defaults" button functionality on Choose column popup 
	Then verify the default column list on "Right" side on Choose column popup 
		| Site ID | Site Name | Address | Postal Code | Active | Time Zone | Tenant | Tenant ID |
		
	# TODO : Below steps are commented (Implemented on 18-Oct)
	Then select All of the columns from "Right" side of choose column popup
	Then verify and click on "Remove" button functionality on Choose column popup 
	Then the "Submit" button become disabled on Choose Column Popup 
	
	Then verify and click on "Reset Defaults" button functionality on Choose column popup 
	And it shows the default layout of Choose column popup 
	    | Site ID | Site Name | Address | Postal Code | Active | Time Zone | Tenant | Tenant ID |
	Then verify and click on "Submit" button functionality on Choose column popup 
	And the Report is dowmloaded for Site Tab 
	Then click the "Hide Inactive" checkbox on site page  	                
	Then click on "Export:icon" tab/button from Site page 
	And the choose Column popup is displayed 
	Then verify and click on "Submit" button functionality on Choose column popup 
	And the Report is dowmloaded for Site Tab 
	Then click the "Hide Inactive" checkbox on site page 
	Then click on "Export:icon" tab/button from Site page 
	And the choose Column popup is displayed 
	Then verify and click on "Submit" button functionality on Choose column popup 
	And the Report is dowmloaded for Site Tab 
	When enter the site name "sitePage_siteBot1" in search field 
	Then click on "Export:icon" tab/button from Site page 
	And the choose Column popup is displayed 
	Then verify and click on "Submit" button functionality on Choose column popup 
	And the Report is dowmloaded for Site Tab 
	Then verify the tenant in tenant dropdown is "Gridpoint"
	Then validate that all the test cases of scenario for portlet executed successfully

@AdminUI_EditFunctionalityADMSites 
Scenario: Scenario_19: Verify different functionality of Edit Site 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_14"   
	Given we are on Site page and search site field is enabled visible 
	When enter the site name "T2150" in search field 
	Then click on "Search" tab/button from Site page 
	Then select the ADM site "T2150" from the site details grid 
	Then verify that following fields are editable of ADM Site 
		| Site Name | Site Description | Address1 | City | Postal Code |
	Then verify the Postal Code field contains postal code for a site 
	Then verify "Delete" button is not displayed 
	Then click on "Paths" accordion 
	Then verify that "Paths Text Field" is editable of ADM Site 
	Then click on "Paths" accordion 
	Then click on "Channels" accordion 
	Then click on "Add Virtual Channel" tab/button from Site page 
	Then verify Virtual channel creation tool window 
	Then click on "X" button on Add Virtual Channel creation tool window 
	Then click on "X" button on edit Site Page 	
	
	Then delete the "Site" data used as test data for site page 
		| sitePage_siteBot1 | 
	When click on "Add Site" tab/button from Site page 
    Then enter the test data for the following fields to create new testData for Site Page for Site "First"  
		| Tenant Dropdown | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country | Phone 1 | Phone 2 | Area | Path | System Size | 
	When click on "Save" button of add site popup for Site Page 
	Then click on "Accept Verified Data" button of add site popup for Site Page 
	When click on "Save" button of add site popup for Site Page
	
	Then click the "HIDE INACTIVE" checkbox on site page
	When enter the site name "sitePage_siteBot1" in search field 
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page 
	Then verify "Real Time Data" icon is "Displayed" on Edit Site Page 
	Then click the "DECOMMISSION DATE:CHECKED" checkbox on site page 
	Then click on "Save and Close" button on edit site page 
	When enter the site name "sitePage_siteBot1" in search field 
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page 
	Then verify "Real Time Data" icon is "Not Displayed" on Edit Site Page 
	Then click on "Channels" accordion 
	Then click on "Endpoints" accordion 
	Then select the first endpoint from the list of Endpoint accordion 
	Then verify "Real Time Data" icon is "Not Displayed" on Edit Endpoint Page 
	Then click on "X" button on edit Endpoint Page 
	
	Then click on the portal button which navigate to HTML Portal for Site Page 
	Then click site tree "clear" button on home tab 
	Then click in the "Portal:Site Search" text box in the portal Home Tab 
	Then enter text "ACM34" in the site search text box on home tab 
	Then click site tree "Select All" button on home tab 
	Then verify "Real Time Tab" icon is not displayed on "Home" page 
	Then click on the "Control" tab in the portal 
	Then click on the "Control:HVAC" tab in the portal 
	Then select any "HVAC Device" on the tab 
	Then verify "Real Time Tab" icon is not displayed on "Control:HVAC" page 
	Then click on the "Control:Lighting" tab in the portal 
	Then select any "Lighting Device" on the tab 
	Then verify "Real Time Tab" icon is not displayed on "Control:Lighting" page 
	Then click on the "Data" tab in the portal 
	Then verify "Real Time Tab" icon is not displayed on "Data" page
	Then validate that all the test cases of scenario for portlet executed successfully 
	
@AdminUI_InactiveTextPortal 
Scenario: Scenario_20: Verify Inactive text on Portal 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_15"   
	Given we are on Site page and search site field is enabled visible 
	Then click the "Hide Inactive" checkbox on site page  
	When enter the site name "sitePage_siteBot1" in search field 
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page  
	Then click the "ACTIVE:UNCHECKED" checkbox on site page 
	Then click on "Save and Close" button on edit site page
	Then click on the portal button which navigate to HTML Portal for Site Page 
	Then click site tree "clear" button on home tab 
	Then click in the "Portal:Site Search" text box in the portal Home Tab 
	Then enter text "sitePage_siteBot1" in the site search text box on home tab 
	Then click site tree "Select All" button on home tab 
	Then verify "Inactive" text is displayed on "Home Tab" 
	And verify "Phone No" on home tab for the site "sitePage_siteBot1" 
	And verify "Decommission Date" on home tab for the site "sitePage_siteBot1" 
	Then click on the "Control" tab in the portal 
	Then verify "Inactive" text is displayed on "Control:Hours Tab" 
	Then click on the "Control:HVAC" tab in the portal 
	Then verify "Inactive" text is displayed on "Control:HVAC Tab" 
	Then click on the "Control:Lighting" tab in the portal 
	Then verify "Inactive" text is displayed on "Control:Lighting Tab" 
	Then click on the "Alarms" tab in the portal 
	Then verify "Inactive" text is displayed on "Alarms Tab" 
	Then click on the "Projects" tab in the portal 
	Then verify "Inactive" text is displayed on "Projects Tab" 
	Then click on the "Data" tab in the portal 
	Then click on the "Graphs" tab in the portal 
	Then verify "Inactive" text is displayed on "Graphs Tab" 
	Then click on the "Reports" tab in the portal 
	Then verify "Inactive" text is displayed on "Reports Tab" 
	Then click on the "Savings" tab in the portal 
	Then verify "Inactive" text is displayed on "Savings Tab"
	Then validate that all the test cases of scenario for portlet executed successfully 

	
#TODO : check the missing below scenario
@VirtualChannelGPAdmin 
Scenario: Scenario_16: Verify the virtual channel functionality which is configured for the site for GPAdmin User 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_16"   
	Given we are on Site page and search site field is enabled visible   
	Then user verify the "Hide Inactive" checkbox with label
	When enter the site name "sitePage_siteBot1" in search field
	Then verify inactive site "sitePage_siteBot1" is not displayed on Site details Page with message "No Records found."
	Then uncheck the "Hide Inactive" checkbox 
	When enter the site name "sitePage_siteBot1" in search field 
	Then click on "Search" tab/button from Site page 
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page
	When click on "Channels" accordion 
	Then enter "sitePage_SubVirtualChannelBot1" in channel search field 
	Then click on "View" button of newly created channel "sitePage_SubVirtualChannelBot1" on Channel Accordion 
	Then click on "Virtual Channel:Remove" button on delete virtual channel pop-up 
	Then click on "Virtual Channel:OK" button on delete confirmation box  
	Then click on "Channel" accordion 
	Then verify Channel "Deletion" message "Channel ######## deleted successfully" is displayed on Channel Accordion for the channel "sitePage_SubVirtualChannelBot1" 
	Then click on "Add Virtual Channel" button on site page 
	Then enter "sitePage_SubVirtualChannelBot1" in Name field of channel on Add Virtual Channel 
	Then select "TEMPERATURE_ZONE" from Measure Type Dropdown 
	Then select "HVAC" from Metadata Category Dropdown 
	Then enter "Local Dewpoint" in channel search field of add virtual channel popup 
	Then click on "ADD" button for adding the channel 
	Then enter "Local Temperature" in channel search field of add virtual channel popup 
	Then click on "ADD" button for adding the channel 
	Then apply "SUB" operator for the selected channels 
	Then click on "VIRTUAL CHANNEL:SAVE" button on site page 
	When click on "Channel" accordion 
	Then verify Channel "Creation" message "Created new virtual channel: ########" is displayed on Channel Accordion for the channel "sitePage_SubVirtualChannelBot1" 
	Then enter "sitePage_AvgVirtualChannelBot2" in channel search field 
	Then click on "View" button of newly created channel "sitePage_AvgVirtualChannelBot2" on Channel Accordion 
	Then click on "Virtual Channel:Remove" button on delete virtual channel pop-up 
	Then click on "Virtual Channel:OK" button on delete confirmation box 
	Then click on "Channel" accordion 
	Then verify Channel "Deletion" message "Channel ######## deleted successfully" is displayed on Channel Accordion for the channel "sitePage_AvgVirtualChannelBot2" 
	Then click on "Add Virtual Channel" button on site page 
	Then enter "sitePage_AvgVirtualChannelBot2" in Name field of channel on Add Virtual Channel 
	Then select "TEMPERATURE_ZONE" from Measure Type Dropdown 
	Then select "WEATHER" from Metadata Category Dropdown 
	Then enter "Local Dewpoint" in channel search field of add virtual channel popup 
	Then click on "ADD" button for adding the channel 
	Then enter "Local Temperature" in channel search field of add virtual channel popup 
	Then click on "ADD" button for adding the channel 
	Then apply "AVG" operator for the selected channels 
	Then enter "sitePage_SubVirtualChannelBot1" in channel search field of add virtual channel popup 
	Then click on "ADD" button for adding the channel 
	And verify "OPERATOR" Dropdown has following operators 
		| AVG |    
	Then click on "VIRTUAL CHANNEL:SAVE" button on site page 
	Then verify Channel "Creation" message "Created new virtual channel: ########" is displayed on Channel Accordion for the channel "sitePage_AvgVirtualChannelBot2" 
	Then enter "sitePage_MultiChannslsAVGVirtualChannelBot3" in channel search field 
	Then click on "View" button of newly created channel "sitePage_MultiChannslsAVGVirtualChannelBot3" on Channel Accordion 
	Then click on "Virtual Channel:Remove" button on delete virtual channel pop-up 
	Then click on "Virtual Channel:OK" button on delete confirmation box 
	Then click on "Channel" accordion 
	Then verify Channel "Deletion" message "Channel ######## deleted successfully" is displayed on Channel Accordion for the channel "sitePage_MultiChannslsAVGVirtualChannelBot3" 
	Then click on "Add Virtual Channel" button on site page 
	Then enter "sitePage_MultiChannslsAVGVirtualChannelBot3" in Name field of channel on Add Virtual Channel 
	Then select "TEMPERATURE_ZONE" from Measure Type Dropdown 
	Then select "HVAC" from Metadata Category Dropdown 
	Then select "DEGREES_FAHRENHEIT" from Channel Filter Dropdown 
	Then enter "Local Dewpoint" in channel search field of add virtual channel popup 
	Then click on "ADD" button for adding the channel 
	Then enter "Local Temperature" in channel search field of add virtual channel popup 
	Then click on "ADD" button for adding the channel 
	Then apply "SUB" operator for the selected channels 
	Then enter "sitePage_AvgVirtualChannelBot2" in channel search field of add virtual channel popup 
	Then click on "ADD" button for adding the channel 
	And verify "OPERATOR" Dropdown has following operators 
		| SUM | SUB |
	Then apply "SUM" operator for the selected channels 
	And verify "OPERATOR" Dropdown has following operators 
		| SUM | SUB |   
	Then click on "VIRTUAL CHANNEL:SAVE" button on site page 
	When click on "Channel" accordion 
	Then verify Channel "Creation" message "Created new virtual channel: ########" is displayed on Channel Accordion for the channel "sitePage_MultiChannslsAVGVirtualChannelBot3" 
	Then enter "sitePage_SubVirtualChannelBot1" in channel search field 
	Then verify newly created channel name "sitePage_SubVirtualChannelBot1" is displayed 
	Then enter "sitePage_AvgVirtualChannelBot2" in channel search field 
	Then verify newly created channel name "sitePage_AvgVirtualChannelBot2" is displayed 
	Then enter "sitePage_MultiChannslsAVGVirtualChannelBot3" in channel search field 
	Then verify newly created channel name "sitePage_MultiChannslsAVGVirtualChannelBot3" is displayed 
	Then click on "X" button on edit Site Page 
	When enter the site name "sitePage_siteBot1" in search field 
	Then click on "Search" button on site page 
	Then verify the tenant name "sitePage_tenantBot" in the tenant column related to entered site "sitePage_siteBot1" 
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page 
	Then click on "Channel" accordion 
	And the Channel list is displayed in channel accordion 
	When click on "Edit" button of Local Dewpoint channel 
	Then verify that following fields are editable of Local Dewpoint channel 
		| Display Name | Measure Type | Metadata Category | Total Type |
	Then modify the valuse of following fields with valid data for Local Dewpoint channel 
		| Display Name | Measure Type | Metadata Category | Total Type |
	When click on "Update" button of Local Dewpoint channel 
	When click on "Edit" button of Local Dewpoint channel 
	Then verify that values of following fields have been modified for Local Dewpoint channel 
		| Display Name | Measure Type | Metadata Category | Total Type | 
	Then modify the valuse of following fields with valid data for Local Dewpoint channel 
		| Measure Type:Cancel | 
	Then click on "Cancel" button of Local Dewpoint channel 
	Then click on "Edit" button of Local Dewpoint channel 
	Then verify last saved value should be displayed for "Measure Type" 
	Then reset the previous values of the Local Dewpoint channel 
		| Display Name | Measure Type | Metadata Category | Total Type | 
	Then click on "Add Virtual Channel" button on site page 
	Then verify Virtual channel creation tool window
	And verify following Available Channel list on Virtual channel creation tool window
	    | Main Load Baseline | Local Dewpoint | Local Humidity | Local Temperature |  
	Then click on "X Virtual Channel" button on site page
	Then user should be navigated on edit site popup with channel accordion expended 
	Then click on "Add Virtual Channel" button on site page 
	Then verify the elements present on "Add Channel" popup 
		| Name | Measure Type | MetaData Category | Total Type | Available Channel UOM | Available Channel Measure Type | Available Channel Search | Available Channel List |
	Then verify the columns in "Virtual Channel" grid 
		| Channel ID | Channel Name | Display Name | Device Name | Unit | Metadata Category | Total Type |
	Then verify the Sorting functionality of columns on "Virtual Channel" list 
		| Channel ID | Channel Name | Display Name | Device Name | Unit | Metadata Category | Total Type |
	When click on save button without filling add virtual channel form. 
	Then valid error message "Please correct the following errors:" with following list should be displayed for "Add Virtual Channel" form 
		| Channel Name should not be empty |
		| Channel Metadata Category should not be empty |
		| Please add atleast two channels |
	Then click on "close" button on add virtual channel error popup 
	When click on "Cancel" button on add virtual channel popup 
	Then confirmation box on virtual channel should displayed on site page with message "All Changes will be lost. Are you sure?" 
	When click on "Cancel on Virtual" button on confirmation box on Site Page 
	Then user should remain on add virtual channel popup 
	When click on "Cancel" button on add virtual channel popup 
	Then confirmation box on virtual channel should displayed on site page with message "All Changes will be lost. Are you sure?" 
	When click on "Ok on Virtual" button on confirmation box on Site Page 
	Then user should be navigated on edit site popup with channel accordion expended 
	Then click on "X" button on edit Site Page
	Then validate that all the test cases of scenario for portlet executed successfully 
	
#TODO : check the missing below scenario	
@VirtualChannelGPSupport 
Scenario: Scenario_17: Verify the virtual channel functionality which is configured for the site for GP Support User 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_17"   
	Given we are on Site page and search site field is enabled visible  	
	Then click on Logout button on site page 
	
	#Creating Test Data
	Then "delete" various users as test data for site page 
		| SitePage_GPSupportUserBot | 
	Then "create" various users as test data for site page 
		| SitePage_GPSupportUserBot | 
	
	#Test Start
	When we will login with "GP Support" credential and varify site tab 
		| SitePage_GPSupportUserBot | 
	When enter the site name "T2150" in search field 
	Then select the site "T2150" from the site details grid for Site Page 
	Then verify "Delete" button is not displayed 
	Then click on "X" button on edit Site Page 
	When enter the site name "sitePage_siteBot1" in search field 
	Then click on "Search" button on site page 
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page
	Then click on "Channel" accordion 
	Then enter "VC/sitePage_siteBot1" in channel search field 
	Then click on "View" button of newly created channel "VC/sitePage_siteBot1" on Channel Accordion 
	Then click on "Virtual Channel:Remove" button on delete virtual channel pop-up 
	Then click on "Virtual Channel:OK" button on delete confirmation box 
	Then click on "Channel" accordion 
	Then click on "Add Virtual Channel" button on site page 
	Then enter "VC/sitePage_siteBot1" in Name field of channel on Add Virtual Channel 
	Then select "TEMPERATURE_ZONE" from Measure Type Dropdown 
	Then select "REFRIGERATION" from Metadata Category Dropdown 
	#	Then select "DEGREES_FAHRENHEIT" from Channel Filter Dropdown 
	Then enter "Local Dewpoint" in channel search field of add virtual channel popup 
	Then click on "ADD" button for adding the channel 
	Then enter "Local Temperature" in channel search field of add virtual channel popup 
	Then click on "ADD" button for adding the channel 
	Then click on "VIRTUAL CHANNEL:SAVE" button on site page 
	When click on "Channel" accordion
	Then verify Channel "Creation" message "Created new virtual channel: ########" is displayed on Channel Accordion for the channel "VC/sitePage_siteBot1" 
	Then click on "Add Virtual Channel" button on site page 
	Then select "DEGREES_FAHRENHEIT" from Channel Filter Dropdown on Available Channels Section 
	Then select "TEMPERATURE_ASSET" from Measure Type Dropdown on Available Channels Section 
	Then verify "VC/sitePage_siteBot1" channel is displayed after making filter on Unit of Measure and Measure Type 
	Then click on "X" button on Add Virtual Channel creation tool window 
	
	Then enter "VC/sitePage_siteBot1" in channel search field 
	Then verify newly created channel name "VC/sitePage_siteBot1" is displayed 
	Then verify that "Show In Graphs" checkbox is checked for channel "VC/sitePage_siteBot1" by default and sql query output should be "true" 
	Then verify following buttons are present in the virtual channel action column 
		| Edit | View | 
	Then click on "Edit" button of newly created channel "VC/sitePage_siteBot1" on Channel Accordion 
	Then modify the valuse of following fields with valid data for Newly Created channel 
		| NVC:Display Name | NVC:Measure Type | NVC:Total Type |  
	Then click on "Update" button of newly created channel "VC/sitePage_siteBot1" on Channel Accordion 
	Then click on "Edit" button of newly created channel "VC/sitePage_siteBot1" on Channel Accordion 
	Then verify that values of following fields have been modified for Newly Created channel 
		| NVC:Display Name | NVC:Measure Type | NVC:Total Type | 	
	Then verify following buttons are present in the virtual channel action column 
		| Update | Cancel | 		   
	Then click on "Update" button of newly created channel "VC/sitePage_siteBot1" on Channel Accordion 
	Then click on "View" button of newly created channel "VC/sitePage_siteBot1" on Channel Accordion 
	Then click on "Virtual Channel:Remove" button on delete virtual channel pop-up 
	Then click on "Virtual Channel:OK" button on delete confirmation box 
	Then click on "Channel" accordion 
	Then verify Channel "Deletion" message "Channel ######## deleted successfully" is displayed on Channel Accordion for the channel "VC/sitePage_siteBot1" 
	Then enter "VC/sitePage_siteBot1_Edited" in channel search field 
	Then verify channel "VC/sitePage_siteBot1_Edited" has been removed from Channel List saying "No records found."
	Then validate that all the test cases of scenario for portlet executed successfully 

# TODO : below scenario was later comments : verify : 1/17/2018 8:49:58 PM		
@InactiveTextPortal-Part-2 
Scenario: Scenario_20: Verify Inactive text on Portal for diffrent portal users
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_18"   
	Given we are on Site page and search site field is enabled visible  
	
	#Creating Test Data
	Then "delete" various users as test data for site page 
		| SitePage_CustomerAnalystUserBot | SitePage_GPAnalystUserBot |	
	Then delete the upload bulk sites from the site tab having name "%UI_TestSiteBulkImport_%"
	Then "create" various users as test data for site page 
		| SitePage_CustomerAnalystUserBot | SitePage_GPAnalystUserBot |
	
	#Test Start
	Then click on "Users" tab/button from Site page 
	When enter the username "SitePage_CustomerAnalystUserBot" in search field on user page 
	Then select the user "SitePage_CustomerAnalystUserBot" from the users detail grid 
	Then click on "Permission" accordion on usertab 
	Then select a tenant "GridPoint Test Automation" from the tenant drop down on edit user 
	When click on "Add All" button on user tab 
	And verify that all Sites get selected and display as "*" with Tenant name on right side of Permission Accordion on edit user page 
	When click on "Permission:Save" button on user tab 
	Then verify the message "Tenant associations have been successfully updated." on "Permission" accordion on edit user 
	When click on "X" button on user tab
	When enter the username "SitePage_GPAnalystUserBot" in search field on user page 
	Then click on "Search" button on user tab 
	Then select the user "SitePage_GPAnalystUserBot" from the users detail grid 
	Then click on "Permission" accordion on usertab 
	Then select a tenant "GridPoint Test Automation" from the tenant drop down on edit user 
	When click on "Add All" button on user tab 
	And verify that all Sites get selected and display as "*" with Tenant name on right side of Permission Accordion on edit user page 
	When click on "Permission:Save" button on user tab 
	Then verify the message "Tenant associations have been successfully updated." on "Permission" accordion on edit user 
	When click on "X" button on user tab 
	When click on Logout button on site page 
	When we will login with "Customer Analyst" credential and varify Home tab 
		| SitePage_CustomerAnalystUserBot |  
	Then click site tree "clear" button on home tab 
	Then click in the "Portal:Site Search" text box in the portal Home Tab 
	Then enter text "sitePage_siteBot1" in the site search text box on home tab 
	Then verify inactive site "sitePage_siteBot1" is not displayed on home tab 
	Then validate that all the test cases of scenario for portlet executed successfully


# TODO : below scenario was later comments : verify : 1/17/2018 8:49:58 PM	
@BulkSiteImport_GPUP-31866
Scenario: Scenario_22: Verify Bulk Import Site creation on site page.
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_19"   
	Given we are on Site page and search site field is enabled visible 
	Then delete the upload bulk sites from the site tab having name "%UI_TestSiteBulkImport_%"
	When click on "Bulk Site Import" button on site page 
	Then bulk site creation popup should be displayed with following fields:
		| Import New Sites | File Name | No File Choosen | Sample File Link | Choose File  | Import | Close |
	Then select the "INVALID HEADER" csv file by clicking on "Choose File" button
	When click on "Save Import" button on bulk import site popup
	Then verify the error messages shown on the popup for "INVALID HEADER" file
	Then select the "INVALID" csv file by clicking on "Choose File" button
	When click on "Save Import" button on bulk import site popup
	Then bulk site creation popup should be displayed with following fields:
		| Copy Response |
	Then verify the error messages shown on the popup for "INVALID" file
	When click on "Close Import" button on bulk import site popup
	Then user should be navigated to sites page with a label saying "Bulk sites upload process completed."
	Then delete the upload bulk sites from the site tab having name "%UI_TestSiteBulkImport_%"
	When click on "Bulk Site Import" button on site page 
	Then select the "VALID" csv file by clicking on "Choose File" button
	When click on "Save Import" button on bulk import site popup
	Then user should be navigated to sites page with a label saying "Bulk sites upload process completed."
	Then enter one of the site name "UI_TestSiteBulkImport_01" in search field imported by bulk import
	Then click the "Hide Inactive" checkbox on site page  
	Then click on "Search" button on site page 
	Then select the site "UI_TestSiteBulkImport_01" from the site details grid for Site Page 
	Then verify the values of site "UI_TestSiteBulkImport_01" on edit site page
	When click on "X:Close" button on site details page
	Then delete the upload bulk sites from the site tab having name "%UI_TestSiteBulkImport_%"
	
	
@AddEditSiteGPSupport
Scenario: Scenario_22: Verify Add and Edit functionality of Site with GP_Support User
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_22"
	# Create test data from UI
	Then delete the "Sites" used as test data  
		| sitePage_siteBot1 | 
	Then delete the "Tenants" used as test data
		| sitePage_tenantBot1 |
	Then click on "Tenant" tab/button from Site page 
	When click on "Add Tenant" tab/button from Site page 
	Then enter the test data for the following fields to create new testData for Site Page for tenant "First"    
	    | Tenant Name | Tenant Parent | Tenant Type | 
	Then click on "Save Tenant" tab/button from Site page 
	Then user should be navigated to tenant page with a label displaying "Tenant sitePage_tenantBot1 saved."   
	Given we are on Site page and search site field is enabled visible
	  	
	Then click on Logout button on site page 
	
	#Creating Test Data
	Then "delete" various users as test data for site page 
		| SitePage_GPSupportUserBot | 
	Then "create" various users as test data for site page 
		| SitePage_GPSupportUserBot | 
	
	When we will login with "GP Support" credential and varify site tab 
		| SitePage_GPSupportUserBot | 
	Then user verify the "Hide Inactive" checkbox with label		
	Then verify following elements are "Displayed" for GP Support Users
	    | Sites | Users | Tenants | Commissioning | Endpoints | Firmware | Bulk Site Creation | Add Site | Export Icon | 
	And verify following elements are "Not Displayed" for GP Support Users
	    | Datafix | Audit |
	    
	## Create Site with GP Support User 
	When click on "Add Site" tab/button from Site page 
	Then add site popup should be displayed with following fields: 
		| Tenant dropdown | Site Name | Site Description | Address1 | Address2 | Phone1 | Phone2 | City | State/Province | Postal Code | Country | commission Date | Control Date | Area | Has Control | Has Solar Panel | Active | Add Path button | Save | Cancel |
	Then verify "Active" checkbox is unchecked by default     	
	Then enter the test data for the following fields to create new testData for Site Page for Site "First"     
		| Tenant Dropdown | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country | Phone 1 | Phone 2 | Area | System Size | 
    Then select the following check boxes for Site Page  
		| Has Control | Active | Solar Panel |
    Then click on "Save" button of add site popup for Site Page
    Then the Address Verification popup should be displayed with the follwoing fields: 
		| Supplied Address1 | Supplied City | Supplied State/Province | Supplied Postal Code | Supplied Country | Latitude | Longitude | Timezone | Override Checkbox | Get Cordinates | Accept Supplied Data | Accept Verified Data | Message Box |
	When click on "Accept Verified Data" button of add site popup for Site Page 
	Then click on "Save" button of add site popup for Site Page 
	Then user should navigate to Site page with message "Site sitePage_siteBot1 saved." for Site Page
    
    ## Edit Site Page
	Then delete the "Sites" used as test data  
		| sitePage_siteBot2 |  
	When enter the site name "sitePage_siteBot1" in search field
	Then verify "Site Name" column contains site value which is entered in site search field   
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page
	
	Then verify "Delete" button is not displayed
	Then modify the values for "Name" fields and click on save button on "Edit" site modal  
	Then modify the values for "Active" fields and click on save button on "Edit" site modal 
	Then all three dates Commission Date Control Date Decommission Date for the site "sitePage_siteBot1" 
	Then modify the values for "Address" fields and click on save button on "Edit" site modal 
	And the Address Verification popup should be displayed with the follwoing fields: 
		| Supplied Address1 | Supplied City | Supplied State/Province | Supplied Postal Code | Supplied Country | Latitude | Longitude | Timezone | Override Checkbox | Get Cordinates | Accept Supplied Data | Accept Verified Data | Message Box |
	And verify the Message for "Valid" data is "Address is verified." 
	Then click on "Accept Verified Data" button of add site popup for Site Page 
	When click on "Save and Close" button of add site popup for Site Page 
	Then user should navigate to Site page with message "Site sitePage_siteBot2 saved." for Site Page
	
	 ## Verification Start
	When enter the site name "sitePage_siteBot2" in search field 
	Then select the site "sitePage_siteBot2" from the site details grid for Site Page
	Then verify following fields have been modified on Edit Site Page 
	   | Site Name | Address1 | City | Postal Code | Country |
	Then verify "Active" checkbox is checked after modification on Edit Page
	
	  
@AdminUI_PathAccordionSupportUser 
Scenario: Scenario_23: To verify the functionality of Paths accordion 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_08"   
	Given we are on Site page and search site field is enabled visible  	
	
	#Create test data
	Then delete the "Tenant" used as test data
        | sitePage_tenantBot1 | sitePage_tenantBot2 |
	Then delete the "Sites" used as test data    
		| sitePage_siteBot1 | sitePage_siteBot2 |  
    Then click on "TENANT" tab/button from Site page
	When click on "Add Tenant" tab/button from Site page 
	Then enter the test data for the following fields to create new testData for Site Page for tenant "First"    
	    | Tenant Name | Tenant Parent | Tenant Type |  
	When click on "Save Tenant" tab/button from Site page 
	Then user should be navigated to tenant page with a label displaying "Tenant sitePage_tenantBot1 saved."
	When click on "Add Tenant" tab/button from Site page 
	Then enter the test data for the following fields to create new testData for Site Page for tenant "Second"    
	    | Tenant Name | Tenant Parent | Tenant Type |  
	When click on "Save Tenant" tab/button from Site page 
	Then user should be navigated to tenant page with a label displaying "Tenant sitePage_tenantBot2 saved."  
	
	Then click on "Sites" tab/button from Site page
	When click on "Add Site" tab/button from Site page
	Then enter the test data for the following fields to create new testData for Site Page for Site "First"  
		| Tenant Dropdown | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country | Phone 1 | Phone 2 | Area | Path | System Size | 	
	When click on "Save" button of add site popup for Site Page 
	Then click on "Accept Verified Data" button of add site popup for Site Page 
	When click on "Save" button of add site popup for Site Page 
	
	When click on "Add Site" tab/button from Site page 
	Then enter the test data for the following fields to create new testData for Site Page for Site "Second"  
		| Tenant Dropdown | Site ID  | Site Description | Address1 | Address2 | City | State | Postal Code | Country | Phone 1 | Phone 2 | Area | Path | System Size | 
	When click on "Save" button of add site popup for Site Page 
	Then click on "Accept Verified Data" button of add site popup for Site Page 
	When click on "Save" button of add site popup for Site Page 
	Then user should be navigated to sites page with a label saying "Site sitePage_siteBot2 saved." 
	
	#Test start
	Then click on "Sites" tab/button from Site page 
	Then click the "Hide Inactive" checkbox on site page 
	When enter the site name "sitePage_siteBot1" in search field 
	Then select the site "sitePage_siteBot1" from the site details grid for Site Page 	
	Then click on "Paths" accordion
	Then verify that path text associated with the site should be same with the Path that is provided during site creation  
	Then verify the text box should contain site path 
	
	#Update tenant 
	Then click on "Paths" accordion
	Then click on "Details" accordion
	When change the tenant to "sitePage_tenantBot2" from tenant dropdown 
	When click on "Save" button of add site popup for Site Page
	Then click on "Paths" accordion 
	And click on "Add Path" button of add site popup for Site Page 
	And the new field is displayed on the edit site page 
	Then verify top level path is auto populated on the basis of tenant selection edit site page 
	Then verify top level path "/sitePage_tenantBot2" is displayed in the site path field
	
	#Update Path
	Then add "/sitePage_tenantBot2" with the existing path on edit site page 
	Then it shows a warning message if the new string is added "This would create a new path under ######## tenant, are you sure?" for "sitePage_tenantBot2" on edit site page 
	When click on "Save and Close" button of add site popup for Site Page 
	Then validate that all the test cases of scenario for portlet executed successfully 
	
@DeleteTestData 
Scenario: Scenario_21: Delete all the test data 
	Given User is already logged in to Admin URL and is already present at the Sitetab for "Scenario_20"   
	Given we are on Site page and search site field is enabled visible 
	Then "Delete" the following test data of PVAudit Table 
		| sitePage_siteBot1 | sitePage_siteBot2 |
	Then delete the "Site" data used as test data for site page 
		| sitePage_siteBot1 | sitePage_siteBot2 | sitePage_siteBot3 |
	Then delete the "Tenant" data used as test data for site page 
		| sitePage_tenantBot |
	Then delete the "Users" data used as test data for site page 
		| SitePage_GPSupportUserBot | SitePage_CustomerAnalystUserBot | SitePage_GPAnalystUserBot |
	Then delete the upload bulk sites from the site tab having name "%UI_TestSiteBulkImport_%"
	
	
	
@CloseBrowser 
Scenario: 
	Scenario_quit: Close the browser after finishing execution of the Authentication portlet test cases 
	Then close the browser 