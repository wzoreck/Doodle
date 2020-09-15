package doodle.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import doodle.entidades.Aluno;
import doodle.entidades.Pessoa;
import doodle.forum.Pergunta;

public class PerguntaDAO implements InterfaceDAO<Pergunta> {

	@Override
	public void adicionar(Pergunta pergunta, ArrayList<Integer> id) {
		// id_pergunta, id_forum, id_autor, titulo, duvida, data
		try {
			String queryPergunta = "INSERT INTO pergunta_forum VALUES (" + pergunta.getIDPergunta() + ", "
					+ pergunta.getIDForum() + "," + pergunta.getObjetoAutor().getId() + ", '" + pergunta.getTitulo()
					+ "', '" + pergunta.getDuvida() + "', '" + pergunta.getData() + "')";

			UtilBD.alterarBd(queryPergunta);

		} catch (SQLException e1) {
			System.err.println("Falha ao inserir Pergunta no banco de dados");
		}
	}

	@Override
	public ArrayList<Pergunta> listar(int aux) {
		Pergunta pergunta = null;
		ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Formato da data
		try {
			String querySelectPerguntas = "SELECT * FROM pergunta_forum"
					+ " INNER JOIN pessoa ON pergunta_forum.id_autor = pessoa.id_pessoa" + " WHERE id_forum = " + aux;
			ResultSet resultSet = UtilBD.consultarBD(querySelectPerguntas);
			while (resultSet.next()) {
				int idPergunta = resultSet.getInt("id_pergunta");
				int idForum = resultSet.getInt("id_forum");
				int idAutor = resultSet.getInt("id_autor");
				String titulo = resultSet.getString("titulo");
				String duvida = resultSet.getString("duvida");
				String dataPublicacao = resultSet.getString("data");

				String nome = resultSet.getString("nome");
				String email = resultSet.getString("email");
				String data = resultSet.getString("data_nascimento");
				String login = resultSet.getString("login");
				String passwd = resultSet.getString("passwd");
				Aluno aluno = new Aluno(nome, email, sdf.parse(data), login, passwd, false);
				aluno.setId(idAutor);
				pergunta = new Pergunta((Pessoa) aluno, titulo, duvida, sdf.parse(dataPublicacao), idForum, false);
				pergunta.setIDPergunta(idPergunta);
				pergunta.setIDForum(idForum);
				perguntas.add(pergunta);
			}
			resultSet.getStatement().close();
			sdf.clone();
		} catch (SQLException e) {
			System.err.println("Não foi possível buscar os Perguntas no banco de dados");
		} catch (ParseException e) {
			System.err.println("Falha ao converter String para Data PerguntaDAO");
		}
		return perguntas;
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
