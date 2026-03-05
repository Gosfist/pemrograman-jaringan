package com.pemrogramanJaringan.pemrogramanJaringan.Stream;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class CopyFile {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();

        JOptionPane.showMessageDialog(null, "Silakan Pilih File Yang Akan Di Copy");
        int sumberInput = fileChooser.showOpenDialog(null);

        if (sumberInput != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File sumberFile = fileChooser.getSelectedFile();

        JOptionPane.showMessageDialog(null, "Pilih Lokasi Tujuan Dan Nama File Yang Baru Di Copy");
        int tujuanInput = fileChooser.showSaveDialog(null);

        if (tujuanInput != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File tujuanFile = fileChooser.getSelectedFile();

        try (InputStream input = new FileInputStream(sumberFile);
                OutputStream output = new FileOutputStream(tujuanFile)) {

            int data;
            while ((data = input.read()) != -1) {
                output.write(data);
            }

            JOptionPane.showMessageDialog(null,
                    "File Berhasil Di Copy \nDari: " + sumberFile.getName() + "\nKe: " + tujuanFile.getName());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
