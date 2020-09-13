package doodle.bd;

import java.sql.SQLException;
import java.util.List;

import doodle.forum.Forum;
import doodle.forum.Pergunta;

public class ForumDAO implements InterfaceDAO<Forum> {

	@Override
	public void adicionar(Forum forum) {
		try {
			String queryForum = "INSERT INTO forum VALUES (NULL," + "(SELECT id_conteudo FROM conteudo WHERE titulo = '"
					+ forum.getTitulo() + "'), " + forum.isAberto() + ")";

			UtilBD.alterarBd(queryForum);

		} catch (SQLException e1) {
			System.err.println("Falha ao inserir Forum no banco de dados");
		}
		try {
			for (Pergunta pergunta : forum.getPerguntas()) {
				String queryPergunta = "INSERT INTO pergunta_forum VALUES (NULL,"
						+ "(SELECT id_forum FROM forum INNER JOIN conteudo ON forum.id_conteudo = conteudo.id_conteudo WHERE titulo = '"
						+ forum.getTitulo() + "')," + " (SELECT id_pessoa FROM pessoa WHERE login = '"
						+ pergunta.getObjetoAutor().getLogin() + "'), '" + pergunta.getTitulo() + "', '"
						+ pergunta.getDuvida() + "', '" + pergunta.getData() + "')";

				UtilBD.alterarBd(queryPergunta);
			}
		} catch (SQLException e2) {
			System.err.println("Falha ao inserir Perguntas no banco de dados");
		}

	}

	@Override
	public List<Forum> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Forum referencia) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Forum referencia) {
		// TODO Auto-generated method stub

	}

}
