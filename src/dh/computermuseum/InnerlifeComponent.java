package dh.computermuseum;

public class InnerlifeComponent extends Antique {
	private String producer;
	private String desc;

	public InnerlifeComponent() {
  		super("innerlifeComponent");
  	}
	
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public String getProducer() {
		return producer;
	}

	public void setDescription(String desc) {
		this.desc = desc;
	}

	public String getDescription() {
		return desc;
	}
}