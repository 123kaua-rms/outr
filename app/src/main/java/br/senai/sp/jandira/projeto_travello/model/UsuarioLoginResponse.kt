package br.senai.sp.jandira.projeto_travello.model

data class UsuarioLoginResponse(
    val status: Boolean,
    val status_code: Int,
    val message: String,
    val usuario: usuario

)
