package br.senai.sp.jandira.projeto_travello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.ppppdm_school.screens.TravelloRegisterScreen
import br.senai.sp.jandira.projeto_travello.ui.theme.Projeto_TravelloTheme
import br.senai.sp.jandira.projeto_travello.ui.theme.screens.TravelloHomeScreenPreview
//import br.senai.sp.jandira.projeto_travello.ui.theme.screens.TravelloHomeScreen
import br.senai.sp.jandira.projeto_travello.ui.theme.screens.TravelloLoguinScreen
import br.senai.sp.jandira.projeto_travello.ui.theme.screens.TravelloRegisterTrip

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Projeto_TravelloTheme {
                val navegacao = rememberNavController()
                NavHost(
                    navController = navegacao,
                    startDestination = "home"
                ) {
                    composable(route = "register") {
                        TravelloRegisterScreen(navegacao)
                    }
                    composable(route = "login") {
                        TravelloLoguinScreen(navegacao)
                    }
                    composable(route = "cadastro_viagem") {
                        TravelloRegisterTrip(navegacao)
                    }
                    composable(route = "home") {
                        TravelloHomeScreenPreview()
                    }
                }
            }
        }
    }
}
