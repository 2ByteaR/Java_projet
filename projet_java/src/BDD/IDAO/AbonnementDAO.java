package BDD.IDAO;

import BDD.DAO.DAO;

import java.util.Date;
import java.util.List;

public interface AbonnementDAO<Abonnement> extends DAO<Abonnement> {
    List<Abonnement> getByDateDeb(Date date_deb);
    List<Abonnement> getByDateFin(Date date_fin);
}
