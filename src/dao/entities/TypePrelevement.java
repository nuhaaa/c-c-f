package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypePrelevement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	private String typePrevement;
	public TypePrelevement() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypePrevement() {
		return typePrevement;
	}
	public void setTypePrevement(String typePrevement) {
		this.typePrevement = typePrevement;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((typePrevement == null) ? 0 : typePrevement.hashCode());
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
		TypePrelevement other = (TypePrelevement) obj;
		if (id != other.id)
			return false;
		if (typePrevement == null) {
			if (other.typePrevement != null)
				return false;
		} else if (!typePrevement.equals(other.typePrevement))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "" + typePrevement + "";
	}
	

}
