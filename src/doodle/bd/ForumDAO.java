package doodle.bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doodle.forum.Forum;

public class ForumDAO implements InterfaceDAO<Forum> {

	@Override
	public void adicionar(Forum forum, ArrayList<Integer> id) {
		// id_forum, id_conteudo (id-0), aberto
		try {
			String queryForum = "INSERT INTO forum VALUES (NULL, " + id.get(0) + "), " + forum.isAberto() + ")";

			UtilBD.alterarBd(queryForum);

		} catch (SQLException e1) {
			System.err.println("Falha ao inserir Forum no banco de dados");
		}
	}

	@Override
	public List<Forum> listar(int aux) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Forum referencia, int aux) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Forum referencia) {
		// TODO Auto-generated method stub

	}

}
