package com.gcdominium

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_cliente.*
import kotlinx.android.synthetic.main.toolbar.*

class ClienteActivity : DebugActivity() {

    var cliente: Cliente? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)


        cliente = intent.getSerializableExtra("cliente") as Cliente

        setSupportActionBar(toolbar)

        supportActionBar?.title = cliente?.nome

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nome_cliente.text = cliente?.nome
        Picasso.with(this).load(cliente?.foto).fit().into(img_cliente,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cliente, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if  (id == R.id.action_excluir) {
            AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Deseja excluir este cliente?")
                .setPositiveButton("Sim") {
                        dialog, which ->
                    dialog.dismiss()
                    taskExcluir()
                }.setNegativeButton("Não") {
                        dialog, which -> dialog.dismiss()
                }.create().show()
        }
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskExcluir() {
        if (this.cliente != null && this.cliente is Cliente) {
            Thread {
                ClienteService.delete(this.cliente as Cliente)
                runOnUiThread {
                    finish()
                }
            }.start()
        }
    }
}
