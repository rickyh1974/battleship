
package jeu;

import java.util.HashMap;
import java.util.ArrayList;



public class Grille {
	 
	HashMap<Integer,ArrayList<Case>> cases;
	

	public Grille() {
		
		cases=new HashMap<Integer,ArrayList<Case>>();

		for(int i=0;i<10;i++){
                    this.cases.put(i,new ArrayList<Case>(10));
                    for (int j=0;j < 10;j++) {
                        this.cases.get(i).add(new Case());
                    }
		}
	}
	

	public HashMap<Integer, ArrayList<Case>> getCases() {
		return cases;
	}


	public void setCases(HashMap<Integer, ArrayList<Case>> cases) {
		this.cases = cases;
	}


	public void setCaseStatut(int ligne, int col, StatutCaseType statut ){
		
		this.cases.get(ligne).get(col).setStatutCase(statut);	 	
	}
	

	public StatutCaseType getCaseStatut(int ligne, int col ) {
		
		return this.cases.get(ligne).get(col).getStatutCase();
	 	
	}
	
	public NavireType getNomNavire(int ligne, int col) {
		return this.cases.get(ligne).get(col).getNomNavire();
	}
	
	public void setNomNavire(int ligne, int col, NavireType nomNavire) {
		this.cases.get(ligne).get(col).setNomNavire(nomNavire);
	}
	
}
