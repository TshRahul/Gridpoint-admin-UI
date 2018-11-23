@UserPage 
Feature: User Page test 
	Here we will test the user Page functionality.
	For this, we will login with admin credentials and will test
	the different scenarios including behavior and function test of the user page.
<<<<<<< Updated upstream
=======

#Background: Check for page refresh requirement for each test scenario 
#	And check if the refresh required for the portlet test scenario
	
@InitializeBrowserForAddTestData, @Test,@abc
Scenario: 
	Scenario_initialize: Initialize the browser before execution of the test cases of Add Test Data 
	Given Initialize resources in the beginning of each scenario
>>>>>>> Stashed changes
	
#@UserPage_UserLogin 
#Scenario: Scenario_01: Login with admin credentials and navigate to User Page 
#	Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_01"  
#	Given we are on User page and search user field is enabled visible 
##	When initialize dummy users in database for different roles and remove dependencies associated with "userRolePage_tenantBot1" 
##		| URP_GPAdmin_testBot | URP_GPSupport_testBot | URP_GPAnalyst_testBot | URP_CustomerAnalyst_testBot | URP_CustomerSiteManager_testBot |

## TODO : Initially the above scenario has steps as below. Compare it and validate : 01/05/2018 06:54:35
<<<<<<< Updated upstream
@UserLogin 
=======
@UserLogin, @Test
>>>>>>> Stashed changes
Scenario: Scenario_01: Login with admin credentials and navigate to User Page 
	Given we launch the browser with admin-UI URL and login with set of valid admin credentials 
	When we will land on Site page, we will click on "User" tab 
	Then verify the "User" page by confirming the availability of user grid. 
	When initialize dummy users in database for different roles and remove dependencies associated with "userRolePage_tenantBot1" 
		| URP_GPAdmin_testBot | URP_GPSupport_testBot | URP_GPAnalyst_testBot | URP_CustomerAnalyst_testBot | URP_CustomerSiteManager_testBot |
		
		
@UserPage_UserTab 
Scenario: Scenario_01: Verify the visibility of User tab 
	Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_01"  
	Given we are on User page and search user field is enabled visible 
		
@UserPage_UserGrid 
Scenario: Scenario_02: Verify the visibility of User tab 
	Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_02"  
	Given we are on User page and search user field is enabled visible 
	Then verify the visibility of following columns in user details grid 
		| Username | Email | First Name | Last Name | Enabled | Locked | Role |		
	# TODO : verify below removed step : 01/05/2018 06:54:35
	Then verify the sorting functionality of columns in user details grid 
		| Username | Email | First Name | Last Name | Enabled | Locked | Role |	
		
<<<<<<< Updated upstream
@UserPage_UserPagination 
Scenario: Scenario_03: Verify the pagination functionality on user detail grid. 
	Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_03"  
	Given we are on User page and search user field is enabled visible
	When click on "next" link in user grid 
	Then "Next" page should displayed in user grid 
	When click on "last" link in user grid 
	Then "Last" page should displayed in user grid  
	When click on "prev" link in user grid 
	Then "Previous" page should displayed in user grid 
	When click on "first" link in user grid 
	Then "First" page should displayed in user grid 
	When enter random page number in page link text in user grid 
	Then user should be navigated on the given random page in user grid 
		
@UserPage_UserResources 
=======
#@UserPage_UserPagination 
#Scenario: Scenario_03: Verify the pagination functionality on user detail grid. 
#	Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_03"  
#	Given we are on User page and search user field is enabled visible
#	When click on "next" link in user grid 
#	Then "Next" page should displayed in user grid 
#	When click on "last" link in user grid 
#	Then "Last" page should displayed in user grid  
#	When click on "prev" link in user grid 
#	Then "Previous" page should displayed in user grid 
#	When click on "first" link in user grid 
#	Then "First" page should displayed in user grid 
#	When enter random page number in page link text in user grid 
#	Then user should be navigated on the given random page in user grid 
		
