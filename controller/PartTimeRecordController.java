package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ui.PartTimeModification;

public class PartTimeRecordController implements Initializable {
	
	private final String URL = "jdbc:mysql://localhost:3306/mydb";
	private final String USER="root";
	private final String PASSWORD = "";
	private Connection connect = null;
	private Statement stmt = null;
	private String sql = null;
	private ResultSet rs;

    @FXML
    private ImageView employeeImage;

    @FXML
    private ComboBox<Integer> employeeID;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfDepartment;

    @FXML
    private TextField tfHours;

    @FXML
    private TextField tfFood;

    @FXML
    private Button btPreview;

    @FXML
    private Button btBack;

    @FXML
    private TextField tfFloor;

    @FXML
    private TextField tfTotalSalary;

    @FXML
    void backButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new PartTimeModification(stage);
    }

    @FXML
    void previewButton(ActionEvent event) {
    	int id = employeeID.getValue();
    	Database database = new Database();
    	
    	try {
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
						ex.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			// pop-up error message
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
    		connect = DriverManager.getConnection(URL, USER, PASSWORD);
    		sql = "SELECT * FROM `part-time` WHERE `id`";
    		PreparedStatement pstmt = connect.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			int id = rs.getInt("id");
    			ObservableList <Integer> list = FXCollections.observableArrayList(id);
    			employeeID.getItems().addAll(list);
    		}
    	}catch(Exception ex) {
    		//error message
    	}	
	}

}
