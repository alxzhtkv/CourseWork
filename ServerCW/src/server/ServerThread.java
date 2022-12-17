package server;
import database.*;
import persons.*;
import library.*;


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
            Book book = null;
            Favourites favourites=null;
            String answer;
            String serverMessage = null;
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
//                                reader=database.getReaderByID(user);
//                                System.out.println(reader.getName());
                            }else serverMessage="refused";
                            soos.writeObject(serverMessage);

//                            soos.writeObject(reader);
//
                        }


                        break;
                    }

                    case "getReader":
                    {
                        answer = (String) sois.readObject();
                        reader=database.getReaderByID(answer);
//                        user=database.getUserByID(answer);
                      System.out.println(reader.getName());

                        soos.writeObject(reader);
//                        soos.writeObject(user);
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

                    case "addFavourites":{
                        boolean flag=false;
                        Vector<Book> booksVector= database.getBooksFromDatabase();
                        favourites = (Favourites) sois.readObject();
                        serverMessage = "error";
                        
                        for (int i=0;i<booksVector.size();i++){
                            if(favourites.getBookID().equals(booksVector.get(i).getID())){
                                System.out.println(booksVector.get(i).getID());
                                database.insertFavourites(favourites);
                                serverMessage = "added";
                            }

                        }
                        soos.writeObject(serverMessage);
                        break;
                    }
                    case "showFavourites":{
                        String readerID = (String) sois.readObject();
                        Vector<Book> favouritesBooksVector=new Vector<>();

                        Vector<Book> booksVector= database.getBooksFromDatabase();
                        Vector<String> idFavouritesBooks = database.getIdFavouritesBooks(readerID);
//

                        for (int i=0;i<booksVector.size();i++){
                            for(int j=0;j<idFavouritesBooks.size();j++){
//                                booksVector.get(i).getID().equals()
                                if( booksVector.get(i).getID().equals(idFavouritesBooks.get(j))){
                                    favouritesBooksVector.add(booksVector.get(i));
                                }
                            }

                        }
                        serverMessage=Integer.toString(favouritesBooksVector.size());

                        soos.writeObject(serverMessage);
                        int i=0;
                        while (i<favouritesBooksVector.size()){
//                            soos.writeObject(booksVector.get(i));
                            sendBook(favouritesBooksVector.get(i));
                            i++;
                        }
//                        soos.writeObject(serverMessage);
                        break;
                    }

                    case "checkTitle":{
                        String id = (String) sois.readObject();
                        String title =database.getTitleBookByID(id);
                        soos.writeObject(title);


                        break;
                    }


                    case "addReview":{
                        answer = (String) sois.readObject();
                        if(answer.equals("ok")){
                            Review review = (Review) sois.readObject();
                            database.insertReview(review);
                        }

                        break;
                    }

                    case "showReviews":{

                        Vector<Review> reviewsVector= database.getReviews();
                        int size=reviewsVector.size();
                        System.out.println(size);
                        int i=0;
                        serverMessage=Integer.toString(size);
                        soos.writeObject(serverMessage);

                        while (i<size){
                            soos.writeObject(reviewsVector.get(i));
//                            sendBook(booksVector.get(i));
                            i++;
                        }
//
                        break;
                    }

                    case "addRequest":{
                        Request request = (Request) sois.readObject();
                        database.insertRequest(request);

                        break;
                    }

                    case "showRequests":{
                        String idReader = (String) sois.readObject();
                        Vector<Request> requestVector = database.getRequestsByID(idReader);

                        int size=requestVector.size();
                        System.out.println(size);
                        int i=0;
                        serverMessage=Integer.toString(size);
                        soos.writeObject(serverMessage);

                        while (i<size){
                            soos.writeObject(requestVector.get(i));
                            i++;
                        }


                        break;
                    }

                    case "checkBook":{
                        String id = (String) sois.readObject();
                        String info =database.getBookByID(id);
                        soos.writeObject(info);


                        break;
                    }

                    case "addOrder":{
                        answer = (String) sois.readObject();
                        if(answer.equals("ok")){
//                            Vector<Book> books = database.getBooksFromDatabase();
                            Order order = (Order) sois.readObject();
                            database.insertOrder(order);
//                            database.insertReview(order);
                        }

                        break;
                    }

                    case "showOrders":{
                        String idReader = (String) sois.readObject();
                        Vector<Order> ordersVector = database.getOrderByReaderID(idReader);

                        int size=ordersVector.size();
                        System.out.println(size);
                        int i=0;
                        serverMessage=Integer.toString(size);
                        soos.writeObject(serverMessage);

                        while (i<size){
                            soos.writeObject(ordersVector.get(i));
                            i++;
                        }


                        break;
                    }

                    case "checkOrder":{
                        String id = (String) sois.readObject();
                        String info =database.getOrderByID(id);
                        soos.writeObject(info);
                        break;
                    }

                    case "checkReturnOrder":{
                        String id = (String) sois.readObject();
                        String info =database.getIssuedOrderByID(id);
                        soos.writeObject(info);
                        break;
                    }
                    case "issueOrder":{

                        IssuedOrder issuedOrder = (IssuedOrder) sois.readObject();
                        boolean flag= database.checkOrderAvailability(issuedOrder);
                        System.out.println(flag);
                        serverMessage ="add";
                        if(flag){
                            soos.writeObject(serverMessage);
                        }else {
                            serverMessage ="error";
                            soos.writeObject(serverMessage);
                        }


//                        Boolean flag = database.
                        break;
                    }


                    case "returnOrder":{

                        String bookID = (String) sois.readObject();
                        String orderID = (String) sois.readObject();

                        boolean flag= database.returnOrder(bookID,orderID);
                        serverMessage ="ok";
                        if(flag){
                            soos.writeObject(serverMessage);
                        }else {
                            serverMessage ="error";
                            soos.writeObject(serverMessage);
                        }


//                        Boolean flag = database.
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
