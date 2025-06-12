package br.senai.sp.jandira.projeto_travello.model.modelViagem

import br.senai.sp.jandira.projeto_travello.model.Usuario
import br.senai.sp.jandira.projeto_travello.model.ModelCategoria.Category
import br.senai.sp.jandira.projeto_travello.model.modelLocalizacao.LocalizacaoDoGet
import com.google.gson.annotations.SerializedName

data class ViagemParaGet(
    val id: Int,
    val nome: String,
    val descricao: String,
    @SerializedName("data_inicio") val dataInicio: String,
    @SerializedName("data_fim") val dataFim: String,
    @SerializedName("foto_principal") val fotoPrincipal: String,
    @SerializedName("foto_secundaria") val fotoSecundaria: String,
    val categoria: List<Category>,
    val localizacao: List<LocalizacaoDoGet>,
    val usuario: List<Usuario>
)
