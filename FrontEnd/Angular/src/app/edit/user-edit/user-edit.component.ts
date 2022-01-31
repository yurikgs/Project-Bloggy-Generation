import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { AuthService } from 'src/app/service/auth.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  user: User = new User
  userId: number

  senhaConfirm: string
  userTipo: string

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(){
    window.scroll(0,0)
    if(environment.token == ''){
      this.router.navigate(['/entrar'])

    }

    this.userId = this.route.snapshot.params['id']
    this.findByIdUser(this.userId)

    this.zerarInputsSenha()

  }

  zerarInputsSenha(){
    // let input2 = window.document.querySelector('#confirmSenhas')
    // input2.value
  }
  confirmSenha(event: any) {
    this.senhaConfirm = event.target.value
  }

  tipoUser(event: any) {
    this.userTipo = event.target.value
  }

  findByIdUser(id: number){
    this.authService.getByIdUser(id).subscribe((resp: User) => {
      this.user = resp
    })
  }

  atualizar(){
    this.user.tipo = this.userTipo

    if(this.user.senha!=this.senhaConfirm){
      alert('As senhas digitadas não são iguais!')
    } else {
      this.authService.atualizar(this.user).subscribe((resp: User) => {
      this.user = resp
      alert('Usuário atualizado com sucesso!')
      this.router.navigate(['/inicio'])
    })
    }

  }

}
