import java.sql.*;

public class Client {

    public void insert(String codePostal,String nom,String noRue,String pays,String prenom,String ville , String voie){
    try{
        Connexion connexion = new Connexion();
        Connection laConnexion = connexion.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("insert into Client (nom,prenom, no_rue,voie,code_postal,ville,pays) values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        requete.setString(1,nom);
        requete.setString(2,prenom);
        requete.setString(3,noRue);
        requete.setString(4,voie);
        requete.setString(5,codePostal);
        requete.setString(6,ville);
        requete.setString(7,pays);

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

