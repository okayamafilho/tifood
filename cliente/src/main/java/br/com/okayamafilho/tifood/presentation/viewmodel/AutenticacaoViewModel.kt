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

    fun cadastrarUsuario(usuario: Usuario) {
        val  retornoValidacao = autenticacaoUseCase.validarCadastroUsuario(usuario)
        _resultadoValidacao.value = retornoValidacao
        //Verificar os dados do usuario
        //Cadastro do usuario
        if (retornoValidacao.sucessoValidacaoCadastro) {
            viewModelScope.launch {
                val retorno = autenticacaoRepositoryImpl.cadastrarUsuario(usuario)
                _sucesso.postValue(retorno)
            }
        }
    }

    fun logarUsuario(usuario: Usuario) {
        val  retornoValidacao = autenticacaoUseCase.validarLoginUsuario(usuario)
        _resultadoValidacao.value = retornoValidacao
        //Verificar os dados do usuario
        //Cadastro do usuario
    }
}