
package jeu;


public abstract class Navire {
	// orientation 0 - VERTICAL 1 - HORIZONTAL
	protected int orientation;
	protected NavireType nom;
	protected int nbPoints;
	protected int taille;

	public abstract boolean estCoule();
	
        
	public abstract int getOrientation();
	
	public abstract void setOrientation(int orientation);

        
	public abstract NavireType getNom();

	public abstract void setNom(NavireType nom);

        
	public abstract int getNbPoints();

	public abstract void setNbPoints(int nbPoints);
	

	public abstract void retirerUnNbPoints();
	
	
	public abstract int getTaille();
	
	public abstract void setTaille(int taille);
	
	
	public abstract String toString(); 
	

}
