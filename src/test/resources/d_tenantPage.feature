@AdminUI_TenantPage 
Feature: Tenant Page Test 
	Here we will test the tenant Page functionality.
	For this, we will login with admin credentials and will test
	the different scenarios including behaviour and function test of the tenant page.
	for testing first we will delete the test data (if exist) and then recreate multiple tenants
	
@InitializeBrowserForAddTestData 
Scenario: 
	Scenario_initialize: Initialize the browser before execution of the test cases of Add Test Data 
	Given Initialize resources in the beginning of each scenario 
	
@AdminUI_TenantPage
Scenario: Scenario_01 Login with admin credentials and navigate to Tenant Page 
	Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_01" 
	Given we are on Tenant page and search Tenant field is enabled visible  
	Then verify the tenant page by confirming the availability of tenant grid    

@AdminUI_TenantSoring 
Scenario: Scenario_02 Login with admin credentials and navigate to Tenant Page 
	Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_02" 
	Given we are on Tenant page and search Tenant field is enabled visible 
	Then verify the columns in tenant details grid 
		| Name | Tenant Type | Parent | Parent Tenant Type | Active |
	Then verify the sorting functionality of columns in tenant details grid  
		| Name | Tenant Type | Parent | Parent Tenant Type | Active |
	Then verify the default size of page navigation 
	
@AdminUI_TenantPagination 
Scenario: Scenario_03 Verify the paging functionality 
	Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_03" 
	Given we are on Tenant page and search Tenant field is enabled visible 
	When click on "next" link 
	Then "Next" page should displayed 
	When click on "prev" link 
	Then "Previous" page should displayed 
	When click on "last" link 
	Then "Last" page should displayed 
	When click on "first" link 
	Then "First" page should displayed 
	When enter random page number in page link text 
	Then user should be navigated on the given random page 

@AdminUI_TenantResources 
Scenario: Scenario_04 verify the add tenant functionality 
    Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_04" 
	Given we are on Tenant page and search Tenant field is enabled visible
	Then delete the "Tenant" data used as test data for tenant page    
		| tenantPage_tenantBot1 | TenantPage_tenantBot1 | p_TenantPage_tenantBot1 | cP_TenantPage_tenantBot1 | p_pTenantPage_tenantBot1 | c_pTenantPage_tenantBot1 | cP_pTenantPage_tenantBot1 | p_cPTenantPage_tenantBot1 | c_cPTenantPage_tenantBot1 | cP_cPTenantPag_tenantBot1 | c_TenantPage_tenantBot1 | pTenantPage_tenantBot1 | cPTenantPage_tenantBot1 |
	When click on "Add Tenant" button on Tenant Page
	Then add tenant popup should be displayed with following fields: 
		| Name | Parent | Tenant Type | Active |
	Then verify the dropdown values for "Tenant Type" field in Add Tenant popup  
		| PARENT | CHANNEL_PARTNER | CUSTOMER | 
	Then verify "Root" is not an option in Tenant Type field on add tenant popup 
	Then verify "Parent of Gridpoint" is not an option in Tenant Type field on add tenant popup 
	Then verify following button status on add tenant popup 
		| Disable | Enable |
		| Save | Cancel |
	Then enter the "tenantPage_tenantBot1" in "Name" field on add tenant popup  
	Then select "GridPoint Test Automation" parent tenant from "Select_Tenant_Parent" parent selection box  
	Then verify Active flag is default to true 
	When click on "Save" button on Tenant Page
	Then user should be navigated to tenant page with a label saying "Tenant tenantPage_tenantBot1 saved." 
	When click on "Add Tenant" button on Tenant Page
	Then enter the "pTenantPage_tenantBot1" in "Name" field on add tenant popup 
	Then select "GridPoint Test Automation" parent tenant from "Select_Tenant_Parent" parent selection box
	Then select "Parent" type from "Tenant Type" dropdown field on Add Tenant popup  
	When click on "Save" button on Tenant Page
	Then user should be navigated to tenant page with a label saying "Tenant pTenantPage_tenantBot1 saved." 
	When click on "Add Tenant" button on Tenant Page 
	Then enter the "cPTenantPage_tenantBot1" in "Name" field on add tenant popup 
	Then select "GridPoint Test Automation" parent tenant from "Select_Tenant_Parent" parent selection box 
	Then select "CHANNEL_PARTNER" type from "Tenant Type" dropdown field on Add Tenant popup 
	When click on "Save" button on Tenant Page 
	Then user should be navigated to tenant page with a label saying "Tenant cPTenantPage_tenantBot1 saved." 
	Then enter the tenant name "cPTenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "cPTenantPage_tenantBot1" from the tenant details grid 
	Then verify the values for newly created tenant "cPTenantPage_tenantBot1" 		
	
