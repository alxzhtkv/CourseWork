package library;

import java.io.Serializable;

public class Request implements Serializable {
    String readerID;
    String title;

    public Request(String readerID, String title) {
        this.readerID = readerID;
        this.title = title;
    }

    public String getReaderID() {
        return readerID;
    }

    public void setReaderID(String readerID) {
        this.readerID = readerID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
