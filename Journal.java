public class Journal extends Material {
    private String journalName;

    public Journal(String materialID, String journalName, String yearPublished, String publisher, int copies) {
        super(materialID, yearPublished, publisher, copies);
        if (journalName == null || journalName.isBlank()) {
            throw new IllegalArgumentException("Journal name cannot be null or blank.");
        }
        this.journalName = journalName;
    }

    public void setJournalName(String journalName) {
        if (journalName == null || journalName.isBlank()) {
            throw new IllegalArgumentException("Journal name cannot be null or blank.");
        }
        this.journalName = journalName;
    }

    public String getJournalName() {
        return journalName;
    }

    @Override
    public String toString() {
        return "Material ID: " + getMaterialID() +
               "\nType: Journal" +
               "\nJournal Name: " + journalName +
               "\nYear Published: " + getYearPublished() +
               "\nPublisher: " + getPublisher() +
               "\nCopies: " + getCopies() +
               (getBorrowDate() != null ? "\nBorrow Date: " + getBorrowDate() : "") +
               (getReturnDate() != null ? "\nReturn Date: " + getReturnDate() : "");
    }

    @Override
    public int getReturnDateDays() {
        return 3; // 3 days for journals
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Journal journal = (Journal) obj;
        return getMaterialID().equals(journal.getMaterialID());
    }

    @Override
    public int hashCode() {
        return getMaterialID().hashCode();
    }
}
