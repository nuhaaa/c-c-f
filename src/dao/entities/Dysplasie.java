package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dysplasie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Dyplasie;
	private String dysplasie;
	public Dysplasie() {
		super();
	}
	public int getId() {
		return id_Dyplasie;
	}
	public void setId(int id) {
		this.id_Dyplasie = id;
	}
	public String getDysplasie() {
		return dysplasie;
	}
	public void setDysplasie(String dysplasie) {
		this.dysplasie = dysplasie;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dysplasie == null) ? 0 : dysplasie.hashCode());
		result = prime * result + id_Dyplasie;
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
		Dysplasie other = (Dysplasie) obj;
		if (dysplasie == null) {
			if (other.dysplasie != null)
				return false;
		} else if (!dysplasie.equals(other.dysplasie))
			return false;
		if (id_Dyplasie != other.id_Dyplasie)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Dysplasie [dysplasie=" + dysplasie + "]";
	}
	
}
