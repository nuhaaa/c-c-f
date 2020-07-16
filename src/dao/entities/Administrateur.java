package dao.entities;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name="administrateur")
public class Administrateur extends Medecin{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	public Administrateur() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public Administrateur() {
//		//super(nom, prenom, email, login, password, medecin, specialte, statut, hopital);
//		// TODO Auto-generated constructor stub
//	}

//	@Override
//	public String toString() {
//		return "Administrateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", login="
//				+ login + ", password=" + password + ", photo=" + Arrays.toString(photo) + "]";
//	}
	
	
}
