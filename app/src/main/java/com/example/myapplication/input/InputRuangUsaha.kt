package com.example.myapplication.input

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.myapplication.databinding.ActivityInputRuangUsahaBinding

class InputRuangUsaha : AppCompatActivity() {
    private lateinit var binding: ActivityInputRuangUsahaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputRuangUsahaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val parkir = resources.getStringArray(com.example.myapplication.R.array.parkir)
        val tipe_harga = resources.getStringArray(com.example.myapplication.R.array.tipe_harga)
        val listrik = resources.getStringArray(com.example.myapplication.R.array.tipe_listrik)
        val kondisi = resources.getStringArray(com.example.myapplication.R.array.kondisi)
        val tipe_air = resources.getStringArray(com.example.myapplication.R.array.air)
        val interior = resources.getStringArray(com.example.myapplication.R.array.interior)
        val akses_jalan = resources.getStringArray(com.example.myapplication.R.array.jalan)
        val menghadap = resources.getStringArray(com.example.myapplication.R.array.menghadap)
        val posisi = resources.getStringArray(com.example.myapplication.R.array.posisi)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputRuangUsaha, InputVideo::class.java)
                startActivity(intentToInputVideo)
            }
            btnBack.setOnClickListener{
                val intentToInputLokasi = Intent(this@InputRuangUsaha, InputLokasi::class.java)
                startActivity(intentToInputLokasi)
            }
            val parkirAdapter = ArrayAdapter(this@InputRuangUsaha,
                R.layout.simple_spinner_dropdown_item,
                parkir)
            parkirAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tempatParkir.adapter = parkirAdapter
            val hargaAdapter = ArrayAdapter(this@InputRuangUsaha,
                R.layout.simple_spinner_dropdown_item,
                tipe_harga)
            hargaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipeHarga.adapter = hargaAdapter
            val listrikAdapter = ArrayAdapter(this@InputRuangUsaha,
                R.layout.simple_spinner_dropdown_item,
                listrik)
            listrikAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dayaListrik.adapter = listrikAdapter
            val airAdapter = ArrayAdapter(this@InputRuangUsaha,
                R.layout.simple_spinner_dropdown_item,
                tipe_air)
            airAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipeAir.adapter = airAdapter
            val kondisiAdapter = ArrayAdapter(this@InputRuangUsaha,
                R.layout.simple_spinner_dropdown_item,
                kondisi)
            kondisiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            kondisiSpinner.adapter = kondisiAdapter
            val interiorAdapter = ArrayAdapter(this@InputRuangUsaha,
                R.layout.simple_spinner_dropdown_item,
                interior)
            interiorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            interiorSpinner.adapter = interiorAdapter
            val jalanAdapter = ArrayAdapter(this@InputRuangUsaha,
                R.layout.simple_spinner_dropdown_item,
                akses_jalan)
            jalanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            aksesJalan.adapter = jalanAdapter
            val menghadapAdapter = ArrayAdapter(this@InputRuangUsaha,
                R.layout.simple_spinner_dropdown_item,
                menghadap)
            menghadapAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            menghadapSpinner.adapter = jalanAdapter
            val posisiAdapter = ArrayAdapter(this@InputRuangUsaha,
                R.layout.simple_spinner_dropdown_item,
                posisi)
            posisiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            posisiSpinner.adapter = jalanAdapter
        }
    }
}