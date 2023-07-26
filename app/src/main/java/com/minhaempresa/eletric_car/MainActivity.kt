package com.minhaempresa.eletric_car



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.minhaempresa.eletric_car.adapter.CalcularAutonomiaActivity


class MainActivity : AppCompatActivity() {

    lateinit var btnCalcular: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
    }

    fun setupView(){
        btnCalcular = findViewById(R.id.btn_calcular)
    }

    fun setupListeners(){
        btnCalcular.setOnClickListener{
           startActivity(Intent(this,CalcularAutonomiaActivity::class.java))
        }
    }


}







/* Log.d("texto preço ->" , preco.toString()) esse codigo serve para log e fazer testes
   Log.d("texto digitado ->" , km.toString())
   Log.d("Resultado ->" , resultado.toString())*/