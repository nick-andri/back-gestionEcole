package fr.dawan.miseEnSituation.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class BlocCompetence implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Version
	private int version;

	@Column(nullable = false, unique = true)
	private String titre;

	@Column(columnDefinition = "TEXT")
	private String description;

	@OneToMany(mappedBy = "blocCompetence")
	private List<Competence> competences;

	@ManyToOne
	private TitreProfessionnel titreProfessionnel;

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

	public TitreProfessionnel getTitreProfessionnel() {
		return titreProfessionnel;
	}

	public void setTitreProfessionnel(TitreProfessionnel titreProfessionnel) {
		this.titreProfessionnel = titreProfessionnel;
	}

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}
}
