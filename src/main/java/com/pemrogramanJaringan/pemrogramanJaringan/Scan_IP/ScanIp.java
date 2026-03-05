package com.pemrogramanJaringan.pemrogramanJaringan.Scan_IP;

import java.util.ArrayList;
import java.util.Scanner;
import java.net.InetAddress;
import java.util.List;

public class ScanIp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> hasil = new ArrayList<>();

        System.out.print("Masukan IP Awal: ");
        String ipAwal = input.nextLine();

        System.out.print("Masukan IP Akhir: ");
        String ipAkhir = input.nextLine();

        String DataAwal[] = ipAwal.split("\\.");
        String DataAkhir[] = ipAkhir.split("\\.");

        int awal = Integer.parseInt(DataAwal[3]);
        int akhir = Integer.parseInt(DataAkhir[3]);

        System.out.println();
        long start = System.nanoTime();
        for (int i = awal; i <= akhir; i++) {
            String ip = DataAwal[0] + "." + DataAwal[1] + "." + DataAwal[2] + "." + i;
            System.out.print("\rProses Scann IP Address: " + ip);

            try {
                InetAddress inet = InetAddress.getByName(ip);
                boolean status = inet.isReachable(3000);
                if (status)
                    hasil.add(ip);
            } catch (Exception e) {
                System.out.println("Terdapat ERRROR " + e);
            }
        }
        long end = System.nanoTime();

        // hapus baris progress
        System.out.print("\r" + " ".repeat(60) + "\r");
        System.out.println("========================");
        System.out.println("Hasil Scanner Ip Address");
        System.out.println("========================");
        for (int i = 0; i < hasil.size(); i++) {
            System.out.println(hasil.get(i));
        }

        System.out.println("\nJumlah Host Aktif: " + hasil.size() +
                "/" + akhir);
        double seconds = (end - start) / 1_000_000_000.0;
        System.err.println("Waktu Scan: " + seconds);

    }
}
