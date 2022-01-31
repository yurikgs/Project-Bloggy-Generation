import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Postagem } from 'src/app/model/Postagem';
import { PostagemService } from 'src/app/service/postagem.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-postagem-delete',
  templateUrl: './postagem-delete.component.html',
  styleUrls: ['./postagem-delete.component.css']
})
export class PostagemDeleteComponent implements OnInit {

  postagem: Postagem = new Postagem

  postagemId: number


  constructor(
    private postagemService: PostagemService,
    private router: Router,
    private route: ActivatedRoute
    ) { }

  ngOnInit(){
    window.scroll(0,0)
    if(environment.token==''){
      alert('Sua sessão expirou, favor faça o login novamente')
      this.router.navigate(['/entrar'])
    }

    this.postagemId = this.route.snapshot.params['id']
    this.findByIdPostagem()
  }


  findByIdPostagem(){
    this.postagemService.getByIdPostagem(this.postagemId).subscribe((resp: Postagem) => {
      this.postagem = resp
    })
  }

  apagar(){
    this.postagemService.deletePostagem(this.postagemId).subscribe(()=> {
      alert('Postagem Excluída!')
      this.router.navigate(['/inicio'])
    })
  }


}
