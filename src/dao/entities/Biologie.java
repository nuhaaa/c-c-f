package dao.entities;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Temporal;

@Entity
@SecondaryTable(name= Biologie.TABLE_NAME, pkJoinColumns = @PrimaryKeyJoinColumn(name="id"))
public class Biologie extends ExamenMedical {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "Biologie";
	
	private String analyse;
	private Float valeur;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dataeAnalyse;
	
	public Biologie() {
		super();
	}
	
	public Biologie(Hopital hopital, DossierMedicale dossier, String analyse, Float valeur, Date dataeAnalyse) {
		super(hopital, dossier);
		this.analyse = analyse;
		this.valeur = valeur;
		this.dataeAnalyse = dataeAnalyse;
	}

	public String getAnalyse() {
		return analyse;
	}
	public void setAnalyse(String analyse) {
		this.analyse = analyse;
	}
	public Float getValeur() {
		return valeur;
	}
	public void setValeur(Float valeur) {
		this.valeur = valeur;
	}
	public Date getDataeAnalyse() {
		return dataeAnalyse;
	}
	public void setDataeAnalyse(Date dataeAnalyse) {
		this.dataeAnalyse = dataeAnalyse;
	}

	@Override
	public String toString() {
		return "Biologie [analyse=" + analyse + ", valeur=" + valeur + ", dataeAnalyse=" + dataeAnalyse + "]";
	}
	    
	}


