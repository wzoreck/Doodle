package doodle.ihc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import doodle.bd.AlunoDAO;
import doodle.bd.ProfessorDAO;
import doodle.entidades.Aluno;
import doodle.entidades.Professor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastrarUsuarioFX extends Application {

	private Stage stage;
	private Pane pane;
	private TextField txtLogin;
	private PasswordField txtPasswd;
	private PasswordField txtVerificacaoPasswd;
	private TextField txtNome;
	private TextField txtEmail;
	private TextField txtDataNascimento;
	private TextField txtSalario;
	private TextField txtCargaHorariaSemanal;

	private CheckBox boolIsProfessor;
	private Button btnVoltar;
	private Button btnCadastrar;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;

		initComponentes();
		configLayout();

		Scene scene = new Scene(pane);
		btnCadastrar.requestFocus();

		stage.setScene(scene);
		stage.setTitle("Cadastro de novo usuário");
		stage.setResizable(true);
		stage.show();

	}

	private void initComponentes() {
		txtLogin = new TextField();
		txtLogin.setPromptText("Informe um nome de usuário");

		txtPasswd = new PasswordField();
		txtPasswd.setPromptText("Informe uma senha");

		txtVerificacaoPasswd = new PasswordField();
		txtVerificacaoPasswd.setPromptText("Confirme a senha");

		txtNome = new TextField();
		txtNome.setPromptText("Informe o nome da pessoa");

		txtEmail = new TextField();
		txtEmail.setPromptText("Informe o e-mail da pessoa");

		txtDataNascimento = new TextField();
		txtDataNascimento.setPromptText("Informe a data de nascimento da pessoa (dd/MM/yyyy)");

		txtSalario = new TextField();
		txtSalario.setPromptText("Informe qual será o salário da pessoa");

		txtCargaHorariaSemanal = new TextField();
		txtCargaHorariaSemanal.setPromptText("Informe qual será a carga horária da pessoa");

		boolIsProfessor = new CheckBox("Definir usuário como professor");

		btnCadastrar = new Button("Cadastrar");
		btnCadastrar.setOnAction(cadastrar());

		btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(voltar());

		pane = new AnchorPane();
		pane.setPrefSize(600, 352);
		pane.getChildren().addAll(txtNome, txtEmail, txtDataNascimento, boolIsProfessor, txtSalario,
				txtCargaHorariaSemanal, txtLogin, txtPasswd, txtVerificacaoPasswd, btnCadastrar, btnVoltar);

	}

	private void configLayout() {
		txtNome.setLayoutX(10);
		txtNome.setLayoutY(10);
		txtNome.setPrefHeight(30);
		txtNome.setPrefWidth(pane.getPrefWidth() - 20);

		txtEmail.setLayoutX(10);
		txtEmail.setLayoutY(50);
		txtEmail.setPrefHeight(30);
		txtEmail.setPrefWidth(pane.getPrefWidth() - 20);

		txtDataNascimento.setLayoutX(10);
		txtDataNascimento.setLayoutY(90);
		txtDataNascimento.setPrefHeight(30);
		txtDataNascimento.setPrefWidth(pane.getPrefWidth() - 20);

		boolIsProfessor.setLayoutX(10);
		boolIsProfessor.setLayoutY(130);

		txtSalario.setLayoutX(10);
		txtSalario.setLayoutY(157);
		txtSalario.setPrefHeight(30);
		txtSalario.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		txtCargaHorariaSemanal.setLayoutX(txtSalario.getPrefWidth() + 20);
		txtCargaHorariaSemanal.setLayoutY(157);
		txtCargaHorariaSemanal.setPrefHeight(30);
		txtCargaHorariaSemanal.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		txtLogin.setLayoutX(10);
		txtLogin.setLayoutY(197);
		txtLogin.setPrefHeight(30);
		txtLogin.setPrefWidth(pane.getPrefWidth() - 20);

		txtPasswd.setLayoutX(10);
		txtPasswd.setLayoutY(237);
		txtPasswd.setPrefHeight(30);
		txtPasswd.setPrefWidth(pane.getPrefWidth() - 20);

		txtVerificacaoPasswd.setLayoutX(10);
		txtVerificacaoPasswd.setLayoutY(277);
		txtVerificacaoPasswd.setPrefHeight(30);
		txtVerificacaoPasswd.setPrefWidth(pane.getPrefWidth() - 20);

		btnCadastrar.setLayoutX(10);
		btnCadastrar.setLayoutY(317);
		btnCadastrar.setPrefHeight(20);
		btnCadastrar.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		btnVoltar.setLayoutX(btnCadastrar.getPrefWidth() + 20);
		btnVoltar.setLayoutY(317);
		btnVoltar.setPrefHeight(20);
		btnVoltar.setPrefWidth((pane.getPrefWidth() - 30) / 2);
	}

	private EventHandler<ActionEvent> voltar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				abrirJanelaLogin();
			}
		};
	}

	private EventHandler<ActionEvent> cadastrar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtNome.getText().isBlank()) {
					AlertaFX.alerta("Nome em branco!");
					return;
				}
				if (txtDataNascimento.getText().isBlank()) {
					AlertaFX.alerta("Data de nascimento em branco!");
					return;
				}
				if (txtLogin.getText().isBlank()) {
					AlertaFX.alerta("Login em branco!");
					return;
				}
				if (txtEmail.getText().isBlank()) {
					AlertaFX.alerta("E-mail em branco!");
					return;
				}
				if (txtPasswd.getText().isBlank()) {
					AlertaFX.alerta("Senha em branco!");
					return;
				}
				if (txtVerificacaoPasswd.getText().isBlank()) {
					AlertaFX.alerta("Confirmação da senha em branco!");
					return;
				}
				if (!txtPasswd.getText().contentEquals(txtVerificacaoPasswd.getText())) {
					AlertaFX.alerta("Confirmação da senha difere da senha!");
					return;
				}

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				ArrayList<Integer> id = new ArrayList<Integer>();

				if (boolIsProfessor.isSelected()) {
					
					if (txtSalario.getText().isBlank()) {
						AlertaFX.alerta("Salario em branco!");
						return;
					}
					if (txtCargaHorariaSemanal.getText().isBlank()) {
						AlertaFX.alerta("Carga horária semanal em branco!");
						return;
					}
					
					try {
						new ProfessorDAO().adicionar(new Professor(txtNome.getText(), txtEmail.getText(),
								sdf.parse(txtDataNascimento.getText()), txtLogin.getText(), txtPasswd.getText(),
								Float.valueOf(txtSalario.getText()), Integer.valueOf(txtCargaHorariaSemanal.getText()),
								true), id);
					} catch (NumberFormatException | ParseException e) {
						AlertaFX.erro(
								"O formato da data de nascimento informada não corresposnde com o padrão do sistema! Favor informar dia/mês/ano");
					}
					return;
				}

				try {
					new AlunoDAO().adicionar(new Aluno(txtNome.getText(), txtEmail.getText(),
							sdf.parse(txtDataNascimento.getText()), txtLogin.getText(), txtPasswd.getText(), true), id);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				AlertaFX.info("Usuário cadastrado com sucesso");

				abrirJanelaLogin();
			}
		};
	}

	private void abrirJanelaLogin() {
		try {
			new LoginFX().start(stage);
		} catch (Exception e) {
			AlertaFX.erro("Não foi possível iniciar a tela de login!");
		}
	}

}
