package com.example.myapplication.input

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.ActivityInputPropertiBinding

class InputProperti : AppCompatActivity() {
    private lateinit var binding: ActivityInputPropertiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputPropertiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val tipe_properti = resources.getStringArray(com.example.myapplication.R.array.tipe_properti)
        val tipe_iklan = resources.getStringArray(com.example.myapplication.R.array.tipe_properti)
        val tipe_sertifikat = resources.getStringArray(com.example.myapplication.R.array.tipe_sertifikat)
        with(binding){
            val tipePropertiAdapter = ArrayAdapter(this@InputProperti,
                R.layout.simple_spinner_dropdown_item,
                tipe_properti)
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
            val tipeIklanAdapter  = ArrayAdapter(this@InputProperti, R.layout.simple_spinner_dropdown_item, tipe_iklan)
            tipeIklanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipeIklan.adapter = tipeIklanAdapter
            val sertifikatAdapter  = ArrayAdapter(this@InputProperti, R.layout.simple_spinner_dropdown_item, tipe_sertifikat)
            sertifikatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sertifikat.adapter = sertifikatAdapter
            btnNext.setOnClickListener {
                val intentToInputLokasi = Intent(this@InputProperti, InputLokasi::class.java)
                intentToInputLokasi.putExtra("tipe", selectedTipe)
                startActivity(intentToInputLokasi)
            }
            btnBack.setOnClickListener{
                val intentToInputLokasi = Intent(this@InputProperti, MainActivity::class.java)
                startActivity(intentToInputLokasi)
            }
        }
    }
}