package dao.entities;




import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class ExamenPreOp extends ExamenClinique {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Enumerated(EnumType.STRING)
	protected TypeExamen typeExamen;

	public ExamenPreOp() {
		super();
	}
	
	

	public ExamenPreOp(Hopital hopital, DossierMedicale dossier, Date dateExamen, Float poids, Float taille, Float oMS,
			Float iMC, TypeExamen typeExamen) {
		super(hopital, dossier, dateExamen, poids, taille, oMS, iMC);
		this.typeExamen = typeExamen;
	}



	public TypeExamen getTypeExamen() {
		return typeExamen;
	}

	public void setTypeExamen(TypeExamen typeExamen) {
		this.typeExamen = typeExamen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((typeExamen == null) ? 0 : typeExamen.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExamenPreOp other = (ExamenPreOp) obj;
		if (typeExamen != other.typeExamen)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExamenPreOp [typeExamen=" + typeExamen + "]";
	}
	
}
