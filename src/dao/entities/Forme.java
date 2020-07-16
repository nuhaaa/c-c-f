package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Forme implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_Forme;
	private String forme;
	public Forme() {
		super();
		
	}
	public int getId() {
		return id_Forme;
	}
	public void setId(int id) {
		this.id_Forme = id;
	}
	public String getForme() {
		return forme;
	}
	public void setForme(String forme) {
		this.forme = forme;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((forme == null) ? 0 : forme.hashCode());
		result = prime * result + id_Forme;
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
		Forme other = (Forme) obj;
		if (forme == null) {
			if (other.forme != null)
				return false;
		} else if (!forme.equals(other.forme))
			return false;
		if (id_Forme != other.id_Forme)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return forme ;
	}
	
}
