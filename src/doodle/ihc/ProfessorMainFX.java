package doodle.ihc;

import java.util.ArrayList;
import java.util.List;

import doodle.bd.CursoDAO;
import doodle.entidades.Curso;
import doodle.entidades.Professor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProfessorMainFX extends Application {

	private Stage stage;
	private Pane pane;
	private String usuarioLogado;
	private Button btnSair;
	private Button btnCadastrarCurso;
	private Button btnAlterarCurso;
	private Button btnExcluirCurso;
	private ListView<String> listaCursos;

	private Professor professor;

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
		btnSair.requestFocus();

		stage.setScene(scene);
		stage.setTitle("Doodle de " + usuarioLogado);
		stage.setResizable(true);
		stage.show();

	}

	private void initComponentes() {
		listaCursos = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList(geraListaCursos());
		listaCursos.setItems(items);

		btnCadastrarCurso = new Button("Cadastrar curso");
		btnCadastrarCurso.setOnAction(cadastrarCurso());

		btnAlterarCurso = new Button("Alterar curso");
//		btnAlterarCurso.setOnAction();

		btnExcluirCurso = new Button("Excluir curso");
//		btnExcluirCurso.setOnAction();

		btnSair = new Button("Sair");
		btnSair.setOnAction(sair());

		pane = new AnchorPane();
		pane.getChildren().addAll(listaCursos, btnCadastrarCurso, btnAlterarCurso, btnExcluirCurso, btnSair);

	}

	private void configLayout() {
		pane.setPrefSize(640, 480);

		listaCursos.setLayoutX(10);
		listaCursos.setLayoutY(10);
		listaCursos.setPrefHeight(pane.getPrefHeight() - 55);
		listaCursos.setPrefWidth(pane.getPrefWidth() - 20);

		btnCadastrarCurso.setLayoutX(pane.getPrefWidth() - 590);
		btnCadastrarCurso.setLayoutY(pane.getPrefHeight() - 35);
		btnCadastrarCurso.setPrefHeight(20);
		btnCadastrarCurso.setPrefWidth(150);

		btnAlterarCurso.setLayoutX(pane.getPrefWidth() - 430);
		btnAlterarCurso.setLayoutY(pane.getPrefHeight() - 35);
		btnAlterarCurso.setPrefHeight(20);
		btnAlterarCurso.setPrefWidth(150);

		btnExcluirCurso.setLayoutX(pane.getPrefWidth() - 270);
		btnExcluirCurso.setLayoutY(pane.getPrefHeight() - 35);
		btnExcluirCurso.setPrefHeight(20);
		btnExcluirCurso.setPrefWidth(150);

		btnSair.setLayoutX(pane.getPrefWidth() - 110);
		btnSair.setLayoutY(pane.getPrefHeight() - 35);
		btnSair.setPrefHeight(20);
		btnSair.setPrefWidth(100);
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
	
	
	private EventHandler<ActionEvent> sair() {
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

}
