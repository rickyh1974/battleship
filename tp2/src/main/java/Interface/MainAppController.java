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
        
        if(PartieEnCours.getPartie() != null)
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
        if(PartieEnCours.getPartie() != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sauvegarder Partie");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
            fileChooser.getExtensionFilters().add(extFilter);
            File fichier = fileChooser.showSaveDialog(new Stage());
        
            if(fichier != null) {
            
                try
                {
                    PartieEnCours.getPartie().sauvegardePartie(fichier.getPath());
                    Alert alert = new Alert(AlertType.INFORMATION);
        
                    alert.setTitle("Fichier Sauvegardé");
                    String s = "Le fichier " + fichier.getName() + " a bien été sauvegardé dans " + fichier.getPath();
                    alert.setContentText(s);

                    Optional<ButtonType> result = alert.showAndWait();
                }
                catch(Exception e) {
                    Alert alert = new Alert(AlertType.ERROR);
        
                    alert.setTitle("Erreur de sauvegarde");
                    String s = "Erreur de sauvegarde";
                    alert.setContentText(s);

                    Optional<ButtonType> result = alert.showAndWait();
                    event.consume();
                }
                
                
            }
        }
        else {
            Alert alert = new Alert(AlertType.INFORMATION);
        
            alert.setTitle("Sauvegarde non possible");
            String s = "Veuillez commencer une partie pour pouvoir sauvegarder.";
            alert.setContentText(s);

            Optional<ButtonType> result = alert.showAndWait();
            event.consume();
        }
        
    }
    
    @FXML
    private void handleBtnVisualiser(ActionEvent event) throws Exception {
        if(PartieEnCours.getPartie() != null && PartieEnCours.getPartie().getEstFinPartie()) {
            ecranCentre.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Visualiser.fxml"));
            ecranCentre.getChildren().add(loader.load());
            VisualiserControlleur controlleur = loader.<VisualiserControlleur>getController();
            controlleur.initData();
        }
        else {
            Alert alert = new Alert(AlertType.INFORMATION);
        
            alert.setTitle("Visualisation non possible");
            String s = "Veuillez terminer une partie pour pouvoir visualiser.";
            alert.setContentText(s);

            Optional<ButtonType> result = alert.showAndWait();
            event.consume();
        }
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
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try
        {
            nouvellePartie();
        } catch(Exception e) {
            System.out.println("MainAppController initialize " + e.getMessage());
        }
    }    
    
}
