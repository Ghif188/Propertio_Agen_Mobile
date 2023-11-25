package com.example.myapplication.input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.DetailProperti
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
        }
    }
}