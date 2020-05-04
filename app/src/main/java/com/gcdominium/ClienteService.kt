package com.gcdominium

import android.content.Context

object ClienteService {

    fun getClientes (context: Context): List<Cliente> {
        val clientes = mutableListOf<Cliente>()

        for (i in 1..10) {
            val cliente = Cliente()
            cliente.nome = "Cliente $i"
            cliente.foto = "https://blog.luz.vc/wp-content/uploads/2017/12/perfil-do-cliente-ideal-o-cliente-rei.png"
            clientes.add(cliente)
        }

        return clientes
    }

}