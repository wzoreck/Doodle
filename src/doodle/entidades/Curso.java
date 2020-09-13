package doodle.entidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import doodle.forum.Forum;
import doodle.questionario.Questionario;

public class Curso {
	private String nome;
	private Date dataInicio;
	private Professor professor;
	private ArrayList<Aluno> alunos;
	private ArrayList<Conteudo> conteudos;
	private int vagas = 40;

	public Curso(Professor professor, String nome) {
		this.professor = professor;
		this.nome = nome;
		this.alunos = new ArrayList<Aluno>();
		this.conteudos = new ArrayList<Conteudo>();
	}

	public Curso(Professor professor, String nome, Date dataInicio) {
		this.professor = professor;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.alunos = new ArrayList<Aluno>();
		this.conteudos = new ArrayList<Conteudo>();
	}

	public void adicionaAluno(Aluno aluno) {
		if (this.alunos.size() >= this.vagas)
			return;

		for (int i = 0; i < this.alunos.size(); i++)
			if (this.alunos.get(i).equals(aluno))
				return;

		this.alunos.add(aluno);
		aluno.setMatriculado(true);
		aluno.adicionaCurso(this);
	}

	public void removeAluno(Aluno aluno) {
		for (int i = 0; i < this.alunos.size(); i++) {
			if (this.alunos.get(i).equals(aluno)) {
				this.alunos.remove(i);
				return;
			}
		}
	}

	public void listaAlunos() {
		for (int i = 0; i < this.alunos.size(); i++) {
			System.out.println("\nNome: " + this.alunos.get(i).getNome());
			System.out.println("Email: " + this.alunos.get(i).getEmail());
			System.out.println("Data de nascimento: " + this.alunos.get(i).getDataNascimento());
			System.out.println(this.alunos.get(i).isMatriculado() ? "Está matriculado!" : "Não está matriculado!");
		}
	}

	public void adicionaQuestionario(String titulo, String descricao, Date data) {
		Questionario q = new Questionario(titulo, descricao, data);
		this.conteudos.add(q);
	}
	
	public void adicionaConteudo(Conteudo conteudo) {
		this.conteudos.add(conteudo);
	}

	public void adicionaForum(String titulo, String descricao, Date data) {
		for (int i = 0; i < conteudos.size(); i++)
			if (this.conteudos.get(i).getTitulo().contentEquals(titulo))
				return;

		Forum f = new Forum(titulo, descricao, data);
		this.conteudos.add(f);
	}

	public void removeConteudo(String titulo) {
		for (int i = 0; i < this.conteudos.size(); i++) {
			if (this.conteudos.get(i).getTitulo().contentEquals(titulo)) {
				this.conteudos.remove(i);
				return;
			}
		}
	}

	public void informacaoCurso() {
		System.out.println("Nome: " + this.nome);
		System.out.println("Criado em: " + getDataInicio());
		System.out.println("Professor: " + this.professor.getNome());
		System.out.println("Contato: " + this.professor.getEmail());
	}

	public void listaConteudos() {
		Forum f = null;
		Questionario q = null;

		for (int i = 0; i < this.conteudos.size(); i++) {

			System.out.println(
					"\nTítulo " + this.conteudos.get(i).getTipoConteudo() + ": " + this.conteudos.get(i).getTitulo());
			System.out.println("Descrição: " + this.conteudos.get(i).getDescricao());
			System.out.println("Data de publicação: " + this.conteudos.get(i).getDataPublicacao());

			if (this.conteudos.get(i).getTipoConteudo().contentEquals("forum")) {
				f = (Forum) this.conteudos.get(i);
				f.listar();
			} else {
				q = (Questionario) this.conteudos.get(i);
				q.listaQuestoes();
			}

		}
	}

	public String getNome() {
		return this.nome;
	}

	public String getDataInicio() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dataInicio);
	}

	public Professor getProfessor() {
		return professor;
	}

	public ArrayList<Aluno> getAlunos() {
		return alunos;
	}

	public ArrayList<Conteudo> getConteudos() {
		return conteudos;
	}

}
