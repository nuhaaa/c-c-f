package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Detail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Detail;
	private Siege siege;
	private Float distance;
	private Float sphinecter;
	
	@OneToOne(mappedBy = "detail")
	private CategorieSynAnormal masse;
	public Detail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Detail(int id, Siege siege, Float distance, Float sphinecter) {
		super();
		this.id_Detail = id;
		this.siege = siege;
		this.distance = distance;
		this.sphinecter = sphinecter;
	}

	public int getId() {
		return id_Detail;
	}
	public void setId(int id) {
		this.id_Detail = id;
	}
	public Siege getSiege() {
		return siege;
	}
	public void setSiege(Siege siege) {
		this.siege = siege;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public Float getSphinecter() {
		return sphinecter;
	}

	public void setSphinecter(Float sphinecter) {
		this.sphinecter = sphinecter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_Detail;
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
		Detail other = (Detail) obj;
		if (id_Detail != other.id_Detail)
			return false;
		return true;
	}
	
}
