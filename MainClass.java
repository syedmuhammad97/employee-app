import javafx.application.Application;
import javafx.stage.Stage;
import ui.MainMenu;

public class MainClass extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		new MainMenu(stage);
		
	}
	
	public static void main(String[] args) {
		launch();
	}

}
