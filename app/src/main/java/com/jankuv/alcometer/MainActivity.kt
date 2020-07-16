package com.jankuv.alcometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        gender_select.adapter = adapter

        calculate.setOnClickListener {
            getGender(it)
        }
    }

    fun getGender(view: View){
        Toast.makeText(this, "Gender selected " + gender_select.selectedItem.toString(), Toast.LENGTH_LONG).show()
    }
}
