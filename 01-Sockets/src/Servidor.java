import java.net.*;
import java.io.*;

public class Servidor {

    static final int PORT = 7777;
    static final String HOST = "localhost";

    ServerSocket srvSocket;
    Socket clientSocket;

    public void connecta() throws IOException {
        srvSocket = new ServerSocket(PORT);
        
        System.out.println("Servidor en marxa a localhost:7777");
        System.out.println("Esperant connexions a localhost:7777");
        
        clientSocket = srvSocket.accept();
        
        System.out.println("Client connectat: " + clientSocket.getInetAddress());
    }

    public void repDades() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String linia;
        
        while ((linia = br.readLine()) != null) {
            System.out.println("Rebut: " + linia);
        }
        
        br.close();
    }

    public void tanca() throws IOException {
        clientSocket.close();
        srvSocket.close();
        
        System.out.println("Servidor tancat.");
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        
        try {
            servidor.connecta();
            servidor.repDades();
            servidor.tanca();
        
        } catch (IOException e) {
            e.printStackTrace();
        
        }
    }
}
