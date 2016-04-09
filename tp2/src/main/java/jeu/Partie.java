/**
 * 
 */
package jeu;

import java.util.LinkedList;

import IOXML.sauvegarde;







/**
 * @author DELL
 *
 */
public class Partie {
	
	LinkedList<Action> listFIFOAction = new LinkedList<Action>(); 

	/**
	 * 
	 */
	public Partie() {
     
     
		
		// TODO Auto-generated constructor stub
	}
	
	public void initialiser(){
		// TODO Auto-generated constructor stub
	}
	
	public void placerNavire(){
		// TODO Auto-generated constructor stub

	}
	public void commencerPartie() {
		// TODO Auto-generated constructor stub

	}
	
	public void verifierVictoire() {
		// TODO Auto-generated constructor stub

	}
	public void executerTour() {
		// TODO Auto-generated constructor stub

	}
	public boolean sauvegardePartie(String partieXML,String path, String nomFichier) {
		return IOXML.SauvegardeXML(partieXML,path,nomFichier);// sauvegarde(partieXML,path,nomFichier);
	}
	public boolean chargement(String partieXML, String path, String nomFichier) {
		return true;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
