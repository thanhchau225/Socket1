package Socket1;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage;
            String clientMessage;

            while (true) {
                if ((serverMessage = reader.readLine()) != null) {
                    System.out.println("Server: " + serverMessage);
                }

                System.out.print("Client: ");
                clientMessage = consoleReader.readLine();
                writer.println(clientMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
