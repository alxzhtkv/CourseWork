package database;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionDB extends Configurations {
    ArrayList<String[]> masResult;
    public static ConnectionDB instance;
    private Statement statement;
    private ResultSet resultSet;
    public static Connection dbConnection;
    public ConnectionDB()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        statement = dbConnection.createStatement();
        System.out.println("DB Connected");
    }
    public static synchronized ConnectionDB getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }
    public ArrayList<String[]> getArrayResult(String str) {
        masResult = new ArrayList<String[]>();
        try {
            resultSet = statement.executeQuery(str);
            int count = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                String[] arrayString = new String[count];
                for (int i = 1;  i <= count; i++)
                    arrayString[i - 1] = resultSet.getString(i);

                masResult.add(arrayString);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return masResult;
    }
}