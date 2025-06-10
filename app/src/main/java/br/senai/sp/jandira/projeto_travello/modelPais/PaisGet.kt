package br.senai.sp.jandira.projeto_travello.modelPais

data class PaisGet(
    val id: Int,
    val nome: String,
    val foto_pais: String? // Pode ser String ou null, dependendo do backend
)