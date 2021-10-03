package ListeMemoireDAO;

import IDAO.AbonnementDAO;
import MySQLDAO.MySQLAbonnementDAO;
import Métier.Abonnement;
import Métier.Periodicite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListeMemoireAbonnementDAO implements AbonnementDAO<Abonnement> {

    private static ListeMemoireAbonnementDAO instance;
    private List<Abonnement> donnees;

    private ListeMemoireAbonnementDAO() {

        this.donnees = new ArrayList<Abonnement>();


        Date date = new Date("2013-03-08");
        Date date1 = new Date("2014-05-06");
        this.donnees.add(new Abonnement(1, date ,date1,1,1));
        this.donnees.add(new Abonnement(2, date,date1,1,1));
    }

    public static ListeMemoireAbonnementDAO getInstance() {
        if (instance == null) {
            instance = new ListeMemoireAbonnementDAO();
        }
        return instance;
    }

    @Override
    public Abonnement getById(int id) {
        int idx = this.donnees.indexOf(new Periodicite(id, "test"));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public boolean create(Abonnement objet) {
        return false;
    }

    @Override
    public boolean update(Abonnement objet) {
        return false;
    }

    @Override
    public boolean delete(Abonnement objet) {
        return false;
    }


    @Override
    public List<Abonnement> getByDateDeb(Date date_deb) {

        List<Abonnement> per = new ArrayList<>();
        for (Abonnement pe: donnees) {
            if (pe.getDate_deb() == date_deb){
                per.add(pe);
            }
        }
        return per;
    }

    @Override
    public List<Abonnement> getByDateFin(Date date_fin) {
        List<Abonnement> per = new ArrayList<>();
        for (Abonnement pe: donnees) {
            if (pe.getDate_deb() == date_fin){
                per.add(pe);
            }
        }
        return per;
    }
}
