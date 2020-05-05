package doodle.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

import doodle.forum.Forum;
import doodle.forum.Pergunta;

public class Pessoa {
	private String nome;
	private String cpf;
	private char sexo;
	private Date dataNascimento;
	private String[] localOrigem = new String[3];
	private String email;
	private String telefone;

	public Pessoa(String nome, String cpf, char sexo, Date dataNascimento, String pais, String estado, String cidade) {
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.localOrigem[0] = pais;
		this.localOrigem[1] = estado;
		this.localOrigem[2] = cidade;
	}

	public Pessoa(String nome, String cpf, char sexo, Date dataNascimento, String pais, String estado, String cidade,
			String email, String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.localOrigem[0] = pais;
		this.localOrigem[1] = estado;
		this.localOrigem[2] = cidade;
		this.email = email;
		this.telefone = telefone;
	}

	//
	public void respondeEmForum(Curso curso, Forum forum) {

	}
	
	public void removePerguntaEmForum(Curso curso, Forum forum, Pergunta pergunta) {
		
	}
	//
	
	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public char getSexo() {
		return sexo;
	}

	public String getDataNascimento() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dataNascimento);
	}

	public String getLocalOrigem() {
		return localOrigem[0] + " - " + localOrigem[2] + "/" + localOrigem[1];
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
