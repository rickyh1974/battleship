
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

public class VisualiserControlleur implements Initializable{

    @FXML
    private Pane grilleGauche;
    
    @FXML
    private Pane grilleDroite;
    
    @FXML
    private Button btnVisualiser;
    
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
        
    /*
        LinkedList<Action> listFIFOAction = StaticPartie.getPartie().getListFIFOAction();
        for(Action action : listFIFOAction)  {
            System.out.println(action.toString());
        }*/
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleGauche.getChildren().add(loader.load());    
        grilleGaucheControlleur = loader.<GrilleControlleur>getController();
        
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleDroite.getChildren().add(loader2.load());    
        grilleDroiteControlleur = loader2.<GrilleControlleur>getController();
        
        grilleGaucheControlleur.initialiserGrilleGauche();
        grilleDroiteControlleur.initialiserGrilleDroite();
        
        grilleGauche.setDisable(true);
        grilleDroite.setDisable(true);
        
        
        //grilleGaucheControlleur.initialiserNavires();
    }
    

}
