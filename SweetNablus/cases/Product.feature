Feature: Product Management

  ################################# Add #################################
  Scenario: owner can add product
    Given the productId is "0000"
    And the productName is "kunafa"
    And the Description is "delisious"
    And the price is "35د"
    And the availability is "available"
 
    Then owner can add product

  Scenario: owner enter id that already exist
    Given the productId is "2"
    And the productName is "kunafa"
    And the Description is "delisious"
    And the price is "35د"
    And the availability is "available"
  
    Then owner can't add product

  ################################# Update #################################
  Scenario: owner can update product
    Given the productId is "2"
    Then owner can update product

  Scenario: owner enter id not exist to update product
    Given the productId is "6000"
    Then owner can't update

  ################################# Delete #################################
  Scenario: owner can delete product
    Given the productId is "2"
    Then the product will be deleted

  Scenario: owner enter id not exist to delete
    Given the productId is "333"
    Then the product not deleted

  ################################# Show #################################
  Scenario: owner show all product 
    Then all product shown