@AdminUI_TenantSearch 
Scenario: Scenario_05 Verify the tenant search functionality 
    Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_05" 
	Given we are on Tenant page and search Tenant field is enabled visible
	Then verify the search output by entering value "tenantPage_tenantBot1:Init3Char" in search field 
	Then verify the search output by entering value ":RANDOM" in search field 
	Then verify the search output by entering value "tenantPage_tenantBot1:BACKSPACE" in search field 
	Then verify the search output by entering value "tenantPage_tenantBot1:DELETE" in search field 
	Then verify the pagination by entering value "tenantPage_tenantBot1:Pagination" in search field 
	Then verify the search output by entering value ":LONGTEXT" in search field 	
		
@AdminUI_RootTenant 
Scenario: Scenario_06 verify there is no Save button in the Root tenant page and all the fields are in read only mode. 
	Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_06" 
	Given we are on Tenant page and search Tenant field is enabled visible 
	When enter the tenant name "GridPoint" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "GridPoint" from the tenant details grid 
	Then root tenant details page should be displayed. 
	Then verify there should not be any "Delete" button 
	Then verify there should not be any "Save" button 
	Then verify following fields should be in Read-only mode 
		| Name | Parent |	Tenant Type |
	Then click on "Back to Tenants" button on Tenant Page	
	
@AdminUI_TenantSelection 
Scenario: Scenario_07 Select the tenant and verify the details. 
	Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_07" 
	Given we are on Tenant page and search Tenant field is enabled visible 
	Then verify the selection of "RANDOM" tenant after clicking on it in tenant detail grid 
	Then Verify the presence of "Edit Tenant:Save" button on Tenants Detail page 
	Then Verify the presence of "Edit Tenant:Cancel" button on Tenants Detail page 
	When click on the "X" button on Tenant Detail page 
	Then user should be navigated on tenant detail grid 	
	
