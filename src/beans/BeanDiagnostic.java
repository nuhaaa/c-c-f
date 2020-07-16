package beans;

import java.util.ArrayList;

import dao.entities.SyndromeFamille;

public class BeanDiagnostic {
	private ArrayList<SyndromeFamille> diagnostics = new ArrayList<>();

	public BeanDiagnostic() {
		super();
		
	}

	public ArrayList<SyndromeFamille> getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(ArrayList<SyndromeFamille> diagnostics) {
		this.diagnostics = diagnostics;
	}
	
	
}
