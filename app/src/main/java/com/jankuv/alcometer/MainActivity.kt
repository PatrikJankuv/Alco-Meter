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
    private var drinks: ArrayList<Shot> = arrayListOf()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //dropdown gender
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        gender_select.adapter = adapter

        //dropdown drinks
        val drinksAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.drinks_array,
            android.R.layout.simple_spinner_item
        )
        drinksAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        drinks_spinner.adapter = drinksAdapter

        calculate.setOnClickListener {
            calculateBac()
        }

        add_drink_button.setOnClickListener {
            addShot()
            list_of_shots()
        }

        list_of_shots()

    }

    /*
    add shot to list of drinks
     */
    private fun addShot() {
        val drink_name = drinks_spinner.selectedItem.toString()

        when (drink_name) {
            "Beer 10°" -> {
                drinks.add(
                    Shot(
                        drink_name,
                        AlcoholMenuEnum.BEER10.volume,
                        AlcoholMenuEnum.BEER10.percentage,
                        LocalDateTime.now()
                    )
                )
            }
            "Beer 12°" -> {
                drinks.add(
                    Shot(
                        drink_name,
                        AlcoholMenuEnum.BEER12.volume,
                        AlcoholMenuEnum.BEER12.percentage,
                        LocalDateTime.now()
                    )
                )
            }
            "Glass of wine" -> {
                drinks.add(
                    Shot(
                        drink_name,
                        AlcoholMenuEnum.WINE.volume,
                        AlcoholMenuEnum.WINE.percentage,
                        LocalDateTime.now()
                    )
                )
            }
            "Vodka (40%) 0,5 dl" -> {
                drinks.add(
                    Shot(
                        drink_name,
                        AlcoholMenuEnum.VODKA.volume,
                        AlcoholMenuEnum.VODKA.percentage,
                        LocalDateTime.now()
                    )
                )
            }
            "Gin (35%) 0,5 dl" -> {
                drinks.add(
                    Shot(
                        drink_name,
                        AlcoholMenuEnum.GIN.volume,
                        AlcoholMenuEnum.GIN.percentage,
                        LocalDateTime.now()
                    )
                )
            }
            "Slivovica (52%) 0,5 dl" -> {
                drinks.add(
                    Shot(
                        drink_name,
                        AlcoholMenuEnum.SPIRIT.volume,
                        AlcoholMenuEnum.SPIRIT.percentage,
                        LocalDateTime.now()
                    )
                )
            }
            "Black Tatra tea (72%) 0,5 dl" -> {
                drinks.add(
                    Shot(
                        drink_name,
                        AlcoholMenuEnum.TATRA_TEA.volume,
                        AlcoholMenuEnum.TATRA_TEA.percentage,
                        LocalDateTime.now()
                    )
                )
            }
        }

        Toast.makeText(this, "Add $drink_name", Toast.LENGTH_LONG).show()
    }


    private fun calculateBac() {
        val weightS: String = weight_ediit.text.toString()

        //check if the weight is set
        if (weightS.trim().isEmpty()) {
            Toast.makeText(applicationContext, "Please enter weight ", Toast.LENGTH_SHORT).show()
        } else {
            val message: String
            val weight = weightS.toInt() * 1000


            val genderConst = getGenderConst(gender_select.selectedItem.toString())
            val BAC = (alcoholConsumed() / (weight * genderConst)) * 100
            val b = "%.3f".format(BAC)
            message = "$b ‰"


            bac_view.text = message
        }
    }

    private fun list_of_shots() {
        //getting recyclerview from xml
        val recyclerView = findViewById(R.id.drinks_list) as RecyclerView

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        val drinkListAdapter = CustomAdapter(drinks)

        recyclerView.adapter = drinkListAdapter
    }

    private fun getGenderConst(gender: String): Double {
        if (gender == "Female") {
            return Gender_const.FEMALE.r
        } else if (gender == "Male") {
            return Gender_const.MALE.r
        }

        return Gender_const.TRANS.r
    }

    private fun alcoholConsumed(): Double {
        var alcohol = 0.0

        for (s in drinks) {
            alcohol += s.alcohol_content()
        }

        return alcohol
    }
}
