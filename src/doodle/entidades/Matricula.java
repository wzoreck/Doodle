package doodle.entidades;

import java.util.Date;

public class Matricula {
	private int id;
	private Aluno aluno;
	private Date data;
	private static int totalMatriculas = 0;
	
	public Matricula(Aluno aluno, Date data) {
		this.aluno = aluno;
		this.data = data;
		this.id = (1000 + Matricula.totalMatriculas);
		totalMatriculas++;
	}
	
	public Matricula(Aluno aluno) {
		this.aluno = aluno;
		this.id = (1000 + Matricula.totalMatriculas);
		totalMatriculas++;
	}

	public int getNumeroMatricula() {
		return id;
	}
	
	public Aluno getAluno() {
		return aluno;
	}

	public Date getData() {
		return data;
	}

	public static int getTotalMatriculas() {
		return totalMatriculas;
	}
		
}
