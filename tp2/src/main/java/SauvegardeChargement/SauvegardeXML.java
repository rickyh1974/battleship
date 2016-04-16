/**
 * 
 */
package SauvegardeChargement;

import java.beans.XMLEncoder;
import java.io.*;


/**
 * @author DELL
 *
 */
public class SauvegardeXML {

	/**
	 * 
	 */
	public static boolean sauvegardeXML(Object partie, String path, String nomFichier) {
		
		XMLEncoder encoder=null;
		Boolean sauvegardeOK=true;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path+nomFichier)));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File dvd.xml");
			sauvegardeOK=false;
		}
		encoder.writeObject(partie);
		
		encoder.close();
		return sauvegardeOK;
		// TODO Auto-generated constructor stub
	}

}
