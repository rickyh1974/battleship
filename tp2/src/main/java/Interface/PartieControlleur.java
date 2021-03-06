
package Interface;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import jeu.Coordonnee;
import jeu.NavireType;
import jeu.NiveauPartieType;
import jeu.Partie;

public class PartieControlleur implements Initializable{

    private String nom;
    private String difficulte;
    private Partie partie;
    HashMap<NavireType, ArrayList<Coordonnee>> placementNavires = new HashMap<>();
    
    @FXML
    private Pane grilleGauche;
    
    @FXML
    private Pane grilleDroite;
    
    @FXML
    private Button btnCommencer;
    
    @FXML
    private Label lblEnCours;
    
    @FXML
    private Label lblMessage;
    
    @FXML
    private Label lblErreurPlacement;
    
    private GrilleControlleur grilleGaucheControlleur;
    
    @FXML
    private void handleBtnCommencer(ActionEvent event) throws Exception {
        if(validerPlacementNavires())
        {
            PartieEnCours.getPartie().placerNaviresJoueurH(placementNavires);
            
            btnCommencer.setDisable(true);
            lblEnCours.setVisible(true);
            lblErreurPlacement.setVisible(false);
            grilleGauche.setDisable(true);
            grilleDroite.setDisable(false);
            lblMessage.setText("C'est à votre tour ! Veuillez choisir une case à attaquer");
        
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
            grilleDroite.getChildren().add(loader2.load());    
            GrilleControlleur controller2 = loader2.<GrilleControlleur>getController();
            controller2.initialiserGrilleDroite();
             
            final ChangeListener changeListener = (ChangeListener) (ObservableValue observableValue, Object oldValue, Object newValue) -> {
                int ligne = ((Coordonnee)newValue).getLigne();
                int col = ((Coordonnee)newValue).getCol();
                PartieEnCours.getPartie().executerTour(new Coordonnee(col,ligne));
            };
            controller2.coordonneeProperty.addListener(changeListener);
        
            
            final ChangeListener changeListener2 = (ChangeListener) (ObservableValue observableValue, Object oldValue, Object newValue) -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
                    alert.setTitle("Partie terminée");
                    String message;
                    if(PartieEnCours.getPartie().getJoueurAI().getTotalPoints() > PartieEnCours.getPartie().getJoueurH().getTotalPoints())
                        message = "Le joueur AI a gagné la partie !";
                    else
                        message = "Vous avez gagné la partie !";
                    alert.setContentText(message);
                    grilleDroite.setDisable(true);
                    lblMessage.setText("Fin de la Partie ! Vous pouvez la visualiser avec le bouton Visualiser dans le menu de gauche");
                    lblEnCours.setVisible(false);
                    Optional<ButtonType> result = alert.showAndWait();
                    
                
            };
        
            PartieEnCours.getPartie().estFinPartieProperty().addListener(changeListener2);
        }
        else { //Placement Navires non valide
            lblErreurPlacement.setVisible(true);
            lblErreurPlacement.setTextFill(Color.RED);
        }
    }
    
    private boolean validerPlacementNavires() {
        
        placementNavires = grilleGaucheControlleur.placerNavires();
        
        if(!validationCoordonnees(NavireType.PORTEAVIONS) 
                || !validationCoordonnees(NavireType.CROISSEUR)
                || !validationCoordonnees(NavireType.CONTRETORPILLEUR)
                || !validationCoordonnees(NavireType.SOUSMARIN)
                || !validationCoordonnees(NavireType.TORPILLEUR))
            return false;
        
        
        return true;
    }
    
    private boolean validationCoordonnees(NavireType navireType) {
        ArrayList<Coordonnee> coord = placementNavires.get(navireType);
        Coordonnee precedente = coord.get(0);
        boolean estHorizontal = false;
        boolean estVertical = false;
        for(Coordonnee c : coord) {
            if(c.getCol() > (precedente.getCol()+1) || c.getCol() < (precedente.getCol()-1))
                return false;
            if(c.getCol() == precedente.getCol() && c.getLigne() > (precedente.getLigne()+1))
                return false;
            if(c.getCol() == (precedente.getCol()+1) && c.getLigne() >= (precedente.getLigne()+1))
                return false;
            if(c.getCol() == (precedente.getCol()-1) && c.getLigne() <= (precedente.getLigne()-1))
                return false;
            if(c.getCol() == (precedente.getCol()+1) && c.getLigne() <= (precedente.getLigne()-1))
                return false;
            if(c.getCol() == (precedente.getCol()-1) && c.getLigne() >= (precedente.getLigne()+1))
                return false;
            
            if(c.getLigne() == precedente.getLigne() && c.getCol() != precedente.getCol())
                estHorizontal = true;
            if(c.getCol() == precedente.getCol() && c.getLigne() != precedente.getLigne())
                estVertical = true;
                
            if(estHorizontal==true && estVertical == true)
                return false;
            
            precedente = c;
        }
        return true;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void initData(String nom, String difficulte) throws Exception {
        this.nom = nom;
        this.difficulte = difficulte;
        
        PartieEnCours.setPartie(new Partie(nom,NiveauPartieType.valueOf(difficulte)));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleGauche.getChildren().add(loader.load());    
        grilleGaucheControlleur = loader.<GrilleControlleur>getController();
        grilleGaucheControlleur.initialiserGrilleGauche();
        grilleGaucheControlleur.initialiserNavires();
    }
    
    public void initRecommencer() throws Exception {
        this.nom = PartieEnCours.getPartie().getJoueurH().getNom();
        this.difficulte = PartieEnCours.getPartie().getNiveau().name();        
        PartieEnCours.setPartie(new Partie(this.nom,NiveauPartieType.valueOf(this.difficulte)));
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleGauche.getChildren().add(loader.load());
        grilleGaucheControlleur = loader.<GrilleControlleur>getController();
        grilleGaucheControlleur.initialiserGrilleGauche();
        grilleGaucheControlleur.initialiserNavires();
    }
    
    public void initDataChargement(File fichier) throws Exception {
        
        Partie p = new Partie("",NiveauPartieType.FACILE);
        PartieEnCours.setPartie(p.chargement(fichier.getPath()));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleGauche.getChildren().add(loader.load());    
        grilleGaucheControlleur = loader.<GrilleControlleur>getController();
        grilleGaucheControlleur.initialiserGrilleGauche();
        grilleGaucheControlleur.chargerNavires();
    }
    

    
}
