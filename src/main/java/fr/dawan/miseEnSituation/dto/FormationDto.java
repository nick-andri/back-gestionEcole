package fr.dawan.miseEnSituation.dto;

import java.io.Serializable;

public class FormationDto implements Serializable {
    private  long id;
    private  int version;
    private  String titre;
    private  int duree;
    private  String objectifsPedagogiques;

    public FormationDto(){

    }


    public long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getTitre() {
        return titre;
    }

    public int getDuree() {
        return duree;
    }

    public String getObjectifsPedagogiques() {
        return objectifsPedagogiques;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setObjectifsPedagogiques(String objectifsPedagogiques) {
        this.objectifsPedagogiques = objectifsPedagogiques;
    }
}
