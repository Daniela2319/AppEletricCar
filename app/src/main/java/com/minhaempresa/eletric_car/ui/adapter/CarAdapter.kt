package com.minhaempresa.eletric_car.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.minhaempresa.eletric_car.R
import com.minhaempresa.eletric_car.domain.Carro
//caradapter para fazer uma lista personalizada
class CarAdapter(private val carros: List<Carro>, private val isFavoriteScreen : Boolean = false) :
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    var carItemList : (Carro) -> Unit = {}

   //Cria uma nova view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_carro_item, parent, false)
        return ViewHolder(view)
    }
    //Pega o contéudo da view e troca pela informação de item de uma lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.preco.text = carros[position].preco
        holder.bateria.text = carros[position].bateria
        holder.potencia.text = carros[position].potencia
        holder.recarga.text = carros[position].recarga

        if (isFavoriteScreen){
            holder.favorito.setImageResource(R.drawable.ic_star_selected)
        }

        holder.favorito.setOnClickListener {
            val carro = carros[position]
            carItemList(carro)

            // troca o iconi quando estiver selecionado favorito
            setupFavorite(carro, holder)
        }

    }

    private fun setupFavorite(
        carro: Carro,
        holder: ViewHolder
    ) {
        carro.isFavorite = !carro.isFavorite

        if (carro.isFavorite)
            holder.favorito.setImageResource(R.drawable.ic_star_selected)
        else
            holder.favorito.setImageResource(R.drawable.ic_star1)
    }

    //Pega a quantidade de carros da lista
    override fun getItemCount(): Int = carros.size

    //viewholder é vai pega cada item e colocar na tela
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val preco: TextView
        val bateria: TextView
        val potencia: TextView
        val recarga: TextView
        val favorito: ImageView

        init {
            view.apply {
                preco = findViewById(R.id.tv_preco_valor)
                bateria = findViewById(R.id.tv_bateria_valor)
                potencia = findViewById(R.id.tv_potencia_value)
                recarga = findViewById(R.id.tv_recarga_valor)
                favorito = findViewById(R.id.iv_favorite)
            }

        }
    }


}