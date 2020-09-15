// Doodle é baseado no Moodle ;)

//    - Organize seu projeto de tal forma que ele contenha no mínimo 2 pacotes (5% da nota);
//    - Todas as classes devem possuir pelo menos uma sobrecarga do método construtor, que faça sentido (10% da nota);
//    - Substitua todos os usos de vetores com listas (35% da nota);
//        - Na Main, deve ser possível adicionar, listar, atualizar e remover objetos de cada uma das entidades (no mínimo 5) do projeto.
//        - Não é necessário editar as classes associadas (referenciadas)
//    - Crie uma hierarquia com herança, que faça sentido, onde exista pelo menos uma classe mãe e duas classes filhas (20% da nota);
//        - A hierarquia na qual a mãe é a classe Pessoa (ou similar) não conta;
//    - Na Main, trabalhe com polimorfismo, isto é, com referências para as classe mães, quando existirem (15% da nota);
//    - Toda classe mãe deverá ser abstrata e possuir pelo menos um método abstrato (15% da nota).

package doodle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import doodle.bd.AlunoDAO;
import doodle.bd.ForumDAO;
import doodle.bd.PerguntaDAO;
import doodle.bd.ProfessorDAO;
import doodle.bd.RespostaDAO;
import doodle.bd.UtilBD;
import doodle.entidades.Aluno;
import doodle.entidades.Curso;
import doodle.entidades.Pessoa;
import doodle.entidades.Professor;

public class Main {

