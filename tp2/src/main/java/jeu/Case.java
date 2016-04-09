/**
 * 
 */
package jeu;

/**
 * @author Ricky Hoben
 *  statutCase 0 = rien   1 = pas touché  2 = touché
 *  navire contient le type de navire
 */
public class Case {
	
	int statutCase=0;
	NavireType nomNavirePresent=null;

	/**
	 * @return the statutCase
	 */
	public int getStatutCase() {
		return statutCase;
	}
	/**
	 * @param statutCase the statutCase to set
	 */
	public void setStatutCase(int statutCase) {
		this.statutCase = statutCase;
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
