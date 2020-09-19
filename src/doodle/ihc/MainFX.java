package doodle.ihc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainFX extends Application {

	private String usuarioLogado;

	public MainFX(String usuarioLogado) {
		if (usuarioLogado.isBlank())
			this.usuarioLogado = "Erro - nome de usuário em branco";
		this.usuarioLogado = usuarioLogado;
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new AnchorPane();
		pane.setPrefSize(640, 480);

		Scene scene = new Scene(pane);

		stage.setScene(scene);
		stage.setTitle("Doodle de " + usuarioLogado);
		stage.setResizable(false);
		stage.show();

	}

}
