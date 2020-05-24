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
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2

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
        Thread {
        this.clientes = ClienteService.getClientes(context)
            runOnUiThread {
        recyclerClientes?.adapter = ClienteAdapter(clientes) {onClickCliente(it)}
                enviaNotificacao(this.clientes.get(4))
    }
        }.start()
    }

    fun enviaNotificacao(cliente: Cliente) {
        // Intent para abrir tela quando clicar na notificação
        val intent = Intent(this, ClienteActivity::class.java)
        // parâmetros extras
        intent.putExtra("cliente", cliente)
        // Disparar notificação
        NotificationUtil.create(this, 1, intent, "GC Dominium", "Novo cliente: ${cliente.nome}")
    }

    fun onClickCliente(cliente: Cliente) {
        Toast.makeText(context, "${cliente.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ClienteActivity::class.java)
        intent.putExtra("cliente", cliente)
        startActivity(intent)
    }


    private fun configuraMenuLateral() {
        var toogle = ActionBarDrawerToggle(this, SideMenu, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        SideMenu.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_clientes -> {
                Toast.makeText(this, "Clientes", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_list -> {
                startActivity(Intent(this, ClienteCadastroActivity::class.java))
            }

            R.id.nav_map -> {
                startActivity(Intent(this, MapasActivity::class.java))
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
            finish();
            startActivity(getIntent())
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        }
         else if (id == R.id.action_cadastrar) {
            val intent = Intent(context, ClienteCadastroActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)
        }

        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}


