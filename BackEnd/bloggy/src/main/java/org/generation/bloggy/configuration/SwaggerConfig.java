package org.generation.bloggy.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

	
	@Bean
	public OpenAPI springBloggyOpenAPI() {
		return new OpenAPI()
		.info(new Info()
				.title("Projeto Bloggy")
				.description("Projeto Bloggy - GenerationBrasil")
				.version("v0.0.1")
		.license(new License()
				.name("MIT License")
				.url("https://github.com/yurikgs/Project_Bloggy-Generation/blob/main/LICENSE"))
		.contact(new Contact()
				.name("Yuri Silva")
				.url("https://www.linkedin.com/in/yuri-silva-dev/")
				.email("yurikgs@outlook.com")))
		.externalDocs(new ExternalDocumentation()
				.description("GitHub")
				.url("https://github.com/yurikgs/Project_Bloggy-Generation"));
		
	}
	
	@Bean
	public  OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
		
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
				
				ApiResponses apiResponses = operation.getResponses();
				
				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso não autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
			}));
		};
	}

	private ApiResponse createApiResponse(String message) {
		
		return new ApiResponse().description(message);
	}
	
}
