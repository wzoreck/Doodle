package doodle.entidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Curso {
	private String nome;
	private Date dataInicio;
	private Professor professor;
	private ArrayList<Matricula> alunosMatriculados;
	private ArrayList<Topico> topicos;

	public Curso(Professor professor, String nome) {
		this.professor = professor;
		this.nome = nome;
		alunosMatriculados = new ArrayList<Matricula>();
		topicos = new ArrayList<Topico>();
	}

	public Curso(Professor professor, String nome, Date dataInicio) {
		this.professor = professor;
		this.nome = nome;
		this.dataInicio = dataInicio;
		alunosMatriculados = new ArrayList<Matricula>();
		topicos = new ArrayList<Topico>();
	}

	public void adicionarAluno(Aluno aluno) {
		Date data = new Date();
		Matricula matricula = new Matricula(aluno, data);
		alunosMatriculados.add(matricula);
	}

	public void adicionaTopico(Topico topico) {
		for (int i = 0; i < topicos.size(); i++)
			if (topicos.get(i).getTitulo().contentEquals(topico.getTitulo()))
				return;
		topicos.add(topico);
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

	public ArrayList<Topico> getTopicos() {
		return topicos;
	}

	public void removerAluno(String cpf) {
		for (int i = 0; i < alunosMatriculados.size(); i++) {
			if (alunosMatriculados.get(i).getAluno().getCpf().contentEquals(cpf)) {
				alunosMatriculados.remove(i);
				return;
			}
		}
	}

	public void removeTopico(Topico topico) {
		for (int i = 0; i < topicos.size(); i++) {
			if (topicos.get(i).getTitulo().contentEquals(topico.getTitulo())) {
				topicos.remove(i);
				return;
			}
		}
	}

}
