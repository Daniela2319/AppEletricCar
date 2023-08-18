package com.minhaempresa.eletric_car.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.minhaempresa.eletric_car.data.local.CarrosContract.CarEntry.COLUMN_NAME_BATERIA
import com.minhaempresa.eletric_car.data.local.CarrosContract.CarEntry.COLUMN_NAME_CAR_ID
import com.minhaempresa.eletric_car.data.local.CarrosContract.CarEntry.COLUMN_NAME_POTENCIA
import com.minhaempresa.eletric_car.data.local.CarrosContract.CarEntry.COLUMN_NAME_PRECO
import com.minhaempresa.eletric_car.data.local.CarrosContract.CarEntry.COLUMN_NAME_RECARGA
import com.minhaempresa.eletric_car.data.local.CarrosContract.CarEntry.COLUMN_NAME_URL_PHOTO
import com.minhaempresa.eletric_car.domain.Carro

class CarRepository(private val context: Context) {

    fun saveOnDatabase( carro: Carro): Boolean{
        var isSaved = false
        try {


            val dbhelper = CarsDbHelper(context)
            val db = dbhelper.writableDatabase

          val values = ContentValues().apply {
            put(COLUMN_NAME_CAR_ID, carro.id)
            put(COLUMN_NAME_PRECO, carro.preco)
            put(COLUMN_NAME_BATERIA, carro.bateria)
            put(COLUMN_NAME_POTENCIA, carro.potencia)
            put(COLUMN_NAME_RECARGA, carro.recarga)
            put(COLUMN_NAME_URL_PHOTO, carro.urlPhoto)
         }
           //vai retorna id do registro
           val inserted = db?.insert(CarrosContract.CarEntry.TABLE_NAME, null, values)
            if (inserted != null){
                isSaved = true
            }
        }catch (ex: Exception) {
            ex.message?.let {
                Log.e("Erro ao inserir", it)
            }
        }
        return isSaved
    }

    fun findCarById(id: Int) : Carro{
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase
        //lista de coluna quer informar para o usuario RESULTADO DA QUERY
        val columns = arrayOf(BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_PRECO,
            COLUMN_NAME_BATERIA,
            COLUMN_NAME_POTENCIA,
            COLUMN_NAME_RECARGA,
            COLUMN_NAME_URL_PHOTO
        )
        val filter = "${COLUMN_NAME_CAR_ID}= ?"
        //filterValues para tratar transforma id numa string
        val filterValues = arrayOf(id.toString())

        val cursor = db.query(
            CarrosContract.CarEntry.TABLE_NAME, //nome da tabela
            columns, // as colunas a serem exibidas
            filter, // where (filtro)
            filterValues, //valor do where, substituindo o parametro ?
            null,
            null,
            null
        )
        val itemCar = mutableListOf<Carro>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID))
                Log.d("ID -> ", itemId.toString())

                val preco = getString(getColumnIndexOrThrow(COLUMN_NAME_PRECO))
                Log.d("preço ->", preco)
            }
        }
        cursor.close()
    }
}