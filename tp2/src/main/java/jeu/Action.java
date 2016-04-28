
package jeu;


public class Action {
    private static int numSeqAction=0;
    private Coordonnee point; // coordonnee x y donnee par joueur
    private String nomJoeur; // contient nom du joueur
    

	public Action() {
		++numSeqAction;
	}
    

	public String getNomJoeur() {
		return nomJoeur;
	}

	public void setNomJoeur(String nomJoeur) {
		this.nomJoeur = nomJoeur;
	}


	public static int getNumSeqAction() {
		return numSeqAction;
	}


	public Coordonnee getPoint() {
		return point;
	}

	public void setPoint(Coordonnee point) {
		this.point = point;
	}
        
}
