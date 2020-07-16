package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MutaMMR implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idMuta;
	private String MMR;
	@Enumerated(EnumType.STRING)
	private Resultat resultat;
	
	public MutaMMR() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return idMuta;
	}
	public void setId(int id) {
		this.idMuta = id;
	}
	
	public String getMMR() {
		return MMR;
	}
	public void setMMR(String mMR) {
		MMR = mMR;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((MMR == null) ? 0 : MMR.hashCode());
		result = prime * result + idMuta;
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
		MutaMMR other = (MutaMMR) obj;
		if (MMR != other.MMR)
			return false;
		if (idMuta != other.idMuta)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "" + MMR + "";
	}
	

}
