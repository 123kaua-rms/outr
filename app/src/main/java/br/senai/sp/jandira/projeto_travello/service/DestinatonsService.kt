package br.senai.sp.jandira.projeto_travello.service

import br.senai.sp.jandira.projeto_travello.modelDestination.Destination
import retrofit2.http.GET

interface ApiService {
    @GET("api/destinations")
    suspend fun getDestinations(): List<Destination>
}