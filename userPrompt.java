import java.util.*;

class userPrompt {
    private Scanner scanner;

    public userPrompt() {
        this.scanner = new Scanner(System.in);
    }

    public int getValidIntegerInput(String prompt) {
        int input = 0;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine(); 
                return input;
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next(); 
            }
        }
    }

    public int promptForValidBorrowerId(String prompt, List<Borrowers> borrowers) {
        int input = 0;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (String.valueOf(input).length() <= 9 && input > 0) {
                    if (findBorrowersById(input, borrowers) == null) {
                        scanner.nextLine(); 
                        return input; 
                    } else {
                        System.out.println("This Borrower ID already exists. Please enter a unique ID.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid 1-9 digit integer.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid 1-9 digit integer.");
                scanner.next(); 
            }
        }
    }

    public int promptForValidInteger(String prompt) {
        int input = 0;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine(); 
                return input;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }
    }

    private static Borrowers findBorrowersById(int id, List<Borrowers> borrowers) {
        for (Borrowers borrower : borrowers) {
            if (borrower.getId() == id) {
                return borrower;
            }
        }
        return null;
    }

    public String promptForValidString(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            boolean hasDigit = false;
            for (char c : input.toCharArray()) {
                if (Character.isDigit(c)) {
                    hasDigit = true;
                    break;
                }
            }
            if (!input.isEmpty() && !hasDigit) {
                return input;
            } else if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            } else {
                System.out.println("Input cannot contain numbers. Please try again.");
            }
        }
    }

    public String promptForValidMidName(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();

            return input;
        }
    }

    public String promptForValidGender(String prompt) {
        String gender;
        while (true) {
            System.out.print(prompt);
            gender = scanner.nextLine().trim().toLowerCase();
            if (gender.equals("male") || gender.equals("female")) {
                return gender.substring(0, 1).toUpperCase() + gender.substring(1); // Capitalize
            } else {
                System.out.println("Invalid gender. Please enter 'Male' or 'Female'.");
            }
        }
    }

    public String promptForValidDate(String prompt) {
        String date;
        while (true) {
            System.out.print(prompt);
            date = scanner.nextLine().trim();

            // Basic YYYY-MM-DD format check
            if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                String[] parts = date.split("-");
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);

                // Check month range
                if (month < 1 || month > 12) {
                    System.out.println("Invalid month. Please enter a month between 1 and 12.");
                    continue;
                }

                // Check day range based on month
                switch (month) {
                    case 1: // January
                    case 3: // March
                    case 5: // May
                    case 7: // July
                    case 8: // August
                    case 10: // October
                    case 12: // December
                        if (day < 1 || day > 31) {
                            System.out.println("Invalid day for the month. Please enter a day between 1 and 31.");
                            continue;
                        }
                        break;
                    case 4: // April
                    case 6: // June
                    case 9: // September
                    case 11: // November
                        if (day < 1 || day > 30) {
                            System.out.println("Invalid day for the month. Please enter a day between 1 and 30.");
                            continue;
                        }
                        break;
                    case 2: // February
                        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                        if (isLeapYear) {
                            if (day < 1 || day > 29) {
                                System.out.println(
                                        "Invalid day for February in a leap year. Please enter a day between 1 and 29.");
                                continue;
                            }
                        } else {
                            if (day < 1 || day > 28) {
                                System.out.println("Invalid day for February. Please enter a day between 1 and 28.");
                                continue;
                            }
                        }
                        break;
                    default:
                        System.out.println("Invalid month. Please enter a month between 1 and 12.");
                        continue;
                }

                
                return date;
            } else {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }

    public String promptForValidYear(String prompt) {
        String year;
        while (true) {
            System.out.print(prompt);
            year = scanner.nextLine().trim();

            // Basic YYYY format check
            if (year.matches("\\d{4}")) {
                return year;
            } else {
                System.out.println("Invalid year format. Please use YYYY.");
            }
        }
    }

    public String promptForValidContactNum(String prompt) {
        String contactNum;

        while (true) {
            System.out.print(prompt);
            contactNum = scanner.nextLine().trim();
            if (contactNum.length() == 11 && contactNum.charAt(0) == '0' && contactNum.charAt(1) == '9') {
                boolean isValid = true;
                for (char digit : contactNum.toCharArray()) {
                    if (digit < '0' || digit > '9') {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    return contactNum;
                }
            }
            System.out.println("Invalid contact number. Please enter an 11-digit number starting with 09.");
        }
    }

    public String promptForValidEmail(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                int atCount = 0;
                boolean hasDotAfterAt = false;
                boolean isValid = true;

                for (int i = 0; i < input.length(); i++) {
                    char c = input.charAt(i);
                    if (c == '@') {
                        atCount++;
                        if (atCount > 1) {
                            isValid = false; // More than one '@' character
                            break;
                        }
                    } else if (c == '.') {
                        if (atCount == 1) {
                            hasDotAfterAt = true;
                        }
                    } else if (!Character.isLetterOrDigit(c) && c != '_' && c != '-' && c != '+') {
                        isValid = false; 
                        break;
                    }
                }

                if (atCount == 1 && hasDotAfterAt && isValid) {
                    return input;
                }
            }
            System.out.println("Invalid input. Please enter a valid email address.");
        }
    }

    public String promptForValidAddress(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                boolean isValid = true;

                for (char c : input.toCharArray()) {
                    if (!Character.isLetterOrDigit(c) && c != ' ' && c != ',' && c != '.' && c != '-' && c != '/') {
                        isValid = false; 
                        break;
                    }
                }
                if (isValid) {
                    return input;
                }
            }
            System.out.println("Invalid input. Please enter a valid address.");
        }
    }

    public String promptForValidMaterialID(String prompt, ArrayList<Material> library) {

        Scanner scanner = new Scanner(System.in);
        String materialID;

        boolean isValid;

        do {
            System.out.print(prompt);
            materialID = scanner.nextLine().trim();
            isValid = true;

            if (!materialID.matches("[a-zA-Z0-9]+")) {
            System.out.println("Invalid input. Please enter an alphanumeric ID.");
            isValid = false;
            continue;
            }

            for (Material material : library) {
            if (material.getMaterialID().equals(materialID)) {
                System.out.println("Material ID already exists. Please enter a unique ID.");
                isValid = false;
                break;
            }
            }
        } while (!isValid);

        return materialID;

    }

    public boolean confirmContinue(String action) {
        while (true) {
            System.out.print("Do You Want to " + action + " Again? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("y")) {
                return true;
            } else if (response.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid Input!!! Please Enter 'y' or 'n'.");
            }
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}
