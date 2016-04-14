package jeu;

//import java.util.ArrayList;
//import java.util.HashMap;

//import java.util.concurrent.ThreadLocalRandom;

//import java.util.ArrayList;
import AI.JoueurAI;

public class test {

	

	public static void main(String[] args) {
		
		
		//JoueurAI joueurAI = new JoueurAI();
		Partie partie = new Partie("ricky", NiveauPartieType.FACILE);
		
		//for ( NavireType navireTypeTemp : NavireType.values() ) {
			partie.placerNavire();
			
			//joueurAI.autoPlacerNavire(joueurAI.getNavire(navireTypeTemp).getTaille(), joueurAI.getNavire(navireTypeTemp).getNom());
		//}
		
		/*joueurAI.autoPlacerNavire(joueurAI.getNavire(NavireType.PORTEAVIONS).getTaille(), joueurAI.getNavire(NavireType.PORTEAVIONS).getNom());
		joueurAI.autoPlacerNavire(joueurAI.getNavire(NavireType.CROISSEUR).getTaille(), joueurAI.getNavire(NavireType.CROISSEUR).getNom());
		joueurAI.autoPlacerNavire(joueurAI.getNavire(NavireType.CONTRETORPILLEUR).getTaille(), joueurAI.getNavire(NavireType.CONTRETORPILLEUR).getNom());
		joueurAI.autoPlacerNavire(joueurAI.getNavire(NavireType.SOUSMARIN).getTaille(), joueurAI.getNavire(NavireType.SOUSMARIN).getNom());
		joueurAI.autoPlacerNavire(joueurAI.getNavire(NavireType.TORPILLEUR).getTaille(), joueurAI.getNavire(NavireType.TORPILLEUR).getNom());
		/*int randLigne=0,randCol=0,randOrient=0,compteur=0,tempCol=0,tempLigne=0,tailleNavire=0;
		Grille grille = new Grille();
		PorteAvions porteAvion = new PorteAvions();
		boolean returnVal=false;
		final int MaxLigne = 10;
		tailleNavire=porteAvion.getTaille();

		grille.afficheGrille();		
	    do {
	    	randOrient=ThreadLocalRandom.current().nextInt(0, 2);
			randCol=ThreadLocalRandom.current().nextInt(0, 10);					
			randLigne=ThreadLocalRandom.current().nextInt(0, 10);
			tempLigne=randLigne;
			tempCol=randCol;
			
			// vertical
			if (randOrient == 0) {
				//
				System.out.println("randOrient="+randOrient+"tempLigne="+tempLigne+" randLigne="+randLigne+"tempCol="+tempCol+" randCol="+randCol);
				if ( randLigne <= (MaxLigne-tailleNavire) ){
					while( tempLigne < 10 && grille.getCaseStatut(tempLigne, randCol)==0 && compteur != tailleNavire ) {					
						++compteur;
						++tempLigne;
						
					}
				}
				if (compteur==tailleNavire) {

					returnVal=true;
				} else{
					returnVal=false;
				}
					
				
			}else{
				System.out.println("randOrient="+randOrient+"tempLigne="+tempLigne+" randLigne="+randLigne+"tempCol="+tempCol+" randCol="+randCol);
				if ( randCol <= (MaxLigne-tailleNavire) ){
					while( tempCol < 10 && grille.getCaseStatut(randLigne, tempCol)==0 && compteur != tailleNavire ) {					
						++compteur;
						++tempCol;
					}
				}	
				if (compteur==tailleNavire){
					returnVal=true;
				} else{
					returnVal=false;
				}
					
			}

	    }while( returnVal== false);
	    
	    
	    if (randOrient == 0) {
	    	compteur=0;
	    	tempLigne=randLigne;
		    while(  compteur != tailleNavire ) {					

				grille.setNomNavire(tempLigne,randCol, NavireType.PORTEAVIONS);
				grille.setCaseStatut(tempLigne, randCol, 1);
				++compteur;
				++tempLigne;
			}
	    } else {
	    	compteur=0;
	    	tempCol=randCol;
		    while(  compteur != tailleNavire ) {					
				System.out.println("randLigne="+randLigne+" tempCol="+tempCol+" randCol="+randCol);
				grille.setNomNavire(randLigne,tempCol, NavireType.PORTEAVIONS);
				grille.setCaseStatut(randLigne,tempCol, 1);
				++compteur;
				++tempCol;
				
			}
	    }
	    
        grille.afficheGrille();
*/        
      //  grille.setCaseStatut(0, 9, 2);
        
      //  grille.setNomNavire(0, 9, NavireType.PORTEAVIONS);
     //   System.out.println(grille.getNomNavire(0, 9));
 
        

			
    }

}
