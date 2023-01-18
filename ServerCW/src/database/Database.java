package database;
import com.mysql.cj.util.DnsSrv;
import persons.*;
import library.*;

import java.sql.*;
import java.util.Vector;

public class Database {
    private static volatile Database database=null;
    String userName = "root";
    String password = "1234";

    String DATABASE_URL = "jdbc:mysql://localhost:3306/library";
    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    Statement statement = null;
    Connection connection=null;

//    public Connection databaseConnection() throws SQLException {
//        Connection connection = null;
//        try {
//            System.out.println("Registering JDBC driver...");
//            Class.forName(JDBC_DRIVER);
//
//            System.out.println("Creating connection to database...");
//            connection = DriverManager.getConnection(DATABASE_URL, userName, password);
//
//        } catch (Exception e) {
//            System.out.println("Error " + e.getMessage());
//            if (connection != null) {
//                connection.close();
//            }
//        }
//        return connection;
//    }

    private Database() throws SQLException {
        try{
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);

            System.out.println("Creating connection to database...");
            connection=DriverManager.getConnection(DATABASE_URL,userName,password);

        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
            if(connection!=null){
                connection.close();
            }
        }
    }

    public static Database getInstance() throws SQLException {
        if(database==null) {
            synchronized (Database.class) {
                if(database==null)
                    database = new Database();
            }
        }
        return database;
    }

    public Statement addUserToTable(Connection conn,String log, String pass) throws SQLException{
        try {

            String SQL = "INSERT libraryUser(login,password) VALUES ('log', 'pass')";
            statement = conn.createStatement();
            statement.executeUpdate(SQL);

        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
            if(statement!=null){
                statement.close();
            }
        }

        return statement;
    }


    public Statement createTable() throws SQLException {
        try {
            String SQL ="CREATE TABLE IF NOT EXISTS LibraryUser"
                    +"(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    +"login INTEGER NOT NULL UNIQUE,"
                    + "password VARCHAR (30) NOT NULL)";
            statement = connection.createStatement();
            statement.executeUpdate(SQL);

            SQL ="CREATE TABLE IF NOT EXISTS LibraryAdmin"
                    +"(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    +"login INTEGER NOT NULL UNIQUE,"
                    + "password VARCHAR (30) NOT NULL)";
            statement = connection.createStatement();
            statement.executeUpdate(SQL);

            SQL ="CREATE TABLE IF NOT EXISTS LibraryReader"
//                    + "(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
////                    + "login INTEGER,"
                    + "(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    + "login INTEGER,"
                    + "passportID VARCHAR (30),"
                    + "nameReader VARCHAR (30),"
                    + "surname VARCHAR (30),"
                    + "patronymic VARCHAR (30),"
//                    + "passwordID VARCHAR (30),"
                    + "phone VARCHAR (30),"
                    + "birthDay VARCHAR (30),"
                    + "FOREIGN KEY(id) REFERENCES LibraryUser(id))";


            statement = connection.createStatement();
            statement.executeUpdate(SQL);

            SQL ="CREATE TABLE IF NOT EXISTS LibraryBooks"
//                    + "(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
////                    + "login INTEGER,"
//                    + "(id INTEGER AUTO_INCREMENT,"
                    + "(IDbook INTEGER PRIMARY KEY,"
                    + "title VARCHAR (30),"
                    + "author VARCHAR (30),"
                    + "publisher VARCHAR (30),"
                    + "genre VARCHAR (30),"
//                    + "passwordID VARCHAR (30),"
                    + "yearBook INTEGER,"
                    + "status VARCHAR (30))";


            statement = connection.createStatement();
            statement.executeUpdate(SQL);


            SQL ="CREATE TABLE IF NOT EXISTS LibraryFavourites"
                    +"(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    +"readerID INTEGER,"
                    + "bookID INTEGER NOT NULL,"
                    + "FOREIGN KEY (bookID) REFERENCES LibraryBooks (IDbook))";
            statement = connection.createStatement();
            statement.executeUpdate(SQL);

            SQL ="CREATE TABLE IF NOT EXISTS LibraryOrders"
                    +"(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    +"readerID INTEGER,"
                    + "bookID INTEGER NOT NULL,"
                    + "booktitle VARCHAR (30),"
                    + "orderID INTEGER NOT NULL UNIQUE,"
                    + "status  VARCHAR (30))";
//                    + "FOREIGN KEY (bookID) REFERENCES LibraryBooks (IDbook))";
            statement = connection.createStatement();
            statement.executeUpdate(SQL);

            SQL ="CREATE TABLE IF NOT EXISTS LibraryReview"
                    +"(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    +"readerID INTEGER,"
                    + "bookID INTEGER NOT NULL,"
                    + "booktitle VARCHAR (30),"
                    + "review TEXT)";
//                    + "FOREIGN KEY (bookID) REFERENCES LibraryBooks (IDbook))";
            statement = connection.createStatement();
            statement.executeUpdate(SQL);

//Request
            SQL ="CREATE TABLE IF NOT EXISTS LibraryRequest"
                    +"(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    +"readerID INTEGER,"

                    + "booktitle VARCHAR (30))";
                    //                    + "FOREIGN KEY (bookID) REFERENCES LibraryBooks (IDbook))";
            statement = connection.createStatement();
            statement.executeUpdate(SQL);


            SQL ="CREATE TABLE IF NOT EXISTS LibraryIssuedOrders"
                    +"(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    + "orderID INTEGER NOT NULL UNIQUE,"
                    + "bookID INTEGER NOT NULL,"
                    + "booktitle VARCHAR (30),"
                    + "readerID INTEGER,"
                    + "dateI VARCHAR (30),"
                    + "dateb  VARCHAR (30))";

//                    + "FOREIGN KEY (bookID) REFERENCES LibraryBooks (IDbook))";
            statement = connection.createStatement();
            statement.executeUpdate(SQL);

        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
            if(statement!=null){
                statement.close();
            }
        }


        return statement;
    }

    public void dropTable() throws SQLException {
        try{
            String SQL="DROP TABLE LibraryUser";
            statement= connection.createStatement();
            statement.executeUpdate(SQL);
        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }
    }

    public void insertUser(User user){
        String SQL = "INSERT INTO LibraryUser(login,password) "
                + "VALUES(?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            int affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertBook(Book book){
        String SQL = "INSERT INTO LibraryBooks(IDbook,title,author,publisher,genre,yearBook,status) "
                + "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, book.getID());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setString(4, book.getPublisher());
            pstmt.setString(5, book.getGenre());
            pstmt.setString(6, book.getYear());
            pstmt.setString(7, book.getCount());


            int affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertAdmin(User user){
        String SQL = "INSERT INTO LibraryAdmin(login,password) "
                + "VALUES(?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            int affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertReader(Reader reader){
        String SQL = "INSERT INTO LibraryReader(login,passportID,nameReader,surname,patronymic,phone,birthDay) "
                + "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, reader.getLogin());
            pstmt.setString(2, reader.getPassportID());
            pstmt.setString(3, reader.getName());
            pstmt.setString(4, reader.getSurname());
            pstmt.setString(5, reader.getPatronymic());
            pstmt.setString(6, reader.getPhone());
            pstmt.setString(7, reader.getBirthDay());
            int affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void insertFavourites(Favourites favourites){
        String SQL = "INSERT INTO LibraryFavourites(readerID,bookID) "
                + "VALUES(?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, favourites.getReaderID());
            pstmt.setString(2, favourites.getBookID());
            int affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean authorizationCheck(User user){
        boolean flag=false;

        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryUser` WHERE (login ="+user.getLogin()+")" );
//            +"AND (password ="+ user.getPassword().toString() + ")"
            if (resultSet.next()){
                String g=resultSet.getString(3);
                if(g.equals(user.getPassword()))
                    flag=true;

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Vector<Book> getBooksFromDatabase(){
        Vector<Book> booksTemp=new Vector<Book>();

        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryBooks`" );
//            +"AND (password ="+ user.getPassword().toString() + ")"
            while (resultSet.next()){
                String ID=resultSet.getString(1);
                String title=resultSet.getString(2);
                String author = resultSet.getString(3);
                String publisher=resultSet.getString(4);
                String genre=resultSet.getString(5);
                String year=resultSet.getString(6);
                String count =resultSet.getString(7);
                Book book = new Book(ID,title,publisher,genre,year,count,author);

                booksTemp.add(book);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booksTemp;
    }

    public Book searchBookByID(String id){
        String undefined = "не определено";
        Book book = new Book(id,undefined,undefined,undefined,undefined,undefined,undefined);
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryBooks` WHERE (IDbook ="+id+")"  );
            if (resultSet.next()){

                String title=resultSet.getString(2);
                String author = resultSet.getString(3);
                String publisher=resultSet.getString(4);
                String genre=resultSet.getString(5);
                String year=resultSet.getString(6);
                String status =resultSet.getString(7);

                book.setTitle(title);
                book.setAuthor(author);
                book.setPublisher(publisher);
                book.setGenre(genre);
                book.setYear(year);
                book.setCount(status);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public void editBookInfo(Book book){


//        String SQL="UPDATE libraryUser SET password=? WHERE login="+reader.getLogin();
//        PreparedStatement  preparedStmt=connection.prepareStatement(SQL);
//        preparedStmt.setString(1,reader.getPassword());
//
//        preparedStmt.executeUpdate();
//        System.out.println("Table User is update");

        String SQL="UPDATE libraryBooks SET title=?, author=?, publisher=?, genre=?, yearBook=? WHERE IDbook="+book.getID();
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(SQL);
            preparedStmt.setString (1, book.getTitle());
            preparedStmt.setString(2,book.getAuthor());
            preparedStmt.setString(3, book.getPublisher());
            preparedStmt.setString(4, book.getGenre());
            preparedStmt.setString(5, book.getYear());

            preparedStmt.executeUpdate();
            System.out.println("Table Books is update");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public String getTitleBookByID(String id){
        String title="Книга не определена";
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryBooks` WHERE (IDbook ="+id+")" );
//            +"AND (password ="+ user.getPassword().toString() + ")"
            while (resultSet.next()){

                 title=resultSet.getString(2);

                System.out.println(title);


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return title;
    }


    public String getBookByID(String id){
        String answer="Не определена;Не определенo";
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryBooks` WHERE (IDbook ="+id+")" );
//            +"AND (password ="+ user.getPassword().toString() + ")"
            while (resultSet.next()){

                String title=resultSet.getString(2);
                String status = resultSet.getString(7);
                answer = title + ";" +status;

                System.out.println(answer);


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return answer;
    }

    public void insertReview(Review review){
        String SQL = "INSERT INTO LibraryReview(readerID,bookID,booktitle,review) "
                + "VALUES(?,?,?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, review.getReaderID());
            pstmt.setString(2, review.getBookID());
            pstmt.setString(3, review.getTitle());
            pstmt.setString(4, review.getText());

            int affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Vector<String> getIdFavouritesBooks(String readerID){
        Vector<String> idFavouritesBooks = new Vector<String>();
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryFavourites` WHERE (readerID ="+readerID+")" );
//            +"AND (password ="+ user.getPassword().toString() + ")"
            while (resultSet.next()){
                String IDbook=resultSet.getString(3);
                idFavouritesBooks.add(IDbook);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return idFavouritesBooks;
    }

    public Vector<Reader> getReadersFromDatabase(){
        Vector<Reader> readersTemp=new Vector<Reader>();
        String password = "1";
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryReader`" );
//            +"AND (password ="+ user.getPassword().toString() + ")"
            while (resultSet.next()){
                String login=resultSet.getString(2);
                String passportID=resultSet.getString(3);
                String nameReader = resultSet.getString(4);
                String surname=resultSet.getString(5);
                String patronymic=resultSet.getString(6);
                String phone=resultSet.getString(7);
                String birthday =resultSet.getString(8);
                Reader reader = new Reader(login,password,nameReader,surname,patronymic,passportID,phone, birthday);


                readersTemp.add(reader);

//                String g=resultSet.getString(3);
//                if(g.equals(user.getPassword()))
//                    flag=true;
//                System.out.println("ты милашка!");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return readersTemp;
    }


    public Reader getReaderByID(String id){
       Reader reader=null;
        String password = "1";
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryReader`  WHERE (login ="+id+")" );
//            +"AND (password ="+ user.getPassword().toString() + ")"
            while (resultSet.next()){
                String login=resultSet.getString(2);
                String passportID=resultSet.getString(3);
                String nameReader = resultSet.getString(4);
                String surname=resultSet.getString(5);
                String patronymic=resultSet.getString(6);
                String phone=resultSet.getString(7);
                String birthday =resultSet.getString(8);
                 reader = new Reader(login,password,nameReader,surname,patronymic,passportID,phone, birthday);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(reader!=null){
            try {
                ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryUser` WHERE (login ="+id+")" );

                while (resultSet.next()){

                    password=resultSet.getString(3);
                    reader.setPassword(password);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

//        try {
//            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryUser`  WHERE (login ="+id+")" );
//            while (resultSet.next()){
//                password=resultSet.getString(3);
//               reader.setPassword(password);
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return reader;
    }



    public Vector<User> getUsersFromDatabase(){
        Vector<User> usersVectorTemp=new Vector<User>();

        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryUser`" );

            while (resultSet.next()){
                String login=resultSet.getString(2);
                String password=resultSet.getString(3);

                User user = new User(login,password);


                usersVectorTemp.add(user);


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersVectorTemp;
    }

    public Boolean authorizationAdminCheck(User user){
        boolean flag=false;

        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryAdmin` WHERE (login ="+user.getLogin()+")" );
//            +"AND (password ="+ user.getPassword().toString() + ")"
            if (resultSet.next()){
                String g=resultSet.getString(3);
                if(g.equals(user.getPassword()))
                    flag=true;

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }





    public Boolean deleteBookByID(String id){
        boolean flag=false;
        String status="";
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM LibraryBooks WHERE (IDbook ="+ id +")" );
//            +"AND (password ="+ user.getPassword().toString() + ")"
            if (resultSet.next()){
                status=resultSet.getString(7);
                if(status.equals("в наличии")){
                    flag=true;
                }


                System.out.println("запись есть!");
            }
            if(flag){
                String sql = "DELETE FROM LibraryBooks " +
                        "WHERE (IDbook ="+id+")";
                statement.executeUpdate(sql);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }




        return flag;
    }

    public Boolean deleteUserByID(String id){
        boolean flag=false;
        String status="";
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM LibraryUser WHERE (login ="+ id +")" );

            if (resultSet.next()){


                ResultSet resultSetOrder =statement.executeQuery("SELECT status FROM LibraryOrders WHERE (readerID ="+ id +")" );
                if(resultSetOrder.next()){

                    status=resultSetOrder.getString(1);
                    if(status.equals("обработан")){

                        flag=true;
                    }
                } else {

                    flag=true;
                }

            }
            if(flag){
                String sql = "DELETE FROM LibraryOrders " +
                        "WHERE (readerID ="+id+")";
                statement.executeUpdate(sql);
                sql = "DELETE FROM LibraryReader " +
                        "WHERE (login ="+id+")";
                statement.executeUpdate(sql);
                sql = "DELETE FROM LibraryUser " +
                        "WHERE (login ="+id+")";
                statement.executeUpdate(sql);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }





        return flag;
    }

    public Boolean deleteOrderByID(String id){
        boolean flag=false;
        String status="";
        try {

                ResultSet resultSet =statement.executeQuery("SELECT status FROM LibraryOrders WHERE (orderID ="+ id +")" );
                if(resultSet.next()){

                    status=resultSet.getString(1);
                    if(status.equals("обработан")){
                        flag=true;
                    }
                }

            if(flag){
                String sql = "DELETE FROM LibraryOrders " +
                        "WHERE (orderID ="+id+")";
                statement.executeUpdate(sql);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }





        return flag;
    }


    public boolean deleteFavouritesByBookID(Favourites favourite){
        boolean flag=false;
        String id="";

        try {
            ResultSet resultSet =statement.executeQuery("SELECT * FROM LibraryFavourites WHERE (readerID ="+ favourite.getReaderID() +")" );

            if(resultSet.next()){
                id=resultSet.getString(1);
                while (resultSet.next()){
                    String bookID = resultSet.getString(3);

                    if(bookID.equals(favourite.getBookID())){
                        flag=true;
                        break;
                    }
                }

            }
            if(flag){
                String sql = "DELETE FROM LibraryFavourites " +
                             "WHERE (id ="+id+")";
                statement.executeUpdate(sql);
                System.out.println("a record in the FavouritesTable has been deleted");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    public  Vector<Book> searchBook(String value){
        Vector<Book> booksTemp=new Vector<Book>();
        boolean flag=false;
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM LibraryBooks" );
//            +"AND (password ="+ user.getPassword().toString() + ")"

                while (resultSet.next()){

                    String ID=resultSet.getString(1);
                    String title=resultSet.getString(2);
                    String author = resultSet.getString(3);
                    String publisher=resultSet.getString(4);
                    String genre=resultSet.getString(5);
                    String year=resultSet.getString(6);
                    String status =resultSet.getString(7);
                    if(value.equals(ID)==true || value.equals(title)==true  ||value.equals(author)==true  ||value.equals(publisher)==true  ||value.equals(genre) ==true || value.equals(year)==true  || value.equals(status)==true ){

                        Book book = new Book(ID,title,publisher,genre,year,status,author);
                        System.out.println(book.getTitle());
                        booksTemp.add(book);
                    }

                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booksTemp;
    }

     public Vector<Review> getReviews(){
         Vector<Review> reviewsTemp=new Vector<Review>();

         try {
             ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryReview`" );

             while (resultSet.next()){
                 String readerID=resultSet.getString(2);
                 String bookID=resultSet.getString(3);
                 String bookTitle=resultSet.getString(4);
                 String text=resultSet.getString(5);

                 Review review = new Review(readerID,bookID,bookTitle,text);
                 reviewsTemp.add(review);
             }

         } catch (SQLException e) {
             e.printStackTrace();
         }
        return reviewsTemp;
     }



     public void insertRequest(Request request){
         String SQL = "INSERT INTO LibraryRequest(readerID,booktitle) "
                 + "VALUES(?,?)";

         try {
             PreparedStatement pstmt = connection.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS);
             pstmt.setString(1, request.getReaderID());
             pstmt.setString(2,request.getTitle());
             int affectedRows = pstmt.executeUpdate();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
     }

     public Vector<Request> getRequestsByID(String readerId){
         Vector<Request> requestsTemp=new Vector<Request>();

         try {
             ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryRequest`  WHERE (readerID ="+readerId+")" );
             while (resultSet.next()){

                 String bookTitle =resultSet.getString(3);
                 Request request = new Request(readerId,bookTitle);
                 requestsTemp.add(request);
             }


         } catch (SQLException e) {
             e.printStackTrace();
         }

         return requestsTemp;
     }

    public void insertOrder(Order order){
        String SQL = "INSERT INTO LibraryOrders(readerID,bookID,booktitle, orderID,status) "
                + "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, order.getReaderID());
            pstmt.setString(2, order.getBooksID());
            pstmt.setString(3, order.getBookTitle());
            pstmt.setString(4, order.getOrderID());
            pstmt.setString(5, order.getStatus());
            int affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vector<Order> getOrderByReaderID(String readerId){
        Vector<Order> ordersTemp=new Vector<Order>();

        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryOrders`  WHERE (readerID ="+readerId+")" );
            while (resultSet.next()){

                String bookID = resultSet.getString(3);
                String bookTitle = resultSet.getString(4);
                String orderID = resultSet.getString(5);
                String status = resultSet.getString(6);
                Order order = new Order(readerId,bookID,orderID,status,bookTitle);
//                Request request = new Request(readerId,bookTitle);
                ordersTemp.add(order);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordersTemp;
    }

    public Vector<IssuedOrder> getIssuedOrderByReaderID(String readerId){
        Vector<IssuedOrder> ordersTemp=new Vector<IssuedOrder>();

        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryIssuedOrders`  WHERE (readerID ="+readerId+")" );
            while (resultSet.next()){

                String orderID = resultSet.getString(2);
                String bookID = resultSet.getString(3);
                String bookTitle = resultSet.getString(4);
                String dateI = resultSet.getString(6);
                String dateB = resultSet.getString(6);
                IssuedOrder order = new IssuedOrder(orderID,readerId,bookID,bookTitle,dateI,dateB);
                ordersTemp.add(order);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordersTemp;
    }

    public String getOrderByID(String id){
        String answer="Не определенo;Не определенo;Не определенo";
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryOrders` WHERE (orderID ="+id+")" );
//            +"AND (password ="+ user.getPassword().toString() + ")"
            while (resultSet.next()){
                String reader = resultSet.getString(2);
                String bookID = resultSet.getString(3);
                String title=resultSet.getString(4);


                answer = title + ";" +reader + ";"+bookID;

                System.out.println(answer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return answer;
    }

    public String getIssuedOrderByID(String id){
        String answer="Не определенo;Не определенo;Не определенo;Не определенo;Не определенo";
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryIssuedOrders` WHERE (orderID ="+id+")" );
//            +"AND (password ="+ user.getPassword().toString() + ")"
            while (resultSet.next()){
                String reader = resultSet.getString(5);
                String bookID = resultSet.getString(3);
                String title=resultSet.getString(4);
                String dateI =resultSet.getString(6);
                String dateB =resultSet.getString(7);


                answer = title + ";" +reader + ";"+bookID+ ";"+dateI+ ";"+dateB;

                System.out.println(answer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return answer;
    }
    public boolean searchAndUpdateOrder(String orderID){
        boolean flag= false;




        return flag;
    }


    public boolean checkOrderAvailability(IssuedOrder order){
        boolean flag=false;
        String statusOrder="";
        String statusBook="";
        try {
            ResultSet resultSet=statement.executeQuery("SELECT status FROM `LibraryOrders` WHERE (orderID ="+order.getOrderID()+")" );


            if (resultSet.next()){

                statusOrder=resultSet.getString(1);
                if(statusOrder.equals("обработан")){

                    ResultSet resultSetBook =statement.executeQuery("SELECT status FROM `LibraryBooks` WHERE (IDbook ="+order.getBooksID()+")" );

                    if (resultSetBook.next()){
                        statusBook=resultSetBook.getString(1);
                        if(statusBook.equals("в наличии")){

                            flag=true;


                        }
                    }
                }
            }

            if(flag){

                String SQL = "INSERT INTO LibraryIssuedOrders(orderID,bookID,booktitle,readerID,dateI,dateb) "
                        + "VALUES(?,?,?,?,?,?)";

                try {
                    PreparedStatement pstmt = connection.prepareStatement(SQL,
                            Statement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1, order.getOrderID());
                    pstmt.setString(2, order.getBooksID());
                    pstmt.setString(3, order.getBookTitle());
                    pstmt.setString(4, order.getReaderID());
                    pstmt.setString(5, order.getDateI());
                    pstmt.setString(6, order.getDateB());
                    int affectedRows = pstmt.executeUpdate();

                    SQL="UPDATE LibraryOrders SET status=? WHERE OrderID="+order.getOrderID();
                    PreparedStatement preparedStmt = connection.prepareStatement(SQL);
                    String status = "выдан";
                    preparedStmt.setString (1, status);
                    preparedStmt.executeUpdate();
                    System.out.println("Table1 is update");

                    SQL="UPDATE Librarybooks SET status=? WHERE IDbook="+order.getBooksID();
                    PreparedStatement preparedStmtBook = connection.prepareStatement(SQL);
                    status = "выдана";
                    preparedStmtBook.setString (1, status);
                    preparedStmtBook.executeUpdate();
                    System.out.println("Table2 is update");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return flag;
    }


    public boolean returnOrder(String bookID,String orderID){
        boolean flag=false;
        String statusOrder="";
        String statusBook="";
        try {
            ResultSet resultSet=statement.executeQuery("SELECT status FROM `LibraryOrders` WHERE (orderID ="+orderID+")" );


            if (resultSet.next()){
                statusOrder=resultSet.getString(1);
                if(statusOrder.equals("выдан")){
                    ResultSet resultSetBook =statement.executeQuery("SELECT status FROM `LibraryBooks` WHERE (IDbook ="+bookID+")" );

                    if (resultSetBook.next()){
                        statusBook=resultSetBook.getString(1);
                        if(statusBook.equals("выдана")){
                            flag=true;


                        }
                    }
                }
            }

            if(flag){

                try {
                    String SQL = "DELETE FROM LibraryIssuedOrders " +
                            "WHERE (orderID ="+orderID+")";

                    statement.executeUpdate(SQL);

                    SQL="DELETE FROM LibraryOrders WHERE orderID="+orderID;
                    statement.executeUpdate(SQL);
                    System.out.println("LibraryOrders is update");

                    SQL="UPDATE Librarybooks SET status=? WHERE IDbook="+bookID;
                    PreparedStatement preparedStmtBook = connection.prepareStatement(SQL);
                    String status = "в наличии";
                    preparedStmtBook.setString (1, status);
                    preparedStmtBook.executeUpdate();
                    System.out.println("Librarybooks is update");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return flag;
    }


    public Vector<String> getGenreForDiagram(){
        Vector<String> data = new Vector<String>();
        String status;
        String genre = "";
        int novel=0;
        int detective=0;
        int scienceFiction = 0;
        int fantasy=0;
        int other=0;




        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryBooks`" );

            while (resultSet.next()){
                status=resultSet.getString(7);
                if(status.equals("выдана")){
                    genre=resultSet.getString(5);
                    if(genre.equals("Роман")){
                        novel++;

                    } else if (genre.equals("Детектив")) {
                        detective++;
                    }
                    else if(genre.equals("Фантастика")){
                        scienceFiction++;
                    }
                    else if(genre.equals("Фентези")){
                        fantasy++;
                    } else
                        other++;

                }


            }

            data.add(Integer.toString(novel));
            data.add(Integer.toString(detective));
            data.add(Integer.toString(scienceFiction));
            data.add(Integer.toString(fantasy));
            data.add(Integer.toString(other));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        return data;
    }


    public Vector<String> getBooksForDiagram(){
        Vector<String> data = new Vector<String>();
        String status;
        String genre = "";
        int fund=0;
        int onHand=0;

        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryBooks`" );

            while (resultSet.next()){
                status=resultSet.getString(7);
                if(status.equals("выдана")){
                    onHand++;
                } else
                    fund++;

                }


            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
            data.add(Integer.toString(fund));
            data.add(Integer.toString(onHand));

        return data;
    }
    public void editReaderInfo(Reader reader) throws SQLException {


        String SQL="UPDATE libraryUser SET password=? WHERE login="+reader.getLogin();
        PreparedStatement  preparedStmt=connection.prepareStatement(SQL);
        preparedStmt.setString(1,reader.getPassword());

        preparedStmt.executeUpdate();
        System.out.println("Table User is update");

        SQL="UPDATE libraryReader SET passportID=?, nameReader=?, surname=?, patronymic=?, phone=?, birthDay=? WHERE login="+reader.getLogin();
        preparedStmt = connection.prepareStatement(SQL);
        System.out.println(reader.getSurname());
        preparedStmt.setString (1, reader.getPassportID());
        preparedStmt.setString(2,reader.getName());
        preparedStmt.setString(3, reader.getSurname());
        preparedStmt.setString(4, reader.getPatronymic());
        preparedStmt.setString(5, reader.getPhone());
        preparedStmt.setString(6, reader.getBirthDay());
        preparedStmt.executeUpdate();
        System.out.println("Table Reader is update");

    }

}