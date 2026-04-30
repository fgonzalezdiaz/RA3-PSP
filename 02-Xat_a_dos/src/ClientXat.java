import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientXat {

    private static final int PORT = 9999;
    private static final String HOST = "localhost";

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public static void main(String[] args) {
        ClientXat client = new ClientXat();

        try {
            client.connecta();

            FilLectorCX fil = new FilLectorCX(client.in);
            System.out.println("Missatge ('sortir' per tancar): Fil de lectura iniciat");
            fil.start();

            Scanner scanner = new Scanner(System.in);
            String linia;
            while(true){
                System.out.print("Missatge ('sortir' per tancar): ");
                linia = scanner.nextLine();
                client.enviarMissatge(linia);
                if(linia.equals("sortir")) break;
            }
            scanner.close();
            System.out.println("Tancant client...");
            client.tancarClient();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connecta() throws IOException {
        socket = new Socket(HOST, PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        System.out.println("Client conectat a localhost:9999");
        System.out.println("Flux de entrada i sortida creat.");
    }

    public void enviarMissatge(String msg) throws IOException {
        out.writeObject(msg);
    
        System.out.println("Enviant missatge: " + msg);
    }

    public void tancarClient() throws IOException {
        out.close();
        in.close();
        socket.close();
    
        System.out.println("Client tancat");
    }

}
