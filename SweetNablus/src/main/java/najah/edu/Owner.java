//////////////// وهي الاونر ///////
package najah.edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.Map;
import java.util.HashMap;
import io.cucumber.core.logging.Logger;

import static najah.edu.Registration.logger;


public class Owner {

	 private List<String> emailMessages = new ArrayList<>();
	 private static final String CONTENT_FILE_PATH = "src/main/resources/myData/content.txt";
	    private static final String SALES_FILE_PATH = "src/main/resources/myData/sales.txt";
	    public static final String BOLD = "\u001B[1m";
	    public static final String RESET_COLOR = "\u001B[0m";
	    private String idCustomer;
	    private String customerName;
	    private String phoneCustomer;
	    private String userName;
	    private Gmail gmail;
	    private String password;
	    private String address;
	    private int id;
	    private int phone;
	    private boolean manageProductsFlag;
	    private boolean  manageOrdersFlag;
	    private boolean monitorSalesFlag;
	    private boolean bestSellingProductsFlag;
	    
	    private boolean dynamicDiscountFlag;
	    private boolean notificationsFlag;
	    private boolean trackOrdersFlag;
	    private Product productManager = new Product();

	    public Owner() {
	    	 emailMessages = new ArrayList<>();
	        manageProductsFlag = false;
	        manageOrdersFlag=false;
	        monitorSalesFlag = false;
	        bestSellingProductsFlag = false;
	        dynamicDiscountFlag = false;
	        notificationsFlag = false;
	        trackOrdersFlag = false;
	        gmail = new Gmail();
	      
	    }
	
	  
	    

	    public String getIdCustomer() {
	        return idCustomer;
	    }

	    public void setIdCustomer(String idCustomer) {
	        this.idCustomer = idCustomer;
	    }
	    
	    public String getCustomerName() {
	        return customerName;
	    }
	    public boolean isProductsFlag() {
	        return manageProductsFlag;
	    }
	    public void setProductsFlag(boolean productsFlag) {
	        this.manageProductsFlag = productsFlag;
	    }
	    public void setCustomerName(String customerName) {
	        this.customerName = customerName;
	    }
	    public String getPhoneCustomer() {
	        return phoneCustomer;
	    }
	    private boolean ownerLogin;
	    
	    public boolean isOwnerLogin() {
	        return ownerLogin;
	    }
	    public void setOwnerLogin(boolean ownerLogin) {
	        this.ownerLogin = ownerLogin;
	    }
	    public void setPhoneCustomer(String phoneCustomer) {
	        this.phoneCustomer = phoneCustomer;
	    }
	    public void login(String username, String password) {
	        if (username.equals("Yara@gmail.com") && password.equals("121")) {
	            setOwnerLogin(true); 
	            }
	    }
	  
