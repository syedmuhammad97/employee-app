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
import model.ModelTableFullTime;
import ui.ChooseTableCategory;

public class FullTimeTableController implements Initializable {
	
	private final String URL = "jdbc:mysql://localhost:3306/mydb";
	private final String USER="root";
	private final String PASSWORD = "";
	private Connection connect = null;
	private String sql = null;
	private ResultSet rs;
	
    @FXML
    private TableView<ModelTableFullTime> table;

    @FXML
    private TableColumn<ModelTableFullTime, String> col_id;

    @FXML
    private TableColumn<ModelTableFullTime, String> col_department;

    @FXML
    private TableColumn<ModelTableFullTime, String> col_floor;

    @FXML
    private TableColumn<ModelTableFullTime, String> col_name;

    @FXML
    private TableColumn<ModelTableFullTime, String> col_wages;

    @FXML
    private TableColumn<ModelTableFullTime, String> col_comm;

    @FXML
    private TableColumn<ModelTableFullTime, String> col_Salary;
    
    @FXML
    private Button btBack;

    @FXML
    void backButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new ChooseTableCategory(stage);
    }

    ObservableList<ModelTableFullTime> oblist = FXCollections.observableArrayList();
	
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	try {
			connect = DriverManager.getConnection(URL, USER, PASSWORD);
			sql = "SELECT * FROM `department` AS dt LEFT JOIN `full-time` AS ft ON dt.Employee_Id = ft.id WHERE ft.id IS NOT NULL ORDER BY dt.department ASC, dt.Employee_Id ASC"; 
			PreparedStatement pstmt = connect.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			oblist.add(new ModelTableFullTime(rs.getString("Employee_Id"), rs.getString("department"), rs.getString("floor"), rs.getString("name"),
    					rs.getString("wages"), rs.getString("comm"), rs.getString("totalSalary")));
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
		col_floor.setCellValueFactory(new PropertyValueFactory<>("floor"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_wages.setCellValueFactory(new PropertyValueFactory<>("wages"));
		col_comm.setCellValueFactory(new PropertyValueFactory<>("comm"));
		col_Salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
		
		table.setItems(oblist);


	}

}

