package MySQLDAO;

import Connexion.Connexion;
import IDAO.AbonnementDAO;
import Métier.Abonnement;
import Métier.Client;

import java.sql.*;
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

    @Override
    public Abonnement getById(int id) {
        int id_revue = 0;
        int id_client;
        Date date_deb;
        Date date_fin;

        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete= laConnexion.prepareStatement("SELECT id_abonnement,id_client,id_revue,date_deb,date_fin, WHERE id_abonnement = ? FROM Abonnement");
            requete.setInt(1,id);
            ResultSet res = requete.executeQuery();

            while  (res.next())
            id_revue = res.getInt("id_revue");
            id_client =res.getInt("id_client");
            date_deb = res.getDate("date_deb");
            date_fin = res.getDate("date_fin");

            return new Abonnement(id,date_deb,date_fin,id_client,id_revue);

        }
        catch (SQLException sql){
            System.out.println("pb dans le select" + sql.getMessage());
        }
        return null;
    }

    @Override
    public boolean create(Abonnement objet) {
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("insert into Abonnement (date_debut,date_fin,id_revue,id_client) values (?,?,?,?)");
            requete.setInt(4, objet.getId_abonnement());
            requete.setInt(3,objet.getId_revue());
            requete.setDate(2, (java.sql.Date) objet.getDate_fin());
            requete.setDate(1, (java.sql.Date) objet.getDate_deb());
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

            requete.setDate(1, (java.sql.Date) objet.getDate_deb());
            requete.setDate(2,(java.sql.Date) objet.getDate_fin());
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
            requete.setInt(1,objet.getId_client());
            requete.executeUpdate();
            return true;
        }catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
        }
        return false;
    }

    @Override
    public List<Abonnement> getByDateDeb(Date date_deb) {
        List<Abonnement> resultat = new ArrayList<>();

        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("SELECT id_abonnement,id_client,id_revue,date_deb,date_fin, WHERE date_deb = ? FROM Abonnement");
            requete.setDate(1, (java.sql.Date) date_deb);
            ResultSet res = requete.executeQuery();
            while (res.next()){
                int id_revue = res.getInt("id_revue");
                int id_client = res.getInt("id_client");
                int id_abonnement = res.getInt("id_abonnement");
                Date date_fin = res.getDate("date_fin");

                Abonnement ab = new Abonnement(id_abonnement,date_deb,date_fin,id_client,id_revue);
                resultat.add(ab);
            }

            return resultat;
        }catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
            return null;
        }

    }


    @Override
    public List<Abonnement> getByDateFin(Date date_fin) {
        List<Abonnement> resultat = new ArrayList<>();

        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("SELECT id_abonnement,id_client,id_revue,date_deb,date_deb, WHERE date_fin = ? FROM Abonnement");
            requete.setDate(1, (java.sql.Date) date_fin);
            ResultSet res = requete.executeQuery();
            while (res.next()){
                int id_revue = res.getInt("id_revue");
                int id_client = res.getInt("id_client");
                int id_abonnement = res.getInt("id_abonnement");
                Date date_deb = res.getDate("date_deb");

                Abonnement ab = new Abonnement(id_abonnement,date_deb,date_fin,id_client,id_revue);
                resultat.add(ab);
            }

            return resultat;
        }catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
            return null;
        }
    }
}
