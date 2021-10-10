package Process;

import Metier.Adresse;

import java.util.Locale;

public class ProcessAdresse {

    public static Adresse normalizeAdresse (Adresse adresse){

        adresse.setPays(normalizePays(adresse));
        adresse.setVille(normalizeVille(adresse));
        adresse.setNumRue(normalizeNoRue(adresse));
        adresse.setCodePostal(normalizeCodePostal(adresse));
        adresse.setVoie(normalizeVoie(adresse));

        return adresse;

    }

    public static String normalizeCodePostal(Adresse adresse) {
        String str = adresse.getCodePostal();
        str = str.replaceAll("[^\\d.]", "").trim();
        while (str.length() < 5) {
            str = "0" + str;
        }
        return str;
    }

    public static String normalizePays(Adresse adresse){
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

    public static String normalizeVille(Adresse adresse){
        String[][] toChangeContent = {
                {"st", "saint"},
                {"ste", "saint"},
        };

        String[] preposition = {
                "lès", "le", "sous", "sur", "à", "aux"
        };

        String[] str = adresse.getVille().toLowerCase().split(" ");

        //Pour chaque mots de la ville vérifie si l'un des mots est a changer par son non abbréviation
        for (int i = 0; i < str.length; i++) {
            for (String[] a : toChangeContent) {
                if (str[i].equals(a[0]) || (str[i].equals(a[0] + "."))) {
                    str[i] = a[1] + "-";
                    break;
                }
            }
            //Pour chaques mot vérifie si l'un d'eux fait partie des propositions et ajoute des tirets autour si oui
            for (int j = 0; j < preposition.length; j++) {
                if (str[i].equals(preposition[j])) {
                    str[i] = "-" + str[i] + "-";
                }
            }

        }

        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < preposition.length; j++) {
                if (!str[i].equals("-" + preposition[j] + "-")) {
                    str[i] = str[i].substring(0, 1).toUpperCase() + str[i].substring(1);
                }
            }
        }

        StringBuilder villeAjuste = new StringBuilder();
        for (int i = 0; i < str.length - 1; i++) {
            if (str[i+1].startsWith("-") || str[i].endsWith("-")) {
                villeAjuste.append(str[i]);
            } else {
                villeAjuste.append(str[i] + " ");
            }
        }
        villeAjuste.append(str[str.length-1]);

        return villeAjuste.toString();
    }

    public static String normalizeVoie(Adresse adresse) {
        String[][] toChangeContent = {
                {"boul", "boulevard"},
                {"bd", "boulevard"},
                {"av", "avenue"},
                {"faub", "faubourg"},
                {"fb", "faubourg"},
                {"pl", "place"}
        };

        String[] oldVoie = adresse.getVoie().toLowerCase().split(" ");

        //Pour chaque mots de la voie vérifie si l'un des mots est a changer par son non abbréviation
        for (int i = 0; i < oldVoie.length; i++) {
            for (String[] a : toChangeContent) {
                //Vérifie si l'un des mots de toChangeContent[x][0] correspond a un mot de abc et si oui le remplace par toChangeContent[x][1]
                if (oldVoie[i].equals(a[0]) || (oldVoie[i].equals(a[0] + "."))) {
                    oldVoie[i] = a[1];
                    break;
                }
            }
        }

        StringBuilder voieAjuste = new StringBuilder();
        for (String mot : oldVoie) {
            voieAjuste.append(mot).append(" ");
        }

        return voieAjuste.toString().trim();
    }

    public static String normalizeNoRue(Adresse adresse){
        return adresse.getNumRue()+",";
    }





}
