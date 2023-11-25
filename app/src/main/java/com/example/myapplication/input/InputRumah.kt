package com.example.myapplication.input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityInputRumahBinding

class InputRumah : AppCompatActivity() {
    private lateinit var binding: ActivityInputRumahBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputRumahBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputRumah, InputVideo::class.java)
                startActivity(intentToInputVideo)
            }
            btnBack.setOnClickListener{
                val intentToInputLokasi = Intent(this@InputRumah, InputLokasi::class.java)
                startActivity(intentToInputLokasi)
            }
        }
    }
}