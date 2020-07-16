package dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

@Entity
@SecondaryTable(name= Infirmier.TABLE_NAME, pkJoinColumns = @PrimaryKeyJoinColumn(name="id"))
public class Infirmier{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "Infirmier";
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String infirmier;
    private String specialte;
    private String statut;
    
    @ManyToOne
    private Hopital hopital;

	public Infirmier() {
		
	}

	public Infirmier(String nom, String prenom, String email, String login, String password) {
		///super(nom, prenom, email, login, password);
		// TODO Auto-generated constructor stub
	}
	
	

	public Infirmier(String nom, String prenom, String email, String login, String password, String infirmier,
			String specialte, String statut, Hopital hopital) {
		//super(nom, prenom, email, login, password);
		this.infirmier = infirmier;
		this.specialte = specialte;
		this.statut = statut;
		this.hopital = hopital;
	}

	public String getInfirmier() {
		return infirmier;
	}

	public void setInfirmier(String infirmier) {
		this.infirmier = infirmier;
	}

	public String getSpecialte() {
		return specialte;
	}

	public void setSpecialte(String specialte) {
		this.specialte = specialte;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Hopital getHopital() {
		return hopital;
	}

	public void setHopital(Hopital hopital) {
		this.hopital = hopital;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((hopital == null) ? 0 : hopital.hashCode());
		result = prime * result + ((infirmier == null) ? 0 : infirmier.hashCode());
		result = prime * result + ((specialte == null) ? 0 : specialte.hashCode());
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Infirmier other = (Infirmier) obj;
		if (hopital == null) {
			if (other.hopital != null)
				return false;
		} else if (!hopital.equals(other.hopital))
			return false;
		if (infirmier == null) {
			if (other.infirmier != null)
				return false;
		} else if (!infirmier.equals(other.infirmier))
			return false;
		if (specialte == null) {
			if (other.specialte != null)
				return false;
		} else if (!specialte.equals(other.specialte))
			return false;
		if (statut == null) {
			if (other.statut != null)
				return false;
		} else if (!statut.equals(other.statut))
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "Infirmier [infirmier=" + nom + ", prenon=" + prenom + "]";
//	}
    
    

}
