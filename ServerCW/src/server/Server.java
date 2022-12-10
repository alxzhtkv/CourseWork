package server;
import database.*;
import publications.*;
import persons.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Server {

   static ServerSocket serverSocket = null;
    static Socket clientAccepted     = null;//объявление объекта класса Socket
    static ObjectInputStream sois   = null;//объявление байтового потока ввода
    static ObjectOutputStream soos   = null;



    public static void main(String[] arg) throws SQLException {

        Database database = new Database();
        Connection connection=database.databaseConnection();
        Statement statement=database.createTable(connection);

        //объявление объекта класса ServerSocket
        ServerSocket serverSocket = null;
        Socket clientAccepted     = null;//объявление объекта класса Socket
        ObjectInputStream sois   = null;//объявление байтового потока ввода
        ObjectOutputStream soos   = null;//объявление байтового потока вывода
        String serverMessage=null;
        String answer=null;
        try {
            System.out.println("server starting....");
            serverSocket = new ServerSocket(2525);//создание сокета сервера для //заданного порта
            clientAccepted = serverSocket.accept();//выполнение метода, который
            // обеспечивает реальное подключение сервера к клиенту
            System.out.println("connection established...."); //создание потока ввода sois = new
            sois = new ObjectInputStream(clientAccepted.getInputStream());
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());

//            String actionCommand;
//            actionCommand= (String) sois.readObject();
//            System.out.println(actionCommand);
            User user;
            Book book;

            boolean exit = true;

            while (exit){
                String choice =(String)sois.readObject();

                switch ((String)sois.readObject()){
                    case "registration":
                    {
                        System.out.println("регистрация");
                        user = getUser();
                        database.insertUser(user,connection);
                        Reader reader = getReader();
                        System.out.println(reader.getName());
                        database.insertReader(reader,connection);


                        break;
                    }
                    case "authorization":
                    {
                        user = getUser();
                        answer = (String) sois.readObject();
                        System.out.println("хуй, но рабочего характера автризации");


                        user.getLogin();

                        if(answer.equals("admin")){
                            if(database.authorizationAdminCheck(user,connection)){
                                serverMessage="approvedAdmin";
                            }else serverMessage="refused";
                            soos.writeObject(serverMessage);
                        }
                        else {
                            if(database.authorizationCheck(user,connection)){
                                serverMessage="approved";
                            }else serverMessage="refused";
                            soos.writeObject(serverMessage);
                        }


                        break;
                    }

                    case "addingBook":{
                        book=getBook();
                        database.insertBook(book,connection);
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
                            sendBook(booksVector.get(i));
                            i++;
                        }

                        break;
                    }
                    case "deleteBook":{
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
                        database.insertAdmin(user,connection);

                        break;


                    }

                }



            }
            } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }



        catch(Exception e) {} finally {
            try {
                sois.close();//закрытие потока ввода
                soos.close();//закрытие потока вывода
                clientAccepted.close();//закрытие сокета, выделенного для клиента
                serverSocket.close();//закрытие сокета сервера
            } catch(Exception e) {
                e.printStackTrace();//вызывается метод исключения е
            }
        }
    }


    public static User getUser(){
        String login,pass;
        try {
            login = (String) Server.sois.readObject();
            pass = (String) sois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        User usertemp=new User(login,pass);
        return usertemp;

    }

    public static Reader getReader(){
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

    public static Book getBook(){
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

    public static void sendBook(Book book){
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

    public static void sendReader(Reader reader){
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

    public static void sendUser(User user){
        try {
            soos.writeObject(user.getLogin());
            soos.writeObject(user.getPassword());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




