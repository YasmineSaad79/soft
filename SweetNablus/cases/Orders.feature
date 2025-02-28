Feature: Order Management
  This feature allows the admin and customer to manage orders without requiring any user input.

  Background:
    Given the admin is logged in

  Scenario: Admin views all orders
    When the admin views all orders
    Then the orders should be displayed

  Scenario: Customer creates a new order
    Given the customer has selected products
    And the products are available in stock
    When the customer creates a new order
    Then the order should be saved with status "pending"

  Scenario: Customer views pending orders
    Given the customer has a pending order
    When the customer views their orders
    Then the pending order should be displayed

  Scenario: Admin updates order status
    Given the order exists
    When the admin updates the order status to "shipped"
    Then the order status should be updated

  Scenario: Customer cancels an order
    Given the customer has a pending order
    When the customer cancels the order
    Then the order should be removed from the system