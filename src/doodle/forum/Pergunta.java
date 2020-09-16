package doodle.forum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import doodle.bd.RespostaDAO;
import doodle.entidades.Pessoa;

public class Pergunta {
	private int idPergunta;
	private Pessoa autor;
	private String titulo;
	private String duvida;
	private ArrayList<Resposta> respostas;
	private Date data;
	private int idForum;
	// Valor inicial baseado na quantidade de INSERTs iniciais em UtilBD
	private static int proxIDPergunta = 2;

	public Pergunta(Pessoa autor, String titulo, String duvida, Date data, int idForum, boolean contIDPergunta) {
		this.autor = autor;
		this.titulo = titulo;
		this.duvida = duvida;
		this.data = data;
		this.respostas = new ArrayList<Resposta>();
		this.idForum = idForum;
		if (contIDPergunta == true) {
			Pergunta.proxIDPergunta++;
			this.idPergunta = proxIDPergunta;
		}
	}

	public void adicionaResposta(Resposta resposta) {
		RespostaDAO respostaDAO = new RespostaDAO();
		respostaDAO.adicionar(resposta, null);
	}

	public void removeResposta(int indice) {
		this.respostas.remove(indice);
	}

	public void listaRespostas(int idForum) {
		RespostaDAO respostaDAO = new RespostaDAO();
		respostas = respostaDAO.listar(idForum);

		for (Resposta resposta : respostas) {
			if (resposta.getIDPergunta() == idPergunta) {
				System.out.println("\n		ID Resposta " + resposta.getIDResposta() + ": " + resposta.getResposta());
				System.out.println("		Autor: " + resposta.getAutor().getNome());
				System.out.println("		publicado em " + resposta.getData());
			}
		}
	}

	public int getIDPergunta() {
		return idPergunta;
	}

	public void setIDPergunta(int id) {
		this.idPergunta = id;
	}

	public String getAutor() {
		return autor.getNome();
	}

	public Pessoa getObjetoAutor() {
		return autor;
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

	public String getData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.data);
	}

	public int getIDForum() {
		return idForum;
	}

	public void setIDForum(int idForum) {
		this.idForum = idForum;
	}
}
