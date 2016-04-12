/**
 * 
 */
package jeu;

import java.util.ArrayList;

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
		this.nbPoints=0;
		this.taille=3;
		this.coordonnees=new ArrayList<Coordonnee>();
	}
	
	/**
	 * @
	 */
	public boolean estCoule() {
		return false;
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
	/**
	 * @return the coordonnees
	 */
	public Coordonnee getCoordonnees(int index) {
		return coordonnees.get(index);
	}
	/**
	 * @param coordonnees the coordonnees to set
	 */
	public void addCoordonnee(Coordonnee coordonnee) {
		this.coordonnees.add(coordonnee);
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
