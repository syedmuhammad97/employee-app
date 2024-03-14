package model;

public class FullTime extends Employees{
	private int wages;
	private int comm;
	private int totalSalary;
	
	public FullTime(Department dept, int id, String name, String category, int wages, int comm) {
		super(dept, id, name, category);
		this.wages = wages;
		this.comm = comm;
	}

	public int getWages() {
		return wages;
	}

	public void setWages(int wages) {
		this.wages = wages;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public int getTotalSalary() {
		totalSalary = comm + wages;
		return totalSalary;
	}

	public void setTotalSalary(int totalSalary) {
		this.totalSalary = totalSalary;
	}
	
	

}
