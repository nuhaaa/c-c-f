package dao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;


@Entity
public class DossierMedicale implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id_Doss;
	    @Temporal(javax.persistence.TemporalType.DATE)
	    private Date dateCreation;
	    
	    @OneToOne(mappedBy="dossier")
	    private Individu patient;
	    
	    @OneToMany(mappedBy="dossier", cascade=CascadeType.ALL)
	    private List<ExamenMedical> examensMedicacales;
	    
	    @OneToMany(mappedBy="dossier", cascade=CascadeType.ALL)
	    private List<Traitement> traitements;

	    public DossierMedicale() {
	    }
	    
	   

	    public DossierMedicale(Date dateCreation, Individu patient) {
			super();
			this.dateCreation = dateCreation;
			this.patient = patient;
		}



		public int getId() {
	        return id_Doss;
	    }

	    public void setId(int id) {
	        this.id_Doss = id;
	    }
	    
	    public int getId_Doss() {
			return id_Doss;
		}



		public void setId_Doss(int id_Doss) {
			this.id_Doss = id_Doss;
		}



		public Date getDateCreation() {
			return dateCreation;
		}



		public void setDateCreation(Date dateCreation) {
			this.dateCreation = dateCreation;
		}



		public Individu getPatient() {
			return patient;
		}



		public void setPatient(Individu patient) {
			this.patient = patient;
		}



		public List<ExamenMedical> getExamensMediacales() {
			if(examensMedicacales == null) examensMedicacales = new ArrayList<>();
			return examensMedicacales;
		}



		public void setExamensMediacales(List<ExamenMedical> examensMediacales) {
			this.examensMedicacales = examensMediacales;
		}



		public List<Traitement> getTraitements() {
			if(traitements == null) traitements = new ArrayList<>();
			return traitements;
		}



		public void setTraitements(List<Traitement> traitements) {
			this.traitements = traitements;
		}
//ajouter 
		
		public void addExamenMedicale(ExamenMedical e){
			examensMedicacales.add(e);
		}
		
		public void addTraitement(Traitement t){
			traitements.add(t);
		}
//	supprimer
		public void removeExamenMedicale(ExamenMedical e){
			examensMedicacales.remove(e);
		}
		
		public void removeTraitement(Traitement t){
			traitements.remove(t);
		}
		
		@Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (int) id_Doss;
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id_Doss fields are not set
	        if (!(object instanceof DossierMedicale)) {
	            return false;
	        }
	        DossierMedicale other = (DossierMedicale) object;
	        if (this.id_Doss != other.id_Doss) {
	            return false;
	        }
	        return true;
	    }



		@Override
		public String toString() {
			return "Dossier Medical de  " + patient +" de la famille: " +patient.getFamille()+" crée le: "+ dateCreation ;
		}

	   
	    

}
