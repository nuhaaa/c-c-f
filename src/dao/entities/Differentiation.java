package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Differentiation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_Diff;
	private String diff;
	public Differentiation() {
		super();
	}
	public int getId() {
		return id_Diff;
	}
	public void setId(int id) {
		this.id_Diff = id;
	}
	public String getDiff() {
		return diff;
	}
	public void setDiff(String diff) {
		this.diff = diff;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diff == null) ? 0 : diff.hashCode());
		result = prime * result + id_Diff;
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
		Differentiation other = (Differentiation) obj;
		if (diff == null) {
			if (other.diff != null)
				return false;
		} else if (!diff.equals(other.diff))
			return false;
		if (id_Diff != other.id_Diff)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Differentiation [diff=" + diff + "]";
	}
	

}
