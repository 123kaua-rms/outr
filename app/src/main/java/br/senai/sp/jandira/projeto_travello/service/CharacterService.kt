package br.senai.sp.jandira.projeto_travello.service


import br.senai.sp.jandira.projeto_travello.model.Character
import br.senai.sp.jandira.projeto_travello.model.Result

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CharacterService {

    @GET("character")
    fun listAll(): Call<Result>

    @GET("character/{id}")
    fun findById(@Path("id") id: Int): Call<Character>
}


//interface CharacterService {
//
//    @POST("character")
//    fun listAll(): Call<Result>
//
//    @POST("character/{id}")
//    fun findById(@Path("id") id: Int): Call<Character>
//}
//
//interface CharacterService {
//
//    @PUT("character")
//    fun listAll(): Call<Result>
//
//    @PUT("character/{id}")
//    fun findById(@Path("id") id: Int): Call<Character>
//}
//
//interface CharacterService {
//
//    @DELETE("character")
//    fun listAll(): Call<Result>
//
//    @DELETE("character/{id}")
//    fun findById(@Path("id") id: Int): Call<Character>
//}