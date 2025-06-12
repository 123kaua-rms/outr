package br.senai.sp.jandira.projeto_travello.service

import br.senai.sp.jandira.projeto_travello.model.Viagem
import br.senai.sp.jandira.projeto_travello.model.modelViagem.ViagemParaPost
import br.senai.sp.jandira.projeto_travello.model.modelLocalizacao.LocationListResponse
import br.senai.sp.jandira.projeto_travello.model.modelViagem.ViagemParaGet
import br.senai.sp.jandira.projeto_travello.model.modelViagem.ViagemResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface TripService {

    @Headers("Content-Type: application/json")
    @POST("viagem")
    fun registerTrip(@Body trip: ViagemParaPost): Call<ViagemParaGet>


    @GET("viagem") // Altere o tipo de retorno para o novo modelo de resposta
    fun getViagem(): Call<ViagemResponse> // <--- AGORA ESPERA LocationListResponse

}