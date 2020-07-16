package dao.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Polype extends AnaPathologie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int nombre;
	@OneToOne
	private Siege siege;
	@OneToOne
	private TypeHystologique tupeHyst;
	@OneToOne
	private SousType sousType;
	@OneToOne
	private Dysplasie displasie;
	@OneToOne
	private LimiteResectEndo limite;
	public Polype() {
		super();
		
	}
	
	public Polype(Hopital hopital, DossierMedicale dossier, Date datePrel, TypePrelevement typePrel, String refAnaPath,
			String file, int nombre, Siege siege, TypeHystologique tupeHyst, SousType sousType, Dysplasie displasie,
			LimiteResectEndo limite) {
		super(hopital, dossier, datePrel, typePrel, refAnaPath, file);
		this.nombre = nombre;
		this.siege = siege;
		this.tupeHyst = tupeHyst;
		this.sousType = sousType;
		this.displasie = displasie;
		this.limite = limite;
	}

	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	public Siege getSiege() {
		return siege;
	}
	public void setSiege(Siege siege) {
		this.siege = siege;
	}
	public TypeHystologique getTupeHyst() {
		return tupeHyst;
	}
	public void setTupeHyst(TypeHystologique tupeHyst) {
		this.tupeHyst = tupeHyst;
	}
	public SousType getSousType() {
		return sousType;
	}
	public void setSousType(SousType sousType) {
		this.sousType = sousType;
	}
	public Dysplasie getDisplasie() {
		return displasie;
	}
	public void setDisplasie(Dysplasie displasie) {
		this.displasie = displasie;
	}
	public LimiteResectEndo getLimite() {
		return limite;
	}
	public void setLimite(LimiteResectEndo limite) {
		this.limite = limite;
	}
	@Override
	public String toString() {
		return "Polype [nombre=" + nombre + ", siege=" + siege + ", tupeHyst=" + tupeHyst + ", sousType=" + sousType
				+ ", displasie=" + displasie + ", limite=" + limite + "]";
	}

}
