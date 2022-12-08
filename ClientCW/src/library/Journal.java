package library;

public class Journal extends Publication{
    String number;

    public Journal(String ID, String title, String publisher, String genre, String year, String count, String number) {
        super(ID, title, publisher, genre, year, count);
        this.number = number;
    }
}
