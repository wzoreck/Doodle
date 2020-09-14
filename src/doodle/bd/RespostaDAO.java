package doodle.bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doodle.forum.Resposta;

public class RespostaDAO implements InterfaceDAO<Resposta> {

	@Override
	public void adicionar(Resposta resposta, ArrayList<Integer> id) {
		// id_resposta, id_forum (id-0), id_pergunta (id-1), id_autor (id-2), resposta, data, correta
		try {
			String queryPergunta = "INSERT INTO resposta_forum VALUES (NULL," + id.get(0) + "," + id.get(1) + ","
					+ id.get(2) + ", '" + resposta.getResposta() + "','" + resposta.getData() + "', '"
					+ resposta.isCorreta() + "')";

			UtilBD.alterarBd(queryPergunta);

		} catch (SQLException e1) {
			System.err.println("Falha ao inserir Resposta no banco de dados");
		}
	}

	@Override
	public List<Resposta> listar(int aux) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Resposta referencia) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Resposta referencia) {
		// TODO Auto-generated method stub

	}

}
