
package Interface;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class PartieControlleur implements Initializable{

    private String nom;
    private String difficulte;
    
    @FXML
    private Pane grilleGauche;
    
    @FXML
    private Pane grilleDroite;
    
    @FXML
    private StackPane stackPaneNavires;
    
    @FXML
    private Rectangle rectangle;
    
    @FXML
    private Button btnCommencer;
    
    @FXML
    private Label lblEnCours;
    
    @FXML
    private Label lblMessage;
    
    @FXML
    private void handleBtnCommencer(ActionEvent event) throws Exception {
        btnCommencer.setDisable(true);
        lblEnCours.setVisible(true);
        grilleGauche.setDisable(true);
        grilleDroite.setDisable(false);
        lblMessage.setText("C'est à votre tour ! Veuillez choisir une case à attaquer");
        
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleDroite.getChildren().add(loader2.load());    
        GrilleControlleur controller2 = loader2.<GrilleControlleur>getController();
        controller2.initialiserGrilleDroite();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    public void initData(String nom, String difficulte) throws Exception {
        this.nom = nom;
        this.difficulte = difficulte;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleGauche.getChildren().add(loader.load());    
        GrilleControlleur controller = loader.<GrilleControlleur>getController();
        controller.initialiserGrilleGauche();
        
        
        
    }
    
    

}
