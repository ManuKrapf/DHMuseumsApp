package dh.computermuseum;

public class Antique {
	private String name;
	private int id;
	private String release;
	private String img;

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

	public void setReleaseDate(String release) {
		this.release = release;
	}

	public String getReleaseDate() {
		return release;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public String getImg() {
		return img;
	}
}
