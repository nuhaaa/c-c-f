package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dao.entities.Biologie;
import dao.entities.DossierMedicale;
import dao.entities.Famille;
import dao.entities.Utilisateur;

public class UtilisateurDAO {

	private final String PU_NAME = "CHU";
	private EntityManagerFactory factory = null;

	public UtilisateurDAO() {
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

	public boolean creerNouveauCompte(Utilisateur utilisateur) {
		boolean ajout = false;
		try {
			EntityManager em = this.newEntityManager();
			em.persist(utilisateur);
			this.closeEntityManager(em);
			System.out.println("Utilisateur bien enregistre au platfrome !");
			ajout = true;
		} catch (Exception e) {
			System.out.println("Erreur d'enregistrement du nouvel utilisateur !!!");
		}
		return ajout;
	}

	public Object[] authentification(String loginOuEmail, String password) {
		Object[] utilisateur = null;
		try {
			EntityManager em = this.newEntityManager();
	        //TypedQuery<Utilisateur> query = em.createQuery("SELECT u, FROM Utilisateur u WHERE u.login = :log AND u.password = :pass", Utilisateur.class);
			utilisateur = (Object[]) em.createQuery("select u.nom, u.prenom, r.roleName from Utilisateur u join u.role r WHERE u.login = :log AND u.password = :pass").setParameter("log", loginOuEmail).setParameter("pass", password).getSingleResult();
//	        query.setParameter("log", loginOuEmail);
//	        query.setParameter("pass", password);
	        //utilisateur = query.getSingleResult();
			this.closeEntityManager(em);
		} catch (NoResultException e) {
			System.out.println("Erreur authentification " + e.getMessage());
		}
		return utilisateur;
	}

	public boolean mettreAjourProfile(int idUtilisateur, Utilisateur nouvelUtilisateur) {
		boolean miseAjour = false;
		try {
			EntityManager em = this.newEntityManager();
			Utilisateur utilisateur = em.find(Utilisateur.class, idUtilisateur);
			utilisateur.setNom(nouvelUtilisateur.getNom());
			utilisateur.setPrenom(nouvelUtilisateur.getPrenom());
			utilisateur.setEmail(nouvelUtilisateur.getEmail());
			utilisateur.setLogin(nouvelUtilisateur.getLogin());
			utilisateur.setPassword(nouvelUtilisateur.getPassword());
			this.closeEntityManager(em);
			System.out.println("Profil de l'Utilisateur " + utilisateur + " est mise à jour !");
			miseAjour = true;
		} catch (Exception e) {
			System.out.println("Erreur de mise à jour du profil utilisateur !!!");
		}
		return miseAjour;
	}

	public int verifierUniciteChamps(String champ) {
		int nbrDefoisExistLeChamp = 0;
		EntityManager em = this.newEntityManager();
		Query requete = null;

		requete = em.createQuery("SELECT COUNT u.login FROM Utilisateur u WHERE u.login = :champ");

		nbrDefoisExistLeChamp = Integer.parseInt(requete.getSingleResult().toString());
		System.out.println("Nombre de champs trouve = " + nbrDefoisExistLeChamp);

		this.closeEntityManager(em);
		return nbrDefoisExistLeChamp;
	}

	public Utilisateur trouverUtilisateurParEmail(String login) {
		Utilisateur utilisateur = null;
		EntityManager em = this.newEntityManager();
		TypedQuery<Utilisateur> requete = em.createQuery("SELECT u FROM Utilisateur u WHERE u.login = :login",
				Utilisateur.class);
		requete.setParameter("login", login);
		try {
			utilisateur = requete.getSingleResult();
			this.closeEntityManager(em);
		} catch (NoResultException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
		return utilisateur;
	}

	public List<Utilisateur> listerUsers() {
		EntityManager em = this.newEntityManager();
		TypedQuery<Utilisateur> query = em.createQuery("SELECT a FROM Utilisateur a", Utilisateur.class);
		List<Utilisateur> users = query.getResultList();
		System.out.println("users :"+users);
		this.closeEntityManager(em);
		return users;
	}

	public Utilisateur listerUserParId(int id) {
		EntityManager em = this.newEntityManager();
		Utilisateur user = em.find(Utilisateur.class, id);
		this.closeEntityManager(em);
		return user;
	}
	
	public boolean supprimerUtilisateur(int user_id){
        EntityManager em = this.newEntityManager();
        try {
        	Utilisateur user = em.find(Utilisateur.class, user_id);
            em.remove(user);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
}
