package beans;

import java.util.List;

import dao.entities.DossierMedicale;

public class BeanDossier {
	private int id;
	private List<DossierMedicale> dossiers;
	public BeanDossier() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<DossierMedicale> getDossiers() {
		return dossiers;
	}
	public void setDossiers(List<DossierMedicale> dossiers) {
		this.dossiers = dossiers;
	}
	
}
