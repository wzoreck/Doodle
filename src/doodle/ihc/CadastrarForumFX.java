package doodle.ihc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import doodle.bd.CursoDAO;
import doodle.bd.ForumDAO;
import doodle.entidades.Conteudo;
import doodle.entidades.Curso;
import doodle.entidades.Professor;
import doodle.forum.Forum;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastrarForumFX extends Application {

	private Stage stage;
	private Pane pane;
	private TextField txtTitulo;
	private TextField txtDescricao;
	private TextField txtDataPublicacao;
	private TextField txtDataTermino;
	private CheckBox boolIsAberto;
	private Button btnVoltar;
	private Button btnCadastrar;

	private Curso curso;
	private Professor professor;

	public CadastrarForumFX(Professor professor, Curso curso) {
		this.curso = curso;
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
		stage.setTitle("Registro de novo Forum");
		stage.setResizable(true);
		stage.show();
	}

	private void initComponentes() {
		txtTitulo = new TextField();
		txtTitulo.setPromptText("Título do forum");

		txtDescricao = new TextField();
		txtDescricao.setPromptText("Descrição");

		txtDataPublicacao = new TextField();
		txtDataPublicacao.setPromptText("Data de início (dd/MM/yyyy)");

		txtDataTermino = new TextField();
		txtDataTermino.setPromptText("Data de término (dd/MM/yyyy)");

		boolIsAberto = new CheckBox("Definir como aberto após o cadastro");

		btnCadastrar = new Button("Cadastrar");
		btnCadastrar.setOnAction(cadastrar());

		btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(voltar());

		pane = new AnchorPane();
		pane.getChildren().addAll(txtTitulo, txtDescricao, txtDataPublicacao, txtDataTermino, boolIsAberto,
				btnCadastrar, btnVoltar);

	}

	private void configLayout() {
		pane.setPrefSize(320, 250);

		txtTitulo.setLayoutX(10);
		txtTitulo.setLayoutY(10);
		txtTitulo.setPrefHeight(30);
		txtTitulo.setPrefWidth(pane.getPrefWidth() - 20);

		txtDescricao.setLayoutX(10);
		txtDescricao.setLayoutY(50);
		txtDescricao.setPrefHeight(30);
		txtDescricao.setPrefWidth(pane.getPrefWidth() - 20);

		txtDataPublicacao.setLayoutX(10);
		txtDataPublicacao.setLayoutY(90);
		txtDataPublicacao.setPrefHeight(30);
		txtDataPublicacao.setPrefWidth(pane.getPrefWidth() - 20);

		txtDataTermino.setLayoutX(10);
		txtDataTermino.setLayoutY(130);
		txtDataTermino.setPrefHeight(30);
		txtDataTermino.setPrefWidth(pane.getPrefWidth() - 20);

		boolIsAberto.setLayoutX(10);
		boolIsAberto.setLayoutY(170);

		btnCadastrar.setLayoutX(10);
		btnCadastrar.setLayoutY(210);
		btnCadastrar.setPrefHeight(20);
		btnCadastrar.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		btnVoltar.setLayoutX(btnCadastrar.getPrefWidth() + 20);
		btnVoltar.setLayoutY(210);
		btnVoltar.setPrefHeight(20);
		btnVoltar.setPrefWidth((pane.getPrefWidth() - 30) / 2);
	}

	private EventHandler<ActionEvent> cadastrar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtTitulo.getText().isBlank()) {
					AlertaFX.alerta("Título em branco!");
					return;
				}
				if (txtDescricao.getText().isBlank()) {
					AlertaFX.alerta("descrição em branco!");
					return;
				}
				if (txtDataPublicacao.getText().isBlank()) {
					AlertaFX.alerta("Data de publicação em branco!");
					return;
				}
				if (txtDataTermino.getText().isBlank()) {
					AlertaFX.alerta("Data de término em branco!");
					return;
				}

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				ArrayList<Integer> id = new ArrayList<Integer>();

				try {
					Forum forum = new Forum(txtTitulo.getText(), txtDescricao.getText(),
							sdf.parse(txtDataPublicacao.getText()), true, true);

					forum.setDataTermino(sdf.parse(txtDataTermino.getText()));
					forum.setAberto(boolIsAberto.isSelected());

					id.add(forum.getId());

					CursoDAO cursoDAO = new CursoDAO();
					cursoDAO.adicinarConteudo((Conteudo) forum, curso.getID());

					ForumDAO forumDAO = new ForumDAO();
					forumDAO.adicionar(forum, id);

					AlertaFX.info("Forum cadastrado com sucesso");
					voltar();

				} catch (ParseException e) {
					AlertaFX.erro("O formato de data informado é incompatível com o sistema!");
				}

			}
		};
	}

	private EventHandler<ActionEvent> voltar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new CursoFX(professor, curso).start(stage);
				} catch (Exception e) {
					AlertaFX.erro("Não foi possível iniciar a tela do curso");
				}
			}
		};
	}

}
