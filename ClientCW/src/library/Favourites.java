package library;

import java.io.Serializable;

public class Favourites implements Serializable {
    private String readerID;
    private String bookID;

    public Favourites(String readerID, String bookID) {
        this.readerID = readerID;
        this.bookID = bookID;
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
}
