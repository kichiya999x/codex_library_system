public class ThesisBook extends Material {
    private String title, author;

    // Constructor
    public ThesisBook(String materialID, String title, String author, String yearPublished, String publisher, int copies) {
        super(materialID, yearPublished, publisher, copies);
        this.title = title;
        this.author = author;
    }

    // Getters and Setters for title and author
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    // Override toString to provide detailed info
    @Override
    public String toString() {
        return "Material ID: " + getMaterialID() +
                "\nType: Thesis Book" +
                "\nTitle: " + title +
                "\nAuthor: " + author +
                "\nYear Published: " + getYearPublished() +
                "\nPublisher: " + getPublisher() +
                "\nCopies: " + getCopies();
    }

    // Override to define specific return period (2 days for thesis books)
    @Override
    public int getReturnDateDays() {
        return 2; // 2 days for thesis books
    }
}
