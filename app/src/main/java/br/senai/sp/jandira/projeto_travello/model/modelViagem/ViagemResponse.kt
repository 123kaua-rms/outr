package br.senai.sp.jandira.projeto_travello.model.modelViagem

import com.google.gson.annotations.SerializedName

data class ViagemResponse(
    val status: Boolean,
    @SerializedName("status_code") val statusCode: Int,
    val items: Int,
    val viagens: List<ViagemParaGet>
)
