package doodle.entidades;

import java.util.ArrayList;
import java.util.Date;

public class Aluno extends Pessoa {
	private boolean matriculado;
	private ArrayList<String> certificados;

	public Aluno(String nome, String email, Date dataNascimento) {
		super(nome, email, dataNascimento);
		this.matriculado = false;
		this.certificados = new ArrayList<String>();
	}
	
	public Aluno(String nome, String email, Date dataNascimento, String login, String passwd) {
		super(nome, email, dataNascimento, login, passwd);
		this.matriculado = false;
		this.certificados = new ArrayList<String>();
	}

	public void adicionaCertificado(String certificado) {
		this.certificados.add(certificado);
	}
	
	public boolean isMatriculado() {
		return matriculado;
	}

	public void setMatriculado(boolean matriculado) {
		this.matriculado = matriculado;
	}

	public ArrayList<String> getCertificados() {
		return certificados;
	}

	@Override
	protected String tipoPessoa() {
		return "aluno";
	}
}
