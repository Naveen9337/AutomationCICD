
#Sample Feature Definition Template
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page
  
  
 @Regression
  Scenario Outline: Positive test of submitting order
    Given Logged in with username <name> and password <password>
    When I add prduct <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." page is displayed on Confirmation page

    Examples: 
      |       name                | password 	        | productName  |
      | naveen.naveen11@gmail.com |     L-FRsh86# 		| ZARA COAT 3  |
      
