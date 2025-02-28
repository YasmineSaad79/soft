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

import java.util.logging.Level;
import java.util.logging.Logger;
import static najah.edu.Registration.logger;

import  najah.edu.Owner;
public class BeneficiaryUser {
    private static final String FILE_PATH = "src/main/resources/myData/BeneficiaryData.txt";
    private static List<BeneficiaryUser> customers = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(BeneficiaryUser.class.getName());
    public static final String BOLD = "\u001B[1m";
    public static final String RESET_COLOR = "\u001B[0m";
	 private static final String CONTENT_FILE_PATH = "src/main/resources/myData/content.txt";
	 private static final String ENTER_YOUR_CHOICE = "Enter your choice: ";
	 private Product productManager = new Product();
	 private Gmail gmail;
	 private boolean testMode = false;
    Order order =new Order();
   Owner owner = new Owner();
   public String getUserName() {
       return userName;
   }
   public void theBeneficiaryUserEnter(String Choice) {
	   if (Choice.equals("1")){
		   setBrowseProductsFlag(true);
       } else if (Choice.equals("2")) {
    	   setMakePurchasesFlag(true);
       } 
       else {
    	            setBrowseProductsFlag(false);
           setMakePurchasesFlag(false);
       }
   }
   public int getNumberOfLine() {
       return numberOfLine;
   }

   public void setNumberOfLine(int numberOfLine) {
       this.numberOfLine = numberOfLine;
   }

   int  numberOfLine;
   public void setUserName(String userName) {
       this.userName = userName;
   }

   public Gmail getGmail() {
       return gmail;
   }

   public void setGmail(String gmail) {
       Gmail1 = gmail;
   }

   public String getPassword() {
       return Password;
   }

