// Trip.kt
package br.senai.sp.jandira.projeto_travello.model

data class Viagem(
    val id: Int,
    val nome: String,
    val descricao: String,
    val data_inicio: String,
    val data_fim: String,
    val foto_principal: String,
    val foto_secundaria: String, // Corrected: This field was added as per your previous request.
    val id_localizacao: Int,     // Corrected: Changed from List<Localizacao> to Int.
    val id_usuario: Int          // Corrected: Changed from List<Usuario> to Int.
)

// The following data classes (Localizacao and Usuario) are commented out
// because they are likely not needed for the *response* of a single trip creation
// if your backend only returns the IDs within the Viagem object.
// Keep them if you use them for other API calls where the full objects are returned.
/*
data class Localizacao(
    val nome: String
)

data class Usuario(
    val username: String,
    val foto_perfil: String
)
*/