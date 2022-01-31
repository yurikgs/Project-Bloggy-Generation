package org.generation.bloggy.repository;

import java.util.Optional;

import org.generation.bloggy.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	/**
	 * Método que busca um usuário pelo seu usuario (email).
	 * 
	 * select * from tb_usuarios where usuario = "usuario procurado"
	 * 
	 * O que muda em relação a tema e postagens é, primeiro, que a busca é por 
	 * termo exato, apenas um "findBy"(where "" no Mysql)
	 * E segundo, muda que o retorno precisará ser uma optional de usuario,
	 * e não uma usuario diretamente, já que o retorno ainda precisará ser trabalhado
	 * no código.
	 */
	
	public Optional<Usuario> findByEmail (String email);
	
}

