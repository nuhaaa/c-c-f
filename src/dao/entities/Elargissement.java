package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Elargissement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_Elarg;
	private String elargie;
	public Elargissement() {
		super();
	}
	public int getId() {
		return id_Elarg;
	}
	public void setId(int id) {
		this.id_Elarg = id;
	}
	public String getElargie() {
		return elargie;
	}
	public void setElargie(String elargie) {
		this.elargie = elargie;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elargie == null) ? 0 : elargie.hashCode());
		result = prime * result + id_Elarg;
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
		Elargissement other = (Elargissement) obj;
		if (elargie == null) {
			if (other.elargie != null)
				return false;
		} else if (!elargie.equals(other.elargie))
			return false;
		if (id_Elarg != other.id_Elarg)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Elargissement [elargie=" + elargie + "]";
	}
	
}
