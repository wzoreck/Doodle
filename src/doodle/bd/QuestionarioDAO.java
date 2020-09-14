package doodle.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import doodle.entidades.Conteudo;
import doodle.questionario.Questionario;

public class QuestionarioDAO implements InterfaceDAO<Questionario> {

	@Override
	public void adicionar(Questionario questionario, ArrayList<Integer> id) {
		Conteudo conteudo = (Conteudo) questionario;
		CursoDAO cursoDAO = new CursoDAO();
		cursoDAO.adicinarConteudo(conteudo, id.get(0)); // ID do curso
		// id_questao, id_conteudo (id-0), questao
		try {
			for (String questao : questionario.getQuestoes()) {
				String queryQuestionario = "INSERT INTO questionario VALUES (" + questionario.getIDQuestionario() + ","
						+ id.get(0) + "," + "'" + questao + "')";

				UtilBD.alterarBd(queryQuestionario);
			}
		} catch (SQLException e) {
			System.err.println("Falha ao inserir Questoes no banco de dados");
		}
	}

	@Override
	public ArrayList<Questionario> listar(int aux) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Formato da data
		ArrayList<Questionario> questionarios = new ArrayList<Questionario>();
		try {
			String querySelectQuestoes = "SELECT * FROM questionario INNER JOIN conteudo ON questionario.id_conteudo = conteudo.id_conteudo"
					+ " WHERE id_conteudo = " + aux;
			ResultSet resultSet = UtilBD.consultarBD(querySelectQuestoes);
			while (resultSet.next()) {
				int idQuestionario = resultSet.getInt("id_conteudo");
				String titulo = resultSet.getString("titulo");
				String descricao = resultSet.getString("descricao");
				String data = resultSet.getString("data_publicacao");
				Questionario questionario = new Questionario(titulo, descricao, sdf.parse(data));
				questionario.setId(idQuestionario);
				questionarios.add(questionario);
			}
			resultSet.getStatement().close();
			sdf.clone();
		} catch (SQLException e) {
			System.err.println("Não foi possível buscar os Questionarios no banco de dados");
		} catch (ParseException e) {
			System.err.println("Falha ao converter String para Data QuestionarioDAO");
		}
		return questionarios;
	}

	@Override
	public void atualizar(Questionario referencia, int aux) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Questionario referencia) {
		// TODO Auto-generated method stub

	}

}
