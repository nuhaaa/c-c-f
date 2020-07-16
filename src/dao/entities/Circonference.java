package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Circonference implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_Cirf;
	private String circonference;
	public Circonference() {
		super();
	}
	public int getId() {
		return id_Cirf;
	}
	public void setId(int id) {
		this.id_Cirf = id;
	}
	public String getCirconference() {
		return circonference;
	}
	public void setCirconference(String circonference) {
		this.circonference = circonference;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((circonference == null) ? 0 : circonference.hashCode());
		result = prime * result + id_Cirf;
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
		Circonference other = (Circonference) obj;
		if (circonference == null) {
			if (other.circonference != null)
				return false;
		} else if (!circonference.equals(other.circonference))
			return false;
		if (id_Cirf != other.id_Cirf)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "" + circonference + "";
	}
	
}
