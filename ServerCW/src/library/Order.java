package library;

import java.io.Serializable;

public class Order implements Serializable {
    String readerID;
    String booksID;

    public Order(String readerID, String booksID) {
        this.readerID = readerID;
        this.booksID = booksID;
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
}