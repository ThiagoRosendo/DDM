package com.gcdominium

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso


class ClienteAdapter (
    val clientes: List<Cliente>,
    val onClick: (Cliente) -> Unit): RecyclerView.Adapter<ClienteAdapter.ClientesViewHolder>() {


    class ClientesViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card_clientes)

        }

    }


    override fun getItemCount() = this.clientes.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientesViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cliente, parent, false)


        val holder = ClientesViewHolder(view)
        return holder
    }



    override fun onBindViewHolder(holder: ClientesViewHolder, position: Int) {
        val context = holder.itemView.context

        val cliente = clientes[position]

        holder.cardNome.text = cliente.nome
        holder.cardProgress.visibility = View.VISIBLE

        Picasso.with(context).load(cliente.foto).fit().into(holder.cardImg,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        holder.cardProgress.visibility = View.GONE
                    }

                    override fun onError() {

                    }
                })


        holder.itemView.setOnClickListener {onClick(cliente)}
    }
}