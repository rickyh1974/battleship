/**
 * 
 */
package SauvegardeChargement;

import java.beans.XMLEncoder;
import java.io.*;


public class SauvegardeXML {

	public static void sauvegardeXML(Object partie, String path) {
		
		XMLEncoder encoder=null;
		
		try{
                    encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
		}catch(FileNotFoundException fileNotFound){
                    System.out.println("ERROR: While Creating or Opening the File dvd.xml");
			
		}
		encoder.writeObject(partie);
		
		encoder.close();
	}

}
