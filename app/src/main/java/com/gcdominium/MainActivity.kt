package com.gcdominium

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.login.btEntrar
import kotlinx.android.synthetic.main.login.tUsuario
import kotlinx.android.synthetic.main.login.*

class MainActivity : AppCompatActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login)

        btEntrar.setOnClickListener { onClickLogin() }


        var lembrar = Prefs.getBoolean("lembrar")
        if (lembrar) {
            var lembrarNome = Prefs.getString("lembrarNome")
            var lembrarSenha = Prefs.getString("lembrarSenha")
            tUsuario.setText(lembrarNome)
            tSenha.setText(lembrarSenha)
            checkBoxLogin.isChecked = lembrar

        }
    }

    fun onClickLogin(){

        val usuario = tUsuario.text.toString()
        val senha = tSenha.text.toString()

        // armazenar valor do checkbox
        Prefs.setBoolean("lembrar", checkBoxLogin.isChecked)
        // verificar se é para pembrar nome e senha
        if (checkBoxLogin.isChecked) {
            Prefs.setString("lembrarNome", usuario)
            Prefs.setString("lembrarSenha", senha)
        } else{
            Prefs.setString("lembrarNome", "")
            Prefs.setString("lembrarSenha", "")
        }

        if (usuario == "aluno" && senha == "impacta"){
            Toast.makeText(this, "Seja bem-vindo, $usuario", Toast.LENGTH_SHORT).show()

            var intent = Intent(this, TelaInicialActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Usuário ou senha inválidos!", Toast.LENGTH_SHORT).show()

        }

    }
}