@AdminUI_AddVariousTenantUnderCustomerChannelPartnerParent 
Scenario: Scenario_08 Add various tenants 
	Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_08" 
	Given we are on Tenant page and search Tenant field is enabled visible 
		
	#Add tenant in customer Tenant
	When click on "Add Tenant" button on Tenant Page 
	Then enter the "p_TenantPage_tenantBot1" in "Name" field on add tenant popup 
	Then select "tenantPage_tenantBot1" parent tenant from "Select_Parent" parent selection box 
	Then select "Parent" type from "Tenant Type" dropdown field on Add Tenant popup 
	When click on "Save" button on Tenant Page 
	Then user should be navigated to tenant page with a label saying "Tenant p_TenantPage_tenantBot1 saved." 
	When click on "Add Tenant" button on Tenant Page 
	Then enter the "cP_TenantPage_tenantBot1" in "Name" field on add tenant popup 
	Then select "tenantPage_tenantBot1" parent tenant from "Select_Parent" parent selection box
	Then select "Channel Partner" type from "Tenant Type" dropdown field on Add Tenant popup 
	When click on "Save" button on Tenant Page 
	Then user should be navigated to tenant page with a label saying "Tenant cP_TenantPage_tenantBot1 saved." 
	When click on "Add Tenant" button on Tenant Page 
	Then enter the "c_TenantPage_tenantBot1" in "Name" field on add tenant popup 
	Then select "tenantPage_tenantBot1" parent tenant from "Select_Parent" parent selection box 
	Then select "Customer" type from "Tenant Type" dropdown field on Add Tenant popup 
	When click on "Save" button on Tenant Page 
	Then user should be navigated to tenant page with a label saying "Tenant c_TenantPage_tenantBot1 saved." 
	
	#Add tenant in parent Tenant
	When click on "Add Tenant" button on Tenant Page 
	Then enter the "p_pTenantPage_tenantBot1" in "Name" field on add tenant popup 
	Then select "pTenantPage_tenantBot1" parent tenant from "Select_Parent" parent selection box 
	Then select "Parent" type from "Tenant Type" dropdown field on Add Tenant popup 
	When click on "Save" button on Tenant Page 
	Then user should be navigated to tenant page with a label saying "Tenant p_pTenantPage_tenantBot1 saved." 
	When click on "Add Tenant" button on Tenant Page 
	Then enter the "c_pTenantPage_tenantBot1" in "Name" field on add tenant popup 
	Then select "pTenantPage_tenantBot1" parent tenant from "Select_Parent" parent selection box 
	Then select "Customer" type from "Tenant Type" dropdown field on Add Tenant popup 
	When click on "Save" button on Tenant Page 
	Then user should be navigated to tenant page with a label saying "Tenant c_pTenantPage_tenantBot1 saved." 
	When click on "Add Tenant" button on Tenant Page 
	Then enter the "cP_pTenantPage_tenantBot1" in "Name" field on add tenant popup 
	Then select "pTenantPage_tenantBot1" parent tenant from "Select_Parent" parent selection box 
	Then select "Channel Partner" type from "Tenant Type" dropdown field on Add Tenant popup 
	When click on "Save" button on Tenant Page 
	Then user should be navigated to tenant page with a label saying "Tenant cP_pTenantPage_tenantBot1 saved." 
	
	#Add tenant in channel partner Tenant
	When click on "Add Tenant" button on Tenant Page 
	Then enter the "p_cPTenantPage_tenantBot1" in "Name" field on add tenant popup 
	Then select "cPTenantPage_tenantBot1" parent tenant from "Select_Parent" parent selection box 
	Then select "Parent" type from "Tenant Type" dropdown field on Add Tenant popup 
	When click on "Save" button on Tenant Page 
	Then user should be navigated to tenant page with a label saying "Tenant p_cPTenantPage_tenantBot1 saved." 
	When click on "Add Tenant" button on Tenant Page 
	Then enter the "c_cPTenantPage_tenantBot1" in "Name" field on add tenant popup 
	Then select "cPTenantPage_tenantBot1" parent tenant from "Select_Parent" parent selection box 
	Then select "Customer" type from "Tenant Type" dropdown field on Add Tenant popup 
	When click on "Save" button on Tenant Page 
	Then user should be navigated to tenant page with a label saying "Tenant c_cPTenantPage_tenantBot1 saved." 
	When click on "Add Tenant" button on Tenant Page 
	Then enter the "cP_cPTenantPag_tenantBot1" in "Name" field on add tenant popup 
	Then select "cPTenantPage_tenantBot1" parent tenant from "Select_Parent" parent selection box
	Then select "CHANNEL_PARTNER" type from "Tenant Type" dropdown field on Add Tenant popup 
	When click on "Save" button on Tenant Page 
	Then user should be navigated to tenant page with a label saying "Tenant cP_cPTenantPag_tenantBot1 saved."	
	
@AdminUI_AddTenantValidation 
Scenario: Scenario_09 verify the validation of add tenant functionality 
	Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_09" 
	Given we are on Tenant page and search Tenant field is enabled visible 
	When click on "Add Tenant" button on Tenant Page 
	Then enter the "text and delete it" in "Name" field on add tenant popup 
	Then valid error message "Tenants name cannot be blank" should be displayed for "Name" field on add tenant popup 
	Then verify following button status on add tenant popup 
		| Disable | Enable |
		| Save | Cancel |
	Then enter the "long text with more than 25 characters" in "Name" field on add tenant popup 
	Then valid error message "Maximum 25 letters allowed" should be displayed for "Name" field on add tenant popup 
	Then verify following button status on add tenant popup 
		| Disable | Enable |
		| Save | Cancel |
	When enter the "invalid special character" in "Name" field on add tenant popup 
	Then valid error message "Tenants name must start with a letter or a number and then be followed with characters (including numbers, underscores, dashes, commas, ampersands, apostrophe and spaces)" should be displayed for "Name" field on add tenant popup 
	Then verify following button status on add tenant popup 
		| Disable | Enable |
		| Save | Cancel |
	When click on "Cancel" button on add tenant popup  
	Then confirmation box should displayed with message "All Changes will be lost. Are you sure?" for tenant tab
	When click on "Cancel" button on confirmation box    
	Then user should remain on add tenant popup 
	When click on "Cancel" button on add tenant popup 
	Then confirmation box should displayed with message "All Changes will be lost. Are you sure?" for tenant tab
	When click on "Ok" button on confirmation box 
	Then user should be navigated on tenant detail grid 
	When click on "Add Tenant" button on Tenant Page 
	Then enter the "tenantPage_tenantBot1" in "Name" field on add tenant popup 
	When click on "Save" button on Tenant Page 
	Then valid error message "Tenant with same name exist" should be displayed for "Name" field on add tenant popup 	
	
