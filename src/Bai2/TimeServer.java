package Bai2;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

public class TimeServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String clientMessage;
                while ((clientMessage = reader.readLine()) != null) {
                    if (clientMessage.equals("time")) {
                        // Trả về thời gian hiện tại cho client
                        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
                        writer.println(time);
                    }
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
