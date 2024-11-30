public class Book extends Material {
    private String title, author;

    public Book(String materialID, String title, String author, String yearPublished, String publisher, int copies) {
        super(materialID, yearPublished, publisher, copies);

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank.");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be null or blank.");
        }

        this.title = title;
        this.author = author;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank.");
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be null or blank.");
        }
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Material ID: " + getMaterialID() +
               "\nType: Book" +
               "\nTitle: " + title +
               "\nAuthor: " + author +
               "\nYear Published: " + getYearPublished() +
               "\nPublisher: " + getPublisher() +
               "\nCopies: " + getCopies() +
               (getBorrowDate() != null ? "\nBorrow Date: " + getBorrowDate() : "") +
               (getReturnDate() != null ? "\nReturn Date: " + getReturnDate() : "");
    }

    @Override
    public int getReturnDateDays() {
        return 7; // 7 days for books
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return getMaterialID().equals(book.getMaterialID());
    }

    @Override
    public int hashCode() {
        return getMaterialID().hashCode();
    }
}
