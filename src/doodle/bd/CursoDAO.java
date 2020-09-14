package doodle.bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doodle.entidades.Aluno;
import doodle.entidades.Conteudo;
import doodle.entidades.Curso;

public class CursoDAO implements InterfaceDAO<Curso> {

	@Override
	public void adicionar(Curso curso, ArrayList<Integer> id) {
		// id_curso, nome, data_inicio, id_professor (id-0)
		try {
			String queryCurso = "INSERT INTO curso VALUES (NULL, '" + curso.getNome() + "', '" + curso.getDataInicio()
					+ "', " + id.get(0) + ")";

			UtilBD.alterarBd(queryCurso);

		} catch (SQLException e) {
			System.err.println("Falha ao inserir Curso no banco de dados");
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

	public void matricularAluno(Aluno aluno, Curso curso) {
		// id_aluno, id_curso
		try {
			String queryMatricula = "INSERT INTO matricula_curso VALUES (" + aluno.getId() + ", " + curso.getID() + ")";

			UtilBD.alterarBd(queryMatricula);

		} catch (SQLException e) {
			System.err.println("Falha ao inserir Matricula no banco de dados");
		}
	}

	public void adicinarConteudo(Conteudo conteudo, Curso curso) {
		// id_conteudo, id_curso, titulo, descricao, data_publicao, prazo, data_inicio,
		// data_termino
		try {
			String queryConteudo = "INSERT INTO conteudo VALUES (NULL," + curso.getID() + "," + conteudo.getTitulo()
					+ "','" + conteudo.getDescricao() + "','" + conteudo.getDataPublicacao() + "'," + conteudo.isPrazo()
					+ ", '" + conteudo.getDataInicio() + "', '" + conteudo.getDataTermino() + "' )";

			UtilBD.alterarBd(queryConteudo);

		} catch (SQLException e) {
			System.err.println("Falaha ao inserir Conteudo no banco de dados");
		}
	}

}
