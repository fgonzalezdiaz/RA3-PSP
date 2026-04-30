import java.io.*;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.net.SocketException;

public class FilLectorCX extends Thread {

    private ObjectInputStream in;

    public FilLectorCX(ObjectInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String missatge;
            
            while (true) {
                missatge = (String) in.readObject();
                
                System.out.println("Rebut: " + missatge);

                if (missatge.equals("sortir")) {
                    break;
                }
            }
        } catch (EOFException | SocketException e) {
            System.out.println("El servidor ha tancat la connexio.");
        
        } catch (Exception e) {
            e.printStackTrace();
        
        }
        System.out.println("El servidor ha tancat la connexio.");
    }
}
