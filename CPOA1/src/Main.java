import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {


    public static void main(String[] args) {
        //ArrayList<Integer> liste = new ArrayList<>();
        Periodicite peo = new Periodicite();
        /*liste = peo.getIdList();

        Iterator<Integer> li  = liste.iterator();

        while (li.hasNext()) {
            System.out.println(li.next());*/
        System.out.println(peo.getLibeller(4));

        }
    }
