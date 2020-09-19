package doodle.ihc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastrarUsuarioFX extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new AnchorPane();
		pane.setPrefSize(1920, 1080);
		
		Scene scene = new Scene(pane);
		
		stage.setScene(scene);
		stage.setTitle("Cadastro de novo usuário");
		stage.setResizable(false);
		stage.show();
		
	}

}
