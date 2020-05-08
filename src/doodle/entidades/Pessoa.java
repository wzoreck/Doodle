package doodle.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Pessoa {
	private String nome;
	private String email;
	private Date dataNascimento;
	private String login;
	private String passwd;

	public Pessoa(String nome, String email, Date dataNascimento) {
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
	}

	public Pessoa(String nome, String email, Date dataNascimento, String login, String passwd) {
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.login = login;
		this.passwd = passwd;
	}

	public boolean validaUsuario(String login, String passwd) {
		if (this.login.contentEquals(login) && this.passwd.contentEquals(passwd))
			return true;
		else
			return false;
	}

	public String getNome() {
		return nome;
	}

	public String getDataNascimento() {
		// Formata a data e retorna como String
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dataNascimento);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getTipoPessoa() {
		return tipoPessoa();
	}
	
	// Metodo Abstrato
	protected abstract String tipoPessoa();
}