	public static void main(String[] args) throws ParseException {

		// Criar o banco de dados e inserir os primeiros registros
		UtilBD.initBD();
		UtilBD.fecharConexao();

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato da data
		Date data = new Date();

		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		ArrayList<Professor> professores = new ArrayList<Professor>();

		Professor professor = null;
		Aluno aluno = null;
		Curso curso = null;

		// Objetos DAO
		AlunoDAO alunoDAO = new AlunoDAO();
		ProfessorDAO professorDAO = new ProfessorDAO();

		ForumDAO forumDAO = new ForumDAO();
		PerguntaDAO perguntaDAO = new PerguntaDAO();
		RespostaDAO respostaDAO = new RespostaDAO();

		String nome, email, nomeUsuario, senha, titulo, descricao, pergunta, resposta, titulo2;
		boolean controle = true, controle2 = true;
		int escolha1 = 0, escolha2, escolhaCurso, cargaHorariaSemanal, id;
		float salario;

		while (true) {
			controle = true;

			while (controle) {
				alunos = alunoDAO.listar(0);
				professores = professorDAO.listar(0);
				pessoas = new ArrayList<Pessoa>();
				for (Aluno a : alunos)
					pessoas.add(a);
				for (Professor p : professores)
					pessoas.add(p);
				
				System.out.println("\n[1] - criar professor");
				System.out.println("[2] - crirar aluno");
				System.out.println("[3] - remover pessoa");
				System.out.println("[4] - alterar pessoa");
				System.out.println("[5] - listar pessoas");
				System.out.println("[6] - fazer login");
				System.out.print("Informe sua escolha: ");
				escolha1 = sc.nextInt();
				sc.nextLine();

				switch (escolha1) {

				case 1:
					System.out.print("\nNome: ");
					nome = sc.nextLine();
					System.out.print("Email: ");
					email = sc.nextLine();
					System.out.print("Data de Nascimento (DD/MM/AAAA): ");
					data = sdf.parse(sc.nextLine());
					System.out.print("Salario: ");
					salario = sc.nextFloat();
					System.out.print("Carga Horaria Semanal: ");
					cargaHorariaSemanal = sc.nextInt();
					sc.nextLine();
					System.out.print("Escolha um nome de usuário: ");
					nomeUsuario = sc.nextLine();
					System.out.print("Escolha uma senha: ");
					senha = sc.nextLine();

					professor = new Professor(nome, email, data, nomeUsuario, senha, salario, cargaHorariaSemanal,
							true);
					professorDAO.adicionar(professor, null);
					break;

				case 2:
					System.out.print("\nNome: ");
					nome = sc.nextLine();
					System.out.print("Email: ");
					email = sc.nextLine();
					System.out.print("Data de Nascimento (DD/MM/AAAA): ");
					data = sdf.parse(sc.nextLine());
					System.out.print("Escolha um nome de usuário: ");
					nomeUsuario = sc.nextLine();
					System.out.print("Escolha uma senha: ");
					senha = sc.nextLine();

					aluno = new Aluno(nome, email, data, nomeUsuario, senha, true);
					alunoDAO.adicionar(aluno, null);
					break;

				case 3:
					System.out.print("\nInforme o ID da pessoa: ");
					id = sc.nextInt();
					sc.nextLine();

					for (Pessoa pessoa : pessoas) {
						if (pessoa.getId() == id) {
							pessoas.remove(pessoa);
							if (pessoa.getTipoPessoa().contentEquals("professor")) {
								professor = (Professor) pessoa;
								professorDAO.remover(professor);
							} else if (pessoa.getTipoPessoa().contentEquals("aluno")) {
								aluno = (Aluno) pessoa;
								alunoDAO.remover(aluno);
							}
							break;
						}
					}

					break;

				case 4:
					System.out.print("\nInforme o ID da pessoa: ");
					id = sc.nextInt();
					sc.nextLine();

					for (Pessoa p : pessoas) {
						if (p.getId() == id) {
							System.out.println("\n---Alterando Pessoa---");
							System.out.print("\nNome: ");
							nome = sc.nextLine();
							p.setNome(nome);
							System.out.print("Email: ");
							email = sc.nextLine();
							p.setEmail(email);
							System.out.print("Data de Nascimento (DD/MM/AAAA): ");
							data = sdf.parse(sc.nextLine());
							p.setDataNascimento(data);
							System.out.print("Escolha um nome de usuário: ");
							nomeUsuario = sc.nextLine();
							p.setLogin(nomeUsuario);
							System.out.print("Escolha uma senha: ");
							senha = sc.nextLine();
							p.setPasswd(senha);

							if (p.getTipoPessoa().contentEquals("aluno")) {
								alunoDAO.atualizar((Aluno) p, 0);
							} else if (p.getTipoPessoa().contentEquals("professor")) {
								professor = (Professor) p;
								System.out.print("Salário: ");
								salario = sc.nextFloat();
								professor.setSalario(salario);
								System.out.print("Carga horária Semanal: ");
								cargaHorariaSemanal = sc.nextInt();
								professor.setCargaHorariaSemanal(cargaHorariaSemanal);
								sc.nextLine();
								professorDAO.atualizar((Professor) professor, 0);
							}

							break;
						}
					}
					break;

				case 5:
					for (Pessoa p : pessoas) {
						System.out.println("\nID: " + p.getId());
						System.out.println("Nome: " + p.getNome());
						System.out.println("Email: " + p.getEmail());
						System.out.println("Data de nascimento: " + p.getDataNascimento());
						System.out.println(p.getNome() + " é um " + p.getTipoPessoa());
					}
					break;

				case 6:
					System.out.println("\n-- Tela de login --");
					System.out.print("Nome de usuário: ");
					nomeUsuario = sc.nextLine();
					System.out.print("Senha: ");
					senha = sc.nextLine();

					for (int i = 0; i < pessoas.size(); i++) {
						if (pessoas.get(i).validaUsuario(nomeUsuario, senha)) {
							if (pessoas.get(i).getTipoPessoa().contentEquals("professor")) {
								professor = (Professor) pessoas.get(i);
								escolha1 = 1;
								controle = false;
								break;
							} else {
								aluno = (Aluno) pessoas.get(i);
								escolha1 = 2;
								controle = false;
								break;
							}
						}
					}
					break;

				}
			}

			controle = true;

			// Pessoa Logada
			while (controle) {
				switch (escolha1) {

				case 1: // Se for uma Professor
					professor.adicionaCursos();
					System.out.println("\n[1] - criar curso");
					System.out.println("[2] - acessar curso");
					System.out.println("[3] - remover curso");
					System.out.println("[4] - voltar para tela de login");
					System.out.println("[5] - editar curso");
					System.out.print("Informe sua escolha: ");
					escolha2 = sc.nextInt();
					sc.nextLine();

					switch (escolha2) {

					case 1:
						System.out.print("\nQual será o nome de curso: ");
						nome = sc.nextLine();
						data = new Date();
						professor.criaCurso(nome, data);
						break;

					case 2:
						System.out.println("\n---Seus cursos---");
						professor.listaCursos();
						System.out.print("\nNome do curso que deseja acessar: ");
						nome = sc.nextLine();
						curso = professor.getCurso(nome);

						if (curso == null)
							break;

						controle2 = true;

						while (controle2) {
							System.out.println("\n---Opções disponíveis para este curso---");
							System.out.println("\n[1] - adicionar aluno");
							System.out.println("[2] - remover aluno");
							System.out.println("[3] - listar alunos");
							System.out.println("[4] - criar questionário X");
							System.out.println("[5] - remover questionário X");
							System.out.println("[6] - criar fórum");
							System.out.println("[7] - remover fórum");
							System.out.println("[8] - responder em fórum");
							System.out.println("[9] - listar conteúdos");
							System.out.println("[10] - voltar");
							System.out.println("[11] - alterar forum");
							System.out.print("Informe sua escolha: ");
							escolhaCurso = sc.nextInt();
							sc.nextLine();

							switch (escolhaCurso) {

							case 1: // adicionar aluno no curso
								for (Aluno a : alunos) {
									System.out.println("\nID: " + a.getId());
									System.out.println("Nome: " + a.getNome());
									System.out.println("Email: " + a.getEmail());
									System.out.println("Data de nascimento: " + a.getDataNascimento());
								}
								
								System.out.print("\nQual o ID do aluno que deseja adicionar: ");
								id = sc.nextInt();
								sc.nextLine();
								
								for (Aluno a : alunos) {
									if (a.getId() == id) {
										aluno = a;
									}
								}
								
								curso.adicionaAluno(aluno);
								break;

							case 2: // remover aluno do curso
								for (Aluno a : alunos) {
									System.out.println("\nID: " + a.getId());
									System.out.println("Nome: " + a.getNome());
									System.out.println("Email: " + a.getEmail());
									System.out.println("Data de nascimento: " + a.getDataNascimento());
								}
								
								System.out.print("\nQual o ID do aluno que deseja remover: ");
								id = sc.nextInt();
								sc.nextLine();
								
								for (Aluno a : alunos) {
									if (a.getId() == id) {
										aluno = a;
									}
								}
								
								curso.removeAluno(aluno);
								break;

							case 3: // listar alunos "matriculados"
								for (Pessoa p : pessoas) {
									System.out.println("\nID: " + p.getId());
									System.out.println("Nome: " + p.getNome());
									System.out.println("Email: " + p.getEmail());
									System.out.println("Data de nascimento: " + p.getDataNascimento());
									System.out.println(p.getNome() + " é um " + p.getTipoPessoa());
								}
								System.out.println("\n---Alunos matriculados---");
								curso.listaAlunos();
								break;

							case 4: // criar questionário no curso
								System.out.print("\nTitulo para o questionário: ");
								titulo = sc.nextLine();
								System.out.print("Descrição: ");
								descricao = sc.nextLine();
								data = new Date();
								curso.adicionaQuestionario(titulo, descricao, data);
								break;

							case 5: // remover questionario do curso
								System.out.print("\nInforme o ID do questionário que deseja remover: ");
								id = sc.nextInt();
								sc.nextLine();
								curso.removeConteudo(id);
								break;

							case 6: // criar fórum no curso
								System.out.print("\nTitulo para o fórum: ");
								titulo = sc.nextLine();
								System.out.print("Descrição: ");
								descricao = sc.nextLine();
								data = new Date();
								curso.adicionaForum(titulo, descricao, data);
								break;

							case 7: // remover fórum do curso
								curso.listaConteudos();
								System.out.print("\nInforme o ID do fórum que deseja remover: ");
								id = sc.nextInt();
								sc.nextLine();
								curso.removeConteudo(id);
								break;

							case 8: // responder em fórum
								System.out.print("\nInforme o ID do fórum: ");
								id = sc.nextInt();
								sc.nextLine();
								System.out.print("Informe o ID da pergunta: ");
								int idPergunta = sc.nextInt();
								sc.nextLine();
								System.out.print("Resposta: ");
								resposta = sc.nextLine();

								professor.respondeForum(curso, id, idPergunta, resposta);
								break;

							case 9: // listar conteúdos do curso
								System.out.println("\n---Conteúdos do Curso---");
								curso.listaConteudos();
								break;

							case 10: // voltar para o "menu" anterior
								controle2 = false;
								break;

							case 11:
								System.out.print("\nInforme o ID do forum que deseja alterar: ");
								id = sc.nextInt();
								sc.nextLine();
								System.out.print("Informe o novo titulo para o forum: ");
								titulo = sc.nextLine();
								System.out.print("Nova descrição: ");
								descricao = sc.nextLine();
								curso.atualizarForum(id, titulo, descricao);
							}
						}

						break;

					case 3: // remover curso
						System.out.println("\n---Seus cursos---");
						professor.listaCursos();
						System.out.print("\nID do curso que deseja remover: ");
						id = sc.nextInt();
						sc.nextLine();
						professor.removeCurso(id);
						break;

					case 4:
						controle = false;
						break;

					case 5: // Editar Curso
						System.out.println("\n---Editar curso---");
						professor.listaCursos();
						System.out.print("\nID do curso que deseja editar: ");
						id = sc.nextInt();
						sc.nextLine();

						System.out.print("Novo nome: ");
						nome = sc.nextLine();
						data = new Date();
						professor.editarCurso(nome, data, id);
					}

					break;

				case 2: // Se for um Aluno
					aluno.adicionaCursos();
					System.out.println("\n---Seus Cursos---");
					aluno.listaCursos();
					System.out.print("\nInforme o nome do curso que deseja acessar: ");
					nome = sc.nextLine();
					curso = aluno.getCurso(nome);

					if (curso == null) {
						controle = false;
						break;
					}

					controle2 = true;

					while (controle2) {
						System.out.println("\n[1] - listar conteúdos");
						System.out.println("[2] - perguntar em fórum");
						System.out.println("[3] - responder em fórum");
						System.out.println("[4] - voltar para meus cursos");
						System.out.println("[5] - voltar para tela de login");
						System.out.print("Informe sua escolha: ");
						escolha2 = sc.nextInt();
						sc.nextLine();

						switch (escolha2) {

						case 1:
							System.out.println("\n---Conteúdos do Curso---");
							curso.listaConteudos();
							break;

						case 2:
							System.out.print("\nInforme ID do fórum: ");
							id = sc.nextInt(); 
							sc.nextLine();
							System.out.print("Titulo para a pergunta: ");
							titulo = sc.nextLine();
							System.out.print("Pergunta: ");
							pergunta = sc.nextLine();

							aluno.perguntaForum(curso, id, titulo, pergunta);
							break;

						case 3:
							System.out.print("\nInforme o ID do fórum: ");
							id = sc.nextInt();
							sc.nextLine();
							System.out.print("Informe o ID da pergunta: ");
							int idPergunta = sc.nextInt();
							sc.nextLine();
							System.out.print("Resposta: ");
							resposta = sc.nextLine();

							aluno.respondeForum(curso, id, idPergunta, resposta);
							break;

						case 4:
							controle2 = false;
							break;

						case 5:
							controle2 = false;
							controle = false;
							break;

						}
					}

					break;

				}
			}
		}

	}

	// Funções
	public static Aluno obterPessoa(ArrayList<Pessoa> p, String email) {
		for (int i = 0; i < p.size(); i++)
			if (p.get(i).getEmail().contentEquals(email) && p.get(i).getTipoPessoa().contentEquals("aluno"))
				return (Aluno) p.get(i);
		return null;
	}
}