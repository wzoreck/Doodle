package doodle.entidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import doodle.forum.Forum;
import doodle.forum.Pergunta;
import doodle.forum.Resposta;

public abstract class Pessoa {
	private int id;
	private String nome;
	private String email;
	private Date dataNascimento;
	private String login;
	private String passwd;
	protected ArrayList<Curso> cursos;
	// Valor inicial baseado na quantidade de INSERTs iniciais em UtilBD
	public static int proxID = 5;

	public Pessoa(String nome, String email, Date dataNascimento, String login, String passwd, boolean contID) {
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.login = login;
		this.passwd = passwd;
		this.cursos = new ArrayList<Curso>();
		if (contID == true) {
			Pessoa.proxID++;
			this.id = proxID;
		}
	}

	public boolean validaUsuario(String login, String passwd) {
		if (this.login.contentEquals(login) && this.passwd.contentEquals(passwd))
			return true;
		else
			return false;
	}

	public void respondeForum(Curso curso, int idForum, int idPergunta, String resposta) {
		ArrayList<Forum> foruns = new ArrayList<Forum>();
		ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>();
		foruns = curso.getConteudos();
		boolean correta = false;
		
		if (this.getTipoPessoa() == "professor") {
			correta = true;
		}
		
		for (Forum forum : foruns) {
			if (forum.getIDForum() == idForum) {
				perguntas = forum.getPerguntas();
				for (Pergunta pergunta : perguntas) {
					if (pergunta.getIDPergunta() == idPergunta) {
						Date data = new Date();
						pergunta.adicionaResposta(new Resposta(idPergunta, idForum, this, resposta, data, correta, false));
					}
				}
			}
		}
	}

	public Curso getCurso(String nome) {
		for (int i = 0; i < this.cursos.size(); i++)
			if (this.cursos.get(i).getNome().contentEquals(nome))
				return this.cursos.get(i);
		return null;
	}

	public void listaCursos() {
		for (int i = 0; i < this.cursos.size(); i++)
			System.out.println("Curso " + (i + 1) + ": " + this.cursos.get(i).getNome());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		// Formata a data e retorna como String
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dataNascimento);
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswd() {
		return passwd;
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
