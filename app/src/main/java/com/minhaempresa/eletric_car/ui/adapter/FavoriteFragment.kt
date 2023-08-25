package com.minhaempresa.eletric_car.ui.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.minhaempresa.eletric_car.R
import com.minhaempresa.eletric_car.data.local.CarRepository
import com.minhaempresa.eletric_car.domain.Carro

class FavoriteFragment : Fragment() {
lateinit var listaCarrosFavoritos: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()

    }
    private fun getCarsOnLocalDb(): List<Carro>{
        val repository = CarRepository(requireContext())
        val carList = repository.getAll() // tela for criada que leia meus carros da lista
        Log.d("Lista de carros", carList.size.toString())
        return carList
    }
    fun setupView(view: View){
        view.apply {
            listaCarrosFavoritos = findViewById(R.id.rv_lista_carros_favoritos)
        }
    }
    fun setupList(){
        val cars = getCarsOnLocalDb()
        val carroAdapter = CarAdapter(cars,isFavoriteScreen = true )
        listaCarrosFavoritos.apply { 
            isVisible = true
            adapter = carroAdapter
        }
        carroAdapter.carItemList = {carro ->
            val isDelete = CarRepository(requireContext()).deleteCarById(id)
        }
    }

}