@UserPage_UserResources,@Test
>>>>>>> Stashed changes
Scenario: Scenario_04: verify the add user functionality. 
	Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_04"  
	Given we are on User page and search user field is enabled visible
	Then "delete" the users data used as test data for user page       
		| userPage_userBot1 | userPage_userBot2 |
#    Then "add" the users data used as test data for user page  
#		| userPage_userBot1 | 	
	When click on "Add User" button on user page 
	Then add user popup should be displayed with following fields: 
		| username | first name | last name | email | Role |
	Then verify following button should be enabled on add user popup 
		| Save | Cancel |	
	Then enter the test data in the following fields for "first" user
	    | username | first name | last name | email | Role | Unit of Temperature  |
	When click on "Save" button on add user popup 
	Then verify "Send Welcome Email" button is disabled 
	When click on "Permission" accordion on user page 
	Then verify "Remove" button is disabled 
	When click on "Add All" button on add user popup 
	Then verify "Add All" button is disabled 
	And verify that all Sites get selected and display as "*" with Tenant name on right side of Permission Accordion 
	Then click on "Selected Sites" in Selected Tenant frame 
	When click on "Remove" button on add user popup 
	Then verify "Remove" button is disabled 
	
	# TODO : below step : 01/05/2018 06:54:35
	#	And verify that "Selected Sites" element is not present in Selected Tenant frame
	
	When click on "Provisioning" checkbox in Available Sites area 
	When click on "ADD" button on add user popup  
	
	# TODO : below 2 step : 01/05/2018 06:54:35
	#	When click on "Tenant" drodown in Permission accordion
	#	Then select "Gridpoint Test Automation" from "Tenant" dropown
	
	And verify that "Provisioning" Site get selected and display with Tenant name on right side of Permission Accordion 
	Then verify "Provisioning Checkbox" button is disabled 
	When click on "Permission:Save" button on add user popup 
	Then verify "Send Welcome Email" button is enabled 
	When click on "X" button on user page 
	When enter the user name "userPage_userBot1" in search field           
	Then click on "Search" button on user page 
	Then select the user "userPage_userBot1" from the user details grid 
	Then verify "Send Welcome Email" button is enabled 
	
# TODO : compare below scenario with above scenario : 01/05/2018 06:54:35
<<<<<<< Updated upstream

