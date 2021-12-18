package org.generation.blogpessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.generation.blogpessoal.model.UsuarioLogin;
import org.generation.blogpessoal.model.Usuario;
import org.generation.blogpessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

//verifica se o email de cadastro já não existe

		if (repository.findByEmail(usuario.getEmail()).isPresent())
			return Optional.empty();
		
		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.of(repository.save(usuario));
	
	}

// Melhoria para esse método: fazer atualização sem necessidade de inserir um id
// Isso poderia ser feito forçando o login para atualizar, e tomando as informações do login
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (repository.findById(usuario.getId()).isPresent()) {
			
			Optional<Usuario> buscaUsuario = repository.findByEmail(usuario.getEmail());

// Verifica se o email que estão tentando atualizar já não existe no sistema
			
			if (buscaUsuario.isPresent()) {				
				if (buscaUsuario.get().getId() != usuario.getId())
					return Optional.empty();
			}
			
			usuario.setSenha(criptografarSenha(usuario.getSenha()));

			return Optional.of(repository.save(usuario));
		} 
			
		return Optional.empty();  // É só por isso que é uma optional?
	}	


	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {

		Optional<Usuario> usuario = repository.findByEmail(usuarioLogin.get().getEmail());

		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

				usuarioLogin.get().setId(usuario.get().getId());				
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getEmail(), usuarioLogin.get().getSenha()));

				return usuarioLogin;

			}
		}	
		
		return Optional.empty();
		
	}

	
	
// OUTRA FORMA DE FAZER:
//	
//	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> user) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		
//		Optional<Usuario> usuario = repository.findByEmail(user.get().getEmail());
//		
//		if(usuario.isPresent()) {
//			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
//				
///*String para gerar senha cript*/	String auth = user.get().getEmail() + ":" + user.get().getSenha();
///*senha criptografada*/				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
///*Token com a senha cript*/			String authHeader ="Basic "+ new String(encodedAuth);
//				
//				user.get().setToken(authHeader);
//				user.get().setNome(usuario.get().getNome());
//				return user;
//			}	
//		}
//		 return null;
//	}
	
	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(senha);

	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senhaDigitada, senhaBanco);

	}

	private String gerarBasicToken(String email, String password) {
		
		String tokenBase = email + ":" + password;
		byte[] tokenBase64 = Base64.encodeBase64(tokenBase.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}

	
	
	
}
