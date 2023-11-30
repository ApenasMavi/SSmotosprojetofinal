package com.example.ssmotos.ui.TelaSSMotos

import androidx.lifecycle.ViewModel
import com.example.ssmotos.model.Clientes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.Flow

class TelaSSmotosViewModel(): ViewModel() {
    private val _telaListaDeClientesUiState = MutableStateFlow(telaListaDeClientesUiState())
    val telaListaDeClientesUiState: StateFlow<telaListaDeClientesUiState> = _telaListaDeClientesUiState.asStateFlow()

    private val firebaseCloudFirestore = FirebaseCloudFirestore()

    fun carregarListaDeClientes(): Flow<MutableList<Clientes>> {
        return firebaseCloudFirestore.carregarListaDeAlunos()
    }
}
