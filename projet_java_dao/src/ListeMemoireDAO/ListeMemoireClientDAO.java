package ListeMemoireDAO;

import IDAO.ClientDAO;
import Métier.Client;
import Métier.Periodicite;
import Métier.Revue;

import java.util.ArrayList;
import java.util.List;

public class ListeMemoireClientDAO implements ClientDAO<Client> {

    private static ListeMemoireClientDAO instance;
    private List<Client> donnees;

    private void ListeMemoireClientDAO(){
        if (donnees == null || donnees.isEmpty()) {
          donnees.add(new Client("CARL","Noé"," rue de josé","Metz","France","2bis","57800",1));
          donnees.add(new Client("COURS","Nathan"," rue de rose","diesen","France","3","57890",2));
        }

    }

    public static ListeMemoireClientDAO getInstance() {
        if (instance == null) {
            instance = new ListeMemoireClientDAO();
        }
        return instance;
    }

    @Override
    public Client getById(int id) {
        return null;
    }

    @Override
    public boolean create(Client objet) {
        objet.setId_client(objet.getId_client());
        // Ne fonctionne que si l'objet mÃ©tier est bien fait...
        while (this.donnees.contains(objet)) {

            objet.setId_client(objet.getId_client() + 1);
        }
        boolean ok = this.donnees.add(objet);

        return ok;
    }

    @Override
    public boolean update(Client objet) {
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
        } else {

            this.donnees.set(idx, objet);
        }

        return true;
    }

    @Override
    public boolean delete(Client objet) {
        Client supprime;

        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
        } else {
            supprime = this.donnees.remove(idx);
        }

        return objet.equals(supprime);
    }



    @Override
    public List<Client> getByCodePostal(String code_postal) {
        List<Client> per = new ArrayList<>();
        for (Client pe: donnees) {
            if (pe.getCode_postal().equalsIgnoreCase(code_postal)){
                per.add(pe);
            }
        }
        return per;
    }

    @Override
    public List<Client> getByNom(String nom) {
        List<Client> listeClient = new ArrayList<>();
        donnees.add(new Client("CARL","Noé"," rue de josé","Metz","France","2bis","57800",1));
        donnees.add (new Client("COURS","Nathan"," rue de rose","diesen","France","3","57890",2));

        for (Client cl: donnees) {
            if (cl.getNom().equalsIgnoreCase(nom)){
                listeClient .add(cl);
            }
        }
        return listeClient;
    }

    @Override
    public List<Client> getByNoRue(String no_rue) {
        List<Client> per = new ArrayList<>();
        for (Client pe: donnees) {
            if (pe.getNoRue().equalsIgnoreCase(no_rue)){
                per.add(pe);
            }
        }
        return per;
    }

    @Override
    public List<Client> getByPrenom(String prenom) {
        List<Client> per = new ArrayList<>();
        for (Client pe: donnees) {
            if (pe.getPrenom().equalsIgnoreCase(prenom)){
                per.add(pe);
            }
        }
        return per;
    }

    @Override
    public List<Client> getByVille(String ville) {
        List<Client> per = new ArrayList<>();
        for (Client pe: donnees) {
            if (pe.getVille().equalsIgnoreCase(ville)){
                per.add(pe);
            }
        }
        return per;
    }

    @Override
    public List<Client> getByVoie(String voie) {
        List<Client> per = new ArrayList<>();
        for (Client pe: donnees) {
            if (pe.getVoie().equalsIgnoreCase(voie)){
                per.add(pe);
            }
        }
        return per;
    }

    @Override
    public List<Client> getByPays(String pays) {
        List<Client> per = new ArrayList<>();
        for (Client pe: donnees) {
            if (pe.getPays().equalsIgnoreCase(pays)){
                per.add(pe);
            }
        }
        return per;
    }
}
