package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ui.MainMenu;
import ui.insertFullTime;
import ui.insertPartTime;

public class ChooseCategoryController {

    @FXML
    private Button fulltime;

    @FXML
    private Button partTime;

    @FXML
    private Button back;

    @FXML
    void backButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new MainMenu(stage);

    }

    @FXML
    void fulltimeButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new insertFullTime(stage);
    }

    @FXML
    void partTimeButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new insertPartTime(stage);

    }

}
