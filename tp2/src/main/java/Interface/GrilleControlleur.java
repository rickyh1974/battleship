
package Interface;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import jeu.Action;
import jeu.Case;
import jeu.Coordonnee;
import jeu.NavireType;
import jeu.StatutCaseType;

public class GrilleControlleur implements Initializable{
    
    public ObjectProperty<Coordonnee> coordonneeProperty = new SimpleObjectProperty();
    
    @FXML
    private GridPane grille;
    
    
    @FXML
    protected void onDragDetected(MouseEvent event) {
        
            Rectangle source = (Rectangle)event.getSource();
                
            Dragboard dragboard = source.startDragAndDrop(TransferMode.MOVE);
                
            ClipboardContent contenu = new ClipboardContent();
            contenu.putString("navire");
            dragboard.setContent(contenu);
    }
       
    @FXML 
    protected void onDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
    }

    @FXML 
    protected void onDragDropped(DragEvent event) {

        StackPane stackPane = (StackPane)event.getGestureTarget();
        if(stackPane.getChildren().size() < 2) {
            Rectangle rectangle = (Rectangle)event.getGestureSource();
            stackPane.getChildren().add(rectangle);
        }
        
        event.setDropCompleted(true);

    }
    
    private void handleMouseClick(MouseEvent mouseEvent) {
        Tuile source = (Tuile)mouseEvent.getSource();
        if(!source.estTouche) {
            Integer row = GridPane.getRowIndex(source)-1;
            Integer col = GridPane.getColumnIndex(source)-1;
            coordonneeProperty.set(new Coordonnee(col,row));
            (source.getScene()).setCursor(Cursor.DEFAULT);
        }
    }
    
    private void onMouseEnteredRectangleCursor(MouseEvent mouseEvent) {
        Rectangle rectangle = (Rectangle)mouseEvent.getSource();
        (rectangle.getScene()).setCursor(Cursor.HAND);
        
    }
    private void onMouseExitedRectangleCursor(MouseEvent mouseEvent) {
        Rectangle rectangle = (Rectangle)mouseEvent.getSource();
        (rectangle.getScene()).setCursor(Cursor.DEFAULT);
    }
    
    private void onMouseEnteredCursor(MouseEvent mouseEvent) {
        Tuile tuile = (Tuile)mouseEvent.getSource();
        if(tuile.estTouche == false) {
            (tuile.getScene()).setCursor(Cursor.HAND);
        }
    }
    private void onMouseExitedCursor(MouseEvent mouseEvent) {
        Tuile tuile = (Tuile)mouseEvent.getSource();
        (tuile.getScene()).setCursor(Cursor.DEFAULT);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void initialiserGrilleGauche() {
        
        HashMap<Integer, ArrayList<Case>> cases = PartieEnCours.getPartie().getJoueurH().getGrillePrincipale().getCases();
        
        for(int ligne = 1; ligne < 11; ligne++) {
            for (int col = 1; col < 11; col++) {
                
                Tuile tuile = new Tuile();
                tuile.setId("tuile");
                
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(tuile);
                
                stackPane.setOnDragOver((DragEvent dragEvent) -> {
                    onDragOver(dragEvent);
                });
                stackPane.setOnDragDropped((DragEvent dragEvent) -> {
                    onDragDropped(dragEvent);
                });
                
                
                final ChangeListener changeListenerStatutCase = (ChangeListener) (ObservableValue observableValue, Object oldValue, Object newValue) -> {
                    if(tuile.estTouche == false) {
                        if(newValue.equals(StatutCaseType.DEMANDENONTOUCHE)) {
                            tuile.toucher();
                        }
                        if(newValue.equals(StatutCaseType.TOUCHE)) {
                            tuile.setEstOccupe();
                            tuile.toucher();
                        }
                        
                    }
                };
                
                cases.get(ligne-1).get(col-1).statutCaseProperty().addListener(changeListenerStatutCase);
                
                GridPane.setConstraints(stackPane, col, ligne);
                GridPane.setHalignment(stackPane, HPos.CENTER);
                GridPane.setValignment(stackPane, VPos.CENTER);
                grille.add(stackPane, col, ligne);
            }
        }
        
        
        
    }
    
    public void initialiserNavires() {
        
            Rectangle rectangle; 
            
            ObservableList<Node> childrens = grille.getChildren();
            for(Node node : childrens) {
                if(node.toString().contains("StackPane")) {
                    if(GridPane.getRowIndex(node) == 1 && GridPane.getColumnIndex(node) <= 5) {
                        rectangle   = creerRectangle();
                        rectangle.setId(NavireType.PORTEAVIONS.toString());
                        rectangle.setFill(Color.DARKSLATEBLUE);
                        ((StackPane)node).getChildren().add(rectangle);
                    }
                    if(GridPane.getRowIndex(node) == 2 && GridPane.getColumnIndex(node) <= 4) {
                        rectangle   = creerRectangle();
                        rectangle.setId(NavireType.CROISSEUR.toString());
                        rectangle.setFill(Color.DARKSLATEGRAY);
                        ((StackPane)node).getChildren().add(rectangle);
                    }
                    if(GridPane.getRowIndex(node) == 3 && GridPane.getColumnIndex(node) <= 3) {
                        rectangle   = creerRectangle();
                        rectangle.setId(NavireType.CONTRETORPILLEUR.toString());
                        rectangle.setFill(Color.DARKOLIVEGREEN);
                        ((StackPane)node).getChildren().add(rectangle);
                    }
                    if(GridPane.getRowIndex(node) == 4 && GridPane.getColumnIndex(node) <= 3) {
                        rectangle   = creerRectangle();
                        rectangle.setId(NavireType.SOUSMARIN.toString());
                        rectangle.setFill(Color.DARKGOLDENROD);
                        ((StackPane)node).getChildren().add(rectangle);
                    }
                    if(GridPane.getRowIndex(node) == 5 && GridPane.getColumnIndex(node) <= 2) {
                        rectangle   = creerRectangle();
                        rectangle.setId(NavireType.TORPILLEUR.toString());
                        rectangle.setFill(Color.DARKORCHID);
                        ((StackPane)node).getChildren().add(rectangle);
                    }
                }
            }         
    }
    private Rectangle creerRectangle() {
        Rectangle rectangle = new Rectangle(25,25);
        
        rectangle.setOnDragDetected((MouseEvent mouseEvent) -> {
           onDragDetected(mouseEvent);
        });      
        rectangle.setOnMouseEntered((MouseEvent mouseEvent) -> {
            onMouseEnteredRectangleCursor(mouseEvent);
        });
        rectangle.setOnMouseExited((MouseEvent mouseEvent) -> {
            onMouseExitedRectangleCursor(mouseEvent);
        });
            
        return rectangle;
    }
    
    public HashMap<NavireType, ArrayList<Coordonnee>> placerNavires() {
        HashMap<NavireType, ArrayList<Coordonnee>> placementNavires = new HashMap<>();
        
        ObservableList<Node> childrens = grille.getChildren();
        for(Node node : childrens) {
            if(node.toString().contains("StackPane")) {
                ObservableList<Node> stackPaneChildrens = ((StackPane)node).getChildren();
                if(stackPaneChildrens.size()==2) {
                    Rectangle rectNavire = (Rectangle)stackPaneChildrens.get(1);
                    if(rectNavire.getId().equals("tuile"))
                        rectNavire = (Rectangle)stackPaneChildrens.get(0);
                    
                    Integer row = GridPane.getRowIndex(node)-1;
                    Integer col = GridPane.getColumnIndex(node)-1;
                    
                    if(!placementNavires.containsKey(NavireType.valueOf(rectNavire.getId())))
                        placementNavires.put(NavireType.valueOf(rectNavire.getId()), new ArrayList<>());

                    placementNavires.get(NavireType.valueOf(rectNavire.getId())).add(new Coordonnee(col,row));
                }
            }
        }      
        
        return placementNavires;
        
    }
    
    public void chargerNavires() {
        
        Rectangle rectangle; 
        
        HashMap<NavireType, ArrayList<Coordonnee>> placements = PartieEnCours.getPartie().getJoueurH().getPlacementNaviresInitial();
            
        ObservableList<Node> childrens = grille.getChildren();
        
        
        for(Node node : childrens) {
            
            if(node.toString().contains("StackPane")) {
                
                if(placements.get(NavireType.PORTEAVIONS).contains(new Coordonnee(GridPane.getColumnIndex(node)-1,GridPane.getRowIndex(node)-1))) {
                    rectangle   = creerRectangle();
                    rectangle.setId(NavireType.PORTEAVIONS.toString());
                    rectangle.setFill(Color.DARKSLATEBLUE);
                    ((StackPane)node).getChildren().add(rectangle);
                }
                if(placements.get(NavireType.CROISSEUR).contains(new Coordonnee(GridPane.getColumnIndex(node)-1,GridPane.getRowIndex(node)-1))) {
                    rectangle   = creerRectangle();
                    rectangle.setId(NavireType.CROISSEUR.toString());
                    rectangle.setFill(Color.DARKSLATEGRAY);
                    ((StackPane)node).getChildren().add(rectangle);
                }
                if(placements.get(NavireType.CONTRETORPILLEUR).contains(new Coordonnee(GridPane.getColumnIndex(node)-1,GridPane.getRowIndex(node)-1))) {
                    rectangle   = creerRectangle();
                    rectangle.setId(NavireType.CONTRETORPILLEUR.toString());
                    rectangle.setFill(Color.DARKOLIVEGREEN);
                    ((StackPane)node).getChildren().add(rectangle);
                }
                if(placements.get(NavireType.SOUSMARIN).contains(new Coordonnee(GridPane.getColumnIndex(node)-1,GridPane.getRowIndex(node)-1))) {
                    rectangle   = creerRectangle();
                    rectangle.setId(NavireType.SOUSMARIN.toString());
                    rectangle.setFill(Color.DARKGOLDENROD);
                    ((StackPane)node).getChildren().add(rectangle);
                }
                if(placements.get(NavireType.TORPILLEUR).contains(new Coordonnee(GridPane.getColumnIndex(node)-1,GridPane.getRowIndex(node)-1))) {
                    rectangle   = creerRectangle();
                    rectangle.setId(NavireType.TORPILLEUR.toString());
                    rectangle.setFill(Color.DARKORCHID);
                    ((StackPane)node).getChildren().add(rectangle);
                }
                
            }
        }         
    }
    
    public void setAction(Action action) {
        
        ObservableList<Node> childrens = grille.getChildren();
        Coordonnee coord = action.getPoint();
        
        for(Node node : childrens) {
            if(node.toString().contains("StackPane") || node.toString().contains("tuile")) {
                
                if(GridPane.getRowIndex(node)-1 == coord.getLigne() && GridPane.getColumnIndex(node)-1 == coord.getCol()) {
                    
                    Tuile tuile;
                    if(!node.toString().contains("tuile")) {
                        
                        ObservableList<Node> stackPaneChildrens = ((StackPane)node).getChildren();
                    
                        if(stackPaneChildrens.get(0).getId().equals("tuile")) {
                            tuile = (Tuile)stackPaneChildrens.get(0);
                        }
                        else {
                            tuile = (Tuile)stackPaneChildrens.get(1);
                        }
                    }
                    else
                        tuile = (Tuile)node;
                            
                    if(coord.isTouche())
                        tuile.setEstOccupe();

                    tuile.toucher();
                    
                }
            }
        }
        
    }
    
    public void initialiserGrilleDroite() {
        
        HashMap<Integer, ArrayList<Case>> cases = PartieEnCours.getPartie().getJoueurH().getGrilleAdverse().getCases();
        
        for(int ligne = 1; ligne < 11; ligne++) {
            for (int col = 1; col < 11; col++) {
                
                Tuile tuile = new Tuile();
                tuile.setId("tuile");
                tuile.setOnMouseClicked((MouseEvent mouseEvent) -> {
                    handleMouseClick(mouseEvent);
                });
                tuile.setOnMouseEntered((MouseEvent mouseEvent) -> {
                    onMouseEnteredCursor(mouseEvent);
                });
                tuile.setOnMouseExited((MouseEvent mouseEvent) -> {
                    onMouseExitedCursor(mouseEvent);
                });
                
                final ChangeListener changeListener = (ChangeListener) (ObservableValue observableValue, Object oldValue, Object newValue) -> {
                    if(tuile.estTouche == false) {
                        if(newValue.equals(StatutCaseType.DEMANDENONTOUCHE)) {
                            tuile.toucher();
                        }
                        if(newValue.equals(StatutCaseType.TOUCHE)) {
                            tuile.setEstOccupe();
                            tuile.toucher();
                        }
                        
                    }
                };
                
                cases.get(ligne-1).get(col-1).statutCaseProperty().addListener(changeListener);
        
                GridPane.setConstraints(tuile, col, ligne);
                GridPane.setHalignment(tuile, HPos.CENTER);
                GridPane.setValignment(tuile, VPos.CENTER);
                grille.add(tuile, col, ligne);
            }
        }
        
        
    }
    
   
}
