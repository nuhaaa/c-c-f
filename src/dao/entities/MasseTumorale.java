package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class MasseTumorale implements Serializable {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_Masse;
	@OneToOne
	private Siege siege;
	private Float taille;
	@Enumerated(EnumType.STRING)
	private Stenose stenose;
	@OneToOne
	private AspectMacro aspect;
	@OneToOne
	private Circonference circonf;
	@OneToOne
	private TypeHystologique type;
	private String refAnaPath;

	public MasseTumorale() {
		super();
	}
	
	
	public MasseTumorale(Siege siege, Float taille, Stenose stenose, AspectMacro aspect, Circonference circonf,
			 String refAnaPath) {
		super();
		this.siege = siege;
		this.taille = taille;
		this.stenose = stenose;
		this.aspect = aspect;
		this.circonf = circonf;
		
		this.refAnaPath = refAnaPath;
	}


	public MasseTumorale(Siege siege, TypeHystologique type) {
		super();
		this.siege = siege;
		this.type = type;
	}


	public int getId() {
		return id_Masse;
	}
	public void setId(int id) {
		this.id_Masse = id;
	}
	
	public Siege getSiege() {
		return siege;
	}
	public void setSiege(Siege siege) {
		this.siege = siege;
	}
	public Float getTaille() {
		return taille;
	}
	public void setTaille(Float taille) {
		this.taille = taille;
	}
	public Stenose getStenose() {
		return stenose;
	}
	public void setStenose(Stenose stenose) {
		this.stenose = stenose;
	}
	public AspectMacro getAspect() {
		return aspect;
	}
	public void setAspect(AspectMacro aspect) {
		this.aspect = aspect;
	}
	public Circonference getCirconf() {
		return circonf;
	}
	public void setCirconf(Circonference circonf) {
		this.circonf = circonf;
	}
	
	public TypeHystologique getType() {
		return type;
	}
	public void setType(TypeHystologique type) {
		this.type = type;
	}
	public String getRefAnaPath() {
		return refAnaPath;
	}
	public void setRefAnaPath(String refAnaPath) {
		this.refAnaPath = refAnaPath;
	}
	
}
