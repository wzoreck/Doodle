package doodle.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import doodle.forum.Forum;

public class ForumDAO implements InterfaceDAO<Forum> {

	@Override
	public void adicionar(Forum forum, ArrayList<Integer> id) {
		// id_forum, id_conteudo (id-0), aberto
		try {
			String queryForum = "INSERT INTO forum VALUES (" + forum.getIDForum() + ", " + id.get(0) + ", "
					+ forum.isAberto() + ")";

			System.out.println("\n\n Executado: " + queryForum);

			UtilBD.alterarBd(queryForum);

		} catch (SQLException e1) {
			System.err.println("Falha ao inserir Forum no banco de dados");
		}
	}

	@Override
	public ArrayList<Forum> listar(int aux) {
		Forum forum = null;
		ArrayList<Forum> foruns = new ArrayList<Forum>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Formato da data
		try {
			String querySelectForuns = "SELECT * FROM forum INNER JOIN conteudo ON forum.id_conteudo = conteudo.id_conteudo"
					+ " WHERE id_curso = " + aux;
			ResultSet resultSet = UtilBD.consultarBD(querySelectForuns);
			while (resultSet.next()) {
				String titulo = resultSet.getString("titulo");
				String descricao = resultSet.getString("descricao");
				String dataPublicacao = resultSet.getString("data_publicacao");
				int idForum = resultSet.getInt("id_forum");
				int idConteudo = resultSet.getInt("id_conteudo");

				forum = new Forum(titulo, descricao, sdf.parse(dataPublicacao), false, false);
				forum.setIDForum(idForum);
				forum.setId(idConteudo);
				foruns.add(forum);
			}
			resultSet.getStatement().close();
			sdf.clone();
		} catch (SQLException e) {
			System.err.println("Não foi possível buscar os Foruns no banco de dados");
		} catch (ParseException e) {
			System.err.println("Falha ao converter String para Data ForumDAO");
		}
		return foruns;
	}

	@Override
	public void atualizar(Forum forum, int aux) {
		try {
			String queryUpdateForum = "UPDATE forum SET aberto = " + forum.isAberto() + " WHERE id_forum = "
					+ forum.getIDForum();
			UtilBD.alterarBd(queryUpdateForum);
			String queryUpdateConteudo = "UPDATE conteudo SET " + "titulo = '" + forum.getTitulo() + "', descricao = '"
					+ forum.getDescricao() + "', data_publicacao = '" + forum.getDataPublicacao()
					+ "' WHERE id_conteudo = " + forum.getId();
			UtilBD.alterarBd(queryUpdateConteudo);
		} catch (SQLException e) {
			System.err.println("Falha ao realizar o update de Forum");
		}

	}

	@Override
	public void remover(Forum forum) {
		try {
			String queryDeleteForum = "DELETE FROM forum WHERE id_forum = " + forum.getIDForum();
			UtilBD.alterarBd(queryDeleteForum);
			String queryDeleteConteudo = "DELETE FROM conteudo WHERE id_conteudo = " + forum.getId();
			UtilBD.alterarBd(queryDeleteConteudo);
		} catch (SQLException e) {
			System.err.println("Falha ao remover Forum do banco de dados");
		}
	}

	public Forum get(int idForum) {
		Forum forum = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String querySelectForum = "SELECT * FROM forum INNER JOIN conteudo ON forum.id_conteudo = conteudo.id_conteudo"
					+ " WHERE id_forum = " + idForum;
			ResultSet resultSet = UtilBD.consultarBD(querySelectForum);
			while (resultSet.next()) {
				String titulo = resultSet.getString("titulo");
				String descricao = resultSet.getString("descricao");
				String dataPublicacao = resultSet.getString("data_publicacao");
				int idConteudo = resultSet.getInt("id_conteudo");

				forum = new Forum(titulo, descricao, sdf.parse(dataPublicacao), false, false);
				forum.setIDForum(idForum);
				forum.setId(idConteudo);
			}
			resultSet.getStatement().close();
		} catch (SQLException e) {
			System.err.println("Falha ao buscar Forum no banco de dados");
		} catch (ParseException e) {
			System.err.println("Falha ao converter String para Data ForumDAO");
		}
		return forum;
	}

}
