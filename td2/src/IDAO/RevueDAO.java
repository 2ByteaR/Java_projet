package IDAO;

import DAO.DAO;
import Métier.Revue;

import java.util.List;

public interface RevueDAO<Revue> extends DAO<Revue> {
    List<Revue> getByDescription(String description);
    List<Revue> getByTitre(String titre);
    List<Revue> getByVisuel(String visuel);

}
