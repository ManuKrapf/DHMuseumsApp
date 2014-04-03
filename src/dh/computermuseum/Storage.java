package dh.computermuseum;

/**
 * Object that represents a storage
 * 
 * @author Johannes Lengdobler, Simon Provinsky, Timo Schaschek, Manuel Krapf
 * @version 1.0
 */
public class Storage extends Antique {
	
	private String stype;
	private String capacity;
	private String material;
	private String endurance;
	private String size;
	private String developer;
	private String rwSpeed;
	
	/**
	 * Constructor
	 */
	public Storage() {
	  super("storage");
	}
	
	/**
	 * Set the storagetype of the storage
	 * 
	 * @param type the type of the storage
	 */
	public void setType(String type) {
	    this.stype = type;
	}
	
	/**
	 * Get the storagetype of the storage
	 * 
	 * @return the storagetype of the storage
	 */
	public String getSType() {
		return stype;
	}
	
	/**
	 * Set the capacity of the storage
	 * 
	 * @param capacity the capacity of the storage
	 */
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * Get the capacity of the storage
	 * 
	 * @return the capacity of the storage
	 */
	public String getCapacity() {
		return capacity;
	}
	
	/**
	 * Set the material of the storage
	 * 
	 * @param material the material of the storage
	 */
	public void setMaterial(String material) {
		this.material = material;
	}
	
	/**
	 * Get the material of the storage
	 * 
	 * @return the material of the storage
	 */
	public String getMaterial() {
		return material;
	}
	
	/**
	 * Set the endurance of the storage
	 * 
	 * @param endurance the endurance of the storage
	 */
	public void setEndurance(String endurance) {
		this.endurance = endurance;
	}
	
	/**
	 * Get the endurance of the storage
	 * 
	 * @return the endurance of the storage
	 */
	public String getEndurance() {
		return endurance;
	}
	
	/**
	 * Set the size of the storage
	 * 
	 * @param size the size of the storage
	 */
	public void setSize(String size) {
		this.size = size;
	}
	
	/**
	 * Get the size of the storage
	 * 
	 * @return the size of the storage
	 */
	public String getSize() {
		return size;
	}
	
	/**
	 * Set the developer of the storage
	 * 
	 * @param developer the developer of the storage
	 */
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	
	/**
	 * Get the developer of the storage
	 * 
	 * @return the developer of the storage
	 */
	public String getDeveloper() {
		return developer;
	}
	
	/**
	 * Set the read/write speed of the storage
	 * 
	 * @param rwSpeed the read/write speed of the storage
	 */
	public void setRwSpeed(String rwSpeed) {
		this.rwSpeed = rwSpeed;
	}
	
	/**
	 * Get the read/write speed of the storage
	 * 
	 * @return the read/write speed of the storage
	 */
	public String getRwSpeed() {
		return rwSpeed;
	}
	
}