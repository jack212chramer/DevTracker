package pl.components;

public class Assignement {

	private String element_type;
	private int id;
	private String full_id;
	private String assigned;
	private String name;
	
	
	
	public String getElement_type() {
		return element_type;
	}
	public void setElement_type(String element_type) {
		this.element_type = element_type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFull_id() {
		return full_id;
	}
	public void setFull_id(String full_id) {
		this.full_id = full_id;
	}
	public String getAssigned() {
		return assigned;
	}
	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 public void buildFullId(){
		 full_id = element_type + id;
	 }
	
}
