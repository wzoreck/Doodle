package doodle.entidades;

import java.util.ArrayList;
import java.util.Date;

import doodle.forum.Forum;

public class Professor extends Pessoa {
	private int registro;
	private char formacao;
	private ArrayList<Curso> cursos;

	public Professor(String nome, String cpf, char sexo, Date dataNascimento, String pais, String estado, String cidade,
			int registro, char formacao) {
		super(nome, cpf, sexo, dataNascimento, pais, estado, cidade);
		this.registro = registro;
		this.formacao = formacao;
		this.cursos = new ArrayList<Curso>();
	}

	public Professor(String nome, String cpf, char sexo, Date dataNascimento, String pais, String estado, String cidade,
			String email, String telefone, int registro, char formacao) {
		super(nome, cpf, sexo, dataNascimento, pais, estado, cidade, email, telefone);
		this.registro = registro;
		this.formacao = formacao;
		this.cursos = new ArrayList<Curso>();
	}

	public void criarCurso(Curso curso) {
		this.cursos.add(curso);
	}

	public void removerCurso(String nome) {
		for (int i = 0; i < cursos.size(); i++) {
			if (cursos.get(i).getNome().contentEquals(nome)) {
				cursos.remove(i);
				break;
			}
		}
	}

	//
	public void criarTopico(Curso curso) {

	}

	public void removerTopico(Curso curso, Topico topico) {

	}

	public void criarAtividade(Curso curso, Topico topico) {

	}

	public void removerAtividade(Curso curso, Topico topico) {

	}

	public void criarForum(Curso curso, Topico topico) {

	}

	public void removerForum(Curso curso, Topico topico, Forum forum) {

	}
	//

	public int getRegistro() {
		return registro;
	}

	public char getFormacao() {
		return formacao;
	}

	public void setFormacao(char formacao) {
		this.formacao = formacao;
	}

}
