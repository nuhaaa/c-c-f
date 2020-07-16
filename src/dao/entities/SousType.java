package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SousType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	private String sousType;
	public SousType() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSousType() {
		return sousType;
	}
	public void setSousType(String sousType) {
		this.sousType = sousType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((sousType == null) ? 0 : sousType.hashCode());
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
		SousType other = (SousType) obj;
		if (id != other.id)
			return false;
		if (sousType == null) {
			if (other.sousType != null)
				return false;
		} else if (!sousType.equals(other.sousType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "" + sousType + "";
	}
	

}
