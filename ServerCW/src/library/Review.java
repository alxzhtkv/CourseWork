package library;

import java.io.Serializable;

public class Review implements Serializable {
    String readerID;
    String bookID;
    String title;
    String text;

    public Review(String readerID, String bookID, String title, String text) {
        this.readerID = readerID;
        this.bookID = bookID;
        this.title = title;
        this.text = text;
    }

    public String getReaderID() {
        return readerID;
    }

    public void setReaderID(String readerID) {
        this.readerID = readerID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}