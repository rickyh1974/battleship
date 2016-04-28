/**
 * 
 */
package jeu;


public class Coordonnee {
	
	private int col; // numéro de la colonne de 0 à 9
	private int ligne; // numéro de la ligne 0 à 9
	private boolean touche; //indique si coordonné a été touché
	
	public Coordonnee() {
		setCol(0);
		setLigne(0);
		setTouche(false);
	}
		

	public Coordonnee(int col, int ligne) {
		setCol(col);
		setLigne(ligne);
		setTouche(false);
	}

	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
	}


	public int getLigne() {
		return ligne;
	}


	public void setLigne(int ligne) {
		this.ligne = ligne;
	}
	

	public boolean isTouche() {
		return touche;
	}


	public void setTouche(boolean touche) {
		this.touche = touche;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + ligne;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordonnee other = (Coordonnee) obj;
		if (col != other.col)
			return false;
		if (ligne != other.ligne)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Coordonnee [col=" + col + ", ligne=" + ligne + ", touche=" + touche + "]";
	}
	
}
