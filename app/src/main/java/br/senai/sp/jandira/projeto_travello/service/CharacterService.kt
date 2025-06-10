package br.senai.sp.jandira.projeto_travello.service

import br.senai.sp.jandira.projeto_travello.modelUsuario.usuario
import br.senai.sp.jandira.projeto_travello.modelUsuario.UsuarioLoginRequest
import br.senai.sp.jandira.projeto_travello.modelUsuario.UsuarioLoginResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST




interface UserService {

    @Headers("Content-Type: application/json")
    @POST("usuario")
    fun registerUser(@Body user: usuario): retrofit2.Call<usuario>

    @Headers("Content-Type: application/json")
    @POST("usuario/login")
    fun loginUser(@Body user: UsuarioLoginRequest): retrofit2.Call<UsuarioLoginResponse>

}

