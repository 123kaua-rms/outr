package br.senai.sp.jandira.projeto_travello.ui.theme.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import br.senai.sp.jandira.projeto_travello.R
import br.senai.sp.jandira.projeto_travello.model.modelViagem.ViagemParaGet
import br.senai.sp.jandira.projeto_travello.model.modelViagem.ViagemResponse
import br.senai.sp.jandira.projeto_travello.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun TravelloHomeScreen(navegacao: NavHostController?) {

    // Estado para armazenar a lista de viagens
    val viagemList = remember { mutableStateOf(listOf<ViagemParaGet>()) }

    // Chamada à API para buscar viagens
    LaunchedEffect(Unit) {
        val call = RetrofitFactory()
            .getTripService()
            .getViagem()

        call.enqueue(object : Callback<ViagemResponse> {
            override fun onResponse(call: Call<ViagemResponse>, response: Response<ViagemResponse>) {
                if (response.isSuccessful) {
                    viagemList.value = response.body()?.viagens ?: emptyList()
                }
            }

            override fun onFailure(call: Call<ViagemResponse>, t: Throwable) {
                // Log de erro ou tratamento
                t.printStackTrace()
            }
        })
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2196F3))
                    .padding(15.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp, start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Logo à esquerda
                        Image(
                            painter = painterResource(id = R.drawable.img_2),
                            contentDescription = "Logo Travello",
                            modifier = Modifier
                                .height(90.dp)
                                .width(90.dp),
                            contentScale = ContentScale.Fit
                        )

                        // Ícone do usuário à direita
                        IconButton(onClick = { /* ação de perfil */ }) {
                            Image(
                                painter = painterResource(id = R.drawable.perfil),
                                contentDescription = "User Profile",
                                modifier = Modifier.size(20.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(1.dp))

                    Text(
                        text = "Hi, username!",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Find the best place",
                        color = Color.Black,
                        fontSize = 14.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Text(
                text = "Popular destination",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viagemList.value) { viagem ->
                    // Pegando os dados da viagem
                    val local = viagem.localizacao.firstOrNull()?.nome ?: "Localização desconhecida"
                    val user = viagem.usuario.firstOrNull()?.username ?: "Usuário desconhecido"

                    CardViagem(
                        foto = viagem.fotoPrincipal,
                        name_viagem = viagem.nome,
                        localizacao = local,
                        ususario = user
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TravelloHomeScreenPreview() {
    TravelloHomeScreen(null)
}
