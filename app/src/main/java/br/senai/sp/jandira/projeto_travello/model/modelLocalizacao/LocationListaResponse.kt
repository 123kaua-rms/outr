package br.senai.sp.jandira.projeto_travello.model.modelLocalizacao

import com.google.gson.annotations.SerializedName

data class LocationListResponse(
    val status: Boolean,
    @SerializedName("status_code") // Mapeia "status_code" do JSON para "statusCode" no Kotlin
    val statusCode: Int,
    val items: Int,
    @SerializedName("localizacao") // A chave "localizacao" do JSON contém a lista de LocalizacaoDoGet
    val localizacao: List<LocalizacaoDoGet> // <--- CORREÇÃO: AGORA REFERENCIA LocalizacaoDoGet!
)