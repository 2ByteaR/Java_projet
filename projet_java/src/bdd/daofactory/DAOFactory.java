package bdd.daofactory;

import bdd.daofactorymethode.ListeMemoireDAOFactory;
import bdd.daofactorymethode.MySQLDAOFactory;
import bdd.idao.AbonnementDAO;
import bdd.idao.ClientDAO;
import bdd.idao.PeriodiciteDAO;
import bdd.idao.RevueDAO;

public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(Persistance cible){

        DAOFactory daoF = null;

        switch (cible) {
            case MYSQL:
                daoF = new MySQLDAOFactory();
                break;
            case ListeMemoire:
                daoF = new ListeMemoireDAOFactory();
                break;
        }
        return daoF;
    }
    public abstract AbonnementDAO getAbonnementDAO();
    public abstract ClientDAO getClientDAO();
    public abstract PeriodiciteDAO getPeriodicteDAO();
    public abstract RevueDAO getRevueDAO();

}

