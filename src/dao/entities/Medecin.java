package dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
//@SecondaryTable(name = Medecin.TABLE_NAME, pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class Medecin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nom;
	private String prenom;
	private String specialte;
	private String statut;

	@ManyToOne
	private Hopital hopital;

	public Medecin() {
	}

//	public Medecin(String nom, String prenom, String email, String login, String password) {
//		// super(nom, prenom, email, login, password);
//
//	}

	public Medecin(String nom, String prenom,String specialte, String statut, Hopital hopital) {
		this.nom = nom;
		this.prenom = prenom;
		this.specialte = specialte;
		this.statut = statut;
		this.hopital = hopital;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSpecialte() {
		return specialte;
	}

	public void setSpecialte(String specialte) {
		this.specialte = specialte;
	}

//	    public String getLogin() {
//			return login;
//		}
//
//
//
//		public void setLogin(String login) {
//			this.login = login;
//		}
//
//
//
//		public String getPassword() {
//			return password;
//		}
//
//
//
//		public void setPassword(String password) {
//			this.password = password;
//		}

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
		// result = prime * result + ((login == null) ? 0 : login.hashCode());
		//result = prime * result + ((medecin == null) ? 0 : medecin.hashCode());
		// result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Medecin other = (Medecin) obj;
		if (hopital == null) {
			if (other.hopital != null)
				return false;
		} else if (!hopital.equals(other.hopital))
			return false;
//			if (login == null) {
//				if (other.login != null)
//					return false;
//			} else if (!login.equals(other.login))
//				return false;
//		if (medecin == null) {
//			if (other.medecin != null)
//				return false;
//		} else if (!medecin.equals(other.medecin))
//			return false;
//			if (password == null) {
//				if (other.password != null)
//					return false;
//			} else if (!password.equals(other.password))
//				return false;
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

	@Override
	public String toString() {
		return "Medecin [nom=" + nom + ", prenom=" + prenom + ", specialte=" + specialte + ", statut=" + statut
				+ ", hopital=" + hopital + "]";
	}



}
