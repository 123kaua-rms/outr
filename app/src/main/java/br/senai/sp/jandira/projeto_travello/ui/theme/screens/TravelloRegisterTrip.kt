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
import br.senai.sp.jandira.ppppdm_school.screens.Country
import br.senai.sp.jandira.ppppdm_school.screens.TravelloRegisterScreen
import br.senai.sp.jandira.projeto_travello.R
import br.senai.sp.jandira.projeto_travello.model.Category
import br.senai.sp.jandira.projeto_travello.model.usuario
import br.senai.sp.jandira.projeto_travello.model.viagem
import br.senai.sp.jandira.projeto_travello.service.RetrofitFactory
import br.senai.sp.jandira.projeto_travello.ui.theme.MontserratFontFamily
import br.senai.sp.jandira.projeto_travello.ui.theme.Projeto_TravelloTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelloRegisterTrip(navegacao: NavHostController?) {
    var idPaisState = remember { mutableStateOf(0) }

    val expandedCategoryState = remember { mutableStateOf(false) }
    val selectedCategoryState = remember { mutableStateOf<Category?>(null) }
    val idCategoriaState = remember { mutableStateOf<Int?>(null) }

    val categories = remember {
        listOf(
            Category(1, "Aventura"),
            Category(2, "Cultural"),
            Category(3, "Gastronômica"),
            Category(4, "Lazer"),
            Category(5, "Trabalho"),
            Category(6, "Religiosa"),
            Category(7, "Esportiva"),
            Category(8, "Intercâmbio")
        )
    }

    val countries = remember {
        listOf(
            Country(1, "Afeganistão"),
            Country(2, "África do Sul"),
            Country(3, "Albânia"),
            Country(4, "Alemanha"),
            Country(5, "Andorra"),
            Country(6, "Angola"),
            Country(7, "Antígua e Barbuda"),
            Country(8, "Arábia Saudita"),
            Country(9, "Argélia"),
            Country(10, "Argentina"),
            Country(11, "Armênia"),
            Country(12, "Austrália"),
            Country(13, "Áustria"),
            Country(14, "Azerbaijão"),
            Country(15, "Bahamas"),
            Country(16, "Bangladesh"),
            Country(17, "Barbados"),
            Country(18, "Barém"),
            Country(19, "Bélgica"),
            Country(20, "Belize"),
            Country(21, "Benin"),
            Country(22, "Bielorrússia"),
            Country(23, "Bolívia"),
            Country(24, "Bósnia e Herzegovina"),
            Country(25, "Botsuana"),
            Country(26, "Brasil"),
            Country(27, "Brunei"),
            Country(28, "Bulgária"),
            Country(29, "Burkina Faso"),
            Country(30, "Burundi"),
            Country(31, "Butão"),
            Country(32, "Cabo Verde"),
            Country(33, "Camarões"),
            Country(34, "Camboja"),
            Country(35, "Canadá"),
            Country(36, "Catar"),
            Country(37, "Cazaquistão"),
            Country(38, "Chade"),
            Country(39, "Chile"),
            Country(40, "China"),
            Country(41, "Chipre"),
            Country(42, "Colômbia"),
            Country(43, "Comores"),
            Country(44, "Congo (Brazzaville)"),
            Country(45, "Coreia do Norte"),
            Country(46, "Coreia do Sul"),
            Country(47, "Costa do Marfim"),
            Country(48, "Costa Rica"),
            Country(49, "Croácia"),
            Country(50, "Cuba"),
            Country(51, "Dinamarca"),
            Country(52, "Djibuti"),
            Country(53, "Dominica"),
            Country(54, "Egito"),
            Country(55, "El Salvador"),
            Country(56, "Emirados Árabes Unidos"),
            Country(57, "Equador"),
            Country(58, "Eritreia"),
            Country(59, "Eslováquia"),
            Country(60, "Eslovênia"),
            Country(61, "Espanha"),
            Country(62, "Estados Unidos"),
            Country(63, "Estônia"),
            Country(64, "Eswatini"),
            Country(65, "Etiópia"),
            Country(66, "Fiji"),
            Country(67, "Filipinas"),
            Country(68, "Finlândia"),
            Country(69, "França"),
            Country(70, "Gabão"),
            Country(71, "Gâmbia"),
            Country(72, "Gana"),
            Country(73, "Geórgia"),
            Country(74, "Granada"),
            Country(75, "Grécia"),
            Country(76, "Guatemala"),
            Country(77, "Guiana"),
            Country(78, "Guiné"),
            Country(79, "Guiné Equatorial"),
            Country(80, "Guiné-Bissau"),
            Country(81, "Haiti"),
            Country(82, "Holanda"),
            Country(83, "Honduras"),
            Country(84, "Hungria"),
            Country(85, "Iémen"),
            Country(86, "Ilhas Marshall"),
            Country(87, "Índia"),
            Country(88, "Indonésia"),
            Country(89, "Irã"),
            Country(90, "Iraque"),
            Country(91, "Irlanda"),
            Country(92, "Islândia"),
            Country(93, "Israel"),
            Country(94, "Itália"),
            Country(95, "Jamaica"),
            Country(96, "Japão"),
            Country(97, "Jordânia"),
            Country(98, "Kiribati"),
            Country(99, "Kuwait"),
            Country(100, "Laos"),
            Country(101, "Lesoto"),
            Country(102, "Letônia"),
            Country(103, "Líbano"),
            Country(104, "Libéria"),
            Country(105, "Líbia"),
            Country(106, "Liechtenstein"),
            Country(107, "Lituânia"),
            Country(108, "Luxemburgo"),
            Country(109, "Macedônia do Norte"),
            Country(110, "Madagáscar"),
            Country(111, "Malásia"),
            Country(112, "Malawi"),
            Country(113, "Maldivas"),
            Country(114, "Mali"),
            Country(115, "Malta"),
            Country(116, "Marrocos"),
            Country(117, "Maurício"),
            Country(118, "Mauritânia"),
            Country(119, "México"),
            Country(120, "Mianmar"),
            Country(121, "Micronésia"),
            Country(122, "Moçambique"),
            Country(123, "Moldávia"),
            Country(124, "Mônaco"),
            Country(125, "Mongólia"),
            Country(126, "Montenegro"),
            Country(127, "Namíbia"),
            Country(128, "Nauru"),
            Country(129, "Nepal"),
            Country(130, "Nicarágua"),
            Country(131, "Níger"),
            Country(132, "Nigéria"),
            Country(133, "Noruega"),
            Country(134, "Nova Zelândia"),
            Country(135, "Omã"),
            Country(136, "Palau"),
            Country(137, "Panamá"),
            Country(138, "Papua-Nova Guiné"),
            Country(139, "Paquistão"),
            Country(140, "Paraguai"),
            Country(141, "Peru"),
            Country(142, "Polônia"),
            Country(143, "Portugal"),
            Country(144, "Quênia"),
            Country(145, "Quirguistão"),
            Country(146, "Reino Unido"),
            Country(147, "República Centro-Africana"),
            Country(148, "República Checa"),
            Country(149, "República Democrática do Congo"),
            Country(150, "República Dominicana"),
            Country(151, "Romênia"),
            Country(152, "Ruanda"),
            Country(153, "Rússia"),
            Country(154, "Samoa"),
            Country(155, "San Marino"),
            Country(156, "Santa Lúcia"),
            Country(157, "São Cristóvão e Neves"),
            Country(158, "São Tomé e Príncipe"),
            Country(159, "São Vicente e Granadinas"),
            Country(160, "Seicheles"),
            Country(161, "Senegal"),
            Country(162, "Serra Leoa"),
            Country(163, "Sérvia"),
            Country(164, "Singapura"),
            Country(165, "Síria"),
            Country(166, "Somália"),
            Country(167, "Sri Lanka"),
            Country(168, "Sudão"),
            Country(169, "Sudão do Sul"),
            Country(170, "Suécia"),
            Country(171, "Suíça"),
            Country(172, "Suriname"),
            Country(173, "Tailândia"),
            Country(174, "Taiwan"),
            Country(175, "Tajiquistão"),
            Country(176, "Tanzânia"),
            Country(177, "Timor-Leste"),
            Country(178, "Togo"),
            Country(179, "Tonga"),
            Country(180, "Trindade e Tobago"),
            Country(181, "Tunísia"),
            Country(182, "Turcomenistão"),
            Country(183, "Turquia"),
            Country(184, "Tuvalu"),
            Country(185, "Ucrânia"),
            Country(186, "Uganda"),
            Country(187, "Uruguai"),
            Country(188, "Uzbequistão"),
            Country(189, "Vanuatu"),
            Country(190, "Vaticano"),
            Country(191, "Venezuela"),
            Country(192, "Vietnã"),
            Country(193, "Zâmbia"),
            Country(194, "Zimbábue")
        )
    }
    var expandedState = remember { mutableStateOf(false) }
    var selectedCountryState = remember { mutableStateOf<Country?>(null) }

    val scrollState = rememberScrollState()

    var foto_principalState = remember { mutableStateOf("") }
    var foto_secundariaState = remember { mutableStateOf("") }
    var nomeState = remember { mutableStateOf("") }
    var data_inicioState = remember { mutableStateOf("") }
    var descricaoState = remember { mutableStateOf("") }
    var data_fimState = remember { mutableStateOf("") }
    // Removed id_categoriaState and id_localizacaoState as they are already managed by selectedCategoryState and idPaisState
    var id_usuarioState = remember { mutableStateOf("") } // This will likely come from user session/login

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

        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(130.dp))

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Travello",
                    modifier = Modifier.height(35.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(35.dp))

                IconButton(onClick = { /* Handle profile click */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.perfil),
                        contentDescription = "User Profile",
                        modifier = Modifier.height(30.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
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
                            .padding(top = 25.dp),
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

                        Spacer(modifier = Modifier.width(100.dp))
                        Spacer(modifier = Modifier.height(100.dp))


                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Column {
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
                                            color = if (selectedCategoryState.value != null) Color.Black else Color.Black,
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
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        text = "Title:",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFontFamily,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 1.dp)
                    )
                    OutlinedTextField(
                        value = nomeState.value,
                        onValueChange = { nomeState.value = it },
                        modifier = Modifier
                            .width(385.dp)
                            .height(53.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(13.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        "Choose a cover photo for your trip.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFontFamily
                    )

                    OutlinedTextField(
                        value = foto_principalState.value,
                        onValueChange = { foto_principalState.value = it },
                        placeholder = {
                            Text(
                                "URL:",
                                fontWeight = FontWeight.Medium,
                                fontFamily = MontserratFontFamily,
                                color = Color(0xFFEA9720)
                            )
                        },
                        modifier = Modifier
                            .width(385.dp)
                            .height(53.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(13.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        "Choose a nice photo from your trip.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFontFamily
                    )
                    OutlinedTextField(
                        value = foto_secundariaState.value,
                        onValueChange = { foto_secundariaState.value = it },
                        placeholder = {
                            Text(
                                "URL:   ",
                                fontWeight = FontWeight.Medium,
                                fontFamily = MontserratFontFamily,
                                color = Color(0xFFEA9720)
                            )
                        },
                        modifier = Modifier
                            .width(385.dp)
                            .height(53.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(13.dp),
                        singleLine = true,
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        text = "Tell us about your trip.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFontFamily,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 10.dp)
                    )
                    OutlinedTextField(
                        value = descricaoState.value, // Changed to descricaoState for description
                        onValueChange = { descricaoState.value = it },
                        modifier = Modifier
                            .width(385.dp)
                            .height(150.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(13.dp),
                        // Removed singleLine as descriptions are usually multi-line
                    )

                    Spacer(modifier = Modifier.height(25.dp))

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
                                value = data_inicioState.value,
                                onValueChange = { data_inicioState.value = it },
                                placeholder = {
                                    Text(
                                        "yyyy/mm/dd",
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = MontserratFontFamily
                                    )
                                },
                                modifier = Modifier
                                    .width(335.dp)
                                    .height(53.dp)
                                    .align(Alignment.CenterHorizontally),
                                shape = RoundedCornerShape(13.dp),
                                singleLine = true
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Return date",
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontFamily = MontserratFontFamily
                            )
                            OutlinedTextField(
                                value = data_fimState.value,
                                onValueChange = { data_fimState.value = it },
                                placeholder = {
                                    Text(
                                        "yyyy/mm/dd",
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = MontserratFontFamily
                                    )
                                },
                                modifier = Modifier
                                    .width(335.dp)
                                    .height(53.dp)
                                    .align(Alignment.CenterHorizontally),
                                shape = RoundedCornerShape(13.dp),
                                singleLine = true
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        text = "Location",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFontFamily,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 1.dp)
                    )

                    OutlinedTextField(
                        value = nomeState.value, // This seems to be for location name, but already used for trip title. Consider renaming or using a new state for location name.
                        onValueChange = { nomeState.value = it },
                        placeholder = {
                            Text(
                                "City/State/Region Name", // Changed placeholder to be more specific for location
                                fontWeight = FontWeight.Medium,
                                fontFamily = MontserratFontFamily
                            )
                        },
                        modifier = Modifier
                            .width(385.dp)
                            .height(53.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(13.dp),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // País Dropdown
                    ExposedDropdownMenuBox(
                        expanded = expandedState.value,
                        onExpandedChange = { expandedState.value = !expandedState.value }
                    ) {
                        OutlinedTextField(
                            value = selectedCountryState.value?.nome ?: "",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Country") }, // Changed label to English
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
                            expanded = expandedState.value,
                            onDismissRequest = { expandedState.value = false }
                        ) {
                            countries.forEach { country ->
                                DropdownMenuItem(
                                    text = { Text(text = country.nome) },
                                    onClick = {
                                        selectedCountryState.value = country
                                        idPaisState.value = country.id
                                        expandedState.value = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(145.dp))

                    Button(
                        onClick = {
                            val trip = viagem(
                                nome = nomeState.value, // This is currently also used for location name, consider separate state
                                descricao = descricaoState.value,
                                data_inicio = data_inicioState.value,
                                data_fim = data_fimState.value,
                                foto_principal = foto_principalState.value,
                                foto_secundaria = foto_secundariaState.value,
                                id_categoria = idCategoriaState.value ?: 0, // Use the selected category ID
                                id_localizacao = id.value, // Use the selected country ID
                                id_usuario = 1 // **IMPORTANT:** Replace `1` with the actual user ID from your session/login
                            )
                            val call = RetrofitFactory()
                                .getTripService()
                                .registerTrip(trip)

                            call.enqueue(object : Callback<viagem> {
                                override fun onResponse(
                                    call: Call<viagem>,
                                    response: Response<viagem>
                                ) {
                                    if (response.isSuccessful) {
                                        Log.i("API", "Trip registered successfully: ${response.body()}")
                                        navegacao?.navigate("home") // Navigate to a success screen or home
                                    } else {
                                        Log.e("API", "Error registering trip: ${response.code()}")
                                        // You might want to show a Snackbar or Toast here
                                    }
                                }

                                override fun onFailure(call: Call<viagem>, t: Throwable) {
                                    Log.e("API", "Request failed: ${t.message}")
                                    // You might want to show a Snackbar or Toast here
                                }
                            })
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEA9720)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            "Register Trip",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = MontserratFontFamily,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TravelloRegisterTripPreview() {
    TravelloRegisterTrip(rememberNavController())
}