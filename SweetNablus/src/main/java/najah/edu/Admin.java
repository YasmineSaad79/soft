package najah.edu;
import static najah.edu.Registration.logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
public class Admin {
		    private static final String INVALID_CHOICE_MESSAGE = "\u001B[31mInvalid choice! Please enter a valid choice.";
		    private boolean testMode = false;
	    private static final String A_FILE_PATH = "src/main/resources/myData/AdminData.txt";
	 private static final String RECIPES_FILE_PATH = "src/main/resources/myData/recipes.txt";
	    private static final String POSTS_FILE_PATH = "src/main/resources/myData/posts.txt";
	    private static final String BENEFICIARY_FILE_PATH = "src/main/resources/myData/BeneficiaryData.txt";
	    private static final String CONTENT_FILE_PATH = "src/main/resources/myData/content.txt";
	    private static final String REPORT_FILE_PATH = "src/main/resources/myData/financial_report.html";
	    private static final String ENTER_YOUR_CHOICE = "Enter your choice: ";
	    public static final String BOLD = "\u001B[1m";
	    public static final String RESET_COLOR = "\u001B[0m";
    Order order = new Order();
    Product product = new Product();
    
    BeneficiaryUser beneficiaryUser = new BeneficiaryUser();
    Owner owner = new Owner();
    private String first;
    private String sec;
    private String third;
    private String adminName;
    private boolean PostsFlag;
    private boolean adminLogin;
    private boolean productFlag;
    private boolean feedbackFlagFlag;
    private boolean userAccountsFlag;
    private boolean orderCustomerFlag;
    private boolean recipesFlag;
    private int adminRoleChoice;
    private boolean generateReportsFlag ; 
    private boolean viewUserStatsFlag ;

    public Admin() {
        adminLogin = true;
        productFlag = false;
        feedbackFlagFlag = false;
        userAccountsFlag = false;
        PostsFlag = false;
        recipesFlag = false;
        generateReportsFlag = false;
        viewUserStatsFlag = false;
    }
    
   
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public boolean isAdminLogin() {
        return adminLogin;
    }
    

    public void setAdminLogin(boolean adminLogin) {
        this.adminLogin = adminLogin;
    }
   
   
    public void setfeedback(boolean feedbackFlagFlag) {
        this.feedbackFlagFlag = feedbackFlagFlag;
    }
    
    public boolean isProductsFlag() {
        return productFlag;
    }

    public void setProductsFlag(boolean productFlag) {
        this.productFlag = productFlag;
    }
    
    public void setRecipes(boolean recipesFlag) {
        this.recipesFlag = recipesFlag;
    }
   
    public void setPosts(boolean PostsFlag) {
        this.PostsFlag = PostsFlag;
    }
    public void setViewUserStatsFlag(boolean flag) {
        this.viewUserStatsFlag = flag;
    }

    public boolean isViewUserStatsFlag() {
        return viewUserStatsFlag;
    }
    public void setGenerateReportsFlag(boolean flag) {
        this.generateReportsFlag = flag;
    }

    public boolean isGenerateReportsFlag() {
        return generateReportsFlag;
    }
    
    public boolean isUserAccountsFlag() {
        return userAccountsFlag;
    }
    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public String getFinancialReportContent() {
        StringBuilder reportContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(REPORT_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                reportContent.append(line).append("\n");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading financial report", e);
        }

        return reportContent.toString();
    }

    public void setUserAccountsFlag(boolean userAccountsFlag) {
        this.userAccountsFlag = userAccountsFlag;
    }

   
  
    public int getAdminRoleChoice() {
        return adminRoleChoice;
    }

