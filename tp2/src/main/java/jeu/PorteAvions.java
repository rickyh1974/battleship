
package jeu;



public class PorteAvions extends Navire {


	public PorteAvions() {	
		this.orientation=0; // vertical par défaut
		this.nom=NavireType.PORTEAVIONS;
		this.nbPoints=5;
		this.taille=5;
		
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