@AdminUI_EditTenantGP_Admin 
Scenario: Scenario_10 Verify the Edit Tenant property for GP Admin User 
	Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_10" 
	Given we are on Tenant page and search Tenant field is enabled visible 
	Then enter the tenant name "pTenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "pTenantPage_tenantBot1" from the tenant details grid 
	And verify that "Subtenant Type" column should be displayed 
	Then select "PARENT" from "Tenant Type" dropdown
	When click on "Save" button on Tenant Page 
	Then enter the tenant name "p_TenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "p_TenantPage_tenantBot1" from the tenant details grid 
	Then select "CUSTOMER" from "Tenant Type" dropdown 
	When click on "Save" button on Tenant Page 
	Then enter the tenant name "p_TenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "p_TenantPage_tenantBot1" from the tenant details grid 
	Then verify that dropdown value of "Tenant Type" has been updated to "CUSTOMER" 
	Then select "CHANNEL_PARTNER" from "Tenant Type" dropdown 
	When click on "Save" button on Tenant Page 
	Then enter the tenant name "p_TenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "p_TenantPage_tenantBot1" from the tenant details grid 
	Then verify that dropdown value of "Tenant Type" has been updated to "CHANNEL_PARTNER" 
	Then click on "Save" button on Tenant Page 
	Then enter the tenant name "c_TenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "c_TenantPage_tenantBot1" from the tenant details grid 
	Then select "PARENT" from "Tenant Type" dropdown 
	When click on "Save" button on Tenant Page
	Then enter the tenant name "c_TenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "c_TenantPage_tenantBot1" from the tenant details grid 
	Then verify that dropdown value of "Tenant Type" has been updated to "PARENT" 
	Then select "CUSTOMER" from "Tenant Type" dropdown 
	When click on "Save" button on Tenant Page 
	Then enter the tenant name "c_TenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "c_TenantPage_tenantBot1" from the tenant details grid 
	Then verify that dropdown value of "Tenant Type" has been updated to "CUSTOMER" 
	Then select "CHANNEL_PARTNER" from "Tenant Type" dropdown 
	When click on "Save" button on Tenant Page 
	Then enter the tenant name "c_TenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "c_TenantPage_tenantBot1" from the tenant details grid 
	Then verify that dropdown value of "Tenant Type" has been updated to "CHANNEL_PARTNER" 
	Then click on "Save" button on Tenant Page 
	Then enter the tenant name "cP_TenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "cP_TenantPage_tenantBot1" from the tenant details grid 
	Then select "PARENT" from "Tenant Type" dropdown 
	When click on "Save" button on Tenant Page 
	Then enter the tenant name "cP_TenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "cP_TenantPage_tenantBot1" from the tenant details grid 
	Then verify that dropdown value of "Tenant Type" has been updated to "PARENT" 
	Then select "CUSTOMER" from "Tenant Type" dropdown 
	When click on "Save" button on Tenant Page 
	Then enter the tenant name "cP_TenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "cP_TenantPage_tenantBot1" from the tenant details grid 
	Then verify that dropdown value of "Tenant Type" has been updated to "CUSTOMER" 
	Then select "CHANNEL_PARTNER" from "Tenant Type" dropdown 
	When click on "Save" button on Tenant Page 
	Then enter the tenant name "cP_TenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page
	Then select the tenant "cP_TenantPage_tenantBot1" from the tenant details grid 
	Then verify that dropdown value of "Tenant Type" has been updated to "CHANNEL_PARTNER"	
	
