package br.com.okayamafilho.tifood.data.remote.firebase.repository

import br.com.okayamafilho.tifood.domain.model.Usuario
import com.google.protobuf.Internal.BooleanList

interface IAutenticacaoRepository {
    suspend fun  cadastrarUsuario(usuario: Usuario) : Boolean
}