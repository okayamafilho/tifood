package br.com.okayamafilho.core

import android.app.Activity
import android.widget.Toast

fun Activity.exibirMensagem(texto: String) {
    Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
}