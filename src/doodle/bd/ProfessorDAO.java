package doodle.bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doodle.entidades.Professor;

public class ProfessorDAO implements InterfaceDAO<Professor> {

	@Override
	public void adicionar(Professor professor, ArrayList<Integer> id) {
		try {
			String queryPessoa = "INSERT INTO pessoa VALUES (NULL, '" + professor.getNome() + "', '"
					+ professor.getEmail() + "', '" + professor.getDataNascimento() + "', '" + professor.getLogin()
					+ "', '" + professor.getPasswd() + "')";

			UtilBD.alterarBd(queryPessoa);

		} catch (SQLException e1) {
			System.err.println("Falaha ao inserir Pessoa no banco de dados");
		}

		try {
			String queryProfessor = "INSERT INTO professor (id_professor, salario, carga_horaria_semanal)\n"
					+ "VALUES ((SELECT (id_pessoa) FROM pessoa WHERE login = '" + professor.getLogin() + "'), "
					+ professor.getSalario() + ", " + professor.getCargaHorariaSemanal() + ")";

			UtilBD.alterarBd(queryProfessor);

		} catch (SQLException e2) {
			System.err.println("Falaha ao inserir Professor no banco de dados");
		}
	}

	@Override
	public List<Professor> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Professor referencia) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Professor professor) {
		try {
			String queryDeleteProfessor = "DELETE FROM professor WHERE id_professor = '" + professor.getId() + "'";
			UtilBD.alterarBd(queryDeleteProfessor);
			String queryDeletePessoa = "DELETE FROM pessoa WHERE id_pessoa = '" + professor.getId() + "'";
			UtilBD.alterarBd(queryDeletePessoa);
		} catch (SQLException e) {
			System.err.println("Falha ao remover Pessoa-Professor do banco de dados");
		}

	}

}
