package doodle.entidades;

import java.util.ArrayList;
import java.util.Date;

import doodle.forum.Forum;
import doodle.forum.Resposta;

public class Professor extends Pessoa {
	private float salario;
	private int cargaHorariaSemanal;
	private ArrayList<String> historicoCursos; // Armazenar "certificados dos cursos, Nome e carga horária"
	private ArrayList<Curso> cursos;

	public Professor(String nome, String email, Date dataNascimento, float salario, int cargaHorariaSemanal) {
		super(nome, email, dataNascimento);
		this.salario = salario;
		this.cargaHorariaSemanal = cargaHorariaSemanal;
		this.historicoCursos = new ArrayList<String>();
		this.cursos = new ArrayList<Curso>();
	}

	public Professor(String nome, String email, Date dataNascimento, String login, String passwd, float salario,
			int cargaHorariaSemanal) {
		super(nome, email, dataNascimento, login, passwd);
		this.salario = salario;
		this.cargaHorariaSemanal = cargaHorariaSemanal;
		this.historicoCursos = new ArrayList<String>();
		this.cursos = new ArrayList<Curso>();
	}

	public void adicionaHistorico(String informacaoCurso) {
		this.historicoCursos.add(informacaoCurso);
	}

	public void criaCurso(String nome, Date data) {
		for (int i = 0; i < this.cursos.size(); i++)
			if (this.cursos.get(i).getNome().contentEquals(nome))
				return;

		Curso c = new Curso(this, nome, data);
		this.cursos.add(c);
	}

	public void removeCurso(String nome) {
		for (int i = 0; i < this.cursos.size(); i++) {
			if (this.cursos.get(i).getNome().contentEquals(nome)) {
				this.cursos.remove(i);
				return;
			}
		}
	}

	public void listaCursos() {
		for (int i = 0; i < this.cursos.size(); i++) {
			System.out.println("\nCurso " + (i + 1));
			this.cursos.get(i).informacaoCurso();
		}
	}

	@Override
	public void respondeForum(Curso curso, String titulo, String pergunta, String resposta) {
		Forum f = null;
		for (int i = 0; i < curso.getConteudos().size(); i++)
			if (curso.getConteudos().get(i).getTipoConteudo().contentEquals("forum"))
				if (curso.getConteudos().get(i).getTitulo().contentEquals(titulo))
					f = (Forum) curso.getConteudos().get(i);

		if (f == null)
			return;

		Date data = new Date();

		// A resposta de um professor sempre será correta
		for (int j = 0; j < f.getPerguntas().size(); j++)
			if (f.getPerguntas().get(j).getTitulo().contentEquals(pergunta))
				f.getPerguntas().get(j).adicionaResposta(new Resposta(this, resposta, data, true));
	}

	public Curso getCurso(String nome) {
		for (int i = 0; i < this.cursos.size(); i++)
			if (this.cursos.get(i).getNome().contentEquals(nome))
				return this.cursos.get(i);
		return null;
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
