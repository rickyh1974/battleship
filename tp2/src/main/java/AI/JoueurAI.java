/**
 * 
 */
package AI;

import java.util.concurrent.ThreadLocalRandom;


import jeu.Grille;
import jeu.NavireType;
import jeu.StatutCaseType;
import jeu.Coordonnee;
import jeu.Joueur;

/**
 * @author DELL
 *
 */
public class JoueurAI extends Joueur {
	/* niveau difficulté 
	* 0 - facile
	* 1 - difficile
	*/ 
	int niveau;

	
	public JoueurAI() {		
		super("JoueurAI");
		this.niveau=0;
        
	}

	/* places les navires du joueur AI */
	public void autoPlacerNavire() {
		
		int randLigne=0,randCol=0,randOrient=0,compteur=0,tempCol=0,tempLigne=0,tailleNavire=0;
		Grille grille = this.getGrillePrincipale();
		boolean returnVal=false;
		final int MaxLigne = 10;
		NavireType nomNavire;
		
		for ( NavireType navireTypeTemp : NavireType.values() ) {
			compteur=0;
			
			nomNavire=this.getNavire(navireTypeTemp).getNom();
			tailleNavire=this.getNavire(navireTypeTemp).getTaille();
		
		    do {
		    	randOrient=ThreadLocalRandom.current().nextInt(0, 2);
				randCol=ThreadLocalRandom.current().nextInt(0, 10);					
				randLigne=ThreadLocalRandom.current().nextInt(0, 10);
				tempLigne=randLigne;
				tempCol=randCol;
				
				// vertical
				if (randOrient == 0) {
					
					if ( randLigne <= (MaxLigne-tailleNavire) ){
						while( tempLigne < 10 && grille.getCaseStatut(tempLigne, randCol)==StatutCaseType.RIEN && compteur != tailleNavire ) {					
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
					if ( randCol <= (MaxLigne-tailleNavire) ){
						while( tempCol < 10 && grille.getCaseStatut(randLigne, tempCol)==StatutCaseType.RIEN && compteur != tailleNavire ) {					
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
	
					grille.setNomNavire(tempLigne,randCol, nomNavire);
					grille.setCaseStatut(tempLigne, randCol, StatutCaseType.OCCUPE);
					++compteur;
					++tempLigne;
				}
		    } else {
		    	compteur=0;
		    	tempCol=randCol;
			    while(  compteur != tailleNavire ) {					
					grille.setNomNavire(randLigne,tempCol, nomNavire);
					grille.setCaseStatut(randLigne,tempCol, StatutCaseType.OCCUPE);
					++compteur;
					++tempCol;
					
				}
		    }
	    
		}
	}
	
	public Coordonnee RandomShots(){
		int randCol=0,randLigne=0;
                
		Coordonnee returnShot = new Coordonnee();
		
		// cherche un point qui n'a pas encore été utilisé
		do {
			randCol=ThreadLocalRandom.current().nextInt(0, 10);					
			randLigne=ThreadLocalRandom.current().nextInt(0, 10);	
		} while( ((this.getGrilleAdverse().getCaseStatut(randLigne, randCol)== StatutCaseType.RIEN ) ? false : true) );
		
		
		returnShot.setLigne(randLigne);
		returnShot.setCol(randCol);
			
		return returnShot; 
	}
	
	public void MinMaxShots(){
		
	}
	
	
}
