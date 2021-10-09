package Main;
import static Process.ProcessAdresse.*;
import Metier.Adresse;

public class Main {

    public static void main(String[] args) {
        Adresse pouet = new Adresse("abc","abc bd pl boul.","abc","abc","abc");
        System.out.println(normalizeVoie(pouet));
    }
}
