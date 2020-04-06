package com.gcdominium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.btEntrar
import kotlinx.android.synthetic.main.login.tUsuario
import kotlinx.android.synthetic.main.login.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login)

        btEntrar.setOnClickListener{
            val usuario = tUsuario.text.toString()
            val senha = tSenha.text.toString()

            if (usuario == "aluno" && senha == "impacta"){
            Toast.makeText(this, "Seja bem-vindo, $usuario", Toast.LENGTH_SHORT).show()

            var intent = Intent(this, TelaInicialActivity::class.java)
            startActivity(intent)
        } else {
                Toast.makeText(this, "Usuário ou senha inválidos!", Toast.LENGTH_SHORT).show()

            }


        }
    }}

