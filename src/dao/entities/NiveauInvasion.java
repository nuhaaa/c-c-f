package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NiveauInvasion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_Niveau;
	private String niveau;
	public NiveauInvasion() {
		super();
	}
	public int getId() {
		return id_Niveau;
	}
	public void setId(int id) {
		this.id_Niveau = id;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_Niveau;
		result = prime * result + ((niveau == null) ? 0 : niveau.hashCode());
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
		NiveauInvasion other = (NiveauInvasion) obj;
		if (id_Niveau != other.id_Niveau)
			return false;
		if (niveau == null) {
			if (other.niveau != null)
				return false;
		} else if (!niveau.equals(other.niveau))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NiveauInvasion [niveau=" + niveau + "]";
	}
	

}
