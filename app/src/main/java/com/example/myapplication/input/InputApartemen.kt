package com.example.myapplication.input

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.databinding.ActivityInputApartemenBinding
import com.example.myapplication.model.FormDetailProperti
import com.example.myapplication.model.FormProperti
import java.io.Serializable

class InputApartemen : AppCompatActivity() {
    private lateinit var binding: ActivityInputApartemenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputApartemenBinding.inflate(layoutInflater)
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

        val dataTempo = getSharedPreferences("dataTemp", MODE_PRIVATE)
        val oldData = getSharedPreferences("property_data", MODE_PRIVATE)
        val idOld = oldData.getString("property_id", null)

        with(binding){
            if (idOld != null) {
                val luasbangunan = oldData.getString("property_luasBangunan", null)
                val jmlkamar = oldData.getString("property_jmlKamar", null)
                val jmlkamarmandi = oldData.getString("property_jmlKamarMandi", null)

                luasBangunan.setText(luasbangunan)
                kamar.setText(jmlkamar)
                kamarMandi.setText(jmlkamarmandi)
            }
            btnNext.setOnClickListener {
                if (harga.text.isEmpty()) {
                    Toast.makeText(this@InputApartemen, "Masukkan harga apartemen terlebih dahulu", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                with(dataTempo.edit()) {
                    putString("detail_deskripsi", deskripsi.text.toString())
                    putInt("detail_luasBangunan", parseToInt(luasBangunan))
                    putInt("detail_jmlKamar", parseToInt(kamar))
                    putInt("detail_jmlKamarMandi", parseToInt(kamarMandi))
                    putInt("detail_harga", parseToInt(harga))
                    putInt("detail_tahunDibangun", parseToInt(tahunDibangun))
                    putString("detail_posisi", posisiSpinner.selectedItem.toString())
                    putString("detail_tempatParkir", tempatParkir.selectedItem.toString())
                    putString("detail_tipeHarga", tipeHarga.selectedItem.toString())
                    putString("detail_dayaListrik", dayaListrik.selectedItem.toString())
                    putString("detail_kondisi", kondisiSpinner.selectedItem.toString())
                    putString("detail_tipeAir", tipeAir.selectedItem.toString())
                    putString("detail_interior", interiorSpinner.selectedItem.toString())
                    putString("detail_aksesJalan", aksesJalan.selectedItem.toString())
                    commit()
                }

                val intentToInputVideo = Intent(this@InputApartemen, InputVideo::class.java)
                startActivity(intentToInputVideo)
            }

            btnBack.setOnClickListener{
                finish()
            }

            val parkirAdapter = ArrayAdapter(this@InputApartemen,
                R.layout.simple_spinner_dropdown_item,
                parkir)
            parkirAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tempatParkir.adapter = parkirAdapter

            val posisiAdapter = ArrayAdapter(this@InputApartemen,
                R.layout.simple_spinner_dropdown_item,
                posisi)
            posisiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            posisiSpinner.adapter = posisiAdapter

            val hargaAdapter = ArrayAdapter(this@InputApartemen,
                R.layout.simple_spinner_dropdown_item,
                tipe_harga)
            hargaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            tipeHarga.adapter = hargaAdapter
            val listrikAdapter = ArrayAdapter(this@InputApartemen,
                R.layout.simple_spinner_dropdown_item,
                listrik)
            listrikAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dayaListrik.adapter = listrikAdapter

            val airAdapter = ArrayAdapter(this@InputApartemen,
                R.layout.simple_spinner_dropdown_item,
                tipe_air)
            airAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipeAir.adapter = airAdapter

            val kondisiAdapter = ArrayAdapter(this@InputApartemen,
                R.layout.simple_spinner_dropdown_item,
                kondisi)
            kondisiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            kondisiSpinner.adapter = kondisiAdapter

            val interiorAdapter = ArrayAdapter(this@InputApartemen,
                R.layout.simple_spinner_dropdown_item,
                interior)
            interiorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            interiorSpinner.adapter = interiorAdapter

            val jalanAdapter = ArrayAdapter(this@InputApartemen,
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