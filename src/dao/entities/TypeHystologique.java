package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypeHystologique implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	private String typeHysto;
	public TypeHystologique() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeHysto() {
		return typeHysto;
	}
	public void setTypeHysto(String typeHysto) {
		this.typeHysto = typeHysto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((typeHysto == null) ? 0 : typeHysto.hashCode());
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
		TypeHystologique other = (TypeHystologique) obj;
		if (id != other.id)
			return false;
		if (typeHysto == null) {
			if (other.typeHysto != null)
				return false;
		} else if (!typeHysto.equals(other.typeHysto))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "" + typeHysto + "";
	}
	

}
