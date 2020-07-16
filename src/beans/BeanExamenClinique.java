package beans;

import java.util.List;

import dao.entities.ExamenClinique;
import dao.entities.Individu;

public class BeanExamenClinique {
	private Individu patient;
	private List<ExamenClinique> examens;
	public BeanExamenClinique() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Individu getPatient() {
		return patient;
	}

	public void setPatient(Individu patient) {
		this.patient = patient;
	}

	public List<ExamenClinique> getExamens() {
		return examens;
	}
	public void setExamens(List<ExamenClinique> examens) {
		this.examens = examens;
	}
	
	
}
