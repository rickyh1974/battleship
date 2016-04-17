/**
 * 
 */
package jeu;

import java.util.LinkedList;



import AI.JoueurAI;
import SauvegardeChargement.SauvegardeXML;
import SauvegardeChargement.ChargementXML;
/**
 * @author DELL
 *
 */
public class Partie {
	

    private LinkedList<Action> listFIFOAction = new LinkedList<Action>(); 
    private EtatPartieType etatPartie;
    private NiveauPartieType niveau;
    private JoueurAI joueurAI;
    private Joueur joueurH;
    private static final int scorePourVictoire=17;
    
    public Partie() {
    	// serialization xml convention java beans
    }
	/**
	 * 
	 */
	public Partie(String nomJoueur, NiveauPartieType niveau) {
     joueurAI = new JoueurAI();
     joueurH = new Joueur(nomJoueur);
     etatPartie = EtatPartieType.tourJoueurH;
     this.niveau = niveau;
     placerNavire();
		
		// TODO Auto-generated constructor stub
	}
	
	public void initialiser(){
		// TODO Auto-generated constructor stub
	   /*  joueurAI. = new JoueurAI();
	     joueurH = new Joueur(this.joueurH.getNom());
	     etatPartie = EtatPartieType.tourJoueurH;
	     listFIFOAction.clear();
	     placerNavire();*/
	}
	
	public void placerNavire(){
		// TODO Auto-generated constructor stub
		joueurAI.autoPlacerNavire();

	}
	
	public void reCommencerPartie() {
		initialiser();
		commencerPartie();
		// TODO Auto-generated constructor stub
		

	}
	public void commencerPartie() {
		// TODO Auto-generated constructor stub
		

	}
	
	public Boolean verifierVictoire() {
		
		return joueurAI.getTotalPoints()==scorePourVictoire||joueurH.getTotalPoints()==scorePourVictoire;
		// TODO Auto-generated constructor stub
	}
	
	public void afficheTouteLesGrilles() {
		System.out.println("Grille principale joueur AI");
		joueurAI.getGrillePrincipale().afficheGrille();
		System.out.println("Grille Adverse joueur AI");
		joueurAI.getGrilleAdverse().afficheGrille();
		System.out.println("Grille principale joueur H");
		joueurH.getGrillePrincipale().afficheGrille();
		System.out.println("Grille Adverse joueur H");
		joueurH.getGrilleAdverse().afficheGrille();
	}
	
