package fr.dawan.miseEnSituation.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
public class Formation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    private int version;

    @Column(nullable = false, unique = true)
    private String titre;

    private int duree;

    @Column(columnDefinition = "TEXT")
    private String objectifsPedagogiques;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getObjectifsPedagogiques() {
        return objectifsPedagogiques;
    }

    public void setObjectifsPedagogiques(String objectifsPedagogiques) {
        this.objectifsPedagogiques = objectifsPedagogiques;
    }


}

