package com.example.myapplication.input

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.databinding.ActivityInputKantorBinding

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

                with(dataTempo.edit()) {
                    putString("detail_deskripsi", deskripsi.text.toString())
                    putInt("detail_luasTanah", parseToInt(luasTanah))
                    putInt("detail_luasBangunan", parseToInt(luasBangunan))
                    putInt("detail_jmlKamar", parseToInt(kamar))
                    putInt("detail_jmlKamarMandi", parseToInt(kamarMandi))
                    putInt("detail_jmlLantai", parseToInt(jmlLantai))
                    putInt("detail_harga", parseToInt(harga))
                    putInt("detail_tahunDibangun", parseToInt(tahunDibangun))
                    putString("detail_tempatParkir", tempatParkir.selectedItem.toString())
                    putString("detail_tipeHarga", tipeHarga.selectedItem.toString())
                    putString("detail_dayaListrik", dayaListrik.selectedItem.toString())
                    putString("detail_kondisi", kondisiSpinner.selectedItem.toString())
                    putString("detail_tipeAir", tipeAir.selectedItem.toString())
                    putString("detail_interior", interiorSpinner.selectedItem.toString())
                    putString("detail_aksesJalan", aksesJalan.selectedItem.toString())
                    commit()
                }

                val intentToInputVideo = Intent(this@InputKantor, InputVideo::class.java)
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