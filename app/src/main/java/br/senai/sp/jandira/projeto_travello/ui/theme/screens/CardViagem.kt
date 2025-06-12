package br.senai.sp.jandira.projeto_travello.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.projeto_travello.R
import br.senai.sp.jandira.projeto_travello.model.modelUsuario.usuario
import coil.compose.AsyncImage

@Composable
fun CardViagem(

    foto: String = "URL da imagem",
    name_viagem: String = "Nome da viagem",
    localizacao : String = "Localizacao da viagem",
    ususario : String = "nome do usuario"



    ) {

    Card(
        modifier = Modifier
            .aspectRatio(1f),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Parte superior do card (imagem ou cor de fundo)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFF64B5F6))
            ){
                AsyncImage(
                    model = foto,
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
            }

            // Parte inferior do card (conteúdo branco com infos)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                // Ícone e username
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.perfil),
                        contentDescription = "User Icon",
                        modifier = Modifier.size(16.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = ususario,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Nome e localização do destino
                Text(
                    text = name_viagem,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(6.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = "Location Icon",
                        modifier = Modifier.size(14.dp),
                        tint = Color(0xFF616161)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = localizacao,
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun CardViagemPreviw() {
    CardViagem()
}