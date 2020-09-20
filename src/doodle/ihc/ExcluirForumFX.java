package doodle.ihc;

import java.util.ArrayList;
import java.util.List;

import doodle.bd.ForumDAO;
import doodle.entidades.Curso;
import doodle.entidades.Professor;
import doodle.forum.Forum;
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

public class ExcluirForumFX extends Application {

	private Stage stage;
	private Pane pane;
	private ComboBox<String> cmbForuns;
	private Label lblForuns;
	private Button btnVoltar;
	private Button btnExcluir;

	private List<Forum> foruns;
	private Professor professor;
	private Curso curso;

	Forum forum;

	public ExcluirForumFX(List<Forum> foruns, Professor professor, Curso curso) {
		this.foruns = foruns;
		this.professor = professor;
		this.curso = curso;
	}

	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;

		initComponentes();

		configLayout();

		Scene scene = new Scene(pane);
		btnVoltar.requestFocus();

		stage.setScene(scene);
		stage.setTitle("Excluir Forum");
		stage.setResizable(true);
		stage.show();
	}

	private void initComponentes() {

		lblForuns = new Label("Forum:");
		cmbForuns = new ComboBox<>();
		cmbForuns.setItems(FXCollections.observableArrayList(geraListaForuns()));
		cmbForuns.getSelectionModel().selectFirst();

		btnExcluir = new Button("Excluir Forum");
		btnExcluir.setOnAction(excluirForum());

		btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(voltar());

		pane = new AnchorPane();
		pane.getChildren().addAll(lblForuns, cmbForuns, btnExcluir, btnVoltar);

	}

	private void configLayout() {
		pane.setPrefSize(640, 115);

		lblForuns.setLayoutX(10);
		lblForuns.setLayoutY(10);

		cmbForuns.setLayoutX(10);
		cmbForuns.setLayoutY(35);
		cmbForuns.setPrefHeight(30);
		cmbForuns.setPrefWidth(pane.getPrefWidth() - 20);

		btnExcluir.setLayoutX(pane.getPrefWidth() - 270);
		btnExcluir.setLayoutY(pane.getPrefHeight() - 35);
		btnExcluir.setPrefHeight(20);
		btnExcluir.setPrefWidth(150);

		btnVoltar.setLayoutX(pane.getPrefWidth() - 110);
		btnVoltar.setLayoutY(pane.getPrefHeight() - 35);
		btnVoltar.setPrefHeight(20);
		btnVoltar.setPrefWidth(100);
	}

	private List<String> geraListaForuns() {
		List<String> retorno = new ArrayList<String>();
		for (Forum forum : foruns)
			retorno.add(forum.getTitulo());
		return retorno;
	}

	private EventHandler<ActionEvent> excluirForum() {
		for (Forum forum : foruns) {
			if (forum.getTitulo().contentEquals(cmbForuns.getSelectionModel().getSelectedItem())) {
				ForumDAO forumDAO = new ForumDAO();
				forumDAO.remover(forum);
			}
		}
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					for (Forum forum : foruns) {
						if (forum.getTitulo().contentEquals(cmbForuns.getSelectionModel().getSelectedItem())) {
							ForumDAO forumDAO = new ForumDAO();
							forumDAO.remover(forum);
							break;
						}
					}

					AlertaFX.info("Forum removido com sucesso");
					voltar();

				} catch (Exception e) {
					AlertaFX.erro("Não foi possível remover o forum");
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
					AlertaFX.erro("Não foi possível iniciar a tela de login");
				}
			}
		};
	}

}
