package client;
import java.io.IOException;


public class Main {

    public static void main(String[] arg)
         throws IOException {
        Connect.client = new Client("127.0.0.2", "2525");
        System.out.println("Connected");
    }

}