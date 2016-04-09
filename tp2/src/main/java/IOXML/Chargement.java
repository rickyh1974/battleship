/**
 * 
 */
package IOXML;

/**
 * @author DELL
 *
 */
public class Chargement {
	
	private String path;
	private String nomFichier;
	private String partieXML;
	
	/**
	 * Constructeur
	 */
	public Chargement(String partieXML, String path, String nomFichier) {
		this.partieXML=partieXML;
		this.path=path;
		this.nomFichier=nomFichier;
		chargementXML(partieXML,path,nomFichier);	
		
	}
	
	/**
	 * 
	 */
	public static boolean chargementXML(String partieXML, String path, String nomFichier) {
		// TODO 
		
		return true;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the nomFichier
	 */
	public String getNomFichier() {
		return nomFichier;
	}

	/**
	 * @param nomFichier the nomFichier to set
	 */
	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	/**
	 * @return the partieXML
	 */
	public String getPartieXML() {
		return partieXML;
	}

	/**
	 * @param partieXML the partieXML to set
	 */
	public void setPartieXML(String partieXML) {
		this.partieXML = partieXML;
	}
	
	

}
