package IDAO;

import DAO.DAO;
import Métier.Periodicite;

import java.util.List;

public interface PeriodiciteDAO<Periodicite> extends DAO<Periodicite>{
    List<Periodicite> getBylibelle(String libelle);
}
