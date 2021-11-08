package bdd.metier;

import java.util.Date;
import java.util.Objects;

public class Abonnement  {
    private int id_abonnement;
    private Date date_deb;
    private Date date_fin;
    private int id_client;
    private int id_revue;


    public Abonnement(int id_abonnement, Date date_deb, Date date_fin, int id_client, int id_revue) {
        this.id_abonnement = id_abonnement;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.id_client = id_client;
        this.id_revue = id_revue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Abonnement)) return false;
        Abonnement that = (Abonnement) o;
        return getId_abonnement() == that.getId_abonnement();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_abonnement());
    }

    public int getId_abonnement() {
        return id_abonnement;
    }

    public void setId_abonnement(int id_abonnement) {
        this.id_abonnement = id_abonnement;
    }

    public Date getDate_deb() {
        return date_deb;
    }

    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }
    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_revue() {
        return id_revue;
    }

    public void setId_revue(int id_revue) {
        this.id_revue = id_revue;
    }

    @Override
    public String toString() {
        return "Abonnement{" +
                "id_abonnement=" + id_abonnement +
                ", date_deb=" + date_deb +
                ", date_fin=" + date_fin +
                ", id_client=" + id_client +
                ", id_revue=" + id_revue +
                '}';
    }
}
