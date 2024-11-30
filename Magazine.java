public class Magazine extends Material {
    private String magazineName;

    public Magazine(String materialID, String magazineName, String yearPublished, String publisher, int copies) {
        super(materialID, yearPublished, publisher, copies);
        if (magazineName == null || magazineName.isBlank()) {
            throw new IllegalArgumentException("Magazine name cannot be null or blank.");
        }
        this.magazineName = magazineName;
    }

    public void setMagazineName(String magazineName) {
        if (magazineName == null || magazineName.isBlank()) {
            throw new IllegalArgumentException("Magazine name cannot be null or blank.");
        }
        this.magazineName = magazineName;
    }

    public String getMagazineName() {
        return magazineName;
    }

    @Override
    public String toString() {
        return "Material ID: " + getMaterialID() +
               "\nType: Magazine" +
               "\nMagazine Name: " + magazineName +
               "\nYear Published: " + getYearPublished() +
               "\nPublisher: " + getPublisher() +
               "\nCopies: " + getCopies() +
               (getBorrowDate() != null ? "\nBorrow Date: " + getBorrowDate() : "") +
               (getReturnDate() != null ? "\nReturn Date: " + getReturnDate() : "");
    }

    @Override
    public int getReturnDateDays() {
        return 1; // 1 day for magazines
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Magazine magazine = (Magazine) obj;
        return getMaterialID().equals(magazine.getMaterialID());
    }

    @Override
    public int hashCode() {
        return getMaterialID().hashCode();
    }
}
