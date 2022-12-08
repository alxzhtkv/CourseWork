package client;
import persons.*;
import library.*;
import persons.Reader;

import java.io.*;//импорт пакета, содержащего классы для
// ввода/вывода
import java.net.*;//импорт пакета, содержащего классы для
// работы в сети
import java.io.Serializable;
import java.util.Arrays;

import java.io.IOException;
import java.util.Scanner;

public class ClientService {
    //    User user;
    ObjectOutputStream coos=null;
    ObjectInputStream cois=null;
    Socket clientSocket=null;

    public  ClientService(){


    }

    public void getConnection(){
        try {
            System.out.println("server connecting....");
            clientSocket = new Socket("127.0.0.1",2525);//установление //соединения между локальной машиной и указанным портом узла сети
            System.out.println("connection established....");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));//создание//буферизированного символьного потока ввода
            coos = new ObjectOutputStream(clientSocket.getOutputStream());//создание//потока вывода
            cois = new ObjectInputStream(clientSocket.getInputStream());//создание//потока ввода



        }catch(Exception e)	{
            e.printStackTrace();//выполнение метода исключения е
            endConnection();
        }
    }

    public void endConnection(){

        try{
            coos.close();//закрытие потока вывода
            cois.close();//закрытие потока ввода
            clientSocket.close();//закрытие сокета
            System.out.println("server disconnecting....");
        }catch(Exception e)	{
            e.printStackTrace();//выполнение метода исключения е
        }
    }

    public void sendUser(User user){
        try {
            coos.writeObject(user.getLogin());
            coos.writeObject(user.getPassword());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendBook(Book book){
        try {
            coos.writeObject(book.getID());
            coos.writeObject(book.getTitle());
            coos.writeObject(book.getPublisher());
            coos.writeObject(book.getGenre());
            coos.writeObject(book.getYear());
            coos.writeObject(book.getCount());
            coos.writeObject(book.getAuthor());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendReader(Reader reader){
        try {
            coos.writeObject(reader.getLogin());
            coos.writeObject(reader.getPassword());
            coos.writeObject(reader.getPassportID());
            coos.writeObject(reader.getName());
            coos.writeObject(reader.getSurname());
            coos.writeObject(reader.getPatronymic());
            coos.writeObject(reader.getPhone());
            coos.writeObject(reader.getBirthDay());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Book getBookFromDatabase(){
        String id,title,publisher,genre,year,count,author;
        try {
            id = (String) cois.readObject();
            title= (String) cois.readObject();
            publisher = (String) cois.readObject();
            genre = (String) cois.readObject();
            year = (String) cois.readObject();
            count = (String) cois.readObject();
            author = (String) cois.readObject();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

//
        Book book=new Book(id,title,publisher,genre,year,count,author);
        return book;

    }

    public Reader getReaderFromDatabase(){
        String login,password,name,surname,patronymic,passportId,phone,birthday;
        try {
            login = (String) cois.readObject();
            password= (String) cois.readObject();
            name = (String) cois.readObject();
            surname = (String) cois.readObject();
            patronymic = (String) cois.readObject();
            passportId = (String) cois.readObject();
            phone = (String) cois.readObject();
            birthday = (String) cois.readObject();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

//
        Reader reader= new Reader(login,password,name,surname,patronymic,passportId,phone,birthday);
        return reader;

    }

    public User getUserFromDatabase(){
        String login,password;
        try {
            login = (String) cois.readObject();
            password= (String) cois.readObject();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

//
        User user= new User(login,password);
        return user;

    }

}