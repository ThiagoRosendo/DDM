package com.gcdominium

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MapasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapas)
        setTitle("Localização")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onResume() {
        super.onResume()
        val mapaFragment = MapaFragment()
        supportFragmentManager.beginTransaction().replace(R.id.layoutMapas, mapaFragment).commit()
    }
}
