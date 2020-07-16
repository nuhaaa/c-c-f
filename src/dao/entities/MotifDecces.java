package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class MotifDecces implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_Motif;
	private String motif;
	
	public MotifDecces() {
		super();
	}
	public int getId() {
		return id_Motif;
	}
	public void setId(int id) {
		this.id_Motif = id;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_Motif;
		result = prime * result + ((motif == null) ? 0 : motif.hashCode());
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
		MotifDecces other = (MotifDecces) obj;
		if (id_Motif != other.id_Motif)
			return false;
		if (motif == null) {
			if (other.motif != null)
				return false;
		} else if (!motif.equals(other.motif))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "" + motif + "";
	}
	 
}