=======
@AddUserValidation 
>>>>>>> Stashed changes
Scenario: Scenario_04: verify the validation and confirmation for add user page. 
	Given we are on User page and search user field is enabled 
	When click on "Add User" button on user page 
	Then valid error message should be displayed for "empty" scenario for add user page 
	When click on "Cancel" button on add user popup 
	Then confirmation box should display with message "All Changes will be lost. Are you sure?" for user page 
	When click on "Cancel" button on confirmation box on add user popup 
	Then user should remain on add user popup 
	When click on "Cancel" button on add user popup 
	Then confirmation box should display with message "All Changes will be lost. Are you sure?" for user page 
	When click on "Ok" button on confirmation box on add user popup 
	Then user should be navigated on user detail grid 
	When click on "Add User" button on user page 
	Then Verify Role Dropdown has ROLE_CUSTOMER_SITE_MANAGER selected 
	Then Verify FAHRENHEIGHT is selected as Unit of Temperature 
	Then Verify FAHRENHEIGHT and CELSIUS are available in Unit of Temperature dropdown 
	Then Verify GPAdmin has following roles 
		| ROLE_GP_ADMIN | ROLE_GP_SUPPORT | ROLE_GP_ANALYST | ROLE_CUSTOMER_ANALYST | ROLE_CUSTOMER_SITE_MANAGER |
	Then Verify GPSupprt has following roles 
		| ROLE_GP_ANALYST | ROLE_CUSTOMER_ANALYST | ROLE_CUSTOMER_SITE_MANAGER |
	Then enter the valid values for user "1" 
	When click on "Save" button on add user popup 
	Then valid error message should be displayed for "Add User with existing values" scenario for user page 
	#When Special charachters "@ ! -  * : = . _  [] {} () ; /\ %" are used user gets created successfully 
	Then Assigning User capabilitirs and site permission are not allowed 
	And User is not able to login for user with special characters 
	Then Add User and set CELSIUS as Unit of Temperature and verify the same unit displayed in edit mode 
	Then Add User and set FAHRENHEIT as Unit of Temperature and verify the same unit displayed in edit mode 
	Then Verify if user changes the unit of temperaturethen Admin User sees the change 
	Then Verify  On setting popup Temperature Unit does not reflect changes automatically as admin set in the admin UI if setting popup is open before change 
	Then Verify  On setting popup Temperature Unit reflects changes automatically as admin set in the admin UI if setting popup is open after changes made 
	
	#*************** Need to update Steps as per the new functional flow*************************
	#	Then add user popup should be displayed with following default fields   
	#		| English (United States) | US | C&I Internal Analyst |
	#		
	#		
	#		
	#	When click on the logout button from user page and verify the login page
	#	When click on "Cancel" button on add user popup 
	#	When click on "Ok" button on confirmation box on add user popup 
	#	Then adding tenant for User Page
	#	Given we are on User page and search user field is enabled 
	#	When click on "Add User" button on user page 
	#	Then enter valid values for user "1"
	#	Then select "userPage_tenantBot" from site tree on "add" user popup
	#	When click on "Save" button on add user popup 
	#	Then user should be navigated to user page with a label saying "User userPage_userBot1 saved." for user page
	#	And verify if the user has been successfully created and an email is sent to user containing username, password and URL and try to login with temp credentials
	#	Then verify that reset password popup is displayed
	#	Then enter the "valid" password in reset password popup
	#	When click on "Cancel" button on reset password popup
	#	Then verify that user is back to login page
	#	Then click on "Login" button on login page
	#	Then enter the "invalid" password in reset password popup
	#	When click on "Submit" button on reset password popup
	#	Then valid error message should be displayed for "Reset Password" scenario for user page
	#	Then enter the "not match" password in reset password popup
	#	When click on "Submit" button on reset password popup
	#	Then valid error message should be displayed for "not match Reset Password" scenario for user page
	#	Then enter the "valid" password in reset password popup
	#	When click on "Submit" button on reset password popup
	#	Then verify that message "Success! Your password has been reset." is displayed after successfull password reset
	#	And logged in Admin UI with following user 
	#		| username | password |
	#		| userPage_userBot1 | P@ssword1! |
	#	When click on the logout button from user page and verify the login page
	#	Then create another user "userPage_userBot2" as test data for user page
	#	And logged in Admin UI with following user 
	#		| username | password |
	#		| gpadmin | P@ssword1! |
	#	Given we are on User page and search user field is enabled 
	#	Then  enter the user name "userPage_userBot2" in search field
	#	When click on "Search" button on add user popup
	#	Then select the user "userPage_userBot2" from the user details grid  
	#	Then "Edit User" popup should open
	#	When click on "role" selector on edit user popup 
	#	And  select "C&I Customer Analyst" from drop-down options 
	#	Then role of "userPage_userBot2" is changed to "C&I Customer Analyst" 
	#	Then select "userPage_tenantBot" from site tree on "edit" user popup
	#	When click on "Save" button on add user popup  
	#	Then user should be navigated to user page with a label saying "User userPage_userBot2 saved." for user page
	
