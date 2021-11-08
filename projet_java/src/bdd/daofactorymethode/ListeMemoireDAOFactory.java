package bdd.daofactorymethode;

import bdd.daofactory.DAOFactory;
import bdd.idao.AbonnementDAO;
import bdd.idao.ClientDAO;
import bdd.idao.PeriodiciteDAO;
import bdd.idao.RevueDAO;
import bdd.listememoiredao.ListeMemoireAbonnementDAO;
import bdd.listememoiredao.ListeMemoireClientDAO;
import bdd.listememoiredao.ListeMemoirePeriodiciteDAO;
import bdd.listememoiredao.ListeMemoireRevueDAO;


public class ListeMemoireDAOFactory extends DAOFactory {
    @Override
    public AbonnementDAO getAbonnementDAO(){
        return ListeMemoireAbonnementDAO.getInstance();
    }

    @Override
    public ClientDAO getClientDAO() {
        return ListeMemoireClientDAO.getInstance();
    }

    @Override
    public PeriodiciteDAO getPeriodicteDAO() {
        return ListeMemoirePeriodiciteDAO.getInstance();
    }

    @Override
    public RevueDAO getRevueDAO() {
        return ListeMemoireRevueDAO.getInstance();
    }
}
