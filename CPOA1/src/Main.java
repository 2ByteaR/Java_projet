import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);



    public static void choixTable(String table, String ope){
        Scanner sc = new Scanner(System.in);
        switch (table.toLowerCase()){
            case "periodicite":
                Periodicite per = new Periodicite();
                switch (ope.toLowerCase()){
                    case "insert":
                        System.out.println("Saissisez un libeller");
                        String libelle = sc.nextLine();
                        per.insert(libelle);
                        break;
                    case "delete":
                        System.out.println("Saissisez un id");
                        int id = sc.nextInt();
                        per.delete(id);
                        break;
                    case "update":
                        System.out.println("Saissisez un id");
                        System.out.println("Saissisez un libeller");
                        id = sc.nextInt();
                        libelle = sc.nextLine();
                        per.update(id,libelle);
                        break;
                }
                break;
            case "client":
                Client cl = new Client();
                switch (ope.toLowerCase()){
                    case "insert":
                        System.out.println("Saissisez un nom/prenom/voie/codePostal/ville/noRue/pays");
                        String nom = sc.nextLine();
                        String prenom = sc.nextLine();
                        String voie = sc.nextLine();
                        String codePostal = sc.nextLine();
                        String ville = sc.nextLine();
                        String noRue = sc.nextLine();
                        String pays = sc.nextLine();
                        cl.insert(codePostal,nom,noRue,pays,prenom,ville,voie);
                        break;

                    case "delete":
                        System.out.println("Saissisez un id");
                        int id = sc.nextInt();
                        cl.delete(id);
                        break;

                    case "update":
                        System.out.println("Saissisez un id/nom/prenom/voie/codePostal/ville/noRue/pays");
                        id = sc.nextInt();
                         nom = sc.nextLine();
                         prenom = sc.nextLine();
                         voie = sc.nextLine();
                         codePostal = sc.nextLine();
                         ville = sc.nextLine();
                         noRue = sc.nextLine();
                         pays = sc.nextLine();
                        cl.update(id,codePostal,nom,noRue,pays,prenom,ville,voie);
                        break;
                }
                break;

            case "revue":
                Revue r1 = new Revue();
                switch (ope.toLowerCase()){
                    case "insert":
                        System.out.println("Saissisez une description/titre/tarif_numero/visuel");
                        String description = sc.nextLine();
                        String titre = sc.nextLine();
                        float tarif_numero = sc.nextFloat();
                        String visuel = sc.nextLine();


                        r1.insert(description,titre,tarif_numero,visuel);
                        break;
                    case "delete":
                        System.out.println("Saissisez un id");
                        int id = sc.nextInt();
                        r1.delete(id);
                        break;

                    case "update":
                        System.out.println("Saissisez un id/description/titre/tarif_numero/visuel");
                        id = sc.nextInt();
                        description = sc.nextLine();
                        titre = sc.nextLine();
                        tarif_numero = sc.nextFloat();
                        visuel = sc.nextLine();
                        r1.update(id,description,titre,tarif_numero,visuel);
                        break;
                }
                break;
            case "abonnement":
                Abonnement ab = new Abonnement();
                switch (ope.toLowerCase()){
                    case "insert":
                        System.out.println("Saissisez un abonnement");
                        ab.insert();
                        break;
                    case "update":
                        System.out.println("Saissisez un id");
                        int id = sc.nextInt();
                        ab.update(id);
                        break;
                    case "delete":
                        System.out.println("Saissisez un id");
                        id = sc.nextInt();
                        ab.delete(id);
                        break;
                }
                break;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choisissez votre table Periodicite/Client/Abonnement/Revue");
        String choixTable = sc.nextLine();
        System.out.println("Choisissez votre opération delete/insert/update");
        String choixOp = sc.nextLine();
        choixTable(choixTable,choixOp);
        }
    }