<<<<<<< Updated upstream
=======
@UserPage_AddUserValidation,@abc
Scenario: Scenario_04A: verify the add user functionality. 
	Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_04A"  
	Given we are on User page and search user field is enabled visible
	
	When click on "Add User" button on user page 
	Then add user popup should be displayed with following fields: 
		| username | first name | last name | email | Role |
	Then verify following button should be enabled on add user popup 
		| Save | Cancel |	
	Then enter the test data in the following fields for "@!-*:=_" user
	    | username | first name | last name | email | Role | Unit of Temperature  |
	When click on "Save" button on add user popup
	#Then click the "Save and Close" button on edit user page and verify entries should not clear when user saves edits to a record
	When click on "SAVE AND CLOSE" button on add user popup
	When enter the user name "@!-*:=_" in search field
	Then click on "Search" button on user page 
	
	Then select the user "@!-*:=_" from the user details grid 
	When click on "Cancel" button on edit user popup
	When click on "Ok" button on confirmation box on add user popup
		  
	Given we are on User page and search user field is enabled visible
	
	When click on "Add User" button on user page 
	Then add user popup should be displayed with following fields: 
		| username | first name | last name | email | Role |
	Then verify following button should be enabled on add user popup 
		| Save | Cancel |	
	Then enter the test data in the following fields for "$  & + ." user
	    | username | first name | last name | email | Role | Unit of Temperature  |
	When click on "Save" button on add user popup	
	Then valid error message should be displayed for "$  & + ." scenario for add user page
	When click on "Cancel" button on edit user popup
	When click on "Ok" button on confirmation box on add user popup
>>>>>>> Stashed changes
	
@UserPage_UserSearch 
Scenario: Scenario_05: Verify the column names and sorting functionality of user detail grid on User page 
	Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_05"  
	Given we are on User page and search user field is enabled visible
	Then verify the search output by entering "use:Init3Char" in user search field 
	Then verify the search output by entering ":RANDOM" in user search field 
	Then verify the search output by entering "userPage_userBot1:BACKSPACE" more than three character and click on backspace button in user search field 
	Then verify the search output by entering string "userPage_userBot1:DELETE" and enter delete button in user search field 
	Then verify the pagination by entering value "userPage_userBot1:Pagination" in user search field 
	
	
@UserPage_AddUserValidation 
Scenario: Scenario_06: verify the validation and confirmation for add user page. 
	Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_06"  
	Given we are on User page and search user field is enabled visible 
	When click on "Add User" button on user page 
	Then valid error message should be displayed for "empty" scenario for add user page 
	When click on "Cancel" button on add user popup 
	Then confirmation box should display with message "All Changes will be lost. Are you sure?" for user page 
	When click on "Cancel" button on confirmation box on add user popup 
	Then user should remain on add user popup 
	When click on "Cancel" button on add user popup 
	Then confirmation box should display with message "All Changes will be lost. Are you sure?" for user page 
	When click on "Ok" button on confirmation box on add user popup 
	Then user should be navigated on user detail grid 
	When click on "Add User" button on user page 
    Then verify default value of "Role" dropdown on add user page for "first" user  
    Then verify default value of "Unit of Temperature" dropdown on add user page for "first" user
    Then user verify that "Unit of Temperature" is a dropdown has the following values
        | FAHRENHEIGHT | CELSIUS |
	Then verify that "Role" is dropdown has the following roles for GP Admin 
     	| ROLE_GP_ADMIN | ROLE_GP_SUPPORT | ROLE_GP_ANALYST | ROLE_CUSTOMER_ANALYST | ROLE_CUSTOMER_SITE_MANAGER |
	Then enter the test data in the following fields for "first" user   
	    | username | first name | last name | email | Role | Unit of Temperature |
	When click on "Save" button on add user popup  
	Then valid error message should be displayed for "Add User with existing values" scenario for user page 

@UserPage_EditUser 
Scenario: Scenario_07: Verify the edit user functionality on Edit User page 
	Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_07"  
	Given we are on User page and search user field is enabled visible 
	Then uncheck the "Hide Inactive" checkbox on user 
	When enter the user name "userPage_userBot1" in search field 
	# TODO : below step
	Then click on "Search" button on user page 
	
	Then select the user "userPage_userBot1" from the user details grid 
	
	# GPUP-29950: Verify Search entries should not clear when user saves edits to a record.	
	Then click the "Save and Close" button on edit user page and verify entries should not clear when user saves edits to a record
	
	# GPUP-29949: Verify Save and Close button on edit user model.	
	# TODO : below step
	Then click on "Search" button on user page 
	
	Then select the user "userPage_userBot1" from the user details grid
