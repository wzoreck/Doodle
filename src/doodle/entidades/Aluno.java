package doodle.entidades;

import java.util.Date;

import doodle.forum.Forum;
import doodle.forum.Pergunta;

public class Aluno extends Pessoa {

	private boolean matriculado;

	public Aluno(String nome, String email, Date dataNascimento) {
		super(nome, email, dataNascimento);
		this.matriculado = false;
	}

	public Aluno(String nome, String email, Date dataNascimento, String login, String passwd) {
		super(nome, email, dataNascimento, login, passwd);
		this.matriculado = false;
	}

	public void adicionaCurso(Curso curso) {
		this.cursos.add(curso);
	}

	public void perguntaForum(Curso curso, String titulo, String tituloPergunta, String pergunta) {
		Forum f = null;

		for (int i = 0; i < curso.getConteudos().size(); i++)
			if (curso.getConteudos().get(i).getTipoConteudo().contentEquals("forum"))
				if (curso.getConteudos().get(i).getTitulo().contentEquals(titulo)) {
					f = (Forum) curso.getConteudos().get(i);
					break;
				}

		if (f == null)
			return;

		Date data = new Date();

		f.adicionaPergunta(new Pergunta(this, tituloPergunta, pergunta, data));
	}

	public boolean isMatriculado() {
		return matriculado;
	}

	public void setMatriculado(boolean matriculado) {
		this.matriculado = matriculado;
	}

	@Override
	protected String tipoPessoa() {
		return "aluno";
	}
}
