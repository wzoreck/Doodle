package doodle.entidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Curso {
	private String nome;
	private Date dataInicio;
	private Professor professor;
	private ArrayList<Aluno> alunos;
	private ArrayList<Conteudo> conteudos; // Criar um metodo listaConteudos() ainda
	private int vagas;

	public Curso(Professor professor, String nome) {
		this.professor = professor;
		this.nome = nome;
		this.conteudos = new ArrayList<Conteudo>();
	}

	public Curso(Professor professor, String nome, Date dataInicio) {
		this.professor = professor;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.conteudos = new ArrayList<Conteudo>();
	}

	public void adicionaAluno(Aluno aluno) {
		if (this.alunos.size() >= this.vagas)
			return;

		for (int i = 0; i < this.alunos.size(); i++)
			if (this.alunos.get(i).equals(aluno))
				return;

		this.alunos.add(aluno);
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

	public void adicionaConteudo(Conteudo conteudo) {
		for (int i = 0; i < this.conteudos.size(); i++)
			if (this.conteudos.get(i).equals(conteudo))
				return;

		this.conteudos.add(conteudo);
	}

	public void removeConteudo(Conteudo conteudo) {
		for (int i = 0; i < this.conteudos.size(); i++) {
			if (this.conteudos.get(i).equals(conteudo)) {
				this.conteudos.remove(i);
				return;
			}
		}
	}
	
	public void InformacaoCurso() {
		System.out.println("\nNome: " + this.nome);
		System.out.println("Criado em: " + this.getDataInicio());
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

}
