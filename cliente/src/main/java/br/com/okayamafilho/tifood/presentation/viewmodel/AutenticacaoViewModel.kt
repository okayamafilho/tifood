package br.com.okayamafilho.tifood.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.okayamafilho.tifood.data.remote.firebase.repository.AutenticacaoRepositoryImpl
import br.com.okayamafilho.tifood.domain.model.Usuario
import br.com.okayamafilho.tifood.domain.usecase.AutenticacaoUseCase
import br.com.okayamafilho.tifood.domain.usecase.ResultadoValidacao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutenticacaoViewModel @Inject constructor(
    private val autenticacaoUseCase: AutenticacaoUseCase,
    private val autenticacaoRepositoryImpl: AutenticacaoRepositoryImpl

) : ViewModel() {

    private val _resultadoValidacao = MutableLiveData<ResultadoValidacao>()
    val resultadoValidacao: LiveData<ResultadoValidacao>
        get() = _resultadoValidacao

    private val _sucesso = MutableLiveData<Boolean>()
    val sucesso: LiveData<Boolean>
        get() = _sucesso

    private val _usuarioEstaLogado = MutableLiveData<Boolean>()
    val usuarioEstaLogado: LiveData<Boolean>
        get() = _usuarioEstaLogado

    private val _carregando = MutableLiveData<Boolean>()
    val carregando: LiveData<Boolean>
        get() = _carregando

    fun cadastrarUsuario(usuario: Usuario) {
        val retornoValidacao = autenticacaoUseCase.validarCadastroUsuario(usuario)
        _resultadoValidacao.value = retornoValidacao
        //Verificar os dados do usuario
        //Cadastro do usuario
        if (retornoValidacao.sucessoValidacaoCadastro) {
            _carregando.value = true
            viewModelScope.launch {
                val retorno = autenticacaoRepositoryImpl.cadastrarUsuario(usuario)
                _sucesso.postValue(retorno)
                _carregando.postValue(false)
            }
        }
    }

    fun logarUsuario(usuario: Usuario) {
        val retornoValidacao = autenticacaoUseCase.validarLoginUsuario(usuario)
        _resultadoValidacao.value = retornoValidacao
        //Verificar os dados do usuario
        //Cadastro do usuario
        if (retornoValidacao.sucessoValidacaoLogin) {
            _carregando.value = true
            viewModelScope.launch {
                val retorno = autenticacaoRepositoryImpl.logarUsuario(usuario)
                _sucesso.postValue(retorno)
                _carregando.postValue(false)
            }
        }
    }

    fun verificarUsuarioLogado() {
        viewModelScope.launch {
            val retorno = autenticacaoRepositoryImpl.verificarUsuarioLogado()
            _usuarioEstaLogado.postValue(retorno)
        }
    }
}