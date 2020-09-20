package doodle.ihc;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import doodle.bd.ForumDAO;
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

public class AlterarForumFX extends Application {

	private Stage stage;
	private Pane pane;
	private TextField txtTitulo;
	private TextField txtDescricao;
	private TextField txtDataPublicacao;
	private TextField txtDataTermino;
	private CheckBox boolIsAberto;
	private Button btnVoltar;
	private Button btnAlterar;

	private Curso curso = null;
	private Professor professor = null;
	private Forum forum = null;

	public AlterarForumFX(Professor professor, Curso curso, Forum forum) {
		this.curso = curso;
		this.professor = professor;
		this.forum = forum;
	}

	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;

		initComponentes();

		configLayout();

		Scene scene = new Scene(pane);
		btnVoltar.requestFocus();

		stage.setScene(scene);
		stage.setTitle("Alterar Forum");
		stage.setResizable(true);
		stage.show();
	}

	private void initComponentes() {
		txtTitulo = new TextField();
		txtTitulo.setPromptText("Título do forum");
		txtTitulo.setText(forum.getTitulo());

		txtDescricao = new TextField();
		txtDescricao.setPromptText("Descrição");
		txtDescricao.setText(forum.getDescricao());

		txtDataPublicacao = new TextField();
		txtDataPublicacao.setPromptText("Data de início (dd/MM/yyyy)");
		txtDataPublicacao.setText(forum.getDataPublicacao());

		txtDataTermino = new TextField();
		txtDataTermino.setPromptText("Data de término (dd/MM/yyyy)");
		txtDataTermino.setText(forum.getDataTermino());

		boolIsAberto = new CheckBox("Definir como aberto após o cadastro");
		boolIsAberto.setSelected(forum.isAberto());

		btnAlterar = new Button("Alterar");
		btnAlterar.setOnAction(alterar());

		btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(voltar());

		pane = new AnchorPane();
		pane.getChildren().addAll(txtTitulo, txtDescricao, txtDataPublicacao, txtDataTermino, boolIsAberto, btnAlterar,
				btnVoltar);

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

		btnAlterar.setLayoutX(10);
		btnAlterar.setLayoutY(210);
		btnAlterar.setPrefHeight(20);
		btnAlterar.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		btnVoltar.setLayoutX(btnAlterar.getPrefWidth() + 20);
		btnVoltar.setLayoutY(210);
		btnVoltar.setPrefHeight(20);
		btnVoltar.setPrefWidth((pane.getPrefWidth() - 30) / 2);
	}

	private EventHandler<ActionEvent> alterar() {
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

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				forum.setTitulo(txtTitulo.getText());
				forum.setDescricao(txtDescricao.getText());
				
				try {
					forum.setDataTermino(sdf.parse(txtDataTermino.getText()));
				} catch (ParseException e1) {
					AlertaFX.erro("O formato de data informado é incompatível com o sistema!");
					return;
				}
				
				try {
					forum.setDataPublicacao(sdf.parse(txtDataPublicacao.getText()));
				} catch (ParseException e1) {
					AlertaFX.erro("O formato de data informado é incompatível com o sistema!");
					return;
				}
				
				forum.setAberto(boolIsAberto.isSelected());

				try {
					forum.setDataTermino(sdf.parse(txtDataTermino.getText()));
					forum.setAberto(boolIsAberto.isSelected());

					ForumDAO forumDAO = new ForumDAO();
					forumDAO.atualizar(forum, 0);

					AlertaFX.info("Forum alterado com sucesso");
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
