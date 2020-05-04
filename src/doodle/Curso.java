package doodle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Curso {
	private String nome;
	private Date dataInicio;
	private Professor professor;
	private ArrayList<Aluno> turma;
	private ArrayList<Topico> topicos;
	
	public Curso(Professor professor, String nome, Date dataInicio) {
		this.professor = professor;
		this.nome = nome;
		this.dataInicio = dataInicio;
		turma = new ArrayList<Aluno>();
		topicos = new ArrayList<Topico>();
	}
	
	public void adicionarAluno(Aluno aluno) {
		for (int i=0; i < turma.size(); i++)
			if (turma.get(i).getCpf().contentEquals(aluno.getCpf()))
				return;
		turma.add(aluno);
	}
	
	public void adicionaTopico(Topico topico) {
		for(int i=0; i < topicos.size(); i++)
			if(topicos.get(i).getTitulo().contentEquals(topico.getTitulo()))
				return;
		topicos.add(topico);
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getDataInicio() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return  sdf.format(dataInicio);
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public ArrayList<Aluno> getTurma() {
		return turma;
	}

	public ArrayList<Topico> getTopicos() {
		return topicos;
	}
	
	/*
	public void removerAluno(Aluno aluno) {
		for(int i=0; i < turma.size(); i++) {
			if(turma.get(i).getCpf().contentEquals(aluno.getCpf())) {
				turma.remove(i);
				return;
			}
		}
	}
	
	public void removeTopico(Topico topico) {
		for(int i=0; i < topicos.size(); i++) {
			if(topicos.get(i).getTitulo().contentEquals(topico.getTitulo())) {
				topicos.remove(i);
				return;
			}
		}
	}
	*/
	
}
