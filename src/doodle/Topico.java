package doodle;

import java.util.ArrayList;

public class Topico {
	private String titulo;
	private String descricao;
	private ArrayList<Forum> foruns;
	
	public Topico(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
		foruns = new ArrayList<Forum>();
	}
	
	public void adicionaForum(Forum forum) {
		for(int i=0; i < foruns.size(); i++)
			if(foruns.get(i).getTitulo().contentEquals(forum.getTitulo()))
				return;
		foruns.add(forum);
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public ArrayList<Forum> getForuns() {
		return foruns;
	}
	
	/*
	public void removeForum(Forum forum) {
		for(int i=0; i < foruns.size(); i++) {
			if(foruns.get(i).getTitulo().contentEquals(forum.getTitulo())) {
				foruns.remove(i);
				return;
			}
		}
	}
	*/
	
}
