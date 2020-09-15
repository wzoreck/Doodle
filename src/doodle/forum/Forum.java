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
		for (int i = 0; i < this.perguntas.size(); i++) {
			System.out.println("\n	Pergunta " + (i + 1) + ": " + this.perguntas.get(i).getTitulo());
			System.out.println("	Duvida: " + this.perguntas.get(i).getDuvida());
			System.out.println("	Autor: " + this.perguntas.get(i).getAutor());
			System.out.println("	publicado em " + this.perguntas.get(i).getData());

			this.perguntas.get(i).listaRespostas();
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
