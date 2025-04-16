
@tag
Feature: Purchase a product from ECommerce Website
As a user, I want to purchase some products from an Ecommerce Website

Background:
Given I landed on Ecommerce Website

  @Regression
  Scenario Outline: Positive test of submitting the order on Ecommerce Website
    Given I logged in with username <name> and password <password>
    When I add the product <productName> to Cart
    And I checkout with product <productName> and submit the order
    Then "Thankyou for the order." message is displayed on the Confirmation Page

    Examples: 
      | name 						 		 | password 	| productName  	 |
      | ankit86@gmail.com		 | Ankit@123	| ZARA COAT 3		 |
      | rahulshetty@gmail.com| Iamking@000| ADIDAS ORIGINAL|
