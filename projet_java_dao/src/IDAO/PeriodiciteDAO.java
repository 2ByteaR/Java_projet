package IDAO;

import DAO.DAO;
import Métier.Periodicite;

public interface PeriodiciteDAO extends DAO<Periodicite>{

    String getBylibelle(String libelle);
}
