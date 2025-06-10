package br.senai.sp.jandira.projeto_travello.modelUsuario

import br.senai.sp.jandira.projeto_travello.modelPais.PaisGet

data class UsuarioGet(
    val id: Int,
    val foto_perfil: String,
    val nome_completo: String,
    val username: String,
    val email: String,
    val senha: String, // Cuidado ao trafegar senhas assim em produção
    val biografia: String,
    val data_cadastro: String,
    val pais: List<PaisGet> // O país do usuário
)