import java.sql.*;

public class Periodicite {

    public void update(String prenom) {
        try {
            Connexion connexion = new Connexion();
            Connection laConnexion = connexion.creeConnexion();

            PreparedStatement req = laConnexion.prepareStatement("insert into Periodicite (libelle) values(?)",
                            Statement.RETURN_GENERATED_KEYS);

            req.setString(1, prenom);
            int nbLignes = req.executeUpdate();
            ResultSet res = req.getGeneratedKeys();
            if (res.next()) {
                int cle = res.getInt(1);
            }
        } catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
    }
}