    public void setAdminRoleChoice(int adminRoleChoice) {
        this.adminRoleChoice = adminRoleChoice;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void Admin_menu(String AdminName) {
        setProductsFlag(false);
        setUserAccountsFlag(false);
        setRecipes(false);
        setAdminName(AdminName);
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, "\n\u001B[37m" + "----------  Welcome " + AdminName + " -------" + "\n" +
                "|    1. Manage user accounts                  |\n" +
                "|    2. Manage feedback                       |\n" +
                "|    3. Manage recipes                        |\n" +
                "|    4. Manage posts                          |\n" +
                "|    5. Generate financial reports            |\n" +
                "|    6. Gather and display user statistics    |\n" +
                "|    7. Identify best-selling products        |\n" +
                "|    8. Exit                                 |\n" +
                "----------------------------------------------\n");
        logger.log(Level.INFO, ENTER_YOUR_CHOICE + RESET_COLOR);
        choice = scanner.nextInt();

        switch (choice) {
        case 1 ->{
        	 userAccountsFlag =true;
        	 menuManageAccountUser();
        	
        }
        case 2 -> {
            feedbackFlagFlag = true;
            manageFeedback();
            
        }
        case 3 ->{
        recipesFlag = true;
        manageRecipes();
        }
        case 4 ->
        {managePosts();
        Admin_menu(AdminName);}
        
        case 5->
        
        {generateFinancialReports();}
        
        case 6 -> gatherAndDisplayUserStatistics();
        case 7 -> identifyBestSellingProducts();

        case 8 -> logger.log(Level.INFO, "Exiting...");
        default -> {
 logger.log(Level.WARNING, INVALID_CHOICE_MESSAGE);
		Admin_menu(AdminName);
        }
    }
}
    public boolean isrecipes() {
        return recipesFlag;
    }
  
    public void menuManageAccountUser() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, "\n\u001B[34m" + "----- Manage User Accounts -----" + "\n" +
                "|     1. Admin                |\n" +
                "|     2. BeneficiaryUser      |\n" +
                "|     3. Back                 |\n" +
                "-------------------------------\n");
        logger.log(Level.INFO,ENTER_YOUR_CHOICE +  RESET_COLOR);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> editAdminProfile();
            case 2 -> BenficaryuserAccountMenu();
           
            case 3 -> Admin_menu(getAdminName());
            default -> {
             logger.log(Level.WARNING, INVALID_CHOICE_MESSAGE);
                menuManageAccountUser();
            }
        }
    }
