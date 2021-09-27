package MySQLDAO;

import DAO.DAO;
import IDAO.PeriodiciteDAO;
import Métier.Periodicite;

import java.util.List;

public class MySQLPeriodiciteDAO implements PeriodiciteDAO {

    private static MySQLPeriodiciteDAO instance;

    private MySQLPeriodiciteDAO() {}

    public static MySQLPeriodiciteDAO getInstance() {
        if (instance == null) {
            instance = new MySQLPeriodiciteDAO();
        }
        return instance;
    }

    @Override
    public Periodicite getById(int id) {
        return null;
    }

    @Override
    public boolean create(Object objet) {
        return false;
    }

    @Override
    public boolean update(Object objet) {
        return false;
    }

    @Override
    public boolean delete(Object objet) {
        return false;
    }

    @Override
    public List getByPeriodicite(Object o) {
        return null;
    }

    @Override
    public List getBylibelle(String libelle) {
        return null;
    }
}
