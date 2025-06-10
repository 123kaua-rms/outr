// br.senai.sp.jandira.projeto_travello.modelPais.viagem.kt

package br.senai.sp.jandira.projeto_travello.modelPais

import br.senai.sp.jandira.projeto_travello.model.CategoriaGet
import br.senai.sp.jandira.projeto_travello.modelLocalizacao.LocalizacaoDoGet
import br.senai.sp.jandira.projeto_travello.modelUsuario.UsuarioGet

data class viagem(
    val id: Int, // O ID agora é gerado pelo backend, então não é mais Int = 0 aqui
    val nome: String,
    val descricao: String,
    val data_inicio: String, // Considere usar um tipo Date/Instant e formatar
    val data_fim: String,     // Considere usar um tipo Date/Instant e formatar
    val foto_principal: String,
    val foto_secundaria: String,
    // Estes campos são agora LISTAS DE OBJETOS, não IDs simples
    val categoria: List<CategoriaGet>, // Renomeado para evitar conflito com 'Category'
    val localizacao: List<LocalizacaoDoGet>,
    val usuario: List<UsuarioGet> // Novo modelo para o usuário retornado
)

// --- ATENÇÃO: Verifique os nomes dos campos (`data_inicio`, `data_fim`, etc.)
// e os nomes das listas (`categoria`, `localizacao`, `usuario`) para que correspondam
// EXATAMENTE ao JSON que seu backend está retornando.