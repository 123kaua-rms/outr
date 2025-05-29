package br.senai.sp.jandira.projeto_travello.service


import br.senai.sp.jandira.projeto_travello.model.usuario



import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST




interface UserService {

    @Headers("Content-Type: application/json")
    @POST("usuario")
    fun registerUser(@Body user: usuario): retrofit2.Call<usuario>

}