package jeu;

//import java.util.ArrayList;
//import java.util.HashMap;

//import java.util.concurrent.ThreadLocalRandom;

//import java.util.ArrayList;
//import AI.JoueurAI;
//import SauvegardeChargement.SauvegardeXML; ;
import Interface.Tuile;

public class test {

	

	public static void main(String[] args) {
		
		
		//JoueurAI joueurAI = new JoueurAI();
		//Joueur joueur = new Joueur("Ricky");
		Tuile tuile = new Tuile();
		Partie partie = new Partie("ricky", NiveauPartieType.FACILE);
		Partie partie1= null;
		Coordonnee temp;
		Coordonnee tempRecu=new Coordonnee();
	//	for ( NavireType navireTypeTemp : NavireType.values() ) {
			partie.placerNavire();
			/*for( int j=0;j<10;++j){
			
				for( int i=0;i<10;++i) {
					tempRecu.setLigne(j);
					tempRecu.setCol(i);
					tempRecu.toString();
					temp=partie.executerTour(tempRecu);
					System.out.println(tempRecu.toString());
					System.out.println(temp.toString());
				}

			}
		//}	
			
		/*	SauvegardeChargement.SauvegardeXML.sauvegardeXML(partie, "c:\\temp\\", "partie.xml");
			partie.afficheTouteLesGrilles();
			partie1=SauvegardeChargement.ChargementXML.chargementXML( "c:\\temp\\", "partie.xml");
			//SauvegardeChargement.ChargementXML.chargementXML(partie, "c:\\temp\\", "partietest.xml");
			System.out.println("Object partie");
			partie1.afficheTouteLesGrilles();
			/*System.out.println("Object partie 1");
            partie.afficheTouteLesGrilles();
            System.out.println(partie1.toString());*/
            
			
    }

}
