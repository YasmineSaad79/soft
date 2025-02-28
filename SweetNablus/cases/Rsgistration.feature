Feature: user sign up
  Scenario: Beneficiary Users can sign up
    Given the name is "yasmine"
    And   the email "yasminne12@gmail.com"
    And   the  password "1111"
    And   the confirmPassword is "1111"
    And   the id is "12345678"
    Then  Beneficiary Users can sign up

  Scenario: Beneficiary Users Enter different password
    Given the  password "1111"
    And   the confirmPassword is "2222"
    Then  try again to sign up!

  Scenario: Beneficiary Users Entered an email that already exists
    Given the email "s12112317@stu.najah.edu"
    Then  try enter email to sign up!

