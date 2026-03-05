package com.pemrogramanJaringan.pemrogramanJaringan.Stream;

import java.io.*;
import javax.swing.*;

public class CountHuruf {
    public static void main(String args[]) {

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();

            FileInputStream openFile = new FileInputStream(file);

            int data;
            int a = 0, i = 0, u = 0, e = 0, o = 0;

            // hitung jumlah huruf a i u e o
            while ((data = openFile.read()) != -1) {
                char huruf = Character.toLowerCase((char) data);
                if (huruf == 'a')
                    a++;
                else if (huruf == 'i')
                    i++;
                else if (huruf == 'u')
                    u++;
                else if (huruf == 'e')
                    e++;
                else if (huruf == 'o')
                    o++;
            }

            // tutup
            openFile.close();

            // tampilkan hasil huruf vokal yang sudah di hitung
            JOptionPane.showMessageDialog(null,
                    "Jumlah Huruf Vokal:\n" +
                            "a = " + a + "\n" +
                            "i = " + i + "\n" +
                            "u = " + u + "\n" +
                            "e = " + e + "\n" +
                            "o = " + o);

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan Pada Sistem!");
        }
    }
}