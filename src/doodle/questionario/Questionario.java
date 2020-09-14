package doodle.questionario;

import java.util.ArrayList;
import java.util.Date;

import doodle.entidades.Conteudo;

public class Questionario extends Conteudo {

	private int idQuestionario;
	private ArrayList<String> questoes;
	// Valor inicial baseado na quantidade de INSERTs iniciais em UtilBD
	private static int proxIDQuestao = 5;

	public Questionario(String titulo, String descricao, Date data) {
		super(titulo, descricao, data);
		questoes = new ArrayList<String>();
		Questionario.proxIDQuestao++;
		this.idQuestionario = proxIDQuestao;
	}

	public void adicionaQuestao(String questao) {
		this.questoes.add(questao);
	}

	public void listaQuestoes() {
		for (int i = 0; i < this.questoes.size(); i++)
			System.out.println("Questão " + (i + 1) + ": " + this.questoes.get(i));
	}

	public int getIDQuestionario() {
		return idQuestionario;
	}

	public ArrayList<String> getQuestoes() {
		return questoes;
	}

	@Override
	protected String tipoConteudo() {
		return "questionario";
	}

}
