package com.example.homework2

import android.util.Log
import com.google.gson.GsonBuilder
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


data class DataExample(val value: String)

val connector by lazy { Connector() }

data class ImageUrl(val message : String,
                    val status : String,
                    val connector : Connector = Connector()){
    suspend fun allImages(): List<DataExample> = connector.load()
}

private suspend fun HttpClient.getRemoteImages():
        String = get("https://dog.ceo/api/breeds/image/random")

val InternetAdress: HttpClient by lazy {
    HttpClient()
}

class Connector(private val client: HttpClient = InternetAdress) {
    suspend fun load(): List<DataExample> {
        val a : MutableList<DataExample> = emptyList<DataExample>().toMutableList()
        repeat(5){
            a.add(withContext(Dispatchers.IO) {
                client.getRemoteImages()
                    .lineSequence()
                    .map { DataExample(it) }
                    .toList()
            }[0])
        }
        Log.d("Connector", a.toString())
        return a
    }

    suspend fun imageLink() : String{
        val gson = GsonBuilder().create()
        val a = gson.fromJson(load()[0].value, ImageUrl::class.java)
        Log.d("from imagelink", a.message)
        return a.message
    }
}
