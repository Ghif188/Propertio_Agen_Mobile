package com.example.myapplication.input

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.myapplication.databinding.ActivityInputTanahBinding
import com.example.myapplication.model.FormDetailProperti
import com.example.myapplication.model.FormProperti
import java.io.Serializable

class InputTanah : AppCompatActivity() {
    private lateinit var binding: ActivityInputTanahBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputTanahBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val tipe_harga = resources.getStringArray(com.example.myapplication.R.array.tipe_harga)
        val akses_jalan = resources.getStringArray(com.example.myapplication.R.array.jalan)

        val dataTemp = intent.extras?.get("temp") as FormProperti

        with(binding){
            var detailTemp = FormDetailProperti()

            detailTemp.deskripsi = deskripsi.text.toString()
            detailTemp.luasTanah = luasTanah.text.toString().toInt()
            detailTemp.harga = harga.text.toString().toInt()
            detailTemp.tipeHarga = tipeHarga.selectedItem.toString()
            detailTemp.aksesJalan = aksesJalan.selectedItem.toString()

            dataTemp.detailProperti = detailTemp
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputTanah, InputVideo::class.java)
                intentToInputVideo.putExtra("temp", dataTemp as Serializable)
                startActivity(intentToInputVideo)
            }

            btnBack.setOnClickListener{
                finish()
            }

            val hargaAdapter = ArrayAdapter(this@InputTanah,
                R.layout.simple_spinner_dropdown_item,
                tipe_harga)
            hargaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipeHarga.adapter = hargaAdapter

            val jalanAdapter = ArrayAdapter(this@InputTanah,
                R.layout.simple_spinner_dropdown_item,
                akses_jalan)
            jalanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            aksesJalan.adapter = jalanAdapter
        }
    }
}