package Main;

import DAOFactory.DAOFactory;
import DAOFactory.Persistance;
import Métier.Periodicite;
import Métier.Revue;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Object Re;

    public static void main(String[] args) {
        /*List<Periodicite> l1 = new ArrayList<>();


        l1 = (List<Periodicite>) daos.getPeriodicteDAO().getBylibelle("abon");

        for (Periodicite periode : l1)
        {
            System.out.println(periode.getLibelle()+periode.getId());
        }*/
        Periodicite periodicite = new Periodicite(7,"francis");
        DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);

        Re = (daos.getRevueDAO().getById(1));

        System.out.println(Re.toString());
    }

}
