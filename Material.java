import java.time.LocalDate;

public abstract class Material {
    private String materialID;
    private String yearPublished;
    private String publisher;
    private int copies;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    // Constructor
    public Material(String materialID, String yearPublished, String publisher, int copies) {
        this.materialID = materialID;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.copies = copies;
    }

    // Getters and Setters
    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    // Borrow Material
    public void borrow() {
        if (this.copies > 0) {
            this.borrowDate = LocalDate.now();
            this.returnDate = borrowDate.plusWeeks(2); // Example: 2 weeks borrowing period
            this.copies--;
        } else {
            throw new IllegalStateException("No copies available to borrow.");
        }
    }

    // Return Material
    public void returnMaterial() {
        if (this.borrowDate == null || this.returnDate == null) {
            throw new IllegalStateException("Material was not borrowed, so it cannot be returned.");
        }
        this.borrowDate = null;
        this.returnDate = null;
        this.copies++;
    }

    // Abstract Method for Subclasses
    public abstract int getReturnDateDays();

    @Override
    public String toString() {
        return "Material{" +
                "materialID='" + materialID + '\'' +
                ", yearPublished='" + yearPublished + '\'' +
                ", publisher='" + publisher + '\'' +
                ", copies=" + copies +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
