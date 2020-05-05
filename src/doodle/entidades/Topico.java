package doodle.entidades;

import java.util.ArrayList;
import java.util.Date;

import doodle.forum.Forum;

public class Topico extends Conteudo{
	
	private ArrayList<Forum> foruns;
	
	public Topico(String titulo, String descricao, Date data) {
		super(titulo, descricao, data);
		foruns = new ArrayList<Forum>();
	}
	
	public Topico(String titulo, String descricao, Date data, Date dataInicio, Date dataFim) {
		super(titulo, descricao, data, true, dataInicio, dataFim);
		foruns = new ArrayList<Forum>();
	}
	
	public ArrayList<Forum> getForuns() {
		return foruns;
	}
	
}
