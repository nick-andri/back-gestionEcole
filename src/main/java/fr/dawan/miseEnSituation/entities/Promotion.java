package fr.dawan.miseEnSituation.entities;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Promotion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String description;
	@Version
	private int version;

	private Date dateDebut;

	private Date dateFin;

	@ManyToOne
	private TitreProfessionnel titreProfessionnel;

	@ManyToOne
	private Ville ville;

	/*@ManyToMany(mappedBy = "promotions", cascade = CascadeType.PERSIST)
	private List<Etudiant> etudiants;*/


	@ManyToMany(cascade = CascadeType.ALL)
	private List<Etudiant> etudiants;

	////////////////
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

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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

	public TitreProfessionnel getTitreProfessionnel() {
		return titreProfessionnel;
	}

	public void setTitreProfessionnel(TitreProfessionnel titreProfessionnel) {
		this.titreProfessionnel = titreProfessionnel;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public List<Long> getEtudiantsId(){
		List<Long> etudiantsId = new ArrayList<Long>();
		if(etudiants != null){
			for(Etudiant etu : etudiants) {
				etudiantsId.add(etu.getId());
			}
		}

		return etudiantsId;
	}


	public List<Etudiant> getEtudiants() {
		return etudiants;
	}
}
