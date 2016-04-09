/**
 * 
 */
package jeu;


import java.util.HashMap;

/**
 * @author DELL
 *
 */
public class Joueur {
	
	private String nom;
	private int totalPoints=0;
	private final int NBR_NAVIRE=5;
	private HashMap<NavireType,Navire> navires;
	private Grille grilleSuperieur;
	private Grille grilleInferieur;

	/**
	 * constructeur de la class joueur
	 */
	public Joueur(String nom) {
		this.nom=nom;
		this.navires = new HashMap<NavireType,Navire>();
		
		// création des bateaux
		this.navires.put(NavireType.PORTEAVIONS, new PorteAvions());
	/*	this.navires.put(NavireType.PORTEAVIONS, new PorteAvions());
		this.navires.put(NavireType.PORTEAVIONS, new PorteAvions());
		this.navires.put(NavireType.PORTEAVIONS, new PorteAvions());
		this.navires.put(NavireType.PORTEAVIONS, new PorteAvions());*/
		
		grilleSuperieur = new Grille();
		grilleInferieur = new Grille();				
		
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the totalPoints
	 */
	public int getTotalPoints() {
		return totalPoints;
	}

	/**
	 * @param totalPoints the totalPoints to set
	 */
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	/**
	 * @return the navires
	 */
	public HashMap<NavireType, Navire> getNavires() {
		return navires;
	}

	/**
	 * @param navires the navires to set
	 */
	public void setNavires(HashMap<NavireType, Navire> navires) {
		this.navires = navires;
	}

	/**
	 * @return the grilleSuperieur
	 */
	public Grille getGrilleSuperieur() {
		return grilleSuperieur;
	}

	/**
	 * @param grilleSuperieur the grilleSuperieur to set
	 */
	public void setGrilleSuperieur(Grille grilleSuperieur) {
		this.grilleSuperieur = grilleSuperieur;
	}

	/**
	 * @return the grilleInferieur
	 */
	public Grille getGrilleInferieur() {
		return grilleInferieur;
	}

	/**
	 * @param grilleInferieur the grilleInferieur to set
	 */
	public void setGrilleInferieur(Grille grilleInferieur) {
		this.grilleInferieur = grilleInferieur;
	}

	/**
	 * @return the nBR_NAVIRE
	 */
	public int getNBR_NAVIRE() {
		return NBR_NAVIRE;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", totalPoints=" + totalPoints + ", NBR_NAVIRE=" + NBR_NAVIRE + ", navires="
				+ navires + ", grilleSuperieur=" + grilleSuperieur + ", grilleInferieur=" + grilleInferieur + "]";
	}

	

}
