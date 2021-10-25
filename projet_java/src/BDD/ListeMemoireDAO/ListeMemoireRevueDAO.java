package BDD.ListeMemoireDAO;

import BDD.IDAO.RevueDAO;
import BDD.Métier.Revue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListeMemoireRevueDAO implements RevueDAO<Revue> {

    private static ListeMemoireRevueDAO instance;
    private List<Revue> donnees;

    public static ListeMemoireRevueDAO getInstance() {
        if (instance == null) {
            instance = new ListeMemoireRevueDAO();
        }
        return instance;
    }

    private ListeMemoireRevueDAO() {

        this.donnees = new ArrayList<Revue>();


        Date date = new Date("2013-03-08");
        Date date1 = new Date("2014-05-06");
        this.donnees.add(new Revue(1,1,"test",15,"titre1","beau"));
        this.donnees.add(new Revue(2,2,"test2",16,"titre2","moche"));
    }

    @Override
    public List<Revue> findAll() {
        return null;
    }

    @Override
    public Revue getById(int id) {
        int idx = this.donnees.indexOf(new Revue(id, 1,"test",15,"test","test"));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public boolean create(Revue objet) {
        objet.setId_revue(objet.getId_revue());
        // Ne fonctionne que si l'objet mÃ©tier est bien fait...
        while (this.donnees.contains(objet)) {

            objet.setId_revue(objet.getId_revue() + 1);
        }
        boolean ok = this.donnees.add(objet);

        return ok;
    }

    @Override
    public boolean update(Revue objet) {
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
        } else {

            this.donnees.set(idx, objet);
        }

        return true;
    }

    @Override
    public boolean delete(Revue objet) {
        Revue supprime;

        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
        } else {
            supprime = this.donnees.remove(idx);
        }

        return objet.equals(supprime);
    }



    @Override
    public List<Revue> getByDescription(String description) {
        List<Revue> per = new ArrayList<>();
        for (Revue pe: donnees) {
            if (pe.getDescription().equalsIgnoreCase(description)){
                per.add(pe);
            }
        }
        return per;
    }

    @Override
    public List<Revue> getByTitre(String titre) {
        List<Revue> per = new ArrayList<>();
        for (Revue pe: donnees) {
            if (pe.getTitre().equalsIgnoreCase(titre)){
                per.add(pe);
            }
        }
        return per;
    }

    @Override
    public List<Revue> getByVisuel(String visuel) {
        List<Revue> per = new ArrayList<>();
        for (Revue pe: donnees) {
            if (pe.getVisuel().equalsIgnoreCase(visuel)){
                per.add(pe);
            }
        }
        return per;
    }
}
