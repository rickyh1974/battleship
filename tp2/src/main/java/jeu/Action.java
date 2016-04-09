/**
 * 
 */
package jeu;


/**
 * @author DELL
 *
 */
public class Action {
    private static int numSeqAction=0;
    private Coordonnee point; // coordonnee x y donnee par joueur
	/**
	 * 
	 */
	public Action() {
		++numSeqAction;
		 
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the numSeqAction
	 */
	public static int getNumSeqAction() {
		return numSeqAction;
	}

	/**
	 * @return the point
	 */
	public Coordonnee getPoint() {
		return point;
	}
	/**
	 * @param point the point to set
	 */
	public void setPoint(Coordonnee point) {
		this.point = point;
	}

}
