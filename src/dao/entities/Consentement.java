package dao.entities;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Consentement implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_Const;
	@Temporal(value = TemporalType.DATE)
	private Date dateConsentement;
	
	@OneToOne(mappedBy="consentement")
	private Individu individu;
	
	public Consentement() {
		super();
		
	}
	
	public Consentement(Date dateConsentement, Individu individu) {
		super();
		this.dateConsentement = dateConsentement;
		this.individu = individu;
	}

	public int getId() {
		return id_Const;
	}
	public void setId(int id) {
		this.id_Const = id;
	}
	public Date getDateConsentement() {
		return dateConsentement;
	}
	public void setDateConsentement(Date dateConsentement) {
		this.dateConsentement = dateConsentement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateConsentement == null) ? 0 : dateConsentement.hashCode());
		result = prime * result + id_Const;
		result = prime * result + ((individu == null) ? 0 : individu.hashCode());
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
		Consentement other = (Consentement) obj;
		if (dateConsentement == null) {
			if (other.dateConsentement != null)
				return false;
		} else if (!dateConsentement.equals(other.dateConsentement))
			return false;
		if (id_Const != other.id_Const)
			return false;
		if (individu == null) {
			if (other.individu != null)
				return false;
		} else if (!individu.equals(other.individu))
			return false;
		return true;
	}
	 
}
