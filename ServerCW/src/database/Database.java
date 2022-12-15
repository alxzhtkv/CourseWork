package database;
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
                    + "password VARCHAR (30) NOT NULL UNIQUE)";
            statement = connection.createStatement();
            statement.executeUpdate(SQL);

            SQL ="CREATE TABLE IF NOT EXISTS LibraryAdmin"
                    +"(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    +"login INTEGER NOT NULL UNIQUE,"
                    + "password VARCHAR (30) NOT NULL UNIQUE)";
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
                    + "bookID INTEGER NOT NULL UNIQUE,"
                    + "FOREIGN KEY (bookID) REFERENCES LibraryBooks (IDbook))";
            statement = connection.createStatement();
            statement.executeUpdate(SQL);

            SQL ="CREATE TABLE IF NOT EXISTS LibraryOrders"
                    +"(id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    +"readerID INTEGER,"
                    + "bookID INTEGER NOT NULL UNIQUE,"
                    + "orderID INTEGER NOT NULL UNIQUE,"
                    + "FOREIGN KEY (bookID) REFERENCES LibraryBooks (IDbook))";
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
        String SQL = "INSERT INTO LibraryBooks(IDbook,title,author,publisher,genre,yearBook, countBooks) "
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
                System.out.println("ты милашка!");
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
                System.out.println(book.getTitle());
                booksTemp.add(book);

            }
            System.out.println("Сработало, малышка!!!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booksTemp;
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

    public void insertOrder(Order order){
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
            System.out.println("Сработало, малышка!!!");

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
            System.out.println("Сработало, малышка!!!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `LibraryUser`  WHERE (login ="+id+")" );
            while (resultSet.next()){
                password=resultSet.getString(3);
               reader.setPassword(password);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

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
                System.out.println("ты милашка!");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean deleteBookByID(String id){
        boolean flag=false;
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM LibraryBooks WHERE (IDbook ="+ id +")" );
//            +"AND (password ="+ user.getPassword().toString() + ")"
            if (resultSet.next()){
                flag=true;
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
                    String count =resultSet.getString(7);
                    if(value.equals(ID)==true || value.equals(title)==true  ||value.equals(author)==true  ||value.equals(publisher)==true  ||value.equals(genre) ==true || value.equals(year)==true  || value.equals(year)==true ){

                        Book book = new Book(ID,title,publisher,genre,year,count,author);
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

//    public void authorsSort(){
//        String SQL="ALTER TABLE LibraryBooks ORDER BY title";
//
//        try {
//            statement.executeUpdate(SQL);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
}