package MySQLDAO;

import DAO.DAO;
import IDAO.PeriodiciteDAO;
import Métier.Periodicite;

public class MySQLPeriodiciteDAO implements PeriodiciteDAO {

    @Override
    public Periodicite getById(int id) {
        return null;
    }

    @Override
    public boolean create(Periodicite objet) {
        return false;
    }

    @Override
    public boolean update(Periodicite objet) {
        return false;
    }

    @Override
    public boolean delete(Periodicite objet) {
        return false;
    }

    @Override
    public String getBylibelle(String libelle) {
        return null;
    }
}
