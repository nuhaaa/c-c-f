package beans;

import java.util.List;

import dao.entities.Individu;

public class BeanIndividu {
	int id;
	List<Individu> individus;
	public BeanIndividu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Individu> getIndividus() {
		return individus;
	}
	public void setIndividus(List<Individu> individus) {
		this.individus = individus;
	}
	
}
