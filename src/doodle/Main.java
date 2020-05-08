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
import java.util.Date;
import java.util.Scanner;

import doodle.entidades.Curso;
import doodle.entidades.Pessoa;
import doodle.entidades.Professor;

public class Main {

	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato da data
		Date data = new Date();
		
		Pessoa p = new Professor("Daniel", "daniel@email.com", sdf.parse("08/07/2000"), "daniel", "123", 3.000f, 10);
		
		Curso c = new Curso((Professor) p, "POO", data);
		
		c.InformacaoCurso();
		

//		ArrayList<Pessoa> professores = new ArrayList<Pessoa>();
//		ArrayList<Pessoa> alunos = new ArrayList<Pessoa>();
//		ArrayList<Curso> cursos = new ArrayList<Curso>();
//
//		Professor professor;
//		Aluno aluno;
//		Curso curso;
//		Topico topico;
//		Forum forum;
//
//		String nome, cpf, pais, estado, cidade, email, telefone, titulo, descricao;
//		char sexo, formacao;
//		int registro;
//
//		int escolha1, escolha2;
//
//		while (true) {
//			System.out.println("\n[1] - Criar objeto");
//			System.out.println("[2] - Listar objeto");
//			System.out.println("[3] - Alterar objeto");
//			System.out.println("[4] - Remover objeto");
//			System.out.println("[5] - Matricular aluno em curso");
//			System.out.print("Informe sua escolha: ");
//			escolha1 = sc.nextInt();
//			sc.nextLine();
//
//			switch (escolha1) {
//			case 1:
//				System.out.println("\n[1] - Criar professor");
//				System.out.println("[2] - Criar aluno");
//				System.out.println("[3] - Criar curso");
//				System.out.println("[4] - Criar topico");
//				System.out.println("[5] - Criar forum");
//				System.out.print("Informe sua escolha: ");
//				escolha2 = sc.nextInt();
//				sc.nextLine();
//
//				switch (escolha2) {
//				case 1:
//					System.out.println("\n\nCadastro de Professor");
//					System.out.print("\nInforme o nome completo da pessoa: ");
//					nome = sc.nextLine();
//					System.out.print("Informe o cpf: ");
//					cpf = sc.nextLine();
//					System.out.print("Informe a data de nascimetno (DD/MM/AAAA): ");
//					data = sdf.parse(sc.nextLine());
//					System.out.print("Informe o sexo (M/F): ");
//					sexo = sc.nextLine().charAt(0);
//					System.out.print("Informe o país de origem: ");
//					pais = sc.nextLine();
//					System.out.print("Informe o estado de origem: ");
//					estado = sc.nextLine();
//					System.out.print("Informe a cidade de origem: ");
//					cidade = sc.nextLine();
//					System.out.print("Informe o email: ");
//					email = sc.nextLine();
//					System.out.print("Informe o telefone: ");
//					telefone = sc.nextLine();
//					System.out.print("Informe o número de registro: ");
//					registro = sc.nextInt();
//					sc.nextLine();
//					System.out.print("Qual a formação (G, M, D): ");
//					formacao = sc.nextLine().charAt(0);
//					
//					professor = (Professor) obterPessoa(professores, cpf);
//					if (professor != null) {
//						System.out.println("\nO professor já existe!");
//						break;
//					}
//					
//					professor = new Professor(nome, cpf, sexo, data, pais, estado, cidade, email, telefone, registro,
//							formacao);
//					professores.add(professor);
//					break;
//
//				case 2:
//					System.out.println("\n\nCadastro de Aluno");
//					System.out.print("\nInforme o nome completo da pessoa: ");
//					nome = sc.nextLine();
//					System.out.print("Informe o cpf: ");
//					cpf = sc.nextLine();
//					System.out.print("Informe a data de nascimetno (DD/MM/AAAA): ");
//					data = sdf.parse(sc.nextLine());
//					System.out.print("Informe o sexo (M/F): ");
//					sexo = sc.nextLine().charAt(0);
//					System.out.print("Informe o país de origem: ");
//					pais = sc.nextLine();
//					System.out.print("Informe o estado de origem: ");
//					estado = sc.nextLine();
//					System.out.print("Informe a cidade de origem: ");
//					cidade = sc.nextLine();
//					System.out.print("Informe o email: ");
//					email = sc.nextLine();
//					System.out.print("Informe o telefone: ");
//					telefone = sc.nextLine();
//
//					aluno = (Aluno) obterPessoa(alunos, cpf);
//					if (aluno != null) {
//						System.out.println("\nO aluno já existe!");
//						break;
//					}
//
//					aluno = new Aluno(nome, cpf, sexo, data, pais, estado, cidade, email, telefone);
//					alunos.add(aluno);
//					break;
//
//				case 3:
//					System.out.println("\n\nCadastro de Curso");
//					System.out.print("\nInforme o cpf do professor: ");
//					cpf = sc.nextLine();
//
//					professor = (Professor) obterPessoa(professores, cpf);
//					if (professor == null) {
//						System.out.println("\nProfessor não encontrado!");
//						break;
//					}
//
//					System.out.print("Informe um nome para o curso: ");
//					nome = sc.nextLine();
//
//					curso = obterCurso(cursos, professor, nome);
//					if (curso != null) {
//						System.out.println("\nO curso " + nome + " ministrado pelo professor " + professor.getNome()
//								+ " já existe!");
//						break;
//					}
//
//					cursos.add(new Curso(professor, nome));
//					break;
//					
//				case 4:
//					System.out.println("\n\nCriar um Tópico em um Curso");
//					System.out.print("\nInforme o nome do curso: ");
//					nome = sc.nextLine();
//					System.out.print("Informe o cpf do professor: ");
//					cpf = sc.nextLine();
//					
//					professor = (Professor) obterPessoa(professores, cpf);
//					if (professor == null) {
//						System.out.println("\nProfessor não encontrado!");
//						break;
//					}
//					
//					curso = obterCurso(cursos, professor, nome);
//					if (curso == null) {
//						System.out.println("\nO curso não existe!");
//						break;
//					}
//					
//					System.out.print("Informe um titulo para o tópico: ");
//					titulo = sc.nextLine();
//					System.out.print("Informe uma descrição para o tópico: ");
//					descricao = sc.nextLine();
//					data = new Date();
//					curso.adicionaTopico(new Topico(titulo, descricao, data));
//					break;
//				
//				case 5: // criar fórum
//					System.out.println("\n\nCriar um Fórum em um Tópico");
//					System.out.print("\nInforme o nome do curso: ");
//					nome = sc.nextLine();
//					System.out.print("Informe o cpf do professor: ");
//					cpf = sc.nextLine();
//					
//					professor = (Professor) obterPessoa(professores, cpf);
//					if (professor == null) {
//						System.out.println("\nProfessor não encontrado!");
//						break;
//					}
//					
//					curso = obterCurso(cursos, professor, nome);
//					if (curso == null) {
//						System.out.println("\nO curso não existe!");
//						break;
//					}
//					
//					System.out.print("Informe o título do tópico: ");
//					titulo = sc.nextLine();
//					for (int i=0; i < curso.getTopico().size(); i++) {
//						if (curso.getTopico().get(i).getTitulo().contentEquals(titulo)) {
//							System.out.print("Informe um título para o fórum: ");
//							titulo = sc.nextLine();
//							System.out.print("Informe uma descrição para o fórum: ");
//							descricao = sc.nextLine();
//							data = new Date();
//							curso.getTopico().get(i).adicionaForum(titulo, descricao, data);
//							break;
//						}
//					}
//					
//					break;
//					
//				}
//				break;
//
//			case 2:
//				System.out.println("\n[1] - Listar professores");
//				System.out.println("[2] - Listar professor pelo cpf");
//				System.out.println("[3] - Listar alunos");
//				System.out.println("[4] - Listar aluno pelo cpf");
//				System.out.println("[5] - Listar cursos (contêm alunos matriculados, topicos e foruns)");
//				System.out.print("Informe sua escolha: ");
//				escolha2 = sc.nextInt();
//				sc.nextLine();
//
//				switch (escolha2) {
//				case 1:
//					listarPessoas(professores);
//					break;
//
//				case 2:
//					System.out.print("\nInforme o cpf do professor: ");
//					cpf = sc.nextLine();
//					professor = (Professor) obterPessoa(professores, cpf);
//					if (professor == null)
//						break;
//					listarPessoa(professor);
//					break;
//
//				case 3:
//					listarPessoas(alunos);
//					break;
//
//				case 4:
//					System.out.print("\nInforme o cpf do aluno: ");
//					cpf = sc.nextLine();
//					aluno = (Aluno) obterPessoa(alunos, cpf);
//					if (aluno == null)
//						break;
//					listarPessoa(aluno);
//					break;
//
//				case 5:
//					listarCursos(cursos);
//					break;
//
//				}
//				break;
//			case 3:
//				System.out.println("\n[1] - Alterar professor");
//				System.out.println("[2] - Alterar aluno");
//				System.out.println("[3] - Alterar curso");
//				System.out.println("[4] - Alterar topico");
//				System.out.println("[5] - Alterar forum");
//				System.out.print("Informe sua escolha: ");
//				escolha2 = sc.nextInt();
//				sc.nextLine();
//
//				switch (escolha2) {
//				case 1:
//					break;
//				}
//				break;
//			case 4:
//				System.out.println("\n[1] - Remover professor");
//				System.out.println("[2] - Remover aluno");
//				System.out.println("[3] - Remover curso");
//				System.out.println("[4] - Remover topico");
//				System.out.println("[5] - Remover forum");
//				System.out.print("Informe sua escolha: ");
//				escolha2 = sc.nextInt();
//				sc.nextLine();
//
//				switch (escolha2) {
//				case 1:
//					System.out.print("\nInforme o cpf do professor: ");
//					cpf = sc.nextLine();
//					removerPessoa(professores, cpf);
//					break;
//
//				case 2:
//					System.out.print("\nInforme o cpf do aluno: ");
//					cpf = sc.nextLine();
//					removerPessoa(alunos, cpf);
//					break;
//
//				case 3:
//					System.out.print("\nInforme o nome do curso: ");
//					nome = sc.nextLine();
//					System.out.print("Informe o cpf do professor que ministra o curso: ");
//					cpf = sc.nextLine();
//
//					professor = (Professor) obterPessoa(professores, cpf);
//					if (professor == null) {
//						System.out.println("\nO professor informado não existe!");
//						break;
//					}
//					curso = obterCurso(cursos, professor, nome);
//					if (curso == null) {
//						System.out.println("\nO curso informado não existe!");
//						break;
//					}
//
//					removerCurso(cursos, nome, professor);
//					break;
//					
//				case 4:
//					System.out.print("\nInforme o nome do curso: ");
//					nome = sc.nextLine();
//					System.out.print("Informe o cpf do professor: ");
//					cpf = sc.nextLine();
//					
//					professor = (Professor) obterPessoa(professores, cpf);
//					if (professor == null) {
//						System.out.println("\nProfessor não encontrado!");
//						break;
//					}
//					
//					curso = obterCurso(cursos, professor, nome);
//					if (curso == null) {
//						System.out.println("\nO curso não existe!");
//						break;
//					}
//					
//					System.out.print("Informe o título do tópico: ");
//					titulo = sc.nextLine();
//					topico = curso.getTopico(titulo);
//					if(topico == null) {
//						System.out.println("\nO tópico informado não existe!");
//						return;
//					}
//					
//					curso.removeTopico(topico);
//					break;
//					
//				case 5:
//					break;
//
//				}
//				break;
//				
//			case 5:
//				System.out.print("\nInforme o nome do curso: ");
//				nome = sc.nextLine();
//				System.out.print("Informe o cpf do professor que ministra o curso: ");
//				cpf = sc.nextLine();
//				
//				professor = (Professor) obterPessoa(professores, cpf);
//				if (professor == null) {
//					System.out.println("\nProfessor não encontrado!");
//					break;
//				}
//
//				curso = obterCurso(cursos, professor, nome);
//				if (curso == null) {
//					System.out.println("\nO curso informado não existe!");
//					break;
//				}
//				
//				System.out.print("Informe o cpf do aluno: ");
//				cpf = sc.nextLine();
//				
//				aluno = (Aluno) obterPessoa(alunos, cpf);
//				if(aluno == null) {
//					System.out.println("\nO aluno informado não existe!");
//					break;
//				}
//				
//				curso.matricularAluno(aluno);
//				
//				break;
//			}
//		}
//
//	}
//
//	// Funções para Pessoa(s)
//	public static void removerPessoa(ArrayList<Pessoa> p, String cpf) {
//		for (int i = 0; i < p.size(); i++) {
//			if (p.get(i).getCpf().contentEquals(cpf)) {
//				p.remove(i);
//				return;
//			}
//		}
//	}
//
//	public static void alterarPessoa(ArrayList<Pessoa> p, String email, String cpf) {
//		for (int i = 0; i < p.size(); i++) {
//			if (p.get(i).getCpf().contentEquals(cpf)) {
//				p.get(i).setEmail(email);
//				return;
//			}
//		}
//	}
//
//	public static void alterarPessoa(ArrayList<Pessoa> p, String email, String telefone, String cpf) {
//		for (int i = 0; i < p.size(); i++) {
//			if (p.get(i).getCpf().contentEquals(cpf)) {
//				p.get(i).setEmail(email);
//				p.get(i).setTelefone(telefone);
//				return;
//			}
//		}
//	}
//
//	public static Pessoa obterPessoa(ArrayList<Pessoa> p, String cpf) {
//		for (int i = 0; i < p.size(); i++) {
//			if (p.get(i).getCpf().contentEquals(cpf)) {
//				return p.get(i);
//			}
//		}
//		return null;
//	}
//
//	public static void listarPessoas(ArrayList<Pessoa> p) {
//		for (int i = 0; i < p.size(); i++) {
//			System.out.println("\nPessoa " + (i + 1));
//			System.out.println("Nome: " + p.get(i).getNome());
//			System.out.println("Sexo: " + p.get(i).getSexo());
//			System.out.println("Cpf: " + p.get(i).getCpf());
//			System.out.println("Data de nascimento: " + p.get(i).getDataNascimento());
//			System.out.println("Local de nascimento: " + p.get(i).getLocalOrigem());
//			System.out.println("Email: " + p.get(i).getEmail());
//			System.out.println("Telefone: " + p.get(i).getTelefone());
//		}
//	}
//
//	public static void listarPessoa(Professor p) {
//		System.out.println("\nNome: " + p.getNome());
//		System.out.println("Sexo: " + p.getSexo());
//		System.out.println("Cpf: " + p.getCpf());
//		System.out.println("Data de nascimento: " + p.getDataNascimento());
//		System.out.println("Local de nascimento: " + p.getLocalOrigem());
//		System.out.println("Email: " + p.getEmail());
//		System.out.println("Telefone: " + p.getTelefone());
//		System.out.println("Formação: " + p.getFormacao());
//		System.out.println("Número de registro: " + p.getRegistro());
//	}
//
//	public static void listarPessoa(Aluno a) {
//		System.out.println("\nNome: " + a.getNome());
//		System.out.println("Sexo: " + a.getSexo());
//		System.out.println("Cpf: " + a.getCpf());
//		System.out.println("Data de nascimento: " + a.getDataNascimento());
//		System.out.println("Local de nascimento: " + a.getLocalOrigem());
//		System.out.println("Email: " + a.getEmail());
//		System.out.println("Telefone: " + a.getTelefone());
//		System.out.println(a.isMatriculado() ? "Está matriculado em algum curso" : "Não está matriculado em um curso");
//	}
//
//	// Funções para Curso(s)
//	public static void removerCurso(ArrayList<Curso> c, String nome, Professor p) {
//		for (int i = 0; i < c.size(); i++) {
//			if (c.get(i).getNome().contentEquals(nome) && c.get(i).getProfessor().getCpf().contentEquals(p.getCpf())) {
//				c.remove(i);
//				Curso.setTotalCursos(Curso.getTotalCursos() - 1);
//				return;
//			}
//		}
//	}
//
//	public static Curso obterCurso(ArrayList<Curso> c, Professor p, String nome) {
//		for (int i = 0; i < c.size(); i++) {
//			if (c.get(i).getNome().contentEquals(nome) && c.get(i).getProfessor().getCpf().contentEquals(p.getCpf())) {
//				return c.get(i);
//			}
//		}
//		return null;
//	}
//
//	public static void listarCursos(ArrayList<Curso> c) {
//		System.out.println("\n------- " + Curso.getTotalCursos() + " cursos cadastrados -------");
//		for (int i = 0; i < c.size(); i++) {
//			System.out.println("\n----------------------------------------------------");
//			System.out.println("Curso "+ (i+1) +": " + c.get(i).getNome());
//			System.out.println("Professor: " + c.get(i).getProfessor().getNome());
//			
//			System.out.println("\n----- " + Matricula.getTotalMatriculas() + " alunos matriculados -----");
//			for (int j=0; j < c.get(i).getTurma().size(); j++) {
//				System.out.println("\nMatricula: " + c.get(i).getTurma().get(j).getNumeroMatricula());
//				System.out.println("Aluno: " + c.get(i).getTurma().get(j).getAluno().getNome());
//				System.out.println("CPF: " + c.get(i).getTurma().get(j).getAluno().getCpf());
//			}
//			
//			System.out.println("\n----- " + Curso.getTotalTopicos() + " tópicos cadastrados -----");
//			for (int k=0; k < c.get(i).getTopico().size(); k++) {
//				System.out.println("\nTópico: " + c.get(i).getTopico().get(k).getTitulo());
//				System.out.println("Descrição: " + c.get(i).getTopico().get(k).getDescricao());
//				
//				if (c.get(i).getTopico().get(k).getForuns().size() > 0) {
//					for (int l=0; l < c.get(i).getTopico().get(k).getForuns().size(); l++) {
//						System.out.println("\nFórum " + (l+1) + ": " + c.get(i).getTopico().get(k).getForuns().get(l).getTitulo());
//						System.out.println("Decrição: " + c.get(i).getTopico().get(k).getForuns().get(l).getDescricao());
//					}
//				}
//				
//			}
//			System.out.println("----------------------------------------------------");
//		}
	}
	
}