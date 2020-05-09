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

import doodle.entidades.Aluno;
import doodle.entidades.Curso;
import doodle.entidades.Pessoa;
import doodle.entidades.Professor;

public class Main {

	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato da data
		Date data = new Date();

		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

		Professor professor = null;
		Aluno aluno = null;
		Curso curso = null;

		String nome, email, nomeUsuario, senha;
		boolean controle = true;
		int escolha1 = 0, escolha2, escolhaCurso, cargaHorariaSemanal;
		float salario;

		// Criar Pessoas
		while (controle) {
			System.out.println("\n[1] - criar professor");
			System.out.println("[2] - criar aluno");
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

				professor = new Professor(nome, email, data, nomeUsuario, senha, salario, cargaHorariaSemanal);

				pessoas.add(professor);
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

				aluno = new Aluno(nome, email, data, nomeUsuario, senha);

				pessoas.add(aluno);
				break;
			}

			System.out.println("\n[1] - criar outra pessoa");
			System.out.println("[2] - fazer login");
			System.out.print("Informe sua escolha: ");
			escolha1 = sc.nextInt();
			sc.nextLine();

			if (escolha1 == 2)
				controle = false;
		}

		controle = true;

		// Realizar Login
		while (controle) {
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

		}

		controle = true;

		// Pessoa Logada
		while (controle) {
			switch (escolha1) {

			case 1: // Se for uma Professor
				System.out.println("\n[1] - criar curso");
				System.out.println("[2] - acessar curso");
				System.out.println("[3] - remover curso");
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

					System.out.println("\n---Opções disponíveis para este curso---");
					System.out.println("\n[1] - adicionar aluno");
					System.out.println("[2] - remover aluno");
					System.out.println("[3] - listar alunos");
					System.out.println("[4] - criar questionário");
					System.out.println("[5] - remover questionário");
					System.out.println("[6] - criar fórum");
					System.out.println("[7] - remover fórum");
					System.out.println("[8] - listar conteúdos");
					System.out.print("Informe sua escolha: ");
					escolhaCurso = sc.nextInt();
					sc.nextLine();

					switch (escolhaCurso) {

					case 1:
						System.out.print("\nQual o email do aluno que deseja adicionar: ");
						email = sc.nextLine();
						aluno = obterPessoa(pessoas, email);
						if (aluno == null)
							break;
						curso.adicionaAluno(aluno);
						break;

					case 2:
						System.out.print("\nInforme o email do aluno para removê-lo: ");
						email = sc.nextLine();
						aluno = obterPessoa(pessoas, email);
						if (aluno == null)
							break;
						curso.removeAluno(aluno);
						break;

					case 3:
						System.out.println("\n---Alunos matriculados---");
						curso.listaAlunos();
						break;

					case 4:
						
						break;

					case 5:
						break;
					case 6:
						break;
					case 7:
						break;
					case 8:
						break;

					}

					break;

				case 3: // remover curso
					System.out.println("\n---Seus cursos---");
					professor.listaCursos();
					System.out.print("\nNome do curso que deseja remover: ");
					nome = sc.nextLine();
					professor.removeCurso(nome);
					break;

				}

				break;

			case 2: // Se for um Aluno
				break;

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