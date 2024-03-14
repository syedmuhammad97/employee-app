package controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import model.PartTime;
import ui.ChooseModifyCategory;
import ui.DisplayMessage;
import ui.PartTimeRecord;

public class PartTimeModificationController implements Initializable{
	String fileName = null; 
    String imgFile = null;
    
    DisplayMessage dm = new DisplayMessage();
	Database database = new Database();
	
    ObservableList<String> employeeDeptList = FXCollections.observableArrayList("Engineer", "Marketing", "MIS");


    @FXML
    private ComboBox<String> employeeDepartment;

    @FXML
    private TextField tfNewName;

    @FXML
    private TextField tfNewHours;

    @FXML
    private TextField tfNewFoodAllowance;

    @FXML
    private ImageView employeeImage;

    @FXML
    private Button btSubmit;

    @FXML
    private Button btBack;

    @FXML
    private Button btChoosePhoto;

    @FXML
    private Button btPreview;

    @FXML
    private ComboBox<Integer> employeeID;

    @FXML
    void backButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new ChooseModifyCategory(stage);
    }

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
    void previewButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new PartTimeRecord(stage);
    }

    @FXML
    void submitButton(ActionEvent event) {
    	try {
    		String floor = null;
    		String cat = "part-time";
    		int id = employeeID.getValue();
    		if(!Validation.idValidation(id)) {
    			dm.alertCheckID();
    		}
    		else {
    			String dept = employeeDepartment.getValue();
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
    				String totalWorkingHr = tfNewHours.getText();
    				int totalWorkingHr1 = Integer.parseInt(totalWorkingHr);
    				if(!Validation.workingHrValidation(totalWorkingHr1)) {
    					dm.alertWorkingHour();
    				}
    				else {
    					String foodAllowance = tfNewFoodAllowance.getText();
    					int foodAllowance1 = Integer.parseInt(foodAllowance);
    					if(!Validation.foodAllowanceValidation(foodAllowance1)) {
							dm.alertFoodAllowance();
						}
    					else {
    					Department department = new Department(dept, floor);
    					PartTime pt = new PartTime(department, id, name, cat, totalWorkingHr1, foodAllowance1);
    					new Database().modifyEmployeePT(department, pt, imgFile);
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
	public void initialize(java.net.URL location, ResourceBundle resources) {
		employeeDepartment.setValue("Choose Your Department");
		employeeDepartment.setItems(employeeDeptList);
		
		try {
    		database.selectPTEmployeeID();		
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
