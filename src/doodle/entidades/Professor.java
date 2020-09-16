package doodle.entidades;

import java.util.ArrayList;
import java.util.Date;

import doodle.bd.CursoDAO;
import doodle.forum.Forum;
import doodle.forum.Pergunta;
import doodle.forum.Resposta;

public class Professor extends Pessoa {
	private float salario;
	private int cargaHorariaSemanal;

	public Professor(String nome, String email, Date dataNascimento, String login, String passwd, float salario,
			int cargaHorariaSemanal, boolean contID) {
		super(nome, email, dataNascimento, login, passwd, contID);
		this.salario = salario;
		this.cargaHorariaSemanal = cargaHorariaSemanal;
	}

	public void adicionaCursos() {
		CursoDAO cursoDAO = new CursoDAO();
		this.cursos = cursoDAO.listar(this.getId());
	}
	
	public void criaCurso(String nome, Date data) {
		for (int i = 0; i < this.cursos.size(); i++)
			if (this.cursos.get(i).getNome().contentEquals(nome))
				return;

		Curso curso = new Curso(this, nome, data);
		this.cursos.add(curso);
		CursoDAO cursoDAO = new CursoDAO();
		// id[0] -> id do professor
		ArrayList<Integer> id = new ArrayList<Integer>();
		id.add(this.getId());
		cursoDAO.adicionar(curso, id);
	}

	public void removeCurso(int id) {
		CursoDAO cursoDAO = new CursoDAO();
		for (Curso curso : cursos) {
			if(curso.getID() == id) {
				cursoDAO.remover(curso);
				this.cursos.remove(curso);
				return;
			}
		}
	}

	public void editarCurso(String nome, Date data, int idCurso) {
		CursoDAO cursoDAO = new CursoDAO();
		for (Curso curso : cursos) {
			if (curso.getID() == idCurso) {
				curso.setNome(nome);
				curso.setDataInicio(data);
				
				cursoDAO.atualizar(curso, this.getId());
				break;
			}
		}
	}
	
	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public int getCargaHorariaSemanal() {
		return cargaHorariaSemanal;
	}

	public void setCargaHorariaSemanal(int c) {
		this.cargaHorariaSemanal = c;
	}

	@Override
	public void listaCursos() {
		for (int i = 0; i < this.cursos.size(); i++) {
			System.out.println("\nCurso " + (i + 1));
			this.cursos.get(i).informacaoCurso();
		}
	}

	@Override
	public void respondeForum(Curso curso, int idForum, int idPergunta, String resposta) {
		ArrayList<Forum> foruns = new ArrayList<Forum>();
		ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>();
		foruns = curso.getConteudos();
		
		for (Forum forum : foruns) {
			if (forum.getIDForum() == idForum) {
				perguntas = forum.getPerguntas();
				for (Pergunta pergunta : perguntas) {
					if (pergunta.getIDPergunta() == idPergunta) {
						Date data = new Date();
						pergunta.adicionaResposta(new Resposta(idPergunta, idForum, this, resposta, data, true, true));
					}
				}
			}
		}
	}

	@Override
	protected String tipoPessoa() {
		return "professor";
	}
}
