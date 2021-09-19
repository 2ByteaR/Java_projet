import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Revue extends Periodicite {

    private ArrayList<Integer> idList = new ArrayList();
    Periodicite peo = new Periodicite();
    Scanner sc = new Scanner(System.in);



    // Vérification que l'id de periodicité existe bien
    public int verif(){
        peo.getIdList();
        System.out.println("Veuillez choisir l'id que vous voulez");
        int id = sc.nextInt();
        while(!peo.getIdList().contains(id)){
            System.out.println("Veuillez choisir un id valide");
            id = sc.nextInt();
        }
        return id;
    }
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
            int id = verif();
            requete.setInt(5,id);

            requete.executeUpdate();
        }catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }
    public void update(int id,String description,String titre,float tarif_numero,String visuel) {
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("update Revue SET description = ? , tarif_numero = ?, titre = ? , visuel = ? , id_periodicite = ? WHERE id_revue = ?");
            requete.setString(1, description);
            requete.setFloat(2, tarif_numero);
            requete.setString(3, titre);
            requete.setString(4, visuel);

            int id_period = verif();
            requete.setInt(5,id_period);

            requete.setInt(6, id);
            requete.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }
    public void delete (int id){
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("delete from Revue where id_revue=?");
            requete.setInt(1,id);
            requete.executeUpdate();

        }
        catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }
}
