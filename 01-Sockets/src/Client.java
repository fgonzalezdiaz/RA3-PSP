import java.net.*;
import java.io.*;

public class Client {

    static final int PORT = 7777;
    static final String HOST = "localhost";

    Socket socket;
    PrintWriter out;

    public void conecta() throws IOException {
        socket = new Socket(HOST, PORT);
        out = new PrintWriter(socket.getOutputStream(), true);
        
        System.out.println("Connectat a servidor en localhost: " + PORT);
    }

    public void tanca() throws IOException {
        out.close();
        socket.close();
        
        System.out.println("Client tancat");
    }

    public void envia(String missatge) {
        out.println(missatge);
        
        System.out.println("Enviat al servidor: " + missatge);
    }

    public static void main(String[] args) {
        Client client = new Client();
        
        try {
            client.conecta();
            client.envia("Prova d'enviament 1");
            client.envia("Prova d'enviament 2");
            client.envia("Adeu!");
            
            System.out.println("Prem enter per tancar el client..");
            System.in.read();
            
            client.tanca();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
