package dao.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Temporal;

@Entity
@SecondaryTable(name = AnaPathologie.TABLE_NAME, pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class AnaPathologie extends ExamenMedical {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "AnaPathologie";
	
	@Temporal(javax.persistence.TemporalType.DATE)
	protected Date datePrel;
	@OneToOne
	protected TypePrelevement typePrel;
	protected String refAnaPath;
	protected String file;

	public AnaPathologie() {
		super();
	}
	
	
	public AnaPathologie( Hopital hopital, DossierMedicale dossier, Date datePrel,
			TypePrelevement typePrel, String refAnaPath, String file) {
		super( hopital, dossier);
		
		this.datePrel = datePrel;
		this.typePrel = typePrel;
		this.refAnaPath = refAnaPath;
		this.file = file;
	}


	public Date getDatePrel() {
		return datePrel;
	}

	public void setDatePrel(Date datePrel) {
		this.datePrel = datePrel;
	}

	public TypePrelevement getTypePrel() {
		return typePrel;
	}

	public void setTypePrel(TypePrelevement typePrel) {
		this.typePrel = typePrel;
	}

	public String getRefAnaPath() {
		return refAnaPath;
	}

	public void setRefAnaPath(String refAnaPath) {
		this.refAnaPath = refAnaPath;
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((datePrel == null) ? 0 : datePrel.hashCode());
		result = prime * result + id;
		result = prime * result + ((refAnaPath == null) ? 0 : refAnaPath.hashCode());
		result = prime * result + ((typePrel == null) ? 0 : typePrel.hashCode());
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
		AnaPathologie other = (AnaPathologie) obj;
		if (datePrel == null) {
			if (other.datePrel != null)
				return false;
		} else if (!datePrel.equals(other.datePrel))
			return false;
		if (id != other.id)
			return false;
		if (refAnaPath == null) {
			if (other.refAnaPath != null)
				return false;
		} else if (!refAnaPath.equals(other.refAnaPath))
			return false;
		if (typePrel == null) {
			if (other.typePrel != null)
				return false;
		} else if (!typePrel.equals(other.typePrel))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "AnaPathologie [datePrel=" + datePrel + ", typePrel=" + typePrel + ", refAnaPath=" + refAnaPath
				+ ", file=" + file + "]";
	}
	
}
