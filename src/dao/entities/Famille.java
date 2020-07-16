package dao.entities;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import arbre.Arbre;


@Entity
public class Famille implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Famille;
    private  String nomFamille;
    @OneToOne
    private SyndromeFamille diagnostic;
    
    @OneToMany(mappedBy = "famille",fetch=FetchType.LAZY)
    private List<Individu> individus;
    
    private Individu casIndex ;

    public Famille() {
    	
    }

    

	public Famille(String nomFamille, SyndromeFamille diagnostic) {
		super();
		this.nomFamille = nomFamille;
		this.diagnostic = diagnostic;
	}



	public int getId() {
        return id_Famille;
    }

    public void setId(int id) {
        this.id_Famille = id;
    }
    
    public String getNomFamille() {
		return nomFamille;
	}



	public void setNomFamille(String nomFamille) {
		this.nomFamille = nomFamille;
	}

	public SyndromeFamille getDiagnostic() {
		return diagnostic;
	}


	public void setDiagnostic(SyndromeFamille diagnostic) {
		this.diagnostic = diagnostic;
	}

	public List<Individu> getIndividus() {
		if(individus == null) individus = new ArrayList<>();
		return individus;
	}



	public void setIndividus(List<Individu> individus) {
		this.individus = individus;
	}



	public Individu getCasIndex() {
		return casIndex;
	}



	public void setCasIndex(Individu casIndex) {
		this.casIndex = casIndex;
	}

	public void addIndividu(Individu ind){
		if(individus == null){
			individus = new ArrayList<>();
		}
		if(casIndex ==null || individus.size() <1){
			casIndex= ind;
		}
		else{
		individus.add(ind);
		ind.setFamille(this);
		}
	}
	
	public Consanguin determinerConsanguinite(Individu ind, String degree){
            if(ind.getFamille().getCasIndex().equals(casIndex )&& ind.getFamille().equals(casIndex.getFamille())){
            	Consanguin consanguin = new Consanguin(casIndex, ind, degree);
            	return consanguin;
            }
            return null;
	}
	public void removeIndividu(Individu ind){
		individus.remove(ind);
	}
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id_Famille;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Famille)) {
            return false;
        }
        Famille other = (Famille) object;
        if (this.id_Famille != other.id_Famille) {
            return false;
        }
        return true;
    }

	
	
	


	@Override
	public String toString() {
		return "" + nomFamille + " (" + casIndex + ")";
	}



	public ArrayList<Individu> getEnfant(Couple membreCouple){
		ArrayList<Individu> enfants = new ArrayList<>();
		for(int i = 0; i< individus.size(); i++){
			Individu p = individus.get(i);
			Couple filsCouple = p.getFilsDeCouple();
			if(filsCouple!=null){
				System.out.println("filsCouple.getPere:"+filsCouple.getPere());
				System.out.println("membreCouple:"+membreCouple);
				if(filsCouple.getPere()!=null){
					if(filsCouple.getPere().equals(membreCouple.getPere())) 	enfants.add(p);
				}else{
			if(filsCouple.getMere()!=null){ 
					if(filsCouple.getMere().equals(membreCouple.getMere()))	enfants.add(p);
			}
		}}
		}
		return enfants;
	}
	
	public Individu getGrandPere(Individu p,Integer o){
		Integer b=0,s=0;
		Individu p1=null,p2=null;
		Couple parent = p.getFilsDeCouple();
		if(parent != null){
			
			if(parent.getPere()!= null){
				p1= getGrandPere(parent.getPere(),b);
				
			}
			
				if(parent.getMere()!= null){
					p2= getGrandPere(parent.getMere(),s);
				
				}
			if (b>s) {
				
			  o=b+1;
				return p1;
			}else{
				 o=b+1;
					return p2;
		}
		}
		o=1;
		return p;
	}
	
	public void dessin(Arbre a, Graphics g, int xd, int yd){
		
		Individu p = getGrandPere(casIndex, 0);
		p.dessin(a, g, xd, yd);
		
		
	}

}
