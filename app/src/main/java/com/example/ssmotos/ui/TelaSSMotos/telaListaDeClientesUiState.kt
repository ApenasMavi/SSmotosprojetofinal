package com.example.ssmotos.ui.TelaSSMotos

import com.example.ssmotos.model.Clientes

data class telaListaDeClientesUiState(
    val listaDeClientes:MutableList<Clientes> = mutableListOf()
)
