package com.example.myapplication.input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.input.InputLokasi
import com.example.myapplication.input.InputVideo
import com.example.myapplication.databinding.ActivityInputApartemenBinding

class InputApartemen : AppCompatActivity() {
    private lateinit var binding: ActivityInputApartemenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputApartemenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputApartemen, InputVideo::class.java)
                startActivity(intentToInputVideo)
            }
            btnBack.setOnClickListener{
                val intentToInputLokasi = Intent(this@InputApartemen, InputLokasi::class.java)
                startActivity(intentToInputLokasi)
            }
        }
    }
}