package com.minhaempresa.eletric_car.ui



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.minhaempresa.eletric_car.R
import com.minhaempresa.eletric_car.ui.adapter.CarAdapter
import com.minhaempresa.eletric_car.data.CarFactory


class MainActivity : AppCompatActivity() {

    lateinit var btnCalcular: Button
    lateinit var listaCarros: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
        setupList()
    }

    fun setupView(){
        btnCalcular = findViewById(R.id.btn_calcular)
        listaCarros = findViewById(R.id.rv_lista_carro)
    }

    fun setupList(){
        val adapter = CarAdapter(CarFactory.list)
        listaCarros.adapter = adapter
    }
    fun setupListeners(){
        btnCalcular.setOnClickListener{
           startActivity(Intent(this, CalcularAutonomiaActivity::class.java))
        }
    }


}







/* Log.d("texto preço ->" , preco.toString()) esse codigo serve para log e fazer testes
   Log.d("texto digitado ->" , km.toString())
   Log.d("Resultado ->" , resultado.toString())*/