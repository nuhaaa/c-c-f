package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Geste implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_Geste;
	private String geste;
	public Geste() {
		super();
	}
	public int getId() {
		return id_Geste;
	}
	public void setId(int id) {
		this.id_Geste = id;
	}
	public String getGeste() {
		return geste;
	}
	public void setGeste(String geste) {
		this.geste = geste;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geste == null) ? 0 : geste.hashCode());
		result = prime * result + id_Geste;
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
		Geste other = (Geste) obj;
		if (geste == null) {
			if (other.geste != null)
				return false;
		} else if (!geste.equals(other.geste))
			return false;
		if (id_Geste != other.id_Geste)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "" + geste + "";
	}
	
}
