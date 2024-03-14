package controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.MainMenu;

public class EmployeeMediaPlayerController {
	
	MediaPlayer player;

    @FXML
    private MediaView video;

    @FXML
    private Button btBack;

    @FXML
    void backButton(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	new MainMenu(stage);
    }

    @FXML
    void playAction(ActionEvent event) {
    	player.play();
    }

    @FXML
    void rewindAction(ActionEvent event) {
    	player.seek(Duration.ZERO);
    }

    @FXML
    void stopAction(ActionEvent event) {
    	player.pause();
    }

    @FXML
    void videoAction(ActionEvent event) {
    	Stage stage = new Stage();
    	FileChooser chooser = new FileChooser();
    	chooser.setInitialDirectory(new File("C:\\Programming II\\Assignment 2 (2020)\\src\\video"));
    	chooser.setTitle("choose a video");
    	
    	File filePath = chooser.showOpenDialog(stage);
    	Media media = new Media(filePath.toURI().toString());
    	//MediaPlayer player = new MediaPlayer(media);
    	player = new MediaPlayer(media);
    	player.setAutoPlay(true);
    	video.setMediaPlayer(player);
    }

}
