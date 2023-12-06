package com.example.myapplication.input

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.databinding.ActivityInputKantorBinding
import com.example.myapplication.model.FormDetailProperti
import com.example.myapplication.model.FormProperti
import java.io.Serializable

class InputKantor : AppCompatActivity() {
    private lateinit var binding: ActivityInputKantorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputKantorBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val parkir = resources.getStringArray(com.example.myapplication.R.array.parkir)
        val posisi = resources.getStringArray(com.example.myapplication.R.array.posisi)
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
                    Toast.makeText(this@InputKantor, "Masukkan deskripsi kantor", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (luasTanah.text.isEmpty()) {
                    Toast.makeText(this@InputKantor, "Masukkan luas tanah", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (luasBangunan.text.isEmpty()) {
                    Toast.makeText(this@InputKantor, "Masukkan luas bangunan", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (jmlLantai.text.isEmpty()) {
                    Toast.makeText(this@InputKantor, "Masukkan jumlah lantai", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (kamar.text.isEmpty()) {
                    Toast.makeText(this@InputKantor, "Masukkan jumlah kamar", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (tahunDibangun.text.isEmpty()) {
                    Toast.makeText(this@InputKantor, "Masukkan tahun dibangun kantor", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (harga.text.isEmpty()) {
                    Toast.makeText(this@InputKantor, "Masukkan harga kantor", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                var detailTemp = FormDetailProperti()

                detailTemp.deskripsi = deskripsi.text.toString()
                detailTemp.luasTanah = parseToInt(luasTanah)
                detailTemp.luasBangunan = parseToInt(luasBangunan)
                detailTemp.jmlKamar = parseToInt(kamar)
                detailTemp.jmlKamarMandi = parseToInt(kamarMandi)
                detailTemp.jmlLantai = parseToInt(jmlLantai)
                detailTemp.tahunDibangun = parseToInt(tahunDibangun)
                detailTemp.harga = parseToInt(harga)
                detailTemp.tempatParkir = tempatParkir.selectedItem.toString()
                detailTemp.posisi = posisiSpinner.selectedItem.toString()
                detailTemp.tipeHarga = tipeHarga.selectedItem.toString()
                detailTemp.dayaListrik = dayaListrik.selectedItem.toString()
                detailTemp.kondisi = kondisiSpinner.selectedItem.toString()
                detailTemp.tipeAir = tipeAir.selectedItem.toString()
                detailTemp.interior = interiorSpinner.selectedItem.toString()
                detailTemp.aksesJalan = aksesJalan.selectedItem.toString()

                dataTemp.detailProperti = detailTemp
                val intentToInputVideo = Intent(this@InputKantor, InputVideo::class.java)
                intentToInputVideo.putExtra("temp", dataTemp as Serializable)
                startActivity(intentToInputVideo)
            }

            btnBack.setOnClickListener{
                finish()
            }

            val parkirAdapter = ArrayAdapter(this@InputKantor,
            R.layout.simple_spinner_dropdown_item,
            parkir)
            parkirAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tempatParkir.adapter = parkirAdapter

            val posisiAdapter = ArrayAdapter(this@InputKantor,
                R.layout.simple_spinner_dropdown_item,
                posisi)
            posisiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            posisiSpinner.adapter = posisiAdapter

            val hargaAdapter = ArrayAdapter(this@InputKantor,
                R.layout.simple_spinner_dropdown_item,
                tipe_harga)
            hargaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipeHarga.adapter = hargaAdapter

            val listrikAdapter = ArrayAdapter(this@InputKantor,
                R.layout.simple_spinner_dropdown_item,
                listrik)
            listrikAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dayaListrik.adapter = listrikAdapter

            val airAdapter = ArrayAdapter(this@InputKantor,
                R.layout.simple_spinner_dropdown_item,
                tipe_air)
            airAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipeAir.adapter = airAdapter

            val kondisiAdapter = ArrayAdapter(this@InputKantor,
                R.layout.simple_spinner_dropdown_item,
                kondisi)
            kondisiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            kondisiSpinner.adapter = kondisiAdapter

            val interiorAdapter = ArrayAdapter(this@InputKantor,
                R.layout.simple_spinner_dropdown_item,
                interior)
            interiorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            interiorSpinner.adapter = interiorAdapter

            val jalanAdapter = ArrayAdapter(this@InputKantor,
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