<<<<<<< Updated upstream
	Then click on "Reset Password" button on user page 
	Then confirmation box should displayed on edit user page with message "The user's password will be reset.  Are you sure?" 
	Then click on cancel button on edit user page   
=======
>>>>>>> Stashed changes
	Then verify following button should be enabled on edit user popup   
		| Cancel | Save | Save and Close |
	Then click "Save" button on edit user model and verify message "Details have been successfully updated." on the same
	Then verify that "Edit User" page have following section  
		| PROPERTIES |
	Then verify that "PROPERTIES" Section have following fields 
		| First Name | Last Name | Email | Locked | Enabled | Locale | Unit of Temperature | Role |
	
	# TODO : below step
	Then verify the expansion and shrinkage while click on "details" accordion on edit user popup
	
	Then verify the expansion and shrinkage while click on "Audit" accordion on edit user popup 
	Then verify that "audit" detail grid have following columns 
		| Property | Action | Original Value | Updated Value |
	Then verify the expansion and shrinkage while click on "Permission" accordion on edit user popup 
	Then verify that "Permission" detail grid have following columns 
		| Tenant | Selected Tenant Type | Available Sites | elected Tenant/Site Permission | Add | Add All | Remove | Permission:Cancel | Permission:Save |
	Then click on "Capabilities" accordion on user page
	And user verify that "Role" is a dropdown has the following values 
		| ROLE_GP_ADMIN | ROLE_GP_SUPPORT | ROLE_GP_ANALYST |
		
	#TODO : all the below step till end of the scenario were removed. check if valid.
	Then Verify that GP Admin can Unlock any user
		Then Audit Page should show the updates for the user 
		Then Verify role drop down is showing empty while trying to edit user with level
		Then Verify Validations for First Name Last Name and Email in edit mode 
		Then Verify Role Field in Detail Tab is not editable
		Then Verify Unit Of Temperature has only two values CELSIUS and FAHRENHEIT
		Then Verify Capabilities Accordion has Role Dropdown and given Capabilities checkboxes for GP Admin and GP Support
				| BILLING_COMPARISON_REPORT | MSTR_CAPABLE | BYPASS_THROTTLING | REPORT_SAVINGS | EDIT_XML | CREATE_OVERRIDE | RUN_HVAC_TESTS | VIEW_SITE_SAVINGS | 
		Then Verify Capabilities Accordion has Role Dropdown and given Capabilities checkboxes for GP Analyst
				| BILLING_COMPARISON_REPORT | MSTR_CAPABLE | BYPASS_THROTTLING | REPORT_SAVINGS | CREATE_OVERRIDE | RUN_HVAC_TESTS | VIEW_SITE_SAVINGS |
		Then Verify Capabilities Accordion has Role Dropdown and given Capabilities checkboxes for Customer Site Manager
				| MSTR_CAPABLE | BYPASS_THROTTLING | REPORT_SAVINGS | CREATE_OVERRIDE | RUN_HVAC_TESTS | VIEW_SITE_SAVINGS |
		Then Verify Capabilities Accordion has Role Dropdown and given Capabilities checkboxes for Customer Analyst
				| MSTR_CAPABLE | BYPASS_THROTTLING | REPORT_SAVINGS | VIEW_SITE_SAVINGS |
		Then Verify that selected tenant is added and count of sites displayed in destination listbox
		Then Verify that selected tenant from the destination listbox is removed by clicking remove button
		Then Verify save functionality of edit page
		Then Verify selected Tenant shows Type of Tenant
		Then Verify selected Tenant_Site Permission label shows the number of sites selected
		Then Verify Site Permission change appears in Audit
		Then Verify Site Tree contains all sites associated with the Tenant
		Then Verify Site tree changes on the basis of Tenant selected
		Then Verify that Root Tenant Gridpoint has multiple tenants available
		Then Verify that Tenant Dropdown shows Gridpoint by default
		Then Verify all sites get selected after selecting a Tenant
		
	When click on "X" button on user page 
	Then uncheck the "Hide Inactive" checkbox on user 
	When enter the user name "URP_CustomerSiteManager_testBot" in search field 
	Then click on "Search" button on user page 
	Then select the user "URP_CustomerSiteManager_testBot" from the user details grid 
	Then click on "Capabilities" accordion on user page 
	Then verify "Role" element is enabled 
	And user verify that "Role" is a dropdown has the following values 
		| ROLE_CUSTOMER_ANALYST | ROLE_CUSTOMER_SITE_MANAGER |  
	When click on "X" button on user page 
	Then uncheck the "Hide Inactive" checkbox on user 
	When enter the user name "URP_CustomerAnalyst_testBot" in search field 
	Then click on "Search" button on user page 
	Then select the user "URP_CustomerAnalyst_testBot" from the user details grid 
	Then click on "Capabilities" accordion on user page 
	Then verify "Role" element is enabled 
	And user verify that "Role" is a dropdown has the following values 
		| ROLE_CUSTOMER_ANALYST | ROLE_CUSTOMER_SITE_MANAGER |       
	Then Click on Permission accordion and verify Add button is disabled when Add All button is clicked 
	Then Verify on Permission accordion multiple selection by SHIFT plus CLICK and then REMOVE button click deletes selected items 
	Then Verify that GP Admin can Unlock any user  
	Then Audit Page should show the updates for the user 
	Then Verify role drop down is showing empty while trying to edit user with level 
	Then Verify Validations for First Name Last Name and Email in edit mode 
	Then Verify Role Field in Detail Tab is not editable 
	Then Verify Unit Of Temperature has only two values CELSIUS and FAHRENHEIT 
	Then Verify Capabilities Accordion has Role Dropdown and given Capabilities checkboxes for GP Admin and GP Support 
		| BILLING_COMPARISON_REPORT | MSTR_CAPABLE | BYPASS_THROTTLING | REPORT_SAVINGS | EDIT_XML | CREATE_OVERRIDE | RUN_HVAC_TESTS | VIEW_SITE_SAVINGS | 
	Then Verify Capabilities Accordion has Role Dropdown and given Capabilities checkboxes for GP Analyst 
		| BILLING_COMPARISON_REPORT | MSTR_CAPABLE | BYPASS_THROTTLING | REPORT_SAVINGS | CREATE_OVERRIDE | RUN_HVAC_TESTS | VIEW_SITE_SAVINGS |
	Then Verify Capabilities Accordion has Role Dropdown and given Capabilities checkboxes for Customer Site Manager 
		| MSTR_CAPABLE | BYPASS_THROTTLING | REPORT_SAVINGS | CREATE_OVERRIDE | RUN_HVAC_TESTS | VIEW_SITE_SAVINGS |
	Then Verify Capabilities Accordion has Role Dropdown and given Capabilities checkboxes for Customer Analyst 
		| MSTR_CAPABLE | BYPASS_THROTTLING | REPORT_SAVINGS | VIEW_SITE_SAVINGS |
	Then Verify that selected tenant is added and count of sites displayed in destination listbox 
	Then Verify that selected tenant from the destination listbox is removed by clicking remove button 
	Then Verify save functionality of edit page 
