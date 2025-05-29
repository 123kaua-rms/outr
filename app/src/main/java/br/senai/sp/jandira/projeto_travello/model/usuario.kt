package br.senai.sp.jandira.projeto_travello.model




data class usuario(
    var foto_perfil: String = "https://i.pinimg.com/736x/2c/47/d5/2c47d5dd5b532f83bb55c4cd6f5bd1ef.jpg",
    var nome_completo: String ="",
    var username: String ="",
    var email: String ="",
    var id_pais: Int = 0,
    var senha: String ="",
    var biografia: String = "Sharing memories and inspiring new adventures!",
    var data_cadastro: String
)
