package IDAO;

import DAO.DAO;
import Métier.Abonnement;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public interface AbonnementDAO<Abonnement> extends DAO<Abonnement> {
    List<Abonnement> getByAbonnement(Abonnement abonnement);
    List<Abonnement> getByDateDeb(Date date_deb);
    List<Abonnement> getByDateFin(Date date_fin);
}
