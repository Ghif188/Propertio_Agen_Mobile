package com.example.myapplication.input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.DetailProperti
import com.example.myapplication.Fasilitas
import com.example.myapplication.FasilitasAdapter
import com.example.myapplication.Properti
import com.example.myapplication.databinding.ActivityInputFasilitasPropertiBinding

class InputFasilitasProperti : AppCompatActivity() {
    private lateinit var binding: ActivityInputFasilitasPropertiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputFasilitasPropertiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputFasilitasProperti, DetailProperti::class.java)
                startActivity(intentToInputVideo)
            }
            btnBack.setOnClickListener{
                val intentToInputLokasi = Intent(this@InputFasilitasProperti, DetailProperti::class.java)
                startActivity(intentToInputLokasi)
            }
            val fasilitasAdapter = FasilitasAdapter(generateDummy()) {
            }
            binding.rvFasilitas.apply {
                adapter = fasilitasAdapter
                layoutManager = GridLayoutManager(this@InputFasilitasProperti, 1)
            }
        }
    }
    private fun generateDummy(): List<Fasilitas> {
        return listOf(
            Fasilitas(nama_fasilitas = "Ac"),
            Fasilitas(nama_fasilitas = "Ruang Tenis"),
            Fasilitas(nama_fasilitas = "Kolam renang"),
            Fasilitas(nama_fasilitas = "Kolam ikan"),
        )
    }
}