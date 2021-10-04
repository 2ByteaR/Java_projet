package ProcessAdresse;

import Métier.Adresse;

import java.util.Locale;

public class ProcessAdresse {

    public Adresse normalizeAdresse (Adresse adresse){

        String pays = normalizePays(adresse);
        //String ville = normalizeVille(adresse);

        return adresse;
    }

    public String normalizePays(Adresse adresse){
        String pays = adresse.getPays();
         if(pays.equalsIgnoreCase("belgium")){
             return "Belgique";
         }else if (pays.equalsIgnoreCase("letzebuerg")){
             return "Luxembourg";
         }else if (pays.equalsIgnoreCase("Switzerland") || pays.equalsIgnoreCase("Schweiz")){
             return "Suisse";
        }else {
             return null;
         }
    }

    public String normalizeVille(String  ville){
        //String ville = adresse.getVille();

        String lettre1 = ville.substring(0,1);
        ville = ville.substring(1,ville.length());
        ville = lettre1.toUpperCase() + ville.trim().replace(" ","-").substring(1,ville.length());
        lettre1.toUpperCase();

        if (ville.contains("st")){
            ville.replace("st","saint");
        }else if (ville.contains("ste")){
            ville.replace("ste","sainte");
        }

        return ville;

    }
}