public void editAdminProfile(){
    int choice;
    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);

    logger.log(Level.INFO,"\n\u001B[36m" + "----- Admin Profile -----"+"\n"+
            "|   1. edit userName   |\n"+
            "|   2. edit Password   |\n"+
            "|   3. edit Gmail      |\n"+
            "|   4. back            |\n"+
            "-----------------------\n");
    logger.log(Level.INFO,ENTER_YOUR_CHOICE+ RESET_COLOR);
    choice = scanner.nextInt();
    String choice2 ;
    String oldPass ;
    String newPass ;
    String newPassCon ;

    if (choice == 1) {
        logger.log(Level.INFO,"Enter The new user Name:"+ RESET_COLOR);
        choice2 = scanner1.nextLine();
        editeUserName(choice2);
        logger.log(Level.INFO,"The user name has been changed successfully"+ RESET_COLOR);
        editAdminProfile();
    }
    else if (choice ==2) {
        logger.log(Level.INFO,"Enter The old password:"+ RESET_COLOR);
        oldPass = scanner1.nextLine();
        logger.log(Level.INFO,"Enter The new password:"+ RESET_COLOR);
        newPass = scanner1.nextLine();
        logger.log(Level.INFO,"Confirm The  password:"+ RESET_COLOR);
        newPassCon = scanner1.nextLine();
        editePassword(oldPass,newPass,newPassCon);
        editAdminProfile();

    }
    else if (choice ==3) {
        logger.log(Level.INFO,"Enter The new Gmail:"+RESET_COLOR);
        choice2 = scanner1.nextLine();
        editeGmail(choice2);
        logger.log(Level.INFO,"The Gmail has been changed successfully"+RESET_COLOR);
        editAdminProfile();

    }
    else if (choice==4){
    	  Admin_menu(getAdminName());
    }
    else {
   logger.log(Level.WARNING, INVALID_CHOICE_MESSAGE);
    }
}

    private void editeGmail(String choice2) {
        fileFunction();
        deleteFileFunction();
        writeToFile(getFirst()+","+choice2+","+getThird());

    }
    private void editePassword(String oldPass, String newPass, String newPassCon) {
        fileFunction();
        if(truepass(oldPass,getThird())){
        if(truepass(newPass,newPassCon)){
            deleteFileFunction();
            writeToFile(getFirst()+","+getSec()+","+newPass);
            logger.log(Level.INFO,"\u001B[35m"+"The Password has been changed successfully"+RESET_COLOR);

        }
        

          }
        else
        logger.log(Level.WARNING,BOLD+"\u001B[31mThe password is incorrect"+RESET_COLOR);

    }

    private void editeUserName(String choice2) {
        fileFunction();
        deleteFileFunction();
        writeToFile(choice2+","+getSec()+","+getThird());
    }
    public void fileFunction(){

        try (RandomAccessFile raf = new RandomAccessFile(A_FILE_PATH, "rw")) {
            String s;
            while ((s = raf.readLine()) != null) {
                String[] loginCustomer = s.split(",");
                first=loginCustomer[0];
                sec=loginCustomer[1];
                third=loginCustomer[2];
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
        public void deleteFileFunction(){

            try (RandomAccessFile raf = new RandomAccessFile(A_FILE_PATH, "rw")) {
                // Seek to the beginning of the file
                long start = 0;
                long currentPos = raf.getFilePointer();
                int currentLine = -1;

                while (currentLine < 0) {
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
 public void writeToFile(String dataToWrite) {
            try (RandomAccessFile file = new RandomAccessFile(A_FILE_PATH, "rw")) {
                file.seek(file.length()); 
                file.writeBytes(dataToWrite); 
                } catch (IOException e) {
                e.printStackTrace(); 
            }
        }
        public boolean truepass (String pass, String ConfirmPass){
            if(pass.equals(ConfirmPass)){
                return true;
            }
            return false;
        }
    
    public void manageRecipes() {
  
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, "\n\u001B[34m" + "----- Manage Recipes -----" + "\n" +
                "|     1. Add Recipe          |\n" +
                "|     2. View Recipes        |\n" +
                "|     3. Edit Recipe         |\n" +
                "|     4. Delete Recipe       |\n" +
                "|     5. Back                |\n" +
                "-----------------------------\n");
        logger.log(Level.INFO, ENTER_YOUR_CHOICE + RESET_COLOR);
        choice = scanner.nextInt();

        switch (choice) {
            case 1 -> addRecipe();
            case 2 -> viewRecipes();
            case 3 -> editRecipe();
            case 4 -> deleteRecipe();
            case 5 -> Admin_menu(getAdminName());
            default -> {
              logger.log(Level.WARNING, INVALID_CHOICE_MESSAGE);
                manageRecipes();
            }
        }
    }
    public void BenficaryuserAccountMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            logger.log(Level.INFO, "\n\u001B[34m" + "----- Manage Beneficiary User Accounts -----" + "\n" +
                    "|     1. View User Accounts              |\n" +
                    "|     2. Update User Details             |\n" +
                    "|     3. Delete User Account             |\n" +
                    "|     4. Back to Previous Menu           |\n" +
                    "-------------------------------------------\n");
            logger.log(Level.INFO, ENTER_YOUR_CHOICE + RESET_COLOR);
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    viewUserAccounts();
                    break;
                case 2:
                    updateUserDetails();
                    break;
                case 3:
                    deleteUserAccount();
                    break;
                case 4:
                    Admin_menu(getAdminName()); // Go back to the admin menu
                    return; // Exit the loop
                default:
                 logger.log(Level.WARNING, INVALID_CHOICE_MESSAGE);
                    break;
            }
        }
    }
    public void viewUserAccounts() {
        try (BufferedReader br = new BufferedReader(new FileReader(BENEFICIARY_FILE_PATH))) {
            String line;
            logger.log(Level.INFO, "\n\u001B[34m" + "----- Beneficiary User Accounts -----" + "\n" +
                    "| Name     | Email                | Password | Address                  | ID   | Phone Num |\n" +
                    "---------------------------------------------------------------");
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length >= 6) {                     String name = userDetails[0];
                    String email = userDetails[1];
                    String password = userDetails[2];
                    String address = userDetails[3];
                    String id = userDetails[4];
                    String phoneNum = userDetails[5];                    
                    logger.log(Level.INFO, String.format("| %-8s | %-20s | %-8s | %-24s | %-4s | %-9s |",
                            name, email, password, address, id, phoneNum));
                } else {
                    logger.log(Level.WARNING, "Line in file does not have the expected number of fields: " + line);
                }
            }
            logger.log(Level.INFO, "---------------------------------------------------------------\n" + "\u001B[0m");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while reading the user accounts file.", e);
        }
    }

    public void updateUserAccount(String email, String newPassword, String newAddress, String newId, String newPhoneNum) {
        File file = new File(BENEFICIARY_FILE_PATH);
        File tempFile = new File("src/main/resources/myData/BeneficiaryData_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean isUpdated = false;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length >= 6 && userDetails[1].equals(email)) {
                    userDetails[2] = newPassword; 
                    userDetails[3] = newAddress; 
                    userDetails[4] = newId; 
                    userDetails[5] = newPhoneNum; 
                    isUpdated = true;
                }

                writer.write(String.join(",", userDetails));
                writer.newLine();
            }

            if (isUpdated) {
                logger.log(Level.INFO, "User account updated successfully.");
            } else {
                logger.log(Level.WARNING, "User with email " + email + " not found.");
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while updating the user account.", e);
        }

        // Replace the original file with the updated file
        if (file.delete()) {
            if (!tempFile.renameTo(file)) {
                logger.log(Level.SEVERE, "Failed to rename temporary file.");
            }
        } else {
            logger.log(Level.SEVERE, "Failed to delete the original file.");
        }
    }

    public void updateUserDetails() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder fileContent = new StringBuilder();
        boolean userFound = false;

        // Step 1: Read existing data
        try (BufferedReader br = new BufferedReader(new FileReader(BENEFICIARY_FILE_PATH))) {
            
            // Get user details to be updated
            logger.log(Level.INFO, "Enter the name of the user to update: ");
            String nameToUpdate = scanner.nextLine();
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length >= 6 && userDetails[0].equalsIgnoreCase(nameToUpdate)) {
                    userFound = true;

                    // Ask user which details to update
                    String newEmail = userDetails[1];
                    String newPassword = userDetails[2];
                    String newAddress = userDetails[3];
                    String newId = userDetails[4];
                    String newPhoneNum = userDetails[5];

                    logger.log(Level.INFO, "Select the details you want to update: ");
                    logger.log(Level.INFO, "1. Email");
                    logger.log(Level.INFO, "2. Password");
                    logger.log(Level.INFO, "3. Address");
                    logger.log(Level.INFO, "4. ID");
                    logger.log(Level.INFO, "5. Phone Number");
                    logger.log(Level.INFO, "6. Cancel");
                    int updateChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over

                    switch (updateChoice) {
                        case 1:
                            logger.log(Level.INFO, "Enter new email: ");
                            newEmail = scanner.nextLine();
                            break;
                        case 2:
                            logger.log(Level.INFO, "Enter new password: ");
                            newPassword = scanner.nextLine();
                            break;
                        case 3:
                            logger.log(Level.INFO, "Enter new address: ");
                            newAddress = scanner.nextLine();
                            break;
                        case 4:
                            logger.log(Level.INFO, "Enter new ID: ");
                            newId = scanner.nextLine();
                            break;
                        case 5:
                            logger.log(Level.INFO, "Enter new phone number: ");
                            newPhoneNum = scanner.nextLine();
                            break;
                        case 6:
                            logger.log(Level.INFO, "Update canceled.");
                            return; // Exit the method if canceled
                        default:
                            logger.log(Level.WARNING, "Invalid choice! No changes made.");
                            break;
                    }
                    fileContent.append(String.format("%s,%s,%s,%s,%s,%s%n",
                            nameToUpdate, newEmail, newPassword, newAddress, newId, newPhoneNum));
                    
                } else {
                    fileContent.append(line).append(System.lineSeparator());
                }
            }
            
            if (!userFound) {
                logger.log(Level.WARNING, "User with name " + nameToUpdate + " not found.");
            }
            
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while reading the file.", e);
        }

        if (userFound) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(BENEFICIARY_FILE_PATH))) {
                bw.write(fileContent.toString());
            } catch (IOException e) {
                logger.log(Level.SEVERE, "An error occurred while writing to the file.", e);
            }
        }
    }
 
    public void deleteUserAccount() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder fileContent = new StringBuilder();
        boolean userFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader(BENEFICIARY_FILE_PATH))) {
            logger.log(Level.INFO, "Enter the name of the user to delete: ");
            String nameToDelete = scanner.nextLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length >= 6 && userDetails[0].equalsIgnoreCase(nameToDelete)) {
                    userFound = true;
                    logger.log(Level.INFO, "User " + nameToDelete + " has been deleted.");
                } else {
                    fileContent.append(line).append(System.lineSeparator());
                }
            }

            if (!userFound) {
                logger.log(Level.WARNING, "User with name " + nameToDelete + " not found.");
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while reading the file.", e);
        }
        if (userFound) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(BENEFICIARY_FILE_PATH))) {
                bw.write(fileContent.toString());
            } catch (IOException e) {
                logger.log(Level.SEVERE, "An error occurred while writing to the file.", e);
            }
        }
    }

    
    

    public void managePosts() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, "\n\u001B[34m" + "----- Manage Posts -----" + "\n" +
                "|     1. Add Post           |\n" +
                "|     2. View Posts         |\n" +
                "|     3. Edit Post          |\n" +
                "|     4. Delete Post        |\n" +
                "|     5. Back               |\n" +
                "-----------------------------\n");
        logger.log(Level.INFO, ENTER_YOUR_CHOICE + RESET_COLOR);
        choice = scanner.nextInt();

        switch (choice) {
            case 1 -> addPost();
            case 2 -> viewPosts();
            case 3 -> editPost();
            case 4 -> deletePost();
            case 5 -> Admin_menu(getAdminName());
            default -> {
                logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31mInvalid choice! Please enter a valid choice." + "\u001B[0m");
                managePosts();
            }
        }
    }

    public void addRecipe() {
        Scanner scanner = new Scanner(System.in);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECIPES_FILE_PATH, true))) {
            System.out.println("Enter recipe name: ");
            String name = scanner.nextLine().trim();
            System.out.println("Enter recipe ingredients: ");
            String ingredients = scanner.nextLine().trim();
            System.out.println("Enter recipe instructions: ");
            String instructions = scanner.nextLine().trim();
            System.out.println("Enter Weight of the piece ");
            String price = scanner.nextLine().trim();

            writer.write(name + "," + ingredients + "," + instructions + "," + price);
            writer.newLine();
            logger.log(Level.INFO, "Recipe added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        manageRecipes();
    }

    private void viewRecipes() {
        StringBuilder recipeDisplay = new StringBuilder("----- Recipe List -----\n");
        try (BufferedReader reader = new BufferedReader(new FileReader(RECIPES_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(",");
                if (words.length >= 4) {
                    recipeDisplay.append(" ").append(words[0].trim())
                            .append(" : ").append(words[1].trim())
                            .append("  ").append(words[2].trim())
                            .append("  ").append(words[3].trim())
                            .append("\n------------------------\n");
                } else {
                    recipeDisplay.append("Line format incorrect: ").append(line).append("\n");
                }
            }
        } catch (IOException e) {
            recipeDisplay.append("Error reading file: ").append(e.getMessage());
        }
        System.out.println(recipeDisplay.toString());
        manageRecipes();
    }
   
    public void whatAdminEnter(String AdminChoice){
        if (AdminChoice.equals("1")){
        	setUserAccountsFlag(true);
        } else if (AdminChoice.equals("2")) {
        	setfeedback(true);
        } else if (AdminChoice.equals("3")) {
        	setRecipes(true);
        }
        else if (AdminChoice.equals("4")) {
        	setPosts(true);
        }
        
        else {
        	setUserAccountsFlag(false);
        	setRecipes(false);
        	setfeedback(false);
        	setPosts(false);
        	
        }
    }
    public void editRecipe() {
        Scanner scanner = new Scanner(System.in);
        File inputFile = new File(RECIPES_FILE_PATH);
        File tempFile = new File("recipes_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            System.out.println("Enter recipe name to edit: ");
            String nameToEdit = scanner.nextLine().trim();
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split(",");
                if (words[0].trim().equalsIgnoreCase(nameToEdit)) {
                    System.out.println("Enter new name: ");
                    String newName = scanner.nextLine().trim();
                    System.out.println("Enter new ingredients: ");
                    String newIngredients = scanner.nextLine().trim();
                    System.out.println("Enter new instructions: ");
                    String newInstructions = scanner.nextLine().trim();
                    System.out.println("Enter new price: ");
                    String newPrice = scanner.nextLine().trim();

                    writer.write(newName + "," + newIngredients + "," + newInstructions + "," + newPrice);
                    writer.newLine();
                    found = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!found) {
                System.out.println("Recipe with the specified name not found.");
            } else {
                logger.log(Level.INFO, "Recipe edited successfully.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete()) {
            System.out.println("Could not delete the original file");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename the temporary file");
        }
        manageRecipes();
    }

    public void deleteRecipe() {
        Scanner scanner = new Scanner(System.in);
        File inputFile = new File(RECIPES_FILE_PATH);
        File tempFile = new File("recipes_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            System.out.println("Enter recipe name to delete: ");
            String nameToDelete = scanner.nextLine().trim();
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split(",");
                if (!words[0].trim().equalsIgnoreCase(nameToDelete)) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    found = true;
                }
            }

            if (found) {
                logger.log(Level.INFO, "Recipe deleted successfully.");
            } else {
                System.out.println("Recipe with the specified name not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete()) {
            System.out.println("Could not delete the original file");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename the temporary file");
        }
        manageRecipes();
    }
    private void addPost() {
        Scanner scanner = new Scanner(System.in);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(POSTS_FILE_PATH, true))) {
            System.out.println("Enter post title: ");
            String title = scanner.nextLine().trim();
            System.out.println("Enter post content: ");
            String content = scanner.nextLine().trim();
            String currentDate = java.time.LocalDate.now().toString(); 
            if (!title.isEmpty() && !content.isEmpty()) {
                writer.write(title + "\t" + content + "\t" + currentDate);
                writer.newLine();
                logger.log(Level.INFO, "Post added successfully.");
            } else {
                System.out.println("Title or content cannot be empty.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        managePosts();
    }


    private void viewPosts() {
        StringBuilder postDisplay = new StringBuilder("----- Post List -----\n");
        try (BufferedReader reader = new BufferedReader(new FileReader(POSTS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 3) {
                    String postId = parts[0].trim();
                    String postContent = parts[1].trim();
                    String postDate = parts[2].trim();
                    postDisplay.append("Post ID: ").append(postId)
                               .append(" | Content: ").append(postContent)
                               .append(" | Date: ").append(postDate)
                               .append("\n------------------------\n");
                } else {
                    postDisplay.append("Line format incorrect: ").append(line).append("\n");
                }
            }
        } catch (IOException e) {
            postDisplay.append("Error reading file: ").append(e.getMessage());
        }
        System.out.println(postDisplay.toString());
        managePosts();
    }
    private void editPost() {
        Scanner scanner = new Scanner(System.in);
        File inputFile = new File(POSTS_FILE_PATH);
        File tempFile = new File("posts_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            System.out.println("Enter post title to edit: ");
            String titleToEdit = scanner.nextLine().trim();
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts[0].trim().equalsIgnoreCase(titleToEdit)) {
                    // Prompt user for new data
                    System.out.println("Enter new title: ");
                    String newTitle = scanner.nextLine().trim();
                    System.out.println("Enter new content: ");
                    String newContent = scanner.nextLine().trim();

                    writer.write(newTitle + "\t" + newContent);
                    writer.newLine();
                    found = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!found) {
                System.out.println("Post with the specified title not found.");
            } else {
                logger.log(Level.INFO, "Post edited successfully.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete()) {
            System.out.println("Could not delete the original file");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename the temporary file");
        }
        managePosts();
    }

    private void deletePost() {
        Scanner scanner = new Scanner(System.in);
        File inputFile = new File(POSTS_FILE_PATH);
        File tempFile = new File("posts_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            System.out.println("Enter post title to delete: ");
            String titleToDelete = scanner.nextLine().trim();
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (!parts[0].trim().equalsIgnoreCase(titleToDelete)) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    found = true;
                }
            }

            if (found) {
                logger.log(Level.INFO, "Post deleted successfully.");
            } else {
                System.out.println("Post with the specified title not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete()) {
            System.out.println("Could not delete the original file");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename the temporary file");
        }
        managePosts();
    }
    public void generateFinancialReports() {
        logger.log(Level.INFO, "Generating financial reports...");

        double totalRevenue = 0.0;
        double totalExpenses = 0.0;
        int totalItems = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(CONTENT_FILE_PATH))) {
            String line;
            boolean isHeader = true; // Skip header line

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; // Skip header line
                }

                String[] parts = line.split(",");
                if (parts.length == 7) { // Ensure there are 7 columns
                    try {
                        // Read quantity and price
                        double quantity = Double.parseDouble(parts[6].trim());
                        double price = Double.parseDouble(parts[3].trim());

                        // Calculate revenue
                        totalRevenue += quantity * price;
                        totalItems += (int) quantity;
                    } catch (NumberFormatException e) {
                        logger.log(Level.WARNING, "Error parsing line: " + line, e);
                    }
                } else {
                    logger.log(Level.WARNING, "Skipping invalid product line: " + line);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading content file", e);
        }

        // Calculate expenses based on total items
        totalExpenses = calculateExpenses(totalItems); 
        double profit = totalRevenue - totalExpenses;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE_PATH))) {
            writer.write("<!DOCTYPE html>");
            writer.write("<html>");
            writer.write("<head>");
            writer.write("<title>Financial Report</title>");
            writer.write("<style>");
            writer.write("body { font-family: Arial, sans-serif; margin: 20px; }");
            writer.write("h1 { color: #2C3E50; }");
            writer.write("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            writer.write("th, td { border: 1px solid #BDC3C7; padding: 10px; text-align: left; }");
            writer.write("th { background-color: #3498DB; color: white; }");
            writer.write("tr:nth-child(even) { background-color: #ECF0F1; }");
            writer.write("tr:hover { background-color: #BDC3C7; }");
            writer.write("</style>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<h1>Financial Report</h1>");
            writer.write("<p>Date: " + java.time.LocalDate.now() + "</p>");
            writer.write("<table>");
            writer.write("<tr><th>Total Items</th><td>" + totalItems + "</td></tr>");
            writer.write("<tr><th>Total Revenue</th><td>$" + formatDouble(totalRevenue) + "</td></tr>");
            writer.write("<tr><th>Total Expenses</th><td>$" + formatDouble(totalExpenses) + "</td></tr>");
            writer.write("<tr><th>Profit</th><td>$" + formatDouble(profit) + "</td></tr>");
            writer.write("</table>");
            writer.write("</body>");
            writer.write("</html>");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing financial report", e);
        }
        
      
    }

    private double calculateExpenses(int totalItems) {
        return totalItems * 1.00; // Example expense rate per item
    }

    private String formatDouble(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }

public void manageFeedback() {
    Scanner scanner = new Scanner(System.in);
    String feedbackFilePath = "src/main/resources/myData/Feedback.txt";
    
    logger.log(Level.INFO, "\n\u001B[33m" + "----- Manage Feedback -----" + "\n" +
               "| 1. View feedback                      |\n" +
               "| 2. Edit feedback                      |\n" +
               "| 3. Delete feedback                    |\n" +
               "| 4. Return to admin menu               |\n" +
               "-----------------------------------------\n" + RESET_COLOR);
    logger.log(Level.INFO, ENTER_YOUR_CHOICE + RESET_COLOR);
    int choice = scanner.nextInt();
    scanner.nextLine();  // Consume newline left-over

    switch (choice) {
        case 1:
            viewFeedback(feedbackFilePath);
            break;
        case 2:
            editFeedback(feedbackFilePath, scanner);
            break;
        case 3:
            deleteFeedback(feedbackFilePath, scanner);
            break;
        case 4:
        	Admin_menu(getAdminName());
        default:
            logger.log(Level.WARNING, BOLD + "\u001B[31mInvalid choice! Please enter a valid choice." + RESET_COLOR);
           
            
    }
}

private void viewFeedback(String feedbackFilePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(feedbackFilePath))) {
        String line;
        logger.log(Level.INFO, "\u001B[32m" + "----- Feedback -----" + "\u001B[0m");
        while ((line = reader.readLine()) != null) {
            logger.log(Level.INFO, line);
        }
    } catch (IOException e) {
        logger.log(Level.SEVERE, BOLD + "\u001B[31mError reading feedback: " + e.getMessage() + RESET_COLOR);
    }
    manageFeedback() ;
    
}

private void editFeedback(String feedbackFilePath, Scanner scanner) {
    List<String> feedbackList = readFeedbackFromFile(feedbackFilePath);
    if (feedbackList == null) {
        return;     }

    logger.log(Level.INFO, "\u001B[32m" + "----- Select Feedback to Edit -----" + "\u001B[0m");
    for (int i = 0; i < feedbackList.size(); i++) {
        logger.log(Level.INFO, (i + 1) + ". " + feedbackList.get(i));
    }
    logger.log(Level.INFO, "Enter the number of the feedback to edit: " + "\u001B[0m");
    int feedbackNumber = scanner.nextInt();
    scanner.nextLine();  
    if (feedbackNumber < 1 || feedbackNumber > feedbackList.size()) {
        logger.log(Level.WARNING, BOLD + "\u001B[31mInvalid number! Please enter a valid number." + RESET_COLOR);
        return;
    }

    logger.log(Level.INFO, "Enter the new feedback: " + RESET_COLOR);
    String newFeedback = scanner.nextLine();
    feedbackList.set(feedbackNumber - 1, newFeedback);

    writeFeedbackToFile(feedbackFilePath, feedbackList);
    logger.log(Level.INFO, "\u001B[32mFeedback updated successfully!\u001B[0m");
   
    manageFeedback() ;

}

private void deleteFeedback(String feedbackFilePath, Scanner scanner) {
    List<String> feedbackList = readFeedbackFromFile(feedbackFilePath);
    if (feedbackList == null) {
        return;     }

    logger.log(Level.INFO, "\u001B[32m" + "----- Select Feedback to Delete -----" + RESET_COLOR);
    for (int i = 0; i < feedbackList.size(); i++) {
        logger.log(Level.INFO, (i + 1) + ". " + feedbackList.get(i));
    }
    logger.log(Level.INFO, "Enter the number of the feedback to delete: " + RESET_COLOR);
    int feedbackNumber = scanner.nextInt();
    scanner.nextLine();  
    if (feedbackNumber < 1 || feedbackNumber > feedbackList.size()) {
        logger.log(Level.WARNING, "\u001B[1m" + "\u001B[31mInvalid number! Please enter a valid number." + "\u001B[0m");
        return;
    }

    feedbackList.remove(feedbackNumber - 1);

    writeFeedbackToFile(feedbackFilePath, feedbackList);
    logger.log(Level.INFO, "\u001B[32mFeedback deleted successfully!\u001B[0m");
    manageFeedback();

}

private List<String> readFeedbackFromFile(String feedbackFilePath) {
    List<String> feedbackList = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(feedbackFilePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            feedbackList.add(line);
        }
    } catch (IOException e) {
        logger.log(Level.SEVERE,  BOLD + "\u001B[31mError reading feedback: " + e.getMessage() + RESET_COLOR);
        return null;
    }
    return feedbackList;
}

