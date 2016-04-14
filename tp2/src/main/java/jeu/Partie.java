/**
 * 
 */
package jeu;

import java.util.LinkedList;

import IOXML.Chargement;
import IOXML.Sauvegarde;
import AI.JoueurAI;

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
     etatPartie = EtatPartieType.Encours;
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
	public void commencerPartie() {
		// TODO Auto-generated constructor stub
		

	}
	
	public void verifierVictoire() {
		// TODO Auto-generated constructor stub

	}
	public void executerTour(Joueur joueur) {
		
		
		
		
		// TODO Auto-generated constructor stub

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
