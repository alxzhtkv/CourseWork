package library;

import java.io.Serializable;
import java.util.Random;

public class Order implements Serializable {
    String readerID;
    String booksID;

    String orderID;

    public Order(String readerID, String booksID) {
        this.readerID = readerID;
        this.booksID = booksID;
        Random random = new Random();
        String id =String.valueOf(random.nextInt(100000)+1);
        this.orderID=id;
    }

    public String getReaderID() {
        return readerID;
    }

    public void setReaderID(String readerID) {
        this.readerID = readerID;
    }

    public String getBooksID() {
        return booksID;
    }

    public void setBooksID(String booksID) {
        this.booksID = booksID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
