package br.senai.sp.jandira.projeto_travello.model

data class UsuarioLoginRequest(
    val email: String = "",
    val senha: String = ""
)
