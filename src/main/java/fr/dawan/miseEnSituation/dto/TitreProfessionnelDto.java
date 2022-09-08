package fr.dawan.miseEnSituation.dto;


import java.io.Serializable;

public class TitreProfessionnelDto implements Serializable {
    private  Long id;
    private  String titre;
    private  int version;
    private String slug;

    public TitreProfessionnelDto() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
