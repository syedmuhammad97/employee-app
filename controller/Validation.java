package controller;

public class Validation {
	public static boolean idValidation(int id) {
		if(id>=1001 && id<=9999) {
			return true;
		}
		else
			return false;
	}

	public static boolean deptValidation(String dept) {
		if(dept.equals("Engineer") || dept.equals("Marketing") || dept.equals("MIS") ) {
			return true;
		}
		else
			return false;
	}

	public static boolean nameValidation(String name) {
		if(name.matches("[A-Za-z ]+")) {
			return true;
		}
		else
			return false;
	}

	public static boolean categoryValidation(String category) {
		if(category.equals("Fulltime") || category.equals("fulltime") || category.equals("Part-time") || category.equals("part-time") ) {
			return true;
		}
		else
			return false;
	}
	
	public static boolean wagesValidation(int wages) {
		if(wages > 0) {
			return true;
		}
		else
			return false;
	}
	
	public static boolean commValidation(int comm) {
		if(comm > 0) {
			return true;
		}
		else
			return false;
	}
	
	public static boolean workingHrValidation(int totalWorkingHour) {
		if(totalWorkingHour > 0) {
			return true;
		}
		else
			return false;
	}
	
	public static boolean foodAllowanceValidation(int foodAllowance) {
		if(foodAllowance > 0) {
			return true;
		}
		else
			return false;
	}
	
	public static boolean totalSalaryValidation(int totalSalary) {
		if(totalSalary > 0) {
			return true;
		}
		else
			return false;
	}
	
	public static boolean monthlySalaryValidation(int monthlySalary) {
		if(monthlySalary > 0) {
			return true;
		}
		else
			return false;
	}
	
	public static boolean floorValidation(String floor) {
		if(floor.equals("8th floor") || floor.equals("5th floor") || floor.equals("3rd floor")) {
			return true;
		}
		else
			return false;
	}
}
