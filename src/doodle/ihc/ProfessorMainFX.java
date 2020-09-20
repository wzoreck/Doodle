package doodle.ihc;

import java.util.ArrayList;
import java.util.List;

import doodle.bd.CursoDAO;
import doodle.entidades.Curso;
import doodle.entidades.Professor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProfessorMainFX extends Application {

	private Stage stage;
	private Pane pane;
	private String usuarioLogado;
	private Button btnVoltar;
	private Button btnCadastrarCurso;
	private Button btnAcessarCurso;
	private Label lblCurso;
	private ComboBox<String> cmbCursos;

	private Professor professor;
	private Curso curso;

	public ProfessorMainFX(Professor professor) {
		if (professor == null)
			this.usuarioLogado = "Erro - nome de usuário em branco";
		this.usuarioLogado = professor.getNome();
		this.professor = professor;
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;

		initComponentes();
		configLayout();

		Scene scene = new Scene(pane);
		cmbCursos.requestFocus();

		stage.setScene(scene);
		stage.setTitle("Doodle de " + usuarioLogado);
		stage.setResizable(true);
		stage.show();

	}

	private void initComponentes() {
		
		lblCurso = new Label("Curso:");
		cmbCursos = new ComboBox<>();
		cmbCursos.setItems(FXCollections.observableArrayList(geraListaCursos()));
		cmbCursos.getSelectionModel().selectFirst();

		btnCadastrarCurso = new Button("Cadastrar novo curso");
		btnCadastrarCurso.setOnAction(cadastrarCurso());

		btnAcessarCurso = new Button("Acessar curso");
		btnAcessarCurso.setOnAction(acessarCurso());

		btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(voltar());

		pane = new AnchorPane();
		pane.getChildren().addAll(lblCurso, cmbCursos, btnCadastrarCurso, btnAcessarCurso, btnVoltar);

	}

	private void configLayout() {
		pane.setPrefSize(640, 115);

		lblCurso.setLayoutX(10);
		lblCurso.setLayoutY(10);
		
		cmbCursos.setLayoutX(10);
		cmbCursos.setLayoutY(35);
		cmbCursos.setPrefHeight(30);
		cmbCursos.setPrefWidth(pane.getPrefWidth() - 20);

		btnCadastrarCurso.setLayoutX(pane.getPrefWidth() - 430);
		btnCadastrarCurso.setLayoutY(pane.getPrefHeight() - 35);
		btnCadastrarCurso.setPrefHeight(20);
		btnCadastrarCurso.setPrefWidth(150);

		btnAcessarCurso.setLayoutX(pane.getPrefWidth() - 270);
		btnAcessarCurso.setLayoutY(pane.getPrefHeight() - 35);
		btnAcessarCurso.setPrefHeight(20);
		btnAcessarCurso.setPrefWidth(150);

		btnVoltar.setLayoutX(pane.getPrefWidth() - 110);
		btnVoltar.setLayoutY(pane.getPrefHeight() - 35);
		btnVoltar.setPrefHeight(20);
		btnVoltar.setPrefWidth(100);
	}

	private List<String> geraListaCursos() {
		List<String> retorno = new ArrayList<String>();
		List<Curso> cursos = new CursoDAO().listar(professor.getId());
		for (Curso curso : cursos)
			retorno.add(curso.getNome());
		return retorno;
	}

	private EventHandler<ActionEvent> cadastrarCurso() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new CadastrarCursoFX(professor).start(stage);
				} catch (Exception e) {
					AlertaFX.erro("Não foi possível iniciar a tela de cadastro de um curso!");
				}
			}
		};
	}	
	
	private EventHandler<ActionEvent> acessarCurso() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new CursoFX(professor, getCurso()).start(stage);
				} catch (Exception e) {
					AlertaFX.erro("Não foi possível iniciar a tela do curso!");
				}
			}
		};
	}	
	
	private EventHandler<ActionEvent> voltar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new LoginFX().start(stage);
				} catch (Exception e) {
					AlertaFX.erro("Não foi possível iniciar a tela de login");
				}
			}
		};
	}

	private Curso getCurso() {
		CursoDAO cursoDAO = new CursoDAO();
		curso = cursoDAO.get(this.cmbCursos.getSelectionModel().getSelectedItem());
		return curso;
	}
}
