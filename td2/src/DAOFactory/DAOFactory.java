package DAOFactory;

import DAOFactoryMethode.ListeMemoireDAOFactory;
import DAOFactoryMethode.MySQLDAOFactory;
import IDAO.AbonnementDAO;
import IDAO.ClientDAO;
import IDAO.PeriodiciteDAO;
import IDAO.RevueDAO;

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

