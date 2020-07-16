package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CategorieSynAnormal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Synd;
	private String syndrome;
	@OneToOne
	private Detail detail;
	public CategorieSynAnormal() {
		super();
	}
	
	public CategorieSynAnormal(int id, String syndrome) {
		super();
		this.id_Synd = id;
		this.syndrome = syndrome;
	}
	
	public CategorieSynAnormal(int id, String syndrome, Detail detail) {
		super();
		this.id_Synd = id;
		this.syndrome = syndrome;
		this.detail = detail;
	}

	public int getId() {
		return id_Synd;
	}
	public void setId(int id) {
		this.id_Synd = id;
	}
	public String getSyndrome() {
		return syndrome;
	}
	public void setSyndrome(String syndrome) {
		this.syndrome = syndrome;
	}
	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_Synd;
		result = prime * result + ((syndrome == null) ? 0 : syndrome.hashCode());
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
		CategorieSynAnormal other = (CategorieSynAnormal) obj;
		if (id_Synd != other.id_Synd)
			return false;
		if (syndrome == null) {
			if (other.syndrome != null)
				return false;
		} else if (!syndrome.equals(other.syndrome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + syndrome + "";
	}

	
	
}
