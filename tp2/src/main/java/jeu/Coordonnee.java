/**
 * 
 */
package jeu;

/**
 * @author Ricky Hoben
 *  Une class qui contient que des les coordonnées X et Y d'un tableau cartésien.
 *  Ils ne contiennent que des nombres entier.
 */
public class Coordonnee {
	
	private int x; // numéro de la colonne
	private int y; // numéro de la ligne


	/**
	 * Constructeur 
	 * x = numéro de la colonne
	 * y = numéro de la ligne
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


}
