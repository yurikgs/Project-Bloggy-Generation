import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Postagem } from '../model/Postagem';
import { Tema } from '../model/Tema';
import { User } from '../model/User';
import { AuthService } from '../service/auth.service';
import { PostagemService } from '../service/postagem.service';
import { TemaService } from '../service/tema.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  postagem: Postagem = new Postagem()
  tema: Tema = new Tema()
  user: User = new User()

  listaTemas: Tema[]
  listaPostagens: Postagem[]

  idTema: number
  idUser = environment.id

  constructor(
    private router: Router,
    private temaService: TemaService,
    private postagemService: PostagemService,
    private authService: AuthService
    ) { }

  ngOnInit(){

    window.scroll(0,0)

    if(environment.token == ''){
      alert('Sua sessão expirou, favor faça o login novamente')
      this.router.navigate(['/entrar'])
    }

    this.authService.refreshToken()
    this.findAllTemas()
    this.findAllPostagens()

  }

  findAllTemas(){
    this.temaService.getAllTema().subscribe((resp: Tema[]) => {
      this.listaTemas = resp
    })
  }

  findByIdTema(){
    this.temaService.getByIdTema(this.idTema).subscribe((resp: Tema) =>{
      this.tema = resp
    })
  }

  findAllPostagens(){
    this.postagemService.getAllPostagens().subscribe((resp: Postagem[]) => {
      this.listaPostagens = resp
    })
  }

  findByIdUser(){
    this.authService.getByIdUser(this.idUser).subscribe((resp: User)=> {
      this.user = resp
    })
  }

  publicar(){

    // Relacionamento entre tabelas(inserção das pk):

    this.tema.id = this.idTema
    this.postagem.tema = this.tema

    console.log(this.idTema)
    console.log(this.tema)

    this.user.id = this.idUser
    this.postagem.usuario = this.user


    console.log(this.postagem)


    this.postagemService.postPostagem(this.postagem).subscribe((resp: Postagem) => {
      this.postagem = resp
      alert('Postagem realizada com sucesso!')
      this.postagem = new Postagem()
      this.findAllPostagens() //faz reiniciar a lista de postagens crregadas no html
    })
  }

}
