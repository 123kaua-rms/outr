package br.senai.sp.jandira.projeto_travello.modelDestination

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow // Importar asStateFlow
//import kotlinx.coroutines.launch
//
//class TravelloHomeScreen : ViewModel() {
//    // StateFlow para a lista de destinos
//    private val _destinations = MutableStateFlow<List<Destination>>(emptyList())
//    val destinations: StateFlow<List<Destination>> = _destinations.asStateFlow() // Expor como StateFlow imutável
//
//    // StateFlow para indicar se os dados estão sendo carregados
//    private val _isLoading = MutableStateFlow(false)
//    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
//
//    // StateFlow para mensagens de erro
//    private val _errorMessage = MutableStateFlow<String?>(null)
//    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
//
//    fun loadDestinations() {
//        viewModelScope.launch {
//            _isLoading.value = true // Indica que o carregamento começou
//            _errorMessage.value = null // Limpa qualquer mensagem de erro anterior
//            try {
//                // Chama o serviço para obter os destinos
//                val result = RetrofitClient.apiService.getDestinations()
//                _destinations.value = result // Atualiza a lista de destinos
//            } catch (e: Exception) {
//                // Em caso de erro, define a mensagem de erro
//                _errorMessage.value = "Falha ao carregar destinos: ${e.localizedMessage ?: "Erro desconhecido"}"
//                e.printStackTrace() // Imprime o erro no logcat para depuração
//            } finally {
//                _isLoading.value = false // Indica que o carregamento terminou (sucesso ou falha)
//            }
//        }
//    }
//}