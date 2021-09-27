package DAOFactoryMethode;

import DAOFactory.DAOFactory;
import IDAO.AbonnementDAO;
import IDAO.ClientDAO;
import IDAO.PeriodiciteDAO;
import IDAO.RevueDAO;
import ListeMemoireDAO.ListeMemoireAbonnementDAO;
import ListeMemoireDAO.ListeMemoireClientDAO;
import ListeMemoireDAO.ListeMemoirePeriodiciteDAO;
import ListeMemoireDAO.ListeMemoireRevueDAO;

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
