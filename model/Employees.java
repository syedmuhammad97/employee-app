package model;

public class Employees {
	private String name;
	private String category;
	private int id;
	private Department dept;
	
	public Employees(Department dept, int id, String name, String category) {
		setName(name);
		setCategory(category);
		setId(id);
		this.dept = dept;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	

}
