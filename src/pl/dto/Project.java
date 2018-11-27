package pl.dto;

public class Project {
	
	private int id;
	private String name;
	private String description;
	private String image;
	private String full_id;
	private String version;
	
	
	
	
	
	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param image
	 */
	public Project(int id, String name, String description, String image) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
	}
	public Project() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getFull_id() {
		full_id = "p"+id;
		return full_id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
	

}
