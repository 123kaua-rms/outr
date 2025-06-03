package br.senai.sp.jandira.projeto_travello.ui.theme.screens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.ppppdm_school.screens.TravelloRegisterScreen
import br.senai.sp.jandira.projeto_travello.R
import br.senai.sp.jandira.projeto_travello.model.UsuarioLoginRequest
import br.senai.sp.jandira.projeto_travello.model.UsuarioLoginResponse
import br.senai.sp.jandira.projeto_travello.service.RetrofitFactory
import br.senai.sp.jandira.projeto_travello.ui.theme.MontserratFontFamily
import br.senai.sp.jandira.projeto_travello.ui.theme.Projeto_TravelloTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




@Composable
fun TravelloLoguinScreen(navegacao: NavHostController?) {
    var emailState = remember { mutableStateOf("") }
    var senhaState = remember { mutableStateOf("") }

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
                    .height(55.dp),

                contentScale = ContentScale.Fit,

            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Welcome Back",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MontserratFontFamily

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "It's good to see you again.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = MontserratFontFamily,
                color = Color(0xFFB4B4B4),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Sign in to your account",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = MontserratFontFamily,
                color = Color(0xFFEA9720)
            )

            Spacer(modifier = Modifier.height(35.dp))



            Text(
                text = "E-mail:",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = MontserratFontFamily,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 40.dp)
            )
            OutlinedTextField(
                value = emailState.value,
                onValueChange = { emailState.value = it },
                modifier = Modifier
                    .width(335.dp)
                    .height(53.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(13.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(25.dp))



            Text(
                text = "Password:",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = MontserratFontFamily,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 40.dp)
            )
            OutlinedTextField(
                value = senhaState.value,
                onValueChange = { senhaState.value = it },
                modifier = Modifier
                    .width(335.dp)
                    .height(53.dp)
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

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = {
                    val user = UsuarioLoginRequest(
                        email = emailState.value,
                        senha = senhaState.value
                    )

                    val call = RetrofitFactory()
                        .getUserService()
                        .loginUser(user)

                    call.enqueue(object : Callback<UsuarioLoginResponse> {
                        override fun onResponse(
                            call: Call<UsuarioLoginResponse>,
                            response: Response<UsuarioLoginResponse>
                        ) {
                            if (response.isSuccessful) {
                                val body = response.body()
                                Log.i("API", "Login bem-sucedido: ${body?.usuario}")
                                navegacao?.navigate("cadastro_viagem")

                            } else {
                                Log.e("API", "Erro no login: ${response.code()} - ${response.errorBody()?.string()}")
                            }
                        }

                        override fun onFailure(call: Call<UsuarioLoginResponse>, t: Throwable) {
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
                Text("Sign in",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MontserratFontFamily)
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(onClick = { /* TODO */ }) {
                Text("Don't have an account? ",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MontserratFontFamily)
                Text("Sign in",
                    color = Color(0xFFEA9720),
                    fontWeight = FontWeight.Bold,
                    fontFamily = MontserratFontFamily)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TravelloLoguinScreenPreview() {
        TravelloLoguinScreen(null)
    }
