// Essa classe cria uma entidade ABSTRATA 'postagem' em nosso
// projeto. Assim que essa é instanciada automaticamente o
// banco de dados é atualizado de acordo com essa classe,
// criando-se/ atualizando-se com essa entidade postagem.
// quem faz isso é o jpa  <---

package org.generation.blogpessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;
//import com.sun.istack.NotNull;  //--> outra possibilidade de 

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

       // "ANOTAÇÕES (@) SÃO PARAMETROS QUE COLOCAMOS ACIMA 
       // DAS CLASSES OU PROPRIEDADES E QUE DEFINEM 
       // COMPORTAMENTOS PARA TAIS"
@Entity
@Table(name = "tb_postagens")                 //O contepudo dessa classe será reconhecido como uma tabela (Table)
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue liga o auto incremented
	private long id;   // sempre long, tipo bigint
	
	@NotBlank(message="O atributo título é obrigatório")           // o  campo "message" vai aparecer quando houver algum erro, por exemplo, se o usuário tentar enviar o campo vazio (null)
	@Size(min = 5, max = 100, message= "O atributo deve conter entre 5 e 100 caracteres.")
	private String titulo;

	@NotBlank(message="O atributo texto é obrigatório")
	@Size(min = 10, max = 1000, message= "O atributo deve conter entre 10 e 1000 caracteres")
	private String texto;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis());
			// assim que 'passar' algum dado por essa
			//classe, esse objeto sempre capturará
			// instantaneamente as informações de data e hora,
			// até os milissegundos.

	@ManyToOne
	@JsonIgnoreProperties("postagens")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagens")
	private Usuario usuario;
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
