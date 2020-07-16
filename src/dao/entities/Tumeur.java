package dao.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Tumeur extends AnaPathologie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	private Siege siege;
	@OneToOne
    private TypeHystologique typeHysto;
	@OneToOne
    private Differentiation differentiation;
    private Float compColMuq;
    private Float compCelInd;
    @OneToOne
    private Stroma stroma;
    @OneToOne(cascade=CascadeType.ALL)
    private Lesion lesion;
    private Boolean embolVasc;
    private Boolean engaiPeri;
    @Enumerated(EnumType.STRING)
    private LimiteChPro limiteChirgPro;
    @Enumerated(EnumType.STRING)
    private LimiteChDist limiteChirgDist;
    @OneToOne
    private NiveauInvasion niveauInvasion;
    private String ganglions;
    private int nbreGanglions;
    private boolean localPerit;
    
	public Tumeur() {
		super();
		
	}
	
	
	public Tumeur( Hopital hopital, DossierMedicale dossier, Date datePrel, TypePrelevement typePrel,
			String refAnaPath, String file, Siege siege, TypeHystologique typeHysto, Differentiation differentiation,
			Float compColMuq, Float compCelInd, Stroma stroma, Lesion lesion, Boolean embolVasc, Boolean engaiPeri,
			LimiteChPro limiteChirgPro, LimiteChDist limiteChirgDist, NiveauInvasion niveauInvasion, String ganglions,
			int nbreGanglions, boolean localPerit) {
		super(hopital, dossier, datePrel, typePrel, refAnaPath, file);
		this.siege = siege;
		this.typeHysto = typeHysto;
		this.differentiation = differentiation;
		this.compColMuq = compColMuq;
		this.compCelInd = compCelInd;
		this.stroma = stroma;
		this.lesion = lesion;
		this.embolVasc = embolVasc;
		this.engaiPeri = engaiPeri;
		this.limiteChirgPro = limiteChirgPro;
		this.limiteChirgDist = limiteChirgDist;
		this.niveauInvasion = niveauInvasion;
		this.ganglions = ganglions;
		this.nbreGanglions = nbreGanglions;
		this.localPerit = localPerit;
	}


	public Siege getSiege() {
		return siege;
	}
	public void setSiege(Siege siege) {
		this.siege = siege;
	}
	public TypeHystologique getTypeHysto() {
		return typeHysto;
	}
	public void setTypeHysto(TypeHystologique typeHysto) {
		this.typeHysto = typeHysto;
	}
	public Differentiation getDifferentiation() {
		return differentiation;
	}
	public void setDifferentiation(Differentiation differentiation) {
		this.differentiation = differentiation;
	}
	public Float getCompColMuq() {
		return compColMuq;
	}
	public void setCompColMuq(Float compColMuq) {
		this.compColMuq = compColMuq;
	}
	public Float getCompCelInd() {
		return compCelInd;
	}
	public void setCompCelInd(Float compCelInd) {
		this.compCelInd = compCelInd;
	}
	public Stroma getStroma() {
		return stroma;
	}
	public void setStroma(Stroma stroma) {
		this.stroma = stroma;
	}
	public Lesion getLesion() {
		return lesion;
	}
	public void setLesion(Lesion lesion) {
		this.lesion = lesion;
	}
	public Boolean getEmbolVasc() {
		return embolVasc;
	}
	public void setEmbolVasc(Boolean embolVasc) {
		this.embolVasc = embolVasc;
	}
	public Boolean getEngaiPeri() {
		return engaiPeri;
	}
	public void setEngaiPeri(Boolean engaiPeri) {
		this.engaiPeri = engaiPeri;
	}
	public LimiteChPro getLimiteChirgPro() {
		return limiteChirgPro;
	}
	public void setLimiteChirgPro(LimiteChPro limiteChirgPro) {
		this.limiteChirgPro = limiteChirgPro;
	}
	public LimiteChDist getLimiteChirgDist() {
		return limiteChirgDist;
	}
	public void setLimiteChirgDist(LimiteChDist limiteChirgDist) {
		this.limiteChirgDist = limiteChirgDist;
	}
	public NiveauInvasion getNiveauInvasion() {
		return niveauInvasion;
	}
	public void setNiveauInvasion(NiveauInvasion niveauInvasion) {
		this.niveauInvasion = niveauInvasion;
	}
	public String getGanglions() {
		return ganglions;
	}
	public void setGanglions(String ganglions) {
		this.ganglions = ganglions;
	}
	public int getNbreGanglions() {
		return nbreGanglions;
	}
	public void setNbreGanglions(int nbreGanglions) {
		this.nbreGanglions = nbreGanglions;
	}
	public boolean isLocalPerit() {
		return localPerit;
	}
	public void setLocalPerit(boolean localPerit) {
		this.localPerit = localPerit;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((compCelInd == null) ? 0 : compCelInd.hashCode());
		result = prime * result + ((compColMuq == null) ? 0 : compColMuq.hashCode());
		result = prime * result + ((differentiation == null) ? 0 : differentiation.hashCode());
		result = prime * result + ((embolVasc == null) ? 0 : embolVasc.hashCode());
		result = prime * result + ((engaiPeri == null) ? 0 : engaiPeri.hashCode());
		result = prime * result + ((ganglions == null) ? 0 : ganglions.hashCode());
		result = prime * result + ((lesion == null) ? 0 : lesion.hashCode());
		result = prime * result + ((limiteChirgDist == null) ? 0 : limiteChirgDist.hashCode());
		result = prime * result + ((limiteChirgPro == null) ? 0 : limiteChirgPro.hashCode());
		result = prime * result + (localPerit ? 1231 : 1237);
		result = prime * result + nbreGanglions;
		result = prime * result + ((niveauInvasion == null) ? 0 : niveauInvasion.hashCode());
		result = prime * result + ((siege == null) ? 0 : siege.hashCode());
		result = prime * result + ((stroma == null) ? 0 : stroma.hashCode());
		result = prime * result + ((typeHysto == null) ? 0 : typeHysto.hashCode());
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
		Tumeur other = (Tumeur) obj;
		if (compCelInd == null) {
			if (other.compCelInd != null)
				return false;
		} else if (!compCelInd.equals(other.compCelInd))
			return false;
		if (compColMuq == null) {
			if (other.compColMuq != null)
				return false;
		} else if (!compColMuq.equals(other.compColMuq))
			return false;
		if (differentiation == null) {
			if (other.differentiation != null)
				return false;
		} else if (!differentiation.equals(other.differentiation))
			return false;
		if (embolVasc == null) {
			if (other.embolVasc != null)
				return false;
		} else if (!embolVasc.equals(other.embolVasc))
			return false;
		if (engaiPeri == null) {
			if (other.engaiPeri != null)
				return false;
		} else if (!engaiPeri.equals(other.engaiPeri))
			return false;
		if (ganglions == null) {
			if (other.ganglions != null)
				return false;
		} else if (!ganglions.equals(other.ganglions))
			return false;
		if (lesion == null) {
			if (other.lesion != null)
				return false;
		} else if (!lesion.equals(other.lesion))
			return false;
		if (limiteChirgDist != other.limiteChirgDist)
			return false;
		if (limiteChirgPro != other.limiteChirgPro)
			return false;
		if (localPerit != other.localPerit)
			return false;
		if (nbreGanglions != other.nbreGanglions)
			return false;
		if (niveauInvasion == null) {
			if (other.niveauInvasion != null)
				return false;
		} else if (!niveauInvasion.equals(other.niveauInvasion))
			return false;
		if (siege == null) {
			if (other.siege != null)
				return false;
		} else if (!siege.equals(other.siege))
			return false;
		if (stroma == null) {
			if (other.stroma != null)
				return false;
		} else if (!stroma.equals(other.stroma))
			return false;
		if (typeHysto == null) {
			if (other.typeHysto != null)
				return false;
		} else if (!typeHysto.equals(other.typeHysto))
			return false;
		return true;
	}
    

}
