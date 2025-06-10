package br.senai.sp.jandira.projeto_travello.modelPais

data class CountryResponse(
    val status: Boolean,
    val status_code: Int,
    val items: Int,
    val paises: List<Country>
)

data class Country(
    val id: Int,
    val nome: String,
    val foto_pais: String
)
