package dao.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;




@Entity
@SecondaryTable(name= Genetique.TABLE_NAME, pkJoinColumns = @PrimaryKeyJoinColumn(name="id"))
public class Genetique extends ExamenMedical{
	
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "Genetique";
	
	private int numeroDossierGenetique;
	@Enumerated(EnumType.STRING)
    private InstMacro instabiliteMacroscopique;
	private Boolean mutaBRAF;
	@Enumerated(EnumType.STRING)
    private Resultat mutaKras;
	private Boolean mutaAPC;
    private Boolean mutaMYH;
    @OneToMany
    private List<MutaMMR> mutaMMR;
    private String autre;
	public Genetique() {
		super();
		
	}
	
	public Genetique(Hopital hopital, DossierMedicale dossier, int numeroDossierGenetique,
			InstMacro instabiliteMacroscopique, Boolean mutaBRAF, Resultat mutaKras, Boolean mutaAPC, Boolean mutaMYH,
			 String autre) {
		super(hopital, dossier);
		this.numeroDossierGenetique = numeroDossierGenetique;
		this.instabiliteMacroscopique = instabiliteMacroscopique;
		this.mutaBRAF = mutaBRAF;
		this.mutaKras = mutaKras;
		this.mutaAPC = mutaAPC;
		this.mutaMYH = mutaMYH;
		this.autre = autre;
	}

	public int getNumeroDossierGenetique() {
		return numeroDossierGenetique;
	}
	public void setNumeroDossierGenetique(int numeroDossierGenetique) {
		this.numeroDossierGenetique = numeroDossierGenetique;
	}
	public InstMacro getInstabiliteMacroscopique() {
		return instabiliteMacroscopique;
	}
	public void setInstabiliteMacroscopique(InstMacro instabiliteMacroscopique) {
		this.instabiliteMacroscopique = instabiliteMacroscopique;
	}
	public Boolean getMutaBRAF() {
		return mutaBRAF;
	}
	public void setMutaBRAF(Boolean mutaBRAF) {
		this.mutaBRAF = mutaBRAF;
	}
	public Resultat getMutaKras() {
		return mutaKras;
	}
	public void setMutaKras(Resultat mutaKras) {
		this.mutaKras = mutaKras;
	}
	public Boolean getMutaAPC() {
		return mutaAPC;
	}
	public void setMutaAPC(Boolean mutaAPC) {
		this.mutaAPC = mutaAPC;
	}
	public Boolean getMutaMYH() {
		return mutaMYH;
	}
	public void setMutaMYH(Boolean mutaMYH) {
		this.mutaMYH = mutaMYH;
	}
	public List<MutaMMR> getMutaMMR() {
		return mutaMMR;
	}
	public void setMutaMMR(List<MutaMMR> mutaMMR) {
		this.mutaMMR = mutaMMR;
	}
	public String getAutre() {
		return autre;
	}
	public void setAutre(String autre) {
		this.autre = autre;
	}

	@Override
	public String toString() {
		return "Genetique [numeroDossierGenetique=" + numeroDossierGenetique + ", instabiliteMacroscopique="
				+ instabiliteMacroscopique + ", mutaBRAF=" + mutaBRAF + ", mutaKras=" + mutaKras + ", mutaAPC="
				+ mutaAPC + ", mutaMYH=" + mutaMYH + ", autre=" + autre + "]";
	}
    
}
