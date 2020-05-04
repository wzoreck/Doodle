// Doodle é baseado no Moodle ;)

package doodle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato da data
		Date data = new Date();
		ArrayList<Professor> professores = new ArrayList<Professor>();
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		ArrayList<Curso> cursos = new ArrayList<Curso>();

		int escolha, escolha2;

		while (true) {
			System.out.println("\nO que deseja fazer?");
			System.out.println("[1] - Cadastrar");
			System.out.println("[2] - Listar");
			System.out.print("Informe sua escolha (número): ");
			escolha = sc.nextInt();

			switch (escolha) {
				case 1:
					System.out.println("\n[1] - Cadastrar professor");
					System.out.println("[2] - Cadastrar aluno");
					System.out.println("[3] - Cadastrar curso");
					System.out.println("[4] - Cadastrar tópico");
					System.out.println("[5] - Cadastrar forum");
					System.out.println("[6] - Incluir aluno em curso");
					System.out.print("O que deseja cadastrar (número): ");
					escolha2 = sc.nextInt();
					sc.nextLine();
					
					String nome, nomeCurso, conteudo, cpf, pais, estado, cidade, email, telefone;
					char sexo;
					
					switch (escolha2) {
						case 1:
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

							professores.add(new Professor(nome, cpf, sexo, data, pais, estado, cidade, email, telefone));
							break;
							
						case 2:
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
							
							alunos.add(new Aluno(nome, cpf, sexo, data, pais, estado, cidade, email, telefone));
							break;
							
						case 3:
							if(professores.size() <= 0) {
								System.out.println("\nNão há professores cadastrados, favor cadastrar um professor ao mínimo!");
								break;
							}
							
							System.out.print("\nInforme o nome de um professor: ");
							nome = sc.nextLine();
							System.out.print("Informe um nome para o curso: ");
							nomeCurso = sc.nextLine();
							System.out.print("Informe a data de início (DD/MM/AAAA): ");
							data = sdf.parse(sc.nextLine());
							
							for (int i=0; i < professores.size(); i++) {
								if(professores.get(i).getNome().contentEquals(nome))
									cursos.add(new Curso(professores.get(i), nomeCurso, data));
							}
							break;
							
						case 4:
							if(cursos.size() <=0) {
								System.out.println("\nNão há cursos cadastrados, favor cadastrar um curso primeiro!");
								break;
							}
							
							System.out.print("\nQual será o título do tópico: ");
							nome = sc.nextLine();
							System.out.print("Descreva o conteúdo do tópico " + nome + ": ");
							conteudo = sc.nextLine();
							System.out.print("Em qual curso deseja cadastrar o tópico: ");
							nomeCurso = sc.nextLine();
							
							for(int i=0; i < cursos.size(); i++) {
								if(cursos.get(i).getNome().contentEquals(nomeCurso)) {
									cursos.get(i).adicionaTopico(new Topico(nome, conteudo));
									break;
								}
							}
							break;
							
						case 5:
							if(cursos.size() <= 0) {
								System.out.println("\nNão há cursos cadastrados, favor cadastrar um curso primeiro!");
							}
							
							System.out.print("\nEm qual curso deseja cadastrar o forum: ");
							nomeCurso = sc.nextLine();
							System.out.print("Em qual tópico deseja criar o forum: ");
							nome = sc.nextLine();
							
							for(int i=0; i < cursos.size(); i++) {
								for(int j=0; j < cursos.get(i).getTopicos().size(); j++) {
									if(cursos.get(i).getTopicos().get(j).getTitulo().contentEquals(nome)) {
										System.out.print("Qual será o título do fórum: ");
										nome = sc.nextLine();
										System.out.print("Descreva o conteúdo do fórum: ");
										conteudo = sc.nextLine();
										data = new Date();
										
										cursos.get(i).getTopicos().get(j).adicionaForum(new Forum(nome, conteudo, data));
										break;
									}
								}
							}
							break;
							
						case 6:
							if(alunos.size() <= 0) {
								System.out.println("\nNão há alunos cadastrados, favor cadastrar alunos primeiro!");
								break;
							}
							System.out.print("\nInforme o cpf do aluno: ");
							cpf = sc.nextLine();
							System.out.print("Em qual curso deseja cadastrar o aluno: ");
							nomeCurso = sc.nextLine();
							
							for(int i=0; i<cursos.size(); i++) {
								if(cursos.get(i).getNome().contentEquals(nomeCurso)) {
									for(int j=0; j<alunos.size(); j++) {
										if(alunos.get(j).getCpf().contentEquals(cpf)) {
											alunos.get(j).setMatriculado(true);
											cursos.get(i).adicionarAluno(alunos.get(j));
											break;
										}
									}
									break;
								}
							}
							break;
					}
					break;
	
				case 2:
					System.out.println("\n[1] - Listar professores");
					System.out.println("[2] - Listar alunos");
					System.out.println("[3] - Listar cursos");
					System.out.print("O que deseja listar (número): ");
					escolha2 = sc.nextInt();
					sc.nextLine();
					
					switch (escolha2) {
						case 1:
							if(professores.size() <= 0) {
								System.out.println("\nNão há nenhum professor cadastrado!");
								break;
							}
							for(int i=0; i < professores.size(); i++) {
								System.out.println("\nProfessor: " + professores.get(i).getNome());
								System.out.println("CPF: " + professores.get(i).getCpf());
								System.out.println("Data de nascimento: " + professores.get(i).getDataNascimento());
								System.out.println("Sexo: " + professores.get(i).getSexo());
								System.out.println("Local de origem: " + professores.get(i).getLocalOrigem());
								System.out.println("E-mail para contato: " + professores.get(i).getEmail());
								System.out.println("Telefone para contato: " + professores.get(i).getTelefone() + "\n");
							}
							break;
							
						case 2:
							if(alunos.size() <= 0) {
								System.out.println("\nNão há nenhum aluno cadastrado!");
								break;
							}
							for(int i=0; i < alunos.size(); i++) {
								System.out.println("\nAluno: " + alunos.get(i).getNome());
								System.out.println("CPF: " + alunos.get(i).getCpf());
								System.out.println("Data de nascimento: " + alunos.get(i).getDataNascimento());
								System.out.println("Sexo: " + alunos.get(i).getSexo());
								System.out.println("Local de origem: " + alunos.get(i).getLocalOrigem());
								System.out.println("E-mail para contato: " + alunos.get(i).getEmail());
								System.out.println("Telefone para contato: " + alunos.get(i).getTelefone());
								System.out.println((alunos.get(i).isMatriculado() ? "Está matriculado" : "Não está matriculado"));
							}
							break;
							
						case 3:
							if(cursos.size() <= 0) {
								System.out.println("\nNão há nenhum curso cadastrado!");
								break;
							}
							// Listar cursos
							for(int i=0; i < cursos.size(); i++) {
								System.out.println("\n---------------------------------------------------------------");
								System.out.println("Curso |" + cursos.get(i).getNome() + "| ministrado pelo professor " + cursos.get(i).getProfessor().getNome());
								System.out.println("Data de inicio: " + cursos.get(i).getDataInicio());
							
								// Listas tópicos cadastrados em um curso, caso haja
								if(cursos.get(i).getTopicos().size() > 0) {
									System.out.println("\n---- Tópicos do curso ----");
									for(int j=0; j < cursos.get(i).getTopicos().size(); j++) {
										System.out.println("\n<< Tópico " + j + " >>");
										System.out.println("Título: " + cursos.get(i).getTopicos().get(j).getTitulo());
										System.out.println("Descrição: " + cursos.get(i).getTopicos().get(j).getDescricao());
										
										// Listar fóruns cadastrados em um tópico de um curso, caso haja
										if(cursos.get(i).getTopicos().get(j).getForuns().size() > 0) {
											for(int k=0; k < cursos.get(i).getTopicos().get(j).getForuns().size(); k++) {
												System.out.println("\n< Fórum " + k + " do tópico " + j + " >");
												System.out.println("Título do fórum: " + cursos.get(i).getTopicos().get(j).getForuns().get(k).getTitulo());
											}
										}
									}
								}
									
								if(cursos.get(i).getTurma().size() > 0) {
									System.out.println("\n<< Alunos matriculados >>");
									for(int l=0; l<cursos.get(i).getTurma().size(); l++) {
										System.out.println("Aluno: " + cursos.get(i).getTurma().get(l).getNome());
										System.out.println("CPF: " + cursos.get(i).getTurma().get(l).getCpf());
										System.out.println("Data de nascimento: " + cursos.get(i).getTurma().get(l).getDataNascimento());
										System.out.println("Sexo: " + cursos.get(i).getTurma().get(l).getSexo());
										System.out.println("Local de origem: " + cursos.get(i).getTurma().get(l).getLocalOrigem());
										System.out.println("E-mail para contato: " + cursos.get(i).getTurma().get(l).getEmail());
										System.out.println("Telefone para contato: " + cursos.get(i).getTurma().get(l).getTelefone());
										System.out.println((cursos.get(i).getTurma().get(l).isMatriculado() ? "Está matriculado" : "Não está matriculado"));
									}
								}
							}
							System.out.println("---------------------------------------------------------------");
							break;
					}
					
					break;

			default:
				System.out.println("\nRetornando...");
			}
		}
		
		

	}
}
