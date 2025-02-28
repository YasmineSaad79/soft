package najah.edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Product {
    private static final Logger logger = Logger.getLogger(Product.class.getName());
    private static final String CONTENT_FILE_PATH = "src/main/resources/myData/content.txt";

    private String productId;
    private String productName;
    private String description;
    private double price;
    private String weight;
    private String availability;
    private int quantity;
    private String id;
    private String name;
    

    private List<Product> products = new ArrayList<>();
    private boolean addProductFlag;
    private boolean updateProductFlag;
    private boolean deleteProductFlag;
    private boolean showAllProductsFlag;
    private boolean searchProductFlag;

    // Constructor
    public Product() {
        loadProductsFromFile(CONTENT_FILE_PATH);
    }

    public Product(String productId, String productName, String description, double price, String weight, String availability, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.availability = availability;
        this.quantity = quantity;
        
    }


    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() { 
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean addProduct(Product product) {
        for (Product p : products) {
            if (p.getProductId().equals(product.getProductId())) {
                logger.log(Level.INFO, "Product ID already exists: {0}", product.getProductId());
                addProductFlag = false;
                return false;
            }
        }
        products.add(product);
        logger.log(Level.INFO, "Product added successfully: {0}", product.getProductId());
        addProductFlag = true;
        saveProductsToFile();
        return true;
    }


    public boolean updateProduct(String productId, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                products.set(i, updatedProduct);
                logger.log(Level.INFO, "Product updated successfully: {0}", productId);
                updateProductFlag = true;
                saveProductsToFile();
                return true;
            }
        }
        logger.log(Level.INFO, "Product ID not found: {0}", productId);
        updateProductFlag = false;
        return false;
    }

    public boolean deleteProduct(String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                products.remove(p);
                logger.log(Level.INFO, "Product deleted successfully: {0}", productId);
                deleteProductFlag = true;
                saveProductsToFile();
                return true;
            }
        }
        logger.log(Level.INFO, "Product ID not found: {0}", productId);
        deleteProductFlag = false;
        return false;
    }
    public List<Product> searchProductById(String productId) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                result.add(p);
            }
        }
        searchProductFlag = !result.isEmpty();
        return result;
    }

    public List<Product> searchProductByName(String productName) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(productName)) {
                result.add(p);
            }
        }
        searchProductFlag = !result.isEmpty();
        return result;
    }

    public List<Product> searchProductByDescription(String description) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getDescription().toLowerCase().contains(description.toLowerCase())) {
                result.add(p);
            }
        }
        searchProductFlag = !result.isEmpty();
        return result;
    }

    public List<Product> searchProductByAvailability(String availability) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getAvailability().equalsIgnoreCase(availability)) {
                result.add(p);
            }
        }
        searchProductFlag = !result.isEmpty();
        return result;
    }

    public boolean isSearchProductFlag() {
        return searchProductFlag;
    }

    public void setSearchProductFlag(boolean searchProductFlag) {
        this.searchProductFlag = searchProductFlag;
    }



    public List<Product> showAllProducts() {
        showAllProductsFlag = true;
        return products;
    }
    public List<Product> getProducts() {
        return products;
    }

    private void loadProductsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 7) { // Check if the line has exactly 7 values
                    String id = values[0];
                    String name = values[1];
                    String description = values[2];
                    double price = Double.parseDouble(values[3]);
                    String weight = values[4];
                    String availability = values[5];
                    int quantity = Integer.parseInt(values[6]);

                    Product product = new Product(id, name, description, price, weight, availability, quantity);
                    products.add(product);
                } else {
                    System.err.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
           logger.log(Level.SEVERE, "NumberFormatException occurred", e);
        }
    }

    public boolean isAddProductFlag() {
        return addProductFlag;
    }

    public boolean isUpdateProductFlag() {
        return updateProductFlag;
    }

    public boolean isDeleteProductFlag() {
        return deleteProductFlag;
    }

    public boolean isShowAllProductsFlag() {
        return showAllProductsFlag;
    }
   

    public void setShowAllProductsFlag(boolean showAllProductsFlag) {
        this.showAllProductsFlag = showAllProductsFlag;
    }
    private void saveProductsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTENT_FILE_PATH))) {
            writer.write("id,name,description,price,weight,availability,quantity"); // Write header
            writer.newLine();
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save products to file.", e);
        }
    }
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%.2f,%s,%s,%d",
                             this.productId,
                             this.productName,
                             this.description,
                             this.price,
                             this.weight,
                             this.availability,
                             this.quantity);
    }
    
    
}
