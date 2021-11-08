package bdd.mysqldao;

import bdd.connexion.Connexion;
import bdd.idao.AbonnementDAO;
import bdd.metier.Abonnement;

import java.sql.*;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

public class MySQLAbonnementDAO implements AbonnementDAO<Abonnement> {

    private static MySQLAbonnementDAO instance;

    private MySQLAbonnementDAO() {}

    public static MySQLAbonnementDAO getInstance() {
        if (instance == null) {
            instance = new MySQLAbonnementDAO();
        }
        return instance;
    }

    private static java.util.Date convertToDateUtil(java.sql.Date date) throws ParseException {
        java.util.Date myDate = new Date(date.getTime());
        SimpleDateFormat sm = new SimpleDateFormat("MM/dd/yyyy",Locale.FRANCE);
        String strDate = sm.format(myDate);
        java.util.Date dt = sm.parse(strDate);
        return dt;
    }

    @Override
    public List<Abonnement> findAll(){

        List<Abonnement> result = new ArrayList<>();
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Abonnement");
            ResultSet res = requete.executeQuery();

            while (res.next()) {
                int id = res.getInt("id_abonnement");
                int id_revue = res.getInt("id_revue");
                int id_client = res.getInt("id_client");
                java.util.Date date_deb = convertToDateUtil(res.getDate("date_debut"));
                java.util.Date date_fin = convertToDateUtil(res.getDate("date_fin"));



                Abonnement ab = new Abonnement(id, date_deb, date_fin, id_client, id_revue);

                result.add(ab);

            }
            return result;
        }
        catch (SQLException | ParseException sql){
            System.out.println("pb dans le select " + sql.getMessage());
        }
        return null;
    }

    @Override
    public Abonnement getById(int id) {
        int id_revue = 0;
        int id_client;
        Date date_deb;
        Date date_fin;

        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("SELECT id_abonnement,id_client,id_revue,date_debut,date_fin FROM Abonnement WHERE id_abonnement = ? ");
            requete.setInt(1, id);
            ResultSet res = requete.executeQuery();

            while (res.next()) {
                id_revue = res.getInt("id_revue");
                id_client = res.getInt("id_client");
                date_deb = res.getDate("date_debut");
                date_fin = res.getDate("date_fin");

                return new Abonnement(id, date_deb, date_fin, id_client, id_revue);

            }
        }
        catch (SQLException sql){
            System.out.println("pb dans le select " + sql.getMessage());
        }
        return null;
    }

    @Override
    public boolean create(Abonnement objet) {
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("insert into Abonnement (date_debut,date_fin,id_revue,id_client) values (?,?,?,?)");
            requete.setInt(4, objet.getId_client());
            requete.setInt(3,objet.getId_revue());

            long timeInMilliSeconds = objet.getDate_deb().getTime();
            java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
            requete.setDate(2, date1);

            timeInMilliSeconds = objet.getDate_fin().getTime();
            java.sql.Date date2 = new java.sql.Date(timeInMilliSeconds);
            requete.setDate(1, date2);

            requete.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("pb dans le select" + e.getMessage());
            return false;
        }
    }

        @Override
    public boolean update(Abonnement objet) {
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("update Abonnement SET date_debut = ?, date_fin = ?, id_revue = ?, id_client = ? WHERE id_abonnement = ?");

            long timeInMilliSeconds = objet.getDate_deb().getTime();
            java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
            requete.setDate(2, date1);

            timeInMilliSeconds = objet.getDate_fin().getTime();
            java.sql.Date date2 = new java.sql.Date(timeInMilliSeconds);
            requete.setDate(1, date2);

            requete.setInt(3,objet.getId_revue());
            requete.setInt(4,objet.getId_client());
            requete.setInt(5,objet.getId_abonnement());
            requete.executeUpdate();

            return true;
        } catch (SQLException e) {
                System.out.println("pb dans le select" + e.getMessage());
                return false;
            }
    }

    @Override
    public boolean delete(Abonnement objet) {
        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("delete from Abonnement where id_abonnement=?");
            requete.setInt(1,objet.getId_abonnement());
            requete.executeUpdate();
            return true;
        }catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
        }
        return false;
    }

}
