package BDD.ListeMemoireDAO;

import BDD.IDAO.PeriodiciteDAO;
import BDD.Métier.Periodicite;

import java.util.ArrayList;
import java.util.List;

public class ListeMemoirePeriodiciteDAO  implements PeriodiciteDAO<Periodicite> {

    private static ListeMemoirePeriodiciteDAO instance;
    private List<Periodicite> donnees;


    public static ListeMemoirePeriodiciteDAO getInstance() {
        if (instance == null) {
            instance = new ListeMemoirePeriodiciteDAO();
        }
        return instance;
    }
    private ListeMemoirePeriodiciteDAO() {

        this.donnees = new ArrayList<Periodicite>();

        this.donnees.add(new Periodicite(1, "Mensuel"));
        this.donnees.add(new Periodicite(2, "Quotidien"));
    }


    @Override
    public boolean create(Periodicite objet) {

        objet.setId(3);
        // Ne fonctionne que si l'objet mÃ©tier est bien fait...
        while (this.donnees.contains(objet)) {

            objet.setId(objet.getId() + 1);
        }
        boolean ok = this.donnees.add(objet);

        return ok;
    }

    @Override
    public boolean update(Periodicite objet) {

        // Ne fonctionne que si l'objet mÃ©tier est bien fait...
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
        } else {

            this.donnees.set(idx, objet);
        }

        return true;
    }

    @Override
    public boolean delete(Periodicite objet) {

        Periodicite supprime;

        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
        } else {
            supprime = this.donnees.remove(idx);
        }

        return objet.equals(supprime);
    }

    @Override
    public Periodicite getById(int id) {
        // Ne fonctionne que si l'objet mÃ©tier est bien fait...
        int idx = this.donnees.indexOf(new Periodicite(id, "test"));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }


    public ArrayList<Periodicite> findAll() {
        return (ArrayList<Periodicite>) this.donnees;
    }

    @Override
    public List<Periodicite> getBylibelle(String libelle) {

        List<Periodicite> per = new ArrayList<>();
        for (Periodicite pe: donnees) {
            if (pe.getLibelle().equalsIgnoreCase(libelle)){
                per.add(pe);
        }
        }
        return per;
    }
}
