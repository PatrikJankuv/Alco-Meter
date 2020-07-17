package com.jankuv.alcometer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var drinks: ArrayList<Shot> = arrayListOf()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //dropdown gender
        val adapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        gender_select.adapter = adapter

        //dropdown drinks
        val drinks_adapter = ArrayAdapter.createFromResource(this, R.array.drinks_array, android.R.layout.simple_spinner_item)
        drinks_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        drinks_spinner.adapter = drinks_adapter

        calculate.setOnClickListener {
            calculate_BAC()
        }

        add_drink_button.setOnClickListener {
            addShot()
            list_of_shots()
        }

        list_of_shots()

    }

    /*
    Get gender from
     */
    private fun getGender() {
        Toast.makeText(this, "Gender selected " + gender_select.selectedItem.toString(), Toast.LENGTH_LONG).show()
    }

    /*
    add shot to list of drinks
     */
    fun addShot() {
        val drink_name = drinks_spinner.selectedItem.toString()

        if(drink_name == "Beer 10°"){
            drinks.add(Shot(drink_name,  AlcoholMenuEnum.BEER10.volume, AlcoholMenuEnum.BEER10.percentage, LocalDateTime.now()))
        }
        else if(drink_name == "Beer 12°"){
            drinks.add(Shot(drink_name,  AlcoholMenuEnum.BEER12.volume, AlcoholMenuEnum.BEER12.percentage, LocalDateTime.now()))
        }
        else if(drink_name == "Glass of wine"){
            drinks.add(Shot(drink_name,  AlcoholMenuEnum.WINE.volume, AlcoholMenuEnum.WINE.percentage, LocalDateTime.now()))
        }
        else if(drink_name == "Vodka (40%) 0,5 dl"){
            drinks.add(Shot(drink_name,  AlcoholMenuEnum.VODKA.volume, AlcoholMenuEnum.VODKA.percentage, LocalDateTime.now()))
        }
        else if(drink_name == "Gin (35%) 0,5 dl"){
            drinks.add(Shot(drink_name,  AlcoholMenuEnum.GIN.volume, AlcoholMenuEnum.GIN.percentage, LocalDateTime.now()))
        }
        else if(drink_name == "Slivovica (52%) 0,5 dl"){
            drinks.add(Shot(drink_name,  AlcoholMenuEnum.SPIRIT.volume, AlcoholMenuEnum.SPIRIT.percentage, LocalDateTime.now()))
        }
        else if(drink_name == "Black Tatra tea (72%) 0,5 dl"){
            drinks.add(Shot(drink_name,  AlcoholMenuEnum.TATRA_TEA.volume, AlcoholMenuEnum.TATRA_TEA.percentage, LocalDateTime.now()))
        }

        Toast.makeText(this, "Add $drink_name", Toast.LENGTH_LONG).show()
//        drinks.add(Shot(drink_name, 7.0, AlcoholMenuEnum.BEER10.percentage))
    }


    private fun calculate_BAC(){
        val msg: String = weight_ediit.text.toString()

        //check if the EditText have values or not
        if(msg.trim().length>0) {
            Toast.makeText(applicationContext, "Message : "+msg, Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "Please enter some message! ", Toast.LENGTH_SHORT).show()
        }
    }

    fun list_of_shots(){
        //getting recyclerview from xml
        val recyclerView = findViewById(R.id.drinks_list) as RecyclerView

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

//        drinks.add(Shot("Beer 10°",  AlcoholMenuEnum.BEER10.volume, AlcoholMenuEnum.BEER10.percentage))
//         drinks.add(Shot("Beer 10°",  AlcoholMenuEnum.BEER10.volume, AlcoholMenuEnum.BEER10.percentage))
//         drinks.add(Shot("Beer 10°",  AlcoholMenuEnum.BEER10.volume, AlcoholMenuEnum.BEER10.percentage))
//         drinks.add(Shot("Beer 10°",  AlcoholMenuEnum.BEER10.volume, AlcoholMenuEnum.BEER10.percentage))
//         drinks.add(Shot("Beer 10°",  AlcoholMenuEnum.BEER10.volume, AlcoholMenuEnum.BEER10.percentage))

        val drink_list_adapter = CustomAdapter(drinks)

        recyclerView.adapter = drink_list_adapter
    }
}
