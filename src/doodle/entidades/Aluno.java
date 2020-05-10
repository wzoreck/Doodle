package doodle.entidades;

import java.util.ArrayList;
import java.util.Date;

import doodle.forum.Forum;
import doodle.forum.Pergunta;

public class Aluno extends Pessoa {
	private boolean matriculado;
	private ArrayList<String> certificados;
	private ArrayList<Curso> meusCursos;

	public Aluno(String nome, String email, Date dataNascimento) {
		super(nome, email, dataNascimento);
		this.matriculado = false;
		this.certificados = new ArrayList<String>();
		this.meusCursos = new ArrayList<Curso>();
	}

	public Aluno(String nome, String email, Date dataNascimento, String login, String passwd) {
		super(nome, email, dataNascimento, login, passwd);
		this.matriculado = false;
		this.certificados = new ArrayList<String>();
		this.meusCursos = new ArrayList<Curso>();
	}

	public void adicionaCertificado(String certificado) {
		this.certificados.add(certificado);
	}

	public void adicionaCurso(Curso curso) {
		this.meusCursos.add(curso);
	}

	public void listaCursos() {
		for (int i = 0; i < this.meusCursos.size(); i++)
			System.out.println("Curso " + (i + 1) + ": " + this.meusCursos.get(i).getNome());
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

	public Curso getCurso(String nome) {
		for (int i = 0; i < this.meusCursos.size(); i++)
			if (this.meusCursos.get(i).getNome().contentEquals(nome))
				return this.meusCursos.get(i);
		return null;
	}

	public boolean isMatriculado() {
		return matriculado;
	}

	public void setMatriculado(boolean matriculado) {
		this.matriculado = matriculado;
	}

	public ArrayList<String> getCertificados() {
		return certificados;
	}

	@Override
	protected String tipoPessoa() {
		return "aluno";
	}
}
