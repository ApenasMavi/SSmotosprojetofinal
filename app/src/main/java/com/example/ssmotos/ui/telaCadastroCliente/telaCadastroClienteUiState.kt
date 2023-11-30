package com.example.ssmotos.ui.telaCadastroCliente

import com.example.ssmotos.model.Clientes

data class telaCadastroClienteUIState(
    var campoNome:String = "",
    var campoMoto:String = "",
    val clientes: Clientes = Clientes(),
    var cadastroEfetuado: Boolean = false
)
