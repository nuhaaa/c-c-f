package dao.entities;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Temporal;



@Entity
@SecondaryTable(name= Endoscopie.TABLE_NAME, pkJoinColumns = @PrimaryKeyJoinColumn(name="id"))
public class Endoscopie extends ExamenMedical {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "Endoscopie";
	
	@Temporal(javax.persistence.TemporalType.DATE)
	protected Date dateEndo;
	@OneToOne
	protected TypeAndoscopie typeEndo;
	protected int numero;
	@Enumerated(EnumType.STRING)
	protected Anesthesie anestesie;
	protected String file;
	public Endoscopie() {
		super();
	}
	
	public Endoscopie(Hopital hopital, DossierMedicale dossier, Date dateEndo, TypeAndoscopie typeEndo, int numero,
			Anesthesie anestesie,String file) {
		super(hopital, dossier);
		this.dateEndo = dateEndo;
		this.typeEndo = typeEndo;
		this.numero = numero;
		this.anestesie = anestesie;
		this.file=file;
	}

	public Date getDateEndo() {
		return dateEndo;
	}
	public void setDateEndo(Date dateEndo) {
		this.dateEndo = dateEndo;
	}
	public TypeAndoscopie getTypeEndo() {
		return typeEndo;
	}
	public void setTypeEndo(TypeAndoscopie typeEndo){
		this.typeEndo = typeEndo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Anesthesie getAnestesie() {
		return anestesie;
	}
	public void setAnestesie(Anesthesie anestesie) {
		this.anestesie = anestesie;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "Endoscopie [dateEndo=" + dateEndo + ", typeEndo=" + typeEndo + ", numero=" + numero + ", anestesie="
				+ anestesie +", file " + file + "]";
	}
}
