package dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Chirurgie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Chirg;
	private String refAnaPath;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date date;
	private String service;
	private int temps;
	private int numDossier;
	@OneToOne
	private TypeExerese type;
	@OneToOne
	private Elargissement elargie;
	@OneToOne
	private Geste geste;
	@OneToOne
	private RRscoring scoring;
	@OneToOne
	private Complication complication;
	
	@OneToOne(mappedBy="chirurgie")
	private Traitement traitement;
	
	public Chirurgie() {
		super();
		
	}
	
	public Chirurgie(String refAnaPath, Date date, String service, int temps, TypeExerese type, Elargissement elargie,
			Geste geste, RRscoring scoring, Complication complication) {
		super();
		this.refAnaPath = refAnaPath;
		this.date = date;
		this.service = service;
		this.temps = temps;
		this.type = type;
		this.elargie = elargie;
		this.geste = geste;
		this.scoring = scoring;
		this.complication = complication;
	}

	public int getId() {
		return id_Chirg;
	}
	public void setId(int id) {
		this.id_Chirg = id;
	}
	public String getRefAnaPath() {
		return refAnaPath;
	}
	public void setRefAnaPath(String refAnaPath) {
		this.refAnaPath = refAnaPath;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public int getTemps() {
		return temps;
	}
	public void setTemps(int temps) {
		this.temps = temps;
	}
	public int getNumDossier() {
		return numDossier;
	}
	public void setNumDossier(int numDossier) {
		this.numDossier = numDossier;
	}
	public TypeExerese getType() {
		return type;
	}
	public void setType(TypeExerese type) {
		this.type = type;
	}
	public Elargissement getElargie() {
		return elargie;
	}
	public void setElargie(Elargissement elargie) {
		this.elargie = elargie;
	}
	public Geste getGeste() {
		return geste;
	}
	public void setGeste(Geste geste) {
		this.geste = geste;
	}
	public RRscoring getScoring() {
		return scoring;
	}
	public void setScoring(RRscoring scoring) {
		this.scoring = scoring;
	}
	public Complication getComplication() {
		return complication;
	}
	public void setComplication(Complication complication) {
		this.complication = complication;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_Chirg;
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
		Chirurgie other = (Chirurgie) obj;
		if (id_Chirg != other.id_Chirg)
			return false;
		return true;
	}
	
	
	
}
