import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Revue extends Periodicite {

    Periodicite peo = new Periodicite();
    Scanner sc = new Scanner(System.in);

    public void insert(String description,String titre,float tarif_numero,String visuel ){
        try{
           ArrayList<Integer> idList = new ArrayList();

            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("insert into Revue (titre,description,tarif_numero,visuel,id_periodicite) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            requete.setString(1,titre);
            requete.setString(2,description);
            requete.setFloat(3,tarif_numero);
            requete.setString(4,visuel);


            peo.getIdList();
            System.out.println("Veuillez choisir l'id que vous voulez rajouter");
            int id = sc.nextInt();
            while(!peo.getIdList().contains(id)){
                System.out.println("Veuillez choisir un id valide");
                id = sc.nextInt();
            }
            requete.setInt(5,id);

            requete.executeUpdate();
        }catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }
    public void update(int id,String codePostal,String nom,String noRue,String pays,String prenom,String ville , String voie) {
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("update Client SET nom = ? , prenom = ?, no_rue = ? , voie = ? , code_postal = ? , ville = ? , pays = ?   WHERE id_client = ?");
            requete.setString(1, nom);
            requete.setString(2, prenom);
            requete.setString(3, noRue);
            requete.setString(4, voie);
            requete.setString(5, codePostal);
            requete.setString(6, ville);
            requete.setString(7, pays);
            requete.setInt(8, id);
            requete.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }
    public void delete (int id){
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("delete from Client where id_client=?");
            requete.setInt(1,id);
            requete.executeUpdate();

        }
        catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }
}
