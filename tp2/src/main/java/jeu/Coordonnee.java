/**
 * 
 */
package jeu;

/**
 * @author Ricky Hoben
 *  Une class qui contient les coordonnées Col et ligne d'un tableau cartésien.
 *  Ils ne contiennent que des nombres entier.
 */
public class Coordonnee {
	
	private int col; // numéro de la colonne de 0 à 9
	private int ligne; // numéro de la ligne 0 à 9
	private boolean touche; //indique si coordonné a été touché
	
	/**
	 * Default Constructeur 
	 * col = initialized to 0
	 * ligne = initialized to 0 
	 * touche initialisé à zero
	 */
	public Coordonnee() {
		setCol(0);
		setLigne(0);
		setTouche(false);
	}
		
	/**
	 * Constructeur 
	 * col = numéro de la colonne
	 * ligne = numéro de la ligne
	 * touche  false si non tout true si touche
	 */
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
	
	/**
	 * @return the touche
	 */
	public boolean isTouche() {
		return touche;
	}

	/**
	 * @param touche the touche to set
	 */
	public void setTouche(boolean touche) {
		this.touche = touche;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + ligne;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coordonnee [col=" + col + ", ligne=" + ligne + ", touche=" + touche + "]";
	}
	
}
