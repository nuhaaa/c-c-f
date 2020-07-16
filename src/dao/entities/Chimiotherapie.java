package dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Chimiotherapie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Chim;
	@Enumerated(EnumType.STRING)
	private Deroulement deroulement;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date debut;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fin;
	
	@OneToOne(mappedBy="chimiotherapie")
	private Traitement traitement;
	
	public Chimiotherapie() {
		super();
		
	}
	
	public Chimiotherapie(Deroulement deroulement, Date debut, Date fin) {
		super();
		this.deroulement = deroulement;
		this.debut = debut;
		this.fin = fin;
	}

	public int getId() {
		return id_Chim;
	}
	public void setId(int id) {
		this.id_Chim = id;
	}
	public Deroulement getDeroulement() {
		return deroulement;
	}
	public void setDeroulement(Deroulement deroulement) {
		this.deroulement = deroulement;
	}
	public Date getDebut() {
		return debut;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	
	public Traitement getTraitement() {
		return traitement;
	}
	public void setTraitement(Traitement traitement) {
		this.traitement = traitement;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((debut == null) ? 0 : debut.hashCode());
		result = prime * result + ((deroulement == null) ? 0 : deroulement.hashCode());
		result = prime * result + ((fin == null) ? 0 : fin.hashCode());
		result = prime * result + id_Chim;
		result = prime * result + ((traitement == null) ? 0 : traitement.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chimiotherapie other = (Chimiotherapie) obj;
		if (debut == null) {
			if (other.debut != null)
				return false;
		} else if (!debut.equals(other.debut))
			return false;
		if (deroulement != other.deroulement)
			return false;
		if (fin == null) {
			if (other.fin != null)
				return false;
		} else if (!fin.equals(other.fin))
			return false;
		if (id_Chim != other.id_Chim)
			return false;
		if (traitement == null) {
			if (other.traitement != null)
				return false;
		} else if (!traitement.equals(other.traitement))
			return false;
		return true;
	}
	
}
