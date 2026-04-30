import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServidorXat {

    private static final int PORT = 9999;
    private static final String HOST = "localhost";
    private static final String MSG_SORTIR = "sortir";

    private ServerSocket serverSocket;

    public void iniciarServidor() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciat a localhost:9999");
    }

    public void pararServidor() throws IOException {
        serverSocket.close();
        System.out.println("Servidor aturat.");
    }

    public String getNom(ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        out.writeObject("Escriu el teu nom:");
        return (String) in.readObject();
    }

    public static void main(String[] args) {
        ServidorXat servidor = new ServidorXat();

        try {
            servidor.iniciarServidor();

            Socket clientSocket = servidor.serverSocket.accept();
            System.out.println("Client connectat: " + clientSocket.getInetAddress());

            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            String nom = servidor.getNom(in, out);
            System.out.println("Nom rebut: " + nom);

            FilServidorXat fil = new FilServidorXat(in, nom);
            System.out.println("Fil de xat creat.");

            fil.start();
            System.out.println("Fil de " + nom + " iniciat");

            Scanner scanner = new Scanner(System.in);
            String linia;
            do {
                System.out.print("Missatge ('sortir' per tancar): ");
                linia = scanner.nextLine();
                out.writeObject(linia);
                System.out.println(linia);
            } while (!linia.equals(MSG_SORTIR));

            fil.join();
            clientSocket.close();
            servidor.pararServidor();

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
