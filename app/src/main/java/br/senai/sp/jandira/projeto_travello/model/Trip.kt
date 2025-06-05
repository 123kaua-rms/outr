package br.senai.sp.jandira.projeto_travello.model

data class viagem(
    val id: Int = 0,
    val nome : String,
    val descricao : String,
    val data_inicio: String,
    val data_fim: String,
    val foto_principal: String,
    val foto_secundaria: String,
    val id_categoria: Int?,
    val id_localizacao: Int,
    val id_usuario: Int
)