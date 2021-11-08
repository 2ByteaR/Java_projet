package bdd.mysqldao;

import bdd.connexion.Connexion;
import bdd.idao.ClientDAO;
import bdd.metier.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLClientDAO implements ClientDAO<Client> {

    private static MySQLClientDAO instance;

    private MySQLClientDAO() {}

    public static MySQLClientDAO getInstance() {
        if (instance == null) {
            instance = new MySQLClientDAO();
        }
        return instance;
    }

    @Override
    public List<Client> findAll() {
            ArrayList<Client> result = new ArrayList<>();
            try{
                Connexion connexion = new Connexion();
                Connection laConnexion = connexion.creeConnexion();

                PreparedStatement req = laConnexion.prepareStatement("SELECT * FROM Client");
                ResultSet res = req.executeQuery();
                while (res.next()){
                    int id_client = res.getInt("id_client");
                    String nom = res.getString("nom");
                    String prenom = res.getString("prenom");
                    String no_rue = res.getString("no_rue");
                    String ville = res.getString("ville");
                    String pays = res.getString("pays");
                    String codePostal =res.getString("code_postal");
                    String voie =res.getString("voie");

                    Client cl = new Client(nom,prenom,no_rue,ville,pays,voie,codePostal,id_client);
                    result.add(cl);
                }
                return result;

            }catch (SQLException e){
                System.out.println("Pb dans select " + e.getMessage());
                return null ;
            }

    }


    @Override
    public Client getById(int id) {
        String nom = null;
        String prenom = null;
        String noRue = null;
        String ville = null;
        String pays = null;
        String voie = null;
        String codePostal = null;
        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE id_client = ?  ");
            req.setInt(1,id);
            ResultSet res = req.executeQuery();
            Client cl = null;
            while (res.next()){
                 prenom =res.getString("prenom");
                 noRue=res.getString("no_rue");
                 voie =res.getString("voie");
                 nom =res.getString("nom");
                 ville = res.getString("ville");
                 pays = res.getString("pays");
                 codePostal =res.getString("code_postal");
                    cl = new Client(nom,prenom,noRue,ville,pays,voie,codePostal,id);
            }
            return cl;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public boolean create(Client objet) {
        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("insert into Client (nom,prenom, no_rue,voie,code_postal,ville,pays) values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            requete.setString(1,objet.getNom());
            requete.setString(2,objet.getPrenom());
            requete.setString(3,objet.getNoRue());
            requete.setString(4,objet.getVoie());
            requete.setString(5,objet.getCode_postal());
            requete.setString(6,objet.getVille());
            requete.setString(7,objet.getPays());

            requete.executeUpdate();
            return true;

        }catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Client objet) {
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("update Client SET nom = ? , prenom = ?, no_rue = ? , voie = ? , code_postal = ? , ville = ? , pays = ?   WHERE id_client = ?");
            requete.setString(1, objet.getNom());
            requete.setString(2, objet.getPrenom());
            requete.setString(3, objet.getNoRue());
            requete.setString(4, objet.getVoie());
            requete.setString(5, objet.getCode_postal());
            requete.setString(6, objet.getVille());
            requete.setString(7, objet.getPays());
            requete.setInt(8, objet.getId_client());

            requete.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return false;
        }


    }

    @Override
    public boolean delete(Client objet) {
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("delete from Client where id_client=?");
            requete.setInt(1,objet.getId_client());
            requete.executeUpdate();

            return true ;


        }
        catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
            return false;
        }

    }

}
