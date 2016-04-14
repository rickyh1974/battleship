/**
 * 
 */
package AI;

import java.util.concurrent.ThreadLocalRandom;


import jeu.Grille;
import jeu.NavireType;
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

	/**
	 * 
	 */
	public JoueurAI() {		
		super("JoueurAI");
		this.niveau=0;
        
	}

	/* places les navires du joueur AI */
	public void autoPlacerNavire() {
		
		int randLigne=0,randCol=0,randOrient=0,compteur=0,tempCol=0,tempLigne=0,tailleNavire=0;
		Grille grille = this.getgrillePrincipale();
		boolean returnVal=false;
		final int MaxLigne = 10;
		NavireType nomNavire;
		
		grille.afficheGrille();
		
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
	
					grille.setNomNavire(tempLigne,randCol, nomNavire);
					grille.setCaseStatut(tempLigne, randCol, 1);
					++compteur;
					++tempLigne;
				}
		    } else {
		    	compteur=0;
		    	tempCol=randCol;
			    while(  compteur != tailleNavire ) {					
					System.out.println("randLigne="+randLigne+" tempCol="+tempCol+" randCol="+randCol);
					grille.setNomNavire(randLigne,tempCol, nomNavire);
					grille.setCaseStatut(randLigne,tempCol, 1);
					++compteur;
					++tempCol;
					
				}
		    }
	    
		}
	    
        grille.afficheGrille();

	}
	
	public Coordonnee RandomShots(){
		int randCol=0,randLigne=0;
	//boolean returnValue=false;
		Coordonnee returnShot = new Coordonnee();
		
		// cherche un point qui n'a pas encore été utilisé
		do {
			randCol=ThreadLocalRandom.current().nextInt(0, 10);					
			randLigne=ThreadLocalRandom.current().nextInt(0, 10);	
	/*		if (this.getgrilleAdverse().getCaseStatut(randLigne, randCol)== 0 ){
				returnValue=true;
			}else{
				returnValue=false;
			};	*/
		} while( ((this.getgrilleAdverse().getCaseStatut(randLigne, randCol)== 0 ) ? true : false) );
		
		
		
		returnShot.setLigne(randLigne);
		returnShot.setCol(randCol);
			
		return returnShot; 
		 
		// to do develop
	}
	
	public void MinMaxShots(){
		// 	to do develop
		
	}
	
	
}
