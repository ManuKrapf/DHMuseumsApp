package dh.computermuseum;

/**
 * Main Object for items of the xml-data contains all general informations
 * 
 * @author Johannes Lengdobler, Simon Provinsky, Timo Schaschek, Manuel Krapf
 * @version 1.0
 */
public class Antique {
	private String name;
	private int id;
	private String release;
	private String img;
	private String type;
	
	/**
	 * Constructor
	 * 
	 * @param type the type of the antique
	 */
	public Antique(String type) {
		this.type = type;
	}
	
	/**
	 * Set the name of the antique
	 * 
	 * @param name the String with the name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the name of the antique
	 * 
	 * @return the name of the antique
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the ID of the antique
	 * 
	 * @param id the id of the antique
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Get the ID of the antique
	 * 
	 * @return the ID of the antique
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Set the release date of the antique
	 * 
	 * @param release the release date
	 */
	public void setReleaseDate(String release) {
		this.release = release;
	}
	
	/**
	 * Get the release date of the antique
	 * 
	 * @return the release date of the antique
	 */
	public String getReleaseDate() {
		return release;
	}
	
	/**
	 * Set the imgagepath of the antique
	 * 
	 * @param img the imgagepath of the antique
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	/**
	 * Get the imgagepath of the antique
	 * 
	 * @return the imagepath of the antique
	 */
	public String getImg() {
		return img;
	}
	
	/**
	 * Get the type of the antique
	 * 
	 * @return the type of the antique
	 */
	public String getType() {
		return type;
	}
}
