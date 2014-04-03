package dh.computermuseum;

/**
 * 
 * 
 * @author Johannes Lengdobler, Simon Provinsky, Timo Schaschek, Manuel Krapf
 * @version 1.0
 */
public class InnerlifeComponent extends Antique {
	private int parentId;
	private String developer;
	private String desc;

	public InnerlifeComponent() {
  		super("innerlifeComponent");
  	}
	
	public void setParentId(int id) {
		parentId = id;
	}
	
	public int getParentId() {
		return parentId;
	}
	
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	
	public String getProducer() {
		return developer;
	}

	public void setDescription(String desc) {
		this.desc = desc;
	}

	public String getDescription() {
		return desc;
	}
}