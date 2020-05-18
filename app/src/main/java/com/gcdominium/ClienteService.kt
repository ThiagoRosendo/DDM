package com.gcdominium

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ClienteService {

    val host = "http://thiagorosendo.pythonanywhere.com"
    val TAG = "TESTE"

    fun getClientes (context: Context): List<Cliente> {

        val dao = DatabaseManager.getclienteDAO()
        var clientesOff = dao.findAll() as ArrayList<Cliente>
        var ids = arrayListOf<Long>()
        var idsOff = arrayListOf<Long>()

        if (AndroidUtils.isInternetDisponivel()) {
            var clientes = ArrayList<Cliente>()
            val url = "$host/clientes"
            var json = HttpHelper.get(url)
            clientes = parserJson(json)

            for (cliente in clientes) {
                saveOffline(cliente)
            }

            for (item in clientes){
                ids.add(item.id)
            }

            for (item in clientesOff){
                idsOff.add(item.id)
            }

            for (cliente in clientesOff){
                if (cliente.id !in ids){
                    save(cliente)
                    json = HttpHelper.get(url)
                    clientes = parserJson(json)
                }
            }

            return clientes

        } else {
            return dao.findAll()
        }
    }

    fun getCliente (context: Context, id: Long): Cliente? {

        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/clientes/${id}"
            val json = HttpHelper.get(url)
            val cliente= parserJson<Cliente>(json)

            return cliente
        } else {
            val dao = DatabaseManager.getclienteDAO()
            val cliente = dao.getById(id)
            return cliente
        }

    }

    fun saveOffline(cliente: Cliente) : Boolean {
        val dao = DatabaseManager.getclienteDAO()

        if (! clienteCadastrado(cliente)) {
            dao.insert(cliente)
        }

        return true

    }

    fun clienteCadastrado(cliente: Cliente): Boolean {
        val dao = DatabaseManager.getclienteDAO()
        return dao.getById(cliente.id) != null
    }



    fun save(cliente: Cliente): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val json = HttpHelper.post("$host/clientes", cliente.toJson())
            return parserJson(json)

        }
        else {
            val dao = DatabaseManager.getclienteDAO()
            var ids = arrayListOf<Long>()
            for (item in dao.findAll()){
                ids.add(item.id)
            }
            var nid = ids.max()
            if (nid != null) {
                nid += 1
                cliente.id = nid
            }
            dao.insert(cliente)

            return Response("OK", "Cliente salvo offline")
        }
    }

    fun delete(cliente: Cliente): Response{
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/clientes/${cliente.id}"
            val json = HttpHelper.delete(url)
            DatabaseManager.getclienteDAO().delete(cliente)
            return parserJson(json)
        } else {
            val dao = DatabaseManager.getclienteDAO()
            dao.delete(cliente)
            return Response(status = "OK", msg = "Cliente excluido localmente")
        }

    }


    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}