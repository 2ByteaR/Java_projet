import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Abonnement {

    private Scanner sc = new Scanner(System.in);
    private ArrayList<Integer> idListClient = new ArrayList();
    private ArrayList<Integer> idListRevue = new ArrayList();

//Vérification de l'id du client si il existe
    public int verif_client(){
        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

//Affichage du nom, prenom, et ID avec un enregistrement des ID dans une arraylist
            PreparedStatement req = laConnexion.prepareStatement("SELECT id_client,nom,prenom FROM Client ");
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id = res.getInt("id_client");
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");

                System.out.println("ID : "+ id );
                System.out.println("nom : "+ nom);
                System.out.println("prenom : "+ prenom);
                idListClient.add(id);
            }
//Choix valide d'un id client
            System.out.println("Veuillez choisir l'id que vous voulez");
            int id1 = sc.nextInt();
            while(!idListClient.contains(id1)){
                System.out.println("Veuillez choisir un id valide");
                id1 = sc.nextInt();
            }
            return id1;
        }catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
            return -1;
        }
    }

//Vérification de l'id de Revue
    public int verif_Revue(){
        int id1 = 0;
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();


            PreparedStatement req = laConnexion.prepareStatement("SELECT id_revue,titre FROM Revue");
            ResultSet res = req.executeQuery();
            while (res.next()) {
                int id = res.getInt("id_revue");
                String titre = res.getString("titre");

                System.out.println("ID : " + id);
                System.out.println("titre : " + titre);
                idListRevue.add(id);
            }
                System.out.println("Veuillez choisir l'id que vous voulez");
                id1 = sc.nextInt();
                while(!idListRevue.contains(id1)){
                    System.out.println("Veuillez choisir un id valide");
                    id1 = sc.nextInt();
                }
                return id1;

        }catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
            return -1;
        }
    }

    //Vérification de la conformité de la Date
    public boolean verifFormatDate(String date){

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

    public java.sql.Date dateValid(){
        String date1;
        do {
            System.out.println("Veuillez saisir une date de la forme yyyy/mm/dd");
            date1 = sc.nextLine();
        }while (!verifFormatDate(date1));
        java.util.Date myDate = new java.util.Date(date1);
       return new Date(myDate.getTime());
    }

    public void insert(){
        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("insert into Abonnement (date_debut,date_fin,id_revue,id_client) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            requete.setDate(1,dateValid());
            requete.setDate(2,dateValid());
            int id = verif_client();
            requete.setInt(3,id1);
            int id = verif_client();
            requete.setInt(4,id);

            requete.executeUpdate();
        }catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }

    public void delete(int id_abo){
        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("delete from Abonnement where id_abonnement=?");
            requete.setInt(1,id_abo);
            requete.executeUpdate();

        }
        catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
            }
        }

    public void update(int id_abo){
        try{
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("update Abonnement SET date_debut = ?, date_fin = ?, id_revue = ?, id_client = ? WHERE id_abonnement = ?");

            requete.setDate(1,dateValid());
            requete.setDate(2,dateValid());
            int id1 = verif_Revue();
            requete.setInt(3,id1);
            int id = verif_client();
            requete.setInt(4,id);
            requete.setInt(5,id_abo);
            requete.executeUpdate();

        }
        catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }
}
