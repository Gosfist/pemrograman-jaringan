package com.PemrogramanJaringan.Pemrograman.Jaringan.Deteksi_Ip;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class DeteksiIp {

    // Method untuk menentukan kelas IP
    public static String getIPClass(String ip) {
        String[] parts = ip.split("\\.");
        int firstOctet = Integer.parseInt(parts[0]);

        if (firstOctet >= 1 && firstOctet <= 126) {
            return "Kelas A";
        } else if (firstOctet >= 128 && firstOctet <= 191) {
            return "Kelas B";
        } else if (firstOctet >= 192 && firstOctet <= 223) {
            return "Kelas C";
        } else if (firstOctet >= 224 && firstOctet <= 239) {
            return "Kelas D (Multicast)";
        } else if (firstOctet >= 240 && firstOctet <= 255) {
            return "Kelas E (Eksperimental)";
        } else {
            return "Tidak diketahui";
        }
    }

    // Method untuk mengubah IP ke bentuk biner
    public static String ipToBinary(String ip) {
        String[] parts = ip.split("\\.");
        StringBuilder binaryIP = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            int octet = Integer.parseInt(parts[i]);
            String binaryOctet = String.format("%8s", Integer.toBinaryString(octet))
                    .replace(' ', '0');
            binaryIP.append(binaryOctet);

            if (i < parts.length - 1) {
                binaryIP.append(".");
            }
        }

        return binaryIP.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan Nama Domain: ");
        String url = input.nextLine();

        try {
            // menggambil nama domain
            InetAddress inetAddress = InetAddress.getByName(url);
            String ip = inetAddress.getHostAddress();

            System.out.println("\n=== HASIL DETEKSI ===");
            System.out.println("IP Address      : " + ip);
            System.out.println("Kelas IP        : " + getIPClass(ip));
            System.out.println("Bentuk Biner IP : " + ipToBinary(ip));

        } catch (UnknownHostException e) {
            System.out.println("URL tidak valid atau tidak ditemukan.");
        }

        input.close();
    }
}