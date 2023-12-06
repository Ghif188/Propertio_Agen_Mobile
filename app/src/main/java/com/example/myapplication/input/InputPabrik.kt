package com.example.myapplication.input

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.databinding.ActivityInputPabrikBinding
import com.example.myapplication.model.FormDetailProperti
import com.example.myapplication.model.FormProperti
import java.io.Serializable

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

        val dataTemp = intent.extras?.get("temp") as FormProperti

        with(binding){
            btnNext.setOnClickListener {
                if (deskripsi.text.isEmpty()) {
                    Toast.makeText(this@InputPabrik, "Masukkan deskripsi pabrik", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (luasTanah.text.isEmpty()) {
                    Toast.makeText(this@InputPabrik, "Masukkan luas tanah", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (luasBangunan.text.isEmpty()) {
                    Toast.makeText(this@InputPabrik, "Masukkan luas bangunan", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (tahunDibangun.text.isEmpty()) {
                    Toast.makeText(this@InputPabrik, "Masukkan tahun dibangun pabrik", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (harga.text.isEmpty()) {
                    Toast.makeText(this@InputPabrik, "Masukkan harga jual pabrik", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                var detailTemp = FormDetailProperti()

                detailTemp.deskripsi = deskripsi.text.toString()
                detailTemp.luasTanah = parseToInt(luasTanah)
                detailTemp.luasBangunan = parseToInt(luasBangunan)
                detailTemp.jmlKamarMandi = parseToInt(kamarMandi)
                detailTemp.tahunDibangun = parseToInt(tahunDibangun)
                detailTemp.harga = parseToInt(harga)
                detailTemp.tempatParkir = tempatParkir.selectedItem.toString()
                detailTemp.tipeHarga = tipeHarga.selectedItem.toString()
                detailTemp.dayaListrik = dayaListrik.selectedItem.toString()
                detailTemp.kondisi = kondisiSpinner.selectedItem.toString()
                detailTemp.tipeAir = tipeAir.selectedItem.toString()
                detailTemp.interior = interiorSpinner.selectedItem.toString()
                detailTemp.aksesJalan = aksesJalan.selectedItem.toString()

                dataTemp.detailProperti = detailTemp
                val intentToInputVideo = Intent(this@InputPabrik, InputVideo::class.java)
                intentToInputVideo.putExtra("temp", dataTemp as Serializable)
                startActivity(intentToInputVideo)
            }

            btnBack.setOnClickListener{
                finish()
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

    private fun parseToInt(editText: EditText) : Int {
        return if (editText.text.isEmpty()) {
            0
        } else {
            editText.text.toString().toInt()
        }
    }
}