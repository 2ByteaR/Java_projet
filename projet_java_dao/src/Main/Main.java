package Main;

import Connexion.Connexion;
import DAOFactory.DAOFactory;
import DAOFactory.Persistance;
import IDAO.AbonnementDAO;
import Métier.Abonnement;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Connexion connexion = new Connexion();
    private static Connection laConnexion = connexion.creeConnexion();
    private static ArrayList<Integer> idList = new ArrayList<>();
    private static Object object;
    private static Object Abonnement;
    private static Object Periodicite;

    public static void main(String[] args) {
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


    public static DAOFactory choixPersistance(){
        System.out.println("Veuillez choisir votre choix de langage de base de données MYSQL/ListeMémoire");
        String choix;
        do {
            Scanner sc = new Scanner(System.in);
            choix = sc.nextLine();
        }while(!(choix.equalsIgnoreCase("mysql") || choix.equalsIgnoreCase("listemémoire")));

        if (choix.equalsIgnoreCase("mysql")){
            return DAOFactory.getDAOFactory(Persistance.MYSQL);
        }else{
            return DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        }
    }


    public static AbonnementDAO creeAbonnement(){
        return choixPersistance().getAbonnementDAO();
    }

    public static void methodeAbonnement(){
        int choix = 0;
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1-rajouter | 2-supprimer | -3 mise à jour | 4- trier par une date de debut ? | 5- de Fin ? |");
        do {
            choix = sc.nextInt();
        }while(!(choix >= 1 && choix <= 6));

        switch (choix){
            case 1:
                creeAbonnement().create(verif_abonnement(false,true));
                break;
            case 2:
                creeAbonnement().delete(verif_abonnement(true,true));
                break;
            case 3:
                creeAbonnement().update((verif_abonnement(true,false)));
                break;
            case 4:
                creeAbonnement().getByDateDeb(dateValid());
                break;
            case 5:
                creeAbonnement().getByDateFin(dateValid());
                break;
        }


    }

    public static

    //Vérification de la conformité de la Date
    public static boolean verifFormatDate(String date){

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

    public static java.sql.Date dateValid(){
        Scanner sc = new Scanner(System.in);
        String date1;
        do {
            System.out.println("Veuillez saisir une date de la forme yyyy/mm/dd");
            date1 = sc.nextLine();
        }while (!verifFormatDate(date1));
        java.util.Date myDate = new java.util.Date(date1);
        return new Date(myDate.getTime());
    }


    public static void choixTable(){
        int choix;
        do {
            System.out.println("Veuillez choisir la table ou vous voulez travaillez 1-Abonnement| 2-Revue | 3-Periodicite | 4-Client");
            Scanner sc = new Scanner(System.in);
            choix = sc.nextInt();
        }while(!(choix >= 1 && choix <= 5));

        switch (choix) {
            case 1 -> methodeAbonnement();
            case 2 -> choixPersistance().getRevueDAO();
            case 3 -> choixPersistance().getPeriodicteDAO();
            case 4 -> choixPersistance().getClientDAO();
        };
    }


}


