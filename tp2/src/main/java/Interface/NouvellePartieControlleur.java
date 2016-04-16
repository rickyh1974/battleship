
package Interface;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class NouvellePartieControlleur implements Initializable{

    @FXML
    private TextField champNom;
    @FXML
    private Label messageErreur;
    @FXML
    private ToggleGroup difficulteToggle;
    @FXML
    private VBox page;

        
    @FXML
    private void handleBtnCommencerPartie(ActionEvent event) throws Exception {
        if(champNom.getText().isEmpty()) {
            messageErreur.setVisible(true);
        }
        else {
            String nom = champNom.getText();
            String difficulte = difficulteToggle.getSelectedToggle().getUserData().toString();
            
            page.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Partie.fxml"));
            page.getChildren().add(loader.load());
            
            PartieControlleur controller = loader.<PartieControlleur>getController();
            controller.initData(nom, difficulte);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        champNom.setText("test"); //TRACE
        
    }    
    

}
