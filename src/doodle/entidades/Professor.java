package doodle.entidades;

import java.util.Date;

import doodle.forum.Forum;

public class Professor extends Pessoa {
	private int registro;
	private char formacao;

	public Professor(String nome, String cpf, char sexo, Date dataNascimento, String pais, String estado, String cidade,
			int registro, char formacao) {
		super(nome, cpf, sexo, dataNascimento, pais, estado, cidade);
		this.registro = registro;
		this.formacao = formacao;
	}

	public Professor(String nome, String cpf, char sexo, Date dataNascimento, String pais, String estado, String cidade,
			String email, String telefone, int registro, char formacao) {
		super(nome, cpf, sexo, dataNascimento, pais, estado, cidade, email, telefone);
		this.registro = registro;
		this.formacao = formacao;
	}
	
	@Override
	public void respondeEmForum(Curso curso, Forum forum) {
		
	}

	public int getRegistro() {
		return registro;
	}

	public String getFormacao() {
		switch (this.formacao) {
		case 'G':
			return "Graduado";
		case 'M':
			return "Mestre";
		case 'D':
			return "Doutor";
		}
		return "Formação Desconhecida";
	}

	public void setFormacao(char formacao) {
		this.formacao = formacao;
	}

}
