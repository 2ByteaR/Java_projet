package MySQLDAO;

import Connexion.Connexion;
import IDAO.PeriodiciteDAO;
import Métier.Periodicite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLPeriodiciteDAO implements PeriodiciteDAO<Periodicite> {

    private static MySQLPeriodiciteDAO instance;

    private MySQLPeriodiciteDAO() {}

    public static MySQLPeriodiciteDAO getInstance() {
        if (instance == null) {
            instance = new MySQLPeriodiciteDAO();
        }
        return instance;
    }



    @Override
    public boolean create(Periodicite objet) {
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("Insert into Periodicite (libelle) value (?)");
            req.setString(1, objet.getLibelle());
            req.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Periodicite objet) {
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("update Periodicite SET libelle = ? WHERE id_periodicite = ?");
            req.setString(1, objet.getLibelle());
            req.setInt(2,objet.getId());
            req.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Periodicite objet) {
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("delete from Periodicite where id_periodicite=?");
            req.setInt(1,objet.getId());
            req.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Periodicite> getBylibelle(String libelle) {
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
    }

    @Override
    public Periodicite getById(int id) {
        String libelle = null;
        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT libelle FROM Periodicite WHERE id_periodicite = ? ");
            req.setInt(1,id);
            ResultSet res = req.executeQuery();
            while (res.next()){
                libelle =res.getString("libelle");
            }
            return new Periodicite(id,libelle);

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }
}
