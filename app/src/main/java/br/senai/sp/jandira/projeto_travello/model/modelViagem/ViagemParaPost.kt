package br.senai.sp.jandira.projeto_travello.model.modelViagem

import com.google.gson.annotations.SerializedName // Importe esta anotação

data class ViagemParaPost(
    val nome: String,
    val descricao: String,
    val data_inicio: String,
    val data_fim: String,
    val foto_principal: String,
    val foto_secundaria: String,
    @SerializedName("id_categoria") // Adicione se o nome da variável for diferente do JSON
    val id_categoria: Int, // Mantenha como id_categoria para corresponder ao JSON se já estiver assim
    @SerializedName("id_localizacao") // <--- ADICIONE ESTA LINHA AQUI!
    val id_localizacao: Int, // <--- RENOMEIE AQUI!
    @SerializedName("id_usuario") // Adicione se o nome da variável for diferente do JSON
    val id_usuario: Int // Mantenha como id_usuario para corresponder ao JSON se já estiver assim
)