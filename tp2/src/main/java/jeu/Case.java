/**
 * 
 */
package jeu;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * @author Ricky Hoben
 *  statutCase 0 = rien, 1 = occuper, 2 = touché  3 = demandé non touché
 *  navire contient le type de navire
 */
public class Case {
	
        ObjectProperty<StatutCaseType> statutCase = new SimpleObjectProperty(StatutCaseType.RIEN);
	NavireType nomNavirePresent=null;
	
	/**
	 * Constructeur par defaut convention java bean pour XML decoder et encoder .
	 */
	public Case(){
		
	}
        
        public ObjectProperty statutCaseProperty() {
            return statutCase;
        }

	/**
	 * @return the statutCase
	 */
	public StatutCaseType getStatutCase() {
		return statutCase.get();
	}
	/**
	 * @param statutCase the statutCase to set
	 */
	public void setStatutCase(StatutCaseType statutCase) {
		this.statutCase.set(statutCase);
	}
	/**
	 * @return the navire
	 */
	public NavireType getNomNavire() {
		return nomNavirePresent;
	}
	/**
	 * @param navire the navire to set
	 */
	public void setNomNavire(NavireType nomNavirePresent) {
		this.nomNavirePresent = nomNavirePresent;
	}

	
	

}
