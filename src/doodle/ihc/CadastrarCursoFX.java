package doodle.ihc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import doodle.bd.CursoDAO;
import doodle.entidades.Curso;
import doodle.entidades.Professor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastrarCursoFX extends Application {

	private Stage stage;
	private Pane pane;
	private TextField txtNomeCurso;
	private TextField txtDataInicio;
	private Professor professor;
	private Button btnVoltar;
	private Button btnCadastrar;
	
	public CadastrarCursoFX(Professor professor) {
		this.professor = professor;
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;

		initComponentes();

		configLayout();

		Scene scene = new Scene(pane);
		btnVoltar.requestFocus();

		stage.setScene(scene);
		stage.setTitle("Registro de um novo jogo");
		stage.setResizable(true);
		stage.show();
	}

	private void initComponentes() {
		txtNomeCurso = new TextField();
		txtNomeCurso.setPromptText("Nome do curso");

		txtDataInicio = new TextField();
		txtDataInicio.setPromptText("Data inicio (dd/MM/yyyy)");

		btnCadastrar = new Button("Cadastrar");
		btnCadastrar.setOnAction(cadastrar());

		btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(voltar());

		pane = new AnchorPane();
		pane.getChildren().addAll(txtNomeCurso, txtDataInicio, btnCadastrar, btnVoltar);

	}

	private void configLayout() {
		pane.setPrefSize(320, 125);
		txtNomeCurso.setLayoutX(10);
		txtNomeCurso.setLayoutY(10);
		txtNomeCurso.setPrefHeight(30);
		txtNomeCurso.setPrefWidth(pane.getPrefWidth() - 20);

		txtDataInicio.setLayoutX(10);
		txtDataInicio.setLayoutY(50);
		txtDataInicio.setPrefHeight(30);
		txtDataInicio.setPrefWidth(pane.getPrefWidth() - 20);

		btnCadastrar.setLayoutX(10);
		btnCadastrar.setLayoutY(90);
		btnCadastrar.setPrefHeight(20);
		btnCadastrar.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		btnVoltar.setLayoutX(btnCadastrar.getPrefWidth() + 20);
		btnVoltar.setLayoutY(90);
		btnVoltar.setPrefHeight(20);
		btnVoltar.setPrefWidth((pane.getPrefWidth() - 30) / 2);
	}
	
	private EventHandler<ActionEvent> cadastrar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtNomeCurso.getText().isBlank()) {
					AlertaFX.alerta("Nome do curso em branco!");
					return;
				}
				if (txtDataInicio.getText().isBlank()) {
					AlertaFX.alerta("Data de incío em branco!");
					return;
				}

				CursoDAO cursoDAO = new CursoDAO();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				ArrayList<Integer> id = new ArrayList<Integer>();
				id.add(professor.getId());
				
				try {
					cursoDAO.adicionar(new Curso(professor, txtNomeCurso.getText(), sdf.parse(txtDataInicio.getText())), id);
				} catch (ParseException e) {
					AlertaFX.erro("O formato da data informada é incompatível com o sistema!");
				}

				AlertaFX.info("Curso cadastrado com sucesso");

				abrirJanelaPrincipal();
			}
		};
	}
	
	private EventHandler<ActionEvent> voltar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				abrirJanelaPrincipal();
			}
		};
	}
	
	private void abrirJanelaPrincipal() {
		try {
			new ProfessorMainFX(professor).start(stage);
		} catch (Exception e) {
			AlertaFX.erro("Não foi possível iniciar a tela principal!");
		}
	}
}
