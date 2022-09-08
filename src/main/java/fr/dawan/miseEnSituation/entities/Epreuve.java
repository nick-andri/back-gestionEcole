package fr.dawan.miseEnSituation.entities;

import java.io.Serializable;

import javax.persistence.*;

import aj.org.objectweb.asm.Type;

@Entity
public class Epreuve implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Version
	private int version;

	private String titre;

	@Column(columnDefinition = "TEXT")
	private String description;

	public enum Type { QCM, MISE_EN_SITUATION};

	@Enumerated(EnumType.STRING)
	private Type type;

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public BlocCompetence getBlocCompetence() {
		return blocCompetence;
	}

	public void setBlocCompetence(BlocCompetence blocCompetence) {
		this.blocCompetence = blocCompetence;
	}
	
}
