
package Interface;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import jeu.Partie;

    
public class PartieEnCours {
    
    private static ObjectProperty<Partie> partie = new SimpleObjectProperty<>();
    
    public static ObjectProperty partieProperty() {
            return partie;
    }
    
    public static Partie getPartie() {
        return partie.get();
    }
    
    public static void setPartie(Partie part) {
        partie.set(part);
    }
    
}