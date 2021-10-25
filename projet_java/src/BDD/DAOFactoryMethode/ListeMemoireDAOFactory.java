package BDD.DAOFactoryMethode;

import BDD.DAOFactory.DAOFactory;
import BDD.IDAO.AbonnementDAO;
import BDD.IDAO.ClientDAO;
import BDD.IDAO.PeriodiciteDAO;
import BDD.IDAO.RevueDAO;
import BDD.ListeMemoireDAO.ListeMemoireAbonnementDAO;
import BDD.ListeMemoireDAO.ListeMemoireClientDAO;
import BDD.ListeMemoireDAO.ListeMemoirePeriodiciteDAO;
import BDD.ListeMemoireDAO.ListeMemoireRevueDAO;

public class ListeMemoireDAOFactory extends DAOFactory {
    @Override
    public AbonnementDAO getAbonnementDAO() {
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
