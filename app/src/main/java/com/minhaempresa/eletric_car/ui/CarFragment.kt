package com.minhaempresa.eletric_car.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Base64InputStream
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.loader.content.AsyncTaskLoader
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.minhaempresa.eletric_car.R
import com.minhaempresa.eletric_car.data.CarFactory
import com.minhaempresa.eletric_car.data.CarsApi
import com.minhaempresa.eletric_car.domain.Carro
import com.minhaempresa.eletric_car.ui.adapter.CarAdapter
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

//Mostra uma lista no outro lado o detalhe da lista
class CarFragment : Fragment() {

    lateinit var fab_Calcular: FloatingActionButton
    lateinit var listaCarros: RecyclerView
    lateinit var progress: ProgressBar
    lateinit var noInternetImagem : ImageView
    lateinit var noIntentText : TextView
    lateinit var carsApi : CarsApi

    var carrosArray: ArrayList<Carro> = ArrayList()

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
        setupRetrofit()
        setupView(view)
        setupListeners()

    }
    override fun onResume() {
        super.onResume()
        if (checkForInternet(context)) {
            //callService() -> outra forma de chamar o serviço
            getAllCars()
        }else {
            emptyState()
        }
    }

    fun setupRetrofit(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://igorbag.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        carsApi = retrofit.create(CarsApi::class.java)

    }

    fun getAllCars() {
        carsApi.getAllCars().enqueue(object : Callback<List<Carro>>{
            override fun onResponse(call: Call<List<Carro>>, response: Response<List<Carro>>) {
                if (response.isSuccessful){
                    progress.isVisible = false
                    noInternetImagem.isVisible = false
                    noIntentText.isVisible = false
                    response.body()?.let {
                        setupList(it)
                    }

                } else{
                    Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<List<Carro>>, t: Throwable) {
                Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
            }
        })
    }
    fun emptyState(){
        progress.isVisible = false
        listaCarros.isVisible = false
        noInternetImagem.isVisible = true
        noIntentText.isVisible = true
    }

    fun setupView(view: View) {

        view.apply {
            fab_Calcular = findViewById(R.id.fab_calcular)
            listaCarros = findViewById(R.id.rv_lista_carro)
            progress = findViewById(R.id.pb_loader)
            noInternetImagem = findViewById(R.id.iv_empty_state)
            noIntentText = findViewById(R.id.tv_no_wifi)
        }
    }
    fun setupList(lista: List<Carro>) {
        val carroAdapter = CarAdapter(lista)
        listaCarros.apply {
            isVisible = true
            listaCarros.adapter = carroAdapter
        }
        carroAdapter.carItemList = {carro ->
            val bateria = carro.bateria
        }
    }
    fun setupListeners() {
        fab_Calcular.setOnClickListener {
            startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }
    }


    fun callService() {
        val urlBase = "https://igorbag.github.io/cars-api/cars.json"
        MyTask().execute(urlBase)
        progress.isVisible = true
    }

    //pega o serviço de conectividade fora do app
    fun checkForInternet(context: Context?) : Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when{
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
           val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    //Utilizar o retrofit como abstração do AsnycTask!
    @SuppressLint("StaticFieldLeak")
    inner class MyTask : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("MyTask", "Inciando...")
        }

        override fun doInBackground(vararg url: String?): String {
            var urlConnection: HttpURLConnection? = null
            try {
                val urlBase = URL(url[0])

                urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000
                urlConnection.setRequestProperty(
                    "Accept", "application/json"
                )

                val responseCode = urlConnection.responseCode

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    var response = urlConnection.inputStream.bufferedReader().use { it.readText() }
                    publishProgress(response)
                } else {
                    Log.e("Erro", "Serviço indisponivel no momento....")
                }


            } catch (ex: Exception) {
                Log.e("Erro", "Erro ao realizar processamento....")
            } finally {
                urlConnection?.disconnect()
            }
            return " "
        }


        //Dados lendo atributos da api
        override fun onProgressUpdate(vararg values: String?) {
            try {
                val jsonArray = JSONTokener(values[0]).nextValue() as JSONArray

                for (i in 0 until jsonArray.length()) {
                    val id = jsonArray.getJSONObject(i).getString("id")
                    Log.d("ID ->", id)

                    val preco = jsonArray.getJSONObject(i).getString("preco")
                    Log.d("Preco ->", preco)

                    val bateria = jsonArray.getJSONObject(i).getString("bateria")
                    Log.d("Bateria ->", bateria)

                    val potencia = jsonArray.getJSONObject(i).getString("potencia")
                    Log.d("Potencia ->", potencia)

                    val recarga = jsonArray.getJSONObject(i).getString("recarga")
                    Log.d("Recarga ->", recarga)

                    val urlPhoto = jsonArray.getJSONObject(i).getString("urlPhoto")
                    Log.d("urlPhoto ->", urlPhoto)

                    val model = Carro(
                        id = id.toInt(),
                        preco = preco,
                        bateria = bateria,
                        potencia = potencia,
                        recarga = recarga,
                        urlPhoto = urlPhoto
                    )

                    carrosArray.add(model)

                }
                progress.isVisible = false
                listaCarros.isVisible = false
                noInternetImagem.isVisible = true
                noIntentText.isVisible = true

            } catch (ex: java.lang.Exception) {
                Log.e("Erro ->", ex.message.toString())
            }
        }
    }
}