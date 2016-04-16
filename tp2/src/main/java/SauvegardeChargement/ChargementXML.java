/**
 * 
 */
package SauvegardeChargement;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jeu.Partie;;


/**
 * @author DELL
 *
 */
public class ChargementXML {

	/**
	 * 
	 */
	public static Partie chargementXML(String path, String nomFichier) {
		
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(path+nomFichier)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File dvd.xml not found");
		}
		Partie object=(Partie)decoder.readObject();
		//System.out.println(object);
		System.out.println("Dans chargement");
		object.afficheTouteLesGrilles();
		return object;
	}
		// TODO Auto-generated constructor stub
}