@AdminUI_TenantDetailsEditTenant
Scenario: Scenario_11 verify the tenant details model 
    Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_11" 
	Given we are on Tenant page and search Tenant field is enabled visible
	Then enter the tenant name "GridPoint Test Automation" in search field 
	And click on "Search" button on Tenant Page
	Then select the tenant "GridPoint Test Automation" from the tenant details grid 
	Then verify the "Label:Active Sites" on tenant details popup 
	Then verify the "Label:Total Sites" on tenant details popup  
	
#TODO: compare below scenario with above scenario
@SelectSiteFromEditTenant 
Scenario: Scenario_12 verify the tenant details model 
	Given we are on Tenant page and search Tenant field is enabled visible 
	When click on "Add Site" button on Tenant Page 
	Then enter the test data for the following fields to create new site 
		| TENANT DROPDOWN | SITE NAME | SITE DESCRIPTION | Address 1 | Address 2 | CITY | STATE/PROVINCE  | POSTAL CODE | AREA | PATHS 	| Phone 1		 | Phone 2 		  | Active |
	When click on the "save" button on add site popup 
	When click on the "Accept Verified Data" button on add site popup 
	When click on the "Save" button on add site popup 
	Given click on "Tenants" Tab 
	When enter the tenant name "tenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "tenantPage_tenantBot1" from the tenant details grid 
	
	#GPUP-29942:Sites area in Edit Tenant modal no longer appear
	Then verify the "Label:Active Sites" on tenant details popup 
	Then verify the "Label:Total Sites" on tenant details popup 
	Then verify the "Total Sites Count" on tenant detail popup for selected tenant "tenantPage_tenantBot1" 
	When click on the "X" button on Tenant Detail page 
	When enter the tenant name "GridPoint Test Automation" in search field 
	And click on "Search" button on Tenant Page
	Then select the tenant "GridPoint Test Automation" from the tenant details grid 
	Then verify the "Label:Active Sites" on tenant details popup 
	Then verify the "Label:Total Sites" on tenant details popup 
	Then verify the "Total Sites Count" on tenant detail popup for selected tenant "GridPoint Test Automation" 
	
@AdminUI_SubtenantsSection 
Scenario: Scenario_12 verify the Subtenants section should have data or not on tenant details section 
	Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_12" 
	Given we are on Tenant page and search Tenant field is enabled visible 
	When enter the tenant name "tenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "tenantPage_tenantBot1" from the tenant details grid 
	Then verify Active flag is default to true 
	Then verify "Root" is not an option in Tenant Type field on edit tenant popup 
 Then verify the values for Tenant Type field in Add Tenant popup 
		| Parent | Channel Partner | Customer | 
	Then verify delete button is not available for the tenant who is associated with any site or subtenant 

## TODO : added below steps by k
When click on "X" button on Tenant Page
	When click on "Add Tenant" button on Tenant Page 
	Then enter the "c_TenantPage_tenantBot1" in "Name" field on add tenant popup 
	Then the "Select_Parent" name "tenantPage_tenantBot1" from parent selection box 
	Then select "CUSTOMER" type from tenant type field 
	When click on "Save" button on Tenant Page 
	Then user should be navigated to tenant page with a label saying "Tenant c_TenantPage_tenantBot1 saved." 	
	Then select the tenant "c_TenantPage_tenantBot1" from the tenant details grid 
	Then select the "c_TenantPage_tenantBot1" tenant in the Subtenants section 
