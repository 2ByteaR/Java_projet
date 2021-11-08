package bdd.daofactorymethode;

import bdd.daofactory.DAOFactory;
import bdd.idao.AbonnementDAO;
import bdd.idao.ClientDAO;
import bdd.idao.PeriodiciteDAO;
import bdd.idao.RevueDAO;
import bdd.mysqldao.MySQLAbonnementDAO;
import bdd.mysqldao.MySQLClientDAO;
import bdd.mysqldao.MySQLPeriodiciteDAO;
import bdd.mysqldao.MySQLRevueDAO;


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
