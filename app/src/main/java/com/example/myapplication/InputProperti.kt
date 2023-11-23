package com.example.myapplication

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.myapplication.databinding.ActivityInputPropertiBinding

class InputProperti : AppCompatActivity() {
    private lateinit var binding: ActivityInputPropertiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputPropertiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val tipeProperti = resources.getStringArray(com.example.myapplication.R.array.tipe_properti)
        with(binding){
            val tipePropertiAdapter = ArrayAdapter(this@InputProperti,
                R.layout.simple_spinner_dropdown_item,
                tipeProperti)
            tipePropertiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipePropertiSpinner.adapter = tipePropertiAdapter
            var selectedTipe = ""
            tipePropertiSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedTipe = parent?.getItemAtPosition(position).toString()
                }
            }
            btnNext.setOnClickListener {
                val intentToInputLokasi = Intent(this@InputProperti, InputLokasi::class.java)
                intentToInputLokasi.putExtra("tipe", selectedTipe)
                startActivity(intentToInputLokasi)
            }
        }
    }
}