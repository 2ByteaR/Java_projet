package MySQLDAO;

import IDAO.AbonnementDAO;
import Métier.Abonnement;
import java.util.*;

public class MySQLAbonnementDAO implements AbonnementDAO<Abonnement> {

    private static MySQLAbonnementDAO instance;

    private MySQLAbonnementDAO() {}

    public static MySQLAbonnementDAO getInstance() {
        if (instance == null) {
            instance = new MySQLAbonnementDAO();
        }
        return instance;
    }

    @Override
    public Abonnement getById(int id) {
        return null;
    }

    @Override
    public boolean create(Abonnement objet) {
        return false;
    }

    @Override
    public boolean update(Abonnement objet) {
        return false;
    }

    @Override
    public boolean delete(Abonnement objet) {
        return false;
    }

    @Override
    public List<Abonnement> getByAbonnement(Abonnement abonnement) {
        return null;
    }

    @Override
    public List<Abonnement> getByDateDeb(Date date_deb) {
        return null;
    }

    @Override
    public List<Abonnement> getByDateFin(Date date_fin) {
        return null;
    }
}
