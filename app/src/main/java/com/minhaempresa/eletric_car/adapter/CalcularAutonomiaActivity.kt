package com.minhaempresa.eletric_car.adapter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.minhaempresa.eletric_car.R

class CalcularAutonomiaActivity : AppCompatActivity(){

    lateinit var preco: EditText
    lateinit var kmPercorrido: EditText
    lateinit var resultado: TextView
    lateinit var btnCalcular: Button
    lateinit var btnclose: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_autonomia)
        setupView()
        setupListeners()


    }

    fun setupView(){

        preco = findViewById(R.id.et_preco_kwh)
        kmPercorrido = findViewById(R.id.et_percorrido_km)
        btnCalcular = findViewById(R.id.btn_calcula)
        resultado = findViewById(R.id.tv_resultado)
        btnclose = findViewById(R.id.iv_close)
    }

    fun setupListeners() {
        btnCalcular.setOnClickListener {
            calcular()

        }
        btnclose.setOnClickListener{
            finish()
        }

    }
    fun calcular() {
        val preco = preco.text.toString().toFloat()
        val km = kmPercorrido.text.toString().toFloat()
        val result = preco / km

        resultado.text = result.toString()
    }

}