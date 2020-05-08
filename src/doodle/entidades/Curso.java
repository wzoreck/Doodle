package doodle.entidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Curso {
	private String nome;
	private Date dataInicio;
	private Professor professor;
	private ArrayList<Aluno> alunos;
	private ArrayList<Topico> topicos; // susbtituir por:
	private ArrayList<Conteudo> conteudos;
	private static int totalCursos = 0;
	// novo --
	private int vagas;

	public Curso(Professor professor, String nome) {
		this.professor = professor;
		this.nome = nome;
		//this.alunosMatriculados = new ArrayList<Matricula>();
		this.topicos = new ArrayList<Topico>();
		totalCursos++;
	}

	public Curso(Professor professor, String nome, Date dataInicio) {
		this.professor = professor;
		this.nome = nome;
		this.dataInicio = dataInicio;
		//this.alunosMatriculados = new ArrayList<Matricula>();
		this.topicos = new ArrayList<Topico>();
		totalCursos++;
	}

	public void adicionaTopico(Topico topico) {
		for (int i = 0; i < topicos.size(); i++)
			if (topicos.get(i).equals(topico))
				return;
		topicos.add(topico);
	}
	
	public void removeTopico(Topico topico) {
		for (int i = 0; i < topicos.size(); i++) {
			if (topicos.get(i).equals(topico)) {
				topicos.remove(i);
				return;
			}
		}
	}
	
	public void matricularAluno(Aluno a) {
		for (int i=0; i < alunosMatriculados.size(); i++) {
			if(alunosMatriculados.get(i).getAluno().equals(a))
				return;
		}
		
		Matricula m = new Matricula(a);
		this.alunosMatriculados.add(m);
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

	public ArrayList<Matricula> getTurma() {
		return alunosMatriculados;
	}

	public Topico getTopico(String titulo) {
		for (int i = 0; i < topicos.size(); i++) {
			if (topicos.get(i).getTitulo().contentEquals(titulo))
				return topicos.get(i);
		}
		return null;
	}
	
	public ArrayList<Topico> getTopico() {
		return topicos; 
	}

	public void removerAluno(Aluno a) {
		for (int i = 0; i < alunosMatriculados.size(); i++) {
			if (alunosMatriculados.get(i).getAluno().equals(a)) {
				alunosMatriculados.remove(i);
				return;
			}
		}
	}

	public static int getTotalCursos() {
		return totalCursos;
	}

	public static void setTotalCursos(int totalCursos) {
		Curso.totalCursos = totalCursos;
	}
	

}
