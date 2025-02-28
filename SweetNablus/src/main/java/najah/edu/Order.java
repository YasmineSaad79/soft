package najah.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;

import static najah.edu.Registration.logger;

public class Order {
    private static final String ORDERS_FILE_PATH = "src/main/resources/myData/orders.txt";
    private Scanner scanner = new Scanner(System.in);
    private static final String CART_FILE_PATH = "src/main/resources/myData/cart.txt";
    private Map<Integer, Integer> cart = new HashMap<>(); 

   
    private int productId;
    private int quantity;
    private String orderStatus = "pending";
  
    private static final Map<Integer, String> ORDER_STATUS = new HashMap<>();
  
    private Product productManager;
    private double totalAmount = 0.0;
    private static final String CONTENT_FILE_PATH = "src/main/resources/myData/content.txt";

    private static String customerName = null;
    private static String idCustomer = null;
    private int orderId;
    private String orderDate;
    private String deliveryDate;
    private String status;
    private float orderPrice;
    private boolean PendingOrderflag;
    private boolean ifOrderExist;
    private boolean ifOrderAdded; // Flag to check if order was added
    private boolean ifOrderUpdated; // Flag to check if order was updated
    private boolean ifOrderDeleted; // Flag to check if order was deleted
    private boolean ifProductAdded; // Flag to check if product was added to order
    private Order order;
    private boolean viewOrdersFlag;
    private String gmailIs;
    boolean ifCustomerShowPending ;

    public Order() {
    	productManager = new Product();
    }

    public Order(int orderId, String orderDate, String deliveryDate, String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }
   
    public void setViewOrdersFlag(boolean flag) {
        this.viewOrdersFlag = flag;
    }

    public boolean isViewOrdersFlag() {
        return this.viewOrdersFlag;
    }

    public void viewOrders() {
        try (BufferedReader ordersReader = new BufferedReader(new FileReader(ORDERS_FILE_PATH))) {
            String line;
            while ((line = ordersReader.readLine()) != null) {
                System.out.println(line);             }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading orders file", e);
        }
    }

    private String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

   

    private void printOrderDetails(String[] orderDetails) {
        logger.info("\u001B[34m Order ID: \u001B[35m " + orderDetails[0] + " |" +
                "\u001B[34m Order Date: \u001B[35m " + orderDetails[1] + " |" +
                "\u001B[34m Delivery Date: \u001B[35m " + orderDetails[2] + " |" +
                "\u001B[34m Status: \u001B[35m " + orderDetails[3] + " |");
    }

