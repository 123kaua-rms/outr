package br.senai.sp.jandira.projeto_travello.modelUsuario

data class UsuarioLoginRequest(
    val email: String = "",
    val senha: String = ""
)