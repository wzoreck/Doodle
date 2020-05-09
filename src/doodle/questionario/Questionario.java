package doodle.questionario;

import java.util.ArrayList;
import java.util.Date;

import doodle.entidades.Conteudo;

public class Questionario extends Conteudo {

	private ArrayList<String> questoes;

	public Questionario(String titulo, String descricao, Date data) {
		super(titulo, descricao, data);
		questoes = new ArrayList<String>();
	}

	public Questionario(String titulo, String descricao, Date data, Date dataInicio, Date dataFim) {
		super(titulo, descricao, data, true, dataInicio, dataFim);
		questoes = new ArrayList<String>();
	}

	public void adicionaQuestao(String questao) {
		this.questoes.add(questao);
	}

	public void listaQuestoes() {
		for (int i = 0; i < this.questoes.size(); i++)
			System.out.println("Questão " + (i + 1) + ": " + this.questoes.get(i));
	}

	@Override
	protected String tipoConteudo() {
		return "questionario";
	}

}
