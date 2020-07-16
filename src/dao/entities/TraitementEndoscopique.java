package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TraitementEndoscopique implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	private String refAnaPath;
	private TypeIntervention intervention;
	private Complication complication;
	
	@OneToOne(mappedBy="TraitEndo") // bidirectionnelle on persiste les deux
	private Traitement traitement;
	public TraitementEndoscopique() {
		super();
	}
	
	public TraitementEndoscopique(String refAnaPath, TypeIntervention intervention, Complication complication) {
		super();
		this.refAnaPath = refAnaPath;
		this.intervention = intervention;
		this.complication = complication;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRefAnaPath() {
		return refAnaPath;
	}
	public void setRefAnaPath(String refAnaPath) {
		this.refAnaPath = refAnaPath;
	}
	public TypeIntervention getIntervention() {
		return intervention;
	}
	public void setIntervention(TypeIntervention intervention) {
		this.intervention = intervention;
	}
	public Complication getComplication() {
		return complication;
	}
	public void setComplication(Complication complication) {
		this.complication = complication;
	}
	

}
