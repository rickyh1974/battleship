package jeu;

import java.util.ArrayList;
import java.util.HashMap;

//import java.util.ArrayList;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
        Grille grille = new Grille();
        
        grille.afficheGrille();
        
        grille.setCaseStatut(0, 9, 2);
        
        grille.setNomNavire(0, 9, NavireType.PORTEAVIONS);
        System.out.println(grille.getNomNavire(0, 9));
        grille.afficheGrille();
		
		/*char temp='A';
		//String temp1;
		
		System.out.print(" ");
		for (int j=0;j < 10;j++) {
		    System.out.print(j+1);
		}
		System.out.println();
		for( int j=0;j<10;j++){
			System.out.print(temp);
		   for(int i=0;i<10;i++){			  
			  System.out.print(grille.getCaseStatut(temp, i));			
		   }
		   temp=(char)((int)temp+1);
		   System.out.println();
		}*/
			
    }

}
