
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {

    private static ArrayList<Borrowers> borrowers = new ArrayList<>();
    private static userPrompt userPrompt = new userPrompt();
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Material> library = new ArrayList<>();
    private static Map<Integer, BorrowersHistory> borrowersHistoryMap = new HashMap<>();
    private static Map<String, AssetHistory> assetHistoryMap = new HashMap<>();

    FileReader fr = null;
    FileWriter fw = null;

    public static void main(String[] args) {
        boolean continueRunning = true;

        while (continueRunning) {
            ClearScreen();
            System.out.println("\n********************************************************");
            System.out.println("\t\tCodeX Library Management Database");
            System.out.println("********************************************************");
            System.out.println("[1] Borrowers Management");
            System.out.println("[2] Assets Management");
            System.out.println("[3] Borrow");
            System.out.println("[4] Return");
            System.out.println("[5] Borrowers History");
            System.out.println("[6] Assets History");
            System.out.println("[7] Exit");

            int mainChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

            switch (mainChoice) {
                case 1:
                    ClearScreen();
                    System.out.println("********************************************************");
                    System.out.println("Borrowers' Information Management");
                    System.out.println("********************************************************");
                    System.out.println("[1] Add");
                    System.out.println("[2] Edit");
                    System.out.println("[3] Delete");
                    System.out.println("[4] View");
                    System.out.println("[5] Back to Main Menu");
                    int bInfoManChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

                    switch (bInfoManChoice) {
                        case 1:
                            ClearScreen();
                            AddBorrower();
                            break;
                        case 2:
                            ClearScreen();
                            EditBorrower();
                            break;
                        case 3:
                            ClearScreen();
                            DeleteBorrower();
                            break;
                        case 4:
                            ClearScreen();
                            ViewBorrower();
                            break;
                        case 5:
                            continue;
                    }

                    break;

                case 2:
                    ClearScreen();
                    System.out.println("********************************************************");
                    System.out.println("Assets' Information Management");
                    System.out.println("********************************************************");
                    System.out.println("[1] Add");
                    System.out.println("[2] Edit");
                    System.out.println("[3] Delete");
                    System.out.println("[4] View");
                    System.out.println("[5] Back to Main Menu");
                    int aInfoManChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

                    switch (aInfoManChoice) {
                        case 1:
                            ClearScreen();
                            AddMaterial();
                            break;
                        case 2:
                            ClearScreen();
                            EditMaterial();
                            break;
                        case 3:
                            ClearScreen();
                            DeleteMaterial();
                            break;
                        case 4:
                            ClearScreen();
                            ViewMaterials();
                            break;
                        case 5:
                            continue;
                    }

                    break;
                case 3:
                    ClearScreen();
                    System.out.println("********************************************************");
                    System.out.println("Borrow Material");
                    System.out.println("********************************************************");
                    borrowMaterial();
                    break;
                case 4:
                    ClearScreen();
                    System.out.println("********************************************************");
                    System.out.println("Borrow Material");
                    System.out.println("********************************************************");
                    returnMaterial();
                    break;

                case 5:
                    ClearScreen();
                    System.out.println("********************************************************");
                    System.out.println("Borrower's History");
                    System.out.println("********************************************************");
                    viewBorrowersHistory();
                    break;
                case 6:
                    ClearScreen();
                    System.out.println("Assets History");
                    viewAssetHistory();
                    break;

                case 7:
                    ClearScreen();
                    continueRunning = false;
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Invalid Choice!!! Please Try Again!!!");
            }
        }

        userPrompt.closeScanner();
    }

    private static void viewBorrowersHistory() {
        int borrowerId = userPrompt.getValidIntegerInput("Enter Borrower ID: ");
        BorrowersHistory history = borrowersHistoryMap.get(borrowerId);
        if (history != null) {
            history.displayHistory();
        } else {
            System.out.println("No history found for Borrower ID: " + borrowerId);
        }
        System.out.println("Press Enter to return to the main menu...");
        input.nextLine();
    }
    private static void viewAssetHistory() {
        String materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);
        AssetHistory history = assetHistoryMap.get(materialID);
        if (history != null) {
            history.displayHistory();
        } else {
            System.out.println("No history found for Material ID: " + materialID);
        }
        System.out.println("Press Enter to return to the main menu...");
        input.nextLine();
    }


    private static void AddBorrower() {
        loadBorrowersFromFile();

        boolean continueAdding = true;
        while (continueAdding) {

            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("           Borrowers' Information Management");
            System.out.println("********************************************************");

            Borrowers borrower = new Borrowers(0, "", "", "", "", "", "", "", "");

            while (true) {
                int borrowerId = userPrompt.promptForValidBorrowerId("Enter Borrower ID: ", borrowers);
                if (borrowerId > 0 && findBorrowersById(borrowerId) == null) {
                    borrower.setId(borrowerId);
                    break;
                } else {
                    System.out.println("Invalid ID!!! It must be a positive integer and unique.");
                }
            }

            String lastName = userPrompt.promptForValidString("Enter Last Name: ");
            borrower.setLastName(lastName);

            String firstName = userPrompt.promptForValidString("Enter First Name: ");
            borrower.setFirstName(firstName);

            String middleName = userPrompt.promptForValidMidName("Enter Middle Name (Press Space/Enter if None): ");
            borrower.setMiddleName(middleName);

            String gender = userPrompt.promptForValidGender("Enter Gender (Male/Female): ");
            borrower.setGender(gender);

            String birthday = userPrompt.promptForValidDate("Enter Birthday (YYYY-MM-DD): ");
            borrower.setBirthday(birthday);

            String contactNum = userPrompt.promptForValidContactNum("Enter Contact Number: ");
            borrower.setContactNumber(contactNum);

            String email = userPrompt.promptForValidEmail("Enter Email: ");
            borrower.setEmail(email);

            String address = userPrompt.promptForValidString("Enter Address: ");
            borrower.setAddress(address);

            borrowers.add(borrower);
            System.out.println("Borrower's Information Added Successfully!!!");

            saveBorrowersToFile();

            continueAdding = userPrompt.confirmContinue("Adding Another Borrower's Information?");
        }
    }

    private static void AddMaterial() {
        boolean continueAdding = true;
        while (continueAdding) {

            Material material = null;

            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("           Material Information Management");
            System.out.println("********************************************************");
            System.out.println("Choose Material Type:");
            System.out.println("[1] Book ");
            System.out.println("[2] Journal ");
            System.out.println("[3] Magazine ");
            System.out.println("[4] Thesis Book ");
            System.out.println("[5] Exit ");

            int choice = userPrompt.getValidIntegerInput("Enter Choice: ");

            if (choice == 5) {
                continueAdding = false;
                continue;
            }

            String materialID = null;

            while (true) {
                materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);
                if (findMaterialByID(materialID) == null) {
                    break;
                } else {
                    System.out.println("Invalid ID!!! It must be alphanumeric and unique.");
                }
            }

            int copies = userPrompt.promptForValidInteger("Enter # of Copies: ");
            String yearPublished = userPrompt.promptForValidYear("Enter Year (YYYY): ");
            String publisher = userPrompt.promptForValidString("Enter Publisher: ");

            switch (choice) {
                case 1: // Book
                    String bookTitle = userPrompt.promptForValidString("Enter Title: ");
                    String author = userPrompt.promptForValidString("Enter Author: ");
                    material = new Book(materialID, bookTitle, author, yearPublished, publisher, copies);
                    break;

                case 2: // Journal
                    String journalName = userPrompt.promptForValidString("Enter Journal Name: ");
                    material = new Journal(materialID, journalName, yearPublished, publisher, copies);
                    break;

                case 3: // Magazine
                    String magazineName = userPrompt.promptForValidString("Enter Magazine Name: ");
                    material = new Magazine(materialID, magazineName, yearPublished, publisher, copies);
                    break;

                case 4: // Thesis Book
                    String title = userPrompt.promptForValidString("Enter Title: ");
                    String authorThesis = userPrompt.promptForValidString("Enter Author: ");
                    material = new ThesisBook(materialID, title, authorThesis, yearPublished, publisher, copies);
                    break;

                default:
                    System.out.println("Invalid choice! Please select a valid material type.");
                    continue;
            }

            if (material != null) {
                library.add(material);
                System.out.println(material.getClass().getSimpleName() + " Successfully Added!");
                saveAssets();

                continueAdding = userPrompt.confirmContinue("Adding Another Material");
            }
        }
    }

    private static void EditBorrower() {
        boolean continueEditing = true;

        loadBorrowersFromFile();
        int borrowerId = userPrompt.getValidIntegerInput("\n\nEnter Borrower ID to edit: ");
        Borrowers borrower = findBorrowersById(borrowerId);

        if (borrower == null) {
            System.out.println("Borrower ID not found.");
            continueEditing = userPrompt.confirmContinue("Editing Borrower's Information");
            return;
        }

        while (continueEditing) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              Edit Borrower's Information");
            System.out.println("********************************************************");
            System.out.println("Choose What to Edit:");
            System.out.println("[1] Last Name");
            System.out.println("[2] First Name");
            System.out.println("[3] Middle Name");
            System.out.println("[4] Gender");
            System.out.println("[5] Birthday");
            System.out.println("[6] Contact Number");
            System.out.println("[7] Email");
            System.out.println("[8] Address");
            System.out.println("[9] Back to Previous Menu");

            int editChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

            switch (editChoice) {
                case 1:
                    String lastName = userPrompt.promptForValidString("Enter New Last Name: ");
                    borrower.setLastName(lastName);
                    break;
                case 2:
                    String firstName = userPrompt.promptForValidString("Enter New First Name: ");
                    borrower.setFirstName(firstName);
                    break;
                case 3:
                    String middleName = userPrompt.promptForValidMidName("Enter New Middle Name (Press Space/Enter if None): ");
                    borrower.setMiddleName(middleName);
                    break;
                case 4:
                    String gender = userPrompt.promptForValidGender("Enter New Gender (Male/Female): ");
                    borrower.setGender(gender);
                    break;
                case 5:
                    String birthday = userPrompt.promptForValidDate("Enter New Birthday (YYYY-MM-DD): ");
                    borrower.setBirthday(birthday);
                    break;
                case 6:
                    String contactNum = userPrompt.promptForValidContactNum("Enter New Contact Number: ");
                    borrower.setContactNumber(contactNum);
                    break;
                case 7:
                    String email = userPrompt.promptForValidEmail("Enter New Email: ");
                    borrower.setEmail(email);
                    break;
                case 8:
                    String address = userPrompt.promptForValidString("Enter New Address: ");
                    borrower.setAddress(address);
                    break;
                case 9:
                    continueEditing = false;
                    break;
                default:
                    System.out.println("Invalid Choice!!! Please Try Again!!!");
            }

            if (editChoice >= 1 && editChoice <= 8) {
                System.out.println("Borrower's Information Edited Successfully!!!");
                saveBorrowersToFile();
            }

            if (editChoice != 9) {
                continueEditing = userPrompt.confirmContinue("Editing Another Borrower's Information?");
            }
        }
    }

    private static void EditMaterial() {
        loadAssets();
        boolean continueEditing = true;

        while (continueEditing) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              Edit Material Information");
            System.out.println("********************************************************");
            String materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);

            // Find the material by ID
            Material material = findMaterialByID(materialID);
            while (material == null) {
                System.out.println("Material ID not found. Please enter a valid Material ID.");
                materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);
                material = findMaterialByID(materialID); 
            }

            
            System.out.println("Material Type: " + material.getClass().getSimpleName());
            System.out.println("\nChoose What to Edit:");

            
            if (material instanceof Book) {
                System.out.println("[0] Enter Another Material ID to Edit");
                System.out.println("[1] Title");
                System.out.println("[2] Author");
                System.out.println("[3] Year Published");
                System.out.println("[4] Publisher");
                System.out.println("[5] Copies");
            } else if (material instanceof Journal) {
                System.out.println("[0] Enter Another Material ID to Edit");
                System.out.println("[1] Journal Name");
                System.out.println("[2] Year Published");
                System.out.println("[3] Publisher");
                System.out.println("[4] Copies");
            } else if (material instanceof Magazine) {
                System.out.println("[0] Enter Another Material ID to Edit");
                System.out.println("[1] Magazine Name");
                System.out.println("[2] Year Published");
                System.out.println("[3] Publisher");
                System.out.println("[4] Copies");
            } else if (material instanceof ThesisBook) {
                System.out.println("[0] Enter Another Material ID to Edit");
                System.out.println("[1] Title");
                System.out.println("[2] Year Published");
                System.out.println("[3] Publisher");
                System.out.println("[4] Copies");
                System.out.println("[5] Author");
            } else {
                System.out.println("[0] Enter Another Material ID to Edit");
                continue;  
            }

            int editChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

            switch (editChoice) {
                case 0:
                    continue;

                case 1: // Title/Name Editing
                    if (material instanceof Book || material instanceof ThesisBook) {
                        String title = userPrompt.promptForValidString("Enter New Title: ");
                        if (material instanceof Book) {
                            ((Book) material).setTitle(title);
                        } else {
                            ((ThesisBook) material).setTitle(title);
                        }
                    } else if (material instanceof Journal) {
                        String journalName = userPrompt.promptForValidString("Enter New Journal Name: ");
                        ((Journal) material).setJournalName(journalName);
                    } else if (material instanceof Magazine) {
                        String magazineName = userPrompt.promptForValidString("Enter New Magazine Name: ");
                        ((Magazine) material).setMagazineName(magazineName);
                    }
                    break;

                case 2: // Year Published Editing
                    String yearPublished = userPrompt.promptForValidYear("Enter New Year Published (YYYY): ");
                    material.setYearPublished(yearPublished);
                    break;

                case 3: // Publisher Editing
                    String publisher = userPrompt.promptForValidString("Enter New Publisher: ");
                    material.setPublisher(publisher);
                    break;

                case 4: // Copies Editing
                    int copies = userPrompt.promptForValidInteger("Enter New # of Copies: ");
                    material.setCopies(copies);
                    break;

                case 5: //Author Editing
                    if (material instanceof Book || material instanceof ThesisBook) {
                        String author = userPrompt.promptForValidString("Enter New Author: ");
                        if (material instanceof Book) {
                            ((Book) material).setAuthor(author);
                        } else {
                            ((ThesisBook) material).setAuthor(author);
                        }
                    } else if (material instanceof Journal) {
                        String journalName = userPrompt.promptForValidString("Enter New Journal Name: ");
                        ((Journal) material).setJournalName(journalName);
                    }
                    break;

                default:
                    System.out.println("Invalid choice! Please select a valid option.");
                    continue;
            }

            // Save changes to the library
            saveAssets();
            System.out.println("Material Information Edited Successfully!");
            continueEditing = userPrompt.confirmContinue("Continue Editing Another Material?");
        }
    }

    private static void DeleteBorrower() {
        loadBorrowersFromFile();

        boolean continueDeleting = true;
        while (continueDeleting) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              Delete Borrower's Information");
            System.out.println("********************************************************");

            int borrowerId = userPrompt.getValidIntegerInput("Enter Borrower ID to delete: ");
            Borrowers borrower = findBorrowersById(borrowerId);

            if (borrower == null) {
                System.out.println("Borrower ID not found. Please enter a valid Borrower ID.");
            } else {
                System.out.println("Borrower Name: " + borrower.getFirstName() + " " + borrower.getLastName());
                System.out.println("Are you sure you want to delete this borrower?");
                System.out.println("[1] Yes");
                System.out.println("[2] No");

                int deleteChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

                switch (deleteChoice) {
                    case 1:
                        System.out.println("Attempting to delete borrower with ID: " + borrowerId);
                        boolean removed = borrowers.remove(borrower);
                        if (removed) {
                            System.out.println("Borrower's Information Deleted Successfully!");
                        } else {
                            System.out.println("Failed to delete borrower. Borrower may not exist in the list.");
                        }
                        saveBorrowersToFile();
                        break;

                    case 2:
                        System.out.println("Borrower's Information Deletion Cancelled.");
                        break;

                    default:
                        System.out.println("Invalid choice! Please select a valid option.");
                        continue;
                }
            }

            continueDeleting = userPrompt.confirmContinue("Continue Deleting Another Borrower?");
        }
    }

    private static void DeleteMaterial() {
        loadAssets(); // Load the current library assets
        boolean continueDeleting = true;
    
        while (continueDeleting) {
            ClearScreen();
            System.out.println("**************************************************");
            System.out.println("              Delete Material Information");
            System.out.println("**************************************************");
    
            // Prompt for a valid Material ID
            System.out.print("Enter Material ID to delete: ");
            String materialID = input.nextLine().trim();
    
            // Check if Material ID exists
            Material materialToDelete = findMaterialByID(materialID);
            if (materialToDelete == null) {
                System.out.println("Error: Material ID not found!");
                continue; // Skip to the next iteration
            }
    
            // Confirm deletion
            System.out.println("Material Found: " + materialToDelete);
            System.out.println("Are you sure you want to delete this material?");
            System.out.println("[1] Yes");
            System.out.println("[2] No");
    
            int choice = input.nextInt();
            input.nextLine(); // Consume newline
    
            switch (choice) {
                case 1:
                    // Remove the material
                    boolean removed = library.removeIf(mat -> mat.getMaterialID().equals(materialID));
                    if (removed) {
                        System.out.println("Material successfully deleted!");
                        saveAssets(); // Save changes to file
                    } else {
                        System.out.println("Error: Material could not be deleted!");
                    }
                    break;
    
                case 2:
                    System.out.println("Deletion cancelled.");
                    break;
    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
    
            // Ask user if they want to delete another material
            System.out.print("Do you want to delete another material? [yes/no]: ");
            String response = input.nextLine().trim().toLowerCase();
            continueDeleting = response.equals("yes");
        }
    }
    
    
    

    private static void ViewBorrower() {
        loadBorrowersFromFile();

        boolean continueViewing = true;
        while (continueViewing) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              View Borrower's Information");
            System.out.println("********************************************************");

            int borrowerId = userPrompt.getValidIntegerInput("ENTER BORROWER ID: ");
            Borrowers borrower = findBorrowersById(borrowerId);

            if (borrower == null) {
                System.out.println("Borrower ID not found.");
            } else {
                System.out.println("   BORROWER:\t" + borrower.getId());
                System.out.println("  LAST NAME:\t" + borrower.getLastName().toUpperCase());
                System.out.println(" FIRST NAME:\t" + borrower.getFirstName().toUpperCase());
                System.out.println("MIDDLE NAME:\t" + borrower.getMiddleName().toUpperCase());
                System.out.println("     GENDER:\t" + borrower.getGender().toUpperCase());
                System.out.println("   BIRTHDAY:\t" + borrower.getBirthday());
                System.out.println("  CONTACT #:\t" + borrower.getContactNumber());
                System.out.println("      EMAIL:\t" + borrower.getEmail());
                System.out.println("    ADDRESS:\t" + borrower.getAddress().toUpperCase());
                System.out.println(" VIOLATIONS:\t" + borrower.getViolationNum());
            }
            continueViewing = userPrompt.confirmContinue("Viewing Borrower's Information");
        }
    }

    private static void ViewMaterials() {
        loadAssets();
        boolean continueViewing = true;

        while (continueViewing) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              View Material Information");
            System.out.println("********************************************************");
            String materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);

            // Find the material by ID
            Material material = findMaterialByID(materialID);
            while (material == null) {
                System.out.println("Material ID not found. Please enter a valid Material ID.");
                materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);
                material = findMaterialByID(materialID); // Check again with the new input
            }

            System.out.println("MATERIAL TYPE: " + material.getClass().getSimpleName().toUpperCase());
            if (material instanceof Book) {
                System.out.println("TITLE:\n\t" + ((Book) material).getTitle().toUpperCase());
                System.out.println("AUTHOR:\n\t" + ((Book) material).getAuthor().toUpperCase());
            } else if (material instanceof Journal) {
                System.out.println("JOURNAL NAME:\n\t" + ((Journal) material).getJournalName().toUpperCase());
            } else if (material instanceof Magazine) {
                System.out.println("MAGAZINE NAME:\n\t" + ((Magazine) material).getMagazineName().toUpperCase());
            } else if (material instanceof ThesisBook) {
                System.out.println("TITLE:\n\t" + ((ThesisBook) material).getTitle().toUpperCase());
                System.out.println("AUTHOR:\n\t" + ((ThesisBook) material).getAuthor().toUpperCase());
            }
            System.out.println("YEAR PUBLISHED:\n\t" + material.getYearPublished());
            System.out.println("PUBLISHER:\n\t" + material.getPublisher());
            System.out.println("AVAILABLE COPIES:\n\t" + material.getCopies());

            continueViewing = userPrompt.confirmContinue("Viewing Another Material?");
        }
    }

   private static void borrowMaterial() {
        loadBorrowersFromFile();
        loadAssets();

        System.out.println("********************************************************");
        System.out.println("              Borrow Material");
        System.out.println("********************************************************");

        int borrowerId = userPrompt.getValidIntegerInput("Enter Borrower ID: ");
        Borrowers borrower = findBorrowersById(borrowerId);

        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }

        if (borrower.getViolationNum() >= 3) {
            System.out.println("Borrower has reached the maximum number of violations.");
            return;
        }

        if (borrower.getBorrowedMaterial() != null) {
            System.out.println("Borrower already has a borrowed material.");
            return;
        }

        while (true) {
            System.out.print("Enter Material ID: ");
            String materialID = input.nextLine();
            Material material = findMaterialByID(materialID);

            if (material == null) {
                System.out.println("Material not found. Please try again.");
                continue;
            }

            if (material.getCopies() <= 0) {
                System.out.println("No copies available for borrowing.");
                return;
            }

            material.borrow();
            borrower.setBorrowedMaterial(material);
            saveAssets();
            saveBorrowersToFile();

            // Add to history
            borrowersHistoryMap.computeIfAbsent(borrowerId, BorrowersHistory::new)
                    .addTransaction(materialID, LocalDate.now().toString(), "Not Returned Yet");
            assetHistoryMap.computeIfAbsent(materialID, id -> new AssetHistory(id))
                    .addTransaction(borrowerId, LocalDate.now().toString(), "Not Returned Yet");

            System.out.println("Material borrowed successfully!");
            break;
        }

        System.out.println("Press Enter to return to the main menu...");
        input.nextLine();
   }

    private static void returnMaterial() {
        System.out.println("********************************************************");
        System.out.println("              Return Material");
        System.out.println("********************************************************");

        int borrowerId = userPrompt.getValidIntegerInput("Enter Borrower ID: ");
        Borrowers borrower = findBorrowersById(borrowerId);

        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }

        Material borrowedMaterial = borrower.getBorrowedMaterial();

        if (borrowedMaterial == null) {
            System.out.println("No borrowed material found for this borrower.");
            return;
        }

        LocalDate borrowDate = borrowedMaterial.getBorrowDate();
        LocalDate returnDate = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(borrowDate, returnDate);

        boolean lateReturn = false;
        if (borrowedMaterial instanceof Book && daysBetween > 7) {
            lateReturn = true;
        } else if (borrowedMaterial instanceof Journal && daysBetween > 3) {
            lateReturn = true;
        } else if (borrowedMaterial instanceof Magazine && daysBetween > 1) {
            lateReturn = true;
        } else if (borrowedMaterial instanceof ThesisBook && daysBetween > 2) {
            lateReturn = true;
        }

        if (lateReturn) {
            borrower.incrementViolation();
            System.out.println("Material returned late. Borrower has been given a strike.");
        } else {
            System.out.println("Material returned on time.");
        }

        borrowedMaterial.returnMaterial();
        borrower.setBorrowedMaterial(null);
        saveAssets();
        saveBorrowersToFile();

        // Update history
        BorrowersHistory borrowerHistory = borrowersHistoryMap.get(borrowerId);
        if (borrowerHistory != null) {
            borrowerHistory.addTransaction(borrowedMaterial.getMaterialID(), borrowDate.toString(), returnDate.toString());
        }

        AssetHistory assetHistory = assetHistoryMap.get(borrowedMaterial.getMaterialID());
        if (assetHistory != null) {
            assetHistory.addTransaction(borrowerId, borrowDate.toString(), returnDate.toString());
        }

        System.out.println("Material returned successfully!");
        System.out.println("Press Enter to return to the main menu...");
        input.nextLine();
    }


    private static Borrowers findBorrowersById(int id) {
        for (Borrowers borrower : borrowers) {
            if (borrower.getId() == id) {
                return borrower;
            }
        }
        return null;
    }

    private static void ClearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error clearing the screen: " + ex.getMessage());
        }
    }

    private static void loadBorrowersFromFile() {
        borrowers.clear(); 
        FileReader fr = null;

        try {
            fr = new FileReader("borrowers.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 9) { 
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                // Create a Borrower
                Borrowers borrower = new Borrowers(
                        Integer.parseInt(data[0]), // ID
                        data[1], // Last Name
                        data[2], // First Name
                        data[3], // Middle Name
                        data[4], // Gender
                        data[5], // Birthday
                        data[6], // Contact Number
                        data[7], // Email
                        data[8] // Address
                );

                
                if (data.length > 9) {
                    borrower.setViolationNum(Integer.parseInt(data[9]));
                } else {
                    borrower.setViolationNum(0); 
                }

                // Add to the borrowers list
                borrowers.add(borrower);
                System.out.println("Loaded borrower: " + borrower);
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error loading borrowers from file: " + e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing FileReader: " + e.getMessage());
            }
        }
    }

    private static void saveBorrowersToFile() {
        Map<Integer, Borrowers> borrowerMap = new HashMap<>();

        
        try (BufferedReader br = new BufferedReader(new FileReader("borrowers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int borrowerID = Integer.parseInt(parts[0]);
                Borrowers borrower = new Borrowers(
                        borrowerID,
                        parts[1], // Last Name
                        parts[2], // First Name
                        parts[3], // Middle Name
                        parts[4], // Gender
                        parts[5], // Birthday
                        parts[6], // Contact Number
                        parts[7], // Email
                        parts[8] // Address
                );
                borrowerMap.put(borrowerID, borrower);
            }
        } catch (IOException e) {
            System.out.println("Error loading existing borrowers: " + e.getMessage());
        }

        
        Iterator<Map.Entry<Integer, Borrowers>> iterator = borrowerMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Borrowers> entry = iterator.next();
            if (!borrowers.contains(entry.getValue())) {
                iterator.remove();
            }
        }

        
        for (Borrowers borrower : borrowers) {
            borrowerMap.put(borrower.getId(), borrower);
        }

        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("borrowers.txt"))) {
            for (Borrowers borrower : borrowerMap.values()) {
                bw.write(borrower.getId() + "," + borrower.getLastName() + "," + borrower.getFirstName() + ","
                        + borrower.getMiddleName() + "," + borrower.getGender() + "," + borrower.getBirthday() + ","
                        + borrower.getContactNumber() + "," + borrower.getEmail() + "," + borrower.getAddress());
                bw.newLine();
            }
            System.out.println("Borrowers saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving borrowers to file: " + e.getMessage());
        }
    }

    private static Material findMaterialByID(String materialID) {
        for (Material material : library) {
            if (material.getMaterialID().equalsIgnoreCase(materialID)) {
                return material;
            }
        }
        return null;
    }

    private static void loadAssets() {
        library.clear();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("assets.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }
                String type = parts[0];
                String materialID = parts[1];
                String yearPublished = parts[2];
                String publisher = parts[3];
                int copies = Integer.parseInt(parts[4]);

                Material material;
                switch (type.toLowerCase()) {
                    case "book":
                        if (parts.length < 7) {
                            System.out.println("Skipping invalid book line: " + line);
                            continue;
                        }
                        String bookTitle = parts[5];
                        String author = parts[6];
                        material = new Book(materialID, bookTitle, author, yearPublished, publisher, copies);
                        break;
                    case "journal":
                        if (parts.length < 6) {
                            System.out.println("Skipping invalid journal line: " + line);
                            continue;
                        }
                        String journalName = parts[5];
                        material = new Journal(materialID, journalName, yearPublished, publisher, copies);
                        break;
                    case "magazine":
                        if (parts.length < 6) {
                            System.out.println("Skipping invalid magazine line: " + line);
                            continue;
                        }
                        String magazineName = parts[5];
                        material = new Magazine(materialID, magazineName, yearPublished, publisher, copies);
                        break;
                    case "thesis book":
                        if (parts.length < 7) {
                            System.out.println("Skipping invalid thesis book line: " + line);
                            continue;
                        }
                        String thesisTitle = parts[5];
                        String thesisAuthor = parts[6];
                        material = new ThesisBook(materialID, thesisTitle, thesisAuthor, yearPublished, publisher, copies);
                        break;
                    default:
                        System.out.println("Unknown material type: " + type);
                        continue;
                }
                library.add(material);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void saveAssets() {
        Map<String, Material> materialMap = new HashMap<>();

        
        try (BufferedReader br = new BufferedReader(new FileReader("assets.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String materialID = parts[1];
                Material material = null;

                switch (parts[0].toLowerCase()) {
                    case "book":
                        material = new Book(materialID, parts[5], parts[6], parts[2], parts[3], Integer.parseInt(parts[4]));
                        break;
                    case "magazine":
                        material = new Magazine(materialID, parts[5], parts[2], parts[3], Integer.parseInt(parts[4]));
                        break;
                    case "journal":
                        material = new Journal(materialID, parts[5], parts[2], parts[3], Integer.parseInt(parts[4]));
                        break;
                    case "thesis book":
                        material = new ThesisBook(materialID, parts[5], parts[6], parts[2], parts[3], Integer.parseInt(parts[4]));
                        break;
                    default:
                        System.out.println("Unknown material type: " + parts[0]);
                        continue;
                }
                materialMap.put(materialID, material);
            }
        } catch (IOException e) {
            System.out.println("Error loading existing assets: " + e.getMessage());
        }

        
        for (Material material : library) {
            materialMap.put(material.getMaterialID(), material);
        }

        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("assets.txt"))) {
            for (Material material : materialMap.values()) {
                StringBuilder line = new StringBuilder();

                if (material instanceof Book) {
                    Book book = (Book) material;
                    line.append("book,")
                            .append(book.getMaterialID()).append(",")
                            .append(book.getYearPublished()).append(",")
                            .append(book.getPublisher()).append(",")
                            .append(book.getCopies()).append(",")
                            .append(book.getTitle()).append(",")
                            .append(book.getAuthor());
                } else if (material instanceof Magazine) {
                    Magazine magazine = (Magazine) material;
                    line.append("magazine,")
                            .append(magazine.getMaterialID()).append(",")
                            .append(magazine.getYearPublished()).append(",")
                            .append(magazine.getPublisher()).append(",")
                            .append(magazine.getCopies()).append(",")
                            .append(magazine.getMagazineName());
                } else if (material instanceof Journal) {
                    Journal journal = (Journal) material;
                    line.append("journal,")
                            .append(journal.getMaterialID()).append(",")
                            .append(journal.getYearPublished()).append(",")
                            .append(journal.getPublisher()).append(",")
                            .append(journal.getCopies()).append(",")
                            .append(journal.getJournalName());
                } else if (material instanceof ThesisBook) {
                    ThesisBook thesisBook = (ThesisBook) material;
                    line.append("thesis book,")
                            .append(thesisBook.getMaterialID()).append(",")
                            .append(thesisBook.getYearPublished()).append(",")
                            .append(thesisBook.getPublisher()).append(",")
                            .append(thesisBook.getCopies()).append(",")
                            .append(thesisBook.getTitle()).append(",")
                            .append(thesisBook.getAuthor());
                } else {
                    System.out.println("Unknown material type: " + material.getClass().getSimpleName());
                    continue; // Skip unknown material types
                }

                bw.write(line.toString());
                bw.newLine(); 
            }
        } catch (IOException e) {
            System.out.println("Error saving assets to file: " + e.getMessage());
        }
    }

}