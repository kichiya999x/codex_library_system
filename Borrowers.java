

public class Borrowers {

    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String gender;
    private String birthday;
    private String contactNumber;
    private String email;
    private String address;
    private int violationNum;
    private Material borrowedMaterial;

    // Constructor
    public Borrowers(int id, String lastName, String firstName, String middleName, String gender, String birthday, String contactNumber, String email, String address) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthday = birthday;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.violationNum = 0; 
        this.borrowedMaterial = null; 
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getViolationNum() {
        return violationNum;
    }

    public void setViolationNum(int violationNum) {
        if (violationNum < 0) {
            this.violationNum = 0; // Ensure it doesn’t go below 0
        } else if (violationNum > 3) {
            this.violationNum = 3; // Limit violations to 3 max
        } else {
            this.violationNum = violationNum;
        }
    }

    public void incrementViolationNum() {
        if (violationNum < 3) {
            violationNum++;
        } else {
            System.out.println("Borrower has reached the maximum number of violations.");
        }
    }

    
    @Override
    public String toString() {
        return "BORROWER ID: " + id
                + "\nNAME: " + firstName.toUpperCase() + " " + middleName.toUpperCase() + " " + lastName.toUpperCase();
    }
}
