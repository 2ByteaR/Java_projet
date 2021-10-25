package DAOFactoryMethode;

import DAOFactory.DAOFactory;
import IDAO.AbonnementDAO;
import IDAO.ClientDAO;
import IDAO.PeriodiciteDAO;
import IDAO.RevueDAO;
import MySQLDAO.MySQLAbonnementDAO;
import MySQLDAO.MySQLClientDAO;
import MySQLDAO.MySQLPeriodiciteDAO;
import MySQLDAO.MySQLRevueDAO;


public class MySQLDAOFactory extends DAOFactory {

    @Override
    public AbonnementDAO getAbonnementDAO() {
        return MySQLAbonnementDAO.getInstance();
    }

    @Override
    public ClientDAO getClientDAO() {
        return MySQLClientDAO.getInstance();
    }

    @Override
    public PeriodiciteDAO getPeriodicteDAO() {
        return MySQLPeriodiciteDAO.getInstance();
    }

    @Override
    public RevueDAO getRevueDAO() {
        return MySQLRevueDAO.getInstance();
    }
}
