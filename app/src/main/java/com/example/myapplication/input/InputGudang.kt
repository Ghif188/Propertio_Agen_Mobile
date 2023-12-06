package com.example.myapplication.input

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.input.InputLokasi
import com.example.myapplication.input.InputVideo
import com.example.myapplication.databinding.ActivityInputGudangBinding
import com.example.myapplication.model.FormDetailProperti
import com.example.myapplication.model.FormProperti
import java.io.Serializable

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

        val dataTemp = intent.extras?.get("temp") as FormProperti
        with(binding){
            btnNext.setOnClickListener {
                if (deskripsi.text.isEmpty()) {
                    Toast.makeText(this@InputGudang, "Masukkan deskripsi gudang", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (luasTanah.text.isEmpty()) {
                    Toast.makeText(this@InputGudang, "Masukkan luas tanah gudang", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (luasBangunan.text.isEmpty()) {
                    Toast.makeText(this@InputGudang, "Masukkan luas bangunan gudang", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (tahunDibangun.text.isEmpty()) {
                    Toast.makeText(this@InputGudang, "Masukkan tahun dibangun gudang", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (harga.text.isEmpty()) {
                    Toast.makeText(this@InputGudang, "Masukkan harga gudang", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                var detailTemp = FormDetailProperti()

                detailTemp.deskripsi = deskripsi.text.toString()
                detailTemp.luasTanah = parseToInt(luasTanah)
                detailTemp.luasBangunan = parseToInt(luasBangunan)
                detailTemp.tempatParkir = tempatParkir.selectedItem.toString()
                detailTemp.tahunDibangun = parseToInt(tahunDibangun)
                detailTemp.harga = parseToInt(harga)
                detailTemp.tipeHarga = tipeHarga.selectedItem.toString()
                detailTemp.dayaListrik = dayaListrik.selectedItem.toString()
                detailTemp.kondisi = kondisiSpinner.selectedItem.toString()
                detailTemp.aksesJalan = aksesJalan.selectedItem.toString()

                dataTemp.detailProperti = detailTemp
                val intentToInputVideo = Intent(this@InputGudang, InputVideo::class.java)
                intentToInputVideo.putExtra("temp", dataTemp as Serializable)
                startActivity(intentToInputVideo)
            }

            btnBack.setOnClickListener{
                finish()
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

    private fun parseToInt(editText: EditText) : Int {
        return if (editText.text.isEmpty()) {
            0
        } else {
            editText.text.toString().toInt()
        }
    }
}