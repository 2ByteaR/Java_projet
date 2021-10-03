package Main;

import Connexion.Connexion;
import DAO.DAO;
import DAOFactory.DAOFactory;
import DAOFactory.Persistance;
import Métier.Abonnement;
import Métier.Client;
import Métier.Revue;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private static String message = "Voulez vous 1-inserer | 2-supprimer | 3-mettre a jour | 4-Recherche par un id | ";
    private static Scanner sc = new Scanner(System.in);
    private static int id;
    private String choix;
    private static ArrayList<Integer> idList = new ArrayList<>();


    public static void main(String[] args) {

        /*Abonnement ab = new Abonnement(5,null,null,0,0);
        DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
        daos.getRevueDAO().delete(ab);*/
        //System.out.println(daos.getRevueDAO().getById(3).toString());
        /*List<Periodicite> l1 = new ArrayList<>();


        l1 = (List<Periodicite>) daos.getPeriodicteDAO().getBylibelle("abon");

        for (Periodicite periode : l1)
        {
            System.out.println(periode.getLibelle()+periode.getId());
        }*/
        /*Periodicite periodicite = new Periodicite(7,"francis");
        DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);

        Re = (daos.getRevueDAO().getById(1));

        System.out.println(Re.toString());*/

        choixTable();
    }

    public static void choixTable(){
        System.out.println("Choississez la table Abonnement/Client/Periodicite/Revue");
        String choix = sc.nextLine();

        switch (choix.toLowerCase()){
            case "abonnement":
                choixAbonnement();
                break;
            case "client":
                choixClient();
                break;
            case "periodicte":
                System.out.println("pe");
                break;
            case "revue":
                System.out.println("re");
                break;
            default:
                System.out.println("Veuillez choisir une table valide");
        }
    }

    public static DAOFactory choixPersistance() {
        System.out.println("Veuillez choisir votre base de données Mysql ou ListeMemoire");
        String choix = null;
        do {
            choix = sc.nextLine();
        } while (!(choix.equalsIgnoreCase("mysql") || choix.equalsIgnoreCase("listememoire")));

        if (choix.equalsIgnoreCase("mysql")) {
            return DAOFactory.getDAOFactory(Persistance.MYSQL);
        } else {
            return DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        }
    }

    public static void choixClient(){
        int choix;
        do {
            System.out.println(message + "5- par un nom| 6- prénom | 7-no Rue | 8-Pays | 9-ville | 10-code | 11-voie");
            choix = sc.nextInt();
        }while(!(choix >= 0 && choix <= 11));
        DAOFactory daos = choixPersistance();

        switch (choix){
            case 1:
                daos.getClientDAO().create(creeClient(1,daos));
                break;
            case 2:
                daos.getClientDAO().delete(creeClient(2,daos));
                break;
            case 3:
                daos.getClientDAO().update(creeClient(3,daos));
                break;
            case 4:
                daos.getClientDAO().getById(sc.nextInt());
                break;
            case 5:
                daos.getClientDAO().getByNom(sc.nextLine());
                break;
            case 6:
                daos.getClientDAO().getByPrenom(sc.nextLine());
                break;
            case 7:
                daos.getClientDAO().getByNoRue(sc.nextLine());
                break;
            case 8:
                daos.getClientDAO().getByPays(sc.nextLine());
                break;
            case 9:
                daos.getClientDAO().getByVille(sc.nextLine());
                break;
            case 10:
                daos.getClientDAO().getByCodePostal(sc.nextLine());
                break;
            case 11:
                daos.getClientDAO().getByVoie(sc.nextLine());
                break;
        }

    }
    public static void choixAbonnement() {

        int choix;
        do {
            System.out.println(message + "5- par une date de début | 6- de fin ?");
            choix = sc.nextInt();
        } while (!(choix >= 0 && choix <= 6));
        DAOFactory daos = choixPersistance();
        switch (choix) {
            case 1:
                daos.getAbonnementDAO().create(creeAbonnement(1, daos));
                break;
            case 2:
                daos.getAbonnementDAO().delete(creeAbonnement(2, daos));
                break;
            case 3:
                daos.getAbonnementDAO().update(creeAbonnement(3, daos));
                break;
            case 4:
                id = sc.nextInt();
                System.out.println(daos.getAbonnementDAO().getById(id));
                break;
            case 5:
                System.out.println(daos.getAbonnementDAO().getByDateDeb(dateValid()));
                break;
            case 6:
                System.out.println(daos.getAbonnementDAO().getByDateFin(dateValid()));
                break;
        }

    }

    public static boolean verifFormatDate(String date) {

        String formatDate = "yyyy/MM/dd";
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatDate);
            format.setLenient(false);
            format.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Date dateValid() {
        String date1;
        do {
            System.out.println("Veuillez saisir une date de la forme yyyy/mm/dd");
            date1 = sc.nextLine();
        } while (!verifFormatDate(date1));
        return new Date(date1);
    }

    public static Abonnement creeAbonnement(int typeAction, DAOFactory daos) {
        Date dateDeb = null;
        Date dateFin = null;
        id = 0;
        int idClient = 0;
        int idRevue = 0;

        switch (typeAction) {
            case 1:
                System.out.println("Veuillez entrez un id de Revue | un id client | une date de début | une date de fin |");
                do {
                    System.out.println("Veuillez choisir un id Revue qui existe");
                    idRevue = sc.nextInt();
                } while (daos.getRevueDAO().getById(idRevue) == null);

                do {
                    System.out.println("Veuillez choisir un id Client qui existe");
                    idClient = sc.nextInt();
                } while (daos.getClientDAO().getById(idClient) == null);

                do {
                    System.out.println("Saississez la date de début");
                    dateDeb = dateValid();
                    System.out.println("Saississez la date de fin");
                    dateFin = dateValid();
                } while (dateDeb.after(dateFin));
                break;
            case 2:
                System.out.println("Choissisez l'id que vous voulez ²²supprimer");
                id = sc.nextInt();
                while (daos.getAbonnementDAO().getById(id) == null) {
                    System.out.println("Veuillez choisir une id qui existe");
                    id = sc.nextInt();
                }
                break;
            case 3:
                System.out.println("Choissisez l'id que vous voulez update");
                id = sc.nextInt();
                while (daos.getAbonnementDAO().getById(id) == null) {
                    System.out.println("Veuillez choisir une id qui existe");
                    id = sc.nextInt();
                }

                System.out.println("Veuillez entrez un id de Revue | un id client | une date de début | une date de fin |");
                do {
                    System.out.println("Veuillez choisir un id Revue qui existe");
                    idRevue = sc.nextInt();
                } while (daos.getRevueDAO().getById(idRevue) == null);

                do {
                    System.out.println("Veuillez choisir un id Client qui existe");
                    idClient = sc.nextInt();
                } while (daos.getClientDAO().getById(idClient) == null);

                do {
                    System.out.println("Saississez la date de début");
                    dateDeb = dateValid();
                    System.out.println("Saississez la date de fin");
                    dateFin = dateValid();
                } while (dateDeb.after(dateFin));

                break;

        }
        return new Abonnement(id, dateDeb, dateFin, idClient, idRevue);
    }

    public static Client creeClient(int typeAction, DAOFactory daos){
        int idClient = 0;
        String nom = null;
        String prenom = null;
        String noRue = null;
        String ville = null;
        String pays = null;
        String voie = null;
        String codePostal = null;

        switch (typeAction){
            case 3:
                do {
                    System.out.println("Saissisez l'id que vous voulez modifier");
                    id = sc.nextInt();
                }while (daos.getClientDAO().getById(id) == null);
                        case 1:
                    System.out.println("Veuillez entrer un nom | un prenom | un noRue | une ville | un pays | une voie | un code_postal ");
                    nom = sc.nextLine();
                    prenom = sc.nextLine();
                    noRue = sc.nextLine();
                    ville = sc.nextLine();
                    pays = sc.nextLine();
                    voie = sc.nextLine();
                    codePostal = sc.nextLine();
                    break;
                    case 2:
                        do {
                        System.out.println("Saissisez l'id que vous voulez supprimer");
                        id = sc.nextInt();
                        break;
                        }while (daos.getClientDAO().getById(id)==null);
        }

        return new Client(nom,prenom, noRue,ville, pays,voie,codePostal,idClient);
    }
}




