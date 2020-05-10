package doodle.forum;

import java.util.ArrayList;
import java.util.Date;

import doodle.entidades.Conteudo;

public class Forum extends Conteudo {
	private boolean aberto;
	private ArrayList<Pergunta> perguntas;

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
	}

	public void removePergunta(int indice) {
		this.perguntas.remove(indice);
	}

	public void listar() {
		for (int i = 0; i < this.perguntas.size(); i++) {
			System.out.println("\n	Pergunta " + (i + 1) + ": " + this.perguntas.get(i).getTitulo());
			System.out.println("	Duvida: " + this.perguntas.get(i).getDuvida());
			System.out.println("	Autor: " + this.perguntas.get(i).getAutor());
			System.out.println("	publicado em " + this.perguntas.get(i).getData());

			this.perguntas.get(i).listaRespostas();
		}
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

	@Override
	protected String tipoConteudo() {
		return "forum";
	}

}
