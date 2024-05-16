package Bai2;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ClockClient extends JFrame {
    private JLabel timeLabel;

    public ClockClient() {
        setTitle("Clock");
        setSize(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timeLabel = new JLabel();
        add(timeLabel);

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();
    }

    private void updateTime() {
        try {
            Socket socket = new Socket("localhost", 12345);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("time");
            String serverTime = reader.readLine();
            timeLabel.setText("Time: " + serverTime);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClockClient clockClient = new ClockClient();
                clockClient.setVisible(true);
            }
        });
    }
}
