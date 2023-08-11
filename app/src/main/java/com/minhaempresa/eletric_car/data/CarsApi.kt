package com.minhaempresa.eletric_car.data

import com.minhaempresa.eletric_car.domain.Carro
import retrofit2.Call
import retrofit2.http.GET

interface CarsApi  {

    //https://igorbar.github.io/cars-api/cars.json usa só final do enderenço
    @GET("cars.json")
    fun getAllCars() : Call<List<Carro>>
}