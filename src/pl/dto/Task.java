package pl.dto;

public class Task {

	private int id;
	private String element_type = "t";
	private String full_id;
	private String name;
	private String description;
	private String version;
	private String workflow;
	private String status;
	private int priority;
	private int concept_id;
	private String concept_name;
	private int project_id;
	private String project_name;
	private String project_image;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getElement_type() {
		return element_type;
	}
	public void setElement_type(String element_type) {
		this.element_type = element_type;
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String[] getWorkflow() {
		String[] parts = workflow.split(";");
		return parts;
	}
	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getConcept_id() {
		return concept_id;
	}
	public void setConcept_id(int concept_id) {
		this.concept_id = concept_id;
	}
	public String getConcept_name() {
		return concept_name;
	}
	public void setConcept_name(String concept_name) {
		this.concept_name = concept_name;
	}
	public String getFull_id() {
		full_id = element_type + id;
		return full_id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_image() {
		return project_image;
	}
	public void setProject_image(String project_image) {
		this.project_image = project_image;
	}

	
}
