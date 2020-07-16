package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypeExerese implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String typeExerese;
	public TypeExerese() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeExerese() {
		return typeExerese;
	}
	public void setTypeExerese(String typeExerese) {
		this.typeExerese = typeExerese;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((typeExerese == null) ? 0 : typeExerese.hashCode());
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
		TypeExerese other = (TypeExerese) obj;
		if (id != other.id)
			return false;
		if (typeExerese == null) {
			if (other.typeExerese != null)
				return false;
		} else if (!typeExerese.equals(other.typeExerese))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "" + typeExerese + "";
	}
	
}
