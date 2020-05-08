package doodle.entidades;

import java.util.ArrayList;
import java.util.Date;

public class Professor extends Pessoa {
	private float salario;
	private int cargaHorariaSemanal;
	private ArrayList<String> historicoCursos; // Armazenar "certificados dos cursos, Nome e carga horária"
	
	public Professor(String nome, String email, Date dataNascimento, float salario, int cargaHorariaSemanal) {
		super(nome, email, dataNascimento);
		this.salario = salario;
		this.cargaHorariaSemanal = cargaHorariaSemanal;
		this.historicoCursos = new ArrayList<String>();
	}

	public Professor(String nome, String email, Date dataNascimento, String login, String passwd, float salario,
			int cargaHorariaSemanal) {
		super(nome, email, dataNascimento, login, passwd);
		this.salario = salario;
		this.cargaHorariaSemanal = cargaHorariaSemanal;
		this.historicoCursos = new ArrayList<String>();
	}
	
	public void adicionaHistorico(String informacaoCurso) {
		this.historicoCursos.add(informacaoCurso);
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public int getCargaHorariaSemanal() {
		return cargaHorariaSemanal;
	}

	public void setCargaHorariaSemanal(int cargaHorariaSemanal) {
		this.cargaHorariaSemanal = cargaHorariaSemanal;
	}

	public ArrayList<String> getHistoricoCursos() {
		return historicoCursos;
	}

	@Override
	protected String tipoPessoa() {
		return "professor";
	}
}
