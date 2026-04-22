import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    private static final String HOST = "10.8.0.37";
    private static final int PORT = 12345;
    private static final int JUMLAH_CLIENT = 5;
    private static final String NAMA_FILE = "kirim file" + File.separator + "file.pptx";

    public static void main(String[] args) {
        File file = new File(NAMA_FILE);

        if (!file.exists()) {
            System.out.println("File tidak ditemukan: " + file.getAbsolutePath());
            return;
        }

        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(HOST, PORT));

            System.out.println("Server aktif di " + HOST + ":" + PORT);
            System.out.println("Menunggu " + JUMLAH_CLIENT + " client untuk menerima file...");

            for (int i = 1; i <= JUMLAH_CLIENT; i++) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client " + i + " terhubung: " + socket.getInetAddress());
                    kirimFile(socket, file);
                    System.out.println("File berhasil dikirim ke client " + i + ".");
                }
            }

            System.out.println("Semua client sudah menerima file.");
            System.out.println("Server selesai.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan di server: " + e.getMessage());
        }
    }

    private static void kirimFile(Socket socket, File file) throws Exception {
        try (
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
            kirimIsiFile(dataOutputStream, file);
        }
    }

    private static void kirimIsiFile(DataOutputStream dataOutputStream, File file) throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            dataOutputStream.writeUTF(file.getName());
            dataOutputStream.writeLong(file.length());

            byte[] buffer = new byte[4096];
            int jumlahByte;

            while ((jumlahByte = fileInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, jumlahByte);
            }

            dataOutputStream.flush();
        }
    }
}
