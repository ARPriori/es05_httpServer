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

            String first_line = in.readLine(); //esempio: GET / HTTP/1.1
            System.out.println(first_line);
            String header;
            do {
                header = in.readLine();
                System.out.println(header);
            } while (!header.isEmpty());

            //parts = [0] -> metodo, [1] -> path, [2] -> versione
            String[] parts = first_line.split(" ");

            String content_type = "text/html; charset=UTF-8";
            String server_name = "APserver";
            String content = "HI - I'M AN HTML PAGE";
            int content_lng = content.length();

            out.println(parts[2] + " 200 OK");
            out.println("Content-Type:" + content_type);
            out.println("Server:" + server_name);
            out.println("Content-Length:" + content_lng);
            out.println("");
            out.println(content);
            
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}