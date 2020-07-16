package dao.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class ExamenPostOp extends ExamenClinique{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int delai;
	private int nbreSelles;
	@OneToMany
	private List<SyndromesPostOp> syndromes;
	@OneToMany
	 private List<ToucherRectal> touchers;
	public ExamenPostOp() {
		super();
	}
	
	

	public ExamenPostOp(Hopital hopital, DossierMedicale dossier, Date dateExamen, Float poids, Float taille, Float oMS,
			Float iMC, int delai, int nbreSelles, List<SyndromesPostOp> syndromes, List<ToucherRectal> touchers) {
		super(hopital, dossier, dateExamen, poids, taille, oMS, iMC);
		this.delai = delai;
		this.nbreSelles = nbreSelles;
		this.syndromes = syndromes;
		this.touchers = touchers;
	}



	public int getDelai() {
		return delai;
	}
	public void setDelai(int delai) {
		this.delai = delai;
	}
	public int getNbreSelles() {
		return nbreSelles;
	}
	public void setNbreSelles(int nbreSelles) {
		this.nbreSelles = nbreSelles;
	}
	public List<SyndromesPostOp> getSyndromes() {
		if(syndromes == null) syndromes = new ArrayList<>();
		return syndromes;
	}
	public void setSyndromes(List<SyndromesPostOp> syndromes) {
		this.syndromes = syndromes;
	}
	public List<ToucherRectal> getTouchers() {
		if(touchers == null) touchers = new ArrayList<>();
		return touchers;
	}
	public void setTouchers(List<ToucherRectal> touchers) {
		this.touchers = touchers;
	}
	
//	ajouter 
	public void addSyndromes(SyndromesPostOp synd){
		syndromes.add(synd);
	}
	public void addListSyndrome(List<SyndromesPostOp> synds){
		syndromes.addAll(synds);
	}
	public void addListToucher(List<ToucherRectal> touchers){
		touchers.addAll(touchers);
	}
	public void  addToucher(ToucherRectal toucher){
		touchers.add(toucher);
	}
	
//	supprimer
	public void supprimerSyndromes(SyndromesPostOp synd){
		syndromes.remove(synd);
	}
	public void supprimerToucher(ToucherRectal toucher){
		touchers.remove(toucher);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + delai;
		result = prime * result + nbreSelles;
		result = prime * result + ((syndromes == null) ? 0 : syndromes.hashCode());
		result = prime * result + ((touchers == null) ? 0 : touchers.hashCode());
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
		ExamenPostOp other = (ExamenPostOp) obj;
		if (delai != other.delai)
			return false;
		if (nbreSelles != other.nbreSelles)
			return false;
		if (syndromes == null) {
			if (other.syndromes != null)
				return false;
		} else if (!syndromes.equals(other.syndromes))
			return false;
		if (touchers == null) {
			if (other.touchers != null)
				return false;
		} else if (!touchers.equals(other.touchers))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ExamenPostOp [delai=" + delai + ", nbreSelles=" + nbreSelles + ", syndromes=" + syndromes
				+ ", touchers=" + touchers + "]";
	}
	
}
