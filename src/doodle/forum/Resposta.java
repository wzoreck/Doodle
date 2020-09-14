package doodle.forum;

import java.text.SimpleDateFormat;
import java.util.Date;

import doodle.entidades.Pessoa;

public class Resposta {
	private int idResposta;
	private Pessoa autor;
	private String resposta;
	private Date data;
	private boolean correta;
	// Valor inicial baseado na quantidade de INSERTs iniciais em UtilBD
	private static int proxIDResposta = 1;

	public Resposta(Pessoa autor, String resposta, Date data) {
		this.autor = autor;
		this.resposta = resposta;
		this.data = data;
		Resposta.proxIDResposta++;
		this.idResposta = proxIDResposta;
	}

	public Resposta(Pessoa autor, String resposta, Date data, boolean correta) {
		this.autor = autor;
		this.resposta = resposta;
		this.data = data;
		this.correta = correta;
		Resposta.proxIDResposta++;
		this.idResposta = proxIDResposta;
	}

	public int getIDResposta() {
		return idResposta;
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
