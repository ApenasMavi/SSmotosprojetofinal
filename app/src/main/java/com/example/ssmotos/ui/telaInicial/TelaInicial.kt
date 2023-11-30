package com.example.ssmotos.ui.telaInicial

import android.text.Layout.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ssmotos.R
import com.example.ssmotos.model.Tela
import com.example.ssmotos.ui.ViewModelCompartilhado
import com.example.ssmotos.ui.componentes.TopBar
import com.google.errorprone.annotations.Modifier

@Composable
fun TelaInicial(
    controleDeNavegacao: NavController,
    viewModelCompartilhado: ViewModelCompartilhado = viewModel()
) {

    val usuarioAutenticado = viewModelCompartilhado.usuarioAutenticado.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                title = R.string.app_name,
                onClick = {
                    viewModelCompartilhado.deslogarUsuario()
                    controleDeNavegacao.popBackStack()
                },
                mostraNavegationIcon = true
            )
        },

        ) { espacosDasBarras ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(espacosDasBarras),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(usuarioAutenticado.value?.fotoPerfilUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.cbtrezentasamarela),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
            )

            usuarioAutenticado.value?.nome?.let {
                Text(
                    text = it,
                    fontSize = 30.dp,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = {
                    controleDeNavegacao.navigate(Tela.SSMotos.descricao)
                }
            ) {
                Text(
                    text = "Lista de Clientes"
                )
            }
        }
    }
}