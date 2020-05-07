package doodle.forum;

import java.util.ArrayList;
import java.util.Date;

import doodle.entidades.Conteudo;

public class Forum extends Conteudo {
	private boolean aberto;
	private ArrayList<Pergunta> perguntas;
	private static int totalPerguntas = 0;

	public Forum(String titulo, String descricao, Date dataPublicacao) {
		super(titulo, descricao, dataPublicacao);
		this.aberto = true;
		this.perguntas = new ArrayList<Pergunta>();
	}

	public Forum(String titulo, String descricao, Date dataPublicacao, boolean prazo, Date dataInicio,
			Date dataTermino) {
		super(titulo, descricao, dataPublicacao, prazo, dataInicio, dataTermino);
		this.aberto = true;
		this.perguntas = new ArrayList<Pergunta>();
	}

	public void adicionaPergunta(Pergunta pergunta) {
		this.perguntas.add(pergunta);
		totalPerguntas++;
	}

	public void removePergunta(int indice) {
		this.perguntas.remove(indice);
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public ArrayList<Pergunta> getPerguntas() {
		return perguntas;
	}

	public static int getTotalPerguntas() {
		return totalPerguntas;
	}

}
