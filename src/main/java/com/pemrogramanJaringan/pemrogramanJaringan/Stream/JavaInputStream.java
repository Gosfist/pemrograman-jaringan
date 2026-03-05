package com.pemrogramanJaringan.pemrogramanJaringan.Stream;

import java.io.*;
import javax.swing.*;

public class JavaInputStream {
    public static void main(String args[]) {

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();

            InputStream fileInput = new FileInputStream(file);

            int data;
            while ((data = fileInput.read()) != -1) {
                System.out.write(data);
            }

            fileInput.close();

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan Pada Sistem!");
        }
    }
}