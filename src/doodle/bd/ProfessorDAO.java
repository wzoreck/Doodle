package doodle.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import doodle.entidades.Professor;

public class ProfessorDAO implements InterfaceDAO<Professor> {

	@Override
	public void adicionar(Professor professor, ArrayList<Integer> id) {
		try {
			String queryPessoa = "INSERT INTO pessoa VALUES (" + professor.getId() + ", '" + professor.getNome()
					+ "', '" + professor.getEmail() + "', '" + professor.getDataNascimento() + "', '"
					+ professor.getLogin() + "', '" + professor.getPasswd() + "')";

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
	public ArrayList<Professor> listar(int aux) {
		ArrayList<Professor> professores = new ArrayList<Professor>();
		Professor professor = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Formato da data
		try {
			String querySelectAlunos = "SELECT * FROM pessoa INNER JOIN professor ON pessoa.id_pessoa = professor.id_professor";
			ResultSet resultSet = UtilBD.consultarBD(querySelectAlunos);
			while (resultSet.next()) {
				int id = resultSet.getInt("id_pessoa");
				String nome = resultSet.getString("nome");
				String email = resultSet.getString("email");
				String data = resultSet.getString("data_nascimento");
				String login = resultSet.getString("login");
				String passwd = resultSet.getString("passwd");
				float salario = resultSet.getFloat("salario");
				int cargaHorariaSemanal = resultSet.getInt("carga_horaria_semanal");
				professor = new Professor(nome, email, sdf.parse(data), login, passwd, salario, cargaHorariaSemanal,
						false);
				professor.setId(id);
				professores.add(professor);
			}
			resultSet.getStatement().close();
			sdf.clone();
		} catch (SQLException e) {
			System.err.println("Não foi possível buscar os Professores no banco de dados");
		} catch (ParseException e) {
			System.err.println("Falha ao transformar String para Data - ProfessoroDAO");
		}
		return professores;
	}

	@Override
	public void atualizar(Professor professor, int aux) {
		try {
			String queryUpdatePessoa = "UPDATE pessoa SET " + "nome = '" + professor.getNome() + "', email = '"
					+ professor.getEmail() + "', data_nascimento = '" + professor.getDataNascimento() + "', login = '"
					+ professor.getLogin() + "', passwd = '" + professor.getPasswd() + "' WHERE id_pessoa = "
					+ professor.getId();
			UtilBD.alterarBd(queryUpdatePessoa);
			String queryUpdateProfessor = "UPDATE professor SET " + "salario = " + professor.getSalario()
					+ ", carga_horaria_semanal = " + professor.getCargaHorariaSemanal() + " WHERE id_professor = "
					+ professor.getId();	
			UtilBD.alterarBd(queryUpdateProfessor);
		} catch (SQLException e) {
			System.err.println("Falha ao realizar o update de Pessoa-Professor");
		}
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
	
	public Professor get(int idPessoa) {
		Professor professor = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String querySelectProfessor = "SELECT * FROM pessoa INNER JOIN professor ON pessoa.id_pessoa = professor.id_professor"
					+ " WHERE id_pessoa = " + idPessoa;
			ResultSet resultSet = UtilBD.consultarBD(querySelectProfessor);
			while (resultSet.next()) {
				int id = resultSet.getInt("id_pessoa");
				String nome = resultSet.getString("nome");
				String email = resultSet.getString("email");
				String data = resultSet.getString("data_nascimento");
				String login = resultSet.getString("login");
				String passwd = resultSet.getString("passwd");
				float salario = resultSet.getFloat("salario");
				int cargaHorariaSemanal = resultSet.getInt("carga_horaria_semanal");
				professor = new Professor(nome, email, sdf.parse(data), login, passwd, salario, cargaHorariaSemanal, false);
				professor.setId(id);
			}
			resultSet.getStatement().close();
		} catch (SQLException e) {
			System.err.println("Falha ao buscar Professor no banco de dados");
		} catch (ParseException e) {
			System.err.println("Falha ao converter String para Data ProfessorDAO");
		}
		return professor;
	}

}
