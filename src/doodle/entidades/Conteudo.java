package doodle.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Conteudo {
	protected String titulo;
	protected String descricao;
	protected Date dataPublicacao;
	protected boolean prazo;
	protected Date dataInicio;
	protected Date dataTermino;
	
	public Conteudo(String titulo, String descricao, Date dataPublicacao) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataPublicacao = dataPublicacao;
	}

	public Conteudo(String titulo, String descricao, Date dataPublicacao, boolean prazo, Date dataInicio,
			Date dataTermino) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataPublicacao = dataPublicacao;
		this.prazo = prazo;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataPublicacao() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dataPublicacao);
	}

	public boolean isPrazo() {
		return prazo;
	}

	public void setPrazo(Date dataTermino) {
		this.prazo = true;
		this.dataTermino = dataTermino;
	}

	public String getDataInicio() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dataInicio);
	}

	public String getDataTermino() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dataTermino);
	}
	
	public String getTipoConteudo() {
		return tipoConteudo();
	}
	
	// Método abstrato
	protected abstract String tipoConteudo();
}
