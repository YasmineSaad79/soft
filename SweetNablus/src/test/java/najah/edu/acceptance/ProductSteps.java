package najah.edu.acceptance;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import najah.edu.Product;

public class ProductSteps {

    private Product productManager = new Product(); // Initialize your Product manager here
    private boolean addProductResult;
    private boolean updateProductResult;
    private boolean deleteProductResult;

    @Given("the productId is {string}")
    public void theProductIdIs(String productId) {
    	productManager.getProductId();
    }

    @Given("the productName is {string}")
    public void theProductNameIs(String productName) {
    	productManager.getProductName();
    }

    @Given("the Description is {string}")
    public void theDescriptionIs(String description) {
    	productManager.getDescription();
    }

    @Given("the price is {string}")
    public void thePriceIs(String price) {
    	productManager.getPrice();
    }

    @Given("the availability is {string}")
    public void theAvailabilityIs(String availability) {
    	productManager.getAvailability();
    }

    @Then("owner can add product")
    public void ownerCanAddProduct() {
        Product newProduct = new Product("0001", "kunafa", "delicious", 35.00, "0g", "available", 10);
        boolean result = productManager.addProduct(newProduct);
        assertTrue("Product should be added successfully", result);
        assertTrue("Add product flag should be set to true", productManager.isAddProductFlag());
    }


    @Then("owner can't add product")
    public void ownerCanTAddProduct() {
        Product existingProduct = new Product("1", "kunafa", "delicious", 35.00, "0g", "available", 10);
        boolean result = productManager.addProduct(existingProduct);
        assertFalse("Product with the same ID should not be added", result);
        assertFalse("Add product flag should be set to false", productManager.isAddProductFlag());
    }


    @Then("owner can update product")
    public void ownerCanUpdateProduct() {
        Product updatedProduct = new Product("2", "updated kunafa", "updated description", 40.00, "0g", "available", 15);
        updateProductResult = productManager.updateProduct("2", updatedProduct);
        assertTrue("Product should be updated successfully", productManager.isUpdateProductFlag());
    }

    @Then("owner can't update")
    public void ownerCanTUpdate() {
        Product updatedProduct = new Product("6000", "non-existent product", "description", 0.00, "0g", "available", 0);
        updateProductResult = productManager.updateProduct("6000", updatedProduct);
        assertFalse("Non-existent product should not be updated", productManager.isUpdateProductFlag());
    }

    @Then("the product will be deleted")
    public void theProductWillBeDeleted() {
        deleteProductResult = productManager.deleteProduct("2");
        assertTrue("Product should be deleted successfully", productManager.isDeleteProductFlag());
    }

    @Then("the product not deleted")
    public void theProductNotDeleted() {
        deleteProductResult = productManager.deleteProduct("333");
        assertFalse("Non-existent product should not be deleted", productManager.isDeleteProductFlag());
    }

    @Then("all product shown")
    public void allProductShown() {
        productManager.setShowAllProductsFlag(true);
        assertTrue("Flag to show all products should be set", productManager.isShowAllProductsFlag());
    }
    @Before
    public void setUp() {
        // Reset or reinitialize the product manager before each test
        productManager = new Product();
    }

    @After
    public void tearDown() {
        // Optionally, clear the file or reset any global state after each test
    }

}
