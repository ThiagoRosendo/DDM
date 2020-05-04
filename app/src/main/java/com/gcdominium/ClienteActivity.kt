package com.gcdominium

import android.os.Bundle
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
}
