package bdd.listememoiredao;

import bdd.idao.AbonnementDAO;
import bdd.metier.Abonnement;
import bdd.metier.Client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListeMemoireAbonnementDAO implements AbonnementDAO<Abonnement> {

    private static ListeMemoireAbonnementDAO instance;
    private List<Abonnement> donnees;

    private static java.util.Date convertToDateUtil(String date){
        try {
            SimpleDateFormat sm = new SimpleDateFormat("MM/dd/yyyy", Locale.FRANCE);
            String strDate = sm.format(date);
            java.util.Date dt = sm.parse(strDate);
            return dt;
        }catch (Exception e){
            System.out.println("Problème "+ e);
            return null;
        }
    }


    private ListeMemoireAbonnementDAO() {

        this.donnees = new ArrayList<>();


        Date date = new Date(2002,03,11);
        Date date1 = new Date(2003,04,12);
        this.donnees.add(new Abonnement(1, date ,date1,1,1));
        this.donnees.add(new Abonnement(2, date,date1,1,1));
    }

    public static ListeMemoireAbonnementDAO getInstance(){
        if (instance == null) {
            instance = new ListeMemoireAbonnementDAO();
        }
        return instance;
    }

    @Override
    public List<Abonnement> findAll() {

        return this.donnees;
    }

    @Override
    public Abonnement getById(int id) {
        int idx = this.donnees.indexOf(new Abonnement(id, null,null,0,0));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public boolean create(Abonnement objet) {
        objet.setId_abonnement(3);
        // Ne fonctionne que si l'objet mÃ©tier est bien fait...
        while (this.donnees.contains(objet)) {

            objet.setId_abonnement(objet.getId_abonnement() + 1);
        }
        boolean ok = this.donnees.add(objet);

        return ok;
    }

    @Override
    public boolean update(Abonnement objet) {
        int idx = this.donnees.indexOf(objet);

        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
        } else {

            this.donnees.set(idx, objet);
        }

        return true;
    }

    @Override
    public boolean delete(Abonnement objet) {
        Abonnement supprimer;

        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
        } else {
            supprimer = this.donnees.remove(idx);
        }

        return objet.equals(supprimer);
    }


}
