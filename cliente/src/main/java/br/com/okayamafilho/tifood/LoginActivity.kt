package br.com.okayamafilho.tifood

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.okayamafilho.core.exibirMensagem
import br.com.okayamafilho.tifood.databinding.ActivityLoginBinding
import br.com.okayamafilho.tifood.databinding.ActivityMainBinding
import br.com.okayamafilho.tifood.domain.model.Usuario
import br.com.okayamafilho.tifood.presentation.viewmodel.AutenticacaoViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val autenticacaoViewModel: AutenticacaoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializar()
//        FirebaseAuth.getInstance().signOut()
    }

    override fun onStart() {
        super.onStart()
        autenticacaoViewModel.verificarUsuarioLogado()

    }

    private fun inicializar() {
        inicializarEventosClique()
        inicializarObservaveis()
    }

    private fun inicializarEventosClique() {
        with(binding) {
            textCadastro.setOnClickListener {
                startActivity(Intent(applicationContext, CadastroActivity::class.java))
            }

            btnLogar.setOnClickListener {
                val email = editLoginEmail.text.toString()
                val senha = editLoginSenha.text.toString()
                val usuario = Usuario(email, senha)

                autenticacaoViewModel.logarUsuario(usuario)
            }
        }
    }

    fun navegarTelaPrincipal() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun inicializarObservaveis() {

        autenticacaoViewModel.usuarioEstaLogado.observe(this) { usuarioEstaLogado ->
            if (usuarioEstaLogado) {
                navegarTelaPrincipal()
            }
        }

        autenticacaoViewModel.sucesso.observe(this) { sucesso ->
            if (sucesso) {
                exibirMensagem("Login realizado com sucesso")
                navegarTelaPrincipal()
            } else {
                exibirMensagem("Erro ao logar usuário")
            }
        }

        autenticacaoViewModel.resultadoValidacao.observe(this) { resultadoValidacao ->
            with(binding) {
                editLoginEmail.error =
                    if (resultadoValidacao.email) null else getString(R.string.erro_cadastro_email)
                editLoginSenha.error =
                    if (resultadoValidacao.senha) null else getString(R.string.erro_cadastro_senha)
            }
        }
    }
}