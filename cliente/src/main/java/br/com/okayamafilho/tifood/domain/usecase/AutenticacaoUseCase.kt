package br.com.okayamafilho.tifood.domain.usecase

import android.util.Log
import br.com.okayamafilho.tifood.domain.model.Usuario
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class AutenticacaoUseCase {

    fun validarDadosUsuario(usuario: Usuario): ResultadoValidacao {
        val resultadoValidacao = ResultadoValidacao()

        val valNome = usuario.nome.validator()
            .nonEmpty()
            .minLength(6)
            .check()

        val valEmail = usuario.email.validator()
            .validEmail()
            .check()

        val valSenha = usuario.senha.validator()
            .minLength(6)
            .check()

        val valTelefone = usuario.telefone.validator()
            .minLength(14)
            .check()

        if (valNome)
            resultadoValidacao.nome = true

        if (valEmail)
            resultadoValidacao.email = true

        if (valSenha)
            resultadoValidacao.senha = true

        if (valTelefone)
            resultadoValidacao.telefone = true

        return resultadoValidacao

    }


}