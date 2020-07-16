package dao.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;




@Entity
public class RendezVous implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id_RendezVous;
	    @Temporal(javax.persistence.TemporalType.DATE)
	    private Date dateRendezVous;
	    private String heureRendez;
	    private String objet;
	    private String note;
	    
	    @ManyToOne
	    private Individu patient;

	    public RendezVous() {
	    }
	    
	    

	    public RendezVous(Date dateRendezVous, String heureRendez, String objet, String note, Individu patient) {
			super();
			this.dateRendezVous = dateRendezVous;
			this.heureRendez = heureRendez;
			this.objet = objet;
			this.note = note;
			this.patient = patient;
		}



		public int getId() {
	        return id_RendezVous;
	    }

	    public void setId(int id) {
	        this.id_RendezVous = id;
	    }

	    public Date getDateRendezVous() {
	        return dateRendezVous;
	    }

	    public void setDateRendezVous(Date dateRendezVous) {
	        this.dateRendezVous = dateRendezVous;
	    }

	    
	    public String getHeureRendez() {
			return heureRendez;
		}



		public void setHeureRendez(String heureRendez) {
			this.heureRendez = heureRendez;
		}



		public String getObjet() {
	        return objet;
	    }

	    public void setObjet(String objet) {
	        this.objet = objet;
	    }

	    public String getNote() {
	        return note;
	    }

	    public void setNote(String note) {
	        this.note = note;
	    }

	    public Individu getPatient() {
	        return patient;
	    }

	    public void setPatient(Individu patient) {
	        this.patient = patient;
	    }

	    
	    
	    @Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (int) id_RendezVous;
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof RendezVous)) {
	            return false;
	        }
	        RendezVous other = (RendezVous) object;
	        if (this.id_RendezVous != other.id_RendezVous) {
	            return false;
	        }
	        return true;
	    }



		@Override
		public String toString() {
			return "RendezVous [dateRendezVous=" + dateRendezVous + ", heureRendez=" + heureRendez + ", objet=" + objet
					+ ", patient=" + patient + "]";
		}

	    
	    

}
