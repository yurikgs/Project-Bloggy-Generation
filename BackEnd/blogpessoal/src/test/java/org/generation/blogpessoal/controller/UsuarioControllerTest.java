package org.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.generation.blogpessoal.model.Usuario;
import org.generation.blogpessoal.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) 
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UsuarioService usuarioService;

	@Test
	@Order(1)
	@DisplayName("Cadastrar um Novo Usuário")
	void deveCriarUmNovoUsuario () {
		
		// Nesse caso, HttpEntity representa o corpo da requisição POST
		// Que será recebida por uma ResponseEntity, logo em seguida no código 
		
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L, 
				"Chapolin", "Ch@polin", "senhaforte", "foto"));
		
		
		// TestResttemplate é a interface que contém os métodos para fazer a requisição em si,
		// sendo eles exchange() [usado aqui] e withBasicAuth [a ser usado quando for 
		// necessário autenticar com usuario e senha]
		
		// TesteRestTemplate deve ser obrigatoriamente injetado, e não importado, se não DÁ ERRO.
		
		ResponseEntity<Usuario> resposta = testRestTemplate
				.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);
		
		//exchange execu
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals("Chapolin", resposta.getBody().getNome());
		assertEquals("Ch@polin", resposta.getBody().getEmail());	

		
	}
	
	@Test
	@Order(2)
	@DisplayName("Não deve permitir cadastro de Usuario duplicado")
	public void naoDeveDuplicarUsuario() {

		usuarioService.cadastrarUsuario(new Usuario(0L, 
				"Chapolin", "Ch@polin", "senhaforte", "foto"));

		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L, 
				"Chapolin", "Ch@polin", "senhaforte",  "foto"));
		
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao,
				Usuario.class);

		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
	}
	
//	@Test
//	@Order(3)
//	@DisplayName("Alterar um Usuário")
//	public void deveAtualizarUmUsuario() {
//
//		Optional<Usuario> usuarioCreate = usuarioService
//				.cadastrarUsuario(new Usuario(0L, 
//						"Peter Parker", "Spider@Man", "aracnofobia",  "foto"));
//
//		Usuario usuarioUpdate = new Usuario(usuarioCreate.get().getId(), "Bruce Banner",
//				"brucebanner@hulk.com", "hulk_esmaga", "foto");
//
//		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioUpdate);
//
//		ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("root", "root")
//				.exchange("/usuarios/atualizar", HttpMethod.PUT, requisicao, Usuario.class);
//
//		assertEquals(HttpStatus.OK, resposta.getStatusCode());
//		assertEquals(usuarioUpdate.getNome(), resposta.getBody().getNome());
//		assertEquals(usuarioUpdate.getEmail(), resposta.getBody().getEmail());
//	}

	
	@Test
	@Order(3)
	@DisplayName("Alterar um Usuário")
	public void deveAtualizarUmUsuario() {

		Optional<Usuario> usuarioCreate = usuarioService.cadastrarUsuario(new Usuario(0L, 
			"Peter Parker", "spider@man.com", "aracnofobi@", "foto"));

		Usuario usuarioUpdate = new Usuario(usuarioCreate.get().getId(), 
				"Peter Parker", "spider@man.com", "aracnofobi@", "foto");
		
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioUpdate);

		ResponseEntity<Usuario> resposta = testRestTemplate
			.withBasicAuth("root", "root")
			.exchange("/usuarios/atualizar", HttpMethod.PUT, requisicao, Usuario.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(usuarioUpdate.getNome(), resposta.getBody().getNome());
		assertEquals(usuarioUpdate.getEmail(), resposta.getBody().getEmail());
	}

	@Test
	@Order(4)
	@DisplayName("Listar todos os Usuários")
	public void deveMostrarTodosUsuarios() {

		usuarioService.cadastrarUsuario(new Usuario(0L, 
			"Carol Danvers", "captain@marvel.com", "thorIsWeak", "foto"));
		
		usuarioService.cadastrarUsuario(new Usuario(0L, 
			"Bruce Wayne", "batman@forever.com", "noNeedSuperPower", "foto"));

		ResponseEntity<String> resposta = testRestTemplate
			.withBasicAuth("root", "root")
			.exchange("/usuarios/all", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

}
