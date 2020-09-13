package doodle.bd;

import java.sql.SQLException;
import java.util.List;

import doodle.entidades.Professor;

public class ProfessorDAO implements InterfaceDAO<Professor> {

	@Override
	public void adicionar(Professor professor) {
		try {
			String query_pessoa = "INSERT INTO pessoa VALUES (NULL, '" + professor.getNome() + "', '"
					+ professor.getEmail() + "', '" + professor.getDataNascimento() + "', '" + professor.getLogin()
					+ "', '" + professor.getPasswd() + "')";

			UtilBD.alterarBd(query_pessoa);

		} catch (SQLException e1) {
			System.err.println("Falaha ao inserir Pessoa no banco de dados");
		}

		try {
			String query_professor = "INSERT INTO professor (id_professor, salario, carga_horaria_semanal)\n"
					+ "VALUES ((SELECT (id_pessoa) FROM pessoa WHERE login = '" + professor.getLogin() + "'), "
					+ professor.getSalario() + ", " + professor.getCargaHorariaSemanal() + ");";

			UtilBD.alterarBd(query_professor);

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
	public void remover(Professor referencia) {
		// TODO Auto-generated method stub

	}

}
