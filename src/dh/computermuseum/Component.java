package dh.computermuseum;

import java.util.ArrayList;

public class Component extends Antique {

	private String producer;
	private String comp;
	private String ftype;
	private String frequency;
	private String memory;
	private String cpu;
	private String register;
	private String interfaces;
	private String description;
	private ArrayList<Tag> tags;

	public Component() {
		super("component");
		tags = new ArrayList<Tag>();
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

	public void setFunctionType(String ftype) {
		this.ftype = ftype;
	}

	public String getFunctionType() {
		return ftype;
	}
	
	public void setFrequency(String freq) {
		this.frequency = freq;
	}
	
	public String getFrequency() {
		return frequency;
	}
	
	public void setMemory(String memory) {
		this.memory = memory;
	}
	
	public String getMemory() {
		return memory;
	}
	
	public void setCPU(String cpu) {
		this.cpu = cpu;
	}
	
	public String getCPU() {
		return cpu;
	}
	
	public void setRegister(String register) {
		this.register = register;
	}
	
	public String getRegister() {
		return register;
	}
	
	public void setInterfaces(String interfaces) {
		this.interfaces = interfaces;
	}
	
	public String getInterfaces() {
		return interfaces;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public void addTag(Tag tag) {
		tags.add(tag);
	}
	
	public ArrayList<Tag> getTags() {
		return tags;
	}
	
	public Tag getTag(int index) {
		return tags.get(index-1);
	}
	
	public class Tag {
		
		private int id;
		private String name;
		private String img;
		private float x;
		private float y;
		private float z;
		private String desc;
		
		public Tag() {
			
		}
		
		public Tag(int id, String n, String i, float _x, float _y, float _z) {
			this.id = id;
			this.name = n;
			this.img = i;
			this.x = _x;
			this.y = _y;
			this.z = _z;
		}
		
		public Tag(String n) {
			this.name = n;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public void setName(String n) {
			this.name = n;
		}
		
		public void setImg(String i) {
			this.img = i;
		}
		
		public void setX(float val) {
			this.x = val;
		}
		
		public void setY(float val) {
			this.y = val;
		}
		
		public void setZ(float val) {
			this.z = val;
		}
		
		public void setDesc(String d) {
			this.desc = d;
		}
		
		public int getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}
		
		public String getImg() {
			return img;
		}
		
		public float[] getPos() {
			return new float[] {x, y, z};
		}
		
		public String getDesc() {
			return desc;
		}
		
	}
}
