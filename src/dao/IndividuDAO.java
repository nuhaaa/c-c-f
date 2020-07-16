package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Consanguin;
import dao.entities.Consentement;
import dao.entities.Couple;
import dao.entities.Decces;
import dao.entities.Famille;
import dao.entities.Individu;
import dao.entities.MotifDecces;
import dao.entities.StatutCancereux;

public class IndividuDAO {

	private final String PU_NAME = "CHU";
	private EntityManagerFactory factory = null;

	public IndividuDAO() {
		factory = Persistence.createEntityManagerFactory(PU_NAME);
	}

	private EntityManager newEntityManager() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		return em;
	}

	private void closeEntityManager(EntityManager em) {
		if (em != null) {
			if (em.isOpen()) {
				EntityTransaction trs = em.getTransaction();
				if (trs.isActive()) {
					trs.commit();
				}
				em.close();
			}
		}
	}

//	Ajouter un individu
//	on ajoute la famille avant d'ajouter l'individu
	public boolean enregistrementIndividu(Individu individu) {
		try {
			EntityManager em = this.newEntityManager();

			Famille famille = individu.getFamille();
			em.detach(famille);
			famille.addIndividu(individu);
			em.merge(famille);
			em.persist(individu);

			this.closeEntityManager(em);
			System.out.println("Individu bien enregistre au platfrome !");
			return true;
		} catch (Exception e) {
			System.out.println("Erreur d'enregistrement du nouveau individu !!!");
			return false;
		}
	}

//	ajouter Consanguinité
	public boolean ajoutConsanguin(Consanguin consang) {
		try {
			EntityManager em = this.newEntityManager();

			em.persist(consang);

			this.closeEntityManager(em);
			System.out.println("bien enregistre au platfrome !");
			return true;
		} catch (Exception e) {
			System.out.println("Erreur d'enregistrement !!!");
			return false;
		}
	}

//	supprimer un individu

	public boolean supprimerIndividu(int id_Ind) {
		EntityManager em = this.newEntityManager();
		try {
			Individu ind = em.find(Individu.class, id_Ind);
			Famille famille = ind.getFamille();
			if (famille.getCasIndex() == ind) {
				famille.setCasIndex(null);
			}
			famille.removeIndividu(ind);
			ind.setMembreDeCouple(null);
			ind.setFilsDeCouple(null);
			ind.setConsentement(null);

			em.remove(ind);
			this.closeEntityManager(em);
			return true;
		} catch (Exception e) {
			this.closeEntityManager(em);
			return false;
		}
	}

//	 Ajouter le decces de l'individu
	public boolean ajoutDecces(Decces decces) {
		EntityManager em = this.newEntityManager();
		try {
			em.persist(decces);
			this.closeEntityManager(em);
			System.out.println("bien enregistre au platfrome !");
			return true;
		} catch (Exception e) {
			this.closeEntityManager(em);
			System.out.println("errer !");
			return false;
		}
	}

//	 ajouter decces
	public boolean ajouterDecces(Date date, int id_ind, int id_motif) {
		EntityManager em = this.newEntityManager();
		Individu ind = em.find(Individu.class, id_ind);
		MotifDecces motif = em.find(MotifDecces.class, id_motif);
		Decces decces = new Decces(ind, motif, date);
		try {
			em.persist(decces);
			this.closeEntityManager(em);
			System.out.println("bien enregistre au platfrome !");
			return true;
		} catch (Exception e) {
			this.closeEntityManager(em);
			System.out.println("errer !");
			return false;
		}
	}

//	 Ajouter le consentement de l'individu
	public boolean ajoutConsentement(Individu ind, Date dateConsent) {
		EntityManager em = this.newEntityManager();
		try {
			Consentement consent = new Consentement(dateConsent, ind);
			em.detach(ind);
			ind.setConsentement(consent);
			em.merge(ind);
			em.persist(consent);
			this.closeEntityManager(em);
			return true;
		} catch (Exception e) {
			this.closeEntityManager(em);
			return false;
		}
	}

	// ajouter les statuts cancereux

	public void ajoutStatutIndidu(StatutCancereux statut, Individu ind) {
		EntityManager em = this.newEntityManager();
		try {
			em.detach(ind);
			ind.addCancer(statut);
			em.merge(ind);
			statut.addIndividu(ind);
			em.persist(statut);
			System.out.println("ajouté !!!");
			this.closeEntityManager(em);
		} catch (Exception e) {
			System.out.println("Erreur !!!");
			this.closeEntityManager(em);
		}
	}
