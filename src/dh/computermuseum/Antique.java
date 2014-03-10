package dh.computermuseum;

public class Antique {
	private String name;
	private int id;
	private String desc;
	private String release;
	private String producer;
	private String type;
	private String os;
	private String memory;
	private String ram;
	private String cpu;
	private String special;

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
	
	public void setOs(String os) {
		this.os = os;
	}
	
	public String getOs() {
		return os;
	}
	
	public void setMemory(String memory) {
		this.memory = memory;
	}
	
	public String getMemory() {
		return memory;
	}
	
	public void setRam(String ram) {
		this.ram = ram;
	}
	
	public String getRam() {
		return ram;
	}
	
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	
	public String getCpu() {
		return cpu;
	}
	
	public void setSpecial(String special) {
		this.special = special;
	}
	
	public String getSpecial() {
		return special;
	}

	public String toString() {
		return "[[" + this.id + "] [" + this.name + "] [" + this.desc + "]"
				+ " [" + this.release + "] [" + this.producer + " ]]";
	}
}

