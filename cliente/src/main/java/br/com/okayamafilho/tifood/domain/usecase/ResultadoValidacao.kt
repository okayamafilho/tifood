package br.com.okayamafilho.tifood.domain.usecase

data class ResultadoValidacao(
    var nome: Boolean = false,
    var email: Boolean = false,
    var senha: Boolean = false,
    var telefone: Boolean = false

) {
}