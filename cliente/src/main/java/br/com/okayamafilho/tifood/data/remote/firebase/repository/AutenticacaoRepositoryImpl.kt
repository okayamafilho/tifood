package br.com.okayamafilho.tifood.data.remote.firebase.repository

import br.com.okayamafilho.tifood.domain.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AutenticacaoRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : IAutenticacaoRepository {
    override suspend fun cadastrarUsuario(usuario: Usuario): Boolean {
        return firebaseAuth.createUserWithEmailAndPassword(
            usuario.email, usuario.senha
        ).await() != null
    }

    override suspend fun logarUsuario(usuario: Usuario): Boolean {
        return firebaseAuth.signInWithEmailAndPassword(
            usuario.email, usuario.senha
        ).await() != null
    }

}