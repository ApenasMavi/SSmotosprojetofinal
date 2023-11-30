package com.example.ssmotos.ui.TelaLogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ssmotos.model.Clientes

@Composable
fun TelaSSMotos(
    espacoDasBarras: PaddingValues,
    controleNavegacao: NavController
){

    Column(
        modifier = Modifier
            .padding(espacoDasBarras)
    ) {

    }
}
@Composable
fun ListaServicos(listaServicos:List<Clientes>){
    LazyColumn{
        items(listaServicos) {
            Clientes ->
            CardCliente(Clientes)
        }
    }
}
@Composable
fun CardCliente (
    Clientes: Clientes
){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        Column {
            Row {
                Image(
                    painter = painterResource(id =Clientes.imagem ),
                    contentDescription = null,
                    modifier= Modifier.size(50.dp)
                )
                Column {
                    Text(
                        text = Clientes.nome
                    )
                    Text(
                        text = Clientes.moto
                    )
                }
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = null
                )
            }
            Column {
                Text(
                    text = "${Clientes.valorReparos}"
                )
                Text(
                    text = "${Clientes.total}"
                )
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun PreviewListaServicos(){
    ListaServicos(
        listaServicos = DBClientes().carregaListaServicos()
    )
}