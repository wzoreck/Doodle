package doodle.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import doodle.entidades.Aluno;
import doodle.entidades.Pessoa;
import doodle.forum.Resposta;
import doodle.ihc.AlertaFX;

public class RespostaDAO implements InterfaceDAO<Resposta> {

	@Override
	public void adicionar(Resposta resposta, ArrayList<Integer> id) {
		// id_resposta, id_forum (id-0), id_pergunta (id-1), id_autor (id-2), resposta,
		// data, correta
		try {
			String queryPergunta = "INSERT INTO resposta_forum VALUES (" + resposta.getIDResposta() + ","
					+ resposta.getIDForum() + "," + resposta.getIDPergunta() + "," + resposta.getAutor().getId() + ", '"
					+ resposta.getResposta() + "','" + resposta.getData() + "', " + resposta.isCorreta() + ")";

			UtilBD.alterarBd(queryPergunta);

		} catch (SQLException e1) {
			AlertaFX.erro("Falha ao inserir Resposta no banco de dados");
		}
	}

	@Override
	public ArrayList<Resposta> listar(int aux) {
		Resposta resposta = null;
		ArrayList<Resposta> respostas = new ArrayList<Resposta>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Formato da data
		try {
			String querySelectRespostas = "SELECT * FROM resposta_forum"
					+ " INNER JOIN pessoa ON resposta_forum.id_autor = pessoa.id_pessoa" + " WHERE id_forum = " + aux;
			ResultSet resultSet = UtilBD.consultarBD(querySelectRespostas);
			while (resultSet.next()) {
				int idResposta = resultSet.getInt("id_resposta");
				int idPergunta = resultSet.getInt("id_pergunta");
				int idForum = resultSet.getInt("id_forum");
				int idAutor = resultSet.getInt("id_autor");
				String respostaString = resultSet.getString("resposta");
				String dataPublicacao = resultSet.getString("data");
				boolean correta = resultSet.getBoolean("correta");

				String nome = resultSet.getString("nome");
				String email = resultSet.getString("email");
				String data = resultSet.getString("data_nascimento");
				String login = resultSet.getString("login");
				String passwd = resultSet.getString("passwd");
				Aluno aluno = new Aluno(nome, email, sdf.parse(data), login, passwd, false);
				aluno.setId(idAutor);
				resposta = new Resposta(idPergunta, idForum, (Pessoa) aluno, respostaString, sdf.parse(dataPublicacao),
						correta, false);
				resposta.setIDResposta(idResposta);
				respostas.add(resposta);
			}
			resultSet.getStatement().close();
			sdf.clone();
		} catch (SQLException e) {
			AlertaFX.erro("Não foi possível buscar os Respostas no banco de dados");
		} catch (ParseException e) {
			AlertaFX.erro("Falha ao converter String para Data RespostaDAO");
		}
		return respostas;
	}

	@Override
	public void atualizar(Resposta referencia, int aux) {
		// Não implementado - Ainda não há essa funcionalidade na Main

	}

	@Override
	public void remover(Resposta referencia) {
		// Não implementado - Ainda não há essa funcionalidade na Main

	}

}
