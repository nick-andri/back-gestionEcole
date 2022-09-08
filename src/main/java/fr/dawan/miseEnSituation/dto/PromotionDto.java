package fr.dawan.miseEnSituation.dto;



import java.io.Serializable;

import java.util.Date;
import java.util.List;

public class PromotionDto implements Serializable {
    private  long id;

    private  String description;
    private  int version;
    private Date dateDebut;
    private Date dateFin;

    private long titreProfessionnelId;

    private long villeId;
    private List<Long> etudiantsId;

    public PromotionDto() {
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Long> getEtudiantsId() {
        return etudiantsId;
    }

    public void setEtudiantsId(List<Long> etudiantsId) {
        this.etudiantsId = etudiantsId;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public long getTitreProfessionnelId() {
        return titreProfessionnelId;
    }

    public void setTitreProfessionnelId(long titreProfessionnelId) {
        this.titreProfessionnelId = titreProfessionnelId;
    }

    public long getVilleId() {
        return villeId;
    }

    public void setVilleId(long villeId) {
        this.villeId = villeId;
    }
}
