package br.com.okayamafilho.tifood

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.okayamafilho.tifood.databinding.ActivityCadastroBinding
import br.com.okayamafilho.tifood.databinding.ActivityMainBinding
import br.com.okayamafilho.tifood.domain.model.Usuario
import br.com.okayamafilho.tifood.presentation.viewmodel.AutenticacaoViewModel
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }

    private val autenticacaoViewModel: AutenticacaoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializar()
    }

    private fun inicializar() {
        inicializarToolbar()
        inicializarEventosClique()
        inicializarObservaveis()
    }

    fun navegarTelaPrincipal() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun inicializarObservaveis() {

        autenticacaoViewModel.sucesso.observe(this) { sucesso ->
            if (sucesso) {
                Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show()
                navegarTelaPrincipal()
            } else {
                Toast.makeText(this, "Erro ao realizar cadastro", Toast.LENGTH_LONG).show()
            }
        }

        autenticacaoViewModel.resultadoValidacao.observe(this) { resultadoValidacao ->
            with(binding) {
                editCadastroNome.error =
                    if (resultadoValidacao.nome) null else getString(R.string.erro_cadastro_nome)
                editCadastroEmail.error =
                    if (resultadoValidacao.email) null else getString(R.string.erro_cadastro_email)
                editCadastroSenha.error =
                    if (resultadoValidacao.senha) null else getString(R.string.erro_cadastro_senha)
                editCadastroTelefone.error =
                    if (resultadoValidacao.telefone) null else getString(R.string.erro_cadastro_telefone)
            }
        }
    }

    private fun inicializarEventosClique() {
        with(binding) {
            btnCadastrar.setOnClickListener {
                val nome = editCadastroNome.text.toString()
                val email = editCadastroEmail.text.toString()
                val senha = editCadastroSenha.text.toString()
                val telefone = editCadastroTelefone.text.toString()

                val usuario = Usuario(
                    email, senha, nome, telefone
                )
                autenticacaoViewModel.cadastrarUsuario(usuario)
            }
        }
    }

    private fun inicializarToolbar() {
        val toolbar = binding.includeTbPrincipal.tbPrincipal
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = "Cadastro de usuário"
            setDisplayHomeAsUpEnabled(true)
        }
    }
}