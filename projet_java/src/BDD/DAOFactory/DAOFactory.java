package BDD.DAOFactory;

import BDD.DAOFactoryMethode.ListeMemoireDAOFactory;
import BDD.DAOFactoryMethode.MySQLDAOFactory;
import BDD.IDAO.AbonnementDAO;
import BDD.IDAO.ClientDAO;
import BDD.IDAO.PeriodiciteDAO;
import BDD.IDAO.RevueDAO;

import java.util.Scanner;

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

