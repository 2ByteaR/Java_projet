package MySQLDAO;

import Connexion.Connexion;
import IDAO.RevueDAO;
import Métier.Periodicite;
import Métier.Revue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLRevueDAO implements RevueDAO<Revue> {

    private static MySQLRevueDAO instance;

    private MySQLRevueDAO() {}

    public static MySQLRevueDAO getInstance() {
        if (instance == null) {
            instance = new MySQLRevueDAO();
        }
        return instance;
    }

    @Override
    public Revue getById(int id) {

        int id_periodicite = 0;
        String titre = null;
        String description = null;
        Float tarif_numeros = null;
        String visuel = null;
        Revue re = null;

        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT id_revue,titre,description,tarif_numero,visuel,id_periodicite FROM Revue WHERE id_revue = ? ");
            req.setInt(1,id);
            ResultSet res = req.executeQuery();
            while (res.next()){
                id_periodicite =res.getInt("id_periodicite");
                titre = res.getString("titre");
                description = res.getString("description");
                tarif_numeros = res.getFloat("tarif_numero");
                visuel = res.getString("visuel");

                re = new Revue(id,id_periodicite,description,tarif_numeros,titre,visuel);
            }
            
            return re;

        }catch (SQLException sqle){
            System.out.println("Pb dans le select"+sqle.getMessage());
            return null;
        }

    }

    @Override
    public boolean create(Revue objet) {

        return false;
    }

    @Override
    public boolean update(Revue objet) {
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("update Revue SET description = ? , tarif_numero = ?, titre = ? , visuel = ? , id_periodicite = ? WHERE id_revue = ?");
            requete.setString(1, objet.getDescription());
            requete.setFloat(2, objet.getTarif_numeros());
            requete.setString(3, objet.getTitre());
            requete.setString(4, objet.getVisuel());
            requete.setInt(5,objet.getId_periodicite());
            requete.setInt(6, objet.getId_revue());
            requete.executeUpdate();

            return true;
        } catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
            return false;
        }

    }

    @Override
    public boolean delete(Revue objet) {
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("delete from Revue where id_revue=?");
            requete.setInt(1,objet.getId_revue());
            requete.executeUpdate();

        }
        catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }

    @Override
    public List<Revue> getByDescription(String description) {
        List<Periodicite> result = new ArrayList<>();
        int id = 0;
        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT id_periodicite FROM Periodicite WHERE libelle = ? ");
            req.setString(1,libelle);
            ResultSet res = req.executeQuery();
            while (res.next()){
                id =res.getInt("id_periodicite");
                Periodicite p1 = new Periodicite(id,libelle);
                result.add(p1);
            }
            return result;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
        return null;
    }

    @Override
    public List<Revue> getByTitre(String titre) {
        return null;
    }

    @Override
    public List<Revue> getByVisuel(String visuel) {
        return null;
    }
}
