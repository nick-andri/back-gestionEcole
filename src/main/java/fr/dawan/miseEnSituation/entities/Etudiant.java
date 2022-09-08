package fr.dawan.miseEnSituation.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Etudiant extends Utilisateur implements Serializable {

	/*@ManyToMany(cascade = CascadeType.ALL)
	private List<Promotion> promotions;*/

	@ManyToMany(mappedBy = "etudiants", cascade = CascadeType.ALL)
	private List<Promotion> promotions;

	public Etudiant() {
		super();
		this.setRole(Role.ETUDIANT);

	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<Long> getPromotionsId(){
		List<Long> promotionsId = new ArrayList<Long>();
		if(promotions != null){
			for(Promotion promo : promotions) {
				if(promo != null){
					promotionsId.add(promo.getId());
				}

			}
		}

		return promotionsId;
	}


}