    public void viewDeliveredOrder(String status) {
        boolean deliveredOrderFound = false;
        int countDelivered = 0;

        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/orders.txt", "rw")) {
            String line;
            while ((line = ref.readLine()) != null) {
                String[] orderDetails = line.split(",");
                
                // Check if the array has enough elements
                if (orderDetails.length > 3 && orderDetails[3].equalsIgnoreCase(status)) {
                    countDelivered++;
                    deliveredOrderFound = true;
                    printDeliveredOrder(orderDetails);
                }
            }

            if (!deliveredOrderFound) {
                logger.info("No delivered orders found for status: " + status);
            } else {
                logger.info("Total delivered orders found: " + countDelivered);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    public void printDeliveredOrder(String[] orderDetails) {
        logger.info("\u001B[34m Order ID: \u001B[35m " + orderDetails[0] + " |" +
                "\u001B[34m Order Date: \u001B[35m " + orderDetails[1] + " |" +
                "\u001B[34m Delivery Date: \u001B[35m " + orderDetails[2] + " |" +
                "\u001B[34m Status: \u001B[35m " + orderDetails[3] + " |");
    }

    public boolean isIfCustomerShowPendingOrder() {
        return PendingOrderflag;
    }

    public void setIfCustomerShowPendingOrder(boolean flag) {
        this.PendingOrderflag = flag;
    }

   
    private void printPendingOrder1(String[] orderDetails) {
        logger.info("\u001B[34m Order ID: \u001B[35m " + orderDetails[0] + " |" +
                "\u001B[34m Order Date: \u001B[35m " + orderDetails[1] + " |" +
                "\u001B[34m Delivery Date: \u001B[35m " + orderDetails[2] + " |" +
                "\u001B[34m Status: \u001B[35m " + orderDetails[3] + " |");
    }
    public void viewPendingOrder(String status, String idCustomer) {
        try (BufferedReader ordersReader = new BufferedReader(new FileReader("src/main/resources/myData/orders.txt"))) {
            String line;
            boolean orderFound = false;
            System.out.println("\n----- Pending Orders -----");
            while ((line = ordersReader.readLine()) != null) {
                String[] orderDetails = line.split(",");
                System.out.println("Line read: " + line);
                System.out.println("Order details length: " + orderDetails.length);
                // Ensure there are enough elements in the array before accessing them
                if (orderDetails.length > 5) {
                    if (orderDetails[5].equals(status) && orderDetails[1].equals(idCustomer)) {
                        System.out.println("Order ID: " + orderDetails[0] +
                                           ", Customer ID: " + orderDetails[1] +
                                           ", Customer Name: " + orderDetails[2] +
                                           ", Product ID: " + orderDetails[3] +
                                           ", Quantity: " + orderDetails[4] +
                                           ", Status: " + orderDetails[5]);
                        orderFound = true;
                    }
                } else {
                    System.out.println("Skipping line due to insufficient fields: " + line);
                }
            }
            if (!orderFound) {
                System.out.println("No pending orders found for customer ID: " + idCustomer);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading orders file", e);
        }
    }
   

    
    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    private void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getFileOrderName() {
        return "orders.txt"; // Assuming the file name is orders.txt
    }

    public boolean isIfOrderExist() {
        return ifOrderExist;
    }

    public void setIfOrderExist(boolean flag) {
        this.ifOrderExist = flag;
    }

    public void searchAboutCustomer(String fileName, long orderId) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/" + fileName, "rw")) {
            String line;
            while ((line = ref.readLine()) != null) {
                String[] orderDetails = line.split(",");
                if (Long.parseLong(orderDetails[0]) == orderId) {
                    setIfOrderExist(true);
                    setCustomerName(orderDetails[4]);
                    setIdCustomer(orderDetails[5]);
                    setOrderPrice(Float.parseFloat(orderDetails[6]));
                    return;
                }
            }
            setIfOrderExist(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setStatusOrder(String newStatus) {
        this.status = newStatus;
    }

    public void editTheOrder(String newStatus) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/orders.txt", "rw")) {
            String line;
            StringBuilder fileContent = new StringBuilder();
            while ((line = ref.readLine()) != null) {
                String[] orderDetails = line.split(",");
                if (Integer.parseInt(orderDetails[0]) == this.orderId) {
                    orderDetails[3] = newStatus; // Assuming the status is at index 3
                    line = String.join(",", orderDetails);
                }
                fileContent.append(line).append("\n");
            }
            ref.seek(0); // Move the pointer to the beginning of the file
            ref.writeBytes(fileContent.toString());
            ifOrderUpdated = true; // Set the flag to true if the order was updated
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOrder(String fileName) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/" + fileName, "rw")) {
            String line;
            StringBuilder fileContent = new StringBuilder();
            while ((line = ref.readLine()) != null) {
                String[] orderDetails = line.split(",");
                if (Integer.parseInt(orderDetails[0]) != this.orderId) {
                    fileContent.append(line).append("\n");
                }
            }
            ref.setLength(0); // Clear the file
            ref.writeBytes(fileContent.toString()); // Write the updated content
            ifOrderDeleted = true; // Set the flag to true if the order was deleted
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewOrderToCustomer(String customerName, String idCustomer, String orderLine) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/orders.txt", "rw")) {
            ref.seek(ref.length()); // Move to the end of the file
            ref.writeBytes(orderLine); // Write the new order line
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewOrderPending(String orderLine) {
        try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/pending_orders.txt", "rw")) {
            ref.seek(ref.length()); // Move to the end of the file
            ref.writeBytes(orderLine); // Write the new order line
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void manageOrders() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, "\n\u001B[34m" + "----- Manage Orders -----" + "\n" +
                "|     1. View Orders          |\n" +
                "|     2. Update Order         |\n" +
                "|     3. Delete Order         |\n" +
                "|     4. Back                 |\n" +
                "-----------------------------\n");
        logger.log(Level.INFO, "Enter your choice: " + "\u001B[0m");
        choice = scanner.nextInt();

        switch (choice) {
            case 1 -> viewOrders();
            case 2 -> updateOrder();
            case 3 -> deleteOrder();
            case 4 -> Admin_menu(getAdminName());
           
            default -> {
                logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31mInvalid choice! Please enter a valid choice." + "\u001B[0m");
                manageOrders();
            }
        }
    }

    private void deleteOrder() {
        // Get the order ID to delete
        System.out.println("Enter the ID of the order you want to delete:");
        String idToDelete = scanner.nextLine().trim();

        List<String> orders = new ArrayList<>();
        boolean found = false;

        // Read the file and filter out the order to delete
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming the ID is the first part of the line
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].trim().equals(idToDelete)) {
                    found = true; // Mark as found if ID matches
                    continue; // Skip adding this line to the list
                }
                orders.add(line); // Add other orders to the list
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading orders file", e);
        }

        if (found) {
            // Write the updated orders back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDERS_FILE_PATH))) {
                for (String order : orders) {
                    writer.write(order);
                    writer.newLine();
                }
                System.out.println("Order deleted successfully.");
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error writing to orders file", e);
            }
        } else {
            System.out.println("Order with ID " + idToDelete + " not found.");
        }
    }
    private boolean updateOrder() {
        System.out.println("Enter the ID of the order you want to update:");
        String idToUpdate = scanner.nextLine().trim();

        List<String> orders = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(ORDERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].trim().equals(idToUpdate)) {
                    found = true;

                    // طلب إدخال التفاصيل الجديدة لكل حقل، مع تخطي أي حقل يتم الضغط على Enter فيه
                    System.out.println("Enter new order date (current: " + parts[1] + "):");
                    String newOrderDate = scanner.nextLine().trim();
                    if (newOrderDate.isEmpty()) {
                        newOrderDate = parts[1];
                    }

                    System.out.println("Enter new delivery date (current: " + parts[2] + "):");
                    String newDeliveryDate = scanner.nextLine().trim();
                    if (newDeliveryDate.isEmpty()) {
                        newDeliveryDate = parts[2];
                    }

                    System.out.println("Enter new status (current: " + parts[3] + "):");
                    String newStatus = scanner.nextLine().trim();
                    if (newStatus.isEmpty()) {
                        newStatus = parts[3];
                    }

                    // إنشاء السطر المحدث للطلب
                    String updatedOrder = idToUpdate + "," + newOrderDate + "," + newDeliveryDate + "," + newStatus;
                    orders.add(updatedOrder);
                } else {
                    orders.add(line);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading orders file", e);
            return false;
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDERS_FILE_PATH))) {
                for (String order : orders) {
                    writer.write(order);
                    writer.newLine();
                }
                System.out.println("Order updated successfully.");
                return true;
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error writing to orders file", e);
                return false;
            }
        } else {
            System.out.println("Order with ID " + idToUpdate + " not found.");
            return false;
        }
    }

    public void Admin_menu(String adminName) {
        System.out.println("Returning to admin menu for: " + adminName);
        // هنا يتم تنفيذ منطق قائمة المسؤول
    }

    public String getAdminName() {
        return "Admin"; // استبدل هذا المنطق بالمنطق الفعلي للحصول على اسم المسؤول
    }

    public void makePurchasesMenu() {
    	 int choice;
         Scanner scanner = new Scanner(System.in);
         logger.log(Level.INFO, "\n\u001B[34m" + "----- Make Purchases -----" + "\n" +
                 "|     1. add Product         |\n" +
                 "|     2. view Cart          |\n" +
                 "|     3. cancel Order         |\n" +
                 "|     4. Back                 |\n" +
                 "-----------------------------\n");
         logger.log(Level.INFO, "Enter your choice: " + "\u001B[0m");
         choice = scanner.nextInt();

            switch (choice) {
                case 1:
                	addProductToCart();
                    break;
                case 2:
                	viewCart();
                    break;
                case 3:
                	cancelOrder1();
                    break;
    
                   
                case 4:
                    logger.info("Exiting Purchase Menu...");
                    break;
                default:
                    logger.warning("Invalid choice! Please enter a valid choice.");
                    makePurchasesMenu();
            }
    }
    public static void addProductToCart() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();

        try {
            BufferedReader contentReader = new BufferedReader(new FileReader(CONTENT_FILE_PATH));
            String line;
            boolean productFound = false;
            // Skip the header line
            contentReader.readLine();

            while ((line = contentReader.readLine()) != null) {
                String[] productDetails = line.split(",");
                int id = Integer.parseInt(productDetails[0]);
                String name = productDetails[1];
                String description = productDetails[2];
                double price = Double.parseDouble(productDetails[3]);
                String weight = productDetails[4];
                String availability = productDetails[5];
                int availableQuantity = Integer.parseInt(productDetails[6]);

                if (id == productId) {
                    productFound = true;
                    if (availability.equals("In Stock") && availableQuantity >= quantity) {
                        addToCart(productId, name, quantity, price);
                        System.out.println("Product added to cart successfully.");
                    } else {
                        System.out.println("Product is not available in the required quantity.");
                    }
                    break;
                }
            }
            contentReader.close();
            if (!productFound) {
                System.out.println("Product not found.");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading content file", e);
        }
    }

    public static void addToCart(int productId, String name, int quantity, double price) {
        double newTotalPrice = price * quantity;
        double totalPrice = 0.0;

        // Read the current total price from the cart file if it exists
        try {
            BufferedReader cartReader = new BufferedReader(new FileReader(CART_FILE_PATH));
            String line;
            while ((line = cartReader.readLine()) != null) {
                String[] cartDetails = line.split(",");
                if (cartDetails.length == 4) {
                    totalPrice += Double.parseDouble(cartDetails[3]);
                }
            }
            cartReader.close();
        } catch (FileNotFoundException e) {
            // File not found, this means the cart is empty, so totalPrice remains 0.0
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading cart file", e);
        }

        totalPrice += newTotalPrice;

        // Append the new product and total price to the cart file
        try {
            BufferedWriter cartWriter = new BufferedWriter(new FileWriter(CART_FILE_PATH, true));
            cartWriter.write(productId + "," + name + "," + quantity + "," + newTotalPrice + "\n");
            cartWriter.write("Total Price," + totalPrice + "\n");
            cartWriter.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to cart file", e);
        }
    }

    public static void viewCart() {
        try {
            BufferedReader cartReader = new BufferedReader(new FileReader(CART_FILE_PATH));
            String line;
            double totalPrice = 0.0;
            System.out.println("\n----- Cart Contents -----");
            while ((line = cartReader.readLine()) != null) {
                String[] cartDetails = line.split(",");
                if (cartDetails[0].equals("Total Price")) {
                    totalPrice = Double.parseDouble(cartDetails[1]);
                } else {
                    System.out.println("Product ID: " + cartDetails[0] +
                            ", Name: " + cartDetails[1] +
                            ", Quantity: " + cartDetails[2] +
                            ", Price: " + cartDetails[3]);
                }
            }
            System.out.println("Total Price: " + totalPrice);
            cartReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cart is empty.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading cart file", e);
        }
    }  
    
    public static void cancelOrder1() {
        try {
            BufferedWriter cartWriter = new BufferedWriter(new FileWriter(CART_FILE_PATH, false));
            cartWriter.write(""); // Write an empty string to clear the file
            cartWriter.close();
            System.out.println("All orders have been cancelled and the cart is now empty.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error clearing cart file", e);
        }
    }

    public void addProductToOrder() {
        ifProductAdded = true;
        System.out.println("Product added to order.");
    }

   

   
    public void setGmailIs(String email) {
        this.gmailIs = email;
    }

    public boolean checkProductExistence(String productId) {
        return productId != null && !productId.isEmpty();
    }

    public boolean validateProductBeforeAdding() {
        return checkProductExistence("someProductId");
    }

    public String getStatusOrder() {
        return this.status;
    }

    public String getIdCustomer() {
        return this.idCustomer;
    }

    public boolean editProductQuantity() {
        return true;
    }
    
    
   

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public boolean editProductQuantity(int quantity) {
        // Logic to edit quantity in cart
        // Check if the quantity is allowed based on available stock
        try {
            BufferedReader contentReader = new BufferedReader(new FileReader("src/main/resources/myData/content.txt"));
            String line;
            while ((line = contentReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == productId) {
                    int availableQuantity = Integer.parseInt(parts[6]);
                    if (quantity <= availableQuantity && quantity > 0) {
                        cart.put(productId, quantity);
                        updateCartFile();
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean createOrder(String customerId, String customerName, int productId, int quantity) {
        try {
            BufferedReader contentReader = new BufferedReader(new FileReader(CONTENT_FILE_PATH));
            String line;
            boolean productFound = false;
            // Skip the header line
            contentReader.readLine();

            while ((line = contentReader.readLine()) != null) {
                String[] productDetails = line.split(",");
                int id = Integer.parseInt(productDetails[0]);
                String name = productDetails[1];
                double price = Double.parseDouble(productDetails[3]);
                String availability = productDetails[5];
                int availableQuantity = Integer.parseInt(productDetails[6]);

                if (id == productId) {
                    productFound = true;
                    if (availability.equals("In Stock") && availableQuantity >= quantity) {
                        addToCart(productId, name, quantity, price);
                        saveOrder(customerId, customerName, productId, quantity, "pending");
                        return true;
                    } else {
                        System.out.println("Product is not available in the required quantity.");
                        return false;
                    }
                }
            }
            contentReader.close();
            if (!productFound) {
                System.out.println("Product not found.");
                return false;
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading content file", e);
            return false;
        }
        return false;
    }

    private static void saveOrder(String customerId, String customerName, int productId, int quantity, String status) {
        try {
            BufferedWriter orderWriter = new BufferedWriter(new FileWriter(ORDERS_FILE_PATH, true));
            String orderId = UUID.randomUUID().toString().replace("-", "");
            orderWriter.write(orderId + "," + customerId + "," + customerName + "," + productId + "," + quantity + "," + status + "\n");
            orderWriter.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to orders file", e);
        }
    }
    public boolean isOrderCreated() {
        // Check if order was created
        try {
            BufferedReader ordersReader = new BufferedReader(new FileReader(ORDERS_FILE_PATH));
            String line;
            while ((line = ordersReader.readLine()) != null) {
                if (line.contains(idCustomer) && line.contains("pending")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getOrderStatus() {
        // Return order status for the given customer
        try {
            BufferedReader ordersReader = new BufferedReader(new FileReader(ORDERS_FILE_PATH));
            String line;
            while ((line = ordersReader.readLine()) != null) {
                if (line.contains(idCustomer)) {
                    String[] parts = line.split(",");
                    return parts[4]; // Assuming status is in the 5th column
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean cancelOrder() {
        // Logic to cancel the order
        try {
            List<String> lines = Files.readAllLines(Paths.get(ORDERS_FILE_PATH));
            List<String> newLines = new ArrayList<>();
            boolean orderCanceled = false;
            for (String line : lines) {
                if (line.contains(idCustomer) && line.contains("pending")) {
                    orderCanceled = true;
                } else {
                    newLines.add(line);
                }
            }
            if (orderCanceled) {
                Files.write(Paths.get(ORDERS_FILE_PATH), newLines);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean canEditQuantity() {
        // Check if the quantity can be edited
        // This could be based on business logic such as order status
        return "pending".equals(orderStatus);
    }

    public boolean canEditProductQuantity(int productId) {
        // Check if the quantity of the specific productId can be edited
        return cart.containsKey(productId) && canEditQuantity();
    }

    private void updateCartFile() throws IOException {
        // Update the cart file with the current cart data
        BufferedWriter cartWriter = new BufferedWriter(new FileWriter(CART_FILE_PATH));
        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            cartWriter.write(entry.getKey() + "," + entry.getValue() + "\n");
        }
        cartWriter.close();
    }

    public boolean isProductExisting() {
        // Check if the product ID exists in the content file
        try {
            BufferedReader contentReader = new BufferedReader(new FileReader("src/main/resources/myData/content.txt"));
            String line;
            while ((line = contentReader.readLine()) != null) {
                if (line.startsWith(String.valueOf(productId))) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPromptForValidQuantity() {
        // Logic to prompt for a valid quantity
        return !editProductQuantity(quantity);
    }

    public void setOrderStatusFilter(String status) {
        // Set the order status filter
        this.orderStatus = status;
    }

    public void setOrderId(String orderId) {
        // Set the current order ID
        this.idCustomer = orderId;
    }

    public boolean canManageOrders() {
        // Logic to check if orders can be managed
        return true; // Placeholder, replace with actual logic
    }

    public boolean canSendEmailNotifications() {
        // Logic to check if email notifications can be sent
        return true; // Placeholder, replace with actual logic
    }

    public boolean canViewPendingOrders() {
        // Logic to check if pending orders can be viewed
        return true; // Placeholder, replace with actual logic
    }

    public boolean canCalculateOrderTotal() {
        // Logic to calculate the total cost of the order
        return true; // Placeholder, replace with actual logic
    }

    public boolean isValidOption(int option1, int option2, int option3, int option4) {
        // Check if the provided option is valid
        List<Integer> validOptions = Arrays.asList(option1, option2, option3, option4);
        return validOptions.contains(Integer.parseInt(orderStatus));
    }

    public boolean editSpecificProductQuantity(Integer productId) {
        return productId != null && productId > 0;
    }

    public boolean performOwnerOperations() {
        return true;
    }

    public boolean sendEmailNotifications() {
        return gmailIs != null && !gmailIs.isEmpty();
    }

    public double calculateTotalCost() {
        return orderPrice;
    }

    public boolean validateOption(Integer option1, Integer option2, Integer option3, Integer option4) {
        return option1 != null && option2 != null && option3 != null && option4 != null;
    }

}
