package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stroma implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String stroma;
	public Stroma() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStroma() {
		return stroma;
	}
	public void setStroma(String stroma) {
		this.stroma = stroma;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((stroma == null) ? 0 : stroma.hashCode());
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
		Stroma other = (Stroma) obj;
		if (id != other.id)
			return false;
		if (stroma == null) {
			if (other.stroma != null)
				return false;
		} else if (!stroma.equals(other.stroma))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "" + stroma + "";
	}
	

}
