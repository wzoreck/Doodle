package doodle.entidades;

import java.util.Date;

public class Matricula {
	private int id;
	private Aluno aluno;
	private Date data;
	private static int totalMatriculas;
	
	public Matricula(Aluno aluno, Date data) {
		this.aluno = aluno;
		this.data = data;
		this.id = (1000 + Matricula.totalMatriculas);
	}
	
	public Matricula(Aluno aluno) {
		this.aluno = aluno;
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
