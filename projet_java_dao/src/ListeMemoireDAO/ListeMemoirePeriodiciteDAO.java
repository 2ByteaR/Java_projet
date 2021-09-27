package ListeMemoireDAO;

import IDAO.PeriodiciteDAO;
import Métier.Periodicite;

import java.util.List;

public class ListeMemoirePeriodiciteDAO  implements PeriodiciteDAO<Periodicite> {

    private static ListeMemoirePeriodiciteDAO instance;

    private ListeMemoirePeriodiciteDAO() {}

    public static ListeMemoirePeriodiciteDAO getInstance() {
        if (instance == null) {
            instance = new ListeMemoirePeriodiciteDAO();
        }
        return instance;
    }
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
    public List<Periodicite> getByPeriodicite(Periodicite periodicite) {
        return null;
    }

    @Override
    public List<Periodicite> getBylibelle(String libelle) {
        return null;
    }
}
