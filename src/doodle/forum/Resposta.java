package doodle.forum;

import java.text.SimpleDateFormat;
import java.util.Date;

import doodle.entidades.Pessoa;

public class Resposta {
	private Pessoa autor;
	private String resposta;
	private Date data;
	private boolean correta;

	public Resposta(Pessoa autor, String resposta, Date data) {
		this.autor = autor;
		this.resposta = resposta;
		this.data = data;
	}

	public Resposta(Pessoa autor, String resposta, Date data, boolean correta) {
		this.autor = autor;
		this.resposta = resposta;
		this.data = data;
		this.correta = correta;
	}

	public Pessoa getAutor() {
		return autor;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getData() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.data);
	}

	public boolean isCorreta() {
		return correta;
	}

	public void setCorreta(boolean correta) {
		this.correta = correta;
	}

}
