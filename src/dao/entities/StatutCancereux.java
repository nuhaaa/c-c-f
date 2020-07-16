package dao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;


@Entity
public class StatutCancereux implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Statut;
	@OneToOne
    private TypeStatut typeStatut;
    private String annee;
    private int age;
    @OneToOne
    private Forme forme;
    @OneToOne
    private Type type;
    @OneToOne
    private Site site;
    @Enumerated(EnumType.STRING)
    private T t;
    @Enumerated(EnumType.STRING)
    private M m;
    @Enumerated(EnumType.STRING)
    private N n;
    
    @OneToMany
    private List<PriseEnCharge> priseEncharge;
    
    
    @ManyToMany(mappedBy = "cancers")
    private List<Individu> individus;

    public StatutCancereux() {
    	individus = new ArrayList<>();
    }



	public StatutCancereux(TypeStatut typeStatut, String annee, int age, Type type, Site site, T t, M m, N n,
			List<PriseEnCharge> priseEncharge) {
		super();
		this.typeStatut = typeStatut;
		this.annee = annee;
		this.age = age;
		this.type = type;
		this.site = site;
		this.t = t;
		this.m = m;
		this.n = n;
		this.priseEncharge = priseEncharge;
	}



	public StatutCancereux(TypeStatut typeStatut, String annee, int age, Forme forme,
			List<PriseEnCharge> priseEncharge) {
		super();
		this.typeStatut = typeStatut;
		this.annee = annee;
		this.age = age;
		this.forme = forme;
		this.priseEncharge = priseEncharge;
	}

	


	public StatutCancereux(TypeStatut typeStatut, String annee, int age, Type type, List<PriseEnCharge> priseEncharge) {
		super();
		this.typeStatut = typeStatut;
		this.annee = annee;
		this.age = age;
		this.type = type;
		this.priseEncharge = priseEncharge;
	}



	public int getId() {
        return id_Statut;
    }

    public void setId(int id) {
        this.id_Statut = id;
    }

   
    public TypeStatut getTypeStatut() {
		return typeStatut;
	}



	public void setTypeStatut(TypeStatut typeStatut) {
		this.typeStatut = typeStatut;
	}



	public String getAnnee() {
		return annee;
	}



	public void setAnnee(String annee) {
		this.annee = annee;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public Forme getForme() {
		return forme;
	}



	public void setForme(Forme forme) {
		this.forme = forme;
	}



	public Type getType() {
		return type;
	}



	public void setType(Type type) {
		this.type = type;
	}



	public Site getSite() {
		return site;
	}



	public void setSite(Site site) {
		this.site = site;
	}



	public T getT() {
		return t;
	}



	public void setT(T t) {
		this.t = t;
	}



	public M getM() {
		return m;
	}



	public void setM(M m) {
		this.m = m;
	}



	public N getN() {
		return n;
	}



	public void setN(N n) {
		this.n = n;
	}



	public List<PriseEnCharge> getPriseEncharge() {
		if(priseEncharge == null) priseEncharge = new ArrayList<>();
		return priseEncharge;
	}



	public void setPriseEncharge(List<PriseEnCharge> priseEncharge) {
		this.priseEncharge = priseEncharge;
	}



	public List<Individu> getIndividus() {
		if(individus == null) individus = new ArrayList<>();
        return individus;
    }

    public void setIndividus(List<Individu> individus) {
        this.individus = individus;
    }

  //ajouter 
	
  		public void addPriseEnCharge(PriseEnCharge p){
  			priseEncharge.add(p);
  		}
  		
  		public void addAllPriseEnCharge(List<PriseEnCharge> priseEncharge){
  			this.priseEncharge.addAll(priseEncharge);
  		}
  		
  		public void addIndividu(Individu i){
  			individus.add(i);
  		}
  
//  		supprimer
  			public void removePriseEnCharge(PriseEnCharge p){
  				priseEncharge.remove(p);
  			}
  			
  			public void removeIndividu(Individu i){
  				individus.remove(i);
  			}		
  		
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id_Statut;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatutCancereux)) {
            return false;
        }
        StatutCancereux other = (StatutCancereux) object;
        if (this.id_Statut != other.id_Statut) {
            return false;
        }
        return true;
    }



	@Override
	public String toString() {
		return "StatutCancereux [id_Statut=" + id_Statut + ", typeStatut=" + typeStatut + ", annee=" + annee + ", age="
				+ age + ", forme=" + forme + ", type=" + type + ", site=" + site + ", t=" + t + ", m=" + m + ", n=" + n
				+ ", priseEncharge=" + priseEncharge + ", individus=" + individus + "]";
	}



	

    
    

}
