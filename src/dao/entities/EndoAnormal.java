package dao.entities;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class EndoAnormal extends Endoscopie{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToMany(cascade=CascadeType.ALL)
	private List<MasseTumorale> masseTumorales;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Polypose> polyposes;
	private List<Complication> complcations;

	public EndoAnormal() {
		super();
	}
	
	



	public EndoAnormal(Hopital hopital, DossierMedicale dossier, Date dateEndo, TypeAndoscopie typeEndo, int numero,
			Anesthesie anestesie,  List<MasseTumorale> masseTumorales, List<Polypose> polyposes,
			List<Complication> complcations, String file) {
		super(hopital, dossier, dateEndo, typeEndo, numero, anestesie,file);
		this.masseTumorales = masseTumorales;
		this.polyposes = polyposes;
		this.complcations = complcations;
	}





	public List<MasseTumorale> getMasseTumorales() {
		if(masseTumorales == null) masseTumorales = new ArrayList<>();
		return masseTumorales;
	}
	public void setMasseTumorales(List<MasseTumorale> masseTumorales) {
		this.masseTumorales = masseTumorales;
	}
	public List<Polypose> getPolypes() {
		if(polyposes == null) polyposes = new ArrayList<>();
		return polyposes;
	}
	public void setPolypes(List<Polypose> polyposes) {
		this.polyposes = polyposes;
	}
	public List<Complication> getComplcations() {
		if(complcations == null) complcations = new ArrayList<>();
		return complcations;
	}
	public void setComplcations(List<Complication> complcations) {
		this.complcations = complcations;
	}
//	ajouter
	public void addMasseTumorale(MasseTumorale masse){
		this.masseTumorales.add(masse);
	}
	public void addMassesTumorales(List<MasseTumorale> masseTumorales){
		this.masseTumorales.addAll(masseTumorales);
	}
	public void addPolypose(Polypose poly){
		this.polyposes.add(poly);
	}
	public void addPolyposes(List<Polypose> polyposes){
		this.polyposes.addAll(polyposes);
	}
	public void addComplication(Complication comp){
		this.complcations.add(comp);
	}
	public void addComplications(List<Complication> complcations){
		this.complcations.addAll(complcations);
	}
//	supprimer
	public void removeMasseTumorale(MasseTumorale masse){
		this.masseTumorales.remove(masse);
	}
	public void removePolypose(Polypose poly){
		this.polyposes.remove(poly);
	}
	public void removeComplication(Complication comp){
		this.complcations.remove(comp);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((complcations == null) ? 0 : complcations.hashCode());
		result = prime * result + ((masseTumorales == null) ? 0 : masseTumorales.hashCode());
		result = prime * result + ((polyposes == null) ? 0 : polyposes.hashCode());
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
		EndoAnormal other = (EndoAnormal) obj;
		if (complcations == null) {
			if (other.complcations != null)
				return false;
		} else if (!complcations.equals(other.complcations))
			return false;
		if (masseTumorales == null) {
			if (other.masseTumorales != null)
				return false;
		} else if (!masseTumorales.equals(other.masseTumorales))
			return false;
		if (polyposes == null) {
			if (other.polyposes != null)
				return false;
		} else if (!polyposes.equals(other.polyposes))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EndoAnormal [masseTumorales=" + masseTumorales + ", polyposes=" + polyposes + ", complcations="
				+ complcations + "]";
	}
	
}
