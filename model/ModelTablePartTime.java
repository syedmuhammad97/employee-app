package model;

public class ModelTablePartTime {
	
	private String id, department, floor, name, totalHour, foodAllowance, salary;

	public ModelTablePartTime(String id, String department, String floor, String name, String totalHour,
			String foodAllowance, String salary) {
		this.id = id;
		this.department = department;
		this.floor = floor;
		this.name = name;
		this.totalHour = totalHour;
		this.foodAllowance = foodAllowance;
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

	public String getTotalHour() {
		return totalHour;
	}

	public void setTotalHour(String totalHour) {
		this.totalHour = totalHour;
	}

	public String getFoodAllowance() {
		return foodAllowance;
	}

	public void setFoodAllowance(String foodAllowance) {
		this.foodAllowance = foodAllowance;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	
	
	

}
