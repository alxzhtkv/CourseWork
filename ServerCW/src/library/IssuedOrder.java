package library;

import java.io.Serializable;

public class IssuedOrder extends Order {
    String dateI;
    String dateB;

    public IssuedOrder(){}

    public IssuedOrder(String dateI, String dateB) {
        super();
        this.dateI = dateI;
        this.dateB = dateB;
    }

    public IssuedOrder(String readerID, String booksID, String orderID, String status, String bookTitle, String dateI, String dateB) {
        super(readerID, booksID, orderID, status, bookTitle);
        this.dateI = dateI;
        this.dateB = dateB;
    }

    public IssuedOrder(String orderID, String readerID, String booksID, String bookTitle, String dateI, String dateB) {
        super(readerID, booksID,orderID, bookTitle);
        this.dateI = dateI;
        this.dateB = dateB;
    }

    public String getDateI() {
        return dateI;
    }

    public void setDateI(String dateI) {
        this.dateI = dateI;
    }

    public String getDateB() {
        return dateB;
    }

    public void setDateB(String dateB) {
        this.dateB = dateB;
    }
}