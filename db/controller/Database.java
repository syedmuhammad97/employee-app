package db.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Department;
import model.Employees;
import model.FullTime;
import model.PartTime;
//import model.PartTime;

public class Database {

	private final String URL = "jdbc:mysql://localhost:3306/mydb";
	private final String USER="root";
	private final String PASSWORD = "";
	private int result;

	private Connection connect = null;
	private Statement stmt = null;
	private String sql = null;
	private ResultSet rs;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public void FTEmployee(Department dept, FullTime ft, String file){

		try {
			connect = DriverManager.getConnection(URL, USER, PASSWORD);
			FileInputStream fis=new FileInputStream("C:\\Programming II\\Assignment 2 (2020)\\src"+file);
			sql = "INSERT INTO `full-time` (`id`, `name`, `wages` , `comm` , `totalSalary` , `image`) values (?,?,?,?,?,?)";
			PreparedStatement pstmt = connect.prepareStatement(sql);
			pstmt.setInt(1, ft.getId());
			pstmt.setString(2, ft.getName());
			pstmt.setInt(3, ft.getWages());
			pstmt.setInt(4, ft.getComm());
			pstmt.setInt(5, ft.getTotalSalary());
			pstmt.setBlob(6, fis);
			result = pstmt.executeUpdate();

			connect = DriverManager.getConnection(URL, USER, PASSWORD);
			sql = "INSERT INTO `department`(`department`, `floor`, `Employee_Id`) values (?,?,?)";
			PreparedStatement pstmt2 = connect.prepareStatement(sql);
			pstmt2.setString(1, dept.getDept());
			pstmt2.setString(2, dept.getFloor());
			pstmt2.setInt(3, ft.getId());
			result = pstmt2.executeUpdate();

			System.out.println("Record Updated"); //just for testing
		}catch(Exception ex) {
			//put error message here
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText("There is an error with the request");
			alert.setContentText("Ooops, there was an error! Please try again.");

			alert.showAndWait();
		}
	}

	public void PTEmployee(Department dept, PartTime pt, String file){
		try {
			connect = DriverManager.getConnection(URL, USER, PASSWORD);
			FileInputStream fis=new FileInputStream("C:\\Programming II\\Assignment 2 (2020)\\src"+file);
			sql = "INSERT INTO `part-time`(`id`, `name`, `total_working_hour`, `food_allowance`, `monthly_salary`, `image`) values (?,?,?,?,?,?)";
			PreparedStatement pstmt = connect.prepareStatement(sql);
			pstmt.setInt(1, pt.getId());
			pstmt.setString(2, pt.getName());
			pstmt.setInt(3, pt.getTotalWorkingHour());
			pstmt.setInt(4, pt.getFoodAllowance());
			pstmt.setInt(5, pt.getMonthlySalary());
			pstmt.setBlob(6, fis);
			result = pstmt.executeUpdate();

			connect = DriverManager.getConnection(URL, USER, PASSWORD);
			sql = "INSERT INTO `department`(`department`, `floor`, `Employee_Id`) values (?,?,?)";
			PreparedStatement pstmt2 = connect.prepareStatement(sql);
			pstmt2.setString(1, dept.getDept());
			pstmt2.setString(2, dept.getFloor());
			pstmt2.setInt(3, pt.getId());
			result = pstmt2.executeUpdate();

			System.out.println("Record Updated"); //just for testing
		}catch(Exception ex) {
			//put error message here
			System.out.println("Error");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText("There is an error with the request");
			alert.setContentText("Ooops, there was an error! Please try again.");

			alert.showAndWait();
		}
	}

	public boolean checkID(int id)throws SQLException {
		//step 1: establish connection
		connect = DriverManager.getConnection(URL, USER, PASSWORD);
		//step 2: create statement
		stmt = connect.createStatement();
		//step 3: execute sql
		sql = "SELECT * FROM `department` WHERE `Employee_Id` = "  + id ;
		rs  = stmt.executeQuery(sql);

		if(rs.next()) {
			return true;
		}
		else
			return false;	
	}

	public boolean checkIDFull(int id)throws SQLException{
		//step 1: establish connection
		connect = DriverManager.getConnection(URL, USER, PASSWORD);
		//step 2: create statement
		stmt = connect.createStatement();
		//step 3: execute sql
		sql = "SELECT * FROM `full-time` WHERE `id` = "  + id ;
		rs  = stmt.executeQuery(sql);

		if(rs.next()) {
			return true;
		}
		else
			return false;	
	}

	public boolean checkIDPart(int id)throws SQLException{
		//step 1: establish connection
		connect = DriverManager.getConnection(URL, USER, PASSWORD);
		//step 2: create statement
		stmt = connect.createStatement();
		//step 3: execute sql
		sql = "SELECT * FROM `part-time` WHERE `id` = "  + id ;
		rs  = stmt.executeQuery(sql);

		if(rs.next()) {
			return true;
		}
		else
			return false;	
	}

	public void viewEmployee(int id)throws SQLException{

		sql = "SELECT * FROM `department` WHERE `Employee_Id` = " + id;
		rs = stmt.executeQuery(sql);

	}

	public void viewFTEmployee(int id)throws SQLException{
		//	sql = "SELECT * FROM `full-time` WHERE `id` = " + id;
		sql = String.format("SELECT * FROM `full-time` WHERE `id` = '%d'", id);
		PreparedStatement ps=connect.prepareStatement(sql); 
		rs = ps.executeQuery();
	}