//		lister la liste des statuts cancereux d'un d'individu 

	public List<StatutCancereux> listerStatutsParIndividu(int id_ind) {
		EntityManager em = this.newEntityManager();
		Individu ind = em.find(Individu.class, id_ind);
		TypedQuery<StatutCancereux> requete = em.createQuery(
				"SELECT DISTINCT s FROM StatutCancereux s JOIN FETCH s.individus i WHERE i.getId() = :iid",
				StatutCancereux.class);
		requete.setParameter("iid", ind.getId());
		List<StatutCancereux> statuts = requete.getResultList();
		this.closeEntityManager(em);
		return statuts;
	}

//	couple
	public boolean ajoutCouple(Couple couple) {
		EntityManager em = this.newEntityManager();
		try {
			Individu mere = couple.getMere();
			em.detach(mere);
			mere.setMembreDeCouple(couple);
			em.merge(mere);

			Individu pere = couple.getPere();
			em.detach(pere);
			pere.setMembreDeCouple(couple);
			em.merge(pere);

			em.persist(couple);

			this.closeEntityManager(em);
			return true;
		} catch (Exception e) {
			System.out.println("Erreur !!!");
			this.closeEntityManager(em);
			return false;
		}
	}

//	 filsdecouple
	public boolean ajoutFilsCouple(Individu fils, Couple couple) {
		EntityManager em = this.newEntityManager();
		try {
			if (fils.getFamille().equals(couple.getMere().getFamille())) {
				em.detach(fils);
				fils.setFilsDeCouple(couple);
				em.merge(fils);
			}

			this.closeEntityManager(em);
			System.out.println("bien  ajouté");
			return true;
		} catch (Exception e) {
			System.out.println("Erreur !!!");
			this.closeEntityManager(em);
			return false;
		}
	}

//	 memebre couple
	public boolean ajoutMembreCouple(Individu membre, Couple couple) {
		EntityManager em = this.newEntityManager();
		try {
			if (membre.getFamille() == couple.getMere().getFamille()) {
				em.detach(membre);
				membre.setMembreDeCouple(couple);
				em.merge(membre);
			}

			this.closeEntityManager(em);
			return true;
		} catch (Exception e) {
			System.out.println("Erreur !!!");
			this.closeEntityManager(em);
			return false;
		}
	}

	public List<Individu> listerLesIndividu() {
		EntityManager em = this.newEntityManager();
		TypedQuery<Individu> query = em.createQuery("SELECT a FROM Individu a", Individu.class);
		List<Individu> individus = query.getResultList();
		this.closeEntityManager(em);
		return individus;
	}

	public List<Couple> listerLesCouple() {
		EntityManager em = this.newEntityManager();
		TypedQuery<Couple> query = em.createQuery("SELECT a FROM Couple a", Couple.class);
		List<Couple> couples = query.getResultList();
		this.closeEntityManager(em);
		return couples;
	}

	public Individu trouverIndParId(int id) {
		EntityManager em = this.newEntityManager();
		Individu ind = em.find(Individu.class, id);

		this.closeEntityManager(em);
		return ind;
	}

	public Individu trouverIndPar(String prenom) {
		Individu ind = null;
		try {
			EntityManager em = this.newEntityManager();
			TypedQuery<Individu> query = em.createQuery("SELECT s FROM Individu s WHERE s.prenom = :nom",
					Individu.class);
			query.setParameter("nom", prenom);
			ind = query.getSingleResult();
			this.closeEntityManager(em);
		} catch (NoResultException e) {
			System.out.println("Erreur " + e.getMessage());
		}
		return ind;
	}

	public Couple trouverCoupleParMere(Individu mere) {
		Couple couple = null;
		try {
			EntityManager em = this.newEntityManager();
			TypedQuery<Couple> query = em.createQuery("SELECT s FROM Couple s WHERE s.getMere() = mere", Couple.class);
//	            query.setParameter("nom", mere);
			couple = query.getSingleResult();
			this.closeEntityManager(em);
		} catch (NoResultException e) {
			System.out.println("Erreur " + e.getMessage());
		}
		return couple;
	}

