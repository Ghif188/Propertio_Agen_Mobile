package com.example.myapplication.input

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.myapplication.databinding.ActivityInputPabrikBinding

class InputPabrik : AppCompatActivity() {
    private lateinit var binding: ActivityInputPabrikBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputPabrikBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val parkir = resources.getStringArray(com.example.myapplication.R.array.parkir)
        val tipe_harga = resources.getStringArray(com.example.myapplication.R.array.tipe_harga)
        val listrik = resources.getStringArray(com.example.myapplication.R.array.tipe_listrik)
        val kondisi = resources.getStringArray(com.example.myapplication.R.array.kondisi)
        val tipe_air = resources.getStringArray(com.example.myapplication.R.array.air)
        val interior = resources.getStringArray(com.example.myapplication.R.array.interior)
        val akses_jalan = resources.getStringArray(com.example.myapplication.R.array.jalan)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputPabrik, InputVideo::class.java)
                startActivity(intentToInputVideo)
            }
            btnBack.setOnClickListener{
                val intentToInputLokasi = Intent(this@InputPabrik, InputLokasi::class.java)
                startActivity(intentToInputLokasi)
            }
            val parkirAdapter = ArrayAdapter(this@InputPabrik,
                R.layout.simple_spinner_dropdown_item,
                parkir)
            parkirAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tempatParkir.adapter = parkirAdapter
            val hargaAdapter = ArrayAdapter(this@InputPabrik,
                R.layout.simple_spinner_dropdown_item,
                tipe_harga)
            hargaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipeHarga.adapter = hargaAdapter
            val listrikAdapter = ArrayAdapter(this@InputPabrik,
                R.layout.simple_spinner_dropdown_item,
                listrik)
            listrikAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dayaListrik.adapter = listrikAdapter
            val airAdapter = ArrayAdapter(this@InputPabrik,
                R.layout.simple_spinner_dropdown_item,
                tipe_air)
            airAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipeAir.adapter = airAdapter
            val kondisiAdapter = ArrayAdapter(this@InputPabrik,
                R.layout.simple_spinner_dropdown_item,
                kondisi)
            kondisiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            kondisiSpinner.adapter = kondisiAdapter
            val interiorAdapter = ArrayAdapter(this@InputPabrik,
                R.layout.simple_spinner_dropdown_item,
                interior)
            interiorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            interiorSpinner.adapter = interiorAdapter
            val jalanAdapter = ArrayAdapter(this@InputPabrik,
                R.layout.simple_spinner_dropdown_item,
                akses_jalan)
            jalanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            aksesJalan.adapter = jalanAdapter
        }
    }
}