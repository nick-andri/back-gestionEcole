package fr.dawan.miseEnSituation.dto;


import java.io.Serializable;

public class EpreuveDto implements Serializable {
    private  long Id;
    private  String titre;
    private  String description;
    private  int version;
    private  String type;

    private long BlocCompetenceId;

    public EpreuveDto() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getBlocCompetenceId() {
        return BlocCompetenceId;
    }

    public void setBlocCompetenceId(long blocCompetenceId) {
        BlocCompetenceId = blocCompetenceId;
    }
}
