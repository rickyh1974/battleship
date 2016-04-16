
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
    public Partie partie;
    
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
    
    public String getNom() {
        return nom;
    }
    
    public String getDifficulte() {
        return difficulte;
    }
    
    public Partie getPartie() {
        return partie;
    }
    
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
        controller2.initialiserGrilleDroite(this);
        
        
        
        final ChangeListener changeListener = (ChangeListener) (ObservableValue observableValue, Object oldValue, Object newValue) -> {
            int ligne = ((Coordonnee)newValue).getLigne();
            int col = ((Coordonnee)newValue).getCol();
            partie.executerTour(new Coordonnee(ligne,col));
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
        
        partie = new Partie(nom,NiveauPartieType.valueOf(difficulte));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleGauche.getChildren().add(loader.load());    
        GrilleControlleur controller = loader.<GrilleControlleur>getController();
        controller.initialiserGrilleGauche(this);
    }
    
    public void initRecommencer(Partie partie) throws Exception {
        //partie.initialiser();
        //this.partie = partie;
        this.nom = partie.getJoueurH().getNom();
        this.difficulte = partie.getNiveau().name();
        this.partie = new Partie(this.nom,NiveauPartieType.valueOf(this.difficulte));
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleGauche.getChildren().add(loader.load());
        GrilleControlleur controller = loader.<GrilleControlleur>getController();
        controller.initialiserGrilleGauche(this);
    }
    
    public void initDataChargement(File fichier) throws Exception {
        
        //partie = new Partie(nom,NiveauPartieType.valueOf(difficulte)); 
        //CHARGEMENT
        partie.chargement(fichier.getPath(), fichier.getName());
        
        this.nom = partie.getJoueurH().getNom();
        this.difficulte = partie.getNiveau().name();
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleGauche.getChildren().add(loader.load());    
        GrilleControlleur controller = loader.<GrilleControlleur>getController();
        controller.initialiserGrilleGauche(this);
    }
    
    

}
