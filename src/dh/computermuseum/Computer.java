package dh.computermuseum;

public class Computer extends Antique {
	
	private String producer;
	private String os;
  	private String memory;
  	private String ram;
  	private String cpu;
  	private String special;
  	private String img;
  	private String description;
  
  	public Computer() {
  		super("computer");
  	}
  	
  	public void setProducer(String producer) {
  		this.producer = producer;
  	}
  	
  	public String getProducer() {
  		return producer;
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
	
	@Override
	public void setImg(String img) {
		this.img = img;
	}
	
	@Override
	public String getImg() {
		return img;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
