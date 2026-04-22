import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class client {
    private static final String SERVER_HOST = "10.8.0.37";
    private static final String CLIENT_HOST = "10.8.0.40";
    private static final int PORT = 12345;
    private static final String FOLDER_OUTPUT = "kirim file";
    private static final int DELAY_RECONNECT_MS = 1000;

    public static void main(String[] args) {
        System.out.println("Client aktif. Menunggu file...");

        while (true) {
            try (Socket socket = new Socket()) {
                socket.bind(new InetSocketAddress(CLIENT_HOST, 0));
                socket.connect(new InetSocketAddress(SERVER_HOST, PORT));

                try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
                    String namaFile = dataInputStream.readUTF();
                    long ukuranFile = dataInputStream.readLong();
                    File folderOutput = new File(FOLDER_OUTPUT);
                    if (!folderOutput.exists()) {
                        folderOutput.mkdirs();
                    }

                    File fileHasil = new File(folderOutput, "hasil_" + namaFile);
                    String namaFileHasil = fileHasil.getPath();

                    try (FileOutputStream fileOutputStream = new FileOutputStream(fileHasil)) {
                        byte[] buffer = new byte[4096];
                        long sisaByte = ukuranFile;

                        while (sisaByte > 0) {
                            int jumlahByte = dataInputStream.read(
                                    buffer,
                                    0,
                                    (int) Math.min(buffer.length, sisaByte));

                            if (jumlahByte == -1) {
                                break;
                            }

                            fileOutputStream.write(buffer, 0, jumlahByte);
                            sisaByte -= jumlahByte;
                        }
                    }

                    System.out.println("File berhasil diterima dan disimpan sebagai " + namaFileHasil);
                    System.out.println("Client selesai.");
                    break;
                }
            } catch (ConnectException e) {
                tungguServer();
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan di client: " + e.getMessage());
                break;
            }
        }
    }

    private static void tungguServer() {
        try {
            Thread.sleep(DELAY_RECONNECT_MS);
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }
}
