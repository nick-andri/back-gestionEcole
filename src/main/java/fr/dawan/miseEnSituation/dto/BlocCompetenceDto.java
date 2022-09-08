package fr.dawan.miseEnSituation.dto;


import java.io.Serializable;
import java.util.List;

public class BlocCompetenceDto implements Serializable {
    private long id;
    private String titre;
    private String description;
    private int version;


    private Long titreProfessionnelId;



    public long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public int getVersion() {
        return version;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Long getTitreProfessionnelId() {
        return titreProfessionnelId;
    }

    public void setTitreProfessionnelId(Long titreProfessionnelId) {
        this.titreProfessionnelId = titreProfessionnelId;
    }
}
