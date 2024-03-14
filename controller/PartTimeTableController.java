package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ModelTablePartTime;
import ui.ChooseTableCategory;

public class PartTimeTableController implements Initializable {
	
	private final String URL = "jdbc:mysql://localhost:3306/mydb";
	private final String USER="root";
	private final String PASSWORD = "";
	private Connection connect = null;
	private String sql = null;
	private ResultSet rs;

    @FXML
    private TableView<ModelTablePartTime> table;

    @FXML
    private TableColumn<ModelTablePartTime, String> col_id;

    @FXML
    private TableColumn<ModelTablePartTime, String> col_department;

    @FXML
    private TableColumn<ModelTablePartTime, String> col_floor;

    @FXML
    private TableColumn<ModelTablePartTime, String> col_name;

    @FXML
    private TableColumn<ModelTablePartTime, String> col_hours;

    @FXML
    private TableColumn<ModelTablePartTime, String> col_food;

    @FXML
    private TableColumn<ModelTablePartTime, String> col_Salary;

    @FXML
    private Button btBack;
    
    ObservableList<ModelTablePartTime> oblist = FXCollections.observableArrayList();


    @FXML
    void backButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new ChooseTableCategory(stage);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			connect = DriverManager.getConnection(URL, USER, PASSWORD);
			sql = "SELECT * FROM `department` AS dt LEFT JOIN `part-time` AS pt ON dt.Employee_Id = pt.id WHERE pt.id is not null ORDER BY dt.department ASC, dt.Employee_Id ASC";
			PreparedStatement pstmt = connect.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			oblist.add(new ModelTablePartTime(rs.getString("Employee_Id"), rs.getString("department"), rs.getString("floor"), 
    					rs.getString("name"), rs.getString("total_working_hour"), rs.getString("food_allowance"), rs.getString("monthly_salary")));
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
		col_floor.setCellValueFactory(new PropertyValueFactory<>("floor"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_hours.setCellValueFactory(new PropertyValueFactory<>("totalHour"));
		col_food.setCellValueFactory(new PropertyValueFactory<>("foodAllowance"));
		col_Salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
		
		table.setItems(oblist);
	}

}
