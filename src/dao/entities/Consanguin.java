package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Consanguin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Consg;
	@OneToOne
	private Individu casIndex;
	@OneToOne
	private Individu membre;
	private String degree;
	
	public Consanguin() {
		super();
	}
	
	public Consanguin(Individu casIndex, Individu membre, String degree) {
		super();
		this.casIndex = casIndex;
		this.membre = membre;
		this.degree = degree;
	}

	public int getId() {
		return id_Consg;
	}
	public void setId(int id) {
		this.id_Consg = id;
	}
	public Individu getCasIndex() {
		return casIndex;
	}
	public void setCasIndex(Individu casIndex) {
		this.casIndex = casIndex;
	}
	public Individu getMembre() {
		return membre;
	}
	public void setMembre(Individu membre) {
		this.membre = membre;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((casIndex == null) ? 0 : casIndex.hashCode());
		result = prime * result + id_Consg;
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
		Consanguin other = (Consanguin) obj;
		if (casIndex == null) {
			if (other.casIndex != null)
				return false;
		} else if (!casIndex.equals(other.casIndex))
			return false;
		if (id_Consg != other.id_Consg)
			return false;
		return true;
	}
	
	
}	
