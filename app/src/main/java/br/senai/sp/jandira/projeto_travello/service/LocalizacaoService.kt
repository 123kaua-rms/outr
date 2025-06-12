package br.senai.sp.jandira.projeto_travello.service

    import br.senai.sp.jandira.projeto_travello.model.modelLocalizacao.LocalizacaoDoGet
    import br.senai.sp.jandira.projeto_travello.model.modelLocalizacao.LocalizacaoParaPost
import br.senai.sp.jandira.projeto_travello.model.modelLocalizacao.LocationListResponse // Importe o novo modelo!
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
    import retrofit2.http.Headers
    import retrofit2.http.POST

interface LocationService {
    @Headers("Content-Type: application/json")
    @POST("localizacao") // Mantenha este, pois é para o POST de uma única localização
    fun registerLocation(@Body localizacao: LocalizacaoParaPost): Call<LocalizacaoDoGet>

    @GET("localizacao") // Altere o tipo de retorno para o novo modelo de resposta
    fun getLocations(): Call<LocationListResponse> // <--- AGORA ESPERA LocationListResponse
}