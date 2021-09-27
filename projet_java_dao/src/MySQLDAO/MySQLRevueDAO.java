package MySQLDAO;

import IDAO.RevueDAO;
import Métier.Revue;

import java.util.List;

public class MySQLRevueDAO implements RevueDAO<Revue> {

    private static MySQLRevueDAO instance;

    private MySQLRevueDAO() {}

    public static MySQLRevueDAO getInstance() {
        if (instance == null) {
            instance = new MySQLRevueDAO();
        }
        return instance;
    }

    @Override
    public Revue getById(int id) {
        return null;
    }

    @Override
    public boolean create(Revue objet) {
        return false;
    }

    @Override
    public boolean update(Revue objet) {
        return false;
    }

    @Override
    public boolean delete(Revue objet) {
        return false;
    }

    @Override
    public List<Revue> getByRevue(Revue revue) {
        return null;
    }

    @Override
    public List<Revue> getByDescription(String description) {
        return null;
    }

    @Override
    public List<Revue> getByTitre(String titre) {
        return null;
    }

    @Override
    public List<Revue> getByVisuel(String visuel) {
        return null;
    }
}
