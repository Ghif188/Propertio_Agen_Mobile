package com.example.myapplication.input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityInputVideoBinding
import com.example.myapplication.input.InputFasilitasProperti
import com.example.myapplication.model.FormFoto
import com.example.myapplication.model.FormProperti
import java.io.Serializable

class InputVideo : AppCompatActivity() {
    private lateinit var binding: ActivityInputVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputVideoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val dataTemp = intent.extras?.get("temp") as FormProperti
        with(binding){
            btnNext.setOnClickListener {
                dataTemp.namaVirtualTour = namaVirtualTour.text.toString()
                dataTemp.linkVideo = linkVideo.text.toString()
                dataTemp.linkVirtualTour = linkVirtualTour.text.toString()
                dataTemp.fotoProperti = listOf(FormFoto())
                val intentToInputFasilitas = Intent(this@InputVideo, InputFasilitasProperti::class.java)
                intentToInputFasilitas.putExtra("temp", dataTemp as Serializable)
                startActivity(intentToInputFasilitas)
            }
            btnBack.setOnClickListener{
                finish()
            }
        }
    }
}