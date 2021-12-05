package com.generation.teste.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testehello")
public class TesteController {

		@GetMapping String testeHello () {
			return "Teste Hello";
		}
}
