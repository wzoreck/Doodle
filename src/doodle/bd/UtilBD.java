package doodle.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilBD {
	private static Connection conexao;

	public static Connection getConexao() {
		try {
			System.out.println("Entrou getConexao()");
			if (conexao == null)
				abrirConexao();

			if (conexao.isClosed())
				abrirConexao();

		} catch (SQLException exception) {
			System.err.println("Ocorreu um erro com o método abrirConexao()!");
		}

		return conexao;
	}

	public static void abrirConexao() {
		try {
			System.out.println("Entrou abrirConexao()");
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
		} catch (SQLException exception) {
			System.err.println("Não foi possível abrir a conexão com o banco!");
		} catch (ClassNotFoundException exception) {
			System.err.println("O Driver SQLite não está funcionando corretamente!");
		}
	}

	public static void fecharConexao() {
		if (conexao == null)
			return;

		try {

			if (!conexao.isClosed())
				conexao.close();

		} catch (SQLException exception) {
			System.err.println("Não foi possível fechar a conexão com o banco!");
		}
	}

	public static void initBD() {
		try {
			System.out.println("1234");
			conexao = getConexao();
			Statement statement = conexao.createStatement();

			criarPessoa(statement);
			criarAluno(statement);
			criarProfessor(statement);
			criarCurso(statement);
			criarMatricula(statement);
			criarConteudo(statement);
			criarQuestionario(statement);
			criarForum(statement);
			criarPerguntaForum(statement);
			criarRespostaForum(statement);

			statement.close();
		} catch (SQLException exception) {
			System.err.println("Falha ao criar o banco!");
		}
	}

	private static void criarPessoa(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS pessoa");

		stm.executeUpdate("CREATE TABLE pessoa (" + "id_pessoa INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
				+ "nome VARCHAR(100) NOT NULL," + "email VARCHAR(100) NOT NULL,"
				+ "data_nascimento VARCHAR(10) NOT NULL," + "login VARCHAR(50) NOT NULL UNIQUE,"
				+ "passwd VARCHAR(50) NOT NULL)");

		stm.executeUpdate("INSERT INTO pessoa VALUES"
				+ "(NULL, 'Joao Nico', 'joao@aluno.email.com', '2000-01-01', 'joao', '1234')");
		stm.executeUpdate("INSERT INTO pessoa VALUES"
				+ "(NULL, 'Lucas', 'lucas@professor.email.com', '1980-08-28', 'lucas', '1234')");
		stm.executeUpdate("INSERT INTO pessoa VALUES"
				+ "(NULL, 'Daniel Wzoreck', 'daniel@aluno.email.com', '2000-08-07', 'daniel', '1234')");
		stm.executeUpdate("INSERT INTO pessoa VALUES"
				+ "(NULL, 'Ana Luiza', 'ana@aluno.email.com', '1999-02-05', 'ana', '1234')");
		stm.executeUpdate("INSERT INTO pessoa VALUES"
				+ "(NULL, 'Luciano', 'luciano@professor.email.com', '1979-06-15', 'luciano', '1234')");
	}

	private static void criarAluno(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS aluno");

		stm.executeUpdate("CREATE TABLE aluno (" + "id_aluno INTEGER NOT NULL UNIQUE,"
				+ "matriculado BOOLEAN NOT NULL DEFAULT FALSE,"
				+ "FOREIGN KEY (id_aluno) REFERENCES pessoa (id_pessoa) ON DELETE CASCADE)");

		stm.executeUpdate("INSERT INTO aluno VALUES" + "(1, FALSE)");
		stm.executeUpdate("INSERT INTO aluno VALUES" + "(3, FALSE)");
		stm.executeUpdate("INSERT INTO aluno VALUES" + "(4, FALSE)");
	}

	private static void criarProfessor(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS professor");

		stm.executeUpdate("CREATE TABLE professor (" + "id_professor INTEGER NOT NULL UNIQUE," + "salario NUMERIC,"
				+ "carga_horaria_semanal INTEGER,"
				+ "FOREIGN KEY (id_professor) REFERENCES pessoa (id_pessoa) ON DELETE CASCADE)");

		stm.executeUpdate("INSERT INTO professor VALUES" + "(2, 3250.27, 20)");
		stm.executeUpdate("INSERT INTO professor VALUES" + "(5, 4100.52, 10)");
	}

	private static void criarCurso(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS curso");

		stm.executeUpdate("CREATE TABLE curso (" + "id_curso INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
				+ "nome VARCHAR(70) NOT NULL UNIQUE," + "data_inicio VARCHAR(10) NOT NULL," + "id_professor INTEGER NOT NULL,"
				+ "FOREIGN KEY (id_professor) REFERENCES pessoa (id_pessoa) ON DELETE CASCADE)");

		stm.executeUpdate("INSERT INTO curso VALUES" + "(NULL, 'POO1-2020', '2020-01-01', 2)");
		stm.executeUpdate("INSERT INTO curso VALUES" + "(NULL, 'Redes-2020', '2020-01-01', 5)");
	}

	private static void criarMatricula(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS matricula_curso");

		stm.executeUpdate("CREATE TABLE matricula_curso (" + "id_aluno INTEGER NOT NULL," + "id_curso INTEGER NOT NULL,"
				+ "PRIMARY KEY(id_aluno, id_curso)"
				+ "FOREIGN KEY (id_aluno) REFERENCES pessoa (id_pessoa) ON DELETE CASCADE,"
				+ "FOREIGN KEY (id_curso) REFERENCES curso (id_curso) ON DELETE CASCADE)");

		stm.executeUpdate("INSERT INTO matricula_curso VALUES" + "(1, 1)");
		stm.executeUpdate("INSERT INTO matricula_curso VALUES" + "(1, 2)");
		stm.executeUpdate("INSERT INTO matricula_curso VALUES" + "(3, 1)");
		stm.executeUpdate("INSERT INTO matricula_curso VALUES" + "(3, 2)");
		stm.executeUpdate("INSERT INTO matricula_curso VALUES" + "(4, 1)");
		stm.executeUpdate("INSERT INTO matricula_curso VALUES" + "(4, 2)");
	}

	private static void criarConteudo(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS conteudo");

		stm.executeUpdate("CREATE TABLE conteudo (" + "id_conteudo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
				+ "id_curso INTEGER NOT NULL," + "titulo VARCHAR(50) NOT NULL UNIQUE," + "descricao VARCHAR(50) NOT NULL,"
				+ "data_publicacao VARCHAR(10) NOT NULL," + "prazo BOOLEAN," + "data_inicio VARCHAR(10),"
				+ "data_termino VARCHAR(10),"
				+ "FOREIGN KEY (id_curso) REFERENCES curso (id_curso) ON DELETE CASCADE)");

		stm.executeUpdate("INSERT INTO conteudo VALUES"
				+ "(NULL, 1, 'Trabalho etapa 1', 'Abordar os conceitos de Abstração e Encapsulamento', '2020-03-20', TRUE, '2020-03-20', '2020-04-20')");
		stm.executeUpdate("INSERT INTO conteudo VALUES"
				+ "(NULL, 2, 'Forum Dúvidas', 'Para dúvidas que surgirem na matéria', '2020-04-18', TRUE, '2020-04-18', '2020-12-30')");
		stm.executeUpdate("INSERT INTO conteudo VALUES"
				+ "(NULL, 1, 'Questionario 1', 'Referente a POO', '2020-07-05', TRUE, '2020-07-05', '2020-07-25')");
	}

	private static void criarQuestionario(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS questionario");

		stm.executeUpdate("CREATE TABLE questionario (" + "id_questao INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "id_conteudo INTEGER NOT NULL," + "questao VARCHAR(1000) NOT NULL,"
				+ "FOREIGN KEY (id_conteudo) REFERENCES conteudo (id_conteudo) ON DELETE CASCADE)");

		stm.executeUpdate(
				"INSERT INTO questionario VALUES" + "(NULL, 3, 'Para que serve o paradigma de Orientação a Objetos?')");
		stm.executeUpdate("INSERT INTO questionario VALUES"
				+ "(NULL, 3, 'Quais são os quatro pilares da Orientação a Objetos?')");
		stm.executeUpdate("INSERT INTO questionario VALUES" + "(NULL, 3, 'Defina o processo de herança?')");
		stm.executeUpdate("INSERT INTO questionario VALUES" + "(NULL, 3, 'Qual a utilidade do polimorfismo?')");
		stm.executeUpdate("INSERT INTO questionario VALUES"
				+ "(NULL, 3, 'Expliqu o que significa [establecer um contrato] quando falamos de interface?')");
	}

	private static void criarForum(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS forum");

		stm.executeUpdate("CREATE TABLE forum (" + "id_forum INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
				+ "id_conteudo INTEGER NOT NULL," + "aberto	BOOLEAN NOT NULL DEFAULT 'TRUE',"
				+ "FOREIGN KEY (id_conteudo) REFERENCES conteudo (id_conteudo) ON DELETE CASCADE)");

		stm.executeUpdate("INSERT INTO FORUM VALUES" + "(NULL, 2, TRUE)");
	}

	private static void criarPerguntaForum(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS pergunta_forum");

		stm.executeUpdate("CREATE TABLE pergunta_forum ("
				+ "id_pergunta INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," + "id_forum INTEGER NOT NULL,"
				+ "id_autor INTEGER NOT NULL," + "titulo VARCHAR(150) NOT NULL," + "duvida VARCHAR(1000) NOT NULL,"
				+ "data TEXT NOT NULL," + "FOREIGN KEY (id_forum) REFERENCES forum (id_forum),"
				+ "FOREIGN KEY (id_autor) REFERENCES pessoa (id_pessoa))");

		stm.executeUpdate("INSERT INTO pergunta_forum VALUES"
				+ "(NULL, 1, 3, 'Como utilizar listas em Java?', 'Dúvida que surgiu durante a 1ª etapa do projeto', '2020-04-11')");
		stm.executeUpdate("INSERT INTO pergunta_forum VALUES"
				+ "(NULL, 1, 1, 'Como utilizar vetores em Java?', 'Dúvida que surgiu durante o projeto', '2020-04-17')");
	}

	private static void criarRespostaForum(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS resposta_forum");

		stm.executeUpdate("CREATE TABLE resposta_forum ("
				+ "id_resposta INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," + "id_forum INTEGER NOT NULL,"
				+ "id_pergunta INTEGER NOT NULL," + "id_autor INTEGER NOT NULL," + "resposta VARCHAR(1000) NOT NULL,"
				+ "data VARCHAR(10) NOT NULL," + "correta BOOLEAN NOT NULL DEFAULT 0,"
				+ "FOREIGN KEY (id_pergunta) REFERENCES pergunta_forum (id_pergunta) ON DELETE CASCADE,"
				+ "FOREIGN KEY (id_forum) REFERENCES forum (id_forum) ON DELETE CASCADE,"
				+ "FOREIGN KEY (id_autor) REFERENCES pessoa (id_pessoa) ON DELETE CASCADE)");

		stm.executeUpdate("INSERT INTO resposta_forum VALUES"
				+ "(NULL, 1, 1, 2, 'Basta utilizar a classe List ou Arraylist!"
				+ "E informar o tipo que será a lista, ela é modelada como tipo genérico!', '2020-06-17', TRUE)");
	}

	public static void alterarBd(String sql) throws SQLException {
		Connection bd = getConexao();
		Statement stm = bd.createStatement();
		stm.executeUpdate(sql);
		stm.close();
	}
}