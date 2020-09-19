package doodle.ihc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

		Button btnNovoUsuario = new Button("Criar novo usuário");

		// Painel
		Pane pane = new Pane();
		pane.setPrefSize(320, 180);

		// Adicionando os componentes no painel
		pane.getChildren().addAll(lblDoodle, txtUsuario, txtPasswd, btnEntrar, btnNovoUsuario);

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
		txtPasswd.setPrefWidth(pane.getPrefWidth() - 20);

		btnEntrar.setLayoutX(10);
		btnEntrar.setLayoutY(115);
		btnEntrar.setPrefHeight(20);
		btnEntrar.setPrefWidth(pane.getPrefWidth() - 20);

		btnNovoUsuario.setLayoutX(10);
		btnNovoUsuario.setLayoutY(145);
		btnNovoUsuario.setPrefHeight(20);
		btnNovoUsuario.setPrefWidth(pane.getPrefWidth() - 20);

		// Cena
		Scene scene = new Scene(pane);
		btnEntrar.requestFocus();

		// Palco
		stage.setScene(scene);
		stage.setTitle("Doodle login");
		stage.setResizable(false);
		stage.show();

		// Comportamento
		btnNovoUsuario.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					new CadastrarUsuarioFX().start(new Stage());
				} catch (Exception e) {
					System.err.println("Não foi possível iniciar a tela de cadastro de usuário");
				}

			}
		});
		
		btnEntrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					new MainFX(txtUsuario.getText()).start(stage);
				} catch (Exception e) {
					System.err.println("Não foi possível iniciar a tela principal");
				}
				
			}
			
			
		});

	}

}
