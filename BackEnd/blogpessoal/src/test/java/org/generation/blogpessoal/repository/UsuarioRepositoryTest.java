package org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.generation.blogpessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start () {
		
		usuarioRepository.save(new Usuario(0L, "Yuri Silva", "yurikgs@outlook.com", "senhaforte", "foto"));
		usuarioRepository.save(new Usuario(0L, "Bruce Wayne", "Batman@batman.com", "senhaforte", "foto"));
		usuarioRepository.save(new Usuario(0L, "Diana Prince", "mulher@maravilha.com", "senhaforte",  "foto"));
	}	
	
	/**
	 * TESTE DO MÉTODO FINDBYEMAIL  DE  USUARIO REPOSITORY
	 * 
	 * DEVE RETORNAR APENAS UM RESULTADO
	 */
	@Test
	@DisplayName("Retorna 1 Usuário")
	void deveRetornarApenasUmEmailCorrespondenciaExata () {
		
		Optional<Usuario> usuario = usuarioRepository.findByEmail("yurikgs@outlook.com");
		assertTrue(usuario.get().getEmail() == "yurikgs@outlook.com");
		
		//Opção: assertTrue(usuario.get().getEmail().equals("yurikgs@outlook.com"));
	};

}
