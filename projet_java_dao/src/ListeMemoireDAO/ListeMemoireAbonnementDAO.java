package ListeMemoireDAO;

import IDAO.AbonnementDAO;
import MySQLDAO.MySQLAbonnementDAO;
import Métier.Abonnement;

import java.util.Date;
import java.util.List;

public class ListeMemoireAbonnementDAO implements AbonnementDAO<Abonnement> {

    private static ListeMemoireAbonnementDAO instance;

    private ListeMemoireAbonnementDAO() {}

    public static ListeMemoireAbonnementDAO getInstance() {
        if (instance == null) {
            instance = new ListeMemoireAbonnementDAO();
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
