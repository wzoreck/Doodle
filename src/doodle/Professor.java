package doodle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Professor {
	private String nome;
	private String cpf;
	private char sexo;
	private Date dataNascimento;
	private String[] localOrigem = new String[3];
	private String email;
	private String telefone;
	
	public Professor(String nome, String cpf, char sexo, Date dataNascimento, String pais,
					String estado, String cidade, String email, String telefone) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.localOrigem[0] = pais;
		this.localOrigem[1] = estado;
		this.localOrigem[2] = cidade;
		this.email = email;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public char getSexo() {
		return sexo;
	}

	public String getDataNascimento() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return  sdf.format(dataNascimento);
	}

	public String getLocalOrigem() {
		return localOrigem[0] + " - " + localOrigem[2] + "/" + localOrigem[1];
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

}
