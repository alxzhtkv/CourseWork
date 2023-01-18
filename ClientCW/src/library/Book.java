package library;

import java.io.Serializable;

public class Book extends Publication implements Serializable {
    String author;


    public Book() {
        super();
    }

    public Book(String ID, String title, String publisher, String genre, String year, String count, String author) {
        super(ID, title, publisher, genre, year, count);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}