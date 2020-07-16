package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LimiteResectEndo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Limite;
	private String limite;
	public LimiteResectEndo() {
		super();
	}
	public int getId() {
		return id_Limite;
	}
	public void setId(int id) {
		this.id_Limite = id;
	}
	public String getLimite() {
		return limite;
	}
	public void setLimite(String limite) {
		this.limite = limite;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_Limite;
		result = prime * result + ((limite == null) ? 0 : limite.hashCode());
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
		LimiteResectEndo other = (LimiteResectEndo) obj;
		if (id_Limite != other.id_Limite)
			return false;
		if (limite == null) {
			if (other.limite != null)
				return false;
		} else if (!limite.equals(other.limite))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LimiteResectEndo [limite=" + limite + "]";
	}
	
}
