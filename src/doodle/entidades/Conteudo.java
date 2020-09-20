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
	
	public Conteudo(String titulo, String descricao, Date dataPublicacao, boolean contID) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataPublicacao = dataPublicacao;
		if (contID == true) {
			Conteudo.proxID++;
			this.id = proxID;
		}
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dataPublicacao);
	}
	
	public void setDataPublicacao(Date data) {
		this.dataPublicacao = data;
	}
	
	public String getTipoConteudo() {
		return tipoConteudo();
	}
	
	// Método abstrato
	protected abstract String tipoConteudo();
}
