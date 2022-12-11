package publications;

import java.io.Serializable;

public class Publication implements Serializable {
    String ID;
    String title;
    String publisher;
    String genre;
    String year;
    String count;

    public Publication(){}


    public Publication(String ID, String title, String publisher, String genre, String year, String count) {
        this.ID = ID;
        this.title = title;
        this.publisher = publisher;
        this.genre = genre;
        this.year = year;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}