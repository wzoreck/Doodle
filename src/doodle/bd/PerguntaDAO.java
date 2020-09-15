package doodle.bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doodle.forum.Pergunta;

public class PerguntaDAO implements InterfaceDAO<Pergunta> {

	@Override
	public void adicionar(Pergunta pergunta, ArrayList<Integer> id) {
		// id_pergunta, id_forum, id_autor, titulo, duvida, data
		try {
			String queryPergunta = "INSERT INTO pergunta_forum VALUES (" + pergunta.getIDPergunta() + ", " + pergunta.getIDForum()
					+ "," + pergunta.getObjetoAutor().getId() + ", '" + pergunta.getTitulo() + "', '" + pergunta.getDuvida() + "', '"
					+ pergunta.getData() + "')";

			UtilBD.alterarBd(queryPergunta);

		} catch (SQLException e1) {
			System.err.println("Falha ao inserir Pergunta no banco de dados");
		}
	}

	@Override
	public List<Pergunta> listar(int aux) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Pergunta referencia, int aux) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Pergunta pergunta) {
		try {
			String queryDeletePergunta = "DELETE FROM pergunta_forum WHERE id_pergunta = " + pergunta.getIDPergunta();
			UtilBD.alterarBd(queryDeletePergunta);
		} catch (SQLException e) {
			System.err.println("Falha ao remover Pergunta do banco de dados");
		}
	}

}
