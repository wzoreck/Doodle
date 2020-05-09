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
	private ArrayList<Conteudo> conteudos; // Criar um metodo listaConteudos() ainda
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
	
	public void adicionaForum(String titulo, String descricao, Date data) {
		Forum f = new Forum(titulo, descricao, data);
		this.conteudos.add(f);
	}

	public void removeConteudo(Conteudo conteudo) {
		for (int i = 0; i < this.conteudos.size(); i++) {
			if (this.conteudos.get(i).equals(conteudo)) {
				this.conteudos.remove(i);
				return;
			}
		}
	}
	
	public void informacaoCurso() {
		System.out.println("Nome: " + this.nome);
		System.out.println("Criado em: " + getDataInicio());
		System.out.println("Alunos matriculados: " + this.vagas);
		System.out.println("Professor: " + this.professor.getNome());
		System.out.println("Contato: " + this.professor.getEmail());
	}

	public String getNome() {
		return this.nome;
	}

	public String getDataInicio() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dataInicio);
	}

	public ArrayList<Conteudo> getConteudos() {
		return conteudos;
	}

}
