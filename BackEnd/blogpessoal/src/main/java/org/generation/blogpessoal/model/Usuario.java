package org.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "O atributo nome é obrigatório")
	@Size(min = 2, max = 70, message = "Atenção! Esse campo de conter, obrigatoriamente, entre 2 e 70 caracteres")
	private String nome;

	@Email
	private String email;

	@NotBlank(message = "O atributo senha é obrigatório")
	@Size(min = 5, message = "Atenção! Senha menor do que o permitido")
	private String senha;

	private String foto;

	/**
	 * CascadeType.REMOVE -> Ele propaga a operação de remoção de um objeto Pai para
	 * um objeto Filho. Apenas quando remover a Entidade Usuario, também será
	 * removida todas as entidades Postagens associadas. Nas demais operações não
	 * haverá a propagação.
	 * 
	 * CascadeType.ALL -> Ele propaga todas a operações (Inserir, Listar, Atualizar
	 * e Apagar) de um objeto Pai para um objeto Filho.
	 */
	// Note que o usuário não precisa listar suas postagens, apenas remover as
	// postagens quando ele mesmo for removido

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;

	// CONSTRUTOR PARA A CLASSE USUARIO (SERÁ IMPORTANTE NA HORA DE TESTAR)
	
	public Usuario(long id,
			@NotBlank(message = "O atributo nome é obrigatório") @Size(min = 2, max = 70, message = "Atenção! Esse campo de conter, obrigatoriamente, entre 2 e 70 caracteres") String nome,
			@NotBlank(message = "O atributo email é obrigatório") @Size(min = 2, max = 30, message = "Atenção! Esse campo de conter, obrigatoriamente, entre 2 e 30 caracteres") String email,
			@NotBlank(message = "O atributo senha é obrigatório") @Size(min = 5, message = "Atenção! Senha menor do que o permitido") String senha, String foto) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.foto = foto;
	}


	// SOBRECARREGAMOS TAMBÉM UM CONSTRUTOR VAZIO, PARA QUANDO PRECSARMOS
	// INSTANCIAR UM OBJETO SEM ATRIBUIR ARGUMENTOS(VALORES).

	public Usuario() {
		super();
	}

	// Getters and Setters:

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
