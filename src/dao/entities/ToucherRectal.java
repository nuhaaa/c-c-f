package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ToucherRectal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	private String toucher;
	
	public ToucherRectal() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToucher() {
		return toucher;
	}
	public void setToucher(String toucher) {
		this.toucher = toucher;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((toucher == null) ? 0 : toucher.hashCode());
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
		ToucherRectal other = (ToucherRectal) obj;
		if (id != other.id)
			return false;
		if (toucher == null) {
			if (other.toucher != null)
				return false;
		} else if (!toucher.equals(other.toucher))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "" + toucher + "";
	}
	
}
