
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
        //drag was detected, start drag-and-drop gesture
                System.out.println("onDragDetected");
                Rectangle source = (Rectangle)event.getSource();
                System.out.println(source);
                // allow any transfer mode 
                Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
                
                // put a string on dragboard 
                ClipboardContent content = new ClipboardContent();
                content.putString("torpilleur");
                db.setContent(content);
                
                event.consume();
    }
    
    @FXML 
    protected void onDragDone(DragEvent event) {
      // the drag-and-drop gesture ended 
                System.out.println("onDragDone");
                
                // if the data was successfully moved, clear it
               // if (event.getTransferMode() == TransferMode.MOVE) {
                 //   StackPane stackPane = (StackPane)event.getGestureTarget();
                   // Rectangle rectangle = (Rectangle)event.getGestureSource();
                    //stackPane.getChildren().add(rectangle);
                //}
                    //rectangle.setFill(Color.PINK);
                //}
                
                event.consume();
    }
       
    @FXML 
    protected void onDragOver(DragEvent event) {
         // data is dragged over the target 
                System.out.println("onDragOver");
                
                // accept it only if it is  not dragged from the same node 
                 // and if it has a string data 
                //if (event.getGestureSource() != grilleGauche &&
                //        event.getDragboard().hasString()) {
                    // allow for both copying and moving, whatever user chooses 
                    event.acceptTransferModes(TransferMode.MOVE);
                
                
                event.consume();
    }
    
    @FXML
    protected void onDragEntered(DragEvent event) {
        // the drag-and-drop gesture entered the target 
                System.out.println("onDragEntered");
                // show to the user that it is an actual gesture target 
                //if (event.getGestureSource() != grilleGauche &&
                  //      event.getDragboard().hasString()) {
                StackPane source = (StackPane)event.getSource();
                System.out.println(source);
                    //rectangle.setFill(Color.GREEN);
                    //grilleGauche.getChildren().add(rectangle);
                    //System.out.println(event.toString());
                    //tuile.setId("tuileC"+i+"R"+j);
                
                
                event.consume();
    }

    @FXML 
    protected void onDragDropped(DragEvent event) {
      // data dropped 
                System.out.println("onDragDropped");
                // if there is a string data on dragboard, read it and use it 
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    //target.setText(db.getString());
                    success = true;
                }
                StackPane stackPane = (StackPane)event.getGestureTarget();
                if(stackPane.getChildren().size() < 2) {
                Rectangle rectangle = (Rectangle)event.getGestureSource();
                stackPane.getChildren().add(rectangle);
                }
                // let the source know whether the string was successfully 
                // transferred and used 
                event.setDropCompleted(success);
                
                event.consume();
    }
    
    private void handleMouseClick(MouseEvent mouseEvent) {
        Tuile source = (Tuile)mouseEvent.getSource();
        if(!source.estTouche) {
            Integer row = grille.getRowIndex(source)-1;
            Integer col = grille.getColumnIndex(source)-1;
            coordonneeProperty.set(new Coordonnee(row,col));
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
        // TODO
        
        
    }    
    
    public void initialiserGrilleGauche() {
        
        HashMap<Integer, ArrayList<Case>> cases = StaticPartie.getPartie().getJoueurH().getGrillePrincipale().getCases();
        
        for(int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                
                Tuile tuile = new Tuile();
                tuile.setId("tuileC"+i+"R"+j);
                
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(tuile);
                
                stackPane.setOnDragEntered((DragEvent dragEvent) -> {
                    onDragEntered(dragEvent);
                });
                stackPane.setOnDragOver((DragEvent dragEvent) -> {
                    onDragOver(dragEvent);
                });
                stackPane.setOnDragDropped((DragEvent dragEvent) -> {
                    onDragDropped(dragEvent);
                });
                
                
                final ChangeListener changeListener = (ChangeListener) (ObservableValue observableValue, Object oldValue, Object newValue) -> {
                    if(tuile.estTouche == false) {
                        if(newValue.equals(StatutCaseType.DEMANDENONTOUCHE)) {
                            tuile.toucher();
                        }
                        if(newValue.equals(StatutCaseType.TOUCHE)) {
                            tuile.estOccupe = true;
                            tuile.toucher();
                        }
                        
                    }
                };
                
                cases.get(j-1).get(i-1).statutCaseProperty().addListener(changeListener);
                
                GridPane.setConstraints(stackPane, j, i);
                GridPane.setHalignment(stackPane, HPos.CENTER);
                GridPane.setValignment(stackPane, VPos.CENTER);
                grille.add(stackPane, j, i);
            }
        }
        
        
        
    }
    
    public void initialiserNavires() {
        
            Rectangle rectangle; 
            
            ObservableList<Node> childrens = grille.getChildren();
            for(Node node : childrens) {
                if(node.toString().contains("StackPane")) {
                    if(grille.getRowIndex(node) == 1 && grille.getColumnIndex(node) <= 5) {
                        rectangle   = creerRectangle();
                        rectangle.setId(NavireType.PORTEAVIONS.toString());
                        rectangle.setFill(Color.DARKSLATEBLUE);
                        ((StackPane)node).getChildren().add(rectangle);
                    }
                    if(grille.getRowIndex(node) == 2 && grille.getColumnIndex(node) <= 4) {
                        rectangle   = creerRectangle();
                        rectangle.setId(NavireType.CROISSEUR.toString());
                        rectangle.setFill(Color.DARKSLATEGRAY);
                        ((StackPane)node).getChildren().add(rectangle);
                    }
                    if(grille.getRowIndex(node) == 3 && grille.getColumnIndex(node) <= 3) {
                        rectangle   = creerRectangle();
                        rectangle.setId(NavireType.CONTRETORPILLEUR.toString());
                        rectangle.setFill(Color.DARKOLIVEGREEN);
                        ((StackPane)node).getChildren().add(rectangle);
                    }
                    if(grille.getRowIndex(node) == 4 && grille.getColumnIndex(node) <= 3) {
                        rectangle   = creerRectangle();
                        rectangle.setId(NavireType.SOUSMARIN.toString());
                        rectangle.setFill(Color.DARKGOLDENROD);
                        ((StackPane)node).getChildren().add(rectangle);
                    }
                    if(grille.getRowIndex(node) == 5 && grille.getColumnIndex(node) <= 2) {
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
                    
        rectangle.setOnDragDone((DragEvent dragEvent) -> {
            onDragDone(dragEvent);
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
                    Integer row = grille.getRowIndex(node)-1;
                    Integer col = grille.getColumnIndex(node)-1;
                    
                    if(!placementNavires.containsKey(NavireType.valueOf(rectNavire.getId())))
                        placementNavires.put(NavireType.valueOf(rectNavire.getId()), new ArrayList<>());

                    placementNavires.get(NavireType.valueOf(rectNavire.getId())).add(new Coordonnee(col,row));
                }
            }
        }      
        
        return placementNavires;
        
    }
    
    public void initialiserGrilleDroite() {
        
        HashMap<Integer, ArrayList<Case>> cases = StaticPartie.getPartie().getJoueurH().getGrilleAdverse().getCases();
        
        for(int ligne = 1; ligne < 11; ligne++) {
            for (int col = 1; col < 11; col++) {
                
                Tuile tuile = new Tuile();
                tuile.setId("tuileC"+ligne+"R"+col);
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
                            //tuile.estOccupe = true;
                            tuile.occuper();
                            tuile.toucher();
                        }
                        
                    }
                };
                
                cases.get(ligne-1).get(col-1).statutCaseProperty().addListener(changeListener);
                
                //cases.get(ligne-1).get(col-1).setStatutCase(cases.get(ligne-1).get(col-1).getStatutCase());
                //if(cases.get(ligne-1).get(col-1).statutCaseProperty().equals(StatutCaseType.DEMANDENONTOUCHE)) {
                  //  cases.get(ligne-1).get(col-1).setStatutCase(StatutCaseType.DEMANDENONTOUCHE);
                //}
                
            
        
                GridPane.setConstraints(tuile, col, ligne);
                GridPane.setHalignment(tuile, HPos.CENTER);
                GridPane.setValignment(tuile, VPos.CENTER);
                grille.add(tuile, col, ligne);
            }
        }
        
        
    }
    
   
}
