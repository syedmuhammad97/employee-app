package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.ChooseCategory;
import ui.ChooseModifyCategory;
import ui.ChooseTableCategory;
import ui.Delete;
import ui.EmployeeMediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Node;


public class MainMenuController {

    @FXML
    private Button register;

    @FXML
    private Button modify;

    @FXML
    private Button delete;

    @FXML
    private Button display;

    @FXML
    private Button exit;
    
    @FXML
    private Button btMedia;

    @FXML
    void deleteButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new Delete(stage);

    }

    @FXML
    void displayButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new ChooseTableCategory(stage);
    }

    @FXML
    void exitButton(ActionEvent event) {
    	System.exit(0);

    }

    @FXML
    void modifyButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new ChooseModifyCategory(stage);
    }

    @FXML
    void registerButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new ChooseCategory(stage);

    }
    
    @FXML
    void mediaButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new EmployeeMediaPlayer(stage);
    }
    
    

}
