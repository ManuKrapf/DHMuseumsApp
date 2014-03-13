package dh.computermuseum;

public class Storage extends Antique {
  private String type;
  private String capacity;
  private String material;
  private String endurance;
  private String size;
  private String developer;
  private String rwSpeed;
  
  public Storage() {
    
  }
  
  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
  
  public void setCapacity(String capacity) {
    this.capacity = capacity;
  }

  public String getCapacity() {
    return capacity;
  }
  
  public void setMaterial(String material) {
    this.material = material;
  }

  public String getMaterial() {
    return material;
  }
  
  public void setEndurance(String endurance) {
    this.endurance = endurance;
  }

  public String getEndurance() {
    return endurance;
  }
  
  public void setSize(String size) {
    this.size = size;
  }

  public String getSize() {
    return size;
  }
  
  public void setDeveloper(String developer) {
    this.developer = developer;
  }

  public String getDeveloper() {
    return developer;
  }
  
  public void setRwSpeed(String rwSpeed) {
    this.rwSpeed = rwSpeed;
  }

  public String getRwSpeed() {
    return rwSpeed;
  }
}
