package doodle.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilBD {
	private Connection conexao;

	public Connection getConexao() {
		try {

			if (conexao == null)
				abrirConexao();

			if (conexao.isClosed())
				abrirConexao();

		} catch (SQLException exception) {
			System.err.println("Ocorreu um erro com o método abrirConexao()!");
		}

		return conexao;
	}

	public void abrirConexao() {
		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:banco.sqlite");
		} catch (SQLException exception) {
			System.err.println("Não foi possível abrir a conexão com o banco!");
		} catch (ClassNotFoundException exception) {
			System.err.println("O Driver SQLite não está funcionando corretamente!");
		}
	}

	public void fecharConexao() {
		if (conexao == null)
			return;

		try {

			if (!conexao.isClosed())
				conexao.close();

		} catch (SQLException exception) {
			System.err.println("Não foi possível fechar a conexão com o banco!");
		}
	}

	public void initBD() {
		try {
			conexao = getConexao();
			Statement statemant = conexao.createStatement();
			// Adicionar as querys SQL aqui para criação da estrutura do banco
			statemant.executeUpdate("");
		} catch (SQLException exception) {
			System.err.println("Falha ao criar o banco!");
		}
	}
	
}