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

/**
 *
 * @author Alexandre
 */
public class MainAppController implements Initializable {
    
    @FXML
    private Pane ecranCentre;
    
    @FXML
    private Button btnRecommencerPartie;
    
    @FXML
    private void handleBtnNouvellePartie(ActionEvent event) throws Exception {
        nouvellePartie();
    }
    
    @FXML
    private void handleBtnRecommencerPartie(ActionEvent event) throws Exception {
        
        if(StaticPartie.getPartie() != null)
        {
            ecranCentre.getChildren().clear();
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Partie.fxml"));
            ecranCentre.getChildren().add(loader.load());
            PartieControlleur controlleur = loader.<PartieControlleur>getController();
            controlleur.initRecommencer();
        }
    }
    
    @FXML
    private void handleBtnCharger(ActionEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger Partie");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File fichier = fileChooser.showOpenDialog(new Stage());
        
        if(fichier != null) {
            ecranCentre.getChildren().clear();
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Partie.fxml"));
            ecranCentre.getChildren().add(loader.load());
            PartieControlleur controlleur = loader.<PartieControlleur>getController();
            controlleur.initDataChargement(fichier);
        }
        
    }
    
    @FXML
    private void handleBtnSauvegarder(ActionEvent event) throws Exception {
        if(StaticPartie.getPartie() != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sauvegarder Partie");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
            fileChooser.getExtensionFilters().add(extFilter);
            File fichier = fileChooser.showSaveDialog(new Stage());
        
            if(fichier != null) {
            
                StaticPartie.getPartie().sauvegardePartie(fichier.getPath());
                Alert alert = new Alert(AlertType.INFORMATION);
        
                alert.setTitle("Fichier Sauvegardé");
                String s = "Le fichier " + fichier.getName() + " a bien été sauvegardé dans " + fichier.getPath();
                alert.setContentText(s);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
            
                }
            }
        }
        
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
    
    private void nouvellePartie() throws Exception{
        ecranCentre.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NouvellePartie.fxml"));
        ecranCentre.getChildren().add(loader.load());
        NouvellePartieControlleur nouvellePartieControlleur = loader.<NouvellePartieControlleur>getController();

        /*StaticPartie.partieProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                change();
            }
        });*/
    }
    public void change() {
        System.out.println("CHANGELISTENER");
    }
    
    public void btna() {
         btnRecommencerPartie.setText("ABCD");
         btnRecommencerPartie.setDisable(false);
         ecranCentre.getChildren().clear();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try
        {
            //StaticPartie s = new StaticPartie();
            nouvellePartie();
        } catch(Exception e) {
            System.out.println("MainAppController initialize " + e.getMessage());
        }
    }    
    
}
