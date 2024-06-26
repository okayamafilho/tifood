package br.com.okayamafilho.tifood

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.okayamafilho.tifood.databinding.ActivityCadastroBinding
import br.com.okayamafilho.tifood.databinding.ActivityMainBinding
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class CadastroActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }

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
    }

    private fun inicializarEventosClique() {
//        with(binding) {
//            btnCadastrar.setOnClickListener {
//                val nome = editCadastroNome.text.toString()
//                val email = editCadastroEmail.text.toString()
//                val senha = editCadastroSenha.text.toString()
//                val telefone = editCadastroTelefone.text.toString()
//
//                val valNome = nome.validator()
//                    .nonEmpty()
//                    .minLength(6)
//                    .check()
//
//                val valEmail = email.validator()
//                    .validEmail()
//                    .check()
//            }
//        }
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