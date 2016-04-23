/**
 * 
 */
package jeu;


import java.util.ArrayList;
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
        private HashMap<NavireType, ArrayList<Coordonnee>> placementNaviresInitial;
	private Grille grilleAdverse;
	private Grille grillePrincipale;
	
	/**
	 * constructeur de la class joueur
	 */
	public Joueur() {}

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
	 * @param totalPoints the totalPoints to set
	 */
	public void ajouteUnTotalPoints() {
		++this.totalPoints;
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
	public Grille getGrilleAdverse() {
		return grilleAdverse;
	}

	/**
	 * @param grilleAdverse the grilleAdverse to set
	 */
	public void setGrilleAdverse(Grille grilleAdverse) {
		this.grilleAdverse = grilleAdverse;
	}

	/**
	 * @return the grillePrincipale
	 */
	public Grille getGrillePrincipale() {
		return grillePrincipale;
	}

	/**
	 * @param grillePrincipale the grillePrincipale to set
	 */
	public void setGrillePrincipale(Grille grillePrincipale) {
		this.grillePrincipale = grillePrincipale;
	}

	/**
	 * @return the nBR_NAVIRE
	 */
	public int getNBR_NAVIRE() {
		return NBR_NAVIRE;
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
        
    public void setPlacementNavires(HashMap<NavireType, ArrayList<Coordonnee>> placementNavires) {
    	NavireType nomNavire;
    	Coordonnee coordCourant;
    	Coordonnee coordProchain;
        
        this.placementNaviresInitial = placementNavires;
    	
    	for ( NavireType navireTypeTemp : NavireType.values() ) {

    		for (int i=0;i<placementNavires.get(navireTypeTemp).size();i++) {
    			if (i==0) {
	    			if (placementNavires.get(navireTypeTemp).get(i).getLigne()==placementNavires.get(navireTypeTemp).get(i+1).getLigne()) {
	    				//vertical
	    			    navires.get(navireTypeTemp).setOrientation(0);
	    			} else {
	    				//horizontal
	    				navires.get(navireTypeTemp).setOrientation(1);
	    			}
    			}
    			System.out.println(navireTypeTemp);
    			System.out.println("size="+placementNavires.get(navireTypeTemp).size());
    			System.out.println(placementNavires.get(navireTypeTemp).get(i).getLigne());
    			System.out.println(placementNavires.get(navireTypeTemp).get(i).getCol());
    			grillePrincipale.setNomNavire(placementNavires.get(navireTypeTemp).get(i).getLigne(), 
    					                      placementNavires.get(navireTypeTemp).get(i).getCol(), navireTypeTemp);
    			grillePrincipale.setCaseStatut(placementNavires.get(navireTypeTemp).get(i).getLigne(), 
    					                       placementNavires.get(navireTypeTemp).get(i).getCol(), StatutCaseType.OCCUPE);
    			//placementNavires.get(navireTypeTemp);

    		}
    	}		
    }

	/**
	 * @return the nBR_NAVIRE
	 */
	public boolean verifierShot(Coordonnee point) {
		//boolean touche=false;
		switch (grillePrincipale.getCaseStatut(point.getLigne(), point.getCol())) {
			case RIEN : // case vide		       
				    // 
			        grillePrincipale.setCaseStatut(point.getLigne(), point.getCol(), StatutCaseType.DEMANDENONTOUCHE);
			        //joueurAdverse.
					break;
			case OCCUPE: // case occupé
					grillePrincipale.setCaseStatut(point.getLigne(), point.getCol(), StatutCaseType.TOUCHE);
					//touche=true;
					break;
			default:
					break;
					
		}
		return grillePrincipale.getCaseStatut(point.getLigne(), point.getCol())==StatutCaseType.TOUCHE;
		
		
	}
        
        public HashMap<NavireType, ArrayList<Coordonnee>> getPlacementNaviresInitial() {
            return this.placementNaviresInitial;
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