	public void viewPTEmployee(int id)throws SQLException{
		sql = "SELECT * FROM `part-time` WHERE `id` = " + id;
		rs = stmt.executeQuery(sql);
	}

	public InputStream retrieve (int id) throws SQLException {	
		InputStream is = null;
		if(checkIDFull(id)) {	
			sql = String.format("SELECT * FROM `full-time` WHERE `id` = '%d'", id);
		}
		else if(checkIDPart(id)) {
			sql = String.format("SELECT * FROM `part-time` WHERE `id` = '%d'", id);
		}
		try {
			PreparedStatement ps = connect.prepareStatement(sql); 
			rs = ps.executeQuery();
			while(rs.next()){
				is = rs.getBinaryStream("image");
			}
			System.out.println("done2");
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return is;
	}

	public void deleteEmployee(int id) throws SQLException {
		//step 1: establish connection
		connect = DriverManager.getConnection(URL, USER, PASSWORD);
		//step 2: create statement
		stmt = connect.createStatement();
		//step 3: execute sql
		sql = "DELETE FROM `department` WHERE `Employee_Id` = " + id;
		result = stmt.executeUpdate(sql);

		sql = "DELETE FROM `full-time` WHERE `id` = " + id;
		result = stmt.executeUpdate(sql);

		sql = "DELETE FROM `part-time` WHERE `id` = " + id;
		result = stmt.executeUpdate(sql);
	}
	
	public void modifyEmployeeFT(Department dept, FullTime ft, String file) {
		try {
			connect = DriverManager.getConnection(URL, USER, PASSWORD);
			FileInputStream fis=new FileInputStream("C:\\Programming II\\Assignment 2 (2020)\\src"+file);
			
			sql = "UPDATE `full-time` SET `name` = ?, `wages` = ?, `comm` = ?, `totalSalary` = ?, `image` = ? WHERE `Id` = ? ";
			PreparedStatement pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, ft.getName());
			pstmt.setInt(2, ft.getWages());
			pstmt.setInt(3, ft.getComm());
			pstmt.setInt(4, ft.getTotalSalary());
			pstmt.setBlob(5, fis);
			pstmt.setInt(6, ft.getId());
			result = pstmt.executeUpdate();
			
			sql = "UPDATE `department` SET `department` = ?, `floor` = ? WHERE `Employee_Id` = ? ";
			PreparedStatement pstmt2 = connect.prepareStatement(sql);
			pstmt2.setString(1, dept.getDept());
			pstmt2.setString(2, dept.getFloor());
			pstmt2.setInt(3, ft.getId());
			result = pstmt2.executeUpdate();
		}catch(Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText("There is an error with the request");
			alert.setContentText("Ooops, there was an error! Please try again.");

			alert.showAndWait();
		}
	}
	
	public void modifyEmployeePT(Department dept, PartTime pt, String file) {
		try {
			connect = DriverManager.getConnection(URL, USER, PASSWORD);
			FileInputStream fis=new FileInputStream("C:\\Programming II\\Assignment 2 (2020)\\src"+file);
			
			sql = "UPDATE `part-time` SET `name` = ?, `total_working_hour` = ?, `food_allowance` = ?, `monthly_salary` = ?, `image` = ? WHERE `id` = ? ";
			PreparedStatement pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, pt.getName());
			pstmt.setInt(2, pt.getTotalWorkingHour());
			pstmt.setInt(3, pt.getFoodAllowance());
			pstmt.setInt(4, pt.getMonthlySalary());
			pstmt.setBlob(5, fis);
			pstmt.setInt(6, pt.getId());
			result = pstmt.executeUpdate();
			
			sql = "UPDATE `department` SET `department` = ?, `floor` = ? WHERE `Employee_Id` = ? ";
			PreparedStatement pstmt2 = connect.prepareStatement(sql);
			pstmt2.setString(1, dept.getDept());
			pstmt2.setString(2, dept.getFloor());
			pstmt2.setInt(3, pt.getId());
			result = pstmt2.executeUpdate();
		}catch(Exception ex) {
			//error messaage popup here
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText("There is an error with the request");
			alert.setContentText("Ooops, there was an error! Please try again.");

			alert.showAndWait();
		}
	}
	
	public void selectEmployeeID() throws SQLException {
		connect = DriverManager.getConnection(URL, USER, PASSWORD);
		sql = "SELECT * FROM `department` WHERE `Employee_Id`";
		PreparedStatement pstmt = connect.prepareStatement(sql);
		rs = pstmt.executeQuery();
	}
	
	public void selectFTEmployeeID()throws SQLException{
		connect = DriverManager.getConnection(URL, USER, PASSWORD);
		sql = "SELECT * FROM `full-time` WHERE `id`";
		PreparedStatement pstmt = connect.prepareStatement(sql);
		rs = pstmt.executeQuery();
	}
	
	public void selectPTEmployeeID()throws SQLException{
		connect = DriverManager.getConnection(URL, USER, PASSWORD);
		sql = "SELECT * FROM `part-time` WHERE `id`";
		PreparedStatement pstmt = connect.prepareStatement(sql);
		rs = pstmt.executeQuery();
	}

}


