package com.example.parsinglocaljson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    lateinit var rvAdapter: RVAdapter
    lateinit var rvItems : RecyclerView
    lateinit var imagesArray : ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imagesArray = ArrayList()
        rvItems = findViewById(R.id.rvItems)

        getJson()
    }

    fun getJson (){

        var json :String? = null

        try {

            var inputStream : InputStream = assets.open("data.json")
            json = inputStream.bufferedReader().use { it.readText() }

            var jsonArray = JSONArray(json)

            for (i in 0..jsonArray.length()-1){
                var jsonObj = jsonArray.getJSONObject(i)
                imagesArray.add(jsonObj.getString("url"))

                rvAdapter = RVAdapter(imagesArray)
                rvItems.adapter = rvAdapter
                rvItems.layoutManager = LinearLayoutManager(this)
            }

        }catch (e : Exception){

        }


    }
}