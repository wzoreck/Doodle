package doodle.forum;

import java.util.ArrayList;
import java.util.Date;

import doodle.entidades.Pessoa;

public class Pergunta {
	private Pessoa autor;
	private String titulo;
	private String duvida;
	private ArrayList<Resposta> respostas;
	private Date data;
	private static int totalRespostas = 0;
	
	public Pergunta(Pessoa autor, String duvida, Date data) {
		this.autor = autor;
		this.duvida = duvida;
		this.data = data;
		this.respostas = new ArrayList<Resposta>();
	}

	public Pergunta(Pessoa autor, String titulo, String duvida, Date data) {
		this.autor = autor;
		this.titulo = titulo;
		this.duvida = duvida;
		this.data = data;
		this.respostas = new ArrayList<Resposta>();
	}
	
	public void adicionaResposta(Resposta resposta) {
		this.respostas.add(resposta);
		totalRespostas++;
	}
	
	public void removeResposta(int indice) {
		this.respostas.remove(indice);
	}

	public String getAutor() {
		return autor.getNome();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDuvida() {
		return duvida;
	}

	public void setDuvida(String duvida) {
		this.duvida = duvida;
	}

	public ArrayList<Resposta> getRespostas() {
		return respostas;
	}

	public Date getData() {
		return data;
	}

	public static int getTotalRespostas() {
		return totalRespostas;
	}
	
}
