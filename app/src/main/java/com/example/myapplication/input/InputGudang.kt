package com.example.myapplication.input

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.myapplication.input.InputLokasi
import com.example.myapplication.input.InputVideo
import com.example.myapplication.databinding.ActivityInputGudangBinding

class InputGudang : AppCompatActivity() {
    private lateinit var binding: ActivityInputGudangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputGudangBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val parkir = resources.getStringArray(com.example.myapplication.R.array.parkir)
        val tipe_harga = resources.getStringArray(com.example.myapplication.R.array.tipe_harga)
        val listrik = resources.getStringArray(com.example.myapplication.R.array.tipe_listrik)
        val kondisi = resources.getStringArray(com.example.myapplication.R.array.kondisi)
        val akses_jalan = resources.getStringArray(com.example.myapplication.R.array.jalan)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputGudang, InputVideo::class.java)
                startActivity(intentToInputVideo)
            }
            btnBack.setOnClickListener{
                val intentToInputLokasi = Intent(this@InputGudang, InputLokasi::class.java)
                startActivity(intentToInputLokasi)
            }
            val parkirAdapter = ArrayAdapter(this@InputGudang,
                R.layout.simple_spinner_dropdown_item,
                parkir)
            parkirAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tempatParkir.adapter = parkirAdapter
            val hargaAdapter = ArrayAdapter(this@InputGudang,
                R.layout.simple_spinner_dropdown_item,
                tipe_harga)
            hargaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipeHarga.adapter = hargaAdapter
            val listrikAdapter = ArrayAdapter(this@InputGudang,
                R.layout.simple_spinner_dropdown_item,
                listrik)
            listrikAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dayaListrik.adapter = listrikAdapter
            val kondisiAdapter = ArrayAdapter(this@InputGudang,
                R.layout.simple_spinner_dropdown_item,
                kondisi)
            kondisiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            kondisiSpinner.adapter = kondisiAdapter
            val jalanAdapter = ArrayAdapter(this@InputGudang,
                R.layout.simple_spinner_dropdown_item,
                akses_jalan)
            jalanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            aksesJalan.adapter = jalanAdapter
        }
    }
}