        public void owner_Menu(String ownerName) {
	                Scanner scanner = new Scanner(System.in);
	                int choice;
	                do {
	                    logger.log(Level.INFO, "\n\u001B[32m" + " -------  Welcome " + ownerName + " ---------" + "\n" +
	                            "|                                   |\n" +
	                            "|      1. Manage Products           |\n" +
	                            "|      2. Monitor Sales             |\n" +
	                            "|      3. Identify Best-selling Products |\n" +
	                            "|      4. Manage Orders             |\n" +  	                            "|      5. Implement Dynamic Discount|\n" +
	                            "|      6. Receive Notifications     |\n" +
	                            "|      7. Exit                      |\n" +  	                            "|                                   |\n" +
	                            "------------------------------------\n");
	                    logger.log(Level.INFO, "Enter your choice: " + RESET_COLOR );
	                    choice = scanner.nextInt();
	                    switch (choice) {
	                        case 1 -> {
	                            manageProductsFlag = true;
	                            manageProductsMenu();
	                        }
	                        case 2 -> {
	                            monitorSalesFlag = true;
	                            monitorSales();
	                        }
	                        case 3 -> {
	                            bestSellingProductsFlag = true;
	                            identifyBestSellingProducts();
	                        }
	                        case 4 -> {
	                            manageOrdersFlag = true;  
	                            Order order = new Order(); 
	                            order.manageOrders();     	                        }
	                        case 5 -> {
	                            dynamicDiscountFlag = true;
	                            implementDynamicDiscount();
	                        }
	                        case 6 -> {
	                            notificationsFlag = true;
	                            receiveNotifications("shipped");
	                            receiveNotifications("delivered");
	                           
	                        }
	                        case 7 -> logger.log(Level.INFO, "Exiting..."); 
	                        default -> logger.log(Level.WARNING, BOLD + "\u001B[31mInvalid choice! Please enter a valid choice." + RESET_COLOR );
	                    }
	                } while (choice != 7); // تغيير الشرط إلى 7
	            }
	    public void receiveNotifications(String orderStatus) {
	        String email = "saady9055@gmail.com";
	        String subject = "Order Status Changed";
	        String message;

	        switch (orderStatus) {
	            case "shipped":
	                message = "The order status has changed to shipped.";
	                break;
	           
	          
	            case "delivered":
	                message = "The order has been delivered.";
	                break;
	           
	            default:
	                message = "The order status has changed.";
	                break;
	        }

	        
	        if (gmail != null) {
	            gmail.sendEmail(email, subject, message);
	            emailMessages.add(message);
	        } else {
	            throw new IllegalStateException("Gmail instance is not initialized.");
	        }
	        logger.log(Level.INFO, "Notification sent to owner about order status change to " + orderStatus);
	    }
	    
	    public List<String> getEmailMessages() {
	        return emailMessages;
	    }
	    private void resetFlags() {
	        manageProductsFlag = false;
	        monitorSalesFlag = false;
	        bestSellingProductsFlag = false;
	        dynamicDiscountFlag = false;
	        notificationsFlag = false;
	        trackOrdersFlag = false;
	    }


	    public void manageProductsMenu() {
	        int choice;
	        Scanner scanner = new Scanner(System.in);
	        logger.log(Level.INFO, """
	           
	           \u001B[35m---------------------------------
	           |                                    |
	           |      1. Add new product            |
	           |      2. Update existing product    |
	           |      3. Delete product             |
	           |                                    | 
	           --------------------------------------
	           """);
	        logger.log(Level.INFO, "Enter your choice: " + RESET_COLOR );
	        choice = scanner.nextInt();
	        scanner.nextLine(); // Consume newline left-over
	        switch (choice) {
	            case 1 -> addProduct(scanner);
	            case 2 -> updateProduct(scanner);
	            case 3 -> deleteProduct(scanner);
	            default -> logger.log(Level.INFO, BOLD + "\u001B[31mInvalid choice! Please enter a valid choice.\u001B[0m");
	        }
	    }

	    private void addProduct(Scanner scanner) {
	        logger.log(Level.INFO, "Enter product ID: ");
	        String productId = scanner.nextLine();
	        logger.log(Level.INFO, "Enter product name: ");
	        String productName = scanner.nextLine();
	        logger.log(Level.INFO, "Enter product description: ");
	        String description = scanner.nextLine();
	        logger.log(Level.INFO, "Enter product price: ");
	        double price = scanner.nextDouble();
	        scanner.nextLine(); // Consume the newline
	        logger.log(Level.INFO, "Enter product weight: ");
	        String weight = scanner.nextLine();
	        logger.log(Level.INFO, "Enter product availability: ");
	        String availability = scanner.nextLine();
	        logger.log(Level.INFO, "Enter product quantity: ");
	        int quantity = scanner.nextInt();
	        
	        Product product = new Product(productId, productName, description, price, weight, availability, quantity);
	        boolean result = productManager.addProduct(product);
	        if (result) {
	            logger.log(Level.INFO, "Product added successfully.");
	        } else {
	            logger.log(Level.INFO, "Failed to add product. Product ID may already exist.");
	        }
	    }


