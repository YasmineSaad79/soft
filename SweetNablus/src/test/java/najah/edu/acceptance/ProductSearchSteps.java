package najah.edu.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import najah.edu.Product;

import java.util.List;

public class ProductSearchSteps {

    private Product productManager = new Product();
    private List<Product> searchResults; // This will hold the search results
    private boolean isProductFound;

    @Given("the user searches for a product with ID {string}")
    public void theUserSearchesForAProductWithID(String id) {
        searchResults = productManager.searchProductById(id);
        isProductFound = !searchResults.isEmpty();
    }

    @Then("the system should display the product with ID {string}")
    public void theSystemShouldDisplayTheProductWithID(String id) {
        if (isProductFound) {
            boolean productDisplayed = searchResults.stream()
                .anyMatch(product -> product.getProductId().equals(id));
            assertTrue("Product with ID " + id + " should be displayed", productDisplayed);
        } else {
            assertFalse("Product with ID " + id + " should not be displayed", productManager.isSearchProductFlag());
        }
    }

    @Given("the user searches for a product with name {string}")
    public void theUserSearchesForAProductWithName(String name) {
        searchResults = productManager.searchProductByName(name);
        isProductFound = !searchResults.isEmpty();
    }

    @Then("the system should display products with the name {string}")
    public void theSystemShouldDisplayProductsWithTheName(String name) {
        if (isProductFound) {
            boolean productDisplayed = searchResults.stream()
                .anyMatch(product -> product.getProductName().equals(name));
            assertTrue("Products with name " + name + " should be displayed", productDisplayed);
        } else {
            assertFalse("Products with name " + name + " should not be displayed", productManager.isSearchProductFlag());
        }
    }

    @Given("the user searches for a product with description {string}")
    public void theUserSearchesForAProductWithDescription(String description) {
        searchResults = productManager.searchProductByDescription(description);
        isProductFound = !searchResults.isEmpty();
    }

    @Then("the system should display products with the description {string}")
    public void theSystemShouldDisplayProductsWithTheDescription(String description) {
        if (isProductFound) {
            boolean productDisplayed = searchResults.stream()
                .anyMatch(product -> product.getDescription().contains(description));
            assertTrue("Products with description containing " + description + " should be displayed", productDisplayed);
        } else {
            assertFalse("Products with description containing " + description + " should not be displayed", productManager.isSearchProductFlag());
        }
    }

    @Given("the user searches for a product with availability {string}")
    public void theUserSearchesForAProductWithAvailability(String availability) {
        searchResults = productManager.searchProductByAvailability(availability);
        isProductFound = !searchResults.isEmpty();
    }

    @Then("the system should display products with the availability {string}")
    public void theSystemShouldDisplayProductsWithTheAvailability(String availability) {
        if (isProductFound) {
            boolean productDisplayed = searchResults.stream()
                .anyMatch(product -> product.getAvailability().equals(availability));
            assertTrue("Products with availability " + availability + " should be displayed", productDisplayed);
        } else {
            assertFalse("Products with availability " + availability + " should not be displayed", productManager.isSearchProductFlag());
        }
    }
    @Then("the system should display the product with ID {string} not found")
    public void theSystemShouldDisplayTheProductWithIDNotFound(String id) {
        searchResults = productManager.searchProductById(id);
        assertTrue("Product with ID " + id + " should not be found", searchResults.isEmpty());
    }

    @Then("the system should display products with the name {string} not found")
    public void theSystemShouldDisplayProductsWithTheNameNotFound(String name) {
        searchResults = productManager.searchProductByName(name);
        assertTrue("Products with name " + name + " should not be found", searchResults.isEmpty());
    }

    @Then("the system should display products with the description {string} not found")
    public void theSystemShouldDisplayProductsWithTheDescriptionNotFound(String description) {
        searchResults = productManager.searchProductByDescription(description);
        assertTrue("Products with description containing " + description + " should not be found", searchResults.isEmpty());
    }

    @Then("the system should display products with the availability {string} not found")
    public void theSystemShouldDisplayProductsWithTheAvailabilityNotFound(String availability) {
        searchResults = productManager.searchProductByAvailability(availability);
        assertTrue("Products with availability " + availability + " should not be found", searchResults.isEmpty());
    }
}
