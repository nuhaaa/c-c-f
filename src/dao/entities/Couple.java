package dao.entities;

import java.awt.Graphics;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import arbre.Arbre;



@Entity
public class Couple implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Couple;
    @OneToOne(cascade=CascadeType.REFRESH)
    private Individu mere;
    @OneToOne(cascade=CascadeType.REFRESH)
    private Individu pere;
    
    public int getId() {
        return id_Couple;
    }

    public void setId(int id) {
        this.id_Couple = id;
    }

    public Individu getMere() {
        return mere;
    }

    public void setMere(Individu mere) {
        this.mere = mere;
    }

    public Individu getPere() {
        return pere;
    }

    public void setPere(Individu pere) {
        this.pere = pere;
    }

    public Couple() {
    }

    public Couple(Individu mere) {
		super();
		this.mere = mere;
	}

	public Couple(Individu mere, Individu pere) {
		super();
		
		this.mere = mere;
		this.pere = pere;
		
	}
    
    public Couple creerCouple(Individu mere, Individu pere){
    	if(mere.getSexe()!= pere.getSexe() && mere.getFamille()==pere.getFamille()){
    		Couple couple = new Couple(mere, pere);
    		return couple;
    	} return null;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_Couple;
		result = prime * result + ((mere == null) ? 0 : mere.hashCode());
		result = prime * result + ((pere == null) ? 0 : pere.hashCode());
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
		Couple other = (Couple) obj;
		if (id_Couple != other.id_Couple)
			return false;
		if (mere == null) {
			if (other.mere != null)
				return false;
		} else if (!mere.equals(other.mere))
			return false;
		if (pere == null) {
			if (other.pere != null)
				return false;
		} else if (!pere.equals(other.pere))
			return false;
		return true;
	}

  
    
	@Override
	public String toString() {
		return pere +" et "+ mere ;
	}


	public void dessin(Arbre a, Graphics g, int xd, int yd, Individu pc){
		if(pc.equals(pere)){
		if(mere!=null)	mere.dessinNeutre(a, g, xd, yd);
		}
		else{
			if(pere!=null) pere.dessinNeutre(a, g, xd, yd);
		}
	}
	
	public void dessinParent(Arbre a, Graphics g, int xd, int yd){
		pere.dessinCouple(a, g, xd, yd);
	}
	
    
}
