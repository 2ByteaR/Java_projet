package BDD.DAOFactoryMethode;

import BDD.DAOFactory.DAOFactory;
import BDD.IDAO.AbonnementDAO;
import BDD.IDAO.ClientDAO;
import BDD.IDAO.PeriodiciteDAO;
import BDD.IDAO.RevueDAO;
import BDD.MySQLDAO.MySQLAbonnementDAO;
import BDD.MySQLDAO.MySQLClientDAO;
import BDD.MySQLDAO.MySQLPeriodiciteDAO;
import BDD.MySQLDAO.MySQLRevueDAO;


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
