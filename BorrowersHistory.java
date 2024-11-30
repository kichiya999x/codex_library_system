import java.util.ArrayList;
import java.util.List;

public class BorrowersHistory {
    private int borrowerId;
    private List<Transaction> transactions;

    public BorrowersHistory(int borrowerId) {
        this.borrowerId = borrowerId;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(String materialID, String borrowDate, String returnDate) {
        transactions.add(new Transaction(materialID, borrowDate, returnDate));
    }

    public void displayHistory() {
        System.out.println("********************************************************");
        System.out.println("              Borrower's Transaction History");
        System.out.println("********************************************************");
        System.out.println("Borrower ID: " + borrowerId);
        for (Transaction transaction : transactions) {
            System.out.println("Material ID: " + transaction.getMaterialID());
            System.out.println("Borrow Date: " + transaction.getBorrowDate());
            System.out.println("Return Date: " + transaction.getReturnDate());
            System.out.println("--------------------------------------------------------");
        }
    }

    private class Transaction {
        private String materialID;
        private String borrowDate;
        private String returnDate;

        public Transaction(String materialID, String borrowDate, String returnDate) {
            this.materialID = materialID;
            this.borrowDate = borrowDate;
            this.returnDate = returnDate;
        }

        public String getMaterialID() {
            return materialID;
        }

        public String getBorrowDate() {
            return borrowDate;
        }

        public String getReturnDate() {
            return returnDate;
        }
    }
}