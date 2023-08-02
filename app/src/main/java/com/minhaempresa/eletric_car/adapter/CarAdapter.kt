package com.minhaempresa.eletric_car.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.minhaempresa.eletric_car.R
import com.minhaempresa.eletric_car.domain.Carro

class CarAdapter(private val carros: List<Carro>) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

   //Cria uma nova view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_carro_item, parent, false)
        return ViewHolder(view)
    }
    //Pega o contéudo da view e troca pela informação de item de uma lista
    override fun onBindViewHolder(holder: CarAdapter.ViewHolder, position: Int) {
        holder.preco.text = carros[position].preco
        holder.bateria.text = carros[position].bateria
        holder.potencia.text = carros[position].potencia
        holder.recarga.text = carros[position].recarga

    }

    //Pega a quantidade de carros da lista
    override fun getItemCount(): Int = carros.size



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val preco: TextView
        val bateria: TextView
        val potencia: TextView
        val recarga: TextView

        init {
            view.apply {
                preco = findViewById(R.id.tv_preco_valor)
                bateria = findViewById(R.id.tv_bateria_valor)
                potencia = findViewById(R.id.tv_potencia_value)
                recarga = findViewById(R.id.tv_recarga_valor)

            }

        }
    }


}