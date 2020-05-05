// Doodle é baseado no Moodle ;)

package doodle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import doodle.entidades.Aluno;
import doodle.entidades.Curso;
import doodle.entidades.Matricula;
import doodle.entidades.Pessoa;
import doodle.entidades.Professor;

public class Main {

	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato da data
		Date data = new Date();

		ArrayList<Pessoa> professores = new ArrayList<Pessoa>();
		ArrayList<Pessoa> alunos = new ArrayList<Pessoa>();

		Professor professor;
		Aluno aluno;

		String nome, cpf, pais, estado, cidade, email, telefone;
		char sexo, formacao;
		int registro;

		int escolha1, escolha2;

		while (true) {
			System.out.println("\n[1] - Criar objeto");
			System.out.println("[2] - Listar objeto");
			System.out.println("[3] - Alterar objeto");
			System.out.println("[4] - Remover objeto");
			System.out.print("Informe sua escolha: ");
			escolha1 = sc.nextInt();

			switch (escolha1) {
			case 1:
				System.out.println("\n[1] - Criar professor");
				System.out.println("[2] - Criar aluno");
				System.out.println("[3] - Criar curso");
				System.out.println("[4] - Criar topico");
				System.out.println("[5] - Criar forum");
				System.out.print("Informe sua escolha: ");
				escolha2 = sc.nextInt();
				sc.nextLine();

				switch (escolha2) {
				case 1:
					System.out.println("\n\nCadastro de Professor");
					System.out.print("\nInforme o nome completo da pessoa: ");
					nome = sc.nextLine();
					System.out.print("Informe o cpf: ");
					cpf = sc.nextLine();
					System.out.print("Informe a data de nascimetno (DD/MM/AAAA): ");
					data = sdf.parse(sc.nextLine());
					System.out.print("Informe o sexo (M/F): ");
					sexo = sc.nextLine().charAt(0);
					System.out.print("Informe o país de origem: ");
					pais = sc.nextLine();
					System.out.print("Informe o estado de origem: ");
					estado = sc.nextLine();
					System.out.print("Informe a cidade de origem: ");
					cidade = sc.nextLine();
					System.out.print("Informe o email: ");
					email = sc.nextLine();
					System.out.print("Informe o telefone: ");
					telefone = sc.nextLine();
					System.out.print("Informe o número de registro: ");
					registro = sc.nextInt();
					sc.nextLine();
					System.out.print("Qual a formação (G, M, D): ");
					formacao = sc.nextLine().charAt(0);

					professor = new Professor(nome, cpf, sexo, data, pais, estado, cidade, email, telefone,
							registro, formacao);
					professores.add(professor);
					break;
				case 2:
					System.out.println("\n\nCadastro de Aluno");
					System.out.print("\nInforme o nome completo da pessoa: ");
					nome = sc.nextLine();
					System.out.print("Informe o cpf: ");
					cpf = sc.nextLine();
					System.out.print("Informe a data de nascimetno (DD/MM/AAAA): ");
					data = sdf.parse(sc.nextLine());
					System.out.print("Informe o sexo (M/F): ");
					sexo = sc.nextLine().charAt(0);
					System.out.print("Informe o país de origem: ");
					pais = sc.nextLine();
					System.out.print("Informe o estado de origem: ");
					estado = sc.nextLine();
					System.out.print("Informe a cidade de origem: ");
					cidade = sc.nextLine();
					System.out.print("Informe o email: ");
					email = sc.nextLine();
					System.out.print("Informe o telefone: ");
					telefone = sc.nextLine();

					aluno = new Aluno(nome, cpf, sexo, data, pais, estado, cidade, email, telefone);
					alunos.add(aluno);
					break;
				}
				break;

			case 2:
				System.out.println("\n[1] - Listar professores");
				System.out.println("[2] - Listar professor pelo cpf");
				System.out.println("[3] - Listar alunos");
				System.out.println("[4] - Listar aluno pelo cpf");
				System.out.println("[5] - Listar cursos (contêm alunos matriculados, topicos e foruns)");
				System.out.print("Informe sua escolha: ");
				escolha2 = sc.nextInt();
				sc.nextLine();

				switch (escolha2) {
				case 1:
					listarPessoas(professores);
					break;
					
				case 2:
					System.out.print("\nInforme o cpf do professor: ");
					cpf = sc.nextLine();
					listarPessoa((Professor) obterPessoa(professores, cpf));
					break;
					
				case 3:
					listarPessoas(alunos);
					break;
					
				case 4:
					System.out.print("\nInforme o cpf do aluno: ");
					cpf = sc.nextLine();
					listarPessoa((Aluno) obterPessoa(alunos, cpf));
					break;
				}
				break;
			case 3:
				System.out.println("\n[1] - Alterar professor");
				System.out.println("[2] - Alterar aluno");
				System.out.println("[3] - Alterar curso");
				System.out.println("[4] - Alterar topico");
				System.out.println("[5] - Alterar forum");
				System.out.print("Informe sua escolha: ");
				escolha2 = sc.nextInt();
				sc.nextLine();

				switch (escolha2) {
				case 1:
					break;
				}
				break;
			case 4:
				System.out.println("\n[1] - Remover professor");
				System.out.println("[2] - Remover aluno");
				System.out.println("[3] - Remover curso");
				System.out.println("[4] - Remover topico");
				System.out.println("[5] - Remover forum");
				System.out.print("Informe sua escolha: ");
				escolha2 = sc.nextInt();
				sc.nextLine();

				switch (escolha2) {
				case 1:
					break;
				}
				break;
			}
		}

	}

	// Manipulação de Pessoa
	public static void removerPessoa(ArrayList<Pessoa> p, String cpf) {
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).getCpf().contentEquals(cpf)) {
				p.remove(i);
				return;
			}
		}
	}

	public static void alterarPessoa(ArrayList<Pessoa> p, String email, String cpf) {
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).getCpf().contentEquals(cpf)) {
				p.get(i).setEmail(email);
				return;
			}
		}
	}

	public static void alterarPessoa(ArrayList<Pessoa> p, String email, String telefone, String cpf) {
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).getCpf().contentEquals(cpf)) {
				p.get(i).setEmail(email);
				p.get(i).setTelefone(telefone);
				return;
			}
		}
	}
	
	public static Pessoa obterPessoa(ArrayList<Pessoa> p, String cpf) {
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).getCpf().contentEquals(cpf)) {
				return p.get(i);
			}
		}
		return null;
	}

	public static void listarPessoas(ArrayList<Pessoa> p) {
		for (int i = 0; i < p.size(); i++) {
			System.out.println("\nPessoa " + (i + 1));
			System.out.println("Nome: " + p.get(i).getNome());
			System.out.println("Sexo: " + p.get(i).getSexo());
			System.out.println("Cpf: " + p.get(i).getCpf());
			System.out.println("Data de nascimento: " + p.get(i).getDataNascimento());
			System.out.println("Local de nascimento: " + p.get(i).getLocalOrigem());
			System.out.println("Email: " + p.get(i).getEmail());
			System.out.println("Telefone: " + p.get(i).getTelefone());
		}
	}
	
	public static void listarPessoa(Professor p) {
		System.out.println("\nNome: " + p.getNome());
		System.out.println("Sexo: " + p.getSexo());
		System.out.println("Cpf: " + p.getCpf());
		System.out.println("Data de nascimento: " + p.getDataNascimento());
		System.out.println("Local de nascimento: " + p.getLocalOrigem());
		System.out.println("Email: " + p.getEmail());
		System.out.println("Telefone: " + p.getTelefone());
		System.out.println("Formação: " + p.getFormacao());
		System.out.println("Número de registro: " + p.getRegistro());
	}
	
	public static void listarPessoa(Aluno a) {
		System.out.println("\nNome: " + a.getNome());
		System.out.println("Sexo: " + a.getSexo());
		System.out.println("Cpf: " + a.getCpf());
		System.out.println("Data de nascimento: " + a.getDataNascimento());
		System.out.println("Local de nascimento: " + a.getLocalOrigem());
		System.out.println("Email: " + a.getEmail());
		System.out.println("Telefone: " + a.getTelefone());
		System.out.println(a.isMatriculado() ? "Está matriculado em algum curso" : "Não está matriculado em um curso");
	}

	public static Curso cadastrarCurso(Professor p, String nome) {
		Curso c = new Curso(p, nome);
		return c;
	}

}