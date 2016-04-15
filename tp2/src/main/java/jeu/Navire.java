/**
 * 
 */
package jeu;



/**
 * @author DELL
 *
 */
public abstract class Navire {
	// orientation 0 - VERTICAL 1 - HORIZONTAL
	protected int orientation;
	protected NavireType nom;
	protected int nbPoints;
	protected int taille;

	
	
	/**
	 * @return the orientation
	 */
	public abstract boolean estCoule();
	
	/**
	 * @return the orientation
	 */
	public abstract int getOrientation();
	
	/**
	 * @param orientation the orientation to set
	 */
	public abstract void setOrientation(int orientation);
	/**
	 * @return the nom
	 */
	public abstract NavireType getNom();
	/**
	 * @param nom the nom to set
	 */
	public abstract void setNom(NavireType nom);
	/**
	 * @return the nbPoints
	 */
	public abstract int getNbPoints();
	/**
	 * @param nbPoints the nbPoints to set
	 */
	public abstract void setNbPoints(int nbPoints);
	
	/**
	 * Retirer 1 a la variable nbPoints
	 */
	public abstract void retirerUnNbPoints();
	
	/**
	 * @return the taille
	 */
	public abstract int getTaille();
	/**
	 * @param taille the taille to set
	 */
	public abstract void setTaille(int taille);
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public abstract String toString(); 
	

}
