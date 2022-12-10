package server;
import database.*;

import java.sql.SQLException;

public class Main {
    public static final int PORT_WORK = 9006;

    public static void main(String[] args) throws SQLException {
        Database database= Database.getInstance();
        database.createTable();
        Server server = new Server(PORT_WORK);
        new Thread(server).start();
//        server.stop();
    }
}
