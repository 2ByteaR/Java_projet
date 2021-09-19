import java.sql.*;
import java.util.ArrayList;

public class Periodicite {
    private ArrayList<Integer> idList = new ArrayList();

    //Permet d'avoir le libeller à partir d'un id
    public String getLibeller (int id){
        try {
            String lib = null;
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("SELECT libelle FROM Periodicite WHERE id_periodicite = ? ");
            req.setInt(1,id);
            ResultSet res = req.executeQuery();
            if (res.next()) {
                lib = res.getString("libelle");
            }
            return lib ;
        }catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
            return "fail";
        }
    }

    //Permet d'afficher la liste des ID et d'afficher leur ID ainsi que le libeller correspondant
    public ArrayList<Integer> getIdList() {
       try {
           Connexion connexion = new Connexion();
           Connection laConnexion = connexion.creeConnexion();


           PreparedStatement req = laConnexion.prepareStatement("SELECT id_periodicite,libelle FROM Periodicite ");
           ResultSet res = req.executeQuery();
           while (res.next()){
               int id = res.getInt("id_periodicite");
               String libelle = res.getString("libelle");

               System.out.println("ID : "+ id );
               System.out.println("Libelle : "+ libelle);
               idList.add(id);


           }
           return idList;
       }catch (SQLException sqle) {
           System.out.println("Pb dans select " + sqle.getMessage());
       }

        return null;
    }

    //Insert le libeller avec ID crée automatiquement
    public void insert (String libelle) {
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("insert into Periodicite (libelle) values(?)",
                            Statement.RETURN_GENERATED_KEYS);

            req.setString(1, libelle);
            ResultSet res = req.getGeneratedKeys();
            if (res.next()) {

                int cle = res.getInt(1);
            }
        } catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }

    //Effacement a partir de l'ID
    public void delete(int id){
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("delete from Periodicite where id_periodicite=?");
            requete.setInt(1,id);
            requete.executeUpdate();

        }
        catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
        }

    }

    //Met a jour un ligne avec l'id  correspondant
    public void update(int id,String lib){
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement requete = laConnexion.prepareStatement("update Periodicite SET libelle = ? WHERE id_periodicite = ?");
            requete.setString(1,lib);
            requete.setInt(2,id);
            requete.executeUpdate();

        }
        catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
        }

    }
}
