
package Interface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

 public class Tuile extends Rectangle {
     
     public boolean estOccupe = false;
     public boolean estTouche = false;

    public Tuile() {
        super(30,30);
        this.setFill(Color.DARKGREY);
        this.setStroke(Color.BLACK);
    }

    public void toucher() {
        estTouche = true;
        if(estOccupe)
            this.setFill(Color.RED);
        else
            this.setFill(Color.WHITE);
            
    }
    
    public void occuper() {
        estOccupe = true;
        this.setFill(Color.DARKSLATEGRAY);
    }
}
