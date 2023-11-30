package com.example.ssmotos.ui.telaCadastroCliente

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.ssmotos.R
import com.example.ssmotos.ui.TelaLogin.CampoDeTexto
import com.example.ssmotos.ui.ViewModelCompartilhado
import com.example.ssmotos.ui.componentes.Botao
import com.example.ssmotos.ui.componentes.TopBar

@Composable
fun TelaCadastroCliente(
    telaCadastroClienteViewModel: telaCadastroClienteViewModel = viewModel(),
    viewModelCompartilhado: ViewModelCompartilhado = viewModel(),
    controleDeNavegacao: NavController
){
    val telaCadastroAlunoUIState by telaCadastroClienteViewModel.telaCadastroClienteUISate.collectAsState()

    if(telaCadastroClienteUIState.cadastroEfetuado){
        telaCadastroClienteViewModel.limpaTelaCadastro()
        controleDeNavegacao.popBackStack()
    }
    Log.d(TAG,"teste2${telaCadastroClienteUIState.cadastroEfetuado}")

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                telaCadastroClienteViewModel.updateFotoPerfilUri(uri)
            }
        }

    val painter = rememberAsyncImagePainter(
        ImageRequest
            .Builder(LocalContext.current)
            .data(data = telaCadastroClienteViewModel.fotoPerfilUri)
            .build()
    )

    Scaffold(
        topBar = {
            TopBar(
                title = R.string.Cadastrar_Cliente,
                onClick = {
                    controleDeNavegacao.popBackStack()
                },
                mostraNavegationIcon = true
            )
        },

        ) { espacosDasBarras ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(espacosDasBarras),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            if (telaCadastroClienteViewModel.fotoPerfilUri == null) {
                ImagemLogin(
                    painter = painterResource(id = R.drawable.cbtrezeentost),
                    launcher = launcher
                )
            } else {
                ImagemLogin(
                    painter = painter,
                    launcher = launcher
                )
            }

            CampoDeTexto(
                value = telaCadastroClienteViewModel.campoNome,
                onValueChange ={telaCadastroClienteViewModel.updateCampoNome(it)},
                idTextoLabel = R.string.nome
            )
            CampoDeTexto(
                value =telaCadastroClienteViewModel.camporeparos,
                onValueChange ={telaCadastroClienteViewModel.updateCampototal(it)},
                idTextoLabel = R.string.Moto
            )

            Botao(
                idTextoBotao = R.string.cadastrar,
                onClick = {
                    telaCadastroClienteViewModel.salvarCliente()
                    controleDeNavegacao.popBackStack()
                }
            )
        }
    }

}

@Composable
fun ImagemLogin(
    painter: Painter,
    launcher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>
) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .clickable {
                launcher.launch(
                    PickVisualMediaRequest(
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            },
        contentScale = ContentScale.Crop,
    )
}