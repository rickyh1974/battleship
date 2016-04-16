
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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import jeu.Case;
import jeu.Coordonnee;
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
        Integer col = grille.getColumnIndex(source)-1;
        Integer row = grille.getRowIndex(source)-1;
        coordonneeProperty.set(new Coordonnee(col,row));
        (source.getScene()).setCursor(Cursor.DEFAULT);
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
    
    public void initialiserGrilleGauche(PartieControlleur partieControlleur) {
        
        HashMap<Integer, ArrayList<Case>> cases = partieControlleur.partie.getJoueurH().getGrillePrincipale().getCases();
        
        for(int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                
                Tuile tuile = new Tuile();
                tuile.setId("tuileC"+i+"R"+j);

                Rectangle rectangle = null;
                if(i<4 && j < 6) {
                    
                    tuile.occuper();
                    
                    rectangle = new Rectangle(25,25);
                    rectangle.setOnDragDetected((MouseEvent mouseEvent) -> {
                        onDragDetected(mouseEvent);
                    });
                    
                    rectangle.setOnDragDone((DragEvent dragEvent) -> {
                        onDragDone(dragEvent);
                    });
                }

                
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(tuile);
                if(rectangle != null) {
                    stackPane.getChildren().add(rectangle);
                    
                    rectangle.setOnMouseEntered((MouseEvent mouseEvent) -> {
                        onMouseEnteredRectangleCursor(mouseEvent);
                    });
                    rectangle.setOnMouseExited((MouseEvent mouseEvent) -> {
                        onMouseExitedRectangleCursor(mouseEvent);
                    });
                    
                }
                
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
                
                cases.get(i-1).get(j-1).statutCaseProperty().addListener(changeListener);
                
                GridPane.setConstraints(stackPane, i, j);
                GridPane.setHalignment(stackPane, HPos.CENTER);
                GridPane.setValignment(stackPane, VPos.CENTER);
                grille.add(stackPane, i, j);
            }
        }
    }
    
    public void initialiserGrilleDroite(PartieControlleur partieControlleur) {
        
        HashMap<Integer, ArrayList<Case>> cases = partieControlleur.partie.getJoueurH().getGrilleAdverse().getCases();
        
        for(int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                
                Tuile tuile = new Tuile();
                tuile.setId("tuileC"+i+"R"+j);
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
                        if(newValue.equals(newValue.equals(StatutCaseType.TOUCHE))) {
                            System.out.println("TOUCHE\nTOUCHE\nTOUCHE");
                            //tuile.estOccupe = true;
                            tuile.occuper();
                            tuile.toucher();
                        }
                        
                    }
                };
                
                cases.get(i-1).get(j-1).statutCaseProperty().addListener(changeListener);
        
                GridPane.setConstraints(tuile, i, j);
                GridPane.setHalignment(tuile, HPos.CENTER);
                GridPane.setValignment(tuile, VPos.CENTER);
                grille.add(tuile, i, j);
            }
        }
    }
    
   
}
