import java.io.*;

public class FilServidorXat extends Thread {

    private static final String MSG_SORTIR = "sortir";

    private ObjectInputStream in;
    private String nom;

    public FilServidorXat(ObjectInputStream in, String nom) {
        this.in = in;
        this.nom = nom;
    }

    @Override
    public void run() {
        try {
            String missatge;
            do {
                missatge = (String) in.readObject();
                System.out.println("Rebut: " + missatge);
            } while (!missatge.equals(MSG_SORTIR));

            System.out.println("Fil de xat finalitzat.");

        } catch (EOFException e) {
            System.out.println("El client ha tancat la connexio.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
