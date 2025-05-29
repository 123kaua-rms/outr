package br.senai.sp.jandira.ppppdm_school.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.projeto_travello.R
import br.senai.sp.jandira.projeto_travello.model.usuario
import br.senai.sp.jandira.projeto_travello.service.RetrofitFactory
import br.senai.sp.jandira.projeto_travello.ui.theme.MontserratFontFamily
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelloRegisterScreen()
        }
    }
}

@Composable
fun TravelloRegisterScreen() {
    var nome_completoState = remember { mutableStateOf("") }
    var usernameState = remember { mutableStateOf("") }
    var emailState = remember { mutableStateOf("") }
    var id_paisState = remember { mutableStateOf("") }
    var senhaState = remember { mutableStateOf("") }
    val dataAtual = LocalDate.now().toString()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
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
                fontFamily = MontserratFontFamily,
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                Text(
                    text = "Name:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = MontserratFontFamily,
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.width(120.dp))

                Text(
                    text = "Username:",
                    fontSize = 11.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontFamily = MontserratFontFamily,
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                OutlinedTextField(
                    value = nome_completoState.value,
                    onValueChange = { nome_completoState.value = it },
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp)
                        .padding(end = 5.dp),
                    shape = RoundedCornerShape(13.dp),
                    singleLine = true
                )
                OutlinedTextField(
                    value = usernameState.value,
                    onValueChange = { usernameState.value = it },
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp)
                        .padding(start = 5.dp),
                    shape = RoundedCornerShape(13.dp),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "E-mail:",
                fontSize = 11.sp,
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
                    .width(280.dp)
                    .height(36.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(13.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Country:",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = MontserratFontFamily,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 40.dp)
            )
            OutlinedTextField(
                value = id_paisState.value,
                onValueChange = { id_paisState.value = it },
                modifier = Modifier
                    .width(280.dp)
                    .height(36.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(13.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Password:",
                fontSize = 11.sp,
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
                    .width(280.dp)
                    .height(36.dp)
                    .align(Alignment.CenterHorizontally),
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

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    val user = usuario(
                        nome_completo = nome_completoState.value,
                        username = usernameState.value,
                        email = emailState.value,
                        id_pais = id_paisState.value.toIntOrNull() ?: 0,
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
                    .width(280.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(13.dp)
            ) {
                Text(
                    text = "Register",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MontserratFontFamily
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TravelloRegisterScreenPreview() {
    TravelloRegisterScreen()
}