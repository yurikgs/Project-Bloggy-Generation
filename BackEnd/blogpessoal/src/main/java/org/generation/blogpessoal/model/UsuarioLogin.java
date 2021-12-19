package org.generation.blogpessoal.model;

public class UsuarioLogin {
	
	/**
	 * A Classe UsuarioLogin não terá nenhuma annotation porque ela
	 * não irá gerar uma tabela no Banco de Dados.
	 * 
	 * A principal função desta classe é servir de apoio ao processo
	 * de login na api.
	 * 
	 * Características: tem os mesmos atributos da model Usuario, menos as chaves
	 * estrangeira e + um atributo de 'token' para guardar a senha encriptada.
	 */
	
	private long id;

	private String nome;
	
	private String email;
	
	private String senha;
	
	private String foto;
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	private String token;
	
	

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
