package fr.dawan.miseEnSituation.dto;

import java.io.Serializable;

public class EvaluationDto implements Serializable {
    private  long id;
    private  int version;
    private  Double note;

    private long epreuveId;
    private long etudiantId;


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

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public long getEpreuveId() {
        return epreuveId;
    }

    public void setEpreuveId(long epreuveId) {
        this.epreuveId = epreuveId;
    }

    public long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(long etudiantId) {
        this.etudiantId = etudiantId;
    }
}
