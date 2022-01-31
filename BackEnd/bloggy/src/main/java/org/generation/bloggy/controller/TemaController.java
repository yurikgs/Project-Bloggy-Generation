package org.generation.bloggy.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.bloggy.model.Tema;
import org.generation.bloggy.repository.TemaRepository;
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
	
	public TemaRepository getRepository() {
		return repository;
	}

	public void setRepository(TemaRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<List<Tema>>  getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(nome));
		
	}
	
	@PostMapping
	public ResponseEntity<Tema>	post(@Valid @RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED)
		.body(repository.save(tema));
	}
	
	@PutMapping
	public ResponseEntity<Tema>	put(@Valid @RequestBody Tema tema) {
		return repository.findById(tema.getId())
				.map(resp -> ResponseEntity.ok().body(repository.save(tema)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		return repository.findById(id)
				.map(resp -> {
					repository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}


}
