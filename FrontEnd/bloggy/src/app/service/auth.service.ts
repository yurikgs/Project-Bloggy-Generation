import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { User } from '../model/User';
import { UserLogin } from '../model/UserLogin';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
    ) { }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  refreshToken(){
    this.token = {
      headers: new HttpHeaders().set('Authorization', environment.token)
    }
  }

  entrar(userLogin: UserLogin): Observable<UserLogin>{
    return this.http.post<UserLogin>('https://bloggyme.herokuapp.com/usuarios/logar', userLogin)
  }

  cadastrar(user: User): Observable<User> {
    return this.http.post<User>('https://bloggyme.herokuapp.com/usuarios/cadastrar', user)
  }

  atualizar(user: User): Observable<User> {
    return this.http.put<User>('https://bloggyme.herokuapp.com/usuarios/atualizar', user, this.token)
  }

  getByIdUser(id: number): Observable<User>{
    return this.http.get<User>(`https://bloggyme.herokuapp.com/usuarios/${id}`, this.token)
  }

  logado(){
    let ok: boolean = false;
    if(environment.token!='') {
      ok=true
    }

    return ok
  }



}
