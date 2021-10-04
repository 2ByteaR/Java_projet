package Main;

import ProcessAdresse.ProcessAdresse;

public class Main {

    public static void main(String[] args) {
        ProcessAdresse AD = new ProcessAdresse();

        String ville = AD.normalizeVille(" st Moulin les metz");
        System.out.println(ville);
    }
}