	    private void updateProduct(Scanner scanner) {
	        logger.log(Level.INFO, "Enter product ID to update: ");
	        String productId = scanner.nextLine();
	        
	        // Retrieve the existing product by ID
	        Product existingProduct = null;
	        for (Product p : productManager.getProducts()) {
	            if (p.getProductId().equals(productId)) {
	                existingProduct = p;
	                break;
	            }
	        }
	        
	        if (existingProduct == null) {
	            logger.log(Level.INFO, "Product ID not found.");
	            return;
	        }
	        
	        // Prompt user for new values, keeping existing values if input is empty
	        logger.log(Level.INFO, "Enter new product name (or press Enter to keep current: {0}): ", existingProduct.getProductName());
	        String productName = scanner.nextLine();
	        if (!productName.isEmpty()) {
	            existingProduct.setProductName(productName);
	        }
	        
	        logger.log(Level.INFO, "Enter new product description (or press Enter to keep current: {0}): ", existingProduct.getDescription());
	        String description = scanner.nextLine();
	        if (!description.isEmpty()) {
	            existingProduct.setDescription(description);
	        }
	        
	        logger.log(Level.INFO, "Enter new product price (or press Enter to keep current: {0}): ", existingProduct.getPrice());
	        String priceInput = scanner.nextLine();
	        if (!priceInput.isEmpty()) {
	            try {
	                double price = Double.parseDouble(priceInput);
	                existingProduct.setPrice(price);
	            } catch (NumberFormatException e) {
	                logger.log(Level.WARNING, "Invalid price input.");
	            }
	        }
	        
	        logger.log(Level.INFO, "Enter new product weight (or press Enter to keep current: {0}): ", existingProduct.getWeight());
	        String weight = scanner.nextLine();
	        if (!weight.isEmpty()) {
	            existingProduct.setWeight(weight);
	        }
	        
	        logger.log(Level.INFO, "Enter new product availability (or press Enter to keep current: {0}): ", existingProduct.getAvailability());
	        String availability = scanner.nextLine();
	        if (!availability.isEmpty()) {
	            existingProduct.setAvailability(availability);
	        }
	        
	        
	        
	        boolean result = productManager.updateProduct(productId, existingProduct);
	        if (result) {
	            logger.log(Level.INFO, "Product updated successfully.");
	        } else {
	            logger.log(Level.INFO, "Failed to update product. Product ID may not exist.");
	        }
	    }


	    private void deleteProduct(Scanner scanner) {
	        logger.log(Level.INFO, "Enter product ID to delete: ");
	        String productId = scanner.nextLine();
	        boolean result = productManager.deleteProduct(productId);
	        if (result) {
	            logger.log(Level.INFO, "Product deleted successfully.");
	        } else {
	            logger.log(Level.INFO, "Failed to delete product. Product ID may not exist.");
	        }
	    }

	    public void monitorSales() {
	       
	       
	        Map<String, Double> salesTotals = new HashMap<>();
	        
	        try (BufferedReader salesReader = new BufferedReader(new FileReader(SALES_FILE_PATH))) {
	            String line;
	            while ((line = salesReader.readLine()) != null) {
	                String[] parts = line.split(", ");
	                String productName = parts[1];
	                int quantity = Integer.parseInt(parts[2]);
	                double pricePerUnit = Double.parseDouble(parts[3]);

	                double totalSalesForProduct = quantity * pricePerUnit;
	                salesTotals.put(productName, salesTotals.getOrDefault(productName, 0.0) + totalSalesForProduct);
	            }
	        } catch (IOException e) {
	            logger.log(Level.SEVERE, "Error reading sales file", e);
	        }

	        logger.log(Level.INFO, "Sales Summary:");
	        for (Map.Entry<String, Double> entry : salesTotals.entrySet()) {
	            String productName = entry.getKey();
	            double totalSales = entry.getValue();
	            logger.log(Level.INFO, String.format("Product: %s, Total Sales: $%.2f", productName, totalSales));
	        }
	    }

