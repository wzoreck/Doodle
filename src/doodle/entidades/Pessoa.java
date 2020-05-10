package doodle.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

import doodle.forum.Forum;
import doodle.forum.Resposta;

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

	public void respondeForum(Curso curso, String titulo, String pergunta, String resposta) {
		Forum f = null;
		for (int i = 0; i < curso.getConteudos().size(); i++)
			if (curso.getConteudos().get(i).getTipoConteudo().contentEquals("forum"))
				if (curso.getConteudos().get(i).getTitulo().contentEquals(titulo))
					f = (Forum) curso.getConteudos().get(i);

		if (f == null)
			return;

		Date data = new Date();

		for (int j = 0; j < f.getPerguntas().size(); j++)
			if (f.getPerguntas().get(j).getTitulo().contentEquals(pergunta))
				f.getPerguntas().get(j).adicionaResposta(new Resposta(this, resposta, data));
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTipoPessoa() {
		return tipoPessoa();
	}

	// Metodo Abstrato
	protected abstract String tipoPessoa();
}
