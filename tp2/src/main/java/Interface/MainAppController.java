package Interface;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jeu.Partie;

/**
 *
 * @author Alexandre
 */
public class MainAppController implements Initializable {
    
    public Partie partie;
    public String nom;
    public String difficulte;
    
    public void setPartie(Partie p) {
        partie = p;
    }
    
    public void setNom(String s) {
        this.nom = s;
    }
    
    public void setDifficulte(String s) {
        this.difficulte = s;
        btnRecommencerPartie.setDisable(false);
    }
    
    @FXML
    private Pane ecranCentre;
    
    @FXML
    private Button btnRecommencerPartie;
    
    @FXML
    private void handleBtnNouvellePartie(ActionEvent event) throws Exception {
        ecranCentre.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NouvellePartie.fxml"));
        ecranCentre.getChildren().add(loader.load());
        NouvellePartieControlleur nouvellePartieControlleur = loader.<NouvellePartieControlleur>getController();
        nouvellePartieControlleur.initData(this);
        
    }
    
    @FXML
    private void handleBtnRecommencerPartie(ActionEvent event) throws Exception {
        
        ecranCentre.getChildren().clear();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Partie.fxml"));
        ecranCentre.getChildren().add(loader.load());
        PartieControlleur controlleur = loader.<PartieControlleur>getController();
        controlleur.initData(this.nom, this.difficulte);
    }
    
    @FXML
    private void handleBtnCharger(ActionEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger Partie");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File fichier = fileChooser.showOpenDialog(new Stage());
        System.out.println(fichier); //TRACE
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Partie.fxml"));
        ecranCentre.getChildren().add(loader.load());
        PartieControlleur controlleur = loader.<PartieControlleur>getController();
        controlleur.initDataChargement(fichier);
        
    }
    
    @FXML
    private void handleBtnSauvegarder(ActionEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sauvegarder Partie");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File fichier = fileChooser.showSaveDialog(new Stage());
        System.out.println(fichier); //TRACE
        //TODO SAUVEGARDE DE FICHIER
    }
    
    @FXML
    private void handleBtnVisualiser(ActionEvent event) throws Exception {
        
        ecranCentre.getChildren().clear();
        ecranCentre.getChildren().add(FXMLLoader.load(getClass().getResource("/fxml/Visualiser.fxml")));
        
    }
    
    @FXML
    private void handleBtnQuitter(ActionEvent event) throws Exception {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        
        alert.setTitle("Quitter");
        String s = "Voulez-vous vraiment quitter ?";
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Platform.exit();
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