   public void setPassword(String password) {
       Password = password;
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

   public String getPhone() {
       return phone;
   }
   public void setPhone(String newPhone) {
	    this.phone = newPhone;
	}

   public void setTestMode(boolean testMode) {
       this.testMode = testMode;
   }

   String userName;
   String Gmail1;
   String Password;
   String address;
   int id;
   String phone;
   public void setCustomerLogin(boolean customerLogin) {
       this.customerLogin = customerLogin;
   }
   private boolean customerLogin ;
   private boolean browseProductsFlag;
   public boolean isSettingFlag() {
       return settingFlag;
   }
   public boolean isfeedbackflag() {
	  return feedbackFlag;
   }
   public BeneficiaryUser(String userName, String email, String password, String address, int id, String phone) {
       this.userName = userName;
       this.gmail = new Gmail(email);
       this.Password = password;
       this.address = address;
       this.id = id;
       this.phone = phone;
   
   }
   public void setfeedbackflag(boolean feedbackflag) {
	   this.feedbackFlag=feedbackflag;

   }

   public void setSettingFlag(boolean settingFlag) {
       this.settingFlag = settingFlag;
   }

   public boolean settingFlag;

  
   public void setBrowseProductsFlag(boolean browseProductsFlag) {
       this.browseProductsFlag = browseProductsFlag;
   }

   public boolean isMakePurchasesFlag() {
       return makePurchasesFlag;
   }

   public void setMakePurchasesFlag(boolean makePurchasesFlag) {
       this.makePurchasesFlag = makePurchasesFlag;
   }

 


   private boolean  feedbackFlag;

   private boolean makePurchasesFlag;
   public BeneficiaryUser() {
       customerLogin = true;
       browseProductsFlag=false;
       makePurchasesFlag=false;
       settingFlag=false;
   }

   public boolean isCustomerLogin() {
       return customerLogin;
   }



   public void Customer_menu (String CostName) {
       setUserName(CostName);
       setBrowseProductsFlag(false);
       setMakePurchasesFlag(false);
       setSettingFlag(false);
       int choice;
       Scanner scanner = new Scanner(System.in);
       logger.log(Level.INFO, "\n\u001B[32m" + " -------  Welcome " +  CostName  + " ---------" + "\n" +
               "|                                  |\n" +
               "|      1. Browse products           |\n" +
               "|      2. Make purchases            |\n" +
               "|      3. Manage personal account   |\n" +
               "|      4. Provide feedback          |\n" +  
               "|                                  |\n" +
               "-----------------------------------\n");
    logger.log(Level.INFO, ENTER_YOUR_CHOICE + RESET_COLOR);


       choice = scanner.nextInt();
       if (choice == 1) {
           browseProductsFlag = true;
           BrowseProductsMenu();
       } else if (choice == 2) {
           makePurchasesFlag = true;
           userAccountMenu();

       } else if (choice == 3) {
    	   settingFlag = true;
    	   managePersonalAccount();

       }
       else if (choice == 4) {
    	   feedback();
    	   feedbackFlag = true;

       }
      
       else {
    	    logger.log(Level.WARNING,BOLD + "\u001B[31mInvalid choice! Please enter one, two, or three.\u001B[0m");
    	}
   }
  
 
   public void managePersonalAccount() {
       if (testMode) {
           logger.log(Level.INFO, "Test mode is active. Skipping user input.");
           updateUserInFile("test@example.com", 0, "Test Name");
           return;
       }

       Scanner scanner = new Scanner(System.in);
       logger.log(Level.INFO, "Enter your email: ");
       String email = scanner.nextLine(); // Get user's email

       while (true) {
           logger.log(Level.INFO, "\n\u001B[32m" + " -------  Manage Personal Account  ---------" + "\n" +
                   "|                                  |\n" +
                   "|      1. Edit name                |\n" +
                   "|      2. Edit email               |\n" +
                   "|      3. Edit password            |\n" +
                   "|      4. Edit address             |\n" +
                   "|      5. Edit phone number        |\n" +
                   "|      6. Back                     |\n" +
                   "|                                  |\n" +
                   "-----------------------------------\n");
           logger.log(Level.INFO, ENTER_YOUR_CHOICE + RESET_COLOR);

           int choice = scanner.nextInt();
           scanner.nextLine(); 

           switch (choice) {
               case 1:
                   logger.log(Level.INFO, "Enter new name: ");
                   String newName = scanner.nextLine();
                   updateUserInFile(email, 0, newName);
                   logger.log(Level.INFO, "Name updated successfully.");
                   break;
               case 2:
                   logger.log(Level.INFO, "Enter new email: ");
                   String newEmail = scanner.nextLine();
                   updateUserInFile(email, 1, newEmail);
                   logger.log(Level.INFO, "Email updated successfully.");
                   break;
               case 3:
                   logger.log(Level.INFO, "Enter new password: ");
                   String newPassword = scanner.nextLine();
                   updateUserInFile(email, 2, newPassword);
                   logger.log(Level.INFO, "Password updated successfully.");
                   break;
               case 4:
                   logger.log(Level.INFO, "Enter new address: ");
                   String newAddress = scanner.nextLine();
                   updateUserInFile(email, 3, newAddress);
                   logger.log(Level.INFO, "Address updated successfully.");
                   break;
               case 5:
                   logger.log(Level.INFO, "Enter new phone number: ");
                   String newPhone = scanner.nextLine();
                   updateUserInFile(email, 5, newPhone);
                   logger.log(Level.INFO, "Phone number updated successfully.");
                   break;
               case 6:
                   Customer_menu(getUserName());
                   return;
               default:
                   logger.log(Level.WARNING, "Invalid choice. Please try again.");
                   break;
           }
       }
   }

  
  
   public void updateUserInFile(String email, int fieldIndex, String newValue) {
	    List<String> lines = new ArrayList<>();
	    boolean userFound = false; // Flag to check if the user was found and updated

	    // Read the file and update the relevant user's line
	    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] fields = line.split(",");
	            if (fields[1].trim().equals(email)) { // Check if email matches
	                userFound = true;
	                switch (fieldIndex) {
	                    case 0:
	                        fields[0] = newValue; // Update name
	                        break;
	                    case 1:
	                        fields[1] = newValue; // Update email
	                        break;
	                    case 2:
	                        fields[2] = newValue; // Update password
	                        break;
	                    case 3:
	                        fields[3] = newValue; // Update address
	                        break;
	                    case 5:
	                        fields[5] = newValue; // Update phone number
	                        break;
	                    default:
	                        logger.log(Level.WARNING, "Invalid field index: " + fieldIndex);
	                        return;
	                }
	                // Join updated fields into a single line
	                lines.add(String.join(",", fields));
	                logger.log(Level.INFO, "User with email " + email + " updated successfully.");
	            } else {
	                // Add unchanged line
	                lines.add(line);
	            }
	        }
	    } catch (IOException e) {
	        logger.log(Level.SEVERE, "Error reading file", e);
	        return;
	    }

	    // Write the updated content back to the file
	    if (userFound) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
	            for (String updatedLine : lines) {
	                writer.write(updatedLine);
	                writer.newLine();
	            }
	            logger.log(Level.INFO, "File updated successfully.");
	        } catch (IOException e) {
	            logger.log(Level.SEVERE, "Error writing file", e);
	        }
	    } else {
	        logger.log(Level.WARNING, "User with email " + email + " not found.");
	    }
	}

   public void userAccountMenu(){
       if (browseProductsFlag){
           BrowseProductsMenu();
           back();
       }
       else if (makePurchasesFlag) {
           
           order.makePurchasesMenu();
           back();
       }
       
       
     
       else {
           logger.log(Level.WARNING,BOLD+"\u001B[31mInvalid choice! Please enter a valid choice."+RESET_COLOR);
           back();
       }
   }
   public void back() {
       logger.log(Level.INFO, """

               1) back\s
               2) Exit""");
       int choice;
       Scanner scanner = new Scanner(System.in);
       choice = scanner.nextInt();
       if (choice == 1)
           Customer_menu(getUserName());
       System.exit(0);
   }

   public void BrowseProductsMenu() {
	    if (testMode) {
	        showAllProducts();
	        searchProducts("Test Product");
	        return; 
	    }

	    Scanner scanner = new Scanner(System.in);
	    boolean running = true;

	    while (running) {
	        logger.log(Level.INFO, """
	              
	              \u001B[35m---------------------------------
	              |                                    |
	              |      1. Show all products          |
	              |      2. Search products            |
	              |      3. Back                       |
	              |      4. Exit                       |  
	              |                                    | 
	              --------------------------------------
	              """);
	        logger.log(Level.INFO, ENTER_YOUR_CHOICE + RESET_COLOR);

	        int choice = scanner.nextInt();
	        scanner.nextLine();  // Consume newline

	        switch (choice) {
	            case 1:
	                showAllProducts();
	                break;
	            case 2:
	                logger.log(Level.INFO, "Enter the search term: ");
	                String searchTerm = scanner.nextLine();
	                searchProducts(searchTerm);
	                break;
	            case 3:
	                Customer_menu(getUserName());
	                break;
	            case 4: // Handle the exit option
	                logger.log(Level.INFO, "Exiting the menu.");
	                running = false;
	                break;
	            default:
	                logger.log(Level.INFO, "Invalid choice. Please try again.");
	                break;
	        }
	    }
	}

   private void showAllProducts() {
       List<String> products = readProductsFromFile();
       if (products.isEmpty()) {
           logger.log(Level.INFO, "No products available.");
       } else {
           logger.log(Level.INFO, "Products List:");
           for (String product : products) {
               logger.log(Level.INFO, product);
           }
       }
   }

   private void searchProducts(String searchTerm) {
       List<String> products = readProductsFromFile();
       boolean found = false;
       logger.log(Level.INFO, "Search Results:");
       for (String product : products) {
           if (product.toLowerCase().contains(searchTerm.toLowerCase())) {
               logger.log(Level.INFO, product);
               found = true;
           }
       }
       if (!found) {
           logger.log(Level.INFO, "No products found matching: " + searchTerm);
       }
   }

   private List<String> readProductsFromFile() {
       List<String> products = new ArrayList<>();
       try (BufferedReader reader = new BufferedReader(new FileReader(CONTENT_FILE_PATH))) {
           String line;
           while ((line = reader.readLine()) != null) {
               products.add(line);
           }
       } catch (IOException e) {
           logger.log(Level.SEVERE, "Error reading the products file.", e);
       }
       return products;
   }

  



   public void menuCustomerAdmin() {
       int choice;
       Scanner scanner = new Scanner(System.in);
       logger.log(Level.INFO,"\n\u001B[33m"+"----- Manage BeneficiaryUser Accounts -----"+"\n"+
               "|                                    |\n" +
               "|     1. View user accounts.     |\n"+
               "|     2. Add user Account.       |\n"+
               "|     3. Delete user Account.    |\n"+
               "|                                    |\n"+
               "-------------------------------------\n");
       logger.log(Level.INFO,ENTER_YOUR_CHOICE+RESET_COLOR);
       choice = scanner.nextInt();
       if (choice == 1) {
           printCustomerAccount();
       } else if (choice == 2) {
           addNewCustomer();
       } else if (choice == 3) {

       } else {
           logger.log(Level.WARNING,BOLD+"\u001B[31mInvalid choice! Please enter a valid choice."+RESET_COLOR);
       }
   }
  public void printCustomerAccount(){

   }
