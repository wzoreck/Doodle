package doodle.ihc;

import java.util.ArrayList;

import doodle.bd.ProfessorDAO;
import doodle.entidades.Pessoa;
import doodle.entidades.Professor;
import javafx.application.Application;
import javafx.application.Platform;
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

	private Pane pane;
	private Label lblDoodle;
	private TextField txtUsuario;
	private PasswordField txtPasswd;
	private Button btnEntrar;
	private Button btnSair;
	private Button btnNovoUsuario;
	private Stage stage;

	private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	private ArrayList<Professor> professores = new ArrayList<Professor>();
	private Professor professor = null;
	private ProfessorDAO professorDAO = new ProfessorDAO();

	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;

		buscarUsuariosBD();
		initComponentes();
		configLayout();

		// Cena
		Scene scene = new Scene(pane);
		btnEntrar.requestFocus();

		// Palco
		stage.setScene(scene);
		stage.setTitle("Doodle login");
		stage.setResizable(false);
		stage.show();
	}

	public void initComponentes() {
		lblDoodle = new Label("Bem-Vindo ao Doodle");

		txtUsuario = new TextField();
		txtUsuario.setPromptText("Nome de usuário");

		txtPasswd = new PasswordField();
		txtPasswd.setPromptText("Senha");

		btnEntrar = new Button("Entrar");
		btnEntrar.setOnAction(entrar());

		btnSair = new Button("Sair");
		btnSair.setOnAction(sair());

		btnNovoUsuario = new Button("Criar novo usuário");
		btnNovoUsuario.setOnAction(novoUsuario());

		// Painel
		pane = new Pane();
		pane.setPrefSize(320, 180);

		// Adicionando os componentes no painel
		pane.getChildren().addAll(lblDoodle, txtUsuario, txtPasswd, btnEntrar, btnSair, btnNovoUsuario);
	}

	public void configLayout() {
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
		btnEntrar.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		btnSair.setLayoutX(btnEntrar.getPrefWidth() + 20);
		btnSair.setLayoutY(115);
		btnSair.setPrefHeight(20);
		btnSair.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		btnNovoUsuario.setLayoutX(10);
		btnNovoUsuario.setLayoutY(145);
		btnNovoUsuario.setPrefHeight(20);
		btnNovoUsuario.setPrefWidth(pane.getPrefWidth() - 20);
	}

	// Comportamentos
	private EventHandler<ActionEvent> entrar() {
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if (txtUsuario.getText().isBlank()) {
						AlertaFX.alerta("Campo usuário em branco");
						return;
					}

					if (txtPasswd.getText().isBlank()) {
						AlertaFX.alerta("Campo senha em branco");
						return;
					}

					for (Pessoa pessoa : pessoas) {
						if (pessoa.getLogin().contentEquals(txtUsuario.getText())) {
							if (pessoa.getPasswd().contentEquals(txtPasswd.getText())) {
								
								if (pessoa.getTipoPessoa().contentEquals("professor")) {
									professor = (Professor) pessoa;
									new ProfessorMainFX(professor).start(stage);
									break;
								}
								
							}
						}
					}

				} catch (Exception e) {
					AlertaFX.erro("Não foi possível iniciar a tela principal");
				}

			}
		};
	}

	private EventHandler<ActionEvent> sair() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		};
	}

	private EventHandler<ActionEvent> novoUsuario() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new CadastrarUsuarioFX().start(stage);
				} catch (Exception e) {
					AlertaFX.erro("Não foi passível iniciar a tela de cadastro de novo usuário");
				}
			}
		};
	}

	private void buscarUsuariosBD() {
		professores = professorDAO.listar(0);
		pessoas = new ArrayList<Pessoa>();
		for (Professor p : professores)
			pessoas.add(p);
	}

}