<<<<<<< Updated upstream
	#******************** Need to update code as per the new edit user UI************************************************* 
		When click on "Cancel" button on edit user popup
		Then confirmation box should display with message "All Changes will be lost. Are you sure?" for user page
		When click on "Ok" button on confirmation box on edit user popup
		Then user navigate back to the user detail page 
	#	When click on "reset password" button on edit user popup
	#	Then confirmation box should display with message "The user's password will be reset.Are you sure?" for user page
	#	When click on "cancel" button 
	#	Then user should remain on edit user popup 
	#	When click on "Cancel" button on edit user popup 
	#	Then confirmation box should display with message "All Changes will be lost. Are you sure?" for user page
	#	When click on "Ok" button on confirmation box on edit user popup
	#Then user should be navigated on user detail grid  
=======
	
	###########################################################################################################
@UserPage_EditUser, @Test
Scenario: Scenario_07A: Verify the edit user functionality on Edit User page 	
		Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_07A"  
		Given we are on User page and search user field is enabled visible 
		Then uncheck the "Hide Inactive" checkbox on user 
		When enter the user name "userPage_userBot1" in search field 
		# TODO : below step
		Then click on "Search" button on user page 
	
		Then select the user "userPage_userBot1" from the user details grid 

		When click on "Cancel" button on edit user popup
		Then confirmation box should display with message "All Changes will be lost. Are you sure?" for user page
		When click on "Ok" button on confirmation box on edit user popup
		Then user should be navigated on user detail grid
		
		Then click on "Search" button on user page 
	
		Then select the user "userPage_userBot1" from the user details grid 
		
		#Then user should remain on edit user popup 
		When click on "reset password" button on edit user popup
		Then confirmation box should display with message "The user's password will be reset.  Are you sure?" for reset Password alert pop up
		When click on "Cancel" button on reset password alert popup
		#When click on "cancel" button 
		Then user should remain on edit user popup 
		When click on "Cancel" button on edit user popup 
		Then confirmation box should display with message "All Changes will be lost. Are you sure?" for user page
		When click on "Ok" button on confirmation box on edit user popup
	    Then user should be navigated on user detail grid  