################	
	
	Then select the "c_TenantPage_tenantBot1" tenant in the Subtenants section 
	Then verify delete button visibility for "GP Admin" users 
	And click on "Delete" button on Tenant Page 
	Then confirmation box on detail tenant popup should displayed with message "Are you sure you want to delete this Tenant?" 
	Then click on "Cancel_Delete" button on delete tenant popup
	And click on "Delete" button on Tenant Page 
	Then click on "Ok_Delete" button on delete tenant popup 
	Then user should be navigated to tenant page with a label saying "Tenant c_TenantPage_tenantBot1 deleted." 
	
	When click on "Add Tenant" button on Tenant Page 
	Then enter the "c_TenantPage_tenantBot1" in "Name" field on add tenant popup 
	Then select "tenantPage_tenantBot1" parent tenant from "Select_Parent" parent selection box
	Then select "Customer" type from "Tenant Type" dropdown field on Add Tenant popup 
	When click on "Save" button on Tenant Page 
	Then user should be navigated to tenant page with a label saying "Tenant c_TenantPage_tenantBot1 saved." 
	When enter the tenant name "TenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "TenantPage_tenantBot1" from the tenant details grid 
	Then verify pagination should be disabled if "Sites" section have no data and verify error message "No records found." 
	Then verify pagination should be disabled if "SUBTENANTS" section have no data and verify error message "No records found." 
	When click on the "X" button on Tenant Detail page  
	Then user should be navigated on tenant detail grid 
	
	When click on "Add Tenant" button on Tenant Page 
	# TODO above step replaces the below commented steps : 1/24/2018 8:51:27 PM
	When enter the tenant name "p_TenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "p_TenantPage_tenantBot1" from the tenant details grid
	
	Then enter the "tenantPage_tenantBot1" in "Name" field on add tenant popup 
	When click on "Save" button on Tenant Page 
	Then valid error message "Name already exists" should be displayed for "Name" field on add tenant popup 
	
@AdminUI_SiteSection 
Scenario: Scenario_13 verify the Sites section should have data or not on tenant details section 
    Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_13" 
	Given we are on Tenant page and search Tenant field is enabled visible 
    When enter the tenant name "tenantPage_tenantBot1" in search field 
    And click on "Search" button on Tenant Page
	Then select the tenant "tenantPage_tenantBot1" from the tenant details grid 
	Then click on the "X" button on Tenant Detail page 
	Then click on "Sites" Tab 
	Then user should navigated on "Sites" page 
	Then click on "Tenants" Tab 
	Then user should navigated on "Tenants" page 
	
@AdminUI_HideInactiveCheckbox 
Scenario: Scenario_14 Verify the functionality of Hide Inactive checkbox and search an inactive site 
	Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_14" 
	Given we are on Tenant page and search Tenant field is enabled visible 
	Then enter the tenant name "tenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "tenantPage_tenantBot1" from the tenant details grid 
	Then click on "Active" checkbox on edit tenant page 
	When click on "Save" button on Tenant Page 
	Then enter the tenant name "tenantPage_tenantBot1" in search field 
	Then verify the search output by entering value "tenantPage_tenantBot1:INACTIVE" in search field 
	Then click the "Hide Inactive:unchecked" checkbox on tenant page 
	Then select the tenant "tenantPage_tenantBot1" from the tenant details grid 
	Then click on "Active" checkbox on edit tenant page 
	When click on "Save" button on Tenant Page 
	Then click the "Hide Inactive:checked" checkbox on tenant page 
	Then enter the tenant name "tenantPage_tenantBot1" in search field 
	Then verify the search output by entering value "tenantPage_tenantBot1:ACTIVE" in search field 
	
# TODO : beloe steps were commented. Have to check this
@AdminUI_ExportIconTenant 
Scenario: Scenario_15 Verify the functionality of Export icon. 
	Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_15" 
	Given we are on Tenant page and search Tenant field is enabled visible 
	Then verify Export icon is display on Tenant tab 
		Then click on Export icon on Tenant tab
	#	Then open the downloaded report for tenant tab
	Then click the "Hide Inactive:unchecked" checkbox on tenant page 
	#	Then click on Export icon on Tenant tab
	#	Then open the downloaded report for tenant tab
	Then click the "Hide Inactive:checked" checkbox on tenant page 
	#	Then click on Export icon on Tenant tab
	#	Then open the downloaded report for tenant tab
	Then enter the tenant name "tenantPage_tenantBot1" in search field 
	#	Then click on Export icon on Tenant tab
	#	Then open the downloaded report for tenant tab
	
