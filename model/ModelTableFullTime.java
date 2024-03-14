package model;

public class ModelTableFullTime {
	
	private String id, department, floor, name, wages, comm, salary;

	public ModelTableFullTime(String id, String department, String floor, String name, String wages, String comm, String salary) {	
		this.id = id;
		this.department = department;
		this.floor = floor;
		this.name = name;
		this.wages = wages;
		this.comm = comm;
		this.salary = salary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWages() {
		return wages;
	}

	public void setWages(String wages) {
		this.wages = wages;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	
	
	
	
	
	

}
