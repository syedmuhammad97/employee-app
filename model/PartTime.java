package model;

public class PartTime extends Employees{
	private int totalWorkingHour;
	private int foodAllowance;
//	private Department dept;
	private int monthlySalary;
	
	public PartTime(Department dept, int id, String name, String category, int totalWorkingHour, int foodAllowance) {
		super( dept, id, name, category);
		this.totalWorkingHour = totalWorkingHour;
		this.foodAllowance = foodAllowance;
	}

	public int getTotalWorkingHour() {
		return totalWorkingHour;
	}

	public void setTotalWorkingHour(int totalWorkingHour) {
		this.totalWorkingHour = totalWorkingHour;
	}

	public int getFoodAllowance() {
		return foodAllowance;
	}

	public void setFoodAllowance(int foodAllowance) {
		this.foodAllowance = foodAllowance;
	}

	public int getMonthlySalary() {
		monthlySalary = foodAllowance + (totalWorkingHour * 12);
		return monthlySalary;
	}

	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	
	

}
