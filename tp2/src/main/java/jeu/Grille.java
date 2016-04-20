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
	 * @return the cases
	 */
	public HashMap<Integer, ArrayList<Case>> getCases() {
		return cases;
	}

	/**
	 * @param cases the cases to set
	 */
	public void setCases(HashMap<Integer, ArrayList<Case>> cases) {
		this.cases = cases;
	}

	/**
	 * setCaseStatut
	 * parametres: 
	 * ligne qui peut être un chiffre de 1 à 9
	 * col   qui peut être un chiffre de 1 à 9
	 * statut qui peut être 
	 * 0 valeur par défaut voulant case vide et non touché
	 * 1 coordonnée déjà donnée par joueur
	 * 2 coordonnée touché  
	 */
	public void setCaseStatut(int ligne, int col, StatutCaseType statut ){
		
		this.cases.get(ligne).get(col).setStatutCase(statut);
			 	
	}
	
	/**
	 * getCaseStatut
	 * parameters : 
	 * ligne qui peut être un chiffre de 1 à 9
	 * col qui peut être un chiffre de 1 à 9
	 */
	public StatutCaseType getCaseStatut(int ligne, int col ) {
		
		return this.cases.get(ligne).get(col).getStatutCase();
	 	
	}
	
	public NavireType getNomNavire(int ligne, int col) {
		return this.cases.get(ligne).get(col).getNomNavire();
	}
	
	public void setNomNavire(int ligne, int col, NavireType nomNavire) {
		this.cases.get(ligne).get(col).setNomNavire(nomNavire);
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
			  System.out.print(this.cases.get(j).get(i).getStatutCase()+" " );			
		   }
		
		   System.out.println();
		}
	}	
}
