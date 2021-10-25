package BDD.IDAO;

import BDD.DAO.DAO;

import java.util.List;

public interface PeriodiciteDAO<Periodicite> extends DAO<Periodicite>{
    List<Periodicite> getBylibelle(String libelle);
}
