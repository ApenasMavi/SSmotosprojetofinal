package com.example.ssmotos.ui.TelaLogin

import com.example.ssmotos.R
import com.example.ssmotos.model.Usuario

data class TelaLoginECadastroUIState(
    val cadastrarAtivo: Boolean = false,
    val idtextoBotaoEntrarCadastrar: Int = R.string.entrar,
    val idTextoCampoEmail: Int = R.string.email,
    val idTextoCampoSenha: Int = R.string.senha,
    val idTextoCampoConfirmarSenha: Int = R.string.confirmar_senha,
    val erroAoLogar: Boolean = false,
    val usuarioPreenchido: Usuario? = null,
    val habilitarBotao: Boolean = false,
)

