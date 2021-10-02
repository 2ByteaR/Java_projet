package MySQLDAO;

import Connexion.Connexion;
import IDAO.ClientDAO;
import Métier.Client;

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
            while (res.next()){
                 prenom =res.getString("prenom");
                 noRue=res.getString("no_rue");
                 voie =res.getString("voie");
                 nom =res.getString("nom");
                 ville = res.getString("ville");
                 pays = res.getString("pays");
                 codePostal =res.getString("code_postal");
            }



            return new Client(nom,prenom,noRue,ville,pays,voie,codePostal,id);

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


    @Override
    public List<Client> getByCodePostal(String code_postal) {
        List<Client> result = new ArrayList<>();

        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE code_postal = ? ");
            req.setString(1,code_postal);
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id =res.getInt("id_client");
                String prenom =res.getString("prenom");
                String noRue=res.getString("no_rue");
                String voie =res.getString("voie");
                String nom =res.getString("nom");
                String ville = res.getString("ville");
                String pays = res.getString("pays");

                Client cl1 = new Client(nom,prenom,noRue,ville,pays,voie,code_postal,id);
                result.add(cl1);
            }
            return result;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Client> getByNom(String nom) {
        List<Client> result = new ArrayList<>();

        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE nom = ? ");
            req.setString(1,nom);
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id =res.getInt("id_client");
                String prenom =res.getString("prenom");
                String noRue=res.getString("no_rue");
                String voie =res.getString("voie");
                String codePostal =res.getString("code_postal");
                String ville = res.getString("ville");
                String pays = res.getString("pays");

                Client cl1 = new Client(nom,prenom,noRue,ville,pays,voie,codePostal,id);
                result.add(cl1);
            }
            return result;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Client> getByNoRue(String no_rue) {
        List<Client> result = new ArrayList<>();

        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE no_rue = ? ");
            req.setString(1,no_rue);
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id =res.getInt("id_client");
                String prenom =res.getString("prenom");
                String nom=res.getString("nom");
                String voie =res.getString("voie");
                String codePostal =res.getString("code_postal");
                String ville = res.getString("ville");
                String pays = res.getString("pays");

                Client cl1 = new Client(nom,prenom,no_rue,ville,pays,voie,codePostal,id);
                result.add(cl1);
            }
            return result;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Client> getByPrenom(String prenom) {
        List<Client> result = new ArrayList<>();
        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE prenom = ? ");
            req.setString(1,prenom);
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id =res.getInt("id_client");
                String noRue =res.getString("no_rue");
                String nom=res.getString("nom");
                String voie =res.getString("voie");
                String codePostal =res.getString("code_postal");
                String ville = res.getString("ville");
                String pays = res.getString("pays");

                Client cl1 = new Client(nom,prenom,noRue,ville,pays,voie,codePostal,id);
                result.add(cl1);
            }
            return result;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Client> getByVille(String ville) {
        List<Client> result = new ArrayList<>();
        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE ville = ? ");
            req.setString(1,ville);
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id =res.getInt("id_client");
                String noRue =res.getString("no_rue");
                String nom=res.getString("nom");
                String voie =res.getString("voie");
                String codePostal =res.getString("code_postal");
                String prenom = res.getString("prenom");
                String pays = res.getString("pays");

                Client cl1 = new Client(nom,prenom,noRue,ville,pays,voie,codePostal,id);
                result.add(cl1);
            }
            return result;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Client> getByVoie(String voie) {
        List<Client> result = new ArrayList<>();
        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE voie = ? ");
            req.setString(1,voie);
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id =res.getInt("id_client");
                String noRue =res.getString("no_rue");
                String nom=res.getString("nom");
                String prenom =res.getString("prenom");
                String codePostal =res.getString("code_postal");
                String ville = res.getString("ville");
                String pays = res.getString("pays");

                Client cl1 = new Client(nom,prenom,noRue,ville,pays,voie,codePostal,id);
                result.add(cl1);
            }
            return result;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Client> getByPays(String pays) {
        List<Client> result = new ArrayList<>();
        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE pays = ? ");
            req.setString(1,pays);
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id =res.getInt("id_client");
                String noRue =res.getString("no_rue");
                String nom=res.getString("nom");
                String voie =res.getString("voie");
                String codePostal =res.getString("code_postal");
                String ville = res.getString("ville");
                String prenom = res.getString("pays");

                Client cl1 = new Client(nom,prenom,noRue,ville,pays,voie,codePostal,id);
                result.add(cl1);
            }
            return result;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }
}
