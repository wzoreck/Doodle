package doodle.bd;

import java.sql.SQLException;
import java.util.List;

import doodle.questionario.Questionario;

public class QuestionarioDAO implements InterfaceDAO<Questionario> {

	@Override
	public void adicionar(Questionario questionario) {
		try {
			for (String questao : questionario.getQuestoes()) {
				String queryQuestionario = "INSERT INTO questionario VALUES (NULL,"
						+ "(SELECT id_conteudo FROM conteudo WHERE titulo = '" + questionario.getTitulo() + "')," + "'"
						+ questao + "')";

				UtilBD.alterarBd(queryQuestionario);
			}
		} catch (SQLException e) {
			System.err.println("Falha ao inserir Questoes no banco de dados");
		}
	}

	@Override
	public List<Questionario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Questionario referencia) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Questionario referencia) {
		// TODO Auto-generated method stub

	}

}
