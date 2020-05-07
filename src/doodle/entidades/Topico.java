package doodle.entidades;

import java.util.ArrayList;
import java.util.Date;

import doodle.forum.Forum;

public class Topico extends Conteudo{
	
	private ArrayList<Forum> foruns;
	private static int totalForuns = 0;
	
	public Topico(String titulo, String descricao, Date data) {
		super(titulo, descricao, data);
		foruns = new ArrayList<Forum>();
	}
	
	public Topico(String titulo, String descricao, Date data, Date dataInicio, Date dataFim) {
		super(titulo, descricao, data, true, dataInicio, dataFim);
		foruns = new ArrayList<Forum>();
	}
	
	public void adicionaForum(String titulo, String descricao, Date dataPublicacao) {
		foruns.add(new Forum(titulo, descricao, dataPublicacao));
		totalForuns++;
	}
	
	public ArrayList<Forum> getForuns() {
		return foruns;
	}

	public static int getTotalForuns() {
		return totalForuns;
	}

	public static void setTotalForuns(int totalForuns) {
		Topico.totalForuns = totalForuns;
	}
	
}
