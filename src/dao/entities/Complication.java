package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Complication implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_Comp;
	private String complication;
	
//	@ManyToOne
//	private EndoAnormal endoAnormal;
	
	public Complication() {
		super();
	}
	public int getId() {
		return id_Comp;
	}
	public void setId(int id) {
		this.id_Comp = id;
	}
	public String getComplication() {
		return complication;
	}
	public void setComplcation(String complication) {
		this.complication = complication;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((complication == null) ? 0 : complication.hashCode());
		result = prime * result + id_Comp;
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
		Complication other = (Complication) obj;
		if (complication == null) {
			if (other.complication != null)
				return false;
		} else if (!complication.equals(other.complication))
			return false;
		if (id_Comp != other.id_Comp)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "" + complication + "";
	}
	
}
