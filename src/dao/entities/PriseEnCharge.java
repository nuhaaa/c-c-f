package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PriseEnCharge implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private int id_Prise;
	 private String priseEnCharge;
	 
	public PriseEnCharge() {
		super();
		
	}
	public int getId() {
		return id_Prise;
	}
	public void setId(int id) {
		this.id_Prise = id;
	}
	public String getPriseEnCharge() {
		return priseEnCharge;
	}
	public void setPriseEnCharge(String priseEnCharge) {
		this.priseEnCharge = priseEnCharge;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_Prise;
		result = prime * result + ((priseEnCharge == null) ? 0 : priseEnCharge.hashCode());
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
		PriseEnCharge other = (PriseEnCharge) obj;
		if (id_Prise != other.id_Prise)
			return false;
		if (priseEnCharge == null) {
			if (other.priseEnCharge != null)
				return false;
		} else if (!priseEnCharge.equals(other.priseEnCharge))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return  priseEnCharge ;
	}
	
}

