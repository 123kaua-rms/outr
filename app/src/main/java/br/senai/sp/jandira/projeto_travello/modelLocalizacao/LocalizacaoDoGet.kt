package br.senai.sp.jandira.projeto_travello.modelLocalizacao

import br.senai.sp.jandira.projeto_travello.modelPais.Country
import com.google.gson.annotations.SerializedName

data class LocalizacaoDoGet( // <-- Nome da classe
    val id: Int = 0,
    val nome: String,
    @SerializedName("pais")
    val pais: List<Country> // <-- Certifique-se de que 'pais' é List<Country>
)