package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityInputTanahBinding

class InputTanah : AppCompatActivity() {
    private lateinit var binding: ActivityInputTanahBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputTanahBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            btnNext.setOnClickListener {
                val intentToInputVideo = Intent(this@InputTanah, InputVideo::class.java)
                startActivity(intentToInputVideo)
            }
        }
    }
}