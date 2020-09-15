package doodle.forum;

import java.text.SimpleDateFormat;
import java.util.Date;

import doodle.entidades.Pessoa;

public class Resposta {
	private int idResposta;
	private int idPergunta;
	private int idForum;
	private Pessoa autor;
	private String resposta;
	private Date data;
	private boolean correta;
	// Valor inicial baseado na quantidade de INSERTs iniciais em UtilBD
	private static int proxIDResposta = 1;

	public Resposta(int idPergunta, int idForum, Pessoa autor, String resposta, Date data, boolean correta,
			boolean proxIdResposta) {
		this.idPergunta = idPergunta;
		this.idForum = idForum;
		this.autor = autor;
		this.resposta = resposta;
		this.data = data;
		this.correta = correta;
		if (proxIdResposta == true) {
			Resposta.proxIDResposta++;
			this.idResposta = proxIDResposta;
		}
	}

	public int getIDResposta() {
		return idResposta;
	}

	public int getIDPergunta() {
		return idPergunta;
	}

	public int getIDForum() {
		return idForum;
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
