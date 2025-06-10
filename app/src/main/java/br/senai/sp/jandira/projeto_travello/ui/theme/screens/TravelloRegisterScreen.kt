package br.senai.sp.jandira.ppppdm_school.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import br.senai.sp.jandira.projeto_travello.R
import br.senai.sp.jandira.projeto_travello.modelUsuario.usuario
import br.senai.sp.jandira.projeto_travello.service.RetrofitFactory
import br.senai.sp.jandira.projeto_travello.ui.theme.MontserratFontFamily
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

data class Country(val id: Int, val nome: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelloRegisterScreen(navegacao: NavHostController?) {
    var nomeCompletoState = remember { mutableStateOf("") }
    var usernameState = remember { mutableStateOf("") }
    var emailState = remember { mutableStateOf("") }
    var idPaisState = remember { mutableStateOf(0) } // armazenar o ID do país
    var senhaState = remember { mutableStateOf("") }
    val dataAtual = LocalDate.now().toString()

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

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Travel Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(6.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(13.dp))

            Text(
                text = "Welcome to Travello",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MontserratFontFamily
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Create an account",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = MontserratFontFamily,
                color = Color(0xFFEA9720)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Nome e username
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Name:",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = MontserratFontFamily,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Username:",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontFamily = MontserratFontFamily
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = nomeCompletoState.value,
                    onValueChange = { nomeCompletoState.value = it },
                    modifier = Modifier
                        .weight(2f)
                        .height(53.dp)
                        .padding(end = 5.dp),
                    shape = RoundedCornerShape(13.dp),
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 20.sp)

                )
                OutlinedTextField(
                    value = usernameState.value,
                    onValueChange = { usernameState.value = it },
                    modifier = Modifier
                        .weight(2f)
                        .height(53.dp)
                        .padding(start = 5.dp),
                    shape = RoundedCornerShape(13.dp),
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 20.sp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Email
            Text(
                text = "E-mail:",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontFamily = MontserratFontFamily,
                modifier = Modifier
                    .align(Alignment.Start)
            )

            OutlinedTextField(
                value = emailState.value,
                onValueChange = { emailState.value = it },
                modifier = Modifier
                    .width(385.dp)
                    .height(53.dp),
                shape = RoundedCornerShape(13.dp),
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp)

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
                    expanded = expandedState.value,
                    onDismissRequest = { expandedState.value = false }
                ) {
                    countries.forEach { country ->
                        DropdownMenuItem(
                            text = { Text(text = country.nome) },
                            modifier = Modifier.background(color = Color.White),
                            onClick = {
                                selectedCountryState.value = country
                                idPaisState.value = country.id
                                expandedState.value = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Senha
            Text(
                text = "Password:",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontFamily = MontserratFontFamily,
                modifier = Modifier
                    .align(Alignment.Start)
            )

            OutlinedTextField(
                value = senhaState.value,
                onValueChange = { senhaState.value = it },
                modifier = Modifier
                    .width(385.dp)
                    .height(53.dp),
                shape = RoundedCornerShape(13.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp),
                        trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon",
                        tint = Color(0xFFC4C4C4)
                    )
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    val user = usuario(
                        nome_completo = nomeCompletoState.value,
                        username = usernameState.value,
                        email = emailState.value,
                        id_pais = idPaisState.value,
                        senha = senhaState.value,
                        data_cadastro = dataAtual
                    )
                    val call = RetrofitFactory()
                        .getUserService()
                        .registerUser(user)

                    call.enqueue(object : Callback<usuario> {
                        override fun onResponse(
                            call: Call<usuario>,
                            response: Response<usuario>
                        ) {
                            if (response.isSuccessful) {
                                Log.i("API", "Usuário cadastrado com sucesso: ${response.body()}")
                                navegacao?.navigate("login")
                            } else {
                                Log.e("API", "Erro ao cadastrar: ${response.code()}")
                            }
                        }

                        override fun onFailure(call: Call<usuario>, t: Throwable) {
                            Log.e("API", "Falha na requisição: ${t.message}")
                        }
                    })
                },
                modifier = Modifier
                    .width(220.dp)
                    .height(43.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEA9720))
            ) {
                Text(
                    "Register",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MontserratFontFamily,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextButton(onClick = {
                navegacao?.navigate("login")
            }) {
                Text(
                    "Already have an account? ",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MontserratFontFamily,
                )
                Text(
                    "Sign in",
                    color = Color(0xFFEA9720),
                    fontWeight = FontWeight.Bold,
                    fontFamily = MontserratFontFamily,
                )
            }
        }
    }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TravelloRegisterScreenPreview() {
    TravelloRegisterScreen(navegacao = null)
}
