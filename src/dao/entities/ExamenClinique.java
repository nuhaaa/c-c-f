package dao.entities;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Temporal;

@Entity
@SecondaryTable(name= ExamenClinique.TABLE_NAME, pkJoinColumns = @PrimaryKeyJoinColumn(name="id"))
public class ExamenClinique extends ExamenMedical {
	
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "ExamenClinique";
	@Temporal(javax.persistence.TemporalType.DATE)
	protected Date dateExamen;
	protected Float poids;
	protected Float taille;
	protected Float OMS;
	protected Float IMC;
	 
	public ExamenClinique() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public ExamenClinique(Hopital hopital, DossierMedicale dossier, Date dateExamen, Float poids, Float taille,
			Float oMS, Float iMC) {
		super(hopital, dossier);
		this.dateExamen = dateExamen;
		this.poids = poids;
		this.taille = taille;
		OMS = oMS;
		IMC = iMC;
	}



	public Date getDateExamen() {
		return dateExamen;
	}
	public void setDateExamen(Date dateExamen) {
		this.dateExamen = dateExamen;
	}
	public Float getPoids() {
		return poids;
	}
	public void setPoids(Float poids) {
		this.poids = poids;
	}
	public Float getTaille() {
		return taille;
	}
	public void setTaille(Float taille) {
		this.taille = taille;
	}
	public Float getOMS() {
		return OMS;
	}
	public void setOMS(Float oMS) {
		OMS = oMS;
	}
	public Float getIMC() {
		return IMC;
	}
	public void setIMC(Float iMC) {
		IMC = iMC;
	}
}
