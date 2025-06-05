package br.senai.sp.jandira.projeto_travello.service

import br.senai.sp.jandira.projeto_travello.model.viagem
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TripService {

    @Headers("Content-Type: application/json")
    @POST("viagem")
    fun registerTrip(@Body trip: viagem): retrofit2.Call<viagem>

}