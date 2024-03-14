package model;

public class Department {
	private String dept;
	private String floor;
	
	public Department(String dept, String floor) {
		setDept(dept);
		setFloor(floor);
	}
	
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	
	@Override
	public String toString() {
		return String.format("%s Department\n Id\n %d", dept, floor);
	}
	
}
