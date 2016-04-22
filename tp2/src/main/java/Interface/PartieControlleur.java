
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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    private Label lblErreurPlacement;
    
    private GrilleControlleur grilleGaucheControlleur;
    
    @FXML
    private void handleBtnCommencer(ActionEvent event) throws Exception {
        if(validerPlacementNavires())
        {
            StaticPartie.getPartie().placerNaviresJoueurH(placementNavires);
            
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
                StaticPartie.getPartie().executerTour(new Coordonnee(ligne,col));
            };
            controller2.coordonneeProperty.addListener(changeListener);
        
            //this.estFinPartie.bind(StaticPartie.getPartie().estFinPartieProperty());
            final ChangeListener changeListener2 = (ChangeListener) (ObservableValue observableValue, Object oldValue, Object newValue) -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
                    alert.setTitle("Partie terminée");
                    String s = "La partie est terminée";
                    alert.setContentText(s);

                    Optional<ButtonType> result = alert.showAndWait();
                
            };
        
            StaticPartie.getPartie().estFinPartieProperty().addListener(changeListener2);
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
        for(Coordonnee c : coord) {
            System.out.println("c : " + c.getCol());
            System.out.println("p : " + precedente.getCol());
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
            
            precedente = c;
        }
        return true;
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
        grilleGaucheControlleur = loader.<GrilleControlleur>getController();
        grilleGaucheControlleur.initialiserGrilleGauche();
        grilleGaucheControlleur.initialiserNavires();
    }
    
    public void initRecommencer() throws Exception {
        this.nom = StaticPartie.getPartie().getJoueurH().getNom();
        this.difficulte = StaticPartie.getPartie().getNiveau().name();        
        StaticPartie.setPartie(new Partie(this.nom,NiveauPartieType.valueOf(this.difficulte)));
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleGauche.getChildren().add(loader.load());
        grilleGaucheControlleur = loader.<GrilleControlleur>getController();
        grilleGaucheControlleur.initialiserGrilleGauche();
        grilleGaucheControlleur.initialiserNavires();
    }
    
    public void initDataChargement(File fichier) throws Exception {
        
        Partie p = new Partie("",NiveauPartieType.FACILE);
        StaticPartie.setPartie(p.chargement(fichier.getPath()));
        //StaticPartie.setPartie(new Partie("abcd",NiveauPartieType.FACILE));
        //StaticPartie.getPartie().chargement(fichiere.getPath(), fichier.getName());
        
        //StaticPartie.setPartie(new Partie(fichier));
        //this.nom = StaticPartie.getPartie().getJoueurH().getNom();
        //this.difficulte = StaticPartie.getPartie().getNiveau().name();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grille.fxml"));
        grilleGauche.getChildren().add(loader.load());    
        grilleGaucheControlleur = loader.<GrilleControlleur>getController();
        grilleGaucheControlleur.initialiserGrilleGauche();
    }
    
    

}
