package com.minhaempresa.eletric_car.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.minhaempresa.eletric_car.R
import com.minhaempresa.eletric_car.data.CarFactory
import com.minhaempresa.eletric_car.ui.adapter.CarAdapter

//Mostra uma lista no outro lado o detalhe da lista
class CarFragment : Fragment(){

    lateinit var fab_Calcular: FloatingActionButton
    lateinit var listaCarros: RecyclerView

    //aqui o android esta desenhando a tela para o usuário
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    //o android terminou de desenhar a tela para o usuário
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
        setupListeners()
    }

    fun setupView(view: View){

        view.apply {
            fab_Calcular = findViewById(R.id.fab_calcular)
            listaCarros = findViewById(R.id.rv_lista_carro)
        }
    }

    fun setupList() {
        val adapter = CarAdapter(CarFactory.list)
        listaCarros.adapter = adapter
    }

    fun setupListeners() {
        fab_Calcular.setOnClickListener {
           startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }
    }
}