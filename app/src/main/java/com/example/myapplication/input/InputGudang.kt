package com.example.myapplication.input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.input.InputLokasi
import com.example.myapplication.input.InputVideo
import com.example.myapplication.databinding.ActivityInputGudangBinding

class InputGudang : AppCompatActivity() {
    private lateinit var binding: ActivityInputGudangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputGudangBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputGudang, InputVideo::class.java)
                startActivity(intentToInputVideo)
            }
            btnBack.setOnClickListener{
                val intentToInputLokasi = Intent(this@InputGudang, InputLokasi::class.java)
                startActivity(intentToInputLokasi)
            }
        }
    }
}