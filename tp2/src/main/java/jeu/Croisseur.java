
package jeu;



public class Croisseur extends Navire {


	public Croisseur() {	
		this.orientation=0; // vertical par défaut
		this.nom=NavireType.CROISSEUR;
		this.nbPoints=4;
		this.taille=4;

	}
	
	public boolean estCoule() {
		return this.getNbPoints()==0;
	}
	

	public int getOrientation() {
	    return this.orientation;	
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public NavireType getNom() {
		return this.nom;
	}

	public void setNom(NavireType nom) {
		this.nom = nom;
	}

	public int getNbPoints() {
		return nbPoints;
	}

	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}

	public void retirerUnNbPoints() {
		 --nbPoints;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}


	@Override
	public String toString() {
		return "PorteAvions [estCoule()=" + estCoule() + ", getOrientation()=" + getOrientation() + ", getNom()="
				+ getNom() + ", getNbPoints()=" + getNbPoints() + ", getTaille()=" + getTaille() + "]";
	}

	
	

}
