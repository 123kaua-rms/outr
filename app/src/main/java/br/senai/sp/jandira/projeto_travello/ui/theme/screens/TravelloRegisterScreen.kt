package br.senai.sp.jandira.ppppdm_school.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.ExposedDropdownMenuDefaults

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.projeto_travello.R
import br.senai.sp.jandira.projeto_travello.model.Country
import br.senai.sp.jandira.projeto_travello.model.usuario
import br.senai.sp.jandira.projeto_travello.service.RetrofitFactory
import br.senai.sp.jandira.projeto_travello.ui.theme.MontserratFontFamily
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

@Composable
fun TravelloRegisterScreen(navegacao: NavHostController?) {
    var nomeCompletoState = remember { mutableStateOf("") }
    var usernameState = remember { mutableStateOf("") }
    var emailState = remember { mutableStateOf("") }
    var idPaisState = remember { mutableStateOf("") }
    var senhaState = remember { mutableStateOf("") }
    val dataAtual = LocalDate.now().toString()


    val paisesState = produceState<List<Country>>(initialValue = emptyList()) {
        value = try {
            val service = RetrofitFactory().getPaisService()
            Log.d("Debug", "Service OK")

            val response = service.getCountries()
            Log.d("Debug", "Response OK: $response")

            val paisesApi = response.paises
            Log.d("Debug", "Paises OK: $paisesApi")

            if (paisesApi.isNullOrEmpty()) {
                listOf(
                    Country(1, "Brasil", "https://flagcdn.com/w320/br.png"),
                    Country(2, "Portugal", "https://flagcdn.com/w320/pt.png"),
                    Country(3, "Angola", "https://flagcdn.com/w320/ao.png")
                )
            } else {
                paisesApi
            }
        } catch (e: Exception) {
            listOf(
                Country(1, "Chile", "https://flagcdn.com/w320/br.png"),
                Country(2, "Paraguai", "https://flagcdn.com/w320/pt.png"),
                Country(3, "Argentina", "https://flagcdn.com/w320/ao.png")
            )
        }
    }

    var selectedCountry by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val filteredCountries = paisesState.value.filter {
        it.nome.contains(selectedCountry, ignoreCase = true)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
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
                    .padding(horizontal = 40.dp)
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
                    .padding(horizontal = 40.dp)
            ) {
                OutlinedTextField(
                    value = nomeCompletoState.value,
                    onValueChange = { nomeCompletoState.value = it },
                    modifier = Modifier
                        .weight(1f)
                        .height(53.dp)
                        .padding(end = 5.dp),
                    shape = RoundedCornerShape(13.dp),
                    singleLine = true
                )
                OutlinedTextField(
                    value = usernameState.value,
                    onValueChange = { usernameState.value = it },
                    modifier = Modifier
                        .weight(1f)
                        .height(53.dp)
                        .padding(start = 5.dp),
                    shape = RoundedCornerShape(13.dp),
                    singleLine = true
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
                    .padding(start = 40.dp)
            )

            OutlinedTextField(
                value = emailState.value,
                onValueChange = { emailState.value = it },
                modifier = Modifier
                    .width(335.dp)
                    .height(53.dp),
                shape = RoundedCornerShape(13.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // País
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedCountry,
                    onValueChange = {
                        selectedCountry = it
                        expanded = true
                    },
                    label = { Text("Selecione o país") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor(),
                    singleLine = true,
                    shape = RoundedCornerShape(13.dp)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    filteredCountries.forEach { country ->
                        DropdownMenuItem(
                            text = { Text(country.nome) },
                            onClick = {
                                selectedCountry = country.nome
                                idPaisState.value = country.id.toString()
                                expanded = false
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
                    .padding(start = 40.dp)
            )

            OutlinedTextField(
                value = senhaState.value,
                onValueChange = { senhaState.value = it },
                modifier = Modifier
                    .width(335.dp)
                    .height(53.dp),
                shape = RoundedCornerShape(13.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon",
                        tint = Color(0xFFC4C4C4)
                    )
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Botão de cadastro
            Button(
                onClick = {
                    val user = usuario(
                        nome_completo = nomeCompletoState.value,
                        username = usernameState.value,
                        email = emailState.value,
                        id_pais = idPaisState.value.toIntOrNull() ?: 0,
                        senha = senhaState.value,
                        data_cadastro = dataAtual
                    )

                    RetrofitFactory()
                        .getUserService()
                        .registerUser(user)
                        .enqueue(object : Callback<usuario> {
                            override fun onResponse(
                                call: Call<usuario>,
                                response: Response<usuario>
                            ) {
                                if (response.isSuccessful) {
                                    Log.i("API", "Usuário cadastrado com sucesso: ${response.body()}")
                                    navegacao?.navigate("login")
                                } else {
                                    Log.e("API", "Erro ao cadastrar: ${response.code()} - ${response.errorBody()?.string()}")
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
                    text = "Register",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MontserratFontFamily
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botão de login
            TextButton(onClick = {
                navegacao?.navigate(route = "login")
            }) {
                Row {
                    Text(
                        text = "Already have an account? ",
                        color = Color.Gray,
                        fontFamily = MontserratFontFamily
                    )
                    Text(
                        text = "Sign in",
                        color = Color(0xFFEA9720),
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFontFamily
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TravelloRegisterScreenPreview() {
    TravelloRegisterScreen(null)
}
