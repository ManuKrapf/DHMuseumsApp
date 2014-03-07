package dh.computermuseum;

public class Antique {
	private String name;
	private int id;
	private String desc;
	private String release;
	private String producer;
	private String type;

	public Antique() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setDescription(String desc) {
		this.desc = desc;
	}

	public String getDescription() {
		return desc;
	}

	public void setReleaseDate(String release) {
		this.release = release;
	}

	public String getReleaseDate() {
		return release;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getProducer() {
		return producer;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public String toString() {
		return "[[" + this.id + "] [" + this.name + "] [" + this.desc + "]"
				+ " [" + this.release + "] [" + this.producer + " ]]";
	}
}

