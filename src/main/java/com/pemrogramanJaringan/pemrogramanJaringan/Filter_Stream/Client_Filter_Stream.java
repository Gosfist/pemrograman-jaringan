package com.pemrogramanJaringan.pemrogramanJaringan.Filter_Stream;

import java.io.PrintWriter;
import java.net.Socket;

public class Client_Filter_Stream {
    public static void main(String[] args) {
        try (Socket s = new Socket("192.168.1.17", 12345);
                PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {
            out.println("halo");
            System.out.println("sukses");
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
