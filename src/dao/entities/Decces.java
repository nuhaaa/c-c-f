package dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Decces implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_Decces;
	@OneToOne
	private Individu individu;
	@OneToOne
	private MotifDecces motif;
	@Temporal(TemporalType.DATE)
	private Date dateDecces;
	
	public Decces() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_Decces() {
		return id_Decces;
	}
	
	public Decces(Individu individu, MotifDecces motif, Date dateDecces) {
		super();
		this.individu = individu;
		this.motif = motif;
		this.dateDecces = dateDecces;
	}
	public void setId_Decces(int id_Decces) {
		this.id_Decces = id_Decces;
	}
	public Individu getIndividu() {
		return individu;
	}
	public void setIndividu(Individu individu) {
		this.individu = individu;
	}
	public MotifDecces getMotif() {
		return motif;
	}
	public void setMotif(MotifDecces motif) {
		this.motif = motif;
	}
	public Date getDateDecces() {
		return dateDecces;
	}
	public void setDateDecces(Date dateDecces) {
		this.dateDecces = dateDecces;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_Decces;
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
		Decces other = (Decces) obj;
		if (id_Decces != other.id_Decces)
			return false;
		return true;
	}
	
}
