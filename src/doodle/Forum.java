package doodle;

import java.util.ArrayList;
import java.util.Date;

public class Forum {
	private String titulo;
	private String conteudo;
	private ArrayList<Forum> respostas;
	private Date dataPostagem;
	
	public Forum(String titulo, String conteudo, Date dataPostagem) {
		super();
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.dataPostagem = dataPostagem;
		this.respostas = new ArrayList<Forum>();
	}

	public void adicionaResposta(Forum resposta) {
		respostas.add(resposta);
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getConteudo() {
		return conteudo;
	}
	
	public ArrayList<Forum> getRespostas() {
		return respostas;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}
	
}
