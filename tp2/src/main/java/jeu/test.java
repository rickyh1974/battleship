package jeu;

//import java.util.ArrayList;
//import java.util.HashMap;

//import java.util.concurrent.ThreadLocalRandom;

//import java.util.ArrayList;
//import AI.JoueurAI;
//import SauvegardeChargement.SauvegardeXML; ;

public class test {

	

	public static void main(String[] args) {
		
		
		//JoueurAI joueurAI = new JoueurAI();
		//Joueur joueur = new Joueur("Ricky");
		Partie partie = new Partie("ricky", NiveauPartieType.FACILE);
		Partie partie1= new Partie("ricky", NiveauPartieType.FACILE);
		Coordonnee temp;
		Coordonnee tempRecu=new Coordonnee();
		//for ( NavireType navireTypeTemp : NavireType.values() ) {
			partie.placerNavire();
			for( int j=0;j<10;++j){
			
				for( int i=0;i<10;++i) {
					tempRecu.setLigne(j);
					tempRecu.setCol(i);
					tempRecu.toString();
					temp=partie.executerTour(tempRecu);
					System.out.println(tempRecu.toString());
					System.out.println(temp.toString());
				}

			}
			
			SauvegardeChargement.SauvegardeXML.sauvegardeXML(partie, "c:\\temp\\", "partie.xml");
			SauvegardeChargement.SauvegardeXML.sauvegardeXML(partie, "c:\\temp\\", "grillePrincAI.xml");
			SauvegardeChargement.SauvegardeXML.sauvegardeXML((partie.getJoueurAI().getGrilleAdverse()), "c:\\temp\\", "grilleAdverseAI.xml");
			SauvegardeChargement.SauvegardeXML.sauvegardeXML(partie, "c:\\temp\\", "partieTestAppend.xml");
			SauvegardeChargement.SauvegardeXML.sauvegardeXML(partie.getJoueurAI().getGrillePrincipale(), "c:\\temp\\", "partieTestAppend.xml");
			SauvegardeChargement.SauvegardeXML.sauvegardeXML(partie.getJoueurAI().getGrilleAdverse(), "c:\\temp\\", "partieTestAppend.xml");
			
			//SauvegardeChargement.ChargementXML.chargementXML(partie1, "c:\\temp\\", "partie.xml");
            partie1.afficheTouteLesGrilles();
            System.out.println(partie.toString());
            
			
    }

}
