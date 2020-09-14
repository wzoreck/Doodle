package doodle.bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doodle.questionario.Questionario;

public class QuestionarioDAO implements InterfaceDAO<Questionario> {

	@Override
	public void adicionar(Questionario questionario, ArrayList<Integer> id) {
		// id_questao, id_conteudo (id-0), questao
		try {
			for (String questao : questionario.getQuestoes()) {
				String queryQuestionario = "INSERT INTO questionario VALUES (NULL," + id.get(0) + "," + "'" + questao
						+ "')";

				UtilBD.alterarBd(queryQuestionario);
			}
		} catch (SQLException e) {
			System.err.println("Falha ao inserir Questoes no banco de dados");
		}
	}

	@Override
	public List<Questionario> listar(int aux) {
		// TODO Auto-generated method stub
		return null;
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
