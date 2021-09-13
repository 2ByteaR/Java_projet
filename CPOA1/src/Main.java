import java.sql.*;

public class Main {

    public void uneRequete() {
        try {
            Connection laConnexion = creeConnection();
            Statement requete = laConnexion.createStatement();
            ResultSet res = requete.executeQuery("select no_etudiant, nom_etudiant from etudiant");

            while (res.next()) {
                int no = res.getInt(1);
                String nom = res.getString("nom_etudiant");
            }
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }
    }

    public static void main(String[] args) {

    }
}
