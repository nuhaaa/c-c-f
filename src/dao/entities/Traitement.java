package dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;



@Entity
public class Traitement implements Serializable {
	 /**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id_Trait;
		@Temporal(javax.persistence.TemporalType.DATE)
		private Date date;
		private int indication;
		@OneToOne(cascade=CascadeType.ALL)
	    private Chirurgie chirurgie;
		@OneToOne(cascade=CascadeType.ALL)
	    private TraitementEndoscopique TraitEndo;
		@OneToOne(cascade=CascadeType.ALL)
	    private Chimiotherapie chimiotherapie;
		@OneToOne(cascade=CascadeType.ALL)
	    private Radiotherapie radiotherapie;
		
		@OneToOne
	    protected Hopital hopital;

	    @ManyToOne
	    private DossierMedicale dossier;

	    public Traitement() {
	    }
	    
	    
	   


		public Traitement(Date date, int indication, Chirurgie chirurgie, TraitementEndoscopique traitEndo,
				Chimiotherapie chimiotherapie, Radiotherapie radiotherapie, Hopital hopital, DossierMedicale dossier) {
			super();
			this.date = date;
			this.indication = indication;
			this.chirurgie = chirurgie;
			TraitEndo = traitEndo;
			this.chimiotherapie = chimiotherapie;
			this.radiotherapie = radiotherapie;
			this.hopital = hopital;
			this.dossier = dossier;
		}





		public int getId() {
	        return id_Trait;
	    }

	    public void setId(int id) {
	        this.id_Trait = id;
	    }
	    
	    public int getIndication() {
			return indication;
		}


		public void setIndication(int indication) {
			this.indication = indication;
		}
		
		

		public Date getDate() {
			return date;
		}


		public void setDate(Date date) {
			this.date = date;
		}


		public Chirurgie getChirurgie() {
			return chirurgie;
		}


		public void setChirurgie(Chirurgie chirurgie) {
			this.chirurgie = chirurgie;
		}


		public TraitementEndoscopique getTraitEndo() {
			return TraitEndo;
		}


		public void setTraitEndo(TraitementEndoscopique traitEndo) {
			TraitEndo = traitEndo;
		}


		public Chimiotherapie getChimiotherapie() {
			return chimiotherapie;
		}


		public void setChimiotherapie(Chimiotherapie chimiotherapie) {
			this.chimiotherapie = chimiotherapie;
		}


		public Radiotherapie getRadiotherapie() {
			return radiotherapie;
		}


		public void setRadiotherapie(Radiotherapie radiotherapie) {
			this.radiotherapie = radiotherapie;
		}


		public DossierMedicale getDossier() {
			return dossier;
		}


		public void setDossier(DossierMedicale dossier) {
			this.dossier = dossier;
		}
		
		

		public Hopital getHopital() {
			return hopital;
		}


		public void setHopital(Hopital hopital) {
			this.hopital = hopital;
		}


		@Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (int) id_Trait;
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof Traitement)) {
	            return false;
	        }
	        Traitement other = (Traitement) object;
	        if (this.id_Trait != other.id_Trait) {
	            return false;
	        }
	        return true;
	    }





		@Override
		public String toString() {
			return "Traitement [id_Trait=" + id_Trait + ", date=" + date + ", indication=" + indication + ", chirurgie="
					+ chirurgie + ", TraitEndo=" + TraitEndo + ", chimiotherapie=" + chimiotherapie + ", radiotherapie="
					+ radiotherapie + ", hopital=" + hopital + ", dossier=" + dossier + "]";
		}


		
	    
	    
}
