Feature: User Roles

  Scenario: The Admin can generate financial reports
    Given The admin is logged into the system
    Then The admin should be able to view and generate financial reports 

  Scenario: The Admin can manage contents
    Given The admin is logged into the system
    Then The Admin should be able to create, edit, or delete contents

  Scenario: The Admin can manage recipes
    Given The admin is logged into the system
    Then The Admin should be able to create, edit, or delete recipes

  Scenario: The Admin can gather and display statistics on registered users by city
    Given The admin is logged into the system
    Then The admin should be able to view statistics on registered users categorized by city 
 
  Scenario: The Admin enters a number not found
    Given The admin is logged into the system
    Then The Admin should be told to enter one, two, three, or four

 Scenario: The Beneficiary User can browse sweets
    Given The Beneficiary User is logged into the system
    Then The Beneficiary User should be able to browse sweets

  Scenario: The Beneficiary User can make purchases
    Given The Beneficiary User is logged into the system
    Then The Beneficiary User should be able to make purchases

 

 Scenario: The Beneficiary User enters a number not found
    Given The Beneficiary User is logged into the system
    Then The Beneficiary User should be told to enter one, two, or three

 
    
 Scenario: The Owner can send email notifications
    Given The Owner is logged into the system
    Then The Owner should be able to send email notifications for shipping, delivery
    
 Scenario: The Owner can identifiy best selling
    Given The Owner is logged into the system
    Then The Owner should be able to identifiy best selling

