package Metier;

public class Adresse {

    private String numRue;
    private String voie;
    private int codePostal;
    private String ville;
    private String pays;

    public String getNumRue() {
        return numRue;
    }

    public void setNumRue(String numRue) {
        this.numRue = numRue;
    }

    public String getVoie() {
        return voie;
    }

    public void setVoie(String voie) {
        this.voie = voie;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Adresse(String numRue, String voie, String codePostal, String ville, String pays) {
        this.numRue = numRue;
        this.voie = voie;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    @Override
    public String toString() {
        return  numRue + voie + " "+codePostal +" "+ ville +" "+pays;
    }
}