	    private Map<String, Double> loadProductPrices() {
	        Map<String, Double> productPrices = new HashMap<>();
	        try (BufferedReader contentReader = new BufferedReader(new FileReader(CONTENT_FILE_PATH))) {
	            String line;
	            while ((line = contentReader.readLine()) != null) {
	                String[] parts = line.split(", ");
	                String productName = parts[1];
	                double price = Double.parseDouble(parts[3]);
	                productPrices.put(productName, price);
	            }
	        } catch (IOException e) {
	            logger.log(Level.SEVERE, "Error reading content file", e);
	        }
	        return productPrices;
	    }

	    public void identifyBestSellingProducts() {
	        String salesFilePath = "src/main/resources/myData/sales.txt";

	        Map<String, Integer> productSalesCount = new HashMap<>();

	        try (BufferedReader reader = new BufferedReader(new FileReader(salesFilePath))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                if (!line.trim().isEmpty()) { 	                    String[] parts = line.split(", ");
	                    if (parts.length >= 4) { 	                        String productName = parts[1];
	                        int quantity = Integer.parseInt(parts[2]);

	                        productSalesCount.merge(productName, quantity, Integer::sum);
	                    } else {
	                        logger.log(Level.WARNING, "Skipping invalid sales line: " + line);
	                    }
	                }
	            }
	        } catch (IOException e) {
	            logger.log(Level.SEVERE, "Error reading sales file: " + e.getMessage());
	            return;
	        }

	        String bestSellingProduct = null;
	        int maxSales = 0;

	        for (Map.Entry<String, Integer> entry : productSalesCount.entrySet()) {
	            if (entry.getValue() > maxSales) {
	                maxSales = entry.getValue();
	                bestSellingProduct = entry.getKey();
	            }
	        }

