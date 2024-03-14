package ui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Delete {
	
	FXMLLoader loader;
	Stage stage;
	Scene scene;
	String fileName = "DeleteEmployee.fxml";
	public Delete(Stage stage) {
		try {	
			//FXMLLoader to load the .fxml file
			loader = new FXMLLoader(this.getClass().getResource(fileName));
			//instantiate the stage
			//stage = new Stage();
			this.stage = stage;
			//instantiate the scene + load() from FXMLLoader
			scene = new Scene (loader.load());
			
			//add scene to stage
			stage.setScene(scene);
			//display stage
			stage.show();

		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