	public void executerTour(Coordonnee temp) {
		
		Action action=new Action();
		Coordonnee AItempRandomShot=new Coordonnee();		
			
		//if (getEtatPartie()==EtatPartieType.tourJoueurAI){

			
		///} else if (this.getEtatPartie()==EtatPartieType.tourJoueurH) /*{*/
		// tour du joueur humain	
		do {
			action.setNomJoeur(joueurH.getNom());			
			temp.setTouche(joueurAI.verifierShot(temp));
			if (temp.isTouche()) {
				joueurAI.getGrillePrincipale().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.TOUCHE);
				joueurH.getGrilleAdverse().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.TOUCHE);
				joueurH.ajouteUnTotalPoints();
				joueurAI.getNavire(joueurAI.getGrillePrincipale().getNomNavire(temp.getLigne(), temp.getCol())).retirerUnNbPoints();
			} else {
				joueurAI.getGrillePrincipale().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.DEMANDENONTOUCHE);
				joueurH.getGrilleAdverse().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.DEMANDENONTOUCHE);
			}
			action.setPoint(temp);
			listFIFOAction.add(action);
			
			afficheTouteLesGrilles();
		}while(temp.isTouche()&& !verifierVictoire());
		
		do {
			action.setNomJoeur(joueurAI.getNom());
			AItempRandomShot=joueurAI.RandomShots();
			AItempRandomShot.setTouche(joueurH.verifierShot(AItempRandomShot));
			this.setEtatPartie(EtatPartieType.tourJoueurH);
			action.setPoint(AItempRandomShot);
			listFIFOAction.add(action);

			afficheTouteLesGrilles();
		}while(AItempRandomShot.isTouche()&& !verifierVictoire());
		
		//}
		
		//return temp;
		
		// TODO Auto-generated constructor stub

	}
	
	public Coordonnee executerTourbackup(Coordonnee temp) {
		
		Action action=new Action();
			
		if (getEtatPartie()==EtatPartieType.tourJoueurAI){
			action.setNomJoeur(joueurAI.getNom());
			temp=joueurAI.RandomShots();
			temp.setTouche(joueurH.verifierShot(temp));
			this.setEtatPartie(EtatPartieType.tourJoueurH);
			action.setPoint(temp);
			listFIFOAction.add(action);

			afficheTouteLesGrilles();
			
		} else if (this.getEtatPartie()==EtatPartieType.tourJoueurH) {
			action.setNomJoeur(joueurH.getNom());			
			temp.setTouche(joueurAI.verifierShot(temp));
			if (temp.isTouche()) {
				joueurAI.getGrillePrincipale().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.TOUCHE);
				joueurH.getGrilleAdverse().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.TOUCHE);
				joueurH.ajouteUnTotalPoints();
				joueurAI.getNavire(joueurAI.getGrillePrincipale().getNomNavire(temp.getLigne(), temp.getCol())).retirerUnNbPoints();
			} else {
				joueurAI.getGrillePrincipale().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.DEMANDENONTOUCHE);
				joueurH.getGrilleAdverse().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.DEMANDENONTOUCHE);
			}
			action.setPoint(temp);
			listFIFOAction.add(action);
			
			afficheTouteLesGrilles();
			
		}
		
		return temp;
		
		// TODO Auto-generated constructor stub

	}
	/**
	 * @return the listFIFOAction
	 */
	public LinkedList<Action> getListFIFOAction() {
		return listFIFOAction;
	}

	/**
	 * @param listFIFOAction the listFIFOAction to set
	 */
	public void setListFIFOAction(LinkedList<Action> listFIFOAction) {
		this.listFIFOAction = listFIFOAction;
	}

	/**
	 * @return the joueurAI
	 */
	public JoueurAI getJoueurAI() {
		return joueurAI;
	}

	/**
	 * @param joueurAI the joueurAI to set
	 */
	public void setJoueurAI(JoueurAI joueurAI) {
		this.joueurAI = joueurAI;
	}

	/**
	 * @return the joueurH
	 */
	public Joueur getJoueurH() {
		return joueurH;
	}

	/**
	 * @param joueurH the joueurH to set
	 */
	public void setJoueurH(Joueur joueurH) {
		this.joueurH = joueurH;
	}

	/**
	 * @return the etatPartie
	 */
	public EtatPartieType getEtatPartie() {
		return etatPartie;
	}

	/**
	 * @param etatPartie the etatPartie to set
	 */
	public void setEtatPartie(EtatPartieType etatPartie) {
		this.etatPartie = etatPartie;
	}

	/**
	 * @return the niveau
	 */
	public NiveauPartieType getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau the niveau to set
	 */
	public void setNiveau(NiveauPartieType niveau) {
		this.niveau = niveau;
	}

	/**
	 * @return the scorepourvictoire
	 */
	public static int getScorepourvictoire() {
		return scorePourVictoire;
	}
	
	public boolean sauvegardePartie(String path, String nomFichier) {
				
		 return SauvegardeXML.sauvegardeXML(this, path, nomFichier);
		 
	}
	public Partie chargement( String path, String nomFichier) {
		
		return ChargementXML.chargementXML( path, nomFichier);
	}
	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Partie [listFIFOAction=" + listFIFOAction + ", etatPartie=" + etatPartie + ", niveau=" + niveau
				+ ", joueurAI=" + joueurAI + ", joueurH=" + joueurH + "]";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
