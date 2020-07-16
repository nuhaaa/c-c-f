package dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SyndromeFamille implements Serializable {
	   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		private String diagnostic;
		
		
		public SyndromeFamille() {
			super();
			// TODO Auto-generated constructor stub
		}
		public SyndromeFamille(int id, String diagnostic) {
			super();
			this.id = id;
			this.diagnostic = diagnostic;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDiagnostic() {
			return diagnostic;
		}
		public void setDiagnostic(String diagnostic) {
			this.diagnostic = diagnostic;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((diagnostic == null) ? 0 : diagnostic.hashCode());
			result = prime * result + id;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SyndromeFamille other = (SyndromeFamille) obj;
			if (diagnostic == null) {
				if (other.diagnostic != null)
					return false;
			} else if (!diagnostic.equals(other.diagnostic))
				return false;
			if (id != other.id)
				return false;
			return true;
		}
//		@Override
//		public String toString() {
//			return "SyndromeFamille [id=" + id + ", diagnostic=" + diagnostic + "]";
//		}
//		
//		
		
		@Override
		public String toString() {
			return " " + diagnostic + " ";
		}
	
		
}
