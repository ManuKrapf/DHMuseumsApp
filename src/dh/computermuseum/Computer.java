package dh.computermuseum;

import java.util.ArrayList;

/**
 * Object that represents a computer
 * 
 * @author Johannes Lengdobler, Simon Provinsky, Timo Schaschek, Manuel Krapf
 * @version 1.0
 */
public class Computer extends Antique {
	
	private String producer;
	private String os;
  	private String memory;
  	private String ram;
  	private String cpu;
  	private String special;
  	private String img;
  	private String description;
  	private ArrayList<InnerlifeComponent> components;
  	private CompVideo video;
  	
  	/**
  	 * Constructor
  	 */
  	public Computer() {
  		super("computer");
  		components = new ArrayList<InnerlifeComponent>();
  	}
  	
  	/**
  	 * Set the producer of the computer
  	 * 
  	 * @param producer the producer of the computer
  	 */
  	public void setProducer(String producer) {
  		this.producer = producer;
  	}
  	
  	/**
  	 * Get the producer of the computer
  	 * 
  	 * @return the producer of the computer
  	 */
  	public String getProducer() {
  		return producer;
  	}
  	
  	/**
  	 * Set the os of the computer
  	 * 
  	 * @param os the os of the computer
  	 */
  	public void setOs(String os) {
		this.os = os;
	}
	/**
	 * Get the os of the computer
	 * 
	 * @return the os of the computer
	 */
	public String getOs() {
		return os;
	}
	
	/**
	 * Set the memory of the computer
	 * 
	 * @param memory the memory of the computer
	 */
	public void setMemory(String memory) {
		this.memory = memory;
	}
	
	/**
	 * Get the memory of the computer
	 * 
	 * @return the memory of the computer
	 */
	public String getMemory() {
		return memory;
	}
	
	/**
	 * Set the RAM of the computer
	 * 
	 * @param ram the RAM of the computer
	 */
	public void setRam(String ram) {
		this.ram = ram;
	}
	
	/**
	 * Get the RAM of the computer
	 * 
	 * @return the RAM of the computer
	 */
	public String getRam() {
		return ram;
	}
	
	/**
	 * Set the CPU of the computer
	 * 
	 * @param cpu the CPU of the computer
	 */
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	
	/**
	 * Get the CPU of the computer
	 * 
	 * @return the CPU of the computer
	 */
	public String getCpu() {
		return cpu;
	}
	
	/**
	 * Set the specials of the computer
	 * 
	 * @param special the specials of the computer
	 */
	public void setSpecial(String special) {
		this.special = special;
	}
	
	/**
	 * Get the specials of the computer
	 * 
	 * @return the specials of the computer
	 */
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
	
	/**
	 * Set the description of the computer
	 * 
	 * @param description the description of the computer
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Get the description of the computer
	 * 
	 * @return the description of the computer
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Add a InnerlifeComponent object to the list 
	 * 
	 * @param ic the InnerlifeComponent object to add
	 */
	public void addComponent(InnerlifeComponent ic) {
		components.add(ic);
	}
	
	/**
	 * Get all InnerlifeCoponents of the computer
	 * 
	 * @return an ArrayList with all InnerlifeComponents
	 */
	public ArrayList<InnerlifeComponent> getComponents() {
		return components;
	}
	
	/**
	 * Get a single InnerlifeComponent object by its index
	 * 
	 * @param index the 1-based index of the component
	 * @return a InnerlifeComponent object
	 */
	public InnerlifeComponent getComponent(int index) {
		return components.get(index-1);
	}
	
	/**
	 * Creates a CompVideo object with the videopath
	 * 
	 * @param path the videopath
	 */
	public void setVideo(String path) {
		video = new CompVideo(path);
	}
	
	/**
	 * Get the CompVideo object
	 * 
	 * @return a CompVideo object
	 */
	public CompVideo getVideo() {
		return video;
	}
	
	/**
	 * A class for storing all informations to a video of the computer
	 */
	public class CompVideo {
		
		private String path;
		private float x;
		private float y;
		private float z;
		
		/**
		 * Constructor to set all values
		 * 
		 * @param p the path of the video
		 * @param _x the x position of the video
		 * @param _y the y position of the video
		 * @param _z the z position of the video
		 */
		public CompVideo(String p, float _x, float _y, float _z) {
			this.path = p;
			this.x = _x;
			this.y = _y;
			this.z = _z;
		}
		
		/**
		 * Constructor setting the path
		 * 
		 * @param p the path of the video
		 */
		public CompVideo(String p) {
			this.path = p;
		}
		
		/**
		 * Get the path of the video
		 * 
		 * @return the videopath
		 */
		public String getPath() {
			return path;
		}
		
		/**
		 * Set the x position of the video
		 * 
		 * @param val the x position of the video
		 */
		public void setX(float val) {
			this.x = val;
		}
		
		/**
		 * Set the y position of the video
		 * 
		 * @param val the y position of the video
		 */
		public void setY(float val) {
			this.y = val;
		}
		
		/**
		 * Set the z position of the video
		 * 
		 * @param val the z position of the video
		 */
		public void setZ(float val) {
			this.z = val;
		}
		
		/**
		 * Get the position of the video
		 * 
		 * @return an array with x, y and z position of the video
		 */
		public float[] getPos() {
			return new float[] {x, y, z};
		}
	}
}
