package br.senai.sp.jandira.projeto_travello.model.modelLocalizacao

import com.google.gson.annotations.SerializedName

data class LocalizacaoParaPost( // <-- Renomeado aqui
    val id: Int = 0,
    @SerializedName("nome") val nome: String,
    @SerializedName("id_pais") val id_pais: Int
)