public void addNewCustomer(){

}




   public boolean truepass (String pass, String ConfirmPass){
       if(pass.equals(ConfirmPass)){
           return true;
       }
       return false;
   }
   public void writeToFile(String dataToWrite,String fileName){
       try {


           RandomAccessFile file = new RandomAccessFile("src/main/resources/myData/"+fileName+".txt", "rw");

           file.seek(file.length());
           file.writeBytes(dataToWrite);
           file.close();
       }
       catch (IOException e) {
           e.printStackTrace();
       }}
public void setTheCustomerIs(int numberOfLineCustomer){
       setNumberOfLine(numberOfLineCustomer);
}

  

public void deleteLine() {
   try {
       RandomAccessFile raf = new RandomAccessFile("src/main/resources/myData/BeneficiaryData.txt", "rw");
       long start = 0;
       long currentPos = raf.getFilePointer();
       int currentLine = -1;

       while (currentLine < getNumberOfLine()){
           start = currentPos;
           raf.readLine();
           currentPos = raf.getFilePointer();
           currentLine++;
       }

       // Save the rest of the file after the line to be deleted
       long end = raf.length();
       byte[] remainingBytes = new byte[(int) (end - currentPos)];
       raf.read(remainingBytes);

       raf.seek(start);
       raf.write(remainingBytes);
       raf.setLength(start + remainingBytes.length);
       raf.close();

   } catch (IOException e) {
       throw new RuntimeException(e);
   }

}
   
   private List<Product> products;

   public boolean isBrowseProductsFlag() {
       return browseProductsFlag;
   }

   public List<String> searchProductsByName(String name) {
       return searchProductsByCriteria(name);
   }

   public List<String> searchProductsByID(String id) {
       return searchProductsByCriteria(id);
   }

   public List<String> searchProductsByDescription(String description) {
       return searchProductsByCriteria(description);
   }

   public List<String> searchProductsByAvailability(String availability) {
       return searchProductsByCriteria(availability);
   }

   private List<String> searchProductsByCriteria(String criteria) {
       List<String> products = readProductsFromFile();
       List<String> matchedProducts = new ArrayList<>();
       for (String product : products) {
           if (product.toLowerCase().contains(criteria.toLowerCase())) {
               matchedProducts.add(product);
           }
       }
       return matchedProducts;
   }

  
   
   private int parseInteger(String value) {
	    try {
	        return Integer.parseInt(value);
	    } catch (NumberFormatException e) {
	        logger.log(Level.WARNING, "Invalid number format: " + value, e);
	        return -1; // or any default value that makes sense for your application
	    }
	}
   public void searchTheCustomerNewLine() {
       int count=-1;
       try (RandomAccessFile ref = new RandomAccessFile("src/main/resources/myData/content.txt", "rw")) {
           String s;
           String isd= String.valueOf(getId());
           while ((s = ref.readLine()) != null) {
               count=count+1;
               String[] productInfo = s.split(",");
               if (isd.equals(productInfo[4]))
               {
                   setUserName(productInfo[0]);
                   setNumberOfLine(count);
                   return;
               }
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }
public void feedback() {
    Scanner scanner = new Scanner(System.in);
    logger.log(Level.INFO, "\n\u001B[32m" + "----- Provide Feedback -----" + "\n" +
               "Please enter your feedback below:" + RESET_COLOR);
    String userFeedback = scanner.nextLine();

    String feedbackFilePath = "src/main/resources/myData/Feedback.txt";

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(feedbackFilePath, true))) {
        writer.write(userFeedback);
        writer.newLine();
        logger.log(Level.INFO, "\u001B[32mThank you for your feedback!\u001B[0m");
    } catch (IOException e) {
        logger.log(Level.SEVERE, BOLD + "\u001B[31mError writing feedback: " + e.getMessage() + RESET_COLOR);
    }
}
}
