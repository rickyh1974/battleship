
package jeu;

import java.util.LinkedList;



import AI.JoueurAI;
import SauvegardeChargement.ChargementXML;
import SauvegardeChargement.SauvegardeXML;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Partie {
	

    private LinkedList<Action> listFIFOAction = new LinkedList<Action>(); 
    private EtatPartieType etatPartie;
    private NiveauPartieType niveau;
    private JoueurAI joueurAI;
    private Joueur joueurH;
    private static final int scorePourVictoire=17;
    
    private BooleanProperty estFinPartie = new SimpleBooleanProperty();
    public BooleanProperty estFinPartieProperty() {
        return estFinPartie;
    }
   
    public Boolean getEstFinPartie() {
        return estFinPartie.get();
    }
    
    public Partie() {
    }

	public Partie(String nomJoueur, NiveauPartieType niveau) {
     joueurAI = new JoueurAI();
     joueurH = new Joueur(nomJoueur);
     etatPartie = EtatPartieType.tourJoueurH;
     this.niveau = niveau;
     placerNavire();

	}
        
        public Partie(File fichier) {
            this.chargement(fichier.getPath());
        }
	
	public void initialiser(){
	}
	
	public void placerNavire(){
		joueurAI.autoPlacerNavire();
	}
        
        public void placerNaviresJoueurH(HashMap<NavireType, ArrayList<Coordonnee>> placementNavires) {
            joueurH.setPlacementNavires(placementNavires);
        }
        
            
	public void reCommencerPartie() {
		initialiser();
		commencerPartie();
	}
        
	public void commencerPartie() {
	}
	
	public Boolean verifierVictoire() {
            if(joueurAI.getTotalPoints()==scorePourVictoire||joueurH.getTotalPoints()==scorePourVictoire) {
                estFinPartie.set(Boolean.TRUE);
                return true;
            }
            else
            {
                return false;
            }
	}
	
	public void executerTour(Coordonnee temp) {
		
		Action action=new Action();
		Action actionAI=new Action();
		Coordonnee AItempRandomShot=new Coordonnee();		
			
		// tour du joueur humain		
		action.setNomJoeur(joueurH.getNom());			
		temp.setTouche(joueurAI.verifierShot(temp));
		if (temp.isTouche()) {
			joueurAI.getGrillePrincipale().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.TOUCHE);
			joueurH.getGrilleAdverse().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.TOUCHE);
			joueurH.ajouteUnTotalPoints();
			joueurAI.getNavire(joueurAI.getGrillePrincipale().getNomNavire(temp.getLigne(), temp.getCol())).retirerUnNbPoints();
		} else {
			joueurAI.getGrillePrincipale().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.DEMANDENONTOUCHE);
			joueurH.getGrilleAdverse().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.DEMANDENONTOUCHE);
			
			// tour joueur AI
			do {
				actionAI.setNomJoeur(joueurAI.getNom());
				AItempRandomShot=joueurAI.RandomShots();
				AItempRandomShot.setTouche(joueurH.verifierShot(AItempRandomShot));
				if ( AItempRandomShot.isTouche()) {
					joueurH.getGrillePrincipale().setCaseStatut(AItempRandomShot.getLigne(), AItempRandomShot.getCol(), StatutCaseType.TOUCHE);
					joueurAI.getGrilleAdverse().setCaseStatut(AItempRandomShot.getLigne(), AItempRandomShot.getCol(), StatutCaseType.TOUCHE);
					joueurAI.ajouteUnTotalPoints();
					joueurH.getNavire(joueurH.getGrillePrincipale().getNomNavire(AItempRandomShot.getLigne(), AItempRandomShot.getCol())).retirerUnNbPoints();
				} else {
					joueurH.getGrillePrincipale().setCaseStatut(AItempRandomShot.getLigne(), AItempRandomShot.getCol(), StatutCaseType.DEMANDENONTOUCHE);
					joueurAI.getGrilleAdverse().setCaseStatut(AItempRandomShot.getLigne(), AItempRandomShot.getCol(), StatutCaseType.DEMANDENONTOUCHE);
				}
				actionAI.setPoint(AItempRandomShot);
				listFIFOAction.add(actionAI);

			}while(!verifierVictoire() && AItempRandomShot.isTouche() );

		}
		action.setPoint(temp);
		listFIFOAction.add(action);
		verifierVictoire();
	}
	
	public Coordonnee executerTourbackup(Coordonnee temp) {
		
		Action action=new Action();
			
		if (getEtatPartie()==EtatPartieType.tourJoueurAI){
			action.setNomJoeur(joueurAI.getNom());
			temp=joueurAI.RandomShots();
			temp.setTouche(joueurH.verifierShot(temp));
			this.setEtatPartie(EtatPartieType.tourJoueurH);
			action.setPoint(temp);
			listFIFOAction.add(action);
			
		} else if (this.getEtatPartie()==EtatPartieType.tourJoueurH) {
			action.setNomJoeur(joueurH.getNom());			
			temp.setTouche(joueurAI.verifierShot(temp));
			if (temp.isTouche()) {
				joueurAI.getGrillePrincipale().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.TOUCHE);
				joueurH.getGrilleAdverse().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.TOUCHE);
				joueurH.ajouteUnTotalPoints();
				joueurAI.getNavire(joueurAI.getGrillePrincipale().getNomNavire(temp.getLigne(), temp.getCol())).retirerUnNbPoints();
			} else {
				joueurAI.getGrillePrincipale().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.DEMANDENONTOUCHE);
				joueurH.getGrilleAdverse().setCaseStatut(temp.getLigne(), temp.getCol(), StatutCaseType.DEMANDENONTOUCHE);
			}
			action.setPoint(temp);
			listFIFOAction.add(action);
			
		}
		
		return temp;

	}

        
	public LinkedList<Action> getListFIFOAction() {
		return listFIFOAction;
	}


	public void setListFIFOAction(LinkedList<Action> listFIFOAction) {
		this.listFIFOAction = listFIFOAction;
	}


	public JoueurAI getJoueurAI() {
		return joueurAI;
	}


	public void setJoueurAI(JoueurAI joueurAI) {
		this.joueurAI = joueurAI;
	}


	public Joueur getJoueurH() {
		return joueurH;
	}


	public void setJoueurH(Joueur joueurH) {
		this.joueurH = joueurH;
	}


	public EtatPartieType getEtatPartie() {
		return etatPartie;
	}


	public void setEtatPartie(EtatPartieType etatPartie) {
		this.etatPartie = etatPartie;
	}


	public NiveauPartieType getNiveau() {
		return niveau;
	}


	public void setNiveau(NiveauPartieType niveau) {
		this.niveau = niveau;
	}


	public static int getScorepourvictoire() {
		return scorePourVictoire;
	}
	
	public void sauvegardePartie(String path) {
				
		  SauvegardeXML.sauvegardeXML(this, path);
		 
	}
	public Partie chargement( String path) {
		
		return ChargementXML.chargementXML( path);
	}
	



	@Override
	public String toString() {
		return "Partie [listFIFOAction=" + listFIFOAction + ", etatPartie=" + etatPartie + ", niveau=" + niveau
				+ ", joueurAI=" + joueurAI + ", joueurH=" + joueurH + "]";
	}

}
