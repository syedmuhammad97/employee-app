package ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DisplayMessage {
	
	public void alertID() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.setHeaderText("There is an error with the request");
		alert.setContentText("ID cannot be less than 1001 and more than 9999! Please try again.");

		alert.showAndWait();
	}
	
	public void alertName() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.setHeaderText("There is an error with the request");
		alert.setContentText("Name cannot contain symbol or number");

		alert.showAndWait();
	}
	
	public void alertWage() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.setHeaderText("There is an error with the request");
		alert.setContentText("Wages cannot be less than 0");

		alert.showAndWait();
	}
	
	public void alertComm() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.setHeaderText("There is an error with the request");
		alert.setContentText("Commission cannot be less than 0");

		alert.showAndWait();
	}
	
	public void alert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.setHeaderText("There is an error with the request");
		alert.setContentText("Ooops, there was an error with your input! Please try again.");

		alert.showAndWait();
	}
	
	public void alertCheckID() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.setHeaderText("There is an error with the request");
		alert.setContentText("Sorry, this ID has already exist, please try again.");

		alert.showAndWait();
	}
	
	public void alertWorkingHour() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.setHeaderText("There is an error with the request");
		alert.setContentText("Total Working Hours cannot be less than 0");

		alert.showAndWait();
	}
	
	public void alertFoodAllowance() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.setHeaderText("There is an error with the request");
		alert.setContentText("Food Allowance cannot be less than 0");

		alert.showAndWait();
	}
	
	public void alertSQLEx() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.setHeaderText("There is an error with the request");
		alert.setContentText("SQL Exception, please try again");

		alert.showAndWait();
	}
	
	public void modifySuccessfully() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Employee has been modify successfully!");

		alert.showAndWait();
	}
	
	public void addSuccessfully() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Employee has been added successfully!");

		alert.showAndWait();
	}

}
