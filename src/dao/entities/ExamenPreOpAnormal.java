package dao.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class ExamenPreOpAnormal extends ExamenPreOp{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CategorieSynAnormal> categories;

	public List<CategorieSynAnormal> getCategories() {
		if(categories == null) categories = new ArrayList<>();
		return categories;
	}

	public void setCategories(List<CategorieSynAnormal> categories) {
		this.categories = categories;
	}
	
	

	public ExamenPreOpAnormal() {
		
	}
	//ajouter 
	
			public void addCategorie(CategorieSynAnormal e){
				categories.add(e);
			}
			
//		supprimer
			public void removeCategorie(CategorieSynAnormal e){
				categories.remove(e);
			}
	

	public ExamenPreOpAnormal(Hopital hopital, DossierMedicale dossier, Date dateExamen, Float poids, Float taille,
			Float oMS, Float iMC, TypeExamen typeExamen, List<CategorieSynAnormal> categories) {
		super(hopital, dossier, dateExamen, poids, taille, oMS, iMC, typeExamen);
		this.categories = categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((categories == null) ? 0 : categories.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExamenPreOpAnormal other = (ExamenPreOpAnormal) obj;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExamenPreOpAnormal [categories=" + categories + ", typeExamen=" + typeExamen + ", dateExamen="
				+ dateExamen + ", poids=" + poids + ", taille=" + taille + ", OMS=" + OMS + ", IMC=" + IMC + ", id="
				+ id + ", hopital=" + hopital + ", dossier=" + dossier + "]";
	}
	
}
