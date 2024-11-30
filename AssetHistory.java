import java.util.ArrayList;
import java.util.List;

public class AssetHistory {
    private String materialID;
    private List<Transaction> transactions;

    public AssetHistory(String materialID) {
        this.materialID = materialID;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(int borrowerId, String borrowDate, String returnDate) {
        transactions.add(new Transaction(borrowerId, borrowDate, returnDate));
    }

    public void displayHistory() {
        System.out.println("********************************************************");
        System.out.println("              Material Borrowing History");
        System.out.println("********************************************************");
        System.out.println("Material ID: " + materialID);
        for (Transaction transaction : transactions) {
            System.out.println("Borrower ID: " + transaction.getBorrowerId());
            System.out.println("Borrow Date: " + transaction.getBorrowDate());
            System.out.println("Return Date: " + transaction.getReturnDate());
            System.out.println("--------------------------------------------------------");
        }
    }

    private class Transaction {
        private int borrowerId;
        private String borrowDate;
        private String returnDate;

        public Transaction(int borrowerId, String borrowDate, String returnDate) {
            this.borrowerId = borrowerId;
            this.borrowDate = borrowDate;
            this.returnDate = returnDate;
        }

        public int getBorrowerId() {
            return borrowerId;
        }

        public String getBorrowDate() {
            return borrowDate;
        }

        public String getReturnDate() {
            return returnDate;
        }
    }
}