/**
 * 
 */
package jeu;

import java.util.LinkedList;

import IOXML.Chargement;
import IOXML.Sauvegarde;
import AI.JoueurAI;
import java.util.Scanner; // testing purposes only

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
	/**
	 * 
	 */
	public Partie(String nomJoueur, NiveauPartieType niveau) {
     joueurAI = new JoueurAI();
     joueurH = new Joueur(nomJoueur);
     etatPartie = EtatPartieType.tourJoueurH;
     this.niveau = niveau;  
		
		// TODO Auto-generated constructor stub
	}
	
	public void initialiser(){
		// TODO Auto-generated constructor stub
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
	
	public void verifierVictoire() {
		// TODO Auto-generated constructor stub

	}
	
	public void afficheTouteLesGrilles() {
		System.out.println("Grille principale joueur AI");
		joueurAI.getgrillePrincipale().afficheGrille();
		System.out.println("Grille Adverse joueur AI");
		joueurAI.getgrilleAdverse().afficheGrille();
		System.out.println("Grille principale joueur H");
		joueurH.getgrillePrincipale().afficheGrille();
		System.out.println("Grille Adverse joueur H");
		joueurH.getgrilleAdverse().afficheGrille();
	}
	
	public Coordonnee executerTour(Coordonnee temp) {
		
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
				joueurAI.getgrillePrincipale().setCaseStatut(temp.getLigne(), temp.getCol(), 2);
				joueurH.getgrilleAdverse().setCaseStatut(temp.getLigne(), temp.getCol(), 2);
				joueurH.ajouteUnTotalPoints();
				joueurAI.getNavire(joueurAI.getgrillePrincipale().getNomNavire(temp.getLigne(), temp.getCol())).retirerUnNbPoints();
			} else {
				joueurAI.getgrillePrincipale().setCaseStatut(temp.getLigne(), temp.getCol(), 3);
				joueurH.getgrilleAdverse().setCaseStatut(temp.getLigne(), temp.getCol(), 3);
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

	public boolean sauvegardePartie(String partieXML,String path, String nomFichier) {
		return Sauvegarde.sauvegardeXML(partieXML, path, nomFichier);
	}
	public boolean chargement(String partieXML, String path, String nomFichier) {
		
		return Chargement.chargementXML(partieXML, path, nomFichier);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