//	  public Couple trouverCoupleParPere(String pere){
//		  Individu ind = null;
//	        try {
//	            EntityManager em = this.newEntityManager();
//	            TypedQuery<Individu> query = em.createQuery("SELECT s FROM Individu s WHERE s.prenom = :nom", Individu.class);
//	            query.setParameter("nom", prenom);
//	            ind = query.getSingleResult();
//	            this.closeEntityManager(em);
//	        } catch (NoResultException e) {
//	            System.out.println("Erreur "+ e.getMessage());
//	        }
//	        return ind;
//	    }
	public Individu trouverCasIndexParIndividu(int id_ind) {
		Individu ind = null;
		try {

			EntityManager em = this.newEntityManager();
			Individu patient = em.find(Individu.class, id_ind);
			TypedQuery<Individu> query = em.createQuery(
					"SELECT s FROM Individu s JOIN FETCH s.famille f JOIN FETCH f.casIndex WHERE s.id_Individu = :id",
					Individu.class);
			query.setParameter("id", patient.getId());
			ind = query.getSingleResult();
			this.closeEntityManager(em);
		} catch (NoResultException e) {
			System.out.println("Erreur  " + e.getMessage());
		}
		return ind;
	}

	public Individu trouverIndById(int id) {
		Individu ind = null;
		try {
			EntityManager em = this.newEntityManager();
			ind = em.find(Individu.class, id);
			this.closeEntityManager(em);
		} catch (NoResultException e) {
			System.out.println("Erreur" + e.getMessage());
		}
		return ind;
	}

	public Couple trouverCoupleById(int id) {
		Couple couple = null;
		try {
			EntityManager em = this.newEntityManager();
			couple = em.find(Couple.class, id);
			this.closeEntityManager(em);
		} catch (NoResultException e) {
			System.out.println("Erreur" + e.getMessage());
		}
		return couple;
	}

	public List<Individu> listIndividuByFamille(int id_famille) {
		List<Individu> individus = new ArrayList<>();
		EntityManager em = this.newEntityManager();
		Famille famille = em.find(Famille.class, id_famille);
		TypedQuery<Individu> requete = em.createQuery(
				"SELECT DISTINCT i FROM Famille f  JOIN FETCH f.individus i WHERE f.id_Famille =:id", Individu.class);
		requete.setParameter("id", famille.getId());
		individus = requete.getResultList();
		this.closeEntityManager(em);
		return individus;
	}

	public List<Individu> listIndividuByNomFamille(String nom) {
		List<Individu> individus = new ArrayList<>();
		EntityManager em = this.newEntityManager();
		TypedQuery<Individu> requete = em.createQuery(
				"SELECT DISTINCT i FROM Famille f  JOIN FETCH f.individus i WHERE f.nomFamille =:nom", Individu.class);
		requete.setParameter("nom", nom);
		individus = requete.getResultList();
		this.closeEntityManager(em);
		return individus;
	}

	public List<Couple> listCoupleByFamille(int id_famille) {
		List<Couple> couples = new ArrayList<>();
		EntityManager em = this.newEntityManager();
		Famille famille = em.find(Famille.class, id_famille);
		TypedQuery<Couple> requete = em.createQuery(
				"SELECT DISTINCT m FROM Famille f  JOIN FETCH f.individus i JOIN FETCH i.membreDeCouple m WHERE f.id_Famille =:id",
				Couple.class);
		requete.setParameter("id", famille.getId());
		couples = requete.getResultList();
		this.closeEntityManager(em);
		return couples;
	}

	public List<Couple> listCoupleByNomFamille(String nom) {
		List<Couple> couples = new ArrayList<>();
		EntityManager em = this.newEntityManager();
		TypedQuery<Couple> requete = em.createQuery(
				"SELECT DISTINCT m FROM Famille f  JOIN FETCH f.individus i JOIN FETCH i.membreDeCouple m WHERE f.nomFamille =:nom",
				Couple.class);
		requete.setParameter("nom", nom);
		couples = requete.getResultList();
		this.closeEntityManager(em);
		return couples;
	}

	public List<Object[]> individuParTypeCancer() {
		List<Object[]> individus = null;
		try {
			EntityManager em = this.newEntityManager();
			individus = em.createQuery(
					"select distinct count(i),s.typeStatut from Individu i join i.cancers s join s.typeStatut ts group by s.typeStatut")
					.getResultList();
			
			this.closeEntityManager(em);
		} catch (NoResultException e) {
			System.out.println("Erreur authentification " + e.getMessage());
		}
		return individus;
		//

	}
}
