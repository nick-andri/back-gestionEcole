package fr.dawan.miseEnSituation.dto;


import java.io.Serializable;

@SuppressWarnings("serial")
public class CountDto implements Serializable{

    private long nb;

    public long getNb() {
        return nb;
    }

    public void setNb(long nb) {
        this.nb = nb;
    }

}