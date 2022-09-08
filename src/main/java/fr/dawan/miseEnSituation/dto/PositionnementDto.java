package fr.dawan.miseEnSituation.dto;

import java.io.Serializable;

public class PositionnementDto implements Serializable {
    private  long id;
    private  int version;

    private  long EtudiantId;
    private  long InterventionId;
    private  long NiveauDebutId;
    private  long NiveauFinId;


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

    public long getEtudiantId() {
        return EtudiantId;
    }

    public void setEtudiantId(long etudiantId) {
        EtudiantId = etudiantId;
    }

    public long getInterventionId() {
        return InterventionId;
    }

    public void setInterventionId(long interventionId) {
        InterventionId = interventionId;
    }

    public long getNiveauDebutId() {
        return NiveauDebutId;
    }

    public void setNiveauDebutId(long niveauDebutId) {
        NiveauDebutId = niveauDebutId;
    }

    public long getNiveauFinId() {
        return NiveauFinId;
    }

    public void setNiveauFinId(long niveauFinId) {
        NiveauFinId = niveauFinId;
    }
}
