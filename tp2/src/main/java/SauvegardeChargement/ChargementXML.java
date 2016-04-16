/**
 * 
 */
package SauvegardeChargement;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author DELL
 *
 */
public class ChargementXML {

	/**
	 * 
	 */
	public static void chargementXML(Object objet, String path, String nomFichier) {
		
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(path+nomFichier)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File dvd.xml not found");
		}
		objet=(Object)decoder.readObject();
		System.out.println(objet);

	}
		// TODO Auto-generated constructor stub
}