@AdminUI_VerifyTenantOnVariousTabs 
Scenario: Scenario_16 Verify the Edit Tenant property for GP Support User
    Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_16" 
	Given we are on Tenant page and search Tenant field is enabled visible
	Given click on "Users" Tab
	
	# TODO : lots of changes in the following commented steps : 1/22/2018 8:04:38 PM
	Then delete the following test data before creating new test data 
		| p_TenantPage_tenantBot1 | cP_TenantPage_tenantBot1 | c_TenantPage_tenantBot1 | tenantPage_tenantBot1 |
	Then delete the "Endpoints" test data for tenant page
		| tenantPage_endpointBot1 | 	
	Then delete the "Sites" for tenant test data
		| tenantPage_siteBot1 | 
	Then we are on tenant page and search tenant field is enabled 
	When click on "Add Tenant" button on Tenant Page
	Then enter the "tenantPage_tenantBot1" in "Name" field on add tenant popup 
	Then the "Select_Tenant_Parent" name "GridPoint Test Automation" from parent selection box 
	When click on "Save" button on Tenant Page
	Then user should be navigated to tenant page with a label saying "Tenant tenantPage_tenantBot1 saved." 	
	
	Given click on "Sites" Tab 
	When click on "Add Site" button on Tenant Page 
	Then enter the test data for the following fields to create new site 
		| TENANT:DROPDOWN | SITE NAME | SITE DESCRIPTION | Address 1 | Address 2 | CITY | STATE/PROVINCE  | POSTAL CODE | AREA | PATHS 	| Phone 1		 | Phone 2 		  | Active |
	When click on the "save" button on add site popup 
	When click on the "Accept Verified Data" button on add site popup 
	When click on the "Save" button on add site popup 

	Given click on "Endpoints" Tab
	When click on "Add Endpoint" button on Tenant Page
	Then enter the values for new endpoint for tenant verification
	Then click on "Save:Endpoint" button on Tenant Page
	Then user should be navigated to endpoint page with a text saying "Endpoint tenantPage_endpointBot1 saved." 
	When enter endpoint id "tenantPage_endpointBot1" in search text box on the Endpoint page
	And  click on "Search" button on endpoint detail page
	Then select the entered endpoint id "tenantPage_endpointBot1" from the endpoint grid 
	Then click the "Save and Close" button on the edit endpoint page	
	
	Then click on "Users" Tab 
	When enter the username "autoSupportTest" in search field 
	Then select the user "autoSupportTest" from the users details grid      
	Then click on "Permission" accordion on userpage 
	Then select a tenant "tenantPage_tenantBot1" from the tenant drop down on edit user page 
	When click on "Add All" button on edit user   
	When click on "Permission:Save and Close" button on edit user 
	
	#Test Start
	Then click on "Tenants" Tab
	When enter the tenant name "tenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "tenantPage_tenantBot1" from the tenant details grid 
	Then verify Active flag is default to true 
	Then verify "Root" is not an option in Tenant Type field on edit tenant popup 
	Then verify the dropdown values for "Tenant Type" field in Add Tenant popup 
		| Parent | Channel Partner | Customer | 
	Then verify delete button is not available for the tenant who is associated with any site or subtenant 
	When click on "X" button on Tenant Page
	Then click on the portal button to logout from the application
	Then login with user "autoSupportTest" to verify tenant  
	Then click on "Tenants" Tab 
	Then enter the tenant name "tenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "tenantPage_tenantBot1" from the tenant details grid 
	Then verify the Edit modal is open and edit Property is not available for GP_Support users 
	Then verify delete button visibility for "GP Support" users 
	When click on "X" button on Tenant Page 	
	







# TODO : same scenario is copeid above as well
@AdminUI_VerifyTenantOnVariousTabs 
Scenario: Scenario_11 Verify the Edit Tenant property for GP Support User
    Given User is already logged in to Admin URL and is already present at the Tenant Tab for "Scenario_10" 
	Given we are on Tenant page and search Tenant field is enabled visible
	Given click on "Users" Tab

