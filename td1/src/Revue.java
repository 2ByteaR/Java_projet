import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Revue {

    private ArrayList<Integer> idList = new ArrayList();
    Scanner sc = new Scanner(System.in);



    // Vérification que l'id de periodicité existe bien
    public int verif() {
        int id1 = 0;
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();


            PreparedStatement req = laConnexion.prepareStatement("SELECT id_periodicite,libelle FROM Periodicite ");
            ResultSet res = req.executeQuery();
            while (res.next()) {
                int id = res.getInt("id_periodicite");
                String libelle = res.getString("libelle");

                System.out.println("ID : " + id);
                System.out.println("Libelle : " + libelle);
                idList.add(id);
            }
                System.out.println("Veuillez choisir l'id que vous voulez");
                id1 = sc.nextInt();
                while (!idList.contains(id1)) {
                    System.out.println("Veuillez choisir un id valide");
                    id1 = sc.nextInt();

                return id1;
            }

        }catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
            return -1;
        }
        return id1;
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