	        if (bestSellingProduct != null) {
	            logger.log(Level.INFO, "Best Selling Product: " + bestSellingProduct + " with " + maxSales + " units sold.");
	            bestSellingProductsFlag = true;
	        } else {
	            logger.log(Level.INFO, "No sales data available.");
	            bestSellingProductsFlag = false;

	        }
	    }
	    private double price;

		private List<String> availableProducts; 
	    public void applyDiscount(double discountPercentage) {
	        if (discountPercentage < 0 || discountPercentage > 100) {
	            throw new IllegalArgumentException("Discount percentage must be between 0 and 100.");
	        }
	        this.price = this.price * (1 - discountPercentage / 100);
	    }
	    public void implementDynamicDiscount() {
	        Scanner scanner = new Scanner(System.in);
	        logger.log(Level.INFO, "Enter discount percentage (e.g., 10 for 10%): ");
	        String input = scanner.nextLine();

	        try {
	            double discountPercentage = Double.parseDouble(input.replaceAll("[^\\d.]", ""));

	            if (discountPercentage < 0 || discountPercentage > 100) {
	                logger.log(Level.WARNING, "Invalid discount percentage. Please enter a value between 0 and 100.");
	                return;
	            }

	            String productsFilePath = "src/main/resources/myData/content.txt";
	            List<String> updatedProducts = new ArrayList<>();

	            try (BufferedReader reader = new BufferedReader(new FileReader(productsFilePath))) {
	                String line;
	                boolean isHeader = true;  // Flag to skip header line

	                while ((line = reader.readLine()) != null) {
	                    if (isHeader) {
	                        updatedProducts.add(line);  // Add header line without modification
	                        isHeader = false;
	                    } else {
	                        String[] parts = line.split(",");
	                        if (parts.length == 7) { // Ensure there are 7 columns
	                            String id = parts[0];
	                            String name = parts[1];
	                            String description = parts[2];
	                            double price = Double.parseDouble(parts[3].trim());
	                            String weight = parts[4];
	                            String availability = parts[5];
	                            int quantity = Integer.parseInt(parts[6].trim());

	                            // Apply discount
	                            double discountedPrice = price * (1 - discountPercentage / 100);
	                            String updatedProductLine = String.format("%s,%s,%s,%.2f,%s,%s,%d",
	                                    id, name, description, discountedPrice, weight, availability, quantity);
	                            updatedProducts.add(updatedProductLine);
	                        } else {
	                            logger.log(Level.WARNING, "Skipping invalid product line: " + line);
	                        }
	                    }
	                }
	            } catch (IOException e) {
	                logger.log(Level.SEVERE, "Error reading product file: " + e.getMessage());
	                return;
	            }

	            try (BufferedWriter writer = new BufferedWriter(new FileWriter(productsFilePath))) {
	                for (String product : updatedProducts) {
	                    writer.write(product);
	                    writer.newLine();
	                }
	                logger.log(Level.INFO, "Discount applied successfully!");
	            } catch (IOException e) {
	                logger.log(Level.SEVERE, "Error writing to product file: " + e.getMessage());
	            }
	        } catch (NumberFormatException e) {
	            logger.log(Level.WARNING, "Invalid input. Please enter a valid discount percentage.");
	        }
	    }



	   

	    public void setGmail(Gmail gmail) {
	        this.gmail = gmail;
	    }

	    

	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public Gmail getGmail() {
	        return gmail;
	    }

	   

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public int getPhone() {
	        return phone;
	    }

	    public void setPhone(int phone) {
	        this.phone = phone;
	    }

	    public boolean isManageProductsFlag() {
	        return manageProductsFlag;
	    }

	 
	    public void setManageProductsFlag(boolean manageProductsFlag) {
	        this.manageProductsFlag = manageProductsFlag;
	    }

	    public boolean isMonitorSalesFlag() {
	        return monitorSalesFlag;
	    }

	    public void setMonitorSalesFlag(boolean monitorSalesFlag) {
	        this.monitorSalesFlag = monitorSalesFlag;
	    }
	    public void whatAdminEnter(String AdminChoice){
	        if (AdminChoice.equals("1")){
	        	setManageProductsFlag(true);
	        } else if (AdminChoice.equals("2")) {
	        	setMonitorSalesFlag(true);
	        } else if (AdminChoice.equals("3")) {
	        	setBestSellingProductsFlag(true);
	        }
	        else if (AdminChoice.equals("4")) {
	        	setTrackOrdersFlag(true);
	        }
	        
	        else {
	        	setManageProductsFlag(false);
	        	setMonitorSalesFlag(false);
	        	setBestSellingProductsFlag(false);
	        	setTrackOrdersFlag(false);
	        	
	        }
	    }
	    public boolean isBestSellingProductsFlag() {
	        return bestSellingProductsFlag;
	    }

	    public void setBestSellingProductsFlag(boolean bestSellingProductsFlag) {
	    	 this.bestSellingProductsFlag = bestSellingProductsFlag;
	    }
	   


	    public boolean isDynamicDiscountFlag() {
	        return dynamicDiscountFlag;
	    }

	    public void setDynamicDiscountFlag(boolean dynamicDiscountFlag) {
	        this.dynamicDiscountFlag = dynamicDiscountFlag;
	    }

	    public boolean isNotificationsFlag() {
	        return notificationsFlag; 
	    }

	    public void setNotificationsFlag(boolean notificationsFlag) {
	        this.notificationsFlag = notificationsFlag;
	    }

	    public boolean isTrackOrdersFlag() {
	        return trackOrdersFlag;
	    }

	    public void setTrackOrdersFlag(boolean trackOrdersFlag) {
	        this.trackOrdersFlag = trackOrdersFlag;
	    }



	    public void displayAvailableProducts() {
	        if (availableProducts == null || availableProducts.isEmpty()) {
	            System.out.println("No products available.");
	        } else {
	            System.out.println("Available Products:");
	            for (String product : availableProducts) {
	                System.out.println(product);
	            }
	        }
	    }


}
