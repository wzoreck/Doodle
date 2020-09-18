package doodle.ihc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginFX extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		// Componentes
		Label lblDoodle = new Label("Bem-Vindo ao Doodle");

		TextField txtUsuario = new TextField();
		txtUsuario.setPromptText("Nome de usuário");

		PasswordField txtPasswd = new PasswordField();
		txtPasswd.setPromptText("Senha");

		Button btnEntrar = new Button("Entrar");

		// Painel
		Pane pane = new Pane();
		pane.setPrefSize(320, 150);

		// Adicionando os componentes no painel
		pane.getChildren().addAll(lblDoodle, txtUsuario, txtPasswd, btnEntrar);

		// Configurar os componentes no painel
		lblDoodle.setLayoutX(10);
		lblDoodle.setLayoutY(10);

		txtUsuario.setLayoutX(10);
		txtUsuario.setLayoutY(35);
		txtUsuario.setPrefHeight(30);
		txtUsuario.setPrefWidth(pane.getPrefWidth() - 20);
		
		txtPasswd.setLayoutX(10);
		txtPasswd.setLayoutY(75);
		txtPasswd.setPrefHeight(30);
		txtPasswd.setPrefWidth(pane.getPrefWidth() -20);
		
		btnEntrar.setLayoutX(10);
		btnEntrar.setLayoutY(115);
		btnEntrar.setPrefHeight(20);
		btnEntrar.setPrefWidth(pane.getPrefWidth() - 20);
		
		
		// Cena
		Scene scene = new Scene(pane);
		btnEntrar.requestFocus();
		
		// Palco
		stage.setScene(scene);
		stage.setTitle("Doodle login");
		stage.setResizable(false);
		stage.show();
	}

}
