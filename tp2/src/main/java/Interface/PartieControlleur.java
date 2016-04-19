
package Interface;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import jeu.Coordonnee;
import jeu.NiveauPartieType;
import jeu.Partie;

public class PartieControlleur implements Initializable{

    private String nom;
    private String difficulte;
    private Partie partie;
    
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
        
        
        
        final ChangeListener changeListener = (ChangeListener) (ObservableValue observableValue, Object oldValue, Object newValue) -> {
            int ligne = ((Coordonnee)newValue).getLigne();
            int col = ((Coordonnee)newValue).getCol();
            StaticPartie.getPartie().executerTour(new Coordonnee(ligne,col));
        };
        
        controller2.coordonneeProperty.addListener(changeListener);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    public void initData(String nom, String difficulte) throws Exception {
        this.nom = nom;
        this.difficulte = difficulte;
        
        StaticPartie.setPartie(new Partie(nom,NiveauPartieType.valueOf(difficulte)));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleGauche.getChildren().add(loader.load());    
        GrilleControlleur controller = loader.<GrilleControlleur>getController();
        controller.initialiserGrilleGauche();
    }
    
    public void initRecommencer() throws Exception {
        this.nom = StaticPartie.getPartie().getJoueurH().getNom();
        this.difficulte = StaticPartie.getPartie().getNiveau().name();        StaticPartie.setPartie(new Partie(this.nom,NiveauPartieType.valueOf(this.difficulte)));
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleGauche.getChildren().add(loader.load());
        GrilleControlleur controller = loader.<GrilleControlleur>getController();
        controller.initialiserGrilleGauche();
    }
    
    public void initDataChargement(File fichier) throws Exception {
        
        StaticPartie.setPartie(new Partie());
        StaticPartie.getPartie().chargement(fichier.getPath(), fichier.getName());
        this.nom = StaticPartie.getPartie().getJoueurH().getNom();
        this.difficulte = StaticPartie.getPartie().getNiveau().name();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleGauche.getChildren().add(loader.load());    
        GrilleControlleur controller = loader.<GrilleControlleur>getController();
        controller.initialiserGrilleGauche();
    }
    
    

}
