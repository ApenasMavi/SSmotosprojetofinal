package com.example.ssmotos.ui.telaCadastroCliente

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ssmotos.model.Clientes
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class telaCadastroClienteViewModel(): ViewModel (){
    private var _telaCadastroClienteUIState = MutableStateFlow(telaCadastroClienteUIState())
    val telaCadastroAlunoUIState: StateFlow<telaCadastroClienteUIState> = _telaCadastroClienteUIState.asStateFlow()

    var campoNome by mutableStateOf("")
        private set

    var campoMoto by mutableStateOf("")
        private set

    var fotoPerfilUri by mutableStateOf<Uri?>(null)
        private set

    fun updateCampoNome(campoNome:String){
        this.campoNome = campoNome
    }
    fun updateCampoCurso(campomoto:String){
        this.campoMoto = campomoto
    }
    fun updateFotoPerfilUri(fotoPerfilUri: Uri){
        this.fotoPerfilUri = fotoPerfilUri
    }
    fun limpaTelaCadastro(){
        this.campoNome = ""
        this.campoMoto = ""
        this.fotoPerfilUri = null
        _telaCadastroClienteUIState.update { currentState ->
            currentState.copy(
                cadastroEfetuado = false
            )
        }
    }

    fun salvarAluno(){
        val db = FirebaseFirestore.getInstance()
        val alunoRef = db.collection("Cliente").document()
        alunoRef.set(Clientes(campoNome,campoMoto,fotoPerfilUri.toString()))
        val filename = campoNome
        val ref = Firebase.reference.child("/fotoCliente/$filename")
        val uploadTask = fotoPerfilUri.let { ref.putFile(it!!) }
        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val url =task.result.toString()
                alunoRef.update("fotoPerfilUrl",url)

                _telaCadastroClienteUIState.value.cadastroEfetuado = true
                Log.d(TAG,"teste ${_telaCadastroClienteUIState.value.cadastroEfetuado}")

                // mensagemToast(context, "Sucesso!!")
            } else {
                //mensagemToast(context, "Falha")
            }
        }
    }

}