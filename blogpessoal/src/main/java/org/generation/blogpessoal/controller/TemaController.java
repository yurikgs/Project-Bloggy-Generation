package org.generation.blogpessoal.controller;

import java.util.List;

import org.generation.blogpessoal.model.Tema;
import org.generation.blogpessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Tema>>  getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	// Ainda não entendo como funciona esse retorno: <--DÚVIDA
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	// Não está funcionando <--
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(nome));
		
	}
	
	@PostMapping
	public ResponseEntity<Tema>	post(@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED)
		.body(repository.save(tema));
	}
	
	//Porque o Put não pode ser com os mesmos métodos que Put? E vice e versa?
	@PutMapping
	public ResponseEntity<Tema>	put(@RequestBody Tema tema) {
		return ResponseEntity.ok(repository.save(tema));
	}
	
	//Preciso corrigir esse Delete, ele vai dar erro quando encontrar um id que não existe mais
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	
}
