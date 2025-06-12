package br.senai.sp.jandira.projeto_travello.model.modelUsuario

import br.senai.sp.jandira.projeto_travello.model.modelUsuario.usuario

data class UsuarioLoginResponse(
    val status: Boolean,
    val status_code: Int,
    val message: String,
    val usuario: usuario

)