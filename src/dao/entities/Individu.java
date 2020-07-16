package dao.entities;


import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

import arbre.Arbre;
import dao.DeccesDAO;
import dao.DossierDAO;




@Entity
public class Individu implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_Individu;
	private String prenom;
	private String nom;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateNaissance;
	private String sexe;
	private boolean urbain;
	private String ville;
	private String region;
	private String adresse;
	private String origine;
	private String image;
	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}

	private int tel1;
	private int tel2;
	@Enumerated(EnumType.STRING)
	private Education education;
	private String occupation;
	@Enumerated(EnumType.STRING)
	private NiveauSocial niveauSocial;
	@Enumerated(EnumType.STRING)
	private CouvertureMedicale couvertMedicale;
	    
	@OneToOne
	private Couple membreDeCouple;
	
	@OneToOne
	private Couple filsDeCouple;
	
	@OneToOne
	private DossierMedicale dossier;
	    
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="IndividusCancereux")
	private List<StatutCancereux> cancers;
	    
	@OneToMany(mappedBy = "patient",cascade=CascadeType.ALL)
	private List<RendezVous> lesRendezVous;
	    
	@ManyToOne
	private Famille famille;
	    
	@OneToOne(cascade=CascadeType.ALL)
	private Consentement consentement;
	    


	    public Individu() {
	    	
	    }
	    
	   
		public Individu(String prenom, String nom, Date dateNaissance, String sexe, boolean urbain,
				String ville, String region, String adresse, String origine,String image,
				Famille famille) {
			super();
			this.prenom = prenom;
			this.nom = nom;
			this.dateNaissance = dateNaissance;
			this.sexe = sexe;
			this.urbain = urbain;
			this.ville = ville;
			this.region = region;
			this.adresse = adresse;
			this.origine = origine;
			this.image = image;
			this.famille = famille;
		}

		
		

		public Individu(String prenom, String nom, Date dateNaissance, String sexe, boolean urbain, String ville,
				String region, String adresse, String origine,String image, int tel1, int tel2, Education education,
				String occupation, NiveauSocial niveauSocial, CouvertureMedicale couvertMedicale, Famille famille) {
			super();
			this.prenom = prenom;
			this.nom = nom;
			this.dateNaissance = dateNaissance;
			this.sexe = sexe;
			this.urbain = urbain;
			this.ville = ville;
			this.region = region;
			this.adresse = adresse;
			this.origine = origine;
			this.image = image;
			this.tel1 = tel1;
			this.tel2 = tel2;
			this.education = education;
			this.occupation = occupation;
			this.niveauSocial = niveauSocial;
			this.couvertMedicale = couvertMedicale;
			this.famille = famille;
		}


		public int getId() {
	        return id_Individu;
	    }

	    public void setId(int id) {
	        this.id_Individu = id;
	    }

	    public String getPrenom() {
	        return prenom;
	    }

	    public void setPrenom(String prenom) {
	        this.prenom = prenom;
	    }

	    public String getNom() {
	        return nom;
	    }

	    public void setNom(String nom) {
	        this.nom = nom;
	    }

	    public Date getDateNaissance() {
	        return dateNaissance;
	    }

	    public void setDateNaissance(Date dateNaissance) {
	        this.dateNaissance = dateNaissance;
	    }

	    public String getSexe() {
	        return sexe;
	    }

	    public void setSexe(String sexe) {
	        this.sexe = sexe;
	    }

	    public boolean isUrbain() {
	        return urbain;
	    }

	    public void setUrbain(boolean urbain) {
	        this.urbain = urbain;
	    }

	    public String getVille() {
	        return ville;
	    }

	    public void setVille(String ville) {
	        this.ville = ville;
	    }

	    public String getRegion() {
	        return region;
	    }

	    public void setRegion(String region) {
	        this.region = region;
	    }

	    public String getAdresse() {
	        return adresse;
	    }

	    public void setAdresse(String adresse) {
	        this.adresse = adresse;
	    }

	    public String getOrigine() {
	        return origine;
	    }

	    public void setOrigine(String origine) {
	        this.origine = origine;
	    }

	    public Number getTel1() {
	        return tel1;
	    }

	    public void setTel1(int tel1) {
	        this.tel1 = tel1;
	    }

	    public Number getTel2() {
	        return tel2;
	    }

	    public void setTel2(int tel2) {
	        this.tel2 = tel2;
	    }

	    

	    public String getOccupation() {
	        return occupation;
	    }

	    public void setOccupation(String occupation) {
	        this.occupation = occupation;
	    }

	    
	    


		public Couple getMembreDeCouple() {
	        return membreDeCouple;
	    }

	    public void setMembreDeCouple(Couple membreDeCouple) {
	        this.membreDeCouple = membreDeCouple;
	    }

	    public Couple getFilsDeCouple() {
	        return filsDeCouple;
	    }

	    public void setFilsDeCouple(Couple filsDeCouple) {
	        this.filsDeCouple = filsDeCouple;
	    }
	    
	    
	    
	    public Education getEducation() {
			return education;
		}



		public void setEducation(Education education) {
			this.education = education;
		}



		public NiveauSocial getNiveauSocial() {
			return niveauSocial;
		}



		public void setNiveauSocial(NiveauSocial niveauSocial) {
			this.niveauSocial = niveauSocial;
		}



		public CouvertureMedicale getCouvertMedicale() {
			return couvertMedicale;
		}



		public void setCouvertMedicale(CouvertureMedicale couvertMedicale) {
			this.couvertMedicale = couvertMedicale;
		}



		public List<StatutCancereux> getCancers() {
			if(cancers== null) cancers = new ArrayList<>();
			return cancers;
		}



		public void setCancers(List<StatutCancereux> cancers) {
			this.cancers = cancers;
		}



		public List<RendezVous> getLesRendezVous() {
			if(lesRendezVous== null) lesRendezVous = new ArrayList<>();
			return lesRendezVous;
		}



		public void setLesRendezVous(List<RendezVous> lesRendezVous) {
			this.lesRendezVous = lesRendezVous;
		}



		public Famille getFamille() {
			return famille;
		}



		public void setFamille(Famille famille) {
			this.famille = famille;
		}



		public Consentement getConsentement() {
			return consentement;
		}



		public void setConsentement(Consentement consentement) {
			this.consentement = consentement;
		}
		
		
		public DossierMedicale getDossier() {
			return dossier;
		}


		public void setDossier(DossierMedicale dossier) {
			this.dossier = dossier;
		}


		//		methodes
		public void addRendezVous(RendezVous r){
			lesRendezVous.add(r);
			r.setPatient(this);
		}

		public void addCancer(StatutCancereux c){
			cancers.add(c);
			c.getIndividus().add(this);
		}
		
		public void addAllCancers(List<StatutCancereux> cancers){
			cancers.addAll(cancers);	
		}
		
		@Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (int) id_Individu;
	        return hash;
	    }
	    
	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof Individu)) {
	            return false;
	        }
	        Individu other = (Individu) object;
	        if (this.id_Individu != other.id_Individu) {
	            return false;
	        }
	        return true;
	    }



		@Override
		public String toString() {
			return nom+ " " + prenom ;
		}

		public void dessinNeutre(Arbre a, Graphics g,int xd,int yd){
			if(sexe.equalsIgnoreCase("masculin")){
				a.symbolPereFils(xd, yd, g, prenom);
			}
			else a.symbolMereFille(xd, yd, g, prenom);
		}
		public void dessinCouple(Arbre a, Graphics g,int xd,int yd){
			

			DossierDAO dosDAO =  new DossierDAO();
			DeccesDAO deccesDAO = new DeccesDAO();
			boolean m=dosDAO.listerLesDossier(id_Individu);
			boolean d= deccesDAO.listerLesDecces(id_Individu);
			if(sexe.equalsIgnoreCase("masculin")){
				if(m==false && d==false){
				a.symbolPereFils(xd, yd, g, prenom);
				}else{
					if(m==true && d==false)
					a.symbolHommeMalade(xd, yd, g, prenom);
					else {
						if(m==false && d==true) a.symbolHommeDecede(xd, yd, g, prenom);
						else a.symbolHommeDecedeMalade(xd, yd, g, prenom);
					}
				}
			}
			else{ 
				if(m==false && d==false){
				a.symbolMereFille(xd, yd, g, prenom);
				}else{
					if(m==true && d==false)
					a.symbolFemmeMalade(xd, yd, g, prenom);
					else{
						if(m==false && d==true) a.symbolFemmeDecede(xd, yd, g, prenom);
						else a.symbolFemmeDecedeMalade(xd, yd, g, prenom);
					}
				}
			}
		
			if(membreDeCouple!= null){
				membreDeCouple.dessin(a, g, xd+40, yd, this);
				if(membreDeCouple.getPere()!=null && membreDeCouple.getMere()!=null)
				a.traitHorizontal(xd+20, yd+10, 20, g);
				else a.traitHorizontal(xd+20, yd+10, 10, g);
				
			}
			
		/*	if(filsCouple != null){
				filsCouple.dessinParent(a, g, xd+20, yd-60);
			}*/
		}

		public void dessin(Arbre a, Graphics g, int xd, int yd){
			
		
			dessinCouple(a, g, xd, yd);
			if(membreDeCouple!=null){
				
			ArrayList<Individu> list = famille.getEnfant(membreDeCouple);
			int xde=xd+20,yde=yd+50;
			if(list.size()==1){
				a.traitVertical(xd+30, yd+10,40, g);
				Individu e = list.get(0);
				e.dessinCouple(a, g, xde, yde);
				//a.traitVertical(xde+10, yde-15,15, g);
			}else if(list.size()!=0){
				a.traitVertical(xd+30, yd+10,25, g);
			xde=xde-	(((list.size()/2)*80)/2);
				a.traitHorizontal(xde+10, yd+35, ((list.size()-1)*80), g);
			for(int i=0;i<list.size();i++){
				Individu e = list.get(i);
				e.dessin(a, g, xde, yde);
				a.traitVertical(xde+10, yde-15,15, g);
				xde+=80;
				
			}
			
			}
			}
			
		}

		

	   

}
