package fr.dawan.miseEnSituation.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
public class Competence implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Version
	private int version;

	@Column(nullable = false, unique = true)
	private String titre;

	@Column(columnDefinition = "TEXT")
	private String description;

	@ManyToOne
	private BlocCompetence blocCompetence;

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

	public BlocCompetence getBlocCompetences() {
		return blocCompetence;
	}

	public void setBlocCompetences(BlocCompetence blocCompetence) {
		this.blocCompetence = blocCompetence;
	}



}

