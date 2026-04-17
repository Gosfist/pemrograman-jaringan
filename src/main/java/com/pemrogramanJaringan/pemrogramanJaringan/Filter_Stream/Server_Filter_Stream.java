package com.pemrogramanJaringan.pemrogramanJaringan.Filter_Stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_Filter_Stream {
    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(12345);
                Socket server = s.accept();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(server.getInputStream()))) {

            String pesan = in.readLine();
            System.out.println("pesan = " + pesan);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
