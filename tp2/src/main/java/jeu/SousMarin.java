/**
 * 
 */
package jeu;



/**
 * @author Ricky Hoben
 *
 */
public class SousMarin extends Navire {

	/**
	 * 
	 */
	public SousMarin() {	
		this.orientation=0; // vertical par défaut
		this.nom=NavireType.SOUSMARIN;
		this.nbPoints=3;
		this.taille=3;
		
	}
	
	/**
	 * @
	 */
	public boolean estCoule() {
		return this.getNbPoints()==0;
	}
	
	/**
	 * @return the orientation
	 */
	public int getOrientation() {
	    return this.orientation;	
	}
	/**
	 * @param orientation the orientation to set
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	/**
	 * @return the nom
	 */
	public NavireType getNom() {
		return this.nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(NavireType nom) {
		this.nom = nom;
	}
	/**
	 * @return the nbPoints
	 */
	public int getNbPoints() {
		return nbPoints;
	}
	/**
	 * @param nbPoints the nbPoints to set
	 */
	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}
	/**
	 * Retirer 1 a la variable nbPoints
	 */
	public void retirerUnNbPoints() {
		 --nbPoints;
	}
	/**
	 * @return the taille
	 */
	public int getTaille() {
		return taille;
	}
	/**
	 * @param taille the taille to set
	 */
	public void setTaille(int taille) {
		this.taille = taille;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PorteAvions [estCoule()=" + estCoule() + ", getOrientation()=" + getOrientation() + ", getNom()="
				+ getNom() + ", getNbPoints()=" + getNbPoints() + ", getTaille()=" + getTaille() + "]";
	}

	
	

}
