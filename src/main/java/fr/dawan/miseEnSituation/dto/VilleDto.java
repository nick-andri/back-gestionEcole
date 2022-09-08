package fr.dawan.miseEnSituation.dto;

import java.io.Serializable;

public class VilleDto implements Serializable {
    private  long id;
    private  int version;
    private  String nom;
    private String slug;


    public VilleDto() {
    }


    public String getSlug() {
        return slug;
    }

    public long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }



    public void setId(long id) {
        this.id = id;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
