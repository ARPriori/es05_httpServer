package it.priori;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyThread extends Thread {
    Socket s;
    static int id = 0;

    public MyThread(Socket socket) {
        s = socket;
    }

    public void run() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            String first_line = in.readLine();
            System.out.println(first_line);
            String header;
            do {
                header = in.readLine();
                System.out.println(header);
            } while (!header.isEmpty());
            
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}