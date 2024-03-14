package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ui.FullTimeModification;
import ui.MainMenu;
import ui.PartTimeModification;

public class ChooseModifyCategoryController {

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
    	new FullTimeModification(stage);
    }

    @FXML
    void partTimeButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new PartTimeModification(stage);
    }

}