package doodle.bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			String queryAluno = "INSERT INTO aluno (id_aluno)\n" + "SELECT (id_pessoa) FROM pessoa\n" + "WHERE login = '"
					+ aluno.getLogin() + "'";

			UtilBD.alterarBd(queryAluno);
			
		} catch (SQLException e2) {
			System.err.println("Falaha ao inserir Aluno no banco de dados");
		}

	}

	@Override
	public List<Aluno> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Aluno referencia) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Aluno referencia) {
		// TODO Auto-generated method stub

	}

}
