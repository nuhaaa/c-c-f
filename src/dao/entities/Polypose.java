package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Polypose implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_Polypose;
	private int nombre;
	private int nombre1;
	private Float taille;
	private String couleur;
	@OneToOne
	private TypeHystologique type;
	@OneToOne
	private AspectMacro aspect;
	@OneToOne
	private Siege siege;
	private String refAnaPath;

	public Polypose() {
		super();
	}
	
	public Polypose(int nombre, int nombre1, Float taille, String couleur, TypeHystologique type, AspectMacro aspect,
			Siege siege, String refAnaPath) {
		super();
		this.nombre = nombre;
		this.nombre1 = nombre1;
		this.taille = taille;
		this.couleur = couleur;
		this.type = type;
		this.aspect = aspect;
		this.siege = siege;
		this.refAnaPath = refAnaPath;
	}

	
	public Polypose(TypeHystologique type, Siege siege) {
		super();
		this.type = type;
		this.siege = siege;
	}

	public int getId() {
		return id_Polypose;
	}
	public void setId(int id) {
		this.id_Polypose = id;
	}
	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	public int getNombre1() {
		return nombre1;
	}
	public void setNombre1(int nombre1) {
		this.nombre1 = nombre1;
	}
	
	public Float getTaille() {
		return taille;
	}
	public void setTaille(Float taille) {
		this.taille = taille;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public AspectMacro getAspect() {
		return aspect;
	}
	public void setAspect(AspectMacro aspect) {
		this.aspect = aspect;
	}
	public Siege getSiege() {
		return siege;
	}
	public void setSiege(Siege siege) {
		this.siege = siege;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aspect == null) ? 0 : aspect.hashCode());
		result = prime * result + ((couleur == null) ? 0 : couleur.hashCode());
		result = prime * result + id_Polypose;
		result = prime * result + nombre;
		result = prime * result + nombre1;
		result = prime * result + ((refAnaPath == null) ? 0 : refAnaPath.hashCode());
		result = prime * result + ((siege == null) ? 0 : siege.hashCode());
		result = prime * result + ((taille == null) ? 0 : taille.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Polypose other = (Polypose) obj;
		if (aspect == null) {
			if (other.aspect != null)
				return false;
		} else if (!aspect.equals(other.aspect))
			return false;
		if (couleur == null) {
			if (other.couleur != null)
				return false;
		} else if (!couleur.equals(other.couleur))
			return false;
		if (id_Polypose != other.id_Polypose)
			return false;
		if (nombre != other.nombre)
			return false;
		if (nombre1 != other.nombre1)
			return false;
		if (refAnaPath == null) {
			if (other.refAnaPath != null)
				return false;
		} else if (!refAnaPath.equals(other.refAnaPath))
			return false;
		if (siege == null) {
			if (other.siege != null)
				return false;
		} else if (!siege.equals(other.siege))
			return false;
		if (taille == null) {
			if (other.taille != null)
				return false;
		} else if (!taille.equals(other.taille))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Polypose [refAnaPath=" + refAnaPath + "]";
	}
	
	 
}
