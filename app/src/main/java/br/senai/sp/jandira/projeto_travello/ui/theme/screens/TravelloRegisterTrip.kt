package br.senai.sp.jandira.projeto_travello.ui.theme.screens

import androidx.compose.foundation.Image
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
import br.senai.sp.jandira.ppppdm_school.screens.TravelloRegisterScreen
import br.senai.sp.jandira.projeto_travello.R
import br.senai.sp.jandira.projeto_travello.ui.theme.MontserratFontFamily
import br.senai.sp.jandira.projeto_travello.ui.theme.Projeto_TravelloTheme

@Composable
fun TravelloRegisterTrip(navegacao: NavHostController?) {
    val scrollState = rememberScrollState()

    var categoryState = remember { mutableStateOf("") }
    var titleState = remember { mutableStateOf("") }
    var departureDateState = remember { mutableStateOf("") }
    var returnDateState = remember { mutableStateOf("") }
    var locationNameState = remember { mutableStateOf("") }
    var nameState = remember { mutableStateOf("") }
    var countryState = remember { mutableStateOf("") }

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
                    modifier = Modifier.height(40.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(30.dp))

                IconButton(onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.perfil),
                        contentDescription = "Logo Travello",
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

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Text(
                                text = categoryState.value.ifBlank { "Category" },
                                fontWeight = FontWeight.Bold,
                                fontFamily = MontserratFontFamily,
                                color = Color.Black,
                                fontSize = 16.sp,
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown",
                                tint = Color.Black
                            )
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
                        value = titleState.value,
                        onValueChange = { titleState.value = it },
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
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors
                            (containerColor = Color(0xFFEA9720))
                    ) {
                        Text(
                            "Add file",
                            fontWeight = FontWeight.Bold,
                            fontFamily = MontserratFontFamily
                        )
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        "Choose a nice photo from your trip.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFontFamily
                    )
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors
                            (containerColor = Color(0xFFEA9720))
                    ) {
                        Text(
                            "Add file",
                            fontWeight = FontWeight.Bold,
                            fontFamily = MontserratFontFamily
                        )
                    }

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
                        value = locationNameState.value,
                        onValueChange = { locationNameState.value = it },
                        modifier = Modifier
                            .width(385.dp)
                            .height(150.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(13.dp),
                        singleLine = true
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
                                value = departureDateState.value,
                                onValueChange = { departureDateState.value = it },
                                placeholder = {
                                    Text(
                                        "dd/mm/yyyy",
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
                                value = returnDateState.value,
                                onValueChange = { returnDateState.value = it },
                                placeholder = {
                                    Text(
                                        "dd/mm/yyyy",
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
                        value = nameState.value,
                        onValueChange = { nameState.value = it },
                        placeholder = {
                            Text(
                                "Name",
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

                    OutlinedTextField(
                        value = countryState.value,
                        onValueChange = { countryState.value = it },
                        placeholder = {
                            Text(
                                "Country",
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

                    Spacer(modifier = Modifier.height(145.dp))

                    Button(
                        modifier = Modifier.padding(start = 270.dp),
                        onClick = { },
                        colors = ButtonDefaults.buttonColors
                            (containerColor = Color(0xFFEA9720))
                    ) {
                        Text(
                            "Submit",
                            fontWeight = FontWeight.Bold,
                            fontFamily = MontserratFontFamily
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
        TravelloRegisterTrip(null)
}

