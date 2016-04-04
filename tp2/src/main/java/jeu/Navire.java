/**
 * 
 */
package jeu;

import java.util.ArrayList;

/**
 * @author DELL
 *
 */
public abstract class Navire {
	
	protected OrientationType orientation;
	protected NavireType nom;
	protected int nbPoints;
	protected int taille;
	protected ArrayList<Coordonnee> coordonnees;
	
	
	/**
	 * @return the orientation
	 */
	public abstract boolean estCoule();
	
	/**
	 * @return the orientation
	 */
	public abstract OrientationType getOrientation();
	
	/**
	 * @param orientation the orientation to set
	 */
	public abstract void setOrientation(OrientationType orientation);
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
	 * @return the taille
	 */
	public abstract int getTaille();
	/**
	 * @param taille the taille to set
	 */
	public abstract void setTaille(int taille);
	/**
	 * @return the coordonnees
	 */
	public abstract Coordonnee getCoordonnees(int index);
	/**
	 * @param coordonnees the coordonnees to set
	 */
	public abstract void addCoordonnee(Coordonnee coordonnee);
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public abstract String toString(); 
	

}
