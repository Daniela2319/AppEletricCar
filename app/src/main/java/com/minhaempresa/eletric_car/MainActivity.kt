package com.minhaempresa.eletric_car



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    lateinit var preco: EditText
    lateinit var percorrido: EditText
    lateinit var btn_Calcular: Button
    lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
    }

    fun setupView(){
        preco = findViewById(R.id.et_preco_kwh)
        percorrido = findViewById(R.id.et_percorrido_km)
        btn_Calcular = findViewById(R.id.btn_calcular)
        resultado = findViewById(R.id.tv_resultado)
    }

    fun setupListeners(){
        btn_Calcular.setOnClickListener{
           calcular()
        }
    }

    fun calcular(){
        val preco = preco.text.toString().toFloat()
        val km = percorrido.text.toString().toFloat()
        val result = preco / km

        resultado.text = result.toString()
    }
}







/* Log.d("texto preço ->" , preco.toString()) esse codigo serve para log e fazer testes
   Log.d("texto digitado ->" , km.toString())
   Log.d("Resultado ->" , resultado.toString())*/