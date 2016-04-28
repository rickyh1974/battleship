
package jeu;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class Case {
	
        ObjectProperty<StatutCaseType> statutCase = new SimpleObjectProperty(StatutCaseType.RIEN);
	NavireType nomNavirePresent=null;
	
	public Case(){
		
	}
        
        public ObjectProperty statutCaseProperty() {
             return statutCase;
        }

	public StatutCaseType getStatutCase() {
		return statutCase.get();
	}

	public void setStatutCase(StatutCaseType statutCase) {
		this.statutCase.set(statutCase);
	}

	public NavireType getNomNavire() {
		return nomNavirePresent;
	}

	public void setNomNavire(NavireType nomNavirePresent) {
		this.nomNavirePresent = nomNavirePresent;
	}

	
	

}