>>>>>>> Stashed changes
	
	
@UserPage_InvalidUser 
Scenario: Scenario_08:  Verify error message with invalid/unauthorized/random credentials for different fields 
	Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_08"  
	Given we are on User page and search user field is enabled visible 
	When enter the user name "userPage_userBot1" in search field 
	Then click on "Search" button on user page 
	Then select the user "userPage_userBot1" from the user details grid 
	Then enter invalid values in edit user fields 
		| email | "hello" |
	Then click on "save" button on edit user popup 
	Then valid error message should be displayed for "Edit User with invalid values" scenario for add user page 
	
@UserPage_VerifyUserRole 
Scenario: Scenario_09: Verify the changes in the role and locale for a user 
	Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_09"  
	Given we are on User page and search user field is enabled visible 
	When enter the user name "userPage_userBot1" in search field 
	Then click on "Search" button on user page 
	Then select the user "userPage_userBot1" from the user details grid 
	Then "Edit User" popup should open 
	When uncheck "Enabled" checkbox on edit user popup 
	And click on "save" button on edit user popup 
	
	# TODO : below steps are commented
		
	#**************Need to update code as per the new add user process ********************** 
	#	Then user should be navigated on user detail grid 
	#	When click on the logout button from user page and verify the login page
	#	And logged in Admin UI with following user 
	#  		| username | password |
	#  		| userPage_userBot1 | P@ssword1! | 
	#	Then A validation message "User is disabled" should be displayed 
	#	And logged in Admin UI with following user 
	#  		| username | password |
	#  		| gpadmin | P@ssword1! |
	#	Given we are on User page and search user field is enabled 
	#	When enter the user name "userPage_userBot1" in search field 
	#	Then click on "Search" button on user page 
	#	Then select the user "userPage_userBot1" from the user details grid 
	#	When check "Enabled" checkbox on edit user popup 
	#	And change the "Local" with "English (United State)" on edit user popup 
	#	And click on "save" button on edit user popup 
	#	Then user should be navigated on user detail grid 
	#	When click on the logout button from user page and verify the login page
	#	And logged in Admin UI with following user 
	#  		| username | password |
	#  		| userPage_userBot1 | P@ssword1! |  
	#	Given we are on User page and search user field is enabled
	   
		
