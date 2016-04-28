
package jeu;


import java.util.ArrayList;
import java.util.HashMap;



public class Joueur {
	
	private String nom;
	private int totalPoints=0;
	private final int NBR_NAVIRE=5;
	private HashMap<NavireType,Navire> navires;
        private HashMap<NavireType, ArrayList<Coordonnee>> placementNaviresInitial;
	private Grille grilleAdverse;
	private Grille grillePrincipale;
	

	public Joueur() {}


	public Joueur(String nom) {
		this.nom=nom;
		this.navires = new HashMap<NavireType,Navire>();
		
		this.navires.put(NavireType.PORTEAVIONS, new PorteAvions());
		this.navires.put(NavireType.CROISSEUR, new Croisseur());
		this.navires.put(NavireType.CONTRETORPILLEUR, new ContreTorpilleur());
		this.navires.put(NavireType.SOUSMARIN, new SousMarin());
		this.navires.put(NavireType.TORPILLEUR, new Torpilleur());
		
		grilleAdverse = new Grille();
		grillePrincipale = new Grille();				
		
	}

	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getTotalPoints() {
		return totalPoints;
	}


	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	

	public void ajouteUnTotalPoints() {
		++this.totalPoints;
	}	


	public Navire getNavire(NavireType nomNavire) {
		return navires.get(nomNavire);
	}


	public void setNavires(Navire navires) {
		
		this.navires.replace(navires.getNom(), navires);
	}


	public Grille getGrilleAdverse() {
		return grilleAdverse;
	}


	public void setGrilleAdverse(Grille grilleAdverse) {
		this.grilleAdverse = grilleAdverse;
	}


	public Grille getGrillePrincipale() {
		return grillePrincipale;
	}


	public void setGrillePrincipale(Grille grillePrincipale) {
		this.grillePrincipale = grillePrincipale;
	}


	public int getNBR_NAVIRE() {
		return NBR_NAVIRE;
	}
	

	public HashMap<NavireType, Navire> getNavires() {
		return navires;
	}


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
    			grillePrincipale.setNomNavire(placementNavires.get(navireTypeTemp).get(i).getLigne(), 
    					                      placementNavires.get(navireTypeTemp).get(i).getCol(), navireTypeTemp);
    			grillePrincipale.setCaseStatut(placementNavires.get(navireTypeTemp).get(i).getLigne(), 
    					                       placementNavires.get(navireTypeTemp).get(i).getCol(), StatutCaseType.OCCUPE);
    		}
    	}		
    }


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
	
	
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", totalPoints=" + totalPoints + ", NBR_NAVIRE=" + NBR_NAVIRE + ", navires="
				+ navires + ", grilleAdverse=" + grilleAdverse + ", grillePrincipale=" + grillePrincipale + "]";
	}

	

}
