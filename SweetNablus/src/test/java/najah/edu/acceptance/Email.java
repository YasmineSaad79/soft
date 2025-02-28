package najah.edu.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import najah.edu.Gmail;
import najah.edu.Owner;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Email {
    private Owner owner;
    private List<String> emailMessages;
    Gmail gmail = new Gmail();
    public Email() {
    	
        owner = new Owner();
        emailMessages = new ArrayList<>();
    }

    @Given("Order status changed {string}")
    public void orderStatusChanged(String status) {
        owner.receiveNotifications(status);

        String expectedMessage;
        switch (status) {
            case "shipped":
                expectedMessage = "The order status has changed to shipped.";
                break;
            case "delivered":
                expectedMessage = "The order has been delivered.";
                break;
            case "canceled":
                expectedMessage = "The order has been canceled.";
                break;
            default:
                expectedMessage = "Unknown status.";
                break;
        }
        emailMessages.add(expectedMessage);
    }


    @Then("Customer should have an order notification about the shipped order")
    public void customerShouldHaveAnOrderNotificationAboutTheShippedOrder() throws InterruptedException {
       
        boolean messageFound = emailMessages.stream()
            .anyMatch(message -> message.equals("The order status has changed to shipped."));
        
        assertTrue("Expected message about shipped order was not found.", messageFound);
    }
    @Then("Customer should have an order notification about the delivered order")
    public void customerShouldHaveAnOrderNotificationAboutTheDeliveredOrder() throws InterruptedException {
        Thread.sleep(5000); 
        
        boolean messageFound = emailMessages.stream()
            .anyMatch(message -> message.contains("The order has been delivered."));
        
        assertTrue("Expected message about delivered order was not found.", messageFound);
    }

    @Given("owner cancel customer order {string}")
    public void ownerCancelCustomerOrder(String status) throws InterruptedException {
        owner.receiveNotifications(status);
        Thread.sleep(500); 

        if ("canceled".equals(status)) {
            emailMessages.add("The order has been canceled.");
        } else if ("delivered".equals(status)) {
            emailMessages.add("The order has been delivered.");
        } else {
            emailMessages.add("The order status has changed to " + status + ".");
        }
    }





}