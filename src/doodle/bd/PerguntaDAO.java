package doodle.bd;

import java.sql.SQLException;
import java.util.List;

import doodle.forum.Pergunta;

public class PerguntaDAO implements InterfaceDAO<Pergunta> {

	@Override
	public void adicionar(Pergunta pergunta) {
		try {
			System.out.println("Titulo Forum: "+pergunta.getTituloForum());
			String queryPergunta = "INSERT INTO pergunta_forum VALUES (NULL,"
					+ "(SELECT id_forum FROM forum INNER JOIN conteudo ON forum.id_conteudo = conteudo.id_conteudo WHERE titulo = '"
					+ pergunta.getTituloForum() + "')," + " (SELECT id_pessoa FROM pessoa WHERE login = '"
					+ pergunta.getObjetoAutor().getLogin() + "'), '" + pergunta.getTitulo() + "', '"
					+ pergunta.getDuvida() + "', '" + pergunta.getData() + "')";

			UtilBD.alterarBd(queryPergunta);

		} catch (SQLException e) {
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
