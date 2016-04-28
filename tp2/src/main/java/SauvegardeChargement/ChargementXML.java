/**
 * 
 */
package SauvegardeChargement;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jeu.Partie;;


public class ChargementXML {

	public static Partie chargementXML(String path) {
		
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: "+path);
		}
		Partie object=(Partie)decoder.readObject();
                
		return object;
	}
}


