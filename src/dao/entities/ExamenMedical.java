package dao.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class ExamenMedical implements Serializable {
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    protected int id;
	    @OneToOne
	    protected Hopital hopital;
	    
	    
	    
	    public ExamenMedical(Hopital hopital, DossierMedicale dossier) {
			super();
			this.hopital = hopital;
			this.dossier = dossier;
		}

		@ManyToOne
	    protected DossierMedicale dossier;

	    public ExamenMedical() {
	    }
	    
	    
	    
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }
	    
	    public Hopital getHopital() {
			return hopital;
		}



		public void setHopital(Hopital hopital) {
			this.hopital = hopital;
		}



		public DossierMedicale getDossier() {
			return dossier;
		}



		public void setDossier(DossierMedicale dossier) {
			this.dossier = dossier;
		}

		

		public ExamenMedical(int id, Hopital hopital, DossierMedicale dossier) {
			super();
			this.id = id;
			this.hopital = hopital;
			this.dossier = dossier;
		}



		@Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (int) id;
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof ExamenMedical)) {
	            return false;
	        }
	        ExamenMedical other = (ExamenMedical) object;
	        if (this.id != other.id) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "dao.entity.Genetique[ id=" + id + " ]";
	    }
	    

}
