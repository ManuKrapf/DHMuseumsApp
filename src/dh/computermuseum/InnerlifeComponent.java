package dh.computermuseum;

/**
 * Object that represents a innerlife component
 * 
 * @author Johannes Lengdobler, Simon Provinsky, Timo Schaschek, Manuel Krapf
 * @version 1.0
 */
public class InnerlifeComponent extends Antique {
	private int parentId;
	private String developer;
	private String desc;
	
	/**
	 * Constructor
	 */
	public InnerlifeComponent() {
  		super("innerlifeComponent");
  	}
	
	/**
	 * Set the parentID of the component
	 * 
	 * @param id the parentID of the component
	 */
	public void setParentId(int id) {
		parentId = id;
	}
	
	/**
	 * Get the parentID of the component
	 * 
	 * @return the parentID of the component
	 */
	public int getParentId() {
		return parentId;
	}
	
	/**
	 * Set the developer of the component
	 * 
	 * @param developer the developer of the component
	 */
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	
	/**
	 * Get the developer of the component
	 * 
	 * @return the developer of the component
	 */
	public String getProducer() {
		return developer;
	}
	
	/**
	 * Set the description of the component
	 * 
	 * @param desc the description of the component
	 */
	public void setDescription(String desc) {
		this.desc = desc;
	}
	
	/**
	 * Get the description of the component
	 * 
	 * @return the description of the component
	 */
	public String getDescription() {
		return desc;
	}
}