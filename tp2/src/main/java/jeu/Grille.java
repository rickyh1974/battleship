/**
 * 
 */
package jeu;

import java.util.HashMap;
import java.util.ArrayList;


/**
 * @author Ricky Hoben
 *
 */
public class Grille {
	 
	HashMap<Integer,ArrayList<Case>> cases;
	
	/**
	 * constructeur de la classe grille 
	*/
	public Grille() {
		
		cases=new HashMap<Integer,ArrayList<Case>>();
		
		
		//System.out.print(" ");
		//for (int j=0;j < 10;j++) {
		//    System.out.print(j+1);
		//}
		//System.out.println();
		for(int i=0;i<10;i++){
			
		//	System.out.print(temp);
			//this.cases.put(Character.toString(temp),new ArrayList<Case>(10));
			this.cases.put(i,new ArrayList<Case>(10));
			// initialise arraylist d'objets de type Case
			for (int j=0;j < 10;j++) {
				this.cases.get(i).add(new Case());
			}
			
			//
		//	for (int j=0;j < 10;j++) {
				
		//		System.out.print(cases.get(Character.toString(temp)).get(j).getStatutCase());
		//	}
		//	System.out.println();
		
		}
			
	}
	
	/**
	 * setCaseStatut
	 * parametres: 
	 * x qui peut être un chiffre de 1 à 9
	 * y qui peut être un chiffre de 1 à 9
	 * statut qui peut être 
	 * 0 valeur par défaut voulant case vide et non touché
	 * 1 coordonnée déjà donnée par joueur
	 * 2 coordonnée touché  
	 */
	public void setCaseStatut(int x, int y, int statut ){
		
		this.cases.get(x).get(y).setStatutCase(statut);
			 	
	}
	
	/**
	 * getCaseStatut
	 * parameters : 
	 * x qui peut être un chiffre de 1 à 9
	 * y qui peut être un chiffre de 1 à 9
	 */
	public int getCaseStatut(int x, int y ) {
		
		return this.cases.get(x).get(y).getStatutCase();
	 	
	}
	
	public NavireType getNomNavire(int x, int y) {
		return this.cases.get(x).get(y).getNomNavire();
	}
	
	public void setNomNavire(int x, int y, NavireType nomNavire) {
		this.cases.get(x).get(y).setNomNavire(nomNavire);
	}
	
	public void afficheGrille(){
		
		
		
		System.out.print(" ");
		for (int j=0;j < 10;j++) {
		    System.out.print(j);
		}
		System.out.println();
		for( int j=0;j<10;j++){
			System.out.print(j);
		   for(int i=0;i<10;i++){			  
			  System.out.print(this.cases.get(j).get(i).getStatutCase() );			
		   }
		
		   System.out.println();
		}
	}
	
	

}
