package dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Radiotherapie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Radio;
	@Enumerated(EnumType.STRING)
	private Deroulement deroulement;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date debut;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fin;
	@OneToOne(mappedBy="radiotherapie")
	private Traitement traitement;
	public Radiotherapie() {
		super();
	
	}
	
	public Radiotherapie(Deroulement deroulement, Date debut, Date fin) {
		super();
		this.deroulement = deroulement;
		this.debut = debut;
		this.fin = fin;
	}

	public int getId() {
		return id_Radio;
	}
	public void setId(int id) {
		this.id_Radio = id;
	}
	public Deroulement getDeroulement() {
		return deroulement;
	}
	public void setDeroulement(Deroulement deroulement) {
		this.deroulement = deroulement;
	}
	public Date getDebut() {
		return debut;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	
}
