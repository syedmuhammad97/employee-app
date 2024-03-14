package controller;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import db.controller.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Department;
import model.PartTime;
import ui.DisplayMessage;
import ui.MainMenu;

public class AddPartTimeController implements Initializable {

	String fileName = null;
	String imgFile = null;

	ObservableList<String> employeeDeptList = FXCollections.observableArrayList("Engineer", "Marketing", "MIS");

	@FXML
	private TextField tfID;

	@FXML
	private ComboBox<String> employeeDept;

	@FXML
	private TextField tfName;

	@FXML
	private TextField tfTotalWorkingHour;

	@FXML
	private TextField tfFoodAllowance;

	@FXML
	private ImageView image;

	@FXML
	private Button submit;

	@FXML
	private Button exit;

	@FXML
	private Button choosePhoto;

	@FXML
	void choosePhotoButton(ActionEvent event) {
		Stage stage = new Stage();
		FileChooser chooser = new FileChooser();
		File fileDirectory = new File("C:\\Programming II\\Assignment 2 (2020)\\src\\image");
		chooser.setInitialDirectory(fileDirectory);
		chooser.setTitle("pick a picture");
		fileName = chooser.showOpenDialog(stage).getName();
		imgFile = "\\image\\" + fileName;

		Image img = new Image(imgFile);
		image.setImage(img);

	}

	@FXML
	void exitButton(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		new MainMenu(stage);

	}

	@FXML
	void submitButton(ActionEvent event) throws SQLException{
		Database database = new Database();
		DisplayMessage dm = new DisplayMessage();
		try {
			String floor = null;
			String cat = "part-time";
			String id = tfID.getText();	
			int id1 = Integer.parseInt(id);
			if(database.checkID(id1)) {
				dm.alertCheckID();
			}
			else {
				if(!Validation.idValidation(id1)) {
					dm.alertID();
				}
				else {
					String dept = employeeDept.getValue();
					if(dept.equals("Engineer")) {
						floor = "8th Floor";
					}
					else if(dept.equals("MIS")) {
						floor = "3rd Floor";
					}
					else if (dept.equals("Marketing")) {
						floor = "5th Floor";
					}
					String name = tfName.getText();
					if(!Validation.nameValidation(name)) {
						dm.alertName();
					}
					else {
						String totalWorkingHr = tfTotalWorkingHour.getText();
						int totalWorkingHr1 = Integer.parseInt(totalWorkingHr);
						if(!Validation.workingHrValidation(totalWorkingHr1)) {
							dm.alertWorkingHour();
						}
						else {
							String foodAllowance = tfFoodAllowance.getText();
							int foodAllowance1 = Integer.parseInt(foodAllowance);
							if(!Validation.foodAllowanceValidation(foodAllowance1)) {
								dm.alertFoodAllowance();
							}
							else {
								Department department = new Department(dept, floor);
								PartTime pt = new PartTime(department, id1, name, cat, totalWorkingHr1, foodAllowance1);
								new Database().PTEmployee(department, pt, imgFile);
								new DisplayMessage().addSuccessfully();
							}
						}
					}
				}
			}
		}catch(NumberFormatException ex) {
			dm.alert();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		employeeDept.setValue("Choose Your Department");
		employeeDept.setItems(employeeDeptList);

	}

}
