
//Interface para métodods de interação com o DB,
// exetende outra interface já existente no jpa

package org.generation.blogpessoal.repository;

import org.generation.blogpessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
									//no diamond operator:
									//tipo de Entidade, Tipo de ID
	
public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
	
}
//^^^^ Busca os atributos pelo titulo,
//todos os que contém a string buscada, em seu titulo
// (faz um "like")
//IgnoreCase - Ignora se as letras são maiúsculas ou minúsculas

