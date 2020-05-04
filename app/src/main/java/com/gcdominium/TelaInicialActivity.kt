package com.gcdominium

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import kotlin.concurrent.schedule

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var clientes = listOf<Cliente>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)


        val args:Bundle? = intent.extras

        val nome = args?.getString("nome")

        setSupportActionBar(toolbar)

        supportActionBar?.title = "Tela inicial"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()

        recyclerClientes?.layoutManager = LinearLayoutManager(context)
        recyclerClientes?.itemAnimator = DefaultItemAnimator()
        recyclerClientes?.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()
        taskClientes()
    }

    fun taskClientes() {
        this.clientes = ClienteService.getClientes(context)
        recyclerClientes?.adapter = ClienteAdapter(clientes) {onClickCliente(it)}
    }

    fun onClickCliente(cliente: Cliente) {
        Toast.makeText(context, "${cliente.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ClienteActivity::class.java)
        intent.putExtra("cliente", cliente)
        startActivity(intent)
    }


    private fun configuraMenuLateral() {

        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(this, SideMenu, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        SideMenu.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_clientes -> {
                Toast.makeText(this, "Cadastrar Cliente", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_list -> {
                Toast.makeText(this, "Lista de clientes", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_mensagens -> {
                Toast.makeText(this, "Serviços", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_sair -> {

                cliqueSair()

            }
        }

        SideMenu.closeDrawer(GravityCompat.START)
        return true
    }

    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saída do BrewerApp");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if  (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            if (progressbar.visibility == View.INVISIBLE){
                progressbar.visibility = View.VISIBLE
                Timer().schedule(10000) {
                    progressbar.visibility = View.INVISIBLE
                }
            }
            else {progressbar.visibility = View.INVISIBLE}
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        }

        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}


