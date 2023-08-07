package com.minhaempresa.eletric_car.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Base64InputStream
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.loader.content.AsyncTaskLoader
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.minhaempresa.eletric_car.R
import com.minhaempresa.eletric_car.data.CarFactory
import com.minhaempresa.eletric_car.ui.adapter.CarAdapter
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

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
            MyTask().execute("https://igorbag.github.io/cars-api/cars.json")
           //startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }
    }

    //esse código esta lendo dados
inner class MyTask : AsyncTask<String, String, String>(){
  fun onProExecute(){
   super.onPreExecute()
    Log.d("MyTask","Inciando...")
}

    override fun doInBackground(vararg url: String?): String {
        var urlConnection: HttpURLConnection? = null
        try {
            val urlBase = URL(url[0])

            urlConnection = urlBase.openConnection() as HttpURLConnection
            urlConnection.connectTimeout = 60000
            urlConnection.readTimeout = 60000

            var response = urlConnection.inputStream.bufferedReader().use { it.readText() }
            publishProgress(response)
        }catch (ex: Exception){
            Log.e("Erro","Erro ao realizar processamento....")
        }finally {
                urlConnection?.disconnect()
            }
        return ""
        }
    }
 //Dados ler atributos da api
 fun onProgressUpdate(vararg values: String){
     try {
         val jsonArray = JSONTokener(values[0]).nextValue()as JSONArray
         for (i in 0 until jsonArray.length()){

         }

     }catch (ex: java.lang.Exception){

     }


}

}