package org.generation.blogpessoal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Atenção! Esse campo de conter, obrigatoriamente, entre 2 e 70 caracteres")
	@Size(min = 2, max = 70, message = "Atenção! Esse campo de conter, obrigatoriamente, entre 2 e 70 caracteres")
	private String nome;
	
	@NotBlank(message = "Atenção! Esse campo de conter, obrigatoriamente, entre 2 e 30 caracteres")
	@Size(min = 2, max = 30, message = "Atenção! Esse campo de conter, obrigatoriamente, entre 2 e 30 caracteres")
	private String usuario;
	
	@NotBlank(message = "Atenção - Esse campo não pode ficar nulo!")
	@Size(max = 255, message = "Atenção! A senha critografada excedeu o limite de caracteres")
	private String senha;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