#	#Create test data
	Then "delete" various users as test data for tenant page 
		| tenantPage_GPSupportUserBot |
	Then "create" various users as test data for tenant page 
		| tenantPage_GPSupportUserBot |
	Then delete the following test data before creating new test data 
		| p_TenantPage_tenantBot1 | cP_TenantPage_tenantBot1 | c_TenantPage_tenantBot1 | tenantPage_tenantBot1 |
	Then delete the "Endpoints" test data for tenant page
		| tenantPage_endpointBot1 | 	
	Then delete the "Sites" for tenant test data
		| tenantPage_siteBot1 | 
	Then we are on tenant page and search tenant field is enabled 
	When click on "Add Tenant" button on Tenant Page
	Then enter the "tenantPage_tenantBot1" in "Name" field on add tenant popup 
	Then the "Select_Tenant_Parent" name "GridPoint Test Automation" from parent selection box 
	When click on "Save" button on Tenant Page
	Then user should be navigated to tenant page with a label saying "Tenant tenantPage_tenantBot1 saved." 	
	
	Given click on "Sites" Tab 
	When click on "Add Site" button on Tenant Page 
	Then enter the test data for the following fields to create new site 
		| TENANT:DROPDOWN | SITE NAME | SITE DESCRIPTION | Address 1 | Address 2 | CITY | STATE/PROVINCE  | POSTAL CODE | AREA | PATHS 	| Phone 1		 | Phone 2 		  | Active |
	When click on the "save" button on add site popup 
	When click on the "Accept Verified Data" button on add site popup 
	When click on the "Save" button on add site popup 

	Given click on "Endpoints" Tab
	When click on "Add Endpoint" button on Tenant Page
	Then enter the values for new endpoint for tenant verification
	Then click on "Save:Endpoint" button on Tenant Page
	Then user should be navigated to endpoint page with a text saying "Endpoint tenantPage_endpointBot1 saved." 
	When enter endpoint id "tenantPage_endpointBot1" in search text box on the Endpoint page
	And  click on "Search" button on endpoint detail page
	Then select the entered endpoint id "tenantPage_endpointBot1" from the endpoint grid 
	Then click the "Save and Close" button on the edit endpoint page	
	
	Then click on "Users" Tab 
	When enter the username "tenantPage_GPSupportUserBot" in search field 
	Then select the user "tenantPage_GPSupportUserBot" from the users details grid 
	Then click on "Permission" accordion on userpage 
	Then select a tenant "tenantPage_tenantBot1" from the tenant drop down on edit user page 
	When click on "Add All" button on edit user 
	When click on "Permission:Save and Close" button on edit user 
	
#	#Test Start
	Then click on "Tenants" Tab
	When enter the tenant name "tenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "tenantPage_tenantBot1" from the tenant details grid 
	Then verify Active flag is default to true 
	Then verify "Root" is not an option in Tenant Type field on edit tenant popup 
	Then verify the values for Tenant Type field in Add Tenant popup 
		| Parent | Channel Partner | Customer | 
	Then verify delete button is not available for the tenant who is associated with any site or subtenant 
	When click on "X" button on Tenant Page
	Then click on the portal button to logout from the application
	Then login with user "tenantPage_GPSupportUserBot" to verify tenant  
	Then click on "Tenants" Tab 
	Then enter the tenant name "tenantPage_tenantBot1" in search field 
	And click on "Search" button on Tenant Page 
	Then select the tenant "tenantPage_tenantBot1" from the tenant details grid 
	Then verify the Edit modal is open and edit Property is not available for GP_Support users 
	Then verify delete button visibility for "GP Support" users 
	When click on "X" button on Tenant Page 
	
	# GPUP-29942:Sites area in Edit Tenant modals no longer appear

# TODO: its not commented in K branch	
#@DeleteTestData
#Scenario: Scenario_16 Delete all the test data
#	Given we are on tenant page and search tenant field is enabled 	
#	Then "delete" various users as test data for tenant page 
#		|tenantPage_GPSupportUserBot|
#	Then delete the following test data before creating new test data 
#		| cPTenantPage_tenantBot1 | p_TenantPage_tenantBot1 | cP_TenantPage_tenantBot1 | p_pTenantPage_tenantBot1 | c_pTenantPage_tenantBot1 | cP_pTenantPage_tenantBot1 | p_cPTenantPage_tenantBot1 | c_cPTenantPage_tenantBot1 | cP_cPTenantPag_tenantBot1 | c_TenantPage_tenantBot1 | pTenantPage_tenantBot1 |  tenantPage_tenantBot1 |
