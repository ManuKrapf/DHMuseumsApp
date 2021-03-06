package dh.computermuseum;

import java.util.ArrayList;

/**
 * Object that represents a component/board
 * 
 * @author Johannes Lengdobler, Simon Provinsky, Timo Schaschek, Manuel Krapf
 * @version 1.0
 */
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
	
	/**
	 * Constructor
	 */
	public Component() {
		super("component");
		tags = new ArrayList<Tag>();
	}
	
	/**
	 * Sets the Producer
	 * 
	 * @param producer the String with the producer to set
	 */
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	/**
	 * Get the Producer
	 * 
	 * @return a String with the producer
	 */
	public String getProducer() {
		return producer;
	}
	
	/**
	 * Sets the company
	 * 
	 * @param comp the String with the company to set
	 */
	public void setComp(String comp) {
		this.comp = comp;
	}
	
	/**
	 * Get the company
	 * 
	 * @return a String with the company
	 */
	public String getComp() {
		return comp;
	}
	
	/**
	 * Sets the functiontype
	 * 
	 * @param ftype the String with the functiontype to set
	 */
	public void setFunctionType(String ftype) {
		this.ftype = ftype;
	}
	
	/**
	 * Get the functiontype
	 * 
	 * @return a String with the functiontype
	 */
	public String getFunctionType() {
		return ftype;
	}
	
	/**
	 * Sets the frequency
	 * 
	 * @param freq the String with the frequency to set
	 */
	public void setFrequency(String freq) {
		this.frequency = freq;
	}
	
	/**
	 * Get the frequency
	 * 
	 * @return a String with the frequency
	 */
	public String getFrequency() {
		return frequency;
	}
	
	/**
	 * Sets the memory
	 * 
	 * @param memory the String with the memory to set
	 */
	public void setMemory(String memory) {
		this.memory = memory;
	}
	
	/**
	 * Get the memory
	 * 
	 * @return a String with the memory
	 */
	public String getMemory() {
		return memory;
	}
	
	/**
	 * Sets the CPU
	 * 
	 * @param cpu the String with the cpu to set
	 */
	public void setCPU(String cpu) {
		this.cpu = cpu;
	}
	
	/**
	 * Get the cpu
	 * 
	 * @return a String with the cpu
	 */
	public String getCPU() {
		return cpu;
	}
	
	/**
	 * Sets the Register
	 * 
	 * @param register the String with the register to set
	 */
	public void setRegister(String register) {
		this.register = register;
	}
	
	/**
	 * Get the register
	 * 
	 * @return a String with the register
	 */
	public String getRegister() {
		return register;
	}
	
	/**
	 * Sets the Interfaces
	 * 
	 * @param interfaces the String with the interfaces to set
	 */
	public void setInterfaces(String interfaces) {
		this.interfaces = interfaces;
	}
	
	/**
	 * Get the interfaces
	 * 
	 * @return a String with the interfaces
	 */
	public String getInterfaces() {
		return interfaces;
	}
	
	/**
	 * Sets the Description
	 * 
	 * @param description the String with the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Get the description
	 * 
	 * @return a String with the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Adds a Tag object
	 * 
	 * @param tag the {@link Tag} object to add
	 */
	public void addTag(Tag tag) {
		tags.add(tag);
	}
	
	/**
	 * Returns all Tags of the component
	 * 
	 * @return an ArrayList with all {@link Tag} objects
	 */
	public ArrayList<Tag> getTags() {
		return tags;
	}
	
	/**
	 * Returns one single {@link Tag} object with the given index
	 * 
	 * @param index the 1-based index of the Tag
	 * @return a {@link Tag} object
	 */
	public Tag getTag(int index) {
		return tags.get(index-1);
	}
	
	/**
	 * Represents a tag on a board
	 */
	public class Tag {
		
		private int id;
		private String name;
		private String img;
		private float x;
		private float y;
		private float z;
		private String desc;
		
		/**
		 * Constructor
		 */
		public Tag() {
			
		}
		
		/**
		 * Constructor to set all Values at creating
		 * 
		 * @param id the id of the tag
		 * @param n the name of the tag
		 * @param i the imagepath of the tag
		 * @param _x the x position of the tag
		 * @param _y the y position of the tag
		 * @param _z the z position of the tag
		 */
		public Tag(int id, String n, String i, float _x, float _y, float _z) {
			this.id = id;
			this.name = n;
			this.img = i;
			this.x = _x;
			this.y = _y;
			this.z = _z;
		}
		
		/**
		 * Constructor to set the name at creating
		 * 
		 * @param n the name of the tag
		 */
		public Tag(String n) {
			this.name = n;
		}
		
		/**
		 * Set the ID of the tag
		 * 
		 * @param id the id of the tag
		 */
		public void setId(int id) {
			this.id = id;
		}
		
		/**
		 * Set the name of the tag
		 * 
		 * @param n the name of the tag
		 */
		public void setName(String n) {
			this.name = n;
		}
		
		/**
		 * Set the imagepath of the tag
		 * 
		 * @param i the imagepath of the tag
		 */
		public void setImg(String i) {
			this.img = i;
		}
		
		/**
		 * Set the x position of the tag
		 * 
		 * @param val the x position of the tag
		 */
		public void setX(float val) {
			this.x = val;
		}
		
		/**
		 * Set the y position of the tag
		 * 
		 * @param val the y position of the tag
		 */
		public void setY(float val) {
			this.y = val;
		}
		
		/**
		 * Set the z position of the tag
		 * 
		 * @param val the z position of the tag
		 */
		public void setZ(float val) {
			this.z = val;
		}
		
		/**
		 * Set the description of the tag
		 * 
		 * @param d the description of the tag
		 */
		public void setDesc(String d) {
			this.desc = d;
		}
		
		/**
		 * Returns the ID of the tag
		 * 
		 * @return the id of the tag
		 */
		public int getId() {
			return id;
		}
		
		/**
		 * Returns the Name of the tag
		 * 
		 * @return the name of the tag
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * Returns the imagepath of the tag
		 * 
		 * @return the imagepath of the tag
		 */
		public String getImg() {
			return img;
		}
		
		/**
		 * Returns the Position of the tag
		 * 
		 * @return an array with x, y and z position of the tag
		 */
		public float[] getPos() {
			return new float[] {x, y, z};
		}
		
		/**
		 * Returns the description of the tag
		 * 
		 * @return the description of the tag
		 */
		public String getDesc() {
			return desc;
		}
		
	}
}
