package doodle.forum;

import java.util.ArrayList;
import java.util.Date;

import doodle.bd.PerguntaDAO;
import doodle.entidades.Conteudo;

public class Forum extends Conteudo {
	private int idForum;
	private boolean aberto;
	private ArrayList<Pergunta> perguntas;
	// Valor inicial baseado na quantidade de INSERTs iniciais em UtilBD
	private static int proxIDForum = 1;

	public Forum(String titulo, String descricao, Date dataPublicacao, boolean contID, boolean contIDForum) {
		super(titulo, descricao, dataPublicacao, contID);
		this.aberto = true;
		this.perguntas = new ArrayList<Pergunta>();
		if (contIDForum == true) {
			Forum.proxIDForum++;
			this.idForum = proxIDForum;
		}
	}

	public void adicionaPergunta(Pergunta pergunta) {
		PerguntaDAO perguntaDAO = new PerguntaDAO();
		ArrayList<Integer> id = new ArrayList<Integer>();
		perguntaDAO.adicionar(pergunta, id);
	}

	public void removePergunta(int indice) {
		this.perguntas.remove(indice);
	}

	public void listar() {
		PerguntaDAO perguntaDAO = new PerguntaDAO();
		perguntas = perguntaDAO.listar(this.getIDForum());
		
		for (Pergunta pergunta : perguntas) {
			System.out.println("\n	ID Pergunta " + pergunta.getIDPergunta() + ": " + pergunta.getTitulo());
			System.out.println("	Duvida: " + pergunta.getDuvida());
			System.out.println("	Autor: " + pergunta.getAutor());
			System.out.println("	publicado em " + pergunta.getData());

			pergunta.listaRespostas();
		}
	}

	public int getIDForum() {
		return idForum;
	}

	public void setIDForum(int id) {
		this.idForum = id;
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
