package dao.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Lesion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Lesion;
	@OneToOne(cascade=CascadeType.ALL)
	private Polypose polype;
	@OneToOne(cascade=CascadeType.ALL)
	private MasseTumorale tumeurSynchrone;
	private boolean MICI;
	private boolean ulcerations;
	private String autre;
	
	public Lesion() {
		super();
	}
	
	public Lesion(Polypose polype, MasseTumorale tumeurSynchrone, boolean mICI, boolean ulcerations, String autre) {
		super();
		this.polype = polype;
		this.tumeurSynchrone = tumeurSynchrone;
		MICI = mICI;
		this.ulcerations = ulcerations;
		this.autre = autre;
	}

	public int getId() {
		return id_Lesion;
	}
	public void setId(int id) {
		this.id_Lesion = id;
	}
	public Polypose getPolype() {
		return polype;
	}
	public void setPolype(Polypose polype) {
		this.polype = polype;
	}
	public MasseTumorale getTumeurSynchrone() {
		return tumeurSynchrone;
	}
	public void setTumeurSynchrone(MasseTumorale tumeurSynchrone) {
		this.tumeurSynchrone = tumeurSynchrone;
	}
	public boolean isMICI() {
		return MICI;
	}
	public void setMICI(boolean mICI) {
		MICI = mICI;
	}
	public boolean isUlcerations() {
		return ulcerations;
	}
	public void setUlcerations(boolean ulcerations) {
		this.ulcerations = ulcerations;
	}
	public String getAutre() {
		return autre;
	}
	public void setAutre(String autre) {
		this.autre = autre;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_Lesion;
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
		Lesion other = (Lesion) obj;
		if (id_Lesion != other.id_Lesion)
			return false;
		return true;
	}
	

}
