package com.example.myapplication.input

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
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
            btnNext.setOnClickListener {
                if (harga.text.isEmpty()) {
                    Toast.makeText(this@InputTanah, "Masukkan harga jual tanah", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                var detailTemp = FormDetailProperti()

                detailTemp.deskripsi = deskripsi.text.toString()
                detailTemp.luasTanah = parseToInt(luasTanah)
                detailTemp.harga = parseToInt(harga)
                detailTemp.tipeHarga = tipeHarga.selectedItem.toString()
                detailTemp.aksesJalan = aksesJalan.selectedItem.toString()

                dataTemp.detailProperti = detailTemp

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

    private fun parseToInt(editText: EditText) : Int {
        return if (editText.text.isEmpty()) {
            0
        } else {
            editText.text.toString().toInt()
        }
    }
}