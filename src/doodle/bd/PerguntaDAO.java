package doodle.bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doodle.forum.Pergunta;

public class PerguntaDAO implements InterfaceDAO<Pergunta> {

	@Override
	public void adicionar(Pergunta pergunta, ArrayList<Integer> id) {
		// id_pergunta, id_forum (id-0), id_autor (id-1), titulo, duvida, data
		try {
			String queryPergunta = "INSERT INTO pergunta_forum VALUES (NULL," + id.get(0) + "," + id.get(1) + ", '"
					+ pergunta.getTitulo() + "', '" + pergunta.getDuvida() + "', '" + pergunta.getData() + "')";

			UtilBD.alterarBd(queryPergunta);

		} catch (SQLException e1) {
			System.err.println("Falha ao inserir Pergunta no banco de dados");
		}
	}

	@Override
	public List<Pergunta> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Pergunta referencia) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Pergunta referencia) {
		// TODO Auto-generated method stub

	}

}
