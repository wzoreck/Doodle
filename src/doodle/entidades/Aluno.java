package doodle.entidades;

import java.util.Date;

import doodle.forum.Forum;

public class Aluno extends Pessoa {

	private boolean matriculado;

	public Aluno(String nome, String cpf, char sexo, Date dataNascimento, String pais, String estado, String cidade,
			String email, String telefone) {
		super(nome, cpf, sexo, dataNascimento, pais, estado, cidade, email, telefone);
		this.matriculado = false;
	}

	public Aluno(String nome, String cpf, char sexo, Date dataNascimento, String pais, String estado, String cidade,
			String email, String telefone, boolean matriculado) {
		super(nome, cpf, sexo, dataNascimento, pais, estado, cidade, email, telefone);
		this.matriculado = matriculado;
	}

	//
	public void perguntaEmForum(Curso curso, Forum forum) {

	}
	
	public void enviaAtividade() {
		
	}
	
	public void visualizaTopico() {
		
	}
	//

	public boolean isMatriculado() {
		return matriculado;
	}

	public void setMatriculado(boolean estaAtivo) {
		this.matriculado = estaAtivo;
	}

}
