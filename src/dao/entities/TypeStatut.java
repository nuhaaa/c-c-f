package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypeStatut implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	private String typeCancer;
	public TypeStatut() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((typeCancer == null) ? 0 : typeCancer.hashCode());
		return result;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeCancer() {
		return typeCancer;
	}
	public void setTypeCancer(String typeCancer) {
		this.typeCancer = typeCancer;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypeStatut other = (TypeStatut) obj;
		if (id != other.id)
			return false;
		if (typeCancer == null) {
			if (other.typeCancer != null)
				return false;
		} else if (!typeCancer.equals(other.typeCancer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return typeCancer;
	}


}
