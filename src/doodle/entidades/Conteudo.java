package doodle.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Conteudo {
	protected int id;
	protected String titulo;
	protected String descricao;
	protected Date dataPublicacao;
	// Valor inicial baseado na quantidade de INSERTs iniciais em UtilBD
	private static int proxID = 3;
	
	public Conteudo(String titulo, String descricao, Date dataPublicacao) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataPublicacao = dataPublicacao;
		Conteudo.proxID++;
		this.id = proxID;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public String getTipoConteudo() {
		return tipoConteudo();
	}
	
	// Método abstrato
	protected abstract String tipoConteudo();
}
