package com.example.myapplication.input

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.databinding.ActivityInputTanahBinding

class InputTanah : AppCompatActivity() {
    private lateinit var binding: ActivityInputTanahBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputTanahBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val tipe_harga = resources.getStringArray(com.example.myapplication.R.array.tipe_harga)
        val akses_jalan = resources.getStringArray(com.example.myapplication.R.array.jalan)

        val dataTempo = getSharedPreferences("dataTemp", MODE_PRIVATE)
        val oldData = getSharedPreferences("property_data", MODE_PRIVATE)
        val idOld = oldData.getString("property_id", null)

        with(binding){
            btnNext.setOnClickListener {
                if (harga.text.isEmpty()) {
                    Toast.makeText(this@InputTanah, "Masukkan harga jual tanah", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                with(dataTempo.edit()) {
                    putString("detail_deskripsi", deskripsi.text.toString())
                    putInt("detail_luasTanah", parseToInt(luasTanah))
                    putInt("detail_harga", parseToInt(harga))
                    putString("detail_tipeHarga", tipeHarga.selectedItem.toString())
                    putString("detail_aksesJalan", aksesJalan.selectedItem.toString())
                    commit()
                }

                val intentToInputVideo = Intent(this@InputTanah, InputVideo::class.java)
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