package server;
import database.*;
import persons.*;
import publications.*;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class ServerThread implements Runnable{
    protected Socket clientSocket = null;
    ObjectInputStream sois;
    ObjectOutputStream soos;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            Database database= Database.getInstance();
            User user;
            Reader reader;
            Book book;
            String answer;
            String serverMessage;
//            Passenger passenger=new Passenger();
//            Person person=new Person();
//            Admin admin=new Admin();
//            User user=new User();
//            Employee employee=new Employee();
            sois = new ObjectInputStream(clientSocket.getInputStream());
            soos = new ObjectOutputStream(clientSocket.getOutputStream());
            while (true){
//                    String choice =(String)sois.readObject();

                switch ((String)sois.readObject()){
                    case "registration":
                    {
                        System.out.println("регистрация");
//                        user = getUser();
                        user = (User) sois.readObject();
                        database.insertUser(user);
//                        Reader reader = getReader();
                        reader = (Reader) sois.readObject();

                        System.out.println(reader.getName());
                        database.insertReader(reader);


                        break;
                    }
                    case "authorization":
                    {
//                        user = getUser();
                        user = (User) sois.readObject();
                        answer = (String) sois.readObject();
                        System.out.println("хуй, но рабочего характера автризации");


                        user.getLogin();

                        if(answer.equals("admin")){
                            if(database.authorizationAdminCheck(user)){
                                serverMessage="approvedAdmin";
                            }else serverMessage="refused";
                            soos.writeObject(serverMessage);
                        }
                        else {
                            if(database.authorizationCheck(user)){
                                serverMessage="approved";
                            }else serverMessage="refused";
                            soos.writeObject(serverMessage);
                        }


                        break;
                    }

                    case "addingBook":{
                        book=getBook();
//
                        database.insertBook(book);
                        System.out.println( book.getAuthor());

                        break;
                    }

                    case "showBooks":{
                        Vector<Book> booksVector= database.getBooksFromDatabase();
                        int size=booksVector.size();
                        System.out.println(size);
                        int i=0;
                        serverMessage=Integer.toString(size);
                        soos.writeObject(serverMessage);

                        while (i<size){
//                            soos.writeObject(booksVector.get(i));
                            sendBook(booksVector.get(i));
                            i++;
                        }
//
                        break;
                    }
                    case "deleteBook":{
                        System.out.println("зашло удаление");
                        answer = (String) sois.readObject();
                        boolean flag=database.deleteBookByID(answer);
                        System.out.println(flag);

                        if(flag){
                            serverMessage="deleted";
                        }else serverMessage="error";
                        soos.writeObject(serverMessage);

                        break;
                    }
                    case "searchBook":{
                        answer = (String) sois.readObject();
                        Vector<Book> booksVectorSearch= database.searchBook(answer);
                        int size=booksVectorSearch.size();
                        System.out.println(size);
                        int i=0;
                        serverMessage=Integer.toString(size);
                        soos.writeObject(serverMessage);

                        while (i<size){
                            sendBook(booksVectorSearch.get(i));
                            i++;
                        }


                        break;
                    }
                    case "showReaders":{
                        Vector<Reader> readersVector= database.getReadersFromDatabase();
                        int size=readersVector.size();
                        System.out.println(size);
                        int i=0;
                        serverMessage=Integer.toString(size);
                        soos.writeObject(serverMessage);

                        while (i<size){
                            sendReader(readersVector.get(i));
                            i++;
                        }

                        break;


                    }

                    case "showUsers":{
                        Vector<User> usersVector= database.getUsersFromDatabase();
                        int size=usersVector.size();
                        System.out.println(size);
                        int i=0;
                        serverMessage=Integer.toString(size);
                        soos.writeObject(serverMessage);

                        while (i<size){
                            sendUser(usersVector.get(i));
                            i++;
                        }

                        break;


                    }

                    case "addAdmin":{
                        user = getUser();
                        database.insertAdmin(user);

                        break;


                    }

                }



            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public User getUser(){
        String login,pass;
        try {
            login = (String) sois.readObject();
            pass = (String) sois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        User usertemp=new User(login,pass);
        return usertemp;

    }

    public Reader getReader(){
        String login,passport,password,name,surname,patronymic,phone,birthday;
        try {
            login = (String) sois.readObject();
            password= (String) sois.readObject();
            passport = (String) sois.readObject();
            name = (String) sois.readObject();
            surname = (String) sois.readObject();
            patronymic = (String) sois.readObject();
            phone = (String) sois.readObject();
            birthday = (String) sois.readObject();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Reader readertemp =new Reader(login,password,name,surname,patronymic,passport,phone,birthday);

        return readertemp;

    }

    public Book getBook(){
        String id,title,publisher,genre,year,count,author;
        try {
            id = (String) sois.readObject();
            title= (String) sois.readObject();
            publisher = (String) sois.readObject();
            genre = (String) sois.readObject();
            year = (String) sois.readObject();
            count = (String) sois.readObject();
            author = (String) sois.readObject();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

//
        Book booktemp=new Book(id,title,publisher,genre,year,count,author);
        return booktemp;

    }

    public void sendBook(Book book){
        try {
            soos.writeObject(book.getID());
            soos.writeObject(book.getTitle());
            soos.writeObject(book.getPublisher());
            soos.writeObject(book.getGenre());
            soos.writeObject(book.getYear());
            soos.writeObject(book.getCount());
            soos.writeObject(book.getAuthor());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendReader(Reader reader){
        try {
            soos.writeObject(reader.getLogin());
            soos.writeObject(reader.getPassword());
            soos.writeObject(reader.getName());
            soos.writeObject(reader.getSurname());
            soos.writeObject(reader.getPatronymic());
            soos.writeObject(reader.getPassportID());
            soos.writeObject(reader.getPhone());
            soos.writeObject(reader.getBirthDay());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendUser(User user){
        try {
            soos.writeObject(user.getLogin());
            soos.writeObject(user.getPassword());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
