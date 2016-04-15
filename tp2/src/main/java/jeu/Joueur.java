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
	private Grille grilleAdverse;
	private Grille grillePrincipale;

	/**
	 * constructeur de la class joueur
	 */
	public Joueur(String nom) {
		this.nom=nom;
		this.navires = new HashMap<NavireType,Navire>();
		
		// création des bateaux
		this.navires.put(NavireType.PORTEAVIONS, new PorteAvions());
		this.navires.put(NavireType.CROISSEUR, new Croisseur());
		this.navires.put(NavireType.CONTRETORPILLEUR, new ContreTorpilleur());
		this.navires.put(NavireType.SOUSMARIN, new SousMarin());
		this.navires.put(NavireType.TORPILLEUR, new Torpilleur());
		
		grilleAdverse = new Grille();
		grillePrincipale = new Grille();				
		
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
	public Navire getNavire(NavireType nomNavire) {
		return navires.get(nomNavire);
	}

	/**
	 * @param navires the navires to set
	 */
	public void setNavires(Navire navires) {
		
		this.navires.replace(navires.getNom(), navires);
	}

	/**
	 * @return the grilleAdverse
	 */
	public Grille getgrilleAdverse() {
		return grilleAdverse;
	}

	/**
	 * @param grilleAdverse the grilleAdverse to set
	 */
	public void setgrilleAdverse(Grille grilleAdverse) {
		this.grilleAdverse = grilleAdverse;
	}

	/**
	 * @return the grillePrincipale
	 */
	public Grille getgrillePrincipale() {
		return grillePrincipale;
	}

	/**
	 * @param grillePrincipale the grillePrincipale to set
	 */
	public void setgrillePrincipale(Grille grillePrincipale) {
		this.grillePrincipale = grillePrincipale;
	}

	/**
	 * @return the nBR_NAVIRE
	 */
	public int getNBR_NAVIRE() {
		return NBR_NAVIRE;
	}
	
	/**
	 * @return the nBR_NAVIRE
	 */
	public boolean verifierShot(Coordonnee point) {
		//boolean touche=false;
		switch (grillePrincipale.getCaseStatut(point.getLigne(), point.getLigne())) {
			case 0: // case vide		       
				    // 
			        grillePrincipale.setCaseStatut(point.getLigne(), point.getCol(), 3);
			        //joueurAdverse.
					break;
			case 1: // case occupé
					grillePrincipale.setCaseStatut(point.getLigne(), point.getLigne(), 2);
					//touche=true;
					break;
		}
		return grillePrincipale.getCaseStatut(point.getLigne(), point.getLigne())==1;
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", totalPoints=" + totalPoints + ", NBR_NAVIRE=" + NBR_NAVIRE + ", navires="
				+ navires + ", grilleAdverse=" + grilleAdverse + ", grillePrincipale=" + grillePrincipale + "]";
	}

	

}
