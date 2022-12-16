package library;

import java.io.Serializable;
import java.util.Random;

public class Order implements Serializable {
    String readerID;
    String booksID;

    String orderID;

    String status;

    String bookTitle;

    public Order() {

    }



    public Order(String readerID, String booksID, String orderID, String bookTitle) {
        this.readerID = readerID;
        this.booksID = booksID;
        this.orderID = orderID;
        this.bookTitle = bookTitle;
        this.status="обработан";
    }

    public Order(String readerID, String booksID, String orderID, String status, String bookTitle) {
        this.readerID = readerID;
        this.booksID = booksID;
        this.orderID = orderID;
        this.status = status;
        this.bookTitle = bookTitle;
    }

    public Order(String readerID, String booksID,String bookTitle) {
        this.readerID = readerID;
        this.booksID = booksID;
        Random random = new Random();
        String id =String.valueOf(random.nextInt(100000)+1);
        this.orderID=id;
        this.status="обработан";
        this.bookTitle=bookTitle;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}