package dh.computermuseum;

public class Component extends Antique {

	private String producer;
	private String comp;
	private String special;
	private String description;

	public Component() {
		super("component");
	}
	
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public String getProducer() {
		return producer;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public String getComp() {
		return comp;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getSpecial() {
		return special;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
