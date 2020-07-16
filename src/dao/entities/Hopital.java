package dao.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Hopital implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Hopital;
    private String hopital;
    @OneToMany
    private List<Medecin> medecins;

    public Hopital() {
    }

    public Hopital(int id, String hopital) {
        this.id_Hopital = id;
        this.hopital = hopital;
    }

   
    
    public int getId() {
        return id_Hopital;
    }

    public void setId(int id) {
        this.id_Hopital = id;
    }

    public String getHopital() {
        return hopital;
    }

    public void setHopital(String hopital) {
        this.hopital = hopital;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id_Hopital;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hopital)) {
            return false;
        }
        Hopital other = (Hopital) object;
        if (this.id_Hopital != other.id_Hopital) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return " " + hopital + "";
	}

    

}
