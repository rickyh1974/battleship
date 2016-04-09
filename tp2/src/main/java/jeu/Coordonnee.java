/**
 * 
 */
package jeu;

/**
 * @author Ricky Hoben
 *  Une class qui contient les coordonnées X et Y d'un tableau cartésien.
 *  Ils ne contiennent que des nombres entier.
 */
public class Coordonnee {
	
	private int x; // numéro de la colonne de 0 à 9
	private int y; // numéro de la ligne 0 à 9
	private boolean touche; //indique si coordonné a été touché 


	/**
	 * Constructeur 
	 * x = numéro de la colonne
	 * y = numéro de la ligne
	 * touche  false si non tout true si touche
	 */
	public Coordonnee(int x, int y) {
		setX(x);
		setY(y);		
	}

	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
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
		result = prime * result + x;
		result = prime * result + y;
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
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coordonnee [x=" + x + ", y=" + y + ", touche=" + touche + "]";
	}
	
}
