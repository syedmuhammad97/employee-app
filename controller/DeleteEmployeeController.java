package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.DisplayMessage;
import ui.MainMenu;

public class DeleteEmployeeController implements Initializable {

	@FXML
	private ComboBox<Integer> employeeID;

	@FXML
	private TextField tfName;

	@FXML
	private TextField tfDepartment;

	@FXML
	private TextField tfWage;

	@FXML
	private TextField tfComm;

	@FXML
	private TextField tfHours;

	@FXML
	private TextField tfFood;

	@FXML
	private TextField tfFloor;

	@FXML
	private TextField tfTotalSalary;

	@FXML
	private Button btSubmit;

	@FXML
	private Button btPreview;

	@FXML
	private Button btExit;

	@FXML
	private ImageView employeeImage;

	@FXML
	void exitButton(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		new MainMenu(stage);

	}

	@FXML
	void previewButton(ActionEvent event){
		DisplayMessage dm = new DisplayMessage();
		try {
			int id = employeeID.getValue();
			viewEmployee(id);
		}catch(Exception e) {
			dm.alert();
			e.printStackTrace();
		}
	}

	@FXML
	void submitButton(ActionEvent event) {
		DisplayMessage dm = new DisplayMessage();

		try {
			int id = employeeID.getValue();
			new Database().deleteEmployee(id);
		} catch (SQLException e) {
			dm.alertSQLEx();
		}
	}

	public void viewEmployee(int id) throws SQLException {
		Database database = new Database();
		DisplayMessage dm = new DisplayMessage();
		if(database.checkIDFull(id)) {
			database.viewEmployee(id);
			while(database.getRs().next()) {
				tfDepartment.setText(database.getRs().getString("department"));
				tfFloor.setText(database.getRs().getString("floor"));
			}
			database.viewFTEmployee(id);
			while(database.getRs().next()) {

				tfName.setText(database.getRs().getString("name"));
				tfWage.setText(Integer.toString(database.getRs().getInt("wages")));
				tfComm.setText(Integer.toString(database.getRs().getInt("comm")));
				tfTotalSalary.setText(Integer.toString(database.getRs().getInt("totalSalary")));
				tfHours.setText("");
				tfFood.setText("");

				InputStream is = database.retrieve(id);
				try {
					OutputStream os = new FileOutputStream(new File("image.jpg"));
					byte b[] = new byte[1024];
					int size = 0;
					while((size = is.read(b)) != -1)
						os.write(b,0, size);
					Image image = new Image("file:image.jpg", employeeImage.getFitWidth(), employeeImage.getFitHeight(), true, true);
					employeeImage.setImage(image);
				}catch(Exception ex) {
					dm.alert();
					ex.printStackTrace();
				}   
			}
		}

		if(database.checkIDPart(id)) {
			database.viewEmployee(id);
			while(database.getRs().next()) {
				tfDepartment.setText(database.getRs().getString("department"));
				tfFloor.setText(database.getRs().getString("floor"));
			}
			database.viewPTEmployee(id);
			while(database.getRs().next()) {
				tfName.setText(database.getRs().getString("name"));
				tfHours.setText(Integer.toString(database.getRs().getInt("total_working_hour")));
				tfFood.setText(Integer.toString(database.getRs().getInt("food_allowance")));
				tfTotalSalary.setText(Integer.toString(database.getRs().getInt("monthly_salary")));
				tfWage.setText("");
				tfComm.setText("");

				InputStream is = database.retrieve(id);
				try {
					OutputStream os = new FileOutputStream(new File("image.jpg"));
					byte b[] = new byte[1024];
					int size = 0;
					while((size = is.read(b)) != -1)
						os.write(b,0, size);
					Image image = new Image("file:image.jpg", employeeImage.getFitWidth(), employeeImage.getFitHeight(), true, true);
					employeeImage.setImage(image);
				}catch(Exception ex) {
					dm.alert();
					ex.printStackTrace();
				}
			}
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DisplayMessage dm = new DisplayMessage();
		Database database = new Database();
		try {
			database.selectEmployeeID(); 		
			while(database.getRs().next()) {
				int id = database.getRs().getInt("Employee_Id");
				ObservableList <Integer> list = FXCollections.observableArrayList(id);
				employeeID.getItems().addAll(list);
			}
		}catch(Exception ex) {
			dm.alert();
		}

	}

}
