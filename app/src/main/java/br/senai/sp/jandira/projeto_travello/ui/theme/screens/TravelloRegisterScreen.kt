package br.senai.sp.jandira.ppppdm_school.screens

import android.os.Bundle
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
import br.senai.sp.jandira.projeto_travello.ui.theme.MontserratFontFamily

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
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var Country by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                text = "Travello is a platform to capture, share, and discover travel experiences in a visual, light, and interactive way. It works like a digital Travel Diary...",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = MontserratFontFamily,
                color = Color(0xFFB4B4B4),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Create an account",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = MontserratFontFamily,
                color = Color(0xFFEA9720)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)) {
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

            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp)
                        .padding(end = 5.dp),
                    shape = RoundedCornerShape(13.dp),
                    singleLine = true
                )
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
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
                value = email,
                onValueChange = { email = it },
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
                value = Country,
                onValueChange = { Country = it },
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
                value = password,
                onValueChange = { password = it },
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
                onClick = { /* TODO */ },
                modifier = Modifier
                    .width(220.dp)
                    .height(43.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEA9720))
            ) {
                Text("Register",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MontserratFontFamily,)
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(onClick = { /* TODO */ }) {
                Text("Already have an account? ",
                    color = Color.Gray ,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MontserratFontFamily,)
                Text("Sign in",
                    color = Color(0xFFEA9720),
                    fontWeight = FontWeight.Bold,
                    fontFamily = MontserratFontFamily,)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TravelloRegisterScreenPreview() {
    TravelloRegisterScreen()
}