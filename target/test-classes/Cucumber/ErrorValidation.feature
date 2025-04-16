
@tag
Feature: Login Error Validation
As a user, when I try to login with wrong username or password, "incorrect email or password" error message should be displayed and I should not be able to login

  @ErrorValidation
  Scenario Outline: Negative test of logging with wrong username or password
    Given I landed on Ecommerce Website
    When I logged in with username <name> and password <password>
    Then "Incorrect email or password." error message is displayed and user can not login

    Examples: 
      | name 						 		 | password 	| 
      | ankit86@gmail.com		 | Anki@123	|
     
