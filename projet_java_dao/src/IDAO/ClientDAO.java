package IDAO;

import DAO.DAO;
import Métier.Client;

import java.util.List;

public interface ClientDAO<Client> extends DAO<Client> {
    List<Client> getByClient(Client client);
    List<Client> getByCodePostal(String code_postal);
    List<Client> getByNom(String nom);
    List<Client> getByNoRue(String no_rue);
    List<Client> getByPrenom(String prenom);
    List<Client> getByVille(String ville);
    List<Client> getByVoie(String voie);
    List<Client> getByPays(String pays);
}