private void writeFeedbackToFile(String feedbackFilePath, List<String> feedbackList) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(feedbackFilePath))) {
        for (String feedback : feedbackList) {
            writer.write(feedback);
            writer.newLine();
        }
    } catch (IOException e) {
        logger.log(Level.SEVERE,  BOLD+ "\u001B[31mError writing feedback: " + e.getMessage() + RESET_COLOR);
    }
}

public void gatherAndDisplayUserStatistics() {
    logger.log(Level.INFO, "Gathering and displaying user statistics by city...");

    Map<String, Integer> cityUserCount = new HashMap<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(BENEFICIARY_FILE_PATH))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 3) {
                String city = parts[3].trim(); 
                cityUserCount.put(city, cityUserCount.getOrDefault(city, 0) + 1);
            }
        }
    } catch (IOException e) {
        logger.log(Level.SEVERE, "Error reading beneficiary users file", e);
    }

    System.out.println("User Statistics by City:");
    System.out.println("========================");
    for (Map.Entry<String, Integer> entry : cityUserCount.entrySet()) {
        System.out.printf("City: %s, Number of Users: %d%n", entry.getKey(), entry.getValue());
    }
    Admin_menu(getAdminName());
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
        } else {
            logger.log(Level.INFO, "No sales data available.");
        }
        Admin_menu(getAdminName());
    }

}
