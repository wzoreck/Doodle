package doodle.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import doodle.entidades.Aluno;

public class AlunoDAO implements InterfaceDAO<Aluno> {

	@Override
	public void adicionar(Aluno aluno, ArrayList<Integer> id) {
		try {
			String queryPessoa = "INSERT INTO pessoa VALUES (NULL, '" + aluno.getNome() + "', '" + aluno.getEmail()
					+ "', '" + aluno.getDataNascimento() + "', '" + aluno.getLogin() + "', '" + aluno.getPasswd()
					+ "')";

			UtilBD.alterarBd(queryPessoa);

		} catch (SQLException e1) {
			System.err.println("Falaha ao inserir Pessoa no banco de dados");
		}

		try {
			String queryAluno = "INSERT INTO aluno (id_aluno)\n" + "SELECT (id_pessoa) FROM pessoa\n"
					+ "WHERE login = '" + aluno.getLogin() + "'";

			UtilBD.alterarBd(queryAluno);

		} catch (SQLException e2) {
			System.err.println("Falaha ao inserir Aluno no banco de dados");
		}

	}

	@Override
	public ArrayList<Aluno> listar() {
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		Aluno aluno = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Formato da data
		try {
			String querySelectAlunos = "SELECT * FROM pessoa INNER JOIN aluno ON pessoa.id_pessoa = aluno.id_aluno";
			ResultSet resultSet = UtilBD.consultarBD(querySelectAlunos);
			while (resultSet.next()) {
				int id = resultSet.getInt("id_pessoa");
				String nome = resultSet.getString("nome");
				String email = resultSet.getString("email");
				String data = resultSet.getString("data_nascimento");
				String login = resultSet.getString("login");
				String passwd = resultSet.getString("passwd");
				boolean matriculado = resultSet.getBoolean("matriculado");
				aluno = new Aluno(nome, email, sdf.parse(data), login, passwd, false);
				aluno.setId(id);
				aluno.setMatriculado(matriculado);
				alunos.add(aluno);
			}
			resultSet.getStatement().close();
			sdf.clone();
		} catch (SQLException e) {
			System.err.println("Não foi possível buscar os Alunos no banco de dados");
		} catch (ParseException e) {
			System.err.println("Falha ao transformar String para Data - AlunoDAO");
		}
		return alunos;
	}

	@Override
	public void atualizar(Aluno referencia) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Aluno aluno) {
		try {
			String queryDeleteAluno = "DELETE FROM aluno WHERE id_aluno = '" + aluno.getId() + "'";
			UtilBD.alterarBd(queryDeleteAluno);
			String queryDeletePessoa = "DELETE FROM pessoa WHERE id_pessoa = '" + aluno.getId() + "'";
			UtilBD.alterarBd(queryDeletePessoa);
		} catch (SQLException e) {
			System.err.println("Falha ao remover Pessoa-Aluno do banco de dados");
		}
	}

}
