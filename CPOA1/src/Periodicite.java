import java.sql.*;

public class Periodicite {

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
