package fr.dawan.miseEnSituation.dto;

import java.io.Serializable;

public class CompetenceDto implements Serializable {
    private  long id;
    private  String titre;
    private  String description;
    private  int version;
    private  Long blocCompetenceId;

    public CompetenceDto() {
    }

    public CompetenceDto(long id, String titre, String description, int version, Long blocCompetenceId) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.version = version;
        this.blocCompetenceId = blocCompetenceId;
    }

    public long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Long getBlocCompetenceId() {
        return blocCompetenceId;
    }

    public void setBlocCompetenceId(Long blocCompetenceId) {
        this.blocCompetenceId = blocCompetenceId;
    }
}
