package br.senai.sp.jandira.projeto_travello.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.projeto_travello.R
import br.senai.sp.jandira.projeto_travello.model.ModelCategoria.Category
import br.senai.sp.jandira.projeto_travello.model.Viagem
import br.senai.sp.jandira.projeto_travello.model.modelPais.Country
import br.senai.sp.jandira.projeto_travello.model.modelLocalizacao.LocalizacaoDoGet
import br.senai.sp.jandira.projeto_travello.model.modelLocalizacao.LocalizacaoParaPost
import br.senai.sp.jandira.projeto_travello.model.modelLocalizacao.LocationListResponse
import br.senai.sp.jandira.projeto_travello.model.modelViagem.ViagemParaPost // Importa o modelo para ENVIAR a requisição
import br.senai.sp.jandira.projeto_travello.service.RetrofitFactory
import br.senai.sp.jandira.projeto_travello.ui.theme.MontserratFontFamily
import br.senai.sp.jandira.projeto_travello.ui.theme.Projeto_TravelloTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// Função para buscar uma localização existente no backend por nome e ID do país.
// Ela usa LocalizacaoDoGet para receber os dados do backend.
fun fetchLocationByDetails(
    locationName: String,
    countryId: Int,
    selectedLocationState: MutableState<LocalizacaoDoGet?>,
    idLocalizacaoState: MutableState<String>,
    onLocationFound: (LocalizacaoDoGet) -> Unit
) {
    val call = RetrofitFactory().getLocationService().getLocations()
    call.enqueue(object : Callback<LocationListResponse> {
        override fun onResponse(call: Call<LocationListResponse>, response: Response<LocationListResponse>) {
            if (response.isSuccessful) {
                val locations = response.body()?.localizacao

                if (locations != null) {
                    val foundLocation = locations.find { loc ->
                        loc.nome == locationName && loc.pais.any { p -> p.id == countryId }
                    }
                    if (foundLocation != null) {
                        selectedLocationState.value = foundLocation
                        idLocalizacaoState.value = foundLocation.id.toString()
                        Log.i("API_LOCATION", "Localização '${foundLocation.nome}' encontrada e ID atribuído: ${foundLocation.id}")
                        onLocationFound(foundLocation)
                    } else {
                        Log.e("API_LOCATION", "Localização '$locationName' com país ID '$countryId' não encontrada após cadastro.")
                    }
                } else {
                    Log.e("API_LOCATION", "Lista de localizações vazia ou nula na resposta do backend.")
                }
            } else {
                Log.e("API_LOCATION", "Erro ao buscar localizações: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<LocationListResponse>, t: Throwable) {
            Log.e("API_LOCATION", "Falha na requisição para buscar localizações: ${t.message}")
        }
    })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelloRegisterTrip(navegacao: NavHostController?) {
    // --- Estados da Viagem ---
    val nomeState = remember { mutableStateOf("") }
    val descricaoState = remember { mutableStateOf("") }
    val dataInicioState = remember { mutableStateOf("") }
    val dataFimState = remember { mutableStateOf("") }
    val fotoPrincipalState = remember { mutableStateOf("") }
    val fotoSecundariaState = remember { mutableStateOf("") }
    val idUsuarioState = remember { mutableStateOf(1) } // Simula um usuário logado

    // --- Estados da Categoria ---
    val expandedCategoryState = remember { mutableStateOf(false) }
    val selectedCategoryState = remember { mutableStateOf<Category?>(null) }
    val idCategoriaState = remember { mutableStateOf<Int?>(null) }

    // --- Estados da Localização ---
    var showLocationDialogState = remember { mutableStateOf(false) }
    var locationNameState = remember { mutableStateOf("") }
    var selectedLocationState = remember { mutableStateOf<LocalizacaoDoGet?>(null) } // Agora guarda LocalizacaoDoGet
    var idLocalizacaoState = remember { mutableStateOf("") }

    // --- Estados do País (dentro do diálogo de localização) ---
    val expandedCountryState = remember { mutableStateOf(false) }
    val selectedCountryState = remember { mutableStateOf<Country?>(null) }


    // --- Listas de Dados Mockados (para Dropdowns) ---
    val categories = listOf(
        Category(1, "Adventure"),
        Category(2, "Cultural"),
        Category(3, "Gastronomic"),
        Category(4, "Leisure"),
        Category(5, "Work"),
        Category(6, "Religious"),
        Category(7, "Sports"),
        Category(8, "Exchange")
    )

    val countries = listOf(
        Country(1, "Afeganistão", "https://flagcdn.com/w320/af.png"),
        Country(2, "África do Sul", "https://flagcdn.com/w320/za.png"),
        Country(3, "Albânia", "https://flagcdn.com/w320/al.png"),
        Country(4, "Alemanha", "https://flagcdn.com/w320/de.png"),
        Country(5, "Andorra", "https://flagcdn.com/w320/ad.png"),
        Country(6, "Angola", "https://flagcdn.com/w320/ao.png"),
        Country(7, "Antígua e Barbuda", "https://flagcdn.com/w320/ag.png"),
        Country(8, "Arábia Saudita", "https://flagcdn.com/w320/sa.png"),
        Country(9, "Argélia", "https://flagcdn.com/w320/dz.png"),
        Country(10, "Argentina", "https://flagcdn.com/w320/ar.png"),
        Country(11, "Armênia", "https://flagcdn.com/w320/am.png"),
        Country(12, "Austrália", "https://flagcdn.com/w320/au.png"),
        Country(13, "Áustria", "https://flagcdn.com/w320/at.png"),
        Country(14, "Azerbaijão", "https://flagcdn.com/w320/az.png"),
        Country(15, "Bahamas", "https://flagcdn.com/w320/bs.png"),
        Country(16, "Bangladesh", "https://flagcdn.com/w320/bd.png"),
        Country(17, "Barbados", "https://flagcdn.com/w320/bb.png"),
        Country(18, "Barém", "https://flagcdn.com/w320/bh.png"),
        Country(19, "Bélgica", "https://flagcdn.com/w320/be.png"),
        Country(20, "Belize", "https://flagcdn.com/w320/bz.png"),
        Country(21, "Benin", "https://flagcdn.com/w320/bj.png"),
        Country(22, "Bielorrússia", "https://flagcdn.com/w320/by.png"),
        Country(23, "Bolívia", "https://flagcdn.com/w320/bo.png"),
        Country(24, "Bósnia e Herzegovina", "https://flagcdn.com/w320/ba.png"),
        Country(25, "Botsuana", "https://flagcdn.com/w320/bw.png"),
        Country(26, "Brasil", "https://flagcdn.com/w320/br.png"),
        Country(27, "Brunei", "https://flagcdn.com/w320/bn.png"),
        Country(28, "Bulgária", "https://flagcdn.com/w320/bg.png"),
        Country(29, "Burkina Faso", "https://flagcdn.com/w320/bf.png"),
        Country(30, "Burundi", "https://flagcdn.com/w320/bi.png"),
        Country(31, "Butão", "https://flagcdn.com/w320/bt.png"),
        Country(32, "Cabo Verde", "https://flagcdn.com/w320/cv.png"),
        Country(33, "Camarões", "https://flagcdn.com/w320/cm.png"),
        Country(34, "Camboja", "https://flagcdn.com/w320/kh.png"),
        Country(35, "Canadá", "https://flagcdn.com/w320/ca.png"),
        Country(36, "Catar", "https://flagcdn.com/w320/qa.png"),
        Country(37, "Cazaquistão", "https://flagcdn.com/w320/kz.png"),
        Country(38, "Chade", "https://flagcdn.com/w320/td.png"),
        Country(39, "Chile", "https://flagcdn.com/w320/cl.png"),
        Country(40, "China", "https://flagcdn.com/w320/cn.png"),
        Country(41, "Chipre", "https://flagcdn.com/w320/cy.png"),
        Country(42, "Colômbia", "https://flagcdn.com/w320/co.png"),
        Country(43, "Comores", "https://flagcdn.com/w320/km.png"),
        Country(44, "Congo (Brazzaville)", "https://flagcdn.com/w320/cg.png"),
        Country(45, "Coreia do Norte", "https://flagcdn.com/w320/kp.png"),
        Country(46, "Coreia do Sul", "https://flagcdn.com/w320/kr.png"),
        Country(47, "Costa do Marfim", "https://flagcdn.com/w320/ci.png"),
        Country(48, "Costa Rica", "https://flagcdn.com/w320/cr.png"),
        Country(49, "Croácia", "https://flagcdn.com/w320/hr.png"),
        Country(50, "Cuba", "https://flagcdn.com/w320/cu.png"),
        Country(51, "Dinamarca", "https://flagcdn.com/w320/dk.png"),
        Country(52, "Djibuti", "https://flagcdn.com/w320/dj.png"),
        Country(53, "Dominica", "https://flagcdn.com/w320/dm.png"),
        Country(54, "Egito", "https://flagcdn.com/w320/eg.png"),
        Country(55, "El Salvador", "https://flagcdn.com/w320/sv.png"),
        Country(56, "Emirados Árabes Unidos", "https://flagcdn.com/w320/ae.png"),
        Country(57, "Equador", "https://flagcdn.com/w320/ec.png"),
        Country(58, "Eritreia", "https://flagcdn.com/w320/er.png"),
        Country(59, "Eslováquia", "https://flagcdn.com/w320/sk.png"),
        Country(60, "Eslovênia", "https://flagcdn.com/w320/si.png"),
        Country(61, "Espanha", "https://flagcdn.com/w320/es.png"),
        Country(62, "Estados Unidos", "https://flagcdn.com/w320/us.png"),
        Country(63, "Estônia", "https://flagcdn.com/w320/ee.png"),
        Country(64, "Eswatini", "https://flagcdn.com/w320/sz.png"),
        Country(65, "Etiópia", "https://flagcdn.com/w320/et.png"),
        Country(66, "Fiji", "https://flagcdn.com/w320/fj.png"),
        Country(67, "Filipinas", "https://flagcdn.com/w320/ph.png"),
        Country(68, "Finlândia", "https://flagcdn.com/w320/fi.png"),
        Country(69, "França", "https://flagcdn.com/w320/fr.png"),
        Country(70, "Gabão", "https://flagcdn.com/w320/ga.png"),
        Country(71, "Gâmbia", "https://flagcdn.com/w320/gm.png"),
        Country(72, "Gana", "https://flagcdn.com/w320/gh.png"),
        Country(73, "Geórgia", "https://flagcdn.com/w320/ge.png"),
        Country(74, "Granada", "https://flagcdn.com/w320/gd.png"),
        Country(75, "Grécia", "https://flagcdn.com/w320/gr.png"),
        Country(76, "Guatemala", "https://flagcdn.com/w320/gt.png"),
        Country(77, "Guiana", "https://flagcdn.com/w320/gy.png"),
        Country(78, "Guiné", "https://flagcdn.com/w320/gn.png"),
        Country(79, "Guiné Equatorial", "https://flagcdn.com/w320/gq.png"),
        Country(80, "Guiné-Bissau", "https://flagcdn.com/w320/gw.png"),
        Country(81, "Haiti", "https://flagcdn.com/w320/ht.png"),
        Country(82, "Holanda", "https://flagcdn.com/w320/nl.png"),
        Country(83, "Honduras", "https://flagcdn.com/w320/hn.png"),
        Country(84, "Hungria", "https://flagcdn.com/w320/hu.png"),
        Country(85, "Iémen", "https://flagcdn.com/w320/ye.png"),
        Country(86, "Ilhas Marshall", "https://flagcdn.com/w320/mh.png"),
        Country(87, "Índia", "https://flagcdn.com/w320/in.png"),
        Country(88, "Indonésia", "https://flagcdn.com/w320/id.png"),
        Country(89, "Irã", "https://flagcdn.com/w320/ir.png"),
        Country(90, "Iraque", "https://flagcdn.com/w320/iq.png"),
        Country(91, "Irlanda", "https://flagcdn.com/w320/ie.png"),
        Country(92, "Islândia", "https://flagcdn.com/w320/is.png"),
        Country(93, "Israel", "https://flagcdn.com/w320/il.png"),
        Country(94, "Itália", "https://flagcdn.com/w320/it.png"),
        Country(95, "Jamaica", "https://flagcdn.com/w320/jm.png"),
        Country(96, "Japão", "https://flagcdn.com/w320/jp.png"),
        Country(97, "Jordânia", "https://flagcdn.com/w320/jo.png"),
        Country(98, "Kiribati", "https://flagcdn.com/w320/ki.png"),
        Country(99, "Kuwait", "https://flagcdn.com/w320/kw.png"),
        Country(100, "Laos", "https://flagcdn.com/w320/la.png"),
        Country(101, "Lesoto", "https://flagcdn.com/w320/ls.png"),
        Country(102, "Letônia", "https://flagcdn.com/w320/lv.png"),
        Country(103, "Líbano", "https://flagcdn.com/w320/lb.png"),
        Country(104, "Libéria", "https://flagcdn.com/w320/lr.png"),
        Country(105, "Líbia", "https://flagcdn.com/w320/ly.png"),
        Country(106, "Liechtenstein", "https://flagcdn.com/w320/li.png"),
        Country(107, "Lituânia", "https://flagcdn.com/w320/lt.png"),
        Country(108, "Luxemburgo", "https://flagcdn.com/w320/lu.png"),
        Country(109, "Macedônia do Norte", "https://flagcdn.com/w320/mk.png"),
        Country(110, "Madagáscar", "https://flagcdn.com/w320/mg.png"),
        Country(111, "Malásia", "https://flagcdn.com/w320/my.png"),
        Country(112, "Malawi", "https://flagcdn.com/w320/mw.png"),
        Country(113, "Maldivas", "https://flagcdn.com/w320/mv.png"),
        Country(114, "Mali", "https://flagcdn.com/w320/ml.png"),
        Country(115, "Malta", "https://flagcdn.com/w320/mt.png"),
        Country(116, "Marrocos", "https://flagcdn.com/w320/ma.png"),
        Country(117, "Maurício", "https://flagcdn.com/w320/mu.png"),
        Country(118, "Mauritânia", "https://flagcdn.com/w320/mr.png"),
        Country(119, "México", "https://flagcdn.com/w320/mx.png"),
        Country(120, "Mianmar", "https://flagcdn.com/w320/mm.png"),
        Country(121, "Micronésia", "https://flagcdn.com/w320/fm.png"),
        Country(122, "Moçambique", "https://flagcdn.com/w320/mz.png"),
        Country(123, "Moldávia", "https://flagcdn.com/w320/md.png"),
        Country(124, "Mônaco", "https://flagcdn.com/w320/mc.png"),
        Country(125, "Mongólia", "https://flagcdn.com/w320/mn.png"),
        Country(126, "Montenegro", "https://flagcdn.com/w320/me.png"),
        Country(127, "Namíbia", "https://flagcdn.com/w320/na.png"),
        Country(128, "Nauru", "https://flagcdn.com/w320/nr.png"),
        Country(129, "Nepal", "https://flagcdn.com/w320/np.png"),
        Country(130, "Nicarágua", "https://flagcdn.com/w320/ni.png"),
        Country(131, "Níger", "https://flagcdn.com/w320/ne.png"),
        Country(132, "Nigéria", "https://flagcdn.com/w320/ng.png"),
        Country(133, "Noruega", "https://flagcdn.com/w320/no.png"),
        Country(134, "Nova Zelândia", "https://flagcdn.com/w320/nz.png"),
        Country(135, "Omã", "https://flagcdn.com/w320/om.png"),
        Country(136, "Palau", "https://flagcdn.com/w320/pw.png"),
        Country(137, "Panamá", "https://flagcdn.com/w320/pa.png"),
        Country(138, "Papua-Nova Guiné", "https://flagcdn.com/w320/pg.png"),
        Country(139, "Paquistão", "https://flagcdn.com/w320/pk.png"),
        Country(140, "Paraguai", "https://flagcdn.com/w320/py.png"),
        Country(141, "Peru", "https://flagcdn.com/w320/pe.png"),
        Country(142, "Polônia", "https://flagcdn.com/w320/pl.png"),
        Country(143, "Portugal", "https://flagcdn.com/w320/pt.png"),
        Country(144, "Quênia", "https://flagcdn.com/w320/ke.png"),
        Country(145, "Quirguistão", "https://flagcdn.com/w320/kg.png"),
        Country(146, "Reino Unido", "https://flagcdn.com/w320/gb.png"),
        Country(147, "República Centro-Africana", "https://flagcdn.com/w320/cf.png"),
        Country(148, "República Checa", "https://flagcdn.com/w320/cz.png"),
        Country(149, "República Democrática do Congo", "https://flagcdn.com/w320/cd.png"),
        Country(150, "República Dominicana", "https://flagcdn.com/w320/do.png"),
        Country(151, "Romênia", "https://flagcdn.com/w320/ro.png"),
        Country(152, "Ruanda", "https://flagcdn.com/w320/rw.png"),
        Country(153, "Rússia", "https://flagcdn.com/w320/ru.png"),
        Country(154, "Samoa", "https://flagcdn.com/w320/ws.png"),
        Country(155, "San Marino", "https://flagcdn.com/w320/sm.png"),
        Country(156, "Santa Lúcia", "https://flagcdn.com/w320/lc.png"),
        Country(157, "São Cristóvão e Neves", "https://flagcdn.com/w320/kn.png"),
        Country(158, "São Tomé e Príncipe", "https://flagcdn.com/w320/st.png"),
        Country(159, "São Vicente e Granadinas", "https://flagcdn.com/w320/vc.png"),
        Country(160, "Seicheles", "https://flagcdn.com/w320/sc.png"),
        Country(161, "Senegal", "https://flagcdn.com/w320/sn.png"),
        Country(162, "Serra Leoa", "https://flagcdn.com/w320/sl.png"),
        Country(163, "Sérvia", "https://flagcdn.com/w320/rs.png"),
        Country(164, "Singapura", "https://flagcdn.com/w320/sg.png"),
        Country(165, "Síria", "https://flagcdn.com/w320/sy.png"),
        Country(166, "Somália", "https://flagcdn.com/w320/so.png"),
        Country(167, "Sri Lanka", "https://flagcdn.com/w320/lk.png"),
        Country(168, "Sudão", "https://flagcdn.com/w320/sd.png"),
        Country(169, "Sudão do Sul", "https://flagcdn.com/w320/ss.png"),
        Country(170, "Suécia", "https://flagcdn.com/w320/se.png"),
        Country(171, "Suíça", "https://flagcdn.com/w320/ch.png"),
        Country(172, "Suriname", "https://flagcdn.com/w320/sr.png"),
        Country(173, "Tailândia", "https://flagcdn.com/w320/th.png"),
        Country(174, "Taiwan", "https://flagcdn.com/w320/tw.png"),
        Country(175, "Tajiquistão", "https://flagcdn.com/w320/tj.png"),
        Country(176, "Tanzânia", "https://flagcdn.com/w320/tz.png"),
        Country(177, "Timor-Leste", "https://flagcdn.com/w320/tl.png"),
        Country(178, "Togo", "https://flagcdn.com/w320/tg.png"),
        Country(179, "Tonga", "https://flagcdn.com/w320/to.png"),
        Country(180, "Trindade e Tobago", "https://flagcdn.com/w320/tt.png"),
        Country(181, "Tunísia", "https://flagcdn.com/w320/tn.png"),
        Country(182, "Turcomenistão", "https://flagcdn.com/w320/tm.png"),
        Country(183, "Turquia", "https://flagcdn.com/w320/tr.png"),
        Country(184, "Tuvalu", "https://flagcdn.com/w320/tv.png"),
        Country(185, "Ucrânia", "https://flagcdn.com/w320/ua.png"),
        Country(186, "Uganda", "https://flagcdn.com/w320/ug.png"),
        Country(187, "Uruguai", "https://flagcdn.com/w320/uy.png"),
        Country(188, "Uzbequistão", "https://flagcdn.com/w320/uz.png"),
        Country(189, "Vanuatu", "https://flagcdn.com/w320/vu.png"),
        Country(190, "Vaticano", "https://flagcdn.com/w320/va.png"),
        Country(191, "Venezuela", "https://flagcdn.com/w320/ve.png"),
        Country(192, "Vietnã", "https://flagcdn.com/w320/vn.png"),
        Country(193, "Zâmbia", "https://flagcdn.com/w320/zm.png"),
        Country(194, "Zimbábue", "https://flagcdn.com/w320/zw.png")
    )

    val scrollState = rememberScrollState()

    // --- Função: registerLocation ---
    // Cadastra uma nova localização no backend.
    // Ela usa LocalizacaoParaPost para enviar os dados e espera LocalizacaoDoGet na resposta.
    fun registerLocation() {
        if (locationNameState.value.isNotEmpty() && selectedCountryState.value != null) {
            val localizacaoToPost = LocalizacaoParaPost(
                id = 0,
                nome = locationNameState.value,
                id_pais = selectedCountryState.value!!.id
            )

            val call = RetrofitFactory().getLocationService().registerLocation(localizacaoToPost)
            call.enqueue(object : Callback<LocalizacaoDoGet> {
                override fun onResponse(call: Call<LocalizacaoDoGet>, response: Response<LocalizacaoDoGet>) {
                    if (response.isSuccessful) {
                        val registeredLocation = response.body()
                        if (registeredLocation != null && registeredLocation.id != 0) {
                            selectedLocationState.value = registeredLocation
                            idLocalizacaoState.value = registeredLocation.id.toString()
                            Log.i("API_LOCATION", "Localização cadastrada e ID recebido: ${registeredLocation.id}")
                            showLocationDialogState.value = false
                            locationNameState.value = ""
                        } else {
                            Log.w("API_LOCATION", "Backend não retornou o ID da localização. Tentando buscar pelo nome e país.")
                            fetchLocationByDetails(
                                locationName = localizacaoToPost.nome,
                                countryId = localizacaoToPost.id_pais,
                                selectedLocationState = selectedLocationState,
                                idLocalizacaoState = idLocalizacaoState
                            ) { foundLoc ->
                                showLocationDialogState.value = false
                                locationNameState.value = ""
                            }
                        }
                    } else {
                        Log.e("API_LOCATION", "Erro ao cadastrar localização: ${response.code()}")
                        // TODO: Mostrar mensagem de erro ao usuário
                    }
                }

                override fun onFailure(call: Call<LocalizacaoDoGet>, t: Throwable) {
                    Log.e("API_LOCATION", "Erro ao cadastrar localização", t)
                    // TODO: Mostrar mensagem de falha de rede ao usuário
                }
            })
        } else {
            Log.w("VALIDATION_LOCATION", "Nome da localização ou país não preenchidos.")
            // TODO: Mensagem para o usuário informando que os campos são obrigatórios
        }
    }

    // --- FUNÇÃO PARA CADASTRAR VIAGEM NO BACKEND ---
    fun registerTrip(newTripPost: ViagemParaPost) { // Recebe o modelo ViagemParaPost
        val call = RetrofitFactory().getTripService().registerTrip(newTripPost)
        call.enqueue(object : Callback<Viagem> { // Espera um objeto 'viagem' completo como resposta
            override fun onResponse(call: Call<Viagem>, response: Response<Viagem>) {
                if (response.isSuccessful) {
                    val createdTrip = response.body()
                    if (createdTrip != null && createdTrip.id != 0) {
                        Log.i("API_TRIP", "Viagem cadastrada com sucesso! ID: ${createdTrip.id}")
                        navegacao?.popBackStack() // Exemplo: volta para a tela anterior após sucesso
                    } else {
                        Log.e("API_TRIP", "Viagem cadastrada, mas backend não retornou ID válido ou corpo vazio.")
                        // Mesmo sem ID, a viagem pode ter sido salva. Verifique o banco de dados.
                        navegacao?.popBackStack() // Ainda pode ser útil voltar
                    }
                } else {
                    Log.e("API_TRIP", "Erro ao cadastrar viagem: ${response.code()} - ${response.errorBody()?.string()}")
                    // TODO: Mostrar mensagem de erro específica para o usuário
                }
            }

            override fun onFailure(call: Call<Viagem>, t: Throwable) {
                Log.e("API_TRIP", "Falha na requisição para cadastrar viagem: ${t.message}")
                // TODO: Mostrar mensagem de erro de conexão ao usuário
            }
        })
    }


    // --- Diálogo para Cadastro de Nova Localização ---
    if (showLocationDialogState.value) {
        AlertDialog(
            onDismissRequest = { showLocationDialogState.value = false },
            title = { Text("Cadastrar Nova Localização") },
            text = {
                Column {
                    OutlinedTextField(
                        value = locationNameState.value,
                        onValueChange = { locationNameState.value = it },
                        label = { Text("Nome da Localização") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ExposedDropdownMenuBox(
                        expanded = expandedCountryState.value,
                        onExpandedChange = { expandedCountryState.value = !expandedCountryState.value }
                    ) {
                        OutlinedTextField(
                            value = selectedCountryState.value?.nome ?: "",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("País") },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(13.dp),
                        )

                        ExposedDropdownMenu(
                            expanded = expandedCountryState.value,
                            onDismissRequest = { expandedCountryState.value = false }
                        ) {
                            countries.forEach { country ->
                                DropdownMenuItem(
                                    text = { Text(text = country.nome) },
                                    onClick = {
                                        selectedCountryState.value = country
                                        expandedCountryState.value = false
                                    }
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { registerLocation() },
                    enabled = locationNameState.value.isNotEmpty() && selectedCountryState.value != null
                ) {
                    Text("Salvar")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showLocationDialogState.value = false },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text("Cancelar", color = Color.Black)
                }
            }
        )
    }

    // --- Layout Principal da Tela de Cadastro de Viagem ---
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_travello),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(80.dp))

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Travello",
                    modifier = Modifier.height(40.dp),
                    contentScale = ContentScale.Crop
                )

                IconButton(onClick = { /* Handle profile click */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.perfil),
                        contentDescription = "User Profile",
                        modifier = Modifier.height(30.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "New Trip",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color(0xFFEA9720),
                            fontWeight = FontWeight.Bold,
                            fontFamily = MontserratFontFamily
                        )

                        ExposedDropdownMenuBox(
                            expanded = expandedCategoryState.value,
                            onExpandedChange = { expandedCategoryState.value = !expandedCategoryState.value }
                        ) {
                            Box(
                                modifier = Modifier
                                    .menuAnchor()
                                    .clickable { expandedCategoryState.value = true }
                                    .padding(vertical = 12.dp)
                            ) {
                                Text(
                                    text = selectedCategoryState.value?.name ?: "Categoria",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = MontserratFontFamily
                                )
                            }

                            ExposedDropdownMenu(
                                expanded = expandedCategoryState.value,
                                onDismissRequest = { expandedCategoryState.value = false }
                            ) {
                                categories.forEach { category ->
                                    DropdownMenuItem(
                                        text = { Text(text = category.name) },
                                        onClick = {
                                            selectedCategoryState.value = category
                                            idCategoriaState.value = category.id
                                            expandedCategoryState.value = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Title:",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFontFamily,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                    OutlinedTextField(
                        value = nomeState.value,
                        onValueChange = { nomeState.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(53.dp),
                        shape = RoundedCornerShape(13.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Choose a cover photo for your trip.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFontFamily
                    )
                    OutlinedTextField(
                        value = fotoPrincipalState.value,
                        onValueChange = { fotoPrincipalState.value = it },
                        placeholder = {
                            Text(
                                "URL:",
                                fontWeight = FontWeight.Medium,
                                fontFamily = MontserratFontFamily,
                                color = Color(0xFFEA9720)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(53.dp),
                        shape = RoundedCornerShape(13.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Choose a nice photo from your trip.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFontFamily
                    )
                    OutlinedTextField(
                        value = fotoSecundariaState.value,
                        onValueChange = { fotoSecundariaState.value = it },
                        placeholder = {
                            Text(
                                "URL:",
                                fontWeight = FontWeight.Medium,
                                fontFamily = MontserratFontFamily,
                                color = Color(0xFFEA9720)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(53.dp),
                        shape = RoundedCornerShape(13.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Tell us about your trip.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFontFamily,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                    OutlinedTextField(
                        value = descricaoState.value,
                        onValueChange = { descricaoState.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        shape = RoundedCornerShape(13.dp),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Departure date",
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontFamily = MontserratFontFamily
                            )
                            OutlinedTextField(
                                value = dataInicioState.value,
                                onValueChange = { dataInicioState.value = it },
                                placeholder = {
                                    Text(
                                        "yyyy-mm-dd",
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = MontserratFontFamily
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(53.dp),
                                shape = RoundedCornerShape(13.dp),
                                singleLine = true
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Return date",
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontFamily = MontserratFontFamily
                            )
                            OutlinedTextField(
                                value = dataFimState.value,
                                onValueChange = { dataFimState.value = it },
                                placeholder = {
                                    Text(
                                        "yyyy-mm-dd",
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = MontserratFontFamily
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(53.dp),
                                shape = RoundedCornerShape(13.dp),
                                singleLine = true
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo de Localização
                    Text(
                        text = "Location",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFontFamily,
                        modifier = Modifier.padding(start = 4.dp)
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedTextField(
                            value = selectedLocationState.value?.nome ?: "",
                            onValueChange = {},
                            readOnly = true,
                            placeholder = { Text("Selecione uma localização") },
                            modifier = Modifier
                                .weight(1f)
                                .height(53.dp),
                            shape = RoundedCornerShape(13.dp),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = { showLocationDialogState.value = true },
                            modifier = Modifier.height(53.dp),
                            shape = RoundedCornerShape(13.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEA9720))
                        ) {
                            Text("Nova", color = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Botão de Cadastrar Viagem
                    Button(
                        onClick = {
                                if (nomeState.value.isNotEmpty() &&
                                    descricaoState.value.isNotEmpty() &&
                                    dataInicioState.value.isNotEmpty() &&
                                    dataFimState.value.isNotEmpty() &&
                                    fotoPrincipalState.value.isNotEmpty() &&
                                    fotoSecundariaState.value.isNotEmpty() &&
                                    selectedCategoryState.value != null &&
                                    selectedLocationState.value != null
                                ) {
                                // Cria o objeto ViagemParaPost para enviar ao backend
                                val novaViagemParaEnviar = ViagemParaPost(
                                    nome = nomeState.value,
                                    descricao = descricaoState.value,
                                    data_inicio = dataInicioState.value,
                                    data_fim = dataFimState.value,
                                    foto_principal = fotoPrincipalState.value,
                                    foto_secundaria = fotoSecundariaState.value,
                                    id_usuario = idUsuarioState.value,
                                    id_categoria = idCategoriaState.value!!,
                                    id_localizacao = selectedLocationState.value!!.id
                                )

                                // Chama a função que faz a requisição Retrofit
                                registerTrip(novaViagemParaEnviar)

                            } else {
                                Log.w("APP_VALIDATION", "Por favor, preencha todos os campos obrigatórios.")
                                // TODO: Mostrar um Toast ou Snackbar para o usuário
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(53.dp),
                        shape = RoundedCornerShape(13.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEA9720))
                    ) {
                        Text(
                            "Cadastrar Viagem",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = MontserratFontFamily
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TravelloRegisterTripPreview() {
    Projeto_TravelloTheme {
        TravelloRegisterTrip(rememberNavController())
    }
}