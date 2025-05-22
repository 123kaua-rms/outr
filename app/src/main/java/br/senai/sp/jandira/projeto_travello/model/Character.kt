package br.senai.sp.jandira.projeto_travello.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class Character(
    var name: String ="",
    var username: String ="",
    var email: String ="",
    var Country: String ="",
    var password : String =""
)
