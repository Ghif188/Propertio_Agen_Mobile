package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.myapplication.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val country = resources.getStringArray(R.array.country)
        with(binding){
            val countriesAdapter = ArrayAdapter(this@Register,
                android.R.layout.simple_spinner_dropdown_item,
                country)
            countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCountry.adapter = countriesAdapter
            spinnerCountry?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val selectedItem = parent?.getItemAtPosition(position).toString()
                    if (selectedItem == "USA") {
                        countryCode.text = "+1"
                    } else if (selectedItem == "AUS"){
                        countryCode.text = "+20"
                    } else if (selectedItem == "AUT"){
                        countryCode.text = "+43"
                    } else if (selectedItem == "IOT"){
                        countryCode.text = "+246"
                    } else if (selectedItem == "CAN"){
                        countryCode.text = "+1"
                    } else if (selectedItem == "FIN"){
                        countryCode.text = "+358"
                    } else if (selectedItem == "IDN"){
                        countryCode.text = "+62"
                    } else if (selectedItem == "JPN"){
                        countryCode.text = "+81"
                    }
                }
            }
        }
    }
}