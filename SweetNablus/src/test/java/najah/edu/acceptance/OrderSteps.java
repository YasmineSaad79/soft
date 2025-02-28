package najah.edu.acceptance;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import najah.edu.Admin;
import najah.edu.Order;

public class OrderSteps {
	 private Admin admin = new Admin();
	 private Order order = new Order();
	@Given("the admin is logged in")
	public void theAdminIsLoggedIn() {
		Assert.assertEquals(true, admin.isAdminLogin());
	}

	@When("the admin views all orders")
	public void theAdminViewsAllOrders() {
		 order.setViewOrdersFlag(true);
        Order. viewCart();
	}

	@Then("the orders should be displayed")
	public void theOrdersShouldBeDisplayed() {
	   Assert.assertTrue(order.isViewOrdersFlag());
	}

	@Given("the customer has selected products")
	public void theCustomerHasSelectedProducts() {
	  order.canManageOrders();
	}

	@Given("the products are available in stock")
	public void theProductsAreAvailableInStock() {
order.isProductExisting();
	}

	@When("the customer creates a new order")
	public void theCustomerCreatesANewOrder() {
		 Assert.assertTrue(Order.createOrder("123", "customerName", 1, 2));
		 }

	@Then("the order should be saved with status {string}")
	public void theOrderShouldBeSavedWithStatus(String expectedStatus) {
		  order.viewDeliveredOrder(expectedStatus);
	}

	@Given("the customer has a pending order")
	public void theCustomerHasAPendingOrder() {
		order.setIfCustomerShowPendingOrder(true);
        Assert.assertTrue(order.isIfCustomerShowPendingOrder());
	}

	@When("the customer views their orders")
	public void theCustomerViewsTheirOrders() {
		String[] orderDetails = {"Order1", "Pending", "Product1", "2024-08-21"};
		order.canViewPendingOrders();
 order.printDeliveredOrder(orderDetails);
	}

	@Then("the pending order should be displayed")
	public void thePendingOrderShouldBeDisplayed() {
		 order.setIfCustomerShowPendingOrder(true);
		    order.viewPendingOrder("pending", "customerId123"); 
	}

	@Given("the order exists")
	public void theOrderExists() {
	   order.setIfOrderExist(true);
	}

	@When("the admin updates the order status to {string}")
	public void theAdminUpdatesTheOrderStatusTo(String newStatus) {
	    order.setStatusOrder(newStatus);
	}

	@Then("the order status should be updated")
	public void theOrderStatusShouldBeUpdated() {
		

	}

	@When("the customer cancels the order")
	public void theCustomerCancelsTheOrder() {
	    Order.cancelOrder1();
	}

	@Then("the order should be removed from the system")
	public void theOrderShouldBeRemovedFromTheSystem() {
	   
	}
}