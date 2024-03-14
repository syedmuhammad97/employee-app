package controller;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import db.controller.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Department;
import model.FullTime;
import ui.ChooseModifyCategory;
import ui.DisplayMessage;
import ui.FullTimeRecord;

public class FullTimeModificationController implements Initializable {
	DisplayMessage dm = new DisplayMessage();
	Database database = new Database();

	String fileName = null; 
	String imgFile = null;

	ObservableList<String> employeeDeptList = FXCollections.observableArrayList("Engineer", "Marketing", "MIS");

	@FXML
	private ComboBox<String> newDepartment;

	@FXML
	private TextField tfNewName;

	@FXML
	private TextField tfNewWage;

	@FXML
	private TextField tfNewComm;

	@FXML
	private ImageView employeeImage;

	@FXML
	private Button btSubmit;

	@FXML
	private Button btExit;

	@FXML
	private Button btChoosePhoto;

	@FXML
	private ComboBox<Integer> employeeID;

	@FXML
	private Button btPreview;

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
		employeeImage.setImage(img);
	}

	@FXML
	void exitButton(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		new ChooseModifyCategory(stage);
	}

	@FXML
	void previewButton(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		new FullTimeRecord(stage);
	}

	@FXML
	void sumbitButton(ActionEvent event) throws SQLException {
		try {
			String floor = null;
			String cat = "part-time";
			int id = employeeID.getValue();
			if(!Validation.idValidation(id)) {
				dm.alertCheckID();
			}
			else {
				String dept = newDepartment.getValue();
				if(dept.equals("Engineer")) {
					floor = "8th Floor";
				}
				else if(dept.equals("MIS")) {
					floor = "3rd Floor";
				}
				else if (dept.equals("Marketing")) {
					floor = "5th Floor";
				}
				String name = tfNewName.getText();
				if(!Validation.nameValidation(name)) {
					dm.alertName();
				}
				else {
					String wage = tfNewWage.getText();
					int wage1 = Integer.parseInt(wage);
					if(!Validation.wagesValidation(wage1)) {
						dm.alertWage();
					}
					else {
						String comm = tfNewComm.getText();
						int comm1 = Integer.parseInt(comm);
						if(!Validation.commValidation(comm1)) {
							dm.alertComm();
						}
						else {
							Department department = new Department(dept, floor);
							FullTime ft = new FullTime(department, id, name, cat, wage1, comm1);
							new Database().modifyEmployeeFT(department, ft, imgFile);
							dm.modifySuccessfully();
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

		newDepartment.setValue("Choose Your Department");
		newDepartment.setItems(employeeDeptList);

		try {
			database.selectFTEmployeeID();

			while(database.getRs().next()) {
				int id = database.getRs().getInt("id");
				ObservableList <Integer> list = FXCollections.observableArrayList(id);
				employeeID.getItems().addAll(list);
			}
		}catch(Exception ex) {
			dm.alert();
		}

	}

}