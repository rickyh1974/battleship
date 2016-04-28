
package Interface;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import jeu.Action;

public class VisualiserControlleur implements Initializable{

    @FXML
    private Pane grilleGauche;
    
    @FXML
    private Pane grilleDroite;
    
    @FXML
    private Button btnVisualiser;
    
    @FXML
    private Button btnReVisualiser;
    
    @FXML
    private Label lblEnCours;
    
    @FXML
    private Label lblMessage;
    
    private GrilleControlleur grilleGaucheControlleur;
    private GrilleControlleur grilleDroiteControlleur;
    
    @FXML
    private void handleBtnVisualiser(ActionEvent event) throws Exception {
    
        btnVisualiser.setDisable(true);
        lblEnCours.setVisible(true);
    
        visualiser();
    }
    
    @FXML
    private void handleBtnReVisualiser(ActionEvent event) throws Exception {
        grilleGaucheControlleur.initialiserGrilleGauche();
        grilleGaucheControlleur.chargerNavires();
        grilleDroiteControlleur.initialiserGrilleDroite();
        
        btnReVisualiser.setDisable(true);
        lblEnCours.setVisible(true);
        visualiser();
    }
    
    private void visualiser() {
        LinkedList<Action> listFIFOAction = PartieEnCours.getPartie().getListFIFOAction();
        LinkedList<Action> copie = new LinkedList<>();
        int size = listFIFOAction.size();
        
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(600), e -> {

                if(!listFIFOAction.isEmpty()) {
                    Action action = listFIFOAction.remove(0);
                    copie.add(action);
                    if(action.getNomJoeur().equals("JoueurAI")) {
                    grilleGaucheControlleur.setAction(action);
                    }
                    else {
                        grilleDroiteControlleur.setAction(action);
                    }
                }
            
            })
        );
        timeline.setCycleCount(size);
        timeline.setOnFinished((ActionEvent arg0) -> {
            btnVisualiser.setVisible(false);
            btnReVisualiser.setVisible(true);
            btnReVisualiser.setDisable(false);
            lblEnCours.setVisible(false);
            PartieEnCours.getPartie().setListFIFOAction(copie);
        });
        
        timeline.play();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void initData() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleGauche.getChildren().add(loader.load());    
        grilleGaucheControlleur = loader.<GrilleControlleur>getController();
        grilleGaucheControlleur.initialiserGrilleGauche();
        grilleGaucheControlleur.chargerNavires();
        
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleDroite.getChildren().add(loader2.load());    
        grilleDroiteControlleur = loader2.<GrilleControlleur>getController();   
        grilleDroiteControlleur.initialiserGrilleDroite();
        
        grilleGauche.setDisable(true);
        grilleDroite.setDisable(true);

    }
    

}
