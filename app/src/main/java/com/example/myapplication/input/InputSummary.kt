package com.example.myapplication.input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.ActivityInputSummaryBinding
import com.example.myapplication.model.FormProperti

class InputSummary : AppCompatActivity() {
    private lateinit var binding: ActivityInputSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputSummaryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dataTemp = intent.extras?.get("temp") as FormProperti

        with(binding){

            if (dataTemp.fotoProperti?.isNotEmpty() == true) {
                val firstPhoto = dataTemp.fotoProperti?.get(0)?.image
                Glide.with(this@InputSummary)
                    .load(firstPhoto)
                    .into(headerImage)
            }
            judulProperti.text = dataTemp.judulProperti
            tipeProperti.text = dataTemp.tipeProperti
            harga.text = dataTemp.detailProperti?.harga.toString()
            var lok = dataTemp.kecamatan + ", " + dataTemp.kota + ", " + dataTemp.provinsi
            lokasi.text = lok
            deskripsi.text = dataTemp.detailProperti?.deskripsi
            sertifikat.text = dataTemp.tipeSertifikat
            jumlahKamar.text = dataTemp.detailProperti?.jmlKamar.toString() + " kamar"
            jumlahKamarMandi.text = dataTemp.detailProperti?.jmlKamarMandi.toString() + " kamar mandi"
            luasBangunan.text = dataTemp.detailProperti?.luasBangunan.toString()

            Log.d("HALO _!!!", dataTemp.toString())

            btnBack.setOnClickListener {
                finish()
            }

            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputSummary, MainActivity::class.java)
                startActivity(intentToInputVideo)
            }
        }
    }
}