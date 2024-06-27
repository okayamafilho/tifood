package br.com.okayamafilho.tifood.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.okayamafilho.tifood.domain.model.Usuario
import br.com.okayamafilho.tifood.domain.usecase.AutenticacaoUseCase
import br.com.okayamafilho.tifood.domain.usecase.ResultadoValidacao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AutenticacaoViewModel @Inject constructor(

    private val autenticacaoUseCase: AutenticacaoUseCase

) : ViewModel() {

    private val _resultadoValidacao = MutableLiveData<ResultadoValidacao>()
    val resultadoValidacao: LiveData<ResultadoValidacao>
        get() = _resultadoValidacao

    fun cadastrarUsuario(usuario: Usuario) {
        val  retornoValidacao = autenticacaoUseCase.validarDadosUsuario(usuario)
        _resultadoValidacao.value = retornoValidacao
        //Verificar os dados do usuario
        //Cadastro do usuario

    }
}