package doodle.bd;

import java.sql.SQLException;
import java.util.List;

import doodle.entidades.Aluno;
import doodle.entidades.Conteudo;
import doodle.entidades.Curso;

public class CursoDAO implements InterfaceDAO<Curso> {

	@Override
	public void adicionar(Curso curso) {
		try {
			String queryCurso = "INSERT INTO curso VALUES (NULL, '" + curso.getNome() + "', '" + curso.getDataInicio()
					+ "', (SELECT id_pessoa FROM pessoa WHERE login = '" + curso.getProfessor().getLogin() + "'))";

			UtilBD.alterarBd(queryCurso);

		} catch (SQLException e1) {
			System.err.println("Falha ao inserir Curso no banco de dados");
		}
		try {
			for (Aluno aluno : curso.getAlunos()) {
				String queryMatricula = "INSERT INTO matricula_curso VALUES ("
						+ "(SELECT id_pessoa FROM pessoa WHERE login = '" + aluno.getLogin()
						+ "' ), (SELECT id_curso FROM curso WHERE nome = '" + curso.getNome() + "' ) )";

				UtilBD.alterarBd(queryMatricula);
			}
		} catch (SQLException e2) {
			System.err.println("Falha ao inserir Matriculas no banco de dados");
		}
		try {
			for (Conteudo conteudo : curso.getConteudos()) {
				String queryConteudo = "INSERT INTO conteudo VALUES (NULL,"
						+ "(SELECT id_curso FROM curso WHERE nome = '" + curso.getNome() + "' ),'"
						+ conteudo.getTitulo() + "', '" + conteudo.getDescricao() + "', '"
						+ conteudo.getDataPublicacao() + "', " + conteudo.isPrazo() + ", '" + conteudo.getDataInicio()
						+ "', '" + conteudo.getDataTermino() + "' )";

				UtilBD.alterarBd(queryConteudo);
			}
		} catch (SQLException e2) {
			System.err.println("Falaha ao inserir Matriculas no banco de dados");
		}
	}

	@Override
	public List<Curso> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Curso referencia) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Curso referencia) {
		// TODO Auto-generated method stub

	}

}
