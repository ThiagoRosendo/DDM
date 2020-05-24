package com.gcdominium

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastrar_cliente.*

class ClienteCadastroActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_cliente)
        setTitle("Cadastrar Cliente")


        btn_cad_cliente.setOnClickListener {
            val cliente = Cliente()
            cliente.nome = nomeCliente.text.toString()
            cliente.cpf = cpfCliente.text.toString()
            cliente.dataN = dataN.text.toString()
            cliente.foto = urlFoto.text.toString()

            taskAtualizar(cliente)
        }
    }

    private fun taskAtualizar(cliente: Cliente) {
        Thread {
            ClienteService.save(cliente)
            runOnUiThread {
                finish()
            }
        }.start()
    }
}