@UserPage_TestingWithGPSupportUsers 
Scenario: Scenario_10: Verify user various sceanrios when user is logged in with GP Support role 
	Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_10"  
	Given we are on User page and search user field is enabled visible 
	Then logout from the current user "GP Admin" role 
	When login with user "autoSupportTest" in admin application 
	Given we are on User page and search user field is enabled visible 
	When enter the user name "autoSupportTest" in search field 
	
	# TODO : below step
	Then click on "Search" button on user page 
	
	Then select the user "autoSupportTest" from the user details grid 
	Then "Edit User" popup should open 
	Then click on "Capabilities" accordion on user page 
	Then verify "Role" element is disabled 
	And user verify that "Role" is a dropdown has the following values 
		|  |
	When click on "X" button on user page 
	Then uncheck the "Hide Inactive" checkbox on user 
	When enter the user name "autoAdminTest" in search field 
	
	# TODO : below step
	Then click on "Search" button on user page 
	
	Then select the user "autoAdminTest" from the user details grid 
	Then "Edit User" popup should open 
	Then click on "Capabilities" accordion on user page 
	Then verify "Role" element is disabled 
	And user verify that "Role" is a dropdown has the following values 
		|  |     
	When click on "X" button on user page 
	Then uncheck the "Hide Inactive" checkbox on user 
	When enter the user name "autoAnalystTest" in search field 
	
	# TODO : below step
	Then click on "Search" button on user page 
	
	Then select the user "autoAnalystTest" from the user details grid 
	Then "Edit User" popup should open 
	Then click on "Capabilities" accordion on user page 
	Then verify "Role" element is enabled 
	And user verify that "Role" is a dropdown has the following values 
		| ROLE_GP_ANALYST |    
	When click on "X" button on user page 
	Then uncheck the "Hide Inactive" checkbox on user 
	When enter the user name "autoCustomerAnalystTest" in search field 
	
	# TODO : below step
	Then click on "Search" button on user page 
	
	Then select the user "autoCustomerAnalystTest" from the user details grid 
	Then "Edit User" popup should open 
	Then click on "Capabilities" accordion on user page 
	Then verify "Role" element is enabled 
	And user verify that "Role" is a dropdown has the following values 
		| ROLE_CUSTOMER_ANALYST | ROLE_CUSTOMER_SITE_MANAGER |	
	When click on "X" button on user page 
	Then uncheck the "Hide Inactive" checkbox on user 
	When enter the user name "autoCSM_Test" in search field 
	
	# TODO : below step
	Then click on "Search" button on user page 
	
	Then select the user "autoCSM_Test" from the user details grid 
	Then "Edit User" popup should open 
	Then click on "Capabilities" accordion on user page 
	Then verify "Role" element is enabled 
	And user verify that "Role" is a dropdown has the following values 
		| ROLE_CUSTOMER_ANALYST | ROLE_CUSTOMER_SITE_MANAGER |    
		
@UserPage_UserRoleChange_Navigation 
Scenario: Scenario_11: Verify user Role change and Navigation from AdminUI to PortalUI and vice-versa 
	Given User is already logged in to Admin URL and is already present at the UserPage Tab for "Scenario_11"  
	Given we are on User page and search user field is enabled visible 
	Then "delete" the users data used as test data for user page  
		| userPage_userBot2 |
	When click on "Add User" button on user page
	Then enter the test data in the following fields for "second" user   
	    | username | first name | last name | email | Role | Unit of Temperature |	 
	And change the user role to "ROLE_GP_ANALYST" for user "userPage_userBot2" 
	# TODO : verify below 2 steps
	When click on "X" button on user page 
	Then uncheck the "Hide Inactive" checkbox on user 
	
	When enter the user name "userPage_userBot2" in search field 
	
	# TODO : below step
	Then click on "Search" button on user page 
	
	Then select the user "userPage_userBot2" from the user details grid 
	Then "Edit User" popup should open 
	Then click on "Capabilities" accordion on user page 
	Then verify default value of "Unit of Temperature" dropdown on add user page for "second" user 
	
	# TODO : missing steps
	Then click on portal button which navigate to HTML Portal 
	And click on Admin button which navigate to Admin UI 